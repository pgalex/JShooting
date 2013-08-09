package com.jshooting.forms;

import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.TrainingMethodsGetter;
import com.jshooting.logics.TrainingMethodsModifier;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.TrainingMethod;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for training methods table
 *
 * @author pgalex
 */
public class TrainingMethodsTableModel extends AbstractTableModel
{
	/**
	 * Index of training method name in table
	 */
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Training methods getter
	 */
	private TrainingMethodsGetter trainingMethodsGetter;
	/**
	 * Using to edit training methods throught table model
	 */
	private TrainingMethodsModifier trainingMethodsModifier;
	/**
	 * Using to optimize access to database
	 */
	private List<TrainingMethod> trainingMethods;

	/**
	 * Create editing training methods table model
	 *
	 * @param logicsFactory logics fatory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public TrainingMethodsTableModel(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		trainingMethodsGetter = logicsFactory.createTrainingMethodsGetter();
		trainingMethodsModifier = logicsFactory.createTrainingMethodsModifier();
		trainingMethods = new ArrayList<TrainingMethod>();
		updateTrainingMethodsList();
	}

	/**
	 * Update list of training methods
	 */
	private void updateTrainingMethodsList()
	{
		try
		{
			trainingMethods = trainingMethodsGetter.getAllTrainingMethods();
		}
		catch (ShootingLogicsException ex)
		{
			trainingMethods = new ArrayList<TrainingMethod>();
		}
	}

	@Override
	public String getColumnName(int i)
	{
		return "Средства";
	}

	@Override
	public int getRowCount()
	{
		return trainingMethods.size();
	}

	@Override
	public int getColumnCount()
	{
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return trainingMethods.get(rowIndex);
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
			if (rowIndex >= 0 && rowIndex < trainingMethods.size())
			{
				TrainingMethod updatingMethod = trainingMethods.get(rowIndex);
				updatingMethod.setName((String) newValue);
				trainingMethodsModifier.updateTrainingMethod(updatingMethod);
				updateTrainingMethodsList();
			}
			else
			{
				// do nothing
			}
		}
		catch (ShootingLogicsException ex)
		{
			updateTrainingMethodsList();
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	/**
	 * Add new row with default value
	 */
	public void addNewTrainingMethod()
	{
		try
		{
			trainingMethodsModifier.addNewTrainingMethod();
			updateTrainingMethodsList();
			fireTableRowsInserted(trainingMethodsGetter.getAllTrainingMethods().size() - 1,
							trainingMethodsGetter.getAllTrainingMethods().size() - 1);
		}
		catch (ShootingLogicsException ex)
		{
			// do nothing
		}
	}
}
