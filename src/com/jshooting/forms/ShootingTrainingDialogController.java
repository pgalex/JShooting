package com.jshooting.forms;

import com.jshooting.logics.DateModifier;
import com.jshooting.logics.PlacesGetter;
import com.jshooting.logics.PlacesNamesListFormer;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.SportsmansByTeamGetter;
import com.jshooting.logics.TeamsGetter;
import com.jshooting.logics.TrainingMethodsGetter;
import com.jshooting.logics.exceptions.ShootingLogicsException;
import com.jshooting.model.Sportsman;
import com.jshooting.model.Team;
import com.jshooting.model.TrainingMethod;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * Cantains logics and common part of dialogs displaying and modifing shooting
 * training parameters
 *
 * @author pgalex
 */
public class ShootingTrainingDialogController
{
	/**
	 * Model for teams combo box
	 */
	private DefaultComboBoxModel teamsComboBoxModel;
	/**
	 * Model for sportsmans combo box
	 */
	private DefaultComboBoxModel sportsmansComboBoxModel;
	/**
	 * Model for training methods combo box
	 */
	private DefaultComboBoxModel trainingMethodsComboBoxModel;
	/**
	 * Using to refill sportsmans combo by selected team
	 */
	private SportsmansByTeamGetter sportsmansGetter;
	/**
	 * Using to get teams
	 */
	private TeamsGetter teamGetter;
	/**
	 * Using to get training methods
	 */
	private TrainingMethodsGetter trainingMethodsGetter;
	/**
	 * Using to get places
	 */
	private PlacesGetter placesGetter;

	/**
	 * Create dialog controller
	 *
	 * @param logicsFactory logics factory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public ShootingTrainingDialogController(ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		sportsmansGetter = logicsFactory.createSportsmansByTeamGetter();
		teamGetter = logicsFactory.createTeamsGetter();
		trainingMethodsGetter = logicsFactory.createTrainingMethodsGetter();
		placesGetter = logicsFactory.createPlacesGetter();

		teamsComboBoxModel = new DefaultComboBoxModel();
		sportsmansComboBoxModel = new DefaultComboBoxModel();
		trainingMethodsComboBoxModel = new DefaultComboBoxModel();
	}

	/**
	 * Find places names that exists in given date
	 *
	 * @param date date using to find places name. Must be not null
	 * @return places names that exists in given date
	 * @throws IllegalArgumentException date is null
	 */
	public String findPlacesNamesByTrainingDate(Date date) throws IllegalArgumentException
	{
		if (date == null)
		{
			throw new IllegalArgumentException("date is null");
		}

		try
		{
			Date periodDateFrom = DateModifier.createDateAsDayBegin(date);
			Date periodDateTo = DateModifier.createDateAsDayEnd(date);

			return PlacesNamesListFormer.getPlacesNamesString(placesGetter.getPlacesByPeriod(periodDateFrom, periodDateTo));
		}
		catch (ShootingLogicsException ex)
		{
			return "";
		}
	}

	/**
	 * Refill training methods combo box model with training methods table
	 *
	 */
	public void refillTrainingMethodComboBox()
	{
		try
		{
			getTrainingMethodsComboBoxModel().removeAllElements();
			List<TrainingMethod> allMethods = trainingMethodsGetter.getAllTrainingMethods();
			for (TrainingMethod trainingMethod : allMethods)
			{
				getTrainingMethodsComboBoxModel().addElement(trainingMethod);
			}
		}
		catch (ShootingLogicsException ex)
		{
			getTrainingMethodsComboBoxModel().removeAllElements();
		}
	}

	/**
	 * Refill teams combo box model by teams table. Model will be empty if can no
	 * get access to table
	 */
	public void refillTeamsComboBoxModel()
	{
		try
		{
			getTeamsComboBoxModel().removeAllElements();
			List<Team> allTeams = teamGetter.getAllTeams();
			for (Team team : allTeams)
			{
				getTeamsComboBoxModel().addElement(team);
			}
		}
		catch (ShootingLogicsException ex)
		{
			getTeamsComboBoxModel().removeAllElements();
		}
	}

	/**
	 * Fill sportmans combo box model by sportmans in given team. Empty if team
	 * not selected or can not get access to sportmans table
	 *
	 * @param team team which sportsmans need to add to model
	 */
	public void fillSportmanComboBoxByTeam(Object team)
	{
		try
		{
			getSportsmansComboBoxModel().removeAllElements();
			if (team instanceof Team)
			{
				Team selectedTeam = (Team) team;
				sportsmansGetter.setFilteringTeam(selectedTeam);
				List<Sportsman> sportsmansInSelectedTeam = sportsmansGetter.getSportsmansInFilteringTeam();
				for (Sportsman sportsman : sportsmansInSelectedTeam)
				{
					getSportsmansComboBoxModel().addElement(sportsman);
				}
			}
		}
		catch (ShootingLogicsException ex)
		{
			getSportsmansComboBoxModel().removeAllElements();
		}
	}

	/**
	 * Model for teams combo box
	 *
	 * @return the teamsComboBoxModel
	 */
	public DefaultComboBoxModel getTeamsComboBoxModel()
	{
		return teamsComboBoxModel;
	}

	/**
	 * Model for sportsmans combo box
	 *
	 * @return the sportsmansComboBoxModel
	 */
	public DefaultComboBoxModel getSportsmansComboBoxModel()
	{
		return sportsmansComboBoxModel;
	}

	/**
	 * Model for training methods combo box
	 *
	 * @return the trainingMethodsComboBoxModel
	 */
	public DefaultComboBoxModel getTrainingMethodsComboBoxModel()
	{
		return trainingMethodsComboBoxModel;
	}
}
