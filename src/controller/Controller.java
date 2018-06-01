package controller;

import javax.tools.DocumentationTool.Location;

import model.*;

public class Controller {

	private Building building;
	private int dec = 4;
	private int noe = 1;
	private int nof = 10;
	private int dfc = 7;
	private int des = 3;
	private String errMsg;

	public Controller( int nrOfFLoors, int nrOfElevators) {
		this.nof = nrOfFLoors;
		this.noe = nrOfElevators;
		
	}
	
	public void setDefaultElevatorCapacity(int elevatorCapacity) {
		this.dec = elevatorCapacity;
	}
	
	public void setDefaultFLoorCapacity(int floorCapacity) {
		this.dfc = floorCapacity;
	}
	
	public void setDefaultElevatorSpeed(int elevatorSpeed) {
		this.des = elevatorSpeed;
	}
	
	public Building init() {
		this.building = new Building(nof, noe, des, dec, dfc);
		return this.building;
		
	}
	
	
	/*************************************/
	
	/**
	 * 
	 * @param id
	 * @return true if npc is moved, false otherwise
	 * @throws Exception 
	 */
	public Boolean npcClicked(NPC n) throws Exception {
		if(n.getLocation()==NPCLocation.LIFT) {
			return moveNPC2Floor(n);
		} else if(n.getLocation()==NPCLocation.FLOOR) {
			return moveNPC2Elevator();
		}else {
			throw new Exception("The clicked NPC had no associated location.");
		}
	}
	
	public Boolean ElevatorArrowCLicked(int floorNr, Elevator e) {
		if(e.isMoving())
			return false;
		else {
			// set destination
			// make it move
			return true;
		}
	}
	
	public void tick() {
		
	}
	
	private Boolean moveNPC2Elevator() {
		// TODO Auto-generated method stub
		return null;
	}

	private Boolean moveNPC2Floor(NPC n) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLatestErrorMessage() {
		return errMsg;
	}

	public static String getFormsTitle() {
		return "Liftimulator";
	}
}
