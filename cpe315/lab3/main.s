@ Peter Chu, Alex DeMello
@ Lab 3        
        
        .arch armv6
        .fpu vfp
        .text

    .global     main
main:
    push    {r4, r5, r6, ip, lr}
    
loop:
    ldr     r0, =msg1               @ prompt user for number1
    bl      printf
    ldr     r0, =scanf_fmt1         @ get value for number1
    mov     r1, sp                  @ load addr of sp to store number1 in
    bl      __isoc99_scanf
    ldr     r4, [sp]     

    ldr     r0, =msg2               @ prompt user for number2
    bl      printf
    ldr     r0, =scanf_fmt1         @ get value for number2
    mov     r1, sp                  @ load addr of sp to store number2 in
    bl      __isoc99_scanf
    ldr     r5, [sp]
    

    @ do multiplication here
    @ for now, simply echo the inputs
    mov     r0, r1
    mov     r1, r4
    mov     r2, r5
    bl      mymult                  @ multiply number1, number2
    mov     r1, r0
    ldr     r0, =msg3
    bl      printf
    
    ldr     r0, =msg4
    bl      printf
    ldr     r0, =scanf_fmt2
    mov     r1, sp
    bl      __isoc99_scanf
    ldrb    r6, [sp]
    cmp     r6, #121
    beq     loop

    mov     r0, #0                  @ Return 0
    pop     {r4, r5, r6, ip, pc}

scanf_fmt1: 
    .asciz  "%d"
scanf_fmt2:
    .asciz  " %c"
msg1:
    .asciz  "Enter Number 1: "
msg2:
    .asciz  "Enter Number 2: "
msg3:
    .asciz  "Product is: %d\n"
msg4:
    .asciz  "Again? "

    .data
    .balign 4
number1_addr:
    .word 0
number2_addr:
    .word 4
