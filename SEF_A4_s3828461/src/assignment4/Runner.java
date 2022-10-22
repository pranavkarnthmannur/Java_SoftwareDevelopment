package assignment4;

import java.io.IOException;

// Class with the Main function to run the program 
public class Runner {
	
	// Adds house details and updates it: Program runner
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Add house
		House addh = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 2, 2, 2, 80, "Beautiful House located in Clyde");
		addh.AddHouse();
		
		//Update house
		addh.UpdateHouse("69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde after Clyde North");

	}
}
