package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import model.*;
import model.entities.*;
import view.GameView;

public class Controller {
	
	/**
	 * Random objects used to generate NPCs
	 */
	Random rnd = new Random();
	Random rnd2 = new Random();

	
	// singleton controller instance
	private static Controller instance = null;
	
	// references for gameModel and gameView
	private GameModel gameModel;
	private GameView gameView;
	
	// setting constants
	int addedNPCs = 0;
	long exec_began = 0;
	private int defaultElevatorCapacity = 4;
	private int numberElevators = 1;
	private int numberFloors = 10;
	private int defaultFloorCapacity = 7;
	private int defaultElevatorSpeed = 1;
	private String gameStatus = "Good luck!";

	// Holds the last tick time
	private static double lastTick = -1;
	
	// The last time NPCs emotion were updated
	private long lastNPCEmotionTick;
	private int NPCEmotionTickDelta = 4000;

	
	/**
	+----------------------+
	|                      |
	|    Initialization    |
	|                      |
	+----------------------+
	*/
	
	/**
	 * Constructor
	 * @param nrOfFloors
	 * @param nrOfElevators
	 */
	public Controller(int nrOfFloors, int nrOfElevators) {
		this.numberFloors = nrOfFloors;
		this.numberElevators = nrOfElevators;
		instance = this;
		init();
		lastNPCEmotionTick = System.currentTimeMillis();
	}
	
	/**
	 * Initializes gameModel and gameView
	 * @return
	 */
	private void init() {
		gameModel = new GameModel(numberFloors, numberElevators, defaultElevatorSpeed, defaultElevatorCapacity,
				defaultFloorCapacity);
		gameView = new GameView(gameModel);
		exec_began = System.nanoTime();
	}
	
	/**
	 * Starts the game, looping untill it ends
	 */
	public void start(){
		new Thread(new Runnable() {
	        public void run() {
	            while (!gameModel.isGameOver()) {
	            	tick();	
	            }
	            
	            gameStatus = "Game over!";
	        }
	    }).start();
	}
	
	/**
	+----------------------+
	|                      |
	|   Getters/Setters    |
	|                      |
	+----------------------+
	*/
	
	/**
	 * Returns the singleton Controller instance
	 * @return
	 */
	public static Controller getInstance() {
		return instance;
	}
	
	/**
	 * Sets the elevator capacity
	 * @param elevatorCapacity
	 * @return
	 */
	public Controller setDefaultElevatorCapacity(int elevatorCapacity) {
		this.defaultElevatorCapacity = elevatorCapacity;
		return this;
	}
	
	/**
	 * Sets the default floor capacity
	 * @param floorCapacity
	 * @return
	 */
	public Controller setDefaultFloorCapacity(int floorCapacity) {
		this.defaultFloorCapacity = floorCapacity;
		return this;
	}
	
	/**
	 * Sets the elevator speed
	 * @param elevatorSpeed
	 * @return
	 */
	public Controller setDefaultElevatorSpeed(int elevatorSpeed) {
		this.defaultElevatorSpeed = elevatorSpeed;
		return this;
	}
	
	/**
	 * Returns this game GameView
	 * @return
	 */
	public GameView getGameView() {
		return gameView;
	}
	
	/**
	 * Returns the latest game event error upon user action
	 * @return
	 */
	public String getLatestErrorMessage() {
		return gameStatus;
	}
	
	/**
	 * Returns the game title
	 * @return
	 */
	public static String getFormsTitle() {
		return "Liftimulator";
	}
	
	/**
	 * Returns the gameModel singleton
	 * @return
	 */
	public GameModel getGameModel() {
		return gameModel;
	}
		
	/**
	 * Returns all NPCs across elevators and floors
	 * @return
	 */
	public Set<NPCModel> getAllNPCs() {

		Set<NPCModel> ret = new HashSet<NPCModel>();

		// Elevators
		for (ElevatorModel e : gameModel.getUserElevators()) {
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
	
	/**
	 * Returns the FloorModel by floor number
	 * @param i
	 * @return
	 */
	public FloorModel getFloorByNumber(int i) {
		return this.getGameModel().getFloors().get(i);
	}
	
	/**
	+----------------------+
	|                      |
	|     Game Engine
	|                      |
	+----------------------+
	*/
	
	/**
	 * Calculates approximately at which Y coordinate a specific floor starts
	 * @param floorNr
	 * @return
	 */
	private int getFloorCoordinates(int floorNr) {
		return (gameView.getHeight()/this.numberFloors) * (this.numberFloors - floorNr);
	}
	
	/**
	 * Determines if a given elevator arrived on a specific floor
	 * @param e
	 * @param floorNr
	 * @return
	 */
	private boolean isElevatorOnFloor(ElevatorModel e, int floorNr) {
		int floorY = getFloorCoordinates(floorNr);
		return (Math.abs(e.getPosY() - (gameView.getHeight() - floorY)) <= 5);
	}
	
	/**
     * Determine if a given elevator has arrived the destination
     * @param e
     * @return
     */
    private boolean hasElevatorArrived(ElevatorModel e) {
        return isElevatorOnFloor(e, e.getDestinationFloor());
    }
	
    /**
     * Updates the game score based on NPC emotions
     * @param emotion
     */
    private void updateScore(NPCEmotion emotion) {
    	int base_score = 0;
    	switch (emotion) {
		case Smiling:
			base_score = 2000;
			break;
		case SlightlySmiling:
			base_score = 1750;
			break;
		case Neutral:
			base_score = 1000;
			break;
		case Thinking:
			base_score = 700;
			break;
		case Bored:
			base_score = 300;
			break;
		case Sleepy:
			base_score = 200;
			break;
		case SlightlyAngry:
			base_score = 100;
			break;
		case Angry:
			base_score = 50;
			break;
		default:
			break;
		}
    	
    	gameModel.addScore(base_score*numberFloors/10);
    }
    
	/**
	 * Make NPCs that are in an elevator which is the the NPC's desired floor
	 * disappear.
	 */
	private void emptyElevator(ElevatorModel e) {
		Iterator<NPCModel> it = e.getNpcs().iterator();
		
		while(it.hasNext()) {
			NPCModel n = it.next();
			if (this.getFloorByNumber(n.getDestinationFloor()) == e.getCurrFloor()) {
				if(e.isUserControllable())
					updateScore(n.getEmotionalLevel());
				it.remove();
			}
		}
	}
	
	private void moveElevator(ElevatorModel e, double delta) {
		int speedFactor = 40;
		if(e.isMoving()) {
			if(getFloorCoordinates(e.getDestinationFloor()) > (gameView.getHeight() - e.getPosY()))
				e.setPosY(e.getPosY() - (int)(e.getSpeed()*speedFactor*delta/1000));
			else 
				e.setPosY(e.getPosY() + (int)(e.getSpeed()*speedFactor*delta/1000));
		}
	}
	
	/**
	 * Randomly generates NPCs that arrive on floors
	 * 
	 * @param delta
	 */
	private void generateRandomNPCs() {
		long currTime = System.nanoTime();
		double timeSpent = (double) ((currTime - exec_began) / 1e9);

		if (addedNPCs < 10) {
			// heur 1
			if (timeSpent / 5 > addedNPCs) {
				makeNPC();

			}
		} else if (addedNPCs < 30) {
			// heur 2
			if (timeSpent / 3.5 > addedNPCs) {
				makeNPC();
			}
		}else {
			
			if (timeSpent / 2.5 > addedNPCs) {
				makeNPC();
			}
		}
		
		if (addedNPCs == 30) {
			for(ElevatorModel e : this.gameModel.getUserElevators()) {
				e.setSpeed(4);
			}
			for(ElevatorModel e : this.gameModel.getBotElevators()) {
				e.setSpeed(4);
			}
			
			NPCEmotionTickDelta = 3000;
		}
		else if (addedNPCs == 60) {
			for(ElevatorModel e : this.gameModel.getUserElevators()) {
				e.setSpeed(5);
			}
			for(ElevatorModel e : this.gameModel.getBotElevators()) {
				e.setSpeed(5);
			}
			
			NPCEmotionTickDelta = 2000;
		}
	}

	private void makeNPC() {
		int floorMax = this.gameModel.getFloorCount();
		int floorFrom = rnd2.nextInt(floorMax);
		int floorTo = rnd2.nextInt(floorMax);

		try {
			NPCModel n = new NPCModel(floorFrom, floorTo);
			this.gameModel.getFloors().get(floorFrom).addNPC(n);
			addedNPCs++;
		} catch (Exception e) {
			// do nothing
		}
	}


	
	/**
	 * Ticks an elevator, updating it's position and state
	 * @param elevator Reference to elevator's model
	 * @param delta Milliseconds since last tick
	 * @return
	 */
	private void tickUserElevator(ElevatorModel e, double delta) {
		if(e.getState() == ElevatorStates.MOVING) {
			// elevator is moving, update its coordinates
			moveElevator(e, delta);
			
			// update state, by checking if elevator arrived destination
			if(hasElevatorArrived(e)) {
				e.toggleState();
				e.setPosY(this.gameView.getHeight()*(1-1/numberFloors) - getFloorCoordinates(e.getDestinationFloor()) - 1);
				e.setFloor(getFloorByNumber(e.getDestinationFloor()));
	
			}
		} else {
			// elevator is stopped on some floor. Let the NPCs exit, if there's any to exit on this floor
			emptyElevator(e);
		}
	}
	
	/**
	 * If an auto elevator is not moving, it sets a new trajectory 
	 * Heuristic:
	 * Look for the floor with more awaiting NPCs and go there
	 * On the way, 
	 * @param e
	 */
	private void tickBotElevator(ElevatorBotModel e, double delta) {
		if(e.getState() == ElevatorStates.MOVING) {
			// elevator is moving, update its coordinates
			moveElevator(e, delta);
			
			// update state, by checking if elevator arrived destination
			if(hasElevatorArrived(e)) {
				e.toggleState();
				e.setPosY(this.gameView.getHeight()*(1-1/numberFloors) - getFloorCoordinates(e.getDestinationFloor()) - 1);
				e.setFloor(getFloorByNumber(e.getDestinationFloor()));
			}
			
			// Move desired NPCs to the elevator
			ArrayList<NPCModel> copy = new ArrayList<NPCModel>(e.getCurrFloor().getNpcs());
			Iterator<NPCModel> it = copy.iterator();
			while(it.hasNext()) {
				NPCModel npc = it.next();
				if(e.getGoalFloorNr() > e.getDestinationFloor()) {
					if(npc.getDestinationFloor() <= e.getGoalFloorNr());
						moveNPC2Elevator(npc, e);
				} else {
					if(npc.getDestinationFloor() >= e.getGoalFloorNr());
						moveNPC2Elevator(npc, e);
				}
			}
			
		} else {
			// elevator is stopped on some floor. Let the NPCs exit, if there's any to exit on this floor
			emptyElevator(e);
			
			// check if destination floor is the goal floor. If so, update trajectory
			if(e.getGoalFloorNr() == e.getDestinationFloor()) {
				// elevator finished it's initial goal, set a new trajectory			
				ArrayList<Integer> floors = gameModel.getFullestFloors();
				
				e.setGoalFloorNr(floors.get(0));
			}
			// else, update the destination floor accordingly
			else {
				e.toggleState();
				if(getFloorCoordinates(e.getGoalFloorNr()) > (gameView.getHeight() - e.getPosY()))
					e.setDestinationFloor(e.getDestinationFloor() - 1);
				else 
					e.setDestinationFloor(e.getDestinationFloor() + 1);
				
				//System.out.println("goal:" + e.getGoalFloorNr() + " | destiny: " + e.getDestinationFloor());
			}
		}
	}
	
	/**
	 * Updates all awaiting NPCs emotion
	 */
	private void tickNPCEmotions() {
		long currTime = System.currentTimeMillis();
		
		for (FloorModel f : gameModel.getFloors()) {
			Iterator<NPCModel> it = f.getNpcs().iterator();
			while(it.hasNext()) {
				NPCModel n = it.next();
				if((currTime - n.getLastEmotionTick()) > NPCEmotionTickDelta) {
					n.setNextEmotionalLevel();
					
					if(n.hasGaveUpWaiting()) {
						it.remove();
						gameModel.decreaseLivesLeft();
						gameStatus = "Ouch! A NPC just left the building. " + gameModel.getLivesLeft() + " lives left!";
					}
				}
			}
		}
	}
	
	/**
	 * Iterates the game, updating all data
	 */
	private void tick() {
		if(lastTick != -1) {
			long thisTick = System.nanoTime();
			double delta = (thisTick - lastTick)/1e6; // miliseconds since last tick
			if(delta < 50)
				return;
			
			// Update elevators
			for(ElevatorModel elevator : gameModel.getUserElevators()) {
				tickUserElevator(elevator, delta);
			}
			
			for(ElevatorBotModel elevator : gameModel.getBotElevators()) {
				tickBotElevator(elevator, delta);
			}
			
			// Add random NPCs
			generateRandomNPCs();
			
			// Update NPCs emotions every 2 seconds
			if(System.currentTimeMillis() - lastNPCEmotionTick > 2000) {
				tickNPCEmotions();
				lastNPCEmotionTick = System.currentTimeMillis();
			}
			
			lastTick = thisTick;
			this.gameView.renderGameView();
		} else {
			lastTick = System.nanoTime();
			this.gameView.renderGameView();
		}
	}
	
	public NPCContainerModel searchNPC(NPCModel npc) {

		// Search in Elevators
		for (ElevatorModel e : gameModel.getUserElevators()) {
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
	
	private Boolean moveNPC2Elevator(NPCModel n, ElevatorModel e) {
		if (e.isMoving())
			return false;
		
		// check if the clicked NPC is on the same floor as the elevator
		if(!isElevatorOnFloor(e, n.getOriginFloor()))
			return false;
		
		FloorModel f = (FloorModel) this.searchNPC(n);
		
		
		if(!e.addNPC(n)) {
			gameStatus = "Elevator is full!";
			return false;
			}
		
		f.removeNPC(n);
		
		return true;
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
	
	/*
	+----------------------+
	|                      |
	|       Events
	|                      |
	+----------------------+
	*/
	
	/**
	 * 
	 * @param floorNr
	 * @param e
	 * @return
	 */
	public Boolean eventElevatorArrowClicked(int floorNr, ElevatorModel e) {
		if (e.isMoving()) {
			gameStatus = "You can't change elevator trajectory while it's moving!";
			return false;
		}
			
		
		e.setDestinationFloor(floorNr);
		e.toggleState();
		return true;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public Boolean eventNPCClicked(NPCModel n) throws Exception {
		if (n.getLocation() == NPCLocation.LIFT) {
			return moveNPC2Floor(n);
		} else if (n.getLocation() == NPCLocation.FLOOR) {
			return moveNPC2Elevator(n, this.gameModel.getUserElevators().get(0));
		} else {
			throw new Exception("The clicked NPC had no associated location.");
		}
	}
}
