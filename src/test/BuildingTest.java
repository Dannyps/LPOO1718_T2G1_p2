package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Building;
import model.Elevator;

public class BuildingTest {

	@Test(expected = IllegalArgumentException.class)
	public void badNrFloors_1() {
		new Building(2, 5, 4, 4);
	}
	
	@Test
	public void goodNrFloors_1() {
		new Building(3, 5, 4, 4);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void badNrElevators_1() {
		new Building(5, 0, 4, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badElevatorSpeed_1() {
		new Building(5, 1, 9, 4);
	}
	
	@Test
	public void genericTest() {
		Building b = new Building(3, 5, 4, 4);
		assertEquals(5, b.getElevators().size());
		assertEquals(3, b.getFloorCount());
		assertEquals(4, b.getElevators().get(0).getSpeed());
	}

}
