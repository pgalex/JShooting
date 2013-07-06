package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.SportsmansTable;
import java.util.ArrayList;
import java.util.List;

/**
 * Get sportsmans filtered by team
 *
 * @author pgalex
 */
public class SportsmansByTeamGetter
{
	/**
	 * Table of sportsmans
	 */
	private SportsmansTable sportsmansTable;
	/**
	 * Team which sportsmans need to show. If null - show all sportsmans
	 */
	private Team filteringTeam;

	/**
	 * Create with sportsmans table with null filtering team
	 *
	 * @param sportsmansTable table of sportsmans. Must be not null
	 * @throws IllegalArgumentException sportsmansTable is null
	 */
	public SportsmansByTeamGetter(SportsmansTable sportsmansTable) throws IllegalArgumentException
	{
		if (sportsmansTable == null)
		{
			throw new IllegalArgumentException("sportsmansTable is null");
		}

		this.filteringTeam = null;
		this.sportsmansTable = sportsmansTable;
	}

	/**
	 * Set team which sportsmans need to get from table
	 *
	 * @param filteringTeamToSet team which sportsmans need to get from sportsmans
	 * table. If filtering team is null, no sportsmans will be get
	 */
	public void setFilteringTeam(Team filteringTeamToSet)
	{
		filteringTeam = filteringTeamToSet;
	}

	/**
	 * Get sportsmans in filtering team
	 *
	 * @return list of sportsmans in filtering team. Empty if filtering team is
	 * null
	 * @throws ShootingLogicsException error while getting sportsmans
	 */
	public List<Sportsman> getSportsmansInFilteringTeam() throws ShootingLogicsException
	{
		try
		{
			if (filteringTeam != null)
			{
				return sportsmansTable.getSportsmansInTeam(filteringTeam);
			}
			else
			{
				return new ArrayList<Sportsman>();
			}
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
