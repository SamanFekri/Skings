package ir.skings.server.net.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.plaf.SliderUI;

public class StringTransfer implements Handler {

	private Socket connection;
	private String request, answer;
	private StringAnalyzer StringAnalyzer;

	public StringTransfer() {
		request = "";
		answer = "*$*";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		BufferedReader br;
		try {
			int l = 0;
			byte[] bytes = new byte[1024];

			InputStream is = connection.getInputStream();
			l = is.available();
			is.read(bytes);
			
			request = new String(bytes);
			request = request.substring(0, l);
			System.out.println("stringTransfer - "+ "get the request : " + request);
			
			StringAnalyzer = new StringAnalyzer(request);
			StringAnalyzer.start();

			answer = StringAnalyzer.getOutputString();
			int timeout = 0;
			while (answer.equals("*$*") && timeout <= 6000) {
				System.out.println("stringTransfer - "+"nothing analyz in " +  timeout + " miliseconds ...");
				Thread.sleep(300);
				answer = StringAnalyzer.getOutputString();
				timeout+=300;
			}
			OutputStream os = connection.getOutputStream();
			os.write(answer.getBytes());
			os.flush();
			System.out.println("stringTransfer - "+"send the answer : " + answer);
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				connection.close();
				System.out.println("stringTransfer - "+"connection closed.♥♥♥ Bye Bye ♥♥♥");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					connection.setKeepAlive(false);
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void setSocket(Socket socket) {
		// TODO Auto-generated method stub
		this.connection = socket;
	}

}
