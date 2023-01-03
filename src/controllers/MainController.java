package controllers;
import model.IncomeTax;
import model.User;
import threads.HighlightForInvalidInputThread;

import java.text.DecimalFormat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import database.DatabaseHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * Класс-контроллер окна "Main"
 * Реализует все взаимодействия пользователя,
 * также проверяет поля на валидность
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class MainController {

    @FXML
    private Button exitBtn;

    @FXML
    private ChoiceBox<String> timeCBox;

    @FXML
    private RadioButton yesWorkRBtn;

    @FXML
    private RadioButton noWorkRBtn;

    @FXML
    private TextField taxTField;

    @FXML
    private Button calculateTaxBtn;

    @FXML
    private Tab extraTab;
    
    @FXML
    private Tab dbTab;
    
    @FXML
    private Tab taxTab;
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private List<TextField> arrayTField;
    
    @FXML 
    private List<TextField> array2TField;
    
    @FXML
    private TextField nameOnDbTabTField;
    
    @FXML
    private TextField surnameOnDbTabTField;
    
    @FXML 
    private RadioButton yesPriviligesRBtn;
    
    @FXML
    private RadioButton yesFaceRBtn;
    
    @FXML
    private TextField numberOfDisabledChildTField;
    
    @FXML
    private TextField nubmerOfDependentsTField;
    
    @FXML
    private TextField numberOfChildTField;
    
    
    @FXML
    private TableView<User> taxTableView;

    @FXML
    private TableColumn<User, String> surnameTColumn;

    @FXML
    private TableColumn<User, String> nameTColumn;

    @FXML
    private TableColumn<User, Double> taxTColumn;
    
    @FXML
    private TableColumn<User, String> periodTColumn;
    
    @FXML
    private TextField costsOfPaymentTField;
    
    @FXML
    private TextField costsOfBuildingTField;
    
    @FXML
    private TextField costsOfEducationTField;
    
    @FXML
    private TextField taxOnDbTabTField;
    
    @FXML
    private Button chosenUserBtn;
    
    @FXML
    private Button usersBtn; //кнопка вывода введённых лиц в TableView
    
    @FXML
    private Button additionBtn; // кнопка добавления физ.лиц в TableView
    
    @FXML
    private Button preservationBtn; // кнопка сохранения в бд из TableView
    
    private ObservableList<User> userList = FXCollections.observableArrayList();
    
    Pattern decimalPattern = Pattern.compile("\\d*(\\.\\d{0,2})?");
   
    float []arrayValuesFromTaxTab = new float[3];
    float []arrayValuesFromExtraTab = new float[5];
    int numberOfDisabled;
   
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Посчитать во вкладке Tax
     * @param event - событие
     */
    @FXML
    void calculateTax(ActionEvent event) {
		if(checkValidInput()) {
			final DecimalFormat DF = new DecimalFormat("0.00");
			IncomeTax incomeTax = new IncomeTax(arrayValuesFromTaxTab, arrayValuesFromExtraTab, 
					timeCBox, noWorkRBtn, yesFaceRBtn, yesPriviligesRBtn);
			incomeTax.calculateTax();
			double incomeTaxValue = incomeTax.getTax();
			taxTField.setText(String.valueOf(DF.format(incomeTaxValue)));
			taxTField.setOpacity(1);
			taxOnDbTabTField.setText(String.valueOf(DF.format(incomeTaxValue)));
		}
    }
    
	/**
     * Метод,который вызывает другое окно
     * @param path - Путь к файлу fxml
     * @param title - Заголовок окна
     */
    private void switchToAnotherWindow(String path,String title) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource(path));
    	try {
    		loader.load();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	Parent root = loader.getRoot();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root));
    	stage.setResizable(false);
    	stage.setTitle(title);
    	stage.showAndWait();
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии Об авторе из меню помощь
     * @param event - событие
     */
    @FXML
    void goToAboutProgramFromMenuItem(ActionEvent event) {
    	String path = "/view/AboutProgram.fxml";
    	String title = "О программе";
    	switchToAnotherWindow(path,title);
    }

    /**
     * Обработка события,которое происходит 
     * при нажатии выход из меню файл
     * @param event - событие
     */
    @FXML
    void goOutFromMenuItem(ActionEvent event) {
    	System.exit(0);
    }

    /**
     * Обработка события,которое происходит 
     * при нажатии Нет в 4 пункте вкладки Подоходный налог
     * @param event - событие
     */
    @FXML
    void NoWorkRBtn(ActionEvent event) {
    	extraTab.setDisable(false);
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии Да в 4 пункте вкладки Подоходный налог
     * @param event - событие
     */
    @FXML
    void YesWorkRBtn(ActionEvent event) {
    	extraTab.setDisable(true);
    }

    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Об программе во вкладке Главная
     * @param event - событие
     */
    @FXML
    void goToAboutProgram(ActionEvent event) {
    	String path = "/view/AboutProgram.fxml";
    	String title = "О программе";
    	switchToAnotherWindow(path,title);
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки О авторе во вкладке Главная
     * @param event - событие
     */
    @FXML
    void goToAboutAuthor(ActionEvent event) {
    	String path = "/view/AboutAuthor.fxml";
    	String title = "Об Авторе";
    	switchToAnotherWindow(path,title);
    }
 
    /**
     * Метод,который проверяет валидность полей
     */
    private boolean checkValidInput() {
        TextField errorTField = null;
        boolean isSwitchTab = false;
    	if(timeCBox.getValue() == null){
    		HighlightForInvalidInputThread checkInputThread = new HighlightForInvalidInputThread();
    		checkInputThread.setTimeCBox(timeCBox);
    		checkInputThread.start();
    		taxTField.setText("Выберите период");
    		taxTField.setOpacity(1);
    		return false;
    	}
   		   try {	
   			   int i=0;
			   for(TextField element:arrayTField){
				   errorTField = element;
				   arrayValuesFromTaxTab[i] = Float.parseFloat(element.getText().trim());
				   i++;
   			   }
			   isSwitchTab = true;
			   i = 0;
			   if(noWorkRBtn.isSelected()) {
				   for(TextField element:array2TField){
					   errorTField = element;
					   arrayValuesFromExtraTab[i] =Float.parseFloat(element.getText().trim());
					   i++;
	   			   }
				   errorTField = numberOfDisabledChildTField;
				   numberOfDisabled = Integer.parseInt(numberOfDisabledChildTField.getText());
			   }
			   return true;
   		   }catch(NumberFormatException e ) {
   			   HighlightForInvalidInputThread checkInputThread = new HighlightForInvalidInputThread(); 
   			   checkInputThread.setErrorTField(errorTField);
   			   taxTField.setText("Неверный ввод");
			   taxTField.setOpacity(1);
   			   if(isSwitchTab) {
   				   SingleSelectionModel <Tab> selectionModel = tabPane.getSelectionModel();
    			   selectionModel.select(extraTab); 
   			   }
   			   checkInputThread.start();
   			   isSwitchTab = false;
   			   return false;
   		   }
    }

    /**
     * Метод,который задает ввод только целых чисел в поля
     * @param textField - текстовое поле
     */
    private void setDigitOnly(TextField textField) {	 
    	/** Анонимный внутренний класс слушатель для некоторых текстовых полей*/
    	textField.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable,String oldValue,
    				String newValue) {
    			if(!newValue.matches("\\d*")) {
    				textField.setText(newValue.replaceAll("[^\\d]", ""));
    			}
    		}
    	 });
    }
    

    private UnaryOperator<Change> filter = c -> {
        if (decimalPattern.matcher(c.getControlNewText()).matches()) {
            return c ;
        } else {
            return null ;
        }
    };
    
    TextFormatter<Double> formatter = new TextFormatter<>(filter);
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Перейти к БД во вкладке Подоходный налог
     * @param event - событие
     */
    @FXML
    void goToDbTab(ActionEvent event) {
    	SingleSelectionModel <Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(dbTab); 	
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Очистить во вкладке База данных
     * @param event - событие
     */
    @FXML
    void clearTextOnDbTab(ActionEvent event) {
    	nameOnDbTabTField.clear();
    	surnameOnDbTabTField.clear();
    }
    
   
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Загрузить из бд во вкладке База данных
     * @param event - событие
     */
    @FXML
    void loadFromDb(ActionEvent event) {
    	DatabaseHandler databaseHandler = new DatabaseHandler();
    	ObservableList<User> userList = FXCollections.observableArrayList();
    	try {
			userList = databaseHandler.getUsersInDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	taxTableView.setItems(userList);
    	chosenUserBtn.setDisable(true);
    	usersBtn.setDisable(false);
    	additionBtn.setDisable(true);
    	preservationBtn.setDisable(true);
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки удалить пользователя вкладке База данных
     * @param event - событие
     */
    @FXML
    void deleteChosenUserFromTableView(ActionEvent event) {
		int row = taxTableView.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			User remoteUser = taxTableView.getSelectionModel().getSelectedItem();
        	userList.remove(remoteUser);
		}
        	
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки сохранить в бд во вкладке База данных
     * @param event - событие
     */
    @FXML
    void saveToDb(ActionEvent event) {
    	DatabaseHandler databaseHandler = new DatabaseHandler();
    	for(User element : userList)
    	{
    		try {
				databaseHandler.addUserInDB(element);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	userList.clear();
    	chosenUserBtn.setDisable(true);
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Вывести введёных во вкладке База данных
     * @param event - событие
     */
    @FXML
    void showUsers(ActionEvent event) {
    	taxTableView.setItems(userList);
    	if(userList.isEmpty()) {
    		chosenUserBtn.setDisable(true);
    	}
    	else {
    		chosenUserBtn.setDisable(false);
    	}
    	usersBtn.setDisable(true);
		additionBtn.setDisable(false);
		preservationBtn.setDisable(false);
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Добавить во вкладке База данных
     * @param event - событие
     */
    @FXML
    void addInTableView(ActionEvent event) {
    	if(surnameOnDbTabTField.getText().isEmpty()) 
    		checkEmptyFieldInDbTab(surnameOnDbTabTField);
    	else if(nameOnDbTabTField.getText().isEmpty()) 
    		checkEmptyFieldInDbTab(nameOnDbTabTField);
    	else if(timeCBox.getValue() == null) {
    		SingleSelectionModel <Tab> selectionModel = tabPane.getSelectionModel();
    		selectionModel.select(taxTab);
    		checkEmptyFieldInDbTab(timeCBox);
    	}
    	else if(taxOnDbTabTField.getText().isEmpty()){
    		SingleSelectionModel <Tab> selectionModel = tabPane.getSelectionModel();
    		selectionModel.select(taxTab);
    		checkEmptyFieldInDbTab(taxTField);
    	}
    	else {
        	String surname = surnameOnDbTabTField.getText();
        	String name = nameOnDbTabTField.getText(); 
        	float tax = convertToFloat(taxOnDbTabTField.getText());
        	String period = timeCBox.getValue();
    		User user = new User(surname,name,period,tax);
        	userList.add(user);
        	taxTableView.setItems(userList);
        	chosenUserBtn.setDisable(false);
    	}
    }
    
	/**
     * Метод,который конвертирует String в float 
     * @param text - подоходный налог в качестве String 
     * @return float
     */ 
    private float convertToFloat(String text) {
    	String []textArray = text.split(",");
    	float number =0;
    	number+=Float.parseFloat(textArray[0]);
    	if(textArray.length >1)
    		number+=Float.parseFloat(textArray[1]) *0.01;
    	return number;
    }
    
	/**
     * Метод,который проверяет пустые ли поля 
     * перед добавлением в TableView
     * @param invalidInput - элемент Node  
     */
    @SuppressWarnings("unchecked")
	private void checkEmptyFieldInDbTab(Node invalidInput) {
    	HighlightForInvalidInputThread checkInputThread = new HighlightForInvalidInputThread();
    	if(invalidInput instanceof TextField) {
    		TextField textField = (TextField)invalidInput;
    		checkInputThread.setErrorTField(textField);
    	}
    	else {
        	ChoiceBox<String> choiceBox = (ChoiceBox<String>)invalidInput;
    		checkInputThread.setTimeCBox(choiceBox);
    	}
		checkInputThread.start();
    }
    
    /**
     * Обработка события,которое происходит 
     * при нажатии кнопки Назад во вкладке Дополнительно
     * @param e - событие
     */
    @FXML
    void goToTaxTab(ActionEvent e) {
    	SingleSelectionModel <Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(taxTab); 
    }
    
	/**
     * Метод,задаёт формат текстовых полей 
     */
    private void setFormatter() {
    	setDigitOnly(numberOfDisabledChildTField);
		setDigitOnly(nubmerOfDependentsTField);
		setDigitOnly(numberOfChildTField);
		for(TextField element: arrayTField) {
			element.setTextFormatter(new TextFormatter<>(filter));
		}
		costsOfPaymentTField.setTextFormatter(new TextFormatter<>(filter));
		costsOfBuildingTField.setTextFormatter(new TextFormatter<>(filter));
		costsOfEducationTField.setTextFormatter(new TextFormatter<>(filter));
    }
    
    @FXML
    void initialize() {
		ObservableList<String> time =FXCollections.observableArrayList("квартал","полугодие","9  месяцев","год");
		timeCBox.setItems(time);
		extraTab.setDisable(true);
		taxTField.setOpacity(0);
		surnameTColumn.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));
		nameTColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
		periodTColumn.setCellValueFactory(new PropertyValueFactory<User,String>("period"));
		taxTColumn.setCellValueFactory(new PropertyValueFactory<User,Double>("incomeTax"));
		setFormatter();
		chosenUserBtn.setDisable(true);
		usersBtn.setDisable(true);
		ExitListenerClass exitListener = new ExitListenerClass();
		exitBtn.setOnAction(exitListener);
    }    
    
    /** Внутренний ExitListenerClass для кнопки exitBtn*/
    class ExitListenerClass implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			System.exit(0);
		}
    }
    
}
