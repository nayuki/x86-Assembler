package org.p79068.libelf;


public enum SectionType {
	
	SHT_NULL(0), SHT_PROGBITS(1), SHT_SYMTAB(2), SHT_STRTAB(3), SHT_RELA(4), SHT_HASH(5), SHT_DYNAMIC(6), SHT_NOTE(7), SHT_NOBITS(8), SHT_REL(9), SHT_SHLIB(10), SHT_DYNSIM(11);
	
	
	public final int value;
	
	
	private SectionType(int value) {
		this.value = value;
	}
	
}
