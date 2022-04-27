package gestioneSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		gestioneServer server;
		server= new gestioneServer(2000); //istanza dell'oggetto di tipo gestioneServer a cui passo la porta in cui deve mettersi in ascolto il server
		
		if(server!=null) { //se l'oggetto server non è null richiamo i seguenti metodi
			server.accetta();
			server.scrivi();
			server.leggi();
			server.chiudiClient();
			server.chiudiServer();
		}
	}

}
