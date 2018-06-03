package model.entities;

public class NPCModel {
	
	private int originFloor = 0;
	private int destinationFloor = 1;
	private NPCEmotion emotionalLevel = NPCEmotion.Smiling;
	private NPCLocation location;
	private long lastEmotionTick;
	/**
	 * 
	 * @param originFloor
	 * @param destinationFloor
	 */
	public NPCModel(int originFloor, int destinationFloor) {

		if (destinationFloor == originFloor) {
			throw new IllegalArgumentException("origin floor and destination floor cannot be the same.");
		}

		this.destinationFloor = destinationFloor;
		this.originFloor = originFloor;
		lastEmotionTick = System.currentTimeMillis();
	}

	public NPCModel() {

	} // simple version for headless tests
	
	/**
	 * 
	 * @return
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}
	
	/**
	 * 
	 * @return
	 */
	public NPCEmotion getEmotionalLevel() {
		return emotionalLevel;
	}
	
	/**
	 * 
	 */
	public void setNextEmotionalLevel() {
		emotionalLevel = emotionalLevel.getNextLevel();
		lastEmotionTick = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @return
	 */
	public NPCLocation getLocation() {
		return location;
	}
	
	/**
	 * 
	 * @param location
	 */
	public void setLocation(NPCLocation location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getOriginFloor() {
		return originFloor;
	}

	public long getLastEmotionTick() {
		return lastEmotionTick;
	}	
}
