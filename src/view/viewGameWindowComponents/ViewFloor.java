package view.viewGameWindowComponents;

import javax.swing.JPanel;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;

/**
 * Represents the view floor, where the NPCs wait for the elevator
 *
 */
@SuppressWarnings("serial")
public class ViewFloor extends JPanel {

	List<viewNPC> npcs = new ArrayList<viewNPC>();
	
	public ViewFloor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{300, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel NPCContainer = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(NPCContainer, gbc_panel_1);
		NPCContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel floorPanel = new JPanel();
		floorPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridheight = 50;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(floorPanel, gbc_panel);		
	}
	
	public void addViewNPC(viewNPC npc) {
		npcs.add(npc);
	}
	
	public void removeViewNPC(viewNPC npc) {
		npcs.remove(npc);
	}

}