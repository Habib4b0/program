
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentSearchLookUp;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.PopupDateField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class RemoveContractSearch extends AbstractContractSearch {

    
    private SelectionDTO selectionDTO = new SelectionDTO();
    private final StplSecurity stplSec = new StplSecurity();
    public static final Logger LOGGER = LoggerFactory.getLogger(RemoveContractSearch.class);

    public RemoveContractSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selectionDTO = selection;
            configureFields();
            configureSecurityPermissions();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }

    }

    @UiHandler("search")
    public void searchBtnLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
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
            selectionDTO.setCountQueryName("Item Load contract Count");
            selectionDTO.setDataQueryName("Load contract Item");
            searchButtonLogic(true);
        }
    }

    private void configureFields() {
        getContent();
        massUpdatePanelOne.setVisible(BooleanConstant.getFalseFlag());
        allItems.addItem("Yes");
        allItems.select("Yes");
        allItems.setEnabled(BooleanConstant.getFalseFlag());
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

    @Override
    public void createFieldFactory() {
        getContractSelectionTable().setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setImmediate(true);
                    if (!mainDto.getWorkFlowStatus().trim().isEmpty()) {
                        check.setVisible(false);
                    } else {
                        check.setVisible(true);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            @Override
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
                if (propertyId.equals("itemEndDate")) {
                    final PopupDateField itemendDate = new PopupDateField();
                    itemendDate.setImmediate(true);
                    itemendDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
                    itemendDate.addStyleName(ConstantsUtil.ALIGN_CENTER);
                    itemendDate.addFocusListener(new com.vaadin.event.FieldEvents.FocusListener() {

                        @Override
                        public void focus(com.vaadin.event.FieldEvents.FocusEvent event) {
                            Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setCaseNo(0);
                                    Date startDate = getLogic().getStartDateCheck(dto, selectionDTO, "START_DATE");
                                    if (startDate != null && itemendDate.getValue() != null && itemendDate.getValue().before(startDate)) {
                                        itemendDate.setValue(null);
                                        MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen.", ButtonId.OK);
                                    } else {
                                        dto.setEndDate(itemendDate.getValue());
                                        dto.setColumnName("END_DATE");
                                        dto.setCaseNo(NumericConstants.TWO);
                                        saveTempItemDetails(dto);
                                    }
                                }
                            };

                            itemendDate.addValueChangeListener(valueChangeListner);
                            valueChangeListner.valueChange(null);
                            itemendDate.removeFocusListener(this);
                        }
                    });

                    return itemendDate;
                }
                return null;
            }
        });

    }
    
    private void saveTempItemDetails(final AbstractContractSearchDTO dto) {
        getLogic().getEditedItemDetails(dto, selectionDTO);
    }

    @Override
    public Boolean submitButtonCheck() {
        return BooleanConstant.getTrueFlag();
    }
     private void configureSecurityPermissions() {
        try {
           Map<String, AppPermission> functionHM = stplSec.getBusinessFunctionPermission(String.valueOf(selectionDTO.getUserId()), "GCM-Item Management", "Item Remove", "Contract Selection Tab");
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
