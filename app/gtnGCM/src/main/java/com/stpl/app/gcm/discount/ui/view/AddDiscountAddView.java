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
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.VerticalLayout;
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
     * Binder for DataSelection.
     */
    private final AddDiscountWindow addWindow;

    private final AddDiscountForm addDiscountForm;
    private final SessionDTO session;

    /**
     * Default constructor.
     *
     * @param session
     * @param resultTable
     * @param dataSelectionDTO
     * @param editWindow
     * @throws java.lang.Exception
     */
    public AddDiscountAddView(final AddDiscountWindow addWindow, final SessionDTO session, List<RemoveDiscountDto> removeList) throws SystemException {
        this.addWindow = addWindow;
        this.session = session;
        addDiscountForm = new AddDiscountForm(this.addWindow, removeList, this.session);
        addComponent(addDiscountForm);
    }


}
