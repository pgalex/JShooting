package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.SportsmansTable;
import java.util.List;

/**
 * Using to get sportsmans
 *
 * @author pgalex
 */
public class SportsmansGetter
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
	public SportsmansGetter(SportsmansTable sportsmansTable) throws IllegalArgumentException
	{
		if (sportsmansTable == null)
		{
			throw new IllegalArgumentException("sportsmansTable is null");
		}

		this.sportsmansTable = sportsmansTable;
	}

	/**
	 * Get sportsmans in team
	 *
	 * @param team team to get sportsmans in. Must be not null
	 * @return list of sportsmans in team
	 * @throws IllegalArgumentException team is null
	 * @throws ShootingLogicsException error while getting sportsmans
	 */
	public List<Sportsman> getSportsmansInTeam(Team team) throws IllegalArgumentException, ShootingLogicsException
	{
		if (team == null)
		{
			throw new IllegalArgumentException("team is null");
		}

		try
		{
			return sportsmansTable.getSportsmansInTeam(team);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
