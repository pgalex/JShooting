package com.jshooting.objectsHighlighting;

import java.awt.Color;

/**
 * Adapter of highlighting object. Using as a layer between color changing part
 * and how highlighting object stores and changes its color
 *
 * @author pgalex
 */
public interface ColourObjectAdapter
{
	/**
	 * Is given objectToCompare equals to colour objectToCompare of adapter
	 *
	 * @param objectToCompare compating object. Must be not null
	 * @return is given object equals to adapter's colour object
	 * @throws IllegalArgumentException objectToCompare is null
	 */
	public boolean isEqualsToColourObject(Object objectToCompare) throws IllegalArgumentException;

	/**
	 * Set new color of adapter's colour object
	 *
	 * @param colorToSet new color. Must be not null
	 * @throws IllegalArgumentException colorToSet is null
	 */
	public void setColor(Color colorToSet) throws IllegalArgumentException;

	/**
	 * Get current color of adapter's colour object
	 *
	 * @return colour object current color. Not null
	 */
	public Color getColor();

	/**
	 * Get adapter's colour object
	 *
	 * @return colour object linked with adapter. Not null
	 */
	public Object getColourObject();
}
