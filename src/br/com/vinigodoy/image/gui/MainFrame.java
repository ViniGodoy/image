/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.gui;

import br.com.vinigodoy.image.ImageLoader;
import br.com.vinigodoy.image.color.ARGBChannels;
import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.color.GrayScaleChannel;
import br.com.vinigodoy.image.filters.*;
import br.com.vinigodoy.image.morpho.*;
import br.com.vinigodoy.image.processing.Processing;
import br.com.vinigodoy.image.transform.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private JPanel jContentPane = null;
    private JDesktopPane dskCenter = null;
    private JMenuBar mbMenu = null;
    private JMenu mnuFile = null;
    private JMenu mnuEdit = null;
    private JMenu mnuFilter = null;
    private JMenu mnuMorpho = null;

    private JMenu mnuHelp = null;
    private JMenuItem mniOpen = null;
    private JMenuItem mniSaveAs = null;
    private JMenuItem mniAbout = null;
    private JMenuItem mniExit = null;
    private JFileChooser openDialog = null;
    private JFileChooser saveDialog = null;

    private ImageLoader loader = new ImageLoader(); // @jve:decl-index=0:
    private static final String[] SUPPORTED_FORMATS = {"png", "jpg", "jpeg",
            "png", "gif", "tiff", "tif", "bmp"};
    private JMenu mnuSmoothing = null;
    private JMenu mnuBorders = null;
    private JMenu mnuAverage = null;
    private JMenuItem mniF1 = null;
    private JMenuItem mniF2 = null;
    private JMenuItem mniF3 = null;
    private JMenuItem mniF4 = null;
    private JMenu mnuPrewitt = null;
    private JMenuItem mniPrewittGx = null;
    private JMenuItem mniPrewittG = null;
    private JMenuItem mniPrewittGy = null;
    private JMenu mnuSobel1 = null;
    private JMenuItem mniSobelG1x = null;
    private JMenuItem mniSobelG1y = null;
    private JMenuItem mniSobelG1 = null;
    private JMenu mnuSobel2 = null;
    private JMenuItem mniSobelG2 = null;
    private JMenuItem mniSobelG2x = null;
    private JMenuItem mniSobelG2y = null;
    private JMenuItem mniMean = null;
    private JMenuItem mniLaplace = null;
    private JMenuItem mniNegative;
    private JMenu mnuChannels = null;
    private JRadioButtonMenuItem radRGB = null;
    private JRadioButtonMenuItem radGrayscale = null;
    private ButtonGroup bgChannels = new ButtonGroup();
    private ColorChannel[] channels = ARGBChannels.colors();
    private JMenuItem mniHistogramRedistribution = null;
    private JMenuItem mniDilation;
    private JMenuItem mniErosion;
    private JMenuItem mniOpening;
    private JMenuItem mniClosing;
    private JMenuItem mniReconstruction;
    private JMenu mnrGranulometry;

    private JMenuItem mniRotate90;
    private JMenuItem mniRotate180;
    private JMenuItem mniRotate270;
    private JMenuItem mniFlipVertical;
    private JMenuItem mniFlipHorizontal;
    private JMenu mnuRotate;
    private JMenuItem mniFlipBoth;
    private JMenu mnuFlip;
    private JMenu mnuGrow;
    private JMenu mnuShrink;
    private JMenuItem mniShrink2x;
    private JMenuItem mniShrink4x;
    private JMenuItem mniShrink8x;
    private JMenuItem mniGrow2x;
    private JMenuItem mniGrow4x;
    private JMenuItem mniGrow8x;
    private JMenuItem mniSubtract;
    private JMenuItem mniCropToBorder;
    private JMenuItem mniGranulometryErosion;
    private JMenuItem mniGranulometryASF;


    /**
     * This method initializes dskCenter
     *
     * @return javax.swing.JDesktopPane
     */
    private JDesktopPane getDskCenter() {
        if (dskCenter == null) {
            dskCenter = new JDesktopPane();
        }
        return dskCenter;
    }

    /**
     * This method initializes mbMenu
     *
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getMbMenu() {
        if (mbMenu == null) {
            mbMenu = new JMenuBar();
            mbMenu.add(getMnuFile());
            mbMenu.add(getMnuEdit());
            mbMenu.add(getMnuFilter());
            mbMenu.add(getMnuMorpho());
            mbMenu.add(getMnuHelp());
        }
        return mbMenu;
    }

    /**
     * This method initializes mnuFile
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuFile() {
        if (mnuFile == null) {
            mnuFile = new JMenu();
            mnuFile.setText("File");
            mnuFile.setMnemonic(KeyEvent.VK_F);
            mnuFile.add(getMniOpen());
            mnuFile.add(getMniSaveAs());
            mnuFile.addSeparator();
            mnuFile.add(getMniExit());
        }
        return mnuFile;
    }

    /**
     * This method initializes mnuFilter
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuFilter() {
        if (mnuFilter == null) {
            mnuFilter = new JMenu();
            mnuFilter.setText("Filters");
            mnuFilter.setMnemonic(KeyEvent.VK_I);
            mnuFilter.add(getMnuChannels());
            mnuFilter.add(getMnuSmoothing());
            mnuFilter.add(getMnuBorders());
            mnuFilter.add(getMniHistogramRedistribution());
        }
        return mnuFilter;
    }

    /**
     * This method initializes mnuFilter
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuMorpho() {
        if (mnuMorpho == null) {
            mnuMorpho = new JMenu();
            mnuMorpho.setText("Morphology");
            mnuMorpho.setMnemonic(KeyEvent.VK_M);
            mnuMorpho.add(getMniErosion());
            mnuMorpho.add(getMniDilation());
            mnuMorpho.addSeparator();
            mnuMorpho.add(getMniOpening());
            mnuMorpho.add(getMniClosing());
            mnuMorpho.addSeparator();
            mnuMorpho.add(getMniReconstruction());
            mnuMorpho.add(getMnuGranulometry());
        }
        return mnuMorpho;
    }

    private JMenu getMnuEdit() {
        if (mnuEdit == null) {
            mnuEdit = new JMenu();
            mnuEdit.setText("Edit");
            mnuEdit.setMnemonic(KeyEvent.VK_E);
            mnuEdit.add(getMnuRotate());
            mnuEdit.add(getMnuFlip());
            mnuEdit.addSeparator();
            mnuEdit.add(getMnuShrink());
            mnuEdit.add(getMnuGrow());
            mnuEdit.addSeparator();
            mnuEdit.add(getMniNegative());
            mnuEdit.add(getMniSubtract());
            mnuEdit.add(getMniCropToBorder());
        }
        return mnuEdit;
    }

    private JMenu getMnuRotate() {
        if (mnuRotate == null) {
            mnuRotate = new JMenu();
            mnuRotate.setText("Rotate");
            mnuRotate.setMnemonic(KeyEvent.VK_R);
            mnuRotate.add(getMniRotate90());
            mnuRotate.add(getMniRotate180());
            mnuRotate.add(getMniRotate270());
        }
        return mnuRotate;
    }

    private JMenu getMnuFlip() {
        if (mnuFlip == null) {
            mnuFlip = new JMenu();
            mnuFlip.setText("Flip");
            mnuFlip.setMnemonic(KeyEvent.VK_F);
            mnuFlip.add(getMniFlipVertical());
            mnuFlip.add(getMniFlipHorizontal());
            mnuFlip.add(getMniFlipBoth());
        }
        return mnuFlip;
    }

    private JMenu getMnuGrow() {
        if (mnuGrow == null) {
            mnuGrow = new JMenu();
            mnuGrow.setText("Enlarge");
            mnuGrow.setMnemonic(KeyEvent.VK_E);
            mnuGrow.add(getMniGrow2x());
            mnuGrow.add(getMniGrow4x());
            mnuGrow.add(getMniGrow8x());
        }
        return mnuGrow;
    }

    private JMenu getMnuShrink() {
        if (mnuShrink == null) {
            mnuShrink = new JMenu();
            mnuShrink.setText("Shrink");
            mnuShrink.add(getMniShrink2x());
            mnuShrink.add(getMniShrink4x());
            mnuShrink.add(getMniShrink8x());
            mnuShrink.setMnemonic(KeyEvent.VK_H);
        }
        return mnuShrink;
    }

    private JMenuItem getMniShrink2x() {
        if (mniShrink2x == null) {
            mniShrink2x = new JMenuItem();
            mniShrink2x.setText("1/2");
            mniShrink2x.setMnemonic(KeyEvent.VK_2);
            mniShrink2x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Shrink to 1/2", new TransformImage(getSelectedFrame()
                            .getImage(), 0.5));
                }
            });
        }
        return mniShrink2x;
    }

    private JMenuItem getMniShrink4x() {
        if (mniShrink4x == null) {
            mniShrink4x = new JMenuItem();
            mniShrink4x.setText("1/4");
            mniShrink4x.setMnemonic(KeyEvent.VK_4);
            mniShrink4x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Shrink to 1/4", new TransformImage(getSelectedFrame()
                            .getImage(), 0.25));
                }
            });
        }
        return mniShrink4x;
    }

    private JMenuItem getMniShrink8x() {
        if (mniShrink8x == null) {
            mniShrink8x = new JMenuItem();
            mniShrink8x.setText("1/8");
            mniShrink8x.setMnemonic(KeyEvent.VK_8);
            mniShrink8x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Shrink to 1/8", new TransformImage(getSelectedFrame()
                            .getImage(), 0.125));
                }
            });
        }
        return mniShrink8x;
    }

    private JMenuItem getMniGrow2x() {
        if (mniGrow2x == null) {
            mniGrow2x = new JMenuItem();
            mniGrow2x.setText("2x");
            mniGrow2x.setMnemonic(KeyEvent.VK_2);
            mniGrow2x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Enlarge 2x", new TransformImage(getSelectedFrame()
                            .getImage(), 2));
                }
            });
        }
        return mniGrow2x;
    }

    private JMenuItem getMniGrow4x() {
        if (mniGrow4x == null) {
            mniGrow4x = new JMenuItem();
            mniGrow4x.setText("4x");
            mniGrow4x.setMnemonic(KeyEvent.VK_4);
            mniGrow4x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Enlarge 4x", new TransformImage(getSelectedFrame()
                            .getImage(), 4));
                }
            });
        }
        return mniGrow4x;
    }

    private JMenuItem getMniGrow8x() {
        if (mniGrow8x == null) {
            mniGrow8x = new JMenuItem();
            mniGrow8x.setText("8x");
            mniGrow8x.setMnemonic(KeyEvent.VK_8);
            mniGrow8x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    applyProcessing("Enlarge 8x", new TransformImage(getSelectedFrame()
                            .getImage(), 8));
                }
            });
        }
        return mniGrow8x;
    }

    private JMenuItem getMniRotate90() {
        if (mniRotate90 == null) {
            mniRotate90 = new JMenuItem();
            mniRotate90.setText("Rotate 90 degrees");
            mniRotate90.setMnemonic(KeyEvent.VK_9);
            mniRotate90.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Rota��o 90 graus", new RotateImage(Rotation.R90));
                }
            });
        }
        return mniRotate90;
    }

    private JMenuItem getMniRotate180() {
        if (mniRotate180 == null) {
            mniRotate180 = new JMenuItem();
            mniRotate180.setText("Rotate 180 degrees");
            mniRotate180.setMnemonic(KeyEvent.VK_1);
            mniRotate180.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Rotate 180 degrees", new RotateImage(Rotation.R180));
                }
            });
        }
        return mniRotate180;
    }

    private JMenuItem getMniRotate270() {
        if (mniRotate270 == null) {
            mniRotate270 = new JMenuItem();
            mniRotate270.setText("Rotate 270 degrees");
            mniRotate270.setMnemonic(KeyEvent.VK_2);
            mniRotate270.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Rotate 270 degrees", new RotateImage(Rotation.R270));
                }
            });
        }
        return mniRotate270;
    }

    private JMenuItem getMniFlipVertical() {
        if (mniFlipVertical == null) {
            mniFlipVertical = new JMenuItem();
            mniFlipVertical.setText("Vertical");
            mniFlipVertical.setMnemonic(KeyEvent.VK_V);
            mniFlipVertical
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            applyProcessing("Flip vertical", new TransformImage(true, false));
                        }
                    });
        }
        return mniFlipVertical;
    }

    private JMenuItem getMniFlipHorizontal() {
        if (mniFlipHorizontal == null) {
            mniFlipHorizontal = new JMenuItem();
            mniFlipHorizontal.setText("Horizontal");
            mniFlipHorizontal.setMnemonic(KeyEvent.VK_H);
            mniFlipHorizontal
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            applyProcessing("Flip horizontal", new TransformImage(false, true));
                        }
                    });
        }
        return mniFlipHorizontal;
    }

    private JMenuItem getMniFlipBoth() {
        if (mniFlipBoth == null) {
            mniFlipBoth = new JMenuItem();
            mniFlipBoth.setText("Both");
            mniFlipBoth.setMnemonic(KeyEvent.VK_H);
            mniFlipBoth.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Flip both", new TransformImage(true, true));
                }
            });
        }
        return mniFlipBoth;
    }

    private JMenuItem getMniNegative() {
        if (mniNegative == null) {
            mniNegative = new JMenuItem();
            mniNegative.setText("Negate");
            mniNegative.setMnemonic(KeyEvent.VK_N);
            mniNegative.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Negate", new Negation(channels));
                }
            });
        }
        return mniNegative;
    }

    private JMenuItem getMniSubtract() {
        if (mniSubtract == null) {
            mniSubtract = new JMenuItem();
            mniSubtract.setText("Subtraction");
            mniSubtract.setMnemonic(KeyEvent.VK_S);
            mniSubtract.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    BufferedImage img = chooseImage("Subtract", "Choose the image to subtract", false);
                    if (img == null)
                        return;
                    applyProcessing("Subtraction", new Subtraction(img, channels));
                }
            });
        }
        return mniSubtract;
    }

    private JMenuItem getMniCropToBorder() {
        if (mniCropToBorder == null) {
            mniCropToBorder = new JMenuItem();
            mniCropToBorder.setText("Just borders");
            mniCropToBorder.setMnemonic(KeyEvent.VK_B);
            mniCropToBorder
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            applyProcessing("Just borders", new CropToBorder());
                        }
                    });
        }
        return mniCropToBorder;
    }

    private String createDescription(String name, ChooseStructuringElementDialog dlg) {
        return String.format("%s (%s %dx)", name, dlg.getStructuringElement().getDescription(), dlg.getIterations());
    }

    private JMenuItem getMniErosion() {
        if (mniErosion == null) {
            mniErosion = new JMenuItem();
            mniErosion.setText("Erosion");
            mniErosion.setMnemonic(KeyEvent.VK_E);
            mniErosion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (getSelectedFrame() == null)
                        return;

                    ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                            MainFrame.this);
                    if (!dlg.showModal())
                        return;

                    applyProcessing(createDescription("Erosion", dlg),
                            new Erosion(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                }
            });
        }
        return mniErosion;
    }

    private JMenuItem getMniDilation() {
        if (mniDilation == null) {
            mniDilation = new JMenuItem();
            mniDilation.setText("Dilation");
            mniDilation.setMnemonic(KeyEvent.VK_D);
            mniDilation.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (getSelectedFrame() == null)
                        return;

                    ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                            MainFrame.this);
                    if (!dlg.showModal())
                        return;

                    applyProcessing(createDescription("Dilation", dlg),
                            new Dilation(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                }
            });
        }
        return mniDilation;
    }

    private JMenuItem getMniOpening() {
        if (mniOpening == null) {
            mniOpening = new JMenuItem();
            mniOpening.setText("Opening");
            mniOpening.setMnemonic(KeyEvent.VK_O);
            mniOpening.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (getSelectedFrame() == null)
                        return;

                    ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                            MainFrame.this);
                    if (!dlg.showModal())
                        return;

                    applyProcessing(createDescription("Opening", dlg),
                            new Opening(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                }
            });
        }
        return mniOpening;
    }

    private JMenuItem getMniClosing() {
        if (mniClosing == null) {
            mniClosing = new JMenuItem();
            mniClosing.setText("Closing");
            mniClosing.setMnemonic(KeyEvent.VK_C);
            mniClosing.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (getSelectedFrame() == null)
                        return;

                    ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                            MainFrame.this);
                    if (!dlg.showModal())
                        return;

                    applyProcessing(createDescription("Closing", dlg),
                            new Closing(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                }
            });
        }
        return mniClosing;
    }

    private JMenuItem getMniReconstruction() {
        if (mniReconstruction == null) {
            mniReconstruction = new JMenuItem();
            mniReconstruction.setText("Reconstruction");
            mniReconstruction.setMnemonic(KeyEvent.VK_R);
            mniReconstruction
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if (getSelectedFrame() == null)
                                return;

                            BufferedImage img = chooseImage("Reconstruction", "Choose the mask image", true);
                            if (img == null)
                                return;

                            applyProcessing("Reconstruction", new Reconstruction(img));
                        }
                    });
        }
        return mniReconstruction;
    }

    private JMenu getMnuGranulometry() {
        if (mnrGranulometry == null) {
            mnrGranulometry = new JMenu();
            mnrGranulometry.setText("Granulometry");
            mnrGranulometry.setMnemonic(KeyEvent.VK_G);
            mnrGranulometry.add(getMniGranulometryErosion());
            mnrGranulometry.add(getMniGranulometryASF());
        }
        return mnrGranulometry;
    }

    private JMenuItem getMniGranulometryErosion() {
        if (mniGranulometryErosion == null) {
            mniGranulometryErosion = new JMenuItem();
            mniGranulometryErosion.setText("Erosion");
            mniGranulometryErosion.setMnemonic(KeyEvent.VK_E);
            mniGranulometryErosion
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if (getSelectedFrame() == null)
                                return;

                            ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                                    MainFrame.this);
                            if (!dlg.showModal())
                                return;

                            applyProcessing(createDescription("Granulometry by erosion", dlg),
                                    new ErosionGranulometry(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                        }
                    });
        }
        return mniGranulometryErosion;
    }

    private JMenuItem getMniGranulometryASF() {
        if (mniGranulometryASF == null) {
            mniGranulometryASF = new JMenuItem();
            mniGranulometryASF.setText("Opening and closing");
            mniGranulometryASF.setMnemonic(KeyEvent.VK_O);
            mniGranulometryASF
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if (getSelectedFrame() == null)
                                return;

                            ChooseStructuringElementDialog dlg = new ChooseStructuringElementDialog(
                                    MainFrame.this);
                            if (!dlg.showModal())
                                return;

                            applyProcessing(createDescription("Opening and closing (ASF)", dlg),
                                    new ASFGranulometry(dlg.getStructuringElement().getMatrix(), dlg.getIterations()));
                        }
                    });
        }
        return mniGranulometryASF;
    }

    private List<String> getImageNames(boolean sameSize) {
        List<String> imagens = new ArrayList<String>();
        for (JInternalFrame frame : getDskCenter().getAllFrames())
            if (frame instanceof ImagemIFrame) {
                if (sameSize) {
                    BufferedImage selected = getSelectedFrame().getImage();
                    BufferedImage other = ((ImagemIFrame) frame).getImage();
                    if (other.getWidth() != selected.getWidth()
                            || other.getHeight() != selected.getHeight())
                        continue;
                }
                imagens.add(frame.getTitle());
            }
        return imagens;
    }

    private BufferedImage chooseImage(String title, String text,
                                      boolean sameSize) {
        List<String> imagens = getImageNames(sameSize);
        imagens.remove(getSelectedFrame().getTitle());

        if (imagens.size() == 0)
            return null;

        String imgName = (String) JOptionPane.showInputDialog(MainFrame.this,
                text, title, JOptionPane.QUESTION_MESSAGE, null,
                imagens.toArray(), imagens.get(0));

        if (imgName == null)
            return null;

        BufferedImage img = null;
        for (JInternalFrame frame : getDskCenter().getAllFrames())
            if (frame instanceof ImagemIFrame
                    && frame.getTitle().equals(imgName))
                img = ((ImagemIFrame) frame).getImage();
        return img;
    }

    /**
     * This method initializes mnuHelp
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuHelp() {
        if (mnuHelp == null) {
            mnuHelp = new JMenu();
            mnuHelp.setText("Help");
            mnuHelp.setMnemonic(KeyEvent.VK_H);
            mnuHelp.add(getMniAbout());
        }
        return mnuHelp;
    }

    /**
     * This method initializes mniOpen
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniOpen() {
        if (mniOpen == null) {
            mniOpen = new JMenuItem();
            mniOpen.setText("Open...");
            mniOpen.setMnemonic(KeyEvent.VK_O);
            mniOpen.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    onOpen();
                }
            });
        }
        return mniOpen;
    }

    public JFileChooser getOpenDialog() {
        if (openDialog == null) {
            openDialog = new JFileChooser();
            openDialog.setAcceptAllFileFilterUsed(false);
            openDialog.setFileFilter(new FileNameExtensionFilter("Image files",
                    SUPPORTED_FORMATS));
        }
        return openDialog;
    }

    protected void onOpen() {
        if (getOpenDialog().showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return;

        openFile(getOpenDialog().getSelectedFile(), true);
    }

    public JFileChooser getSaveDialog() {
        if (saveDialog == null) {
            saveDialog = new JFileChooser();
            saveDialog.setAcceptAllFileFilterUsed(false);
            saveDialog.setFileFilter(new FileNameExtensionFilter("Image files",
                    SUPPORTED_FORMATS));
        }
        return saveDialog;
    }

    private ImagemIFrame getSelectedFrame() {
        return (ImagemIFrame) getDskCenter().getSelectedFrame();
    }

    private void onSaveAs() {
        ImagemIFrame frame = getSelectedFrame();

        if (frame == null)
            return;

        if (frame.getFile() != null)
            getSaveDialog().setSelectedFile(frame.getFile());

        if (getSaveDialog().showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
            return;

        try {
            String fileName = getSaveDialog().getSelectedFile().getName();
            String format;
            int index = fileName.lastIndexOf(".");
            if (index == -1) {
                fileName = fileName + ".png";
                format = "png";
            } else {
                format = fileName.substring(index + 1);
            }

            File file = new File(getSaveDialog().getSelectedFile()
                    .getParentFile(), fileName);
            ImageIO.write(frame.getImage(), format, file);
            frame.setFile(file);
            setTitle(frame);
        } catch (IOException e) {
            showError("Could not save file!");
        }

    }

    private void openFile(File file, boolean showError) {
        try {
            BufferedImage img = loader.load(file);
            openWindow("", file, img);
        } catch (Exception e) {
            if (showError)
                showError("Could not open the selected file.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void setTitle(JInternalFrame frame) {
        setTitle("Image editor - " + frame.getTitle());
    }

    private void openWindow(String name, File selectedFile, BufferedImage img) {
        Point p = getSelectedFrame() == null ? new Point(0, 0)
                : getSelectedFrame().getLocation();
        ImagemIFrame frame = new ImagemIFrame(name, selectedFile, img);
        frame.setLocation(p.x + 20, p.y + 20);
        frame.setVisible(true);
        getDskCenter().add(frame);
        frame.moveToFront();
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        setTitle(frame);

        frame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                getDskCenter().remove(e.getInternalFrame());
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                setTitle(e.getInternalFrame());
            }
        });
    }

    /**
     * This method initializes mniSaveAs
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSaveAs() {
        if (mniSaveAs == null) {
            mniSaveAs = new JMenuItem();
            mniSaveAs.setText("Save as...");
            mniSaveAs.setMnemonic(KeyEvent.VK_A);
            mniSaveAs.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    onSaveAs();
                }
            });
        }
        return mniSaveAs;
    }

    /**
     * This method initializes mniAbout
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniAbout() {
        if (mniAbout == null) {
            mniAbout = new JMenuItem();
            mniAbout.setText("About...");
            mniAbout.setMnemonic(KeyEvent.VK_A);
            mniAbout.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new AboutDialog(MainFrame.this).setVisible(true);
                }
            });
        }
        return mniAbout;
    }

    /**
     * This method initializes mniExit
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniExit() {
        if (mniExit == null) {
            mniExit = new JMenuItem();
            mniExit.setText("Exit");
            mniExit.setMnemonic(KeyEvent.VK_R);
            mniExit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    onClose();
                }
            });
        }
        return mniExit;
    }

    protected void onClose() {
        if (JOptionPane
                .showConfirmDialog(
                        this,
                        "<html><body>Unsaved data can be lost<br>Are you sure?",
                        "Close software", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_NO_OPTION)
            return;

        System.exit(0);
    }

    public static boolean isImage(String fileName) {
        for (String format : SUPPORTED_FORMATS)
            if (fileName.endsWith("." + format))
                return true;
        return false;
    }

    /**
     * This method initializes mnuSmoothing
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuSmoothing() {
        if (mnuSmoothing == null) {
            mnuSmoothing = new JMenu();
            mnuSmoothing.setText("Noise smoothing");
            mnuSmoothing.add(getMnuAverage());
            mnuSmoothing.add(getMniMean());
        }
        return mnuSmoothing;
    }

    /**
     * This method initializes mnuBorders
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuBorders() {
        if (mnuBorders == null) {
            mnuBorders = new JMenu();
            mnuBorders.setText("Edge detection");
            mnuBorders.add(getMnuPrewitt());
            mnuBorders.add(getMnuSobel1());
            mnuBorders.add(getMnuSobel2());
            mnuBorders.add(getMniLaplace());
        }
        return mnuBorders;
    }

    /**
     * This method initializes mnuAverage
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuAverage() {
        if (mnuAverage == null) {
            mnuAverage = new JMenu();
            mnuAverage.setText("Linear filtering");
            mnuAverage.add(getMniF1());
            mnuAverage.add(getMniF2());
            mnuAverage.add(getMniF3());
            mnuAverage.add(getMniF4());
        }
        return mnuAverage;
    }

    /**
     * This method initializes mniF1
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniF1() {
        if (mniF1 == null) {
            mniF1 = new JMenuItem();
            mniF1.setText("F1");
            mniF1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Linear filter - F1", LinearFilter.createF(1, channels));
                }
            });
        }
        return mniF1;
    }

    protected void applyProcessing(String name, Processing filter) {
        if (getSelectedFrame() == null)
            return;
        openWindow(name, null, new FilterProgressDialog(this).showModal(filter,
                getSelectedFrame().getImage()));
    }

    /**
     * This method initializes mniF2
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniF2() {
        if (mniF2 == null) {
            mniF2 = new JMenuItem();
            mniF2.setText("F2");
            mniF2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Linear filter - F2", LinearFilter.createF(2, channels));
                }
            });
        }
        return mniF2;
    }

    /**
     * This method initializes mniF3
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniF3() {
        if (mniF3 == null) {
            mniF3 = new JMenuItem();
            mniF3.setText("F3");
            mniF3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Linear filter - F3", LinearFilter.createF(3, channels));
                }
            });
        }
        return mniF3;
    }

    /**
     * This method initializes mniF4
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniF4() {
        if (mniF4 == null) {
            mniF4 = new JMenuItem();
            mniF4.setText("F4");
            mniF4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Linear filter - F4", LinearFilter.createF(4, channels));
                }
            });
        }
        return mniF4;
    }

    /**
     * This method initializes mnuPrewitt
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuPrewitt() {
        if (mnuPrewitt == null) {
            mnuPrewitt = new JMenu();
            mnuPrewitt.setText("Prewitt");
            mnuPrewitt.add(getMniPrewittG());
            mnuPrewitt.add(getMniPrewittGx());
            mnuPrewitt.add(getMniPrewittGy());
        }
        return mnuPrewitt;
    }

    /**
     * This method initializes mniPrewittGx
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniPrewittGx() {
        if (mniPrewittGx == null) {
            mniPrewittGx = new JMenuItem();
            mniPrewittGx.setText("Gx");
            mniPrewittGx.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Prewitt filter - Gx", new PrewittFilter(
                            PrewittFilter.Operator.Gx, channels));
                }
            });
        }
        return mniPrewittGx;
    }

    /**
     * This method initializes mniPrewittG
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniPrewittG() {
        if (mniPrewittG == null) {
            mniPrewittG = new JMenuItem();
            mniPrewittG.setText("G");
            mniPrewittG.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Prewitt filter - G", new PrewittFilter(PrewittFilter.Operator.G,
                            channels));
                }
            });
        }
        return mniPrewittG;
    }

    /**
     * This method initializes mniPrewittGy
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniPrewittGy() {
        if (mniPrewittGy == null) {
            mniPrewittGy = new JMenuItem();
            mniPrewittGy.setText("Gy");
            mniPrewittGy.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Prewitt filter - Gy", new PrewittFilter(
                            PrewittFilter.Operator.Gy, channels));
                }
            });
        }
        return mniPrewittGy;
    }

    /**
     * This method initializes mnuSobel1
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuSobel1() {
        if (mnuSobel1 == null) {
            mnuSobel1 = new JMenu();
            mnuSobel1.setText("Sobel 1");
            mnuSobel1.add(getMniSobelG1());
            mnuSobel1.add(getMniSobelG1x());
            mnuSobel1.add(getMniSobelG1y());
        }
        return mnuSobel1;
    }

    /**
     * This method initializes mniSobelG1x
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG1x() {
        if (mniSobelG1x == null) {
            mniSobelG1x = new JMenuItem();
            mniSobelG1x.setText("Gx");
            mniSobelG1x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter - Gx", new SobelFilter(SobelFilter.Operator1.Gx,
                            channels));
                }
            });
        }
        return mniSobelG1x;
    }

    /**
     * This method initializes mniSobelG1y
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG1y() {
        if (mniSobelG1y == null) {
            mniSobelG1y = new JMenuItem();
            mniSobelG1y.setText("Gy");
            mniSobelG1y.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter - Gy", new SobelFilter(SobelFilter.Operator1.Gy,
                            channels));
                }
            });
        }
        return mniSobelG1y;
    }

    /**
     * This method initializes mniSobelG1
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG1() {
        if (mniSobelG1 == null) {
            mniSobelG1 = new JMenuItem();
            mniSobelG1.setText("G");
            mniSobelG1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter - G", new SobelFilter(SobelFilter.Operator1.G,
                            channels));
                }
            });
        }
        return mniSobelG1;
    }

    /**
     * This method initializes mnuSobel2
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuSobel2() {
        if (mnuSobel2 == null) {
            mnuSobel2 = new JMenu();
            mnuSobel2.setText("Sobel 2");
            mnuSobel2.add(getMniSobelG2());
            mnuSobel2.add(getMniSobelG2x());
            mnuSobel2.add(getMniSobelG2y());
        }
        return mnuSobel2;
    }

    /**
     * This method initializes mniSobelG2
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG2() {
        if (mniSobelG2 == null) {
            mniSobelG2 = new JMenuItem();
            mniSobelG2.setText("G");
            mniSobelG2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter (operator 2) - G",
                            new SobelFilter(SobelFilter.Operator2.G, channels));
                }
            });
        }
        return mniSobelG2;
    }

    /**
     * This method initializes mniSobelG2x
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG2x() {
        if (mniSobelG2x == null) {
            mniSobelG2x = new JMenuItem();
            mniSobelG2x.setText("Gx");
            mniSobelG2x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter (operator 2) - Gx",
                            new SobelFilter(SobelFilter.Operator2.Gx, channels));
                }
            });
        }
        return mniSobelG2x;
    }

    /**
     * This method initializes mniSobelG2y
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniSobelG2y() {
        if (mniSobelG2y == null) {
            mniSobelG2y = new JMenuItem();
            mniSobelG2y.setText("Gy");
            mniSobelG2y.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Sobel filter (operator 2) - Gy",
                            new SobelFilter(SobelFilter.Operator2.Gy, channels));
                }
            });
        }
        return mniSobelG2y;
    }

    /**
     * This method initializes mniMean
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniMean() {
        if (mniMean == null) {
            mniMean = new JMenuItem();
            mniMean.setText("Median");
            mniMean.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ChooseKernelSizeDialog dlg = new ChooseKernelSizeDialog(MainFrame.this);
                    if (!dlg.showModal())
                        return;
                    applyProcessing("Median filter",
                            new MeanFilter(dlg.getColumns(), dlg.getLines(), channels));
                }

            });
        }
        return mniMean;
    }

    /**
     * This method initializes mniLaplace
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniLaplace() {
        if (mniLaplace == null) {
            mniLaplace = new JMenuItem();
            mniLaplace.setText("Laplace");
            mniLaplace.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    applyProcessing("Laplace Filter", LinearFilter.createLaplace(channels));
                }
            });
        }
        return mniLaplace;
    }

    /**
     * This method initializes mnuChannels
     *
     * @return javax.swing.JMenu
     */
    private JMenu getMnuChannels() {
        if (mnuChannels == null) {
            mnuChannels = new JMenu();
            mnuChannels.setText("Channels");
            mnuChannels.add(getRadRGB());
            mnuChannels.add(getRadGrayscale());
        }
        return mnuChannels;
    }

    /**
     * This method initializes radRGB
     *
     * @return javax.swing.JRadioButtonMenuItem
     */
    private JRadioButtonMenuItem getRadRGB() {
        if (radRGB == null) {
            radRGB = new JRadioButtonMenuItem();
            radRGB.setText("RGB");
            radRGB.setSelected(true);
            radRGB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (radRGB.isSelected())
                        channels = ARGBChannels.colors();
                }
            });
            bgChannels.add(radRGB);
        }
        return radRGB;
    }

    /**
     * This method initializes radGrayscale
     *
     * @return javax.swing.JRadioButtonMenuItem
     */
    private JRadioButtonMenuItem getRadGrayscale() {
        if (radGrayscale == null) {
            radGrayscale = new JRadioButtonMenuItem();
            radGrayscale.setText("Grayscale");
            radGrayscale.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (radGrayscale.isSelected())
                        channels = GrayScaleChannel.values();
                }
            });
            bgChannels.add(radGrayscale);
        }
        return radGrayscale;
    }

    /**
     * This method initializes mniHistogramRedistribution
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getMniHistogramRedistribution() {
        if (mniHistogramRedistribution == null) {
            mniHistogramRedistribution = new JMenuItem();
            mniHistogramRedistribution.setText("Equalization");
            mniHistogramRedistribution.setMnemonic(KeyEvent.VK_E);
            mniHistogramRedistribution
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            applyProcessing("Equalization", new HistogramRedistributionFilter(channels));
                        }
                    });
        }
        return mniHistogramRedistribution;
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                for (String arg : args) {
                    if (!isImage(arg))
                        continue;

                    File f = new File(arg);
                    if (f.exists() && f.isFile())
                        mainFrame.openFile(f, false);
                }
                mainFrame.setVisible(true);
            }
        });
    }

    /**
     * This is the default constructor
     */
    public MainFrame() {
        super();
        initialize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setJMenuBar(getMbMenu());
        this.setContentPane(getJContentPane());
        this.setTitle("Image Editor");
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onClose();
            }
        });
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
            jContentPane.add(getDskCenter(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

} // @jve:decl-index=0:visual-constraint="10,10"
