
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentSearchLookUp;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class RemoveContractSearch extends AbstractContractSearch {

    SelectionDTO selection = new SelectionDTO();
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<>();
    public static final Logger LOGGER = Logger.getLogger(RemoveContractSearch.class);

    public RemoveContractSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selection = selection;
            configureFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @UiHandler("search")
    public void searchBtnLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
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

    private void configureFields() {
        getContent();
        MassUpdatePanel1.setVisible(Boolean.FALSE);
        allItems.addItem("Yes");
        allItems.select("Yes");
        allItems.setEnabled(Boolean.FALSE);
        ConfigureTable();
        getBinder();
        loadAllDdlb();
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

    public void createFieldFactory() {
        contractSelectionTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    if (!mainDto.getWorkFlowStatus().trim().isEmpty()) {
                        check.setVisible(false);
                    } else {
                        check.setVisible(true);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                if (itemId instanceof AbstractContractSearchDTO) {
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setCheckRecord(check.getValue());
                                    dto.setColumnName("CHECK_RECORD");
                                    dto.setCaseNo(NumericConstants.SEVENTEEN);
                                    saveTempItemDetails(dto);
                                }

                            }
                        });
                    }
                    return check;
                }

                return null;
            }
        });

    }

    private void saveTempItemDetails(final AbstractContractSearchDTO dto) {
        logic.getEditedItemDetails(dto, selection);
    }

    public Boolean submitButtonCheck() {
        return true;
    }
     private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), "GCM-Item Management", "Item Remove", "Contract Selection Tab");
           getSearchBtn().setVisible(CommonLogic.isButtonVisibleAccess("search", functionHM));
            getResetBtn().setVisible(CommonLogic.isButtonVisibleAccess("reset1", functionHM));
            getPopulateBtn().setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            getResetBtncur().setVisible(CommonLogic.isButtonVisibleAccess("reset2", functionHM));
            getSubmit().setVisible(CommonLogic.isButtonVisibleAccess("submit", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
