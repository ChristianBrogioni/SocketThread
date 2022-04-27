package gestioneSocketThread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

public class gestioneServer {
	
	ServerSocket serverSocket;
	Socket socket;
	BufferedReader inDalClient;
	DataOutputStream outVersoClient;
	TimeOut thread;
	String tempoDiComunicazione= "30";
	
public gestioneServer(int porta) {
		
		try {
			serverSocket= new ServerSocket(porta);
			thread= new TimeOut(10);
			thread.start();
			serverSocket.setSoTimeout(10000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	protected Socket accetta() {
		try {
			socket=serverSocket.accept();
			thread.stopThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return socket;
		
	}
	
	public void leggi() {
		
		try {
			inDalClient= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String messaggioDelClient= inDalClient.readLine(); //lettura dell'InputStream tramite metodo readLine
			System.out.println("Messaggio del client: "+ messaggioDelClient);
				String timeString = new Timestamp(System.currentTimeMillis()).toString();
				outVersoClient.writeBytes(timeString+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrivi() {
		try {
			outVersoClient = new DataOutputStream(socket.getOutputStream());
			outVersoClient.writeBytes(tempoDiComunicazione +"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void chiudiClient() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chiudiServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

