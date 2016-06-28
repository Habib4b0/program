/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.TableDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionDetailsLogic;
import com.stpl.app.global.deductioncalendar.logic.tablelogic.DeductionTableLogic;
import com.stpl.app.global.deductioncalendar.ui.util.CommonUtil;
import com.stpl.app.global.deductioncalendar.ui.util.HeaderUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import java.util.Map;
import java.util.logging.Level;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sibi
 */
public class DeductionDetails extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(DeductionDetails.class);

    @UiField("dataview")
    private OptionGroup dataview;
    @UiField("listview")
    private OptionGroup listview;
    @UiField("type")
    private OptionGroup type;
    @UiField("basis")
    private OptionGroup basis;
    @UiField("variable")
    private OptionGroup variable;
    @UiField("adjustmentPeriods")
    private OptionGroup adjustmentPeriods;
    @UiField("massUpdate")
    private OptionGroup massUpdate;
    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;
    @UiField("filterDdlb")
    private ComboBox filterDdlb;
    @UiField("generateBtn")
    private Button generateBtn;
    @UiField("from")
    private PopupDateField from;
    @UiField("to")
    private PopupDateField to;
    @UiField("allocationMethododlogyDdlb")
    private ComboBox allocationMethododlogyDdlb;
    @UiField("fieldDdlb")
    private ComboBox fieldDdlb;
    @UiField("type2")
    private Label type2;
    @UiField("startPeriod")
    private ComboBox startPeriod;
    @UiField("endPeriod")
    private ComboBox endPeriod;
    @UiField("populate")
    private Button populate;
    @UiField("value")
    private TextField value;
    @UiField("adjustmentPanel")
    private Panel adjustmentPanel;
    @UiField("massUpdatePanel")
    private Panel massUpdatePanel;
    @UiField("adjustment")
    private TextField adjustment;

    private Button refreshBtn = new Button("REFRESH");

    @UiField("adjustBtn")
    private Button adjustBtn;
    
    @UiField("levelDdlb")
    private ComboBox levelDdlb;

    protected CustomTableHeaderDTO leftHeader = null;
    protected CustomTableHeaderDTO rightHeader = null;
    DeductionDetailsLogic logic = new DeductionDetailsLogic();
    protected CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    DeductionTableLogic tableLogic = new DeductionTableLogic();
    protected FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    DeductionDetailsDTO detailsDto = new DeductionDetailsDTO();
    TableDTO tableDTO = new TableDTO();
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    private static final String[] CURRENT_MONTH = {"Jan~1", "Feb~2", "Mar~3", "Apr~4", "May~5", "Jun~6", "Jul~7", "Aug~8", "Sep~9", "Oct~10", "Nov~11", "Dec~12"};

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Set<String> childLevelSet = new HashSet<String>();

    Set<String> refreshSet = new HashSet<String>();

    boolean refreshFlag = false;

    boolean checkAll = false;

    List<Object> checkBoxList = new ArrayList();
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
    @UiField("tableVerticalLayout")
    public VerticalLayout tableVerticalLayout;
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    CustomTreeContainer<TableDTO> resultBeanContainer = new CustomTreeContainer<TableDTO>(TableDTO.class);
    HeaderUtils headerUtils = new HeaderUtils();

    DecimalFormat DEC_FORMAT = new DecimalFormat("###0.00");

    String oldValue;

    SessionDTO sessionDTO;
    
    private String generatedView;
    
    private String filterValue;
    
    public DeductionDetails(final SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/deduction_calendar/deductionDetails.xml"), this));
        init();
        detailsDto.setUserId(sessionDTO.getUserId());
        detailsDto.setSessionId(sessionDTO.getUiSessionId());
    }

    private void init() {
        try {
            configureFields();
            addResultTable();
            if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
                disableFieldsOnView();
            }
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> functionDCHM = stplSecurity.getBusinessFunctionPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR + ConstantsUtils.COMMA + ConstantsUtils.DEDUCTION_DETAILS);
            getButtonPermission(functionDCHM);
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(DeductionDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(DeductionDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetFields() {
        dataview.select(ConstantsUtils.CUSTOMER);
        detailsDto.setDataView(String.valueOf(dataview.getValue()));
        generatedView=detailsDto.getDataView();
        listview.select("Collapse");
        detailsDto.setListView(String.valueOf(listview.getValue()));
        type.select(ConstantsUtils.INCREMENTAL);
        basis.select(ConstantsUtils.AMOUNT);
        variable.select(ConstantsUtils.DOLLARS);
        adjustmentPeriods.select(ConstantsUtils.ALL);
        massUpdate.select(ConstantsUtils.DISABLE);
        fieldDdlb.select(ConstantsUtils.SELECT_ONE);
        fieldDdlb.setEnabled(false);
        levelDdlb.select(ConstantsUtils.SELECT_ONE);
        levelDdlb.setEnabled(false);
        value.setValue("");
        value.setEnabled(false);
        startPeriod.setValue(null);
        startPeriod.setEnabled(false);
        endPeriod.setValue(null);
        endPeriod.setEnabled(false);
        populate.setEnabled(false);

        frequencyDdlb.select("Annual");
        from.setValue(null);
        to.setValue(null);
        filterDdlb.select(null);
        allocationMethododlogyDdlb.select(null);
        adjustment.setValue("");
    }

    private void configureFields() {
        try {
            detailsDto = logic.getForecastConfig(detailsDto);
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT1);// or any date format you want
            from.setValue(sdf.parse(detailsDto.getForecastFromDate()));
            to.setValue(sdf.parse(detailsDto.getForecastToDate()));
            filterDdlb.setNullSelectionAllowed(Boolean.TRUE);
            filterDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            filterDdlb.addItem(ConstantsUtils.SELECT_ONE);
            dataview.addItem("Customer");
            dataview.addItem("Product");
            dataview.select(ConstantsUtils.CUSTOMER);
            detailsDto.setDataView(String.valueOf(dataview.getValue()));
            dataview.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    detailsDto.setDataView(String.valueOf(dataview.getValue()));
                    filterDdlb.removeAllItems();
                    filterDdlb.addItem(ConstantsUtils.SELECT_ONE);
                    for (Object[] object : (List<Object[]>) logic.loadFilterDdlb(detailsDto)) {
                        filterDdlb.addItems(object[1]);
                        filterDdlb.setItemCaption(object[1], object[0].toString());
                    }
                }
            });
            listview.addItem("Expand");
            listview.addItem("Collapse");
            listview.select("Collapse");
            detailsDto.setListView(String.valueOf(listview.getValue()));
            listview.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    detailsDto.setListView(String.valueOf(listview.getValue()));
                }
            });
            type.addItem("Incremental");
            type.select(ConstantsUtils.INCREMENTAL);
            type.addItem("Override");
            basis.addItem("Amount");
            basis.select(ConstantsUtils.AMOUNT);
            basis.addItem("Percentage");
            variable.addItem("Dollar");
            variable.select(ConstantsUtils.DOLLARS);
            variable.addItem("Unit");
            variable.setItemEnabled("Unit", Boolean.FALSE);
            adjustmentPeriods.addItem("All");
            adjustmentPeriods.select(ConstantsUtils.ALL);
            adjustmentPeriods.addItem(ConstantsUtils.SELECT1);
            massUpdate.addItem("Enable");
            massUpdate.select(ConstantsUtils.ENABLE);
            massUpdate.addItem("Disable");
            massUpdate.select("Disable");
            massUpdate.addListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {

                    if ("Enable".equals(massUpdate.getValue())) {
                        fieldDdlb.setEnabled(true);
                        levelDdlb.setEnabled(true);
                        value.setEnabled(true);
                        startPeriod.setEnabled(true);
                        endPeriod.setEnabled(true);
                        populate.setEnabled(true);
                    } else {
                        fieldDdlb.setEnabled(false);
                        levelDdlb.setEnabled(false);
                        value.setEnabled(false);
                        startPeriod.setEnabled(false);
                        endPeriod.setEnabled(false);
                        populate.setEnabled(false);
                    }

                }
            });
            frequencyDdlb.addItem("Annual");
            frequencyDdlb.addItem("Semi-Annual");
            frequencyDdlb.addItem("Quarterly");
            frequencyDdlb.addItem(ConstantsUtils.MONTHLY);
            frequencyDdlb.select("Annual");
            frequencyDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                }
            });
            from.setEnabled(true);
            from.setDateFormat(ConstantsUtils.DATE_FORMAT);
            to.setEnabled(true);
            to.setDateFormat(ConstantsUtils.DATE_FORMAT);
            allocationMethododlogyDdlb.addItem("Forecast Ratio");
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            fieldDdlb.addItem(ConstantsUtils.SELECT_ONE);
            fieldDdlb.addItem("Discount Amount");
            fieldDdlb.setEnabled(false);
            value.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);
            
            levelDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            levelDdlb.addItem(ConstantsUtils.SELECT_ONE);
            levelDdlb.addItem("Customer");
            levelDdlb.addItem("Brand");
            levelDdlb.addItem("Product");
            filterDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    detailsDto.setFilterDdlb(String.valueOf(filterDdlb.getValue()));
                }
            });
            initializeResultTable();
            configureResultTable();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     *
     * @param startYr
     * @param endYr
     * @param start
     * @param end
     * @return
     */
    private List getUpdateProperty(int startYr, int endYr, int start, int end) {
        List<String> list = new ArrayList<String>();
        int month = startYr == endYr ? end : 12;
        for (int i = startYr; i <= endYr; i++) {
            for (int j = start; j <= month; j++) {
                list.add("" + CURRENT_MONTH[j - 1] + "~" + String.valueOf(i));
            }
            start = 1;
            month = i + 1 == endYr ? end : 12;
        }
        return list;
    }

    /**
     * /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName("table-header-center");
    }

    private void configureResultTable() {
        tableLogic.setPageLength(10);
        fullHeader = new CustomTableHeaderDTO();
        detailsDto.setFrequency(String.valueOf(frequencyDdlb.getValue()).equals(null) ? "Quarterly" : String.valueOf(frequencyDdlb.getValue()));
        leftHeader = headerUtils.getLeftDeductionDetailsHeader(fullHeader);
        rightHeader = headerUtils.getRightDeductionDetailsHeader(fullHeader, detailsDto);
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());
        resultBeanContainer.setColumnProperties(rightHeader.getProperties());
        tableLogic.setTableDTO(tableDTO);
        tableLogic.setContainerDataSource(resultBeanContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        resultsTable.setHeight("390px");
        leftTable.setHeight("390px");
        rightTable.setHeight("390px");
        leftTable.setColumnWidth("group", 300);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
            rightTable.setColumnCheckBox(propertyId, true);
        }
        for (Object propertyId : rightTable.getVisibleColumns()) {
            if (!detailsDto.getFrequency().equals(ConstantsUtils.MONTHLY)) {
                rightTable.setColumnCheckBoxDisable(propertyId, true);
            }
        }
        configureFieldsForLeftTables(leftTable);
        if (detailsDto.getFrequency().equals(ConstantsUtils.MONTHLY)) {
            configureFieldsForRightTables(rightTable);
        }else{
            rightTable.setEditable(false);
        }

        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (event.isChecked()) {
                    if (!checkBoxList.contains(event.getPropertyId())) {
                        checkBoxList.add(event.getPropertyId());
                    }
                } else {
                    if (checkBoxList.contains(event.getPropertyId())) {
                        checkBoxList.remove(event.getPropertyId());
                    }
                }

            }
        });

    }

    /**
     * Field factory for left table
     */
    public void configureFieldsForLeftTables(final ExtPagedTreeTable leftTable) {
        leftTable.setEditable(true);
        leftTable.setTableFieldFactory(new TableFieldFactory() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {

                if ("check".equals(propertyId)) {
                    final ExtCustomCheckBox checkBox = new ExtCustomCheckBox();
                    if (detailsDto.getFrequency().equals(ConstantsUtils.MONTHLY)) {
                        checkBox.setEnabled(true);
                    } else {
                        checkBox.setEnabled(false);
                    }
                    checkBox.setImmediate(true);
                    checkBox.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            boolean check = checkBox.getValue();
                            int updatedRecordsNo = 0;
                            getBeanFromId(itemId).addBooleanProperties(propertyId, check);
                            if ("Total".equalsIgnoreCase(getBeanFromId(itemId).getGroup())) {
                                updatedRecordsNo = logic.checkTempTable(detailsDto, check);
                                if(check) {
                                checkAll = true;
                                } else {
                                checkAll = false;  
                                }
                                leftTable.setColumnCheckBox("check", true, checkAll);
                            } else {
                                updatedRecordsNo = logic.checkTempTable(detailsDto, check, "check", getBeanFromId(itemId), String.valueOf(filterDdlb.getValue()));
                            }
                            final String tableHierarchyNo = tableLogic.getTreeLevelonCurrentPage(itemId);
                            updateCheckForParentLevels(itemId, updatedRecordsNo, check);
                            updateCheckForChildLevels(tableHierarchyNo, itemId, check);
                        }
                    });
                    return checkBox;
                }
                return null;
            }
        });
        leftTable.setColumnCheckBox("check", true);
        checkAll = false;
        leftTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                checkAll = event.isChecked();
                int count = logic.checkTempTable(detailsDto, checkAll);
        List<String> levels = tableLogic.getAllLevels();
                for (String level : levels) {
                    boolean isPresent = true;
                    Object itemID = tableLogic.getcurrentTreeData(level);
                    if (itemID == null) {
                        isPresent = false;
                        itemID = tableLogic.getExpandedTreeValues(level);
                    }

                    if (itemID != null) {

                        TableDTO dto = (TableDTO) itemID;
                        dto.setCheck(checkAll);
                        if (isPresent) {
                            leftTable.getContainerProperty(itemID, "check").setValue(checkAll);
                        }
                    }
                }
    }
        });
    
    }

    /**
     * Field factor for right table.
     *
     * @param rightTable
     */
    private void configureFieldsForRightTables(final ExtPagedTreeTable rightTable) {
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {
                if (!"Total".equalsIgnoreCase(getBeanFromId(itemId).getGroup())) {
                    final TextField textField = new TextField();
                    oldValue = "";
                    textField.setData(propertyId + "~" + getBeanFromId(itemId).getSid() + "~" + getBeanFromId(itemId).getLevelNo());
                    textField.setImmediate(true);
                    textField.setWidth("100%");
                    textField.setEnabled(true);
                    textField.addFocusListener(new FieldEvents.FocusListener() {

                        @Override
                        public void focus(FieldEvents.FocusEvent event) {
                            oldValue = String.valueOf(((TextField) event.getComponent()).getValue());
                        }
                    });
                    textField.addBlurListener(new FieldEvents.BlurListener() {

                        public void blur(FieldEvents.BlurEvent event) {
                            try {
                                String currentLevel = tableLogic.getTreeLevelonCurrentPage(itemId) + "~" + propertyId;
                                if (refreshSet.isEmpty() || !refreshSet.contains(currentLevel)) {
                                    String value = String.valueOf(((TextField) event.getComponent()).getValue());
                                    String getData = String.valueOf(((TextField) event.getComponent()).getData());
                                    logic.UpdateTempTable(detailsDto, value, getData, getBeanFromId(itemId));
                                    getBeanFromId(itemId).addStringProperties(propertyId, DEC_FORMAT.format(Double.valueOf(value)));
                                    setRefreshSet(itemId, propertyId);
                                } else {
                                    MessageBox.showPlain(Icon.ERROR, "Refresh", "Please click refresh button", new MessageBoxListener() {

                                        @Override
                                        public void buttonClicked(ButtonId buttonId) {
                                            if (buttonId.name().equalsIgnoreCase("OK")) {
                                                textField.setValue(oldValue);
                                            }
                                        }
                                    }, ButtonId.OK);
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });

                    return textField;
                } else {
                    return null;
                }
            }
        });
    }

    private void setRefreshSet(final Object itemId, final Object propertyId) {
        for (String obj : tableLogic.getAllChildLevels(itemId)) {
            refreshSet.add(obj + "~" + propertyId);
        }
        for (String obj : tableLogic.getAllParentLevels(itemId)) {
            refreshSet.add(obj + "~" + propertyId);
        }
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        controlLayout = tableLogic.createControls();
        controlLayout.setSizeUndefined();
        controlLayout.addStyleName("responsivePagedTable");
        tableLogic.sinkItemPerPageWithPageLength(false);
        controlLayout.addComponent(refreshBtn);
        tableVerticalLayout.addComponent(controlLayout);
    }

    /**
     * Generate Logic
     */
    private void generateLogic() {
        try {
            if (detailsDto.getListView().equals("Expand")) {
                tableLogic.loadExpandData(detailsDto);
            } else {
                tableLogic.setProjectionResultsData(detailsDto);
            }
            initializeResultTable();
            configureResultTable();
            Boolean checkall=true;
            for (Object component :resultBeanContainer.getItemIds()) {
                TableDTO dto=(TableDTO)component;
               if(dto.getCheck()!=null && dto.getCheck().equals(false)){
                   checkall=false;
                   break;
               }
            }

            checkBoxList.clear();
            leftTable.setColumnCheckBox("check", true, checkall);
            generatedView=detailsDto.getDataView();
            filterValue=detailsDto.getFilterDdlb();
        } catch (Exception ex) {
            LOGGER.error(ex);;
        }
    }

    /**
     * Genrate Btn Logic.
     *
     * @param event the event
     * @throws java.lang.Exception
     */
    public void generateButton() {
        generateBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    detailsDto.setGenerated(Boolean.TRUE);
                    loadStartAndEndPeriodDDLB();
                    Date foreFrom = CommonUtil.stringToDateFormat(detailsDto.getForecastFromDate());
                    Date foreTo = CommonUtil.stringToDateFormat(detailsDto.getForecastToDate());

                    if (from.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Date Range", "Please select From date");
                        return;
                    } else if (to.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Date Range", "Please select to date");
                        return;
                    } else {
                        if (from.getValue().equals(foreFrom) || from.getValue().after(foreFrom)) {
                            detailsDto.setDetailsFromDate(format.format(from.getValue()));
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Date Range", "From date cannot be before " + detailsDto.getForecastFromDate());
                            return;
                        }
                        if (to.getValue() != null) {
                            if (to.getValue().equals(foreTo) || to.getValue().before(foreTo)) {
                                detailsDto.setDetailsToDate(format.format(to.getValue()));
                            } else {
                                AbstractNotificationUtils.getErrorNotification("Date Range", "To date cannot be after" + detailsDto.getForecastToDate());
                                return;
                            }
                        } else {
                            detailsDto.setDetailsToDate(StringUtils.EMPTY);
                        }
                    }
                    loadStartAndEndPeriodDDLB();
                    generateLogic();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

        });
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public TableDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof TableDTO) {
            targetItem = new BeanItem<TableDTO>(
                    (TableDTO) obj);
        }
        return (TableDTO) targetItem.getBean();
    }

    public void disableFieldsOnView() {
        adjustmentPanel.setEnabled(false);
        massUpdatePanel.setEnabled(false);
        rightTable.setEditable(false);
        leftTable.setColumnCheckBoxDisable("check", true);

    }

    private void getButtonPermission(Map<String, AppPermission> functionDCHM) {
        if (functionDCHM.get(ConstantsUtils.GENERATE) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.GENERATE)).isFunctionFlag()) {
            generateBtn.setVisible(false);
        } else {
            try {
                generateButton();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(DeductionDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (functionDCHM.get(ConstantsUtils.ADJUST) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.ADJUST)).isFunctionFlag()) {
            adjustBtn.setVisible(false);
        } else {
            adjustButton();
        }
        if (functionDCHM.get(ConstantsUtils.POPULATE) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.POPULATE)).isFunctionFlag()) {
            populate.setVisible(false);
            refreshBtn.setVisible(false);
        } else {
            populateButton();
            refreshButton();
        }
    }

    private void refreshButton() {
        LOGGER.info("Inside Refresh button logic");
        refreshBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    if (event != null) {
                        refreshLogic();
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        LOGGER.info("Ending Refresh button logic");
    }
   
    private void refreshLogic() {
        generateLogic();
        refreshSet.clear();
        childLevelSet.clear();
    }

    private void populateButton() {
        populate.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    String query = "";
                    if (fieldDdlb.getValue() == null || ConstantsUtils.SELECT_ONE.equals(fieldDdlb.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Mass Update", "Please select field");
                    } else if (levelDdlb.getValue() == null || ConstantsUtils.SELECT_ONE.equals(levelDdlb.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Mass Update", "Please select a level");
                    } else if (value.getValue() == null) {
                        AbstractNotificationUtils.getErrorNotification("Mass Update", "Please enter value");
                    } else {
                        if (startPeriod.getValue() == null) {
                            AbstractNotificationUtils.getErrorNotification("Mass Update", "Please select Start period");
                        } else if (endPeriod.getValue() == null) {
                            AbstractNotificationUtils.getErrorNotification("Mass Update", "Please select end period");
                        } else {
                            String[] start = String.valueOf(startPeriod.getValue()).split(" ");
                            String[] end = String.valueOf(endPeriod.getValue()).split(" ");
                            int startMonth = CommonUtil.getMonth(start[0]);
                            int endMonth = CommonUtil.getMonth(end[0]);
                            if (Integer.valueOf(start[1]) > Integer.valueOf(end[1])) {
                                AbstractNotificationUtils.getErrorNotification("Mass Update", "Start period cannot be greater than end period");
                            } else if (Integer.valueOf(start[1]) == Integer.valueOf(end[1]) && (startMonth > endMonth)) {
                                AbstractNotificationUtils.getErrorNotification("Mass Update", "Start period cannot be greater than end period");
                            } else {
                                if (refreshSet.isEmpty()) {
                                    String updateValue = value.getValue();
                                    String sqlID=StringUtils.EMPTY;
                                    String selectedSID=StringUtils.isNotBlank(filterValue)&&!ConstantsUtils.NULL.equalsIgnoreCase(filterValue)
                                            ? ("Customer".equalsIgnoreCase(generatedView) ? " AND CD.Company_master_sid="+filterValue : " AND CD.item_master_sid="+filterValue)
                                            :StringUtils.EMPTY;
                                    switch(String.valueOf(levelDdlb.getValue())){
                                        case "Customer":
                                            sqlID="Customer".equalsIgnoreCase(generatedView)?"mass-populate-customer":"mass-populate-customer-product-view";
                                            break;
                                        case "Brand":
                                            sqlID="Customer".equalsIgnoreCase(generatedView)?"mass-populate-brand":"mass-populate-brand-product-view";
                                            break;    
                                        case "Product":
                                            sqlID="mass-populate-item";
                                            break;  

                                    }
                                    boolean isCheck=logic.isCheckedDetails(sessionDTO,generatedView,StringUtils.isNotBlank(filterValue)&&!ConstantsUtils.NULL.equalsIgnoreCase(filterValue)?filterValue:"");
                                    if (isCheck) {
                                    query=CustomSQLUtil.get(sqlID)
                                            .replace("?VAL", updateValue)
                                            .replace("?UID", sessionDTO.getUserId())
                                            .replace("?SID", sessionDTO.getUiSessionId())
                                            .replace("?SY", start[1])
                                            .replace("?EY", end[1])
                                            .replace("?SM", String.valueOf(startMonth))
                                            .replace("?EM", String.valueOf(endMonth))
                                            .replace("?FILTER", selectedSID);
                                    
                                        logic.bulkUpdate(query);
                                        refreshLogic();
                                    } else {
                                        AbstractNotificationUtils.getErrorNotification("Error", "Please select a record to Mass Update.");
                                    }
                                } else {
                                    AbstractNotificationUtils.getErrorNotification("Refresh", "Please click refresh button");
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
    }

    /**
     * Adjustment button click handler
     *
     * @param event
     */
    public void adjustButton() {
        adjustBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                LOGGER.info("adjustButton listener method starts");
                try {
                    if (event != null) {
                        if (ConstantsUtils.NULL.equals(String.valueOf(adjustment.getValue()))) {
                            AbstractNotificationUtils.getErrorNotification("Adjustment", "Please enter Adjustment");
                        } else if (allocationMethododlogyDdlb.getValue() == null) {
                            AbstractNotificationUtils.getErrorNotification("Adjustment", "Please select Adjustment methododlogy");
                        } else {

                            if (ConstantsUtils.SELECT1.equals(String.valueOf(adjustmentPeriods.getValue()))) {
                                if (checkBoxList.size() > 0) {
                                    adjustmentLogic();
                                } else {
                                    AbstractNotificationUtils.getErrorNotification("No period selected", "Please select which periods need to be included in the adjustment. ");
                                }
                            } else {
                                adjustmentLogic();
                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
                  LOGGER.info("adjustButton listener method ends");
            }
        });
        LOGGER.info("Inside search listener method ends");
    }

    /**
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {
        LOGGER.info("Inside updateCheckForParentLevels");
        TableDTO dto = (TableDTO) itemId;
        int newRecordsCount = updatedRecordsNo;
        LOGGER.info(" updatedRecordsNo " + newRecordsCount);
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else {
            if (newRecordsCount < dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        }
        LOGGER.info((checkValue ? "Checked" : "Unchecked") + " updatedRecordsNo " + newRecordsCount);
        List<String> hierarchyNos = tableLogic.getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {
            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                TableDTO tempDto = (TableDTO) tempId;
                LOGGER.info(tempDto.getLevelNo() + " " + tempDto.getGroup() + " Parent Uncheck count before " + tempDto.getUncheckCount());
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }
                LOGGER.info(tempDto.getLevelNo() + " " + tempDto.getGroup() + " Parent Uncheck count after " + tempDto.getUncheckCount());
                if(tempDto.getUncheckCount() == 0) {
                    leftTable.setColumnCheckBox("check", true, true);
                } else {
                    leftTable.setColumnCheckBox("check", true, false);
                }
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("Exiting updateCheckForParentLevels");
    }

    /**
     * To update records count for child
     *
     * @param tableHierarchyNo
     * @param itemId
     * @param checkValue
     */
    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {
        LOGGER.info("Inside updateCheckForChildLevels");
        List<String> childTableHierarchyNos = tableLogic.getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                TableDTO tempDto = (TableDTO) tempId;
                LOGGER.info(tempDto.getLevelNo() + " " + tempDto.getGroup() + " Child Uncheck count before " + tempDto.getUncheckCount());

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(tempDto.getCcpCount());
                }
                LOGGER.info(tempDto.getLevelNo() + " " + tempDto.getGroup() + " Child Uncheck count after " + tempDto.getUncheckCount());
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("Ending updateCheckForChildLevels");
    }

    private void updateChecks(Object tempId, boolean isPresentInContainer) {

        TableDTO tempDto = (TableDTO) tempId;
        boolean checkValue = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties("check", checkValue);
        if (isPresentInContainer) {
            tableLogic.getContainerDataSource().getContainerProperty(tempId, "check").setValue(checkValue);
        }
    }

    public void loadFilterDdlb() {
        filterDdlb.removeAllItems();
        filterDdlb.addItem(ConstantsUtils.SELECT_ONE);
        for (Object[] object : (List<Object[]>)logic.loadFilterDdlb(detailsDto)) {
            filterDdlb.addItems(object[1]);
            filterDdlb.setItemCaption(object[1], object[0].toString());
        }
        detailsDto.setFilterDdlb(String.valueOf(filterDdlb.getValue()));
        filterValue=String.valueOf(filterDdlb.getValue());
    }

    public void loadDetailsOnTabChange() {
        LOGGER.info("Inside loadDetailsOnTabChange");
        generateLogic();
        LOGGER.info("Ending loadDetailsOnTabChange");
    }

    public void loadStartAndEndPeriodDDLB() {
        detailsDto.setFrequency(String.valueOf(frequencyDdlb.getValue()).equals(null) ? "Quarterly" : String.valueOf(frequencyDdlb.getValue()));
        if (ConstantsUtils.MONTHLY.equals(String.valueOf(frequencyDdlb.getValue()))) {
            massUpdate.setEnabled(true);
            if ("Enable".equalsIgnoreCase(String.valueOf(massUpdate.getValue()))) {
                startPeriod.setEnabled(true);
                endPeriod.setEnabled(true);
                fieldDdlb.setEnabled(true);
                levelDdlb.setEnabled(true);
                value.setEnabled(true);
                populate.setEnabled(true);
            }
            startPeriod.removeAllItems();
            endPeriod.removeAllItems();
            List list = CommonUtil.columnPropertyForDetailsTable(detailsDto);
            startPeriod.addItems(list);
            endPeriod.addItems(list);
        } else {
            massUpdate.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            fieldDdlb.setEnabled(false);
            levelDdlb.setEnabled(false);
            value.setEnabled(false);
            populate.setEnabled(false);
        }
    }

   private void adjustmentLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm " + type.getValue().toString(), "You are about to make the following ($/ %) adjustment for the following periods (list periods). Are you sure you want to continue?", new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("OK")) {
                    try {
                        if (ConstantsUtils.MONTHLY.equalsIgnoreCase(detailsDto.getFrequency())) {
                            
                            String lastValue = StringUtils.EMPTY;

                            List<Object> periodList = new ArrayList();
                            if (ConstantsUtils.SELECT1.equals(String.valueOf(adjustmentPeriods.getValue()))) {
                                periodList = checkBoxList;
                            } else {
                                periodList = rightHeader.getSingleColumns();
                            }
                            int periodSize = periodList.size();
                            for (int i = 0; i < periodSize; i++) {
                                Object[] arr = String.valueOf(periodList.get(i)).split("~");

                                if (i == 0) {
                                    lastValue += "m" + String.valueOf(arr[1]) + " " + String.valueOf(arr[2]);
                                } else {
                                    lastValue += ConstantsUtils.COMMA + "m" + String.valueOf(arr[1]) + " " + String.valueOf(arr[2]);
                                }
                            }
                            Object[] orderedArgs = {lastValue, detailsDto.getUserId(), detailsDto.getSessionId(), type.getValue().toString()
                                                    , adjustment.getValue(), basis.getValue().toString(), variable.getValue().toString(), allocationMethododlogyDdlb.getValue().toString()};
                            CommonUtil.callProcedure("PRC_DEDUCTION_CALENDAR_ADJUSTMENT", orderedArgs);

                            if (detailsDto.getListView().equals("Expand")) {
                                tableLogic.loadExpandData(detailsDto);
                            } else {
                                tableLogic.setProjectionResultsData(detailsDto);
                            }

                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.OK, ButtonId.CANCEL);
    }
    
}

