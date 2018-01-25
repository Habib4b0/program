/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch;
import static com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch.LOGGER;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentSearchLookUp;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class CurrentContractContractSearch extends AbstractContractSearch {

    @UiField("exportLayout")
    protected HorizontalLayout exportLayout;
    private SelectionDTO selectionDto;
    private Object valueChange;
    private Label Label = new Label("Transfer Sales Projection :");
    private OptionGroup transferSales = new OptionGroup();
    private CheckBox removeProjection = new CheckBox("Remove Projection Details");
    private Object[] visibleColumn = {ConstantsUtil.CHECK_RECORD, Constants.PROJ_ID_LINK, Constants.WORKFLOW_STATUS, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, 
        Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, ConstantsUtil.STATUS, ConstantsUtil.ITEM_START_DATE, 
        ConstantsUtil.ITEM_END_DATE, Constants.CFP_NO, Constants.CFP_NAME, Constants.IFP_NO_COLUMN, Constants.IFPNAME, Constants.PS_NO_COLUMN, Constants.PSNAME, Constants.RS_NO_COLUMN, Constants.RSNAME, Constants.RAR_CATEGORY_COLUMN};
    
    private String[] header = {StringUtils.EMPTY, Constants.PROJECTION_ID_HEADER, Constants.WORK_FLOW_STATUS_HEADER, Constants.CONTRACT_HOLDER_HEADER, Constants.CONTRACT_NO_HEADER, Constants.CONTRACT_NAME_HEADER, Constants.MARKET_TYPE_HEADER, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, Constants.CFP_NO_HEADER, Constants.CFP_NAME_HEADER, Constants.IFP_NO, 
        Constants.IFP_NAME_LABEL, Constants.PS_NO,  Constants.PS_NAME_LABEL, Constants.RS_NO_HEADER, Constants.RS_NAME_LABEL, Constants.RAR_CATEGORY_HEADER};
    private Object[] visibleColumnPt = {ConstantsUtil.CHECK_RECORD, Constants.PROJ_ID_LINK, Constants.WORKFLOW_STATUS, Constants.CONTRACT_HOLDER,
        Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, ConstantsUtil.ITEM_START_DATE,
        ConstantsUtil.ITEM_END_DATE, Constants.CFP_NO, Constants.CFP_NAME, Constants.IFP_NO_COLUMN, Constants.IFPNAME, Constants.PS_NO_COLUMN, Constants.PSNAME, Constants.RS_NO_COLUMN, Constants.RSNAME, Constants.RAR_CATEGORY_COLUMN};
    private String[] headerPt = {StringUtils.EMPTY, Constants.PROJECTION_ID_HEADER, Constants.WORK_FLOW_STATUS_HEADER, Constants.CONTRACT_HOLDER_HEADER, Constants.CONTRACT_NO_HEADER, 
        Constants.CONTRACT_NAME_HEADER, Constants.MARKET_TYPE_HEADER, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, Constants.CFP_NO_HEADER, Constants.CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, Constants.PS_NO, Constants.PS_NAME_LABEL, Constants.RS_NO_HEADER, Constants.RS_NAME_LABEL, Constants.RAR_CATEGORY_HEADER};
   
    public CurrentContractContractSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selectionDto = selection;
            configureFields();
            configureSecurityPermissions();
            setTabOperation(ConstantsUtil.CURRENT_COONTRACT);
        } catch (Exception ex) {
            LOGGER.error(ex);

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
            if (selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                selectionDto.setCountQueryName("ProjectionTransferItemCountQuery");
                selectionDto.setDataQueryName("ProjectionTransferItemDataQuery");
            } else {
                selectionDto.setCountQueryName("Item Load contract Count");
                selectionDto.setDataQueryName("Load contract Item");
            }

            searchButtonLogic(true);
        }
    }

    private void configureFields() {
        getContent();
        MassUpdatePanel1.setVisible(Boolean.TRUE);
        allItems.setEnabled(Boolean.FALSE);
        ConfigureTable();
        getBinder();
        loadAllDdlb();
        addTransferSales();
    }

    @Override
    public void createFieldFactory() {
        getContractSelectionTable().setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
                final AbstractLogic abstractLogic = AbstractLogic.getInstance();
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
                    itemendDate.addFocusListener(new FieldEvents.FocusListener() {

                        @Override
                        public void focus(FieldEvents.FocusEvent event) {
                            Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setCaseNo(0);
                                    Date startDate = abstractLogic.getStartDateCheck(dto, selectionDto, "START_DATE");
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

    private void saveTempItemDetails(final AbstractContractSearchDTO dto) {
        getLogic().getEditedItemDetails(dto, selectionDto);
    }

    @Override
    public List getInput() {
        List input = new ArrayList();
        if (selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(AbstractLogic.getItemIds(getSelectedItemList()));
        }
        input.add(selectionDto.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        if (selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(selectionDto.getButtonMode());
        }

        if (getBinderDto().getContractNo() != null && !getBinderDto().getContractNo().isEmpty()) {
            input.add(getBinderDto().getContractNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (getBinderDto().getContractName() != null && !getBinderDto().getContractName().isEmpty()) {
            input.add(getBinderDto().getContractName().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (getBinderDto().getContractHolder() != null && !getBinderDto().getContractHolder().isEmpty()) {
            input.add(getBinderDto().getContractHolder().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (getBinderDto().getMarketType_DTO() != null) {
            input.add(getBinderDto().getMarketType_DTO().getId());
        } else {
            input.add("%");
        }
        if (getBinderDto().getCfpNO() != null && !getBinderDto().getCfpNO().isEmpty()) {
            input.add(getBinderDto().getCfpNO().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (getBinderDto().getIfpNo() != null && !getBinderDto().getIfpNo().isEmpty()) {
            input.add(getBinderDto().getIfpNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (getBinderDto().getPsNo() != null && !getBinderDto().getPsNo().isEmpty()) {
            input.add(getBinderDto().getPsNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleId() != null && !getBinderDto().getRebateScheduleId().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleId().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleName() != null && !getBinderDto().getRebateScheduleName().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (getBinderDto().getRebateScheduleType_DTO() != null) {
            input.add(getBinderDto().getRebateScheduleType_DTO().getId());
        } else {
            input.add("%");
        }
        if (getBinderDto().getRebateScheduleCategory_DTO() != null) {
            input.add(getBinderDto().getRebateScheduleCategory_DTO().getId());
        } else {
            input.add("%");
        }
        if (getBinderDto().getRebateProgramType_DTO() != null) {
            input.add(getBinderDto().getRebateProgramType_DTO().getId());
        } else {
            input.add("%");
        }
        if (getBinderDto().getRebateScheduleAlias() != null && !getBinderDto().getRebateScheduleAlias().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleAlias().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleNo() != null && !getBinderDto().getRebateScheduleNo().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        return input;
    }

    @Override
    public List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        input.add(ConstantsUtil.CURRENT_SUMMARY);
        return input;
    }

    /**
     *
     * @param selection
     * @return
     */
    @Override
    public List getPopulateDateCheckInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        return input;
    }

    @Override
    public boolean submit() {
        try {
            List input = getSessionInput(selectionDto);
            setTransferSalesString(String.valueOf(getTransferSales().getValue()));
            setRemoveProjectionBooleanVal(removeProjection.getValue());
            if (selectingOneContract("Selecting one contract", input)) {
                if (submitButtonCheck(input)) {
                    if (isSalesAndUnitsCheck(getContractSelectionTable().getItemIds())) {
                        if (!nonApprovedSubmitCheck("SavedRejectedSubmitcheckForProjTransfer", input)) {
                            if (!nonApprovedSubmitCheck("PendingSubmitcheckForProjTransfer", input)) {
                                if (singleContractCheck("Single contract check", input)) {
                                    new AbstractNotificationUtils() {
                                        @Override
                                        public void yesMethod() {
                                            try {
                                                List input = getResultsInput(selectionDto);
                                                if (selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                                                    ItemQueries.itemUpdate(input, "SubmittingthecontractForUPdateProjTransfer");
                                                } else {
                                                    ItemQueries.itemUpdate(input, "Submitting the contract For UPdate");
                                                }
                                                updateSubmittedContract();
                                                binder.commit();
                                                selectionDto.setIsTransfer(removeProjection.getValue());
                                                selectionDto.getLookup().changeTab();
                                                setSubmit(true);
                                            } catch (Exception ex) {
                                                LOGGER.error(ex);
                                            }
                                        }

                                        @Override
                                        public void noMethod() {
                                            setSubmit(false);
                                        }
                                    }.getConfirmationMessage("Confirmation", "Are you sure you want to submit the selected contracts?");
                                } else {
                                    AbstractNotificationUtils.getErrorNotification("Error",
                                            "Please select one contract to proceed.");
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification("Workflow Status Issue",
                                        "The following Contract & Item combination: " + getContractItemName() + "," + AbstractLogic.getItemName(getSelectedItemList()) + " "
                                        + "is currently associated to a projection in a ‘Saved’ or ‘Rejected’ "
                                        + "Workflow queue. \n"
                                        + "You must delete or approve the projection before proceeding with this Item Transfer. ");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Workflow Status Issue",
                                    "The following Contract & Item combination: " + getContractItemName() + "," + AbstractLogic.getItemName(getSelectedItemList()) + " "
                                    + "is currently associated to a projection in a ‘Pending’ "
                                    + "Workflow queue. \n"
                                    + "You must delete or approve the projection before proceeding with this Item Transfer. ");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Values to Transfer",
                                "The Contract/Item combination '" + getContractItemName() + "," + AbstractLogic.getItemName(getSelectedItemList()) + "' do not have any projected sales or units to transfer. ");
                    }
                } else {
                    setSubmit(false);
                    AbstractNotificationUtils.getErrorNotification("Submit error",
                            "Please enter an End Date for the selected Contract record.Then try again. ");
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Required Fields Error",
                        "Please select atleast one approved contract to proceed.");
            }
        } catch (Exception ez) {
            LOGGER.error(ez);
        }
        return isSubmit();
    }

    /**
     *
     * @return
     */
    public Boolean submitButtonCheck(List input) {

        List<Object[]> list = ItemQueries.getItemData(input, "Submit endDate check", null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return obj == null ? Boolean.TRUE : (Integer) obj == 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void addTransferSales() {
        getTransferSales().addItems("Yes", "No");
        getTransferSales().setValue("Yes");
        getTransferSales().addStyleName("horizontal");
        exportLayout.addComponent(Label);
        exportLayout.addComponent(getTransferSales());
        exportLayout.addComponent(removeProjection);

    }

    @Override
    public void LoadField() {
        field.addItems(Constants.SELECT_ONE, Constants.ITEM_END_DATE);
        valuelabel.setVisible(Boolean.FALSE);
        massUpdateValue.setVisible(Boolean.FALSE);

    }

    @Override
    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    @Override
    public void setVisibleColumns() {
        if (selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            getContractSelectionTable().setVisibleColumns(visibleColumnPt);
            getContractSelectionTable().setColumnHeaders(headerPt);
        } else {
            getContractSelectionTable().setVisibleColumns(visibleColumn);
            getContractSelectionTable().setColumnHeaders(header);
        }

    }

    public boolean isChecked() {
        List input = new ArrayList();
        input.add(selectionDto.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        List list = ItemQueries.getItemData(input, "Mass Update checkreord check", null);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List getSessionInput(SelectionDTO selection) {
        return AbstractLogic.getCurrentInput(selection);
    }

    @Override
    public List getSessionSummary(SelectionDTO selection) {
        return AbstractLogic.getCurrentSummary(selection);
    }

    @Override
    public String getContractItemName() {
        return contractItemName;
    }

    @Override
    public void setContractItemName(String contractItemName) {
        this.contractItemName = contractItemName;
    }

    @Override
    public void updateSubmittedContract() {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(ConstantsUtil.CURRENT_SUMMARY);
        updateInput.addAll(getSessionInput(selectionDto));
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }

    @Override
    public boolean isRemoveProjectionBooleanVal() {
        return removeProjection.getValue();
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selectionDto.getUserId()), "GCM-Item Management", "Item Transfer", "Contract Selection Tab");
            getSearchBtn().setVisible(CommonLogic.isButtonVisibleAccess("search", functionHM));
            getResetBtn().setVisible(CommonLogic.isButtonVisibleAccess("reset1", functionHM));
            getPopulateBtn().setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            getResetBtncur().setVisible(CommonLogic.isButtonVisibleAccess("reset2", functionHM));
            getSubmit().setVisible(CommonLogic.isButtonVisibleAccess("submit", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

	public OptionGroup getTransferSales() {
		return transferSales;
	}

	public void setTransferSales(OptionGroup transferSales) {
		this.transferSales = transferSales;
	}
}
