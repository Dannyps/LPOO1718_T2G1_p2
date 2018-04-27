package model;

public class NPC {
	public int getDestinationFloor() {
		return destinationFloor;
	}
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	public int getEmotionalLevel() {
		return emotionalLevel;
	}
	public void setEmotionalLevel(int emotionalLevel) {
		this.emotionalLevel = emotionalLevel;
	}
	public NPCLocation getLocation() {
		return location;
	}
	public void setLocation(NPCLocation location) {
		this.location = location;
	}
	
	private int destinationFloor;
	private int emotionalLevel;
	private NPCLocation location;
	
}
