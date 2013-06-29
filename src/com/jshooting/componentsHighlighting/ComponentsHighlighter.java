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
 * AWT Components highlighter. Smooth blinks component's background between two
 * colors
 *
 * @author pgalex
 */
public class ComponentsHighlighter
{
	private static final int CHANGIND_COLOR_TIMER_IDLE = 100;
	private Timer changingColorTimer;
	private Map<Component, ColorChanger> highlightingComponents;

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

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		changingColorTimer.stop();
	}

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

	// blinkingTime - врем€, за которое компонент должен изменить цвет от начального до highlightingColor и обратно
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

		ColorChanger colorChanger = new ColorChanger(component.getBackground(), highlightColor, blinkingTime, CHANGIND_COLOR_TIMER_IDLE);
		highlightingComponents.put(component, colorChanger);

		if (!changingColorTimer.isRunning())
		{
			changingColorTimer.start();
		}
	}

	public void stopComponentHighlighting(Component component)
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
