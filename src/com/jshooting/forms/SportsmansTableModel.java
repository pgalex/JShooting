package com.jshooting.forms;

import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.SportsmansByTeamGetter;
import com.jshooting.logics.SportsmansModifier;
import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
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
	private SportsmansByTeamGetter sportsmansGetter;
	/**
	 * Using to edit sportsmans
	 */
	private SportsmansModifier sportsmansModifier;
	/**
	 * List of sportsmans in filtering team. Using to optimize access to database
	 */
	private List<Sportsman> sportsmanInFilteringTeam;

	/**
	 * Create by sportsmans table with null filtering team
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

		sportsmansGetter = logicsFactory.createSportsmansByTeamGetter();
		sportsmansModifier = logicsFactory.createSportsmansModifier();
		updateSportsmansList();
	}

	/**
	 * Set team which sportsmans need to get from table
	 *
	 * @param filteringTeamToSet team which sportsmans need to get from sportsmans
	 * table. If filtering team is null, model will be empty
	 */
	public void setFilteringTeam(Team filteringTeamToSet)
	{
		sportsmansGetter.setFilteringTeam(filteringTeamToSet);
		updateSportsmansList();
		fireTableDataChanged();
	}

	/**
	 * Fill sportsmans list by filtering team from database
	 *
	 */
	private void updateSportsmansList()
	{
		try
		{
			sportsmanInFilteringTeam = sportsmansGetter.getSportsmansInFilteringTeam();
		}
		catch (ShootingLogicsErrorException ex)
		{
			sportsmanInFilteringTeam = new ArrayList<Sportsman>();
		}
	}

	@Override
	public int getRowCount()
	{
		return sportsmanInFilteringTeam.size();
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
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (sportsmanInFilteringTeam.size() > 0)
		{
			return sportsmanInFilteringTeam.get(rowIndex);
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
	public void setValueAt(Object newValue, int rowIndex, int columnIndex)
	{
		try
		{
			if (rowIndex < sportsmanInFilteringTeam.size())
			{
				Sportsman updatingSportsman = sportsmanInFilteringTeam.get(rowIndex);
				updatingSportsman.setName((String) newValue);
				sportsmansModifier.updateSportsman(updatingSportsman);
				updateSportsmansList();
			}
			else
			{
				// do nothing
			}
		}
		catch (ShootingLogicsErrorException ex)
		{
			updateSportsmansList();
			fireTableCellUpdated(rowIndex, columnIndex);
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
			throw new IllegalArgumentException("newSportsmanTeam is null");
		}

		try
		{
			sportsmansModifier.addNewSportsmanWithTeam(newSportsmanTeam);
			updateSportsmansList();
			fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		}
		catch (ShootingLogicsErrorException ex)
		{
			// do nothing
		}
	}
}
