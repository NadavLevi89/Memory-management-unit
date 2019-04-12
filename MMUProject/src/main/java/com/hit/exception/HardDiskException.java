package com.hit.exception;

public class HardDiskException extends java.io.IOException {

	private static final long serialVersionUID = 1L;
	
	public enum ActionError {
		PAGE_FAULT,PAGE_REPLACEMENT;
	}
	
	public HardDiskException(){}
	
	public HardDiskException(java.lang.String msg){
		super(msg);
	}
	public HardDiskException(java.lang.String msg, HardDiskException.ActionError status){
		super(msg + " " + status.toString());
	}
	
	public static HardDiskException.ActionError[] values(){
		return new ActionError[] {ActionError.PAGE_FAULT, ActionError.PAGE_REPLACEMENT};
	}
	
	public static HardDiskException.ActionError valueOf(java.lang.String name){
		ActionError errorName;
		switch(name)
		{
		case ("PAGE_FAULT"):
			errorName = ActionError.PAGE_FAULT;
		default:
			errorName = ActionError.PAGE_REPLACEMENT;
		return errorName;
		}
	}
	
}
