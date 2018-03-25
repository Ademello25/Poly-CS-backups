@Lab 1 Alex DeMello , Peter Chu
    .syntax unified

    .global main
main:
    push    {ip, lr}

    ldr r0, =line1
    bl    printf

    ldr r0, =line2
    bl    printf

    ldr r0, =line3
    bl    printf

    mov r0, #0
    pop    {ip, pc}

line1:
    .asciz "Hello Raspberry Pi\n"
line2:
    .asciz "You are a wonderful thing\n"
line3:
    .asciz "I want to eat you\n"
