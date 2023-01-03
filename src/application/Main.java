package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
/**
 * Класс Main 
 * создание графического окна "Title" 
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class Main extends Application {
    
	/**
     * Метод создания окна 
     * @param primaryStage  - Stage
     */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/view/Title.fxml"));		
			Scene scene = new Scene(root,600,400);
			scene.setRoot(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setTitle("Расчёт подоходного налога");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
