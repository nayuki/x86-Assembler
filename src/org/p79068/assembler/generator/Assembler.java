package org.p79068.assembler.generator;

import java.io.BufferedOutputStream;
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
		int offset = 0;
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
		
		OutputStream out0 = new FileOutputStream(outputfile);
		OutputStream out = new BufferedOutputStream(out0);
		
		try {
			offset = 0;
			for (Statement st : program.getStatements()) {
				if (st instanceof InstructionStatement) {
					InstructionStatement ist = (InstructionStatement)st;
					String mnemonic = ist.getMnemonic();
					Operand[] operands = ist.getOperands();
					byte[] machinecode = CodeGenerator.getMachineCode(patterntable, mnemonic, operands, program, offset);
					out.write(machinecode);
					offset += machinecode.length;
				} else if (st instanceof LabelStatement) {
					String name = ((LabelStatement)st).getName();
					if (offset != program.getLabelOffset(name))
						throw new AssertionError();
				}
			}
		} finally {
			out.close();
		}
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Assembler() {}
	
}
