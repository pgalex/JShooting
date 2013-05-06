package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate realization of sportsmans table
 *
 * @author pgalex
 */
public class HibernateSportsmansTable implements SportsmansTable
{
	/**
	 * Hiberbate session factory using to get access to teams
	 */
	private SessionFactory sessionFactory;

	/**
	 * Create with session
	 *
	 * @param hibernateSessionFactory hiberbate session factory using to get
	 * access to teams. Must be not null
	 * @throws IllegalArgumentException hibernateSession is null
	 */
	public HibernateSportsmansTable(SessionFactory hibernateSessionFactory) throws IllegalArgumentException
	{
		if (hibernateSessionFactory == null)
		{
			throw new IllegalArgumentException("hibernateSessionFactory is null");
		}

		sessionFactory = hibernateSessionFactory;
	}

	/**
	 * Get all sportsmans
	 *
	 * @return list all sportsmans
	 * @throws DatabaseErrorException error while getting sportsmans from database
	 */
	@Override
	public List<Sportsman> getAllSportsmans() throws DatabaseErrorException
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			List<Sportsman> sportsmans = session.createCriteria(Sportsman.class).list();
			return sportsmans;
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	/**
	 * Add new sportsman to database
	 *
	 * @param sportsmanToAdd adding sportsman
	 * @throws IllegalArgumentException sportsmanToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addSportsman(Sportsman sportsmanToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (sportsmanToAdd == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(sportsmanToAdd);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}
}
