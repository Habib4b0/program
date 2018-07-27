/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.view;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.ui.form.CopyContractform;
import com.stpl.app.gcm.discount.ui.layout.CopyContractWindow;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.Collections;
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

    private final CopyContractform addDiscountForm;

    /**
     * Default constructor.
     *
     * @param session
     * @param resultTable
     * @param dataSelectionDTO
     * @param editWindow
     * @throws java.lang.Exception
     */
    public CopyContractView(final CopyContractWindow addWindow, final SessionDTO session, List<ContractSelectionDTO> selectedList, String Count) {
        addDiscountForm = new CopyContractform(addWindow, selectedList, Count);
        addComponent(addDiscountForm);
    }

}
