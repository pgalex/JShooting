package com.jshooting.forms;

import com.jshooting.logics.PlacesGetter;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Place;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model of places table
 *
 * @author pgalex
 */
public class PlacesTableModel extends AbstractTableModel
{
	/**
	 * Count of columns in places table
	 */
	public static final int COLUMNS_COUNT = 3;
	/**
	 * Place name column index
	 */
	public static final int NAME_COLUMN_INDEX = 0;
	/**
	 * Place begin date column index
	 */
	public static final int BEGIN_DATE_COLUMN_INDEX = 1;
	/**
	 * Place end date column index
	 */
	public static final int END_DATE_COLUMN_INDEX = 2;
	/**
	 * Using to get places
	 */
	private PlacesGetter placesGetter;
	/**
	 * List of places using for optimization accessing to database. Synchronized
	 * with database places
	 */
	private List<Place> places;

	/**
	 * Create with editing place table
	 *
	 * @param logicsFactory logics factory. Must be not null
	 * @throws IllegalArgumentException placesTable is null
	 */
	public PlacesTableModel(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		placesGetter = logicsFactory.createPlacesGetter();
		updatePlacesList();
	}

	/**
	 * Update placeses array by database table
	 */
	private void updatePlacesList()
	{
		try
		{
			places = placesGetter.getAllPlaces();
		}
		catch (ShootingLogicsException ex)
		{
			places = new ArrayList<Place>();
		}
	}

	/**
	 * Update model by getting data from database
	 */
	public void update()
	{
		updatePlacesList();
		fireTableDataChanged();
	}

	/**
	 * Get place associated with row
	 *
	 * @param rowIndex index of row to get place of. Must be more or equals zero
	 * and less than rows count
	 * @return place associated with row with given index
	 * @throws IllegalArgumentException rowIndex out of bounds
	 */
	public Place getPlaceAtRow(int rowIndex) throws IllegalArgumentException
	{
		if (rowIndex < 0 || rowIndex >= places.size())
		{
			throw new IllegalArgumentException("rowIndex out of bounds");
		}

		return places.get(rowIndex);
	}

	@Override
	public int getRowCount()
	{
		try
		{
			return placesGetter.getAllPlaces().size();
		}
		catch (ShootingLogicsException ex)
		{
			return 0;
		}
	}

	@Override
	public int getColumnCount()
	{
		return COLUMNS_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex)
	{
		switch (columnIndex)
		{
			case NAME_COLUMN_INDEX:
				return "Место проведения";
			case BEGIN_DATE_COLUMN_INDEX:
				return "Дата начала";
			case END_DATE_COLUMN_INDEX:
				return "Дата завершения";
			default:
				return "";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		switch (columnIndex)
		{
			case NAME_COLUMN_INDEX:
				return String.class;
			case BEGIN_DATE_COLUMN_INDEX:
				return Date.class;
			case END_DATE_COLUMN_INDEX:
				return Date.class;
			default:
				return super.getColumnClass(columnIndex);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Place place = places.get(rowIndex);
		switch (columnIndex)
		{
			case NAME_COLUMN_INDEX:
				return place.getName();
			case BEGIN_DATE_COLUMN_INDEX:
				return place.getBeginDate();
			case END_DATE_COLUMN_INDEX:
				return place.getEndDate();
			default:
				return "";
		}
	}

	@Override
	public boolean isCellEditable(int i, int i1)
	{
		return false;
	}
}
