package org.p79068.assembler.parser;


/**
 * A stream of {@link Token}s.
 */
abstract class Tokenizer {
	
	/**
	 * Returns the next token from this tokenizer. This is never {@code null} because there is an end-of-file token type.
	 * @return the next token from this tokenizer
	 */
	public abstract Token next();
	
}
