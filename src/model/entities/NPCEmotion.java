package model.entities;

public enum NPCEmotion {
	Smiling,
	SlightlySmiling,
	Neutral,
	Thinking,
	Expressionless,
	Unamused,
	SlightlyFrowning,
	Frowning;
	
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
			return Expressionless;
		case Expressionless:
			return Unamused;
		case Unamused:
			return SlightlyFrowning;
		case SlightlyFrowning:
			return Frowning;
		default :
			return null;
		}
		
	}
}
