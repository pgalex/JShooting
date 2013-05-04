package com.jshooting.forms;

import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for teams table. Using to edit teams from table
 *
 * @author pgalex
 */
public class TeamsTableModel extends AbstractTableModel
{
	/**
	 * Editing teams table
	 */
	private TeamsTable teamsTable;

	/**
	 * Create for editing given teams table
	 *
	 * @param editingTeamsTable editing teams table
	 * @throws IllegalArgumentException editingTeamsTable is null
	 */
	public TeamsTableModel(TeamsTable editingTeamsTable) throws IllegalArgumentException
	{
		super();

		if (editingTeamsTable == null)
		{
			throw new IllegalArgumentException("editingTeamsTable is null");
		}

		teamsTable = editingTeamsTable;
	}

	@Override
	public String getColumnName(int i)
	{
		return "Каманды";
	}

	@Override
	public int getRowCount()
	{
		try
		{
			return teamsTable.getAllTeams().size();
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
	public Object getValueAt(int i, int i1)
	{
		try
		{
			return teamsTable.getAllTeams().get(i);
		}
		catch (DatabaseErrorException ex)
		{
			return "";
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
			Team updatingTeam = teamsTable.getAllTeams().get(i);
			updatingTeam.setName((String) o);
			teamsTable.updateTeam(updatingTeam);
		}
		catch (DatabaseErrorException ex)
		{
			// do nothing
		}
	}

	/**
	 * Add new row with default value
	 */
	public void addNewRow()
	{
		try
		{
			Team newTeam = new Team();
			newTeam.setName("Новая команда");
			teamsTable.addTeam(newTeam);
			fireTableRowsInserted(teamsTable.getAllTeams().size() - 1, 0);
		}
		catch (DatabaseErrorException ex)
		{
			// do nothing
		}
	}
}
