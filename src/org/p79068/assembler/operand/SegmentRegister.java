package org.p79068.assembler.operand;


/**
 * A segment register operand. Immutable.
 */
public final class SegmentRegister extends Register {
	
	/** The ES register. */
	public static SegmentRegister ES = new SegmentRegister("es", 0);
	
	/** The CS register. */
	public static SegmentRegister CS = new SegmentRegister("cs", 1);
	
	/** The SS register. */
	public static SegmentRegister SS = new SegmentRegister("ss", 2);
	
	/** The DS register. */
	public static SegmentRegister DS = new SegmentRegister("ds", 3);
	
	/** The FS register. */
	public static SegmentRegister FS = new SegmentRegister("fs", 4);
	
	/** The GS register. */
	public static SegmentRegister GS = new SegmentRegister("gs", 5);
	
	
	
	private SegmentRegister(String name, int registerNumber) {
		super(name, registerNumber);
	}
	
}
