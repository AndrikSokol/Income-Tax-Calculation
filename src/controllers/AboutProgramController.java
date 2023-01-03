package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import threads.HelpInfoThread;
/**
 * Класс-контроллер окна "About Program"
 * Содержит информацию о программе
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class AboutProgramController {

    @FXML
    private Button backBtn;

    @FXML
    private Pane helpInfoPane;

    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки backBtn
     * @param event - событие
     */
    @FXML
    void goToBack(ActionEvent event) {
    	backBtn.getScene().getWindow().hide();
        }

    @FXML
    void initialize() {
    	HelpInfoThread helpInfoThread = new HelpInfoThread();
    	helpInfoThread.setHelpInfoPane(helpInfoPane);
    	helpInfoThread.start();
    }
}
