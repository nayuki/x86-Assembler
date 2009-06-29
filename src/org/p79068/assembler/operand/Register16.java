package org.p79068.assembler.operand;


/**
 * A 16-bit register operand.
 */
public final class Register16 extends Register {
	
	/** The AX register. */
	public static Register16 AX_REGISTER = new Register16("%ax", 0);
	
	/** The CX register. */
	public static Register16 CX_REGISTER = new Register16("%cx", 1);
	
	/** The DX register. */
	public static Register16 DX_REGISTER = new Register16("%dx", 2);
	
	/** The BX register. */
	public static Register16 BX_REGISTER = new Register16("%bx", 3);
	
	/** The SP register. */
	public static Register16 SP_REGISTER = new Register16("%sp", 4);
	
	/** The BP register. */
	public static Register16 BP_REGISTER = new Register16("%bp", 5);
	
	/** The SI register. */
	public static Register16 SI_REGISTER = new Register16("%si", 6);
	
	/** The DI register. */
	public static Register16 DI_REGISTER = new Register16("%di", 7);
	
	
	
	public static Register16 parseOperand(String name) {
		if (name.equals("%ax")) return AX_REGISTER;
		if (name.equals("%bx")) return BX_REGISTER;
		if (name.equals("%cx")) return CX_REGISTER;
		if (name.equals("%dx")) return DX_REGISTER;
		if (name.equals("%sp")) return SP_REGISTER;
		if (name.equals("%bp")) return BP_REGISTER;
		if (name.equals("%si")) return SI_REGISTER;
		if (name.equals("%di")) return DI_REGISTER;
		return null;
	}
	
	
	
	private String name;
	
	
	private Register16(String name, int registerNumber) {
		super(registerNumber);
		this.name = name;
	}
	
	
	
	public String toString() {
		return name;
	}
	
}