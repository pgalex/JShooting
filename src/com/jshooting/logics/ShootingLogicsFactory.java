package com.jshooting.logics;

import com.jshooting.shootingDatabase.ShootingDatabase;

/**
 * Creates objects of shooting logics
 *
 * @author pgalex
 */
public class ShootingLogicsFactory
{
	/**
	 * Shooting database, that logics clasess works with
	 */
	private ShootingDatabase shootingDatabase;

	/**
	 * Create with shooting database
	 *
	 * @param shootingDatabase shooting database, that logics clasess works with.
	 * Must be not null
	 * @throws IllegalArgumentException shootingDatabase is null
	 */
	public ShootingLogicsFactory(ShootingDatabase shootingDatabase) throws IllegalArgumentException
	{
		if (shootingDatabase == null)
		{
			throw new IllegalArgumentException("shootingDatabase is null");
		}

		this.shootingDatabase = shootingDatabase;
	}

	/**
	 * Create new sportsmans modifier
	 *
	 * @return sportsmans modifier
	 */
	public SportsmansModifier createSportsmansModifier()
	{
		return new SportsmansModifier(shootingDatabase.getSportsmansTable());
	}

	public SportsmansByTeamGetter createSportsmansByTeamGetter()
	{
		return new SportsmansByTeamGetter(shootingDatabase.getSportsmansTable());
	}

	public TeamsGetter createTeamsGetter()
	{
		return new TeamsGetter(shootingDatabase.getTeamsTable());
	}

	public TeamsModifier createTeamsModifier()
	{
		return new TeamsModifier(shootingDatabase.getTeamsTable());
	}

	public PlacesGetter createPlacesGetter()
	{
		return new PlacesGetter(shootingDatabase.getPlacesTable());
	}

	public PlacesModifier createPlacesModifier()
	{
		return new PlacesModifier(shootingDatabase.getPlacesTable());
	}

	public TrainingMethodsGetter createTrainingMethodsGetter()
	{
		return new TrainingMethodsGetter(shootingDatabase.getTrainingMethodsTable());
	}

	public TrainingMethodsModifier createTrainingMethodsModifier()
	{
		return new TrainingMethodsModifier(shootingDatabase.getTrainingMethodsTable());
	}

	public ShootingTrainingsModifier createShootingTrainingsModifier()
	{
		return new ShootingTrainingsModifier(shootingDatabase.getShootingTrainingsTable());
	}

	public ShootingTrainingsGetter createShootingTrainingsGetter()
	{
		return new ShootingTrainingsGetter(shootingDatabase.getShootingTrainingsTable());
	}
}
