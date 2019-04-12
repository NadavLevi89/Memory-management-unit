package com.hit.processes;

import java.util.List;

public class ProcessCycles extends java.lang.Object{
	protected List<ProcessCycle> processCycles;
	
	public ProcessCycles(List<ProcessCycle> processCycles) {
		setProcessCycles(processCycles);
	}
	
	public List<ProcessCycle> getProcessCycles(){
		return this.processCycles;
	}
	
	public void setProcessCycles(List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}
	
	public String toString(){
		return super.toString();
		
	}
}
