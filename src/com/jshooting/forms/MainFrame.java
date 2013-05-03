package com.jshooting.forms;

import com.jshooting.shootingDatabase.ShootingDatabase;
import com.jshooting.shootingDatabase.ShootingDatabaseFactory;
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
	 * Creates new form MainFrame
	 */
	public MainFrame()
	{
		initComponents();
		setTitle("JShooting");

		shootingDatabase = null;

		readUserSettings();
		File previousDatabaseFile = new File(UserSettings.getInstance().getDatabaseFileName());
		if (previousDatabaseFile.exists())
		{
			connectToPreviousDatabase();
		}
		else
		{
			shootingDatabase = null;
		}

		updateWorkingControlsEnable();
		updateDatabaseFileNameControls();
	}

	/**
	 * Connect to database, by file name saved in user settings
	 */
	private void connectToPreviousDatabase()
	{
		try
		{
			shootingDatabase = ShootingDatabaseFactory.openDatabaseFromFile(UserSettings.getInstance().getDatabaseFileName());
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
	 * Set working controls enable by shooting database state(choosed or no)
	 */
	private void updateWorkingControlsEnable()
	{
		jPanelWorkingControls.setVisible(shootingDatabase != null);
	}

	/**
	 * Set database file name to controls
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
    jButtonTeams = new javax.swing.JButton();
    jButtonPlaces = new javax.swing.JButton();
    jButtonAddTraining = new javax.swing.JButton();
    jButtonReports = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
    jButtonOpenDatabase.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonOpenDatabaseActionPerformed(evt);
      }
    });

    jButtonCreateDatabase.setText("Создать ...");
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

    jButtonSportsmans.setText("Спортсмены");

    jButtonTeams.setText("Команды");

    jButtonPlaces.setText("УТС");

    jButtonAddTraining.setText("Добавить тренировку ...");

    jButtonReports.setText("Отчеты ...");

    org.jdesktop.layout.GroupLayout jPanelWorkingControlsLayout = new org.jdesktop.layout.GroupLayout(jPanelWorkingControls);
    jPanelWorkingControls.setLayout(jPanelWorkingControlsLayout);
    jPanelWorkingControlsLayout.setHorizontalGroup(
      jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelWorkingControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanelWorkingControlsLayout.createSequentialGroup()
            .add(jButtonTeams)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonSportsmans)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButtonPlaces))
          .add(jButtonAddTraining)
          .add(jButtonReports))
        .addContainerGap(215, Short.MAX_VALUE))
    );
    jPanelWorkingControlsLayout.setVerticalGroup(
      jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelWorkingControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelWorkingControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonSportsmans)
          .add(jButtonTeams)
          .add(jButtonPlaces))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonAddTraining)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonReports)
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
		try
		{
			UserSettings.getInstance().writeToFile(new File(USER_SETTINGS_FILE_NAME));
		}
		catch (IOException ex)
		{
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
  }//GEN-LAST:event_formWindowClosing

  private void jButtonCreateDatabaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCreateDatabaseActionPerformed
  {//GEN-HEADEREND:event_jButtonCreateDatabaseActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int showDialogResult = fileChooser.showSaveDialog(this);
		if (showDialogResult == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				shootingDatabase = ShootingDatabaseFactory.openDatabaseFromFile(fileChooser.getSelectedFile().getPath());
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Невозможно создать базу данных: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
				shootingDatabase = null;
			}

			updateDatabaseFileNameControls();
			updateWorkingControlsEnable();
		}
  }//GEN-LAST:event_jButtonCreateDatabaseActionPerformed

  private void jButtonOpenDatabaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOpenDatabaseActionPerformed
  {//GEN-HEADEREND:event_jButtonOpenDatabaseActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_jButtonOpenDatabaseActionPerformed

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
  private javax.swing.JButton jButtonAddTraining;
  private javax.swing.JButton jButtonCreateDatabase;
  private javax.swing.JButton jButtonOpenDatabase;
  private javax.swing.JButton jButtonPlaces;
  private javax.swing.JButton jButtonReports;
  private javax.swing.JButton jButtonSportsmans;
  private javax.swing.JButton jButtonTeams;
  private javax.swing.JLabel jLabelDatabaseFileName;
  private javax.swing.JPanel jPanelChooseDatabaseControls;
  private javax.swing.JPanel jPanelWorkingControls;
  private javax.swing.JTextField jTextFieldDatabaseFileName;
  // End of variables declaration//GEN-END:variables
}
