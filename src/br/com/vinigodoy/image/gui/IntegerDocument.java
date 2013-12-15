package br.com.vinigodoy.image.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

@SuppressWarnings("serial")
public class IntegerDocument extends FixedLengthDocument {
    public IntegerDocument(int maxlen) {
        super(maxlen);
    }

    @Override
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