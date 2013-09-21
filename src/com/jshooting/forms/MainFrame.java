package com.jshooting.forms;

import com.jshooting.logics.ReportsMaker;
import com.jshooting.support.UserSettings;
import com.jshooting.objectsHighlighting.ColourObjectsHighlighter;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.exceptions.ShowingReportErrorException;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.objectsHighlighting.ComponentBackgroundHighlightingAdapter;
import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.ShootingDatabaseFactory;
import com.jshooting.support.DatabaseReserveMaker;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Main frame
 *
 * @author pgalex
 */
public class MainFrame extends javax.swing.JFrame
{
	/**
	 * Name of user settings file
	 */
	private static final String USER_SETTINGS_FILE_NAME = "userSettings.dat";
	/**
	 * Connection to database. If null there is no database choosed
	 */
	private ShootingDatabase shootingDatabase;
	/**
	 * Highlighter of dialog components
	 */
	private ColourObjectsHighlighter componentsHighlighter;
	private ReportsMaker reportsMaker;

	/**
	 * Creates new form
	 */
	public MainFrame()
	{
		shootingDatabase = null;
		componentsHighlighter = new ColourObjectsHighlighter();
		reportsMaker = new ReportsMaker();

		initComponents();

		readUserSettings();
		connectToDatabaseFromUserSettings();

		updateWorkingControlsEnable();
		updateDatabaseControlsHighlighing();
		updateDatabaseFileNameControls();
	}

	/**
	 * Try connect to database by database file name storing in user settings
	 */
	private void connectToDatabaseFromUserSettings()
	{
		boolean noDatabaseSavedInSettings = UserSettings.getInstance().getDatabaseFileName().isEmpty();
		if (noDatabaseSavedInSettings)
		{
			shootingDatabase = null;
			return;
		}

		File previousDatabaseFile = new File(UserSettings.getInstance().getDatabaseFileName());
		if (previousDatabaseFile.exists())
		{
			openOrCreateDatabase(previousDatabaseFile.getPath());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Не удалось подключится к предыдущей базе данных", "Предупреждение", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Connect to database by file path. If database not exists it will be
	 * created. Shows error message if can not connect
	 *
	 * @param databaseFilePath path to database file. Must be not null
	 */
	private void openOrCreateDatabase(String databaseFilePath) throws IllegalArgumentException
	{
		if (databaseFilePath == null)
		{
			throw new IllegalArgumentException("databaseFilePath is null");
		}

		try
		{
			if (shootingDatabase != null)
			{
				shootingDatabase.close();
			}

			shootingDatabase = ShootingDatabaseFactory.openDatabaseFromFile(databaseFilePath);
			DatabaseReserveMaker.makeReserveOfDatabase(shootingDatabase.getFileName());
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Невозможно подключится к базе данных: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			shootingDatabase = null;
		}
	}

	/**
	 * Read user settings from standart user settings file
	 */
	private void readUserSettings()
	{
		try
		{
			UserSettings.getInstance().readFromFile(new File(USER_SETTINGS_FILE_NAME));
		}
		catch (IOException ex)
		{
			UserSettings.setAsDefault();
		}
	}

	/**
	 * Save user settings to standart settings file
	 */
	private void saveUserSettings()
	{
		try
		{
			trySaveUserSettings();
		}
		catch (IOException ex)
		{
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void trySaveUserSettings() throws IOException
	{
		if (shootingDatabase != null)
		{
			UserSettings.getInstance().setDatabaseFileName(shootingDatabase.getFileName());
		}
		else
		{
			UserSettings.getInstance().setDatabaseFileName("");
		}

		UserSettings.getInstance().writeToFile(new File(USER_SETTINGS_FILE_NAME));
	}

	/**
	 * Update working controls enable
	 */
	private void updateWorkingControlsEnable()
	{
		jPanelWorkingControls.setVisible(shootingDatabase != null);
	}

	/**
	 * Update highlighing of database choosing controls
	 */
	private void updateDatabaseControlsHighlighing()
	{
		if (shootingDatabase == null)
		{
			componentsHighlighter.startObjectHightlighing(new ComponentBackgroundHighlightingAdapter(jButtonOpenDatabase),
							HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
			componentsHighlighter.startObjectHightlighing(new ComponentBackgroundHighlightingAdapter(jButtonCreateDatabase),
							HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
		}
		else
		{
			componentsHighlighter.stopObjectHighlighting(jButtonOpenDatabase);
			componentsHighlighter.stopObjectHighlighting(jButtonCreateDatabase);
		}
	}

	/**
	 * Set database file name to database file name control
	 */
	private void updateDatabaseFileNameControls()
	{
		if (shootingDatabase != null)
		{
			jTextFieldDatabaseFileName.setText(shootingDatabase.getFileName());
		}
		else
		{
			jTextFieldDatabaseFileName.setText("");
		}
	}

	private void tryShowCombinedReport() throws ShowingReportErrorException
	{
		ShootingTrainingsFilterDialog filterDialog = new ShootingTrainingsFilterDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		filterDialog.setLocationRelativeTo(this);
		filterDialog.setVisible(true);

		if (filterDialog.isOKButtonPressed())
		{
			ShootingTrainingsFilter trainingsFilter = filterDialog.getFilter();
			reportsMaker.showCombinedReportWithFilter(trainingsFilter, shootingDatabase.getShootingTrainingsTable());
		}
	}

	private void tryShowIndividualReport() throws ShowingReportErrorException
	{
		ShootingTrainingsFilterDialog filterDialog = new ShootingTrainingsFilterDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		filterDialog.setOneSportsmanSelectingMode();
		filterDialog.setLocationRelativeTo(this);
		filterDialog.setVisible(true);

		if (filterDialog.isOKButtonPressed())
		{
			ShootingTrainingsFilter trainingsFilter = filterDialog.getFilter();
			reportsMaker.showIndividualReportWithFilter(trainingsFilter, shootingDatabase.getShootingTrainingsTable());
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

    jPanelChooseDatabaseControls = new javax.swing.JPanel();
    jLabelDatabaseFileName = new javax.swing.JLabel();
    jTextFieldDatabaseFileName = new javax.swing.JTextField();
    jButtonOpenDatabase = new javax.swing.JButton();
    jButtonCreateDatabase = new javax.swing.JButton();
    jPanelWorkingControls = new javax.swing.JPanel();
    jButtonSportsmans = new javax.swing.JButton();
    jButtonPlaces = new javax.swing.JButton();
    jButtonAddTrainings = new javax.swing.JButton();
    jButtonCombined = new javax.swing.JButton();
    jButtonTrainingMethods = new javax.swing.JButton();
    jButtonEditTrainings = new javax.swing.JButton();
    jButtonIndividualReport = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("JShooting");
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent evt)
      {
        formWindowClosing(evt);
      }
    });

    jLabelDatabaseFileName.setText("База данных:");

    jTextFieldDatabaseFileName.setEditable(false);
    jTextFieldDatabaseFileName.setToolTipText("Пусть к открытой базе данных");

    jButtonOpenDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/openDatabaseIcon.png"))); // NOI18N
    jButtonOpenDatabase.setText("Открыть существующую");
    jButtonOpenDatabase.setToolTipText("Открыть существующую базу данных");
    jButtonOpenDatabase.setActionCommand("");
    jButtonOpenDatabase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonOpenDatabase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButtonOpenDatabase.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonOpenDatabaseActionPerformed(evt);
      }
    });

    jButtonCreateDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/createDatabaseIcon.png"))); // NOI18N
    jButtonCreateDatabase.setText("Создать новую");
    jButtonCreateDatabase.setToolTipText("Создать новую базу данных");
    jButtonCreateDatabase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonCreateDatabase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButtonCreateDatabase.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCreateDatabaseActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanelChooseDatabaseControlsLayout = new org.jdesktop.layout.GroupLayout(jPanelChooseDatabaseControls);
    jPanelChooseDatabaseControls.setLayout(jPanelChooseDatabaseControlsLayout);
    jPanelChooseDatabaseControlsLayout.setHorizontalGroup(
      jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
            .add(jLabelDatabaseFileName)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jTextFieldDatabaseFileName))
          .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
            .add(jButtonOpenDatabase, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonCreateDatabase, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanelChooseDatabaseControlsLayout.setVerticalGroup(
      jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jTextFieldDatabaseFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabelDatabaseFileName))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jButtonOpenDatabase, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
          .add(jButtonCreateDatabase, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButtonSportsmans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/Спортсмены.png"))); // NOI18N
    jButtonSportsmans.setText("Спортсмены");
    jButtonSportsmans.setToolTipText(null);
    jButtonSportsmans.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonSportsmans.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButtonSportsmans.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonSportsmansActionPerformed(evt);
      }
    });

    jButtonPlaces.setText("УТС");
    jButtonPlaces.setToolTipText(null);
    jButtonPlaces.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonPlacesActionPerformed(evt);
      }
    });

    jButtonAddTrainings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/добавитьТренировки.png"))); // NOI18N
    jButtonAddTrainings.setText("Добавить тренировки");
    jButtonAddTrainings.setToolTipText(null);
    jButtonAddTrainings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonAddTrainings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButtonAddTrainings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTrainingsActionPerformed(evt);
      }
    });

    jButtonCombined.setText("Групповой отчет");
    jButtonCombined.setToolTipText(null);
    jButtonCombined.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCombinedActionPerformed(evt);
      }
    });

    jButtonTrainingMethods.setText("Средства");
    jButtonTrainingMethods.setToolTipText(null);
    jButtonTrainingMethods.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonTrainingMethodsActionPerformed(evt);
      }
    });

    jButtonEditTrainings.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
    jButtonEditTrainings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jshooting/resources/редактироватьТренировки.png"))); // NOI18N
    jButtonEditTrainings.setText("Редактировать тренировоки");
    jButtonEditTrainings.setToolTipText(null);
    jButtonEditTrainings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonEditTrainings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButtonEditTrainings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEditTrainingsActionPerformed(evt);
      }
    });

    jButtonIndividualReport.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
    jButtonIndividualReport.setText("Индивидуальный отчет");
    jButtonIndividualReport.setToolTipText(null);
    jButtonIndividualReport.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonIndividualReportActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanelWorkingControlsLayout = new org.jdesktop.layout.GroupLayout(jPanelWorkingControls);
    jPanelWorkingControls.setLayout(jPanelWorkingControlsLayout);
    jPanelWorkingControlsLayout.setHorizontalGroup(
      jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelWorkingControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanelWorkingControlsLayout.createSequentialGroup()
            .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jButtonSportsmans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jButtonPlaces, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
            .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelWorkingControlsLayout.createSequentialGroup()
                .add(jButtonEditTrainings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButtonIndividualReport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
              .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelWorkingControlsLayout.createSequentialGroup()
                .add(jButtonAddTrainings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButtonCombined, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
          .add(jButtonTrainingMethods, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );
    jPanelWorkingControlsLayout.setVerticalGroup(
      jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelWorkingControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jButtonSportsmans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jButtonAddTrainings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jButtonCombined, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jButtonIndividualReport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jButtonEditTrainings, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jButtonPlaces, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonTrainingMethods, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(jPanelWorkingControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .add(jPanelChooseDatabaseControls, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jPanelWorkingControls, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
  {//GEN-HEADEREND:event_formWindowClosing
		saveUserSettings();
  }//GEN-LAST:event_formWindowClosing

  private void jButtonCreateDatabaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCreateDatabaseActionPerformed
  {//GEN-HEADEREND:event_jButtonCreateDatabaseActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int showDialogResult = fileChooser.showSaveDialog(this);
		if (showDialogResult == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.exists())
			{
				int confirmDialogResult = JOptionPane.showConfirmDialog(this, "Для новой базы данных выбран существующий файл.\n"
								+ "Он будет перезаписан. Продолжить создание?",
								"Создание базы данных", JOptionPane.YES_NO_OPTION);
				if (confirmDialogResult == JOptionPane.YES_OPTION)
				{
					selectedFile.delete();
					openOrCreateDatabase(selectedFile.getPath());
				}
			}
			else
			{
				openOrCreateDatabase(selectedFile.getPath());
			}
			updateDatabaseFileNameControls();
			updateWorkingControlsEnable();
			updateDatabaseControlsHighlighing();
		}
  }//GEN-LAST:event_jButtonCreateDatabaseActionPerformed

  private void jButtonOpenDatabaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOpenDatabaseActionPerformed
  {//GEN-HEADEREND:event_jButtonOpenDatabaseActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int showDialogResult = fileChooser.showOpenDialog(this);
		if (showDialogResult == JFileChooser.APPROVE_OPTION)
		{
			if (fileChooser.getSelectedFile().exists())
			{
				openOrCreateDatabase(fileChooser.getSelectedFile().getPath());

				updateDatabaseFileNameControls();
				updateWorkingControlsEnable();
				updateDatabaseControlsHighlighing();
			}
		}
  }//GEN-LAST:event_jButtonOpenDatabaseActionPerformed

  private void jButtonSportsmansActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSportsmansActionPerformed
  {//GEN-HEADEREND:event_jButtonSportsmansActionPerformed
		SportsmansDialog editSportsmansDialog = new SportsmansDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editSportsmansDialog.setLocationRelativeTo(this);
		editSportsmansDialog.setVisible(true);
  }//GEN-LAST:event_jButtonSportsmansActionPerformed

  private void jButtonTrainingMethodsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTrainingMethodsActionPerformed
  {//GEN-HEADEREND:event_jButtonTrainingMethodsActionPerformed
		TrainingMethodsDialog editTrainingMethodsDialog = new TrainingMethodsDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editTrainingMethodsDialog.setLocationRelativeTo(this);
		editTrainingMethodsDialog.setVisible(true);
  }//GEN-LAST:event_jButtonTrainingMethodsActionPerformed

  private void jButtonAddTrainingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTrainingsActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTrainingsActionPerformed
		AddShootingTrainingsDialog addShootingTrainingDialog = new AddShootingTrainingsDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		addShootingTrainingDialog.setLocationRelativeTo(this);
		addShootingTrainingDialog.setVisible(true);
  }//GEN-LAST:event_jButtonAddTrainingsActionPerformed

  private void jButtonCombinedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCombinedActionPerformed
  {//GEN-HEADEREND:event_jButtonCombinedActionPerformed
		try
		{
			tryShowCombinedReport();
		}
		catch (ShowingReportErrorException ex)
		{
			JOptionPane.showMessageDialog(null, "Невозможно отобразить отчет: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
  }//GEN-LAST:event_jButtonCombinedActionPerformed

  private void jButtonEditTrainingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditTrainingsActionPerformed
  {//GEN-HEADEREND:event_jButtonEditTrainingsActionPerformed
		ShootingTrainingsDialog editShootingTrainingsDialog = new ShootingTrainingsDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editShootingTrainingsDialog.setLocationRelativeTo(this);
		editShootingTrainingsDialog.setVisible(true);
  }//GEN-LAST:event_jButtonEditTrainingsActionPerformed

  private void jButtonIndividualReportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonIndividualReportActionPerformed
  {//GEN-HEADEREND:event_jButtonIndividualReportActionPerformed
		try
		{
			tryShowIndividualReport();
		}
		catch (ShowingReportErrorException ex)
		{
			JOptionPane.showMessageDialog(null, "Невозможно отобразить отчет: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
  }//GEN-LAST:event_jButtonIndividualReportActionPerformed

  private void jButtonPlacesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonPlacesActionPerformed
  {//GEN-HEADEREND:event_jButtonPlacesActionPerformed
		PlacesDialog editPlaceDialog = new PlacesDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editPlaceDialog.setLocationRelativeTo(this);
		editPlaceDialog.setVisible(true);
  }//GEN-LAST:event_jButtonPlacesActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainFrame mainFrame = new MainFrame();
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setVisible(true);
			}
		});
	}
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTrainings;
  private javax.swing.JButton jButtonCombined;
  private javax.swing.JButton jButtonCreateDatabase;
  private javax.swing.JButton jButtonEditTrainings;
  private javax.swing.JButton jButtonIndividualReport;
  private javax.swing.JButton jButtonOpenDatabase;
  private javax.swing.JButton jButtonPlaces;
  private javax.swing.JButton jButtonSportsmans;
  private javax.swing.JButton jButtonTrainingMethods;
  private javax.swing.JLabel jLabelDatabaseFileName;
  private javax.swing.JPanel jPanelChooseDatabaseControls;
  private javax.swing.JPanel jPanelWorkingControls;
  private javax.swing.JTextField jTextFieldDatabaseFileName;
  // End of variables declaration//GEN-END:variables
}
