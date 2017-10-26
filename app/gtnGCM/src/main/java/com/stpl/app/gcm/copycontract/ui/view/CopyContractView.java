/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.view;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.ui.form.CopyContractform;
import com.stpl.app.gcm.discount.ui.layout.CopyContractWindow;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractView extends VerticalLayout {

    public static final String NAME = "GTNBALANCE";

    /**
     * Binder for DataSelection.
     */
    CopyContractWindow addWindow;

    CopyContractform addDiscountForm;
    SessionDTO session;
    List<ContractSelectionDTO> selectedList;

    /**
     * Default constructor.
     *
     * @param session
     * @param resultTable
     * @param dataSelectionDTO
     * @param editWindow
     * @throws java.lang.Exception
     */
    public CopyContractView(final CopyContractWindow addWindow, final SessionDTO session, List<ContractSelectionDTO> selectedList, String Count) throws SystemException {
        this.addWindow = addWindow;
        this.session = session;
        this.selectedList = selectedList;
        addDiscountForm = new CopyContractform(this.addWindow, selectedList, Count);
        addComponent(addDiscountForm);
        enter();
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter() {
    }

}
