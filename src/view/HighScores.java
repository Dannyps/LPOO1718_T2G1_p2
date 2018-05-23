package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HighScores {

	private JFrame frame;
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Score"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		frame.getContentPane().add(table, BorderLayout.CENTER);
		this.fillTable(table);
	}
	
	private void fillTable(JTable t) throws IOException {
		// build a URL
	    String s = "http://lpoo.dannyps.net/highscores";
	    URL url = new URL(s);
	 
	    // read from the URL
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    
	    JSONArray arr = new JSONArray(str);
	    
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    for(Object obj : arr){
		   JSONObject o = (JSONObject) obj;
		   model.addRow(new Object[]{o.get("name"), o.get("score")});
	    }
	    
	}
}

	
