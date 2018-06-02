package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.tools.DocumentationTool.Location;

import model.*;

public class Controller {

	private Building building;
	public Building getBuilding() {
		return building;
	}

	private int dec = 4;
	private int noe = 1;
	private int nof = 10;
	private int dfc = 7;
	private int des = 3;
	private String errMsg;

	public Controller(int nrOfFLoors, int nrOfElevators) {
		this.nof = nrOfFLoors;
		this.noe = nrOfElevators;

	}

	public Controller setDefaultElevatorCapacity(int elevatorCapacity) {
		this.dec = elevatorCapacity;
		return this;
	}

	public Controller setDefaultFLoorCapacity(int floorCapacity) {
		this.dfc = floorCapacity;
		return this;
	}

	public Controller setDefaultElevatorSpeed(int elevatorSpeed) {
		this.des = elevatorSpeed;
		return this;
	}

	public Controller init() {
		this.building = new Building(nof, noe, des, dec, dfc);
		return this;

	}

	/*************************************/

	/**
	 * 
	 * @param id
	 * @return true if npc is moved, false otherwise
	 * @throws Exception
	 */
	public Boolean npcClicked(NPC n) throws Exception {
		if (n.getLocation() == NPCLocation.LIFT) {
			return moveNPC2Floor(n);
		} else if (n.getLocation() == NPCLocation.FLOOR) {
			return moveNPC2Elevator(n, this.getBuilding().getElevators().get(0));
		} else {
			throw new Exception("The clicked NPC had no associated location.");
		}
	}

	public Boolean ElevatorArrowCLicked(int floorNr, Elevator e) {
		if (e.isMoving())
			return false;
		else {
			// set destination
			// make it move
			return true;
		}
	}

	public void tick() {
		// update all elevators' positions.
		// generate NPCs
	}

	private Boolean moveNPC2Elevator(NPC n, Elevator e) {
		if(e.isMoving())
			return false;
		Floor f = (Floor) this.searchNPC(n);
		f.removeNPC(n);
		e.addNPC(n);
		return true;
	}

	public NPCContainer searchNPC(NPC npc) {

		// Search in Elevators
		for (Elevator e : this.building.getElevators()) {
			for (NPC n : e.getNpcs()) {
				if (n.equals(npc)) {
					return e;
				}
			}
		}

		// Search in Floors
		for (Floor f : this.building.getFloors()) {
			for (NPC n : f.getNpcs()) {
				if (n.equals(npc)) {
					return f;
				}
			}
		}

		return null;
	}
	
	
	
	public Set<NPC> getAllNPCs(){
		
		Set<NPC> ret = new HashSet<NPC>(); 
		
		// Elevators
		for (Elevator e : this.building.getElevators()) {
			for (NPC n : e.getNpcs()) {
				ret.add(n);
			}
		}

		// Floors
		for (Floor f : this.building.getFloors()) {
			for (NPC n : f.getNpcs()) {
				ret.add(n);
			}
		}
		return ret;
		
	}

	public Floor getFLoorByNumber(int i) {
		return this.getBuilding().getFloors().get(i);
	}
	
	private Boolean moveNPC2Floor(NPC n) {
		Elevator e = (Elevator) this.searchNPC(n);
		if(e.isMoving())
			return false;
		
		Floor f = e.getCurrFloor();
		e.removeNPC(n);
		f.addNPC(n);
		return true;
	}

	
	public String getLatestErrorMessage() {
		return errMsg;
	}

	public static String getFormsTitle() {
		return "Liftimulator";
	}

}
