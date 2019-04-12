package com.hit.processes;

import java.util.logging.Level;

import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.util.MMULogger;

public class Process extends java.lang.Object implements java.util.concurrent.Callable<java.lang.Boolean>
{
	protected int id;
	protected MemoryManagementUnit mmu;
	protected ProcessCycles processCycles;
	
	public Process(int id,MemoryManagementUnit mmu, ProcessCycles processCycles) {
		setId(id);
		this.mmu = mmu;
		this.processCycles = processCycles;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Override
	public Boolean call() throws Exception {

		try {
			for(ProcessCycle pC : processCycles.getProcessCycles()) {
				
				synchronized (mmu) {
					Page<byte[]>[] pages = mmu.getPages(pC.getPages().toArray(new Long[0]));
					for(int i = 0; i < pages.length;i++) {
						pages[i].setContent(pC.getData().get(i));
						MMULogger.getInstance().write("GP: P" + this.getId() + " " + pC.toString(i) + "\r\n" ,Level.INFO);
					}
				}
				Thread.sleep(pC.getSleepMs());
			}
		}catch(Exception e) {
			MMULogger.getInstance().write("fail to get pages\r\n", Level.SEVERE);
			return false;
		}
		return true;
	}
}
