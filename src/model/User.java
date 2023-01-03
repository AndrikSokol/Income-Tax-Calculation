package model;
/**
 * Класс User
 * содержит информацию о пользователе:
 * имя,фамилия,подоходный налог, период
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class User {
	private String name;
	private String surname;
	private float incomeTax;
	private String period;
	
    /**
     * Конструктор для создания экземпляра пользователя
     * @param surname ‐ фамилия
     * @param name ‐ имя
     * @param period - период
     * @param incomeTax - подоходный налог
     */
	public User(String surname,String name,String period,float incomeTax){
		this.surname = surname;
		this.name = name;
		this.period = period;
		this.incomeTax = incomeTax;
	}
	
	/**
     * Метод,который возвращает имя
     * @return String
     */
	public String getName() {
		return name;
	}
	
	/**
     * Метод,который изменяет имя
     * @param name - имя
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Метод,который возвращает фамилию
     * @return String
     */
	public String getSurname() {
		return surname;
	}
	
	/**
     * Метод,который изменяет фамилию
     * @param surname - фамилия
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
     * Метод,который возвращает подоходный налог
     * @return float
     */
	public float getIncomeTax() {
		return incomeTax;
	}
	
	/**
     * Метод,который изменяет фамилию
     * @param incomeTax - подоходный налог
     */
	public void setIncomeTax(float incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	/**
     * Метод,который возвращает период
     * @return String
     */
	public String getPeriod() {
		return period;
	}
	
	/**
     * Метод,который изменяет период
     * @param period - период
     */
	public void setPeriod(String period) {
		this.period = period;
	}
}
