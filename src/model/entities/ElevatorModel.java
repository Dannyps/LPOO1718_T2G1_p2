package model.entities;

public class ElevatorModel extends NPCContainerModel {
	private int posY; // the vertical position
	private int speed; // the current speed
	private ElevatorStates state = ElevatorStates.MOVING;
	private int floorDest; // if elevator is moving, this represents the destination floor number
	/**
	 * Constructor
	 * 
	 * @param capacity
	 *            This elevator capacity (maximum number of NPCs inside)
	 * @param speed
	 *            This elevator base speed
	 */
	public ElevatorModel(BuildingModel building, int capacity, int speed) {
		super(building);
		this.setCapacity(capacity);
		this.setSpeed(speed);
		this.currFloor = building.getFloors().get(0); // elevators start at floor 0.
	}

	/**
	 * 
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return The elevator base speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Updates the elevator base speed
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		if (speed <= 0 || speed > 5)
			throw new IllegalArgumentException("speed must be between 1 and 5.");
		this.speed = speed;
	}
	
	
	/**
	 * 
	 * @return The current elevator state
	 */
	public ElevatorStates getState() {
		return state;
	}
	
	/**
	 * Sets the elevator new state
	 * @param state
	 */
	public void setState(ElevatorStates state) {
		this.state = state;
	}
	
	/**
	 * Toggles the elevator state. If it was moving now is stopped, and vice-versa
	 */
	public void toggleState() {
		if(this.state == ElevatorStates.MOVING)
			this.state = ElevatorStates.STOPPED;
		else 
			this.state = ElevatorStates.MOVING;
	}

	/**
	 * Adds a NPC inside the elevator and updates the NPC location
	 * 
	 * @see model.entities.NPCContainerModel#addNPC(NPCModel)
	 * @return True if the NPC was added, false otherwise
	 */
	public boolean addNPC(NPCModel n) {
		if (super.addNPC(n)) {
			n.setLocation(NPCLocation.LIFT);
			return true;
		}
		return false;
	}

	/**
	 * Updates this elevator capacity (maximum number of NPCs inside)
	 */
	@Override
	public void setCapacity(int cap) {
		if (cap < 1)
			throw new IllegalArgumentException("capacity must be 1 or more.");
		this.capacity = cap;
	}

	public boolean isMoving() {
		return (currFloor == null);

	}

	private FloorModel currFloor = null;

	public FloorModel getCurrFloor() {
		return currFloor;
	}

	@Override
	public String toString() {
		return "Elevator at " + currFloor.getNumber() + " [npcs=" + npcs + ", capacity=" + capacity + "]";
	}

	public void setMoving() {
		this.currFloor = null;
	}

	public void setFloor(FloorModel floor) {
		this.currFloor = floor;

	}

}
