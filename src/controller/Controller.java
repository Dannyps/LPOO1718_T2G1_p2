package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.DocumentationTool.Location;

import model.*;
import model.entities.*;
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
	
	// Holds the last tick time
	private static double lastTick = -1;

	public Controller(int nrOfFloors, int nrOfElevators) {
		this.numberFloors = nrOfFloors;
		this.numberElevators = nrOfElevators;
	}

	public Controller setDefaultElevatorCapacity(int elevatorCapacity) {
		this.defaultElevatorCapacity = elevatorCapacity;
		return this;
	}

	public Controller setDefaultFLoorCapacity(int floorCapacity) {
		this.defaultFloorCapacity = floorCapacity;
		return this;
	}

	public Controller setDefaultElevatorSpeed(int elevatorSpeed) {
		this.defaultElevatorSpeed = elevatorSpeed;
		return this;
	}

	public Controller init() {
		gameModel = new GameModel(numberFloors, numberElevators, defaultElevatorSpeed, defaultElevatorCapacity,
				defaultFloorCapacity);
		gameView = new GameView(gameModel);
		return this;
	}

	public GameView getGameView() {
		return gameView;
	}

	/*************************************/

	public void tick() {
		if (lastTick != -1) {
			double delta = (System.nanoTime() - lastTick) / 1e6; // miliseconds since last tick
		}
		lastTick = System.nanoTime();

		moveElevators();
		emptyElevators();
		generateNPCs();
		
		
		this.gameView.renderGameView();

	}

	/**
	 * Poisson, let's make some NPCs!
	 */
	private void generateNPCs() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Make NPCs that are in an elevator which is the the NPC's desired floor disappear.
	 */
	private void emptyElevators() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * one tick more, we'll get there!
	 * Only currently moving elevators should be moved :/
	 */
	private void moveElevators() {
		// TODO Auto-generated method stub
		
	}

	public Boolean ElevatorArrowCLicked(int floorNr, ElevatorModel e) {
		if (e.isMoving())
			return false;
		else {
			// set destination
			// make it move
			return true;
		}
	}

	private Boolean moveNPC2Elevator(NPCModel n, ElevatorModel e) {
		if (e.isMoving())
			return false;
		
		FloorModel f = (FloorModel) this.searchNPC(n);
		f.removeNPC(n);
		e.addNPC(n);
		return true;
	}

	public NPCContainerModel searchNPC(NPCModel npc) {

		// Search in Elevators
		for (ElevatorModel e : gameModel.getElevators()) {
			for (NPCModel n : e.getNpcs()) {
				if (n.equals(npc)) {
					return e;
				}
			}
		}

		// Search in Floors
		for (FloorModel f : gameModel.getFloors()) {
			for (NPCModel n : f.getNpcs()) {
				if (n.equals(npc)) {
					return f;
				}
			}
		}

		return null;
	}

	public FloorModel getFLoorByNumber(int i) {
		return this.getBuilding().getFloors().get(i);
	}

	private Boolean moveNPC2Floor(NPCModel n) {
		ElevatorModel e = (ElevatorModel) this.searchNPC(n);
		if (e.isMoving())
			return false;

		FloorModel f = e.getCurrFloor();
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

	/**
	 * 
	 * @param id
	 * @return true if npc is moved, false otherwise
	 * @throws Exception
	 */
	public Boolean npcClicked(NPCModel n) throws Exception {
		if (n.getLocation() == NPCLocation.LIFT) {
			return moveNPC2Floor(n);
		} else if (n.getLocation() == NPCLocation.FLOOR) {
			return moveNPC2Elevator(n, this.gameModel.getElevators().get(0));
		} else {
			throw new Exception("The clicked NPC had no associated location.");
		}
	}

	public GameModel getBuilding() {
		return gameModel;
	}

	public Set<NPCModel> getAllNPCs() {

		Set<NPCModel> ret = new HashSet<NPCModel>();

		// Elevators
		for (ElevatorModel e : gameModel.getElevators()) {
			for (NPCModel n : e.getNpcs()) {
				ret.add(n);
			}
		}

		// Floors
		for (FloorModel f : gameModel.getFloors()) {
			for (NPCModel n : f.getNpcs()) {
				ret.add(n);
			}
		}
		return ret;

	}

}
