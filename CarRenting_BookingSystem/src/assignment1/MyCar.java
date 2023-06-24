package assignment1;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class MyCar: reads the file and executes the functionalities 
public class MyCar{
	
List<Vehicle_details> vehicleList = new ArrayList<Vehicle_details>();

// To read the input file.
    public void ReadRecords(String filename){
        try {

            BufferedReader br = new BufferedReader(new FileReader(filename)); // to read the file 
            br.readLine();
            String line1 = null;
            
            
                while ((line1 = br.readLine())!= null)
                {
                    String[] cell = line1.strip().split(",");
                    Vehicle_details vehicle = new Vehicle_details();
                    
                    vehicle.setId(cell[0]);
                    vehicle.setBrand(cell[1]);
                    vehicle.setModel(cell[2]);
                    vehicle.setType(cell[3]);
                    vehicle.setYear(Integer.parseInt(cell[4]));
                    vehicle.setSeats(Integer.parseInt(cell[5]));
                    vehicle.setColour(cell[6]);
                    vehicle.setRent(Double.parseDouble(cell[7]));
                    vehicle.setInsurance(Double.parseDouble(cell[8].replaceAll("N/A", "0")));
                    vehicle.setFee(Double.parseDouble(cell[9].replaceAll("N/A", "0")));
                    vehicle.setDiscount(Double.parseDouble(cell[10].replaceAll("N/A", "0")));
                    
                    vehicleList.add(vehicle);

                }         
            } 
        
  // If file not found or Invalid input
        catch (FileNotFoundException e) {
           
        	System.out.println("Sorry, file not found. Goodbye!");
            System.exit(0);
        } 

        catch (IOException e) {
        	
        	System.out.println("Sorry, invalid input. Goodbye!");
			System.exit(0);
		}    
    
    }
    
    // Validate user name and surname
    public static boolean isValidName(String name)
	{
		return Pattern.matches("[a-zA-Z]+", name);
		
	}
    
    // Validate user email address
    public static boolean isValidEmail(String useremail)
	{
		Pattern emailpat =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher match = emailpat.matcher(useremail);
		return match.find();		
	}
    
    // Validate if the return date is after the pick-up date
    public static boolean isAfterPickUp(String pickdate, String retdate)
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate pickupdate = LocalDate.parse(pickdate,formatter);
	    LocalDate returndate = LocalDate.parse(retdate,formatter);
		
	    if(returndate.isAfter(pickupdate))
	    {
    	return true;
	    }
	    else
	    {
	    return false;
	    }
    }
    

    // Validate pickup date is after today
    public static boolean isAfterTodayDate(String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateObj = LocalDate.now();
	    String date1 = dateObj.format(formatter);
	    LocalDate datetoday = LocalDate.parse(date1,formatter);
	    LocalDate strDatelocal = LocalDate.parse(date,formatter);
	  
	       if(strDatelocal.isAfter(datetoday))
	       {
	       return true;
	       }
	       else
	       {
	    	   return false;
	       }
	}
    
    // Validate date input by user
	public static boolean isValidDate(String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try
		{
			formatter.parse(date);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
    
    
    
    // Ask the user for pickup and return date, calculate total and print invoice with personal details 
    public void Invoice(Vehicle_details usermodel)
    {	

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	    LocalDate dateObj = LocalDate.now();
	    String date = dateObj.format(formatter);
	    LocalDate datetoday = LocalDate.parse(date,formatter);

	    Scanner sc = new Scanner(System.in);
	    
    	System.out.println("--------------------------------------------------------------------------------\r\n"
    			+ "> Provide dates\r\n"
    			+ "--------------------------------------------------------------------------------");
	    
    	
    	// Validating date: pick up and return date
	    String strDate;
	    while(true)
	    {
	    System.out.print("Please provide pick-up date (dd/mm/yyyy): ");
		strDate = sc.next().strip();		
		if(isValidDate(strDate) && isAfterTodayDate(strDate))
		{
			break;
		}
		else
		{
		System.out.println();
		System.out.println("Enter your date in dd/mm/yyyy format only.");
		System.out.println("Your pick-up date should be after today.");
		System.out.println();
		continue;
		}
 
	    }

	    
	    String endDate;
	    while(true)
	    {
	    	System.out.print("Please provide return date (dd/mm/yyyy): ");
			endDate = sc.next().strip();	
			
			if(isValidDate(endDate) && isAfterPickUp(strDate,endDate))
			{
				break;
			}
			else
			{
			System.out.println();
			System.out.println("Enter your date in dd/mm/yyyy format only.");
			System.out.println("Your return date should be after the pick-up date.");
			System.out.println();
			continue;
			}
			
	    }	
  	
    	LocalDate date1 = LocalDate.parse(strDate,formatter);
		LocalDate date2 = LocalDate.parse(endDate,formatter);

		long noOfDaysBetween = ChronoUnit.DAYS.between(date1, date2) + 1;
		
		double rentalperday = usermodel.getRent();
		double discount = (100-usermodel.getDiscount())/100;
		double insuranceperday = usermodel.getInsurance();
		double servicefee = usermodel.getFee();
		double nodiscprice = (rentalperday+insuranceperday)*noOfDaysBetween + servicefee;
		double discprice = (rentalperday*discount+insuranceperday)*noOfDaysBetween + servicefee;
		double total;

		// Showing the vehicle details
		System.out.println("--------------------------------------------------------------------------------\r\n"
				+ "> Show vehicle details\r\n"
				+ "--------------------------------------------------------------------------------");
		

		System.out.printf("%-31s %s\n","Vehicle:",usermodel.getId());
		System.out.printf("%-31s %s\n","Brand:",usermodel.getBrand());
		System.out.printf("%-31s %s\n","Model:",usermodel.getModel());
		System.out.printf("%-31s %s\n","Type of vehicle:",usermodel.getType());
		System.out.printf("%-31s %s\n","Year of manufacture:",usermodel.getYear());
		System.out.printf("%-31s %s\n","No. Of seats:",usermodel.getSeats());
		System.out.printf("%-31s %s\n","Colour:",usermodel.getColour());
		System.out.println("Rental:             "+"\t\t"+"$"+rentalperday*noOfDaysBetween+"\t\t"+"("+rentalperday+" * "+noOfDaysBetween+" days"+")");
		if(noOfDaysBetween<7)
		{
		System.out.println("Discounted price:   "+"\t\t"+"$"+rentalperday*noOfDaysBetween+"\t\t"+"(Discount is not applicable)");
		}
		else
		{
		System.out.println("Discounted price:   "+"\t\t"+"$"+rentalperday*discount*noOfDaysBetween+"\t\t"+"(Discount is "+((rentalperday*noOfDaysBetween)-(rentalperday*discount*noOfDaysBetween))+")");
		}
		System.out.println("Insurance:           "+"\t\t"+"$"+insuranceperday*noOfDaysBetween+"\t\t"+"("+insuranceperday+" * "+noOfDaysBetween+" days"+")");
		System.out.println("Service fee:         "+"\t\t"+"$"+servicefee);
		if(noOfDaysBetween<7)
		{
		System.out.println("Total:              "+"\t\t"+"$"+nodiscprice);
		total = nodiscprice;
		}
		else
		{
		System.out.println("Total:              "+"\t\t"+"$"+nodiscprice);
		total = discprice;
		}
		
		// Validating user input
		while(true)
		{
		System.out.print("Would you like to reserve the vehicle (Y/N)?: ");
		String yesorno = sc.next().strip();
		if(yesorno.toLowerCase().strip().equals("y"))
		{
			
		System.out.println("--------------------------------------------------------------------------------\r\n"
				+ "> Provide personal information\r\n"
				+ "--------------------------------------------------------------------------------\r\n"
				+ "");
		
		// Validating user name
		String fname;
		while(true)
		{
		System.out.print("Please provide your given name: ");
		fname = sc.next().strip();
		
		if(isValidName(fname))
		{
			break;
		}
		else
		{
			System.out.println();
			System.out.println("Please enter a valid name.");
			System.out.println();
			continue;
		}
		}
		
		// Validating user surname
		String sname;
		while(true)
		{
		System.out.print("Please provide your surname: ");
		sname = sc.next().strip();
		
		if(isValidName(sname))
		{
			break;
		}
		else
		{
			System.out.println();
			System.out.println("Please enter a valid surname.");
			System.out.println();
			continue;
		}
		}
		
		// Validating user email address
		String email;
		while(true)
		{
		System.out.print("Please provide your email address: ");
		email = sc.next().strip();
		
		if(isValidEmail(email))
		{
		break;
		}
		else
		{	
				System.out.println();
				System.out.println("Please enter a valid email address, for example: pranav@gmail.com");
				System.out.println();
				continue;
		}
		}

		// Validating the number of passengers 
		String pass;
		while(true)
		{
		System.out.print("Please provide the number of passengers: ");
		pass = sc.next().strip();
		
		try 
		{ 
			Integer.parseInt(pass); 
			int passcount = Integer.parseInt(pass);
			if(passcount<usermodel.getSeats())
			{
			break;
			}
			else
			{	
				System.out.println();
				System.out.println("The number of passengers should be less than or equal to the number of seats.");
				System.out.println();
				continue;
			}
		}  
		catch (NumberFormatException e)  
		{ 
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Please enter a valid input."); 
			System.out.println("--------------------------------------------------------------------------------");
			continue;
		}
    	}

		// Printing final car details/ Invoice
		while(true)
		{
		System.out.print("Confirm and pay (Y/N): ");
		String confirm = sc.next().strip();
		if(confirm.toLowerCase().strip().equals("y"))
		{
		System.out.println("--------------------------------------------------------------------------------\r\n"
				+ "> Congratulations! Your vehicle is booked. A receipt has been sent to your email.\r\n"
				+ " We will soon be in touch before your pick-up date.\r\n"
				+ "--------------------------------------------------------------------------------");
		
		System.out.println("Name                   "+"\t\t"+fname+" "+sname);
		System.out.printf("%-31s %s\n","Email:",email);
		System.out.println("Your Vehicle           "+"\t\t"+usermodel.getBrand() +" "+ usermodel.getModel() + " "+ usermodel.getType() +" with "+usermodel.getSeats()+" seats");
		System.out.printf("%-31s %s\n","Number of passengers:",pass);
		System.out.printf("%-31s %s\n","Pick-up date:",date1);
		System.out.printf("%-31s %s\n","Return date:",date2);
		System.out.println("Total Payment          "+"\t\t"+"$"+total);
		System.exit(0);	
		}
		else if(confirm.toLowerCase().strip().equals("n"))
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Kindly review your decision:");
			System.out.println("--------------------------------------------------------------------------------");
			break;
		} 
		else
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Please enter only Y or N");
			System.out.println("--------------------------------------------------------------------------------");
			continue;
		}
		}
		
		
		}
		else if(yesorno.toLowerCase().strip().equals("n"))
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Lets go back to the main menu:");
			System.out.println("--------------------------------------------------------------------------------");
			break;
		}
		else
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Please enter only Y or N");
			System.out.println("--------------------------------------------------------------------------------");
			continue;
		}
		}	

    }
    
    // Display menu: Top level Menu
    public void Menu(){
        System.out.println("Welcome to MyCar!");
        System.out.println("--------------------------------------------------------------------------------\r\n"
        		+ "> Select from main menu\r\n"
        		+ "--------------------------------------------------------------------------------\r\n"
        		+ " 1) Search by brand\r\n"
        		+ " 2) Browse by vehicle type\r\n"
        		+ " 3) Filter by number of passengers\r\n"
        		+ " 4) Exit\r\n"
        		+ "");
    }

    
    // Allow the user to choose the car by brand name 
    public Vehicle_details SearchByBrand(){
    	MyCar obj = new MyCar();
    	Scanner sc = new Scanner(System.in);
    	List<Vehicle_details> brandsearch = new ArrayList<Vehicle_details>();
        
        
        Scanner sc1 = new Scanner(System.in);

        Vehicle_details usermodel = null;
        boolean menubreak1 = false;
 

        System.out.print("Please provide a brand: ");
    	String brandname = sc.next().strip();

        while(menubreak1 == false)
        {
        		
            int i = 0;
            for(Vehicle_details v: vehicleList){
            	
            	if(v.getBrand().toLowerCase().equals(brandname.toLowerCase())){
                	i= i+1;
                    brandsearch.add(v);

                    System.out.println(i+") " +v.getId()+" "+v.getBrand() +" "+ v.getModel() + " "+ v.getType() +" with "+v.getSeats()+" seats.");
                }  

            }
            
            if(i>0)
            {
            System.out.println(i+1+") "+"Go to main menu");	
            }
            
            // If vehicle from the following brand is not available. 
            else
            {
            	System.out.println("--------------------------------------------------------------------------------");
            	System.out.println("Sorry no Vehicle available with the following brand, Let's go back!");
            	System.out.println("--------------------------------------------------------------------------------");
            	break;
            } 
            i=0;

        // User choosing car option. 
        String modeloption;
        while(true)
        {
        int catchi = 1; 
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Choose your model from the above options only. ");
        modeloption = sc1.next().strip();
        
        try 
    	{ 
    		Integer.parseInt(modeloption); 
    		break;
    	}  
        
    	catch (NumberFormatException e)  
    	{ 
    		System.out.println("--------------------------------------------------------------------------------");
    		System.out.println("Please enter a number input only.");   	
    		continue;
    	} 
        }    
        
        int modeloptioncar = Integer.parseInt(modeloption);
        
        if(modeloptioncar>=0 && modeloptioncar<=brandsearch.size())
        {
        	usermodel = brandsearch.get(modeloptioncar-1);
        	menubreak1 = true;
        }

        else if(modeloptioncar==brandsearch.size()+1)
        {
        	menubreak1 = true;
        }
        
        else
        {
        	System.out.println("--------------------------------------------------------------------------------");
        	System.out.println("You have entered an incorrect value, please choose from the following options");
        	System.out.println("--------------------------------------------------------------------------------");
        	menubreak1 = false;
        }
        } 
        return usermodel;       
    }
    
    // Allow the user to choose the car by type of vehicle 
    public Vehicle_details vehicleType()
    {	
    	
    MyCar obj = new MyCar();

    boolean menubreak2 = false;
    Vehicle_details usermodel = null;

    while(menubreak2 == false)
    {
    	
    	// To print the type of vehicles available as a menu option.
    	Set<String> settype = new HashSet<String>(); 
    	int j = 1;
    	for(Vehicle_details v: vehicleList){
    		settype.add(v.getType());
    	}
    	for(String type: settype)
    	{
       	 System.out.println(j+") "+type);
       	 j = j+1;
    	}
   	System.out.println(j+") "+"Go to main menu");
   	j=1;

   	Scanner sc2 = new Scanner(System.in);
    String usertype = null;
    // Converting the set to an array: copies the elements and easy to access at the desired location
    String[] arrsettype = settype.toArray(new String[settype.size()]);
    
    ArrayList<Vehicle_details> modelnametype = new ArrayList<Vehicle_details>();
    
    // User selects type of car.
    int catchj = 1;
    String typeoption;
    while(true)
    {
    System.out.println("--------------------------------------------------------------------------------");
    System.out.print("Choose your model: ");
    typeoption = sc2.next().strip();
    
    try 
	{ 
		Integer.parseInt(typeoption); 
		break;
	}  
    
	catch (NumberFormatException e)  
	{ 
		for(String type: settype)
    	{
       	 System.out.println(catchj+") "+type);
       	 catchj = catchj+1;
    	}
		System.out.println(catchj+") "+"Go to main menu");
		catchj = 1;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Please enter a number input only."); 
		continue;		
	} 
	}
    
    
    int typeoptioncar = Integer.parseInt(typeoption);
    
    
    if(typeoptioncar>=0 && typeoptioncar<=settype.size())
    {	
    		
    	usertype = arrsettype[typeoptioncar-1]; // accessing the element from the array
    	menubreak2 = true;
        

        boolean menubreak3 = false;
        
        while(menubreak3 == false)
        {
        	
        	int k = 0;
            for(Vehicle_details v: vehicleList){
            	if(v.getType().equals(usertype)){
                	
                	k = k+1;
                	modelnametype.add(v);
                	System.out.println(k+") " +v.getId()+" "+v.getBrand() +" "+ v.getModel() + " "+ v.getType() +" with "+v.getSeats()+" seats.");
                }
            }
            System.out.println(k+1+") "+"Go to main menu");	
            k=0;
        	
            // User chooses car of the respective type. 
            String usermodelnametype;
            while(true)
            {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Choose your model ");
            usermodelnametype = sc2.next().strip();
            
            try 
        	{ 
        		Integer.parseInt(usermodelnametype); 
        		break;
        	}  
            
        	catch (NumberFormatException e)  
        	{ 
           		System.out.println("--------------------------------------------------------------------------------");
           		System.out.println("Please enter a number input only.");          		
        		continue;
        	} 
        	}
            
        int usermodelnametypeoption = Integer.parseInt(usermodelnametype);
        
        if(usermodelnametypeoption>=0 && usermodelnametypeoption<=modelnametype.size())
        {
        	usermodel = modelnametype.get(usermodelnametypeoption-1);
        	menubreak3 = true;
        }
        else if(usermodelnametypeoption==modelnametype.size()+1)
        {
        	menubreak3 = true;
        }
        else
        {	
        	System.out.println("--------------------------------------------------------------------------------");
        	System.out.println("You have entered an incorrect value, please choose from the following options only");
        	System.out.println("--------------------------------------------------------------------------------");
        	menubreak3 = false;
        }
        }   
    }
    
    else if(typeoptioncar == arrsettype.length+1)
    {
    	menubreak2 = true;
    }
    
    else
    {
    	System.out.println("--------------------------------------------------------------------------------");
    	System.out.println("You have entered an incorrect value, please choose from the following options only");
    	System.out.println("--------------------------------------------------------------------------------");
    	
    	menubreak2 = false;
    }
    } 

    return usermodel;
    }
    
    
 // Allow the user to choose the car by number of seats/ passengers
    public Vehicle_details numberPassenger()
    {
    	MyCar obj = new MyCar();
    	Scanner sc4 = new Scanner(System.in);

    	String passengers;
    	int passengersint;
    	
    	// User enters the number of passengers
    	while(true)
    	{
    	System.out.println("--------------------------------------------------------------------------------");
    	System.out.print("Please provide the number of passengers: ");
    	passengers = sc4.next().strip();
    	
    	try 
		{ 
			Integer.parseInt(passengers); 
			break;
		}  
		catch (NumberFormatException e)  
		{ 
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Please enter a number input only."); 
			continue;
		} 
    	}
    	
    	int passengerscount = Integer.parseInt(passengers);
        Vehicle_details usermodel = null;
        boolean menubreak3 = false;
        
        while(menubreak3 == false)
        {
        	
            List<Vehicle_details> passengersearch = new ArrayList<Vehicle_details>();
            // To print the vehicles available as a menu option, based on the number of seats: Number of passengers should be less than number of seats.
            int i = 0;
            for(Vehicle_details v: vehicleList){
            	if(v.getSeats() > passengerscount){
                	i= i+1;
                    passengersearch.add(v);
                    System.out.println(i+") " +v.getId()+" "+v.getBrand() +" "+ v.getModel() + " "+ v.getType() +" with "+v.getSeats()+" seats.");
                }
            }
            
            if(i>0)
            {
            System.out.println(passengersearch.size()+1+") "+"Go to main menu");
            }
            else
            {
            	System.out.println("--------------------------------------------------------------------------------");
            	System.out.println("Sorry no Vehicle available with this number of passengers, Let's go back!");
            	System.out.println("--------------------------------------------------------------------------------");	
            	break;
            }	
        	
            // User selects the car based of the number of passengers entered. 
            String passengeroption;
            while(true)
            {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Choose your model ");
            passengeroption = sc4.next().strip();
            
            try 
        	{ 
        		Integer.parseInt(passengeroption); 
        		break;
        	}  
        	catch (NumberFormatException e)  
        	{ 
        		
           		System.out.println("--------------------------------------------------------------------------------");
           		System.out.println("Please enter a number input only.");     
        		continue;
        	} 
        	}  

        int passengeroptionint = Integer.parseInt(passengeroption);
        if(passengeroptionint>=0 && passengeroptionint<=passengersearch.size())
        {

        	usermodel = passengersearch.get(passengeroptionint-1);
        	menubreak3 = true;
        }

        else if(passengeroptionint==passengersearch.size()+1)
        {
        	menubreak3 = true;
        }
        
        else
        {
        	System.out.println("--------------------------------------------------------------------------------");
        	System.out.println("You have entered an incorrect value, please choose from the following options only");
        	System.out.println("--------------------------------------------------------------------------------");
        	menubreak3 = false;
        }
        }  
        return usermodel;	
    }
}
