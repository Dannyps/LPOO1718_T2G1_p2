package controller;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.*;
import model.entities.*;
import view.GameView;

public class Controller {

	Random rnd = new Random();
	Random rnd2 = new Random();

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
		double delta = 0;
		if (lastTick != -1) {
			delta = (System.nanoTime() - lastTick) / 1e6; // miliseconds since last tick
			lastTick = System.nanoTime();
		} else {
			lastTick = System.nanoTime();
			return; // first tick only sets lastTick.
		}

		moveElevators(delta);
		emptyElevators();
		generateNPCs(delta);

		this.gameView.renderGameView();

	}

	/**
	 * Poisson, let's make some NPCs!
	 */
	private void generateNPCs(double delta) {
		double spawnRate = 0.5; // NPCs per second

		spawnRate *= 0.1190;
		spawnRate *= rnd.nextDouble();

		int npcCount = (int) (delta * spawnRate);

		if (npcCount == 0) {
			return;
		}

		for (int i = 0; i < npcCount; i++) {
			int floorMax = this.gameModel.getFloorCount();
			int floorFrom = rnd2.nextInt(floorMax);
			int floorTo = rnd2.nextInt(floorMax);
			
			try {
				NPCModel n = new NPCModel(floorFrom, floorTo);
				this.gameModel.getFloors().get(floorFrom).addNPC(n);
			} catch (Exception e) {
				// do nothing
			}
			
		}
	}

	/**
	 * Make NPCs that are in an elevator which is the the NPC's desired floor
	 * disappear.
	 */
	private void emptyElevators() {
		// TODO Auto-generated method stub

	}

	/**
	 * one tick more, we'll get there! Only currently MOVING elevators should be
	 * moved :/
	 * 
	 * @param delta
	 */
	private void moveElevators(double delta) {
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
