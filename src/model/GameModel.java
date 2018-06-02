package model;

import java.util.List;

import model.entities.*;

public class GameModel {
	
	/**
	 * Singleton. There's only one GameModel instance
	 */
	private static GameModel gameModel;
	
	/**
	 * The building model
	 */
	private BuildingModel buildingModel;
	
	/**
	 * Constructor
	 */
	public GameModel(int nrOfFloors, int nrOfElevators, int defaultElevatorSpeed, int defaultElevatorCap,
			int defaultFloorCap) {
		
		buildingModel = new BuildingModel(nrOfFloors, nrOfElevators, defaultElevatorSpeed, defaultElevatorCap, defaultFloorCap);
		
		gameModel = this;
	}
	
	/**
	 * @return All building floors
	 */
	public List<FloorModel> getFloors() {
		return buildingModel.getFloors();
	}
	
	/**
	 * @return Number of floors on this building
	 */
	public int getNumberFloors() {
		return buildingModel.getFloorCount();
	}
	
	/**
	 * @return All building elevators
	 */
	public List<ElevatorModel> getElevators() {
		return buildingModel.getElevators();
	}
	
	/**
	 * @return The game model instance, if any
	 */
	public static GameModel getInstance() {
		return gameModel;
	}

	public int getFloorCount() {
		return buildingModel.getFloorCount();
	}
}
