package com.jshooting.shootingDatabase;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.Collection;

/**
 * Shooting database teams table
 *
 * @author pgalex
 */
public interface TeamsTable
{
	/**
	 * Get all teams from table
	 *
	 * @return all teams
	 * @throws DatabaseErrorException error while getting teams from database
	 */
	public Collection<Team> getAllTeams() throws DatabaseErrorException;

	/**
	 * Add team to database
	 *
	 * @param teamToAdd adding team
	 * @throws IllegalArgumentException teamToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addTeam(Team teamToAdd) throws IllegalArgumentException, DatabaseErrorException;
}
