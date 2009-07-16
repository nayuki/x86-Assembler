package org.p79068.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * An assembly language program. A program consists of a list of statements. Program objects are mutable.
 */
public final class Program {
	
	/** The list of statements. */
	private List<Statement> statements;
	
	private Map<String,Integer> labels;
	
	
	
	/**
	 * Constructs a program with an empty list of statements.
	 */
	public Program() {
		statements = new ArrayList<Statement>();
		labels = new HashMap<String,Integer>();
	}
	
	
	
	/**
	 * Returns a read-only view of the list of statements in this program. The data will change as the program changes.
	 * @return the list of statements in this program
	 */
	public List<Statement> getStatements() {
		return Collections.unmodifiableList(statements);
	}
	
	
	public int getLabelOffset(String name) {
		if (name == null)
			throw new NullPointerException();
		if (!labels.containsKey(name))
			throw new IllegalArgumentException("Label does not exist");
		return labels.get(name);
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
	
	
	public void addLabel(String name, int offset) {
		if (name == null)
			throw new NullPointerException();
		if (labels.containsKey(name))
			throw new IllegalArgumentException("Label already exists");
		labels.put(name, offset);
	}
	
}
