package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * Teams table Hibernate realization
 *
 * @author pgalex
 */
public class HibernateTeamsTable implements TeamsTable
{
	/**
	 * Hiberbate session using to get access to teams table
	 */
	private Session session;

	/**
	 * Create with session
	 *
	 * @param session hiberbate session factory using to get access to teams
	 * table. Must be not null
	 * @throws IllegalArgumentException session is null
	 */
	public HibernateTeamsTable(Session session) throws IllegalArgumentException
	{
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		this.session = session;
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
		List<Team> teams = session.createCriteria(Team.class).list();
		return teams;
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

		session.beginTransaction();
		session.save(teamToAdd);
		session.getTransaction().commit();
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

		session.beginTransaction();
		session.update(teamToUpdate);
		session.getTransaction().commit();
	}

	/**
	 * Test table for correction
	 *
	 * @return is table correct
	 */
	public boolean testTableForCorrection()
	{
		try
		{
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
	}
}
