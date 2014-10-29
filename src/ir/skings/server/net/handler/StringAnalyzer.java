package ir.skings.server.net.handler;

import java.io.SyncFailedException;

public class StringAnalyzer extends Thread{
	
	private String inputString , outputString;
	
	public StringAnalyzer(String request){
		inputString = request;
		outputString = "*$*";
	}

	public String getInputString() {
		return inputString;
	}

	public synchronized String getOutputString() {
		System.out.println("now--");
		return outputString;
	}

	public void setOutputString(String outputString) {
		this.outputString = outputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	
	public void analyze() {
		// analyze the string and put the answer on outputString
		if(inputString.equalsIgnoreCase("salam")){
			outputString = "salam";
		}else{
			outputString = "akbar";
		}
	}
	
	@Override
	public void run() {
		System.out.println("stringAnalyzer - "+"starting analyz .....");
		analyze();
		System.out.println("stringAnalyzer - "+"analyz finished .....");
	}
}
