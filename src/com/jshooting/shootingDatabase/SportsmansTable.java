package com.jshooting.shootingDatabase;

import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Table of sportsmans
 *
 * @author pgalex
 */
public interface SportsmansTable
{
	/**
	 * Get all sportsmans
	 *
	 * @return list of all sportsmans. Empty of there is no sportsmans
	 * @throws DatabaseErrorException error while getting sportsmans
	 */
	public List<Sportsman> getAllSportsmans() throws DatabaseErrorException;

	/**
	 * Add new sportsman
	 *
	 * @param sportsmanToAdd adding sportsman. Must be not null, team must be not
	 * null
	 * @throws IllegalArgumentException sportsmanToAdd is null or its team is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addSportsman(Sportsman sportsmanToAdd) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Update sportsman
	 *
	 * @param sportsmanToUpdate updating sportsman. Must be not null team must be
	 * not null
	 * @throws IllegalArgumentException sportsmanToUpdate is null or its teams is
	 * null
	 * @throws DatabaseErrorException error while updating
	 */
	public void updateSportsman(Sportsman sportsmanToUpdate) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Get all sportsmans of team
	 *
	 * @param team team using to filter sportsmans. Must be not null
	 * @return sportsmans in given team. Empty if there is no sportsmans in given
	 * team
	 * @throws IllegalArgumentException team is null
	 * @throws DatabaseErrorException error while getting sportsmans
	 */
	public List<Sportsman> getSportsmansInTeam(Team team) throws IllegalArgumentException, DatabaseErrorException;
}
