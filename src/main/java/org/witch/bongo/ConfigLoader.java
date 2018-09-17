package org.witch.bongo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigLoader
{
		
	public static CatConfig loadConfig()
	{
		Gson decoder = new GsonBuilder().setPrettyPrinting().create();
		// Check config file
		File configFile = new File("config.json");
		if(!configFile.exists())
		{
			return createDefault(decoder);
		}
		
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(configFile.toURI()));
			CatConfig configBag = decoder.fromJson(new String(encoded, StandardCharsets.UTF_8), CatConfig.class);
			return configBag;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return createDefault(decoder);
		}
	}
	
	private static CatConfig createDefault(Gson decoder)
	{
		boolean writeToFile = true;
		CatConfig defaultConfig = new CatConfig();
		defaultConfig.setKey1(44);
		defaultConfig.setKey2(45);
		defaultConfig.setTopLeftX(0);
		defaultConfig.setTopLeftY(0);
		defaultConfig.setBotRightX(1920);
		defaultConfig.setBotRightY(1080);

		if(writeToFile)
		{
			File configFile = new File("config.json");
			try
			{
				if(!configFile.exists() && !configFile.createNewFile())
				{
					return defaultConfig;
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return defaultConfig;
			}
			String configString = decoder.toJson(defaultConfig, CatConfig.class);
			try
			{
				Files.write(Paths.get(configFile.toURI()), configString.getBytes(), StandardOpenOption.CREATE);
				return defaultConfig;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return defaultConfig;
			}
		}
		return defaultConfig;
	}

}

