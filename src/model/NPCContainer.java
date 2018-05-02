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
	
	/**
	 * Constructor
	 */
	public NPCContainer() {
		super();
	}
	
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
		if (this.getEmptyCount() < 1 || this.npcExists(npc)) {
			return false;
		} else {
			npcs.add(npc);
			return true;
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