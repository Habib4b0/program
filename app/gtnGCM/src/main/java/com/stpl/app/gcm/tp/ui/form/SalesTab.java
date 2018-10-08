/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.SalesTabDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.stpl.app.gcm.tp.tablelogic.SalesTabTableLogic;
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
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class SalesTab extends VerticalLayout {

    @UiField("tradingPartnerSalesTableLayout")
    private VerticalLayout tradingPartnerSalesTableLayout;
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("frequency")
    public ComboBox frequency;
    @UiField("history")
    public ComboBox history;
    private CustomTableHeaderDTO tableHeader = new CustomTableHeaderDTO();
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesTab.class);
    
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightDTO;
    private CustomTableHeaderDTO leftDTO;
    private ExtTreeContainer<SalesTabDTO> resultBean = new ExtTreeContainer<>(SalesTabDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<>();
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<>();
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    private SalesTabTableLogic tableLogic = new SalesTabTableLogic();
    private FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /**
     * The max split position.
     */
    private static final float MAX_SPLIT_POSITION = 1000;
    /**
     * The min split position.
     */
    private static final float MIN_SPLIT_POSITION = NumericConstants.TWO_HUNDRED;
    /**
     * The split position.
     */
    private static final float SPLIT_POSITION = 300;
    private final TabSelectionDTO selectionDTO = new TabSelectionDTO();
    private ExtCustomTreeTable exportSalesPeriodViewTable;
    private ExtTreeContainer<SalesTabDTO> excelSalesBean = new ExtTreeContainer<>(SalesTabDTO.class, ExtContainer.DataStructureMode.MAP);
    private final LoadTabLogic tabLogic = new LoadTabLogic();
    private final BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
    private final SessionDTO session;
    private boolean load = false;
    private int projectionId = 0;

    public SalesTab(SessionDTO session, boolean isLoad) {
        this.session = session;
        this.load = isLoad;
        tableLogic.setSession(session);
        getContent();
    }

    public final void getContent() {
        LOGGER.debug("getContent method starts");

        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/salesTab.xml"), this));
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
        frequency.addItem(Constants.QUARTERLY);
        frequency.addItem("Monthly");
        frequency.setValue(Constants.QUARTERLY);
        frequency.focus();

        history.addItem(StringConstantsUtil.FOUR_QUARTERS);
        history.setValue(StringConstantsUtil.FOUR_QUARTERS);

        selectionDTO.setSessionID(Integer.parseInt(session.getSessionId()));
        selectionDTO.setCompanyMasterSids(session.getCompanyMasterSids());
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        fullHeader = new CustomTableHeaderDTO();
        leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
        tableHeader = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getSalesAndRebateColumns(tableHeader, fullHeader, NumericConstants.FOUR, Constants.QUARTERLY, true);
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
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(
                    ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });

        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(
                    ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked()
                        + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        tradingPartnerSalesTableLayout.addComponent(resultsTable);
        HorizontalLayout hLayout;
        hLayout = tableLogic.createControls();
        tradingPartnerSalesTableLayout.addComponent(hLayout);
    }

    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setSplitPosition(SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(MIN_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(MAX_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public void loadResultTable(SessionDTO session, int projectionId) {
        this.projectionId = projectionId;
        selectionDTO.setSessionID(Integer.parseInt(session.getSessionId()));
        selectionDTO.setCompanyName(session.getCompanyName());
        selectionDTO.setCompanyMasterSids(session.getCompanyMasterSids());
        selectionDTO.setContractMasterSid(session.getContMasteSid());
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
        if (!resultBean.isEmpty()) {
            loadExcelResultTable();
        }
        exportSalesPeriodViewTable.setRefresh(BooleanConstant.getTrueFlag());
        Map<String, String> formatter = new HashMap<>();
        formatter.put("currencyNoDecimal", "Sales");
        formatter.put("unitOneDecimal", "Units");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, Constants.TRUE);
        CustomExcelExport exp = new CustomExcelExport(new ExtCustomTableHolder(exportSalesPeriodViewTable), "Sales Projection", "Sales Projection", "Sales_Projection.xls", false, formatter);
        exp.export();
        tradingPartnerSalesTableLayout.removeComponent(exportSalesPeriodViewTable);

    }

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    private void configureExcelResultTable() {
        excelSalesBean = new ExtTreeContainer<>(SalesTabDTO.class,ExtContainer.DataStructureMode.MAP);
        exportSalesPeriodViewTable = new ExtFilterTreeTable();
        tradingPartnerSalesTableLayout.addComponent(exportSalesPeriodViewTable);
        excelSalesBean.setColumnProperties(leftDTO.getProperties());
        excelSalesBean.setColumnProperties(rightDTO.getProperties());
        exportSalesPeriodViewTable.setContainerDataSource(excelSalesBean);
        exportSalesPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportSalesPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exportSalesPeriodViewTable.setDoubleHeaderVisible(true);
        exportSalesPeriodViewTable.setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exportSalesPeriodViewTable.setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));
        exportSalesPeriodViewTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

 
    @SuppressWarnings("serial")
    private void loadExcelResultTable() {
        excelSalesBean.removeAllItems();
        List<SalesTabDTO> resultList = tabLogic.getConfiguredSalesTabResults(new Object(), selectionDTO, true);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<SalesTabDTO> resultList, Object parentId) {
        for (SalesTabDTO dto : resultList) {
            excelSalesBean.addBean(dto);
            if (parentId != null) {
                excelSalesBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelSalesBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelSalesBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        List<SalesTabDTO> resultList = tabLogic.getConfiguredSalesTabResults(id, selectionDTO, true);
        loadDataToContainer(resultList, id);
    }

    public void enter() {
        return;
    }

    public ExtTreeContainer<SalesTabDTO> getResultBean() {
        return resultBean;
    }

    public void setResultBean(ExtTreeContainer<SalesTabDTO> resultBean) {
        this.resultBean = resultBean;
    }

    public SalesTabTableLogic getTableLogic() {
        return tableLogic;
    }

    public void setTableLogic(SalesTabTableLogic tableLogic) {
        this.tableLogic = tableLogic;
    }

    public ExtFilterTreeTable getLeftTable() {
        return leftTable;
    }

    public void setLeftTable(ExtFilterTreeTable leftTable) {
        this.leftTable = leftTable;
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
            tableHeader = new CustomTableHeaderDTO();
            fullHeader = new CustomTableHeaderDTO();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
            tableHeader = new CustomTableHeaderDTO();
            rightDTO = HeaderUtils.getSalesAndRebateColumns(tableHeader, fullHeader, Integer.parseInt(his[0]), String.valueOf(frequency.getValue()), true);
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
            HorizontalLayout hLayout;
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
            historyConstant = StringConstantsUtil.FOUR_QUARTERS;
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
    protected final List<String> loadHistory(String frequency, String periodParam) {
        List<String> historyload = new ArrayList<>();
        String period = periodParam;
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
                historyload.add(String.valueOf(i) + SPACE + period);
            } else {
                historyload.add(String.valueOf(i) + SPACE + freq);
            }
        }

        return historyload;
    }

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}
}
