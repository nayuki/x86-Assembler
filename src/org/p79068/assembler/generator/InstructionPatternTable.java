package org.p79068.assembler.generator;

import static org.p79068.assembler.generator.OperandPattern.*;
import static org.p79068.assembler.generator.OperandSizeMode.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.p79068.assembler.operand.Operand;


public class InstructionPatternTable {
	
	public static final InstructionPatternTable MODE32_TABLE;
	
	static {
		MODE32_TABLE = new InstructionPatternTable();
		MODE32_TABLE.add("byte"         , opPat(IMM8)                , MODELESS, new int[]{});
		MODE32_TABLE.add("word"         , opPat(IMM16)               , MODELESS, new int[]{});
		MODE32_TABLE.add("dword"        , opPat(IMM32)               , MODELESS, new int[]{});
		MODE32_TABLE.add("addb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x00}, new ModRM(0, 1));
		MODE32_TABLE.add("addw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x01}, new ModRM(0, 1));
		MODE32_TABLE.add("addl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x01}, new ModRM(0, 1));
		MODE32_TABLE.add("addb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x02}, new ModRM(1, 0));
		MODE32_TABLE.add("addw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x03}, new ModRM(1, 0));
		MODE32_TABLE.add("addl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x03}, new ModRM(1, 0));
		MODE32_TABLE.add("addb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x04});
		MODE32_TABLE.add("addw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x05});
		MODE32_TABLE.add("addl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x05});
		MODE32_TABLE.add("pushw"        , opPat(ES)                  , MODELESS, new int[]{0x06});
		MODE32_TABLE.add("popw"         , opPat(ES)                  , MODELESS, new int[]{0x07});
		MODE32_TABLE.add("orb"          , opPat(RM8, REG8)           , MODELESS, new int[]{0x08}, new ModRM(0, 1));
		MODE32_TABLE.add("orw"          , opPat(RM16, REG16)         , MODE16  , new int[]{0x09}, new ModRM(0, 1));
		MODE32_TABLE.add("orl"          , opPat(RM32, REG32)         , MODE32  , new int[]{0x09}, new ModRM(0, 1));
		MODE32_TABLE.add("orb"          , opPat(REG8, RM8)           , MODELESS, new int[]{0x0A}, new ModRM(1, 0));
		MODE32_TABLE.add("orw"          , opPat(REG16, RM16)         , MODE16  , new int[]{0x0B}, new ModRM(1, 0));
		MODE32_TABLE.add("orl"          , opPat(REG32, RM32)         , MODE32  , new int[]{0x0B}, new ModRM(1, 0));
		MODE32_TABLE.add("orb"          , opPat(AL, IMM8)            , MODELESS, new int[]{0x0C});
		MODE32_TABLE.add("orw"          , opPat(AX, IMM16)           , MODE16  , new int[]{0x0D});
		MODE32_TABLE.add("orl"          , opPat(EAX, IMM32)          , MODE32  , new int[]{0x0D});
		MODE32_TABLE.add("pushw"        , opPat(CS)                  , MODELESS, new int[]{0x0E});
		MODE32_TABLE.add("adcb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x10}, new ModRM(0, 1));
		MODE32_TABLE.add("adcw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x11}, new ModRM(0, 1));
		MODE32_TABLE.add("adcl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x11}, new ModRM(0, 1));
		MODE32_TABLE.add("adcb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x12}, new ModRM(1, 0));
		MODE32_TABLE.add("adcw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x13}, new ModRM(1, 0));
		MODE32_TABLE.add("adcl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x13}, new ModRM(1, 0));
		MODE32_TABLE.add("adcb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x14});
		MODE32_TABLE.add("adcw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x15});
		MODE32_TABLE.add("adcl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x15});
		MODE32_TABLE.add("pushw"        , opPat(SS)                  , MODELESS, new int[]{0x16});
		MODE32_TABLE.add("popw"         , opPat(SS)                  , MODELESS, new int[]{0x17});
		MODE32_TABLE.add("sbbb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x18}, new ModRM(0, 1));
		MODE32_TABLE.add("sbbw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x19}, new ModRM(0, 1));
		MODE32_TABLE.add("sbbl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x19}, new ModRM(0, 1));
		MODE32_TABLE.add("sbbb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x1A}, new ModRM(1, 0));
		MODE32_TABLE.add("sbbw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x1B}, new ModRM(1, 0));
		MODE32_TABLE.add("sbbl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x1B}, new ModRM(1, 0));
		MODE32_TABLE.add("sbbb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x1C});
		MODE32_TABLE.add("sbbw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x1D});
		MODE32_TABLE.add("sbbl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x1D});
		MODE32_TABLE.add("pushw"        , opPat(DS)                  , MODELESS, new int[]{0x1E});
		MODE32_TABLE.add("popw"         , opPat(DS)                  , MODELESS, new int[]{0x1F});
		MODE32_TABLE.add("andb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x20}, new ModRM(0, 1));
		MODE32_TABLE.add("andw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x21}, new ModRM(0, 1));
		MODE32_TABLE.add("andl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x21}, new ModRM(0, 1));
		MODE32_TABLE.add("andb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x22}, new ModRM(1, 0));
		MODE32_TABLE.add("andw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x23}, new ModRM(1, 0));
		MODE32_TABLE.add("andl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x23}, new ModRM(1, 0));
		MODE32_TABLE.add("andb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x24});
		MODE32_TABLE.add("andw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x25});
		MODE32_TABLE.add("andl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x25});
		MODE32_TABLE.add("daa"          , opPat()                    , MODELESS, new int[]{0x27});
		MODE32_TABLE.add("subb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x28}, new ModRM(0, 1));
		MODE32_TABLE.add("subw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x29}, new ModRM(0, 1));
		MODE32_TABLE.add("subl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x29}, new ModRM(0, 1));
		MODE32_TABLE.add("subb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x2A}, new ModRM(1, 0));
		MODE32_TABLE.add("subw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x2B}, new ModRM(1, 0));
		MODE32_TABLE.add("subl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x2B}, new ModRM(1, 0));
		MODE32_TABLE.add("subb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x2C});
		MODE32_TABLE.add("subw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x2D});
		MODE32_TABLE.add("subl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x2D});
		MODE32_TABLE.add("das"          , opPat()                    , MODELESS, new int[]{0x2F});
		MODE32_TABLE.add("xorb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x30}, new ModRM(0, 1));
		MODE32_TABLE.add("xorw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x31}, new ModRM(0, 1));
		MODE32_TABLE.add("xorl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x31}, new ModRM(0, 1));
		MODE32_TABLE.add("xorb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x32}, new ModRM(1, 0));
		MODE32_TABLE.add("xorw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x33}, new ModRM(1, 0));
		MODE32_TABLE.add("xorl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x33}, new ModRM(1, 0));
		MODE32_TABLE.add("xorb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x34});
		MODE32_TABLE.add("xorw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x35});
		MODE32_TABLE.add("xorl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x35});
		MODE32_TABLE.add("aaa"          , opPat()                    , MODELESS, new int[]{0x37});
		MODE32_TABLE.add("cmpb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x38}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x39}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x39}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x3A}, new ModRM(1, 0));
		MODE32_TABLE.add("cmpw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x3B}, new ModRM(1, 0));
		MODE32_TABLE.add("cmpl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x3B}, new ModRM(1, 0));
		MODE32_TABLE.add("cmpb"         , opPat(AL, IMM8)            , MODELESS, new int[]{0x3C});
		MODE32_TABLE.add("cmpw"         , opPat(AX, IMM16)           , MODE16  , new int[]{0x3D});
		MODE32_TABLE.add("cmpl"         , opPat(EAX, IMM32)          , MODE32  , new int[]{0x3D});
		MODE32_TABLE.add("aas"          , opPat()                    , MODELESS, new int[]{0x3F});
		MODE32_TABLE.add("incw"         , opPat(REG16)               , MODE16  , new int[]{0x40}, new RegisterInOpcode(0));
		MODE32_TABLE.add("incl"         , opPat(REG32)               , MODE32  , new int[]{0x40}, new RegisterInOpcode(0));
		MODE32_TABLE.add("decw"         , opPat(REG16)               , MODE16  , new int[]{0x48}, new RegisterInOpcode(0));
		MODE32_TABLE.add("decl"         , opPat(REG32)               , MODE32  , new int[]{0x48}, new RegisterInOpcode(0));
		MODE32_TABLE.add("pushw"        , opPat(REG16)               , MODE16  , new int[]{0x50}, new RegisterInOpcode(0));
		MODE32_TABLE.add("pushl"        , opPat(REG32)               , MODE32  , new int[]{0x50}, new RegisterInOpcode(0));
		MODE32_TABLE.add("popw"         , opPat(REG16)               , MODE16  , new int[]{0x58}, new RegisterInOpcode(0));
		MODE32_TABLE.add("popl"         , opPat(REG32)               , MODE32  , new int[]{0x58}, new RegisterInOpcode(0));
		MODE32_TABLE.add("pushaw"       , opPat()                    , MODE16  , new int[]{0x60});
		MODE32_TABLE.add("pushal"       , opPat()                    , MODE32  , new int[]{0x60});
		MODE32_TABLE.add("popaw"        , opPat()                    , MODE16  , new int[]{0x61});
		MODE32_TABLE.add("popal"        , opPat()                    , MODE32  , new int[]{0x61});
		MODE32_TABLE.add("boundw"       , opPat(REG16, MEM)          , MODE16  , new int[]{0x62}, new ModRM(1, 0));
		MODE32_TABLE.add("boundl"       , opPat(REG32, MEM)          , MODE32  , new int[]{0x62}, new ModRM(1, 0));
		MODE32_TABLE.add("arpl"         , opPat(RM16, REG16)         , MODELESS, new int[]{0x63}, new ModRM(0, 1));
		MODE32_TABLE.add("pushw"        , opPat(IMM16)               , MODE16  , new int[]{0x68});
		MODE32_TABLE.add("pushl"        , opPat(IMM32)               , MODE32  , new int[]{0x68});
		MODE32_TABLE.add("imulw"        , opPat(REG16, RM16, IMM16)  , MODE16  , new int[]{0x69}, new ModRM(1, 0));
		MODE32_TABLE.add("imull"        , opPat(REG32, RM32, IMM32)  , MODE32  , new int[]{0x69}, new ModRM(1, 0));
		MODE32_TABLE.add("pushb"        , opPat(IMM8)                , MODELESS, new int[]{0x6A});
		MODE32_TABLE.add("imulwb"       , opPat(REG16, RM16, IMM8)   , MODE16  , new int[]{0x6B}, new ModRM(1, 0));
		MODE32_TABLE.add("imullb"       , opPat(REG32, RM32, IMM8)   , MODE32  , new int[]{0x6B}, new ModRM(1, 0));
		MODE32_TABLE.add("insb"         , opPat()                    , MODELESS, new int[]{0x6C});
		MODE32_TABLE.add("insw"         , opPat()                    , MODE16  , new int[]{0x6D});
		MODE32_TABLE.add("insl"         , opPat()                    , MODE32  , new int[]{0x6D});
		MODE32_TABLE.add("outsb"        , opPat()                    , MODELESS, new int[]{0x6E});
		MODE32_TABLE.add("outsw"        , opPat()                    , MODE16  , new int[]{0x6F});
		MODE32_TABLE.add("outsl"        , opPat()                    , MODE32  , new int[]{0x6F});
		MODE32_TABLE.add("jo"           , opPat(REL8)                , MODELESS, new int[]{0x70});
		MODE32_TABLE.add("jno"          , opPat(REL8)                , MODELESS, new int[]{0x71});
		MODE32_TABLE.add("jb|jnae|jc"   , opPat(REL8)                , MODELESS, new int[]{0x72});
		MODE32_TABLE.add("jnb|jae|jnc"  , opPat(REL8)                , MODELESS, new int[]{0x73});
		MODE32_TABLE.add("jz|je"        , opPat(REL8)                , MODELESS, new int[]{0x74});
		MODE32_TABLE.add("jnz|jne"      , opPat(REL8)                , MODELESS, new int[]{0x75});
		MODE32_TABLE.add("jbe|jna"      , opPat(REL8)                , MODELESS, new int[]{0x76});
		MODE32_TABLE.add("jnbe|ja"      , opPat(REL8)                , MODELESS, new int[]{0x77});
		MODE32_TABLE.add("js"           , opPat(REL8)                , MODELESS, new int[]{0x78});
		MODE32_TABLE.add("jns"          , opPat(REL8)                , MODELESS, new int[]{0x79});
		MODE32_TABLE.add("jp|jpe"       , opPat(REL8)                , MODELESS, new int[]{0x7A});
		MODE32_TABLE.add("jnp|jpo"      , opPat(REL8)                , MODELESS, new int[]{0x7B});
		MODE32_TABLE.add("jl|jnge"      , opPat(REL8)                , MODELESS, new int[]{0x7C});
		MODE32_TABLE.add("jnl|jge"      , opPat(REL8)                , MODELESS, new int[]{0x7D});
		MODE32_TABLE.add("jle|jng"      , opPat(REL8)                , MODELESS, new int[]{0x7E});
		MODE32_TABLE.add("jnle|jg"      , opPat(REL8)                , MODELESS, new int[]{0x7F});
		MODE32_TABLE.add("addb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 10));
		MODE32_TABLE.add("orb"          , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 11));
		MODE32_TABLE.add("adcb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 12));
		MODE32_TABLE.add("sbbb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 13));
		MODE32_TABLE.add("andb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 14));
		MODE32_TABLE.add("subb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 15));
		MODE32_TABLE.add("xorb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 16));
		MODE32_TABLE.add("cmpb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0x80}, new ModRM(0, 17));
		MODE32_TABLE.add("addw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 10));
		MODE32_TABLE.add("orw"          , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 11));
		MODE32_TABLE.add("adcw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 12));
		MODE32_TABLE.add("sbbw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 13));
		MODE32_TABLE.add("andw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 14));
		MODE32_TABLE.add("subw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 15));
		MODE32_TABLE.add("xorw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 16));
		MODE32_TABLE.add("cmpw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0x81}, new ModRM(0, 17));
		MODE32_TABLE.add("addl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 10));
		MODE32_TABLE.add("orl"          , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 11));
		MODE32_TABLE.add("adcl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 12));
		MODE32_TABLE.add("sbbl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 13));
		MODE32_TABLE.add("andl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 14));
		MODE32_TABLE.add("subl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 15));
		MODE32_TABLE.add("xorl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 16));
		MODE32_TABLE.add("cmpl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0x81}, new ModRM(0, 17));
		MODE32_TABLE.add("addwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 10));
		MODE32_TABLE.add("orwb"         , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 11));
		MODE32_TABLE.add("adcwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 12));
		MODE32_TABLE.add("sbbwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 13));
		MODE32_TABLE.add("andwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 14));
		MODE32_TABLE.add("subwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 15));
		MODE32_TABLE.add("xorwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 16));
		MODE32_TABLE.add("cmpwb"        , opPat(RM16, IMM8S)         , MODE16  , new int[]{0x83}, new ModRM(0, 17));
		MODE32_TABLE.add("addlb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 10));
		MODE32_TABLE.add("orlb"         , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 11));
		MODE32_TABLE.add("adclb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 12));
		MODE32_TABLE.add("sbblb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 13));
		MODE32_TABLE.add("andlb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 14));
		MODE32_TABLE.add("sublb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 15));
		MODE32_TABLE.add("xorlb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 16));
		MODE32_TABLE.add("cmplb"        , opPat(RM32, IMM8S)         , MODE32  , new int[]{0x83}, new ModRM(0, 17));
		MODE32_TABLE.add("testb"        , opPat(RM8, REG8)           , MODELESS, new int[]{0x84}, new ModRM(0, 1));
		MODE32_TABLE.add("testw"        , opPat(RM16, REG16)         , MODE16  , new int[]{0x85}, new ModRM(0, 1));
		MODE32_TABLE.add("testl"        , opPat(RM32, REG32)         , MODE32  , new int[]{0x85}, new ModRM(0, 1));
		MODE32_TABLE.add("xchgb"        , opPat(RM8, REG8)           , MODELESS, new int[]{0x86}, new ModRM(0, 1));
		MODE32_TABLE.add("xchgw"        , opPat(RM16, REG16)         , MODE16  , new int[]{0x87}, new ModRM(0, 1));
		MODE32_TABLE.add("xchgl"        , opPat(RM32, REG32)         , MODE32  , new int[]{0x87}, new ModRM(0, 1));
		MODE32_TABLE.add("movb"         , opPat(RM8, REG8)           , MODELESS, new int[]{0x88}, new ModRM(0, 1));
		MODE32_TABLE.add("movw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x89}, new ModRM(0, 1));
		MODE32_TABLE.add("movl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x89}, new ModRM(0, 1));
		MODE32_TABLE.add("movb"         , opPat(REG8, RM8)           , MODELESS, new int[]{0x8A}, new ModRM(1, 0));
		MODE32_TABLE.add("movw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x8B}, new ModRM(1, 0));
		MODE32_TABLE.add("movl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x8B}, new ModRM(1, 0));
		MODE32_TABLE.add("movw"         , opPat(RM16, SREG)          , MODE16  , new int[]{0x8C}, new ModRM(0, 1));
		MODE32_TABLE.add("movlw"        , opPat(RM32, SREG)          , MODE32  , new int[]{0x8C}, new ModRM(0, 1));
		MODE32_TABLE.add("leaw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0x8D}, new ModRM(1, 0));
		MODE32_TABLE.add("leal"         , opPat(REG32, MEM)          , MODE32  , new int[]{0x8D}, new ModRM(1, 0));
		MODE32_TABLE.add("movw"         , opPat(SREG, RM16)          , MODE16  , new int[]{0x8E}, new ModRM(1, 0));
		MODE32_TABLE.add("movwl"        , opPat(SREG, RM32)          , MODE32  , new int[]{0x8E}, new ModRM(1, 0));
		MODE32_TABLE.add("popw"         , opPat(RM16)                , MODE16  , new int[]{0x8F}, new ModRM(0, 10));
		MODE32_TABLE.add("popl"         , opPat(RM32)                , MODE32  , new int[]{0x8F}, new ModRM(0, 10));
		MODE32_TABLE.add("nop"          , opPat()                    , MODELESS, new int[]{0x90});
		MODE32_TABLE.add("xchgw"        , opPat(REG16, AX)           , MODE16  , new int[]{0x90}, new RegisterInOpcode(0));
		MODE32_TABLE.add("xchgl"        , opPat(REG32, EAX)          , MODE32  , new int[]{0x90}, new RegisterInOpcode(0));
		MODE32_TABLE.add("cbw"          , opPat()                    , MODE16  , new int[]{0x98});
		MODE32_TABLE.add("cwde"         , opPat()                    , MODE32  , new int[]{0x98});
		MODE32_TABLE.add("cwd"          , opPat()                    , MODE16  , new int[]{0x99});
		MODE32_TABLE.add("cdq"          , opPat()                    , MODE32  , new int[]{0x99});
		MODE32_TABLE.add("pushfw"       , opPat()                    , MODE16  , new int[]{0x9C});
		MODE32_TABLE.add("pushfl"       , opPat()                    , MODE32  , new int[]{0x9C});
		MODE32_TABLE.add("popfw"        , opPat()                    , MODE16  , new int[]{0x9D});
		MODE32_TABLE.add("popfl"        , opPat()                    , MODE32  , new int[]{0x9D});
		MODE32_TABLE.add("sahf"         , opPat()                    , MODELESS, new int[]{0x9E});
		MODE32_TABLE.add("lahf"         , opPat()                    , MODELESS, new int[]{0x9F});
		MODE32_TABLE.add("movsb"        , opPat()                    , MODELESS, new int[]{0xA4});
		MODE32_TABLE.add("movsw"        , opPat()                    , MODE16  , new int[]{0xA5});
		MODE32_TABLE.add("movsl"        , opPat()                    , MODE32  , new int[]{0xA5});
		MODE32_TABLE.add("cmpsb"        , opPat()                    , MODELESS, new int[]{0xA6});
		MODE32_TABLE.add("cmpsw"        , opPat()                    , MODE16  , new int[]{0xA7});
		MODE32_TABLE.add("cmpsl"        , opPat()                    , MODE32  , new int[]{0xA8});
		MODE32_TABLE.add("testb"        , opPat(AL, IMM8)            , MODELESS, new int[]{0xA8});
		MODE32_TABLE.add("testw"        , opPat(AX, IMM16)           , MODE16  , new int[]{0xA9});
		MODE32_TABLE.add("testl"        , opPat(AX, IMM32)           , MODE32  , new int[]{0xA9});
		MODE32_TABLE.add("stosb"        , opPat()                    , MODELESS, new int[]{0xAA});
		MODE32_TABLE.add("stosw"        , opPat()                    , MODE16  , new int[]{0xAB});
		MODE32_TABLE.add("stosl"        , opPat()                    , MODE32  , new int[]{0xAB});
		MODE32_TABLE.add("lodsb"        , opPat()                    , MODELESS, new int[]{0xAC});
		MODE32_TABLE.add("lodsw"        , opPat()                    , MODE16  , new int[]{0xAD});
		MODE32_TABLE.add("lodsl"        , opPat()                    , MODE32  , new int[]{0xAD});
		MODE32_TABLE.add("scasb"        , opPat()                    , MODELESS, new int[]{0xAE});
		MODE32_TABLE.add("scasw"        , opPat()                    , MODE16  , new int[]{0xAF});
		MODE32_TABLE.add("scasl"        , opPat()                    , MODE32  , new int[]{0xAF});
		MODE32_TABLE.add("movb"         , opPat(REG8, IMM8)          , MODELESS, new int[]{0xB0}, new RegisterInOpcode(0));
		MODE32_TABLE.add("movw"         , opPat(REG16, IMM16)        , MODE16  , new int[]{0xB8}, new RegisterInOpcode(0));
		MODE32_TABLE.add("movl"         , opPat(REG32, IMM32)        , MODE32  , new int[]{0xB8}, new RegisterInOpcode(0));
		MODE32_TABLE.add("rolb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 10));
		MODE32_TABLE.add("rorb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 11));
		MODE32_TABLE.add("rclb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 13));
		MODE32_TABLE.add("shlb|salb"    , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 14));
		MODE32_TABLE.add("shrb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 15));
		MODE32_TABLE.add("sarb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC0}, new ModRM(0, 17));
		MODE32_TABLE.add("rolw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 10));
		MODE32_TABLE.add("rorw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 11));
		MODE32_TABLE.add("rclw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 13));
		MODE32_TABLE.add("shlw|salw"    , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 14));
		MODE32_TABLE.add("shrw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 15));
		MODE32_TABLE.add("sarw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0xC1}, new ModRM(0, 17));
		MODE32_TABLE.add("roll"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 10));
		MODE32_TABLE.add("rorl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 11));
		MODE32_TABLE.add("rcll"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 13));
		MODE32_TABLE.add("shll|sall"    , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 14));
		MODE32_TABLE.add("shrl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 15));
		MODE32_TABLE.add("sarl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0xC1}, new ModRM(0, 17));
		MODE32_TABLE.add("ret"          , opPat(IMM16)               , MODELESS, new int[]{0xC2});
		MODE32_TABLE.add("ret"          , opPat()                    , MODELESS, new int[]{0xC3});
		MODE32_TABLE.add("lesw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0xC4}, new ModRM(1, 0));
		MODE32_TABLE.add("lesl"         , opPat(REG32, MEM)          , MODE32  , new int[]{0xC4}, new ModRM(1, 0));
		MODE32_TABLE.add("ldsw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0xC5}, new ModRM(1, 0));
		MODE32_TABLE.add("ldsl"         , opPat(REG32, MEM)          , MODE32  , new int[]{0xC5}, new ModRM(1, 0));
		MODE32_TABLE.add("movb"         , opPat(RM8, IMM8)           , MODELESS, new int[]{0xC6}, new ModRM(0, 10));
		MODE32_TABLE.add("movw"         , opPat(RM16, IMM16)         , MODE16  , new int[]{0xC7}, new ModRM(0, 10));
		MODE32_TABLE.add("movl"         , opPat(RM32, IMM32)         , MODE32  , new int[]{0xC7}, new ModRM(0, 10));
		MODE32_TABLE.add("enter"        , opPat(IMM16, IMM8)         , MODELESS, new int[]{0xC8});
		MODE32_TABLE.add("leave"        , opPat()                    , MODELESS, new int[]{0xC9});
		MODE32_TABLE.add("lret"         , opPat(IMM16)               , MODELESS, new int[]{0xCA});
		MODE32_TABLE.add("lret"         , opPat()                    , MODELESS, new int[]{0xCB});
		MODE32_TABLE.add("int"          , opPat(IMM_VAL_3)           , MODELESS, new int[]{0xCC});
		MODE32_TABLE.add("int"          , opPat(IMM8)                , MODELESS, new int[]{0xCD});
		MODE32_TABLE.add("into"         , opPat()                    , MODELESS, new int[]{0xCE});
		MODE32_TABLE.add("iretw"        , opPat()                    , MODE16  , new int[]{0xCF});
		MODE32_TABLE.add("iretl"        , opPat()                    , MODE32  , new int[]{0xCF});
		MODE32_TABLE.add("rolb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 10));
		MODE32_TABLE.add("rorb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 11));
		MODE32_TABLE.add("rclb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 13));
		MODE32_TABLE.add("shlb|salb"    , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 14));
		MODE32_TABLE.add("shrb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 15));
		MODE32_TABLE.add("sarb"         , opPat(RM8, IMM_VAL_1)      , MODELESS, new int[]{0xD0}, new ModRM(0, 17));
		MODE32_TABLE.add("rolw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 10));
		MODE32_TABLE.add("rorw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 11));
		MODE32_TABLE.add("rclw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 13));
		MODE32_TABLE.add("shlw|shlw"    , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 14));
		MODE32_TABLE.add("shrw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 15));
		MODE32_TABLE.add("sarw"         , opPat(RM16, IMM_VAL_1)     , MODE16  , new int[]{0xD1}, new ModRM(0, 17));
		MODE32_TABLE.add("roll"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 10));
		MODE32_TABLE.add("rorl"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 11));
		MODE32_TABLE.add("rcll"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrl"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 13));
		MODE32_TABLE.add("shll|sall"    , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 14));
		MODE32_TABLE.add("shrl"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 15));
		MODE32_TABLE.add("sarl"         , opPat(RM32, IMM_VAL_1)     , MODE32  , new int[]{0xD1}, new ModRM(0, 17));
		MODE32_TABLE.add("rolb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 10));
		MODE32_TABLE.add("rorb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 11));
		MODE32_TABLE.add("rclb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 13));
		MODE32_TABLE.add("shlb|salb"    , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 14));
		MODE32_TABLE.add("shrb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 15));
		MODE32_TABLE.add("sarb"         , opPat(RM8, CL)             , MODELESS, new int[]{0xD2}, new ModRM(0, 17));
		MODE32_TABLE.add("rolw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 10));
		MODE32_TABLE.add("rorw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 11));
		MODE32_TABLE.add("rclw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 13));
		MODE32_TABLE.add("shlw|salw"    , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 14));
		MODE32_TABLE.add("shrw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 15));
		MODE32_TABLE.add("sarw"         , opPat(RM16, CL)            , MODE16  , new int[]{0xD3}, new ModRM(0, 17));
		MODE32_TABLE.add("roll"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 10));
		MODE32_TABLE.add("rorl"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 11));
		MODE32_TABLE.add("rcll"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 12));
		MODE32_TABLE.add("rcrl"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 13));
		MODE32_TABLE.add("shll|sall"    , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 14));
		MODE32_TABLE.add("shrl"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 15));
		MODE32_TABLE.add("sarl"         , opPat(RM32, CL)            , MODE32  , new int[]{0xD3}, new ModRM(0, 17));
		MODE32_TABLE.add("aam"          , opPat()                    , MODELESS, new int[]{0xD4, 0x0A});
		MODE32_TABLE.add("aad"          , opPat()                    , MODELESS, new int[]{0xD5, 0x0A});
		MODE32_TABLE.add("salc"         , opPat()                    , MODELESS, new int[]{0xD6});
		MODE32_TABLE.add("setalc"       , opPat()                    , MODELESS, new int[]{0xD6});
		MODE32_TABLE.add("xlat"         , opPat()                    , MODELESS, new int[]{0xD7});
		MODE32_TABLE.add("loopnz|loopne", opPat(REL8)                , MODELESS, new int[]{0xE0});
		MODE32_TABLE.add("loopz|loope"  , opPat(REL8)                , MODELESS, new int[]{0xE1});
		MODE32_TABLE.add("loop"         , opPat(REL8)                , MODELESS, new int[]{0xE2});
		MODE32_TABLE.add("jecxz"        , opPat(REL8)                , MODELESS, new int[]{0xE3});
		MODE32_TABLE.add("inb"          , opPat(AL, IMM8)            , MODELESS, new int[]{0xE4});
		MODE32_TABLE.add("inw"          , opPat(AX, IMM8)            , MODE16  , new int[]{0xE5});
		MODE32_TABLE.add("inl"          , opPat(EAX, IMM8)           , MODE32  , new int[]{0xE5});
		MODE32_TABLE.add("outb"         , opPat(IMM8, AL)            , MODELESS, new int[]{0xE6});
		MODE32_TABLE.add("outw"         , opPat(IMM8, AX)            , MODE16  , new int[]{0xE7});
		MODE32_TABLE.add("outl"         , opPat(IMM8, EAX)           , MODE32  , new int[]{0xE7});
		MODE32_TABLE.add("call"         , opPat(REL16)               , MODE16  , new int[]{0xE8});
		MODE32_TABLE.add("call"         , opPat(REL32)               , MODE32  , new int[]{0xE8});
		MODE32_TABLE.add("jmp"          , opPat(REL16)               , MODE16  , new int[]{0xE9});
		MODE32_TABLE.add("jmp"          , opPat(REL32)               , MODE32  , new int[]{0xE9});
		MODE32_TABLE.add("ljmpw"        , opPat(MEM)                 , MODE16  , new int[]{0xE9});
		MODE32_TABLE.add("ljmpl"        , opPat(MEM)                 , MODE32  , new int[]{0xE9});
		MODE32_TABLE.add("jmp"          , opPat(REL8)                , MODELESS, new int[]{0xEB});
		MODE32_TABLE.add("inb"          , opPat(AL, DX)              , MODELESS, new int[]{0xEC});
		MODE32_TABLE.add("inw"          , opPat(AX, DX)              , MODE16  , new int[]{0xED});
		MODE32_TABLE.add("inl"          , opPat(EAX, DX)             , MODE32  , new int[]{0xED});
		MODE32_TABLE.add("outb"         , opPat(DX, AL)              , MODELESS, new int[]{0xEE});
		MODE32_TABLE.add("outw"         , opPat(DX, AX)              , MODE16  , new int[]{0xEF});
		MODE32_TABLE.add("outl"         , opPat(DX, EAX)             , MODE32  , new int[]{0xEF});
		MODE32_TABLE.add("lock"         , opPat()                    , MODELESS, new int[]{0xF0});
		MODE32_TABLE.add("repnz|repne"  , opPat()                    , MODELESS, new int[]{0xF2});
		MODE32_TABLE.add("rep|repz|repe", opPat()                    , MODELESS, new int[]{0xF3});
		MODE32_TABLE.add("hlt"          , opPat()                    , MODELESS, new int[]{0xF4});
		MODE32_TABLE.add("cmc"          , opPat()                    , MODELESS, new int[]{0xF5});
		MODE32_TABLE.add("testb"        , opPat(RM8, IMM8)           , MODELESS, new int[]{0xF6}, new ModRM(0, 10));
		MODE32_TABLE.add("notb"         , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 12));
		MODE32_TABLE.add("negb"         , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 13));
		MODE32_TABLE.add("mulb"         , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 14));
		MODE32_TABLE.add("imulb"        , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 15));
		MODE32_TABLE.add("divb"         , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 16));
		MODE32_TABLE.add("idivb"        , opPat(RM8)                 , MODELESS, new int[]{0xF6}, new ModRM(0, 17));
		MODE32_TABLE.add("testw"        , opPat(RM16, IMM16)         , MODE16  , new int[]{0xF7}, new ModRM(0, 10));
		MODE32_TABLE.add("notw"         , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 12));
		MODE32_TABLE.add("negw"         , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 13));
		MODE32_TABLE.add("mulw"         , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 14));
		MODE32_TABLE.add("imulw"        , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 15));
		MODE32_TABLE.add("divw"         , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 16));
		MODE32_TABLE.add("idivw"        , opPat(RM16)                , MODE16  , new int[]{0xF7}, new ModRM(0, 17));
		MODE32_TABLE.add("testl"        , opPat(RM32, IMM32)         , MODE32  , new int[]{0xF7}, new ModRM(0, 10));
		MODE32_TABLE.add("notl"         , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 12));
		MODE32_TABLE.add("negl"         , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 13));
		MODE32_TABLE.add("mull"         , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 14));
		MODE32_TABLE.add("imull"        , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 15));
		MODE32_TABLE.add("divl"         , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 16));
		MODE32_TABLE.add("idivl"        , opPat(RM32)                , MODE32  , new int[]{0xF7}, new ModRM(0, 17));
		MODE32_TABLE.add("clc"          , opPat()                    , MODELESS, new int[]{0xF8});
		MODE32_TABLE.add("stc"          , opPat()                    , MODELESS, new int[]{0xF9});
		MODE32_TABLE.add("cli"          , opPat()                    , MODELESS, new int[]{0xFA});
		MODE32_TABLE.add("sti"          , opPat()                    , MODELESS, new int[]{0xFB});
		MODE32_TABLE.add("cld"          , opPat()                    , MODELESS, new int[]{0xFC});
		MODE32_TABLE.add("std"          , opPat()                    , MODELESS, new int[]{0xFD});
		MODE32_TABLE.add("incb"         , opPat(RM8)                 , MODELESS, new int[]{0xFE}, new ModRM(0, 10));
		MODE32_TABLE.add("decb"         , opPat(RM8)                 , MODELESS, new int[]{0xFE}, new ModRM(0, 11));
		MODE32_TABLE.add("incw"         , opPat(RM16)                , MODE16  , new int[]{0xFF}, new ModRM(0, 10));
		MODE32_TABLE.add("decw"         , opPat(RM16)                , MODE16  , new int[]{0xFF}, new ModRM(0, 11));
		MODE32_TABLE.add("callw"        , opPat(RM16)                , MODE16  , new int[]{0xFF}, new ModRM(0, 12));
		MODE32_TABLE.add("lcallw"       , opPat(MEM)                 , MODE16  , new int[]{0xFF}, new ModRM(0, 13));
		MODE32_TABLE.add("jmpw"         , opPat(RM16)                , MODE16  , new int[]{0xFF}, new ModRM(0, 14));
		MODE32_TABLE.add("ljmpw"        , opPat(MEM)                 , MODE16  , new int[]{0xFF}, new ModRM(0, 15));
		MODE32_TABLE.add("pushw"        , opPat(RM16)                , MODE16  , new int[]{0xFF}, new ModRM(0, 16));
		MODE32_TABLE.add("incl"         , opPat(RM32)                , MODE32  , new int[]{0xFF}, new ModRM(0, 10));
		MODE32_TABLE.add("decl"         , opPat(RM32)                , MODE32  , new int[]{0xFF}, new ModRM(0, 11));
		MODE32_TABLE.add("calll"        , opPat(RM32)                , MODE32  , new int[]{0xFF}, new ModRM(0, 12));
		MODE32_TABLE.add("lcalll"       , opPat(MEM)                 , MODE32  , new int[]{0xFF}, new ModRM(0, 13));
		MODE32_TABLE.add("jmpl"         , opPat(RM32)                , MODE32  , new int[]{0xFF}, new ModRM(0, 14));
		MODE32_TABLE.add("ljmpl"        , opPat(MEM)                 , MODE32  , new int[]{0xFF}, new ModRM(0, 15));
		MODE32_TABLE.add("pushl"        , opPat(RM32)                , MODE32  , new int[]{0xFF}, new ModRM(0, 16));
		MODE32_TABLE.add("invd"         , opPat()                    , MODELESS, new int[]{0x0F, 0x08});
		MODE32_TABLE.add("wbinvd"       , opPat()                    , MODELESS, new int[]{0x0F, 0x09});
		MODE32_TABLE.add("ud2"          , opPat()                    , MODELESS, new int[]{0x0F, 0x0B});
		MODE32_TABLE.add("sysenter"     , opPat()                    , MODELESS, new int[]{0x0F, 0x34});
		MODE32_TABLE.add("sysexit"      , opPat()                    , MODELESS, new int[]{0x0F, 0x35});
		MODE32_TABLE.add("jo"           , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x80});
		MODE32_TABLE.add("jno"          , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x81});
		MODE32_TABLE.add("jb|jnae|jc"   , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x82});
		MODE32_TABLE.add("jnb|jae|jnc"  , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x83});
		MODE32_TABLE.add("jz|je"        , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x84});
		MODE32_TABLE.add("jnz|jne"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x85});
		MODE32_TABLE.add("jbe|jna"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x86});
		MODE32_TABLE.add("jnbe|ja"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x87});
		MODE32_TABLE.add("js"           , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x88});
		MODE32_TABLE.add("jns"          , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x89});
		MODE32_TABLE.add("jp|jpe"       , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8A});
		MODE32_TABLE.add("jnp|jpo"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8B});
		MODE32_TABLE.add("jl|jnge"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8C});
		MODE32_TABLE.add("jnl|jge"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8D});
		MODE32_TABLE.add("jle|jng"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8E});
		MODE32_TABLE.add("jnle|jg"      , opPat(REL16)               , MODE16  , new int[]{0x0F, 0x8F});
		MODE32_TABLE.add("jo"           , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x80});
		MODE32_TABLE.add("jno"          , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x81});
		MODE32_TABLE.add("jb|jnae|jc"   , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x82});
		MODE32_TABLE.add("jnb|jae|jnc"  , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x83});
		MODE32_TABLE.add("jz|je"        , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x84});
		MODE32_TABLE.add("jnz|jne"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x85});
		MODE32_TABLE.add("jbe|jna"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x86});
		MODE32_TABLE.add("jnbe|ja"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x87});
		MODE32_TABLE.add("js"           , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x88});
		MODE32_TABLE.add("jns"          , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x89});
		MODE32_TABLE.add("jp|jpe"       , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8A});
		MODE32_TABLE.add("jnp|jpo"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8B});
		MODE32_TABLE.add("jl|jnge"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8C});
		MODE32_TABLE.add("jnl|jge"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8D});
		MODE32_TABLE.add("jle|jng"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8E});
		MODE32_TABLE.add("jnle|jg"      , opPat(REL32)               , MODE32  , new int[]{0x0F, 0x8F});
		MODE32_TABLE.add("pushw"        , opPat(FS)                  , MODELESS, new int[]{0x0F, 0xA0});
		MODE32_TABLE.add("popw"         , opPat(FS)                  , MODELESS, new int[]{0x0F, 0xA1});
		MODE32_TABLE.add("cpuid"        , opPat()                    , MODELESS, new int[]{0x0F, 0xA2});
		MODE32_TABLE.add("btw"          , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xA3}, new ModRM(0, 1));
		MODE32_TABLE.add("btl"          , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xA3}, new ModRM(0, 1));
		MODE32_TABLE.add("shldw"        , opPat(RM16, REG16, IMM8)   , MODE16  , new int[]{0x0F, 0xA4}, new ModRM(0, 1));
		MODE32_TABLE.add("shldl"        , opPat(RM32, REG32, IMM8)   , MODE32  , new int[]{0x0F, 0xA4}, new ModRM(0, 1));
		MODE32_TABLE.add("shldw"        , opPat(RM16, REG16, CL)     , MODE16  , new int[]{0x0F, 0xA5}, new ModRM(0, 1));
		MODE32_TABLE.add("shldl"        , opPat(RM32, REG32, CL)     , MODE32  , new int[]{0x0F, 0xA5}, new ModRM(0, 1));
		MODE32_TABLE.add("pushw"        , opPat(GS)                  , MODELESS, new int[]{0x0F, 0xA8});
		MODE32_TABLE.add("popw"         , opPat(GS)                  , MODELESS, new int[]{0x0F, 0xA9});
		MODE32_TABLE.add("btsw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xAB}, new ModRM(0, 1));
		MODE32_TABLE.add("btsl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xAB}, new ModRM(0, 1));
		MODE32_TABLE.add("shrdw"        , opPat(RM16, REG16, IMM8)   , MODE16  , new int[]{0x0F, 0xAC}, new ModRM(0, 1));
		MODE32_TABLE.add("shrdl"        , opPat(RM32, REG32, IMM8)   , MODE32  , new int[]{0x0F, 0xAC}, new ModRM(0, 1));
		MODE32_TABLE.add("shrdw"        , opPat(RM16, REG16, CL)     , MODE16  , new int[]{0x0F, 0xAD}, new ModRM(0, 1));
		MODE32_TABLE.add("shrdl"        , opPat(RM32, REG32, CL)     , MODE32  , new int[]{0x0F, 0xAD}, new ModRM(0, 1));
		MODE32_TABLE.add("imulw"        , opPat(REG16, RM16)         , MODE16  , new int[]{0x0F, 0xAF}, new ModRM(1, 0));
		MODE32_TABLE.add("imull"        , opPat(REG32, RM32)         , MODE32  , new int[]{0x0F, 0xAF}, new ModRM(1, 0));
		MODE32_TABLE.add("cmpxchgb"     , opPat(RM8, REG8)           , MODELESS, new int[]{0x0F, 0xB0}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpxchgw"     , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xB1}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpxchgl"     , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xB1}, new ModRM(0, 1));
		MODE32_TABLE.add("lssw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0x0F, 0xB2}, new ModRM(1, 0));
		MODE32_TABLE.add("lssl"         , opPat(REG32, MEM)          , MODE32  , new int[]{0x0F, 0xB2}, new ModRM(1, 0));
		MODE32_TABLE.add("btrw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xB3}, new ModRM(0, 1));
		MODE32_TABLE.add("btrl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xB3}, new ModRM(0, 1));
		MODE32_TABLE.add("lfsw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0x0F, 0xB4}, new ModRM(1, 0));
		MODE32_TABLE.add("lfsl"         , opPat(REG32, MEM)          , MODE32  , new int[]{0x0F, 0xB4}, new ModRM(1, 0));
		MODE32_TABLE.add("lgsw"         , opPat(REG16, MEM)          , MODE16  , new int[]{0x0F, 0xB5}, new ModRM(1, 0));
		MODE32_TABLE.add("lgsl"         , opPat(REG32, MEM)          , MODE32  , new int[]{0x0F, 0xB5}, new ModRM(1, 0));
		MODE32_TABLE.add("movzxwb"      , opPat(REG16, RM8)          , MODE16  , new int[]{0x0F, 0xB6}, new ModRM(1, 0));
		MODE32_TABLE.add("movzxlb"      , opPat(REG32, RM8)          , MODE32  , new int[]{0x0F, 0xB6}, new ModRM(1, 0));
		MODE32_TABLE.add("movzxww"      , opPat(REG16, RM16)         , MODE16  , new int[]{0x0F, 0xB7}, new ModRM(1, 0));
		MODE32_TABLE.add("movzxlw"      , opPat(REG32, RM16)         , MODE32  , new int[]{0x0F, 0xB7}, new ModRM(1, 0));
		MODE32_TABLE.add("btw"          , opPat(RM16, IMM8)          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 14));
		MODE32_TABLE.add("btl"          , opPat(RM32, IMM8)          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 14));
		MODE32_TABLE.add("btsw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 15));
		MODE32_TABLE.add("btsl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 15));
		MODE32_TABLE.add("btrw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 16));
		MODE32_TABLE.add("btrl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 16));
		MODE32_TABLE.add("btcw"         , opPat(RM16, IMM8)          , MODE16  , new int[]{0x0F, 0xBA}, new ModRM(0, 17));
		MODE32_TABLE.add("btcl"         , opPat(RM32, IMM8)          , MODE32  , new int[]{0x0F, 0xBA}, new ModRM(0, 17));
		MODE32_TABLE.add("btcw"         , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xBB}, new ModRM(0, 1));
		MODE32_TABLE.add("btcl"         , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xBB}, new ModRM(0, 1));
		MODE32_TABLE.add("bsfw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x0F, 0xBC}, new ModRM(1, 0));
		MODE32_TABLE.add("bsfl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x0F, 0xBC}, new ModRM(1, 0));
		MODE32_TABLE.add("bsrw"         , opPat(REG16, RM16)         , MODE16  , new int[]{0x0F, 0xBD}, new ModRM(1, 0));
		MODE32_TABLE.add("bsrl"         , opPat(REG32, RM32)         , MODE32  , new int[]{0x0F, 0xBD}, new ModRM(1, 0));
		MODE32_TABLE.add("movsxwb"      , opPat(REG16, RM8)          , MODE16  , new int[]{0x0F, 0xBE}, new ModRM(1, 0));
		MODE32_TABLE.add("movsxlb"      , opPat(REG32, RM8)          , MODE32  , new int[]{0x0F, 0xBE}, new ModRM(1, 0));
		MODE32_TABLE.add("movsxww"      , opPat(REG16, RM16)         , MODE16  , new int[]{0x0F, 0xBF}, new ModRM(1, 0));
		MODE32_TABLE.add("movsxlw"      , opPat(REG32, RM16)         , MODE32  , new int[]{0x0F, 0xBF}, new ModRM(1, 0));
		MODE32_TABLE.add("xaddb"        , opPat(RM8, REG8)           , MODELESS, new int[]{0x0F, 0xC0}, new ModRM(0, 1));
		MODE32_TABLE.add("xaddw"        , opPat(RM16, REG16)         , MODE16  , new int[]{0x0F, 0xC1}, new ModRM(0, 1));
		MODE32_TABLE.add("xaddl"        , opPat(RM32, REG32)         , MODE32  , new int[]{0x0F, 0xC1}, new ModRM(0, 1));
		MODE32_TABLE.add("cmpxchg8b"    , opPat(MEM)                 , MODELESS, new int[]{0x0F, 0xC7}, new ModRM(0, 11));
		MODE32_TABLE.add("bswapw"       , opPat(REG16)               , MODE16  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0));
		MODE32_TABLE.add("bswapl"       , opPat(REG32)               , MODE32  , new int[]{0x0F, 0xC8}, new RegisterInOpcode(0));
	}
	
	
	
	private Set<InstructionPattern> patterns;
	
	private Map<String,Set<InstructionPattern>> patternsByMnemonic;
	
	
	
	private InstructionPatternTable() {
		patterns = new HashSet<InstructionPattern>();
		patternsByMnemonic = new HashMap<String,Set<InstructionPattern>>();
	}
	
	
	
	private void add(InstructionPattern pat) {
		if (pat == null)
			throw new NullPointerException();
		patterns.add(pat);
		
		if (!patternsByMnemonic.containsKey(pat.mnemonic))
			patternsByMnemonic.put(pat.mnemonic, new HashSet<InstructionPattern>());
		patternsByMnemonic.get(pat.mnemonic).add(pat);
	}
	
	
	private void add(String mnemonics, OperandPattern[] operands, OperandSizeMode operandSizeMode, int[] opcodes, InstructionOption... options) {
		for (String mnemonic : mnemonics.split("\\|"))
			add(new InstructionPattern(mnemonic, operands, operandSizeMode, opcodes, options));
	}
	
	
	private static OperandPattern[] opPat(OperandPattern... operands) {
		if (operands == null)
			throw new NullPointerException();
		return operands;
	}
	
	
	public InstructionPattern match(String mnemonic, List<Operand> operands) {
		if (mnemonic == null || operands == null)
			throw new NullPointerException();
		if (!patternsByMnemonic.containsKey(mnemonic))
			throw new IllegalArgumentException("Invalid mnemonic: " + mnemonic);
		
		InstructionPattern bestmatch = null;
		for (InstructionPattern pat : patternsByMnemonic.get(mnemonic)) {
			if (matches(pat, operands) && (bestmatch == null || isBetterMatch(pat, bestmatch, operands))) {
				bestmatch = pat;
			}
		}
		
		if (bestmatch != null)
			return bestmatch;
		else
			throw new IllegalArgumentException("No match: " + mnemonic);
	}
	
	
	private static boolean matches(InstructionPattern pat, List<Operand> operands) {
		if (pat.operands.length != operands.size())
			return false;
		for (int i = 0; i < pat.operands.length && i < operands.size(); i++) {
			if (!pat.operands[i].matches(operands.get(i)))
				return false;
		}
		return true;
	}
	
	
	private static boolean isBetterMatch(InstructionPattern x, InstructionPattern y, List<Operand> operands) {
		boolean isbetter = false;
		boolean isworse = false;
		
		for (int i = 0; i < operands.size(); i++) {
			if (x.operands[i] == REL8 || x.operands[i] == REL16 || x.operands[i] == REL32) {
				// Wider is better
				isbetter |= isWider(x.operands[i], y.operands[i]);
				isworse |= isWider(y.operands[i], x.operands[i]);
			}
		}
		
		return !isworse && isbetter;
	}
	
	
	private static boolean isWider(OperandPattern x, OperandPattern y) {
		return getWidth(x) > getWidth(y);
	}
	
	
	private static int getWidth(OperandPattern op) {
		if (op == null)
			throw new NullPointerException();
		if (op == REL8)
			return 8;
		else if (op == REL16)
			return 16;
		else if (op == REL32)
			return 32;
		else
			throw new IllegalArgumentException("Not applicable to operand");
	}
	
}
