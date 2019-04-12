package com.hit.memoryunits;

import java.util.HashMap;
import java.util.Map;

public class RAM extends java.lang.Object {
	protected int intialCapacity;
	protected Map <java.lang.Long,Page<byte[]>> pageTable;
	
	RAM (int initialCapacity){
		this.setInitialCapacity(initialCapacity);
		pageTable = new  HashMap<java.lang.Long,Page<byte[]>>(initialCapacity);
		
	}
	public java.util.Map<Long,Page<byte[]>> getPages(){
		return pageTable;
	}
	
	public void setPages(java.util.Map<Long,Page<byte[]>> pages) {
		pageTable = pages;
	}
	
	public Page<byte[]> getPage(Long pageId){
		return pageTable.get(pageId);
		}
	@SuppressWarnings("null")
	public Page<byte[]>[] getPages(java.lang.Long[] pageIds){
		Page<byte[]>[] pages = null;
		for(int i=0;i<pageIds.length;i++) 
			pages[i]=getPage(pageIds[i]);
		return pages;
		}
	
	public void addPage(Page<byte[]> addPage) {
		pageTable.put(addPage.getPageId(),addPage);
	}
	
	public void addPages(Page<byte[]>[] addPages) {
		for(int i=0;i<addPages.length;i++) {
			addPage(addPages[i]);
		}
	}
	
	public void removePage(Page<byte[]> removePage) {
		pageTable.remove(removePage.getPageId());
	}
	
	public void removePages(Page<byte[]>[] removePages) {
		for(int i=0;i<removePages.length;i++) {
			removePage(removePages[i]);
		}
	}
	
	public int getInitialCapacity() {
		return this.intialCapacity;
	}
	
	public void setInitialCapacity(int initialCapacity) {
		this.intialCapacity = initialCapacity;
	}
}