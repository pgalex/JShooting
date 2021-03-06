package com.jshooting.shootingDatabase;

import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.model.ShootingTraining;
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
	 * Update exists training
	 *
	 * @param trainingToUpdate updating training. Must be not null and valid
	 * @throws IllegalArgumentException trainingToUpdate is null or invalid
	 * @throws DatabaseErrorException error while updating
	 */
	public void updateTraining(ShootingTraining trainingToUpdate) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Delete given shooting training from table
	 *
	 * @param trainingToDelete deleting training
	 * @throws IllegalArgumentException trainingToDelete is null
	 * @throws DatabaseErrorException error while deleting
	 */
	public void deleteTraining(ShootingTraining trainingToDelete) throws IllegalArgumentException, DatabaseErrorException;

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
	 * @param trainingToAdd adding shooting training. Must be not null; Must be
	 * valid
	 * @throws IllegalArgumentException trainingToAdd null or invalid
	 * @throws DatabaseErrorException error while adding
	 */
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Get shooting trainings accepted by given filter
	 *
	 * @param filter filter which using to determine which trainings need to get.
	 * Must be not null
	 * @return list of trainings get with filter. Empty if there is no accepted
	 * trainings
	 * @throws IllegalArgumentException filter is null
	 * @throws DatabaseErrorException error while getting trainings
	 */
	public List<ShootingTraining> getTrainingsWithFilter(ShootingTrainingsFilter filter) throws IllegalArgumentException, DatabaseErrorException;
}
