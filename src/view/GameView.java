package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.GameModel;
import model.entities.ElevatorModel;
import view.viewGameWindowComponents.ElevatorContainerView;
import view.viewGameWindowComponents.FloorView;

@SuppressWarnings("serial")
public class GameView extends JPanel {

	// A reference to gameModel
	private GameModel game;
	
	// some size preferences
	private static final int FLOOR_WIDTH_PREF = 300;
	private static final int FLOOR_HEIGHT_PREF = 50;
	private static final int FLOOR_WIDTH_MIN = 200;
	private static final int FLOOR_HEIGHT_MIN = 50;

	private static final int ELEVATOR_WIDTH_PREF = 100;
	private static final int ELEVATOR_HEIGHT_PREF = 150;
	private static final int ELEVATOR_WIDTH_MIN = 100;
	private static final int ELEVATOR_HEIGHT_MIN = 150;

	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public GameView(GameModel game) {
		this.game = game;

		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
	}

	/**
	 * It renders the game
	 */
	public void renderGameView() {
		this.removeAll();
		
		renderElevators();
		renderFloors();
		this.revalidate();

		
	}

	private void renderElevators() {
		for (int i = 0; i < game.getElevators().size(); i++) {
			ElevatorModel elevator = game.getElevators().get(i);

			// create elevator container view
			ElevatorContainerView elevatorContainerView = new ElevatorContainerView(game.getNumberFloors(), elevator);

			// set grid constraints
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = i;
			gbc.gridy = 0;
			gbc.gridheight = GridBagConstraints.REMAINDER;

			// set size
			elevatorContainerView.setMinimumSize(new Dimension(ELEVATOR_WIDTH_PREF, 500));
			elevatorContainerView.setPreferredSize(new Dimension(ELEVATOR_WIDTH_PREF, 500));

			// add
			this.add(elevatorContainerView, gbc);
		}

	}

	private void renderFloors() {
		// add floors
		for (int i = 0; i < game.getFloors().size(); i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = game.getElevators().size();
			gbc.gridy = i;
			FloorView floorView = new FloorView();
			floorView.setPreferredSize(new Dimension(FLOOR_WIDTH_PREF, FLOOR_HEIGHT_PREF));
			floorView.setMinimumSize(new Dimension(FLOOR_WIDTH_MIN, FLOOR_HEIGHT_MIN));
			add(floorView, gbc);
		}
	}
	
}
