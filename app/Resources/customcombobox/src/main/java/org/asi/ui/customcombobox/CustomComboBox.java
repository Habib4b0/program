package org.asi.ui.customcombobox;

import com.vaadin.v7.data.Container;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.v7.ui.ComboBox;
import java.util.Collection;

// This is the server-side UI component that provides public API 
// for CustomComboBox
public class CustomComboBox extends ComboBox {
    private boolean addBlurValue=false;
    public CustomComboBox() {
        super();
    }

    public CustomComboBox(String caption, Collection<?> options) {
        super(caption, options);
    }

    public CustomComboBox(String caption, Container dataSource) {
        super(caption, dataSource);
    }

    public CustomComboBox(String caption) {
        super(caption);
    }
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
            if (isAddBlurValue()) {
                target.addAttribute("addblurvalue", true);
            }
            super.paintContent(target);
        }

    public boolean isAddBlurValue() {
        return addBlurValue;
    }

    public void addBlurValue(boolean addBlurValue) {
        this.addBlurValue = addBlurValue;
    }
    
}
