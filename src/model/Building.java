package model;

import java.util.ArrayList;
import java.util.List;

public class Building {

	List<Elevator> elevators = new ArrayList<Elevator>();

	List<Floor> floors = new ArrayList<Floor>();

	public Building(int nrOfFloors) {
		addFloors(nrOfFloors);
	}

	/**
	 * @param nrOfFloors
	 */
	private void addFloors(int n) {
		for (int i = 0; i < n; i++) {
			floors.add(new Floor());
		}
	}
}
