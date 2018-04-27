package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Elevator;
import model.NPC;

public class ElevatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_1() {
		new Elevator(3, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_2() {
		new Elevator(6, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_1() {
		new Elevator(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_2() {
		new Elevator(-99, 8);
	}

	@Test
	public void GeneralTest() {
		Elevator e = new Elevator(4, 5);
		assertEquals(5, e.getSpeed());
		assertEquals(4, e.getCapacity());

		e.setCapacity(80);
		assertEquals(80, e.getCapacity());

		NPC n1 = new NPC();
		NPC n2 = new NPC();
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
		Building b = new Building();
	}

}
