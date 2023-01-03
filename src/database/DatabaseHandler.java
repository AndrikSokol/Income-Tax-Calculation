package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
/**
 * Класс DatabaseHandler
 * Устанавливает соединение и 
 * взаимодействует с базой данных:
 * добавление полей в бд
 * извлечение записей из бд
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class DatabaseHandler {
	public static final String USER_TABLE = "users";
	public static final String USER_ID = "user_id";
	public static final String USER_SURNAME = "surname";
	public static final String USER_NAME = "name";
	public static final String USER_PERIOD = "period";
	public static final String USER_TAX = "tax";
	private String dbName = "incometax";	
	Connection dbConnection;
	private ObservableList<User> userList = FXCollections.observableArrayList();
	
	/**
	 * Метод,который возвращает путь к базе данных
     * @return Connection
     */
	private Connection getDbConnection() {
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/incometax?" +
					"user=root&password=12345");
		}catch(Exception e){
			System.out.print(e);
		}
		return dbConnection;
	}
	
	/**
	 * Метод,который добавляет пользователя в бд
	 * @param user - пользователь
	 * @throws ClassNotFoundException
	 * @throws SQLException
     */
	public void addUserInDB(User user ) throws ClassNotFoundException, SQLException {
		String insert = "INSERT INTO " + USER_TABLE +"(" 
				+ USER_SURNAME +"," 
				+ USER_NAME + ","
				+ USER_PERIOD + ","
				+ USER_TAX  +")"
				+"VALUES(?,?,?,?)";
		PreparedStatement prSt = getDbConnection().prepareStatement(insert);
		prSt.setString(1,user.getSurname());
		prSt.setString(2,user.getName());
		prSt.setString(3,user.getPeriod());
		prSt.setFloat(4, user.getIncomeTax());
		prSt.executeUpdate();
	}
	
	/**
	 * Метод,который возвращает записи пользователей из бд
	 * @return ObservableList<User>
	 * @throws ClassNotFoundException
	 * @throws SQLException
     */
	public ObservableList<User> getUsersInDB() throws ClassNotFoundException, SQLException {
		String insert = "SELECT * FROM " + dbName +"." + USER_TABLE;
		PreparedStatement prSt = getDbConnection().prepareStatement(insert);
		ResultSet rs;
		rs = prSt.executeQuery();
		while(rs.next() != false) {
			String surname = rs.getString("surname");
			String name = rs.getString("name");
			String period = rs.getString("period");
			float tax = rs.getFloat("tax");
			
			User newUser = new User(surname,name,period,tax);
			userList.add(newUser);
		}
		return userList;
	}
}
