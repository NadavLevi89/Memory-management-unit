package com.hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger extends java.lang.Object {
	private static MMULogger logger = null;
	public static final String DEFAULT_FILE_NAME = "logs\\log.txt";
	private static FileHandler handler;
	
	private MMULogger() {
		
			try {
				MMULogger.handler = new FileHandler(DEFAULT_FILE_NAME);
				handler.setFormatter(new OnlyMessageFormatter());
			} catch (SecurityException e) {
				System.out.println("couldn't create a handler "+ e.getMessage());
			} catch (IOException e) {
				System.out.println("couldn't create a handler "+ e.getMessage());
			}
			
	}
	
	public static synchronized MMULogger getInstance() {
		if(logger == null)
			return logger = new MMULogger();
		return logger;
		
	}
	
	public synchronized void write(String command , Level level) {
		LogRecord lR = new LogRecord(level, command);
		handler.publish(lR);
	}
	
	public class OnlyMessageFormatter extends Formatter{

		public OnlyMessageFormatter() {super();}
		
		@Override
		public String format(final LogRecord record) {
			return record.getMessage();
		}
		
	}
	public void closeLogFile() {
		handler.close();
	}
	
}
