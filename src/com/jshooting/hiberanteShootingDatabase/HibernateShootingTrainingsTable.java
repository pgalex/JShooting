package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.model.ShootingTraining;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate realization of shooting trainings table
 *
 * @author pgalex
 */
public class HibernateShootingTrainingsTable implements ShootingTrainingsTable
{
	/**
	 * Hiberbate session using to get access to trainings table
	 */
	private Session session;

	/**
	 * Create with session
	 *
	 * @param session hiberbate session using to get access to trainings table.
	 * Must be not null
	 * @throws IllegalArgumentException session is null
	 */
	public HibernateShootingTrainingsTable(Session session) throws IllegalArgumentException
	{
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		this.session = session;
	}

	/**
	 * Delete given shooting training from table
	 *
	 * @param trainingToDelete deleting training
	 * @throws IllegalArgumentException trainingToDelete is null
	 * @throws DatabaseErrorException error while deleting
	 */
	@Override
	public void deleteTraining(ShootingTraining trainingToDelete) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingToDelete == null)
		{
			throw new IllegalArgumentException("trainingToDelete is null");
		}

		try
		{
			session.beginTransaction();
			session.delete(trainingToDelete);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
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
		try
		{
			List<ShootingTraining> trainings = session.createCriteria(ShootingTraining.class).list();
			return trainings;
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
	}

	/**
	 * Add new shooting training to database
	 *
	 * @param trainingToAdd adding shooting training. Must be not null; Must be
	 * valid
	 * @throws IllegalArgumentException trainingToAdd null or invalid
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addTraining(ShootingTraining trainingToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingToAdd == null)
		{
			throw new IllegalArgumentException("trainingToAdd is null");
		}
		if (!trainingToAdd.isValid())
		{
			throw new IllegalArgumentException("trainingToAdd invalid");
		}

		try
		{
			session.beginTransaction();
			session.save(trainingToAdd);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
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

		try
		{
			Criteria criteria = createCriteriaByFilter(filter, session);
			return criteria.list();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
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

	/**
	 * Update exists training
	 *
	 * @param trainingToUpdate updating training. Must be not null and valid
	 * @throws IllegalArgumentException trainingToUpdate is null or invalid
	 * @throws DatabaseErrorException error while updating
	 */
	@Override
	public void updateTraining(ShootingTraining trainingToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		if (trainingToUpdate == null)
		{
			throw new IllegalArgumentException("trainingToUpdate is null");
		}
		if (!trainingToUpdate.isValid())
		{
			throw new IllegalArgumentException("trainingToUpdate invalid");
		}

		try
		{
			session.beginTransaction();
			session.update(trainingToUpdate);
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			throw new DatabaseErrorException(ex);
		}
	}
}
