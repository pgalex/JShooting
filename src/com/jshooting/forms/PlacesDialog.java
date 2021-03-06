package com.jshooting.forms;

import com.jshooting.objectsHighlighting.ColourObjectsHighlighter;
import com.jshooting.logics.PlacesModifier;
import com.jshooting.logics.ShootingLogicsFactory;
import com.jshooting.logics.exceptions.ShootingLogicsErrorException;
import com.jshooting.model.Place;
import com.jshooting.objectsHighlighting.ComponentBackgroundHighlightingAdapter;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Window;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 * Dialog for editing places
 *
 * @author pgalex
 */
public class PlacesDialog extends javax.swing.JDialog
{
	/**
	 * Dialog components highlighter
	 */
	private ColourObjectsHighlighter componentsHighlighter;
	/**
	 * Table model of places table
	 */
	private PlacesTableModel placesTableModel;
	/**
	 * Place modifier using while adding and editing places
	 */
	private PlacesModifier placesModifier;

	/**
	 * Creates new dialog
	 *
	 * @param parentWindow parent window
	 * @param modalityType modality type of dialog
	 * @param logicsFactory logics factory. Must be not null
	 * @throws IllegalArgumentException logicsFactory is null
	 */
	public PlacesDialog(Window parentWindow, ModalityType modalityType, ShootingLogicsFactory logicsFactory) throws IllegalArgumentException
	{
		super(parentWindow, modalityType);

		if (logicsFactory == null)
		{
			throw new IllegalArgumentException("logicsFactory is null");
		}

		placesTableModel = new PlacesTableModel(logicsFactory);
		componentsHighlighter = new ColourObjectsHighlighter();
		placesModifier = logicsFactory.createPlacesModifier();

		initComponents();

		TableColumn beginDateColumn = jTablePlaces.getColumnModel().getColumn(PlacesTableModel.BEGIN_DATE_COLUMN_INDEX);
		beginDateColumn.setCellEditor(new DefaultCellEditor(new JTextFieldDateEditor(null, null, '-')));
		TableColumn endDateColumn = jTablePlaces.getColumnModel().getColumn(PlacesTableModel.END_DATE_COLUMN_INDEX);
		endDateColumn.setCellEditor(new DefaultCellEditor(new JTextFieldDateEditor(null, null, '-')));

		updateEditPlaceButtonEnabled();
		updateHighlighting();
	}

	/**
	 * Update dialog components highlighting
	 */
	private void updateHighlighting()
	{
		if (jTablePlaces.getRowCount() == 0)
		{
			componentsHighlighter.startObjectHightlighing(new ComponentBackgroundHighlightingAdapter(jButtonAddPlace), 
							HighlightingConstants.GOOD_HIGHLIGHT_COLOR, HighlightingConstants.BLINKING_TIME);
		}
		else
		{
			componentsHighlighter.stopObjectHighlighting(jButtonAddPlace);
		}
	}

	/**
	 * Update edit button enable
	 */
	private void updateEditPlaceButtonEnabled()
	{
		jButtonEditPlace.setEnabled(jTablePlaces.getRowCount() > 0);
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
    jTablePlaces = new javax.swing.JTable();
    jButtonAddPlace = new javax.swing.JButton();
    jButtonEditPlace = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("УТС");
    setMinimumSize(new java.awt.Dimension(269, 77));

    jTablePlaces.setModel(placesTableModel);
    jTablePlaces.setRowHeight(24);
    jTablePlaces.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(jTablePlaces);

    jButtonAddPlace.setText("Добавить");
    jButtonAddPlace.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonAddPlaceActionPerformed(evt);
      }
    });

    jButtonEditPlace.setText("Редактировать");
    jButtonEditPlace.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        jButtonEditPlaceActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jButtonAddPlace)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jButtonEditPlace)
        .addContainerGap(248, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jButtonAddPlace)
          .add(jButtonEditPlace))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddPlaceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddPlaceActionPerformed
  {//GEN-HEADEREND:event_jButtonAddPlaceActionPerformed

		EditPlaceDialog addPlaceDialog = new EditPlaceDialog(this, ModalityType.DOCUMENT_MODAL, "Добавить УТС", "Добавить");
		addPlaceDialog.setLocationRelativeTo(this);
		addPlaceDialog.setVisible(true);
		if (addPlaceDialog.isOkButtonPressed())
		{
			try
			{
				placesModifier.addPlace(addPlaceDialog.getPlace());
			}
			catch (ShootingLogicsErrorException ex)
			{
				JOptionPane.showMessageDialog(null, "Не удалось добавить УТС: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
			finally
			{
				placesTableModel.update();
				updateEditPlaceButtonEnabled();
				updateHighlighting();
			}
		}
  }//GEN-LAST:event_jButtonAddPlaceActionPerformed

  private void jButtonEditPlaceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditPlaceActionPerformed
  {//GEN-HEADEREND:event_jButtonEditPlaceActionPerformed
		if (jTablePlaces.getSelectedRowCount() > 0)
		{
			Place placeToEdit = placesTableModel.getPlaceAtRow(jTablePlaces.getSelectedRows()[0]);
			EditPlaceDialog editPlaceDialog = new EditPlaceDialog(this, ModalityType.DOCUMENT_MODAL, "Редактировать УТС", "OK");
			editPlaceDialog.setPlace(placeToEdit);
			editPlaceDialog.setLocationRelativeTo(this);
			editPlaceDialog.setVisible(true);
			if (editPlaceDialog.isOkButtonPressed())
			{
				try
				{
					placesModifier.updatePlace(editPlaceDialog.getPlace());
				}
				catch (ShootingLogicsErrorException ex)
				{
					JOptionPane.showMessageDialog(null, "Не удалось изменить УТС: " + ex.getLocalizedMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
				finally
				{
					placesTableModel.update();
				}
			}
		}
  }//GEN-LAST:event_jButtonEditPlaceActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddPlace;
  private javax.swing.JButton jButtonEditPlace;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTablePlaces;
  // End of variables declaration//GEN-END:variables
}
