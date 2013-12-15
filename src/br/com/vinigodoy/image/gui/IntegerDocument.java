/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

@SuppressWarnings("serial")
public class IntegerDocument extends FixedLengthDocument {
    public IntegerDocument(int maxlen) {
        super(maxlen);
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null)
            return;

        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return;
        }

        super.insertString(offset, str, attr);
    }
}