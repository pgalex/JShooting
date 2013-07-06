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
	 * Create new sportsmans getter
	 *
	 * @return sportsmans getter
	 */
	public SportsmansGetter createSportsmansGetter()
	{
		return new SportsmansGetter(shootingDatabase.getSportsmansTable());
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
}
