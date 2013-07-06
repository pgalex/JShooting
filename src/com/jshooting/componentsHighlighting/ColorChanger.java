package com.jshooting.componentsHighlighting;

import java.awt.Color;

/**
 * Changer of color, smoothly blinkns it between two colors
 *
 * @author pgalex
 */
public class ColorChanger
{
	/**
	 * Initial blinking color
	 */
	private Color initialColor;
	/**
	 * End blinking color
	 */
	private Color endColor;
	/**
	 * Current color alpha
	 */
	private double currentColorAlpha;
	/**
	 * Current color red
	 */
	private double currentColorRed;
	/**
	 * Current color green
	 */
	private double currentColorGreen;
	/**
	 * Current color blue
	 */
	private double currentColorBlue;
	/**
	 * Increment of alpha channel of color
	 */
	private double alphaIncrement;
	/**
	 * Increment of red channel of color
	 */
	private double redIncrement;
	/**
	 * Increment of green channel of color
	 */
	private double greenIncrement;
	/**
	 * Increment of blue channel of color
	 */
	private double blueIncrement;

	/**
	 * Create color changer between two colors
	 *
	 * @param initialColor initial color. Must be not null
	 * @param endColor end color. Must be not null
	 * @param blinkingTime time in milliseconds in that need two change initial
	 * color to end and back to initial. Must be more than changingColorTimerIdle
	 * @param changingColorTimerIdle time in milliseconds in which color changing.
	 * Must be more than zero
	 * @throws IllegalArgumentException initialColor or endColor is null;
	 * changingColorTimerIdle less or equals zero; blinkingTime less or equals
	 * changingColorTimerIdle
	 */
	public ColorChanger(Color initialColor, Color endColor, int blinkingTime, int changingColorTimerIdle) throws IllegalArgumentException
	{
		if (initialColor == null)
		{
			throw new IllegalArgumentException("initialColor is null");
		}
		if (endColor == null)
		{
			throw new IllegalArgumentException("endColor is null");
		}
		if (blinkingTime <= changingColorTimerIdle)
		{
			throw new IllegalArgumentException("blinkingTime must be more than changingColorTimerIdle");
		}
		if (changingColorTimerIdle <= 0)
		{
			throw new IllegalArgumentException("changingColorTimerIdle must be more than zero");
		}

		this.initialColor = initialColor;
		this.endColor = endColor;
		this.currentColorAlpha = initialColor.getAlpha();
		this.currentColorRed = initialColor.getRed();
		this.currentColorGreen = initialColor.getGreen();
		this.currentColorBlue = initialColor.getBlue();

		double colorChangingSpeed = (blinkingTime / 2) / changingColorTimerIdle;
		this.alphaIncrement = (endColor.getAlpha() - initialColor.getAlpha()) / colorChangingSpeed;
		this.redIncrement = (endColor.getRed() - initialColor.getRed()) / colorChangingSpeed;
		this.greenIncrement = (endColor.getGreen() - initialColor.getGreen()) / colorChangingSpeed;
		this.blueIncrement = (endColor.getBlue() - initialColor.getBlue()) / colorChangingSpeed;
	}

	/**
	 * Calculate next color
	 */
	void nextColor()
	{
		currentColorAlpha += alphaIncrement;
		currentColorRed += redIncrement;
		currentColorGreen += greenIncrement;
		currentColorBlue += blueIncrement;

		int minimumAlpha = Math.min(initialColor.getAlpha(), endColor.getAlpha());
		int minimumRed = Math.min(initialColor.getRed(), endColor.getRed());
		int minimumGreen = Math.min(initialColor.getGreen(), endColor.getGreen());
		int minimumBlue = Math.min(initialColor.getBlue(), endColor.getBlue());

		int maximumAlpha = Math.max(initialColor.getAlpha(), endColor.getAlpha());
		int maximumRed = Math.max(initialColor.getRed(), endColor.getRed());
		int maximumGreen = Math.max(initialColor.getGreen(), endColor.getGreen());
		int maximumBlue = Math.max(initialColor.getBlue(), endColor.getBlue());

		if (Math.round(currentColorAlpha) <= minimumAlpha)
		{
			currentColorAlpha = minimumAlpha;
			alphaIncrement = -alphaIncrement;
		}
		if (Math.round(currentColorAlpha) >= maximumAlpha)
		{
			currentColorAlpha = maximumAlpha;
			alphaIncrement = -alphaIncrement;
		}

		if (Math.round(currentColorRed) <= minimumRed)
		{
			currentColorRed = minimumRed;
			redIncrement = -redIncrement;
		}
		if (Math.round(currentColorRed) >= maximumRed)
		{
			currentColorRed = maximumRed;
			redIncrement = -redIncrement;
		}

		if (Math.round(currentColorGreen) <= minimumGreen)
		{
			currentColorGreen = minimumGreen;
			greenIncrement = -greenIncrement;
		}
		if (Math.round(currentColorGreen) >= maximumGreen)
		{
			currentColorGreen = maximumGreen;
			greenIncrement = -greenIncrement;
		}

		if (Math.round(currentColorBlue) <= minimumBlue)
		{
			currentColorBlue = minimumBlue;
			blueIncrement = -blueIncrement;
		}
		if (Math.round(currentColorBlue) >= maximumBlue)
		{
			currentColorBlue = maximumBlue;
			blueIncrement = -blueIncrement;
		}
	}

	/**
	 * Get current color
	 *
	 * @return current color
	 */
	public Color getColor()
	{
		return new Color((int) Math.round(currentColorRed), (int) Math.round(currentColorGreen), (int) Math.round(currentColorBlue), (int) Math.round(currentColorAlpha));
	}

	public Color getInitialColor()
	{
		return initialColor;
	}
}
