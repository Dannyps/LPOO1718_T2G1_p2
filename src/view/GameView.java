package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.GameModel;
import model.entities.ElevatorBotModel;
import model.entities.ElevatorModel;
import view.viewGameWindowComponents.ElevatorContainerView;
import view.viewGameWindowComponents.FloorView;

@SuppressWarnings({ "unused", "serial" })
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
		setBackground(new java.awt.Color(245, 222, 179));
		setLayout(gridBagLayout);
		setMinimumSize(new Dimension(500, 600));
	}

	/**
	 * It renders the game
	 */
	public void renderGameView() {
		
		this.removeAll();
		
		renderElevators();
		renderFloors();
		
		this.repaint();
		this.revalidate();

		
	}

	private void renderElevators() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;
		
		int i = 0;
		
		for (; i < game.getBotElevators().size(); i++) {
			ElevatorBotModel elevator = game.getBotElevators().get(i);
			// create elevator container view
			ElevatorContainerView elevatorContainerView = new ElevatorContainerView(game.getNumberFloors(), elevator);

			// set grid constraints
			gbc.gridx = i;
			
			// set size
			elevatorContainerView.setMinimumSize(new Dimension(ELEVATOR_WIDTH_PREF, 600));
			elevatorContainerView.setPreferredSize(new Dimension(ELEVATOR_WIDTH_PREF, this.getHeight()));

			// add
			this.add(elevatorContainerView, gbc);
		}
		
		for (int j = 0; j < game.getUserElevators().size(); j++) {
			ElevatorModel elevator = game.getUserElevators().get(j);
			// create elevator container view
			ElevatorContainerView elevatorContainerView = new ElevatorContainerView(game.getNumberFloors(), elevator);

			// set grid constraints
			gbc.gridx = i + j;
			
			// set size
			elevatorContainerView.setMinimumSize(new Dimension(ELEVATOR_WIDTH_PREF, 600));
			elevatorContainerView.setPreferredSize(new Dimension(ELEVATOR_WIDTH_PREF, this.getHeight()));

			// add
			this.add(elevatorContainerView, gbc);
		}

	}

	private void renderFloors() {
		// add floors
		for (int i = 0; i < game.getFloors().size(); i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = game.getUserElevators().size() + game.getBotElevators().size();
			gbc.gridy = game.getFloors().size() - i - 1;
			gbc.fill = GridBagConstraints.VERTICAL;
			FloorView floorView = new FloorView(game.getFloors().get(i));
			floorView.setPreferredSize(new Dimension(FLOOR_WIDTH_PREF, this.getHeight()/game.getFloors().size()));
			floorView.setMinimumSize(new Dimension(FLOOR_WIDTH_MIN, FLOOR_HEIGHT_MIN));
			add(floorView, gbc);
		}
	}
	
}
