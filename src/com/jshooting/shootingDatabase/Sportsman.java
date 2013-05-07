package com.jshooting.shootingDatabase;

/**
 * Sportsman
 *
 * @author pgalex
 */
public class Sportsman
{
	/**
	 * Id
	 */
	private long id;
	/**
	 * Name of sportsman
	 */
	private String name;
	/**
	 * Sportsman's team
	 */
	private Team team;

	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the team
	 */
	public Team getTeam()
	{
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team)
	{
		this.team = team;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
