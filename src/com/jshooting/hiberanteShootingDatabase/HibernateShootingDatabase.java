package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.TeamsTable;
import org.hibernate.Session;
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
	 * Hiberanate session for shooting database
	 */
	private Session session;
	/**
	 * File name of database
	 */
	private String fileName;

	/**
	 * Create connection to database with file name
	 *
	 * @param databaseFileName name of database file. Must be not null, not empty
	 * @throws IllegalArgumentException databaseFileName is null or empty
	 */
	public HibernateShootingDatabase(String databaseFileName) throws IllegalArgumentException
	{
		if (databaseFileName == null)
		{
			throw new IllegalArgumentException("databaseFileName is null");
		}
		if (databaseFileName.isEmpty())
		{
			throw new IllegalArgumentException("databaseFileName is empty");
		}

		fileName = databaseFileName;

		Configuration hibernateConfiguration = new Configuration();
		hibernateConfiguration.configure();
		hibernateConfiguration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + databaseFileName);
		sessionFactory = hibernateConfiguration.buildSessionFactory();

		session = sessionFactory.openSession();
	}

	/**
	 * Get teams table
	 *
	 * @return database's table of teams
	 */
	@Override
	public TeamsTable getTeamsTable()
	{
		return null;
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
}
