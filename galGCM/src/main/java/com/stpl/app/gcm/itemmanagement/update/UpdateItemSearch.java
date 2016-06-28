
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.update;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentSearchLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.Constants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import org.asi.ui.customtextfield.CustomTextField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class UpdateItemSearch extends AbstractContractSearch {

    SelectionDTO selection = new SelectionDTO();

    public UpdateItemSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selection = selection;
            configureFields();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    @UiHandler("search")
    public void searchBtnLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        binder.commit();
        checkrecord();
        if ((binderDto.getContractHolder() == null || binderDto.getContractHolder().isEmpty()) && (binderDto.getMarketType_DTO() == null)
                && (binderDto.getCfpNO() == null || binderDto.getCfpNO().isEmpty()) && (binderDto.getContractNo() == null || binderDto.getContractNo().isEmpty())
                && (binderDto.getStartDate() == null) && (binderDto.getEndDate() == null)
                && (binderDto.getIfpNo() == null || binderDto.getIfpNo().isEmpty())
                && (binderDto.getContractName() == null || binderDto.getContractName().isEmpty()) && (binderDto.getPsNo() == null || binderDto.getPsNo().isEmpty())
                && (binderDto.getRebateScheduleId() == null || binderDto.getRebateScheduleId().isEmpty()) && (binderDto.getRebateScheduleName() == null || binderDto.getRebateScheduleName().isEmpty())
                && (binderDto.getRebateScheduleNo() == null || binderDto.getRebateScheduleNo().isEmpty())
                && (binderDto.getRebateProgramType_DTO() == null) && (binderDto.getRebateScheduleAlias() == null || binderDto.getRebateScheduleAlias().isEmpty())
                && (binderDto.getRebateScheduleCategory_DTO() == null) && (binderDto.getRebateScheduleType_DTO() == null)) {

            MessageBox.showPlain(Icon.INFO, "Error", "Please enter/select search criteria", ButtonId.OK);
        } else {
            selection.setCountQueryName("Item Load contract Count");
            selection.setDataQueryName("Load contract Item");
            searchButtonLogic();
        }
    }

    public Boolean checkrecord() {
        List input = AbstractLogic.getResultsInput(selection);
        String queryname = "checkrecord condition check";
        Boolean isUpdated = ItemQueries.itemUpdate(input, queryname);
        return isUpdated;

    }

    private void configureFields() throws Exception {

        getContent();
        getMassUpdate();
        ConfigureTable();
        getBinder();
        loadAllDdlb();
        allItems.setEnabled(Boolean.FALSE);

    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("companyFamilyPlanNo")
    public void CFP(CustomTextField.ClickEvent event) {
        ComponentSearchLookUp cfp = new ComponentSearchLookUp(Constants.CFP, cfpNO);
        cfp.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (cfpNO.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) cfpNO.getData();
                    cfpNO.setValue(object.getComponentNo());
                }
            }
        });

        UI.getCurrent().addWindow(cfp);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("itemFamilyPlanNo")
    public void IFP(CustomTextField.ClickEvent event) {
        ComponentSearchLookUp ifp = new ComponentSearchLookUp(Constants.IFP, ifpNo);
        ifp.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (ifpNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) ifpNo.getData();
                    ifpNo.setValue(object.getComponentNo());
                }
            }
        });

        UI.getCurrent().addWindow(ifp);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("priceScheduleNo")
    public void PS(CustomTextField.ClickEvent event) {
        ComponentSearchLookUp ps = new ComponentSearchLookUp(Constants.PS, psNo);
        ps.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (psNo.getData() != null) {
                    ComponentLookUpDTO object = (ComponentLookUpDTO) psNo.getData();
                    psNo.setValue(object.getComponentNo());
                }
            }
        });

        UI.getCurrent().addWindow(ps);
    }

}
