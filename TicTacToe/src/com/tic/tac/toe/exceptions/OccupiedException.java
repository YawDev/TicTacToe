package com.tic.tac.toe.exceptions;

public class OccupiedException extends Exception{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OccupiedException(String errorMessage) {
	        super(errorMessage);
	    }

}
