package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Класс-контроллер окна "About Author"
 * Содержит информацию об авторе
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class AboutAuthorController {

    @FXML
    private Button backBtn;

    @FXML
    private void initialize() {
    	
    	/** Анонимный внутренний класс слушатель для кнопки backBtn
    	 */
    	backBtn.setOnAction(new EventHandler<ActionEvent>() {
    		
		   /**
    	     * Обработка события,которое происходит 
    	     * при нажатии кнопки backBtn
    	     * @param event - событие
    	     */
    	    @Override
    	    public void handle(ActionEvent event) {
    	    	backBtn.getScene().getWindow().hide();
    	    }
    	});
    }   		
   
}

