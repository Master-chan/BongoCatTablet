package org.witch.bongo;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil
{
	
	public static BufferedImage getBackgroundImage(String path)
	{
		BufferedImage image;
		try
		{
			image = ImageIO.read(getResourceAsStream("/" + path));
			return image;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static InputStream getResourceAsStream(String path)
	{
		InputStream stream = ImageUtil.class.getResourceAsStream(path);
		String[] split = path.split("/");
		path = split[split.length - 1];
		if (stream == null)
		{
			File resource = new File("src\\main\\resources\\" + path);
			if (resource.exists())
			{
				try
				{
					stream = new BufferedInputStream(new FileInputStream(resource));
				}
				catch (IOException ignore)
				{
				}
			}
		}
		return stream;
	}
}
