package model.entities;

public class FloorModel extends NPCContainerModel {

	/**
	 * Constructor
	 * 
	 * @param capacity
	 *            Number of people who can be waiting for the elevator on this floor
	 * @throws IllegalArgumentException
	 *             If the capacity is invalid. Must be higher or equal to 1
	 */
	public FloorModel(BuildingModel building, int capacity) throws IllegalArgumentException {
		super(building);
		this.setCapacity(capacity);
	}

	@Override
	public void setCapacity(int cap) {
		if (cap < 2)
			throw new IllegalArgumentException("capacity must be 2 or more.");
		this.capacity = cap;
	}

	private int number;

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "FLoor " + number + " [npcs=" + npcs + ", capacity=" + capacity + "]";
	}
	
	/**
	 * Adds a NPC inside the floor and updates the NPC location
	 * @see model.NPCContainer#addNPC(NPC)
	 * @return True if the NPC was added, false otherwise
	 */
	@Override
	public boolean addNPC(NPCModel npc) {
		if(super.addNPC(npc)) {
			// npc was added
			npc.setLocation(NPCLocation.FLOOR);
			return true;
		}

		return false;		
	}
}
