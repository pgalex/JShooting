package com.jshooting.logics;

import java.util.Calendar;
import java.util.Date;

/**
 * Using to modify date and time
 *
 * @author pgalex
 */
public class DateModifier
{
	/**
	 * Set exists date's time as day begin (00:00:00.0)
	 *
	 * @param date date which time need to set as day begin. Must be not null
	 * @return date with time set to day begin
	 * @throws IllegalArgumentException date is null
	 */
	public static Date createDateAsDayBegin(Date date) throws IllegalArgumentException
	{
		if (date == null)
		{
			throw new IllegalArgumentException("date is null");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Set exists date's time as day end (23:59:59.999)
	 *
	 * @param date date which time need to set as day end. Must be not null
	 * @return date with time set to day end
	 * @throws IllegalArgumentException date is null
	 */
	public static Date createDateAsDayEnd(Date date) throws IllegalArgumentException
	{
		if (date == null)
		{
			throw new IllegalArgumentException("date is null");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date createTodayBegin()
	{
		return createDateAsDayBegin(new Date());
	}

	public static Date createTodayEnd()
	{
		return createDateAsDayEnd(new Date());
	}
}
