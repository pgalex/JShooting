package com.jshooting.forms;

import com.jshooting.shootingDatabase.TrainingMethodsTable;
import java.awt.Window;

/**
 * Training methods editing dialog
 *
 * @author pgalex
 */
public class EditTrainingMethodsDialog extends javax.swing.JDialog
{
	/**
	 * Table model for training methods table
	 */
	private TrainingMethodsTableModel trainingMethodsTableModel;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param trainingMethodsTable editing training methods table. Must be not
	 * null
	 * @throws IllegalArgumentException
	 */
	public EditTrainingMethodsDialog(Window parentWindow, ModalityType modalityType,
					TrainingMethodsTable trainingMethodsTable) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (trainingMethodsTable == null)
		{
			throw new IllegalArgumentException("trainingMethodsTable is null");
		}

		trainingMethodsTableModel = new TrainingMethodsTableModel(trainingMethodsTable);

		initComponents();
		setTitle("Средства");
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
    jTableTrainingMethods = new javax.swing.JTable();
    jButtonAddTrainingMethod = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jTableTrainingMethods.setModel(trainingMethodsTableModel);
    jTableTrainingMethods.setRowHeight(24);
    jScrollPane1.setViewportView(jTableTrainingMethods);

    jButtonAddTrainingMethod.setText("Добавить");
    jButtonAddTrainingMethod.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTrainingMethodActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddTrainingMethod)
        .addContainerGap(289, Short.MAX_VALUE))
      .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonAddTrainingMethod)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddTrainingMethodActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTrainingMethodActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTrainingMethodActionPerformed
		trainingMethodsTableModel.addNewTrainingMethod();
  }//GEN-LAST:event_jButtonAddTrainingMethodActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTrainingMethod;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableTrainingMethods;
  // End of variables declaration//GEN-END:variables
}
