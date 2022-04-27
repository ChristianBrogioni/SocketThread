package gestioneSocketThread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class gestioneClient {
	
Socket socket;
BufferedReader inDalServer;
BufferedReader tastiera;
DataOutputStream outVersoServer;
String messaggioDelServer;


	
	public gestioneClient(InetAddress ip, int porta) {
		
		try {
			socket= new Socket(ip, porta);
			inDalServer= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			tastiera= new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	
	public String leggi() {
		messaggioDelServer="";
		try {
			messaggioDelServer = inDalServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messaggioDelServer;
	}
	
	public void scrivi() {
		try {
			DataOutputStream outVersoServer= new DataOutputStream(socket.getOutputStream());
			System.out.println("Messaggio per il server: ");
			String messaggioPerServer = tastiera.readLine();
			outVersoServer.writeBytes(messaggioPerServer+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chiudiServer() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
