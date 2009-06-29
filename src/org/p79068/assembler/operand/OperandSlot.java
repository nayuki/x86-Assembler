package org.p79068.assembler.operand;


public abstract class OperandSlot {
	
	public abstract boolean matches(Operand operand);
	
	
	
	private static class LiteralOperandSlot extends OperandSlot {
		
		private Operand literal;
		
		
		public LiteralOperandSlot(Operand literal) {
			this.literal = literal;
		}
		
		@Override
		public boolean matches(Operand operand) {
			return operand.equals(literal);
		}
		
	}
	
	
	
	public static OperandSlot IMM8 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Immediate && ((Immediate)op).is8Bit(); } };
	public static OperandSlot IMM16 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Immediate && ((Immediate)op).is16Bit(); } };
	public static OperandSlot IMM32 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Immediate; } };
	
	public static OperandSlot IMM_VAL_1 = new LiteralOperandSlot(new Immediate(1));
	public static OperandSlot IMM_VAL_3 = new LiteralOperandSlot(new Immediate(3));
	
	public static OperandSlot RM8 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register8 || op instanceof Memory32; } };
	public static OperandSlot RM16 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register16 || op instanceof Memory32; } };
	public static OperandSlot RM32 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register32 || op instanceof Memory32; } };
	
	public static OperandSlot MEM = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Memory32; } };
	
	public static OperandSlot REG8 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register8; } };
	public static OperandSlot REG16 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register16; } };
	public static OperandSlot REG32 = new OperandSlot() { public boolean matches(Operand op) { return op instanceof Register32; } };
	
	public static OperandSlot SREG = new OperandSlot() { public boolean matches(Operand op) { return op instanceof SegmentRegister; } };
	
	public static OperandSlot AL = new LiteralOperandSlot(Register8.AL_REGISTER);
	public static OperandSlot AH = new LiteralOperandSlot(Register8.AH_REGISTER);
	public static OperandSlot BL = new LiteralOperandSlot(Register8.BL_REGISTER);
	public static OperandSlot BH = new LiteralOperandSlot(Register8.BH_REGISTER);
	public static OperandSlot CL = new LiteralOperandSlot(Register8.CL_REGISTER);
	public static OperandSlot CH = new LiteralOperandSlot(Register8.CH_REGISTER);
	public static OperandSlot DL = new LiteralOperandSlot(Register8.DL_REGISTER);
	public static OperandSlot DH = new LiteralOperandSlot(Register8.DH_REGISTER);
	
	public static OperandSlot AX = new LiteralOperandSlot(Register16.AX_REGISTER);
	public static OperandSlot BX = new LiteralOperandSlot(Register16.BX_REGISTER);
	public static OperandSlot CX = new LiteralOperandSlot(Register16.CX_REGISTER);
	public static OperandSlot DX = new LiteralOperandSlot(Register16.DX_REGISTER);
	public static OperandSlot SP = new LiteralOperandSlot(Register16.SP_REGISTER);
	public static OperandSlot BP = new LiteralOperandSlot(Register16.BP_REGISTER);
	public static OperandSlot SI = new LiteralOperandSlot(Register16.SI_REGISTER);
	public static OperandSlot DI = new LiteralOperandSlot(Register16.DI_REGISTER);
	
	public static OperandSlot EAX = new LiteralOperandSlot(Register32.EAX_REGISTER);
	public static OperandSlot EBX = new LiteralOperandSlot(Register32.EBX_REGISTER);
	public static OperandSlot ECX = new LiteralOperandSlot(Register32.ECX_REGISTER);
	public static OperandSlot EDX = new LiteralOperandSlot(Register32.EDX_REGISTER);
	public static OperandSlot ESP = new LiteralOperandSlot(Register32.ESP_REGISTER);
	public static OperandSlot EBP = new LiteralOperandSlot(Register32.EBP_REGISTER);
	public static OperandSlot ESI = new LiteralOperandSlot(Register32.ESI_REGISTER);
	public static OperandSlot EDI = new LiteralOperandSlot(Register32.EDI_REGISTER);
	
	public static OperandSlot CS = new LiteralOperandSlot(SegmentRegister.CS_REGISTER);
	public static OperandSlot DS = new LiteralOperandSlot(SegmentRegister.DS_REGISTER);
	public static OperandSlot ES = new LiteralOperandSlot(SegmentRegister.ES_REGISTER);
	public static OperandSlot FS = new LiteralOperandSlot(SegmentRegister.FS_REGISTER);
	public static OperandSlot GS = new LiteralOperandSlot(SegmentRegister.GS_REGISTER);
	public static OperandSlot SS = new LiteralOperandSlot(SegmentRegister.SS_REGISTER);
	
}