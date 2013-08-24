package com.jshooting.forms;

import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.ShootingTrainingsGetter;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.ShootingTraining;
import com.jshooting.model.ShootingTrainingType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
	 * List of trainings that model currently working with. Using for optimization
	 * accessing to database
	 */
	private List<ShootingTraining> trainings;
	/**
	 * Shooting trainings table that model works with
	 */
	private ShootingTrainingsGetter shootingTrainingsGetter;

	/**
	 * Create with logics factory
	 *
	 * @param logicsFactory logics factory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public ShootingTrainingsTableModel(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		shootingTrainingsGetter = logicsFactory.createShootingTrainingsGetter();

		updateTrainingsList();
	}

	/**
	 * Refill working trainings list
	 */
	private void updateTrainingsList()
	{
		try
		{
			trainings = shootingTrainingsGetter.getAllTrainings();
			Collections.reverse(trainings); // последняя добавленная тренировка будет отображаться наверху
		}
		catch (ShootingLogicsException ex)
		{
			trainings = new ArrayList<ShootingTraining>();
		}
	}

	public void update()
	{
		updateTrainingsList();
		fireTableDataChanged();
	}

	public void updateRow(int rowIndex) throws IllegalArgumentException
	{
		if (rowIndex < 0 || rowIndex >= trainings.size())
		{
			throw new IllegalArgumentException("rowIndex is out of range");
		}

		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public ShootingTraining getShootingTrainingAtRow(int rowIndex) throws IllegalArgumentException
	{
		if (rowIndex < 0 || rowIndex >= trainings.size())
		{
			throw new IllegalArgumentException("rowIndex is out of range");
		}

		return trainings.get(rowIndex);
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