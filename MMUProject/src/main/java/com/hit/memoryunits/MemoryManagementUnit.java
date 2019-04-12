package com.hit.memoryunits;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import com.hit.algorithm.IAlgoCache;
import com.hit.util.MMULogger;

public class MemoryManagementUnit extends java.lang.Object{
	protected RAM ram = null;
	protected IAlgoCache<Long, Long> algo;

	
	public MemoryManagementUnit(int ramCapacity,IAlgoCache<Long, Long> algo) {
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
		
	}
	
	@SuppressWarnings("unchecked") // its checked 
	public Page<byte[]>[] getPages(java.lang.Long[] PageIds) throws java.io.IOException {
		List<Page<byte[]>> pagesList = new LinkedList<Page<byte[]>>();
		Page<byte[]>[] pages;
		for(int i=0 ;i<PageIds.length;i++ ) {
			if(algo.getElement(PageIds[i]) == null) {
				if(ram.pageTable.size()< ram.getInitialCapacity()) {
					algo.putElement(PageIds[i], PageIds[i]);  
					ram.addPage(HardDisk.getInstance().pageFault(PageIds[i]));
					pagesList.add(HardDisk.getInstance().pageFault(PageIds[i]));
					MMULogger.getInstance().write("PF:"+PageIds[i] + "\r\n", Level.INFO);
				}	
				else {
					Long pageToHD = algo.putElement(PageIds[i],PageIds[i]);
					Page<byte[]> pageToRam = HardDisk.getInstance().pageReplacement(ram.getPage(pageToHD), PageIds[i]);
					ram.removePage(ram.getPage(pageToHD));
					ram.addPage(pageToRam);
					pagesList.add(pageToRam);
					MMULogger.getInstance().write("PR:MTH "+ pageToHD + " MTR " + pageToRam.getPageId() + "\r\n" , Level.INFO);
				}
			}
			else
				pagesList.add(ram.getPage(PageIds[i]));
		}
		pages = new Page[pagesList.size()];
		for(int i = 0 ; i < pagesList.size() ; ++i) {
			pages[i] = pagesList.get(i);
		}
		return pages;
	}
	
	public void shutDown() {
		ram.pageTable.clear();
		algo = null;
	}
	
	public IAlgoCache<Long,Long> getAlgo(){
		return this.algo;
		}
	
	public void setAlgo(IAlgoCache<Long,Long> algo) {
		this.algo = algo;
	}
	
	public RAM getRam() {
		return this.ram;
	}
	
	public void setRam(RAM ram) {
		this.ram = ram;
	}
	
	public Long[] getRamIds() {
		Set<Long> keys = this.getRam().pageTable.keySet();
		Long[] ramKeys = keys.toArray(new Long[keys.size()]);
		return ramKeys;
	}
	
	public void update() {}
}
