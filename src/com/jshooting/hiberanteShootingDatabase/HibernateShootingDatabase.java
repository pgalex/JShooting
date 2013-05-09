package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Shooting database Hibernate realization
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
	 * Name of database file
	 */
	private String fileName;
	/**
	 * Teams table
	 */
	private HibernateTeamsTable teamsTable;
	/**
	 * Sportsmans table
	 */
	private HibernateSportsmansTable sportsmansTable;

	/**
	 * Create connection to database by file name
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

			teamsTable = new HibernateTeamsTable(sessionFactory);
			sportsmansTable = new HibernateSportsmansTable(sessionFactory);
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
		return teamsTable.testTableForCorrection();
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
	 * Get sportsmans table
	 *
	 * @return database's table of sportsmans
	 */
	@Override
	public SportsmansTable getSportsmansTable()
	{
		return sportsmansTable;
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
