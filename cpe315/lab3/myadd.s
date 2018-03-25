@ Peter Chu, Alex DeMello
@ Lab 3      
      
      .arch armv6 
      .fpu vfp
      .text

   .global  myadd
myadd:
      push  {r4, r5, r6, ip, lr}
      
      mov   r4, r0      @ save a to r4
      mov   r5, r1      @ save b to r5
      mov   r6, #0      @ set carry to 0.
loop: 
      cmp   r5, #0      @ if b is equal to 0
      beq   endloop
      ands  r6, r4, r5
      eors  r4, r4, r5  @ exors a and b
      lsls  r5, r6, #1  @ carry is shifted by one
      b     loop
endloop:
      mov   r0, r4      @returning r4 (a)
      pop   {r4, r5, r6, ip, pc}
