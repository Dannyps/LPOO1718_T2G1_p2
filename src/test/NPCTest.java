package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Building;
import model.NPC;

public class NPCTest {

	@Test
	public void generalTest() {
		Building b = new Building(30, 1, 3, 2);
		NPC n3 = new NPC(0, 23);
		assertEquals(1, n3.getEmotionalLevel());
		for (int i = 0; i < 5500; i++) {
			n3.tick();
		}
		assertEquals(510, n3.getEmotionTicker());
		assertEquals(6, n3.getEmotionalLevel());
		assertEquals(23, n3.getDestinationFloor());
		assertEquals(0, n3.getOriginFloor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void badOriginFloor() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(5, 1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badOriginFloor2() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(-1, 1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badDestinationFloor() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(1, 5));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badDestinationFloor2() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(1, -1));
	}
	
	@Test
	public void goodFloors() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(1, 4));
		b.getElevators().get(0).addNPC(new NPC(4, 3));
		b.getElevators().get(0).addNPC(new NPC(0, 3));
		b.getElevators().get(0).addNPC(new NPC(1, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sameFloors() {
		Building b = new Building(5, 1, 4, 4);
		b.getElevators().get(0).addNPC(new NPC(4, 4));
	}

}
