package com.jshooting.forms;

import com.jshooting.shootingDatabase.Sportsman;
import com.jshooting.shootingDatabase.SportsmansTable;
import com.jshooting.shootingDatabase.Team;
import com.jshooting.shootingDatabase.TeamsTable;
import com.jshooting.shootingDatabase.exceptions.DatabaseErrorException;
import java.awt.Color;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

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
	 * Table of teams
	 */
	private TeamsTable teamsTable;

	/**
	 * Create new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param teamsTable teams table
	 * @param sportsmansTable sportsmans table
	 * @throws IllegalArgumentException teamsTable or sportsmansTable is null
	 *
	 */
	public EditSportsmansDialog(Window parentWindow, ModalityType modalityType, TeamsTable teamsTable,
					SportsmansTable sportsmansTable) throws IllegalArgumentException
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

		this.teamsTable = teamsTable;
		this.sportsmansTableModel = new SportsmansTableModel(sportsmansTable);
		initializeTeamsComboBoxModel();

		initComponents();

		fillSportsmansTableBySelectedTeam();
		updateControlsStateByTeams();
	}

	/**
	 * Update dialog controls state by "is any teams exists"
	 */
	private void updateControlsStateByTeams()
	{
		if (jComboBoxTeams.getItemCount() > 0)
		{
			jButtonAddSportsman.setEnabled(true);
			jComboBoxTeams.setEnabled(true);
			jButtonEditTeams.setBackground(UIManager.getColor("Button.background"));
		}
		else
		{
			jButtonAddSportsman.setEnabled(false);
			jComboBoxTeams.setEnabled(false);
			jButtonEditTeams.setBackground(new Color(0, 185, 0, 255));
		}
	}

	/**
	 * Initialize teams combo box model by getting all teams from teams table
	 */
	private void initializeTeamsComboBoxModel()
	{
		try
		{
			teamsComboBoxModel = new DefaultComboBoxModel(teamsTable.getAllTeams().toArray());
		}
		catch (DatabaseErrorException ex)
		{
			teamsComboBoxModel = new DefaultComboBoxModel();
		}
	}

	/**
	 * Fill sportsmans table with sportsmans filtered by selected team in combo
	 * box
	 */
	private void fillSportsmansTableBySelectedTeam()
	{
		if (jComboBoxTeams.getSelectedItem() != null)
		{
			Team selectedTeam = (Team) jComboBoxTeams.getSelectedItem();
			sportsmansTableModel.setFilteringTeam(selectedTeam);
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
    jButtonEditTeams = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Спортсмены");

    jTableSportsmans.setModel(sportsmansTableModel);
    jScrollPane1.setViewportView(jTableSportsmans);

    jComboBoxTeams.setModel(teamsComboBoxModel);
    jComboBoxTeams.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jComboBoxTeamsActionPerformed(evt);
      }
    });

    jLabelTeam.setText("Команда");

    jButtonAddSportsman.setText("Добавить");
    jButtonAddSportsman.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddSportsmanActionPerformed(evt);
      }
    });

    jButtonEditTeams.setText("Команды ...");
    jButtonEditTeams.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEditTeamsActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddSportsman)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .add(layout.createSequentialGroup()
        .add(15, 15, 15)
        .add(jLabelTeam)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jComboBoxTeams, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonEditTeams)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(7, 7, 7)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jComboBoxTeams, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabelTeam)
          .add(jButtonEditTeams))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jButtonAddSportsman)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddSportsmanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddSportsmanActionPerformed
  {//GEN-HEADEREND:event_jButtonAddSportsmanActionPerformed
		if (jComboBoxTeams.getSelectedItem() != null)
		{
			Team selectedTeam = (Team) jComboBoxTeams.getSelectedItem();
			Sportsman newSportsman = new Sportsman();
			newSportsman.setName("Новый спортсмен");
			newSportsman.setTeam(selectedTeam);
			sportsmansTableModel.addNewSportsman(newSportsman);
		}
  }//GEN-LAST:event_jButtonAddSportsmanActionPerformed

  private void jButtonEditTeamsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditTeamsActionPerformed
  {//GEN-HEADEREND:event_jButtonEditTeamsActionPerformed
		EditTeamsDialog editTeamsDialog = new EditTeamsDialog(this, ModalityType.APPLICATION_MODAL,
						teamsTable);
		editTeamsDialog.setLocationRelativeTo(this);
		editTeamsDialog.setVisible(true);

		int previousSelectedTeamIndex = jComboBoxTeams.getSelectedIndex();
		initializeTeamsComboBoxModel();
		jComboBoxTeams.setModel(teamsComboBoxModel);
		if (previousSelectedTeamIndex >= 0 && previousSelectedTeamIndex < jComboBoxTeams.getItemCount())
		{
			jComboBoxTeams.setSelectedIndex(previousSelectedTeamIndex);
		}
		fillSportsmansTableBySelectedTeam();
		updateControlsStateByTeams();
  }//GEN-LAST:event_jButtonEditTeamsActionPerformed

  private void jComboBoxTeamsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTeamsActionPerformed
  {//GEN-HEADEREND:event_jComboBoxTeamsActionPerformed
		fillSportsmansTableBySelectedTeam();
  }//GEN-LAST:event_jComboBoxTeamsActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddSportsman;
  private javax.swing.JButton jButtonEditTeams;
  private javax.swing.JComboBox jComboBoxTeams;
  private javax.swing.JLabel jLabelTeam;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableSportsmans;
  // End of variables declaration//GEN-END:variables
}
