/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

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
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
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
    SelectionDTO selection;
    Object valueChange;
    Label Label = new Label("Transfer Sales Projection :");
    public OptionGroup transferSales = new OptionGroup();
    CheckBox removeProjection = new CheckBox("Remove Projection Details");
    Object[] VISIBLE_COLUMN = {ConstantsUtil.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, ConstantsUtil.STATUS, ConstantsUtil.ITEM_START_DATE, ConstantsUtil.ITEM_END_DATE, "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] HEADER = {StringUtils.EMPTY, "Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CFP No", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};
    Object[] VISIBLE_COLUMN_PT = {ConstantsUtil.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER,
        Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, ConstantsUtil.ITEM_START_DATE,
        ConstantsUtil.ITEM_END_DATE, "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME,
        "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] HEADER_PT = {StringUtils.EMPTY, "Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CFP No", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};
    Object[] EXCEL_VISIBLE_COLUMN_PT = {"projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER,
        Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, ConstantsUtil.ITEM_START_DATE,
        ConstantsUtil.ITEM_END_DATE, "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME,
        "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] EXCEL_HEADER_PT = {"Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CFP No", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};

    public CurrentContractContractSearch(SelectionDTO selection, List selectedItemList) {
        super(selection, selectedItemList);
        try {
            this.selection = selection;
            configureFields();
            setTabOperation(ConstantsUtil.CURRENT_COONTRACT);
        } catch (Exception ex) {
            LOGGER.error(ex);

        }

    }

    @UiHandler("search")
    public void searchBtnLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
            if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                selection.setCountQueryName("ProjectionTransferItemCountQuery");
                selection.setDataQueryName("ProjectionTransferItemDataQuery");
            } else {
                selection.setCountQueryName("Item Load contract Count");
                selection.setDataQueryName("Load contract Item");
            }

            searchButtonLogic();
        }
    }

    private void configureFields() throws Exception {
        getContent();
        MassUpdatePanel1.setVisible(Boolean.TRUE);
        allItems.setEnabled(Boolean.FALSE);
        ConfigureTable();
        getBinder();
        loadAllDdlb();
        addTransferSales();
    }

    public void createFieldFactory() {
        contractSelectionTable.setTableFieldFactory(new TableFieldFactory() {
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
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                if (itemId instanceof AbstractContractSearchDTO) {
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setCheckRecord(check.getValue());
                                    dto.setColumnName("CHECK_RECORD");
                                    dto.setCaseNo(17);
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

                        public void focus(FieldEvents.FocusEvent event) {
                            Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {

                                public void valueChange(Property.ValueChangeEvent event) {
                                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) itemId;
                                    dto.setCaseNo(0);
                                    Date startDate = abstractLogic.getStartDateCheck(dto, selection, "START_DATE");
                                    if (startDate != null && itemendDate.getValue() != null && itemendDate.getValue().before(startDate)) {
                                        itemendDate.setValue(null);
                                        MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen.", ButtonId.OK);
                                    } else {
                                        dto.setEndDate(itemendDate.getValue());
                                        dto.setColumnName("END_DATE");
                                        dto.setCaseNo(2);
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
        boolean flag = logic.getEditedItemDetails(dto, selection);
    }

    public List getInput() {
        List input = new ArrayList();
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(AbstractLogic.getItemIds(selectedItemList));
        }
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(selection.getButtonMode());
        }

        if (binderDto.getContractNo() != null && !binderDto.getContractNo().isEmpty()) {
            input.add(binderDto.getContractNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractName() != null && !binderDto.getContractName().isEmpty()) {
            input.add(binderDto.getContractName().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getContractHolder() != null && !binderDto.getContractHolder().isEmpty()) {
            input.add(binderDto.getContractHolder().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getMarketType_DTO() != null) {
            input.add(binderDto.getMarketType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getCfpNO() != null && !binderDto.getCfpNO().isEmpty()) {
            input.add(binderDto.getCfpNO().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getIfpNo() != null && !binderDto.getIfpNo().isEmpty()) {
            input.add(binderDto.getIfpNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getPsNo() != null && !binderDto.getPsNo().isEmpty()) {
            input.add(binderDto.getPsNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleId() != null && !binderDto.getRebateScheduleId().isEmpty()) {
            input.add(binderDto.getRebateScheduleId().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleName() != null && !binderDto.getRebateScheduleName().isEmpty()) {
            input.add(binderDto.getRebateScheduleName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleType_DTO() != null) {
            input.add(binderDto.getRebateScheduleType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleCategory_DTO() != null) {
            input.add(binderDto.getRebateScheduleCategory_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateProgramType_DTO() != null) {
            input.add(binderDto.getRebateProgramType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleAlias() != null && !binderDto.getRebateScheduleAlias().isEmpty()) {
            input.add(binderDto.getRebateScheduleAlias().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleNo() != null && !binderDto.getRebateScheduleNo().isEmpty()) {
            input.add(binderDto.getRebateScheduleNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        return input;
    }

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
    public List getPopulateDateCheckInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        return input;
    }

    public boolean submit() {
        try {
            List input = getSessionInput(selection);
            setTransferSalesString(String.valueOf(transferSales.getValue()));
            setRemoveProjectionBooleanVal(removeProjection.getValue());
            if (selectingOneContract("Selecting one contract", input)) {
                if (submitButtonCheck(input)) {
                    if (isSalesAndUnitsCheck(contractSelectionTable.getItemIds())) {
                        if (!nonApprovedSubmitCheck("SavedRejectedSubmitcheckForProjTransfer", input)) {
                            if (!nonApprovedSubmitCheck("PendingSubmitcheckForProjTransfer", input)) {
                                if (singleContractCheck("Single contract check", input)) {
                                    new AbstractNotificationUtils() {
                                        @Override
                                        public void yesMethod() {
                                            try {
                                                List input = getResultsInput(selection);
                                                if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                                                    ItemQueries.itemUpdate(input, "SubmittingthecontractForUPdateProjTransfer");
                                                } else {
                                                    ItemQueries.itemUpdate(input, "Submitting the contract For UPdate");
                                                }
                                                updateSubmittedContract();
                                                binder.commit();
                                                selection.setIsTransfer(removeProjection.getValue());
                                                selection.getLookup().changeTab();
                                                isSubmit = true;
                                            } catch (Exception ex) {
                                                LOGGER.error(ex.getMessage());
                                            }
                                        }

                                        @Override
                                        public void noMethod() {
                                            isSubmit = false;
                                        }
                                    }.getConfirmationMessage("Confirmation", "Are you sure you want to submit the selected contracts?");
                                } else {
                                    AbstractNotificationUtils.getErrorNotification("Error",
                                            "Please select one contract to proceed.");
                                }
                            } else {
                                AbstractNotificationUtils.getErrorNotification("Workflow Status Issue",
                                        "The following Contract & Item combination: " + getContractItemName() + "," + AbstractLogic.getItemName(selectedItemList) + " "
                                        + "is currently associated to a projection in a ‘Saved’ or ‘Rejected’ "
                                        + "Workflow queue. \n"
                                        + "You must delete or approve the projection before proceeding with this Item Transfer. ");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Workflow Status Issue",
                                    "The following Contract & Item combination: " + getContractItemName() + "," + AbstractLogic.getItemName(selectedItemList) + " "
                                    + "is currently associated to a projection in a ‘Pending’ "
                                    + "Workflow queue. \n"
                                    + "You must delete or approve the projection before proceeding with this Item Transfer. ");
                        }
                } else {
                        AbstractNotificationUtils.getErrorNotification("No Values to Transfer",
                                "The Contract/Item combination '" + getContractItemName() + "," + AbstractLogic.getItemName(selectedItemList) + "' do not have any projected sales or units to transfer. ");
                    }
                } else {
                    isSubmit = false;
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
        return isSubmit;
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
        transferSales.addItems("Yes", "No");
        transferSales.setValue("Yes");
        transferSales.addStyleName("horizontal");
        exportLayout.addComponent(Label);
        exportLayout.addComponent(transferSales);
        exportLayout.addComponent(removeProjection);

    }

    public void LoadField() {
        field.addItems(Constants.SELECT_ONE, "End Date");
        valuelabel.setVisible(Boolean.FALSE);
        massUpdateValue.setVisible(Boolean.FALSE);

    }

    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    public void setVisibleColumns() {
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            contractSelectionTable.setVisibleColumns(VISIBLE_COLUMN_PT);
            contractSelectionTable.setColumnHeaders(HEADER_PT);
        } else {
            contractSelectionTable.setVisibleColumns(VISIBLE_COLUMN);
            contractSelectionTable.setColumnHeaders(HEADER);
        }

    }

    public boolean isChecked() {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        List list = ItemQueries.getItemData(input, "Mass Update checkreord check", null);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    public List getSessionInput(SelectionDTO selection) {
        return AbstractLogic.getCurrentInput(selection);
    }

    public List getSessionSummary(SelectionDTO selection) {
        return AbstractLogic.getCurrentSummary(selection);
    }

    public String getContractItemName() {
        return contractItemName;
    }

    public void setContractItemName(String contractItemName) {
        this.contractItemName = contractItemName;
    }

    public void updateSubmittedContract() {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(ConstantsUtil.CURRENT_SUMMARY);
        updateInput.addAll(getSessionInput(selection));
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }
      public boolean isRemoveProjectionBooleanVal() {
        return removeProjection.getValue();
    }

}
