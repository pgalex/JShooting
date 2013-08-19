package com.jshooting.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Using to make reserve copies of databases
 *
 * @author pgalex
 */
public class DatabaseReserveMaker
{
	/**
	 * Make reserve copy of database by its file name
	 *
	 * @param databaseFileName name of database file to make reserve of. Must be
	 * not null. If empty or file not exists no reverve will be created
	 * @throws IllegalArgumentException databaseFileName is null
	 */
	public static void makeReserveOfDatabase(String databaseFileName) throws IllegalArgumentException
	{
		if (databaseFileName == null)
		{
			throw new IllegalArgumentException("databaseFileName is null");
		}

		if (databaseFileName.isEmpty())
		{
			return;
		}

		final File databaseFile = new File(databaseFileName);
		if (!databaseFile.exists())
		{
			return;
		}

		try
		{
			createReserveFolderIfNotExists();

			File reserveFile = new File("reserve/" + databaseFile.getName());
			if (reserveFile.exists())
			{
				reserveFile.delete();
			}
			copyFile(databaseFile, reserveFile);
		}
		catch (IOException ex)
		{
			// do nothing
		}
	}

	private static void createReserveFolderIfNotExists()
	{
		File reserveFolder = new File("reserve");
		if (!reserveFolder.exists() || (reserveFolder.exists() && reserveFolder.isDirectory()))
		{
			reserveFolder.mkdir();
		}
	}

	private static void copyFile(File sourceFile, File destFile) throws IOException
	{
		if (!destFile.exists())
		{
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try
		{
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally
		{
			if (source != null)
			{
				source.close();
			}
			if (destination != null)
			{
				destination.close();
			}
		}
	}
}
