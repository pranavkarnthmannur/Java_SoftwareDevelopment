package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.User;
import main.java.model.UserRecord;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";
	private final String TABLE_NAME_REC = "healthrecords"; 

	public UserDaoImpl() {
	}
	
	// Creating tables: user and record
	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection(); Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL," + "firstname VARCHAR(10) NOT NULL," + "lastname VARCHAR(10) NOT NULL," + "PRIMARY KEY (username))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_REC + "(rID INTEGER PRIMARY KEY AUTOINCREMENT," + "username VARCHAR(10) REFERENCES users (username)," + "date STRING," + 
			"temperature DOUBLE," + "weight DOUBLE," + "lowbp DOUBLE," + "highbp DOUBLE," + "notes STRING)";
			stmt.executeUpdate(sql);
		}
	}
	
	//Retrieving data from user table 
	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);


			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					return user;
				}
				return null;
			}
		}
	}
	
	// Inserting data into user table
	@Override
	public User createUser(String username, String password, String firstname, String lastname) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstname);
			stmt.setString(4, lastname);

			stmt.executeUpdate();
			return new User(username, password, firstname, lastname);
		}
	}
	
	// Updating data of user; user table
	@Override
	public void edituserdetails(String newfname, String newlname, User user) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET firstname = ?, lastname = ?" +" WHERE username = ?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, newfname);
			stmt.setString(2, newlname);
			stmt.setString(3, user.getUsername());
			stmt.executeUpdate();
		}
	}
	
	// Editing user details: health records
	@Override
	public void edituserrecorddetails(String date, double wt, double temp, double lowbp, double highbp, String note, int rID) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME_REC + " SET date = ?, temperature = ?, weight = ? , lowbp = ?, highbp = ?, notes = ?" +" WHERE rID = ?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, date);
			stmt.setDouble(2, wt);
			stmt.setDouble(3, temp);
			stmt.setDouble(4, lowbp);
			stmt.setDouble(5, highbp);
			stmt.setString(6, note);
			stmt.setInt(7, rID);
			stmt.executeUpdate();
		}
	}
	
	// Retrieving all records data
	@Override
	public List<main.java.model.UserRecord> exisitingRecords(String username) throws SQLException{
		String sql = "SELECT * FROM " + TABLE_NAME_REC + " WHERE username = ?";
		List<main.java.model.UserRecord> recordsobj = new ArrayList<main.java.model.UserRecord>();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
				do {
					UserRecord userrecord = new UserRecord();
					userrecord.setTemperature(rs.getDouble("temperature"));
					userrecord.setWeight(rs.getDouble("weight"));
					userrecord.setLowbp(rs.getDouble("lowbp"));
					userrecord.setHighbp(rs.getDouble("highbp"));
					userrecord.setNotes(rs.getString("notes"));
					userrecord.setDate(rs.getString("date"));
					userrecord.setrID(rs.getInt("rID"));
					recordsobj.add(userrecord);
					} while (rs.next());
				return recordsobj;
				}
				return null;
			}
		}
	}
	
	// adding record ID for each record
	@Override
	public int addnewrecordID() throws SQLException{
		String sql = "SELECT * FROM " + TABLE_NAME_REC + " ORDER BY rID DESC LIMIT 1";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
				return rs.getInt("rID");
				}
				else {
				return 0;
				}
			}
		}
	}
	
	// Deleting record from table
	@Override
	public void delRec(int rID) throws SQLException{

		String sql = "DELETE FROM " + TABLE_NAME_REC + " WHERE rID = ?";

		try (Connection connection = Database.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sql);) {

			stmt.setInt(1, rID);
			stmt.executeUpdate();

		}

	}
	
	
	// Inserting into table health records
	@Override
	public main.java.model.UserRecord addUserRecord(String date, double weight, double temperature, double lowbp, double highbp, String notes, String username) throws SQLException{
		String sql = "INSERT INTO " + TABLE_NAME_REC + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(2, username);
			stmt.setString(3, date);
			stmt.setDouble(4, temperature);
			stmt.setDouble(5, weight);
			stmt.setDouble(6, lowbp);
			stmt.setDouble(7, highbp);
			stmt.setString(8, notes);

			stmt.executeUpdate();
			return new main.java.model.UserRecord(date, weight, temperature, lowbp, highbp, notes);
		}
	}

	
	
}
