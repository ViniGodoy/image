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

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel lblAbout = null;
    private JLabel lblLogo = null;


    public AboutDialog(Frame owner) {
        super(owner);
        initialize();
        setLocationRelativeTo(owner);
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(409, 199);
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setModal(true);
        this.setTitle("About the image editor...");
        this.setContentPane(getJContentPane());
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            lblLogo = new JLabel();
            lblLogo.setText("");
            lblLogo.setOpaque(true);
            lblLogo.setBackground(Color.white);
            lblLogo.setVerticalAlignment(SwingConstants.TOP);
            lblLogo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
            lblLogo.setIcon(new ImageIcon(getClass().getResource("/br/com/vinigodoy/image/gui/resource/LogoPucSmall.jpg")));
            lblAbout = new JLabel();
            lblAbout.setText("<html><body><b><font size=+1>Image editor</font><p>Version 1.3.0<p></b><p><b>Author:</b> Vinicius Godoy de Mendonca<p><b>Coordinator:</b> Jacques Facon<p><p>Developed in the Computer Vision Group of PUCPR PPGIa.");
            lblAbout.setBackground(Color.white);
            lblAbout.setOpaque(true);
            lblAbout.setVerticalAlignment(SwingConstants.TOP);
            lblAbout.setFont(new Font("Dialog", Font.PLAIN, 12));
            lblAbout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.setBackground(Color.white);
            jContentPane.add(lblAbout, lblAbout.getName());
            jContentPane.add(lblLogo, BorderLayout.EAST);
        }
        return jContentPane;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
