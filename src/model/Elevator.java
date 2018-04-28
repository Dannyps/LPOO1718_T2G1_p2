package model;

public class Elevator extends NPCContainer {

	public Elevator(int cap, int speed) {
		this.setCapacity(cap);
		this.setSpeed(speed);
	}

	private int speed;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if (speed <= 0 || speed > 5)
			throw new IllegalArgumentException("speed must be between 1 and 5.");
		this.speed = speed;
	}

	public boolean addNPC(NPC n) {
		if (super.addNPC(n)) {
			n.setLocation(NPCLocation.LIFT);
			return true;
		}
		return false;
	}

	@Override
	public void setCapacity(int cap) {
		if (cap < 1)
			throw new IllegalArgumentException("capacity must be 1 or more.");
		this.capacity = cap;
	}
}
