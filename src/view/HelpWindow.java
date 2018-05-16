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
				"Liftimulator is an Elevator Simulation Game. Make sure that everyone gets to the desired floor\r\n" + 
				"as quickly as possible, in order to maximize your score!\r\n<br><br>" + 
				"Click the arrows (on the screen) to move the lift to the desired location.\r\n" + 
				"Click each smiley to move the corresponding NPC from the lift to the floor, and vice-versa.\r\n" + 
				"Press the designated keys (signaled in blue) to activate turbo-mode for the lifts.\r\n" + 
				"Right click on a smiley on any floor to send it by the stairs. This will free up the capacity of that\r\n" + 
				"floor, but will make your score go down.\r\n" + 
				"The desired destination for each NPC is represented by the number next to the smiley.\r\n" + 
				"Each floor has a defined capacity, as well as each lift.\r\n" + 
				"Whenever someone arrives at a floor, but has no space to wait there, a red exclamation mark\r\n" + 
				"will flash.\r\n<br><br>" + 
				"The base speed of the elevators depends on the game difficulty.\r\n" + 
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
	}

}
