package com.jshooting.forms;

import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for shooting trainings table
 *
 * @author pgalex
 */
public class ShootingTrainingsTableModel extends AbstractTableModel
{
	public static final int SPORTSMAN_COLUMN_INDEX = 0;
	/**
	 * Table of shooting trainings using to fill table model
	 */
	private ShootingTrainingsTable shootingTrainingsTable;

	/**
	 * Create model filling with trainings table
	 *
	 * @param shootingTrainingsTable table of shooting trainings using to fill.
	 * Must be not null table model
	 * @throws IllegalArgumentException shootingTrainingsTable is null
	 */
	public ShootingTrainingsTableModel(ShootingTrainingsTable shootingTrainingsTable) throws IllegalArgumentException
	{
		if (shootingTrainingsTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingsTable is null");
		}

		this.shootingTrainingsTable = shootingTrainingsTable;
	}

	@Override
	public int getRowCount()
	{
		try
		{
			return shootingTrainingsTable.getAllTrainings().size();
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
		switch (i)
		{
			case SPORTSMAN_COLUMN_INDEX:
				return "Спортсмен";
			default:
				return "<>";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		try
		{
			// rowIndex always must be in range
			ShootingTraining trainingAtRow = shootingTrainingsTable.getAllTrainings().get(rowIndex);

			switch (columnIndex)
			{
				case SPORTSMAN_COLUMN_INDEX:
					return trainingAtRow.getSportsman().getName();
				default:
					return null;
			}
		}
		catch (DatabaseErrorException ex)
		{
			return null;
		}
	}
}