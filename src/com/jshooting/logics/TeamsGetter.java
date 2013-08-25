package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import java.util.List;

/**
 * Gets teams from database
 *
 * @author pgalex
 */
public class TeamsGetter
{
	/**
	 * Table of teams
	 */
	private TeamsTable teamsTable;

	/**
	 * Create with teams table
	 *
	 * @param teamsTable table of teams. Must be not null
	 * @throws IllegalArgumentException teamsTable is null
	 */
	public TeamsGetter(TeamsTable teamsTable) throws IllegalArgumentException
	{
		if (teamsTable == null)
		{
			throw new IllegalArgumentException("teamsTable is null");
		}

		this.teamsTable = teamsTable;
	}

	/**
	 * Get all teams
	 *
	 * @return list of all teams
	 * @throws ShootingLogicsErrorException error while getting teams
	 */
	public List<Team> getAllTeams() throws ShootingLogicsErrorException
	{
		try
		{
			return teamsTable.getAllTeams();
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
