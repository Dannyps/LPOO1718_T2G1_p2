package view.viewGameWindowComponents;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A container for elevators
 * Graphically it represents a vertical column where an ElevatorView can translate
 * The container has a number of cells/floors where the elevator can stop by
 *
 */
public class ElevatorContainerView extends JPanel{
	
	private int numberCells;
	
	public ElevatorContainerView(int numberCells) {
		this.numberCells = numberCells;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i <= numberCells; i++) {
			int thisY = i * this.getHeight()/numberCells;
			g.drawLine(0, thisY , thisY, this.getWidth());
		}
	}
}
