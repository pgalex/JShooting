package com.jshooting.forms;

import com.jshooting.shootingDatabase.PlacesTable;
import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import com.jshooting.shootingDatabase.ShootingTrainingType;
import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.TrainingMethod;
import com.jshooting.shootingDatabase.TrainingMethodsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.awt.Window;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * Dialog for adding shooting training
 *
 * @author pgalex
 */
public class AddShootingTrainingDialog extends javax.swing.JDialog
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
	 * Table of sportsmans. Using to refill sportsmans combo by selected team
	 */
	private SportsmansTable sportsmansTable;
	/**
	 * Table of teams
	 */
	private TeamsTable teamsTable;
	/**
	 * Table of training methods
	 */
	private TrainingMethodsTable trainingMethodsTable;
	/**
	 * Table of shooting trainings
	 */
	private ShootingTrainingsTable shootingTrainingTable;
	/**
	 * Table of places
	 */
	private PlacesTable placesTable;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param teamsTable table of teams
	 * @param sportsmansTable table of sportsmans
	 * @param trainingMethodsTable table of training methods
	 * @param shootingTrainingTable table of shooting trainings
	 * @param placesTable table of places
	 * @throws IllegalArgumentException teamsTable, sportsmansTable, placesTable,
	 * shootingTrainingTable or trainingMethodsTable is null
	 */
	public AddShootingTrainingDialog(Window parentWindow, ModalityType modalityType,
					TeamsTable teamsTable, SportsmansTable sportsmansTable, TrainingMethodsTable trainingMethodsTable,
					ShootingTrainingsTable shootingTrainingTable, PlacesTable placesTable) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (teamsTable == null)
		{
			throw new IllegalArgumentException("teamsTable is null");
		}
		if (sportsmansTable == null)
		{
			throw new IllegalArgumentException("sportsmansTable is null");
		}
		if (trainingMethodsTable == null)
		{
			throw new IllegalArgumentException("trainingMethodsTable is null");
		}
		if (shootingTrainingTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingTable is null");
		}
		if (placesTable == null)
		{
			throw new IllegalArgumentException("placesTable is null");
		}

		this.teamsTable = teamsTable;
		this.sportsmansTable = sportsmansTable;
		this.trainingMethodsTable = trainingMethodsTable;
		this.shootingTrainingTable = shootingTrainingTable;
		this.placesTable = placesTable;

		teamsComboBoxModel = new DefaultComboBoxModel();
		sportsmansComboBoxModel = new DefaultComboBoxModel();
		trainingMethodsComboBoxModel = new DefaultComboBoxModel();

		initComponents();
		jLabelAddingToDatabaseAnimation.setVisible(false);

		fillTeamsComboBox();
		fillSportmanComboBoxBySelectedTeam();
		fillTrainingMethodComboBox();
		jDateChooserTrainingDate.setDate(new Date());
	}

	/**
	 * Fill training methods combo box model with training methods table. Model
	 * will be empty can not get access to table data
	 */
	private void fillTrainingMethodComboBox()
	{
		try
		{
			trainingMethodsComboBoxModel.removeAllElements();
			List<TrainingMethod> allMethods = trainingMethodsTable.getAllTrainingMethods();
			for (TrainingMethod trainingMethod : allMethods)
			{
				trainingMethodsComboBoxModel.addElement(trainingMethod);
			}
		}
		catch (DatabaseErrorException ex)
		{
			trainingMethodsComboBoxModel.removeAllElements();
		}
	}

	/**
	 * Fill teams combo box model by teams table. Model will be empty if can no
	 * get access to table
	 *
	 * @param teamsTable table of teams
	 */
	private void fillTeamsComboBox()
	{
		try
		{
			teamsComboBoxModel.removeAllElements();
			List<Team> allTeams = teamsTable.getAllTeams();
			for (Team team : allTeams)
			{
				teamsComboBoxModel.addElement(team);
			}
		}
		catch (DatabaseErrorException ex)
		{
			teamsComboBoxModel.removeAllElements();
		}
	}

	/**
	 * Fill sportmans combo box model by sportmans in team, selected in teams
	 * combo box. Empty if team not selected or can not get access to sportmans
	 * table
	 */
	private void fillSportmanComboBoxBySelectedTeam()
	{
		try
		{
			sportsmansComboBoxModel.removeAllElements();
			Object selectedItem = jComboBoxTeam.getSelectedItem();
			if (selectedItem instanceof Team)
			{
				Team selectedTeam = (Team) selectedItem;
				List<Sportsman> sportsmansInSelectedTeam = sportsmansTable.getSportsmansInTeam(selectedTeam);
				for (Sportsman sportsman : sportsmansInSelectedTeam)
				{
					sportsmansComboBoxModel.addElement(sportsman);
				}
			}
		}
		catch (DatabaseErrorException ex)
		{
			sportsmansComboBoxModel.removeAllElements();
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();
    jComboBoxSportsman = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    jComboBoxTeam = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    jComboBoxTrainingMethod = new javax.swing.JComboBox();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jTextFieldWeather = new javax.swing.JTextField();
    jTextFieldComments = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jDateChooserTrainingDate = new com.toedter.calendar.JDateChooser();
    jButtonAddTraining = new javax.swing.JButton();
    jLabel7 = new javax.swing.JLabel();
    jComboBoxTrainingType = new javax.swing.JComboBox();
    jPanel1 = new javax.swing.JPanel();
    jSpinnerNumLyingInRest = new javax.swing.JSpinner();
    jSpinnerMissLyingInRest = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading = new javax.swing.JSpinner();
    jSpinnerMissLyingLoading = new javax.swing.JSpinner();
    jSpinnerNumLyingCompetition = new javax.swing.JSpinner();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    jLabel14 = new javax.swing.JLabel();
    jSpinnerMissLyingCompetition = new javax.swing.JSpinner();
    jSpinnerNumStandingInRest = new javax.swing.JSpinner();
    jSpinnerMissStandingInRest = new javax.swing.JSpinner();
    jSpinnerDelayLyingLoading = new javax.swing.JSpinner();
    jSpinnerMissStandingLoading = new javax.swing.JSpinner();
    jSpinnerNumStandingCompetition = new javax.swing.JSpinner();
    jSpinnerMissStandingCompetition = new javax.swing.JSpinner();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jSpinnerNumStandingLoading = new javax.swing.JSpinner();
    jSpinnerFirstLyingLoading = new javax.swing.JSpinner();
    jSpinnerFirstLyingCompetition = new javax.swing.JSpinner();
    jSpinnerDelayLyingCompetition = new javax.swing.JSpinner();
    jSpinnerFirstStandingLoading = new javax.swing.JSpinner();
    jSpinnerFirstStandingCompetition = new javax.swing.JSpinner();
    jSpinnerDelayStandingLoading = new javax.swing.JSpinner();
    jSpinnerDelayStandingCompetition = new javax.swing.JSpinner();
    jLabel18 = new javax.swing.JLabel();
    jLabel19 = new javax.swing.JLabel();
    jLabel20 = new javax.swing.JLabel();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jLabel23 = new javax.swing.JLabel();
    jLabel24 = new javax.swing.JLabel();
    jLabel25 = new javax.swing.JLabel();
    jSpinnerTrail = new javax.swing.JSpinner();
    jLabel27 = new javax.swing.JLabel();
    jLabel26 = new javax.swing.JLabel();
    jSpinnerZeroingIn = new javax.swing.JSpinner();
    jSpinnerScatt = new javax.swing.JSpinner();
    jLabelAddingToDatabaseAnimation = new javax.swing.JLabel();
    jTextFieldPlaceName = new javax.swing.JTextField();
    jLabel28 = new javax.swing.JLabel();

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][]
      {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String []
      {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(jTable1);

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Добавить тренировку");
    setResizable(false);

    jLabel1.setText("Спортсмен");

    jComboBoxSportsman.setModel(sportsmansComboBoxModel);

    jLabel2.setText("Команда");

    jComboBoxTeam.setModel(teamsComboBoxModel);
    jComboBoxTeam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jComboBoxTeamActionPerformed(evt);
      }
    });

    jLabel3.setText("Дата");

    jComboBoxTrainingMethod.setModel(trainingMethodsComboBoxModel);

    jLabel4.setText("Средство");

    jLabel5.setText("Погода");

    jLabel6.setText("Комментарий");

    jButtonAddTraining.setText("Добавить тренировку");
    jButtonAddTraining.setToolTipText("Добавить тренировку для выбранного спортсмена");
    jButtonAddTraining.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTrainingActionPerformed(evt);
      }
    });

    jLabel7.setText("Тип тренировки");

    jComboBoxTrainingType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Комплексная", "Стрелковая", "Соревнование" }));

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jSpinnerNumLyingInRest.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerMissLyingInRest.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerNumLyingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerMissLyingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerNumLyingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Количество выстрелов");

    jLabel9.setText("В покое");

    jLabel10.setText("С нагрузки");

    jLabel11.setText("Соревн.");

    jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel12.setText("Лежа");

    jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel13.setText("Кол-во");

    jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel14.setText("Промахи");

    jSpinnerMissLyingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerNumStandingInRest.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerMissStandingInRest.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerDelayLyingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerMissStandingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerNumStandingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerMissStandingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel15.setText("Кол-во");

    jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel16.setText("Промахи");

    jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel17.setText("Стоя");

    jSpinnerNumStandingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerFirstLyingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerFirstLyingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerDelayLyingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerFirstStandingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerFirstStandingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerDelayStandingLoading.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerDelayStandingCompetition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jLabel18.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel18.setText("1 выстрел");

    jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel19.setText("Задержка");

    jLabel20.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel20.setText("1 выстрел");

    jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel21.setText("Задержка");

    jLabel22.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel22.setText("Лежа");

    jLabel23.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel23.setText("Стоя");

    jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel24.setText("Время стрельбы");

    jLabel25.setText("Пристрелка");

    jSpinnerTrail.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jLabel27.setText("Тренаж, минут");

    jLabel26.setText("Скатт, минут");

    jSpinnerZeroingIn.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    jSpinnerScatt.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createSequentialGroup()
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel9)
              .add(jLabel11)
              .add(jLabel10))
            .add(29, 29, 29)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                      .add(jSpinnerNumLyingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                      .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                      .add(jSpinnerMissLyingInRest)
                      .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                      .add(jSpinnerNumStandingInRest, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                      .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                      .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .add(jPanel1Layout.createSequentialGroup()
                        .add(jSpinnerMissStandingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))))))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerNumLyingLoading, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerNumLyingCompetition))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerMissLyingCompetition, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerMissLyingLoading))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerNumStandingCompetition, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerNumStandingLoading))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerMissStandingLoading, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerMissStandingCompetition))
                .add(0, 0, Short.MAX_VALUE)))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerFirstLyingLoading, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerFirstLyingCompetition))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerDelayLyingLoading, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerDelayLyingCompetition))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jSpinnerFirstStandingLoading, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                  .add(jSpinnerFirstStandingCompetition))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerDelayStandingCompetition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerDelayStandingLoading)))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                  .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                    .add(jLabel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                .add(0, 0, Short.MAX_VALUE))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))))
          .add(jPanel1Layout.createSequentialGroup()
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jLabel27)
                  .add(jLabel25))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerZeroingIn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerTrail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jSpinnerScatt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabel8)
          .add(jLabel24))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabel12)
          .add(jLabel17)
          .add(jLabel22)
          .add(jLabel23))
        .add(3, 3, 3)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jLabel13)
            .add(jLabel14))
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jLabel20)
            .add(jLabel21))
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jLabel16)
            .add(jLabel18)
            .add(jLabel19)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerNumStandingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerMissStandingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerNumLyingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerMissLyingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(jSpinnerFirstLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumStandingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissStandingLoading)
            .add(jSpinnerMissLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerDelayLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerFirstStandingLoading))
          .add(jSpinnerDelayStandingLoading, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerDelayStandingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerDelayLyingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissLyingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumStandingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissStandingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerFirstLyingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerFirstStandingCompetition))
          .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .add(18, 18, 18)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jLabel25, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerZeroingIn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerTrail))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jLabel26, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerScatt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabelAddingToDatabaseAnimation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/ajax-loader.gif"))); // NOI18N

    jTextFieldPlaceName.setEditable(false);

    jLabel28.setText("УТС");

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(layout.createSequentialGroup()
                .add(jLabel5)
                .add(0, 0, Short.MAX_VALUE))
              .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jLabel7)
                  .add(jLabel3)
                  .add(jLabel1)
                  .add(jLabel2)
                  .add(jLabel4)
                  .add(jLabel28))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jTextFieldPlaceName)
                  .add(jTextFieldWeather)
                  .add(jComboBoxTrainingMethod, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jComboBoxTrainingType, 0, 186, Short.MAX_VALUE)
                  .add(jComboBoxSportsman, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jComboBoxTeam, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jDateChooserTrainingDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(layout.createSequentialGroup()
            .add(jButtonAddTraining)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jLabelAddingToDatabaseAnimation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(0, 0, Short.MAX_VALUE))
          .add(layout.createSequentialGroup()
            .add(jLabel6)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
            .add(jTextFieldComments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 803, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(layout.createSequentialGroup()
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 269, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
          .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jComboBoxTeam, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel2))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jComboBoxSportsman, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel1))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
              .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jDateChooserTrainingDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jTextFieldPlaceName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel28))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jComboBoxTrainingType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel7))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jComboBoxTrainingMethod, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel4))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jTextFieldWeather, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel5))
            .add(49, 49, 49)))
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jTextFieldComments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabel6))
        .add(28, 28, 28)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jButtonAddTraining, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jLabelAddingToDatabaseAnimation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jComboBoxTeamActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTeamActionPerformed
  {//GEN-HEADEREND:event_jComboBoxTeamActionPerformed
		fillSportmanComboBoxBySelectedTeam();
  }//GEN-LAST:event_jComboBoxTeamActionPerformed

  private void jButtonAddTrainingActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTrainingActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTrainingActionPerformed
		Object selectedSportsmanItem = jComboBoxSportsman.getSelectedItem();
		Object selectedMethodItem = jComboBoxTrainingMethod.getSelectedItem();

		if (selectedSportsmanItem instanceof Sportsman
						&& selectedMethodItem instanceof TrainingMethod)
		{
			try
			{
				ShootingTraining newTraining = new ShootingTraining();
				newTraining.setSportsman((Sportsman) selectedSportsmanItem);
				newTraining.setDate(jDateChooserTrainingDate.getDate());
				newTraining.setType(ShootingTrainingType.values()[jComboBoxTrainingType.getSelectedIndex()]);
				newTraining.setTrainingMethod((TrainingMethod) selectedMethodItem);
				newTraining.setWeather(jTextFieldWeather.getText());
				newTraining.setComments(jTextFieldComments.getText());

				newTraining.setNumLyingInRest((Integer) jSpinnerNumLyingInRest.getValue());
				newTraining.setMissLyingInRest((Integer) jSpinnerMissLyingInRest.getValue());
				newTraining.setNumLyingLoading((Integer) jSpinnerNumLyingLoading.getValue());
				newTraining.setMissLyingLoading((Integer) jSpinnerMissLyingLoading.getValue());
				newTraining.setNumLyingCompetition((Integer) jSpinnerNumLyingCompetition.getValue());
				newTraining.setMissLyingCompetition((Integer) jSpinnerMissLyingCompetition.getValue());

				newTraining.setNumStandingInRest((Integer) jSpinnerNumStandingInRest.getValue());
				newTraining.setMissStandingInRest(((Integer) jSpinnerMissStandingInRest.getValue()));
				newTraining.setNumStandingLoading((Integer) jSpinnerNumStandingLoading.getValue());
				newTraining.setMissStandingLoading((Integer) jSpinnerMissStandingLoading.getValue());
				newTraining.setNumStandingCompetition((Integer) jSpinnerNumStandingCompetition.getValue());
				newTraining.setMissStandingCompetition((Integer) jSpinnerMissStandingCompetition.getValue());

				newTraining.setFirstLyingLoading((Integer) jSpinnerFirstLyingLoading.getValue());
				newTraining.setFirstLyingCompetition((Integer) jSpinnerFirstLyingCompetition.getValue());
				newTraining.setDelayLyingLoading((Integer) jSpinnerDelayLyingLoading.getValue());
				newTraining.setDelayLyingCompetition((Integer) jSpinnerDelayLyingCompetition.getValue());

				newTraining.setFirstStandingLoading((Integer) jSpinnerFirstStandingLoading.getValue());
				newTraining.setFirstStandingCompetition((Integer) jSpinnerFirstStandingCompetition.getValue());
				newTraining.setDelayStandingLoading((Integer) jSpinnerDelayStandingLoading.getValue());
				newTraining.setDelayStandingCompetition((Integer) jSpinnerDelayStandingCompetition.getValue());

				newTraining.setZeroingIn((Integer) jSpinnerZeroingIn.getValue());
				newTraining.setTrail((Integer) jSpinnerTrail.getValue());
				newTraining.setScatt((Integer) jSpinnerScatt.getValue());

				shootingTrainingTable.addTraining(newTraining);

				jLabelAddingToDatabaseAnimation.setVisible(true);
				Thread hidingAnimationThread = new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							Thread.sleep(1000);
						}
						catch (InterruptedException ex)
						{
							// do nothing
						}
						jLabelAddingToDatabaseAnimation.setVisible(false);
					}
				});
				hidingAnimationThread.start();

			}
			catch (DatabaseErrorException ex)
			{
				JOptionPane.showMessageDialog(null, "Невозможно добавить тренировку: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Необходимо выбрать спортсмена и средство", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
  }//GEN-LAST:event_jButtonAddTrainingActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTraining;
  private javax.swing.JComboBox jComboBoxSportsman;
  private javax.swing.JComboBox jComboBoxTeam;
  private javax.swing.JComboBox jComboBoxTrainingMethod;
  private javax.swing.JComboBox jComboBoxTrainingType;
  private com.toedter.calendar.JDateChooser jDateChooserTrainingDate;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JLabel jLabel19;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel20;
  private javax.swing.JLabel jLabel21;
  private javax.swing.JLabel jLabel22;
  private javax.swing.JLabel jLabel23;
  private javax.swing.JLabel jLabel24;
  private javax.swing.JLabel jLabel25;
  private javax.swing.JLabel jLabel26;
  private javax.swing.JLabel jLabel27;
  private javax.swing.JLabel jLabel28;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JLabel jLabelAddingToDatabaseAnimation;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSpinner jSpinnerDelayLyingCompetition;
  private javax.swing.JSpinner jSpinnerDelayLyingLoading;
  private javax.swing.JSpinner jSpinnerDelayStandingCompetition;
  private javax.swing.JSpinner jSpinnerDelayStandingLoading;
  private javax.swing.JSpinner jSpinnerFirstLyingCompetition;
  private javax.swing.JSpinner jSpinnerFirstLyingLoading;
  private javax.swing.JSpinner jSpinnerFirstStandingCompetition;
  private javax.swing.JSpinner jSpinnerFirstStandingLoading;
  private javax.swing.JSpinner jSpinnerMissLyingCompetition;
  private javax.swing.JSpinner jSpinnerMissLyingInRest;
  private javax.swing.JSpinner jSpinnerMissLyingLoading;
  private javax.swing.JSpinner jSpinnerMissStandingCompetition;
  private javax.swing.JSpinner jSpinnerMissStandingInRest;
  private javax.swing.JSpinner jSpinnerMissStandingLoading;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition;
  private javax.swing.JSpinner jSpinnerNumLyingInRest;
  private javax.swing.JSpinner jSpinnerNumLyingLoading;
  private javax.swing.JSpinner jSpinnerNumStandingCompetition;
  private javax.swing.JSpinner jSpinnerNumStandingInRest;
  private javax.swing.JSpinner jSpinnerNumStandingLoading;
  private javax.swing.JSpinner jSpinnerScatt;
  private javax.swing.JSpinner jSpinnerTrail;
  private javax.swing.JSpinner jSpinnerZeroingIn;
  private javax.swing.JTable jTable1;
  private javax.swing.JTextField jTextFieldComments;
  private javax.swing.JTextField jTextFieldPlaceName;
  private javax.swing.JTextField jTextFieldWeather;
  // End of variables declaration//GEN-END:variables
}
