package org.p79068.assembler;

import java.io.File;
import java.io.IOException;

import org.p79068.assembler.generator.Assembler;
import org.p79068.assembler.parser.Parser;


public final class Main {
	
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
