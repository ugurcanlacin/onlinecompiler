package com.onlinecompiler.service;

public class ExecutedCodeResult {
	private String output;
	private String error;
	
	public ExecutedCodeResult(String output, String error) {
		super();
		this.output = output;
		this.error = error;
	}

	public String getOutput() {
		return output;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
}
