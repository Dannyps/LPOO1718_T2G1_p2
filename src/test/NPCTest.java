package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entities.BuildingModel;
import model.entities.NPCEmotion;
import model.entities.NPCModel;

public class NPCTest {

	@Test
	public void generalTest() {
		BuildingModel b = new BuildingModel(30, 1, 3, 2);
		NPCModel n3 = new NPCModel(0, 23);
		assertEquals(NPCEmotion.Smiling, n3.getEmotionalLevel());
		for (int i = 0; i < 3; i++) {
			n3.setNextEmotionalLevel();
		}
		assertEquals(NPCEmotion.Thinking, n3.getEmotionalLevel());
		assertEquals(23, n3.getDestinationFloor());
		assertEquals(0, n3.getOriginFloor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void badOriginFloor() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(5, 1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badOriginFloor2() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(-1, 1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badDestinationFloor() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(1, 5));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badDestinationFloor2() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(1, -1));
	}
	
	@Test
	public void goodFloors() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(1, 4));
		b.getUserElevators().get(0).addNPC(new NPCModel(4, 3));
		b.getUserElevators().get(0).addNPC(new NPCModel(0, 3));
		b.getUserElevators().get(0).addNPC(new NPCModel(1, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sameFloors() {
		BuildingModel b = new BuildingModel(5, 1, 4, 4);
		b.getUserElevators().get(0).addNPC(new NPCModel(4, 4));
	}

}
