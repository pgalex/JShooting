package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
	 * Get all sportsmans of team
	 *
	 * @param team team using to filter sportsmans
	 * @return sportsmans in given team
	 * @throws IllegalArgumentException team is null
	 * @throws DatabaseErrorException error while getting sportsmans
	 */
	@Override
	public List<Sportsman> getSportsmansInTeam(Team team) throws IllegalArgumentException, DatabaseErrorException
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			Criteria getCriteria = session.createCriteria(Sportsman.class);
			getCriteria.add(Restrictions.like("team", team));
			List<Sportsman> sportsmansInTeam = getCriteria.list();
			return sportsmansInTeam;
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
	 * @param sportsmanToAdd adding sportsman. Must be not null, team must be not
	 * null
	 * @throws IllegalArgumentException sportsmanToAdd is null or its team is null
	 */
	@Override
	public void addSportsman(Sportsman sportsmanToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (sportsmanToAdd == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd is null");
		}
		if (sportsmanToAdd.getTeam() == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd team is null");
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

	/**
	 * Update sportsman
	 *
	 * @param sportsmanToUpdate updating sportsman
	 * @throws IllegalArgumentException sportsmanToUpdate is null or its team is
	 * null
	 * @throws DatabaseErrorException error while updating
	 */
	@Override
	public void updateSportsman(Sportsman sportsmanToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		if (sportsmanToUpdate == null)
		{
			throw new IllegalArgumentException("sportsmanToUpdate is null");
		}
		if (sportsmanToUpdate.getTeam() == null)
		{
			throw new IllegalArgumentException("sportsmanToUpdate team is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(sportsmanToUpdate);
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
