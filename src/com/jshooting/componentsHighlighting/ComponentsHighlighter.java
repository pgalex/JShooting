package com.jshooting.componentsHighlighting;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

/**
 * AWT Components highlighter. Smoothly blinks component's background between
 * two colors
 *
 * @author pgalex
 */
public class ComponentsHighlighter
{
	/**
	 * Time between color chaging
	 */
	public static final int CHANGIND_COLOR_TIMER_IDLE = 100;
	/**
	 * Timer using to periodically changing of components color
	 */
	private Timer changingColorTimer;
	/**
	 * Map of highlighted components and its color changers
	 */
	private Map<Component, ColorChanger> highlightingComponents;

	/**
	 * Create with no components highlighted
	 */
	public ComponentsHighlighter()
	{
		highlightingComponents = Collections.synchronizedMap(new HashMap<Component, ColorChanger>());

		changingColorTimer = new javax.swing.Timer(CHANGIND_COLOR_TIMER_IDLE, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				changingColorTimerActionPerformed(e);
			}
		});
	}

	/**
	 * Using to stop highlighting when application exists
	 *
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		changingColorTimer.stop();
	}

	/**
	 * Event color changing timer - means that need to calculate and change to
	 * next color for all highlighted components
	 *
	 * @param ev
	 */
	private void changingColorTimerActionPerformed(ActionEvent ev)
	{
		for (Map.Entry<Component, ColorChanger> entry : highlightingComponents.entrySet())
		{
			Component component = entry.getKey();
			ColorChanger colorChanger = entry.getValue();

			colorChanger.nextColor();
			component.setBackground(colorChanger.getColor());
		}
	}

	/**
	 * Start highlighting of component
	 *
	 * @param component component to highlight. Its background color will be
	 * overriden. Must be not null
	 * @param highlightColor color for highlighted state of component. Must be not
	 * null
	 * @param blinkingTime time in which need to change components color from
	 * normal state to highlighted and back. Must be more than
	 * CHANGIND_COLOR_TIMER_IDLE constant
	 * @throws IllegalArgumentException component is null; highlightColor is null;
	 * blinkingTime less or equals CHANGIND_COLOR_TIMER_IDLE
	 */
	public void startComponentHightlighing(Component component, Color highlightColor, int blinkingTime) throws IllegalArgumentException
	{
		if (component == null)
		{
			throw new IllegalArgumentException("component is null");
		}
		if (highlightColor == null)
		{
			throw new IllegalArgumentException("highlightColor is null");
		}
		if (blinkingTime <= CHANGIND_COLOR_TIMER_IDLE)
		{
			throw new IllegalArgumentException("blinkingTime must be more than " + CHANGIND_COLOR_TIMER_IDLE);
		}

		if (highlightingComponents.containsKey(component))
		{
			return; // already highlighted
		}

		ColorChanger colorChanger = new ColorChanger(component.getBackground(), highlightColor, blinkingTime, CHANGIND_COLOR_TIMER_IDLE);
		highlightingComponents.put(component, colorChanger);

		if (!changingColorTimer.isRunning())
		{
			changingColorTimer.start();
		}
	}

	/**
	 * Stop highlighted of component
	 *
	 * @param component component which highlighting need to stop. Background
	 * color of components set to color that was while starting of component
	 * highlighting. Must be not null
	 * @throws IllegalArgumentException component is null
	 */
	public void stopComponentHighlighting(Component component) throws IllegalArgumentException
	{
		if (component == null)
		{
			throw new IllegalArgumentException("component is null");
		}

		if (highlightingComponents.containsKey(component))
		{
			component.setBackground(highlightingComponents.get(component).getInitialColor());
			highlightingComponents.remove(component);
		}

		if (highlightingComponents.isEmpty())
		{
			changingColorTimer.stop();
		}
	}
}
