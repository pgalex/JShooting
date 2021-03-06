package com.jshooting.logics;

import com.jshooting.model.Place;
import java.util.List;

/**
 * Creates string of places names
 *
 * @author pgalex
 */
public class PlacesNamesListFormer
{
	/**
	 * Get string contains names of places
	 *
	 * @param places places to take names from
	 * @return string of names of places. Empty if there is no places
	 * @throws IllegalArgumentException places is null
	 */
	public static String getPlacesNamesString(List<Place> places) throws IllegalArgumentException
	{
		if (places == null)
		{
			throw new IllegalArgumentException("places is null");
		}

		String placesListString = new String();
		List<Place> placesInTrainingDate = places;
		for (int i = 0; i < placesInTrainingDate.size(); i++)
		{
			placesListString += placesInTrainingDate.get(i).getName();
			if (i != placesInTrainingDate.size() - 1)
			{
				placesListString += ", ";
			}
		}
		return placesListString;
	}
}
