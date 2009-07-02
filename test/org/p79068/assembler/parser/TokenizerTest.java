package org.p79068.assembler.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class TokenizerTest {
	
	@Test
	public void testEmpty() {
		Tokenizer t = new Tokenizer("");
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testSymbols() {
		Tokenizer t = new Tokenizer("$(),");
		assertEquals(new Token(TokenType.DOLLAR, "$"), t.next());
		assertEquals(new Token(TokenType.LEFT_PAREN, "("), t.next());
		assertEquals(new Token(TokenType.RIGHT_PAREN, ")"), t.next());
		assertEquals(new Token(TokenType.COMMA, ","), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testWhitespace() {
		Tokenizer t = new Tokenizer(" $\t(  ) \t ");
		assertEquals(new Token(TokenType.DOLLAR, "$"), t.next());
		assertEquals(new Token(TokenType.LEFT_PAREN, "("), t.next());
		assertEquals(new Token(TokenType.RIGHT_PAREN, ")"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testName() {
		Tokenizer t = new Tokenizer("name");
		assertEquals(new Token(TokenType.NAME, "name"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testLabel() {
		Tokenizer t = new Tokenizer("label:");
		assertEquals(new Token(TokenType.LABEL, "label:"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testRegister() {
		Tokenizer t = new Tokenizer("%reg");
		assertEquals(new Token(TokenType.REGISTER, "%reg"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testDecimal() {
		Tokenizer t = new Tokenizer("0 1 -23");
		assertEquals(new Token(TokenType.DECIMAL, "0"), t.next());
		assertEquals(new Token(TokenType.DECIMAL, "1"), t.next());
		assertEquals(new Token(TokenType.DECIMAL, "-23"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
	
	@Test
	public void testHexadecimal() {
		Tokenizer t = new Tokenizer("0x0 0X1 0xdead 0xBEEF");
		assertEquals(new Token(TokenType.HEXADECIMAL, "0x0"), t.next());
		assertEquals(new Token(TokenType.HEXADECIMAL, "0X1"), t.next());
		assertEquals(new Token(TokenType.HEXADECIMAL, "0xdead"), t.next());
		assertEquals(new Token(TokenType.HEXADECIMAL, "0xBEEF"), t.next());
		assertEquals(new Token(TokenType.END_OF_FILE, ""), t.next());
	}
	
}
