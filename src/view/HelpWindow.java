package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpWindow {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpWindow window = new HelpWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 629, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("<html>" +
				"Liftimulator is an Elevator Simulation Game. Make sure that everyone gets to the desired floor as quickly as possible, in order to maximize your score!\n" + 
				"\n" + 
				"Click the numbers (on the screen) to move the lift to the desired location. Click each smiley to move the corresponding NPC from the floor to the lift. \n" + 
				"\n" + 
				"Right click on a smiley on any floor to send it by the stairs. This will free up the capacity of that floor, but will make your score go down.\n" + 
				"\n" + 
				"The desired destination for each NPC is represented by the number next to the smiley. A smiley will automatically leave the elevator it is in when the elevator stops in the NPC's desired exit floor.\n" + 
				"\n" + 
				"Each floor has a defined capacity, as well as each lift. Notice the messages on the bottom of the screen!\n" + 
				"\n" + 
				"Whenever someone arrives at a floor, but has no space to wait there, a message will be displayed, and your score will go down.\n" + 
				"\n" + 
				"In the end, you can submit your score and thus compete with players from all around the world. May the best win!" + 
				"</html>");
		lblNewLabel.setFont(new Font("Roboto", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 1));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		panel_2.add(btnNewButton);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
