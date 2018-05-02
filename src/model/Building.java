package model;

import java.util.ArrayList;
import java.util.List;

public class Building {
	/**
	 * List of elevators. A building can have more than one elevator
	 */
	private List<Elevator> elevators = new ArrayList<Elevator>();
	
	/**
	 * List of floors on this building. Every building has at least 3 floors
	 */
	private List<Floor> floors = new ArrayList<Floor>();
	
	/**
	 * Constructor
	 * @param nrOfFloors Number of floors (>= 3)
	 * @param nrOfElevators Number of buildings (>= 1)
	 * @param defaultElevatorSpeed The elevator base speed (number of units the elevator moves per tick)
	 * @param defaultElevatorCap The elevators capacity (maximum number of NPCs inside the elevator)
	 * @param defaultFloorCap The floors capacity (maximum number of NPCs that can be waiting for the elevator on each floor)
	 */
	public Building(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap,
			int defaultFloorCap) {
		if (nrOfFloors < 3) {
			throw new IllegalArgumentException("a building must have at least 3 floors");
		}

		if (nrOfElevators < 1) {
			throw new IllegalArgumentException("a building must have at least 1 elevator");
		}

		addFloors(nrOfFloors, defaultFloorCap);

		addElevators(nrOfElevators, defaultElevatorSpeed, defaultElevatorCap);
	}
	
	/**
	 * Constructor (default floor capacity is 4)
	 * @see Building(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap,
			int defaultFloorCap)
	 */
	public Building(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap) {
		this(nrOfFloors, nrOfElevators, defaultElevatorSpeed, defaultElevatorCap, 4);
	}

	/**
	 * Adds elevators to this building
	 * @param n Number of elevators
	 * @param capacity Maximum number of NPCs that can be inside the elevator
	 * @param speed Base speed of the elevator
	 */
	private void addElevators(int n, int capacity, int speed) {
		for (int i = 0; i < n; i++) {
			getElevators().add(new Elevator(capacity, speed));
		}
	}

	/**
	 * Adds floors to this building
	 * @param n
	 * @param capacity Maximum number of NPCs that can be waiting for the elevator on each floor
	 */
	private void addFloors(int n, int capacity) {
		for (int i = 0; i < n; i++) {
			floors.add(new Floor(capacity));
		}
	}

	/**
	 * @return Number of floors on this building
	 */
	public int getFloorCount() {
		return this.floors.size();
	}
	
	/**
	 * @return The elevators of this building
	 */
	public List<Elevator> getElevators() {
		return elevators;
	}

}
