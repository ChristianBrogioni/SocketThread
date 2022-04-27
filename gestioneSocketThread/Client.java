package gestioneSocketThread;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
		
		public static void main(String[] args) {
			gestioneClient client;
			try {
				client = new gestioneClient(InetAddress.getLocalHost(), 2000); //istanza dell'oggetto di tipo gestioneClient a cui passo il local host e la porta in cui il server è in ascolto
				String str= client.leggi(); //variabile di tipo string ottenuta dal metodo leggi. Contiene il numero di secondi del countdown della comunicazione
				TimeOut threadCli= new TimeOut(Integer.parseInt(str)); //istanza dell'oggetto di tipo TimeOut (è un thread) a cui passo il numero di secondi del countdown. Faccio il casting in quanto str è una stringa e va passato un int
				threadCli.start(); //avvio il thread
				client.scrivi();
				String strTime= client.leggi(); //variabile di tipo string che contiene la data e l'ora attuale
				System.out.println(strTime); 
				client.chiudiServer();
			} catch (UnknownHostException e) { //gestione eccezzione host sconosciuto
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
