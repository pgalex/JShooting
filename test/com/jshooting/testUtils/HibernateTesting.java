package com.jshooting.testUtils;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Common utils for testing classes works with hibernate
 *
 * @author pgalex
 */
public class HibernateTesting
{
	/**
	 * Create hibernate session factory by SQLite database with fileName
	 *
	 * @param fileName name of database file
	 * @return hibernate session factory
	 * @throws DatabaseErrorException error while creating factory
	 */
	public static SessionFactory createSessionFactoryByFile(String fileName) throws DatabaseErrorException
	{
		try
		{
			Configuration hibernateConfiguration = new Configuration();
			hibernateConfiguration.configure();
			hibernateConfiguration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + fileName);
			return hibernateConfiguration.buildSessionFactory();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
	}
}
