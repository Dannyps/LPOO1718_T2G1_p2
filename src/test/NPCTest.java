package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Building;
import model.NPC;

public class NPCTest {

	@Test
	public void generalTest() {
		Building b = new Building(30, 1, 3, 2);
		NPC n3 = new NPC(b, 0, 23);
		assertEquals(1, n3.getEmotionalLevel());
		for(int i = 0;i<5500;i++) {
			n3.tick();
		}
		assertEquals(6, n3.getEmotionalLevel());
	}
	

}
