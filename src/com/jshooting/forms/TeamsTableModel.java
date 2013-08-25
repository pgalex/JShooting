package com.jshooting.forms;

import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.TeamsGetter;
import com.jshooting.logics.TeamsModifier;
import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Team;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for teams table. Using to edit teams from table
 *
 * @author pgalex
 */
public class TeamsTableModel extends AbstractTableModel
{
	/**
	 * Index of team name column
	 */
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Using to edit teams
	 */
	private TeamsModifier teamsModifier;
	/**
	 * Using to get teams
	 */
	private TeamsGetter teamsGetter;
	/**
	 * List of teams, using to optimize access to database
	 */
	private List<Team> teams;

	/**
	 * Create with logics factory
	 *
	 * @param logicsFactory editing teams table
	 * @throws IllegalArgumentException editingTeamsTable is null
	 */
	public TeamsTableModel(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		super();

		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("editingTeamsTable is null");
		}

		teamsModifier = logicsFactory.createTeamsModifier();
		teamsGetter = logicsFactory.createTeamsGetter();
		updateTeamsList();
	}

	/**
	 * Update cached list of teams
	 */
	private void updateTeamsList()
	{
		try
		{
			teams = teamsGetter.getAllTeams();
		}
		catch (ShootingLogicsErrorException ex)
		{
			teams = new ArrayList<Team>();
		}
	}

	@Override
	public String getColumnName(int i)
	{
		return "Каманды";
	}

	@Override
	public int getRowCount()
	{
		return teams.size();
	}

	@Override
	public int getColumnCount()
	{
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (teams.size() > 0)
		{
			return teams.get(rowIndex);
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
			if (rowIndex < teams.size())
			{
				Team updatingTeam = teams.get(rowIndex);
				updatingTeam.setName((String) newValue);
				teamsModifier.updateTeam(updatingTeam);
				updateTeamsList();
			}
			else
			{
				// do nothing
			}
		}
		catch (ShootingLogicsErrorException ex)
		{
			updateTeamsList();
			
		}
	}

	/**
	 * Add new row with default value
	 */
	public void addNewTeam()
	{
		try
		{
			teamsModifier.addNewTeam();
			updateTeamsList();
			fireTableRowsInserted(teams.size() - 1, teams.size() - 1);
		}
		catch (ShootingLogicsErrorException ex)
		{
			// do nothing
		}
	}
}
