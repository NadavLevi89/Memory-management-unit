package com.hit.processes;

import java.util.List;

public class RunConfiguration extends java.lang.Object{
	protected List<ProcessCycles> processesCycles;
	
	public RunConfiguration(List<ProcessCycles> processCycles) {
		setProcessesCycles(processCycles);
	}

	public List<ProcessCycles> getProcessesCycles(){
		return this.processesCycles;
	}
	
	public void setProcessesCycles(List<ProcessCycles> processesCycles) {
		this.processesCycles = processesCycles;
	}
	
	public  java.lang.String toString(){
		return super.toString();
	}
	
}
