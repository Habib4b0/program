/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getFtpBundleValue;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.CFFIndexTableLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CFFFilterGenerator;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.util.service.SchedulerSynchronizer;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox.ClickListener;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author ahalya
 */
public class CFFSearchLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(CFFSearchLookUp.class);

    private ProcessSchedulerDTO psDTO = new ProcessSchedulerDTO();

    public CustomFieldGroup cffSearchBinder = new CustomFieldGroup(new BeanItem<>(psDTO));

    @UiField("financialForecastId")
    private TextField financialForecastId;

    @UiField("projectionId")
    private TextField projectionId;

    @UiField("customerNo")
    private TextField customerNo;

    @UiField("companyNo")
    private TextField companyNo;

    @UiField("financialForecastName")
    private TextField financialForecastName;

    @UiField("projectionName")
    private TextField projectionName;

    @UiField("customerName")
    private TextField customerName;

    @UiField("companyName")
    private TextField companyName;

    @UiField("contractNo")
    private TextField contractNo;

    @UiField("itemNo")
    private TextField itemNo;

    @UiField("businessUnitNo")
    private TextField businessUnitNo;

    @UiField("contractName")
    private TextField contractName;

    @UiField("itemName")
    private TextField itemName;

    @UiField("businessUnitName")
    private TextField businessUnitName;

    @UiField("typeDdlb")
    private ComboBox typeDdlb;

    @UiField("cffCreationDateFrom")
    private PopupDateField cffCreationDateFrom;

    @UiField("cffCreationDateTo")
    private PopupDateField cffCreationDateTo;

    @UiField("cffApprovalDateFrom")
    private PopupDateField cffApprovalDateFrom;

    @UiField("cffApprovalDateTo")
    private PopupDateField cffApprovalDateTo;

    @UiField("resultLayout")
    private VerticalLayout resultLayout;
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();

    private final ErrorLabel errorMsg = new ErrorLabel();

    private final BeanItemContainer<ProcessSchedulerDTO> resultsContainer = new BeanItemContainer<>(ProcessSchedulerDTO.class);
    private SessionDTO sessionDTO;
    private CommonUtils commonutil = new CommonUtils();
    private CFFIndexTableLogic tableLogic = new CFFIndexTableLogic();
    public ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    private String scriptName = StringUtils.EMPTY;

    private String processId = StringUtils.EMPTY;

    private String displayName = StringUtils.EMPTY;

    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");

    private final ProcessSchedulerLogic cffLogic = new ProcessSchedulerLogic();
    public static final String CHECK_RECORD = "checkRecord";

    public final Object[] resultTableVisibleColumn = new Object[]{CHECK_RECORD, "financialForecastId", "financialForecastName", "typeDesc",
        "projectionId", "projectionName", "year", "month", "contractId", "contractNo", "contractName", "contractType", "contractHolderId", "contractHolderNo",
        "contractHolderName", "customerId", "customerNo", "customerName", "itemId", "itemNo", "itemName", "salesDollars", "salesUnits", "salesInclusion",
        "deductionId", "deductionNo", "deductionName", "deductionCategory", "deductionType", "deductionProgram", "deductionInclusion", "deductionCategoryOne",
        "deductionCategoryTwo", "deductionCategoryThree", "deductionCategoryFour", "deductionCategoryFive", "deductionCategorySix", "deductionDollars", "deductionRate",
        "deductionPerUnit", "netSalesDollar", "cogsAmount", "cogsPerUnit", "netProfitDollars", "netProfitPerUnit", "companyId", "companyNo", "companyName",
        "businessUnitId", "businessUnitNo", "businessUnitName", "financialForecastCreationDate", "financialForecastApprovalDate" //            , "batchId", "source", "createdBy", "createdDate", "modifiedBy", "modifiedDate"
        , "outboundStatus", "originalBatchId"};

    public final String[] resultTableHeader = new String[]{ConstantsUtils.EMPTY, "Financial Forecast ID", "Financial Forecast Name", "Type",
        "Project ID", "Projection Name", "Year", "Month", "Contract ID", "Contract No", "Contract Name", "Contract Type", "Contract Holder ID", "Contract Holder No",
        "Contract Holder Name", "Customer ID", "Customer No", "Customer Name", "Item ID", "Item No", "Item Name", "Sales Dollars", "Sales Units", "Sales Inclusion",
        "Deduction ID", "Deduction No", "Deduction Name", "Deduction Category", "Deduction Type", "Deduction Program", "Deduction Inclusion", "Deduction Category 1",
        "Deduction Category 2", "Deduction Category 3", "Deduction Category 4", "Deduction Category 5", "Deduction Category 6", "Deduction Dollars", "Deduction Rate",
        "Deduction Per Unit", "Net Sales Dollar", "COGS Amount", "COGS Per Unit", "Net Profit Dollars", "Net Profit Per Unit", "Company ID", "Company No", "Company Name",
        "Business Unit ID", "Business Unit No", "Business Unit Name", "Financial Forecast Creation Date", "Financial Forecast Approval Date" //            , "Batch ID", "Source","Created By", "Created Date", "Modified By", "Modified Date"
        , "Outbound Status", "Original Batch ID"};

    /**
     * ConsolidatedFinancialForecastForm constructor
     *
     * @param sessionDTO
     */
    public CFFSearchLookUp(final SessionDTO sessionDTO, final String scriptName, final String processId, final String displayName) {
        LOGGER.debug("Entering init method");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/CFFSearch.xml"), this));
        setId("CFF SEARCH");
        setCaption("CFF SEARCH");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth(NumericConstants.ONE_ZERO_FIVE_ZERO, Sizeable.Unit.PIXELS);
        setHeight("800px");
        this.sessionDTO = sessionDTO;
        getBinder();
        configureFields();
        this.scriptName = scriptName;
        this.processId = processId;
        this.displayName = displayName;
        LOGGER.debug("init method Ends");
    }

    private CustomFieldGroup getBinder() {
        cffSearchBinder.bindMemberFields(this);
        cffSearchBinder.setItemDataSource(new BeanItem<>(psDTO));
        cffSearchBinder.setBuffered(true);
        cffSearchBinder.setErrorDisplay(errorMsg);
        return cffSearchBinder;
    }

    private void configureFields() {
        try {
            LOGGER.debug("Enters ConsolidatedFinancialForecastForm Configure Field method");
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("CFF");
            financialForecastId.setImmediate(true);
            financialForecastId.addValidator(new StringLengthValidator("Financial Forecast Id should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            financialForecastId.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "CFF Id should be alphanumeric"));

            financialForecastName.setImmediate(true);
            financialForecastName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            financialForecastName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "CFF Name should be alphanumeric"));

            projectionId.setImmediate(true);
            projectionId.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            projectionId.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Project Id should be alphanumeric"));

            projectionName.setImmediate(true);
            projectionName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            projectionName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Projection Name should be alphanumeric"));

            contractNo.setImmediate(true);
            contractNo.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            contractNo.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Contract No should be alphanumeric"));

            contractName.setImmediate(true);
            contractName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            contractName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Contract Name should be alphanumeric"));

            customerNo.setImmediate(true);
            customerNo.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            customerNo.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Customer No should be alphanumeric"));

            customerName.setImmediate(true);
            customerName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            customerName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Customer Name should be alphanumeric"));

            itemNo.setImmediate(true);
            itemNo.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            itemNo.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Item No should be alphanumeric"));

            itemName.setImmediate(true);
            itemName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            itemName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Item Name should be alphanumeric"));

            companyNo.setImmediate(true);
            companyNo.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            companyNo.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Company No should be alphanumeric"));

            companyName.setImmediate(true);
            companyName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            companyName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Company Name should be alphanumeric"));

            businessUnitNo.setImmediate(true);
            businessUnitNo.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            businessUnitNo.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Business Unit No should be alphanumeric"));

            businessUnitName.setImmediate(true);
            businessUnitName.addValidator(new StringLengthValidator(StringConstantUtils.FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS, 0, NumericConstants.FIFTY, true));
            businessUnitName.addValidator(new RegexpValidator(ConstantsUtils.ALPHA_NUMERIC_CHARS, "Business Unit Name should be alphanumeric"));

            cffCreationDateFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cffCreationDateFrom.setImmediate(true);

            cffCreationDateTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cffCreationDateTo.setImmediate(true);

            cffApprovalDateFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cffApprovalDateFrom.setImmediate(true);

            cffApprovalDateTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
            cffApprovalDateTo.setImmediate(true);

            typeDdlb.focus();
            commonutil.loadComboBox(typeDdlb, ConstantsUtils.CFF_TYPE, false);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(NumericConstants.TEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultTable.setVisibleColumns(resultTableVisibleColumn);
            resultTable.setColumnHeaders(resultTableHeader);
            resultTable.setColumnCheckBox(CHECK_RECORD, true, false);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setSelectable(true);
            resultTable.setImmediate(true);
            resultTable.setEditable(true);
            resultTable.setPageLength(NumericConstants.TEN);
            resultTable.setHeight("455px");
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.addStyleName("filterbar");
            resultTable.addStyleName("filtertable");

            HorizontalLayout resultHorizontalLayout = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
            resultLayout.addComponent(resultTable);
            resultLayout.addComponent(resultHorizontalLayout);

            resultTable.setTableFieldFactory(new DefaultFieldFactory() {
                /**
                 *
                 */
                private static final long serialVersionUID = 1L;

                /**
                 * To create editable fields inside table .
                 */
                @Override
                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    if (String.valueOf(propertyId).equals(CHECK_RECORD)) {
                        final ProcessSchedulerDTO processSchedulerDTO = (ProcessSchedulerDTO) itemId;
                        final ExtCustomCheckBox checkbox = new ExtCustomCheckBox();
                        checkbox.setReadOnly(false);
                        checkbox.setImmediate(true);
                        checkbox.addClickListener(new ClickListener() {

                            @Override
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                if ((boolean) checkbox.getValue()) {
                                    cffLogic.updateTempCffOutbound(processSchedulerDTO, sessionDTO, Boolean.FALSE, (boolean) checkbox.getValue());
                                    processSchedulerDTO.setCheckRecord(true);
                                } else if (!processSchedulerDTO.getCheckRecord()) {
                                    cffLogic.updateTempCffOutbound(processSchedulerDTO, sessionDTO, Boolean.FALSE, (boolean) checkbox.getValue());
                                    processSchedulerDTO.setCheckRecord(false);
                                }
                            }
                        });

                        return checkbox;
                    }
                    return null;

                }
            });
            resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                    if (event.isChecked()) {
                        cffLogic.updateTempCffOutbound(null, sessionDTO, Boolean.TRUE, Boolean.TRUE);
                        loadGrid();
//                      this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox(CHECK_RECORD, true, true);
                    } else {
                        cffLogic.updateTempCffOutbound(null, sessionDTO, Boolean.TRUE, Boolean.FALSE);
                        loadGrid();
//                      this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox(CHECK_RECORD, true, false);
                    }

                }
            });
            LOGGER.debug("Exists ConsolidatedFinancialForecastForm Configure Field method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Search button logic
     *
     * @param event
     */
    @UiHandler("searchBtn")
    public void searchBtnClickLogic(Button.ClickEvent event) {
        try {
            if ((financialForecastId.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastId.getValue()))))
                    && (financialForecastName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastName.getValue()))))
                    && (typeDdlb.getValue() == null || String.valueOf(typeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE) || typeDdlb.getValue()==null)
                    && cffCreationDateFrom.getValue() == null && cffCreationDateTo.getValue() == null && cffApprovalDateFrom.getValue() == null && cffApprovalDateTo.getValue() == null
                    && (projectionId.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(projectionId.getValue()))))
                    && (customerNo.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(customerNo.getValue()))))
                    && (companyNo.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(companyNo.getValue()))))
                    && (projectionName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(projectionName.getValue()))))
                    && (customerName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(customerName.getValue()))))
                    && (contractNo.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(contractNo.getValue()))))
                    && (itemNo.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(itemNo.getValue()))))
                    && (businessUnitNo.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(businessUnitNo.getValue()))))
                    && (itemName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(itemName.getValue()))))
                    && (businessUnitName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(businessUnitName.getValue()))))
                    && (contractName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(contractName.getValue()))))) {

                AbstractNotificationUtils.getErrorNotification("Search Criteria", "Please enter Search Criteria.");

            } else {
                if (dateValidation()) {
                    searchButtonClickLogic();
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Method is for performing reset functionality
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                return;
            }

            @Override

            public void yesMethod() {
                financialForecastId.setValue(StringUtils.EMPTY);
                financialForecastName.setValue(StringUtils.EMPTY);
                typeDdlb.setValue(null);
                cffCreationDateFrom.setValue(null);
                cffCreationDateTo.setValue(null);
                cffApprovalDateFrom.setValue(null);
                cffApprovalDateTo.setValue(null);
                projectionId.setValue(StringUtils.EMPTY);
                customerNo.setValue(StringUtils.EMPTY);
                companyNo.setValue(StringUtils.EMPTY);
                projectionName.setValue(StringUtils.EMPTY);
                customerName.setValue(StringUtils.EMPTY);
                contractNo.setValue(StringUtils.EMPTY);
                itemNo.setValue(StringUtils.EMPTY);
                businessUnitNo.setValue(StringUtils.EMPTY);
                itemName.setValue(StringUtils.EMPTY);
                businessUnitName.setValue(StringUtils.EMPTY);
                contractName.setValue(StringUtils.EMPTY);
                companyName.setValue(StringUtils.EMPTY);
            }
        }.getConfirmationMessage("Reset Confirmation",
                "Are you sure you want to reset the Search Criteria?");
    }

    /**
     * Search button event Method performs If the records exist in the table for
     * current user id and session id it will delete it List of CFF details id's
     * will be return based on the search criteria List of CFF details id's will
     * be sent as input for the procedure input for inserting into temp table
     *
     * @param event
     */
    protected void searchButtonClickLogic() {
        try {
            cffSearchBinder.commit();
            resultsContainer.removeAllItems();
            cffLogic.deleteTempCffOutbound(sessionDTO, Boolean.FALSE);
            cffLogic.insertCFFOnSearch(sessionDTO, cffSearchBinder);
            if (!tableLogic.loadSetData(psDTO, Boolean.TRUE, sessionDTO)) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "There are no results that match the Search Criteria.", ButtonId.OK);
            } else {
                Notification.show("Search Completed");
            }
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setColumnCheckBox(CHECK_RECORD, true, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.loadSetData(psDTO, Boolean.TRUE, sessionDTO);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setSelectable(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method for CFF Outbound 1st instance is created for performing
     * synchronized job then we will lock the class so that other thread has to
     * wait until we unLock in order to perform in synchronized way
     *
     * @param event
     */
    @UiHandler("outboundBtn")
    public void outBountLogic(Button.ClickEvent event) {
        SchedulerSynchronizer process = SchedulerSynchronizer.getInstance(" Process scheduler ");
        try {
            process.lock();
            int i = 0;
            if (cffLogic.checkETLRecords(sessionDTO)) {
                cffLogic.runJob(getFtpBundleValue(), scriptName);
                cffLogic.updateLastRun(Integer.valueOf(processId), false);
                while (cffLogic.existsQuery(sessionDTO.getUserId(), sessionDTO.getSessionId())) {
                    // Waiting block for ETL to end
                    Thread.sleep(NumericConstants.THREE_THOUSAND);
                    i++;
                    if (i == NumericConstants.HUNDRED) {
                        deleteOnClose();
                        break;
                    }
                }
                loadGrid();
                resultTable.setColumnCheckBox(CHECK_RECORD, Boolean.TRUE, Boolean.FALSE);
                Notification notif = new Notification(displayName + confirmationMessage.getString("MSG_ID_045"), Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.show(Page.getCurrent());
                notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Record Selected.",
                        "Please select a CFF for Outbound.");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            com.stpl.ifs.ui.util.AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), displayName + confirmationMessage.getString("MSG_ID_046"));
        } finally {
            process.unlock();
        }
    }

    /**
     * Method for validating dates enter for searching
     *
     * @return
     */
    private Boolean dateValidation() {
        if (cffCreationDateFrom.getValue() == null && cffCreationDateTo.getValue() != null) {
            AbstractNotificationUtils.getErrorNotification(ERROR, "Please select CFF Creation From date");
            return Boolean.FALSE;
        } else if (cffCreationDateTo.getValue() != null && cffCreationDateFrom.getValue().after(cffCreationDateTo.getValue())) {
            AbstractNotificationUtils.getErrorNotification(ERROR, " CFF Creation To date cannot be after CFF Creation From date");
            return Boolean.FALSE;
        }

        if (cffApprovalDateFrom.getValue() == null && cffApprovalDateTo.getValue() != null) {
            AbstractNotificationUtils.getErrorNotification(ERROR, "Please select CFF Approval From date");
            return Boolean.FALSE;
        } else if (cffApprovalDateTo.getValue() != null && cffApprovalDateFrom.getValue().after(cffApprovalDateTo.getValue())) {
            AbstractNotificationUtils.getErrorNotification(ERROR, " CFF Approval To date cannot be after CFF Approval From date");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    public static final String ERROR = "Error";

    /**
     * Method is for deleting value if it exist for current user and session id
     */
    public void deleteOnClose() {
        LOGGER.debug("Inside Delete On Close");
        cffLogic.deleteTempCffOutbound(sessionDTO, Boolean.FALSE);
        LOGGER.debug("Ending Delete On Close");
    }

}
