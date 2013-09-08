package com.jshooting.modelTests;

import com.jshooting.model.MissMarksArray;
import java.awt.geom.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тесты класса MissMarksArray
 *
 * @author pgalex
 */
public class MissMarksArrayTest
{
	@Test
	public void gettingWithIndexOutOfBounds()
	{
	}

	@Test
	public void addingNullMissMark()
	{
	}

	@Test
	public void clearing()
	{
		MissMarksArray marksArray = new MissMarksArray();
		marksArray.add(new Point2D.Double(5, 10));
		marksArray.add(new Point2D.Double(11, 12));
		marksArray.clear();
		assertEquals(0, marksArray.count());
	}

	@Test
	public void deletingAtCoordinatesAllMarksAt()
	{
		final double testPointX = 50;
		final double testPointY = -10;
		MissMarksArray marksArray = new MissMarksArray();
		marksArray.add(new Point2D.Double(testPointX + MissMarksArray.MISS_MAKR_SIZE / 2.0, testPointY + MissMarksArray.MISS_MAKR_SIZE / 2.0));
		marksArray.add(new Point2D.Double(testPointX + MissMarksArray.MISS_MAKR_SIZE / 2.0, testPointY + MissMarksArray.MISS_MAKR_SIZE / 2.0));
		marksArray.deleteFirstMissMarkAtCoordinates(testPointX, testPointY);
		assertEquals(1, marksArray.count());
	}

	@Test
	public void deletingAtCoordinatesNoMarksAt()
	{
		final double testPointX = 50;
		final double testPointY = -10;
		MissMarksArray marksArray = new MissMarksArray();
		marksArray.add(new Point2D.Double(testPointX + MissMarksArray.MISS_MAKR_SIZE + 5, testPointY + MissMarksArray.MISS_MAKR_SIZE - 5));
		marksArray.add(new Point2D.Double(testPointX + MissMarksArray.MISS_MAKR_SIZE + 5, testPointY + MissMarksArray.MISS_MAKR_SIZE - 5));
		marksArray.deleteFirstMissMarkAtCoordinates(testPointX, testPointY);
		assertEquals(2, marksArray.count());
	}
}