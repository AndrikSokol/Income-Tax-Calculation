package threads;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
/**
 * Класс HighlightForInvalidInputThread
 * Используется подсвечивания некоторых
 * компонентов в окне
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class HighlightForInvalidInputThread extends Thread{
    private String oldStyle;
    private TextField errorTField = null;
    private ChoiceBox<String> timeCBox = null;
    
	@Override
	public void run() {
		try {
			if(errorTField !=null) {
				errorTField.setStyle("-fx-border-color: red;");
				sleep(1000);
				errorTField.setStyle(oldStyle);
			}
			else if(timeCBox != null) {
				timeCBox.setStyle("-fx-border-color: red;");
				sleep(1000);
				timeCBox.setStyle(oldStyle);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	/**
     * Метод,который изменяет errorTField
     * @param TextField - элемент errorTField
     */
	public void setErrorTField(TextField errorTField) {
		this.errorTField = errorTField;
		oldStyle = errorTField.getStyle();
	}
	
	/**
     * Метод,который изменяет timeCBox
     * @param timeCBox - элемент для выбора периода
     */
	public void setTimeCBox(ChoiceBox<String> timeCBox) {
		this.timeCBox = timeCBox;
		oldStyle = timeCBox.getStyle();
	}
}
