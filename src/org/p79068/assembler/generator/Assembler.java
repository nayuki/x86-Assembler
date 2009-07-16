package org.p79068.assembler.generator;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.Statement;
import org.p79068.assembler.operand.Operand;
import org.p79068.libelf.ElfFile;
import org.p79068.libelf.ElfHeader;
import org.p79068.libelf.ObjectType;
import org.p79068.libelf.ProgramHeader;
import org.p79068.libelf.SegmentType;


public final class Assembler {
	
	private static InstructionPatternTable patterntable = InstructionPatternTable.MODE32_TABLE;
	
	private static final int ENTRY_POINT = 0x00100054;
	
	
	
	public static void assembleToFile(Program program, File outputfile) throws IOException {
		if (program == null || outputfile == null)
			throw new NullPointerException();
		
		Map<String,Integer> labelOffsets = computeLabelOffsets(program);
		assembleProgram(program, labelOffsets, outputfile);
	}
	
	
	private static Map<String,Integer> computeLabelOffsets(Program program) {
		Map<String,Integer> result = new HashMap<String,Integer>();
		int offset = ENTRY_POINT;
		for (Statement st : program.getStatements()) {
			if (st instanceof InstructionStatement) {
				InstructionStatement ist = (InstructionStatement)st;
				String mnemonic = ist.getMnemonic();
				List<Operand> operands = ist.getOperands();
				int length = CodeGenerator.getMachineCodeLength(patterntable, mnemonic, operands);
				offset += length;
			} else if (st instanceof LabelStatement) {
				String name = ((LabelStatement)st).getName();
				result.put(name, offset);
			}
		}
		return result;
	}
	
	
	private static void assembleProgram(Program program, Map<String,Integer> labelOffsets, File outputfile) throws IOException {
		byte[] code = assembleToBytes(program, labelOffsets);
		
		ElfFile elf = new ElfFile();
		
		ElfHeader eh = new ElfHeader();
		eh.type = ObjectType.ET_EXEC;
		eh.entry = ENTRY_POINT;
		eh.shstrndx = 0;
		elf.elfHeader = eh;
		
		ProgramHeader ph = new ProgramHeader();
		ph.type = SegmentType.PT_LOAD;
		ph.vaddr = ENTRY_POINT;
		ph.filesz = code.length;
		ph.memsz = code.length;
		ph.flags = 0x7;
		ph.align = 0x1000;
		elf.programHeaders.add(ph);
		ph.offset = elf.getDataOffset();
		
		elf.data = code;
		
		OutputStream out0 = new FileOutputStream(outputfile);
		OutputStream out = new BufferedOutputStream(out0);
		
		try {
			out.write(elf.toBytes());
		} finally {
			out.close();
		}
	}
	
	
	private static byte[] assembleToBytes(Program program, Map<String,Integer> labelOffsets) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(); 
			int offset = ENTRY_POINT;
			for (Statement st : program.getStatements()) {
				if (st instanceof InstructionStatement) {
					InstructionStatement ist = (InstructionStatement)st;
					String mnemonic = ist.getMnemonic();
					List<Operand> operands = ist.getOperands();
					byte[] machinecode = CodeGenerator.makeMachineCode(patterntable, mnemonic, operands, program, labelOffsets, offset);
					out.write(machinecode);
					offset += machinecode.length;
				} else if (st instanceof LabelStatement) {
					String name = ((LabelStatement)st).getName();
					if (offset != labelOffsets.get(name))
						throw new AssertionError("Label offset mismatch");
				}
			}
			return out.toByteArray();
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Assembler() {}
	
}
