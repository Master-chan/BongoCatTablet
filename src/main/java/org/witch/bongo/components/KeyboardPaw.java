package org.witch.bongo.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import org.witch.bongo.ImageUtil;

import lombok.Setter;

public class KeyboardPaw extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage released;
	private BufferedImage index;
	private BufferedImage middle;
	
	@Setter private boolean indexPressed = false;
	@Setter private boolean middlePressed = false;
	
	public KeyboardPaw()
	{
		released = ImageUtil.getBackgroundImage("paw_released.png");
		index = ImageUtil.getBackgroundImage("paw_index.png");
		middle = ImageUtil.getBackgroundImage("paw_middle.png");
		setSize(released.getWidth(), released.getHeight());
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(middlePressed)
		{
			g2d.drawImage(middle, 0, 0, middle.getWidth(), middle.getHeight(), null);
		}
		else if(indexPressed)
		{
			g2d.drawImage(index, 0, 0, index.getWidth(), index.getHeight(), null);
		}
		else
		{
			g2d.drawImage(released, 0, 0, released.getWidth(), released.getHeight(), null);
		}
		
	}
	
}
