package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Building;
import model.Floor;

public class FloorTest {

	@Test
	public void generalTest() {
		Floor f = new Floor(new Building(), 3, 0);
		f.setCapacity(2);
		assertEquals(2, f.getCapacity());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badCapacity() {
		new Floor(new Building(), 1, 0);
	}
	

}
