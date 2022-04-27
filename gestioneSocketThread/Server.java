package gestioneSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		gestioneServer server;
		server= new gestioneServer(2000);
		
		//per permettere la connessione di più client in file ciclo
		if(server!=null) {
			server.accetta();
			server.scrivi();
			server.leggi();
			server.chiudiClient();
			server.chiudiServer();
		}
	}

}
