/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.GTNbalanceLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportResultsTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.BRCommonUtils;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class BalanceReport extends CustomComponent {

    public static final Logger LOGGER = Logger.getLogger(BalanceReport.class);

    SessionDTO session;
    private BalanceReportDTO balanceReportDTO;
    @UiField("exportBtn")
    public Button exportBtn;
    @UiField("tableVerticalLayout")
    public VerticalLayout tableVerticalLayout;
    @UiField("liabilitySummaryBtn")
    public Button liabilitySummaryBtn;
    @UiField("frequencyDdlb")
    public ComboBox frequencyDdlb;
    @UiField("viewDdlb")
    public ComboBox viewDdlb;
    @UiField("itemIdentifierDdlb")
    public ComboBox itemIdentifierDdlb;
    @UiField("fromDate")
    public ComboBox fromDateDdlb;
    @UiField("toDate")
    public ComboBox toDateDdlb;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    protected CustomMenuBar.CustomMenuItem customMenuItem;

    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("filterTypeDdlb")
    public ComboBox filterTypeDdlb;
    @UiField("valueDdlb")
    public ComboBox valueDdlb;
    private ExtTreeContainer<BalanceReportDTO> resultsContainer = new ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The Excel container */
    ExtTreeContainer<BalanceReportDTO> excelContainer = new ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);
    CustomTableHeaderDTO headerDTO;
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    List<String> doubleHeaderList = new ArrayList<String>();
    List<String> selectedVariables = new ArrayList<String>();
    private BalanceReportLogic balanceRepLogic = new BalanceReportLogic();
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;

    /**
     * The split position.
     */
    private final float splitPosition = 300;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    BalanceReportResultsTableLogic tableLogic = new BalanceReportResultsTableLogic();
    public FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    BRCommonUtils commonUtils = new BRCommonUtils();

    public BalanceReport(final SessionDTO session, final BalanceReportDTO balanceReportDTO) {
        this.session = session;
        this.balanceReportDTO = balanceReportDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/balanceReport.xml"), this));
        configureFields();
    }

    /**
     *
     */
    protected void configureFields() {
        try {
            String[] variableValues = Constants.BasReportVariables.names();
            frequencyDdlb.focus();
            frequencyDdlb.setImmediate(true);
            frequencyDdlb.setNullSelectionAllowed(false);
            commonUtils.loadDDLBValue(frequencyDdlb, "LoadFrequencyValue");
            frequencyDdlb.setValue(Constants.FrequencyConstants.QUARTERLY.getConstant());

            itemIdentifierDdlb.setImmediate(true);
            itemIdentifierDdlb.setEnabled(false);
            itemIdentifierDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            itemIdentifierDdlb.setNullSelectionAllowed(true);
            itemIdentifierDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            commonUtils.loadDDLBValue(itemIdentifierDdlb, "LoadNDCValue");
            itemIdentifierDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

            viewDdlb.setImmediate(true);
            viewDdlb.setNullSelectionAllowed(false);
            commonUtils.loadDDLBValue(viewDdlb, "LoadLevelValue");
            viewDdlb.setValue(Constants.IndicatorConstants.CUSTOMER.getConstant());
            fromDateDdlb.setNullSelectionAllowed(false);
            toDateDdlb.setNullSelectionAllowed(false);

            filterTypeDdlb.setImmediate(true);
            filterTypeDdlb.setNullSelectionAllowed(false);
            filterTypeDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            commonUtils.loadDDLBValue(filterTypeDdlb, "LoadLevelValue");
            filterTypeDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            valueDdlb.setImmediate(true);
            customMenuBar.setImmediate(true);
            customMenuBar.setWidth("180px");
            customMenuItem = customMenuBar.addItem("-Select Variables-", null);
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableValues.length];
            for (int i = 0; i < variableValues.length; i++) {
                customItem[i] = customMenuItem.addItem(variableValues[i].trim(), null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);

                customItem[i].setItemClickNotClosable(true);
                if (i == 0) {
                    customItem[i].setCheckAll(true);
                }
            }
            loadSelectionCriteria();
            getCheckedValues();
            initializeResultTable();
            configureResultTable();
            addResultTable();
            loadResultTable();

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Configure Result Table.
     */
    @SuppressWarnings("serial")
    private void configureResultTable() {
        try {

            fullHeader = new CustomTableHeaderDTO();
            initializeDualTable();
            configureSelectionCriteria();
            leftHeader = HeaderUtils.getBalanceReportLeftTableColumns(fullHeader);
            rightHeader = HeaderUtils.getBalanceReportRightTableColumns(balanceReportDTO, fullHeader, doubleHeaderList, selectedVariables);
            resultsContainer.setColumnProperties(leftHeader.getProperties());
            resultsContainer.setColumnProperties(rightHeader.getProperties());
            //Mandatory do send information of visible indexed column to container
            List<Object> recordHeader = new ArrayList<Object>(rightHeader.getSingleColumns());
            recordHeader.add(0, "check.0");
            recordHeader.add(1, "customer.1");
            resultsContainer.setRecordHeader(recordHeader);
            tableLogic.sinkItemPerPageWithPageLength(false);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(10);

            leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
            leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
            leftTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
            leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
            leftTable.setColumnCheckBox("check.0", true);

            rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
            rightTable.setDoubleHeaderVisible(true);
            rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
            rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
            rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
            for (Object column : rightHeader.getDoubleColumns()) {
                rightTable.setDoubleHeaderColumnRadioButton(column, "radiobuttonforright");
            }
            for (Object obj : rightTable.getDoubleHeaderVisibleColumns()) {
                rightTable.setDoubleHeaderColumnAlignment(obj, ExtCustomTable.Align.CENTER);
            }
            for (Object propertyId : rightTable.getVisibleColumns()) {
                rightTable.setColumnWidth(propertyId, 125);
            }

            configureDualTable(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @UiHandler("liabilitySummaryBtn")
    public void liabilitySummaryBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering liabilitySummaryBtn method");

        final LiabilitySummaryPopup summaryPopup = new LiabilitySummaryPopup(session);
        UI.getCurrent().addWindow(summaryPopup);

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableVerticalLayout.addComponent(controlLayout);
    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName("center-radio");
    }

    @UiHandler("viewDdlb")
    public void loadItemIdentifier(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadItemIdentifier method");
        if (Constants.IndicatorConstants.ITEM.getConstant().equals(String.valueOf(viewDdlb.getValue()))) {
            itemIdentifierDdlb.setEnabled(true);
            itemIdentifierDdlb.setValue(Constants.IndicatorConstants.NDC8.getConstant());
        } else {
            itemIdentifierDdlb.setEnabled(false);
        }
        LOGGER.info("End of loadItemIdentifier method");
    }

    private void loadSelectionCriteria() throws Exception {
        LOGGER.info("From and To date in Balance REport::::" + session.getDsFromDate() + " :::::: " + session.getDsToDate());
        doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "BR", QUARTERLY.getConstant());
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @UiHandler("frequencyDdlb")
    public void loadFrequency(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Balance Report ValueChangeEvent initiated ");
        doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "BR", String.valueOf(frequencyDdlb.getValue()));
        LOGGER.info("Balance Report ValueChangeEvent ends ");
    }

    private void initializeDualTable() {
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setEditable(true);
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);

    }

    private void configureDualTable(final boolean includeLeftTable) {
        if (includeLeftTable) {
            leftTable.setTableFieldFactory(new TableFieldFactory() {
                public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                    if (propertyId.equals("check.0")) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();

                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                BalanceReportDTO trDto = (BalanceReportDTO) itemId;
                                Boolean checkValue = check.getValue();
                                trDto.addBooleanProperties(propertyId.toString(), checkValue);
                                trDto.setCheckRecord(checkValue);
                                balanceRepLogic.updateTempTableRecord(trDto, session, Constants.CHECK_RECORD);
                            }
                        });
                        return check;
                    }
                    return null;
                }
            });
            leftTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    Collection list = leftTable.getItemIds();
                    for (Object obj : list) {
                        tableLogic.getContainerDataSource().getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    }
                }
            });
        }
        rightTable.addDoubleHeaderColumnRadioCheckListener(new ExtCustomTable.DoubleHeaderColumnRadioCheckListener() {

            public void doubleHeaderColumnRadioCheck(ExtCustomTable.DoubleHeaderColumnRadioCheckEvent event) {
                String str = event.getCurrentValue();
                session.setChosenPeriod(StringUtils.isBlank(str) ? StringUtils.EMPTY : str);
            }
        });
    }

    private void loadResultTable() {
        balanceReportDTO.setQuery("gtnmultipleccpquery");
        try {
            tableLogic.setData(session, balanceReportDTO, selectedVariables, false, doubleHeaderList);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureSelectionCriteria() {
        BRCommonUtils brUtils = new BRCommonUtils();
        if ("VIEW".equals(session.getAction())) {
            BalanceReportLogic logic = new BalanceReportLogic();
            List<String> strList = logic.getSelectionDetails(session.getAcctCloserMasterId());
            frequencyDdlb.setValue(strList.get(0));
            fromDateDdlb.setValue(strList.get(1));
            toDateDdlb.setValue(strList.get(2));
            session.setChosenPeriod(strList.get(3));
            String variables[] = strList.get(4).split(",");
            viewDdlb.setValue(variables[0]);
            selectedVariables.clear();
            for (int i = 1; i <= variables.length - 1; i++) {
                String variable = variables[i];
                selectedVariables.add(variable.trim());
            }
            brUtils.getOrderedCheckedValue(selectedVariables);
            String frequency = String.valueOf(frequencyDdlb.getValue());
            String timePeriodFrom = String.valueOf(fromDateDdlb.getValue());
            String timePeriodTo = String.valueOf(toDateDdlb.getValue());
            String view = String.valueOf(viewDdlb.getValue());
            String itemIdentifier = StringUtils.EMPTY;
            if (itemIdentifierDdlb.isEnabled()) {
                itemIdentifier = String.valueOf(itemIdentifierDdlb.getValue());
            }
            balanceReportDTO.setFrequency(frequency);
            balanceReportDTO.setTimePeriodFrom(timePeriodFrom);
            balanceReportDTO.setTimePeriodTo(timePeriodTo);
            balanceReportDTO.setViewType(view);
            balanceReportDTO.setItemIdentifier(itemIdentifier);
            session.setSelectedPeriods(doubleHeaderList);
            session.setBalanceReportDTO(balanceReportDTO);
            session.setSelectedVariables(selectedVariables);
            enableDisableLogic(false);

        } else {
            String frequency = String.valueOf(frequencyDdlb.getValue());
            String timePeriodFrom = String.valueOf(fromDateDdlb.getValue());
            String timePeriodTo = String.valueOf(toDateDdlb.getValue());
            String view = String.valueOf(viewDdlb.getValue());
            String itemIdentifier = StringUtils.EMPTY;
            if (itemIdentifierDdlb.isEnabled()) {
                itemIdentifier = String.valueOf(itemIdentifierDdlb.getValue());
            }
            balanceReportDTO.setFrequency(frequency);
            balanceReportDTO.setTimePeriodFrom(timePeriodFrom);
            balanceReportDTO.setTimePeriodTo(timePeriodTo);
            balanceReportDTO.setViewType(view);
            balanceReportDTO.setItemIdentifier(itemIdentifier);
            session.setSelectedPeriods(doubleHeaderList);
            session.setBalanceReportDTO(balanceReportDTO);
            session.setSelectedVariables(selectedVariables);
            enableDisableLogic(true);
        }
    }

    @UiHandler("populateBtn")
    public void populateBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering populateBtn method");
        try {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
            tableVerticalLayout.removeAllComponents();
            tableLogic = new BalanceReportResultsTableLogic();
            getCheckedValues();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            populateBtnLogic();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending populateBtn method");
    }

    private void populateBtnLogic() {
        loadResultTable();
    }

    public void saveSelection() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(session.getChosenPeriod())) {
            map.put("Frequency", String.valueOf(frequencyDdlb.getValue()));
            map.put("fromDateDdlb", String.valueOf(fromDateDdlb.getValue()));
            map.put("toDateDdlb", String.valueOf(toDateDdlb.getValue()));
            map.put("chosenPeriod", String.valueOf(session.getChosenPeriod()));
            map.put("view", String.valueOf(viewDdlb.getValue()));
            String str = StringUtils.EMPTY;
            for (String variables : selectedVariables) {
                if (StringUtils.EMPTY.equals(str)) {
                    str += variables;
                } else {
                    str += "," + variables;
                }
            }
            map.put("selectedVariables", str);
            new GTNbalanceLogic().saveSelection(map, session.getAcctCloserMasterId(), "GTN Balance Report");
            session.setSaveFlag(true);
        } else {
            MessageBox.showPlain(Icon.INFO, "Missing Selection", "Please select a period from the list view.", ButtonId.OK);
        }

    }

    private void enableDisableLogic(boolean b) {
        frequencyDdlb.setEnabled(b);
        fromDateDdlb.setEnabled(b);
        toDateDdlb.setEnabled(b);
        viewDdlb.setEnabled(b);
        populateBtn.setEnabled(b);
        resetBtn.setEnabled(b);
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering resetBtn method");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    frequencyDdlb.setValue(Constants.FrequencyConstants.QUARTERLY.getConstant());
                    itemIdentifierDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    viewDdlb.setValue(Constants.IndicatorConstants.CUSTOMER.getConstant());
                    doubleHeaderList = DataSelectionUtil.configureTimeDdlb(fromDateDdlb, toDateDdlb, session.getDsFromDate(), session.getDsToDate(), StringUtils.EMPTY, "BR", String.valueOf(frequencyDdlb.getValue()));

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset screen ?\n");

        LOGGER.info("Ending resetBtn method");
    }

    @UiHandler("exportBtn")
    public void excelBtnClick(Button.ClickEvent event) {
        try {
            tableVerticalLayout.addComponent(excelTable);
            excelTable.setVisible(false);

            excelContainer = new ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);
            excelContainer.setColumnProperties(fullHeader.getProperties());
            List<Object> recordHeader = new ArrayList<Object>(fullHeader.getSingleColumns());
            recordHeader.add(0, "customer.0");
            excelContainer.setRecordHeader(recordHeader);
            excelTable.setContainerDataSource(excelContainer);
            excelTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
            excelTable.setDoubleHeaderVisible(true);
            excelTable
                    .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
            excelTable
                    .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

            excelTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
            configureSelectionCriteria();
            balanceReportDTO.setLevelNo(1);
            int count1 = balanceRepLogic.getBalanceReportCount(new Object(), session, balanceReportDTO, selectedVariables, true);
            excelContainer.setColumnProperties(leftHeader.getProperties());
            excelContainer.setColumnProperties(rightHeader.getProperties());
            List<BalanceReportDTO> report = balanceRepLogic.getBalanceReportResults(new Object(), 0, count1, session, balanceReportDTO, selectedVariables, true, doubleHeaderList, true);
            excelContainer.removeAllItems();
            loadDataToContainer(report, null);
            Map<String, String> formatter = new HashMap<String, String>();
            formatter.put("currencyTwoDecimal", "Amount");
            formatter.put("percentTwoDecimal", "Rate");
            LOGGER.info("excelContainer" + excelContainer.size() + excelTable.size());
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Balance Report", "Balance Report", "Balance_Report.xls", false);
            export.export();
            tableVerticalLayout.removeComponent(excelTable);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     *
     * @param report
     * @param parentId
     */
    public void loadDataToContainer(List<BalanceReportDTO> report, Object parentId) {
        try {
            LOGGER.info("Inside loadDataToContainer" + report.size());
            for (BalanceReportDTO dto : report) {
                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }
                if (dto.getParent() == 1) {
                    excelContainer.setChildrenAllowed(dto, true);
                    addLowerLevelsForExport(dto);
                } else {
                    excelContainer.setChildrenAllowed(dto, false);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended loadDataToContainer");

    }

    /**
     *
     * @param id
     */
    public void addLowerLevelsForExport(Object id) {
        LOGGER.info("Inside addLowerLevelsForExport");
        int count1 = balanceRepLogic.getBalanceReportCount(id, session, balanceReportDTO, selectedVariables, true);
        List<BalanceReportDTO> report = balanceRepLogic.getExpandLevelResult(id, 0, count1, session, balanceReportDTO, selectedVariables, true, doubleHeaderList);
        loadDataToContainer(report, id);
        LOGGER.info("Ended addLowerLevelsForExport");
    }

    @UiHandler("filterTypeDdlb")
    public void loadFilterValueDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Loading the Filter Value Ddlb initiated ");
        detachListeners(valueDdlb);
        String filterType = String.valueOf(filterTypeDdlb.getValue());
        valueDdlb = commonLoadingDdlb(valueDdlb, filterType);
        attachListeners(valueDdlb);
        LOGGER.info("Loading the Filter Value Ddlb ends ");
    }

    public ComboBox commonLoadingDdlb(ComboBox comboBox, final String filterType) throws Exception {
        if (!Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(filterType)) {
            List<HelperDTO> dtos = balanceRepLogic.getFilterValues(filterType, session);
            comboBox = CommonLogic.getComboBox(comboBox, dtos);
            comboBox.setNullSelectionAllowed(false);
        }
        return comboBox;
    }

    @UiHandler("valueDdlb")
    public void expandTreeLogic(Property.ValueChangeEvent event) throws Exception {
        String filterValue = String.valueOf(valueDdlb.getValue());
        String view = String.valueOf(viewDdlb.getValue());
        if (String.valueOf(filterValue).equals("0")) {
            loadResultTable();
        } else {
            String filterType = String.valueOf(filterTypeDdlb.getValue());

            try {
                tableLogic.loadExpandData(view, filterType, filterValue);
            } catch (Exception e) {
                LOGGER.error(e);
            }

        }
    }

    public void attachListeners(final AbstractField field) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    expandTreeLogic(event);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    public void detachListeners(final AbstractField field) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
    }

    protected void getCheckedValues() {
        selectedVariables = commonUtils.getCurrentCheckValue(selectedVariables, customMenuItem);
    }

}
