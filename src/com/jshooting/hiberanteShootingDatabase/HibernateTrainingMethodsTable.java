package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.TrainingMethod;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Hibernate realization of training methods table
 *
 * @author pgalex
 */
public class HibernateTrainingMethodsTable implements TrainingMethodsTable
{
	/**
	 * Hiberbate session using to get access to training methods table
	 */
	private Session session;

	/**
	 * Create with session
	 *
	 * @param session hiberbate session factory using to get access to training
	 * methods table. Must be not null
	 * @throws IllegalArgumentException session is null
	 */
	public HibernateTrainingMethodsTable(Session session) throws IllegalArgumentException
	{
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		this.session = session;
	}

	/**
	 * Get all training methods
	 *
	 * @return all training methods in table. Empty if there is no methods
	 * @throws DatabaseErrorException error while getting methods from database
	 */
	@Override
	public List<TrainingMethod> getAllTrainingMethods() throws DatabaseErrorException
	{
		List<TrainingMethod> methodsList = session.createCriteria(TrainingMethod.class).list();
		return methodsList;
	}

	/**
	 * Add new training method
	 *
	 * @param trainingMethodToAdd adding training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addTrainingMethod(TrainingMethod trainingMethodToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingMethodToAdd == null)
		{
			throw new IllegalArgumentException("trainingMethodToAdd is null");
		}


		session.beginTransaction();
		session.save(trainingMethodToAdd);
		session.getTransaction().commit();
	}

	/**
	 * Update exists training method
	 *
	 * @param trainingMethodToUpdate updating training method. Must be not null
	 * @throws IllegalArgumentException trainingMethodToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	@Override
	public void updateTrainingMethod(TrainingMethod trainingMethodToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingMethodToUpdate == null)
		{
			throw new IllegalArgumentException("trainingMethodToUpdate is null");
		}

		session.beginTransaction();
		session.update(trainingMethodToUpdate);
		session.getTransaction().commit();
	}
}
