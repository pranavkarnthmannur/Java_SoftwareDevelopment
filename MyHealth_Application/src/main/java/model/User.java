package main.java.model;

import java.util.ArrayList;
import java.util.List;


// User has all the attributes of the user
public class User {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<UserRecord> records = new ArrayList<UserRecord>();

	public User() {
	}
	
	// Constructor
	public User(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public void setrecords(UserRecord record)
	{
		records.add(record);
	}
	
	public List<UserRecord> getrecords()
	{
		return records;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
