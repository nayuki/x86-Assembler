package org.p79068.util;


/**
 * A mutable sequence of bytes. Bytes can be appended, and the whole sequence can be retrieved. The backing array automatically grows to accomodate the data. Unsynchronized.
 */
public final class ByteBuffer {
	
	/**
	 * The sequence of bytes. Only the substring [0, length) is used. This is not {@code null}.
	 */
	private byte[] data;
	
	/**
	 * The length of the sequence. This is non-negative.
	 */
	private int length;
	
	
	
	/**
	 * Constructs a byte buffer with a default initial capacity.
	 */
	public ByteBuffer() {
		this(64);
	}
	
	
	/**
	 * Constructs a byte buffer with the specified initial capacity. The initial capacity must be positive.
	 * @param initCapacity the initial capacity
	 * @throws IllegalArgumentException if the initial capacity is less than or equal to 0
	 */
	public ByteBuffer(int initCapacity) {
		if (initCapacity <= 0)
			throw new IllegalArgumentException("Non-positive capacity");
		data = new byte[initCapacity];
		length = 0;
	}
	
	
	
	/**
	 * Appends the specified byte to this sequence.
	 * @param b the byte to append
	 */
	public void append(byte b) {
		ensureCapacity((long)length + 1);
		data[length] = b;
		length++;
	}
	
	
	/**
	 * Appends the specified 16-bit integer to this sequence as 2 bytes in little endian.
	 * @param x the 16-bit integer to append
	 */
	public void appendLittleEndian(short x) {
		ensureCapacity((long)length + 2);
		data[length + 0] = (byte)(x >>> 0);
		data[length + 1] = (byte)(x >>> 8);
		length += 2;
	}
	
	
	/**
	 * Appends the specified 32-bit integer to this sequence as 4 bytes in little endian.
	 * @param x the 32-bit integer to append
	 */
	public void appendLittleEndian(int x) {
		ensureCapacity((long)length + 4);
		data[length + 0] = (byte)(x >>>  0);
		data[length + 1] = (byte)(x >>>  8);
		data[length + 2] = (byte)(x >>> 16);
		data[length + 3] = (byte)(x >>> 24);
		length += 4;
	}
	
	
	/**
	 * Appends the specified byte array to this sequence.
	 * @param x the byte array to append
	 * @throws NullPointerException if the byte array is {@code null}
	 */
	public void append(byte[] b) {
		if (b == null)
			throw new NullPointerException();
		ensureCapacity((long)length + b.length);
		System.arraycopy(b, 0, data, length, b.length);
		length += b.length;
	}
	
	
	/**
	 * Returns this sequence as a new byte array. The returned array is not the same as the backing array for this buffer.
	 * @return this sequence as a new byte array
	 */
	public byte[] toArray() {
		byte[] result = new byte[length];
		System.arraycopy(data, 0, result, 0, length);
		return result;
	}
	
	
	/**
	 * Ensures that the backing array is at least as large as the specified capacity. This method resizes the array if its length is less than the specified capacity, otherwise it does nothing.
	 * @param capacity
	 * @throws IllegalArgumentException if the capacity is negative
	 * @throws IllegalStateException if the capacity is greater than Integer.MAX_VALUE
	 */
	private void ensureCapacity(long capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Negative capacity");
		if (capacity > Integer.MAX_VALUE)
			throw new IllegalStateException("Maximum length exceeded");
		
		while (data.length < capacity)
			resize((int)Math.min((long)data.length * 2, Integer.MAX_VALUE));
	}
	
	
	/**
	 * Copies the backing array to a new array of the specified length and replaces the original backing array with the new one.
	 * @param newCapacity the length of the new backing array
	 * @throws IllegalArgumentException if the capacity is negative
	 * @throws IllegalArgumentException if the capacity is less than the current data length
	 */
	private void resize(int newCapacity) {
		if (newCapacity < 0)
			throw new IllegalArgumentException("Negative capacity");
		if (newCapacity < length)
			throw new IllegalArgumentException("Capacity less than length");
		byte[] newdata = new byte[newCapacity];
		System.arraycopy(data, 0, newdata, 0, length);
		data = newdata;
	}
	
}
