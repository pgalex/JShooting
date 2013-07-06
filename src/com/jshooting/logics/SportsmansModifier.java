package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.SportsmansTable;

/**
 * Modifier of sportsmans
 *
 * @author pgalex
 */
public class SportsmansModifier
{
	/**
	 * Table of sportsmans
	 */
	private SportsmansTable sportsmansTable;

	/**
	 * Create with sportsmans table
	 *
	 * @param sportsmansTable table of sportsmans. Must be not null
	 * @throws IllegalArgumentException sportsmansTable is null
	 */
	public SportsmansModifier(SportsmansTable sportsmansTable) throws IllegalArgumentException
	{
		if (sportsmansTable == null)
		{
			throw new IllegalArgumentException("sportsmansTable is null");
		}
		
		this.sportsmansTable = sportsmansTable;
	}

	/**
	 * Create and add new sportsman with given team
	 *
	 * @param newSportsmanTeam team of new sportsmans. Must be not null
	 * @throws IllegalArgumentException newSportsmanTeam is null
	 * @throws ShootingLogicsException error while adding sportsman
	 */
	public void addNewSportsmanWithTeam(Team newSportsmanTeam) throws IllegalArgumentException, ShootingLogicsException
	{
		if (newSportsmanTeam == null)
		{
			throw new IllegalArgumentException("newSportsmanTeam is null");
		}
		
		try
		{
			Sportsman newSportsman = new Sportsman();
			newSportsman.setName("");
			newSportsman.setTeam(newSportsmanTeam);
			sportsmansTable.addSportsman(newSportsman);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
