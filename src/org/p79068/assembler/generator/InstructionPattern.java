package org.p79068.assembler.generator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.p79068.assembler.operand.OperandSizeMode;
import org.p79068.assembler.operand.OperandSlot;

import static org.p79068.assembler.operand.OperandSlot.*;
import static org.p79068.assembler.operand.OperandSizeMode.*;


public final class InstructionPattern {
	
	/**
	 * The regular expression pattern for mnemonics, which is one lowercase letter followed by zero or more lowercase or numeric characters.
	 */
	private static Pattern MNEMONIC_PATTERN = Pattern.compile("[a-z][a-z0-9]*");
	
	
	public static final Set<InstructionPattern> MODE32_PATTERN_TABLE;
	
	static {
		Set<InstructionPattern> mode32 = new HashSet<InstructionPattern>();
		
		mode32.add(new InstructionPattern("addb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x00}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x01}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x01}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x02}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x03}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x03}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x04}));
		mode32.add(new InstructionPattern("addw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x05}));
		mode32.add(new InstructionPattern("addl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x05}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{ES}                  , MODELESS, new int[]{0x06}));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{ES}                  , MODELESS, new int[]{0x07}));
		mode32.add(new InstructionPattern("orb"   , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x08}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orw"   , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x09}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orl"   , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x09}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orb"   , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x0A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orw"   , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x0B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orl"   , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x0B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orb"   , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x0C}));
		mode32.add(new InstructionPattern("orw"   , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x0D}));
		mode32.add(new InstructionPattern("orl"   , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x0D}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{CS}                  , MODELESS, new int[]{0x0E}));
		mode32.add(new InstructionPattern("adcb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x10}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x11}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x11}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x12}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x13}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x13}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x14}));
		mode32.add(new InstructionPattern("adcw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x15}));
		mode32.add(new InstructionPattern("adcl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x15}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{SS}                  , MODELESS, new int[]{0x16}));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{SS}                  , MODELESS, new int[]{0x17}));
		mode32.add(new InstructionPattern("sbbb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x18}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x19}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x19}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x1A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x1B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x1B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x1C}));
		mode32.add(new InstructionPattern("sbbw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x1D}));
		mode32.add(new InstructionPattern("sbbl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x1D}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{DS}                  , MODELESS, new int[]{0x1E}));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{DS}                  , MODELESS, new int[]{0x1F}));
		mode32.add(new InstructionPattern("andb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x20}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x21}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x21}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x22}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x23}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x23}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x24}));
		mode32.add(new InstructionPattern("andw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x25}));
		mode32.add(new InstructionPattern("andl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x25}));
		mode32.add(new InstructionPattern("daa"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x27}));
		mode32.add(new InstructionPattern("subb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x28}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x29}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x29}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x2A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x2B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x2B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x2C}));
		mode32.add(new InstructionPattern("subw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x2D}));
		mode32.add(new InstructionPattern("subl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x2D}));
		mode32.add(new InstructionPattern("das"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x2F}));
		mode32.add(new InstructionPattern("xorb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x30}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x31}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x31}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x32}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x33}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x33}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x34}));
		mode32.add(new InstructionPattern("xorw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x35}));
		mode32.add(new InstructionPattern("xorl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x35}));
		mode32.add(new InstructionPattern("aaa"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x37}));
		mode32.add(new InstructionPattern("cmpb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x38}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x39}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x39}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x3A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x3B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x3B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0x3C}));
		mode32.add(new InstructionPattern("cmpw"  , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0x3D}));
		mode32.add(new InstructionPattern("cmpl"  , new OperandSlot[]{EAX, IMM32}          , MODE32  , new int[]{0x3D}));
		mode32.add(new InstructionPattern("aas"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x3F}));
		mode32.add(new InstructionPattern("incw"  , new OperandSlot[]{REG16}               , MODE16  , new int[]{0x40}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("incl"  , new OperandSlot[]{REG32}               , MODE32  , new int[]{0x40}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("decw"  , new OperandSlot[]{REG16}               , MODE16  , new int[]{0x48}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("decl"  , new OperandSlot[]{REG32}               , MODE32  , new int[]{0x48}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{REG16}               , MODE16  , new int[]{0x50}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushl" , new OperandSlot[]{REG32}               , MODE32  , new int[]{0x50}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{REG16}               , MODE16  , new int[]{0x58}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("popl"  , new OperandSlot[]{REG32}               , MODE32  , new int[]{0x58}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushaw", new OperandSlot[]{}                    , MODE16  , new int[]{0x60}));
		mode32.add(new InstructionPattern("pushal", new OperandSlot[]{}                    , MODE32  , new int[]{0x60}));
		mode32.add(new InstructionPattern("popaw" , new OperandSlot[]{}                    , MODE16  , new int[]{0x61}));
		mode32.add(new InstructionPattern("popal" , new OperandSlot[]{}                    , MODE32  , new int[]{0x61}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{IMM16}               , MODE16  , new int[]{0x68}));
		mode32.add(new InstructionPattern("pushl" , new OperandSlot[]{IMM32}               , MODE32  , new int[]{0x68}));
		mode32.add(new InstructionPattern("imulw" , new OperandSlot[]{REG16, RM16, IMM16}  , MODE16  , new int[]{0x69}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("imull" , new OperandSlot[]{REG32, RM32, IMM32}  , MODE32  , new int[]{0x69}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("pushb" , new OperandSlot[]{IMM8}                , MODELESS, new int[]{0x6A}));
		mode32.add(new InstructionPattern("imulwb", new OperandSlot[]{REG16, RM16, IMM8}   , MODE16  , new int[]{0x6B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("imullb", new OperandSlot[]{REG32, RM32, IMM8}   , MODE32  , new int[]{0x6B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orb"   , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orw"   , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orl"   , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orwb"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpwb" , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0x83}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addlb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orlb"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adclb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbblb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andlb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sublb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorlb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmplb" , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0x83}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testb" , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x84}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("testw" , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x85}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("testl" , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x85}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgb" , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x86}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgw" , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x87}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgl" , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x87}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movb"  , new OperandSlot[]{RM8, REG8}           , MODELESS, new int[]{0x88}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{RM16, REG16}         , MODE16  , new int[]{0x89}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movl"  , new OperandSlot[]{RM32, REG32}         , MODE32  , new int[]{0x89}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movb"  , new OperandSlot[]{REG8, RM8}           , MODELESS, new int[]{0x8A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{REG16, RM16}         , MODE16  , new int[]{0x8B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movl"  , new OperandSlot[]{REG32, RM32}         , MODE32  , new int[]{0x8B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{RM16, SREG}          , MODE16  , new int[]{0x8C}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movlw" , new OperandSlot[]{RM32, SREG}          , MODE32  , new int[]{0x8C}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{SREG, RM16}          , MODE16  , new int[]{0x8E}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movwl" , new OperandSlot[]{SREG, RM32}          , MODE32  , new int[]{0x8E}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{RM16}                , MODE16  , new int[]{0x8F}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("popl"  , new OperandSlot[]{RM32}                , MODE32  , new int[]{0x8F}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("nop"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x90}));
		mode32.add(new InstructionPattern("xchgw" , new OperandSlot[]{REG16}               , MODE16  , new int[]{0x90}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("xchgl" , new OperandSlot[]{REG32}               , MODE32  , new int[]{0x90}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("cbw"   , new OperandSlot[]{}                    , MODE16  , new int[]{0x98}));
		mode32.add(new InstructionPattern("cwde"  , new OperandSlot[]{}                    , MODE32  , new int[]{0x98}));
		mode32.add(new InstructionPattern("cwd"   , new OperandSlot[]{}                    , MODE16  , new int[]{0x99}));
		mode32.add(new InstructionPattern("cdq"   , new OperandSlot[]{}                    , MODE32  , new int[]{0x99}));
		mode32.add(new InstructionPattern("sahf"  , new OperandSlot[]{}                    , MODELESS, new int[]{0x9E}));
		mode32.add(new InstructionPattern("lahf"  , new OperandSlot[]{}                    , MODELESS, new int[]{0x9F}));
		mode32.add(new InstructionPattern("testb" , new OperandSlot[]{AL, IMM8}            , MODELESS, new int[]{0xA8}));
		mode32.add(new InstructionPattern("testw" , new OperandSlot[]{AX, IMM16}           , MODE16  , new int[]{0xA9}));
		mode32.add(new InstructionPattern("testl" , new OperandSlot[]{AX, IMM32}           , MODE32  , new int[]{0xA9}));
		mode32.add(new InstructionPattern("stosb" , new OperandSlot[]{}                    , MODELESS, new int[]{0xAA}));
		mode32.add(new InstructionPattern("stosw" , new OperandSlot[]{}                    , MODE16  , new int[]{0xAB}));
		mode32.add(new InstructionPattern("stosl" , new OperandSlot[]{}                    , MODE32  , new int[]{0xAB}));
		mode32.add(new InstructionPattern("lodsb" , new OperandSlot[]{}                    , MODELESS, new int[]{0xAC}));
		mode32.add(new InstructionPattern("lodsw" , new OperandSlot[]{}                    , MODE16  , new int[]{0xAD}));
		mode32.add(new InstructionPattern("lodsl" , new OperandSlot[]{}                    , MODE32  , new int[]{0xAD}));
		mode32.add(new InstructionPattern("scasb" , new OperandSlot[]{}                    , MODELESS, new int[]{0xAE}));
		mode32.add(new InstructionPattern("scasw" , new OperandSlot[]{}                    , MODE16  , new int[]{0xAF}));
		mode32.add(new InstructionPattern("scasl" , new OperandSlot[]{}                    , MODE32  , new int[]{0xAF}));
		mode32.add(new InstructionPattern("movb"  , new OperandSlot[]{REG8, IMM8}          , MODELESS, new int[]{0xB0}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{REG16, IMM16}        , MODE16  , new int[]{0xB8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("movl"  , new OperandSlot[]{REG32, IMM32}        , MODE32  , new int[]{0xB8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("rolb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandSlot[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandSlot[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("movb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xC6}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("movw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0xC7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("movl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0xC7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("int"   , new OperandSlot[]{IMM_VAL_3}           , MODELESS, new int[]{0xCC}));
		mode32.add(new InstructionPattern("int"   , new OperandSlot[]{IMM8}                , MODELESS, new int[]{0xCD}));
		mode32.add(new InstructionPattern("into"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xCE}));
		mode32.add(new InstructionPattern("iretw" , new OperandSlot[]{}                    , MODE16  , new int[]{0xCF}));
		mode32.add(new InstructionPattern("iretl" , new OperandSlot[]{}                    , MODE32  , new int[]{0xCF}));
		mode32.add(new InstructionPattern("rolb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandSlot[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandSlot[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandSlot[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandSlot[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandSlot[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandSlot[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("aam"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xD4, 0x0A}));
		mode32.add(new InstructionPattern("aad"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xD5, 0x0A}));
		mode32.add(new InstructionPattern("salc"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xD6}));
		mode32.add(new InstructionPattern("setalc", new OperandSlot[]{}                    , MODELESS, new int[]{0xD6}));
		mode32.add(new InstructionPattern("inb"  , new OperandSlot[]{AL, IMM8}             , MODELESS, new int[]{0xE4}));
		mode32.add(new InstructionPattern("inw"  , new OperandSlot[]{AX, IMM8}             , MODE16  , new int[]{0xE5}));
		mode32.add(new InstructionPattern("inl"  , new OperandSlot[]{EAX, IMM8}            , MODE32  , new int[]{0xE5}));
		mode32.add(new InstructionPattern("outb" , new OperandSlot[]{IMM8, AL}             , MODELESS, new int[]{0xE6}));
		mode32.add(new InstructionPattern("outw" , new OperandSlot[]{IMM8, AX}             , MODE16  , new int[]{0xE7}));
		mode32.add(new InstructionPattern("outl" , new OperandSlot[]{IMM8, EAX}            , MODE32  , new int[]{0xE7}));
		mode32.add(new InstructionPattern("inb"  , new OperandSlot[]{AL, DX}               , MODELESS, new int[]{0xEC}));
		mode32.add(new InstructionPattern("inw"  , new OperandSlot[]{AX, DX}               , MODE16  , new int[]{0xED}));
		mode32.add(new InstructionPattern("inl"  , new OperandSlot[]{EAX, DX}              , MODE32  , new int[]{0xED}));
		mode32.add(new InstructionPattern("outb" , new OperandSlot[]{DX, AL}               , MODELESS, new int[]{0xEE}));
		mode32.add(new InstructionPattern("outw" , new OperandSlot[]{DX, AX}               , MODE16  , new int[]{0xEF}));
		mode32.add(new InstructionPattern("outl" , new OperandSlot[]{DX, EAX}              , MODE32  , new int[]{0xEF}));
		mode32.add(new InstructionPattern("lock"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xF0}));
		mode32.add(new InstructionPattern("lock"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xF0}));
		mode32.add(new InstructionPattern("repne" , new OperandSlot[]{}                    , MODELESS, new int[]{0xF2}));
		mode32.add(new InstructionPattern("repnz" , new OperandSlot[]{}                    , MODELESS, new int[]{0xF2}));
		mode32.add(new InstructionPattern("rep"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("repe"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("repz"  , new OperandSlot[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("hlt"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xF4}));
		mode32.add(new InstructionPattern("cmc"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xF5}));
		mode32.add(new InstructionPattern("testb" , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negb"  , new OperandSlot[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mulb"  , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imulb" , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divb"  , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivb" , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testw" , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negw"  , new OperandSlot[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mulw"  , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imulw" , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divw"  , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivw" , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testl" , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negl"  , new OperandSlot[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mull"  , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imull" , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divl"  , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivl" , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("clc"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xF8}));
		mode32.add(new InstructionPattern("stc"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xF9}));
		mode32.add(new InstructionPattern("cli"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xFA}));
		mode32.add(new InstructionPattern("sti"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xFB}));
		mode32.add(new InstructionPattern("cld"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xFC}));
		mode32.add(new InstructionPattern("std"   , new OperandSlot[]{}                    , MODELESS, new int[]{0xFD}));
		mode32.add(new InstructionPattern("incb"  , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xFE}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decb"  , new OperandSlot[]{RM8}                 , MODELESS, new int[]{0xFE}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("incw"  , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decw"  , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("incl"  , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decl"  , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("pushl" , new OperandSlot[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("invd"  , new OperandSlot[]{}                    , MODELESS, new int[]{0x0F, 0x08}));
		mode32.add(new InstructionPattern("wbinvd", new OperandSlot[]{}                    , MODELESS, new int[]{0x0F, 0x09}));
		mode32.add(new InstructionPattern("ud2"   , new OperandSlot[]{}                    , MODELESS, new int[]{0x0F, 0x0B}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{FS}                  , MODELESS, new int[]{0x0F, 0xA0}));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{FS}                  , MODELESS, new int[]{0x0F, 0xA1}));
		mode32.add(new InstructionPattern("pushw" , new OperandSlot[]{GS}                  , MODELESS, new int[]{0x0F, 0xA8}));
		mode32.add(new InstructionPattern("popw"  , new OperandSlot[]{GS}                  , MODELESS, new int[]{0x0F, 0xA9}));
		
		mode32.add(new InstructionPattern("cmpxchgb", new OperandSlot[]{RM8, REG8}         , MODELESS, new int[]{0x0F, 0xB0}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpxchgw", new OperandSlot[]{RM16, REG16}       , MODE16  , new int[]{0x0F, 0xB1}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpxchgl", new OperandSlot[]{RM32, REG32}       , MODE32  , new int[]{0x0F, 0xB1}, new ModRM(0, 1)));
		
		mode32.add(new InstructionPattern("bswapw", new OperandSlot[]{REG16}               , MODE16  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("bswapl", new OperandSlot[]{REG32}               , MODE32  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0)));
		
		MODE32_PATTERN_TABLE = Collections.unmodifiableSet(mode32);
	}
	
	
	
	public String mnemonic;
	
	public OperandSlot[] operands;
	
	public OperandSizeMode operandSizeMode;
	
	public InstructionOption[] options;
	
	
	public byte[] opcodes;
	
	
	
	private InstructionPattern(String mnemonic, OperandSlot[] operands, OperandSizeMode operandSizeMode, int[] opcodes, InstructionOption... options) {
		if (mnemonic == null || operands == null || operandSizeMode == null || opcodes == null || options == null)
			throw new NullPointerException();
		
		if (!MNEMONIC_PATTERN.matcher(mnemonic).matches())
			throw new IllegalArgumentException("Invalid mnemonic");
		
		if (operands.length > 10)
			throw new IllegalArgumentException("Invalid operands");
		
		if (opcodes.length == 0)
			throw new IllegalArgumentException("Invalid opcodes");
		
		if (options.length > 1)
			throw new IllegalArgumentException("Invalid options");
		if (options.length == 1) {
			InstructionOption option = options[0];
			if (option instanceof RegisterInOpcode)
				checkOption((RegisterInOpcode)option, operands);
			else if (option instanceof ModRM)
				checkOption((ModRM)option, operands);
			else
				throw new AssertionError();
		}
		
		this.mnemonic = mnemonic;
		this.operands = operands.clone();
		this.operandSizeMode = operandSizeMode;
		this.options = options;
		this.opcodes = toBytes(opcodes);
	}
	
	
	
	private static void checkOption(RegisterInOpcode option, OperandSlot[] operands) {
		if (option.parameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterOperandSlot(operands[option.parameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static void checkOption(ModRM option, OperandSlot[] operands) {
		if (option.rmParameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterMemoryOperandSlot(operands[option.rmParameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
		
		if (option.regOpcodeParameterIndex >= 10 && option.regOpcodeParameterIndex < 18);  // No problem
		else if (option.regOpcodeParameterIndex >= 18)
			throw new IllegalArgumentException("Invalid register/opcode constant value");
		else if (option.regOpcodeParameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
	}
	
	
	private static boolean isRegisterMemoryOperandSlot(OperandSlot opslot) {
		return opslot == RM8
		    || opslot == RM16
		    || opslot == RM32;
	}
	
	
	private static boolean isRegisterOperandSlot(OperandSlot opslot) {
		return opslot == REG8
		    || opslot == REG16
		    || opslot == REG32;
	}
	
	
	private static byte[] toBytes(int[] opcodes) {
		byte[] result = new byte[opcodes.length];
		for (int i = 0; i < opcodes.length; i++) {
			if ((opcodes[i] & 0xFF) != opcodes[i])
				throw new IllegalArgumentException("Byte value out of range");
			result[i] = (byte)opcodes[i];
		}
		return result;
	}
	
}