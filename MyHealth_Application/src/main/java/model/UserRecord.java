package main.java.model;

public class UserRecord {
	
	// User Record Model: has the records of the User
	private String date;
	private double weight;
	private double temperature;
	private double lowbp;
	private double highbp;
	private String notes;
	private int rID = 0;
	
	public UserRecord() {
		
	}
	
	//Constructor
	public UserRecord(String date, double weight, double temperature, double lowbp, double highbp, String notes)
	{
		this.date = date;
		this.weight = weight;
		this.temperature = temperature;
		this.lowbp = lowbp;
		this.highbp = highbp;
		this.notes = notes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getLowbp() {
		return lowbp;
	}

	public void setLowbp(double lowbp) {
		this.lowbp = lowbp;
	}

	public double getHighbp() {
		return highbp;
	}

	public void setHighbp(double highbp) {
		this.highbp = highbp;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}
	
	
}
