package model;

public class NPC {
	private static final int EMOTION_TICKS = 1000;

	public NPC(int originFloor, int destinationFLoor) {
		
		if (destinationFLoor == originFloor) {
			throw new IllegalArgumentException("origin floor and destination floor cannot be the same.");
		}

		this.destinationFloor = destinationFLoor;
		this.originFloor = originFloor;
	}

	public NPC() {

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

	private int originFloor;
	private int destinationFloor;
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
