package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.DocumentationTool.Location;

import model.*;
import model.entities.*;
import view.GameView;

public class Controller {
	
	// singleton controller instance
	private static Controller instance = null;
	
	// references for gameModel and gameView
	private GameModel gameModel;
	private GameView gameView;
	
	// setting constants
	private int defaultElevatorCapacity = 4;
	private int numberElevators = 1;
	private int numberFloors = 10;
	private int defaultFloorCapacity = 7;
	private int defaultElevatorSpeed = 1;
	private String errMsg;
	
	// Holds the last tick time
	private static double lastTick = -1;
	
	/**
	 * Constructor
	 * @param nrOfFloors
	 * @param nrOfElevators
	 */
	public Controller(int nrOfFloors, int nrOfElevators) {
		this.numberFloors = nrOfFloors;
		this.numberElevators = nrOfElevators;
		instance = this;
	}
	
	/**
	 * Initializes gameModel and gameView
	 * @return
	 */
	public Controller init() {
		gameModel = new GameModel(numberFloors, numberElevators, defaultElevatorSpeed, defaultElevatorCapacity,
				defaultFloorCapacity);
		gameView = new GameView(gameModel);
		return this;
	}
	
	/**
	 * Returns the singleton Controller instance
	 * @return
	 */
	public static Controller getInstance() {
		return instance;
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

	public GameView getGameView() {
		return gameView;
	}

	/**
	 * @throws InterruptedException ***********************************/
	
	public void start(){
		new Thread(new Runnable() {
	        public void run() {
	            while (true) {
	            	tick();
	            	try {
	            		Thread.sleep(20);
	            	}
	            	catch (Exception e) {
						// TODO: handle exception
					}
	            	
	            }
	        }
	    }).start();
	}
	
	private void tick() {
		if(lastTick != -1) {
			long thisTick = System.nanoTime();
			double delta = (thisTick - lastTick)/1e6; // miliseconds since last tick
			if(delta < 100)
				return;
			
			for(ElevatorModel elevator : gameModel.getElevators()) {
				if(elevator.getState() == ElevatorStates.MOVING) {
					
					// check if elevator arrived destination
					// update coordinates
					elevator.setPosY(elevator.getPosY() + (int)(elevator.getSpeed()*20*delta/1000));
					/*int elevatorFloor = Math.abs(gameView.getHeight()/elevator.getPosY());
					System.out.println(elevatorFloor);*/
				}
			}
			
			lastTick = thisTick;
		} else {
			lastTick = System.nanoTime();
		}
		
		
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
		if (e.isMoving()) {
			errMsg = "You can't change elevator trajectory while it's moving!";
			return false;
		}
			
		
		e.setDestinationFloor(floorNr);
		e.toggleState();
		return true;
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

	public FloorModel getFloorByNumber(int i) {
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
