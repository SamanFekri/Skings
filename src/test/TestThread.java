package test;
import ir.skings.client.net.Client;

import java.util.Scanner;

public class TestThread extends Thread {
	
	private String request = "";
	private Client client;
	
	public TestThread() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		request = sc.nextLine();
		while(!request.equalsIgnoreCase("end")){
			client = new Client(request, "localhost", 1374);
			System.out.println(client.getAnswer());
			request = sc.nextLine();
		}
		sc.close();
		System.exit(555);
	}
}
