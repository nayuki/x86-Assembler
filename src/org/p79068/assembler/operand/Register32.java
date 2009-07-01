package org.p79068.assembler.operand;


/**
 * A 32-bit register operand. Immutable.
 */
public final class Register32 extends Register {
	
	/** The EAX register. */
	public static Register32 EAX_REGISTER = new Register32("%eax", 0);
	
	/** The ECX register. */
	public static Register32 ECX_REGISTER = new Register32("%ecx", 1);
	
	/** The EDX register. */
	public static Register32 EDX_REGISTER = new Register32("%edx", 2);
	
	/** The EBX register. */
	public static Register32 EBX_REGISTER = new Register32("%ebx", 3);
	
	/** The ESP register. */
	public static Register32 ESP_REGISTER = new Register32("%esp", 4);
	
	/** The EBP register. */
	public static Register32 EBP_REGISTER = new Register32("%ebp", 5);
	
	/** The ESI register. */
	public static Register32 ESI_REGISTER = new Register32("%esi", 6);
	
	/** The EDI register. */
	public static Register32 EDI_REGISTER = new Register32("%edi", 7);
	
	
	
	private Register32(String name, int registerNumber) {
		super(name, registerNumber);
	}
	
}
