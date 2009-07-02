package org.p79068.assembler.operand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ImmediateValueTest {
	
	@Test
	public void testIsSigned8Bit() {
		assertFalse(new ImmediateValue(Integer.MIN_VALUE).isSigned8Bit());
		assertFalse(new ImmediateValue(-20000000).isSigned8Bit());
		assertFalse(new ImmediateValue(-32768).isSigned8Bit());
		assertFalse(new ImmediateValue(-129).isSigned8Bit());
		assertTrue(new ImmediateValue(-128).isSigned8Bit());
		assertTrue(new ImmediateValue(-48).isSigned8Bit());
		assertTrue(new ImmediateValue(0).isSigned8Bit());
		assertTrue(new ImmediateValue(1).isSigned8Bit());
		assertTrue(new ImmediateValue(32).isSigned8Bit());
		assertTrue(new ImmediateValue(127).isSigned8Bit());
		assertFalse(new ImmediateValue(128).isSigned8Bit());
		assertFalse(new ImmediateValue(7000).isSigned8Bit());
		assertFalse(new ImmediateValue(300000000).isSigned8Bit());
		assertFalse(new ImmediateValue(Integer.MAX_VALUE).isSigned8Bit());
	}
	
	
	@Test
	public void testIsSigned16Bit() {
		assertFalse(new ImmediateValue(Integer.MIN_VALUE).isSigned16Bit());
		assertFalse(new ImmediateValue(-20000000).isSigned16Bit());
		assertFalse(new ImmediateValue(-32769).isSigned16Bit());
		assertTrue(new ImmediateValue(-32768).isSigned16Bit());
		assertTrue(new ImmediateValue(-800).isSigned16Bit());
		assertTrue(new ImmediateValue(0).isSigned16Bit());
		assertTrue(new ImmediateValue(1).isSigned16Bit());
		assertTrue(new ImmediateValue(300).isSigned16Bit());
		assertTrue(new ImmediateValue(32767).isSigned16Bit());
		assertFalse(new ImmediateValue(32768).isSigned16Bit());
		assertFalse(new ImmediateValue(300000000).isSigned16Bit());
		assertFalse(new ImmediateValue(Integer.MAX_VALUE).isSigned16Bit());
	}
	
	
	@Test
	public void testIs8Bit() {
		assertFalse(new ImmediateValue(Integer.MIN_VALUE).is8Bit());
		assertFalse(new ImmediateValue(-20000000).is8Bit());
		assertFalse(new ImmediateValue(-32768).is8Bit());
		assertFalse(new ImmediateValue(-129).is8Bit());
		assertTrue(new ImmediateValue(-128).is8Bit());
		assertTrue(new ImmediateValue(-48).is8Bit());
		assertTrue(new ImmediateValue(-1).is8Bit());
		assertTrue(new ImmediateValue(0).is8Bit());
		assertTrue(new ImmediateValue(1).is8Bit());
		assertTrue(new ImmediateValue(79).is8Bit());
		assertTrue(new ImmediateValue(255).is8Bit());
		assertFalse(new ImmediateValue(256).is8Bit());
		assertFalse(new ImmediateValue(65536).is8Bit());
		assertFalse(new ImmediateValue(300000000).is8Bit());
		assertFalse(new ImmediateValue(Integer.MAX_VALUE).is8Bit());
	}
	
	
	@Test
	public void testIs16Bit() {
		assertFalse(new ImmediateValue(Integer.MIN_VALUE).is16Bit());
		assertFalse(new ImmediateValue(-20000000).is16Bit());
		assertFalse(new ImmediateValue(-32769).is16Bit());
		assertTrue(new ImmediateValue(-32768).is16Bit());
		assertTrue(new ImmediateValue(-800).is16Bit());
		assertTrue(new ImmediateValue(0).is16Bit());
		assertTrue(new ImmediateValue(1).is16Bit());
		assertTrue(new ImmediateValue(300).is16Bit());
		assertTrue(new ImmediateValue(65535).is16Bit());
		assertFalse(new ImmediateValue(65536).is16Bit());
		assertFalse(new ImmediateValue(300000000).is16Bit());
		assertFalse(new ImmediateValue(Integer.MAX_VALUE).is16Bit());
	}
	
}
