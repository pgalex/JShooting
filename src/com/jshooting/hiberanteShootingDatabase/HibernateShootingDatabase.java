package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate powered shooting database
 *
 * @author pgalex
 */
public class HibernateShootingDatabase implements ShootingDatabase
{
	/**
	 * Hiberanate session factory
	 */
	private SessionFactory sessionFactory;
	/**
	 * File name of database
	 */
	private String fileName;
	/**
	 * Teams table
	 */
	private HiberanteTeamsTable teamsTable;

	/**
	 * Create connection to database with file name
	 *
	 * @param databaseFileName name of database file. Must be not null, not empty
	 * @throws IllegalArgumentException databaseFileName is null or empty
	 * @throws DatabaseErrorException error while getting access to database
	 */
	public HibernateShootingDatabase(String databaseFileName) throws IllegalArgumentException, DatabaseErrorException
	{
		if (databaseFileName == null)
		{
			throw new IllegalArgumentException("databaseFileName is null");
		}
		if (databaseFileName.isEmpty())
		{
			throw new IllegalArgumentException("databaseFileName is empty");
		}

		try
		{
			fileName = databaseFileName;

			Configuration hibernateConfiguration = new Configuration();
			hibernateConfiguration.configure();
			hibernateConfiguration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + databaseFileName);
			sessionFactory = hibernateConfiguration.buildSessionFactory();

			teamsTable = new HiberanteTeamsTable(sessionFactory);
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}

		if (!isDatabaseCorrect())
		{
			throw new DatabaseErrorException("incorrect database");
		}
	}

	/**
	 * Test opened database for correction
	 *
	 * @return is database correct
	 */
	private boolean isDatabaseCorrect()
	{
		return teamsTable.testTableCorrection();
	}

	/**
	 * Get teams table
	 *
	 * @return database's table of teams
	 */
	@Override
	public TeamsTable getTeamsTable()
	{
		return teamsTable;
	}

	/**
	 * Close connection to database
	 */
	@Override
	public void close()
	{
		sessionFactory.close();
	}

	/**
	 * Get database file name
	 *
	 * @return database file name
	 */
	@Override
	public String getFileName()
	{
		return fileName;
	}
}
