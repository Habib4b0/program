
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
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

    SelectionDTO selection;
    final static CommonDao ITEMDAO = CommonImpl.getInstance();
    public boolean isnext = false;

    public TransferContractSearch(SelectionDTO selection, List selectedItemList) {
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

            if (allItems.getValue().equals("YES")) {
                selection.setCountQueryName("Item Load contract Count for transfer");
                selection.setDataQueryName("Load contract Item For transfer");
            } else {
                selection.setCountQueryName("Load Non Selected item Contract Count");
                selection.setDataQueryName("Load Non Selected item Contract");
            }
            searchButtonLogic(true);
        }
    }

    private void configureFields() {
        getContent();
        MassUpdatePanel1.setVisible(Boolean.TRUE);
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

    public List getInput() {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_SUMMARY);
        if (allItems.getValue().equals("YES")) {
            if (!selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                input.add(AbstractLogic.getItemIds(selectedItemList));
            } else {
                input.add(AbstractLogic.getItemIds(selection.getTransterItemIds()));
            }
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
        LOGGER.debug("input-->>" + input.size() + "------>>" + input);
        return input;
    }

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
    public List getPopulateDateCheckInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        return input;
    }

    public Boolean submitButtonCheck() {
        List input = getSessionInput(selection);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit condition check", null);
        if (AbstractLogic.getCount(list) != 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean submitBtnCheck() {
        List input = getSessionInput(selection);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit check Pojection Transfer", null);
        if (AbstractLogic.getCount(list) != 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    public List getSessionInput(SelectionDTO selection) {
        return AbstractLogic.getTransferInput(selection);
    }

    public List getSessionSummary(SelectionDTO selection) {
        return AbstractLogic.getTransferSummary(selection);
    }

    public void updateSubmittedContract() {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(ConstantsUtil.TRANSFER_SUMMARY);
        updateInput.addAll(getSessionInput(selection));
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }

    public boolean submit() {
        try {
            final List input = getSessionInput(selection);
            boolean dateValid = true;
            boolean afterDateValid = true;
            String contractName = StringUtils.EMPTY;
            String difference = StringUtils.EMPTY;
            if (selectingOneContract("Selecting one contract", input)) {
                if (submitBtnCheck()) {
                    for (Object itemId : contractSelectionTable.getItemIds()) {
                        final AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
                        Date startDate = mainDto.getItemStartDate();
                        Date endDate = null;
                        if (mainDto.getCheckRecord()) {
                            List queryInput = new ArrayList();
                            queryInput.add(selection.getSessionId());
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
                                    isSubmit = false;
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
                                    isSubmit = false;
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
                                    if (isSalesAndUnitsCheck(contractSelectionTable.getItemIds()) && getTransferSalesString().equals("Yes")) {
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
                            if (isSalesAndUnitsCheck(contractSelectionTable.getItemIds()) && getTransferSalesString().equals("Yes")) {
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
                    isSubmit = false;
                    AbstractNotificationUtils.getErrorNotification("Submit error",
                            "Please enter a Status and Start Date for the selected Contracts. Then try again. ");
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

    public void submit2(List input) {
        String msg = "Are you sure you want to submit the selected contracts?";
        if (!nonApprovedSubmitCheck("SavedRejectedSubmitcheckForProjTransfer", input)) {
            if (!nonApprovedSubmitCheck("PendingSubmitcheckForProjTransfer", input)) {
                if (!allItems.getValue().equals("YES") && selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                    msg = !(ValidationForItemInContract().isEmpty()) ? ValidationForItemInContract() : msg;
                }

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
                            searchButtonLogic(true);
                            selection.getLookup().changeTab();
                            isSubmit = true;
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }

                    @Override
                    public void noMethod() {
                        isSubmit = false;
                    }
                }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, msg);
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
    }

    private String ValidationForItemInContract() {
        StringBuilder ConfirmationMsg = new StringBuilder();
        String contractMsg = StringUtils.EMPTY;
        Integer masterSid = 0;
        Integer psSid = 0;
        StringBuilder query = new StringBuilder();
        StringBuilder contractQuery = new StringBuilder();
        for (Object object : contractSelectionTable.getItemIds()) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                masterSid = dto.getContractMasterSid();
                psSid = dto.getPsContractSid();
                if (contractQuery.length() == 0) {
                    contractQuery.append("'" + masterSid + "'");
                } else {
                    contractQuery.append(", '" + masterSid + " '");
                }
                if (query.length() == 0) {
                    query.append("(co.CONTRACT_MASTER_SID =" + masterSid + " and co.PS_CONTRACT_SID =" + psSid + " )  ");
                } else {
                    query.append("OR (co.CONTRACT_MASTER_SID =" + masterSid + " and co.PS_CONTRACT_SID =" + psSid + " )  ");
                }
            }
        }
        Map<String, String> itemName = new HashMap<>();
        List SelectedItemIds = new ArrayList();
        List itemIds = selection.getTransterItemIds();
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
                    ConfirmationMsg.append(",");
                } else {
                    String[] str;
                    String item = itemName.get(SelectedItemIds.get(j));
                    str = item.split(",");

                    ConfirmationMsg.append("<").append(str[0]).append(",").
                            append(str[1]).append(">");

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
        isnext = false;
        boolean isSelected = false;
        final List input = getSessionInput(selection);
        boolean dateValid = true;
        boolean afterDateValid = true;
        String contractName = StringUtils.EMPTY;
        String difference = StringUtils.EMPTY;
        for (Object itemId : contractSelectionTable.getItemIds()) {
            final AbstractContractSearchDTO mainDto = (AbstractContractSearchDTO) itemId;
            Date startDate = mainDto.getItemStartDate();
            Date endDate = null;
            if (mainDto.getCheckRecord()) {
                isSelected = true;
                List queryInput = new ArrayList();
                queryInput.add(selection.getSessionId());
                queryInput.add(mainDto.getPriceToleranceType().getId());
                queryInput.add(mainDto.getPriceToleranceInterval().getId());
                queryInput.add(mainDto.getPriceTolerance());
                queryInput.add(mainDto.getPriceToleranceFrequency().getId());
                List list = ItemQueries.getItemData(queryInput, "getCurrentContractEndDate", null);
                if (list != null && !list.isEmpty()) {
                    endDate = (Date) list.get(0);
                    if (startDate != null && endDate != null && startDate.before(endDate)) {
                        dateValid = false;
                        isSubmit = false;
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
                        isSubmit = false;
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
        return isnext;
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), "GCM-Item Management", "Item Transfer", "Transfer Contract Tab");
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
