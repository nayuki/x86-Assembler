# This program prints the numbers 0 to 99, one per line.

# The calling convention is that all registers are caller-save.

main:
	movl %eax, $0
main_loop:
	pushl %eax
	call $print_int
	call $delay
	popl %eax
	incl %eax
	cmpl %eax, $100
	jne $main_loop
	
	call $exit


# Prints the integer contained in %eax, in base 10.
print_int:
	movl %ebp, %esp  # Preserve ESP
	movl %ecx, $0   # Count
	
	# New line character at the end
	decl %esp
	movb (%esp), $10
	incl %ecx
	
	movl %ebx, $10  # Divisor
print_int_loop:
	movl %edx, $0
	divl %ebx
	addl %edx, $48
	decl %esp
	movb (%esp), %dl
	incl %ecx
	cmpl %eax, $0
	jne $print_int_loop
	
	# System call write
	movl %eax, $4    # System call #4 (sys_write)
	movl %ebx, $1    # File descriptor 1 (stdout)
	movl %edx, %ecx  # Buffer length
	movl %ecx, %esp  # Buffer
	int $0x80
	
	movl %esp, %ebp  # Restore ESP
	ret


# Returns after a delay.
delay:
	movl %eax, $300000000
delay_loop:
	decl %eax
	jnz $delay_loop
	ret


# The exit system call (does not return)
exit:
	movl %eax, $1    # System call #1 (sys_exit)
	movl %ebx, $0    # Exit code 0
	int $0x80
