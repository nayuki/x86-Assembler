package org.p79068.assembler.parser;

import static org.junit.Assert.assertEquals;
import static org.p79068.assembler.parser.TokenType.*;

import org.junit.Test;



public class StringTokenizerTest {
	
	private static Tokenizer newTokenizer(String code) {
		return new StringTokenizer(code);
	}
	
	
	@Test
	public void testEmpty() {
		Tokenizer t = newTokenizer("");
		compareNext(t, END_OF_FILE, "");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testSymbols() {
		Tokenizer t = newTokenizer("$(),");
		compareNext(t, DOLLAR, "$");
		compareNext(t, LEFT_PAREN, "(");
		compareNext(t, RIGHT_PAREN, ")");
		compareNext(t, COMMA, ",");
		compareNext(t, END_OF_FILE, "");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testWhitespace() {
		Tokenizer t = newTokenizer(" $\t(  ) \t ");
		compareNext(t, DOLLAR, "$");
		compareNext(t, LEFT_PAREN, "(");
		compareNext(t, RIGHT_PAREN, ")");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testName() {
		Tokenizer t = newTokenizer("name");
		compareNext(t, NAME, "name");
		compareNext(t, END_OF_FILE, "");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testLabel() {
		Tokenizer t = newTokenizer("label:");
		compareNext(t, LABEL, "label:");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testRegister() {
		Tokenizer t = newTokenizer("%reg");
		compareNext(t, REGISTER, "%reg");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testDecimal() {
		Tokenizer t = newTokenizer("0 1 -23");
		compareNext(t, DECIMAL, "0");
		compareNext(t, DECIMAL, "1");
		compareNext(t, DECIMAL, "-23");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testHexadecimal() {
		Tokenizer t = newTokenizer("0x0 0X1 0xdead 0xBEEF");
		compareNext(t, HEXADECIMAL, "0x0");
		compareNext(t, HEXADECIMAL, "0X1");
		compareNext(t, HEXADECIMAL, "0xdead");
		compareNext(t, HEXADECIMAL, "0xBEEF");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testNewline() {
		Tokenizer t = newTokenizer("0x123ABC\ndef: \r\n\r\n\nGHI \r\r \t");
		compareNext(t, HEXADECIMAL, "0x123ABC");
		compareNext(t, NEWLINE, "\n");
		compareNext(t, LABEL, "def:");
		compareNext(t, NEWLINE, "\r\n\r\n\n");
		compareNext(t, NAME, "GHI");
		compareNext(t, NEWLINE, "\r\r");
		compareNext(t, END_OF_FILE, "");
		compareNext(t, END_OF_FILE, "");
	}
	
	
	@Test
	public void testComment() {
		Tokenizer t = newTokenizer("the#quick\nbrown # fox 123 !@#$% \r jumped");
		compareNext(t, NAME, "the");
		compareNext(t, NEWLINE, "\n");
		compareNext(t, NAME, "brown");
		compareNext(t, NEWLINE, "\r");
		compareNext(t, NAME, "jumped");
	}
	
	
	
	private static void compareNext(Tokenizer t, TokenType type, String text) {
		assertEquals(new Token(type, text), t.next());
	}
	
}
