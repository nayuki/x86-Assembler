package org.p79068.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * An assembly language program. A program consists of a list of statements. Program objects are mutable.
 */
public final class Program {
	
	/** The list of statements. */
	private List<Statement> statements;
	
	
	
	/**
	 * Constructs a program with an empty list of statements.
	 */
	public Program() {
		statements = new ArrayList<Statement>();
	}
	
	
	
	/**
	 * Returns a read-only view of the list of statements in this program. The data will change as the program changes.
	 * @return the list of statements in this program
	 */
	public List<Statement> getStatements() {
		return Collections.unmodifiableList(statements);
	}
	
	
	/**
	 * Appends the specified statement to the list of statements in this program.
	 * @param statement the statement to append
	 */
	public void addStatement(Statement statement) {
		if (statement == null)
			throw new NullPointerException();
		statements.add(statement);
	}
	
}
