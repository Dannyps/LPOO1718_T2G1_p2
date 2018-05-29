package view.viewGameWindowComponents;

import javax.swing.JPanel;

import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;

/**
 * Represents the view floor, where the NPCs wait for the elevator
 */

@SuppressWarnings("serial")
public class ViewFloor extends JPanel {

	private JPanel NPCContainer;
	
	public ViewFloor() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(200, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		NPCContainer = new JPanel();
		NPCContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		add(NPCContainer);
		NPCContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel floorPanel = new JPanel();
		floorPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		floorPanel.setBackground(Color.LIGHT_GRAY);
		floorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		add(floorPanel);		
	}
	
	public void addViewNPC(ViewNPC npc) {
		NPCContainer.add(npc);
	}
	
	public void removeViewNPC(ViewNPC npc) {
		NPCContainer.remove(npc);
	}

}
