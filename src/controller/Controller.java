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
	private static final int EMOTION_TICKS = 1000;

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
	            		//Thread.sleep(20);
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
			
			// Update elevators
			for(ElevatorModel elevator : gameModel.getElevators()) {
				elevatorTick(elevator, delta);
			}
			
			// Add random NPCs
			generateNPCs(delta);
			
			lastTick = thisTick;
			this.gameView.renderGameView();
		} else {
			lastTick = System.nanoTime();
			this.gameView.renderGameView();
		}
	}
	
	/**
	 * Determine if a given elevator has arrived the destination
	 * @param e
	 * @return
	 */
	private boolean hasElevatorArrived(ElevatorModel e) {
		int floorY = getFloorCoordinates(e.getDestinationFloor());
		return (Math.abs(e.getPosY() - (gameView.getHeight() - floorY)) <= 5);
	}
	
	/**
	 * Calculates aproximately at which Y coordinate a specific floor starts
	 * @param floorNr
	 * @return
	 */
	private int getFloorCoordinates(int floorNr) {
		return (gameView.getHeight()/this.numberFloors) * (this.numberFloors - floorNr);
	}
	
	/**
	 * Ticks an elevator, updating it's position and state
	 * @param elevator Reference to elevator's model
	 * @param delta Milliseconds since last tick
	 * @return
	 */
	private void elevatorTick(ElevatorModel e, double delta) {
		if(e.getState() == ElevatorStates.MOVING) {
			// determine if speed is positive or negative and update coordinates
			if(getFloorCoordinates(e.getDestinationFloor()) > (gameView.getHeight() - e.getPosY()))
				e.setPosY(e.getPosY() - (int)(e.getSpeed()*20*delta/1000));
			else 
				e.setPosY(e.getPosY() + (int)(e.getSpeed()*20*delta/1000));

			// Update elevator state
			if(hasElevatorArrived(e)) {
				e.toggleState();
				e.setPosY(this.gameView.getHeight()*(1-1/numberFloors) - getFloorCoordinates(e.getDestinationFloor()) - 1);
			}
		}
	}
	
	/**
	 * Poisson, let's make some NPCs!
	 */
	private void generateNPCs(double delta) {
		double spawnRate = 0.5; // NPCs per second

		spawnRate *= 0.021;
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
				System.out.println(floorFrom);
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
