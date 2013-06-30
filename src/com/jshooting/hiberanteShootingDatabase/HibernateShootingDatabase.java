package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.PlacesTable;
import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import org.hibernate.Session;
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
	 * Hibernate session
	 */
	private Session session;
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
	 * Training methods table
	 */
	private TrainingMethodsTable trainingMethodsTable;
	/**
	 * Shootings trainings table
	 */
	private ShootingTrainingsTable shootingTrainingTable;
	/**
	 * Places table
	 */
	private PlacesTable placesTable;

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
			session = sessionFactory.openSession();

			teamsTable = new HibernateTeamsTable(session);
			sportsmansTable = new HibernateSportsmansTable(session);
			trainingMethodsTable = new HibernateTrainingMethodsTable(session);
			shootingTrainingTable = new HibernateShootingTrainingsTable(session);
			placesTable = new HibernatePlacesTable(session);
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
	 * Get training methods table
	 *
	 * @return database's table of training methods
	 */
	@Override
	public TrainingMethodsTable getTrainingMethodsTable()
	{
		return trainingMethodsTable;
	}

	/**
	 * Close connection to database
	 */
	@Override
	public void close()
	{
		session.close();
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

	/**
	 * Get shooting trainigs table
	 *
	 * @return database's table of shooting trainigs
	 */
	@Override
	public ShootingTrainingsTable getShootingTrainingsTable()
	{
		return shootingTrainingTable;
	}

	/**
	 * Get places table
	 *
	 * @return database's table of places
	 */
	@Override
	public PlacesTable getPlacesTable()
	{
		return placesTable;
	}
}
