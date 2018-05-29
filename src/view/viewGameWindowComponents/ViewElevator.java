package view.viewGameWindowComponents;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class ViewElevator extends JPanel {
	
	private JPanel npcContainer; // the panel that will hold NPCs
	
	public ViewElevator() {			
		// set layout
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// the panel that will contain the NPCs
		npcContainer = new JPanel();
		npcContainer.setBackground(Color.WHITE);
		npcContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.add(npcContainer);
	}
	
	public void addViewNPC(ViewNPC npc) {
		npcContainer.add(npc);
	}
	
	public void removeViewNPC(ViewNPC npc) {
		npcContainer.remove(npc);
	}
	
	/*
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int w = getWidth();
        int h = getHeight();
        
        
        Graphics2D g2d = (Graphics2D) g;
        
        Stroke oldStroke = g2d.getStroke();
        
        // paint the background
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gpBorder = new GradientPaint(0, 0, Color.GRAY, 0, h, Color.DARK_GRAY);
        GradientPaint gpFill = new GradientPaint(0, 0, Color.WHITE, 0, h, Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(borderThickness));
        //g2d.setPaint(gpFill);
        //g2d.fillRect(0, 0, w, h);
        g2d.setPaint(gpBorder);
        g2d.drawRect(0, 0, w, h);
        // add a border
        
        g2d.setStroke(oldStroke);
    }
    */
}
