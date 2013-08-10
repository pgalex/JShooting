package com.jshooting.forms;

import com.jshooting.logics.DateModifier;
import com.jshooting.logics.ShootingTrainingsModifier;
import com.jshooting.shootingDatabase.ShootingTrainingsTable;
import java.awt.Window;
import javax.swing.ListSelectionModel;

/**
 * Dialog for viewing and editing exists shooting trainins
 *
 * @author pgalex
 */
public class ShootingTrainingsDialog extends javax.swing.JDialog
{
	/**
	 * Table model of trainings table
	 */
	private ShootingTrainingsTableModel shootingTrainingsTableModel;
	private ShootingTrainingsModifier shootingTrainingsModifier;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param shootingTrainingsTable editing table of shooting trainings. Must be
	 * not null
	 * @throws IllegalArgumentException shootingTrainingsTable is null
	 */
	public ShootingTrainingsDialog(Window parentWindow, ModalityType modalityType,
					ShootingTrainingsTable shootingTrainingsTable) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (shootingTrainingsTable == null)
		{
			throw new IllegalArgumentException("shootingTrainingsTable is null");
		}

		shootingTrainingsTableModel = new ShootingTrainingsTableModel(shootingTrainingsTable);

		initComponents();

		jDateChooserDateFrom.setDate(DateModifier.createTodayBegin());
		jDateChooserDateTo.setDate(DateModifier.createTodayEnd());

		jTableTrainings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//
		jPanelFilter.setVisible(false);
		//
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
    jTableTrainings = new javax.swing.JTable();
    jPanelEditControls = new javax.swing.JPanel();
    jButtonAddTrainings = new javax.swing.JButton();
    jButtonEditSelected = new javax.swing.JButton();
    jButtonDeleteSelected = new javax.swing.JButton();
    jPanelFilter = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jDateChooserDateFrom = new com.toedter.calendar.JDateChooser();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jDateChooserDateTo = new com.toedter.calendar.JDateChooser();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Тренировки");
    setMinimumSize(new java.awt.Dimension(629, 158));

    jTableTrainings.setModel(shootingTrainingsTableModel);
    jTableTrainings.setRowHeight(24);
    jScrollPane1.setViewportView(jTableTrainings);

    jButtonAddTrainings.setText("Добавить тренировки ...");
    jButtonAddTrainings.setEnabled(false);
    jButtonAddTrainings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTrainingsActionPerformed(evt);
      }
    });

    jButtonEditSelected.setText("Редактировать выделенную ...");
    jButtonEditSelected.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEditSelectedActionPerformed(evt);
      }
    });

    jButtonDeleteSelected.setText("Удалить выделенную");
    jButtonDeleteSelected.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonDeleteSelectedActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanelEditControlsLayout = new org.jdesktop.layout.GroupLayout(jPanelEditControls);
    jPanelEditControls.setLayout(jPanelEditControlsLayout);
    jPanelEditControlsLayout.setHorizontalGroup(
      jPanelEditControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelEditControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddTrainings)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonEditSelected)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonDeleteSelected)
        .addContainerGap(151, Short.MAX_VALUE))
    );
    jPanelEditControlsLayout.setVerticalGroup(
      jPanelEditControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelEditControlsLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelEditControlsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonAddTrainings)
          .add(jButtonEditSelected)
          .add(jButtonDeleteSelected))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel1.setText("Период:");

    jLabel2.setText("с");

    jLabel3.setText("по");

    org.jdesktop.layout.GroupLayout jPanelFilterLayout = new org.jdesktop.layout.GroupLayout(jPanelFilter);
    jPanelFilter.setLayout(jPanelFilterLayout);
    jPanelFilterLayout.setHorizontalGroup(
      jPanelFilterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelFilterLayout.createSequentialGroup()
        .addContainerGap()
        .add(jLabel1)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jLabel2)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jDateChooserDateFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jLabel3)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jDateChooserDateTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelFilterLayout.setVerticalGroup(
      jPanelFilterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelFilterLayout.createSequentialGroup()
        .addContainerGap()
        .add(jPanelFilterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jDateChooserDateTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jPanelFilterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jDateChooserDateFrom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanelEditControls, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(jPanelFilter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(jPanelFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanelEditControls, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddTrainingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTrainingsActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTrainingsActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_jButtonAddTrainingsActionPerformed

  private void jButtonDeleteSelectedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteSelectedActionPerformed
  {//GEN-HEADEREND:event_jButtonDeleteSelectedActionPerformed
		if (jTableTrainings.getSelectedRowCount() > 0)
		{
			shootingTrainingsTableModel.removeRowAndTraining(jTableTrainings.getSelectedRow());
		}
  }//GEN-LAST:event_jButtonDeleteSelectedActionPerformed

  private void jButtonEditSelectedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditSelectedActionPerformed
  {//GEN-HEADEREND:event_jButtonEditSelectedActionPerformed
    
  }//GEN-LAST:event_jButtonEditSelectedActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTrainings;
  private javax.swing.JButton jButtonDeleteSelected;
  private javax.swing.JButton jButtonEditSelected;
  private com.toedter.calendar.JDateChooser jDateChooserDateFrom;
  private com.toedter.calendar.JDateChooser jDateChooserDateTo;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanelEditControls;
  private javax.swing.JPanel jPanelFilter;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableTrainings;
  // End of variables declaration//GEN-END:variables
}
