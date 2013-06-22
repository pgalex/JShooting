package com.jshooting.forms;

import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for shooting trainings table
 *
 * @author pgalex
 */
public class ShootingTrainingsTableModel extends AbstractTableModel
{
	/**
	 * Index of sportsman column
	 */
	public static final int SPORTSMAN_COLUMN_INDEX = 0;
	/**
	 * Table of shooting trainings using to fill table model
	 */
	private ShootingTrainingsTable shootingTrainingsTable;
	/**
	 * List of trainings that model currently working with
	 */
	private List<ShootingTraining> trainings;

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

		try
		{
			trainings = shootingTrainingsTable.getAllTrainings();
		}
		catch (DatabaseErrorException ex)
		{
			trainings = new ArrayList<ShootingTraining>();
		}
	}

	@Override
	public int getRowCount()
	{
		return trainings.size();
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
		// rowIndex always must be in range
		ShootingTraining trainingAtRow = trainings.get(rowIndex);

		switch (columnIndex)
		{
			case SPORTSMAN_COLUMN_INDEX:
				return trainingAtRow.getSportsman().getName();
			default:
				return null;
		}
	}
}