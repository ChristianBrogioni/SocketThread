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
	
public gestioneServer(int porta) { //costruttore della classe gestioneServer. Gli passo come parametro la porta in cui il server dovrà mettersi in ascolto
		
		try {
			serverSocket= new ServerSocket(porta); //creazione di un'istanza della classa java.net.ServerSocket, va specificato il numero di porta su cui rimanere in ascolto. Realizza il connection socket
			thread= new TimeOut(10);//istanza dell'oggetto di tipo TimeOut (è un thread) a cui passo il numero di secondi del countdown
			thread.start(); //avvio il thread
			serverSocket.setSoTimeout(10000); //metodo che serve ad impostare l'SO_TIMEOUT (timeout che se raggiunto causa una SocketTimeoutException che viene gestita)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	protected Socket accetta() { 
		try {
			socket=serverSocket.accept(); //ritorno del metodo accept, è un metodo bloccante (non succede nulla finchè non arriva la richiesta)
			thread.stopThread(); //una volta stabilita la connessione fermo il thread e di conseguenza il countdown
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return socket;
		
	}
	
	public void leggi() {
		
		try {
			inDalClient= new BufferedReader(new InputStreamReader(socket.getInputStream())); //il server può ricevere dati dal client leggendo dall'InputStream
			String messaggioDelClient= inDalClient.readLine(); //lettura dell'InputStream tramite metodo readLine
			System.out.println("Messaggio del client: "+ messaggioDelClient);
			//if(messaggioDelClient=="sinc") {
				String timeString = new Timestamp(System.currentTimeMillis()).toString(); //ottengo la data e l'ora corrente trasformandola in stringa tramite metodo toString. La trasformo in stringa in modo da poter utilizzare il metodo writeBytes
				outVersoClient.writeBytes(timeString+"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes. La concatenazione dei caratteri di controllo di fine riga "\r\n" è necessaria per l'utilizzo del metodo readLine()
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrivi() {
		try {
			outVersoClient = new DataOutputStream(socket.getOutputStream()); //il server può scrivere sull'OutputStream
			outVersoClient.writeBytes(tempoDiComunicazione +"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes. La concatenazione dei caratteri di controllo di fine riga "\r\n" è necessaria per l'utilizzo del metodo readLine()
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void chiudiClient() {
		try {
			System.out.println("Chiusura comunicazione con il client");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void chiudiServer() {
		try {
			System.out.println("Chiusura server");
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

