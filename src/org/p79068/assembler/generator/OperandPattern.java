package org.p79068.assembler.generator;

import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.ImmediateValue;
import org.p79068.assembler.operand.Label;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register16;
import org.p79068.assembler.operand.Register32;
import org.p79068.assembler.operand.Register8;
import org.p79068.assembler.operand.SegmentRegister;


abstract class OperandPattern {
	
	public static OperandPattern IMM8  = new OperandPattern("imm8" ) { public boolean matches(Operand op) { return op instanceof Label || op instanceof ImmediateValue && ((ImmediateValue)op).is8Bit(); } };
	public static OperandPattern IMM16 = new OperandPattern("imm16") { public boolean matches(Operand op) { return op instanceof Label || op instanceof ImmediateValue && ((ImmediateValue)op).is16Bit(); } };
	public static OperandPattern IMM32 = new OperandPattern("imm32") { public boolean matches(Operand op) { return op instanceof Label || op instanceof ImmediateValue; } };
	
	public static OperandPattern IMM8S = new OperandPattern("imm8s") { public boolean matches(Operand op) { return op instanceof ImmediateValue && ((ImmediateValue)op).isSigned8Bit(); } };
	
	public static OperandPattern REL8  = new OperandPattern("rel8" ) { public boolean matches(Operand op) { return op instanceof Immediate; } };
	public static OperandPattern REL16 = new OperandPattern("rel16") { public boolean matches(Operand op) { return op instanceof Immediate; } };
	public static OperandPattern REL32 = new OperandPattern("rel32") { public boolean matches(Operand op) { return op instanceof Immediate; } };
	
	public static OperandPattern IMM_VAL_1 = new LiteralOperandPattern(new ImmediateValue(1));
	public static OperandPattern IMM_VAL_3 = new LiteralOperandPattern(new ImmediateValue(3));
	
	public static OperandPattern MEM = new OperandPattern("mem") { public boolean matches(Operand op) { return op instanceof Memory32; } };
	
	public static OperandPattern RM8  = new OperandPattern("r/m8" ) { public boolean matches(Operand op) { return op instanceof Register8  || op instanceof Memory32; } };
	public static OperandPattern RM16 = new OperandPattern("r/m16") { public boolean matches(Operand op) { return op instanceof Register16 || op instanceof Memory32; } };
	public static OperandPattern RM32 = new OperandPattern("r/m32") { public boolean matches(Operand op) { return op instanceof Register32 || op instanceof Memory32; } };
	
	public static OperandPattern REG8  = new OperandPattern("reg8" ) { public boolean matches(Operand op) { return op instanceof Register8; } };
	public static OperandPattern REG16 = new OperandPattern("reg16") { public boolean matches(Operand op) { return op instanceof Register16; } };
	public static OperandPattern REG32 = new OperandPattern("reg32") { public boolean matches(Operand op) { return op instanceof Register32; } };
	public static OperandPattern SREG  = new OperandPattern("sreg")  { public boolean matches(Operand op) { return op instanceof SegmentRegister; } };
	
	public static OperandPattern AL = new LiteralOperandPattern(Register8.AL);
	public static OperandPattern AH = new LiteralOperandPattern(Register8.AH);
	public static OperandPattern BL = new LiteralOperandPattern(Register8.BL);
	public static OperandPattern BH = new LiteralOperandPattern(Register8.BH);
	public static OperandPattern CL = new LiteralOperandPattern(Register8.CL);
	public static OperandPattern CH = new LiteralOperandPattern(Register8.CH);
	public static OperandPattern DL = new LiteralOperandPattern(Register8.DL);
	public static OperandPattern DH = new LiteralOperandPattern(Register8.DH);
	
	public static OperandPattern AX = new LiteralOperandPattern(Register16.AX);
	public static OperandPattern BX = new LiteralOperandPattern(Register16.BX);
	public static OperandPattern CX = new LiteralOperandPattern(Register16.CX);
	public static OperandPattern DX = new LiteralOperandPattern(Register16.DX);
	public static OperandPattern SP = new LiteralOperandPattern(Register16.SP);
	public static OperandPattern BP = new LiteralOperandPattern(Register16.BP);
	public static OperandPattern SI = new LiteralOperandPattern(Register16.SI);
	public static OperandPattern DI = new LiteralOperandPattern(Register16.DI);
	
	public static OperandPattern EAX = new LiteralOperandPattern(Register32.EAX);
	public static OperandPattern EBX = new LiteralOperandPattern(Register32.EBX);
	public static OperandPattern ECX = new LiteralOperandPattern(Register32.ECX);
	public static OperandPattern EDX = new LiteralOperandPattern(Register32.EDX);
	public static OperandPattern ESP = new LiteralOperandPattern(Register32.ESP);
	public static OperandPattern EBP = new LiteralOperandPattern(Register32.EBP);
	public static OperandPattern ESI = new LiteralOperandPattern(Register32.ESI);
	public static OperandPattern EDI = new LiteralOperandPattern(Register32.EDI);
	
	public static OperandPattern CS = new LiteralOperandPattern(SegmentRegister.CS);
	public static OperandPattern DS = new LiteralOperandPattern(SegmentRegister.DS);
	public static OperandPattern ES = new LiteralOperandPattern(SegmentRegister.ES);
	public static OperandPattern FS = new LiteralOperandPattern(SegmentRegister.FS);
	public static OperandPattern GS = new LiteralOperandPattern(SegmentRegister.GS);
	public static OperandPattern SS = new LiteralOperandPattern(SegmentRegister.SS);
	
	
	
	private String name;
	
	
	
	public OperandPattern(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	public abstract boolean matches(Operand operand);
	
	
	/**
	 * Returns a string representation of this operand pattern. The format is subjected to change.
	 * @return a string representation of this operand pattern
	 */
	public String toString() {
		return name;
	}
	
}
