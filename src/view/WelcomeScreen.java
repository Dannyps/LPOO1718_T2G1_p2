package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.Controller;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class WelcomeScreen {

	private JFrame frmWelcomeScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen window = new WelcomeScreen();
					window.frmWelcomeScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeScreen = new JFrame();
		frmWelcomeScreen.setTitle(Controller.getFormsTitle());
		frmWelcomeScreen.setBounds(100, 100, 497, 492);
		frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeScreen.getContentPane().setLayout(null);
		
		JLabel lblWelcomeTo = new JLabel("Welcome to ");
		lblWelcomeTo.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblWelcomeTo.setBounds(37, 54, 139, 42);
		frmWelcomeScreen.getContentPane().add(lblWelcomeTo);
		
		JLabel lblIkfsdfsd = new JLabel(Controller.getFormsTitle());
		lblIkfsdfsd.setFont(new Font("SimSun", Font.BOLD | Font.ITALIC, 36));
		lblIkfsdfsd.setBounds(173, 34, 257, 72);
		frmWelcomeScreen.getContentPane().add(lblIkfsdfsd);
		
		JPanel panel = new JPanel();
		panel.setBounds(57, 125, 365, 49);
		frmWelcomeScreen.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{167, 135, 0};
		gbl_panel.rowHeights = new int[]{42, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblGameDificulty = new JLabel("Game Dificulty");
		lblGameDificulty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblGameDificulty = new GridBagConstraints();
		gbc_lblGameDificulty.insets = new Insets(0, 0, 0, 5);
		gbc_lblGameDificulty.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblGameDificulty.gridx = 0;
		gbc_lblGameDificulty.gridy = 0;
		panel.add(lblGameDificulty, gbc_lblGameDificulty);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
	}
}
