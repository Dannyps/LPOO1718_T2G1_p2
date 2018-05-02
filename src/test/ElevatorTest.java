package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Building;
import model.Elevator;
import model.NPC;
import model.NPCLocation;

public class ElevatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_1() {
		new Elevator(new Building(), 3, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_2() {
		new Elevator(new Building(), 6, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_1() {
		new Elevator(new Building(), 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_2() {
		new Elevator(new Building(), -99, 8);
	}

	@Test
	public void GeneralTest() {
		Elevator e = new Elevator(new Building(), 4, 5);
		assertEquals(5, e.getSpeed());
		assertEquals(4, e.getCapacity());

		e.setCapacity(80);
		assertEquals(80, e.getCapacity());

		NPC n1 = new NPC();
		NPC n2 = new NPC();
		System.out.println(n1.getOriginFloor());
		System.out.println(n1.getDestinationFloor());
		System.out.println(e.b.getFloorCount());
		assertTrue(e.addNPC(n1));
		assertEquals(1, e.getFillCount());
		assertEquals(79, e.getEmptyCount());
		assertFalse(e.addNPC(n1));
		assertFalse(e.removeNPC(n2));
		assertTrue(e.addNPC(n2));
		assertTrue(e.removeNPC(n2));
		e.setCapacity(1);
		assertFalse(e.addNPC(n2));
		assertTrue(e.removeNPC(n1));
		assertTrue(e.addNPC(n2));
		for (NPC n : e.getNpcs()) {
			assertEquals(n, n2);
		}

		e.setSpeed(5);
		assertEquals(5, e.getSpeed());

	}

	@Test
	public void integratedTest() {
		Building b = new Building(20, 1, 4, 2, 3);
		NPC n1 = new NPC(0, 2);
		NPC n2 = new NPC(0, 2);
		NPC n3 = new NPC(0, 19);
		Elevator e = b.getElevators().get(0);
		assertTrue(e.addNPC(n1));
		assertTrue(e.addNPC(n2));
		assertFalse(e.addNPC(n3)); // elevator is full
		assertEquals(NPCLocation.LIFT, n1.getLocation());
	}

}
