package com.jshooting.forms;

import java.awt.Color;

/**
 * Constants of components highlighting
 *
 * @author pgalex
 */
public class HighlightingConstants
{
	/**
	 * Time while components changes color to highlighted state and back
	 */
	public static final int BLINKING_TIME = 1200;
	public static final int WARNINGS_HIGHLIGHTING_TIME = BLINKING_TIME * 2;
	/**
	 * Highlighting color which means "accepted" or "good" option
	 */
	public static final Color GOOD_HIGHLIGHT_COLOR = new Color(160, 255, 160, 255);
	/**
	 * Highlighting color which means "error"
	 */
	public static final Color ERROR_HIGHLIGHT_COLOR = new Color(255, 160, 160, 255);
}
