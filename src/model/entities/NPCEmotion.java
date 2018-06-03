package model.entities;

public enum NPCEmotion {
	Smiling,
	SlightlySmiling,
	Neutral,
	Thinking,
	Bored,
	Sleepy,
	SlightlyAngry,
	Angry;
	
	/**
	 * 
	 * @return Based on the current state returns the next state. On the last state, null is returned because there's no next state
	 */
	public NPCEmotion getNextLevel() {
		switch(this) {
		case Smiling:
			return SlightlySmiling;
		case SlightlySmiling:
			return Neutral;
		case Neutral:
			return Thinking;
		case Thinking:
			return Bored;
		case Bored:
			return Sleepy;
		case Sleepy:
			return SlightlyAngry;
		case SlightlyAngry:
			return Angry;
		default :
			return null;
		}
		
	}
}
