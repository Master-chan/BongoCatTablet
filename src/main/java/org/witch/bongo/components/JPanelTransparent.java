package org.witch.bongo.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.witch.bongo.ImageUtil;

public class JPanelTransparent extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = null;
	
	private Color outline;
	private int outlineThickness;
	
	public void addOutline(Color color, int thickness)
	{
		this.outline = color;
		this.outlineThickness = thickness;
	}
	
	public JPanelTransparent(String imagesrc)
	{
		setOpaque(false);
		image = ImageUtil.getBackgroundImage(imagesrc);
	}
	
	public JPanelTransparent(BufferedImage image)
	{
		setOpaque(false);
		this.image = image;
	}
	
	public void resizeFromImage()
	{
		this.setSize(this.image.getWidth(), this.image.getHeight());
	}
	
	public JPanelTransparent()
	{
		setOpaque(false);
	}
	
	@Override
	public boolean isOptimizedDrawingEnabled()
	{
		return true;
	}
	
	private boolean colorSet = false;
	
	@Override
	public void setBackground(Color c)
	{
		colorSet = true;
		super.setBackground(c);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(image != null)
		{
			if(this.outline != null && this.outlineThickness > 0)
			{
				g2d.setColor(outline);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.drawImage(image, outlineThickness, outlineThickness, image.getWidth(), image.getHeight(), null);
			}
			else
			{
				g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
			}
		}
		else if(colorSet)
		{
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
		
		super.paintComponent(g2d);
	}
	

}
