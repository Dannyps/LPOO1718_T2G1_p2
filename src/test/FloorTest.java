package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Floor;

public class FloorTest {

	@Test
	public void generalTest() {
		Floor f = new Floor(3);
		f.setCapacity(2);
		assertEquals(2, f.getCapacity());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badCapacity() {
		new Floor(1);
	}
	

}
