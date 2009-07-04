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
	
	private static final int ENTRY_POINT = 0x00100054;
	
	
	
	public static void assembleToFile(Program program, File outputfile) throws IOException {
		if (program == null || outputfile == null)
			throw new NullPointerException();
		
		computeLabelOffsets(program);
		assembleProgram(program, outputfile);
	}
	
	
	private static void computeLabelOffsets(Program program) {
		int offset = ENTRY_POINT;
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
			out.write(makeElfHeader(ENTRY_POINT));
			out.write(makeProgramHeader(ENTRY_POINT, code.length));
			out.write(code);
		} finally {
			out.close();
		}
	}
	
	
	private static byte[] makeElfHeader(int entryPoint) {
		return new byte[] {
			0x7F, 'E', 'L', 'F',  // Magic number
			0x01,  // Class: 0x01 = 32-bit
			0x01,  // Data: 0x01 = Little endian
			0x01,  // Version: 0x01 = Current
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,  // Padding: 9 bytes of zeros
			0x02, 0x00,  // Type: 0x0002 = Executable file
			0x03, 0x00,  // Machine: 0x0003 = Intel 80386
			0x01, 0x00, 0x00, 0x00,  // Version: 0x00000001 = Current
			(byte)(entryPoint >>> 0), (byte)(entryPoint >>> 8), (byte)(entryPoint >>> 16), (byte)(entryPoint >>> 24),  // Enttry point
			0x34, 0x00, 0x00, 0x00,  // Program header offset
			0x00, 0x00, 0x00, 0x00,  // Section header offset
			0x00, 0x00, 0x00, 0x00,  // Flags (unused on xcodeSize86)
			0x34, 0x00,  // ELF header size
			0x20, 0x00,  // Program header entry size
			0x01, 0x00,  // Program header entry count
			0x00, 0x00,  // Section header entry size
			0x00, 0x00,  // Section header entry count
			0x00, 0x00   // Section name string table section index
		};
	}
	
	
	private static byte[] makeProgramHeader(int virtualAddress, int codeSize) {
		return new byte[] {
			0x01, 0x00, 0x00, 0x00,  // Type: 0x00000001 = Loadable
			0x54, 0x00, 0x00, 0x00,  // File offset
			(byte)(virtualAddress >>> 0), (byte)(virtualAddress >>> 8), (byte)(virtualAddress >>> 16), (byte)(virtualAddress >>> 24),  // Virtual address
			0x00, 0x00, 0x00, 0x00,  // Physical address (ignored on x86)
			(byte)(codeSize >>> 0), (byte)(codeSize >>> 8), (byte)(codeSize >>> 16), (byte)(codeSize >>> 24),  // Size in file
			(byte)(codeSize >>> 0), (byte)(codeSize >>> 8), (byte)(codeSize >>> 16), (byte)(codeSize >>> 24),  // Size in memory
			0x07, 0x00, 0x00, 0x00,  // Flags: 0x00000007 = Read, write, execute
			0x00, 0x10, 0x00, 0x00   // Alignment: 0x00001000 = 4 KiB
		};
	}
	
	
	private static byte[] assembleToBytes(Program program) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(); 
			int offset = ENTRY_POINT;
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
