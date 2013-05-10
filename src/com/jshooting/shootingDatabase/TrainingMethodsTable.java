package com.jshooting.shootingDatabase;

import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 * Table of training methods
 *
 * @author pgalex
 */
public interface TrainingMethodsTable
{
	/**
	 * Get all training methods
	 *
	 * @return all training methods in table. Empty if there is no methods
	 * @throws DatabaseErrorException error while getting methods from database
	 */
	public List<TrainingMethod> getAllTrainingMethods() throws DatabaseErrorException;

	/**
	 * Add new training method
	 *
	 * @param trainingMethodToAdd adding training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	public void addTrainingMethod(TrainingMethod trainingMethodToAdd) throws IllegalArgumentException, DatabaseErrorException;

	/**
	 * Update exists training method
	 *
	 * @param trainingMethodToUpdate updating training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	public void updateTrainingMethod(TrainingMethod trainingMethodToUpdate) throws IllegalArgumentException, DatabaseErrorException;
}
