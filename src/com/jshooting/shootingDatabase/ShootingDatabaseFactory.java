package com.jshooting.shootingDatabase;

import com.jshooting.hiberanteShootingDatabase.HibernateShootingDatabase;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;

/**
 * Factory of shooting database
 *
 * @author pgalex
 */
public class ShootingDatabaseFactory
{
	/**
	 * Open shooting database from file with given file name. If file not exists,
	 * database will be created
	 *
	 * @param fileName name of database file. Must be not null, not empty
	 * @return shooting database associated with given file
	 * @throws IllegalArgumentException fileName is null or empry
	 * @throws DatabaseErrorException error while opening connection to database
	 */
	public static ShootingDatabase openDatabaseFromFile(String fileName) throws IllegalArgumentException, DatabaseErrorException
	{
		if (fileName == null)
		{
			throw new IllegalArgumentException("fileName is null");
		}
		if (fileName.isEmpty())
		{
			throw new IllegalArgumentException("fileName is empty");
		}

		return new HibernateShootingDatabase(fileName);
	}
}
