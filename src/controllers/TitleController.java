package controllers;

import javafx.event.ActionEvent;
import threads.ExitThread;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Класс-контроллер окна "Title"
 * Содержит информацию об авторе
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class TitleController {

    @FXML
    private Button transitionBtn;

    @FXML
    private Button exitBtn;
    
	ExitThread exitThread = new ExitThread();	
    
    @FXML
    void initialize() {
    	ButtonListenerClass buttonListener = new ButtonListenerClass();
    	exitBtn.setOnAction(buttonListener);
    	transitionBtn.setOnAction(buttonListener);
    	exitThread.start();
    }
    
    /** Внутренний ButtonListenerClass для кнопок transitionBtn и exitBtn*/
    class ButtonListenerClass implements EventHandler<ActionEvent>{
    	/**
	     * Обработка события,которое происходит 
	     * при нажатии кнопкок transitionBtn или exitBtn
	     * @param event - событие
	     */
        @SuppressWarnings("deprecation")
    	@Override
    	public void handle(ActionEvent event) {
        	exitThread.stop();
        	exitThread.interrupt();
        	if(event.getSource() == transitionBtn) {
            	transitionBtn.getScene().getWindow().hide();
            	FXMLLoader loader = new FXMLLoader();
            	loader.setLocation(getClass().getResource("/view/Main.fxml"));
            	
            	try {
            		loader.load();
            	}catch (IOException e) {
            		e.printStackTrace();
            	}
            	Parent root = loader.getRoot();
            	Stage stage = new Stage();
            	stage.setScene(new Scene(root));
            	stage.setResizable(false);
            	stage.showAndWait();
            }
        	if(event.getSource() == exitBtn) {
        		System.exit(0);
        	}
        }
    }
    
}
