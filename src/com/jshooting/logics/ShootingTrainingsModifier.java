package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;

/**
 * Using to modify shooting trainings
 *
 * @author pgalex
 */
public class ShootingTrainingsModifier
{
	/**
	 * Shooting trainings table
	 */
	private ShootingTrainingsTable shootingTrainingsTable;

	/**
	 * Create with trainings table
	 *
	 * @param shootingTrainingsTable shooting trainings table. Must be not null
	 * @throws IllegalArgumentException shootingTrainingsTable is null
	 */
	public ShootingTrainingsModifier(ShootingTrainingsTable shootingTrainingsTable) throws IllegalArgumentException
	{
		if (shootingTrainingsTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingsTable is null");
		}

		this.shootingTrainingsTable = shootingTrainingsTable;
	}

	/**
	 * Add shooting training
	 *
	 * @param trainingToAdd adding shooting training. Must be not null; its
	 * sportsman, date and training method must be not null
	 * @throws IllegalArgumentException trainingToAdd or its sportsman, date or
	 * training method is null
	 * @throws ShootingLogicsErrorException error while adding
	 */
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (trainingToAdd == null)
		{
			throw new IllegalArgumentException("trainingToAdd is null");
		}

		try
		{
			shootingTrainingsTable.addTraining(trainingToAdd);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Delete exists shooting training
	 *
	 * @param trainingToDelete deleting training. Must be not null
	 * @throws IllegalArgumentException trainingToDelete is null
	 * @throws ShootingLogicsErrorException error while deleting training
	 */
	public void deleteTraining(ShootingTraining trainingToDelete) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (trainingToDelete == null)
		{
			throw new IllegalArgumentException("trainingToDelete is null");
		}

		try
		{
			shootingTrainingsTable.deleteTraining(trainingToDelete);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}

	/**
	 * Update exists training
	 *
	 * @param trainingToUpdate updating training. Must be not null
	 * @throws IllegalArgumentException trainingToUpdate is null
	 * @throws ShootingLogicsErrorException error while updating
	 */
	public void updateTraining(ShootingTraining trainingToUpdate) throws IllegalArgumentException, ShootingLogicsErrorException
	{
		if (trainingToUpdate == null)
		{
			throw new IllegalArgumentException("trainingToUpdate is null");
		}

		try
		{
			shootingTrainingsTable.updateTraining(trainingToUpdate);
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
