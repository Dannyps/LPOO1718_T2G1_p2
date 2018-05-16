package view;

import javax.swing.JPanel;

import com.sun.prism.impl.BaseMesh.FaceMembers;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

public class ViewElevator extends JPanel {
	List<viewNPC> npcs = new ArrayList<viewNPC>();
	/**
	 * Create the panel.
	 */
	public ViewElevator() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		
		for(int i = 0; i < npcs.size(); i++) {
			this.add(npcs.get(i));
		}
		
		this.setPreferredSize(new Dimension(100, 200));
	}
	
	public void addViewNPC(viewNPC npc) {
		npcs.add(npc);
	}
	
	public void removeViewNPC(viewNPC npc) {
		npcs.remove(npc);
	}

}
