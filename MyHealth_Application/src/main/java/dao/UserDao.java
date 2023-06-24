package main.java.dao;

import java.sql.SQLException;
import java.util.List;

import main.java.model.User;
import main.java.model.UserRecord;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface
 * to a database or other persistence mechanism. the DAO maps application calls
 * to the persistence layer and provides some specific data operations without
 * exposing details of the database.
 */

// User DAO Interface
public interface UserDao {
	void setup() throws SQLException;

	User getUser(String username, String password) throws SQLException;

	User createUser(String username, String password, String firstname, String lastname) throws SQLException;
	
	void edituserdetails(String newfname, String newlname, User user) throws SQLException;
	
	List<main.java.model.UserRecord> exisitingRecords(String username) throws SQLException;
	main.java.model.UserRecord addUserRecord(String date, double weight, double temperature, double bplow, double bphigh, String note, String username) throws SQLException;

	int addnewrecordID() throws SQLException;

	void delRec(int rID) throws SQLException;

	void edituserrecorddetails(String date, double wt, double temp, double lowbp, double highbp, String note, int rID)
			throws SQLException;

}
