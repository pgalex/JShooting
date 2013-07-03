package com.jshooting.forms;

import com.jshooting.model.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.model.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for sportsmans table
 *
 * @author pgalex
 */
public class SportsmansTableModel extends AbstractTableModel
{
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Sportsman table using to fill model
	 */
	private SportsmansTable sportsmansTable;
	/**
	 * Team which sportsmans need to show. If null - show all sportsmans
	 */
	private Team filteringTeam;

	/**
	 * Create by sportsmans table with not filtering by team
	 *
	 * @param sportsmansTable Sportsman table using to fill model
	 * @throws IllegalArgumentException sportsmansTable is null
	 */
	public SportsmansTableModel(SportsmansTable sportsmansTable) throws IllegalArgumentException
	{
		if (sportsmansTable == null)
		{
			throw new IllegalArgumentException("sportsmansTable is null");
		}

		this.sportsmansTable = sportsmansTable;
		this.filteringTeam = null;
	}

	/**
	 * Set team which sportsmans need to get from table
	 *
	 * @param filteringTeamToSet team which sportsmans need to get from sportsmans
	 * table
	 * @throws IllegalArgumentException filteringTeam is null
	 */
	public void setFilteringTeam(Team filteringTeamToSet) throws IllegalArgumentException
	{
		if (filteringTeamToSet == null)
		{
			throw new IllegalArgumentException("filteringTeam is null");
		}

		filteringTeam = filteringTeamToSet;
		fireTableDataChanged();
	}

	/**
	 * Get sportsmans from sportsmans table using filtering team
	 *
	 * @return sportsmans get using filtering team. Empty if there is no
	 * sportsmans
	 */
	private List<Sportsman> getSportsmansByTeamFilter()
	{
		try
		{
			if (filteringTeam != null)
			{
				return sportsmansTable.getSportsmansInTeam(filteringTeam);
			}
			else
			{
				return sportsmansTable.getAllSportsmans();
			}
		}
		catch (DatabaseErrorException ex)
		{
			return new ArrayList<Sportsman>();
		}
	}

	@Override
	public int getRowCount()
	{
		return getSportsmansByTeamFilter().size();
	}

	@Override
	public int getColumnCount()
	{
		return 1;
	}

	@Override
	public String getColumnName(int i)
	{
		return "Имя";
	}

	@Override
	public Object getValueAt(int i, int i1)
	{
		List<Sportsman> sportsmans = getSportsmansByTeamFilter();
		if (sportsmans.size() > 0)
		{
			return sportsmans.get(i);
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int i, int i1)
	{
		return true;
	}

	@Override
	public void setValueAt(Object o, int i, int i1)
	{
		try
		{
			List<Sportsman> sportsmans = getSportsmansByTeamFilter();
			if (i < sportsmans.size())
			{
				Sportsman updatingSportsman = sportsmans.get(i);
				updatingSportsman.setName((String) o);
				sportsmansTable.updateSportsman(updatingSportsman);
			}
			else
			{
				// do nothing
			}
		}
		catch (DatabaseErrorException ex)
		{
			// do nothing
		}
	}

	/**
	 * Add new sportsman
	 *
	 * @param sportsmanToAdd adding sportsman
	 * @throws IllegalArgumentException sportsmanToAdd is null or its team is null
	 */
	public void addNewSportsman(Sportsman sportsmanToAdd) throws IllegalArgumentException
	{
		if (sportsmanToAdd == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd is null");
		}
		if (sportsmanToAdd.getTeam() == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd team is null");
		}

		try
		{
			sportsmansTable.addSportsman(sportsmanToAdd);
			fireTableRowsInserted(sportsmansTable.getAllSportsmans().size() - 1,
							sportsmansTable.getAllSportsmans().size() - 1);
		}
		catch (DatabaseErrorException ex)
		{
			// do nothing
		}
	}
}
