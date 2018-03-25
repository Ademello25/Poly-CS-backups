#include <stdio.h>
#include <stdlib.h>
#include "lwp.h"
 
#define TEMP_STACK_SIZE 100
 
/* set up the variables to manage the list of threads and their tids */
static int numThreads = 0;
 
/* creates a thread and adds it into the scheduling pool for use */
int new_lwp(lwpfun func, void *arg, size_t stack_size) {
   thread new;
unsigned long *new_TOS, *old_BP;
 
   if(!(new = malloc(sizeof(context))))
      return THREAD_FAILURE;
 
   if(!(new->stack = malloc(sizeof(unsigned long) * stack_size)))
      return THREAD_FAILURE;
   new->stacksize = stack_size;
   new->tid = ++numThreads;
 
   old_BP = new_TOS = new->stack + new->stacksize;
   *new_TOS = (unsigned long)lwp_exit;
   new_TOS--;
   *new_TOS = (unsigned long)func;
   new_TOS--;
   *new_TOS = (unsigned long)old_BP;
 
   new->state.rsp = new->state.rbp = (unsigned long)new_TOS;
   new->state.rdi = (unsigned long)arg;
   
   return new->tid;
}
 
/* terminates the current running thread and frees its associated resources */
void lwp_exit() {
   current_tid = lwp_getpid();
   thread next, current;
   unsigned long *free_TOS, *stop_TOS;
   static unsigned long free_stack[TEMP_STACK_SIZE], stop_stack[TEMP_STACK_SIZE];
   static context free_context, stop_context;
 
   /* initializes the stack for the return to lwp_free_thread() */
   free_context.stack = free_stack;
   free_TOS = &free_stack[TEMP_STACK_SIZE - 1];
   *free_TOS = (unsigned long)lwp_free_thread;
   free_TOS--;
   *free_TOS = (unsigned long)(free_TOS + 1);
   free_context.state.rbp = free_context.state.rsp = (unsigned long)free_TOS;
 
   /* initializes the stack for a possible return to lwp_stop */
   stop_context.stack = stop_stack;
   stop_TOS = &stop_stack[TEMP_STACK_SIZE - 1];
   *stop_TOS = (unsigned long)lwp_stop;
   stop_TOS--;
   *stop_TOS = (unsigned long)(stop_TOS + 1);
   stop_context.state.rbp = stop_context.state.rsp = (unsigned long)stop_TOS;
 
   swap_rfiles(&current->state, &free_context.state);
}
 
/* Should only be called within the lwp system.
 * Returns the tid of the thread that calls it. */
int lwp_getpid() {
   unsigned long current_rsp;
   thread cursor = lwp_tlist;
 
   GetSP(current_rsp);
 
   while (cursor && !((unsigned long)cursor->stack <= current_rsp && 
    (unsigned long)(cursor->stack + cursor->stacksize) >= current_rsp))
      cursor = cursor->tlist_next;
 
   if (cursor)
      return cursor->tid;
   else
      return NO_THREAD;
}
 
/* Should only be called by a thread within the lwp system -- will do
 * nothing if called from outside a thread.
 * Suspends the current running thread and switches to a new one as
 * provided by the current scheduler. */
void lwp_yield() {
   current_tid = lwp_getpid();
   thread next, current;
 
   if (!(current = lwp_get_thread(current_tid)))
      fprintf(stderr, "Error in lwp_yield(): current thread not found\n");
 
   if (next_tid != NO_THREAD) {
      if (!(next = lwp_get_thread(next_tid)))
         fprintf(stderr, "Error in lwp_yield(): next thread not found\n");
      else
         swap_rfiles(&current->state, &next->state);
   }
}
 
/* Starts the lwp system. Threads must be created for this to do
 * anything besides immediately return. */
void lwp_start() {
}
 
/* Suspends the lwp system and returns to the program that called
 * lwp_start(). Threads may be resumed with a subsequent call of lwp_start(). */
void lwp_stop() {
   tid_t current_tid = lwp_getpid();
   thread current;
 
   if (current = lwp_get_thread(current_tid))
      swap_rfiles(&current->state, &caller);
   else
      load_context(&caller);
}
 
/* Switches the current scheduler to the one passed to it.
 * If passed NULL, switches to round robin scheduling. */
void lwp_set_scheduler(scheduler func) {

}
