package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.GameModel;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainGameWindow {

	private JFrame frame;
	private JTextField scoreField;
	private JLabel lblStatus;
	private int nrFloors;
	private int nrElevators;
	/**
	 * Launch the application.
	 */
	public static void showMainGameWindow(int nrFloors, int nrElevators) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameWindow window = new MainGameWindow(nrFloors, nrElevators);
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
	public MainGameWindow(int nrFloors, int nrElevators) {
		this.nrFloors = nrFloors;
		this.nrElevators = nrElevators;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		/**
		 * 	+--------------------------------+
 			|                                |
 			|            Top Panel           |
 			|                                |
 			+--------------------------------+
		 */
		
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
		
		/**
		 * 	+--------------------------------+
 			|                                |
 			|           Game View            |
 			|                                |
 			+--------------------------------+
		 */
		JPanel gameview_panel = new JPanel();
		frame.getContentPane().add(gameview_panel, BorderLayout.CENTER);
		gameview_panel.setBackground(Color.ORANGE);
		gameview_panel.setLayout(new BoxLayout(gameview_panel, BoxLayout.X_AXIS));
		
		/**
		 * 	+--------------------------------+
 			|                                |
 			|          Bottom Panel          |
 			|                                |
 			+--------------------------------+
		 */
		
		JPanel bottom_panel = new JPanel();
		frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel user_action_panel = new JPanel();
		bottom_panel.add(user_action_panel);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreen.main(null);
				frame.dispose();
			}
		});
		user_action_panel.add(btnExitGame);
		
		JButton btnStartAgain = new JButton("Start Again");
		btnStartAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameModel.getInstance().setGameOver();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UsernameModal unm = new UsernameModal(Integer.toString(GameModel.getInstance().getScore()));
	            unm.frame.setVisible(true);
				unm.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowClosed(e);
						run(gameview_panel);
					}
				});
			}
		});
		user_action_panel.add(btnStartAgain);
		
		JPanel status_panel = new JPanel();
		status_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottom_panel.add(status_panel, BorderLayout.SOUTH);
		status_panel.setLayout(new BorderLayout(0, 0));
		
		lblStatus = new JLabel("Status Label");
		status_panel.add(lblStatus);
		
		/**
		 * 	+--------------------------------+
 			|                                |
 			|            Run Game            |
 			|                                |
 			+--------------------------------+
		 */
		
		run(gameview_panel);
	}
	
	private void run(JPanel gameViewPanel) {
		Controller ctrl = (new Controller(nrFloors, nrElevators)).
				setDefaultElevatorCapacity(4).
				setDefaultElevatorSpeed(3).
				setDefaultFloorCapacity(7);
		gameViewPanel.removeAll();
		gameViewPanel.add(ctrl.getGameView());
		ctrl.start();
		
		new Thread(new Runnable() {
	        public void run() {
	            while (!GameModel.getInstance().isGameOver()) {
	            	scoreField.setText(Integer.toString(GameModel.getInstance().getScore()));
	            	lblStatus.setText(ctrl.getLatestErrorMessage());
	            	try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            
	            lblStatus.setText("Game Over!");
	        }
	    }).start();
	}
}
