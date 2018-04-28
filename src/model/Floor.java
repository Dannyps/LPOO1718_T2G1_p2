package model;

public class Floor {
	private int capacity;

	public Floor(int cap) {
		this.setCapacity(cap);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("capacity must be 1 or more.");
		this.capacity = capacity;
	}
}
