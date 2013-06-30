package com.jshooting.forms;

import com.jshooting.shootingDatabase.TeamsTable;
import java.awt.Window;

/**
 * Teams editing dialog
 *
 * @author pgalex
 */
public class EditTeamsDialog extends javax.swing.JDialog
{
	/**
	 * Table model for teams table
	 */
	private TeamsTableModel teamsTableModel;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param editingTeamsTable teams table using to edit teams
	 * @throws IllegalArgumentException editingTeamsTable is null
	 */
	public EditTeamsDialog(Window parentWindow, ModalityType modalityType,
					TeamsTable editingTeamsTable) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (editingTeamsTable == null)
		{
			throw new IllegalArgumentException("editingTeamsTable is null");
		}

		teamsTableModel = new TeamsTableModel(editingTeamsTable);

		initComponents();
		setTitle("Команды");
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
    jTableTeams = new javax.swing.JTable();
    jButtonAddTeam = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jTableTeams.setModel(teamsTableModel);
    jScrollPane1.setViewportView(jTableTeams);

    jButtonAddTeam.setText("Добавить");
    jButtonAddTeam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddTeamActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddTeam)
        .addContainerGap(255, Short.MAX_VALUE))
      .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonAddTeam)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddTeamActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddTeamActionPerformed
  {//GEN-HEADEREND:event_jButtonAddTeamActionPerformed
		teamsTableModel.addNewTeam();
  }//GEN-LAST:event_jButtonAddTeamActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTeam;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableTeams;
  // End of variables declaration//GEN-END:variables
}
