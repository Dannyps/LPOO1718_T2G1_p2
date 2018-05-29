package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.entities.BuildingModel;
import model.entities.ElevatorModel;
import view.viewGameWindowComponents.ElevatorView;

@SuppressWarnings("serial")
public class GameView extends JPanel {
	// A reference to building model
	private BuildingModel b;
	
	public GameView(BuildingModel b) {
		this.b = b;
	}
	
	public void renderGameView() {
		for(ElevatorModel elevator : b.getElevators()) {
			
		}
	}
	
	private void renderElevator() {
		JPanel elevatorColumn = new JPanel();
		
	}
	
	private void renderFloors() {
		
	}
}
