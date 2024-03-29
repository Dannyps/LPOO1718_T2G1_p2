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
				.setDefaultFloorCapacity(7);
		NPCModel n = new NPCModel();
		c.eventNPCClicked(n);	
		
	}
	
	@Test
	public void GeneralTest() {
		Controller c = (new Controller(5, 2)).setDefaultElevatorCapacity(4).setDefaultElevatorSpeed(3)
				.setDefaultFloorCapacity(7);

		assertEquals(5, c.getGameModel().getFloorCount());

		NPCModel n1 = new NPCModel();
		NPCModel n2 = new NPCModel();
		NPCModel n3 = new NPCModel();

		c.getGameModel().getFloors().get(0).addNPC(n1);

		c.getGameModel().getUserElevators().get(0).addNPC(n2);

		assertTrue(c.searchNPC(n1) instanceof FloorModel);

		assertTrue(c.searchNPC(n2) instanceof ElevatorModel);

		assertEquals(2, c.getAllNPCs().size());

		assertEquals(null, c.searchNPC(n3)); // should not be found as it has not been assigned to any NPCContainer

		try {
			NPCLocation startLocation = n1.getLocation();
			assertTrue(c.eventNPCClicked(n1));
			assertTrue(c.eventNPCClicked(n1));
			NPCLocation endLocation = n1.getLocation();
			assertEquals(startLocation, endLocation);
			
			c.eventNPCClicked(n1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		c.getGameModel().getUserElevators().get(0).toggleState(); 

		try {
			assertFalse(c.eventNPCClicked(n1));
			assertFalse(c.eventNPCClicked(n2));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		assertFalse(c.eventElevatorArrowClicked(5, c.getGameModel().getUserElevators().get(0)));
		
		c.getGameModel().getUserElevators().get(0).toggleState(); 
		
		c.getGameModel().getUserElevators().get(0).setFloor(c.getFloorByNumber(4));
		
		try {
			assertTrue(c.eventNPCClicked(n1));
			assertEquals(NPCLocation.FLOOR, n1.getLocation());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void GeneralTest2() {
		Controller c = (new Controller(5, 2)).setDefaultElevatorCapacity(4).setDefaultElevatorSpeed(3)
				.setDefaultFloorCapacity(7);
		assertTrue(c.getAllNPCs().isEmpty());
		assertNotNull(c.getGameModel());
		assertNotNull(c.getGameView());
		assertNotNull(Controller.getFormsTitle());
		assertNotNull(c.getLatestErrorMessage());
		assertEquals(0, c.getGameModel().getScore());
		assertEquals(0, c.getGameModel().getScore());
		
	}
}