package model.entities;

public class NPCModel {
	// information related with floors and location
	private int originFloor = 0;
	private int destinationFloor = 1;
	private NPCLocation location;
	
	// information related with NPC emotions
	private NPCEmotion emotionalLevel = NPCEmotion.Smiling;
	private long lastEmotionTick;
	private boolean gaveUpWaiting = false;
	
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
		NPCEmotion newEmotion = emotionalLevel.getNextLevel();
		if(newEmotion == null)
			gaveUpWaiting = true;
		else 
			emotionalLevel = newEmotion;
		
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
	
	public boolean hasGaveUpWaiting() {
		return gaveUpWaiting;
	}
}
