package com.jshooting.forms;

import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;

/**
 * Sportsmans editing dialog
 *
 * @author pgalex
 */
public class EditSportsmansDialog extends javax.swing.JDialog
{
	/**
	 * Model of team selecting combo box
	 */
	private DefaultComboBoxModel teamsComboBoxModel;
	/**
	 * Table model for sportsmans table
	 */
	private SportsmansTableModel sportsmansTableModel;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param teamsTable teams table
	 * @param editingSportsmansTable editing sportsmans table
	 * @throws IllegalArgumentException teamsTable or editingSportsmansTable is
	 * null
	 *
	 */
	public EditSportsmansDialog(Window parentWindow, ModalityType modalityType, TeamsTable teamsTable,
					SportsmansTable editingSportsmansTable) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);
		
		if (teamsTable == null)
		{
			throw new IllegalArgumentException("teamsTable is null");
		}
		if (editingSportsmansTable == null)
		{
			throw new IllegalArgumentException("editingSportsmansTable is null");
		}
		
		try
		{
			teamsComboBoxModel = new DefaultComboBoxModel(teamsTable.getAllTeams().toArray());
		}
		catch (DatabaseErrorException ex)
		{
			teamsComboBoxModel = new DefaultComboBoxModel();
		}
		sportsmansTableModel = new SportsmansTableModel(editingSportsmansTable);
		
		initComponents();
		setTitle("Спортсмены");
		
		if (jComboBoxTeams.getItemCount() > 0)
		{
			jComboBoxTeams.setSelectedIndex(0);
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
    jTableSportsmans = new javax.swing.JTable();
    jComboBoxTeams = new javax.swing.JComboBox();
    jLabelTeam = new javax.swing.JLabel();
    jButtonAddSportsman = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jTableSportsmans.setModel(sportsmansTableModel);
    jScrollPane1.setViewportView(jTableSportsmans);

    jComboBoxTeams.setModel(teamsComboBoxModel);

    jLabelTeam.setText("Команда");

    jButtonAddSportsman.setText("Добавить");
    jButtonAddSportsman.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddSportsmanActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .add(14, 14, 14)
        .add(jLabelTeam)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jComboBoxTeams, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddSportsman)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddSportsman)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jComboBoxTeams, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabelTeam))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddSportsmanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddSportsmanActionPerformed
  {//GEN-HEADEREND:event_jButtonAddSportsmanActionPerformed
		if (jComboBoxTeams.getSelectedIndex() >= 0 && jComboBoxTeams.getSelectedIndex() < jComboBoxTeams.getItemCount())
		{
			Team selectedTeam = (Team) teamsComboBoxModel.getElementAt(jComboBoxTeams.getSelectedIndex());
			Sportsman newSportsman = new Sportsman();
			newSportsman.setName("Новый спортсмен");
			newSportsman.setTeam(selectedTeam);
			sportsmansTableModel.addSportsman(newSportsman);
		}
  }//GEN-LAST:event_jButtonAddSportsmanActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddSportsman;
  private javax.swing.JComboBox jComboBoxTeams;
  private javax.swing.JLabel jLabelTeam;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableSportsmans;
  // End of variables declaration//GEN-END:variables
}
