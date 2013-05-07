package com.jshooting.forms;

import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for sportsmans table
 *
 * @author pgalex
 */
public class SportsmansTableModel extends AbstractTableModel
{
	/**
	 * Sportsman table using to fill model
	 */
	private SportsmansTable sportsmansTable;

	/**
	 * Create by sportsmans table
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
	}

	@Override
	public int getRowCount()
	{
		try
		{
			return sportsmansTable.getAllSportsmans().size();
		}
		catch (DatabaseErrorException ex)
		{
			return 0;
		}
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
		try
		{
			return sportsmansTable.getAllSportsmans().get(i);
		}
		catch (DatabaseErrorException ex)
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
			Sportsman updatingSportsman = sportsmansTable.getAllSportsmans().get(i);
			updatingSportsman.setName((String) o);
			sportsmansTable.updateSportsman(updatingSportsman);
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
	public void addSportsman(Sportsman sportsmanToAdd) throws IllegalArgumentException
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
