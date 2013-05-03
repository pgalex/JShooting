package com.jshooting.shootingDatabase;

/**
 * Shooting database
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
	 * Close connection to database
	 */
	public void close();
	/**
	 * Get database file name
	 * @return database file name
	 */
	public String getFileName();
}
