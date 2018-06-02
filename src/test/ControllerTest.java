package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import model.entities.*;

public class ControllerTest {

	
	@Test(expected = Exception.class)
	public void badNPC() throws Exception {
		Controller c = (new Controller(5, 2))
				.setDefaultElevatorCapacity(4)
				.setDefaultElevatorSpeed(3)
				.setDefaultFLoorCapacity(7)
				.init();
		NPCModel n = new NPCModel();
		c.npcClicked(n);	
		
	}
	
	@Test
	public void GeneralTest() {
		Controller c = (new Controller(5, 2)).setDefaultElevatorCapacity(4).setDefaultElevatorSpeed(3)
				.setDefaultFLoorCapacity(7).init();

		assertEquals(5, c.getBuilding().getFloorCount());

		NPCModel n1 = new NPCModel();
		NPCModel n2 = new NPCModel();
		NPCModel n3 = new NPCModel();

		c.getBuilding().getFloors().get(0).addNPC(n1);

		c.getBuilding().getElevators().get(0).addNPC(n2);

		assertTrue(c.searchNPC(n1) instanceof FloorModel);

		assertTrue(c.searchNPC(n2) instanceof ElevatorModel);

		assertEquals(2, c.getAllNPCs().size());

		assertEquals(null, c.searchNPC(n3)); // should not be found as it has not been assigned to any NPCContainer

		try {
			NPCLocation startLocation = n1.getLocation();
			assertTrue(c.npcClicked(n1));
			assertTrue(c.npcClicked(n1));
			NPCLocation endLocation = n1.getLocation();
			assertEquals(startLocation, endLocation);
			
			c.npcClicked(n1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		c.getBuilding().getElevators().get(0).setMoving();

		try {
			assertFalse(c.npcClicked(n1));
			assertFalse(c.npcClicked(n2));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		assertFalse(c.ElevatorArrowCLicked(5, c.getBuilding().getElevators().get(0)));
		
		c.getBuilding().getElevators().get(0).setFloor(c.getFLoorByNumber(4));
		
		try {
			assertTrue(c.npcClicked(n1));
			assertEquals(NPCLocation.FLOOR, n1.getLocation());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}