package com.jshooting.objectsHighlighting;

import java.awt.Color;
import java.awt.Component;

/**
 * Adapter for highlighting of AWT Component background color
 *
 * @author pgalex
 */
public class ComponentBackgroundHighlightingAdapter implements ColourObjectAdapter
{
	private Component component;

	public ComponentBackgroundHighlightingAdapter(Component component) throws IllegalArgumentException
	{
		if (component == null)
		{
			throw new IllegalArgumentException("component is null");
		}

		this.component = component;
	}

	@Override
	public boolean isEqualsToColourObject(Object objectToCompare) throws IllegalArgumentException
	{
		return component.equals(objectToCompare);
	}

	@Override
	public void setColor(Color colorToSet) throws IllegalArgumentException
	{
		component.setBackground(colorToSet);
	}

	@Override
	public Color getColor()
	{
		return component.getBackground();
	}

	@Override
	public Object getColourObject()
	{
		return component;
	}
}
