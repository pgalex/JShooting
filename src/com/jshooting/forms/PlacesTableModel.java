package com.jshooting.forms;

import com.jshooting.logics.PlacesGetter;
import com.jshooting.logics.PlacesModifier;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Place;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model of places table. Uses for editing places throught table
 *
 * @author pgalex
 */
public class PlacesTableModel extends AbstractTableModel
{
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
	 * Using to modify places
	 */
	private PlacesModifier placesModifier;
	/**
	 * List of places using for optimization accessing to database
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
		placesModifier = logicsFactory.createPlacesModifier();
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
		return 3;
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
	public void setValueAt(Object newValue, int rowIndex, int columnIndex)
	{
		try
		{
			Place updatingPlace = places.get(rowIndex);
			switch (columnIndex)
			{
				case NAME_COLUMN_INDEX:
					updatingPlace.setName((String) newValue);
					break;
				case BEGIN_DATE_COLUMN_INDEX:
					updatePlaceBeginDate(updatingPlace, (new SimpleDateFormat("dd.MM.yyyy")).parse((String) newValue));
					break;
				case END_DATE_COLUMN_INDEX:
					updatePlaceEndDate(updatingPlace, (new SimpleDateFormat("dd.MM.yyyy")).parse((String) newValue));
					break;
				default:
					break;
			}
			placesModifier.updatePlace(updatingPlace);
			updatePlacesList();
		}
		catch (Exception ex)
		{
			updatePlacesList();
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	private void updatePlaceBeginDate(Place place, Date newBeginDate) throws IllegalArgumentException
	{
		if (place == null)
		{
			throw new IllegalArgumentException("place is null");
		}
		if (newBeginDate == null)
		{
			throw new IllegalArgumentException("newBeginDate is null");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newBeginDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		place.setBeginDate(calendar.getTime());
	}

	private void updatePlaceEndDate(Place place, Date newEndDate) throws IllegalArgumentException
	{
		if (place == null)
		{
			throw new IllegalArgumentException("place is null");
		}
		if (newEndDate == null)
		{
			throw new IllegalArgumentException("newBeginDate is null");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newEndDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		place.setEndDate(calendar.getTime());
	}

	@Override
	public boolean isCellEditable(int i, int i1)
	{
		return true;
	}

	/**
	 * Add new place to database and model
	 */
	public void addNewPlace()
	{
		try
		{
			Place newPlace = new Place();
			newPlace.setName("");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			newPlace.setBeginDate(calendar.getTime());

			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			newPlace.setEndDate(calendar.getTime());

			placesModifier.addNewPlace();
			updatePlacesList();
			fireTableRowsInserted(places.size() - 1, places.size() - 1);
		}
		catch (ShootingLogicsException ex)
		{
			// do nothing
		}
	}
}
