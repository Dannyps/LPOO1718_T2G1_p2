package view.viewGameWindowComponents;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class ViewBuilding extends JPanel {
	
	List<ViewFloor> floors = new ArrayList<ViewFloor>();
	/**
	 * Create the panel.
	 */
	public ViewBuilding(int numFloors) {
		setLayout(new GridLayout(numFloors, 2, 0, 0));
		
		for(int i = 0; i < numFloors; i++) {
			floors.add(i, new ViewFloor());
			add(floors.get(i));
		}
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked");
			}
			
		});
	
	}
	

}
