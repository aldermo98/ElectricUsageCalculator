package edu.csus.csc131.euc.mainWindow;

public class Rate {
	
	private String[] hours;
	private double price;
	private final String[] defaultHours;
	private final double defaultPrice;
	private int[] listTimesIndices;
	private boolean isCustomized;
	
//Constructors, getters, setters///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Rate(String[] defaultHours, double defaultPrice) {
		this.hours = defaultHours;
		this.price = defaultPrice;
		this.defaultHours = defaultHours;
		this.defaultPrice = defaultPrice;
		this.isCustomized = false;
	}
	
	public String[] getHours() {
		return hours;
	}
	public String getHours(int index) {
		return hours[index];
	}
	public void setHours(String[] hours) {
		this.hours = hours;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int[] getListTimesIndices() {
		return listTimesIndices;
	}
	public void setListTimesIndices(int[] indices) {
		this.listTimesIndices = indices;
	}
	
	public void setIsCustomized(boolean wasCustomized) {
		this.isCustomized = wasCustomized;
	}
	public boolean isCustomized() {
		return isCustomized;
	}
	
//Getters for default rate plan values//////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getDefaultHours() {
		return defaultHours;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}
	
}