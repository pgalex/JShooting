package com.jshooting.shootingDatabase;

import java.io.Serializable;

/**
 * Team
 *
 * @author pgalex
 */
public class Team implements Serializable
{
	/**
	 * Id
	 */
	private long id;
	/**
	 * Name of team
	 */
	private String name;

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
	 * Name of team
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Name of team
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}
