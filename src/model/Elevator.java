package model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

	List<NPC> npcs = new ArrayList<NPC>();
	
	private int capacity;
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int cap) {
		if(cap<1)
			throw new IllegalArgumentException("capacity must be 1 or more.");
		this.capacity = cap;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if(speed <= 0 || speed >5)
			throw new IllegalArgumentException("speed must be between 1 and 5.");
		this.speed=speed;
	}

	public List<NPC> getNpcs() {
		return npcs;
	}

	private int speed;
	
	public Elevator(int cap, int speed) {
		this.setCapacity(cap);
		this.setSpeed(speed);
	}
	
	public int getFillCount() {
		return npcs.size();
	}
	
	public int getEmptyCount(){
		return this.capacity-this.getFillCount();
	}
	
	public boolean addNPC(NPC npc){
		if(this.getEmptyCount()<1 || this.npcExists(npc)) {
			return false;
		}else {
			npcs.add(npc);
			return true;
		}
	}
	
	private boolean npcExists(NPC npc) {
		for(NPC n : this.npcs) {
			if(n.equals(npc))
				return true;
		}
		return false;
	}

	public boolean removeNPC(NPC npc) {
		return npcs.remove(npc);
	}
}
