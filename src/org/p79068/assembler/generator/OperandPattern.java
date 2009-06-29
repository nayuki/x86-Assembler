package org.p79068.assembler.generator;

import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register16;
import org.p79068.assembler.operand.Register32;
import org.p79068.assembler.operand.Register8;
import org.p79068.assembler.operand.SegmentRegister;


public abstract class OperandPattern {
	
	public static OperandPattern IMM8 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Immediate && ((Immediate)op).is8Bit(); } };
	public static OperandPattern IMM16 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Immediate && ((Immediate)op).is16Bit(); } };
	public static OperandPattern IMM32 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Immediate; } };
	
	public static OperandPattern IMM_VAL_1 = new LiteralOperandSlot(new Immediate(1));
	public static OperandPattern IMM_VAL_3 = new LiteralOperandSlot(new Immediate(3));
	
	public static OperandPattern RM8 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register8 || op instanceof Memory32; } };
	public static OperandPattern RM16 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register16 || op instanceof Memory32; } };
	public static OperandPattern RM32 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register32 || op instanceof Memory32; } };
	
	public static OperandPattern MEM = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Memory32; } };
	
	public static OperandPattern REG8 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register8; } };
	public static OperandPattern REG16 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register16; } };
	public static OperandPattern REG32 = new OperandPattern() { public boolean matches(Operand op) { return op instanceof Register32; } };
	
	public static OperandPattern SREG = new OperandPattern() { public boolean matches(Operand op) { return op instanceof SegmentRegister; } };
	
	public static OperandPattern AL = new LiteralOperandSlot(Register8.AL_REGISTER);
	public static OperandPattern AH = new LiteralOperandSlot(Register8.AH_REGISTER);
	public static OperandPattern BL = new LiteralOperandSlot(Register8.BL_REGISTER);
	public static OperandPattern BH = new LiteralOperandSlot(Register8.BH_REGISTER);
	public static OperandPattern CL = new LiteralOperandSlot(Register8.CL_REGISTER);
	public static OperandPattern CH = new LiteralOperandSlot(Register8.CH_REGISTER);
	public static OperandPattern DL = new LiteralOperandSlot(Register8.DL_REGISTER);
	public static OperandPattern DH = new LiteralOperandSlot(Register8.DH_REGISTER);
	
	public static OperandPattern AX = new LiteralOperandSlot(Register16.AX_REGISTER);
	public static OperandPattern BX = new LiteralOperandSlot(Register16.BX_REGISTER);
	public static OperandPattern CX = new LiteralOperandSlot(Register16.CX_REGISTER);
	public static OperandPattern DX = new LiteralOperandSlot(Register16.DX_REGISTER);
	public static OperandPattern SP = new LiteralOperandSlot(Register16.SP_REGISTER);
	public static OperandPattern BP = new LiteralOperandSlot(Register16.BP_REGISTER);
	public static OperandPattern SI = new LiteralOperandSlot(Register16.SI_REGISTER);
	public static OperandPattern DI = new LiteralOperandSlot(Register16.DI_REGISTER);
	
	public static OperandPattern EAX = new LiteralOperandSlot(Register32.EAX_REGISTER);
	public static OperandPattern EBX = new LiteralOperandSlot(Register32.EBX_REGISTER);
	public static OperandPattern ECX = new LiteralOperandSlot(Register32.ECX_REGISTER);
	public static OperandPattern EDX = new LiteralOperandSlot(Register32.EDX_REGISTER);
	public static OperandPattern ESP = new LiteralOperandSlot(Register32.ESP_REGISTER);
	public static OperandPattern EBP = new LiteralOperandSlot(Register32.EBP_REGISTER);
	public static OperandPattern ESI = new LiteralOperandSlot(Register32.ESI_REGISTER);
	public static OperandPattern EDI = new LiteralOperandSlot(Register32.EDI_REGISTER);
	
	public static OperandPattern CS = new LiteralOperandSlot(SegmentRegister.CS_REGISTER);
	public static OperandPattern DS = new LiteralOperandSlot(SegmentRegister.DS_REGISTER);
	public static OperandPattern ES = new LiteralOperandSlot(SegmentRegister.ES_REGISTER);
	public static OperandPattern FS = new LiteralOperandSlot(SegmentRegister.FS_REGISTER);
	public static OperandPattern GS = new LiteralOperandSlot(SegmentRegister.GS_REGISTER);
	public static OperandPattern SS = new LiteralOperandSlot(SegmentRegister.SS_REGISTER);
	
	
	
	public abstract boolean matches(Operand operand);
	
	
	
	
	private static class LiteralOperandSlot extends OperandPattern {
		
		private Operand literal;
		
		
		public LiteralOperandSlot(Operand literal) {
			this.literal = literal;
		}
		
		@Override
		public boolean matches(Operand operand) {
			return operand.equals(literal);
		}
		
	}
	
}
