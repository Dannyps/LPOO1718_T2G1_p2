package model;

import java.util.ArrayList;
import java.util.List;

public class Building {

	private List<Elevator> elevators = new ArrayList<Elevator>();

	List<Floor> floors = new ArrayList<Floor>();

	public Building(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap) {
		if(nrOfFloors<3) {
			throw new IllegalArgumentException("a building must have at least 3 floors");
		}
		
		if(nrOfElevators<1) {
			throw new IllegalArgumentException("a building must have at least 1 elevator");
		}
		
		addFloors(nrOfFloors);
		
		addElevators(nrOfElevators, defaultElevatorSpeed, defaultElevatorCap);
	}

	/**
	 * @param nrOfFloors
	 */
	private void addElevators(int n, int des, int dec) {
		for (int i = 0; i < n; i++) {
			getElevators().add(new Elevator(dec, des));
		}
	}

	/**
	 * @param nrOfFloors
	 */
	private void addFloors(int n) {
		for (int i = 0; i < n; i++) {
			floors.add(new Floor());
		}
	}

	/**
	 * 
	 * @return number of floors in this building
	 */
	public int getFloorCount() {
		return this.floors.size();
	}

	public List<Elevator> getElevators() {
		return elevators;
	}

}
