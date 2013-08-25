package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
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
	 * @throws ShootingLogicsErrorException error while adding
	 */
	public void addNewTrainingMethod() throws ShootingLogicsErrorException
	{
		try
		{
			TrainingMethod newMethod = new TrainingMethod();
			newMethod.setName("");
			trainingMethodsTable.addTrainingMethod(newMethod);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Update exists training method
	 *
	 * @param trainingMethodToUpdate updating training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToUpdate is null
	 * @throws ShootingLogicsErrorException error while updating
	 */
	public void updateTrainingMethod(TrainingMethod trainingMethodToUpdate) throws IllegalArgumentException, ShootingLogicsErrorException
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
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
