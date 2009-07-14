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
		if (length == Integer.MAX_VALUE)
			throw new IllegalStateException();
		if (length == data.length)
			resize(length * 2);
		data[length] = b;
		length++;
	}
	
	
	public void appendLittleEndian(short x) {
		if ((long)length + 2 > Integer.MAX_VALUE)
			throw new IllegalStateException();
		while (length + 2 > data.length)
			resize(data.length * 2);
		data[length + 0] = (byte)(x >>> 0);
		data[length + 1] = (byte)(x >>> 8);
		length += 2;
	}
	
	
	public void appendLittleEndian(int x) {
		if ((long)length + 4 > Integer.MAX_VALUE)
			throw new IllegalStateException();
		while (length + 4 > data.length)
			resize(data.length * 2);
		data[length + 0] = (byte)(x >>>  0);
		data[length + 1] = (byte)(x >>>  8);
		data[length + 2] = (byte)(x >>> 16);
		data[length + 3] = (byte)(x >>> 24);
		length += 4;
	}
	
	
	public void append(byte[] b) {
		if ((long)length + b.length > Integer.MAX_VALUE)
			throw new IllegalStateException();
		while (length + b.length > data.length)
			resize(data.length * 2);
		System.arraycopy(b, 0, data, length, b.length);
		length += b.length;
	}
	
	
	public byte[] toArray() {
		byte[] result = new byte[length];
		System.arraycopy(data, 0, result, 0, length);
		return result;
	}
	
	
	private void resize(int newCapacity) {
		if (newCapacity < length)
			throw new AssertionError();
		byte[] newdata = new byte[newCapacity];
		System.arraycopy(data, 0, newdata, 0, length);
		data = newdata;
	}
	
}
