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
	
	public static OperandPattern AL = new LiteralOperandPattern(Register8.AL_REGISTER);
	public static OperandPattern AH = new LiteralOperandPattern(Register8.AH_REGISTER);
	public static OperandPattern BL = new LiteralOperandPattern(Register8.BL_REGISTER);
	public static OperandPattern BH = new LiteralOperandPattern(Register8.BH_REGISTER);
	public static OperandPattern CL = new LiteralOperandPattern(Register8.CL_REGISTER);
	public static OperandPattern CH = new LiteralOperandPattern(Register8.CH_REGISTER);
	public static OperandPattern DL = new LiteralOperandPattern(Register8.DL_REGISTER);
	public static OperandPattern DH = new LiteralOperandPattern(Register8.DH_REGISTER);
	
	public static OperandPattern AX = new LiteralOperandPattern(Register16.AX_REGISTER);
	public static OperandPattern BX = new LiteralOperandPattern(Register16.BX_REGISTER);
	public static OperandPattern CX = new LiteralOperandPattern(Register16.CX_REGISTER);
	public static OperandPattern DX = new LiteralOperandPattern(Register16.DX_REGISTER);
	public static OperandPattern SP = new LiteralOperandPattern(Register16.SP_REGISTER);
	public static OperandPattern BP = new LiteralOperandPattern(Register16.BP_REGISTER);
	public static OperandPattern SI = new LiteralOperandPattern(Register16.SI_REGISTER);
	public static OperandPattern DI = new LiteralOperandPattern(Register16.DI_REGISTER);
	
	public static OperandPattern EAX = new LiteralOperandPattern(Register32.EAX_REGISTER);
	public static OperandPattern EBX = new LiteralOperandPattern(Register32.EBX_REGISTER);
	public static OperandPattern ECX = new LiteralOperandPattern(Register32.ECX_REGISTER);
	public static OperandPattern EDX = new LiteralOperandPattern(Register32.EDX_REGISTER);
	public static OperandPattern ESP = new LiteralOperandPattern(Register32.ESP_REGISTER);
	public static OperandPattern EBP = new LiteralOperandPattern(Register32.EBP_REGISTER);
	public static OperandPattern ESI = new LiteralOperandPattern(Register32.ESI_REGISTER);
	public static OperandPattern EDI = new LiteralOperandPattern(Register32.EDI_REGISTER);
	
	public static OperandPattern CS = new LiteralOperandPattern(SegmentRegister.CS_REGISTER);
	public static OperandPattern DS = new LiteralOperandPattern(SegmentRegister.DS_REGISTER);
	public static OperandPattern ES = new LiteralOperandPattern(SegmentRegister.ES_REGISTER);
	public static OperandPattern FS = new LiteralOperandPattern(SegmentRegister.FS_REGISTER);
	public static OperandPattern GS = new LiteralOperandPattern(SegmentRegister.GS_REGISTER);
	public static OperandPattern SS = new LiteralOperandPattern(SegmentRegister.SS_REGISTER);
	
	
	
	private String name;
	
	
	
	public OperandPattern(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	public abstract boolean matches(Operand operand);
	
	
	public String toString() {
		return name;
	}
	
}
