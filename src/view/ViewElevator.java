package view;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

public class ViewElevator extends JPanel {
	List<viewNPC> npcs = new ArrayList<viewNPC>();
	/**
	 * Create the panel.
	 */
	
	private int prefWidth = 72;
	private int prefHeight = 100;
	private int borderThickness = 20;
	
	public ViewElevator() {		
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		npcs.add(new viewNPC(NPCFace.Smiling, 0));
		
		for(int i = 0; i < npcs.size(); i++) {
			this.add(npcs.get(i));
		}
		
		this.setSize(new Dimension(prefWidth + borderThickness*2, prefHeight + borderThickness*2));
		
		// the panel that will contain the NPCs
		JPanel npcContainer = new JPanel();
		npcContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		npcContainer.setSize(new Dimension(prefWidth, prefHeight));
		npcContainer.setLocation(borderThickness, borderThickness);
		this.add(npcContainer);
	}
	
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
	
	public void addViewNPC(viewNPC npc) {
		npcs.add(npc);
	}
	
	public void removeViewNPC(viewNPC npc) {
		npcs.remove(npc);
	}

}
