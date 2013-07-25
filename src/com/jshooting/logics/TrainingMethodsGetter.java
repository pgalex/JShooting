package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.TrainingMethod;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import java.util.List;

/**
 * Using to get training methods from database
 *
 * @author pgalex
 */
public class TrainingMethodsGetter
{
	/**
	 * Table of training methods get data from
	 */
	private TrainingMethodsTable trainingMethodsTable;

	/**
	 * Create with training methods table
	 *
	 * @param trainingMethodsTable table of training methods get data from. Must
	 * be not null
	 * @throws IllegalArgumentException trainingMethodsTable is null
	 */
	public TrainingMethodsGetter(TrainingMethodsTable trainingMethodsTable) throws IllegalArgumentException
	{
		if (trainingMethodsTable == null)
		{
			throw new IllegalArgumentException("trainingMethodsTable is null");
		}

		this.trainingMethodsTable = trainingMethodsTable;
	}

	/**
	 * Get all training methods
	 *
	 * @return all training methods in table. Empty if there is no methods
	 * @throws ShootingLogicsException error while getting methods
	 */
	public List<TrainingMethod> getAllTrainingMethods() throws ShootingLogicsException
	{
		try
		{
			return trainingMethodsTable.getAllTrainingMethods();
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
