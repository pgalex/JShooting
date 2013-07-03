package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.model.Place;
import com.jshooting.shootingDatabase.PlacesTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Places table hibernate realization
 *
 * @author pgalex
 */
public class HibernatePlacesTable implements PlacesTable
{
	/**
	 * Hiberbate session using to get access to table
	 */
	private Session session;

	/**
	 * Create with session
	 *
	 * @param session hiberbate session factory using to get access to places
	 * table. Must be not null
	 * @throws IllegalArgumentException session is null
	 */
	public HibernatePlacesTable(Session session) throws IllegalArgumentException
	{
		if (session == null)
		{
			throw new IllegalArgumentException("session is null");
		}

		this.session = session;
	}

	/**
	 * Get all places
	 *
	 * @return list of all plces in table. Empty if there is no places
	 * @throws DatabaseErrorException error while getting places
	 */
	@Override
	public List<Place> getAllPlaces() throws DatabaseErrorException
	{
		return session.createCriteria(Place.class).list();
	}

	/**
	 * Get places that exists in period by its begin and end date
	 *
	 * @param periodDateFrom period date from (including)
	 * @param periodDateTo period date to (including)
	 * @return list of places exists in given period
	 * @throws IllegalArgumentException periodDateFrom is null, periodDateTo is
	 * null; periodDateFrom more than periodDateTo
	 */
	@Override
	public List<Place> getPlacesByPeriod(Date periodDateFrom, Date periodDateTo) throws IllegalArgumentException, DatabaseErrorException
	{
		if (periodDateFrom == null)
		{
			throw new IllegalArgumentException("periodDateFrom is null");
		}
		if (periodDateTo == null)
		{
			throw new IllegalArgumentException("periodDateTo is null");
		}
		if (periodDateFrom.after(periodDateTo))
		{
			throw new IllegalArgumentException("periodDateFrom is after periodDateTo");
		}

		Criteria criteria = session.createCriteria(Place.class);
		criteria.add(Restrictions.ge("endDate", periodDateFrom));
		criteria.add(Restrictions.le("beginDate", periodDateTo));
		return criteria.list();
	}

	/**
	 * Add new place
	 *
	 * @param placeToAdd adding place. Must be not null
	 * @throws IllegalArgumentException placeToAdd is null
	 * @throws DatabaseErrorException error while adding
	 */
	@Override
	public void addPlace(Place placeToAdd) throws IllegalArgumentException, DatabaseErrorException
	{
		if (placeToAdd == null)
		{
			throw new IllegalArgumentException("placeToAdd is null");
		}

		session.beginTransaction();
		session.save(placeToAdd);
		session.getTransaction().commit();
	}

	/**
	 * Update exists place
	 *
	 * @param placeToUpdate updating place. Must be not null
	 * @throws IllegalArgumentException placeToUpdate is null
	 * @throws DatabaseErrorException error while updating
	 */
	@Override
	public void updatePlace(Place placeToUpdate) throws IllegalArgumentException, DatabaseErrorException
	{
		if (placeToUpdate == null)
		{
			throw new IllegalArgumentException("placeToUpdate is null");
		}

		session.beginTransaction();
		session.update(placeToUpdate);
		session.getTransaction().commit();
	}
}
