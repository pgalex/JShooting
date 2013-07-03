package com.jshooting.shootingDatabase;

import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Table of teams
 *
 * @author pgalex
 */
public interface TeamsTable
{
	/**
	 * Get all teams
	 *
	 * @return all teams in table. Empty if there is no teams
	 * @throws DatabaseErrorException error while getting teams
	 */
	public List<Team> getAllTeams() throws DatabaseErrorException;

	/**
	 * Add new team
	 *
	 * @param teamToAdd adding team. Must be not null
	 * @throws IllegalArgumentException teamToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addTeam(Team teamToAdd) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Update team
	 *
	 * @param teamToUpdate updating team. Must be not null
	 * @throws IllegalArgumentException teamToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	public void updateTeam(Team teamToUpdate) throws IllegalArgumentException, DatabaseErrorException;
}
