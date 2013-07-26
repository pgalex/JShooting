package com.jshooting.forms;

import com.jshooting.componentsHighlighting.ComponentsHighlighter;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.model.ShootingTrainingsFilter;
import com.jshooting.reports.CombinedReportJRDataSource;
import com.jshooting.reports.IndividualReportJRDataSource;
import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.ShootingDatabaseFactory;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
	 * Highlighter of frame components
	 */
	private ComponentsHighlighter componentsHighlighter;

	/**
	 * Creates new form
	 */
	public MainFrame()
	{
		shootingDatabase = null;
		componentsHighlighter = new ComponentsHighlighter();

		initComponents();

		readUserSettings();
		connectToDatabaseFromUserSettings();

		updateWorkingControlsEnable();
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
	 * Try connect to database file. If database not exists it will be created
	 *
	 * @param databaseFilePath path to database file
	 */
	private void openOrCreateDatabase(String databaseFilePath)
	{
		try
		{
			shootingDatabase = ShootingDatabaseFactory.openDatabaseFromFile(databaseFilePath);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Невозможно подключится к базе данных: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			shootingDatabase = null;
		}
	}

	/**
	 * Read user settings from file
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
	 * Save new user settings to file
	 */
	private void saveUserSettings()
	{
		try
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
		catch (IOException ex)
		{
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Set working controls enable by shooting database state(choosed or no)
	 */
	private void updateWorkingControlsEnable()
	{
		if (shootingDatabase == null)
		{
			componentsHighlighter.startComponentHightlighing(jButtonOpenDatabase,
							HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
			componentsHighlighter.startComponentHightlighing(jButtonCreateDatabase,
							HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
			jPanelWorkingControls.setVisible(false);
		}
		else
		{
			componentsHighlighter.stopComponentHighlighting(jButtonOpenDatabase);
			componentsHighlighter.stopComponentHighlighting(jButtonCreateDatabase);
			jPanelWorkingControls.setVisible(true);
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

    jButtonOpenDatabase.setText("Открыть ...");
    jButtonOpenDatabase.setToolTipText("Открыть существующую базу данных");
    jButtonOpenDatabase.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonOpenDatabaseActionPerformed(evt);
      }
    });

    jButtonCreateDatabase.setText("Создать ...");
    jButtonCreateDatabase.setToolTipText("Создать новую базу данных");
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
            .add(jButtonOpenDatabase)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonCreateDatabase)
            .add(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanelChooseDatabaseControlsLayout.setVerticalGroup(
      jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabelDatabaseFileName)
          .add(jTextFieldDatabaseFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonOpenDatabase)
          .add(jButtonCreateDatabase)))
    );

    jButtonSportsmans.setText("Спортсмены ...");
    jButtonSportsmans.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonSportsmansActionPerformed(evt);
      }
    });

    jButtonPlaces.setText("УТС ...");
    jButtonPlaces.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonPlacesActionPerformed(evt);
      }
    });

    jButtonAddTrainings.setText("Добавить тренировки ...");
    jButtonAddTrainings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTrainingsActionPerformed(evt);
      }
    });

    jButtonCombined.setText("Групповой отчет ...");
    jButtonCombined.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonCombinedActionPerformed(evt);
      }
    });

    jButtonTrainingMethods.setText("Средства ...");
    jButtonTrainingMethods.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonTrainingMethodsActionPerformed(evt);
      }
    });

    jButtonEditTrainings.setText("Просмотр и редактирование тренировок ...");
    jButtonEditTrainings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEditTrainingsActionPerformed(evt);
      }
    });

    jButtonIndividualReport.setText("Индивидуальный отчет ...");
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
            .add(jButtonAddTrainings)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonEditTrainings))
          .add(jPanelWorkingControlsLayout.createSequentialGroup()
            .add(jButtonCombined)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonIndividualReport))
          .add(jPanelWorkingControlsLayout.createSequentialGroup()
            .add(jButtonSportsmans)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonPlaces)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonTrainingMethods)))
        .addContainerGap(10, Short.MAX_VALUE))
    );
    jPanelWorkingControlsLayout.setVerticalGroup(
      jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelWorkingControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonSportsmans)
          .add(jButtonPlaces)
          .add(jButtonTrainingMethods))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonAddTrainings)
          .add(jButtonEditTrainings))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonCombined)
          .add(jButtonIndividualReport))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelWorkingControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(jPanelChooseDatabaseControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .add(jPanelChooseDatabaseControls, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(18, 18, 18)
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
			if (fileChooser.getSelectedFile().exists())
			{
				fileChooser.getSelectedFile().delete();
			}
			openOrCreateDatabase(fileChooser.getSelectedFile().getPath());

			updateDatabaseFileNameControls();
			updateWorkingControlsEnable();
		}
  }//GEN-LAST:event_jButtonCreateDatabaseActionPerformed

  private void jButtonOpenDatabaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOpenDatabaseActionPerformed
  {//GEN-HEADEREND:event_jButtonOpenDatabaseActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int showDialogResult = fileChooser.showOpenDialog(this);
		if (showDialogResult == JFileChooser.APPROVE_OPTION)
		{
			openOrCreateDatabase(fileChooser.getSelectedFile().getPath());

			updateDatabaseFileNameControls();
			updateWorkingControlsEnable();
		}
  }//GEN-LAST:event_jButtonOpenDatabaseActionPerformed

  private void jButtonSportsmansActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSportsmansActionPerformed
  {//GEN-HEADEREND:event_jButtonSportsmansActionPerformed
		EditSportsmansDialog editSportsmansDialog = new EditSportsmansDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editSportsmansDialog.setLocationRelativeTo(this);
		editSportsmansDialog.setVisible(true);
  }//GEN-LAST:event_jButtonSportsmansActionPerformed

  private void jButtonTrainingMethodsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTrainingMethodsActionPerformed
  {//GEN-HEADEREND:event_jButtonTrainingMethodsActionPerformed
		EditTrainingMethodsDialog editTrainingMethodsDialog = new EditTrainingMethodsDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		editTrainingMethodsDialog.setLocationRelativeTo(this);
		editTrainingMethodsDialog.setVisible(true);
  }//GEN-LAST:event_jButtonTrainingMethodsActionPerformed

  private void jButtonAddTrainingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTrainingsActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTrainingsActionPerformed
		AddShootingTrainingDialog addShootingTrainingDialog = new AddShootingTrainingDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		addShootingTrainingDialog.setLocationRelativeTo(this);
		addShootingTrainingDialog.setVisible(true);
  }//GEN-LAST:event_jButtonAddTrainingsActionPerformed

  private void jButtonCombinedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCombinedActionPerformed
  {//GEN-HEADEREND:event_jButtonCombinedActionPerformed
		ShootingTrainingsFilterDialog filterDialog = new ShootingTrainingsFilterDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		filterDialog.setLocationRelativeTo(this);
		filterDialog.setVisible(true);

		if (filterDialog.isOKButtonPressed())
		{
			ShootingTrainingsFilter trainingsFilter = filterDialog.getFilter();
			CombinedReportJRDataSource reportDataSource = new CombinedReportJRDataSource(trainingsFilter, shootingDatabase.getShootingTrainingsTable());

			try
			{
				Map<String, Object> parametersMap = new HashMap<String, Object>();
				DateFormat reportDateFormat = new SimpleDateFormat("dd.MM.yyyy");
				parametersMap.put("DATE_FROM", reportDateFormat.format(trainingsFilter.getDateFrom()));
				parametersMap.put("DATE_TO", reportDateFormat.format(trainingsFilter.getDateTo()));
				if (trainingsFilter.isPlaceUsedAsPeriod())
				{
					parametersMap.put("PLACE_NAME", trainingsFilter.getPlace().getName());
				}
				else
				{
					parametersMap.put("PLACE_NAME", "");
				}

				JasperDesign desing = JRXmlLoader.load("reports/combinedReport.jrxml");
				JasperReport report = JasperCompileManager.compileReport(desing);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, reportDataSource);
				JasperViewer.viewReport(jasperPrint, false);
			}
			catch (JRException ex)
			{
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
  }//GEN-LAST:event_jButtonCombinedActionPerformed

  private void jButtonEditTrainingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditTrainingsActionPerformed
  {//GEN-HEADEREND:event_jButtonEditTrainingsActionPerformed
		EditShootingTrainingsDialog editShootingTrainingsDialog = new EditShootingTrainingsDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						shootingDatabase.getShootingTrainingsTable());
		editShootingTrainingsDialog.setLocationRelativeTo(this);
		editShootingTrainingsDialog.setVisible(true);
  }//GEN-LAST:event_jButtonEditTrainingsActionPerformed

  private void jButtonIndividualReportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonIndividualReportActionPerformed
  {//GEN-HEADEREND:event_jButtonIndividualReportActionPerformed
		ShootingTrainingsFilterDialog filterDialog = new ShootingTrainingsFilterDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
						new ShootingLogicsFactory(shootingDatabase));
		filterDialog.setOneSportsmanSelectingMode();
		filterDialog.setLocationRelativeTo(this);
		filterDialog.setVisible(true);

		if (filterDialog.isOKButtonPressed())
		{
			ShootingTrainingsFilter trainingsFilter = filterDialog.getFilter();
			IndividualReportJRDataSource reportDataSource = new IndividualReportJRDataSource(trainingsFilter,
							shootingDatabase.getShootingTrainingsTable());
			try
			{
				Map<String, Object> parametersMap = new HashMap<String, Object>();
				DateFormat reportDateFormat = new SimpleDateFormat("dd.MM.yyyy");
				parametersMap.put("DATE_FROM", reportDateFormat.format(trainingsFilter.getDateFrom()));
				parametersMap.put("DATE_TO", reportDateFormat.format(trainingsFilter.getDateTo()));
				if (trainingsFilter.isPlaceUsedAsPeriod())
				{
					parametersMap.put("PLACE_NAME", trainingsFilter.getPlace().getName());
				}
				else
				{
					parametersMap.put("PLACE_NAME", "");
				}
				if (trainingsFilter.getSportsmans().size() > 0)
				{
					parametersMap.put("SPORTSMAN_NAME", trainingsFilter.getSportsmans().get(0).getName());
				}

				JasperDesign desing = JRXmlLoader.load("reports/individualReport.jrxml");
				JasperReport report = JasperCompileManager.compileReport(desing);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, reportDataSource);
				JasperViewer.viewReport(jasperPrint, false);
			}
			catch (JRException ex)
			{
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
  }//GEN-LAST:event_jButtonIndividualReportActionPerformed

  private void jButtonPlacesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonPlacesActionPerformed
  {//GEN-HEADEREND:event_jButtonPlacesActionPerformed
		EditPlacesDialog editPlaceDialog = new EditPlacesDialog(this, Dialog.ModalityType.APPLICATION_MODAL,
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
