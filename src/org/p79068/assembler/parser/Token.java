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
	
	
	
	/**
	 * Compares this token to the specified object for equality. Returns {@code true} if the specified object is a token with the same type and text. Otherwise returns {@code false}.
	 * @param obj the object to compare this token against
	 * @return {@code true} if the object is a token with the same type and text, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Token))
			return false;
		else {
			Token other = (Token)obj;
			return type.equals(other.type) && text.equals(other.text);
		}
	}
	
	
	/**
	 * Returns the hash code for this token.
	 * @return the hash code for this token.
	 */
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
