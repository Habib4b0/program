package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentTableLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator.SummaryFieldFactory;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.app.arm.supercode.HasExcel;
import com.stpl.app.arm.supercode.HasLogic;
import com.stpl.app.arm.supercode.HasSelection;
import com.stpl.app.arm.supercode.HasTableLogic;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.PropertyFormatCustomTreeTable;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 * @param <T>
 */
public abstract class AbstractSearchResults<T extends AbstractSelectionDTO> extends VerticalLayout implements HasTableLogic, HasLogic, HasExcel, HasSelection, GenerateAble {

    @UiField("ARMLevelLabel")
    protected Label armLevelLabel;
    @UiField("TableLayout")
    protected VerticalLayout tableLayout;
    @UiField("abstractSearchContent")
    protected VerticalLayout abstractSearchContent;
    @UiField("customerProductView")
    protected ComboBox customerProductView;
    @UiField("valueDdlb")
    protected ComboBox valueDdlb;

    @UiField("levelDdlb")
    protected ComboBox levelDdlb;

    @UiField("levelFilterDdlb")
    protected ComboBox levelFilterDdlb;
    @UiField("panelCaption")
    protected Panel panelCaption;
    @UiField("BB-calculate")
    protected Button calculateBtn;

    @UiField("BB-export")
    protected Button bbExport;
    @UiField("cpLabel")
    protected Label cpLabel;
    @UiField("BB-expand")
    protected Button expandbtn;
    @UiField("BB-collapse")
    protected Button collapseBtn;

    @UiField("BB-searchResultsGrid")
    protected GridLayout searchResultsGrid;

    @UiField("cancelOverride")
    protected Button cancelOverride;

    protected final AdjustmentTableLogic tableLogic;
    protected final FreezePagedTreeTable table;
    protected ExtPagedTreeTable leftTable;
    protected ExtPagedTreeTable rightTable;
    protected PropertyFormatCustomTreeTable excelTable;
    private final float maxSplitPosition = 1000;
    private final float minSplitPosition = 200;
    private final float splitPosition = 300;
    protected final LogicAble logic;
    protected final T selection;
    protected ExtTreeContainer<AdjustmentDTO> resultBeanContainer = new ExtTreeContainer<>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    protected ExtTreeContainer<AdjustmentDTO> excelBeanContainer;
    protected boolean valueChangeAllowed = false;
    protected boolean valueDdlbChangeAllowed = false;
    private boolean levelFilterEnable = false;
    private boolean levelFilterValueDdlbEnable = false;
    private final Logger logger = LoggerFactory.getLogger(AbstractSearchResults.class);

    public AbstractSearchResults(LogicAble logic, T selection) {
        this.logic = logic;
        this.selection = selection;
        this.tableLogic = new AdjustmentTableLogic(getSummaryLogic(), getSelection());
        this.table = new FreezePagedTreeTable(getTableLogic());
    }

    protected AbstractSearchResults() {
        this.tableLogic = null;// For Sonar Fix
        this.table = null;
        this.logic = null;
        this.selection = null;
    }

    public void getResults() {
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/abstractSearchResults.xml"), this));
        configurePermission();
        abstractSearchContent.setSizeFull();
        searchResultsGrid.setSizeUndefined();
        configureResultsTable();
        initializeResultTable();
        loadLabelValues();
        setValueChangeAllowed(false);
        loadRespectiveHierarchy();
        configureLevelAndLevelFilter();
        setValueChangeAllowed(true);
        calculateBtn();
    }

    protected void initializeResultTable() {
        table.markAsDirty();
        table.setSelectable(false);
        table.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        table.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        table.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        table.addStyleName(ARMUtils.CENTER_CHECK);
    }

    public void configureFields() {
        setValueChangeAllowed(false);
        loadLevelValues();
        setValueChangeAllowed(true);
        levelDdlb.setNewItemsAllowed(false);
        levelFilterDdlb.setNewItemsAllowed(false);
    }

    private void configureResultsTable() {
        tableLayout.addComponent(table);
        table.markAsDirty();
        tableLogic.setContainerDataSource(resultBeanContainer);
        table.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.sinkItemPerPageWithPageLength(false);
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        table.setDoubleHeaderVisible(true);
        tableLayout.addComponent(getResponsiveControls(tableLogic.createControls()));
        setVisibleColumnsAndHeaders();
    }

    public abstract void setVisibleColumnsAndHeaders();

    private void loadLabelValues() {
        armLevelLabel.setCaption("Level:");
    }

    private final CustomNotification notifier = new CustomNotification();

    /**
     * load and set the default selection for level DDLBs
     */
    protected void loadLevelValues() {
        Object[] obj = new Object[]{ARMConstants.getDeductionProduct(), ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()};
        customerProductView.setImmediate(true);
        customerProductView.addItems(obj);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        break;
                    case "save":
                        break;
                    case "CANCEL OVERRIDE":
                        cancelOverrideLogic();
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    protected void cancelOverrideLogic() {
        selection.setCancelOverride(true);
        refreshTableData(getAllExpandedHierarchyNo());
        selection.setCancelOverride(false);
    }

    protected abstract boolean calculateLogic();

    @UiHandler("cancelOverride")
    public void cancelOverrideButtonClick(Button.ClickEvent event) {
        notifier.setButtonName("CANCEL OVERRIDE");
        notifier.getOkCancelMessage("Cancel Override Confirmation", "Are you sure you want to reset the Override values?");

    }

    public void calculateBtn() {
        calculateBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (rightTable.getTableFieldFactory() instanceof SummaryFieldFactory) {
                        ((SummaryFieldFactory) rightTable.getTableFieldFactory()).setCheckLeave(false);
                    }
                    calculateLogic();
                    refreshTableData(getAllExpandedHierarchyNo());
                } catch (Exception e) {
                    logger.error("Error in calculateBtn :", e);
                }
            }
        });
    }

    @UiHandler("BB-expand")
    public void expandButtonClick(Button.ClickEvent event) {
        Object value = getLevelDdlb().getValue();
        try {
            if (value != null) {
                int levelNo = (Integer) value;
                if (levelNo > 0) {
                    expandCollapseClickLogic(levelNo);
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("Error in expandButtonClick :", e);
        }
        AbstractNotificationUtils.getErrorNotification(ARMMessages.getExpandMessageName(), ARMMessages.getExpandMessageMsgId_001());
    }

    @UiHandler("BB-collapse")
    public void collapseButtonClick(Button.ClickEvent event) {
        Object value = getLevelDdlb().getValue();
        try {
            if (value != null) {
                int levelNo = (Integer) value;
                if (levelNo > 0) {
                    expandCollapseClickLogic(levelNo - 1);
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("Error in collapseButtonClick :", e);
        }
        AbstractNotificationUtils.getErrorNotification(ARMMessages.getExpandMessageName(), ARMMessages.getExpandMessageMsgId_001());
    }

    protected void expandCollapseClickLogic(int levelNo) {
        setValueChangeAllowed(false);
        getLevelFilterDdlb().setValue(0);
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        getLevelFilterValueDdlb().setValue(0);
        setRespectiveLevelFileterValue(GlobalConstants.getSelectOne(), 0);
        getTableLogic().loadExpandData(levelNo);
        setValueChangeAllowed(true);
    }

    @UiHandler("levelFilterDdlb")
    public void levelFilterValueChange(Property.ValueChangeEvent event) {
        if (isValueChangeAllowed()) {
            Object value = event.getProperty().getValue();
            int val = 0;
            String caption = "";
            if (value != null && value instanceof Integer) {

                val = (Integer) value;
                caption = getLevelFilterDdlb().getItemCaption(value);
            }
            levelFilterValueChangeLogic(val, caption);
        }
    }

    @UiHandler("valueDdlb")
    public void valueDdlbValueChangeMethod(Property.ValueChangeEvent event) {
        if (isValueChangeAllowed() && isLevelFilterValueDdlbEnable()) {
            Object value = event.getProperty().getValue();
            int val = 0;
            if (value != null && value instanceof Integer) {
                val = (Integer) value;
                valueDdlbValueChange(val);
            } else {
                String message = String.valueOf(value) + " is not an integer ";
                logger.error(message);
            }
        }

    }

    protected void configureLevelAndLevelFilter() {
        setLevelFilterEnable(false);
        Map<Integer, String> levelVal = getHierarchy();
        getLevelDdlb().removeAllItems();
        getLevelDdlb().addItem(0);
        getLevelDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        getLevelFilterDdlb().removeAllItems();
        getLevelFilterDdlb().addItem(0);
        getLevelFilterDdlb().setItemCaption(0, GlobalConstants.getSelectOne());

        int size = levelVal.size();
        for (int i = 0; i < size; i++) {
            int id = i + 1;
            String value = levelVal.get(id);
            getLevelFilterDdlb().addItem(id);
            getLevelFilterDdlb().setItemCaption(id, value);
            if (i < (size - 1)) {
                getLevelDdlb().addItem(id);
                getLevelDdlb().setItemCaption(id, "Level " + id + " " + value);
            }
        }
        getLevelDdlb().setValue(0);
        getLevelFilterDdlb().setValue(0);
        getLevelFilterValueDdlb().setValue(0);
        setLevelFilterEnable(true);
        setRespectiveLevelFileterValue(GlobalConstants.getSelectOne(), 0);
    }

    protected void levelFilterValueChangeLogic(int levelNo, String value) {
        if (isLevelFilterEnable()) {
            try {
                if (setRespectiveLevelFileterValue(value, levelNo)) {
                    getTableLogic().loadSetData(Boolean.FALSE);
                }
                setLevelFilterValueDdlbEnable(false);
                loadLevelFilterValueDdlb(value, levelNo);
                setLevelFilterValueDdlbEnable(true);
            } catch (Exception e) {
                logger.error("Error in levelFilterValueChangeLogic ", e);
            }
        }
    }

    protected abstract boolean setRespectiveLevelFileterValue(String levelValue, int levelNo);

    protected abstract void loadLevelFilterValueDdlb(String levelValue, int levelNo);

    protected abstract void valueDdlbValueChange(int masterSids);

    /**
     * Excel export logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void excelExportLogic() throws IllegalAccessException, InvocationTargetException {
        tableLayout.addComponent(getExcelTable());
        getExcelTable().setContainerDataSource(getExcelContainer());
        getExcelTable().setRefresh(Boolean.FALSE);
        getExcelTable().setVisible(Boolean.FALSE);
        setExcelVisibleColumn();
        Object[] excelHierarchy = getExcelHierarchy();
        List resultList = getExcelLogic().getExcelResultList(getSelection());
        callExcelCustomization(resultList, excelHierarchy);
        ((CommonUI) getUI()).setExcelFlag(Boolean.TRUE);
        getExcelTable().setRefresh(Boolean.TRUE);
        ExtCustomTableHolder extCustomTableHolder = new ExtCustomTableHolder(getExcelTable());
        ExcelExport export = new ExcelExport(extCustomTableHolder, getExcelFileName(), getExcelFileName(), getExcelFileName() + ".xls", false);
        export.setUseTableFormatPropertyValue(Boolean.TRUE);
        export.export();
        getExcelContainer().removeAllItems();
        tableLayout.removeComponent(getExcelTable());
    }

    public abstract Object[] getExcelHierarchy();

    public abstract List getExcelExportVisibleColumn();

    public abstract String getExcelFileName();

    public abstract boolean getisFixedColumns();

    public abstract int getInterval();

    public abstract boolean getisDeductionCustomer();

    public abstract int discountColumnNeeded();

    protected abstract boolean getIsDemandSreen();

    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List<Object> listData = new ArrayList<>();
        listData.add(getisFixedColumns());
        listData.add(getisDeductionCustomer());
        listData.add(getIsDemandSreen());
        listData.add(getInterval());

        ExcelUtils.setExcelData(list, excelHierarchy, getExcelExportVisibleColumn(), getExcelContainer(), discountColumnNeeded(), StringUtils.EMPTY, listData);
    }

    public PropertyFormatCustomTreeTable getExcelTable() {
        if (excelTable == null) {
            excelTable = new PropertyFormatCustomTreeTable();
        }
        return excelTable;
    }

    public ExtTreeContainer<AdjustmentDTO> getExcelContainer() {
        if (excelBeanContainer == null) {
            excelBeanContainer = new ExtTreeContainer<>(AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
        }
        return excelBeanContainer;
    }

    protected void loadExcelData() {
    }

    protected abstract void setExcelVisibleColumn();

    protected ExtPagedTreeTable getRightTable() {
        return rightTable;
    }

    protected abstract void configureRightTable();

    protected abstract void customerProductValueChange();

    @UiHandler("customerProductView")
    public void cutomerProductViewChangeLogic(Property.ValueChangeEvent event) {
        if (isValueChangeAllowed()) {
            setValueChangeAllowed(false);
            customerProductValueChange();
            setValueChangeAllowed(true);
        }
    }

    @Override
    public AdjustmentTableLogic getTableLogic() {
        return tableLogic;
    }

    @Override
    public LogicAble getSummaryLogic() {
        return logic;
    }

    public ComboBox getLevelDdlb() {
        return levelDdlb;
    }

    public ComboBox getLevelFilterDdlb() {
        return levelFilterDdlb;
    }

    public ComboBox getLevelFilterValueDdlb() {
        return valueDdlb;
    }

    public boolean isLevelFilterEnable() {
        return levelFilterEnable;
    }

    public boolean isLevelFilterValueDdlbEnable() {
        return levelFilterValueDdlbEnable;
    }

    public void setLevelFilterEnable(boolean levelFilterEnable) {
        this.levelFilterEnable = levelFilterEnable;
    }

    public void setLevelFilterValueDdlbEnable(boolean levelFilterValueDdlbEnable) {
        this.levelFilterValueDdlbEnable = levelFilterValueDdlbEnable;
    }

    public boolean isValueChangeAllowed() {
        return valueChangeAllowed;
    }

    public void setValueChangeAllowed(boolean valueChangeAllowed) {
        this.valueChangeAllowed = valueChangeAllowed;
    }

    public FreezePagedTreeTable getTable() {
        return table;
    }

    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainerVal() {
        return resultBeanContainer;
    }

    @Override
    public T getSelection() {
        return selection;
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("BB-export")
    public void exportButtonLogic(Button.ClickEvent event) {
        try {
            excelExportLogic();
        } catch (Exception ex) {
            logger.error("Error in exportButtonLogic :", ex);
        }
    }

    public abstract Map<Integer, String> getHierarchy();

    public abstract void setRespectiveHierarchy(String viewType);

    @Override
    public boolean isGenerated() {
        return !table.getRightFreezeAsTable().getItemIds().isEmpty();
    }

    /**
     * Method contains the logic for the Table Refresh.
     *
     * @param finalHierarchyNo
     */
    protected void refreshTableData(Set<String> finalHierarchyNo) {
        getTableLogic().setHierarchyToRefresh(finalHierarchyNo);
        getTableLogic().setCurrentPage(getTableLogic().getCurrentPage());
    }

    protected Set<String> getAllExpandedHierarchyNo() {
        Set<String> finalHierarchyNo = new HashSet<>();
        for (String tableTreeLevelNo : getTableLogic().getAllLevels()) {
            finalHierarchyNo.add(tableTreeLevelNo);
        }
        return finalHierarchyNo;
    }

    protected void loadRespectiveHierarchy() {
        setRespectiveHierarchy(ARMConstants.getDeductionProduct());
    }

    protected void setConverter(ExtCustomTable excelTable, Object[] visibleColumns) {
        DataFormatConverter currency2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_DOLLAR);
        DataFormatConverter rate2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter rate3Dec = new DataFormatConverter(ARMConstants.getThreeDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter zeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat());
        DataFormatConverter perzeroDec = new DataFormatConverter(ARMConstants.getNoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
        DataFormatConverter currencyzeroDec = new DataFormatConverter(ARMConstants.getCurrNoDecFormat());
        DataFormatConverter num2Dec = new DataFormatConverter(ARMConstants.getTwoDecFormat());
        for (Object visibleColumn : visibleColumns) {
            if (!"group".equals(String.valueOf(visibleColumn)) && !"month".equals(String.valueOf(visibleColumn))) {
                if (isPercentageColumnzeroDecimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, perzeroDec, Boolean.FALSE);
                } else if (isPercentageColumn2Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate2Dec, Boolean.FALSE);
                } else if (isNumericTwoDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, num2Dec, Boolean.FALSE);
                } else if (isPercentageColumn3Decimal(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, rate3Dec, Boolean.FALSE);
                } else if (isUnitColumn(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, zeroDec, Boolean.FALSE);
                } else if (isCurrencyZeroDecimalFormat(visibleColumn.toString())) {
                    excelTable.setConverter(visibleColumn, currencyzeroDec, Boolean.FALSE);
                } else {
                    excelTable.setConverter(visibleColumn, currency2Dec, Boolean.FALSE);
                }
            }
        }
    }

    /**
     * Method is used to check the column is Percentage append able with 2
     * decimal format
     *
     * @param column
     * @return
     */
    protected boolean isPercentageColumn3Decimal(String column) {
        return column.contains("Ratio") || column.contains("ratio") || column.contains("Per") || column.contains("per");
    }

    /**
     * Method is used to check the column is Percentage append able with 2
     * decimal format
     *
     * @param column
     * @return
     */
    protected boolean isPercentageColumn2Decimal(String column) {
        logger.debug(CommonConstant.VISIBLE_COLUMN_NAME, column);
        return false;
    }

    /**
     * Method is used to check the column is formatted with zero decimal format
     *
     * @param column
     * @return
     */
    protected boolean isUnitColumn(String column) {
        return column.contains("units") || column.contains("Units") || column.contains("totalInventory") || column.contains("~");
    }

    /**
     * Method is used to check the column is formatted currency with zero
     * decimal format
     *
     * @param column
     * @return
     */
    protected boolean isCurrencyZeroDecimalFormat(String column) {
        logger.debug(CommonConstant.VISIBLE_COLUMN_NAME, column);
        return false;
    }

    protected boolean isNumericTwoDecimalFormat(String column) {
        logger.debug(CommonConstant.VISIBLE_COLUMN_NAME, column);
        return false;
    }

    protected boolean isPercentageColumnzeroDecimal(String column) {
        logger.debug(CommonConstant.VISIBLE_COLUMN_NAME, column);
        return false;
    }

    private void configurePermission() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(ConstantsUtils.USER_ID));

        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantsUtils.COMMA + ConstantsUtils.SALES);
        if (functionCfpHM.get("calculateBtn") != null && (functionCfpHM.get("calculateBtn")).isFunctionFlag()) {
            calculateBtn();
        } else {
            calculateBtn.setVisible(false);
        }

    }

    public Button getExpandbtn() {
        return expandbtn;
    }

    public Button getCollapseBtn() {
        return collapseBtn;
    }

    public Button getBbExport() {
        return bbExport;
    }

    public Button getCalculateBtn() {
        return calculateBtn;
    }

    public Button getCancelOverride() {
        return cancelOverride;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
