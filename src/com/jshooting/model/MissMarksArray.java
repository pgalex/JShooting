package com.jshooting.model;

import java.awt.geom.Point2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Отметки промахов
 *
 * @author pgalex
 */
public class MissMarksArray
{
	/**
	 * Размер отметки промаха
	 */
	public static double MISS_MAKR_SIZE = 6;
	
	static
	{
		//ImageIcon missMarkIcon = new javax.swing.ImageIcon(MissMarksArray.class.getClass().getResource("/com/jshooting/resources/отметкаПромаха.png"));
		//MISS_MAKR_SIZE = (missMarkIcon.getIconWidth() + missMarkIcon.getIconHeight()) / 2.0;
	}
	private ArrayList<Point2D> missMarks;

	/**
	 * Создать пустым
	 */
	public MissMarksArray()
	{
		missMarks = new ArrayList<Point2D>();
	}

	/**
	 * Получить кол-во отметок промахов
	 *
	 * @return
	 */
	public int count()
	{
		return missMarks.size();
	}

	/**
	 * Получить отметку промаха по индексу
	 *
	 * @param index индекс отметки о промахе. Должен быть от 0 до count - 1
	 * @return отметка промаха по индексу
	 * @throws IllegalArgumentException index имеет значение вне допустимого
	 * диапазона
	 */
	public Point2D get(int index) throws IllegalArgumentException
	{
		if (index < 0 || index > missMarks.size())
		{
			throw new IllegalArgumentException("index out of bounds");
		}
		
		return missMarks.get(index);
	}

	/**
	 * Удалить все отметки промахов
	 */
	public void clear()
	{
		missMarks.clear();
	}

	/**
	 * Добавить отметку промаха
	 *
	 * @param missMarkToAdd добавляемая отметка промаха. Должа быть не null
	 * @throws IllegalArgumentException missMarkToAdd null
	 */
	public void add(Point2D missMarkToAdd) throws IllegalArgumentException
	{
		if (missMarkToAdd == null)
		{
			throw new IllegalArgumentException("missMarkToAdd is null");
		}
		
		missMarks.add(missMarkToAdd);
	}

	/**
	 * Удалить первую в списке отметку промаха, находящуюся в точке с
	 * координатами. Если отметок в данных координатах нет, то ничего удалено не
	 * будет
	 *
	 * @param x x координата точки по которой будет удаляться отмека промаха
	 * @param y y координата точки по которой будет удаляться отмека промаха
	 */
	public void deleteFirstMissMarkAtCoordinates(double x, double y)
	{
		ArrayList<Point2D> marksAtCoordinates = findMissMarksAtCoordinates(x, y);
		if (marksAtCoordinates.size() > 0)
		{
			missMarks.remove(marksAtCoordinates.get(0));
		}
	}

	/**
	 * Найти отметки промахов близкие к точке с координатами
	 *
	 * @param x x координата точки по которой будет находится отмека промаха
	 * @param y y координата точки по которой будет находится отмека промаха
	 * @return найденные точки остановки. Пустой если ничего не найдено
	 */
	private ArrayList<Point2D> findMissMarksAtCoordinates(double x, double y)
	{
		ArrayList<Point2D> foundMissMarks = new ArrayList<Point2D>();
		for (Point2D mark : missMarks)
		{
			if (mark.distance(x, y) <= MISS_MAKR_SIZE)
			{
				foundMissMarks.add(mark);
			}
		}
		
		return foundMissMarks;
	}
	
	public void writeToStream(DataOutputStream outputStream) throws IllegalArgumentException, IOException
	{
		if (outputStream == null)
		{
			throw new IllegalArgumentException("outputStream is null");
		}
		
		outputStream.writeInt(missMarks.size());
		for (Point2D mark : missMarks)
		{
			outputStream.writeDouble(mark.getX());
			outputStream.writeDouble(mark.getY());
		}
	}
	
	public void readFromStream(DataInputStream inputStream) throws IllegalArgumentException, IOException
	{
		if (inputStream == null)
		{
			throw new IllegalArgumentException("inputStream is null");
		}
		
		int marksCount = inputStream.readInt();
		missMarks = new ArrayList<Point2D>(marksCount);
		for (int i = 0; i < marksCount; i++)
		{
			double missMarkX = inputStream.readDouble();
			double missMarkY = inputStream.readDouble();
			missMarks.add(new Point2D.Double(missMarkX, missMarkY));
		}
	}
}