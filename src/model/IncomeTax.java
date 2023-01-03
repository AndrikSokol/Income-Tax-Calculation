package model;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
/**
 * Класс IncomeTax
 * Вычисляет подоходный налог
 * @author Sakalouski A.V.
 * @version 1.0
 */
public class IncomeTax {
	private float []arrayValuesFromTaxTab = new float[3];;
	private float []arrayValuesFromExtraTab = new float[5];
	private ChoiceBox<String> timeCBox;
	private RadioButton noWorkRBtn;
	private RadioButton yesFaceRBtn;
	private RadioButton yesPriviligesRBtn;
	private double incomeTax;
	private int time;
	
    /**
     * Конструктор для создания экземпляра класса IncomeTax
     * @param arrayValuesFromTaxTab ‐ массив значений текстовых полей из вкладки налог
     * @param arrayValuesFromExtraTab ‐  массив значений текстовых полей из вкладки дополнительно
     * @param timeCBox - элемент ChoiceBox<String>
     * @param noWorkRBtn - элемент RadioButton
     * @param yesFaceRBtn - элемент RadioButton
     * @param yesPriviligesRBtn - элемент RadioButton
     */
	public IncomeTax(float[] arrayValuesFromTaxTab, float[]arrayValuesFromExtraTab, 
			ChoiceBox<String> timeCBox, RadioButton noWorkRBtn, 
			RadioButton yesFaceRBtn,RadioButton yesPriviligesRBtn){
		setArrayValuesFromTaxTab(arrayValuesFromTaxTab);
		setArrayValuesFromExtraTab(arrayValuesFromExtraTab);
		setTimeCBox(timeCBox);
		setNoWorkRBtn(noWorkRBtn);
		setYesFaceRBtn(yesFaceRBtn);
		setYesPriviligesRBtn(yesPriviligesRBtn);
		time = getTime();
	}
	
	/**
     * Метод,который считает подоходный налог
     */
	public void calculateTax() {
		int deductionforChild = 40;
		int numberOfDisabled = 0;
		int dependentPeople = 40;
		if(arrayValuesFromTaxTab[0]+arrayValuesFromTaxTab[1] > arrayValuesFromTaxTab[2]) {
			if(noWorkRBtn.isSelected()) {
				if(yesFaceRBtn.isSelected()) {
					dependentPeople = 75;
				}
				if(arrayValuesFromExtraTab[0]>1 || yesFaceRBtn.isSelected()) {
					deductionforChild = 75;
				}
				else if(arrayValuesFromExtraTab[0] == numberOfDisabled) {
					deductionforChild = 75;
				}
				float cashInMounth = (arrayValuesFromTaxTab[0]+arrayValuesFromTaxTab[1]-arrayValuesFromTaxTab[2])/time;
				if(cashInMounth < 817) {
					cashInMounth -= 135;
    			}
				if(yesPriviligesRBtn.isSelected()) {
					cashInMounth -=190;
				}	
				incomeTax =  (cashInMounth - arrayValuesFromExtraTab[0]*deductionforChild - arrayValuesFromExtraTab[1]*dependentPeople - arrayValuesFromExtraTab[2]/time - arrayValuesFromExtraTab[3]/time - arrayValuesFromExtraTab[4]/time) * 0.16 * time;
				if (incomeTax <=0) {
					incomeTax =0;
    			}
			}
			else {
				incomeTax = (arrayValuesFromTaxTab[0]+arrayValuesFromTaxTab[1]-arrayValuesFromTaxTab[2])*0.16;
			}
		}
	}
	
	/**
     * Метод,возвращает время периода в числовом виде
     * @return int
     */
	private int getTime() {
    	String time =timeCBox.getValue();
    	if(time.contains("квартал"))
    		return 3;
    	else if(time.contains("полугодие"))
    		return 6;
    	else if(time.contains("9 месяцев"))
    		return 9;
    	else return 12;
    }
	
	
	/**
     * Метод,который изменяет timeCBox 
     * @param timeCBox - элемент для выбора периода
     */
	public void setTimeCBox(ChoiceBox<String> timeCBox) {
		this.timeCBox = timeCBox;
	}
	
	/**
     * Метод,который изменяет arrayValuesFromTaxTab
     * @param arrayValuesFromTaxTab -  массив значений текстовых 
     * полей из вкладки налог
     */
	public void setArrayValuesFromTaxTab(float []arrayValuesFromTaxTab) {
		int i=0;
		for(float element :arrayValuesFromTaxTab) {
			this.arrayValuesFromTaxTab[i] = element;
			i++;
		}
	}
	
	/**
     * Метод,который изменяет arrayValuesFromExtraTab
     * @param arrayValuesFromTaxTab -  массив значений текстовых 
     * полей из вкладки дополнительно
     */
	public void setArrayValuesFromExtraTab(float []arrayValuesFromExtraTab) {
		int i=0;
		for(float element :arrayValuesFromExtraTab) {
			this.arrayValuesFromExtraTab[i] = element;
			i++;
		}
	}
	
	/**
     * Метод,который изменяет noWorkRBtn
     * @param noWorkRBtn - элемент RadioButton
     */
	public void setNoWorkRBtn(RadioButton noWorkRBtn) {
		this.noWorkRBtn = noWorkRBtn;
	}
	
	/**
     * Метод,который изменяет yesFaceRBtn
     * @param yesFaceRBtn - элемент RadioButton
     */
	public void setYesFaceRBtn(RadioButton yesFaceRBtn) {
		this.yesFaceRBtn = yesFaceRBtn;
	}
	
	/**
     * Метод,который изменяет yesPriviligesRBtn
     * @param yesPriviligesRBtn - элемент RadioButton
     */
	public void setYesPriviligesRBtn(RadioButton yesPriviligesRBtn) {
		this.yesPriviligesRBtn = yesPriviligesRBtn;
	}
	
	/**
     * Метод,который возвращает подоходный налог
     * @return double
     */
	public double getTax() {
		return incomeTax;
	}
	

}
