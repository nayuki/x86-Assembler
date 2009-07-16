package org.p79068.assembler;

import java.io.File;
import java.io.IOException;

import org.p79068.assembler.generator.Assembler;
import org.p79068.assembler.parser.Parser;


/**
 * The Project79068 assembler main program.
 */
public final class Main {
	
	/**
	 * The main method. Argument 0 is the input file name. Argument 1 is the output file name.
	 * @param args the list of command line arguments
	 * @throws IOException if an I/O exception occurred
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: java org.p79068.assembler.Main INPUTFILE OUTPUTFILE");
			System.exit(1);
		}
		
		File inputfile = new File(args[0]);
		File outputfile = new File(args[1]);
		
		Program program = Parser.parseFile(inputfile);
		Assembler.assembleToFile(program, outputfile);
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Main() {}
	
}
