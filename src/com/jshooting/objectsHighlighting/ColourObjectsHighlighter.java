package com.jshooting.objectsHighlighting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

/**
 * Colour objects highlighter. Changes objects color with given pattern and
 * colors
 *
 * @author pgalex
 */
public class ColourObjectsHighlighter
{
	/**
	 * Time between color chaging
	 */
	public static final int CHANGIND_COLOR_TIMER_IDLE = 100;
	/**
	 * Timer using for periodically changing of objects color. Determines time
	 * when need to change colors
	 */
	private Timer changingColorTimer;
	/**
	 * Map of highlighted colour objects and its color changers
	 */
	private Map<ColourObjectAdapter, ColorChanger> highlightingComponents;

	/**
	 * Create with no objects highlighted
	 */
	public ColourObjectsHighlighter()
	{
		highlightingComponents = Collections.synchronizedMap(new HashMap<ColourObjectAdapter, ColorChanger>());

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
	 * next color for all highlighted objects
	 *
	 * @param ev
	 */
	private void changingColorTimerActionPerformed(ActionEvent ev)
	{
		for (Map.Entry<ColourObjectAdapter, ColorChanger> entry : highlightingComponents.entrySet())
		{
			ColourObjectAdapter colourObjectAdapter = entry.getKey();
			ColorChanger colorChanger = entry.getValue();

			colorChanger.nextColor();
			colourObjectAdapter.setColor(colorChanger.getColor());
		}
	}

	/**
	 * Start highlighting of object with its highlighting adapter
	 *
	 * @param highlightingColourObjectAdapter adapter of highlighting object. Must
	 * be not null
	 * @param highlightStateColor color for highlighted state of highlighting
	 * object. Must be not null
	 * @param blinkingTime time in which need to change components color from
	 * normal state to highlighted and back. Must be more than
	 * CHANGIND_COLOR_TIMER_IDLE constant
	 * @throws IllegalArgumentException highlightingColourObjectAdapter is null;
	 * highlightStateColor is null; blinkingTime less or equals
	 * CHANGIND_COLOR_TIMER_IDLE
	 */
	public void startObjectHightlighing(ColourObjectAdapter highlightingColourObjectAdapter, Color highlightStateColor, int blinkingTime) throws IllegalArgumentException
	{
		if (highlightingColourObjectAdapter == null)
		{
			throw new IllegalArgumentException("highlightingColourObjectAdapter is null");
		}
		if (highlightStateColor == null)
		{
			throw new IllegalArgumentException("highlightStateColor is null");
		}
		if (blinkingTime <= CHANGIND_COLOR_TIMER_IDLE)
		{
			throw new IllegalArgumentException("blinkingTime must be more than " + CHANGIND_COLOR_TIMER_IDLE);
		}
		if (isObjectExistsInHighlighted(highlightingColourObjectAdapter.getColourObject()))
		{
			return;
		}

		ColorChanger colorChanger = new ColorChanger(highlightingColourObjectAdapter.getColor(), highlightStateColor, blinkingTime, CHANGIND_COLOR_TIMER_IDLE);
		highlightingComponents.put(highlightingColourObjectAdapter, colorChanger);

		if (!changingColorTimer.isRunning())
		{
			changingColorTimer.start();
		}
	}

	/**
	 * Is object exists among currently highlighted objects
	 *
	 * @param object object that need to find among highlighted. Must be not null
	 * @throws IllegalArgumentException object is null
	 */
	private boolean isObjectExistsInHighlighted(Object object) throws IllegalArgumentException
	{
		if (object == null)
		{
			throw new IllegalArgumentException("object is null");
		}

		for (Map.Entry<ColourObjectAdapter, ColorChanger> entry : highlightingComponents.entrySet())
		{
			ColourObjectAdapter colourObjectAdapter = entry.getKey();
			ColorChanger colorChanger = entry.getValue();
			if (colourObjectAdapter.isEqualsToColourObject(object))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Start highlighting of object with its highlighting adapter for a given
	 * period. After time expiring highlighting will be stop
	 *
	 * @param highlightingColourObjectAdapter adapter of highlighting object. Must
	 * be not null
	 * @param highlightStateColor color for highlighted state of component. Must
	 * be not null
	 * @param blinkingTime time in which need to change components color from
	 * normal state to highlighted and back. Must be more than
	 * CHANGIND_COLOR_TIMER_IDLE constant
	 * @param highlightingTime time in milleseconds to stop highlighting after.
	 * Must be more equals or more than zero
	 * @throws IllegalArgumentException component is null; highlightColor is null;
	 * blinkingTime less or equals CHANGIND_COLOR_TIMER_IDLE
	 */
	public void startObjectHightlighingForTime(ColourObjectAdapter highlightingColourObjectAdapter, Color highlightStateColor, int blinkingTime, long highlightingTime) throws IllegalArgumentException
	{
		if (highlightingColourObjectAdapter == null)
		{
			throw new IllegalArgumentException("highlightingColourObjectAdapter is null");
		}
		if (highlightStateColor == null)
		{
			throw new IllegalArgumentException("highlightStateColor is null");
		}
		if (blinkingTime <= CHANGIND_COLOR_TIMER_IDLE)
		{
			throw new IllegalArgumentException("blinkingTime must be more than " + CHANGIND_COLOR_TIMER_IDLE);
		}
		if (highlightingTime < 0)
		{
			throw new IllegalArgumentException("timeInMilleseconds is negative");
		}
		if (isObjectExistsInHighlighted(highlightingColourObjectAdapter.getColourObject()))
		{
			return;
		}

		ColorChanger colorChanger = new ColorChanger(highlightingColourObjectAdapter.getColor(), highlightStateColor, blinkingTime, CHANGIND_COLOR_TIMER_IDLE);
		highlightingComponents.put(highlightingColourObjectAdapter, colorChanger);

		if (!changingColorTimer.isRunning())
		{
			changingColorTimer.start();
		}

		stopObjectHighlightingTimeExpiration(highlightingColourObjectAdapter.getColourObject(), highlightingTime);
	}

	/**
	 * Stop given colour object highlighting after time expiration
	 *
	 * @param colourObject colour object which highlighting need to stop. Must be
	 * not null
	 * @param timeInMilleseconds time in milleseconds to stop highlighting after.
	 * Must be more equals or more than zero
	 * @throws IllegalArgumentException colourObject is null or timeInMilleseconds
	 * negative
	 */
	public void stopObjectHighlightingTimeExpiration(final Object colourObject, final long timeInMilleseconds) throws IllegalArgumentException
	{
		if (colourObject == null)
		{
			throw new IllegalArgumentException("colourObject is null");
		}
		if (timeInMilleseconds < 0)
		{
			throw new IllegalArgumentException("timeInMilleseconds is negative");
		}

		Thread stoppingHighlightingThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(timeInMilleseconds);
				}
				catch (InterruptedException ex)
				{
					// do nothing
				}
				stopObjectHighlighting(colourObject);
			}
		});
		stoppingHighlightingThread.start();
	}

	/**
	 * Stop highlighted of colour object
	 *
	 * @param colourObject colour object which highlighting need to stop.Must be
	 * not null
	 * @throws IllegalArgumentException colourObject is null
	 */
	public void stopObjectHighlighting(Object colourObject) throws IllegalArgumentException
	{
		if (colourObject == null)
		{
			throw new IllegalArgumentException("colourObject is null");
		}

		ColourObjectAdapter associatedAdapter = null;
		ColorChanger associatedColorChanger = null;
		for (Map.Entry<ColourObjectAdapter, ColorChanger> entry : highlightingComponents.entrySet())
		{
			ColourObjectAdapter colourObjectAdapter = entry.getKey();
			ColorChanger colorChanger = entry.getValue();
			if (colourObjectAdapter.isEqualsToColourObject(colourObject))
			{
				associatedAdapter = colourObjectAdapter;
				associatedColorChanger = colorChanger;
				break;
			}
		}

		if (associatedAdapter != null && associatedColorChanger != null)
		{
			associatedAdapter.setColor(associatedColorChanger.getInitialColor());
			highlightingComponents.remove(associatedAdapter);

			if (highlightingComponents.isEmpty())
			{
				changingColorTimer.stop();
			}
		}
	}
}
