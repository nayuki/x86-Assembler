package org.p79068.assembler.parser;


final class Token {
	
	public final TokenType type;
	
	public final String text;
	
	
	
	Token(TokenType type, String text) {
		if (text == null)
			throw new NullPointerException();
		this.type = type;
		this.text = text;
	}
	
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Token))
			return false;
		else {
			Token other = (Token)obj;
			return type.equals(other.type) && text.equals(other.text);
		}
	}
	
	
	public int hashCode() {
		return type.hashCode() + text.hashCode();
	}
	
	
	public String toString() {
		return String.format("[%s %s]", type, text);
	}
	
}
