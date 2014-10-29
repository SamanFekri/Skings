package ir.skings.server.net;

import ir.skings.server.net.handler.Handler;
import ir.skings.server.net.handler.StringTransfer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher extends Thread {
	private volatile boolean run;
	private ServerSocket server;
	private InetAddress addr;

	public Dispatcher() throws IOException {
		super("Dispatcher Thread");
		addr = InetAddress.getByName("localhost");
		server = new ServerSocket(1374,100,addr);
		System.out.println("server - " + "you will listen to this host : " + server.toString() +" .");
	}

	@Override
	public void run() {
		while (true) {
			try {
				
				System.out.println("server - "+"waiting for connection ....");
				Socket socket = server.accept();
				System.out.println("server - "+"a connection arrive ....");
				Handler handler = new StringTransfer();
				handler.setSocket(socket);
				new Thread(handler).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
