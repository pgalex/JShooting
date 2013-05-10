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
	 * Hiberbate session factory using to get access to training methods table
	 */
	private SessionFactory sessionFactory;

	/**
	 * Create with session
	 *
	 * @param hibernateSessionFactory hiberbate session factory using to get
	 * access to training methods table. Must be not null
	 * @throws IllegalArgumentException hibernateSessionFactory is null
	 */
	public HibernateTrainingMethodsTable(SessionFactory hibernateSessionFactory) throws IllegalArgumentException
	{
		if (hibernateSessionFactory == null)
		{
			throw new IllegalArgumentException("hibernateSessionFactory is null");
		}

		sessionFactory = hibernateSessionFactory;
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
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			List<TrainingMethod> methodsList = session.createCriteria(TrainingMethod.class).list();
			return methodsList;
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
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

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(trainingMethodToAdd);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
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

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(trainingMethodToUpdate);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}
}
