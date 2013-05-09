package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * Teams table Hibernate realization
 *
 * @author pgalex
 */
public class HibernateTeamsTable implements TeamsTable
{
	/**
	 * Hiberbate session factory using to get access to teams table
	 */
	private SessionFactory sessionFactory;

	/**
	 * Create by session factory
	 *
	 * @param hibernateSessionFactory hiberbate session factory using to get
	 * access to teams table. Must be not null
	 * @throws IllegalArgumentException hibernateSession is null
	 */
	public HibernateTeamsTable(SessionFactory hibernateSessionFactory) throws IllegalArgumentException
	{
		if (hibernateSessionFactory == null)
		{
			throw new IllegalArgumentException("hibernateSessionFactory is null");
		}

		sessionFactory = hibernateSessionFactory;
	}

	/**
	 * Get all teams
	 *
	 * @return all teams in table. Empty if there is no teams
	 * @throws DatabaseErrorException error while getting teams
	 */
	@Override
	public List<Team> getAllTeams() throws DatabaseErrorException
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			List<Team> teams = session.createCriteria(Team.class).list();
			return teams;
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
	 * Add new team
	 *
	 * @param teamToAdd adding team. Must be not null
	 * @throws IllegalArgumentException teamToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addTeam(Team teamToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (teamToAdd == null)
		{
			throw new IllegalArgumentException("teamToAdd is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(teamToAdd);
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
	 * Update team
	 *
	 * @param teamToUpdate updating team. Must be not null
	 * @throws IllegalArgumentException teamToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	@Override
	public void updateTeam(Team teamToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		if (teamToUpdate == null)
		{
			throw new IllegalArgumentException("teamToUpdate is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(teamToUpdate);
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
	 * Test table for correction
	 *
	 * @return is table correct
	 */
	public boolean testTableForCorrection()
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Team testTeam = new Team();
			testTeam.setName("testTeam");
			session.save(testTeam);
			session.getTransaction().commit();
			session.flush();

			session.beginTransaction();
			session.delete(testTeam);
			session.getTransaction().commit();

			return true;
		}
		catch (Exception ex)
		{
			return false;
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
