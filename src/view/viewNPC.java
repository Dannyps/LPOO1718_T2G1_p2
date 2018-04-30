package view;

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

public class viewNPC extends JPanel {
	
	private String imgPath;
	private int floorDest;
	
	/**
	 * Create the panel.
	 */
	public viewNPC(String imgPath, int floorDest) {
		// set current image
		this.imgPath = imgPath;
		
		// set destination floor
		this.floorDest = floorDest;
		
		// transparency
		this.setOpaque(true);
		
		// layout grid
		setLayout(new GridLayout(2, 1, 0, 0));
		
		// label for floor destination
		JLabel lblDestFloor = new JLabel(Integer.toString(floorDest));
		lblDestFloor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDestFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestFloor.setPreferredSize(new Dimension(getWidth(), 19));
		add(lblDestFloor);
		
		// label with image
		Image imgScaled = new ImageIcon(this.imgPath).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(imgScaled);
		JLabel lblImg = new JLabel(img);
		lblImg.setPreferredSize(new Dimension(32, 32));
		add(lblImg);
		
	}
		
	/**
	 * Update the NPC image
	 * @param imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
