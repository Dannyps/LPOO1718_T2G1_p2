package view.viewGameWindowComponents;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import model.entities.ElevatorModel;

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
		
		// TODO
		// this will tell game controller that user clicked on cell Y
		// We probably need to know from which building this container belongs to
		// We need a reference to gameController to call some method to indicate user action
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int floorClicked = (int) ((double)e.getY()/getHeight() * numberCells);
				System.out.println("Clicked on floor" + Integer.toString(floorClicked));
			}
		});
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
		}
		
		// draw elevator
		this.add(elevatorView);
		elevatorView.setBounds(0, elevatorModel.getPosY(), this.getWidth(), this.getHeight()/numberCells);
		
		// TODO Add NPCs inside elevator
	}
}
