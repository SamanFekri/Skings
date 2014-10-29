package ir.skings.server.net.handler;

import java.net.Socket;

public interface Handler extends Runnable {
	void setSocket(Socket socket);
}
