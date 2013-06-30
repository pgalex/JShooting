package com.jshooting.shootingDatabase;

import java.util.Date;

/**
 * УТС. Place of training
 *
 * @author pgalex
 */
public class Place
{
	/**
	 * Id
	 */
	private long id;
	/**
	 * Место проведения
	 */
	private String name;
	/**
	 * Дата начала
	 */
	private Date beginDate;
	/**
	 * Дата окончания
	 */
	private Date endDate;

	/**
	 * Id
	 *
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Id
	 *
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Место проведения
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Место проведения
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Дата начала
	 *
	 * @return the beginDate
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * Дата начала
	 *
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	/**
	 * Дата окончания
	 *
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Дата окончания
	 *
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
}
