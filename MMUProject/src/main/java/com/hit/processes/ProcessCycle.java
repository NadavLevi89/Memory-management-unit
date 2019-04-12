package com.hit.processes;

import java.util.List;

public class ProcessCycle extends java.lang.Object{
	protected List<Long> pages;
	protected int sleepMs;
	protected List<byte[]> data;
	
	
	public ProcessCycle(List<Long> pages, int sleepMs, List<byte[]> data){
		setPages(pages);
		setSleepMs(sleepMs);
		setData(data);
	}
	
	public List<Long> getPages(){
		return pages;		
	}
	
	public void setPages(List<Long> pages) {
		this.pages = pages;
	}
	
	public int getSleepMs() {
		return sleepMs;
	}
	
	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}
	
	public List<byte[]> getData(){
		return this.data;	
	}
	
	public void setData(List<byte[]> data) {
		this.data = data;	
	}
	
	public String toString(){
		String text = null;
		for(int i = 0; i<this.getPages().size();i++) {
			text += toString(i);
			text +="\n";
		}
		return text;
	}
	
	public String toString(int i) {
		String returnToLog = this.pages.get(i) + "  [";
		for(int j = 0; j<4;j++)
			returnToLog += this.data.get(i)[j] + ", ";
		returnToLog += this.data.get(i)[4];
		returnToLog += "]";
		return returnToLog;
		
	}
	
}




















