package org.p79068.assembler.operand;


/**
 * A segment register operand. Immutable.
 */
public final class SegmentRegister extends Register {
	
	/** The ES register. */
	public static SegmentRegister ES_REGISTER = new SegmentRegister("%es", 0);
	
	/** The CS register. */
	public static SegmentRegister CS_REGISTER = new SegmentRegister("%cs", 1);
	
	/** The SS register. */
	public static SegmentRegister SS_REGISTER = new SegmentRegister("%ss", 2);
	
	/** The DS register. */
	public static SegmentRegister DS_REGISTER = new SegmentRegister("%ds", 3);
	
	/** The FS register. */
	public static SegmentRegister FS_REGISTER = new SegmentRegister("%fs", 4);
	
	/** The GS register. */
	public static SegmentRegister GS_REGISTER = new SegmentRegister("%gs", 5);
	
	
	
	public static SegmentRegister parseOperand(String name) {
		if (name.equals("%cs")) return CS_REGISTER;
		if (name.equals("%ds")) return DS_REGISTER;
		if (name.equals("%es")) return ES_REGISTER;
		if (name.equals("%fs")) return FS_REGISTER;
		if (name.equals("%gs")) return GS_REGISTER;
		if (name.equals("%ss")) return SS_REGISTER;
		return null;
	}
	
	
	
	private String name;
	
	
	
	private SegmentRegister(String name, int registerNumber) {
		super(registerNumber);
		this.name = name;
	}
	
	
	
	public String toString() {
		return name;
	}
	
}
