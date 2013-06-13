package com.jshooting.shootingDatabase;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Table of shooting trainings
 *
 * @author pgalex
 */
public interface ShootingTrainingsTable
{
	/**
	 * Get all shoting trainings
	 *
	 * @return list of all shoting trainings. Empty if there is no trainings in
	 * table
	 * @throws DatabaseErrorException error while getting trainings from database
	 */
	public List<ShootingTraining> getAllTrainings() throws DatabaseErrorException;

	/**
	 * Add new shooting training to database
	 *
	 * @param trainingToAdd adding shooting training. Must be not null; its
	 * sportsman, date and training method must be not null
	 * @throws IllegalArgumentException trainingToAdd or its sportsman, date or
	 * training method is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, DatabaseErrorException;
}
