package org.witch.bongo;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.witch.bongo.components.JPanelTransparent;
import org.witch.bongo.components.KeyboardPaw;
import org.witch.bongo.components.MousePaw;
import org.witch.bongo.nativelisteners.GlobalKeyListener;
import org.witch.bongo.nativelisteners.GlobalMouseListener;


public class Frame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		CatConfig config = ConfigLoader.loadConfig();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Frame frame = new Frame(config);
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame(CatConfig config)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 703);
		contentPane = new JPanelTransparent("background.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("BongoCat Tablet");
		
		MousePaw lblNewLabel = new MousePaw(config);
		lblNewLabel.setBounds(10, 11, 547, 455);
		contentPane.add(lblNewLabel);
		
		KeyboardPaw kbPaw = new KeyboardPaw();
		kbPaw.setLocation(383, 250);
		contentPane.add(kbPaw);
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(10, 615, 46, 14);
		contentPane.add(lblX);
		
		JLabel lblY = new JLabel("y:");
		lblY.setBounds(10, 640, 46, 14);
		contentPane.add(lblY);
		
		JLabel showX = new JLabel("New label");
		showX.setBounds(20, 615, 46, 14);
		contentPane.add(showX);
		
		JLabel showY = new JLabel("New label");
		showY.setBounds(20, 640, 46, 14);
		contentPane.add(showY);
		
		JLabel lblLastKeyId = new JLabel("Last key id:");
		lblLastKeyId.setBounds(10, 591, 72, 14);
		contentPane.add(lblLastKeyId);
		
		JLabel lblNewLabel_1 = new JLabel("none");
		lblNewLabel_1.setBounds(69, 591, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try
		{
			GlobalScreen.setEventDispatcher(new SwingDispatchService());
			GlobalScreen.registerNativeHook();
		}
		catch(NativeHookException ex)
		{
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		GlobalScreen.addNativeMouseMotionListener(new GlobalMouseListener(lblNewLabel, showX, showY, config));
		GlobalScreen.addNativeKeyListener(new GlobalKeyListener(config.getKey1(), config.getKey2(), kbPaw, lblNewLabel_1));
		
	}
}
