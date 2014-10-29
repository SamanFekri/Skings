package ir.skings.server.net;

import java.io.IOException;

import test.TestThread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dispatcher dispatcher = null;
		try {
			dispatcher = new Dispatcher();
			TestThread testThread = new TestThread();
			testThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.start();

	}

}
