package org.p79068.assembler;

import java.io.File;
import java.io.IOException;

import org.p79068.assembler.generator.Assembler;
import org.p79068.assembler.parser.Parser;


public final class Main {
	
	public static void main(String[] args) throws IOException {
		File inputfile = new File(args[0]);
		File outputfile = new File(args[1]);
		
		Program program = Parser.parseFile(inputfile);
		Assembler.assembleToFile(program, outputfile);
	}
	
	
	
	private Main() {}
	
}