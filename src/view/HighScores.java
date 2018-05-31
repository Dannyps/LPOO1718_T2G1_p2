package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.SwingConstants;

import controller.HighScore;
import javafx.util.Pair;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HighScores {

	JFrame frame;
	private final JPanel panel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighScores window = new HighScores();
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
	public HighScores() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("serial")
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 358, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblHighscores = new JLabel("High Scores");
		panel.add(lblHighscores);
		lblHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscores.setFont(new Font("Impact", Font.PLAIN, 41));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Score" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, Integer.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		frame.getContentPane().add(table, BorderLayout.CENTER);
		this.fillTable(table);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void fillTable(JTable t) throws IOException {

		Pair<String, Integer> highscores[];
		
		try {
			highscores = HighScore.getHighscores();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (Pair<String, Integer> p : highscores) {
				model.addRow(new Object[] { p.getKey(), p.getValue() });
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("gawd.");
		}

	}

}
