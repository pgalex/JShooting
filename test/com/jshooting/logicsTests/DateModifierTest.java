package com.jshooting.logicsTests;

import com.jshooting.logics.DateModifier;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of DateModifier class
 *
 * @author pgalex
 */
public class DateModifierTest
{
	@Test
	public void settingDateAsDayBeginTest()
	{
		Date today = new Date();
		Date dayBegin = DateModifier.createDateAsDayBegin(today);
		assertTrue(today.after(dayBegin) || today.equals(dayBegin));
	}

	@Test
	public void settingDateAsDayEndTest()
	{
		Date today = new Date();
		Date dayEnd = DateModifier.createDateAsDayEnd(today);
		assertTrue(today.before(dayEnd) || today.equals(dayEnd));
	}

	
	@Test
	public void settingNullDateAsDayBeginTest()
	{
		try
		{
			DateModifier.createDateAsDayBegin(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}
	
	@Test
	public void settingNullDateAsDayEndTest()
	{
		try
		{
			DateModifier.createDateAsDayEnd(null);
			fail();
		}
		catch (IllegalArgumentException ex)
		{
			// ok
		}
	}
}