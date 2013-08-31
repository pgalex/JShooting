/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jshooting.logicsTests;

import com.jshooting.model.ShootingTraining;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;

/**
 *
 * @author pgalex
 */
public class FakeShootingTrainingsTable implements ShootingTrainingsTable
{
	@Override
	public void updateTraining(ShootingTraining trainingToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
	}

	@Override
	public void deleteTraining(ShootingTraining trainingToDelete) throws IllegalArgumentException, DatabaseErrorException
	{
	}

	@Override
	public List<ShootingTraining> getAllTrainings() throws DatabaseErrorException
	{
		return null;
	}

	@Override
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
	}

	@Override
	public List<ShootingTraining> getTrainingsWithFilter(ShootingTrainingsFilter filter) throws IllegalArgumentException, DatabaseErrorException
	{
		return null;
	}
}
