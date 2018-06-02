package controller;

import javax.tools.DocumentationTool.Location;

import model.*;
import view.GameView;

public class Controller {

	private GameModel gameModel;
	private GameView gameView;
	
	private int defaultElevatorCapacity = 4;
	private int numberElevators = 1;
	private int numberFloors = 10;
	private int defaultFloorCapacity = 7;
	private int defaultElevatorSpeed = 3;
	private String errMsg;

	public Controller(int nrOfFloors, int nrOfElevators) {
		this.numberFloors = nrOfFloors;
		this.numberElevators = nrOfElevators;
		
		gameModel = new GameModel(nrOfFloors, nrOfElevators, defaultElevatorSpeed, defaultElevatorCapacity, defaultFloorCapacity);
		gameView = new GameView(gameModel);
	}
	
	public void setDefaultElevatorCapacity(int elevatorCapacity) {
		this.defaultElevatorCapacity = elevatorCapacity;
	}
	
	public void setDefaultFLoorCapacity(int floorCapacity) {
		this.defaultFloorCapacity = floorCapacity;
	}
	
	public void setDefaultElevatorSpeed(int elevatorSpeed) {
		this.defaultElevatorSpeed = elevatorSpeed;
	}
	
	public GameView getGameView() {
		return gameView;
	}
		
	/*************************************/
	
	public void tick() {
		this.gameView.renderGameView();
	}
	
	/**
	 * 
	 * @param id
	 * @return true if npc is moved, false otherwise
	 * @throws Exception 
	 */
	/*
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

	*/
	
	public static String getFormsTitle() {
		return "Liftimulator";
	}
}
