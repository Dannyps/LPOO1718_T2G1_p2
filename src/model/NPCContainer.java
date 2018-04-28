package model;

import java.util.ArrayList;
import java.util.List;

public abstract class NPCContainer {

	List<NPC> npcs = new ArrayList<NPC>();
	int capacity;
	
	public int getCapacity() {
		return capacity;
	}

	public abstract void setCapacity(int cap);

	public List<NPC> getNpcs() {
		return npcs;
	}

	public NPCContainer() {
		super();
	}

	public int getFillCount() {
		return npcs.size();
	}

	public int getEmptyCount() {
		return this.capacity - this.getFillCount();
	}

	public boolean addNPC(NPC npc) {
		if (this.getEmptyCount() < 1 || this.npcExists(npc)) {
			return false;
		} else {
			npcs.add(npc);
			return true;
		}
	}

	private boolean npcExists(NPC npc) {
		for (NPC n : this.npcs) {
			if (n.equals(npc))
				return true;
		}
		return false;
	}

	public boolean removeNPC(NPC npc) {
		return npcs.remove(npc);
	}

}