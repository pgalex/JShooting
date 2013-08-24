package com.jshooting.objectsHighlighting;

import java.awt.Color;
import java.awt.Component;

/**
 * Adapter for highlighting of AWT Component foreground color
 *
 * @author pgalex
 */
public class ComponentForegroundHighlightingAdapter implements ColourObjectAdapter
{
	private Component component;

	public ComponentForegroundHighlightingAdapter(Component component) throws IllegalArgumentException
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
		component.setForeground(colorToSet);
	}

	@Override
	public Color getColor()
	{
		return component.getForeground();
	}

	@Override
	public Object getColourObject()
	{
		return component;
	}
}
