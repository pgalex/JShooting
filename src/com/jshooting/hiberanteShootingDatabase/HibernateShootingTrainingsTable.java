package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate realization of shooting trainings table
 *
 * @author pgalex
 */
public class HibernateShootingTrainingsTable implements ShootingTrainingsTable
{
	/**
	 * Hiberbate session factory using to get access to trainings table
	 */
	private SessionFactory sessionFactory;

	/**
	 * Create with session
	 *
	 * @param hibernateSessionFactory hiberbate session factory using to get
	 * access to trainings table. Must be not null
	 * @throws IllegalArgumentException hibernateSessionFactory is null
	 */
	public HibernateShootingTrainingsTable(SessionFactory hibernateSessionFactory) throws IllegalArgumentException
	{
		if (hibernateSessionFactory == null)
		{
			throw new IllegalArgumentException("hibernateSessionFactory is null");
		}

		sessionFactory = hibernateSessionFactory;
	}

	/**
	 * Get all shoting trainings
	 *
	 * @return list of all shoting trainings. Empty if there is no trainings in
	 * table
	 * @throws DatabaseErrorException error while getting trainings from database
	 */
	@Override
	public List<ShootingTraining> getAllTrainings() throws DatabaseErrorException
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			List<ShootingTraining> trainings = session.createCriteria(ShootingTraining.class).list();
			return trainings;
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
	 * Add new shooting training to database
	 *
	 * @param trainingToAdd adding shooting training. Must be not null; its
	 * sportsman, date and training method must be not null
	 * @throws IllegalArgumentException trainingToAdd or its sportsman, date or
	 * training method is null
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingToAdd == null)
		{
			throw new IllegalArgumentException("trainingToAdd is null");
		}
		if (trainingToAdd.getSportsman() == null)
		{
			throw new IllegalArgumentException("trainingToAdd sportsman is null");
		}
		if (trainingToAdd.getDate() == null)
		{
			throw new IllegalArgumentException("trainingToAdd date is null");
		}
		if (trainingToAdd.getTrainingMethod() == null)
		{
			throw new IllegalArgumentException("trainingToAdd training method is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(trainingToAdd);
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
	 * Get shooting trainings accepted by given filter
	 *
	 * @param filter filter which using to determine which trainings need to get.
	 * Must be not null
	 * @return list of trainings get with filter. Empty if there is no accepted
	 * trainings
	 * @throws IllegalArgumentException filter is null
	 * @throws DatabaseErrorException error while getting trainings
	 */
	@Override
	public List<ShootingTraining> getTrainingsWithFilter(ShootingTrainingsFilter filter) throws IllegalArgumentException, DatabaseErrorException
	{
		if (filter == null)
		{
			throw new IllegalArgumentException("filter is null");
		}

		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			Criteria criteria = createCriteriaByFilter(filter, session);
			List<ShootingTraining> trainingsByFilter = criteria.list();
			return trainingsByFilter;
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
	 * Create Criteria for trainings table by given filter
	 *
	 * @param filter filter of trainings create Criteria by
	 * @param session hibernate session
	 * @return Criteria created by filter
	 * @throws IllegalArgumentException filter or session is null
	 */
	private Criteria createCriteriaByFilter(ShootingTrainingsFilter filter, Session session) throws IllegalArgumentException
	{
		if (filter == null)
		{
			throw new IllegalArgumentException("filter is null");
		}
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		Criteria criteriaByFilter = session.createCriteria(ShootingTraining.class);
		criteriaByFilter.add(Restrictions.in("sportsman", filter.getSportsmans()));
		criteriaByFilter.add(Restrictions.between("date", filter.getDateFrom(), filter.getDateTo()));
		criteriaByFilter.add(Restrictions.ge("date", filter.getDateFrom()));
		criteriaByFilter.add(Restrictions.le("date", filter.getDateTo()));
		criteriaByFilter.add(Restrictions.in("type", filter.getTrainingTypes()));
		return criteriaByFilter;
	}
}
