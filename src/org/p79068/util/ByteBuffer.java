package org.p79068.util;


public final class ByteBuffer {
	
	private byte[] data;
	
	private int length;
	
	
	
	public ByteBuffer() {
		this(64);
	}
	
	
	public ByteBuffer(int newCapacity) {
		if (newCapacity <= 0)
			throw new IllegalArgumentException("Non-positive capacity");
		data = new byte[newCapacity];
		length = 0;
	}
	
	
	
	public void append(byte b) {
		ensureCapacity((long)length + 1);
		data[length] = b;
		length++;
	}
	
	
	public void appendLittleEndian(short x) {
		ensureCapacity((long)length + 2);
		data[length + 0] = (byte)(x >>> 0);
		data[length + 1] = (byte)(x >>> 8);
		length += 2;
	}
	
	
	public void appendLittleEndian(int x) {
		ensureCapacity((long)length + 4);
		data[length + 0] = (byte)(x >>>  0);
		data[length + 1] = (byte)(x >>>  8);
		data[length + 2] = (byte)(x >>> 16);
		data[length + 3] = (byte)(x >>> 24);
		length += 4;
	}
	
	
	public void append(byte[] b) {
		ensureCapacity((long)length + b.length);
		System.arraycopy(b, 0, data, length, b.length);
		length += b.length;
	}
	
	
	public byte[] toArray() {
		byte[] result = new byte[length];
		System.arraycopy(data, 0, result, 0, length);
		return result;
	}
	
	
	private void ensureCapacity(long capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Negative capacity");
		if (capacity > Integer.MAX_VALUE)
			throw new IllegalStateException("Maximum length exceeded");
		
		while (data.length < capacity)
			resize((int)Math.min((long)data.length * 2, Integer.MAX_VALUE));
	}
	
	
	private void resize(int newCapacity) {
		if (newCapacity < length)
			throw new IllegalArgumentException("Capacity less than length");
		byte[] newdata = new byte[newCapacity];
		System.arraycopy(data, 0, newdata, 0, length);
		data = newdata;
	}
	
}
