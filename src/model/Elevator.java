package model;

public class Elevator extends NPCContainer {
	
	private int speed;
	
	/**
	 * Constructor
	 * @param cap This elevator capacity (maximum number of NPCs inside)
	 * @param speed This elevator base speed
	 */
	public Elevator(Building building, int cap, int speed) {
		super(building);
		this.setCapacity(cap);
		this.setSpeed(speed);
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
	 * @see model.NPCContainer#addNPC(NPC)
	 * @return True if the NPC was added, false otherwise
	 */
	public boolean addNPC(NPC n) {
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
