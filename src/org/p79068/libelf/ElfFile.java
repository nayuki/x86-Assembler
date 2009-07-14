package org.p79068.libelf;

import java.util.ArrayList;
import java.util.List;

import org.p79068.util.ByteBuffer;


public class ElfFile {
	
	public ElfHeader elfHeader;
	
	public List<ProgramHeader> programHeaders;
	
	public List<SectionHeader> sectionHeaders;
	
	public byte[] data;
	
	
	
	public ElfFile() {
		programHeaders = new ArrayList<ProgramHeader>();
		sectionHeaders = new ArrayList<SectionHeader>();
	}

	
	
	public int getDataOffset() {
		int result = 0;
		result += ElfHeader.ELF_HEADER_SIZE;
		result += programHeaders.size() * ProgramHeader.PROGRAM_HEADER_ENTRY_SIZE;
		result += sectionHeaders.size() * SectionHeader.SECTION_HEADER_ENTRY_SIZE;
		return result;
	}
	
	
	public byte[] toBytes() {
		ByteBuffer b = new ByteBuffer();
		b.append(elfHeader.getBytes((short)programHeaders.size(), (short)sectionHeaders.size()));
		for (ProgramHeader ph : programHeaders)
			b.append(ph.toBytes());
		for (SectionHeader sh : sectionHeaders)
			b.append(sh.toBytes());
		b.append(data);
		return b.toArray();
	}
	
}
