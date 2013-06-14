package com.jshooting.shootingDatabase;

import java.util.Date;

/**
 * Shooting training
 *
 * @author pgalex
 */
public class ShootingTraining
{
	/**
	 * Id
	 */
	private long id;
	/**
	 * Спортсмен
	 */
	private Sportsman sportsman;
	/**
	 * Дата
	 */
	private Date date;
	/**
	 * Тип тренировки
	 */
	private ShootingTrainingType type;
	/**
	 * Средство
	 */
	private TrainingMethod trainingMethod;
	/**
	 * Описание погоды
	 */
	private String weather;
	/**
	 * Примечание
	 */
	private String comments;
	/**
	 * Кол-во лежа в покое
	 */
	private int numLyingInRest;
	/**
	 * Промахов лежа в покое
	 */
	private int missLyingInRest;
	/**
	 * Кол-во лежа с нагрузки
	 */
	private int numLyingLoading;
	/**
	 * Промахов лежа с нагрузки
	 */
	private int missLyingLoading;
	/**
	 * Кол-во лежа соревновательно
	 */
	private int numLyingCompetition;
	/**
	 * Промахов лежа соревновательно
	 */
	private int missLyingCompetition;
	/**
	 * Кол-во строя в покое
	 */
	private int numStandingInRest;
	/**
	 * Промахов стоя в покое
	 */
	private int missStandingInRest;
	/**
	 * Кол-во стоя с нагрузки
	 */
	private int numStandingLoading;
	/**
	 * Промахов стоя с нагрузки
	 */
	private int missStandingLoading;
	/**
	 * Кол-во стоя соревновательно
	 */
	private int numStandingCompetition;
	/**
	 * Промахов стоя соревновательно
	 */
	private int missStandingCompetition;
	/**
	 * Время до первого выстрела лежа с нагрузки (секунд)
	 */
	private int firstLyingLoading;
	/**
	 * Время до первого лежа соревновательно (секунд)
	 */
	private int firstLyingCompetition;
	/**
	 * Задержка на рубеже лежа с нагрузки (секунд)
	 */
	private int delayLyingLoading;
	/**
	 * Задержка на рубеже лежа соревновательно (секунд)
	 */
	private int delayLyingCompetition;
	/**
	 * Время до первого выстрела стоя с нагрузки (секунд)
	 */
	private int firstStandingLoading;
	/**
	 * Время до первого стоя соревновательно (секунд)
	 */
	private int firstStandingCompetition;
	/**
	 * Задержка на рубеже стоя с нагрузки (секунд)
	 */
	private int delayStandingLoading;
	/**
	 * Задержка на рубже стоя соревновательно (секунд)
	 */
	private int delayStandingCompetition;
	/**
	 * Пристрелка
	 */
	private int zeroingIn;
	/**
	 * Тренаж (минут)
	 */
	private int trail;
	/**
	 * Скатт (минут)
	 */
	private int scatt;
	/**
	 * Отметки промахов лежа
	 */
	private byte[] missMarksLying;
	/**
	 * Отметки промахов стоя
	 */
	private byte[] missMarksStanding;

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
	 * Спортсмен
	 *
	 * @return the sportsman
	 */
	public Sportsman getSportsman()
	{
		return sportsman;
	}

	/**
	 * Спортсмен
	 *
	 * @param sportsman the sportsman to set
	 */
	public void setSportsman(Sportsman sportsman)
	{
		this.sportsman = sportsman;
	}

	/**
	 * Дата
	 *
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Дата
	 *
	 * @param date the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * Тип тренировки
	 *
	 * @return the type
	 */
	public ShootingTrainingType getType()
	{
		return type;
	}

	/**
	 * Тип тренировки
	 *
	 * @param type the type to set
	 */
	public void setType(ShootingTrainingType type)
	{
		this.type = type;
	}

	/**
	 * Средство
	 *
	 * @return the trainingMethod
	 */
	public TrainingMethod getTrainingMethod()
	{
		return trainingMethod;
	}

	/**
	 * Средство
	 *
	 * @param trainingMethod the trainingMethod to set
	 */
	public void setTrainingMethod(TrainingMethod trainingMethod)
	{
		this.trainingMethod = trainingMethod;
	}

	/**
	 * Описание погоды
	 *
	 * @return the weather
	 */
	public String getWeather()
	{
		return weather;
	}

	/**
	 * Описание погоды
	 *
	 * @param weather the weather to set
	 */
	public void setWeather(String weather)
	{
		this.weather = weather;
	}

	/**
	 * Примечание
	 *
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * Примечание
	 *
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * Кол-во лежа в покое
	 *
	 * @return the numLyingInRest
	 */
	public int getNumLyingInRest()
	{
		return numLyingInRest;
	}

	/**
	 * Кол-во лежа в покое
	 *
	 * @param numLyingInRest the numLyingInRest to set
	 */
	public void setNumLyingInRest(int numLyingInRest)
	{
		this.numLyingInRest = numLyingInRest;
	}

	/**
	 * Промахов лежа в покое
	 *
	 * @return the missLyingInRest
	 */
	public int getMissLyingInRest()
	{
		return missLyingInRest;
	}

	/**
	 * Промахов лежа в покое
	 *
	 * @param missLyingInRest the missLyingInRest to set
	 */
	public void setMissLyingInRest(int missLyingInRest)
	{
		this.missLyingInRest = missLyingInRest;
	}

	/**
	 * Кол-во лежа с нагрузки
	 *
	 * @return the numLyingLoding
	 */
	public int getNumLyingLoading()
	{
		return numLyingLoading;
	}

	/**
	 * Кол-во лежа с нагрузки
	 *
	 * @param numLyingLoding the numLyingLoding to set
	 */
	public void setNumLyingLoading(int numLyingLoding)
	{
		this.numLyingLoading = numLyingLoding;
	}

	/**
	 * Промахов лежа с нагрузки
	 *
	 * @return the missLyingLoading
	 */
	public int getMissLyingLoading()
	{
		return missLyingLoading;
	}

	/**
	 * Промахов лежа с нагрузки
	 *
	 * @param missLyingLoading the missLyingLoading to set
	 */
	public void setMissLyingLoading(int missLyingLoading)
	{
		this.missLyingLoading = missLyingLoading;
	}

	/**
	 * Кол-во лежа соревновательно
	 *
	 * @return the numLyingCompetition
	 */
	public int getNumLyingCompetition()
	{
		return numLyingCompetition;
	}

	/**
	 * Кол-во лежа соревновательно
	 *
	 * @param numLyingCompetition the numLyingCompetition to set
	 */
	public void setNumLyingCompetition(int numLyingCompetition)
	{
		this.numLyingCompetition = numLyingCompetition;
	}

	/**
	 * Промахов лежа соревновательно
	 *
	 * @return the missLyingCompetition
	 */
	public int getMissLyingCompetition()
	{
		return missLyingCompetition;
	}

	/**
	 * Промахов лежа соревновательно
	 *
	 *
	 * @param missLyingCompetition missLyingCompetition to set
	 */
	public void setMissLyingCompetition(int missLyingCompetition)
	{
		this.missLyingCompetition = missLyingCompetition;
	}

	/**
	 * Кол-во строя в покое
	 *
	 * @return the numStandingInRest
	 */
	public int getNumStandingInRest()
	{
		return numStandingInRest;
	}

	/**
	 * Кол-во строя в покое
	 *
	 * @param numStandingInRest the numStandingInRest to set
	 */
	public void setNumStandingInRest(int numStandingInRest)
	{
		this.numStandingInRest = numStandingInRest;
	}

	/**
	 * Промахов стоя в покое
	 *
	 * @return the missStandingInRest
	 */
	public int getMissStandingInRest()
	{
		return missStandingInRest;
	}

	/**
	 * Промахов стоя в покое
	 *
	 * @param missStandingInRest the missStandingInRest to set
	 */
	public void setMissStandingInRest(int missStandingInRest)
	{
		this.missStandingInRest = missStandingInRest;
	}

	/**
	 * Кол-во стоя с нагрузки
	 *
	 * @return the numStandingLoading
	 */
	public int getNumStandingLoading()
	{
		return numStandingLoading;
	}

	/**
	 * Кол-во стоя с нагрузки
	 *
	 * @param numStandingLoading the numStandingLoading to set
	 */
	public void setNumStandingLoading(int numStandingLoading)
	{
		this.numStandingLoading = numStandingLoading;
	}

	/**
	 * Промахов стоя с нагрузки
	 *
	 * @return the missStandingLoading
	 */
	public int getMissStandingLoading()
	{
		return missStandingLoading;
	}

	/**
	 * Промахов стоя с нагрузки
	 *
	 * @param missStandingLoading the missStandingLoading to set
	 */
	public void setMissStandingLoading(int missStandingLoading)
	{
		this.missStandingLoading = missStandingLoading;
	}

	/**
	 * Кол-во стоя соревновательно
	 *
	 * @return the numStandingCompetition
	 */
	public int getNumStandingCompetition()
	{
		return numStandingCompetition;
	}

	/**
	 * Кол-во стоя соревновательно
	 *
	 * @param numStandingCompetition the numStandingCompetition to set
	 */
	public void setNumStandingCompetition(int numStandingCompetition)
	{
		this.numStandingCompetition = numStandingCompetition;
	}

	/**
	 * Промахов стоя соревновательно
	 *
	 * @return the missStandingCompetition
	 */
	public int getMissStandingCompetition()
	{
		return missStandingCompetition;
	}

	/**
	 * Промахов стоя соревновательно
	 *
	 * @param missStandingCompetition the missStandingCompetition to set
	 */
	public void setMissStandingCompetition(int missStandingCompetition)
	{
		this.missStandingCompetition = missStandingCompetition;
	}

	/**
	 * Время до первого выстрела лежа с нагрузки (секунд)
	 *
	 * @return the firstLyingLoading
	 */
	public int getFirstLyingLoading()
	{
		return firstLyingLoading;
	}

	/**
	 * Время до первого выстрела лежа с нагрузки (секунд)
	 *
	 * @param firstLyingLoading the firstLyingLoading to set
	 */
	public void setFirstLyingLoading(int firstLyingLoading)
	{
		this.firstLyingLoading = firstLyingLoading;
	}

	/**
	 * Время до первого лежа соревновательно (секунд)
	 *
	 * @return the firstLyingCompetition
	 */
	public int getFirstLyingCompetition()
	{
		return firstLyingCompetition;
	}

	/**
	 * Время до первого лежа соревновательно (секунд)
	 *
	 * @param firstLyingCompetition the firstLyingCompetition to set
	 */
	public void setFirstLyingCompetition(int firstLyingCompetition)
	{
		this.firstLyingCompetition = firstLyingCompetition;
	}

	/**
	 * Задержка на рубеже лежа с нагрузки (секунд)
	 *
	 * @return the delayLyingLoading
	 */
	public int getDelayLyingLoading()
	{
		return delayLyingLoading;
	}

	/**
	 * Задержка на рубеже лежа с нагрузки (секунд)
	 *
	 * @param delayLyingLoading the delayLyingLoading to set
	 */
	public void setDelayLyingLoading(int delayLyingLoading)
	{
		this.delayLyingLoading = delayLyingLoading;
	}

	/**
	 * Задержка на рубеже лежа соревновательно (секунд)
	 *
	 * @return the delayLyingCompetition
	 */
	public int getDelayLyingCompetition()
	{
		return delayLyingCompetition;
	}

	/**
	 * Задержка на рубеже лежа соревновательно (секунд)
	 *
	 * @param delayLyingCompetition the delayLyingCompetition to set
	 */
	public void setDelayLyingCompetition(int delayLyingCompetition)
	{
		this.delayLyingCompetition = delayLyingCompetition;
	}

	/**
	 * Время до первого выстрела стоя с нагрузки (секунд)
	 *
	 * @return the firstStandingLoading
	 */
	public int getFirstStandingLoading()
	{
		return firstStandingLoading;
	}

	/**
	 * Время до первого выстрела стоя с нагрузки (секунд)
	 *
	 * @param firstStandingLoading the firstStandingLoading to set
	 */
	public void setFirstStandingLoading(int firstStandingLoading)
	{
		this.firstStandingLoading = firstStandingLoading;
	}

	/**
	 * Время до первого стоя соревновательно (секунд)
	 *
	 * @return the firstStandingCompetition
	 */
	public int getFirstStandingCompetition()
	{
		return firstStandingCompetition;
	}

	/**
	 * Время до первого стоя соревновательно (секунд)
	 *
	 * @param firstStandingCompetition the firstStandingCompetition to set
	 */
	public void setFirstStandingCompetition(int firstStandingCompetition)
	{
		this.firstStandingCompetition = firstStandingCompetition;
	}

	/**
	 * Задержка на рубеже стоя с нагрузки (секунд)
	 *
	 * @return the delayStandingLoading
	 */
	public int getDelayStandingLoading()
	{
		return delayStandingLoading;
	}

	/**
	 * Задержка на рубеже стоя с нагрузки (секунд)
	 *
	 * @param delayStandingLoading the delayStandingLoading to set
	 */
	public void setDelayStandingLoading(int delayStandingLoading)
	{
		this.delayStandingLoading = delayStandingLoading;
	}

	/**
	 * Задержка на рубже стоя соревновательно (секунд)
	 *
	 * @return the delayStandingCompetition
	 */
	public int getDelayStandingCompetition()
	{
		return delayStandingCompetition;
	}

	/**
	 * Задержка на рубже стоя соревновательно (секунд)
	 *
	 * @param delayStandingCompetition the delayStandingCompetition to set
	 */
	public void setDelayStandingCompetition(int delayStandingCompetition)
	{
		this.delayStandingCompetition = delayStandingCompetition;
	}

	/**
	 * Пристрелка
	 *
	 * @return the zeroingIn
	 */
	public int getZeroingIn()
	{
		return zeroingIn;
	}

	/**
	 * Пристрелка
	 *
	 * @param zeroingIn the zeroingIn to set
	 */
	public void setZeroingIn(int zeroingIn)
	{
		this.zeroingIn = zeroingIn;
	}

	/**
	 * Тренаж (минут)
	 *
	 * @return the trail
	 */
	public int getTrail()
	{
		return trail;
	}

	/**
	 * Тренаж (минут)
	 *
	 * @param trail the trail to set
	 */
	public void setTrail(int trail)
	{
		this.trail = trail;
	}

	/**
	 * Скатт (минут)
	 *
	 * @return the scatt
	 */
	public int getScatt()
	{
		return scatt;
	}

	/**
	 * Скатт (минут)
	 *
	 * @param scatt the scatt to set
	 */
	public void setScatt(int scatt)
	{
		this.scatt = scatt;
	}

	/**
	 * Отметки промахов лежа
	 *
	 * @return the missMarksLying
	 */
	public byte[] getMissMarksLying()
	{
		return missMarksLying;
	}

	/**
	 * Отметки промахов лежа
	 *
	 * @param missMarksLying the missMarksLying to set
	 */
	public void setMissMarksLying(byte[] missMarksLying)
	{
		this.missMarksLying = missMarksLying;
	}

	/**
	 * Отметки промахов стоя
	 *
	 * @return the missMarksStanding
	 */
	public byte[] getMissMarksStanding()
	{
		return missMarksStanding;
	}

	/**
	 * Отметки промахов стоя
	 *
	 * @param missMarksStanding the missMarksStanding to set
	 */
	public void setMissMarksStanding(byte[] missMarksStanding)
	{
		this.missMarksStanding = missMarksStanding;
	}
}
