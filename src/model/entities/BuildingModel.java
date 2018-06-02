package model.entities;

import java.util.ArrayList;
import java.util.List;

public class BuildingModel {
	/**
	 * List of elevators. A building can have more than one elevator
	 */
	private List<ElevatorModel> elevators = new ArrayList<ElevatorModel>();
	
	/**
	 * List of floors on this building. Every building has at least 3 floors
	 */
	private List<FloorModel> floors = new ArrayList<FloorModel>();
	
	/**
	 * Constructor
	 * @param nrOfFloors Number of floors (>= 3)
	 * @param nrOfElevators Number of buildings (>= 1)
	 * @param defaultElevatorSpeed The elevator base speed (number of units the elevator moves per tick)
	 * @param defaultElevatorCap The elevators capacity (maximum number of NPCs inside the elevator)
	 * @param defaultFloorCap The floors capacity (maximum number of NPCs that can be waiting for the elevator on each floor)
	 */
	public BuildingModel(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap,
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
	 * Simple Constructor for tests
	 */
	public BuildingModel() {
		
		addFloors(4, 3);
		addElevators(1, 2, 3);
	}
	
	/**
	 * Constructor (default floor capacity is 4)
	 * @see Building(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap,
			int defaultFloorCap)
	 */
	public BuildingModel(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap) {
		this(nrOfFloors, nrOfElevators, defaultElevatorSpeed, defaultElevatorCap, 4);
	}

	/**
	 * Adds elevators to this building
	 * @param n Number of elevators
	 * @param capacity Maximum number of NPCs that can be inside the elevator
	 * @param speed Base speed of the elevator
	 */
	private void addElevators(int n, int speed, int capacity) {
		for (int i = 0; i < n; i++) {
			getElevators().add(new ElevatorModel(this, capacity, speed));
		}
	}

	/**
	 * Adds floors to this building
	 * @param n
	 * @param capacity Maximum number of NPCs that can be waiting for the elevator on each floor
	 */
	private void addFloors(int n, int capacity) {
		for (int i = 0; i < n; i++) {
			floors.add(new FloorModel(this, capacity, i));
		}
	}
	
	/**
	 * @return Number of floors on this building
	 */
	public int getFloorCount() {
		return this.floors.size();
	}
	
	/**
	 * @return This building's floors
	 */
	public List<FloorModel> getFloors() {
		return floors;
	}
	
	/**
	 * @return This building's elevators
	 */
	public List<ElevatorModel> getElevators() {
		return elevators;
	}

}
