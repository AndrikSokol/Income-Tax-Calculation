package threads;
/**
 * Класс ExitThread
 * Используется для выхода из программы,
 * если в течение 60 секунд оставаться
 * в окне Title
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class ExitThread extends Thread{
	@Override
    public void run() {
    	for(int i=0;i<60;i++) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {					
						e.printStackTrace();
					}
    	}
    	if(!(isInterrupted()))
    			System.exit(0);
    }
}
