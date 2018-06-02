package model.entities;

public enum ElevatorStates {
	STOPPED, // initial state, elevator stopped at some floor waiting for user input
	MOVING // elevator is moving to destination floor
}
