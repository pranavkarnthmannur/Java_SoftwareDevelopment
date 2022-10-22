package assignment4;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;


class HouseTest {
	
	// Testing the addHouse function with Valid inputs
	@Test
	public void addhousetest1() {
		House H1 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		assertEquals(true, H1.AddHouse());
		
		House H2 = new House("AAA8989664", "35 Nepean drive Cranbourne 3970", "Apartment", 550000, 2, 1, 1, 80, "This is a small comfy Apartment");
		assertEquals(true, H2.AddHouse());
	}
	
	// Testing the addHouse function with inValid House ID
	@Test
	public void addhousetest2() {
		House H3 = new House("AAA", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		assertEquals(true,H3.AddHouse());
		
		House H4 = new House("AAA89896645", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		assertEquals(true, H4.AddHouse());
	}
	
	// Testing the addHouse function with inValid description less than 5 words and more than 10 words
	@Test
	public void addhousetest3() {
		House H5 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 25, "Beautiful House");
		assertEquals(true, H5.AddHouse());
		
		House H6 = new House("AAA8989678", "97 Monash drive Malvern 3500", "Townhouse", 700000, 3, 2, 1, 80, "Malvern east Malvern east expensive Townhouse Plush living Garden and dog park closeby with kids play area");
		assertEquals(true, H6.AddHouse());
	}
	
	// Testing the addHouse function with inValid Price: more than 1500000 and less than 100000
	@Test
	public void addhousetest4() {
		House H7 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 1600000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		assertEquals(true, H7.AddHouse());
		
		House H8 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 85000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		assertEquals(true, H8.AddHouse());
	}
	
	// Testing the updateHouse function with valid inputs
	@Test
	public void updatehousetest1() {
		House H9 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		H9.AddHouse();
		boolean B9 = H9.UpdateHouse("69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde after Clyde North"); 
		assertEquals(true, B9);
		
		House H10 = new House("AAA8989664", "35 Nepean drive Cranbourne 3970", "Apartment", 550000, 2, 1, 1, 80, "This is a small comfy Apartment");
		H10.AddHouse();
		boolean B10 = H10.UpdateHouse("35 Nepean drive Cranbourne 3970", "Apartment", 550000, 2, 1, 1, 80, "This is a small comfy Apartment in Cranbourne east");	
		assertEquals(true, B10);
	}
	
	// Testing the updateHouse function with Invalid price: more than 10% for less than 3 bedroom house and more than 20% for other houses
	@Test
	public void updatehousetest2() {
		House H11 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 2, 2, 2, 80, "Beautiful House located in Clyde");
		H11.AddHouse();
		boolean B11 = H11.UpdateHouse("69 Pelagos drive Clyde 3978", "House", 950000, 3, 2, 2, 80, "Beautiful House located in Clyde after Clyde North"); 
		assertEquals(true, B11);
		
		House H12 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		H12.AddHouse();
		boolean B12 = H12.UpdateHouse("69 Pelagos drive Clyde 3978", "House", 2000000000, 3, 2, 2, 80, "Beautiful House located in Clyde after Clyde North");	
		assertEquals(true, B12);
	}
	
	// Testing the updateHouse function with Invalid size: increase the size between 5% and 10%
	@Test
	public void updatehousetest3() {
		House H13 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		H13.AddHouse();
		boolean B13 = H13.UpdateHouse("69 Pelagos drive ClydeNorth 3978", "House", 850000, 3, 2, 2, 82, "Beautiful House located in Clyde"); 
		assertEquals(false, B13);
		
		House H14 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
		H14.AddHouse();
		boolean B14 = H14.UpdateHouse("69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 500, "Beautiful House located in Clyde after Clyde North");	
		assertEquals(false, B14);
	}
	
	// Testing the updateHouse function with invalid address: address change for other houses (not townhouse) 
		@Test
		public void updatehousetest4() {
			House H15 = new House("AAA8989664", "69 Pelagos drive Clyde 3978", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde");
			H15.AddHouse();
			boolean B15 = H15.UpdateHouse("69 Pelagos drive Clyde 3978 after Clyde North", "House", 850000, 3, 2, 2, 80, "Beautiful House located in Clyde after Clyde North"); 
			assertEquals(false, B15);
			
			House H16 = new House("AAA8989664", "35 Nepean drive Cranbourne 3970", "Apartment", 550000, 2, 1, 1, 80, "This is a small comfy Apartment");
			H16.AddHouse();
			boolean B16 = H16.UpdateHouse("35 Nepean drive Cranbourne 3970 before Clyde", "Apartment", 550000, 2, 1, 1, 80, "This is a small comfy Apartment in Cranbourne east");	
			assertEquals(false, B16);
		}

	

}
