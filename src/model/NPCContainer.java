package model;

import java.util.ArrayList;
import java.util.List;

public abstract class NPCContainer {
	/**
	 * List of NPCs on this container
	 */
	List<NPC> npcs = new ArrayList<NPC>();
	
	/**
	 * Maximum number of NPCs this container can hold
	 */
	int capacity;
	private Building b;

	/**
	 * 
	 * @return The container capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Updates the capacity
	 * @param cap New capacity
	 */
	public abstract void setCapacity(int cap);
	
	/**
	 *
	 * @return List of NPCs inside the container
	 */
	public List<NPC> getNpcs() {
		return npcs;
	}

	public NPCContainer(Building building) {
		this.b = building;
	}

	
	/**
	 * 
	 * @return Number of NPCs inside the container
	 */
	public int getFillCount() {
		return npcs.size();
	}

	/**
	 * 
	 * @return Number of free spots remaining
	 */
	public int getEmptyCount() {
		return this.capacity - this.getFillCount();
	}
	
	/**
	 * Adds a NPC to the container
	 * @param npc
	 * @return True if it NPC was added successfully. False otherwise
	 */
	public boolean addNPC(NPC npc) {
		// System.out.println("Adding npc to container with " + this.getEmptyCount() + "
		// empty spaces.");
		if (this.getEmptyCount() < 1 || this.npcExists(npc)) {
			return false;
		} else {
			checkNpcDestinationFloor(npc);
			checkNpcOriginFloor(npc);
			npcs.add(npc);
			return true;

		}
	}

	/**
	 * @param npc
	 */
	private void checkNpcDestinationFloor(NPC npc) {
		if (this.b.getFloorCount() <= npc.getDestinationFloor() || npc.getDestinationFloor() < 0) {
			throw new IllegalArgumentException("the npc's dest floor does not exist in the container's building.");
		}
	}

	/**
	 * @param npc
	 */
	private void checkNpcOriginFloor(NPC npc) {
		if (this.b.getFloorCount() <= npc.getOriginFloor() || npc.getOriginFloor() < 0) {
			throw new IllegalArgumentException("the npc's origin floor does not exist in the container's building.");
		}
	}

	
	/**
	 * Check if some NPC is inside this container
	 * @param npc
	 * @return True if NPC exists inside the container. False otherwise
	 */
	private boolean npcExists(NPC npc) {
		for (NPC n : this.npcs) {
			if (n.equals(npc))
				return true;
		}
		return false;
	}
	
	/**
	 * Removes a NPC from the container
	 * @param npc
	 * @return True if the NPC was removed, false if the specified NPC doesn't exist on the container
	 */
	public boolean removeNPC(NPC npc) {
		return npcs.remove(npc);
	}

}