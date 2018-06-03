package view.viewGameWindowComponents;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.Controller;
import model.entities.ElevatorModel;
import model.entities.NPCModel;

/**
 * A container for elevators
 * Graphically it represents a vertical column where an ElevatorView can translate
 * The container has a number of cells/floors where the elevator can stop by
 *
 */
@SuppressWarnings("serial")
public class ElevatorContainerView extends JPanel{
	
	private int numberCells;
	private ElevatorModel elevatorModel;
	private ElevatorView elevatorView;
	
	public ElevatorContainerView(int numberCells, ElevatorModel elevatorModel) {
		// initialize fields
		this.numberCells = numberCells;
		this.elevatorModel = elevatorModel;
		this.elevatorView = new ElevatorView();
		
		// set layout
		setLayout(null);
		
		// Add a mouse listener to tell gameController that user clicked on a certain floor
		if(elevatorModel.isUserControllable())
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int floorClicked = numberCells - 1 - (int) ((double)e.getY()/getHeight() * numberCells);
					Controller.getInstance().eventElevatorArrowClicked(floorClicked, elevatorModel);
				}
			});
		
	}
	
	private void renderNPCs() {
		for(NPCModel npc : elevatorModel.getNpcs()) {
			elevatorView.addViewNPC(new NPCView(npc));
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw vertical lines
		g.drawLine(0, 0, 0, this.getHeight()-1);
		g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);
		
		// draw horizontal lines to delimit cells
		g.drawLine(0, 0, this.getWidth()-1, 0);
		for(int i = 1; i <= numberCells; i++) {
			int thisY = i * this.getHeight()/numberCells;
			g.drawLine(0, thisY-1, this.getWidth(), thisY-1);
			g.drawString(Integer.toString(numberCells - i), this.getWidth()/2, thisY-this.getHeight()/numberCells/2);
		}
		
		// draw elevator
		elevatorView.setBounds(0, this.getHeight() - this.getHeight()/numberCells - elevatorModel.getPosY() - 1, this.getWidth(), this.getHeight()/numberCells);
		this.add(elevatorView);
		
		// TODO Add NPCs inside elevator
		renderNPCs();
	}
}
