package view.viewGameWindowComponents;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.GridBagLayout;

public class ViewBuilding extends JPanel {
	
	List<ViewFloor> floors = new ArrayList<ViewFloor>();
	/**
	 * Create the panel.
	 */
	public ViewBuilding(int numFloors) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		
		
		for(int i = 0; i < numFloors; i++) {
			floors.add(i, new ViewFloor());
			add(floors.get(i), gbc);
		}
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked on floor" );
			}
			
		});
	
	}
	

}
