package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entities.BuildingModel;
import model.entities.ElevatorModel;
import model.entities.NPCLocation;
import model.entities.NPCModel;

public class ElevatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_1() {
		new ElevatorModel(new BuildingModel(), 3, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badSpeed_2() {
		new ElevatorModel(new BuildingModel(), 6, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_1() {
		new ElevatorModel(new BuildingModel(), 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badCapacity_2() {
		new ElevatorModel(new BuildingModel(), -99, 8);
	}

	@Test
	public void GeneralTest() {
		ElevatorModel e = new ElevatorModel(new BuildingModel(), 4, 5);
		assertEquals(5, e.getSpeed());
		assertEquals(4, e.getCapacity());

		e.setCapacity(80);
		assertEquals(80, e.getCapacity());

		NPCModel n1 = new NPCModel();
		NPCModel n2 = new NPCModel();
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
		for (NPCModel n : e.getNpcs()) {
			assertEquals(n, n2);
		}

		e.setSpeed(5);
		assertEquals(5, e.getSpeed());

	}

	@Test
	public void integratedTest() {
		BuildingModel b = new BuildingModel(20, 1, 4, 2, 3);
		NPCModel n1 = new NPCModel(0, 2);
		NPCModel n2 = new NPCModel(0, 2);
		NPCModel n3 = new NPCModel(0, 19);
		ElevatorModel e = b.getElevators().get(0);
		assertTrue(e.addNPC(n1));
		assertTrue(e.addNPC(n2));
		assertFalse(e.addNPC(n3)); // elevator is full
		assertEquals(NPCLocation.LIFT, n1.getLocation());
	}

}
