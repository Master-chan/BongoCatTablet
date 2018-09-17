package org.witch.bongo.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import org.witch.bongo.CatConfig;
import org.witch.bongo.ImageUtil;
import org.witch.bongo.math.PVector;
import org.witch.bongo.math.QuadRectTransform;

import lombok.Setter;

public class MousePaw extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stroke s = new BasicStroke(5);
	@Setter private int x = 0;
	@Setter private int y = 0;
	private BufferedImage pen;
	
	private CatConfig config;
	private QuadRectTransform transform;
	private PVector q1, q2, q3, q4, r1, r2, r3, r4;
	
	
	public MousePaw(CatConfig config)
	{
		this.config = config;
		pen = ImageUtil.getBackgroundImage("pen2.png");
		
		// Quadrilateral coordinates (top-left, bottom-left, bottom-right, top-right)
		q1 = new PVector(147, 341);
		q2 = new PVector(208, 285);
		q3 = new PVector(88, 257);
		q4 = new PVector(27, 307);
		
		// Rectangle coordinates to transform (theoretically may be any quadrilateral)
		r1 = new PVector(config.getTopLeftX(), config.getTopLeftY());
		r2 = new PVector(config.getTopLeftX(), config.getBotRightY());
		r3 = new PVector(config.getBotRightX(), config.getBotRightY());
		r4 = new PVector(config.getBotRightX(), config.getTopLeftY());
		
		transform = new QuadRectTransform(q1, q2, q3, q4, r1, r2, r3, r4);
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(s);

			double startX = 254;
			double startY = 230;
			
			PVector v = new PVector(x, y);
			PVector u = transform.rect2quad(v);
			double controlPointX = u.x;
			double controlPointY = u.y;
			double endX = 295;
			double endY = 290;
			QuadCurve2D.Double curve = new QuadCurve2D.Double(startX, startY,
					controlPointX,
					controlPointY,
					endX,
					endY);
			
			double normal = ((x / (double) config.getBotRightX()) / 10D) / 2D;
			
			double t = 0.6D - normal;
			
			double midX = (1 - t) * (1 - t) * startX + 2 * (1 - t) * t * controlPointX + t * t * endX;
			double midY = (1 - t) * (1 - t) * startY + 2 * (1 - t) * t * controlPointY + t * t * endY;
			

			g2d.setColor(Color.WHITE);
			g2d.fill(curve);
			g2d.setColor(Color.BLACK);
			g2d.draw(curve);
			int imageX = (int) (midX - (pen.getWidth() / 2));
			int imageY = (int) (midY - (pen.getHeight() / 2));
			g2d.drawImage(pen,
					imageX,
					imageY, 
					pen.getWidth(), pen.getHeight(), null);
	}
}
