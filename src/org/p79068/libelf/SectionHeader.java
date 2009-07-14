package org.p79068.libelf;

import org.p79068.util.ByteBuffer;


public class SectionHeader {
	
	static int SECTION_HEADER_ENTRY_SIZE = 40;
	
	
	public int name;
	
	public SectionType type;
	
	public int flags;
	
	public int addr;
	
	public int offset;
	
	public int size;
	
	public int link;
	
	public int info;
	
	public int addralign;
	
	public int entsize;
	
	
	
	byte[] toBytes() {
		ByteBuffer b = new ByteBuffer(SECTION_HEADER_ENTRY_SIZE);
		b.appendLittleEndian(name);
		b.appendLittleEndian(type.value);
		b.appendLittleEndian(flags);
		b.appendLittleEndian(addr);
		b.appendLittleEndian(offset);
		b.appendLittleEndian(size);
		b.appendLittleEndian(link);
		b.appendLittleEndian(info);
		b.appendLittleEndian(addralign);
		b.appendLittleEndian(entsize);
		return b.toArray();
	}
	
}
