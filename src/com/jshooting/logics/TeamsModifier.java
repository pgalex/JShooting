package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.TeamsTable;

/**
 * Using to edit teams
 *
 * @author pgalex
 */
public class TeamsModifier
{
	/**
	 * Table of teams
	 */
	private TeamsTable teamsTable;

	/**
	 * Create with teams table
	 *
	 * @param teamsTable table of teams that will be edited. Must be not null
	 * @throws IllegalArgumentException teamsTable is null
	 */
	public TeamsModifier(TeamsTable teamsTable) throws IllegalArgumentException
	{
		if (teamsTable == null)
		{
			throw new IllegalArgumentException("teamsTable is null");
		}

		this.teamsTable = teamsTable;
	}

	/**
	 * Add new team with empty name
	 *
	 * @throws ShootingLogicsErrorException error while adding
	 */
	public void addNewTeam() throws ShootingLogicsErrorException
	{
		try
		{
			Team newTeam = new Team();
			newTeam.setName("");
			teamsTable.addTeam(newTeam);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Update existing team
	 *
	 * @param teamToUpdate updating team. Must be not null
	 * @throws IllegalArgumentException teamToUpdate is null
	 * @throws ShootingLogicsErrorException error while updating
	 */
	public void updateTeam(Team teamToUpdate) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (teamToUpdate == null)
		{
			throw new IllegalArgumentException("teamToUpdate is null");
		}
		
		try
		{
			teamsTable.updateTeam(teamToUpdate);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
