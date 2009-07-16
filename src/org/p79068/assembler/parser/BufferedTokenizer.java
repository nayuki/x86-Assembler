package org.p79068.assembler.parser;


/**
 * Decorates a tokenizer with peeking capabilities.
 */
final class BufferedTokenizer extends Tokenizer {
	
	/**
	 * The underlying tokenizer, which provides the tokens.
	 */
	private Tokenizer tokenizer;
	
	/**
	 * The next token. The peek() method saves the token here. It is cleared to {@code null} when next() is called.
	 */
	private Token nextToken;
	
	
	
	/**
	 * Constructs a buffered tokenizer from the specified tokenizer.
	 * @param tokenizer the underlying tokenizer providing the tokens
	 */
	public BufferedTokenizer(Tokenizer tokenizer) {
		if (tokenizer == null)
			throw new NullPointerException();
		this.tokenizer = tokenizer;
		nextToken = null;
	}
	
	
	
	/**
	 * Returns the next token from this tokenizer.
	 * @return the next token from this tokenizer
	 */
	public Token next() {
		if (nextToken != null) {
			Token result = nextToken;
			nextToken = null;
			return result;
		} else
			return tokenizer.next();
	}
	
	
	/**
	 * Returns the next token without consuming it. Consecutive calls to this method return the same value.
	 * @return the next token
	 */
	public Token peek() {
		if (nextToken == null)
			nextToken = tokenizer.next();
		return nextToken;
	}
	
	
	/**
	 * Peeks at the next token and tests whether it has the specified type. Returns {@code peek().type == type}. 
	 * @param type the type to test against
	 * @return {@code true} if the next token (without consuming it) has the specified type, {@code false} otherwise
	 */
	public boolean check(TokenType type) {
		return peek().type == type;
	}
	
}
