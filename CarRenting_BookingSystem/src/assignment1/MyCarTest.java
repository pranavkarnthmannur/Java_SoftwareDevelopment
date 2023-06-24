package assignment1;

import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;

public class MyCarTest {
	
	// Testing User Email Address: All possible cases
	@Test
	public void emailtest1() {
		MyCar mycar = new MyCar();
		assertEquals(true,mycar.isValidEmail("s3828461@student.rmit.edu.au"));
	}
	
	@Test
	public void emailtest2() {
		MyCar mycar = new MyCar();
		assertTrue(mycar.isValidEmail("randomstring"));
	}
	
	@Test
	public void emailtest3() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidEmail("pranav1234.com"));
	}
	
	@Test
	public void emailtest4() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidEmail("4372fwg5"));
	}
	
	@Test
	public void emailtest5() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidEmail("646583"));
	}
	
	// Testing Customer name: All possible cases
	@Test
	public void nametest1() {
		MyCar mycar = new MyCar();
		assertEquals(true,mycar.isValidName("Pranav"));
	}
	
	@Test
	public void nametest2() {
		MyCar mycar = new MyCar();
		assertTrue(mycar.isValidName("Mannur"));
	}
	
	@Test
	public void nametest3() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidName("pranav1234"));
	}
	
	@Test
	public void nametest4() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidName("92841"));
	}
	
	
	// Testing the date validity: All possible cases
	@Test
	public void validDate1() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidDate("thisisastring"));
	}
	
	@Test
	public void validDate2() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isValidDate("18/08/2022"));
	}
	
	@Test
	public void validDate3() {
		MyCar mycar = new MyCar();
		assertEquals(true,mycar.isValidDate("18/08/2022"));
	}
	
	@Test
	public void validDate4() {
		MyCar mycar = new MyCar();
		assertEquals(true,mycar.isValidDate("18-08-2022"));
	}
	
	@Test
	public void validDate5() {
		MyCar mycar = new MyCar();
		assertEquals(true,mycar.isValidDate("8/08/2022"));
	}
	
	public void validDate6() {
		MyCar mycar = new MyCar();
		assertTrue(mycar.isValidDate("027101"));
	}
	
	// Testing Pickup date after today's date: All possible cases
	@Test
	public void aftertoday1() {
		MyCar mycar = new MyCar();
		assertTrue(mycar.isAfterTodayDate("18/12/2022"));
	}
	
	@Test
	public void aftertoday2() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isAfterTodayDate("16/08/2022"));
	}
	
	@Test
	public void aftertoday3() {
		MyCar mycar = new MyCar();
		assertEquals(false,mycar.isAfterTodayDate("22/08/2022"));
	}

	
	// Testing if return date is after pick-up date: All possible cases
	@Test
	public void isAfterPickUp1() {
		MyCar mycar = new MyCar();
		assertEquals(false,mycar.isAfterPickUp("15/09/2022","14/09/2022"));
	}
	@Test
	public void isAfterPickUp2() {
		MyCar mycar = new MyCar();
		assertFalse(mycar.isAfterPickUp("15/09/2022","16/09/2022"));
	}
	
	@Test
	public void isAfterPickUp3() {
		MyCar mycar = new MyCar();
		assertTrue(mycar.isAfterPickUp("15/09/2022","25/09/2022"));
	}

}
