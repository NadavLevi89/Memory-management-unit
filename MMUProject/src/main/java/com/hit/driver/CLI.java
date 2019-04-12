package com.hit.driver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.hit.view.View;

public class CLI extends java.util.Observable implements java.lang.Runnable,View{
	
	public static final java.lang.String MRU = "MRU";
	public static final java.lang.String LRU = "LRU";
	public static final java.lang.String RANDOM = "RANDOM";
	public static final java.lang.String START = "start";
	public static final java.lang.String STOP = "stop";
	
	protected Scanner input;
	protected PrintWriter output;
	protected String line;
	
	public CLI(InputStream in,OutputStream out) {
		this.input = new Scanner(in);
		this.output = new PrintWriter(out);
		this.line = null;
	}
	
	public void Write(String string) {
		output.write(string);
		output.flush();
	}

	boolean ValidCommend(String line, int stage) { // if return true the line is invalid
		String[] splitLine;
		int ramSize;
		switch(stage)
		{
		case 0:								// check first part
			if(!line.equals(START) && !line.equals(STOP))
				return false;
			return true;
		case 1:								// check second part
			splitLine = line.split(" ");
			if(splitLine.length != 2)
				return false;
			
			try {							// check if number
				ramSize = Integer.parseInt(splitLine[1]);
			} catch (NumberFormatException e) {return false;}
			
			if(ramSize < 1 || ramSize > 100) // ram is positive and lower than HD size
				return false;
			
			switch(splitLine[0].toUpperCase()) 
			{								// check if LRU MRU or Random
			case LRU:
				return true;
			case MRU:
				return true;
			case RANDOM:
				return true;	
			default:
				return false;	
			}
		default:
			return false;
		}
	}
	
	
	@Override
	public void run() {
		String[] splitLine;
		Write("please chose stat 'start' or 'stop'\r\n");
		line = input.nextLine();
		while(!ValidCommend(line,0)){
			Write("InvalidCommend\r\n");
			line = input.nextLine();
		}
		if(line.equals(START)) {
			Write("Please enter required algorithm and RAM capacity\r\n");
			line = input.nextLine();	
			while(!ValidCommend(line,1)) {
				Write("InvalidCommend\r\n");
				Write("Please enter required algorithm and RAM capacity\r\n");
				line = input.nextLine();	
			}
			splitLine = line.split(" ");
			setChanged();
			notifyObservers(splitLine);
			
		}
		else
			Write("Thank you");
	}		
	
	public void start() {
		this.run();
		
	}
}



















