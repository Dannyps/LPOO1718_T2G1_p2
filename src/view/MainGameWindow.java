package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import view.viewGameWindowComponents.ViewBuilding;
import view.viewGameWindowComponents.ViewElevator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagConstraints;

public class MainGameWindow {

	private JFrame frame;
	private JTextField scoreField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameWindow window = new MainGameWindow();
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
	public MainGameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel top_panel_score = new JPanel();
		top_panel_score.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(top_panel_score, BorderLayout.NORTH);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setVerticalAlignment(SwingConstants.BOTTOM);
		
		scoreField = new JTextField();
		scoreField.setText("0");
		scoreField.setEditable(false);
		scoreField.setColumns(10);
		GroupLayout gl_top_panel_score = new GroupLayout(top_panel_score);
		gl_top_panel_score.setHorizontalGroup(
			gl_top_panel_score.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_top_panel_score.createSequentialGroup()
					.addContainerGap(475, Short.MAX_VALUE)
					.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scoreField, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_top_panel_score.setVerticalGroup(
			gl_top_panel_score.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_top_panel_score.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_top_panel_score.createParallelGroup(Alignment.BASELINE)
						.addComponent(scoreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblScore, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addContainerGap())
		);
		top_panel_score.setLayout(gl_top_panel_score);
		
		JPanel gameview_panel = new JPanel();
		frame.getContentPane().add(gameview_panel, BorderLayout.CENTER);
		gameview_panel.setBackground(Color.ORANGE);
		
		JPanel teste = new ViewBuilding(10);
		teste.setMinimumSize(new Dimension(300,50));
		teste.setPreferredSize(new Dimension(300,150));
		teste.setMaximumSize(new Dimension(300,150));
		GridBagConstraints gbc_teste = new GridBagConstraints();
		gbc_teste.fill = GridBagConstraints.VERTICAL;
		gameview_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		gameview_panel.add(teste);
		
		JPanel bottom_panel = new JPanel();
		frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		bottom_panel.add(panel_2);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_2.add(btnExitGame);
		
		JButton btnStartAgain = new JButton("Start Again");
		panel_2.add(btnStartAgain);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottom_panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Status Label");
		panel_4.add(lblNewLabel);
	}
}
