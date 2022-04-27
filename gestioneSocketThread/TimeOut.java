package gestioneSocketThread;

public class TimeOut extends Thread{
    
	private int tempo; //tempo di durata del timer
	private boolean stop= false; //variabile booleana che uso per stoppare il thread
	
	
	public TimeOut(int t) { //costruttore classe TimeOut. Gli passo come parametro t (tempo) che vado a definire durante l'istanza dell'oggetto di tipo TimeOut
		this.tempo= t;
	}

	@Override
    public void run() //override del metodo run che deriva dalla classe Thread (nella classe Thread è vuoto e quindi faccio l'override) 
    {
				for(int i=tempo; i>0; i--) { //ciclo for per creare il countdown. Finchè i è maggiore di 0 esegue le istruzioni, ad ogni ciclo i diminuisce di 1
					if(!stop) { // se la variabile booleana stop è falsa eseguo le istruzioni. Se è vera vuol dire che il thread è stato stoppato e quindi le istruzioni non devono essere eseguite
					try {
						System.out.println(i);
						Thread.sleep(1000); //delay di 1 secondo
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				
				
						
		}
	
	public void stopThread() { //funzione che imposta la variabile booleana stop a true. Viene richiamata quando voglio stoppare il thread
		stop= true;
	}
	
    }
