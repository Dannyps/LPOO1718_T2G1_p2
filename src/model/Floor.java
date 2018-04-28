package model;

import java.util.*; 

public class Floor {
	private int capacity;
	private List<NPC> queue;
	
	/**
	 * Constructor
	 * @param capacity Number of people who can be waiting for the elevator on this floor
	 * @throws IllegalArgumentException If the capacity is invalid. Must be higher or equal to 1
	 */
	public Floor(int capacity) throws IllegalArgumentException {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity must be 1 or more.");
		
		this.capacity = capacity;
		this.queue = new ArrayList<NPC>(capacity);
	}
	
	/**
	 * Adds a new character waiting for the lift
	 * @param npc
	 * @throws Exception
	 */
	public void pushCharacter(NPC npc) throws Exception {
		if(this.capacity == this.queue.size()) {
			
		}
		else {
			this.queue.add(npc);
		}
	}
	
	
	public void popCharacter() {
		
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity must be 1 or more.");
		
		this.capacity = capacity;
	}
}


