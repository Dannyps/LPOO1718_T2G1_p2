package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entities.BuildingModel;
import model.entities.FloorModel;

public class FloorTest {

	@Test
	public void generalTest() {
		FloorModel f = new FloorModel(new BuildingModel(), 3, 0);
		f.setCapacity(2);
		assertEquals(2, f.getCapacity());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badCapacity() {
		new FloorModel(new BuildingModel(), 1, 0);
	}
	

}
