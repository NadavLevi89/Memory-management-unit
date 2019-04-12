package com.hit.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.MRUAlgoCacheImpl;
import com.hit.algorithm.Random;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.util.MMULogger;

public class MMUModel extends java.util.Observable implements Model {

	public int numProcesses;
	public int ramCapacity;
	public long[] process;
	private String[] confi;
	private List<com.hit.processes.Process> allProcesses;
	private List<com.hit.processes.Process> pRC = null;
	private Page<byte[]>[] pages;
	private  MemoryManagementUnit mmu;
	private int[] counter = {0,0} ;
	IAlgoCache <Long, Long> algo = null;
	
	
	
	public MMUModel() {
		
	}
	
	public List<com.hit.processes.Process> getProcesses(){
		return allProcesses;
	}
	public MemoryManagementUnit getMMU() {
		return mmu;
	}
	
	
	public int getNumOfProcesses() {
		return this.numProcesses;
	}
	

	
	public void setConfiguration(String[] configuration) {
		this.confi =  configuration;
	}
	@Override
	public void start() {
		ramCapacity = Integer.parseInt(confi[1]);
		MMULogger.getInstance().write("RC: " + ramCapacity + "\r\n", Level.INFO);
		switch(confi[0].toUpperCase()) {
		case "LRU":
			algo = new LRUAlgoCacheImpl<Long,Long>(ramCapacity);
			break;
		case "MRU":
			algo = new MRUAlgoCacheImpl<Long,Long>(ramCapacity);
			break;
		case "RANDOM":
			algo = new Random<Long,Long>(ramCapacity);
			break;
		}
		mmu = new MemoryManagementUnit(ramCapacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		allProcesses = createProcesses(processCycles,mmu);
		numProcesses = allProcesses.size();
		MMULogger.getInstance().write("PN:" + numProcesses + "\r\n\r\n",Level.INFO);
	}
	
	
	
	public List<com.hit.processes.Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu){
		List<com.hit.processes.Process> allProcesses = new LinkedList<>();
		List<ProcessCycles> pCS = processCycles;
		com.hit.processes.Process p = null;
		for(int i = 0;i< pCS.size();i++) {
			p = new com.hit.processes.Process(i,mmu,processCycles.get(i));
			allProcesses.add(p);
		}
		return allProcesses;
	}
	
	@SuppressWarnings("unchecked")
	public void runProcesses(List<com.hit.processes.Process> applications) {
		Thread[] currentThread = new Thread[applications.size()];
		for(int i = 0 ;i<(applications.size()); i++) {
			
			try {
				@SuppressWarnings("rawtypes")
				FutureTask task = new FutureTask(applications.get(i));
				currentThread[i] = new Thread(task);
				currentThread[i].start();
				
			} catch (Exception e) {
				MMULogger.getInstance().write("Threading fail",Level.SEVERE);
			}
		}
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			MMULogger.getInstance().write("fail to lseep", Level.SEVERE);
		}
		
		try {
			pages = this.getMMU().getPages(this.getMMU().getRamIds());
		} catch (IOException e) {
			MMULogger.getInstance().write("fail to lseep", Level.SEVERE);
		}
        finingPFandPR();
		setChanged();
        notifyObservers(pages);
	}
	
	public void updateProcesses(int[] selectedProcesses) {
		if(pRC == null)
			pRC = new LinkedList<>();
		else
			pRC.clear();
		
		for(int i = 0;i<selectedProcesses.length;i++)
			pRC.add(this.allProcesses.get(selectedProcesses[i]));
		runProcesses(pRC);
	}
	
	public RunConfiguration readConfigurationFile() {
		String path = "src\\main\\resources\\com\\hit\\config\\Configuration.json";
		RunConfiguration rC = null;
		Gson processReader = new Gson();
		try{
			rC = processReader.fromJson(new JsonReader(new FileReader(path)), RunConfiguration.class);
		}catch(JsonParseException e) {
			MMULogger.getInstance().write("file not found", Level.SEVERE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rC;
	}
	

	public void finingPFandPR() {
		this.counter[0] = 0;
		this.counter[1] = 0;
		BufferedReader br = null;
		try {
			MMULogger.getInstance();				
			br = new BufferedReader(new FileReader(MMULogger.DEFAULT_FILE_NAME));
		} catch (FileNotFoundException e) {
			MMULogger.getInstance().write("failed to load log file", Level.SEVERE);
		}
		String line;
		try {
			while((line = br.readLine()) != null) {
				if(line.contains("PF"))
					this.counter[0]++;
				else if(line.contains("PR"))
					this.counter[1]++;
			}
		} catch (IOException e) {
			MMULogger.getInstance().write("failed to read line", Level.SEVERE);
		}
	}

	public String[] convortCounter() {
		String[] convorted = new String[2];
		convorted[0] = String.valueOf(counter[0]);
		convorted[1] = String.valueOf(counter[1]);
		return  convorted;
	}

	


}
