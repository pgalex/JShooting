package com.jshooting.forms;

import com.jshooting.componentsHighlighting.ComponentsHighlighter;
import com.jshooting.logics.ShootingLogicsFactory;
import java.awt.Window;

/**
 * Teams editing dialog
 *
 * @author pgalex
 */
public class TeamsDialog extends javax.swing.JDialog
{
	/**
	 * Dialog components highlighter
	 */
	private ComponentsHighlighter componentsHighlighter;
	/**
	 * Table model for teams table
	 */
	private TeamsTableModel teamsTableModel;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param logicsFactory shooting logics factory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public TeamsDialog(Window parentWindow, ModalityType modalityType,
					ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("editingTeamsTable is null");
		}

		teamsTableModel = new TeamsTableModel(logicsFactory);
		componentsHighlighter = new ComponentsHighlighter();

		initComponents();

		updateHighlighting();
	}

	/**
	 * Update dialog components highlighting
	 */
	private void updateHighlighting()
	{
		if (jTableTeams.getRowCount() == 0)
		{
			componentsHighlighter.startComponentHightlighing(jButtonAddTeam, HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
		}
		else
		{
			componentsHighlighter.stopComponentHighlighting(jButtonAddTeam);
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
    jTableTeams = new javax.swing.JTable();
    jButtonAddTeam = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Комадны");

    jTableTeams.setModel(teamsTableModel);
    jTableTeams.setRowHeight(24);
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
		if (jTableTeams.getRowCount() > 0)
		{
			jTableTeams.setRowSelectionInterval(jTableTeams.getRowCount() - 1, jTableTeams.getRowCount() - 1);
			jTableTeams.editCellAt(jTableTeams.getRowCount() - 1, TeamsTableModel.NAME_COLUMN_INDEX);
			jTableTeams.requestFocus();
		}

		updateHighlighting();
  }//GEN-LAST:event_jButtonAddTeamActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTeam;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableTeams;
  // End of variables declaration//GEN-END:variables
}