package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.TrainingMethod;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;

/**
 * Using to modify training methods
 *
 * @author pgalex
 */
public class TrainingMethodsModifier
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
	public TrainingMethodsModifier(TrainingMethodsTable trainingMethodsTable) throws IllegalArgumentException
	{
		if (trainingMethodsTable == null)
		{
			throw new IllegalArgumentException("trainingMethodsTable is null");
		}

		this.trainingMethodsTable = trainingMethodsTable;
	}

	/**
	 * Add new training method with empty name
	 *
	 * @throws ShootingLogicsException error while adding
	 */
	public void addNewTrainingMethod() throws ShootingLogicsException
	{
		try
		{
			TrainingMethod newMethod = new TrainingMethod();
			newMethod.setName("");
			trainingMethodsTable.addTrainingMethod(newMethod);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}

	/**
	 * Update exists training method
	 *
	 * @param trainingMethodToUpdate updating training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToUpdate is null
	 * @throws ShootingLogicsException error while updating
	 */
	public void updateTrainingMethod(TrainingMethod trainingMethodToUpdate) throws IllegalArgumentException, ShootingLogicsException
	{
		if (trainingMethodToUpdate == null)
		{
			throw new IllegalArgumentException("trainingMethodToUpdate is null");
		}

		try
		{
			trainingMethodsTable.updateTrainingMethod(trainingMethodToUpdate);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsException(ex);
		}
	}
}
