package view.viewGameWindowComponents;

import javax.swing.JPanel;

import model.entities.FloorModel;
import model.entities.NPCEmotion;
import model.entities.NPCModel;

import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;

/**
 * Represents the view floor, where the NPCs wait for the elevator
 */

@SuppressWarnings("serial")
public class FloorView extends JPanel {

	private JPanel NPCContainer;
	private FloorModel floorModel;
	public FloorView(FloorModel floorModel) {
		this.floorModel = floorModel;
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(200, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		NPCContainer = new JPanel();
		NPCContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 3));
		NPCContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		NPCContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		add(NPCContainer);
		
		
		JPanel floorPanel = new JPanel();
		floorPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		floorPanel.setBackground(Color.LIGHT_GRAY);
		floorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		add(floorPanel);
		
		addViewNPCs();
	}
	
	private void addViewNPCs() {
		for(NPCModel npc : floorModel.getNpcs()) {
			NPCContainer.add(new NPCView(NPCEmotion.Smiling, 0));
		}
	}
	public void addViewNPC(NPCView npc) {
		NPCContainer.add(npc);
	}
	
	public void removeViewNPC(NPCView npc) {
		NPCContainer.remove(npc);
	}

}
