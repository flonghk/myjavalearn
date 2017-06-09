package com.hk.learn.utils;

//Template load and render execption wrapper to RuntimeException
public class DataPrepareException extends Exception  {
	public DataPrepareException(Throwable cause) {
		super(cause);
	}

	public DataPrepareException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8787229869074453324L;
}
