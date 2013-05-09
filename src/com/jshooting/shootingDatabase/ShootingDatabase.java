package com.jshooting.shootingDatabase;

/**
 * Shooting trainings database
 *
 * @author pgalex
 */
public interface ShootingDatabase
{
	/**
	 * Get teams table
	 *
	 * @return database's table of teams
	 */
	public TeamsTable getTeamsTable();

	/**
	 * Get sportsmans table
	 *
	 * @return database's table of sportsmans
	 */
	public SportsmansTable getSportsmansTable();

	/**
	 * Close connection to database
	 */
	public void close();

	/**
	 * Get database file name
	 *
	 * @return database file name
	 */
	public String getFileName();
}
