package assignment1;

import java.util.Scanner;

// This class runs the program/ application
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
        MyCar obj = new MyCar();
        obj.ReadRecords("Fleet.csv");

        String option = null;

        while(true)
        {
        	obj.Menu();
        	Scanner sc = new Scanner(System.in);
        	System.out.print("Please select: ");
            option = sc.next();
            Vehicle_details usermodel = null;

            switch(option){
                case "1":
                    usermodel = obj.SearchByBrand();
                    if(usermodel!=null)
                    {
                    obj.Invoice(usermodel);
                    }
                    break;
                case "2":
                    usermodel = obj.vehicleType();
                    if(usermodel!=null)
                    {
                    obj.Invoice(usermodel);
                    }
                    break;
                case "3":
                    usermodel = obj.numberPassenger();
                    if(usermodel!=null)
                    {
                    obj.Invoice(usermodel);
                    }
                    break;
                    
                case "4": System.out.println("********** Thank you for shopping with us **********");
                System.exit(0);
                break;
                
                default:  System.out.println("****** Please enter a valid input between 1 and 4 ******"); break;
                 
        }
        }
		
		
	}

}

