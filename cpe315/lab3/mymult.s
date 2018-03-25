@ Peter Chu, Alex DeMello
@ Lab 3     
     
      .arch armv6           
      .fpu vfp
      .text

   .global  mymult
mymult:
      push {r4, r5, r6, r7, lr}
      mov   r6, #0          @set product to 0.
loop: 
      cmp   r5, #0          @ compare multiplier to 0, if not zero loop, if zero endloop 
      beq   endloop
      and   r7, r5, #1      @check LSB of rmultiplier        
      cmp   r7, #1          @if its 1 product = product + mutliplicand, if not skip to shifting
      bne   endif
      
      mov   r0, r6
      mov   r1, r4
      mov   r2, r6
      bl    myadd
      mov   r6, r0
endif:
      lsrs  r5, r5, #1 
      lsls  r4, r4, #1
      b loop
endloop:
      mov r0, r6
      pop {r4, r5, r6, r7, pc}
