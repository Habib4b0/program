/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.form;

import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.stpl.app.arm.adjustmentconfiguration.logic.AdjustmentConfigLogic;
import com.stpl.app.arm.adjustmentconfiguration.logic.HistAdjustmentTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.R
 */
public class AdjustmentConfigHistory extends Window {

    /**
     * The Table Layout
     */
    @UiField("resultsTableLayoutRes")
    private VerticalLayout resultsTableLayoutRes;
    @UiField("transactionName")
    protected TextField transactionName;
    @UiField("transactionDesc")
    protected TextField transactionDesc;
    @UiField("methodology")
    protected ComboBox methodologyDDLB;
    @UiField("redemptionPeriod")
    protected ComboBox redemptionPeriod;

    @UiField("closeBtnRes")
    protected Button historyBtn;
    @UiField("excelBtnRes")
    protected Button excelBtnRes;

    private AdjustmentConfigDTO binderDTO;
    private AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    /**
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected HistAdjustmentTableLogic configTableLogic = new HistAdjustmentTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable historyResultsTable = new ExtPagedTable(configTableLogic);

    protected static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentConfigHistory.class);
    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AdjustmentConfigDTO> detailsTableContainer = new BeanItemContainer<>(AdjustmentConfigDTO.class);

    public AdjustmentConfigHistory(AdjustmentConfigDTO binderDTO) {
        super("Adjustment Configuration- History");
        this.binderDTO = binderDTO;
        configureFields();
    }

    private void configureFields() {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_config/History_Config.xml"), this));
        configureDDLBS();
        loadResultsTable();
        excelBtnRes.setPrimaryStyleName("link");
        excelBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        historyBtn.focus();

    }

    private void configureDDLBS() {
        methodologyDDLB.addItem(binderDTO.getMethodology());
        redemptionPeriod.addItem(binderDTO.getRedemptionPeriod());
        transactionName.setValue(binderDTO.getTransactionName());
        transactionDesc.setValue(binderDTO.getTransactionDesc());
        methodologyDDLB.setValue(binderDTO.getMethodology());
        redemptionPeriod.setValue(binderDTO.getRedemptionPeriod());

        transactionName.setEnabled(false);
        transactionDesc.setEnabled(false);
        methodologyDDLB.setEnabled(false);
        redemptionPeriod.setEnabled(false);

    }

    private void loadResultsTable() {
        resultsTableLayoutRes.addComponent(historyResultsTable);
        resultsTableLayoutRes.addComponent(getResponsiveControls(configTableLogic.createControls()));
        configTableLogic.setContainerDataSource(detailsTableContainer);
        historyResultsTable.setSelectable(true);
        historyResultsTable.setMultiSelect(true);
        configTableLogic.setPageLength(NumericConstants.TEN);
        configTableLogic.sinkItemPerPageWithPageLength(false);
        historyResultsTable.setVisibleColumns(ARMUtils.getAdjustmentConfigColumn());
        historyResultsTable.setColumnHeaders(ARMUtils.getAdjustmentConfigHeader());
        historyResultsTable.setFilterBarVisible(true);
        historyResultsTable.setSizeFull();
        historyResultsTable.setImmediate(true);
        historyResultsTable.setPageLength(NumericConstants.TEN);
        historyResultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        historyResultsTable.addStyleName("table-header-normal");
        historyResultsTable.addStyleName(ARMUtils.CENTER_CHECK);

        historyResultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        historyResultsTable.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);

        configTableLogic.loadsetData(true, binderDTO);

        historyResultsTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                String filterValue = null;
                if ((originatingField.getValue() != null)) {
                    filterValue = String.valueOf(originatingField.getValue());
                    if ("redemptionPeriod".equals(propertyId) && !originatingField.getValue().equals(StringUtils.EMPTY)) {
                        if ("yes".contains(originatingField.getValue().toString())) {
                            filterValue = "1";
                        } else {
                            filterValue = "0";
                        }
                    }
                    return new SimpleStringFilter(propertyId, filterValue, false, false);

                }
                return generateFilter(propertyId, originatingField.getValue());
            }

            @Override
            public void filterRemoved(Object propertyId) {
                LOGGER.debug("Inside History filterRemoved Method");
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                LOGGER.debug("Inside History filterAdded Method");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reasons, Object property, Object val) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object property) {
                return null;
            }
        });
        historyResultsTable.setFilterBarVisible(true);
        historyResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("excelBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) {
        createWorkSheet("Adjustment Configuration - History", historyResultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) {
        long recordCount = 0;
        String[] visibleList = historyResultsTable.getColumnHeaders();
        if (resultTable.size() != 0) {
            try {
                recordCount = logic.getAdjustmentConfigCountForHistory(configTableLogic.getFilters(), binderDTO);
            } catch (SQLException ex) {
                LOGGER.error("Error in createWorkSheet :", ex);
            }
        }
        try {
            ExcelExportforBB.createWorkSheet(visibleList, recordCount, this, UI.getCurrent(), moduleName.toUpperCase(Locale.ENGLISH));
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error("Error in createWorkSheet :", ex);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {

        Object[] visibleList = historyResultsTable.getVisibleColumns();
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getAdjustmentConfigDataForHistory(start, end, configTableLogic.getFilters(), configTableLogic.getSortByColumns(), binderDTO);
                ExcelExportforBB.createFileContent(visibleList, searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error("Error in createWorkSheetContent :", e);
        }
    }

    /**
     * close button logic.
     *
     * @param event
     */
    @UiHandler("closeBtnRes")
    public void closeButtonLogic(Button.ClickEvent event) {
        close();
    }

    @Override
    public boolean equals(Object adjustmentConfigHistoryobj) {
        return super.equals(adjustmentConfigHistoryobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream adjustmentConfigHistoryout) throws IOException {
        adjustmentConfigHistoryout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjustmentConfigHistoryin) throws IOException, ClassNotFoundException {
        adjustmentConfigHistoryin.defaultReadObject();
    }

}
