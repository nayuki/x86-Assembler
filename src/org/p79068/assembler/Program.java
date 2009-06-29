package org.p79068.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Program {
	
	private List<Statement> statements;
	
	private Map<String,Integer> labels;
	
	
	
	public Program() {
		statements = new ArrayList<Statement>();
		labels = new HashMap<String,Integer>();
	}

	
	
	public List<Statement> getStatements() {
		return Collections.unmodifiableList(statements);
	}
	
	
	public int getLabelOffset(String name) {
		if (!labels.containsKey(name))
			throw new IllegalArgumentException("Label does not exist");
		return labels.get(name);
	}
	
	
	public void addStatement(Statement statement) {
		statements.add(statement);
	}
	
	
	public void addLabel(String name, int offset) {
		if (labels.containsKey(name))
			throw new IllegalArgumentException("Label already exists");
		labels.put(name, offset);
	}
	
}