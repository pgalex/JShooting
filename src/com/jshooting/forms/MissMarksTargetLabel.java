package com.jshooting.forms;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 * Label с изображение мишени для отметок промахов. Позволяет выполнять рисовать
 * отметок во время перерисовки
 *
 * @author pgalex
 */
public class MissMarksTargetLabel extends JLabel
{
	private MissMarksTargetPanelPaintDelegate paintDelegate;

	public MissMarksTargetLabel(MissMarksTargetPanelPaintDelegate paintDelegate)
	{
		super();
		this.paintDelegate = paintDelegate;
	}

	@Override
	protected void paintComponent(Graphics grphcs)
	{
		super.paintComponent(grphcs);
		if (paintDelegate != null)
		{
			paintDelegate.paintOnTargetPanel(grphcs);
		}
	}
}
