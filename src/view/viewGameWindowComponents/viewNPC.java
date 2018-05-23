package view.viewGameWindowComponents;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class viewNPC extends JPanel {

	private String imgPath;
	private int floorDest;

	/**
	 * Create the panel.
	 */
	public viewNPC(NPCFace face, int floorDest) {
		// set current image
		this.imgPath = this.getImagePath(face);

		// set destination floor
		this.floorDest = floorDest;

		// transparency
		this.setOpaque(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 24, 24 };
		gridBagLayout.columnWidths = new int[] { 24, 10 };
		setLayout(gridBagLayout);
		
		// label with image
		Image imgScaled = new ImageIcon(this.imgPath).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(imgScaled);

		JLabel lblImg = new JLabel(img);
		
		GridBagConstraints gbc_lblImg = new GridBagConstraints();
		gbc_lblImg.fill = GridBagConstraints.BOTH;
		gbc_lblImg.gridx = 0;
		gbc_lblImg.gridy = 0;
		add(lblImg, gbc_lblImg);

		// label for floor destination
		JLabel lblDestFloor = new JLabel(Integer.toString(this.floorDest));
		lblDestFloor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		GridBagConstraints gbc_lblDestFloor = new GridBagConstraints();
		gbc_lblDestFloor.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc_lblDestFloor.gridx = 1;
		gbc_lblDestFloor.gridy = 0;
		add(lblDestFloor, gbc_lblDestFloor);

	}

	private String getImagePath(NPCFace face) {
		return "res/"+face.toString()+".png";
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
