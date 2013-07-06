package com.jshooting.forms;

import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.SportsmansGetter;
import com.jshooting.logics.SportsmansModifier;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
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
	/**
	 * Index of sportsman name column
	 */
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Using to get sportsmans with criterias
	 */
	private SportsmansGetter sportsmansGetter;
	/**
	 * Using to edit sportsmans
	 */
	private SportsmansModifier sportsmansModifier;
	/**
	 * Team which sportsmans need to show. If null - show all sportsmans
	 */
	private Team filteringTeam;

	/**
	 * Create by sportsmans table with not filtering by team
	 *
	 * @param logicsFactory shooting logics factory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public SportsmansTableModel(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		sportsmansGetter = logicsFactory.createSportsmansGetter();
		sportsmansModifier = logicsFactory.createSportsmansModifier();
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
				return sportsmansGetter.getSportsmansInTeam(filteringTeam);
			}
			else
			{
				return new ArrayList<Sportsman>();
			}
		}
		catch (ShootingLogicsException ex)
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
				sportsmansModifier.updateSportsman(updatingSportsman);
			}
			else
			{
				// do nothing
			}
		}
		catch (ShootingLogicsException ex)
		{
			// do nothing
		}
	}

	/**
	 * Add new sportsman
	 *
	 * @param newSportsmanTeam team of new sportsmans. Must be not null
	 * @throws IllegalArgumentException newSportsmanTeam is null
	 */
	public void addNewSportsman(Team newSportsmanTeam) throws IllegalArgumentException
	{
		if (newSportsmanTeam == null)
		{
			throw new IllegalArgumentException("sportsmanToAdd is null");
		}

		try
		{
			sportsmansModifier.addNewSportsmanWithTeam(newSportsmanTeam);
			fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		}
		catch (ShootingLogicsException ex)
		{
			// do nothing
		}
	}
}
