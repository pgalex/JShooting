package com.jshooting.logics;

import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import java.util.List;

/**
 * Using to get exists shooting trainings
 *
 * @author pgalex
 */
public class ShootingTrainingsGetter
{
	private ShootingTrainingsTable shootingTrainingsTable;

	public ShootingTrainingsGetter(ShootingTrainingsTable shootingTrainingsTable)
	{
		this.shootingTrainingsTable = shootingTrainingsTable;
	}

	/**
	 * Get all shoting trainings
	 *
	 * @return list of all shooting trainings
	 * @throws ShootingLogicsErrorException error while getting trainings
	 */
	public List<ShootingTraining> getAllTrainings() throws ShootingLogicsErrorException
	{
		try
		{
			return shootingTrainingsTable.getAllTrainings();
		}
		catch (Exception ex)
		{
			throw new ShootingLogicsErrorException(ex);
		}
	}
}
