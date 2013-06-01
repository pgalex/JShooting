package com.jshooting.forms;

import com.jshooting.shootingDatabase.ShootingTraining;
import com.jshooting.shootingDatabase.ShootingTrainingTable;
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
	private ShootingTrainingTable shootingTrainingTable;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param teamsTable table of teams
	 * @param sportsmansTable table of sportsmans
	 * @param trainingMethodsTable table of training methods
	 * @param shootingTrainingTable table of shooting trainings
	 * @throws IllegalArgumentException teamsTable, sportsmansTable,
	 * shootingTrainingTable or trainingMethodsTable is null
	 */
	public AddShootingTrainingDialog(Window parentWindow, ModalityType modalityType,
					TeamsTable teamsTable, SportsmansTable sportsmansTable,
					TrainingMethodsTable trainingMethodsTable,
					ShootingTrainingTable shootingTrainingTable) throws IllegalArgumentException
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

		this.teamsTable = teamsTable;
		this.sportsmansTable = sportsmansTable;
		this.trainingMethodsTable = trainingMethodsTable;
		this.shootingTrainingTable = shootingTrainingTable;

		teamsComboBoxModel = new DefaultComboBoxModel();
		sportsmansComboBoxModel = new DefaultComboBoxModel();
		trainingMethodsComboBoxModel = new DefaultComboBoxModel();

		initComponents();
		setTitle("Добавить тренировку");

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
    jSpinnerMissLyingCompotition = new javax.swing.JSpinner();
    jSpinnerNumLyingInRest1 = new javax.swing.JSpinner();
    jSpinnerMissLyingInRest1 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading1 = new javax.swing.JSpinner();
    jSpinnerMissLyingLoading1 = new javax.swing.JSpinner();
    jSpinnerNumLyingCompetition1 = new javax.swing.JSpinner();
    jSpinnerMissLyingCompotition1 = new javax.swing.JSpinner();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jSpinnerNumLyingLoading2 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading3 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading4 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading5 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading6 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading7 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading8 = new javax.swing.JSpinner();
    jSpinnerNumLyingLoading9 = new javax.swing.JSpinner();
    jLabel18 = new javax.swing.JLabel();
    jLabel19 = new javax.swing.JLabel();
    jLabel20 = new javax.swing.JLabel();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jLabel23 = new javax.swing.JLabel();
    jLabel24 = new javax.swing.JLabel();
    jLabel25 = new javax.swing.JLabel();
    jSpinnerNumLyingCompetition2 = new javax.swing.JSpinner();
    jLabel27 = new javax.swing.JLabel();
    jLabel26 = new javax.swing.JLabel();
    jSpinnerNumLyingCompetition3 = new javax.swing.JSpinner();
    jSpinnerNumLyingCompetition4 = new javax.swing.JSpinner();

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

    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Количество выстрелов");

    jLabel9.setText("В покое");

    jLabel10.setText("С нагрузки");

    jLabel11.setText("Соревн.");

    jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel12.setText("Лежа");

    jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel13.setText("Кол-во");

    jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel14.setText("Промахи");

    jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel15.setText("Кол-во");

    jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel16.setText("Промахи");

    jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
    jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel17.setText("Стоя");

    jLabel18.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel18.setText("1 выстрел");

    jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel19.setText("Задержка");

    jLabel20.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
    jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel20.setText("1 выстрел");

    jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
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

    jLabel27.setText("Тренаж");

    jLabel26.setText("Скатт");

    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jLabel9)
          .add(jLabel11)
          .add(jLabel10)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel25)
            .add(jLabel27))
          .add(jLabel26))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createSequentialGroup()
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                  .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                      .add(jSpinnerNumLyingInRest, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                      .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                      .add(jSpinnerMissLyingInRest, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                      .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                      .add(jSpinnerNumLyingInRest1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                      .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                      .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .add(jSpinnerMissLyingInRest1)))
                  .add(jLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerNumLyingCompetition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingLoading, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingCompetition3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingCompetition2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jPanel1Layout.createSequentialGroup()
                    .add(jSpinnerMissLyingLoading, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSpinnerNumLyingLoading2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSpinnerMissLyingLoading1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                  .add(jPanel1Layout.createSequentialGroup()
                    .add(jSpinnerMissLyingCompotition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSpinnerNumLyingCompetition1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSpinnerMissLyingCompotition1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(0, 0, Short.MAX_VALUE)))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                  .add(jLabel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                  .add(jLabel18)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jLabel20)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jLabel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerNumLyingLoading3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingLoading4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerNumLyingLoading1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingLoading5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerNumLyingLoading6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingLoading7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                  .add(jSpinnerNumLyingLoading8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .add(jSpinnerNumLyingLoading9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
          .add(jPanel1Layout.createSequentialGroup()
            .add(jSpinnerNumLyingCompetition4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
          .add(jSpinnerNumLyingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerMissLyingInRest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerNumLyingInRest1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jSpinnerMissLyingInRest1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(jSpinnerNumLyingLoading3)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading2)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissLyingLoading1)
            .add(jSpinnerMissLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading1)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading6))
          .add(jSpinnerNumLyingLoading8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading9)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading5)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissLyingCompotition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingCompetition)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingCompetition1)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerMissLyingCompotition1)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading4)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jSpinnerNumLyingLoading7))
          .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jLabel25, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerNumLyingCompetition3))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerNumLyingCompetition2))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jLabel26, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jSpinnerNumLyingCompetition4))
        .add(12, 12, 12))
    );

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
                  .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                  .add(jTextFieldWeather)
                  .add(jComboBoxTrainingMethod, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jComboBoxTrainingType, 0, 186, Short.MAX_VALUE)
                  .add(jComboBoxSportsman, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jComboBoxTeam, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .add(jDateChooserTrainingDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
          .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(layout.createSequentialGroup()
                .add(jButtonAddTraining)
                .add(0, 0, Short.MAX_VALUE))
              .add(layout.createSequentialGroup()
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 25, Short.MAX_VALUE)
                .add(jTextFieldComments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 688, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(6, 6, 6))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
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
              .add(jLabel5)))
          .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jTextFieldComments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabel6))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
        .add(jButtonAddTraining, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
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
				
				shootingTrainingTable.addTraining(newTraining);
			}
			catch (DatabaseErrorException ex)
			{
				JOptionPane.showMessageDialog(null, "Невозможно добавить тренировку: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
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
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSpinner jSpinnerMissLyingCompotition;
  private javax.swing.JSpinner jSpinnerMissLyingCompotition1;
  private javax.swing.JSpinner jSpinnerMissLyingInRest;
  private javax.swing.JSpinner jSpinnerMissLyingInRest1;
  private javax.swing.JSpinner jSpinnerMissLyingLoading;
  private javax.swing.JSpinner jSpinnerMissLyingLoading1;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition1;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition2;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition3;
  private javax.swing.JSpinner jSpinnerNumLyingCompetition4;
  private javax.swing.JSpinner jSpinnerNumLyingInRest;
  private javax.swing.JSpinner jSpinnerNumLyingInRest1;
  private javax.swing.JSpinner jSpinnerNumLyingLoading;
  private javax.swing.JSpinner jSpinnerNumLyingLoading1;
  private javax.swing.JSpinner jSpinnerNumLyingLoading2;
  private javax.swing.JSpinner jSpinnerNumLyingLoading3;
  private javax.swing.JSpinner jSpinnerNumLyingLoading4;
  private javax.swing.JSpinner jSpinnerNumLyingLoading5;
  private javax.swing.JSpinner jSpinnerNumLyingLoading6;
  private javax.swing.JSpinner jSpinnerNumLyingLoading7;
  private javax.swing.JSpinner jSpinnerNumLyingLoading8;
  private javax.swing.JSpinner jSpinnerNumLyingLoading9;
  private javax.swing.JTable jTable1;
  private javax.swing.JTextField jTextFieldComments;
  private javax.swing.JTextField jTextFieldWeather;
  // End of variables declaration//GEN-END:variables
}
