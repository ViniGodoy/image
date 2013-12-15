/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.gui;

import br.com.vinigodoy.image.morpho.StructuringElement;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChooseStructuringElementDialog extends JDialog {
    private StructuringElement selected;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtIterations;
    private final ButtonGroup bgForma = new ButtonGroup();
    private JRadioButton rdbtnSquare;
    private JRadioButton rdbtnCross;
    private JRadioButton rdbtnVerticalLine;
    private JRadioButton rdbtnRhombus;
    private JRadioButton rdbtnHorizontalLine;
    private int iteracoes;
    private boolean ok = false;

    public ChooseStructuringElementDialog(JFrame mainFrame) {
        super(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setModal(true);
        setResizable(false);
        setTitle("Structuring element");
        setBounds(100, 100, 290, 294);
        setLocationRelativeTo(mainFrame);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);


        JLabel lblIteracoes = new JLabel("Iterations:");

        txtIterations = new JTextField();
        txtIterations.setColumns(10);
        txtIterations.setDocument(new IntegerDocument(2));
        txtIterations.setText("1");

        rdbtnCross = new JRadioButton("");
        bgForma.add(rdbtnCross);
        rdbtnCross.setSelected(true);

        rdbtnHorizontalLine = new JRadioButton("");
        bgForma.add(rdbtnHorizontalLine);

        rdbtnVerticalLine = new JRadioButton("");
        bgForma.add(rdbtnVerticalLine);

        rdbtnSquare = new JRadioButton("");
        bgForma.add(rdbtnSquare);

        rdbtnRhombus = new JRadioButton("");
        bgForma.add(rdbtnRhombus);

        JLabel lblHorizontalLine = new JLabel("  Horizontal line");
        lblHorizontalLine
                .setIcon(new ImageIcon(
                        ChooseStructuringElementDialog.class
                                .getResource("/br/com/vinigodoy/image/gui/resource/horizontalLine.png")));

        JLabel lblVerticalLine = new JLabel("  Vertical line");
        lblVerticalLine
                .setIcon(new ImageIcon(
                        ChooseStructuringElementDialog.class
                                .getResource("/br/com/vinigodoy/image/gui/resource/verticalLine.png")));

        JLabel lblCross = new JLabel("  Cross");
        lblCross.setIcon(new ImageIcon(ChooseStructuringElementDialog.class
                .getResource("/br/com/vinigodoy/image/gui/resource/cross.png")));

        JLabel lblSquare = new JLabel("  Square");
        lblSquare
                .setIcon(new ImageIcon(
                        ChooseStructuringElementDialog.class
                                .getResource("/br/com/vinigodoy/image/gui/resource/square.png")));

        JLabel lblRhombus = new JLabel("  Rhombus");
        lblRhombus
                .setIcon(new ImageIcon(
                        ChooseStructuringElementDialog.class
                                .getResource("/br/com/vinigodoy/image/gui/resource/rhombus.png")));
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel
                .setHorizontalGroup(gl_contentPanel
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                gl_contentPanel
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addGroup(
                                                                gl_contentPanel
                                                                        .createSequentialGroup()
                                                                        .addComponent(
                                                                                rdbtnHorizontalLine)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                lblHorizontalLine)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                64,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(
                                                                                lblIteracoes)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                txtIterations,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(
                                                                gl_contentPanel
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                gl_contentPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                rdbtnVerticalLine)
                                                                                        .addComponent(
                                                                                                rdbtnCross))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                gl_contentPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                lblVerticalLine)
                                                                                        .addComponent(
                                                                                                lblCross))
                                                                        .addGap(6))
                                                        .addGroup(
                                                                gl_contentPanel
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                gl_contentPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                rdbtnSquare)
                                                                                        .addComponent(
                                                                                                rdbtnRhombus))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                gl_contentPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                lblRhombus)
                                                                                        .addComponent(
                                                                                                lblSquare))
                                                                        .addGap(8)))
                                        .addContainerGap()));
        gl_contentPanel
                .setVerticalGroup(gl_contentPanel
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                gl_contentPanel
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                rdbtnHorizontalLine)
                                                        .addGroup(
                                                                gl_contentPanel
                                                                        .createParallelGroup(
                                                                                Alignment.BASELINE)
                                                                        .addComponent(
                                                                                lblHorizontalLine)
                                                                        .addComponent(
                                                                                txtIterations,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                lblIteracoes)))
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                rdbtnVerticalLine)
                                                        .addComponent(
                                                                lblVerticalLine))
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(lblCross)
                                                        .addComponent(rdbtnCross))
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                rdbtnSquare)
                                                        .addComponent(
                                                                lblSquare))
                                        .addGap(7)
                                        .addGroup(
                                                gl_contentPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                lblRhombus)
                                                        .addComponent(
                                                                rdbtnRhombus))
                                        .addContainerGap(31, Short.MAX_VALUE)));
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onOk();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    protected void onOk() {
        try {
            iteracoes = Integer.parseInt(txtIterations.getText());
        } catch (NumberFormatException e) {
            iteracoes = 0;
        }

        if (iteracoes < 1) {
            JOptionPane.showMessageDialog(this,
                    "You must define at least one interaction!");
            return;
        }

        if (getRdbtnCross().isSelected())
            selected = StructuringElement.CROSS;
        else if (getRdbtnHorizontalLine().isSelected())
            selected = StructuringElement.HORIZONTAL_LINE;
        else if (getRdbtnVerticalLine().isSelected())
            selected = StructuringElement.VERTICAL_LINE;
        else if (getRdbtnSquare().isSelected())
            selected = StructuringElement.SQUARE;
        else
            selected = StructuringElement.RHOMBUS;

        ok = true;
        dispose();
    }

    public StructuringElement getStructuringElement() {
        return selected;
    }

    public int getIterations() {
        return Integer.parseInt(txtIterations.getText());
    }

    protected JRadioButton getRdbtnSquare() {
        return rdbtnSquare;
    }

    protected JRadioButton getRdbtnCross() {
        return rdbtnCross;
    }

    protected JRadioButton getRdbtnVerticalLine() {
        return rdbtnVerticalLine;
    }

    protected JRadioButton getRdbtnRhombus() {
        return rdbtnRhombus;
    }

    protected JRadioButton getRdbtnHorizontalLine() {
        return rdbtnHorizontalLine;
    }

    protected JTextField getTxtIterations() {
        return txtIterations;
    }

    public boolean showModal() {
        ok = false;
        setVisible(true);
        return ok;
    }
}
