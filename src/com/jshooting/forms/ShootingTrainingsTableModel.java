package com.jshooting.forms;

import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingType;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
	 * Index of date column
	 */
	public static final int DATE_COLUMN_INDEX = 1;
	/**
	 * Index of type column
	 */
	public static final int TYPE_COLUMN_INDEX = 2;
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
		return 3;
	}

	@Override
	public String getColumnName(int i)
	{
		switch (i)
		{
			case SPORTSMAN_COLUMN_INDEX:
				return "Спортсмен";
			case DATE_COLUMN_INDEX:
				return "Дата";
			case TYPE_COLUMN_INDEX:
				return "Тип тренировки";
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
			case DATE_COLUMN_INDEX:
				return (new SimpleDateFormat("dd.MM.yyyy")).format(trainingAtRow.getDate());
			case TYPE_COLUMN_INDEX:
				return ShootingTrainingType.toString(trainingAtRow.getType());
			default:
				return null;
		}
	}

	@Override
	public boolean isCellEditable(int i, int i1)
	{
		return false;
	}
}