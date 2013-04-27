package com.jshooting.forms;

/**
 * Main frame
 *
 * @author pgalex
 */
public class MainFrame extends javax.swing.JFrame
{
	/**
	 * Creates new form MainFrame
	 */
	public MainFrame()
	{
		initComponents();
		setTitle("JShooting");
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

    jPanelWorkingControls = new javax.swing.JPanel();
    jButtonSportsmans = new javax.swing.JButton();
    jButtonTeams = new javax.swing.JButton();
    jButtonPlaces = new javax.swing.JButton();
    jButtonAddTraining = new javax.swing.JButton();
    jButtonReports = new javax.swing.JButton();
    jPanelChooseDatabaseControls = new javax.swing.JPanel();
    jLabelDatabaseFileName = new javax.swing.JLabel();
    jTextFieldDatabaseFileName = new javax.swing.JTextField();
    jButtonOpenDatabase = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);

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
        .addContainerGap(161, Short.MAX_VALUE))
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

    jLabelDatabaseFileName.setText("База данных:");

    jTextFieldDatabaseFileName.setEditable(false);

    jButtonOpenDatabase.setText("Открыть ...");

    org.jdesktop.layout.GroupLayout jPanelChooseDatabaseControlsLayout = new org.jdesktop.layout.GroupLayout(jPanelChooseDatabaseControls);
    jPanelChooseDatabaseControls.setLayout(jPanelChooseDatabaseControlsLayout);
    jPanelChooseDatabaseControlsLayout.setHorizontalGroup(
      jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jLabelDatabaseFileName)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jTextFieldDatabaseFileName)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonOpenDatabase)
        .addContainerGap())
    );
    jPanelChooseDatabaseControlsLayout.setVerticalGroup(
      jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelChooseDatabaseControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelChooseDatabaseControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabelDatabaseFileName)
          .add(jTextFieldDatabaseFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jButtonOpenDatabase))
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
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelWorkingControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

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
