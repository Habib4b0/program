
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
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
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.asi.ui.customtextfield.CustomTextField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class TransferContractSearch extends AbstractContractSearch {

    
    private SelectionDTO selectionDto;
    private boolean isnext = false;

    public TransferContractSearch(SelectionDTO selection, List selectedItemList) {
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

            if (allItems.getValue().equals("YES")) {
                selectionDto.setCountQueryName("Item Load contract Count for transfer");
                selectionDto.setDataQueryName("Load contract Item For transfer");
            } else {
                selectionDto.setCountQueryName("Load Non Selected item Contract Count");
                selectionDto.setDataQueryName("Load Non Selected item Contract");
            }
            searchButtonLogic(true);
        }
    }

    private void configureFields() {
        getContent();
        MassUpdatePanel1.setVisible(BooleanConstant.getTrueFlag());
        ConfigureTable();
        getBinder();
        loadAllDdlb();
        configureAllitems();
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

    private void configureAllitems() {
        allItems.addItems("YES", "NO");
        allItems.select("YES");
    }

    @Override
    public List getInput() {
        List input = new ArrayList();
        input.add(selectionDto.getSessionId());
        input.add(ConstantsUtil.CURRENT_SUMMARY);
        if (allItems.getValue().equals("YES")) {
            if (!selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                input.add(AbstractLogic.getItemIds(getSelectedItemList()));
            } else {
                input.add(AbstractLogic.getItemIds(selectionDto.getTransterItemIds()));
            }
        }

        if (getBinderDto().getContractNo() != null && !getBinderDto().getContractNo().isEmpty()) {
            input.add(getBinderDto().getContractNo().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (getBinderDto().getContractName() != null && !getBinderDto().getContractName().isEmpty()) {
            input.add(getBinderDto().getContractName().replace('*', '%'));
        } else {
            input.add("%");
        }

        if (getBinderDto().getContractHolder() != null && !getBinderDto().getContractHolder().isEmpty()) {
            input.add(getBinderDto().getContractHolder().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (getBinderDto().getMarketType_DTO() != null) {
            input.add(getBinderDto().getMarketType_DTO().getId());
        } else {
            input.add("%");
        }
        if (getBinderDto().getCfpNO() != null && !getBinderDto().getCfpNO().isEmpty()) {
            input.add(getBinderDto().getCfpNO().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (getBinderDto().getIfpNo() != null && !getBinderDto().getIfpNo().isEmpty()) {
            input.add(getBinderDto().getIfpNo().replace('*', '%'));
        } else {
            input.add("%");
        }

        if (getBinderDto().getPsNo() != null && !getBinderDto().getPsNo().isEmpty()) {
            input.add(getBinderDto().getPsNo().replace('*', '%'));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleId() != null && !getBinderDto().getRebateScheduleId().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleId().replace('*', '%'));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleName() != null && !getBinderDto().getRebateScheduleName().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleName().replace('*', '%'));
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
            input.add(getBinderDto().getRebateScheduleAlias().replace('*', '%'));
        } else {
            input.add("%");
        }

        if (getBinderDto().getRebateScheduleNo() != null && !getBinderDto().getRebateScheduleNo().isEmpty()) {
            input.add(getBinderDto().getRebateScheduleNo().replace('*', '%'));
        } else {
            input.add("%");
        }
        LOGGER.debug("input-->> {} ------>> {}" , input.size() , input);
        return input;
    }

    @Override
    public List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        input.add(ConstantsUtil.TRANSFER_SUMMARY);
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
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        return input;
    }

    @Override
    public Boolean submitButtonCheck() {
        List input = getSessionInput(selectionDto);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit condition check", null);
        if (AbstractLogic.getCount(list) != 0) {
            return BooleanConstant.getFalseFlag();
        } else {
            return BooleanConstant.getTrueFlag();
        }
    }

    public Boolean submitBtnCheck() {
        List input = getSessionInput(selectionDto);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit check Pojection Transfer", null);
        if (AbstractLogic.getCount(list) != 0) {
            return BooleanConstant.getFalseFlag();
        } else {
            return BooleanConstant.getTrueFlag();
        }
    }

    @Override
    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    @Override
    public List getSessionInput(SelectionDTO selection) {
        return AbstractLogic.getTransferInput(selection);
    }

    @Override
    public List getSessionSummary(SelectionDTO selection) {
        return AbstractLogic.getTransferSummary(selection);
    }

    @Override
    public void updateSubmittedContract() {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(ConstantsUtil.TRANSFER_SUMMARY);
        updateInput.addAll(getSessionInput(selectionDto));
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }

    @Override
    public boolean submit() {
        try {
            final List input = getSessionInput(selectionDto);
            boolean dateValid = true;
            boolean afterDateValid = true;
            String contractName = StringUtils.EMPTY;
            String difference = StringUtils.EMPTY;
            if (selectingOneContract("Selecting one contract", input)) {
                if (submitBtnCheck()) {
                    for (Object itemId : getContractSelectionTable().getItemIds()) {
                        final AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
                        Date startDate = mainDto.getItemStartDate();
                        Date endDate = null;
                        if (mainDto.getCheckRecord()) {
                            List queryInput = new ArrayList();
                            queryInput.add(selectionDto.getSessionId());
                            queryInput.add(mainDto.getPriceToleranceType().getId());
                            queryInput.add(mainDto.getPriceToleranceInterval().getId());
                            queryInput.add(mainDto.getPriceTolerance());
                            queryInput.add(mainDto.getPriceToleranceFrequency().getId());
                            List list;
                            list = ItemQueries.getItemData(queryInput, "getCurrentContractEndDate", null);
                            if (list != null && !list.isEmpty() && list.size() > 0) {
                                endDate = (Date) list.get(0);
                                if (startDate != null && endDate != null && startDate.before(endDate)) {
                                    dateValid = false;
                                    setSubmit(false);
                                    contractName = mainDto.getContractName() + "," + mainDto.getRsName();
                                    break;
                                }
                                if (startDate != null && endDate != null && startDate.after(endDate)) {
                                    Calendar startCalendar = new GregorianCalendar();
                                    startCalendar.setTime(endDate);
                                    Calendar endCalendar = new GregorianCalendar();
                                    endCalendar.setTime(startDate);
                                    int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                                    int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                                    long diff = startDate.getTime() - endDate.getTime();
                                    long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                    afterDateValid = false;
                                    switch (diffMonth) {
                                        case 0:
                                            difference = diffDays + "Days";
                                            break;
                                        case 1:
                                            difference = diffMonth + "Month";
                                            break;
                                        default:
                                            difference = diffMonth + "Months";
                                            break;
                                    }
                                    setSubmit(false);
                                    contractName = mainDto.getContractName() + "," + mainDto.getRsName();
                                    break;
                                }
                            }
                        }
                    }

                    if (dateValid) {
                        if (!afterDateValid) {
                            new AbstractNotificationUtils() {
                                @Override
                                public void yesMethod() {
                                    if (isSalesAndUnitsCheck(getContractSelectionTable().getItemIds()) && getTransferSalesString().equals("Yes")) {
                                        new AbstractNotificationUtils() {
                                            @Override
                                            public void yesMethod() {
                                                submit2(input);
                                            }

                                            @Override
                                            public void noMethod() {
                                                return;
                                            }
                                        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER,
                                                " There are currently projected sales and units values for"
                                                + " the selected Contract/Item combination. "
                                                + "Are you sure you want to override these projection values?");
                                    } else {
                                        submit2(input);
                                    }

                                }

                                @Override
                                public void noMethod() {
                                    return;
                                }
                            }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "The Item Start Date you have entered for the following Contract/Rebate Schedule combination <" + contractName + ">does not directly follow the End Date you have previously entered. If you proceed with this update, there will be a total of  " + difference + " where the selected Item (or Items) will not be on either Contract. \n"
                                    + "Are you sure you want to proceed? ?");

                        } else {
                            if (isSalesAndUnitsCheck(getContractSelectionTable().getItemIds()) && getTransferSalesString().equals("Yes")) {
                                new AbstractNotificationUtils() {
                                    @Override
                                    public void yesMethod() {
                                        submit2(input);
                                    }

                                    @Override
                                    public void noMethod() {
                                        return;
                                    }
                                }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER,
                                        " There are currently projected sales and units values for"
                                        + " the selected Contract/Item combination. "
                                        + "Are you sure you want to override these projection values?");
                            } else {
                                submit2(input);
                            }
                        }
                    } else {
                        MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen." + contractName, ButtonId.OK);
                    }
                } else {
                    setSubmit(false);
                    AbstractNotificationUtils.getErrorNotification("Submit error",
                            "Please enter a Status and Start Date for the selected Contracts. Then try again. ");
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Required Fields Error",
                        "Please select atleast one approved contract to proceed.");
            }
        } catch (Exception ez) {
            LOGGER.error("",ez);
        }
        return isSubmit();
    }

    public void submit2(List input) {
        String msg = "Are you sure you want to submit the selected contracts?";
        if (!nonApprovedSubmitCheck("SavedRejectedSubmitcheckForProjTransfer", input)) {
            if (!nonApprovedSubmitCheck("PendingSubmitcheckForProjTransfer", input)) {
                if (!allItems.getValue().equals("YES") && selectionDto.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                    msg = !(ValidationForItemInContract().isEmpty()) ? ValidationForItemInContract() : msg;
                }

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
                            searchButtonLogic(true);
                            selectionDto.getLookup().changeTab();
                            setSubmit(true);
                        } catch (Exception ex) {
                            LOGGER.error("",ex);
                        }
                    }

                    @Override
                    public void noMethod() {
                        setSubmit(false);
                    }
                }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, msg);
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
    }

    private String ValidationForItemInContract() {
        StringBuilder ConfirmationMsg = new StringBuilder();
        String contractMsg = StringUtils.EMPTY;
        Integer masterSid = 0;
        Integer psSid = 0;
        StringBuilder query = new StringBuilder();
        StringBuilder contractQuery = new StringBuilder();
        for (Object object : getContractSelectionTable().getItemIds()) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                masterSid = dto.getContractMasterSid();
                psSid = dto.getPsContractSid();
                if (contractQuery.length() == 0) {
                    contractQuery.append('\'' ).append( masterSid ).append( '\'');
                } else {
                    contractQuery.append(", '" ).append( masterSid ).append( " '");
                }
                if (query.length() == 0) {
                    query.append("(co.CONTRACT_MASTER_SID =" ).append( masterSid ).append( " and co.PS_CONTRACT_SID =" ).append( psSid ).append( " )  ");
                } else {
                    query.append("OR (co.CONTRACT_MASTER_SID =" ).append( masterSid ).append( " and co.PS_CONTRACT_SID =" ).append( psSid ).append( " )  ");
                }
            }
        }
        Map<String, String> itemName = new HashMap<>();
        List SelectedItemIds = new ArrayList();
        List itemIds = selectionDto.getTransterItemIds();
        for (Object object : itemIds) {
            ItemIndexDto itemDto = (ItemIndexDto) object;
            SelectedItemIds.add(itemDto.getSystemId());
            itemName.put(itemDto.getSystemId(), itemDto.getItemNoTo() + "," + itemDto.getItemNameTo());
        }
        for (int j = 0; j < SelectedItemIds.size(); j++) {
            List input1 = new ArrayList();
            input1.add(contractQuery.toString());
            input1.add(query.toString());

            input1.add(SelectedItemIds.get(j));

            contractMsg = getContractNAmesCheck("contractItemCheck", input1);
            if (!contractMsg.isEmpty()) {
                if (ConfirmationMsg.length() > 0) {
                    ConfirmationMsg.append(',');
                } else {
                    String[] str;
                    String item = itemName.get(SelectedItemIds.get(j));
                    str = item.split(",");

                    ConfirmationMsg.append('<').append(str[0]).append(',').
                            append(str[1]).append('>');

                }

            }
        }
        if (ConfirmationMsg.length() > 0) {
            ConfirmationMsg.append(" Are not currently on the selected contract(s): ");
            ConfirmationMsg.append(contractMsg);
            ConfirmationMsg.append("\n Are you sure you want to add these Items to this contract?");
        }
        return ConfirmationMsg.toString();
    }

    public boolean submitLogic() {
        setIsnext(false);
        boolean isSelected = false;
        final List input = getSessionInput(selectionDto);
        boolean dateValid = true;
        boolean afterDateValid = true;
        String contractName = StringUtils.EMPTY;
        String difference = StringUtils.EMPTY;
        for (Object itemId : getContractSelectionTable().getItemIds()) {
            final AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
            Date startDate = mainDto.getItemStartDate();
            Date endDate = null;
            if (mainDto.getCheckRecord()) {
                isSelected = true;
                List queryInput = new ArrayList();
                queryInput.add(selectionDto.getSessionId());
                queryInput.add(mainDto.getPriceToleranceType().getId());
                queryInput.add(mainDto.getPriceToleranceInterval().getId());
                queryInput.add(mainDto.getPriceTolerance());
                queryInput.add(mainDto.getPriceToleranceFrequency().getId());
                List list = ItemQueries.getItemData(queryInput, "getCurrentContractEndDate", null);
                if (list != null && !list.isEmpty()) {
                    endDate = (Date) list.get(0);
                    if (startDate != null && endDate != null && startDate.before(endDate)) {
                        dateValid = false;
                        setSubmit(false);
                        contractName = mainDto.getContractName() + "," + mainDto.getRsName();
                        break;
                    }
                    if (startDate != null && endDate != null && startDate.after(endDate)) {
                        Calendar startCalendar = new GregorianCalendar();
                        startCalendar.setTime(endDate);
                        Calendar endCalendar = new GregorianCalendar();
                        endCalendar.setTime(startDate);
                        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                        int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                        long diff = startDate.getTime() - endDate.getTime();
                        long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        afterDateValid = false;
                        switch (diffMonth) {
                            case 0:
                                difference = diffDays + "Days";
                                break;
                            case 1:
                                difference = diffMonth + "Month";
                                break;
                            default:
                                difference = diffMonth + "Months";
                                break;
                        }
                        setSubmit(false);
                        contractName = mainDto.getContractName() + "," + mainDto.getRsName();
                        break;
                    }
                }
            }
        }

        if (isSelected && submitBtnCheck()) {
            if (dateValid) {
                if (!afterDateValid) {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            submit2(input);
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "The Item Start Date you have entered for the following Contract/Rebate Schedule combination <" + contractName + ">does not directly follow the End Date you have previously entered. If you proceed with this update, there will be a total of  " + difference + " where the selected Item (or Items) will not be on either Contract. \n"
                            + "Are you sure you want to proceed? ?");

                } else {
                    submit2(input);
                }
            } else {
                MessageBox.showPlain(Icon.ERROR, "Start Date cannot come before the End Date", "You cannot proceed with this Item Start Date since it does not come after the End Date you have entered on the previous screen." + contractName, ButtonId.OK);
            }

        } else {
            MessageBox.showPlain(Icon.ERROR, "No Item information selected", "Please select a contract to transfer the selected Item to. Then select a Status, and an Item Start Date. ", ButtonId.OK);
        }
        return isIsnext();
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selectionDto.getUserId()), "GCM-Item Management", "Item Transfer", "Transfer Contract Tab");
            getSearchBtn().setVisible(CommonLogic.isButtonVisibleAccess("search", functionHM));
            getResetBtn().setVisible(CommonLogic.isButtonVisibleAccess("reset1", functionHM));
            getPopulateBtn().setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            getResetBtncur().setVisible(CommonLogic.isButtonVisibleAccess("reset2", functionHM));
            getSubmit().setVisible(CommonLogic.isButtonVisibleAccess("submit", functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

	public boolean isIsnext() {
		return isnext;
	}

	public void setIsnext(boolean isnext) {
		this.isnext = isnext;
	}
}
