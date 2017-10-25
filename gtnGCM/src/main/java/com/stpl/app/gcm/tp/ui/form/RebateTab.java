/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.RebateTabDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.stpl.app.gcm.tp.tablelogic.RebateTabTableLogic;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.ANNUALLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.YEAR;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.YEARS;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.QUARTERLY;
import static com.stpl.app.gcm.util.Constants.SPACE;
import com.stpl.app.gcm.util.CustomExcelExport;
import com.stpl.app.gcm.util.HeaderUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class RebateTab extends VerticalLayout {

    @UiField("tradingPartnerSalesTableLayout")
    VerticalLayout tradingPartnerSalesTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("frequency")
    public ComboBox frequency;
    @UiField("history")
    public ComboBox history;
    CustomTableHeaderDTO tableHeader = new CustomTableHeaderDTO();
    final private BeanItemContainer<String> historyBean = new BeanItemContainer<String>(String.class);
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(RebateTab.class);
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightDTO;
    CustomTableHeaderDTO leftDTO;
    private ExtTreeContainer<RebateTabDTO> resultBean = new ExtTreeContainer<RebateTabDTO>(RebateTabDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<Object, Object[]>();
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<Object, Object[]>();
    ExtFilterTreeTable leftTable;
    ExtFilterTreeTable rightTable;
    RebateTabTableLogic tableLogic = new RebateTabTableLogic();
    FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /*
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = NumericConstants.TWO_HUNDRED;
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    @UiField("mainPanel")
    public Panel panel;
    public TabSelectionDTO selectionDTO = new TabSelectionDTO();
    private ExtCustomTreeTable exportPeriodViewTable;
    private ExtTreeContainer<RebateTabDTO> excelResultBean = new ExtTreeContainer<RebateTabDTO>(RebateTabDTO.class,ExtContainer.DataStructureMode.MAP);
    LoadTabLogic tabLogic = new LoadTabLogic();
    boolean load = false;
    SessionDTO session;
    int projectionId = 0;

    public RebateTab(SessionDTO session, boolean isLoad) {
        this.load = isLoad;
        this.session = session;
        tableLogic.setSession(session);
        getContent();
    }

    public void getContent() {
        LOGGER.debug("getContent method starts");

        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/salesTab.xml"), this));
        panel.setCaption("Transfer Details-Rebates");
        configureTable();
        configureFields();
        LOGGER.debug("getContent method ends");

    }

    private void configureFields() {
        initializeResultTable();
        excelBtn.setIcon(excelExportImage);
        selectionDTO.setFrequency("QUARTER");
        frequency.addItem("Annually");
        frequency.addItem("Semi-Annually");
        frequency.addItem("Quarterly");
        frequency.addItem("Monthly");
        frequency.setValue("Quarterly");
        frequency.focus();

        history.addItem("4 Quarters");
        history.setValue("4 Quarters");
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        fullHeader = new CustomTableHeaderDTO();
        leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
        tableHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getSalesAndRebateColumns(tableHeader, fullHeader, NumericConstants.FOUR, "Quarterly", false);
        resultBean = new ExtTreeContainer<RebateTabDTO>(RebateTabDTO.class,ExtContainer.DataStructureMode.MAP);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setContainerDataSource(resultBean);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable
                .setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable
                .setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
        mapLeftVisibleColumns = leftDTO.getDoubleHeaderMaps();
        rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable
                .setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
        for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        mapRightVisibleColumns = rightDTO.getDoubleHeaderMaps();
        resultsTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
        rightTable
                .addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            public void doubleHeaderColumnCheck(
                    ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable
                .addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(
                    ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        tradingPartnerSalesTableLayout.addComponent(resultsTable);
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout = tableLogic.createControls();
        tradingPartnerSalesTableLayout.addComponent(hLayout);
    }

    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public void loadResultTable(SessionDTO session, int projectionId) {
        selectionDTO.setSessionID(Integer.parseInt(session.getSessionId()));
        selectionDTO.setCompanyMasterSids(session.getCompanyMasterSids());
        this.projectionId = projectionId;
        tableLogic.setProjectionResultsData(selectionDTO, projectionId);
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelButtonLogic(Button.ClickEvent event) {

        configureExcelResultTable();
        if (resultBean.size() > 0) {
            loadExcelResultTable();
        }
        exportPeriodViewTable.setRefresh(Boolean.TRUE);
        Map<String, String> formatter = new HashMap<String, String>();
        formatter.put("currencyNoDecimal", "Amount");
        formatter.put("perTwoDecimal", "Rate");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, Constants.TRUE);
        CustomExcelExport exp = new CustomExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), "Rebate Projection", "Rebate Projection", "Rebate_Projection.xls", false, formatter);
        exp.export();
        tradingPartnerSalesTableLayout.removeComponent(exportPeriodViewTable);

    }

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(RebateTabDTO.class,ExtContainer.DataStructureMode.MAP);
        exportPeriodViewTable = new ExtFilterTreeTable();
        tradingPartnerSalesTableLayout.addComponent(exportPeriodViewTable);
        excelResultBean.setColumnProperties(leftDTO.getProperties());
        excelResultBean.setColumnProperties(rightDTO.getProperties());
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exportPeriodViewTable.setDoubleHeaderVisible(true);
        exportPeriodViewTable.setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exportPeriodViewTable.setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));
        exportPeriodViewTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable() {
        excelResultBean.removeAllItems();
        List<RebateTabDTO> resultList = tabLogic.getConfiguredRebateTabResults(new Object(), selectionDTO, true);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<RebateTabDTO> resultList, Object parentId) {
        for (RebateTabDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        List<RebateTabDTO> resultList = tabLogic.getConfiguredRebateTabResults(id, selectionDTO, true);
        loadDataToContainer(resultList, id);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @UiHandler("history")
    public void historyChange(Property.ValueChangeEvent event) {
        if (history.getValue() != null) {
            tradingPartnerSalesTableLayout.removeAllComponents();
            String[] his = history.getValue().toString().split(" ");
            HeaderUtils header = new HeaderUtils();
            tableHeader = new CustomTableHeaderDTO();
            fullHeader = new CustomTableHeaderDTO();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
            tableHeader = new CustomTableHeaderDTO();
            rightDTO = header.getSalesAndRebateColumns(tableHeader, fullHeader, Integer.valueOf(his[0]), String.valueOf(frequency.getValue()), false);
            resultBean.setColumnProperties(leftDTO.getProperties());
            resultBean.setColumnProperties(rightDTO.getProperties());
            tableLogic.setTreeNodeMultiClick(false);
            tableLogic.setPageLength(NumericConstants.FIFTEEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            tableLogic.setContainerDataSource(resultBean);
            leftTable = resultsTable.getLeftFreezeAsTable();
            rightTable = resultsTable.getRightFreezeAsTable();
            leftTable.markAsDirty();
            rightTable.markAsDirty();
            leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
            leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
            leftTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
            leftTable.setColumnWidth("levelValue", NumericConstants.FOUR_HUNDRED);
            mapLeftVisibleColumns = leftDTO.getDoubleHeaderMaps();
            rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
            for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
                rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
            }
            rightTable.setDoubleHeaderVisible(true);
            rightTable.setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
            rightTable.setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
            for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
                rightTable.setColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
            }
            mapRightVisibleColumns = rightDTO.getDoubleHeaderMaps();
            resultsTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
            tradingPartnerSalesTableLayout.addComponent(resultsTable);
            HorizontalLayout hLayout = new HorizontalLayout();
            hLayout = tableLogic.createControls();
            tradingPartnerSalesTableLayout.addComponent(hLayout);
            if (Constants.QUARTERLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selectionDTO.setFrequency("QUARTER");
            } else if (Constants.SEMIANNUALLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selectionDTO.setFrequency("SEMI_ANNUAL");
            } else if (Constants.MONTHLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selectionDTO.setFrequency("MONTH");
            } else {
                selectionDTO.setFrequency("\"YEAR\"");
            }
            tableLogic.setProjectionResultsData(selectionDTO, projectionId);
        }
    }

    @UiHandler("frequency")
    public void loadFrequency(Property.ValueChangeEvent event) {
        String historyConstant = StringUtils.EMPTY;
        if ("Annually".equals(String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(loadHistory(Constants.ANNUALLY, YEAR.getConstant()));
            historyConstant = "1 Year";

        } else if ("Semi-Annually".equals(String.valueOf(frequency
                .getValue()))) {
            history.removeAllItems();
            historyBean.addAll(loadHistory(Constants.SEMIANNUALLY, SEMI_ANNUAL.getConstant()));
            historyConstant = "2 Semi-Annual";

        } else if (QUARTERLY.equals(
                String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(loadHistory(Constants.QUARTERLY, QUARTERS.getConstant()));
            historyConstant = "4 Quarters";
        } else if (MONTHLY.getConstant().equals(
                String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(loadHistory(Constants.MONTHLY, MONTHS.getConstant()));
            historyConstant = "NumericConstants.TWELVE Months";
        }

        history.setContainerDataSource(historyBean);
        history.setValue(historyConstant);

    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period) {
        List<String> history = new ArrayList<String>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.equals(frequency)) {
            endValue = NumericConstants.THREE;
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {

            endValue = NumericConstants.SIX;
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.equals(frequency)) {

            endValue = NumericConstants.TWELVE;
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {

            endValue = NumericConstants.THIRTY_SIX;
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1) && (QUARTERS.getConstant().equals(freq) || MONTHS.getConstant().equals(freq) || YEARS.getConstant().equals(freq))) {
                period = freq.replace("s", StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE + period);
            } else {
                history.add(String.valueOf(i) + SPACE + freq);
            }
        }

        return history;
    }
}
