package model.entities;

public class ElevatorModel extends NPCContainerModel {
	private int posY; // the vertical position
	private int speed; // the current speed
	
	/**
	 * Constructor
	 * @param capacity This elevator capacity (maximum number of NPCs inside)
	 * @param speed This elevator base speed
	 */
	public ElevatorModel(BuildingModel building, int capacity, int speed) {
		super(building);
		this.setCapacity(capacity);
		this.setSpeed(speed);
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
	 * @param speed
	 */
	public void setSpeed(int speed) {
		if (speed <= 0 || speed > 5)
			throw new IllegalArgumentException("speed must be between 1 and 5.");
		this.speed = speed;
	}
	
	/**
	 * Adds a NPC inside the elevator and updates the NPC location
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
	
	
}