package model.entities;

public class NPCModel {
	private static final int EMOTION_TICKS = 1000;

	public NPCModel(int originFloor, int destinationFloor) {

		if (destinationFloor == originFloor) {
			throw new IllegalArgumentException("origin floor and destination floor cannot be the same.");
		}

		this.destinationFloor = destinationFloor;
		this.originFloor = originFloor;
	}

	public NPCModel() {

	} // simple version for headless tests

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public int getEmotionalLevel() {
		return emotionalLevel;
	}

	public NPCLocation getLocation() {
		return location;
	}

	public void setLocation(NPCLocation location) {
		this.location = location;
	}

	public int getOriginFloor() {
		return originFloor;
	}

	private int originFloor = 0;
	private int destinationFloor = 1;
	private int emotionalLevel = 1;
	private NPCLocation location;
	private int emotionTicker = EMOTION_TICKS;

	public void tick() {
		if (this.emotionTicker-- < 0) {
			this.emotionTicker = EMOTION_TICKS;
			this.emotionalLevel++;
		}
	}

	public int getEmotionTicker() {
		return emotionTicker;
	}

}
