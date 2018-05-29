package view.viewGameWindowComponents;

import javax.swing.JPanel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class BuildingView extends JPanel {
	// references to all 
	List<FloorView> floors = new ArrayList<FloorView>();
	List<JPanel> elevatorCells = new ArrayList<JPanel>();
	ElevatorView elevator;
	
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
	 * Create the panel.
	 */
	public BuildingView(int numFloors) {
		
		// setup layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(FLOOR_WIDTH_PREF + ELEVATOR_WIDTH_PREF, FLOOR_HEIGHT_PREF + ELEVATOR_HEIGHT_PREF));
		setMinimumSize(new Dimension(FLOOR_WIDTH_MIN + ELEVATOR_WIDTH_MIN, FLOOR_HEIGHT_MIN + ELEVATOR_HEIGHT_MIN));
		
		// add floors
		for(int i = 0; i < numFloors; i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = i;
			FloorView vFloor = new FloorView();
			floors.add(i, vFloor);
			vFloor.setPreferredSize(new Dimension(FLOOR_WIDTH_PREF, FLOOR_HEIGHT_PREF));
			vFloor.setMinimumSize(new Dimension(FLOOR_WIDTH_MIN, FLOOR_HEIGHT_MIN));
			add(vFloor, gbc);
		}
		
		// add empty cells that will hold the elevator
		for(int i = 0; i < numFloors; i++) {
			JPanel panel = new JPanel();
			elevatorCells.add(panel);
			
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Clicked on floor");
				}
				
			});
		}
		// add elevator and empty panels to fill cells
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		elevator = new ElevatorView();
		elevator.setPreferredSize(new Dimension(ELEVATOR_WIDTH_PREF, ELEVATOR_HEIGHT_PREF));
		elevator.setPreferredSize(new Dimension(ELEVATOR_WIDTH_PREF, ELEVATOR_HEIGHT_PREF));
		add(elevator);
		
		
	
	}
	

}
