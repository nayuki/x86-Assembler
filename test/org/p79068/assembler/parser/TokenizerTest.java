package org.p79068.assembler.parser;

import static org.junit.Assert.assertEquals;
import static org.p79068.assembler.parser.TokenType.*;

import org.junit.Test;



public class TokenizerTest {
	
	@Test
	public void testEmpty() {
		Tokenizer t = new Tokenizer("");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testSymbols() {
		Tokenizer t = new Tokenizer("$(),");
		compareNext(t, DOLLAR, "$");
		compareNext(t, LEFT_PAREN, "(");
		compareNext(t, RIGHT_PAREN, ")");
		compareNext(t, COMMA, ",");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testWhitespace() {
		Tokenizer t = new Tokenizer(" $\t(  ) \t ");
		compareNext(t, DOLLAR, "$");
		compareNext(t, LEFT_PAREN, "(");
		compareNext(t, RIGHT_PAREN, ")");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testName() {
		Tokenizer t = new Tokenizer("name");
		compareNext(t, NAME, "name");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testLabel() {
		Tokenizer t = new Tokenizer("label:");
		compareNext(t, LABEL, "label:");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testRegister() {
		Tokenizer t = new Tokenizer("%reg");
		compareNext(t, REGISTER, "%reg");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testDecimal() {
		Tokenizer t = new Tokenizer("0 1 -23");
		compareNext(t, DECIMAL, "0");
		compareNext(t, DECIMAL, "1");
		compareNext(t, DECIMAL, "-23");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testHexadecimal() {
		Tokenizer t = new Tokenizer("0x0 0X1 0xdead 0xBEEF");
		compareNext(t, HEXADECIMAL, "0x0");
		compareNext(t, HEXADECIMAL, "0X1");
		compareNext(t, HEXADECIMAL, "0xdead");
		compareNext(t, HEXADECIMAL, "0xBEEF");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	
	private static void compareNext(Tokenizer t, TokenType type, String text) {
		assertEquals(new Token(type, text), t.next());
	}
	
}
