package org.p79068.assembler.generator;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.Statement;
import org.p79068.assembler.operand.Operand;


public final class Assembler {
	
	private static InstructionPatternTable patterntable = InstructionPatternTable.MODE32_TABLE;
	
	
	public static void assembleToFile(Program program, File outputfile) throws IOException {
		if (program == null || outputfile == null)
			throw new NullPointerException();
		
		computeLabelOffsets(program);
		assembleProgram(program, outputfile);
	}
	
	
	private static void computeLabelOffsets(Program program) {
		int offset = 0x08000054;
		for (Statement st : program.getStatements()) {
			if (st instanceof InstructionStatement) {
				InstructionStatement ist = (InstructionStatement)st;
				String mnemonic = ist.getMnemonic();
				Operand[] operands = ist.getOperands();
				int length = CodeGenerator.getMachineCodeLength(patterntable, mnemonic, operands);
				offset += length;
			} else if (st instanceof LabelStatement) {
				String name = ((LabelStatement)st).getName();
				program.addLabel(name, offset);
			}
		}
	}
	
	
	private static void assembleProgram(Program program, File outputfile) throws IOException {
		byte[] code = assembleToBytes(program);
		OutputStream out0 = new FileOutputStream(outputfile);
		OutputStream out = new BufferedOutputStream(out0);
		
		try {
			writeElfHeader(code, out);
			out.write(code);
		} finally {
			out.close();
		}
	}
	
	
	private static void writeElfHeader(byte[] code, OutputStream out) throws IOException {
		// Write ELF header
		
		// e_ident
		out.write(new byte[]{0x7F, 'E', 'L', 'F'});  // Magic number
		out.write(0x01);  // Class: 0x01 = 32-bit
		out.write(0x01);  // Data: 0x01 = Little endian
		out.write(0x01);  // Version: 0x01 = Current
		out.write(new byte[9]);  // Padding: 9 bytes of zeros
		
		// e_type
		out.write(0x02); out.write(0x00);  // Type: 0x0002 = Executable file
		
		// e_machine
		out.write(0x03); out.write(0x00);  // Machine: 0x0003 = Intel 80386
		
		// e_version
		out.write(0x01); out.write(0x00); out.write(0x00); out.write(0x00);  // Version: 0x00000001 = Current
		
		// e_entry
		out.write(0x54); out.write(0x00); out.write(0x00); out.write(0x08);  // Entry point: 0x08000000
		
		// e_phoff
		out.write(0x34); out.write(0x00); out.write(0x00); out.write(0x00);
		
		// e_shoff
		out.write(0x00); out.write(0x00); out.write(0x00); out.write(0x00);
		
		// e_flags
		out.write(0x00); out.write(0x00); out.write(0x00); out.write(0x00);
		
		// e_ehsize
		out.write(0x34); out.write(0x00);
		
		// e_phentsize
		out.write(0x20); out.write(0x00);
		
		// e_phnum
		out.write(0x01); out.write(0x00);
		
		// e_shentsize
		out.write(0x00); out.write(0x00);
		
		// e_shnum
		out.write(0x00); out.write(0x00);
		
		// e_shstrndx
		out.write(0x00); out.write(0x00);
		
		// Write program header
		
		// p_type
		out.write(0x01); out.write(0x00); out.write(0x00); out.write(0x00);  // Type: 0x00000001 = Loadable
		
		// p_offset
		out.write(0x54); out.write(0x00); out.write(0x00); out.write(0x00);
		
		// p_vaddr
		out.write(0x54); out.write(0x00); out.write(0x00); out.write(0x08);
		
		// p_paddr
		out.write(0x00); out.write(0x00); out.write(0x00); out.write(0x00);  // Physical address: Ignored
		
		// p_filesz
		out.write(code.length >>> 0); out.write(code.length >>> 8); out.write(code.length >>> 16); out.write(code.length >>> 24);
		
		// p_memsz
		out.write(code.length >>> 0); out.write(code.length >>> 8); out.write(code.length >>> 16); out.write(code.length >>> 24);
		
		// p_flags
		out.write(0x07); out.write(0x00); out.write(0x00); out.write(0x00);
		
		// p_align
		out.write(0x00); out.write(0x10); out.write(0x00); out.write(0x00);
	}
	
	
	private static byte[] assembleToBytes(Program program) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(); 
			int offset = 0x08000054;
			for (Statement st : program.getStatements()) {
				if (st instanceof InstructionStatement) {
					InstructionStatement ist = (InstructionStatement)st;
					String mnemonic = ist.getMnemonic();
					Operand[] operands = ist.getOperands();
					byte[] machinecode = CodeGenerator.makeMachineCode(patterntable, mnemonic, operands, program, offset);
					out.write(machinecode);
					offset += machinecode.length;
				} else if (st instanceof LabelStatement) {
					String name = ((LabelStatement)st).getName();
					if (offset != program.getLabelOffset(name))
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
