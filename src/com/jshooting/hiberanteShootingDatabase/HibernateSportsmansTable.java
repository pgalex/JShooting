package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.model.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate realization of sportsmans table
 *
 * @author pgalex
 */
public class HibernateSportsmansTable implements SportsmansTable
{
	/**
	 * Hiberbate session using to get access to sportsmans table
	 */
	private Session session;

	/**
	 * Create with session
	 *
	 * @param session hiberbate session using to get access to sportsmans table.
	 * Must be not null
	 * @throws IllegalArgumentException session is null
	 */
	public HibernateSportsmansTable(Session session) throws IllegalArgumentException
	{
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		this.session = session;
	}

	/**
	 * Get all sportsmans
	 *
	 * @return list of all sportsmans. Empty of there is no sportsmans
	 * @throws DatabaseErrorException error while getting sportsmans
	 */
	@Override
	public List<Sportsman> getAllSportsmans() throws DatabaseErrorException
	{
		List<Sportsman> sportsmans = session.createCriteria(Sportsman.class).list();
		return sportsmans;
	}

	/**
	 * Add new sportsman
	 *
	 * @param sportsmanToAdd adding sportsman. Must be not null, team must be not
	 * null
	 * @throws IllegalArgumentException sportsmanToAdd is null or its team is null
	 * @throws DatabaseErrorException error while adding
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

		session.beginTransaction();
		session.save(sportsmanToAdd);
		session.getTransaction().commit();
	}

	/**
	 * Update sportsman
	 *
	 * @param sportsmanToUpdate updating sportsman. Must be not null team must be
	 * not null
	 * @throws IllegalArgumentException sportsmanToUpdate is null or its teams is
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

		session.beginTransaction();
		session.update(sportsmanToUpdate);
		session.getTransaction().commit();
	}

	/**
	 * Get all sportsmans of team
	 *
	 * @param team team using to filter sportsmans. Must be not null
	 * @return sportsmans in given team. Empty if there is no sportsmans in given
	 * team
	 * @throws IllegalArgumentException team is null
	 * @throws DatabaseErrorException error while getting sportsmans
	 */
	@Override
	public List<Sportsman> getSportsmansInTeam(Team team) throws IllegalArgumentException, DatabaseErrorException
	{
		Criteria getCriteria = session.createCriteria(Sportsman.class);
		getCriteria.add(Restrictions.like("team", team));
		List<Sportsman> sportsmansInTeam = getCriteria.list();
		return sportsmansInTeam;
	}
}
