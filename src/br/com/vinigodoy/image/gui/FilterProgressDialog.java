/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.gui;

import br.com.vinigodoy.image.processing.Processing;
import br.com.vinigodoy.image.processing.ProcessingEvent;
import br.com.vinigodoy.image.processing.ProcessingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FilterProgressDialog extends JDialog implements ProcessingListener {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel lblInformation = null;
    private JProgressBar pbFiltro = null;
    private BufferedImage filtered;

    public FilterProgressDialog(Frame owner) {
        super(owner);
        initialize();
        this.setLocationRelativeTo(owner);
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(469, 100);
        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Processing...");
        this.setContentPane(getJContentPane());
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints1.insets = new Insets(10, 5, 0, 5);
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.gridy = 1;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints.gridy = 0;
            lblInformation = new JLabel();
            jContentPane = new JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jContentPane.add(lblInformation, gridBagConstraints);
            jContentPane.add(getPbFiltro(), gridBagConstraints1);
        }
        return jContentPane;
    }

    @Override
    public void operationStarted(ProcessingEvent evt) {
        pbFiltro.setMinimum(0);
        lblInformation.setText("Processing " + evt.getName() + ", please wait...");
        if (evt.getTotalPixels() != -1) {
            pbFiltro.setMaximum(evt.getTotalPixels());
            pbFiltro.setString("0 of " + evt.getTotalPixels());
            pbFiltro.setStringPainted(true);
            pbFiltro.setIndeterminate(false);
        } else {
            pbFiltro.setIndeterminate(true);
            pbFiltro.setStringPainted(false);
        }
    }

    @Override
    public void operationProgress(ProcessingEvent evt) {
        pbFiltro.setValue(evt.getCurrentPixel());
        pbFiltro.setString(evt.getCurrentPixel() + " of " + evt.getTotalPixels());
    }

    @Override
    public void operationEnded(ProcessingEvent evt) {
        filtered = evt.getResult();
        dispose();
    }

    public BufferedImage showModal(Processing filter, BufferedImage img) {
        filter.addListener(this);
        FilterRunnable filterRunnable = new FilterRunnable(filter, img);
        Thread filterThread = new Thread(filterRunnable, "Filter thread");
        filterThread.setDaemon(true);
        filterThread.start();
        setVisible(true);
        filter.removeListener(this);
        return filtered;
    }

    private static class FilterRunnable implements Runnable {
        private BufferedImage source;
        private Processing filter;

        public FilterRunnable(Processing filter, BufferedImage source) {
            this.source = source;
            this.filter = filter;
        }

        @Override
        public void run() {
            filter.process(source);
        }
    }

    /**
     * This method initializes pbFiltro
     *
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getPbFiltro() {
        if (pbFiltro == null) {
            pbFiltro = new JProgressBar();
        }
        return pbFiltro;
    }


}  //  @jve:decl-index=0:visual-constraint="11,-4"
