package view.viewGameWindowComponents;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.entities.NPCEmotion;
import model.entities.NPCModel;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class NPCView extends JPanel {

	private String imgPath;

	/**
	 * Create the panel.
	 */
	public NPCView(NPCModel npcModel) {
		// set current image
		this.imgPath = this.getImagePath(npcModel.getEmotionalLevel());

		// transparency
		this.setOpaque(false);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 16, 16 };
		gridBagLayout.columnWidths = new int[] { 16, 10 };
		setLayout(gridBagLayout);

		// label with image
		Image imgScaled = new ImageIcon(this.imgPath).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(imgScaled);

		JLabel lblImg = new JLabel(img);

		GridBagConstraints gbc_lblImg = new GridBagConstraints();
		gbc_lblImg.fill = GridBagConstraints.BOTH;
		gbc_lblImg.gridx = 0;
		gbc_lblImg.gridy = 0;
		add(lblImg, gbc_lblImg);

		// label for floor destination
		JLabel lblDestFloor = new JLabel(Integer.toString(npcModel.getDestinationFloor()));
		lblDestFloor.setFont(new Font("Tahoma", Font.PLAIN, 12));

		GridBagConstraints gbc_lblDestFloor = new GridBagConstraints();
		gbc_lblDestFloor.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc_lblDestFloor.gridx = 1;
		gbc_lblDestFloor.gridy = 0;
		add(lblDestFloor, gbc_lblDestFloor);

		// Add a mouse listener to tell gameController that user clicked on a certain
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if(SwingUtilities.isLeftMouseButton(e))
						Controller.getInstance().eventNPCClicked(npcModel);
					else if(SwingUtilities.isRightMouseButton(e))
						Controller.getInstance().eventNPCRightClicked(npcModel);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} 
		});

	}

	private String getImagePath(NPCEmotion face) {
		return "res/" + face.toString() + ".png";
	}

	/**
	 * Update the NPC image
	 * 
	 * @param imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
