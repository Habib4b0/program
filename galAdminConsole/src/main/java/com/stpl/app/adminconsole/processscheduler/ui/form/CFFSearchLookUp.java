/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.CFFIndexTableLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CFFFilterGenerator;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;

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

    ProcessSchedulerDTO psDTO = new ProcessSchedulerDTO();

    public CustomFieldGroup cffSearchBinder = new CustomFieldGroup(new BeanItem<ProcessSchedulerDTO>(psDTO));

    @UiField("financialForecastIdLabel")
    private Label financialForecastIdLabel;

    @UiField("financialForecastId")
    private TextField financialForecastId;

    @UiField("typeLabel")
    private Label typeLabel;

    @UiField("typeDdlb")
    private ComboBox typeDdlb;

    @UiField("financialForecastNameLabel")
    private Label financialForecastNameLabel;

    @UiField("financialForecastName")
    private TextField financialForecastName;

    @UiField("statusLabel")
    private Label statusLabel;

    @UiField("statusDdlb")
    private ComboBox statusDdlb;

    @UiField("creationFromDate")
    private PopupDateField creationFromDate;

    @UiField("creationToDate")
    private PopupDateField creationToDate;

    @UiField("approvalFromDate")
    private PopupDateField approvalFromDate;

    @UiField("approvalToDate")
    private PopupDateField approvalToDate;

    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("outboundBtn")
    private Button outboundBtn;

    @UiField("creationFrom")
    private Label creationFrom;

    @UiField("creationTo")
    private Label creationTo;

    @UiField("approvalFrom")
    private Label approvalFrom;

    @UiField("approvalTo")
    private Label approvalTo;

    @UiField("resultLayout")
    private VerticalLayout resultLayout;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();

    final ErrorLabel errorMsg = new ErrorLabel();

    private final BeanItemContainer<ProcessSchedulerDTO> resultsContainer = new BeanItemContainer<ProcessSchedulerDTO>(ProcessSchedulerDTO.class);
    SessionDTO sessionDTO;
    Set checkedCffsSet = new HashSet();
    CommonUtils commonutil = new CommonUtils();
    CFFIndexTableLogic tableLogic = new CFFIndexTableLogic();
    public ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    public static final Object[] RESULT_TABLE_VISIBLE_COLUMN = new Object[]{"checkRecord", "financialForecastId", "financialForecastName", "typeDesc", "statusDesc",
        "finalApprovalDate", "approvedBy", "activeFromDate", "activeToDate"};

    public static final String[] RESULT_TABLE_HEADER = new String[]{ConstantsUtils.EMPTY, "Financial Forecast ID", "Financial Forecast Name", "Type", "Status",
        "Final Approval Date", "Approved By", "Active From Date", "Active To Date"};

    public static final Object[] EXCEL_OUTBOUND_VISIBLE_COLUMN = new Object[]{"financialForecastName", "cffType", "projectionName",
        "contract", "customer", "item", "salesDollars", "salesUnits", "rebateDollars", "rebateRate", "rebatePerUnit",
        "netSalesDollars", "costOfGoodsSoldDollars", "costOfGoodsSoldUnits", "netProfutDollars", "netProfitUnits", "year", "month"

    };

    public static final String[] EXCEL_OUTBOUND_HEADER = new String[]{"Financial Forecast Name", "Type", "Projection Name",
        "Contract", "Customer", "Item", "Sales Dollars", "Sales Units", "Rebate Dollars", "Rebate Rate", "Rebate Per Unit",
        "Net Sales Dollars", "Cost of Goods Sold Dollars (COGS by product)", "Cost of Goods Sold Per Unit (COGS by product)",
        "Net Profit Dollars", "Net Profit Per Unit", "Year", "Month"

    };

    List<ProcessSchedulerDTO> reportList = null;

    ExtTreeContainer<ProcessSchedulerDTO> excelContainer
            = new ExtTreeContainer<ProcessSchedulerDTO>(ProcessSchedulerDTO.class);

    /**
     * ConsolidatedFinancialForecastForm constructor
     *
     * @param sessionDTO
     */
    public CFFSearchLookUp(final SessionDTO sessionDTO) {
        LOGGER.info("Entering init method");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/CFFSearch.xml"), this));
        setId("CFF SEARCH");
        setCaption("CFF SEARCH");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth(1050, Sizeable.Unit.PIXELS);
        setHeight("800px");
        this.sessionDTO = sessionDTO;
        getBinder();
        configureFields();

        LOGGER.info("init method Ends");
    }

   
    private CustomFieldGroup getBinder() {
        cffSearchBinder.bindMemberFields(this);
        cffSearchBinder.setItemDataSource(new BeanItem<ProcessSchedulerDTO>(psDTO));
        cffSearchBinder.setBuffered(true);
        cffSearchBinder.setErrorDisplay(errorMsg);
        return cffSearchBinder;
    }

  
    private void configureFields() {
        try {
            LOGGER.info("Enters ConsolidatedFinancialForecastForm Configure Field method");
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("CFF");
            financialForecastId.setImmediate(true);
            financialForecastId.addValidator(new StringLengthValidator("Financial Forecast Id should be less than 50 characters", 0, 50, true));
            financialForecastId.addValidator(new RegexpValidator(ConstantsUtils.alphaNumericChars, "Financial Forecast Id should be alphanumeric"));

            financialForecastName.setImmediate(true);
            financialForecastName.addValidator(new StringLengthValidator("Financial Forecast Name should be less than 50 characters", 0, 50, true));
            financialForecastName.addValidator(new RegexpValidator(ConstantsUtils.alphaNumericChars, "Financial Forecast Name should be alphanumeric"));

            creationFromDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            creationToDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            approvalFromDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            approvalToDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

            creationFrom.addStyleName("fromTo");
            creationTo.addStyleName("fromTo");
            approvalFrom.addStyleName("fromTo");
            approvalTo.addStyleName("fromTo");

            typeDdlb.focus();
            commonutil.loadComboBox(typeDdlb, ConstantsUtils.CFF_TYPE, false);
            commonutil.loadComboBox(statusDdlb, ConstantsUtils.WORKFLOW_STATUS, false);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultTable.setVisibleColumns(RESULT_TABLE_VISIBLE_COLUMN);
            resultTable.setColumnHeaders(RESULT_TABLE_HEADER);
            resultTable.setColumnAlignment(RESULT_TABLE_VISIBLE_COLUMN[4], ExtCustomTable.Align.CENTER);
            resultTable.setColumnAlignment(RESULT_TABLE_VISIBLE_COLUMN[6], ExtCustomTable.Align.CENTER);
            resultTable.setColumnAlignment(RESULT_TABLE_VISIBLE_COLUMN[7], ExtCustomTable.Align.CENTER);
            resultTable.setColumnCheckBox("checkRecord", true, false);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setSelectable(true);
            resultTable.setImmediate(true);
            resultTable.setEditable(true);
            resultTable.setPageLength(10);
            resultTable.setHeight("455px");
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.addStyleName("filterbar");
            resultTable.addStyleName("filtertable");
            resultTable.setConverter("finalApprovalDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            resultTable.setConverter("activeFromDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            resultTable.setConverter("activeToDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });

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
                    if (String.valueOf(propertyId).equals("checkRecord")) {
                        final ProcessSchedulerDTO processSchedulerDTO=  ((ProcessSchedulerDTO)itemId);
                        final CheckBox checkbox = new CheckBox();
                        checkbox.setReadOnly(false);
                        checkbox.setImmediate(true);
                        if (checkedCffsSet.contains(processSchedulerDTO.getCffMasterSid())) {
                            checkbox.setValue(true);
                            processSchedulerDTO.setCheckRecord(true);
                        }
                        checkbox.addValueChangeListener(new Property.ValueChangeListener() {

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                if ((boolean) checkbox.getValue()) {
                                    checkedCffsSet.add(processSchedulerDTO.getCffMasterSid());
                                    processSchedulerDTO.setCheckRecord(true);
                                } else if (!processSchedulerDTO.getCheckRecord()) {
                                    checkedCffsSet.remove(processSchedulerDTO.getCffMasterSid());
                                    processSchedulerDTO.setCheckRecord(false);
                                }
                            }
                        });

                        return checkbox;
                    }
                    return null;

                }
            }
            );
            resultTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            
                    if (event.isChecked()) {
                        checkedCffsSet.clear();
                        loadGrid(true);
//                      this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox("checkRecord", true, true);
                    } else {
                        checkedCffsSet.clear();
                        loadGrid(false);
//                      this setCurrentPage is used to refresh the lazy conatiner
                        resultTable.setCurrentPage(resultTable.getCurrentPage());
                        resultTable.setColumnCheckBox("checkRecord", true, false);
                    }
                
            }
        });
            LOGGER.info("Exists ConsolidatedFinancialForecastForm Configure Field method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("outboundBtn")
    public void outboundBtnLogic(Button.ClickEvent event) {
       if (!checkedCffsSet.isEmpty()|| resultTable.getColumnCheckBox("checkRecord")) {
            new AbstractNotificationUtils() {
                public void noMethod() {

                }

                @Override
              
                public void yesMethod() {

                    LOGGER.info("Entering EXCEL Export Button Click");
                    try {
                        boolean checkedAll = false;
                        if (resultTable.getColumnCheckBox("checkRecord")) {
                            checkedAll = true;
                        }
                        Long startTime = System.currentTimeMillis();
                        if(checkedAll){
                            ProcessSchedulerLogic outboundLogic = new ProcessSchedulerLogic();
                            checkedCffsSet = outboundLogic.getAllCheckedCffIds(psDTO);
                            createWorkSheet("CffOutbound");
                            checkedCffsSet.clear();
                        }else{
                        createWorkSheet("CffOutbound");
                        }
                        Long endTime = System.currentTimeMillis();
                        LOGGER.info("_____CFF_____Total Time taken===in milli sesconds=" + (endTime - startTime));
                        LOGGER.info("____CFF______Total Time taken==in seconds==" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    LOGGER.info(" Ends  EXCEL Export Button Click");

                }

            }.getConfirmationMessage("Outbound Confirmation",
                    "Are you sure you want to generate Outbound?");
        } else {
            AbstractNotificationUtils.getErrorNotification("No Record Selected.",
                    "Please select a CFF for Outbound.");
        }
        
    }

    @UiHandler("searchBtn")
    public void searchBtnClickLogic(Button.ClickEvent event) {
        try {
            boolean pass = true;
            if ((financialForecastId.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastId.getValue()))))
                    && (financialForecastName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastName.getValue()))))
                    && (typeDdlb.getValue() == null || String.valueOf(typeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE) || String.valueOf(typeDdlb.getValue()).equals(null))
                    && (statusDdlb.getValue() == null || String.valueOf(statusDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE) || String.valueOf(statusDdlb.getValue()).equals(null))
                    && creationFromDate.getValue() == null && creationToDate.getValue() == null && approvalFromDate.getValue() == null && approvalToDate.getValue() == null) {
                pass = false;
                AbstractNotificationUtils.getErrorNotification("Search Criteria", "Please enter Search Criteria.");

            } else {
                if (creationFromDate.getValue() != null && creationToDate.getValue() != null) {
                    if (creationFromDate.getValue().after(creationToDate.getValue())) {
                        pass = false;
                        AbstractNotificationUtils.getErrorNotification("Date Error", "Creation From date should be before Creation To date");
                    }
                }
                if (approvalFromDate.getValue() != null && approvalToDate.getValue() != null) {
                    if (approvalFromDate.getValue().after(approvalToDate.getValue())) {
                        pass = false;
                        AbstractNotificationUtils.getErrorNotification("Date Error", "Approval From date should be before Approval To date");
                    }
                }
            }
            if (pass) {
                searchButtonClickLogic(event);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {

            }

            @Override
           
            public void yesMethod() {
                financialForecastId.setValue(StringUtils.EMPTY);
                financialForecastName.setValue(StringUtils.EMPTY);
                typeDdlb.setValue(ConstantsUtils.SELECT_ONE);
                statusDdlb.setValue(ConstantsUtils.SELECT_ONE);
                creationFromDate.setValue(null);
                creationToDate.setValue(null);
                approvalFromDate.setValue(null);
                approvalToDate.setValue(null);
            }
        }.getConfirmationMessage("Reset Confirmation",
                "Are you sure you want to reset the Search Criteria?");
    }

   
    protected void searchButtonClickLogic(final Button.ClickEvent event) {
        try {
            checkedCffsSet = new HashSet();
            cffSearchBinder.commit();
            resultsContainer.removeAllItems();
            if (!tableLogic.loadSetData(psDTO, Boolean.TRUE,false)) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "There are no results that match the Search Criteria.", ButtonId.OK);
            } else {
                Notification.show("Search Completed");
            }
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.setImmediate(true);
            resultTable.setColumnCheckBox("checkRecord", true, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void createWorkSheet(String moduleName) throws SystemException, PortalException, Exception {
        long recordCount = 0;
        try {
            reportList = ProcessSchedulerLogic.getResultsForCffOutbound(checkedCffsSet);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        recordCount = reportList.size();
        ExcelExportforBB.createWorkSheet(EXCEL_OUTBOUND_HEADER, recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                ExcelExportforBB.createFileContent(EXCEL_OUTBOUND_VISIBLE_COLUMN, reportList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
/**
     * TO load the grid
     */
    private void loadGrid(boolean isCheckAll) {
        try {

            tableLogic.loadSetData(psDTO, Boolean.TRUE,isCheckAll);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.setImmediate(true);           
            resultTable.setSelectable(true);
          
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
