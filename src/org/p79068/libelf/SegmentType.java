package org.p79068.libelf;


public enum SegmentType {
	
	PT_NULL(0), PT_LOAD(1), PT_DYNAMIC(2), PT_INTERP(3), PT_NOTE(4), PT_SHLIB(5), PT_PHDR(6);
	
	
	public final int value;
	
	
	private SegmentType(int value) {
		this.value = value;
	}
	
}
