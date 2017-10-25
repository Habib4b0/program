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
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.DateToStringConverter;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.datefilter.ExtDateInterval;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
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

    AdjustmentConfigDTO binderDTO;
    AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    /**
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected HistAdjustmentTableLogic configTableLogic = new HistAdjustmentTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable resultsTable = new ExtPagedTable(configTableLogic);
    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AdjustmentConfigDTO> detailsTableContainer = new BeanItemContainer<>(AdjustmentConfigDTO.class);

    public AdjustmentConfigHistory(AdjustmentConfigDTO binderDTO) {
        super("Adjustment Configuration- History");
        this.binderDTO = binderDTO;
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_config/History_Config.xml"), this));
        configureFields();
    }

    private void configureFields() {
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

        transactionName.setEnabled(Boolean.FALSE);
        transactionDesc.setEnabled(Boolean.FALSE);
        methodologyDDLB.setEnabled(Boolean.FALSE);
        redemptionPeriod.setEnabled(Boolean.FALSE);

    }

    private void loadResultsTable() {
        resultsTableLayoutRes.addComponent(resultsTable);
        resultsTableLayoutRes.addComponent(getResponsiveControls(configTableLogic.createControls()));
        configTableLogic.setContainerDataSource(detailsTableContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        configTableLogic.setPageLength(NumericConstants.TEN);
        configTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.ADJUSTMENT_CONFIG_COLUMN);
        resultsTable.setColumnHeaders(ARMUtils.ADJUSTMENT_CONFIG_HEADER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);
        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        
        resultsTable.setConverter("createdDate", new DateToStringConverter());
        resultsTable.setConverter("modifiedDate", new DateToStringConverter());
        
        resultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
        
        configTableLogic.loadsetData(Boolean.TRUE, binderDTO);

        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

            public Container.Filter generateFilter(Object propertyId, Object value) {
                if ((propertyId.toString().equals("createdBy")||propertyId.toString().equals("modifiedBy")) && (value != null)) {
                        return new SimpleStringFilter(propertyId, String.valueOf(value), false, false);
                }
                if ((value instanceof ExtDateInterval ) && (value != null) ) {
                        ExtDateInterval interval = (ExtDateInterval) value;
                        Comparable<?> actualFrom = interval.getFrom(), actualTo = interval
                                .getTo();
                        actualFrom = actualFrom == null ? null : new Timestamp(interval
                                .getFrom().getTime());
                        actualTo = actualTo == null ? null : new Timestamp(interval
                                .getTo().getTime());

                        if (actualFrom != null && actualTo != null) {
                            return new Between(propertyId, actualFrom, actualTo);
                        } else if (actualFrom != null) {
                            return new Compare.GreaterOrEqual(propertyId, actualFrom);
                        } else {
                            return new Compare.LessOrEqual(propertyId, actualTo);
                        }
                }
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if ((originatingField instanceof ComboBox) && (originatingField.getValue() != null)) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);

                }
                return generateFilter(propertyId, originatingField.getValue());
            }

            @Override
            public void filterRemoved(Object propertyId) {
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                try {
                    final ComboBox comboBox = new ComboBox();
                    comboBox.setImmediate(true);
                    switch (propertyId.toString()) {
                        case "methodology":
                            CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, "ARM_TRX_METHDOLOGY", Boolean.TRUE);
                            return comboBox;
                        case "redemptionPeriod":
                            loadRedemptionPeriodDDLB(comboBox);
                            return comboBox;
                        default:
                            return null;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                return null;
            }
        });
        resultsTable.setFilterBarVisible(Boolean.TRUE);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("excelBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        createWorkSheet("Adjustment Configuration - History", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        long recordCount = 0;
        String[] visibleList = resultsTable.getColumnHeaders();
        if (resultTable.size() != 0) {
            recordCount = logic.getAdjustmentConfigCountForHistory(configTableLogic.getFilters(), binderDTO);
        }
        ExcelExportforBB.createWorkSheet(visibleList, recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {

        Object[] visibleList = resultsTable.getVisibleColumns();
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getAdjustmentConfigDataForHistory(start, end, configTableLogic.getFilters(), configTableLogic.getSortByColumns(), binderDTO);
                ExcelExportforBB.createFileContent(visibleList, searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * close button logic.
     *
     * @param event
     */
    @UiHandler("closeBtnRes")
    public void closeButtonLogic(Button.ClickEvent event) throws PortalException {
        close();
    }

    /**
     * To Load redemption Period ddlb
     */
    private void loadRedemptionPeriodDDLB(ComboBox redemptionPeriod) {
        redemptionPeriod.addItem(GlobalConstants.getShowAll());
        redemptionPeriod.addItem(1);
        redemptionPeriod.setItemCaption(1, ARMConstants.getYes());
        redemptionPeriod.addItem(0);
        redemptionPeriod.setItemCaption(0, ARMConstants.getNo());
        redemptionPeriod.setNullSelectionAllowed(Boolean.TRUE);
        redemptionPeriod.setNullSelectionItemId(GlobalConstants.getShowAll());
        redemptionPeriod.select(GlobalConstants.getShowAll());
    }

}
