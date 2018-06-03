package view.viewGameWindowComponents;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ElevatorView extends JPanel {
	
	private JPanel npcContainer; // the panel that will hold NPCs
	
	public ElevatorView() {			
		setLayout(new BorderLayout(0, 0));

		// the panel that will contain the NPCs
		npcContainer = new JPanel();
		npcContainer.setOpaque(false);
		npcContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		this.add(npcContainer);
	}
	
	public void addViewNPC(NPCView npc) {
		npcContainer.add(npc);
	}
	
	public void removeViewNPC(NPCView npc) {
		npcContainer.remove(npc);
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int w = getWidth() - 1;
        int h = getHeight() - 1;
        
        
        Graphics2D g2d = (Graphics2D) g;
        
        Stroke oldStroke = g2d.getStroke();
        
        // paint the background
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gpBorder = new GradientPaint(0, 0, Color.GRAY, 0, h, Color.DARK_GRAY);
        GradientPaint gpFill = new GradientPaint(0, 0, Color.WHITE, 0, h, Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(gpFill);
        g2d.fillRect(0, 0, w, h);
        g2d.setPaint(gpBorder);
        g2d.drawRect(0, 0, w, h);
        // add a border
        
        g2d.setStroke(oldStroke);
        
        npcContainer.revalidate();
    }
    
}
