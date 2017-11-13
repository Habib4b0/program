package org.asi.ui.customdatefield.client;

import com.vaadin.client.ui.datefield.PopupDateFieldConnector;
import com.vaadin.shared.ui.Connect;
import org.asi.ui.customdatefield.CustomDateField;

@Connect(CustomDateField.class)
public class CustomDateFieldConnector extends PopupDateFieldConnector{
    @Override
    public VCustomDateField getWidget() {
        return (VCustomDateField) super.getWidget();
    }
}
