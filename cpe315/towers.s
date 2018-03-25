	.arch armv6
	.fpu vfp
	.text

    .global	print
print:
	stmfd	sp!, {r3, lr}
	mov	r3, r0
	mov	r2, r1
	ldr	r0, startstring
	mov	r1, r3
	bl	printf
	ldmfd	sp!, {r3, pc}

startstring:
	.word	string0

    .global	towers
towers:
   /* Save Registers */
   Push {r4, r5, r6, r7, r8, lr}
   /* Save a copy of all 3 original parameters */
   mov r4, r0  @r4 is numDiscs
   mov r5, r1  @r5 is start
   mov r6, r2  @r6 is goal 
if: 
   /* Compare numDisks with 2 or (numDisks - 2)*/
   cmp r4, #2
   /* Check if less than, else branch to else */
   bge else   

   /* set start to start for printing */
   mov r0, r5
   /* set end to end for printing */
   mov r1, r6
   /* call print function */
   bl printdata
   /* Set return register to 1 */
   mov r0, #1
   /* branch to endif */
   b endif
else:
   /* Use a saved varable for temp and set it to 6 */
   mov r7, #6 @r7 is "peg/temp"
   /* Subract start from temp and store to itself */
   sub r7, r7, r1
   /* Subtract goal from temp and store to itself (temp = 6 - start - goal)*/
   sub r7, r7, r2
   /* subract 1 from original NUMDISKS and store it to numDisks. */
   sub r0, r4, #1 

   /* Set end as temp */
   mov r2, r7

   /* Call towers function */
   bl towers
   /* Save result to saved variable for total steps */
   mov r8, r0 @r8 is steps
   /* Set numDiscs to 1 */
   mov r0, #1
   /* Set start to original start */
    mov r1, r5
   /* Set goal to original goal */
    mov r2, r6
   /* Call towers function */
   bl towers
   /* Add result to total steps so far */
   add r8, r8, r0
   /* Set numDisks to original numDisks - 1 */
   sub r0, r4, #1
   /* set start to temp */
   mov r1, r7
   /* set goal to original goal */
   mov r2, r6
   /* Call towers function */
   bl towers
   /* Add result to total steps so far and save it to return register */
   add r0, r8, r0
endif:
   /* Restore Registers */
   Pop {r4, r5, r6, r7, r8, pc}

    .global	main
main:
	str	lr, [sp, #-4]!
	sub	sp, sp, #20
	ldr	r0, printdata
	bl	printf
	ldr	r0, printdata+4
	add	r1, sp, #12
	bl	__isoc99_scanf
	ldr	r0, [sp, #12]
	mov	r1, #1
	mov	r2, #3
	bl	towers
	str	r0, [sp]
	ldr	r0, printdata+8
	ldr	r1, [sp, #12]
	mov	r2, #1
	mov	r3, #3
	bl	printf
	mov	r0, #0
	add	sp, sp, #20
	ldr	pc, [sp], #4
end:

printdata:
	.word	string1
	.word	string2
	.word	string3

string0:
	.ascii	"Move from peg %d to peg %d\012\000"
        .align 2
string1:
	.ascii	"Enter the number of disks to be moved: \000"
        .align 2
string2:
	.ascii	"%d\000"
        .align 2
	.space	1
string3:
	.ascii	"\012%d disks moved from peg %d to peg %d in %d step"
	.align 2
        .ascii	"s\012\000"
        .align 2
