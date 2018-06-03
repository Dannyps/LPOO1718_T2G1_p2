package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.HighScore;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UsernameModal {

	JFrame frame;
	String score;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsernameModal window = new UsernameModal("0");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public UsernameModal(String score) {
		this.score = score;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createJFrame();
		
		createLabel();
		
		JPanel panel = createPanel();
		
		createTextField(panel);
		
		JPanel panel_2 = createJPanel2();
		
		
		fillField(panel_2);
		
		JPanel panel_1 = createJPanel3();
		
		JButton btnNewButton = addSubmitButton();
		panel_1.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_1.add(btnCancel);
	}

	private JButton addSubmitButton() {
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				if(name != "") {
					HighScore h = new HighScore(name, Integer.parseInt(score)); 
					try {
						if(h.submit()) {
							JOptionPane.showMessageDialog(frame,
								    "Score submitted successfully!",
								    "Success",
								    JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame,
							    "Could not submit highscore. Please check your internet connection!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		return btnNewButton;
	}

	private JPanel createJPanel3() {
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		return panel_1;
	}

	private void fillField(JPanel panel_2) {
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		//textField_1.setEnabled(false);
		textField_1.setColumns(15);
		panel_2.add(textField_1);
		textField_1.setText(score);
	}

	private JPanel createJPanel2() {
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		
		JLabel lblScore = new JLabel("Score:");
		panel_2.add(lblScore);
		return panel_2;
	}

	private void createTextField(JPanel panel) {
		JLabel lblUsername = new JLabel("Name:");
		panel.add(lblUsername);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(15);
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		return panel;
	}

	private void createLabel() {
		JLabel lblInsertYourName = new JLabel("Insert your name to submit your score.");
		GridBagConstraints gbc_lblInsertYourName = new GridBagConstraints();
		gbc_lblInsertYourName.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertYourName.gridx = 0;
		gbc_lblInsertYourName.gridy = 0;
		frame.getContentPane().add(lblInsertYourName, gbc_lblInsertYourName);
	}

	private void createJFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 304, 247);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{24, 0};
		gridBagLayout.rowHeights = new int[]{34, 54, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
	}

}
