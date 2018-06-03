package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.Controller;
import javax.swing.SwingConstants;

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
		a1();
		
		JPanel panel = a2();
		
		JSpinner spinner = a3(panel);
		
		JSpinner spinner_1 = a4(panel);
		
		JButton btnNewButton = new JButton("Help!");
		btnNewButton.setBounds(199, 227, 78, 23);
		frmWelcomeScreen.getContentPane().add(btnNewButton);
		
		a5(spinner, spinner_1);
		
		JButton btnNewButton_2 = new JButton("View Highscores");
		btnNewButton_2.setBounds(169, 301, 139, 23);
		frmWelcomeScreen.getContentPane().add(btnNewButton_2);
		
		a7(btnNewButton_2);
		
		HelpWindow help = new HelpWindow();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.frame.setVisible(true);
			}
		});
	}

	private void a7(JButton btnNewButton_2) {
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(404, 319, 66, 23);
		frmWelcomeScreen.getContentPane().add(btnQuit);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new HighScores()).frame.setVisible(true);
			}
		});
	}

	private void a5(JSpinner spinner, JSpinner spinner_1) {
		JButton btnNewButton_1 = new JButton("Start Game!");
		btnNewButton_1.setBounds(145, 261, 183, 29);
		frmWelcomeScreen.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGameWindow.showMainGameWindow((int)spinner.getValue(), (int) spinner_1.getValue());
				frmWelcomeScreen.dispose();
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}

	private JSpinner a4(JPanel panel) {
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		spinner_1.setToolTipText("More than one lift will unlock multiplayer mode");
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 1;
		panel.add(spinner_1, gbc_spinner_1);
		return spinner_1;
	}

	private JSpinner a3(JPanel panel) {
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(3, 3, 20, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel.add(spinner, gbc_spinner);
		
		JLabel lblNewLabel = new JLabel("Number of Lifts");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		return spinner;
	}

	private JPanel a2() {
		JPanel panel = new JPanel();
		panel.setBounds(120, 106, 232, 80);
		frmWelcomeScreen.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {150, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Floors");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		return panel;
	}

	private void a1() {
		frmWelcomeScreen = new JFrame();
		frmWelcomeScreen.setTitle(Controller.getFormsTitle());
		frmWelcomeScreen.setBounds(100, 100, 496, 392);
		frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeScreen.getContentPane().setLayout(null);
		
		JLabel lblWelcomeTo = new JLabel("Welcome to ");
		lblWelcomeTo.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblWelcomeTo.setBounds(44, 41, 139, 42);
		frmWelcomeScreen.getContentPane().add(lblWelcomeTo);
		
		JLabel lblIkfsdfsd = new JLabel(Controller.getFormsTitle());
		lblIkfsdfsd.setFont(new Font("SimSun", Font.BOLD | Font.ITALIC, 36));
		lblIkfsdfsd.setBounds(180, 21, 257, 72);
		frmWelcomeScreen.getContentPane().add(lblIkfsdfsd);
	}
}
