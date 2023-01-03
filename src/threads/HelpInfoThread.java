package threads;

import javafx.scene.layout.Pane;
/**
 * Класс HelpInfoThread
 * Используется для анимирования панели
 * помощи в окне "About Program"
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class HelpInfoThread extends Thread{
	private Pane helpInfoPane;
	@Override
	public void run() {
		try {
			sleep(3000);
			double opacity = helpInfoPane.getOpacity();
			for(int i=0;i<100;i++) {
				sleep(50);
				helpInfoPane.setOpacity(opacity -=0.01);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Метод,который изменяет helpInfoPane
     * @param helpInfoPane - панель пояснения
     */
	public void setHelpInfoPane(Pane helpInfoPane){
		this.helpInfoPane = helpInfoPane;
	}
}
