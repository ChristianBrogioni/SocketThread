package gestioneSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		gestioneServer server;
		server= new gestioneServer(2000);
		
		if(server!=null) {
			server.accetta();
			server.scrivi();
			server.leggi();
			server.chiudiClient();
			server.chiudiServer();
		}
	}

}
