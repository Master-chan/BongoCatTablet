package org.witch.bongo.nativelisteners;

import javax.swing.JLabel;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.witch.bongo.components.KeyboardPaw;

public class GlobalKeyListener implements NativeKeyListener
{

	private int key1;
	private int key2;
	
	private KeyboardPaw paw;
	private JLabel lastKey;
	
	public GlobalKeyListener(int key1, int key2, KeyboardPaw paw, JLabel lastKey)
	{
		this.key1 = key1;
		this.key2 = key2;
		this.paw = paw;
		this.lastKey = lastKey;
	}
	
	public void resetKeys(int key1, int key2)
	{
		paw.setIndexPressed(false);
		paw.setMiddlePressed(false);
		paw.repaint();
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		//System.out.println("Code: " + e.getKeyCode());
		lastKey.setText(e.getKeyCode() + "");
		if(e.getKeyCode() == key1)
		{
			paw.setMiddlePressed(true);
			paw.repaint();
		}
		else if(e.getKeyCode() == key2)
		{
			paw.setIndexPressed(true);
			paw.repaint();
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e)
	{
		if(e.getKeyCode() == key1)
		{
			paw.setMiddlePressed(false);
			paw.repaint();
		}
		else if(e.getKeyCode() == key2)
		{
			paw.setIndexPressed(false);
			paw.repaint();
		}
	}

	public void nativeKeyTyped(NativeKeyEvent e)
	{
	}
}
