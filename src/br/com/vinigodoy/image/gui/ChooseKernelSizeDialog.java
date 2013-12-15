/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.gui;

import javax.swing.*;
import java.awt.*;

public class ChooseKernelSizeDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel pnlButtons = null;
    private JPanel pnlCenter = null;
    private JLabel lblLines = null;
    private JTextField txtLines = null;
    private JLabel lblColumns = null;
    private JTextField txtColumns = null;
    private JButton btnOk = null;
    private JButton btnCancel = null;
    private boolean modalResult = false;
    public int lines = -1;
    public int columns = -1;

    public ChooseKernelSizeDialog(Frame owner) {
        super(owner);
        initialize();
        setLocationRelativeTo(owner);
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(163, 134);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setTitle("Kernel");
        this.setContentPane(getJContentPane());
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getPnlButtons(), BorderLayout.SOUTH);
            jContentPane.add(getPnlCenter(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    /**
     * This method initializes pnlButtons
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlButtons() {
        if (pnlButtons == null) {
            pnlButtons = new JPanel();
            pnlButtons.setLayout(new FlowLayout());
            pnlButtons.add(getBtnOk(), null);
            pnlButtons.add(getBtnCancel(), null);
        }
        return pnlButtons;
    }

    /**
     * This method initializes pnlCenter
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlCenter() {
        if (pnlCenter == null) {
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.fill = GridBagConstraints.BOTH;
            gridBagConstraints3.gridy = 1;
            gridBagConstraints3.weightx = 1.0;
            gridBagConstraints3.insets = new Insets(0, 5, 5, 5);
            gridBagConstraints3.gridx = 1;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.insets = new Insets(0, 5, 5, 5);
            gridBagConstraints2.anchor = GridBagConstraints.EAST;
            gridBagConstraints2.gridy = 1;
            lblColumns = new JLabel();
            lblColumns.setText("Columns:");
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.fill = GridBagConstraints.BOTH;
            gridBagConstraints1.gridy = 0;
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints1.gridx = 1;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints.anchor = GridBagConstraints.EAST;
            gridBagConstraints.gridy = 0;
            lblLines = new JLabel();
            lblLines.setText("Lines:");
            pnlCenter = new JPanel();
            pnlCenter.setLayout(new GridBagLayout());
            pnlCenter.add(lblLines, gridBagConstraints);
            pnlCenter.add(getTxtLines(), gridBagConstraints1);
            pnlCenter.add(lblColumns, gridBagConstraints2);
            pnlCenter.add(getTxtColumns(), gridBagConstraints3);
        }
        return pnlCenter;
    }

    /**
     * This method initializes txtLines
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtLines() {
        if (txtLines == null) {
            txtLines = new JTextField();
            txtLines.setDocument(new IntegerDocument(1));
            txtLines.setText("3");

        }
        return txtLines;
    }

    /**
     * This method initializes txtColumns
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtColumns() {
        if (txtColumns == null) {
            txtColumns = new JTextField();
            txtColumns.setDocument(new IntegerDocument(1));
            txtColumns.setText("3");
        }
        return txtColumns;
    }

    /**
     * This method initializes btnOk
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnOk() {
        if (btnOk == null) {
            btnOk = new JButton();
            btnOk.setText("Ok");
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    onOk();
                }
            });
        }
        return btnOk;
    }

    protected void onOk() {
        if (txtLines.getText().isEmpty()) {
            showError("Choose the line number!!");
            txtLines.requestFocusInWindow();
            return;
        }

        if (txtColumns.getText().isEmpty()) {
            showError("Choose the column number!");
            txtColumns.requestFocusInWindow();
            return;
        }

        try {
            lines = Integer.parseInt(txtLines.getText());
            columns = Integer.parseInt(txtColumns.getText());
        } catch (Exception e) {
            return;
        }

        modalResult = true;
        dispose();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method initializes btnCancel
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new JButton();
            btnCancel.setText("Cancel");
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnCancel;
    }

    public boolean showModal() {
        modalResult = false;
        setVisible(true);
        return modalResult;
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
