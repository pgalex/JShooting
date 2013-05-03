package com.jshooting.hiberanteShootingDatabase;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.TeamsTable;

/**
 * Hibernate powered shooting database
 *
 * @author pgalex
 */
public class HibernateShootingDatabase implements ShootingDatabase
{
	/**
	 * Get teams table
	 *
	 * @return database's table of teams
	 */
	@Override
	public TeamsTable getTeamsTable()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
