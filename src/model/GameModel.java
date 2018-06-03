package model;

import java.util.ArrayList;
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
	 * @return All user controllable elevators
	 */
	public List<ElevatorModel> getUserElevators() {
		return buildingModel.getUserElevators();
	}
	
	/**
	 * @return All bot controllable elevators
	 */
	public List<ElevatorBotModel> getBotElevators() {
		return buildingModel.getBotElevators();
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
	
	/**
	 * Returns a list with the fullest floors on the building
	 * @return
	 */
	public ArrayList<Integer> getFullestFloors() {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(0);
		int fillCount = 0;
		for (FloorModel floorModel : buildingModel.getFloors()) {
			if(floorModel.getFillCount() > fillCount) {
				lst.clear();
				fillCount = floorModel.getFillCount();
				lst.add(floorModel.getNumber());
			} else if (floorModel.getFillCount() > fillCount) {
				lst.add(floorModel.getNumber());
			}
		}
		return lst;
	}
}
