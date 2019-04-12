package com.hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.util.MMULogger;

public class HardDisk extends java.lang.Object {
	private static HardDisk hD ;
	protected int _Size = 100;
	protected String DEFAUKT_FILE_NAME = "HardDisk.bin";
	protected Map <Long,Page<byte[]>> hardDiskMap;
	
	public HardDisk() {
		hardDiskMap = new HashMap<Long,Page<byte[]>>(_Size);
		for(Long i =  (long) 0 ; i< _Size;i++)
			hardDiskMap.put(i, new Page<byte[]>(i,new byte[] {0,0,0,0,0}));
		saveHD();
	}
	
	public static synchronized HardDisk getInstance(){	
		if(hD == null)
			hD = new HardDisk();
		return hD;
	}
	
	public Page<byte[]> pageFault(Long pageId){
		return hardDiskMap.get(pageId);
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveTohDPage, Long moveToRamId) throws com.hit.exception.HardDiskException{
		hD.hardDiskMap.put(moveTohDPage.getPageId(),moveTohDPage);
		return hardDiskMap.get(moveToRamId);
		}

	
	public void WritingToFile() throws IOException {
		try(Writer writer = new FileWriter(DEFAUKT_FILE_NAME)){	
		Gson gson = new GsonBuilder().create();
		gson.toJson(hardDiskMap);
		}
	}	
	public Map<Long,Page<byte[]>> getHDMap() {
		return hardDiskMap;
		
	}

	public void saveHD(){
		ObjectOutputStream outputStream = null;
		try {			
			outputStream = new ObjectOutputStream(new FileOutputStream("lib\\"+DEFAUKT_FILE_NAME));
			for (long i = (long) 0;i<=100;i++)
				outputStream.writeObject(this.getHDMap().get(i));
			outputStream.close();			
		} 
		catch (FileNotFoundException e) {
			MMULogger.getInstance().write( "File not found \r\n", Level.SEVERE);
		} 
		catch (IOException e) {
			MMULogger.getInstance().write("error\r\n", Level.SEVERE);
		}
	}
}
