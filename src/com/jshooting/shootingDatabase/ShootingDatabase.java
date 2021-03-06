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
	 * Get training methods table
	 *
	 * @return database's table of training methods
	 */
	public TrainingMethodsTable getTrainingMethodsTable();

	/**
	 * Get shooting trainigs table
	 *
	 * @return database's table of shooting trainigs
	 */
	public ShootingTrainingsTable getShootingTrainingsTable();

	/**
	 * Get places table
	 *
	 * @return database's table of places
	 */
	public PlacesTable getPlacesTable();

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
