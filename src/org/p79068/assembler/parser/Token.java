package org.p79068.assembler.parser;


/**
 * A lexical token. A token has a type and a string of captured text.
 * @see Tokenizer
 */
final class Token {
	
	/** The type of this token. */
	public final TokenType type;
	
	/** The text captured by this token. */
	public final String text;
	
	
	
	/**
	 * Constructs a token with the specified type and text.
	 * @param type the type
	 * @param text the captured text
	 * @throws NullPointerException if the type or the text is {@code null}
	 */
	Token(TokenType type, String text) {
		if (type == null || text == null)
			throw new NullPointerException();
		this.type = type;
		this.text = text;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Token))
			return false;
		else {
			Token other = (Token)obj;
			return type.equals(other.type) && text.equals(other.text);
		}
	}
	
	
	@Override
	public int hashCode() {
		return type.hashCode() + text.hashCode();
	}
	
	
	/**
	 * Returns a string representation of this token. The format is subjected to change.
	 * @return a string representation of this token
	 */
	@Override
	public String toString() {
		return String.format("[%s %s]", type, text);
	}
	
}
