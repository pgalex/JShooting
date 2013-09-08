package com.jshooting.forms;

import java.awt.Graphics;

/**
 * Делегат перерисовки панели мишени отметок промахов
 *
 * @author pgalex
 */
public interface MissMarksTargetPanelPaintDelegate
{
	public void paintOnTargetPanel(Graphics panelGraphics);
}
