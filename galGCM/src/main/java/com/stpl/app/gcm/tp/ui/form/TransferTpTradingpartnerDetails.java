
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.tp.ui.layout.CustomTPDetailsLayout;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_CURRENT_CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_TRANSFER_CONTRACT;
import com.stpl.app.gcm.util.TransferPDFilterGenerator;
import com.stpl.app.util.UIUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class TransferTpTradingpartnerDetails extends CustomTPDetailsLayout {

    @UiField("currentTradingPartnerTableLayout")
    public VerticalLayout currentTradingPartnerTableLayout;

    @UiField("transferTradingPartnerTableLayout")
    public VerticalLayout transferTradingPartnerTableLayout;

    @UiField("excelBtnCurrent")
    public Button excelBtnCurrent;
    @UiField("excelBtn")
    public Button excelBtn;

    SessionDTO session;
    ContractSelectionLogic logic = new ContractSelectionLogic();
    CommmonLogic commonLogic = new CommmonLogic();
    public ExtPagedFilterTable currentTradingPartnerDetailsTable = new ExtPagedFilterTable();
    public ExtPagedFilterTable transferTradingPartnerDetailsTable = new ExtPagedFilterTable();

    TransferTPForm transferTpForm;

    private BeanItemContainer<ContractResultDTO> currentTPDetailsContainer;
    private BeanItemContainer<ContractResultDTO> transferTPDetailsContainer;
    boolean currentContractRefresh;
    boolean transferContractRefresh;
    boolean currentCheckValue = false;
    boolean transferCheckValue = false;
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private boolean csvTransferFlag = false;

    CommonUtil commonUtil=CommonUtil.getInstance();
    List<IdDescriptionDTO> statusResultList = new ArrayList<IdDescriptionDTO>();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TransferTpTradingpartnerDetails.class);

    public TransferTpTradingpartnerDetails(SessionDTO session, BeanItemContainer<ContractResultDTO> currentTPDetailsContainer,
            BeanItemContainer<ContractResultDTO> transferTPDetailsContainer, TransferTPForm transferTpForm) {
        this.session = session;
        this.currentTPDetailsContainer = currentTPDetailsContainer;
        this.transferTPDetailsContainer = transferTPDetailsContainer;
        this.transferTpForm = transferTpForm;
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/transferTP_tradingPartnerDetails.xml"), this));
        configureFields();
    }

    public void configureFields() {

        currentTradingPartnerTableLayout.addComponent(currentTradingPartnerDetailsTable);
        transferTradingPartnerTableLayout.addComponent(transferTradingPartnerDetailsTable);
        excelBtnCurrent.setIcon(excelExportImage);
        excelBtn.setIcon(excelExportImage);

        configueTradingPartnerDetailsTable();
    }

    public void configueTradingPartnerDetailsTable() {
        try{
        currentTradingPartnerDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        currentTradingPartnerDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);

        currentTradingPartnerDetailsTable.setPageLength(5);
        currentTradingPartnerDetailsTable.setContainerDataSource(currentTPDetailsContainer);
        currentTradingPartnerDetailsTable.setVisibleColumns(Constants.SUMMARY_TRANSFER_TP_COLUMNS);
        currentTradingPartnerDetailsTable.setColumnHeaders(Constants.SUMMARY_CONTRACT_SELECTION_HEADERS);
        currentTradingPartnerDetailsTable.setEditable(true);
        currentTradingPartnerDetailsTable.setFilterGenerator(new TransferPDFilterGenerator());
        currentTradingPartnerDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        currentTradingPartnerDetailsTable.setFilterBarVisible(true);

        HorizontalLayout currentTpControlLayout = new HorizontalLayout();
        currentTpControlLayout = currentTradingPartnerDetailsTable.createControls();
        currentTradingPartnerTableLayout.addComponent(currentTpControlLayout);

        transferTradingPartnerDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        transferTradingPartnerDetailsTable.setWidth(100, Sizeable.Unit.PERCENTAGE);

        transferTradingPartnerDetailsTable.setPageLength(5);
        transferTradingPartnerDetailsTable.setContainerDataSource(transferTPDetailsContainer);
        transferTradingPartnerDetailsTable.setVisibleColumns(Constants.SUMMARY_TRANSFER_TP_COLUMNS);
        transferTradingPartnerDetailsTable.setColumnHeaders(Constants.SUMMARY_CONTRACT_SELECTION_HEADERS);
        transferTradingPartnerDetailsTable.setEditable(true);
        transferTradingPartnerDetailsTable.setFilterBarVisible(true);
        transferTradingPartnerDetailsTable.setFilterGenerator(new TransferPDFilterGenerator());
        transferTradingPartnerDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
     
        HorizontalLayout transferTpControlLayout = new HorizontalLayout();
        transferTpControlLayout = transferTradingPartnerDetailsTable.createControls();
        transferTradingPartnerTableLayout.addComponent(transferTpControlLayout);

        currentTradingPartnerDetailsTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            currentCheckValue = check.getValue();
                            commonLogic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, TAB_CURRENT_CONTRACT.getConstant(), session);
                        }
                    });
                    return check;
                }

                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat("MM/dd/yyyy");
                    compEndDate.setStyleName("dateFieldCenter");
                    compEndDate.addStyleName("datefieldcentered");
                    compEndDate.setEnabled(false);

                    return compEndDate;
                }

                if (propertyId.equals("compStartDate")) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setDateFormat("MM/dd/yyyy");
                    compStartDate.setStyleName("dateFieldCenter");
                    compStartDate.addStyleName("datefieldcentered");
                    compStartDate.setEnabled(false);
                    return compStartDate;
                }

                if (propertyId.equals(
                        "contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat("MM/dd/yyyy");
                    contEndDate.setStyleName("dateFieldCenter");
                    contEndDate.addStyleName("datefieldcentered");
                    contEndDate.setEnabled(false);

                    return contEndDate;
                }

                if (propertyId.equals(
                        "contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat("MM/dd/yyyy");
                    contStartDate.setStyleName("dateFieldCenter");
                    contStartDate.addStyleName("datefieldcentered");
                    contStartDate.setEnabled(false);
                    return contStartDate;
                }

                return null;
            }
        });

        transferTradingPartnerDetailsTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            transferCheckValue = check.getValue();
                            commonLogic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, TAB_TRANSFER_CONTRACT.getConstant(), session);
                        }
                    });
                    return check;
                }
              
                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat("MM/dd/yyyy");
                    compEndDate.setStyleName("dateFieldCenter");
                    compEndDate.addStyleName("datefieldcentered");
                    compEndDate.setEnabled(false);
                    return compEndDate;
                }

                if (propertyId.equals("compStartDate")) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setDateFormat("MM/dd/yyyy");
                    compStartDate.setStyleName("dateFieldCenter");
                    compStartDate.addStyleName("datefieldcentered");
                    compStartDate.setEnabled(false);
                    return compStartDate;
                }

                if (propertyId.equals(
                        "contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat("MM/dd/yyyy");
                    contEndDate.setStyleName("dateFieldCenter");
                    contEndDate.addStyleName("datefieldcentered");
                    contEndDate.setEnabled(false);

                    return contEndDate;
                }

                if (propertyId.equals(
                        "contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat("MM/dd/yyyy");
                    contStartDate.setStyleName("dateFieldCenter");
                    contStartDate.addStyleName("datefieldcentered");
                    contStartDate.setEnabled(false);
                    return contStartDate;
                }

                return null;
            }
        });
        }catch(Exception ex){
          LOGGER.error(ex.getMessage());       
        }      
    }

    @UiHandler("currentRemove")
    public void currentRemoveButtonClickListener(Button.ClickEvent event) {
        if (!logic.isAnyRecordSelected(session.getUserId(), session.getSessionId(), TAB_CURRENT_CONTRACT.getConstant())) {
            AbstractNotificationUtils.getErrorNotification("No values selected", "Please select a record to remove. Then try again. ");
            return;
        }
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                removeCheckedItems(currentTPDetailsContainer, TAB_CURRENT_CONTRACT.getConstant());
                logic.updateSubmitFlag(session.getModuleName(), TAB_CURRENT_CONTRACT.getConstant(), session.getUserId(), session.getSessionId(), false);
                currentContractRefresh = true;
                transferTpForm.getTabSheet().setSelectedTab(transferTpForm.getTabPosition() - 2);
            }
        }.getConfirmationMessage("Remove Confirmation", "Are you sure you want to undo these selected Company links? \n Companies will be returned to their respective list views.");

    }

    @UiHandler("transferRemove")
    public void transferRemoveButtonClickListener(Button.ClickEvent event) {
        if (!logic.isAnyRecordSelected(session.getUserId(), session.getSessionId(), TAB_TRANSFER_CONTRACT.getConstant())) {
            AbstractNotificationUtils.getErrorNotification("No values selected", "Please select a record to remove. Then try again. ");
            return;
        }

        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                removeCheckedItems(transferTPDetailsContainer, TAB_TRANSFER_CONTRACT.getConstant());
                logic.updateSubmitFlag(session.getModuleName(), TAB_TRANSFER_CONTRACT.getConstant(), session.getUserId(), session.getSessionId(), false);
                transferContractRefresh = true;
                transferTpForm.getTabSheet().setSelectedTab(transferTpForm.getTabPosition() - 1);
            }
        }.getConfirmationMessage("Remove Confirmation", "Are you sure you want to undo these selected Company links? \n Companies will be returned to their respective list views.");

    }

    private void removeCheckedItems(BeanItemContainer<ContractResultDTO> tpDetailsContainer, String tableName) {
        List<ContractResultDTO> checkedContracts = new ArrayList<ContractResultDTO>();
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
                if (TAB_CURRENT_CONTRACT.getConstant().equals(tableName)) {
                    commonLogic.callCheckRecUpdate(currentCheckValue, contract, TAB_CURRENT_CONTRACT.getConstant(), session);
                } else {
                    commonLogic.callCheckRecUpdate(transferCheckValue, contract, TAB_TRANSFER_CONTRACT.getConstant(), session);
                }
            }
        }
        for (ContractResultDTO contract : checkedContracts) {
            tpDetailsContainer.removeItem(contract);
        }
    }

    @Override
    public boolean isCurrentContractRefresh() {
        return currentContractRefresh;
    }

    @Override
    public void setCurrentContractRefresh(boolean currentContractRefresh) {
        this.currentContractRefresh = currentContractRefresh;
    }

    @Override
    public boolean isTransferContractRefresh() {
        return transferContractRefresh;
    }

    @Override
    public void setTransferContractRefresh(boolean transferContractRefresh) {
        this.transferContractRefresh = transferContractRefresh;
    }

    @UiHandler("excelBtnCurrent")
    public void currentTPExport(Button.ClickEvent event) {
        try {
            csvTransferFlag = false;
            List list = CommmonLogic.getSubmittedRecords(session, TAB_CURRENT_CONTRACT.getConstant(), true);
            int recordCount = Integer.valueOf(String.valueOf(list.get(0)));

            List tempVisibleHeaders = new ArrayList(Arrays.asList(currentTradingPartnerDetailsTable.getColumnHeaders()));
            tempVisibleHeaders.remove(0);
            String[] visibleHeaders = Arrays.copyOf(tempVisibleHeaders.toArray(), tempVisibleHeaders.size(), String[].class);

            ExcelExportforBB.createWorkSheet(visibleHeaders, recordCount, this, UI.getCurrent(), "Current_Contract_Details");
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    @UiHandler("excelBtn")
    public void transferTPExport(Button.ClickEvent event) {
        try {
            csvTransferFlag = true;
            List list = CommmonLogic.getSubmittedRecords(session, TAB_TRANSFER_CONTRACT.getConstant(), true);
            int recordCount = Integer.valueOf(String.valueOf(list.get(0)));

            List tempVisibleHeaders = new ArrayList(Arrays.asList(transferTradingPartnerDetailsTable.getColumnHeaders()));
            tempVisibleHeaders.remove(0);
            String[] visibleHeaders = Arrays.copyOf(tempVisibleHeaders.toArray(), tempVisibleHeaders.size(), String[].class);

            ExcelExportforBB.createWorkSheet(visibleHeaders, recordCount, this, UI.getCurrent(), "Transfer_Contract_Details");
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "at excel export");
        }
    }

    // For CSV Export. Do not delete
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheetContent with start " + start + " end " + end);
        try {

            List tempVisibleColumns = new ArrayList();

            String screenName;
            if (csvTransferFlag) {
                screenName = TAB_TRANSFER_CONTRACT.getConstant();
                tempVisibleColumns = new ArrayList(Arrays.asList(transferTradingPartnerDetailsTable.getVisibleColumns()));
            } else {
                screenName = TAB_CURRENT_CONTRACT.getConstant();
                tempVisibleColumns = new ArrayList(Arrays.asList(currentTradingPartnerDetailsTable.getVisibleColumns()));
            }

            tempVisibleColumns.remove(0);
            Object[] visibleColumns = tempVisibleColumns.toArray();
            List checkedContractList = commonLogic.getContractResults(CommmonLogic.getSubmittedRecords(session, screenName, false));
            ExcelExportforBB.createFileContent(visibleColumns, checkedContractList, printWriter);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " at createWorkSheetContent");
        }
    }


}
