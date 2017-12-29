/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.ui.layout.CustomTPDetailsLayout;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_REMOVE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_UPDATE;
import com.stpl.app.gcm.util.CustomerFilterGenerator;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class TradingpartnerDetails extends CustomTPDetailsLayout {

    @UiField("tradingPartnerTableLayout")
    public VerticalLayout tradingPartnerTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    private boolean contractRefresh = false;

    @UiField("rebuild")
    public Button rebuild;

    public ExtFilterTable tradingPartnerDetailsTable = new ExtFilterTable();
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    private final BeanItemContainer<ContractResultDTO> tradingPartnerDetailsContainer;
    private final CommmonLogic logic = new CommmonLogic();
    private final Set checkedCount = new HashSet();
    private final StplSecurity stplSecurity = new StplSecurity();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TradingpartnerDetails.class);
    private final SessionDTO session;
    private boolean checkValue = false;

    public TradingpartnerDetails(BeanItemContainer<ContractResultDTO> tradingPartnerDetailsContainer, SessionDTO session) {
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/tradingPartnerDetails.xml"), this));
        this.tradingPartnerDetailsContainer = tradingPartnerDetailsContainer;
        this.session = session;
        configureFields();
        configureSecurityPermissions();
    }

    public void configureFields() {
        tradingPartnerTableLayout.addComponent(tradingPartnerDetailsTable);
        excelBtn.setIcon(excelExportImage);
        configueTradingPartnerDetailsTable();
    }

    public void configueTradingPartnerDetailsTable() {
        tradingPartnerDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tradingPartnerDetailsTable.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        tradingPartnerDetailsTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        tradingPartnerDetailsTable.setPageLength(NumericConstants.FIVE);
        tradingPartnerDetailsTable.setContainerDataSource(tradingPartnerDetailsContainer);
        tradingPartnerDetailsTable.setVisibleColumns(Constants.getInstance().removeTpSummaryContractSelectionColumns);
        tradingPartnerDetailsTable.setColumnHeaders(Constants.getInstance().removeTpContractSelectionHeaders);
        tradingPartnerDetailsTable.setColumnAlignment("contStartDate", ExtCustomTable.Align.CENTER);
        tradingPartnerDetailsTable.setColumnAlignment("contEndDate", ExtCustomTable.Align.CENTER);
        tradingPartnerDetailsTable.setColumnAlignment("compStartDate", ExtCustomTable.Align.CENTER);
        tradingPartnerDetailsTable.setColumnAlignment("compEndDate", ExtCustomTable.Align.CENTER);
        tradingPartnerDetailsTable.setEditable(true);
        tradingPartnerDetailsTable.setTableFieldFactory(new TableFieldFactory() {
            @Override
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Constants.CHECK_RECORD)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            checkValue = check.getValue();
                            logic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, StringUtils.EMPTY, session);
                            if (check.getValue()) {
                                checkedCount.add(itemId);
                            } else {
                                checkedCount.remove(itemId);
                            }
                            if (checkedCount.size() > 0) {
                                session.setRemoveCheck(true);
                            } else {
                                session.setRemoveCheck(false);
                            }

                        }
                    });
                    return check;
                }

                if (propertyId.equals("compStartDate")) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setDateFormat(Constants.DATE_FORMAT);
                    compStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    compStartDate.addStyleName(Constants.DATE_FIELD_CENTERED);
                    compStartDate.setEnabled(false);
                    return compStartDate;
                }

                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat(Constants.DATE_FORMAT);
                    compEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    compEndDate.addStyleName(Constants.DATE_FIELD_CENTERED);
                    compEndDate.setEnabled(false);
                    return compEndDate;
                }

                if (propertyId.equals("contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat(Constants.DATE_FORMAT);
                    contEndDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    contEndDate.addStyleName(Constants.DATE_FIELD_CENTERED);
                    contEndDate.setEnabled(false);
                    return contEndDate;
                }

                if (propertyId.equals("contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat(Constants.DATE_FORMAT);
                    contStartDate.setStyleName(Constants.DATE_FIELD_CENTER);
                    contStartDate.addStyleName(Constants.DATE_FIELD_CENTERED);
                    contStartDate.setEnabled(false);
                    return contStartDate;
                }

                return null;
            }
        });
        tradingPartnerDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        tradingPartnerDetailsTable.setFilterGenerator(new CustomerFilterGenerator());
        tradingPartnerDetailsTable.setFilterBarVisible(true);
        tradingPartnerDetailsTable.addStyleName("filtertable");
        tradingPartnerDetailsTable.addStyleName("table-header-normal");
        if (TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {

            rebuild.setCaption("CANCEL UPDATE");
        }
        for (Object object : tradingPartnerDetailsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                tradingPartnerDetailsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        tradingPartnerDetailsTable.resetFilters();
        tradingPartnerDetailsTable.setFilterFieldVisible(Constants.CHECK_RECORD, false);
    }

    @UiHandler("rebuild")
    public void currentRemoveButtonClickListener(Button.ClickEvent event) {
        String messageHeader = "Cancel confirmation";
        String message = "Update";
        String msgHeader = "No Records Selected";
        if (TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {
            messageHeader = "Cancel confirmation";
            message = "Update";
            msgHeader = "No Records Selected";
        } else if (TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName())) {
            messageHeader = "Remove Confirmation";
            message = "Remove";
            msgHeader = "Nothing Selected";
        }
        if (!getCheckedRecCount()) {
            AbstractNotificationUtils.getErrorNotification(msgHeader, "Please select a value to remove.\n Then try again. ");
            return;
        }

        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {

                    removeCheckedItems(tradingPartnerDetailsContainer);
                    contractRefresh = true;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage(messageHeader, "Are you sure you want to remove the selected Contract from the " + message + " Customer process?\n It will be removed and added back to the Available List of Contracts in the Current Contract Selection screen. ");
    }

    private void removeCheckedItems(BeanItemContainer<ContractResultDTO> tpDetailsContainer) {
        List<ContractResultDTO> checkedContracts = new ArrayList<>();
        ContractSelectionLogic csLogic = new ContractSelectionLogic();
        List<ContractResultDTO> tableRecords = tpDetailsContainer.getItemIds();
        String contractName = StringUtils.EMPTY;
        String cfpName = StringUtils.EMPTY;
        for (ContractResultDTO contract : tableRecords) {
            if (contract.getCheckRecord()) {
                checkedContracts.add(contract);
                contractName = contract.getContractName();
                cfpName = contract.getCfpName();
            }
            if (contractName.equals(contract.getContractName()) && cfpName.equals(contract.getCfpName())) {
                checkedContracts.add(contract);
                logic.callCheckRecUpdate(checkValue, contract, StringUtils.EMPTY, session);
            }
        }
        for (ContractResultDTO contract : checkedContracts) {
            tpDetailsContainer.removeItem(contract);
        }
        csLogic.updateSubmitFlag(session.getModuleName(), StringUtils.EMPTY, session.getUserId(), session.getSessionId(), false);
    }

    @Override
    public boolean isCurrentContractRefresh() {
        return contractRefresh;
    }

    @Override
    public boolean isTransferContractRefresh() {
        return false;
    }

    @Override
    public void setCurrentContractRefresh(boolean currentContractRefresh) {
        contractRefresh = currentContractRefresh;
    }

    @Override
    public void setTransferContractRefresh(boolean transferContractRefresh) {
        return;
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            if (tradingPartnerDetailsContainer.size() > 0) {
                createWorkSheet("Contract_Details", tradingPartnerDetailsTable);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void createWorkSheet(String moduleName, ExtFilterTable resultTable) throws SystemException, PortalException {
        try {
            long recordCount = 0;
            CommmonLogic logic = new CommmonLogic();
            if (resultTable.size() != 0) {
                List list = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, StringUtils.EMPTY, false));
                recordCount = list.size();
            }
            Object[] headers = resultTable.getColumnHeaders();
            headers = ArrayUtils.removeElement(headers, StringUtils.EMPTY);
            ExcelExportforBB.createWorkSheet((String[]) headers, recordCount, this, UI.getCurrent(), moduleName);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        LOGGER.debug("Entering createWorkSheetContent with start " + start + " end " + end);
        try {
            CommmonLogic logic = new CommmonLogic();
            if (tradingPartnerDetailsContainer.size() > 0) {
                List<ContractResultDTO> checkedContractList = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, StringUtils.EMPTY, false));
                Object[] columns = tradingPartnerDetailsTable.getVisibleColumns();
                columns = ArrayUtils.removeElement(columns, Constants.CHECK_RECORD);
                ExcelExportforBB.createFileContent(columns, checkedContractList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private boolean getCheckedRecCount() {
        boolean isChecked = false;
        List<ContractResultDTO> tableRecords = tradingPartnerDetailsContainer.getItemIds();

        for (ContractResultDTO contract : tableRecords) {
            if (contract.getCheckRecord()) {
                isChecked = true;
                break;
            }
        }

        return isChecked;
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "UpdateCustomer", "SummaryTab");
            rebuild.setVisible(CommonLogic.isButtonVisibleAccess("rebuild", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
