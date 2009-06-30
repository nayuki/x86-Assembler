package org.p79068.assembler.generator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.p79068.assembler.generator.OperandSizeMode.*;
import static org.p79068.assembler.generator.OperandPattern.*;


public final class InstructionPattern {
	
	/**
	 * The regular expression pattern for mnemonics, which is one lowercase letter followed by zero or more lowercase or numeric characters.
	 */
	private static Pattern MNEMONIC_PATTERN = Pattern.compile("[a-z][a-z0-9]*");
	
	
	public static final Set<InstructionPattern> MODE32_PATTERN_TABLE;
	
	static {
		Set<InstructionPattern> mode32 = new HashSet<InstructionPattern>();
		
		mode32.add(new InstructionPattern("addb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x00}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x01}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x01}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("addb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x02}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x03}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x03}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("addb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x04}));
		mode32.add(new InstructionPattern("addw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x05}));
		mode32.add(new InstructionPattern("addl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x05}));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{ES}                  , MODELESS, new int[]{0x06}));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{ES}                  , MODELESS, new int[]{0x07}));
		mode32.add(new InstructionPattern("orb"   , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x08}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orw"   , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x09}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orl"   , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x09}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("orb"   , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x0A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orw"   , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x0B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orl"   , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x0B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("orb"   , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x0C}));
		mode32.add(new InstructionPattern("orw"   , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x0D}));
		mode32.add(new InstructionPattern("orl"   , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x0D}));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{CS}                  , MODELESS, new int[]{0x0E}));
		mode32.add(new InstructionPattern("adcb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x10}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x11}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x11}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("adcb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x12}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x13}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x13}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("adcb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x14}));
		mode32.add(new InstructionPattern("adcw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x15}));
		mode32.add(new InstructionPattern("adcl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x15}));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{SS}                  , MODELESS, new int[]{0x16}));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{SS}                  , MODELESS, new int[]{0x17}));
		mode32.add(new InstructionPattern("sbbb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x18}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x19}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x19}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x1A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x1B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x1B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x1C}));
		mode32.add(new InstructionPattern("sbbw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x1D}));
		mode32.add(new InstructionPattern("sbbl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x1D}));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{DS}                  , MODELESS, new int[]{0x1E}));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{DS}                  , MODELESS, new int[]{0x1F}));
		mode32.add(new InstructionPattern("andb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x20}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x21}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x21}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("andb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x22}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x23}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x23}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("andb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x24}));
		mode32.add(new InstructionPattern("andw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x25}));
		mode32.add(new InstructionPattern("andl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x25}));
		mode32.add(new InstructionPattern("daa"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x27}));
		mode32.add(new InstructionPattern("subb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x28}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x29}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x29}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("subb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x2A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x2B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x2B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("subb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x2C}));
		mode32.add(new InstructionPattern("subw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x2D}));
		mode32.add(new InstructionPattern("subl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x2D}));
		mode32.add(new InstructionPattern("das"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x2F}));
		mode32.add(new InstructionPattern("xorb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x30}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x31}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x31}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xorb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x32}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x33}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x33}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xorb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x34}));
		mode32.add(new InstructionPattern("xorw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x35}));
		mode32.add(new InstructionPattern("xorl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x35}));
		mode32.add(new InstructionPattern("aaa"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x37}));
		mode32.add(new InstructionPattern("cmpb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x38}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x39}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x39}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x3A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x3B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x3B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0x3C}));
		mode32.add(new InstructionPattern("cmpw"  , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0x3D}));
		mode32.add(new InstructionPattern("cmpl"  , new OperandPattern[]{EAX, IMM32}          , MODE32  , new int[]{0x3D}));
		mode32.add(new InstructionPattern("aas"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x3F}));
		mode32.add(new InstructionPattern("incw"  , new OperandPattern[]{REG16}               , MODE16  , new int[]{0x40}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("incl"  , new OperandPattern[]{REG32}               , MODE32  , new int[]{0x40}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("decw"  , new OperandPattern[]{REG16}               , MODE16  , new int[]{0x48}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("decl"  , new OperandPattern[]{REG32}               , MODE32  , new int[]{0x48}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{REG16}               , MODE16  , new int[]{0x50}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushl" , new OperandPattern[]{REG32}               , MODE32  , new int[]{0x50}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{REG16}               , MODE16  , new int[]{0x58}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("popl"  , new OperandPattern[]{REG32}               , MODE32  , new int[]{0x58}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("pushaw", new OperandPattern[]{}                    , MODE16  , new int[]{0x60}));
		mode32.add(new InstructionPattern("pushal", new OperandPattern[]{}                    , MODE32  , new int[]{0x60}));
		mode32.add(new InstructionPattern("popaw" , new OperandPattern[]{}                    , MODE16  , new int[]{0x61}));
		mode32.add(new InstructionPattern("popal" , new OperandPattern[]{}                    , MODE32  , new int[]{0x61}));
		mode32.add(new InstructionPattern("boundw", new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0x62}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("boundl", new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0x62}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("arpl"  , new OperandPattern[]{RM16, REG16}         , MODELESS, new int[]{0x63}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{IMM16}               , MODE16  , new int[]{0x68}));
		mode32.add(new InstructionPattern("pushl" , new OperandPattern[]{IMM32}               , MODE32  , new int[]{0x68}));
		mode32.add(new InstructionPattern("imulw" , new OperandPattern[]{REG16, RM16, IMM16}  , MODE16  , new int[]{0x69}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("imull" , new OperandPattern[]{REG32, RM32, IMM32}  , MODE32  , new int[]{0x69}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("pushb" , new OperandPattern[]{IMM8}                , MODELESS, new int[]{0x6A}));
		mode32.add(new InstructionPattern("imulwb", new OperandPattern[]{REG16, RM16, IMM8}   , MODE16  , new int[]{0x6B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("imullb", new OperandPattern[]{REG32, RM32, IMM8}   , MODE32  , new int[]{0x6B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("insb"  , new OperandPattern[]{}                    , MODELESS, new int[]{0x6C}));
		mode32.add(new InstructionPattern("insw"  , new OperandPattern[]{}                    , MODE16  , new int[]{0x6D}));
		mode32.add(new InstructionPattern("insl"  , new OperandPattern[]{}                    , MODE32  , new int[]{0x6D}));
		mode32.add(new InstructionPattern("outsb" , new OperandPattern[]{}                    , MODELESS, new int[]{0x6E}));
		mode32.add(new InstructionPattern("outsw" , new OperandPattern[]{}                    , MODE16  , new int[]{0x6F}));
		mode32.add(new InstructionPattern("outsl" , new OperandPattern[]{}                    , MODE32  , new int[]{0x6F}));
		mode32.add(new InstructionPattern("addb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orb"   , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0x80}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orw"   , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0x81}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orl"   , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0x81}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orwb"  , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adcwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbbwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("subwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmpwb" , new OperandPattern[]{RM16, IMM8S}         , MODE16  , new int[]{0x83}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("addlb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("orlb"  , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("adclb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("sbblb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("andlb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sublb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("xorlb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("cmplb" , new OperandPattern[]{RM32, IMM8S}         , MODE32  , new int[]{0x83}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testb" , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x84}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("testw" , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x85}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("testl" , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x85}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgb" , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x86}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgw" , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x87}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xchgl" , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x87}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movb"  , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x88}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x89}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x89}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movb"  , new OperandPattern[]{REG8, RM8}           , MODELESS, new int[]{0x8A}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x8B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x8B}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{RM16, SREG}          , MODE16  , new int[]{0x8C}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("movlw" , new OperandPattern[]{RM32, SREG}          , MODE32  , new int[]{0x8C}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("leaw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0x8D}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("leal"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0x8D}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{SREG, RM16}          , MODE16  , new int[]{0x8E}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movwl" , new OperandPattern[]{SREG, RM32}          , MODE32  , new int[]{0x8E}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{RM16}                , MODE16  , new int[]{0x8F}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("popl"  , new OperandPattern[]{RM32}                , MODE32  , new int[]{0x8F}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("nop"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x90}));
		mode32.add(new InstructionPattern("xchgw" , new OperandPattern[]{REG16, AX}           , MODE16  , new int[]{0x90}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("xchgl" , new OperandPattern[]{REG32, EAX}          , MODE32  , new int[]{0x90}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("cbw"   , new OperandPattern[]{}                    , MODE16  , new int[]{0x98}));
		mode32.add(new InstructionPattern("cwde"  , new OperandPattern[]{}                    , MODE32  , new int[]{0x98}));
		mode32.add(new InstructionPattern("cwd"   , new OperandPattern[]{}                    , MODE16  , new int[]{0x99}));
		mode32.add(new InstructionPattern("cdq"   , new OperandPattern[]{}                    , MODE32  , new int[]{0x99}));
		mode32.add(new InstructionPattern("pushfw", new OperandPattern[]{}                    , MODE16  , new int[]{0x9C}));
		mode32.add(new InstructionPattern("pushfl", new OperandPattern[]{}                    , MODE32  , new int[]{0x9C}));
		mode32.add(new InstructionPattern("popfw" , new OperandPattern[]{}                    , MODE16  , new int[]{0x9D}));
		mode32.add(new InstructionPattern("popfl" , new OperandPattern[]{}                    , MODE32  , new int[]{0x9D}));
		mode32.add(new InstructionPattern("sahf"  , new OperandPattern[]{}                    , MODELESS, new int[]{0x9E}));
		mode32.add(new InstructionPattern("lahf"  , new OperandPattern[]{}                    , MODELESS, new int[]{0x9F}));
		mode32.add(new InstructionPattern("movsb" , new OperandPattern[]{}                    , MODELESS, new int[]{0xA4}));
		mode32.add(new InstructionPattern("movsw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xA5}));
		mode32.add(new InstructionPattern("movsl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xA5}));
		mode32.add(new InstructionPattern("cmpsb" , new OperandPattern[]{}                    , MODELESS, new int[]{0xA6}));
		mode32.add(new InstructionPattern("cmpsw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xA7}));
		mode32.add(new InstructionPattern("cmpsl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xA8}));
		mode32.add(new InstructionPattern("testb" , new OperandPattern[]{AL, IMM8}            , MODELESS, new int[]{0xA8}));
		mode32.add(new InstructionPattern("testw" , new OperandPattern[]{AX, IMM16}           , MODE16  , new int[]{0xA9}));
		mode32.add(new InstructionPattern("testl" , new OperandPattern[]{AX, IMM32}           , MODE32  , new int[]{0xA9}));
		mode32.add(new InstructionPattern("stosb" , new OperandPattern[]{}                    , MODELESS, new int[]{0xAA}));
		mode32.add(new InstructionPattern("stosw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xAB}));
		mode32.add(new InstructionPattern("stosl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xAB}));
		mode32.add(new InstructionPattern("lodsb" , new OperandPattern[]{}                    , MODELESS, new int[]{0xAC}));
		mode32.add(new InstructionPattern("lodsw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xAD}));
		mode32.add(new InstructionPattern("lodsl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xAD}));
		mode32.add(new InstructionPattern("scasb" , new OperandPattern[]{}                    , MODELESS, new int[]{0xAE}));
		mode32.add(new InstructionPattern("scasw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xAF}));
		mode32.add(new InstructionPattern("scasl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xAF}));
		mode32.add(new InstructionPattern("movb"  , new OperandPattern[]{REG8, IMM8}          , MODELESS, new int[]{0xB0}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{REG16, IMM16}        , MODE16  , new int[]{0xB8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("movl"  , new OperandPattern[]{REG32, IMM32}        , MODE32  , new int[]{0xB8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("rolb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC0}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0xC1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0xC1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("ret"   , new OperandPattern[]{IMM16}               , MODELESS, new int[]{0xC2}));
		mode32.add(new InstructionPattern("ret"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xC3}));
		mode32.add(new InstructionPattern("lesw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0xC4}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("lesl"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0xC4}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("ldsw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0xC5}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("ldsl"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0xC5}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xC6}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("movw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0xC7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("movl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0xC7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("enter" , new OperandPattern[]{IMM16, IMM8}         , MODELESS, new int[]{0xC8}));
		mode32.add(new InstructionPattern("leave" , new OperandPattern[]{}                    , MODELESS, new int[]{0xC9}));
		mode32.add(new InstructionPattern("lret"  , new OperandPattern[]{IMM16}               , MODELESS, new int[]{0xCA}));
		mode32.add(new InstructionPattern("lret"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xCB}));
		mode32.add(new InstructionPattern("int"   , new OperandPattern[]{IMM_VAL_3}           , MODELESS, new int[]{0xCC}));
		mode32.add(new InstructionPattern("int"   , new OperandPattern[]{IMM8}                , MODELESS, new int[]{0xCD}));
		mode32.add(new InstructionPattern("into"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xCE}));
		mode32.add(new InstructionPattern("iretw" , new OperandPattern[]{}                    , MODE16  , new int[]{0xCF}));
		mode32.add(new InstructionPattern("iretl" , new OperandPattern[]{}                    , MODE32  , new int[]{0xCF}));
		mode32.add(new InstructionPattern("rolb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandPattern[]{RM8, IMM_VAL_1}      , MODELESS, new int[]{0xD0}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandPattern[]{RM16, IMM_VAL_1}     , MODE16  , new int[]{0xD1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandPattern[]{RM32, IMM_VAL_1}     , MODE32  , new int[]{0xD1}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarb"  , new OperandPattern[]{RM8, CL}             , MODELESS, new int[]{0xD2}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("rolw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rclw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shlw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("salw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarw"  , new OperandPattern[]{RM16, CL}            , MODE16  , new int[]{0xD3}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("roll"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("rorl"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("rcll"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("rcrl"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("shll"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("sall"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("shrl"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("sarl"  , new OperandPattern[]{RM32, CL}            , MODE32  , new int[]{0xD3}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("aam"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xD4, 0x0A}));
		mode32.add(new InstructionPattern("aad"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xD5, 0x0A}));
		mode32.add(new InstructionPattern("salc"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xD6}));
		mode32.add(new InstructionPattern("setalc", new OperandPattern[]{}                    , MODELESS, new int[]{0xD6}));
		mode32.add(new InstructionPattern("xlat"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xD7}));
		mode32.add(new InstructionPattern("inb"  , new OperandPattern[]{AL, IMM8}             , MODELESS, new int[]{0xE4}));
		mode32.add(new InstructionPattern("inw"  , new OperandPattern[]{AX, IMM8}             , MODE16  , new int[]{0xE5}));
		mode32.add(new InstructionPattern("inl"  , new OperandPattern[]{EAX, IMM8}            , MODE32  , new int[]{0xE5}));
		mode32.add(new InstructionPattern("outb" , new OperandPattern[]{IMM8, AL}             , MODELESS, new int[]{0xE6}));
		mode32.add(new InstructionPattern("outw" , new OperandPattern[]{IMM8, AX}             , MODE16  , new int[]{0xE7}));
		mode32.add(new InstructionPattern("outl" , new OperandPattern[]{IMM8, EAX}            , MODE32  , new int[]{0xE7}));
		mode32.add(new InstructionPattern("inb"  , new OperandPattern[]{AL, DX}               , MODELESS, new int[]{0xEC}));
		mode32.add(new InstructionPattern("inw"  , new OperandPattern[]{AX, DX}               , MODE16  , new int[]{0xED}));
		mode32.add(new InstructionPattern("inl"  , new OperandPattern[]{EAX, DX}              , MODE32  , new int[]{0xED}));
		mode32.add(new InstructionPattern("outb" , new OperandPattern[]{DX, AL}               , MODELESS, new int[]{0xEE}));
		mode32.add(new InstructionPattern("outw" , new OperandPattern[]{DX, AX}               , MODE16  , new int[]{0xEF}));
		mode32.add(new InstructionPattern("outl" , new OperandPattern[]{DX, EAX}              , MODE32  , new int[]{0xEF}));
		mode32.add(new InstructionPattern("lock"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xF0}));
		mode32.add(new InstructionPattern("lock"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xF0}));
		mode32.add(new InstructionPattern("repne" , new OperandPattern[]{}                    , MODELESS, new int[]{0xF2}));
		mode32.add(new InstructionPattern("repnz" , new OperandPattern[]{}                    , MODELESS, new int[]{0xF2}));
		mode32.add(new InstructionPattern("rep"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("repe"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("repz"  , new OperandPattern[]{}                    , MODELESS, new int[]{0xF3}));
		mode32.add(new InstructionPattern("hlt"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xF4}));
		mode32.add(new InstructionPattern("cmc"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xF5}));
		mode32.add(new InstructionPattern("testb" , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negb"  , new OperandPattern[]{RM8, IMM8}           , MODELESS, new int[]{0xF6}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mulb"  , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imulb" , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divb"  , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivb" , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xF6}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testw" , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negw"  , new OperandPattern[]{RM16, IMM16}         , MODE16  , new int[]{0xF7}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mulw"  , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imulw" , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divw"  , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivw" , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xF7}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("testl" , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("notl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 12)));
		mode32.add(new InstructionPattern("negl"  , new OperandPattern[]{RM32, IMM32}         , MODE32  , new int[]{0xF7}, new ModRM(0, 13)));
		mode32.add(new InstructionPattern("mull"  , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("imull" , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("divl"  , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("idivl" , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xF7}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("clc"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xF8}));
		mode32.add(new InstructionPattern("stc"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xF9}));
		mode32.add(new InstructionPattern("cli"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xFA}));
		mode32.add(new InstructionPattern("sti"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xFB}));
		mode32.add(new InstructionPattern("cld"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xFC}));
		mode32.add(new InstructionPattern("std"   , new OperandPattern[]{}                    , MODELESS, new int[]{0xFD}));
		mode32.add(new InstructionPattern("incb"  , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xFE}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decb"  , new OperandPattern[]{RM8}                 , MODELESS, new int[]{0xFE}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("incw"  , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decw"  , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{RM16}                , MODE16  , new int[]{0xFF}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("incl"  , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 10)));
		mode32.add(new InstructionPattern("decl"  , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("pushl" , new OperandPattern[]{RM32}                , MODE32  , new int[]{0xFF}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("invd"  , new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0x08}));
		mode32.add(new InstructionPattern("wbinvd", new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0x09}));
		mode32.add(new InstructionPattern("ud2"   , new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0x0B}));
		mode32.add(new InstructionPattern("sysenter", new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0x34}));
		mode32.add(new InstructionPattern("sysexit" , new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0x35}));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{FS}                  , MODELESS, new int[]{0x0F, 0xA0}));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{FS}                  , MODELESS, new int[]{0x0F, 0xA1}));
		mode32.add(new InstructionPattern("cpuid" , new OperandPattern[]{}                    , MODELESS, new int[]{0x0F, 0xA2}));
		mode32.add(new InstructionPattern("btw"   , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x0F, 0xA3}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("btl"   , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x0F, 0xA3}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shldw" , new OperandPattern[]{RM16, REG16, IMM8}   , MODE16  , new int[]{0x0F, 0xA4}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shldl" , new OperandPattern[]{RM32, REG32, IMM8}   , MODE32  , new int[]{0x0F, 0xA4}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shldw" , new OperandPattern[]{RM16, REG16, CL}     , MODE16  , new int[]{0x0F, 0xA5}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shldl" , new OperandPattern[]{RM32, REG32, CL}     , MODE32  , new int[]{0x0F, 0xA5}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("pushw" , new OperandPattern[]{GS}                  , MODELESS, new int[]{0x0F, 0xA8}));
		mode32.add(new InstructionPattern("popw"  , new OperandPattern[]{GS}                  , MODELESS, new int[]{0x0F, 0xA9}));
		mode32.add(new InstructionPattern("btsw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x0F, 0xAB}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("btsl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x0F, 0xAB}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shrdw" , new OperandPattern[]{RM16, REG16, IMM8}   , MODE16  , new int[]{0x0F, 0xAC}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shrdl" , new OperandPattern[]{RM32, REG32, IMM8}   , MODE32  , new int[]{0x0F, 0xAC}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shrdw" , new OperandPattern[]{RM16, REG16, CL}     , MODE16  , new int[]{0x0F, 0xAD}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("shrdl" , new OperandPattern[]{RM32, REG32, CL}     , MODE32  , new int[]{0x0F, 0xAD}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("imulw" , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x0F, 0xAF}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("imull" , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x0F, 0xAF}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("cmpxchgb", new OperandPattern[]{RM8, REG8}         , MODELESS, new int[]{0x0F, 0xB0}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpxchgw", new OperandPattern[]{RM16, REG16}       , MODE16  , new int[]{0x0F, 0xB1}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpxchgl", new OperandPattern[]{RM32, REG32}       , MODE32  , new int[]{0x0F, 0xB1}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("lssw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0x0F, 0xB2}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("lssl"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0x0F, 0xB2}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("btrw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x0F, 0xB3}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("btrl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x0F, 0xB3}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("lfsw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0x0F, 0xB4}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("lfsl"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0x0F, 0xB4}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("lgsw"  , new OperandPattern[]{REG16, MEM}          , MODE16  , new int[]{0x0F, 0xB5}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("lgsl"  , new OperandPattern[]{REG32, MEM}          , MODE32  , new int[]{0x0F, 0xB5}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movzxwb", new OperandPattern[]{RM16, RM8}          , MODE16  , new int[]{0x0F, 0xB6}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movzxlb", new OperandPattern[]{RM32, RM8}          , MODE32  , new int[]{0x0F, 0xB6}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movzxww", new OperandPattern[]{RM16, RM16}         , MODE16  , new int[]{0x0F, 0xB7}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movzxlw", new OperandPattern[]{RM32, RM16}         , MODE32  , new int[]{0x0F, 0xB7}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("btw"   , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("btl"   , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 14)));
		mode32.add(new InstructionPattern("btsw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("btsl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 15)));
		mode32.add(new InstructionPattern("btrw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("btrl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 16)));
		mode32.add(new InstructionPattern("btcw"  , new OperandPattern[]{RM16, IMM8}          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("btcl"  , new OperandPattern[]{RM32, IMM8}          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 17)));
		mode32.add(new InstructionPattern("btcw"  , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x0F, 0xBB}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("btcl"  , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x0F, 0xBB}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("bsfw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x0F, 0xBC}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("bsfl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x0F, 0xBC}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("bsrw"  , new OperandPattern[]{REG16, RM16}         , MODE16  , new int[]{0x0F, 0xBD}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("bsrl"  , new OperandPattern[]{REG32, RM32}         , MODE32  , new int[]{0x0F, 0xBD}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movsxwb", new OperandPattern[]{RM16, RM8}          , MODE16  , new int[]{0x0F, 0xBE}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movsxlb", new OperandPattern[]{RM32, RM8}          , MODE32  , new int[]{0x0F, 0xBE}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movsxww", new OperandPattern[]{RM16, RM16}         , MODE16  , new int[]{0x0F, 0xBF}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("movsxlw", new OperandPattern[]{RM32, RM16}         , MODE32  , new int[]{0x0F, 0xBF}, new ModRM(1, 0)));
		mode32.add(new InstructionPattern("xaddb" , new OperandPattern[]{RM8, REG8}           , MODELESS, new int[]{0x0F, 0xC0}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xaddw" , new OperandPattern[]{RM16, REG16}         , MODE16  , new int[]{0x0F, 0xC1}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("xaddl" , new OperandPattern[]{RM32, REG32}         , MODE32  , new int[]{0x0F, 0xC1}, new ModRM(0, 1)));
		mode32.add(new InstructionPattern("cmpxchg8b", new OperandPattern[]{MEM}                , MODELESS, new int[]{0x0F, 0xC7}, new ModRM(0, 11)));
		mode32.add(new InstructionPattern("bswapw", new OperandPattern[]{REG16}               , MODE16  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0)));
		mode32.add(new InstructionPattern("bswapl", new OperandPattern[]{REG32}               , MODE32  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0)));
		
		MODE32_PATTERN_TABLE = Collections.unmodifiableSet(mode32);
	}
	
	
	
	public String mnemonic;
	
	public OperandPattern[] operands;
	
	public OperandSizeMode operandSizeMode;
	
	public InstructionOption[] options;
	
	
	public byte[] opcodes;
	
	
	
	private InstructionPattern(String mnemonic, OperandPattern[] operands, OperandSizeMode operandSizeMode, int[] opcodes, InstructionOption... options) {
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
	
	
	
	private static void checkOption(RegisterInOpcode option, OperandPattern[] operands) {
		if (option.parameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterOperandSlot(operands[option.parameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static void checkOption(ModRM option, OperandPattern[] operands) {
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
	
	
	private static boolean isRegisterMemoryOperandSlot(OperandPattern opslot) {
		return opslot == RM8
		    || opslot == RM16
		    || opslot == RM32;
	}
	
	
	private static boolean isRegisterOperandSlot(OperandPattern opslot) {
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
