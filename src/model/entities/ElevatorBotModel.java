package model.entities;

public class ElevatorBotModel extends ElevatorModel {
	/*
	 *  The bot has a destination floor in mind, but it moves through floor by floor to pick NPCs which destination is before the destination floor.
	 *  That floor is the goalFloorNr, therefore the destinationFloorNr increments/decrements 1 unit at the time until it reaches the goal floor 
	 */
	private int goalFloorNr;
	private int awaitTicks = 0; // Ticks counter use to give user perception that a bot controllable elevator stops for a while on a given floor
	
	public ElevatorBotModel(BuildingModel building, int capacity, int speed) {
		super(building, capacity, speed);
	}
	
	@Override
	public boolean isUserControllable() {
		return false;
	}
	
	public int getGoalFloorNr() {
		return goalFloorNr;
	}
	
	public void setGoalFloorNr(int goal) {
		this.goalFloorNr = goal;
	}
	
	public void setAwaitTicks(int nrTicks) {
		this.awaitTicks = nrTicks;
	}
	
	public int getAwaitTicks() {
		return this.awaitTicks;
	}

}
