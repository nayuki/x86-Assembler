package org.p79068.assembler.generator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import org.p79068.assembler.Program;
import org.p79068.assembler.Statement;
import org.p79068.assembler.operand.Operand;


public final class Assembler {
	
	private static Set<InstructionPattern> patterntable = InstructionPattern.MODE32_PATTERN_TABLE;
	
	
	public static void assembleToFile(Program program, File outputfile) throws IOException {
		OutputStream out0 = new FileOutputStream(outputfile);
		OutputStream out = new BufferedOutputStream(out0);
		
		try {
			for (Statement st : program.getStatements()) {
				String mnemonic = st.getMnemonic();
				Operand[] operands = st.getOperands();
				byte[] machinecode = CodeGenerator.getMachineCode(patterntable, mnemonic, operands);
				out.write(machinecode);
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