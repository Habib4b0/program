/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.view;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.ui.form.AddDiscountForm;
import com.stpl.app.gcm.discount.ui.layout.AddDiscountWindow;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public class AddDiscountAddView extends VerticalLayout {

    /**
     * View name for navigation.
     */
    public static final String NAME = "GTNBALANCE";

    /**
     * Default constructor.
     *
     * @param addWindow
     * @param session
     * @param removeList
     */
    public AddDiscountAddView(final AddDiscountWindow addWindow, final SessionDTO session, List<RemoveDiscountDto> removeList)  {
        AddDiscountForm addDiscountForm = new AddDiscountForm(addWindow, removeList, session);
        addComponent(addDiscountForm);
    }


}
