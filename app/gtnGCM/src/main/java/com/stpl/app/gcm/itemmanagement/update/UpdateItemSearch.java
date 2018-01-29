
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.update;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentSearchLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class UpdateItemSearch extends AbstractContractSearch {

    private SelectionDTO selectionDto = new SelectionDTO();

    public UpdateItemSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selectionDto = selection;
            configureFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }

    }

    @UiHandler("search")
    public void searchBtnLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        checkrecord();
        if ((getBinderDto().getContractHolder() == null || getBinderDto().getContractHolder().isEmpty()) && (getBinderDto().getMarketType_DTO() == null)
                && (getBinderDto().getCfpNO() == null || getBinderDto().getCfpNO().isEmpty()) && (getBinderDto().getContractNo() == null || getBinderDto().getContractNo().isEmpty())
                && (getBinderDto().getStartDate() == null) && (getBinderDto().getEndDate() == null)
                && (getBinderDto().getIfpNo() == null || getBinderDto().getIfpNo().isEmpty())
                && (getBinderDto().getContractName() == null || getBinderDto().getContractName().isEmpty()) && (getBinderDto().getPsNo() == null || getBinderDto().getPsNo().isEmpty())
                && (getBinderDto().getRebateScheduleId() == null || getBinderDto().getRebateScheduleId().isEmpty()) && (getBinderDto().getRebateScheduleName() == null || getBinderDto().getRebateScheduleName().isEmpty())
                && (getBinderDto().getRebateScheduleNo() == null || getBinderDto().getRebateScheduleNo().isEmpty())
                && (getBinderDto().getRebateProgramType_DTO() == null) && (getBinderDto().getRebateScheduleAlias() == null || getBinderDto().getRebateScheduleAlias().isEmpty())
                && (getBinderDto().getRebateScheduleCategory_DTO() == null) && (getBinderDto().getRebateScheduleType_DTO() == null)) {

            MessageBox.showPlain(Icon.INFO, "Error", "Please enter/select search criteria", ButtonId.OK);
        } else {
            selectionDto.setCountQueryName("Item Load contract Count");
            selectionDto.setDataQueryName("Load contract Item");
            searchButtonLogic(true);
        }
    }

    public Boolean checkrecord() {
        List input = AbstractLogic.getResultsInput(selectionDto);
        String queryname = "checkrecord condition check";
        Boolean isUpdated = ItemQueries.itemUpdate(input, queryname);
        return isUpdated;

    }

    private void configureFields() {

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

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selectionDto.getUserId()), "GCM-Item Management", "Item Update", "Contract Selection Tab");
            getSearchBtn().setVisible(CommonLogic.isButtonVisibleAccess("search", functionHM));
            getResetBtn().setVisible(CommonLogic.isButtonVisibleAccess("reset1", functionHM));
            getPopulateBtn().setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            getResetBtncur().setVisible(CommonLogic.isButtonVisibleAccess("reset2", functionHM));
            getSubmit().setVisible(CommonLogic.isButtonVisibleAccess("submit", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}
