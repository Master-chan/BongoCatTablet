package org.witch.bongo.nativelisteners;

import javax.swing.JLabel;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.witch.bongo.CatConfig;
import org.witch.bongo.components.MousePaw;

public class GlobalMouseListener implements NativeMouseInputListener
{

	private CatConfig config;
	private MousePaw arc;
	private JLabel x;
	private JLabel y;
	
	private volatile long lastRepaint = 0;
	
	public GlobalMouseListener(MousePaw arc, JLabel x, JLabel y, CatConfig config)
	{
		this.arc = arc;
		this.x = x;
		this.y = y;
		this.config = config;
	}
	
	public void nativeMouseClicked(NativeMouseEvent e)
	{
	}

	public void nativeMousePressed(NativeMouseEvent e)
	{
	}

	public void nativeMouseReleased(NativeMouseEvent e)
	{
	}

	public void nativeMouseMoved(NativeMouseEvent e)
	{
		long current = System.currentTimeMillis();
		
		if(lastRepaint != 0 && lastRepaint + 16 >= current) // 16ms ~= 60 fps
		{
			return;
		}
		lastRepaint = current;
		int xcoord = Math.max(config.getTopLeftX(), e.getX());
		xcoord = Math.min(config.getBotRightX(), xcoord);
		
		int ycoord = Math.max(config.getTopLeftY(), e.getY());
		ycoord = Math.min(config.getBotRightY(), ycoord);
		
		x.setText(String.valueOf(xcoord));
		y.setText(String.valueOf(ycoord));
		arc.setX(xcoord);
		arc.setY(ycoord);
		arc.repaint();
	}

	public void nativeMouseDragged(NativeMouseEvent e)
	{
		long current = System.currentTimeMillis();
		
		if(lastRepaint != 0 && lastRepaint + 16 >= current) // 16ms ~= 60 fps
		{
			return;
		}
		lastRepaint = current;
		int xcoord = Math.max(0, e.getX());
		xcoord = Math.min(1920, xcoord);
		
		int ycoord = Math.max(0, e.getY());
		ycoord = Math.min(1200, ycoord);
		
		x.setText(String.valueOf(xcoord));
		y.setText(String.valueOf(ycoord));
		arc.setX(xcoord);
		arc.setY(ycoord);
		arc.repaint();	
	}

}