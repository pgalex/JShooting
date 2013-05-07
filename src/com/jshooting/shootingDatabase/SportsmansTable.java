package com.jshooting.shootingDatabase;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Database table of sportsmans
 *
 * @author pgalex
 */
public interface SportsmansTable
{
	/**
	 * Get all sportsmans
	 *
	 * @return list all sportsmans
	 * @throws DatabaseErrorException error while getting sportsmans from database
	 */
	public List<Sportsman> getAllSportsmans() throws DatabaseErrorException;

	/**
	 * Add new sportsman to database
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
	 * @param sportsmanToUpdate updating sportsman
	 * @throws IllegalArgumentException sportsmanToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	public void updateSportsman(Sportsman sportsmanToUpdate) throws IllegalArgumentException, DatabaseErrorException;
}
