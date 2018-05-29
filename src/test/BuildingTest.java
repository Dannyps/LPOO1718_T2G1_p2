package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entities.BuildingModel;

public class BuildingTest {
	
	@Test
	public void testSimpleConstructor() {
		BuildingModel b = new BuildingModel();
		assertEquals(2, b.getElevators().get(0).getSpeed());
	}

	@Test(expected = IllegalArgumentException.class)
	public void badNrFloors_1() {
		new BuildingModel(2, 5, 4, 4);
	}
	
	@Test
	public void goodNrFloors_1() {
		new BuildingModel(3, 5, 4, 4);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void badNrElevators_1() {
		new BuildingModel(5, 0, 4, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badElevatorSpeed_1() {
		new BuildingModel(5, 1, 9, 4);
	}
	
	@Test
	public void genericTest() {
		BuildingModel b = new BuildingModel(3, 5, 4, 4);
		assertEquals(5, b.getElevators().size());
		assertEquals(3, b.getFloorCount());
		assertEquals(4, b.getElevators().get(0).getSpeed());
	}

}
