package com.jshooting.forms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Save settings between application runnings. Singleton
 *
 * @author pgalex
 */
public class UserSettings
{
	/**
	 * Version for input/output
	 */
	private static final int IO_VESION = 0;
	/**
	 * Single instance of settings
	 */
	private static UserSettings instance = null;
	/**
	 * Saving database file name
	 */
	private String databaseFileName;

	/**
	 * Create with default values
	 */
	private UserSettings()
	{
		databaseFileName = "";
	}

	/**
	 * Set all user settings to default values
	 */
	public static void setAsDefault()
	{
		instance = new UserSettings();
	}

	/**
	 * Get instance of settings
	 *
	 * @return instance of settings
	 */
	public static UserSettings getInstance()
	{
		if (instance == null)
		{
			instance = new UserSettings();
		}

		return instance;
	}

	/**
	 * Read settings from file
	 *
	 * @param file file to read settings from
	 * @throws IOException error while reading
	 */
	public void readFromFile(File file) throws IOException
	{
		try
		{
			DataInputStream intput = new DataInputStream(new FileInputStream(file));
			int version = intput.readInt();
			databaseFileName = intput.readUTF();
			intput.close();
		}
		catch (Exception ex)
		{
			throw new IOException(ex);
		}
	}

	/**
	 * Write settings to file
	 *
	 * @param file file to write setting to
	 * @throws IOException error while writing
	 */
	public void writeToFile(File file) throws IOException
	{
		try
		{
			DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
			output.writeInt(IO_VESION);
			output.writeUTF(databaseFileName);
			output.close();
		}
		catch (Exception ex)
		{
			throw new IOException(ex);
		}
	}

	/**
	 * Set database file name to save
	 *
	 * @param databaseFileNameToSave saving database file name
	 * @throws IllegalArgumentException databaseFileNameToSave is null
	 */
	public void setDatabaseFileName(String databaseFileNameToSave) throws IllegalArgumentException
	{
		if (databaseFileNameToSave == null)
		{
			throw new IllegalArgumentException("databaseFileNameToSave is null");
		}
		databaseFileName = databaseFileNameToSave;
	}

	/**
	 * Get saved database file name
	 *
	 * @return saved database file name.
	 */
	public String getDatabaseFileName()
	{
		return databaseFileName;
	}
}
