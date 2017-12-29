
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.summary;

import com.stpl.app.gcm.util.StringConstantsUtil;
import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.FrequencyConstants.SELECT_ONE;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractSummaryDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.SummaryTableLogic;
import com.stpl.app.gcm.itemmanagement.remove.logic.RemoveItemLogic;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.YEAR;
import static com.stpl.app.gcm.util.Constants.QUARTERLY;
import com.stpl.app.gcm.util.HeaderUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Srithar
 */
public abstract class Summary extends CustomComponent {

    @UiField("tradingPartnerSalesTableLayout")
    protected VerticalLayout tradingPartnerSalesTableLayout;
    @UiField("excelBtn")
    private Button excelBtn;
    @UiField("mainPanel")
    protected Panel mainPanel;
    private final ComboBox frequency = new ComboBox();
    private final ComboBox history = new ComboBox();
    private final SummaryTableLogic deletetableLogic = new SummaryTableLogic();
    private FreezePagedTreeTable deletedetailtable = new FreezePagedTreeTable(deletetableLogic);
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightDTO;
    protected SelectionDTO selection;
    @UiField("horizontalLayout1")
    private HorizontalLayout horizontalLayout1;
    @UiField("verti")
    private VerticalLayout verti;
    private final ExtTreeContainer<AbstractSummaryDTO> resultBean = new ExtTreeContainer<>(AbstractSummaryDTO.class,ExtContainer.DataStructureMode.MAP);
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
    private List<ItemIndexDto> itemList;
    protected ExtCustomTreeTable exportPeriodViewTable;
    protected ExtTreeContainer<AbstractSummaryDTO> excelResultBean = new ExtTreeContainer<>(AbstractSummaryDTO.class,ExtContainer.DataStructureMode.MAP);
    protected final RemoveItemLogic logic = new RemoveItemLogic();
    protected final TabSelectionDTO selectionDTO = new TabSelectionDTO();

    public Summary(List<ItemIndexDto> itemList, SelectionDTO selection) {
        this.selection = selection;
        this.itemList = itemList;
    }

    public Component getContent(List<ItemIndexDto> selecteditemList, final SelectionDTO selection) {
        this.selection = selection;
        this.itemList = selecteditemList;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/salesTab.xml"), this));
        layout.setMargin(true);
        layout.setSpacing(true);
        Panel panel = new Panel();
        panel.setContent(layout);
        configureFields();
        ConfigureSalesSummaryTable();
        loadResultTable();
        return panel;
    }

    void ConfigureSalesSummaryTable() {
        fullHeader = new CustomTableHeaderDTO();
        String[] his = history.getValue().toString().split(" ");
        selectionDTO.setFrequency(StringConstantsUtil.QUARTER_LABEL);
        selectionDTO.setSessionID(selection.getSessionId());
        selectionDTO.setOperation(selection.getButtonMode());
        selectionDTO.setItemList(itemList);
        CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
        getRightVisibleColumns(his, String.valueOf(frequency.getValue()));
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        deletetableLogic.setTreeNodeMultiClick(false);
        deletetableLogic.setPageLength(NumericConstants.TEN);
        deletetableLogic.sinkItemPerPageWithPageLength(false);
        deletetableLogic.setContainerDataSource(resultBean);
        leftTable = deletedetailtable.getLeftFreezeAsTable();
        rightTable = deletedetailtable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
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
        deletedetailtable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
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

        deletedetailtable.setSizeFull();
        deletedetailtable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tradingPartnerSalesTableLayout.addComponent(deletedetailtable);
        HorizontalLayout controls = deletetableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tradingPartnerSalesTableLayout.addComponent(controlLayout);
    }

    public void configureFields() {

        setMainPanelCaption();
        excelBtn.setIcon(excelExportImage);
        HorizontalLayout frequencyLayout = new HorizontalLayout();
        frequencyLayout.addComponent(new Label("Frequency"));
        frequencyLayout.addComponent(frequency);
        frequencyLayout.addComponent(new Label("History"));
        frequencyLayout.addComponent(history);
        tradingPartnerSalesTableLayout.addComponent(frequencyLayout);
        verti.removeComponent(horizontalLayout1);
        frequency.focus();
        frequency.addItem(SELECT_ONE.getConstant());
        frequency.setNullSelectionItemId(SELECT_ONE.getConstant());
        history.addItem(SELECT_ONE.getConstant());
        history.setNullSelectionItemId(SELECT_ONE.getConstant());
        selection.getTabSelection().setFrequency(StringConstantsUtil.QUARTER_LABEL);

        frequency.addItem("Annually");
        frequency.addItem("Semi-Annually");
        frequency.addItem("Quarterly");
        frequency.addItem("Monthly");
        frequency.setValue("Quarterly");
        frequency.focus();
        history.addItem(StringConstantsUtil.FOUR_QUARTERS);
        history.setValue(StringConstantsUtil.FOUR_QUARTERS);
        frequency.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                frequenyChangeLogic();
            }
        });
        history.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                historyChangeLogic();
            }
        });
    }

    public void loadResultTable() {
        deletetableLogic.setProjectionResultsData(itemList, selection, isSales());
    }

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    public void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(AbstractSummaryDTO.class,ExtContainer.DataStructureMode.MAP);
        exportPeriodViewTable = new ExtFilterTreeTable();
        tradingPartnerSalesTableLayout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(Boolean.FALSE);
        exportPeriodViewTable.setVisible(false);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exportPeriodViewTable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exportPeriodViewTable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exportPeriodViewTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

    public void loadExcelResultTable() {
        excelResultBean.removeAllItems();



         RemoveItemLogic.getIdAndForecastingType(selectionDTO, selection);
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            this.selectionDTO.setOperation(ConstantsUtil.TRANSFER_SUMMARY);
        } else {
            this.selectionDTO.setOperation(ConstantsUtil.SUMMARY);
        }
        List<AbstractSummaryDTO> resultList = logic.getConfiguredSalesTabResults(new Object(), selectionDTO, true);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<AbstractSummaryDTO> resultList, Object parentId) {
        for (AbstractSummaryDTO dto : resultList) {
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
        List<AbstractSummaryDTO> resultList = logic.getConfiguredSalesTabResults(id,selectionDTO, true);
        loadDataToContainer(resultList, id);
    }



    public abstract void setMainPanelCaption();

    public abstract boolean isSales();

    public void getRightVisibleColumns(final String[] his, String frequency) {
        rightDTO = new CustomTableHeaderDTO();
        rightDTO = HeaderUtils.getSalesAndRebateColumns(rightDTO, fullHeader, Integer.valueOf(his[0]), frequency, isSales());
    }

    /**
     * Change History values
     */
    private void historyChangeLogic() {
        if (history.getValue() != null) {
            tradingPartnerSalesTableLayout.removeComponent(deletedetailtable);
            String[] his = history.getValue().toString().split(" ");
            fullHeader = new CustomTableHeaderDTO();
            deletedetailtable = new FreezePagedTreeTable(deletetableLogic);
            CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesTabLeftTableColumns(fullHeader);
            getRightVisibleColumns(his, String.valueOf(frequency.getValue()));
            resultBean.setColumnProperties(leftDTO.getProperties());
            resultBean.setColumnProperties(rightDTO.getProperties());
            deletetableLogic.setTreeNodeMultiClick(false);
            deletetableLogic.setPageLength(NumericConstants.FIFTEEN);
            deletetableLogic.sinkItemPerPageWithPageLength(false);
            deletetableLogic.setContainerDataSource(resultBean);
            leftTable = deletedetailtable.getLeftFreezeAsTable();
            rightTable = deletedetailtable.getRightFreezeAsTable();
            leftTable.markAsDirty();
            rightTable.markAsDirty();
            leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
            leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));
            leftTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftDTO.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftDTO.getDoubleHeaders().toArray(new String[leftDTO.getDoubleHeaders().size()]));
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
            deletedetailtable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
             tradingPartnerSalesTableLayout.addComponent(deletedetailtable);
            if (Constants.QUARTERLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selection.getTabSelection().setFrequency(StringConstantsUtil.QUARTER_LABEL);
                selectionDTO.setFrequency(StringConstantsUtil.QUARTER_LABEL);
            } else if (Constants.SEMIANNUALLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selection.getTabSelection().setFrequency("SEMI_ANNUAL");
                selectionDTO.setFrequency("SEMI_ANNUAL");
            } else if (Constants.MONTHLY.equalsIgnoreCase(String.valueOf(frequency.getValue()))) {
                selection.getTabSelection().setFrequency("MONTH");
                selectionDTO.setFrequency("MONTH");
            } else {
                selection.getTabSelection().setFrequency("\"YEAR\"");
                selectionDTO.setFrequency("YEAR");
            }
            deletetableLogic.setProjectionResultsData(itemList, selection, isSales());
        }
    }

    /**
     * Frequency change
     */
    private void frequenyChangeLogic() {
        String historyConstant = StringUtils.EMPTY;
        final BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
        if ("Annually".equals(String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(AbstractLogic.loadHistory(Constants.ANNUALLY, YEAR.getConstant()));
            historyConstant = "1 Year";
        } else if ("Semi-Annually".equals(String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(AbstractLogic.loadHistory(Constants.SEMIANNUALLY, SEMI_ANNUAL.getConstant()));
            historyConstant = "2 Semi-Annual";
        } else if (QUARTERLY.equals(String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(AbstractLogic.loadHistory(Constants.QUARTERLY, QUARTERS.getConstant()));
            historyConstant = StringConstantsUtil.FOUR_QUARTERS;
        } else if (MONTHLY.getConstant().equals(String.valueOf(frequency.getValue()))) {
            history.removeAllItems();
            historyBean.addAll(AbstractLogic.loadHistory(Constants.MONTHLY, MONTHS.getConstant()));
            historyConstant = "12 Months";
        }
        history.setContainerDataSource(historyBean);
        history.setValue(historyConstant);
    }
}
