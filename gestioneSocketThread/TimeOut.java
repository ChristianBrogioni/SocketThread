package gestioneSocketThread;

public class TimeOut extends Thread{
    
	private int tempo;
	private boolean stop= false;
	
	
	public TimeOut(int t) {
		this.tempo= t;
	}

	@Override
    public void run()
    {
				for(int i=tempo; i>0; i--) {
					if(!stop) {
					try {
						System.out.println(i);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				
				
						
		}
	
	public void stopThread() {
		stop= true;
	}
	
    }
