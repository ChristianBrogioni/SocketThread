package gestioneSocketThread;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
		
		public static void main(String[] args) {
			gestioneClient client;
			try {
				client = new gestioneClient(InetAddress.getLocalHost(), 2000);
				String str= client.leggi();
				TimeOut threadCli= new TimeOut(Integer.parseInt(str));
				threadCli.start();
				client.scrivi();
				String strTime= client.leggi();
				System.out.println(strTime);
				client.chiudiServer();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
