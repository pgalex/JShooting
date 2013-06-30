package com.jshooting.forms;

import com.jshooting.shootingDatabase.TrainingMethod;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for training methods table
 *
 * @author pgalex
 */
public class TrainingMethodsTableModel extends AbstractTableModel
{
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Editing training methods table
	 */
	private TrainingMethodsTable trainingMethodsTable;

	/**
	 * Create for editing training methods table
	 *
	 * @param editingTable editing training methods table. Must be not null
	 * @throws IllegalArgumentException editingTable is null
	 */
	public TrainingMethodsTableModel(TrainingMethodsTable editingTable) throws IllegalArgumentException
	{
		if (editingTable == null)
		{
			throw new IllegalArgumentException("editingTable is null");
		}

		trainingMethodsTable = editingTable;
	}

	@Override
	public String getColumnName(int i)
	{
		return "Средства";
	}

	@Override
	public int getRowCount()
	{
		try
		{
			return trainingMethodsTable.getAllTrainingMethods().size();
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
			List<TrainingMethod> allMethods = trainingMethodsTable.getAllTrainingMethods();
			if (!allMethods.isEmpty())
			{
				return allMethods.get(i);
			}
			else
			{
				return null;
			}
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
			List<TrainingMethod> allMethods = trainingMethodsTable.getAllTrainingMethods();
			if (i >= 0 && i < allMethods.size())
			{
				TrainingMethod updatingMethod = allMethods.get(i);
				updatingMethod.setName((String) o);
				trainingMethodsTable.updateTrainingMethod(updatingMethod);
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
	 * Add new row with default value
	 */
	public void addNewTrainingMethod()
	{
		try
		{
			TrainingMethod newMethod = new TrainingMethod();
			newMethod.setName("");
			trainingMethodsTable.addTrainingMethod(newMethod);
			fireTableRowsInserted(trainingMethodsTable.getAllTrainingMethods().size() - 1,
							trainingMethodsTable.getAllTrainingMethods().size() - 1);
		}
		catch (DatabaseErrorException ex)
		{
			// do nothing
		}
	}
}
