
package assignment4;
import java.io.*;


public class House {
	
	// Declaring all the variables with private keyword
	private String houseID;
	private String houseAddress;
	private String houseType;
	private double housePrice;
	private int numBedrooms;
	private int numBathrooms;
	private int numCarsspace;
	private double sizeHouse;
	private String descriptionHouse;
	private boolean filewriten = false;
	
	
	// Using getter and setter for all the variables 
	public String getHouseID() {
		return houseID;
	}

	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public double getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(double housePrice) {
		this.housePrice = housePrice;
	}

	public int getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public int getNumBathrooms() {
		return numBathrooms;
	}

	public void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public int getNumCarsspace() {
		return numCarsspace;
	}

	public void setNumCarsspace(int numCarsspace) {
		this.numCarsspace = numCarsspace;
	}

	public double getSizeHouse() {
		return sizeHouse;
	}

	public void setSizeHouse(double sizeHouse) {
		this.sizeHouse = sizeHouse;
	}

	public String getDescriptionHouse() {
		return descriptionHouse;
	}

	public void setDescriptionHouse(String descriptionHouse) {
		this.descriptionHouse = descriptionHouse;
	}
	
	// House Constructor 
	public House (String id, String address, String type, double price, int bedrooms, int bathrooms, int carspace, double size, String description)
	{
		this.houseID = id;
		this.houseAddress = address;
		this.houseType = type;
		this.housePrice = price;
		this.numBedrooms = bedrooms;
		this.numBathrooms = bathrooms;
		this.numCarsspace = carspace;
		this.sizeHouse = size;
		this.descriptionHouse = description;
	}
	
	// Checking if the first 3 characters in the house ID are uppercase
	public static boolean isUpperCase(String s)
	{
	    for (int i=0; i<3; i++)
	    {
	        if (!Character.isUpperCase(s.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}
	
	// Checking if the 4th to the last characters in the house ID are numeric
	public static boolean isNumeric(String s) { 
		  try {  
		    Integer.parseInt(s);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	// Checking if the House description is between 5 and 10 words long
	public static boolean condition1(String s)
	{
		String[] hdesc = s.split(" ");
		if(hdesc.length >=5 && hdesc.length <= 10)
		{
			return true;
		}
		return false;
	}
	
	// Checking if the House added has more than 4 bedrooms and bathrooms is less than 2
	public boolean condition2(int numbed, int numbath)
	{

		if(numbed>4 && numbath <=2)
		{
			return false;
		}
		return true;
	}
	
	// Checking if the House added has less than 50 meter sq in size and price is more than 350000
	public boolean condition3(double hsize, double hprice)
	{
		if(hsize<50 && hprice > 350000)
		{
			return false;
		}
		return true;
	}
	
	// Checking if the House with type Apartment has atleast 1 carspace and other types have 0 or more carspaces 
	public boolean condition4(String htype, int hcarspace)
	{
		if(htype.equals("Apartment") && hcarspace >=1)
		{
			return true;
		}
		else if(!htype.equals("Apartment"))
		{
			if(numCarsspace >= 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	// Checking if the house added has a price between 100000 and 1500000
	public boolean condition5(double hprice)
	{
		if(hprice >= 100000 && hprice <= 1500000)
		{
			return true;
		}
		return false;
	}
	
	// Checking if the house added has less than 3 bedrooms and 2 bathrooms and exceeds $750000
	public boolean condition6(int numbed, int numbath, double hprice)
	{
		if(numbed<3 && numbath <2)
		{
			if(hprice>=750000)
			{
			return false;
			}
		}
		return true;
	}
	
	// Checking if the updated house details has an increase in houseprice less than or equal to 10% for less than 3 bedrooms and 20% for other houses
	public boolean updatecondition2(double oldprice, double newprice, int oldbed)
	{
		if(newprice>oldprice)
		{
			if(oldbed<3)
			{
				double percinc = (Math.abs(newprice - oldprice)/oldprice)*100;
				if(percinc<=10)
				{
					return true;
				}
			}
			else if(oldbed>=3)
			{
				double percinc = (Math.abs(newprice - oldprice)/oldprice)*100;
				if(percinc<=20)
				{
					return true;
				}
			}
		}
		if(newprice<=oldprice)
		{
		return true;
		}
		return false;
				
	}
	
	// Checking if the updated house details has an address change for other houses except for townhouse
	public boolean updatecondition3(String htype, String oldaddress, String newaddress)
	{
		if(!oldaddress.equals(newaddress))
		{ 	
			if(htype.equals("Townhouse"))
			{
			return true;
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	
	// Checking if the updated house details has a size increase between 5% and 10%
	public boolean updatecondition4(double oldsize, double newsize)
	{
			
			if(newsize>oldsize)
			{
				double percsizeinc = (Math.abs(newsize - oldsize)/oldsize)*100;
				if(percsizeinc<=10 && percsizeinc>=5)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if(newsize<=oldsize)
			{
				return true;
			}
			return false;
	}
	
	
	//Create new txt file to store, retrieve and update the house details
	File file = new File("text.txt");
	
	// Add house function checks all possible details of the house and writes it to a file: returns true if added else returns false
	public boolean AddHouse(){
		
		if(isUpperCase(houseID) && houseID.length() == 10 && isNumeric(houseID.substring(3,houseID.length())) && condition1(descriptionHouse)
				&& condition2(numBedrooms, numBathrooms) && condition3(sizeHouse, housePrice) && condition4(houseType, numCarsspace)
				&& condition5(housePrice) && condition6(numBedrooms, numBathrooms, housePrice))
		{	
			// Creates a new file to add details to the file to write 
			try
			{
				BufferedWriter bw = new BufferedWriter( new FileWriter(file));
				bw.write(this.houseID+","+this.houseAddress+","+this.houseType+","+this.housePrice+","+this.numBedrooms+","+this.numBathrooms+","+this.numCarsspace+","+this.sizeHouse+","+this.descriptionHouse+"\n");
				bw.close();
				
				System.out.println("House added");
				
				return true;
				
				
			}
			catch(Exception e)
			{
				System.out.println("House not added");
				return false;
				
			}
		}
		System.out.println("House not added");
		return false;
	}
	
	//Update house function checks condition of all possible details of the house and updates it to a file: return true if updated else returns false
	public boolean UpdateHouse(String newAddress, String newType, double newPrice, int newBedrooms,
			int newBathrooms, int newCarspace, double newSize, String newDescription) 
	{
		
		// Checks if the file is written with house details
		if(file.length()!=0)
		{
			if(condition1(newDescription) && condition2(newBedrooms, newBathrooms) && updatecondition2(housePrice, newPrice,numBedrooms) &&
			condition3(newSize, newPrice) && condition4(newType, newCarspace) && condition4(newType, newCarspace) && updatecondition3(houseType, houseAddress, newAddress)
			&& condition5(newPrice) && condition6(newBedrooms, newBathrooms, newPrice) && updatecondition4(sizeHouse,newSize))
			{
				// Writes to the existing file 
				try
				{
					BufferedWriter bw = new BufferedWriter( new FileWriter("text.txt"));
					bw.write(this.houseID+","+newAddress+","+newType+","+newPrice+","+newBedrooms+","+newBathrooms+","+newCarspace+","+newSize+","+newDescription+"\n");
					bw.close();
					
					System.out.println("House updated");
					return true;
					
				}
				catch(Exception e)
				{
					System.out.println("House not updated ");
					return false;
					
				}
			}
			else
			{
				System.out.println("House not updated");
				return false;
			}
		}
		System.out.println("House not added, so not updated");
		return false;
}
	
}
