/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.tablelogic.CustomContainerLogic;
import com.stpl.app.galforecasting.logic.SalesProjectionLogic;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.lookups.AlternateHistoryLookup;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.ui.form.lookups.PMPYCalculator;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.Constant.FOUR_QUARTERS;
import static com.stpl.app.galforecasting.utils.Constant.GROUP;
import static com.stpl.app.galforecasting.utils.Constant.SALES;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import static com.stpl.app.galforecasting.utils.Constant.UNITS;
import com.stpl.app.galforecasting.utils.CustomExcelNM;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.TotalLivesChart;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_MODULE_NON_MANDATED;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
// TODO: Auto-generated Javadoc

/**
 * The Class SalesProjection.
 *
 * @author Mahesh
 */
public class SalesProjection extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(SalesProjection.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The history ddlb.
     */
    @UiField("historyDdlb")
    public ComboBox historyDdlb;
    /**
     * The field ddlb.
     */
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;
    /**
     * The value ddlb.
     */
    @UiField("valueDdlb")
    public CustomComboBox valueDdlb;
    /**
     * The pro period ord.
     */
    @UiField("proPeriodOrd")
    public OptionGroup proPeriodOrd;
    /**
     * The actuals projections.
     */
    @UiField("ActualsProjections")
    public OptionGroup actualsProjections;
    /**
     * The variables.
     */
    @UiField("variables")
    public OptionGroup variables;
    /**
     * The contract.
     */
    @UiField("contract")
    public CustomTextField contract;
    /**
     * The brand.
     */
    @UiField("brand")
    public CustomTextField brand;
    /**
     * The value txt.
     */
    @UiField("valueTxt")
    public TextField valueTxt;
    /**
     * The mass update.
     */
    @UiField("massUpdate")
    public OptionGroup massUpdate;
    /**
     * The type.
     */
    @UiField("type")
    public OptionGroup type;
    /**
     * The basis.
     */
    @UiField("basis")
    public OptionGroup basis;
    /**
     * The variable.
     */
    @UiField("variable")
    public OptionGroup variable;
    /**
     * The adjust periods.
     */
    @UiField("adjustPeriods")
    public OptionGroup adjustPeriods;
    /**
     * The view.
     */
    @UiField("view")
    public OptionGroup view;
    /**
     * The newnew btn.
     */
    @UiField("newBtn")
    public Button newBtn;
    /**
     * The edit btn.
     */
    @UiField("editBtn")
    public Button editBtn;
    /**
     * The pmpy.
     */
    @UiField("pmpy")
    public Button pmpy;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    public Button resetBtn;
    /**
     * The populate.
     */
    @UiField("populate")
    public Button populate;
    /**
     * The excel export.
     */
    @UiField("excelExport")
    public Button excelExport;
    /**
     * The graph icon.
     */
    @UiField("graphIcon")
    public Button graphIcon;
    /**
     * The start period
     */
    @UiField("startPeriod")
    public ComboBox startPeriod;
    /**
     * The end period
     */
    @UiField("endPeriod")
    public ComboBox endPeriod;
    /**
     * The methodology ddlb
     */
    @UiField("methodologyDdlb")
    public ComboBox methodology;
    /**
     * The allocation methodology
     */
    @UiField("allocationMethodology")
    public ComboBox allocMethodology;
    @UiField("adjustment")
    public TextField adjustment;
    /**
     * The level
     */
    @UiField("levelDdlb")
    public ComboBox level;
    /**
     * The level filter ddlb
     */
    @UiField("levelFilterDdlb")
    public ComboBox levelFilter;
    /**
     * The view ddlb
     */
    @UiField("viewDdlb")
    public ComboBox viewDdlb;
    /**
     * The generate
     */
    @UiField("generateBtn")
    public Button generate;
    @UiField("adjust")
    public Button adjust;
    @UiField("totalLives")
    public TextField totalLives;
    @UiField("calculate")
    public Button calculate;
    @UiField("tableLayout")
    public VerticalLayout tableLayout;
    @UiField("expand")
    public Button expand;
    @UiField("collapse")
    public Button collapse;

    public ComboBox group = new ComboBox();
    @UiField("lblEnd")
    public Label lblEnd;
    @UiField("lblStart")
    public Label lblStart;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 300;
    /**
     * The split position.
     */
    private final float splitPosition = 800;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    private Map<Object, Boolean> checkBoxMap = new HashMap<Object, Boolean>();
    private Map<Object, String> radioMap = new HashMap<Object, String>();
    private CustomTreeContainer<SalesRowDto> customContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);
    List<Object> possibleKeyList = new ArrayList<Object>();
    private boolean valueChange = false;
    Property.ValueChangeListener valueChangeListener = null;
    private Map<String, String> editedValues = new HashMap<String, String>();
    private Map<String, BigDecimal> incOrDecPer = new HashMap<String, BigDecimal>();
    final public BeanItemContainer<String> groupBean = new BeanItemContainer<String>(String.class);
    final public BeanItemContainer<String> massGroupBean = new BeanItemContainer<String>(String.class);
    String history = FOUR_QUARTERS;
    String actualsOrProjections = Constant.ACTUALS;
    String projectionPeriodorder = "Ascending";
    private SessionDTO session;
    private CustomContainerLogic tableLogic = new CustomContainerLogic();
    FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    CustomTableHeaderDTO leftHeader = null;
    CustomTableHeaderDTO rightHeader = null;
    CustomTableHeaderDTO excelHeader = null;
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    private int projectionId = 0;
    /**
     * The custom id.
     */
    int customId = 0;
    /**
     * The custom id to select.
     */
    int customIdToSelect = 0;
    /**
     * Customer Hierarchy
     */
    boolean isCustomHierarchy = false;
    /**
     * To store the current hierarchy
     */
    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    boolean customHierarchy = false;
    public Map<String, Integer> rowCountMap = new HashMap<String, Integer>();
    public Map<String, Integer> keymap = new HashMap<String, Integer>();
    ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    int projectionDetailsId;
    List<Object> projectionDetailsIdForPMPY;
    public boolean salesSubmitFlag = false;
    public boolean salesFlag = false;
    public boolean unitFlag = false;
    public boolean accGrwFlag = false;
    public boolean proGrwFlag = false;
    public boolean restrictOnViewChange = false;
    public boolean checkAll = false;
    List<Object> visibleExcelColumns = new ArrayList<Object>();
    public boolean pmpyImport = false;
    CustomTreeContainer<SalesRowDto> excelContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);
    public String variablesSelection = StringUtils.EMPTY;
    public Map<String, String> editedGroupValues = new HashMap<String, String>();
    private int hierarchyLevelNo = 0;
    List<String> doubleProjectedColumns = new ArrayList<String>();
    Map<Object, Object[]> reProjectedColumn = new HashMap<Object, Object[]>();
    Map<Object, Object[]> doubleHeaderHistoryMap = new HashMap<Object, Object[]>();
    Map<String, SalesRowDto> editedItems = new HashMap<String, SalesRowDto>();
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter unitFormat = new DataFormatConverter("#,##0");
    DataFormatConverter salesFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption();
        }
    };
    Property.ValueChangeListener groupFilterValueChangeListener = null;
    Property.ValueChangeListener baseLineFilterValueChangeListener = null;
    Property.ValueChangeListener methodologyFilterValueChangeListener = null;
    /**
     * Refresh Button
     */
    Button refresh = new Button("REFRESH");
    public String oldValue = StringUtils.EMPTY;
    public String changedProperty = StringUtils.EMPTY;
    public Boolean oldCheckValue = false;

    Set<String> tableHirarechyNos = new HashSet<String>();
    Map<String, String> hierTableNoMap = new HashMap<String, String>();
    ComboBox userGroupTest = new ComboBox();

    private ComboBox metohdologyFilter = new ComboBox();
    private ComboBox baseLineFilter = new ComboBox();
    final public BeanItemContainer<String> methdologyBean = new BeanItemContainer<String>(String.class);
    final public BeanItemContainer<String> baseLineBean = new BeanItemContainer<String>(String.class);

    /**
     * Instantiates a new SALES_SMALL projection.
     */
    public SalesProjection(SessionDTO session) {
        try {
            tableLogic.setRestrictLoad(false);
            this.session = session;
            projectionId = session.getProjectionId();
            tableLogic.setPageLength(10);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
            tableLogic.setProjectionId(session.getProjectionId());
            tableLogic.setUserId(session.getUserId());
            tableLogic.setSessionId(session.getSessionId());
            tableLogic.setCurrentHierarchy(new ArrayList<Leveldto>());
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
            tableLogic.setSalesObject(this);
            tableLogic.setTreeNodeMultiClick(false);
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/SalesProjection.xml"), this));
            configureTable();
            configurefields();
            initProduct();
            tableLogic.setRestrictLoad(false);

            enableDisableFields();
        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
        }
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public SalesRowDto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesRowDto) {
            targetItem = new BeanItem<SalesRowDto>(
                    (SalesRowDto) obj);
        }

        return (SalesRowDto) targetItem.getBean();
    }

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    private void initProduct() {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        resultsTable.markAsDirty();
        resultsTable.setSelectable(true);

        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);

        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        String historys = String.valueOf(historyDdlb.getValue());
        historys = historys.trim();
        final String actualsOrProjection = String.valueOf(actualsProjections.getValue());
        final String projectionPeriod = String.valueOf(proPeriodOrd.getValue());

        Map<String, String> selection = new HashMap<String, String>();
        selection.put(Constant.FREQUENCY, QUARTERLY.getConstant());
        selection.put(Constant.HISTORY, historys);
        selection.put("projectFrequency", String.valueOf(getProjections(session.getForecastDTO().getForecastStartDate(), session.getForecastDTO().getForecastEndDate())));
        selection.put(Constant.ORDER, projectionPeriod);
        selection.put("projection", actualsOrProjection);
        selection.put(SALES, "false");
        selection.put(UNITS, "false");
        selection.put(Constant.P_Growth, "false");
        selection.put(Constant.A_Growth, "false");
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

            final String[] col = tempVariables.split(",");

            for (String value : col) {
                value = value.trim();

                if (value.equals(Constant.SALES_SMALL)) {

                    selection.put(SALES, Constant.TRUE);

                }
                if (value.equals(Constant.UNITS_SMALL)) {

                    selection.put(UNITS, Constant.TRUE);

                }
                if (value.equals(Constant.PRODUCT_GROWTH)) {

                    selection.put(Constant.P_Growth, Constant.TRUE);

                }
                if (value.equals(Constant.ACCOUNT_GROWTH)) {

                    selection.put(Constant.A_Growth, Constant.TRUE);

                }
            }
        }

        leftHeader = HeaderUtils.getSalesProjectionLeftTableColumns(selection);
        List headerList = HeaderUtils.getSalesProjectionRightTableColumns(selection, session);
        rightHeader = (CustomTableHeaderDTO) headerList.get(0);

        excelHeader = (CustomTableHeaderDTO) headerList.get(1);
        doubleProjectedColumns = (List<String>) headerList.get(2);
        doubleHeaderHistoryMap = (Map<Object, Object[]>) headerList.get(3);

        List<Object> visibleColumn = rightHeader.getSingleColumns();
        List<String> columnHeader = rightHeader.getSingleHeaders();
        List<Object> doubleVisibleColumn = rightHeader.getDoubleColumns();
        List<String> doubleColumnHeader = rightHeader.getDoubleHeaders();

        if (proPeriodOrd.getValue().toString().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(visibleColumn);
            Collections.reverse(columnHeader);
            Collections.reverse(doubleVisibleColumn);
            Collections.reverse(doubleColumnHeader);

        }

        tableLogic.setRightDto(rightHeader);
        tableLogic.setLevelNo(1);
        tableLogic.setCurrentHierarchy(currentHierarchy);
        tableLogic.setIsCustomHierarchy(false);
        tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        tableLogic.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
        tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
        customContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);
        possibleKeyList = rightHeader.getSingleColumns();
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());
        resultsTable.setContainerDataSource(customContainer);

        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        rightTable.setEditable(true);
        rightHeader.getDoubleColumns();
        List objList = rightHeader.getDoubleColumns();
        for (Object obj : objList) {
            if (!String.valueOf(obj).contains(GROUP)) {
                rightTable.setDoubleHeaderColumnCheckBox(obj, true);
                checkBoxMap.put(obj, false);
            }
        }

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.CHECK)) {
                leftTable.setColumnCheckBox(obj, true);
                checkAll = false;
            }
        }

        Date currentDate = session.getForecastDTO().getForecastStartDate();

        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        for (Object obj : rightHeader.getDoubleHistoryColumns()) {

            Object single[] = doubleHeaderHistoryMap.get(obj);
            for (Object ob : single) {
                rightTable.setColumnRadioButton(ob, (String) obj);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }

        leftTable.addColumnCheckListener(checkListener);

        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                if (event.isChecked()) {
                    checkBoxMap.put(event.getPropertyId(), true);
                    String arr[] = rightTable.getColumnRadioButtonArray((String) event.getPropertyId());
                    if (arr != null) {
                        for (String a : arr) {
                            rightTable.setColumnRadioButtonDisable(a, false);
                        }

                    }
                } else {
                    checkBoxMap.put(event.getPropertyId(), false);

                    String arr[] = rightTable.getColumnRadioButtonArray((String) event.getPropertyId());
                    if (arr != null) {
                        for (String a : arr) {
                            rightTable.setColumnRadioButtonDisable(a, true);
                        }

                    }

                }

            }
        });

        rightTable.addColumnRadioCheckListener(new ExtCustomTable.ColumnRadioCheckListener() {
            @Override
            public void columnRadioCheck(ExtCustomTable.ColumnRadioCheckEvent event) {
                radioMap.put(event.getRadioButtonName(), event.getCurrentValue());
            }
        });

        leftTable.setEditable(true);
        loadPeriods();

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        if (leftHeader.getDoubleColumns() != null) {

            leftTable.setDoubleHeaderVisible(false);
            leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        }
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, 145, TAB_SALES_PROJECTION.getConstant());
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                String property = String.valueOf(propertyId);
                final Item item = container.getItem(itemId);
                final String level = String.valueOf(item.getItemProperty("hierarchyLevel").getValue());
                if (String.valueOf(propertyId).equals(Constant.CHECK)) {
                    final ExtCustomCheckBox checkRecord = new ExtCustomCheckBox();

                    if (checkAll) {
                        checkRecord.setValue(true);
                    } else {
                        checkRecord.setValue(false);
                    }

                    checkRecord.setImmediate(true);

                    checkRecord.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {

                            SalesRowDto dto = (SalesRowDto) itemId;
                            Boolean checkValue = checkRecord.getValue();
                            dto.addBooleanProperties(propertyId, checkValue);
                            final String tableHierarchyNo = tableLogic.getTreeLevelonCurrentPage(itemId);

                            resultsTable.getLeftFreezeAsTable().setRefresh(false);

                            Boolean newCheckValue = Boolean.valueOf(((ExtCustomCheckBox) event.getComponent()).getValue());
                            String levelValues = StringUtils.EMPTY;
                            String hierarchyNo = "WHERE";
                            hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + getBeanFromId(itemId).getHierarchyNo() + "%' ";
                            levelValues = levelValues + "'" + getBeanFromId(itemId).getLevelName() + "'";
                            Object[] inputParameters = new Object[14];

                            if (tableLogic.isIsCustomHierarchy()) {

                                inputParameters[0] = session.getProjectionId();
                                inputParameters[1] = getBeanFromId(itemId).getTreeLevelNo() - 1;
                                String groupFilterValue = String.valueOf(group.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
                                String methodologyFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
                                String baseFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());

                                inputParameters[2] = groupFilterValue + "-" + methodologyFilterValue + "-" + baseFilterValue;

                                inputParameters[3] = StringUtils.EMPTY + getBeanFromId(itemId).getHierarchyNo() + StringUtils.EMPTY;
                                inputParameters[8] = "getCustomProjectDetailsId";
                                inputParameters[4] = session.getUserId();
                                inputParameters[5] = session.getSessionId();
                                inputParameters[6] = checkAll;
                                if (newCheckValue) {
                                    inputParameters[7] = Constant.CHECK;
                                } else {
                                    inputParameters[7] = "uncheck";
                                }

                                inputParameters[9] = tableLogic.getCustomId();
                                inputParameters[10] = getBeanFromId(itemId).getParentHierarchyType();
                                inputParameters[11] = getBeanFromId(itemId).getLastCustomerHierarchyno();
                                inputParameters[12] = getBeanFromId(itemId).getLastProductHierarchyno();
                                inputParameters[13] = getBeanFromId(itemId).getHierarchyType();
                            } else {

                                inputParameters[0] = session.getProjectionId();
                                String groupFilterValue = String.valueOf(group.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
                                String methodologyFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
                                String baseFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());

                                inputParameters[1] = groupFilterValue + "-" + methodologyFilterValue + "-" + baseFilterValue;
                                inputParameters[2] = tableLogic.getViewType();
                                inputParameters[3] = hierarchyNo;
                                inputParameters[8] = "saveCheckUncheckRecords";
                                inputParameters[4] = session.getUserId();
                                inputParameters[5] = session.getSessionId();
                                inputParameters[6] = checkAll;
                                if (newCheckValue) {
                                    inputParameters[7] = Constant.CHECK;
                                } else {
                                    inputParameters[7] = "uncheck";
                                }

                            }
                            SalesProjectionLogic salesLogic = new SalesProjectionLogic();
                            int updatedRecordsNo = salesLogic.savecheckedRecords(inputParameters) * (3);

                            updateCheckForParentLevels(itemId, updatedRecordsNo, checkValue);
                            updateCheckForChildLevels(tableHierarchyNo, itemId, checkValue);
                            if (!newCheckValue) {
                                ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                                leftTable.removeColumnCheckListener(checkListener);
                                leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                                leftTable.addColumnCheckListener(checkListener);
                                checkAll = false;
                            }

                            resultsTable.getLeftFreezeAsTable().setRefresh(true);
                        }
                    });

                    return checkRecord;
                }

                if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    if (GROUP.equals(property) && (Constant.TRADINGPARTNER.equalsIgnoreCase(level.trim()) || Constant.TRADING_PARTNER.equals(level.trim()))) {

                        final TextField textField = new TextField();
                        textField.setData(getBeanFromId(itemId).getHierarchyNo());

                        textField.setImmediate(true);
                        textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                        textField.setWidth(100, UNITS_PERCENTAGE);
                        textField.addFocusListener(new FieldEvents.FocusListener() {
                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                attachValueChangeListener((AbstractField) event.getComponent());
                            }
                        });
                        textField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                if (valueChange) {
                                    editedGroupValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));

                                    valueChange = false;
                                    SalesRowDto dto = (SalesRowDto) itemId;
                                    customContainer.getContainerProperty(itemId, Constant.GROUP).setValue(String.valueOf(((TextField) event.getComponent()).getValue()));
                                    dto.addStringProperties(Constant.GROUP, String.valueOf(((TextField) event.getComponent()).getValue()));
                                    saveEditedRecs();
                                }
                                detachLisener((AbstractField) event.getComponent());
                            }
                        });

                        return textField;

                    }
                }

                return null;
            }
        });

        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {

                if (!Constant.VIEW.equalsIgnoreCase(session.getAction())) {

                    String fieldvalue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue());

                    if (Constant.NULL.equals(fieldvalue)) {
                        SalesRowDto dto = (SalesRowDto) itemId;
                        dto.addStringProperties(propertyId, "-");
                        container.getContainerProperty(itemId, propertyId).setValue("-");
                    }
                    if (!String.valueOf(propertyId).contains(Constant.HISTORY_CAPS) && !String.valueOf(propertyId).contains(Constant.ACTUALSALES) && !String.valueOf(propertyId).contains(Constant.ACTUALUNITS) && !String.valueOf(propertyId).contains("Dis")) {

                        final TextField textField = new TextField();
                        textField.setData(propertyId + "~" + getBeanFromId(itemId).getHierarchyNo() + "~" + getBeanFromId(itemId).getLastCustomerHierarchyno() + "~" + getBeanFromId(itemId).getLastProductHierarchyno());
                        textField.setImmediate(true);
                        textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                        textField.setWidth(100, UNITS_PERCENTAGE);

                        if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                            textField.setConverter(salesFormat);
                        } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
                            textField.setConverter(unitFormat);
                        } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
                            textField.setConverter(percentFormat);
                        }

                        textField.addFocusListener(new FieldEvents.FocusListener() {
                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                oldValue = String.valueOf(((TextField) event.getComponent()).getValue());
                                oldValue = oldValue.replace("$", StringUtils.EMPTY);
                                oldValue = oldValue.replace(",", StringUtils.EMPTY);
                                oldValue = oldValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                            }
                        });
                        textField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                String newValue = String.valueOf(((TextField) event.getComponent()).getValue());
                                newValue = newValue.replace("$", StringUtils.EMPTY);
                                newValue = newValue.replace(",", StringUtils.EMPTY);
                                newValue = newValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                                Double newNumber, oldNumber = 0.0;
                                if (!oldValue.equals(newValue)) {
                                    BigDecimal newBigNum = new BigDecimal(newValue);
                                    newBigNum.round(MathContext.DECIMAL128);
                                    BigDecimal oldBigNum = new BigDecimal(oldValue);
                                    oldBigNum.round(MathContext.DECIMAL128);
                                    if (!oldBigNum.equals(new BigDecimal(0))) {

                                        newNumber = Double.valueOf(newValue);
                                        oldNumber = Double.valueOf(oldValue);
                                        Double incOrDec = ((newNumber - oldNumber) / oldNumber) * 100;
                                        BigDecimal incOrDecBig = ((newBigNum.subtract(oldBigNum)).divide(oldBigNum, 22, RoundingMode.HALF_UP));

                                        incOrDecBig.round(MathContext.DECIMAL128);

                                        incOrDecPer.put(String.valueOf(((TextField) event.getComponent()).getData()), incOrDecBig);
                                    }

                                    String tempValue = String.valueOf(((TextField) event.getComponent()).getData());
                                    String tempArray[] = tempValue.split("-");
                                    tempValue = tempArray[2];
                                    String tempArray1[] = tempValue.split("~");
                                    if (!tempArray1[0].contains(Constant.GROWTH)) {

                                        if (changedProperty.equals(tempArray1[0]) || changedProperty.equals(StringUtils.EMPTY)) {
                                            changedProperty = tempArray1[0];
                                        } else {
                                            ((TextField) event.getComponent()).setValue(oldValue);
                                            AbstractNotificationUtils.getErrorNotification("Mandatory Error", "Please change either sales or units for all periods,\n cannot combine both sales and units for Manual Entry");
                                            return;
                                        }
                                    }

                                    editedValues.put(String.valueOf(((TextField) event.getComponent()).getData()), String.valueOf(((TextField) event.getComponent()).getValue()));
                                    editedItems.put(getBeanFromId(itemId).getHierarchyNo() + "~" + getBeanFromId(itemId).getLastCustomerHierarchyno() + "~" + getBeanFromId(itemId).getLastProductHierarchyno(), getBeanFromId(itemId));
                                    if (editedValues.size() > 10) {
                                        saveEditedRecs();
                                        changedProperty = StringUtils.EMPTY;
                                        editedValues.clear();
                                        incOrDecPer.clear();
                                        editedItems.clear();
                                    }
                                    valueChange = false;

                                    tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                }

                            }
                        });

                        return textField;

                    }
                }
                return null;
            }
        });

        levelFilter.addValueChangeListener(levelFilterChangeOption);

        if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
            currentHierarchy = CommonLogic.getCustomerHierarchy(projectionId, hierarchyLevelNo);
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
        } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
            currentHierarchy = CommonLogic.getProductHierarchy(projectionId, hierarchyLevelNo);
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getProductLevelNumber()));
        } else if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = true;

        }

        tableLogic.setCurrentHierarchy(currentHierarchy);
        history = String.valueOf(historyDdlb.getValue());
        variablesSelection = String.valueOf(variables.getValue());
        actualsOrProjections = String.valueOf(actualsProjections.getValue());
        projectionPeriodorder = String.valueOf(proPeriodOrd.getValue()).trim();
        restrictOnViewChange = true;
        List<Object> propertyIdList = Arrays.asList(resultsTable.getRightFreezeAsTable().getVisibleColumns());
        resultsTable.getRightFreezeAsTable().setRefresh(Boolean.FALSE);
        for (Object propertyId : propertyIdList) {
            formatData(propertyId);
        }
        loadMethodology();
        loadBaseLine();
        leftTable.setColumnWidth(Constant.CHECK, 120);
        leftTable.setColumnWidth(Constant.LEVELNAME, 150);
        leftTable.setColumnWidth(Constant.GROUP, 120);

        resultsTable.getRightFreezeAsTable().setRefresh(Boolean.TRUE);
    }

    public void levelFilterDdlbChangeOption() {

        if (levelFilter.getValue() != null) {
            tableLogic.setLevelFilterValue((String) levelFilter.getValue());
        } else {

            tableLogic.setLevelFilterValue(STRING_EMPTY);
        }
        Object selections[] = new Object[10];
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        salesLogic.levelFilterSalesProjection(selections);

        reLoadTable();
    }

    private void loadPeriods() {

        startPeriod.removeAllItems();
        endPeriod.removeAllItems();

        startPeriod.addItem(Constant.SELECT_ONE);
        startPeriod.setNullSelectionItemId(Constant.SELECT_ONE);
        endPeriod.addItem(Constant.SELECT_ONE);
        endPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        startPeriod.addItems(doubleProjectedColumns);
        endPeriod.addItems(doubleProjectedColumns);

    }

    /**
     *
     * Configurefields.
     */
    private void configurefields() {

        adjustment.setStyleName(Constant.TXT_RIGHT_ALIGN);

        resultsTable.getRightFreezeAsTable().setFilterBarVisible(false);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);

        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        SalesProjectionLogic logic = new SalesProjectionLogic();

        historyDdlb.focus();
        historyDdlb.addItem(Constant.SELECT_ONE);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        massUpdate.addItem(Constant.LabelConstants.ENABLE);
        massUpdate.addItem(Constant.LabelConstants.DISABLE);
        massUpdate.select(Constant.LabelConstants.DISABLE);
        massUpdate.setStyleName(Constant.HORIZONTAL);
        tableLayout.addComponent(resultsTable);
        tableLayout.addComponent(excelTable);

        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
        cssControls.addComponent(refresh);
        tableLayout.addComponent(cssControls);
        proPeriodOrd.addItem(Constant.ASCENDING);
        proPeriodOrd.addItem(Constant.DESCENDING);
        proPeriodOrd.select(Constant.ASCENDING);
        proPeriodOrd.setStyleName(Constant.HORIZONTAL);

        actualsProjections.addItem(Constant.ACTUALS);
        actualsProjections.addItem(Constant.PROJECTIONS);
        actualsProjections.addItem(Constant.BOTH);
        actualsProjections.select(Constant.ACTUALS);
        actualsProjections.setStyleName(Constant.HORIZONTAL);

        variables.addItem(Constant.SALES_SMALL);
        variables.addItem(Constant.UNITS_SMALL);
        variables.addItem(Constant.PRODUCT_GROWTH);
        variables.addItem(Constant.ACCOUNT_GROWTH);

        variables.select(Constant.SALES_SMALL);
        salesFlag = true;
        variables.setStyleName(Constant.HORIZONTAL);

        type.addItem(Constant.LabelConstants.INCREMENTAL);
        type.addItem(Constant.LabelConstants.OVERRIDE);
        type.select(Constant.LabelConstants.INCREMENTAL);
        type.setStyleName(Constant.HORIZONTAL);

        basis.addItem(Constant.LabelConstants.AMOUNT);
        basis.addItem(Constant.LabelConstants.PERCENTAGE);
        basis.select(Constant.LabelConstants.AMOUNT);
        basis.setStyleName(Constant.HORIZONTAL);

        variable.addItem(Constant.SALES_SMALL);
        variable.addItem(Constant.UNIT);
        variable.select(Constant.SALES_SMALL);
        variable.setStyleName(Constant.HORIZONTAL);

        adjustPeriods.addItem(Constant.All);
        adjustPeriods.addItem(Constants.ButtonConstants.SELECT.getConstant());
        adjustPeriods.select(Constant.All);
        adjustPeriods.setStyleName(Constant.HORIZONTAL);

        graphIcon.setStyleName(Reindeer.BUTTON_LINK);

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT);
        view.addItem(Constant.CUSTOM);
        view.select(Constant.CUSTOMER_SMALL);
        view.setStyleName(Constant.HORIZONTAL);

        excelExport.setIcon(excelExportImage);

        excelTable.setVisible(false);
        graphIcon.setIcon(graphImage);

        fieldDdlb.addItem(Constant.GROUPFCAPS);
        fieldDdlb.addItem(Constant.ACCOUNT_GROWTH);
        fieldDdlb.addItem(Constant.PRODUCT_GROWTH);
        fieldDdlb.addItem(Constant.SELECT_ONE);
        fieldDdlb.addItem(Constant.SELECT_ONE);
        fieldDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        valueDdlb.setVisible(true);
        valueDdlb.addItem(Constant.SELECT_ONE);
        valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        valueDdlb.addBlurValue(Boolean.TRUE);
        valueTxt.setStyleName(Constant.TXT_RIGHT_ALIGN);
        valueTxt.setVisible(false);

        startPeriod.addItem(Constant.SELECT_ONE);
        startPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        endPeriod.addItem(Constant.SELECT_ONE);
        endPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        methodology.addItem(Constant.SELECT_ONE);
        methodology.setNullSelectionItemId(Constant.SELECT_ONE);

        methodology.addItem(Constant.AVERAGE);
        methodology.addItem(Constant.SINGLE_PERIOD);
        methodology.addItem(Constant.PERCENTAGEOFBUSINESS);

        allocMethodology.addItem(Constant.SELECT_ONE);
        allocMethodology.setNullSelectionItemId(Constant.SELECT_ONE);
        allocMethodology.addItem(Constant.HISPEROFBUSINESS);
        allocMethodology.addItem(Constant.FOREPERCOFBUSINESS);

        level.addItem(Constant.SELECT_ONE);
        level.setNullSelectionItemId(Constant.SELECT_ONE);

        levelFilter.addItem(Constant.SELECT_ONE);
        levelFilter.setNullSelectionItemId(Constant.SELECT_ONE);

        viewDdlb.addItem(Constant.SELECT_ONE);
        viewDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        valueDdlb.setNewItemsAllowed(true);

        group.setContainerDataSource(groupBean);
        valueDdlb.setContainerDataSource(massGroupBean);

        metohdologyFilter.setContainerDataSource(methdologyBean);
        group.setNullSelectionAllowed(false);
        metohdologyFilter.setNullSelectionAllowed(false);
        baseLineFilter.setNullSelectionAllowed(false);

        baseLineFilter.setContainerDataSource(baseLineBean);
        refresh.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                callManualEntryProcedure();
            }
        });
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                throw new UnsupportedOperationException("Not supported yet."); 
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                throw new UnsupportedOperationException("Not supported yet."); 
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (GROUP.equals(propertyId)) {

                    group.setContainerDataSource(groupBean);
                    return group;
                } else if (Constant.METHODOLOGY.equals(propertyId)) {

                    metohdologyFilter.setContainerDataSource(methdologyBean);

                    return metohdologyFilter;

                } else if (Constant.BASELINE.equals(propertyId)) {

                    baseLineFilter.setContainerDataSource(baseLineBean);

                    return baseLineFilter;
                } else if (Constant.LEVELNAME.equals(propertyId)) {
                    TextField levelField = new TextField();
                    levelField.setReadOnly(true);
                    levelField.setWidth("100%");
                    return levelField;
                } else if (Constant.CHECK.equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    checkField.setReadOnly(true);
                    return checkField;

                }

                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {

            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {

            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                throw new UnsupportedOperationException("Not supported yet."); 
            }
        });

        CommonUtils.frequenceValueChange(QUARTERLY.getConstant(), historyDdlb, session);
        startPeriod.setImmediate(true);
        endPeriod.setImmediate(true);
        historyDdlb.setValue(1);

        Object input[] = new Object[9];
        input[8] = "pmpyProDetailId";
        input[0] = session.getProjectionId();
        int count = logic.getPMPYProtDetID(input);

        if (count == 0) {
            pmpy.setEnabled(false);
        }

        totalLives.setValue(String.valueOf(logic.getTotalLives(session)));

        if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            setProjectionSelection();
        }

        variables.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {

                    String tempVariables = event.getProperty().getValue().toString();

                    tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

                    final String[] col = tempVariables.split(",");

                    for (String value : col) {
                        value = value.trim();

                        if (value.equals(Constant.SALES_SMALL)) {
                            salesFlag = false;
                        }
                        if (value.equals(Constant.UNITS_SMALL)) {
                            unitFlag = false;
                        }
                        if (value.equals(Constant.PRODUCT_GROWTH)) {
                            accGrwFlag = false;
                        }
                        if (value.equals(Constant.ACCOUNT_GROWTH)) {
                            proGrwFlag = false;
                        }
                    }
                } else {
                    salesFlag = false;
                    unitFlag = false;
                    proGrwFlag = false;
                    accGrwFlag = false;
                }
            }
        });

        if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
            currentHierarchy = CommonLogic.getCustomerHierarchy(projectionId, hierarchyLevelNo);
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
        } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
            currentHierarchy = CommonLogic.getProductHierarchy(projectionId, hierarchyLevelNo);
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getProductLevelNumber()));
        } else if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = true;
            currentHierarchy = CommonLogic.getCustomTree(customId);
        }
        loadLevelFilterValue();
        loadLevelFilter();
        loadUserGroup();
        loadMethodology();
        loadBaseLine();

        groupFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.info("groupDdlbChangeOption ValueChangeEvent initiated ");
                String groupFilterValue = String.valueOf(group.getValue());
                if (!groupFilterValue.equals(Constant.SHOW_ALL)) {
                    tableLogic.setUserGroup(groupFilterValue);
                    reLoadTable();
                } else {
                    tableLogic.setUserGroup(STRING_EMPTY);
                    reLoadTable();
                }

                LOGGER.info("groupDdlbChangeOption ValueChangeEvent ends ");
            }
        };

        baseLineFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String baseLineFilterValue = String.valueOf(baseLineFilter.getValue());
                if (!baseLineFilterValue.equals(Constant.SHOW_ALL)) {
                    tableLogic.setBaseLine(baseLineFilterValue);
                    reLoadTable();
                } else {
                    tableLogic.setBaseLine(STRING_EMPTY);
                    reLoadTable();
                }
            }
        };

        methodologyFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                String methodologyFilterValue = String.valueOf(event.getProperty().getValue());
                if (!methodologyFilterValue.equals(Constant.SHOW_ALL)) {
                    tableLogic.setMethodology(methodologyFilterValue);
                    reLoadTable();
                } else {
                    tableLogic.setMethodology(STRING_EMPTY);
                    reLoadTable();
                }
            }
        };
        group.addValueChangeListener(groupFilterValueChangeListener);
        baseLineFilter.addValueChangeListener(baseLineFilterValueChangeListener);
        metohdologyFilter.addValueChangeListener(methodologyFilterValueChangeListener);

    }

    /**
     * Contract brand lookup.s
     *
     * @param event the event
     */
    @UiHandler("contract")
    public void contractLookup(CustomTextField.ClickEvent event) {
        boolean contractSelected = false;
        boolean hasNoActuals = false;
        SalesRowDto salesRow = new SalesRowDto();

        SalesProjectionLogic logic = new SalesProjectionLogic();
        int i = 0;

        for (SalesRowDto dto : customContainer.getItemIds()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("CONTRACT HOLDER".equalsIgnoreCase(dto.getHierarchyLevel()) || "CH".equalsIgnoreCase(dto.getHierarchyLevel()) || "contractholder".equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;
                salesRow = dto;
                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(dto));
            }
        }

        if (contractSelected && i == 1) {

            String levelValues = StringUtils.EMPTY;
            String hierarchyNo = "WHERE";
            hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + salesRow.getHierarchyNo() + "%' ";
            levelValues = levelValues + "'" + salesRow.getLevelName() + "'";
            Object[] inputParameters = new Object[14];
            inputParameters[7] = tableLogic.isIsCustomHierarchy();

            if (tableLogic.isIsCustomHierarchy()) {

                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = salesRow.getTreeLevelNo() - 1;

                inputParameters[3] = StringUtils.EMPTY + salesRow.getHierarchyNo() + StringUtils.EMPTY;
                inputParameters[8] = "getZeroAc";
                inputParameters[4] = session.getUserId();
                inputParameters[5] = session.getSessionId();

                inputParameters[9] = tableLogic.getCustomId();
                inputParameters[10] = salesRow.getParentHierarchyType();
                inputParameters[11] = salesRow.getLastCustomerHierarchyno();
                inputParameters[12] = salesRow.getLastProductHierarchyno();
                inputParameters[13] = salesRow.getHierarchyType();

            } else {

                inputParameters[0] = session.getProjectionId();
                inputParameters[4] = tableLogic.getViewType();
                inputParameters[5] = levelValues;
                inputParameters[6] = hierarchyNo;
                inputParameters[8] = "getZeroAc";
                inputParameters[9] = session.getUserId();
                inputParameters[10] = session.getSessionId();

            }

            hasNoActuals = logic.isNoActuals(inputParameters);

            if (hasNoActuals) {
                saveEditedRecs();

                final AlternateHistoryLookup alternateContractLookup = new AlternateHistoryLookup("Alternate History Lookup", INDICATOR_MODULE_NON_MANDATED.getConstant(), session, "holder", salesRow.getHierarchyNo(), contract, brand);
                alternateContractLookup.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {

                        refreshTableData(getCheckedRecordsHierarchyNo());
                    }
                });

                getUI().addWindow(alternateContractLookup);
            } else {
                AbstractNotificationUtils.getErrorNotification("Contract LookUp", "Selected contract should not have  History");
            }
        } else {
            if (contractSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Contract Holder Selected",
                        "There are More than one Contract Holder selected.\n Please select only one Contract Holder and try again");

            } else {

                AbstractNotificationUtils.getErrorNotification("No Contract Holder Selected.", "Please select a Contract Holder. ");

            }
        }

    }

    @UiHandler("brand")
    public void brandLookup(CustomTextField.ClickEvent event) {

        boolean contractSelected = false;
        boolean hasNoActuals = false;
        SalesRowDto salesRow = new SalesRowDto();
        SalesProjectionLogic logic = new SalesProjectionLogic();
        int i = 0;
        for (SalesRowDto dto : customContainer.getItemIds()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("BRAND".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.BRAND.equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;

                salesRow = dto;
                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(dto));
            }

        }

        if (contractSelected && i == 1) {

            String levelValues = StringUtils.EMPTY;
            String hierarchyNo = "WHERE";
            hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + salesRow.getHierarchyNo() + "%' ";
            levelValues = levelValues + "'" + salesRow.getLevelName() + "'";
            Object[] inputParameters = new Object[14];
            inputParameters[7] = tableLogic.isIsCustomHierarchy();

            if (tableLogic.isIsCustomHierarchy()) {

                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = salesRow.getTreeLevelNo() - 1;

                inputParameters[3] = StringUtils.EMPTY + salesRow.getHierarchyNo() + StringUtils.EMPTY;
                inputParameters[8] = "getZeroAc";
                inputParameters[4] = session.getUserId();
                inputParameters[5] = session.getSessionId();

                inputParameters[9] = tableLogic.getCustomId();
                inputParameters[10] = salesRow.getParentHierarchyType();
                inputParameters[11] = salesRow.getLastCustomerHierarchyno();
                inputParameters[12] = salesRow.getLastProductHierarchyno();
                inputParameters[13] = salesRow.getHierarchyType();

            } else {

                inputParameters[0] = session.getProjectionId();
                inputParameters[4] = tableLogic.getViewType();
                inputParameters[5] = levelValues;
                inputParameters[6] = hierarchyNo;
                inputParameters[8] = "getZeroAc";
                inputParameters[9] = session.getUserId();
                inputParameters[10] = session.getSessionId();

            }

            hasNoActuals = logic.isNoActuals(inputParameters);

            if (hasNoActuals) {

                saveEditedRecs();
                AlternateHistoryLookup alternateHistoryLookup = new AlternateHistoryLookup("Alternate History Lookup", INDICATOR_MODULE_NON_MANDATED.getConstant(), session, Constant.BRAND, salesRow.getHierarchyNo(), contract, brand);
                alternateHistoryLookup.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        refreshTableData(getCheckedRecordsHierarchyNo());

                    }
                });

                getUI().addWindow(alternateHistoryLookup);
            } else {
                AbstractNotificationUtils.getErrorNotification("Brand LookUp", "Selected Brand should not have  History");
            }
        } else {
            if (contractSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Brand Selected",
                        "There are More than one Brands selected.\n Please select only one Brand and try again");

            } else {

                AbstractNotificationUtils.getErrorNotification("No Brand Selected.", "Please select a Brand. ");

            }
        }

    }

    /**
     * new the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {

        final CustomTreeBuild customTree = new CustomTreeBuild(Constant.ADD_SMALL, session);
        customTree.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();

                }
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        if (CommonLogic.editButtonValidation(viewDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(Constant.EDIT, session, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
    }

    /**
     * Pmpy calculator.
     *
     * @param event the event
     */
    @UiHandler("pmpy")
    public void pmpyCalculator(Button.ClickEvent event) {
        boolean tpSelected = false;
        boolean hasActuals = false;
        int i = 0;
        String levelValues = StringUtils.EMPTY;
        String hierarchyNo = StringUtils.EMPTY;

        for (SalesRowDto dto : customContainer.getItemIds()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && ((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                tpSelected = true;
                i++;
                hierarchyNo = dto.getHierarchyNo();

                levelValues = dto.getLevelName();

                tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(dto));
            }
            for (Object key : dto.getProperties().keySet()) {
                if ((Boolean) dto.getPropertyValue(Constant.CHECK) && ((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                    if (String.valueOf(key).contains("Actual")) {
                        String value = String.valueOf(dto.getProperties().get(key));

                        if (!value.equals("-") && !value.equals("0.00") && !value.equals("$0") && !value.equals(DASH) && !value.equals("0.000000")) {
                            hasActuals = true;

                        }

                    }
                }

            }

        }

        if (tpSelected && i == 1) {

            if (!hasActuals) {

                PMPYCalculatorDTO pmpyDTO = new PMPYCalculatorDTO();
                SalesProjectionLogic logic = new SalesProjectionLogic();
                String historyPeriods = String.valueOf(historyDdlb.getValue());
                hierarchyNo = " WHERE RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ";
                levelValues = "'" + levelValues + "'";

                Object[] inputParameters = new Object[10];
                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = levelValues;
                inputParameters[2] = tableLogic.getViewType();
                inputParameters[3] = hierarchyNo;
                inputParameters[8] = "getProjectionDetId";
                inputParameters[4] = session.getUserId();
                inputParameters[5] = session.getSessionId();
                SalesProjectionLogic salesLogic = new SalesProjectionLogic();
                projectionDetailsIdForPMPY = salesLogic.getProjectionDetailsSid(inputParameters);
                projectionDetailsId = Integer.valueOf(projectionDetailsIdForPMPY.get(0).toString());
                List list = logic.getTradingPartnerInfo(projectionDetailsId);
                String tradeName = String.valueOf(list.get(0) != null ? list.get(0) : " ");
                String tradeNo = String.valueOf(list.get(1) != null ? list.get(1) : " ");
                String contractHolder = String.valueOf(list.get(2) != null ? list.get(2) : " ");

                final PMPYCalculator pmpyCalc = new PMPYCalculator(historyPeriods, projectionDetailsIdForPMPY, generate, rightHeader, tradeName, tradeNo, contractHolder, session, doubleProjectedColumns);
                pmpyCalc.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {

                        if (pmpyCalc.isImportEvent()) {
                            refreshTableData(getCheckedRecordsHierarchyNo());

                        }

                    }
                });
                getUI().addWindow(pmpyCalc);
            } else {
                AbstractNotificationUtils.getErrorNotification("PMPY calculation cannot be performed",
                        "PMPY calculation cannot be performed for trading partner which already have history values.");
            }
        } else {
            if (tpSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Trading Partner Selected",
                        "There are More than one trading partners selected.\n Please select only one trading partner and try again");

            } else {

                AbstractNotificationUtils.getErrorNotification("No Trading Partner Selected.", "Please select a Trading Partner. ");

            }
        }

    }

    @UiHandler("graphIcon")
    public void totalLiveGraph(Button.ClickEvent event) {
        TotalLivesChart totalLivesChart = new TotalLivesChart(session);
        getUI().addWindow(totalLivesChart);

    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {

            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.info("Entering resetBtn method");
                historyDdlb.setValue(1);
                actualsProjections.select(Constant.ACTUALS);
                proPeriodOrd.select(Constant.ASCENDING);
                variables.removeAllItems();
                variables.addItem(Constant.SALES_SMALL);
                variables.addItem(Constant.UNITS_SMALL);
                variables.addItem(Constant.PRODUCT_GROWTH);
                variables.addItem(Constant.ACCOUNT_GROWTH);
                variables.select(Constant.SALES_SMALL);
                LOGGER.info("End of resetBtn method");
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populate")
    public void populateBtnLogic(Button.ClickEvent event) {

        SalesProjectionLogic salesLogic = new SalesProjectionLogic();

        try {

            if (fieldDdlb.getValue() != null) {

                if (!Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue()))) {

                    if ((valueTxt.getValue() == null || StringUtils.isEmpty(valueTxt.getValue()))) {

                        AbstractNotificationUtils.getErrorNotification("No Value Entered", "Please enter any value to update");
                    } else if (startPeriod.getValue() == null) {

                        AbstractNotificationUtils.getErrorNotification("No Start Period Selected", "A Start Period is required for an Account Growth or Product Growth.\n  Please select a start period and try again.");
                    } else {

                        int startQuater = Integer.valueOf(startPeriod.getValue().toString().charAt(1) - 48);

                        int startYear = Integer.valueOf(startPeriod.getValue().toString().substring(3, 7));

                        int endQuater = 0;
                        int endYear = 0;
                        if (endPeriod.getValue() == null) {
                            endQuater = Integer.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - 48);
                            endYear = Integer.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).substring(3, 7));
                        } else {
                            endQuater = Integer.valueOf(endPeriod.getValue().toString().charAt(1) - 48);

                            endYear = Integer.valueOf(endPeriod.getValue().toString().substring(3, 7));

                        }
                        if (Constant.ACCOUNT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {

                            Double value = Double.valueOf(valueTxt.getValue().toString());

                            String levelValues = StringUtils.EMPTY;
                            String hierarchyNo = "WHERE";
                            for (SalesRowDto dto : customContainer.getItemIds()) {
                                if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                                    if (!levelValues.equals(StringUtils.EMPTY)) {
                                        levelValues = levelValues + ",";
                                    }
                                    if (!hierarchyNo.equals("WHERE")) {
                                        hierarchyNo = hierarchyNo + " OR ";
                                    }
                                    hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                                    levelValues = levelValues + "'" + dto.getLevelName() + "'";

                                }

                            }
                            Object[] inputParam = new Object[10];

                            inputParam[4] = session.getUserId();
                            inputParam[5] = session.getSessionId();
                            inputParam[8] = "getCheckedRecords";
                            List<Integer> proDetailsID = new ArrayList<Integer>();
                            proDetailsID = salesLogic.getCheckedRecords(inputParam);

                            if (proDetailsID.size() <= 1) {

                                AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for Update ");
                                return;
                            }
                            saveEditedRecs();

                            Object[] inputParameters = new Object[10];
                            inputParameters[0] = session.getProjectionId();
                            inputParameters[1] = levelValues;
                            inputParameters[2] = tableLogic.getViewType();
                            inputParameters[3] = hierarchyNo;
                            inputParameters[8] = "saveCheckRecords";
                            inputParameters[4] = session.getUserId();
                            inputParameters[5] = session.getSessionId();
                            inputParameters[6] = checkAll;

                            inputParameters[7] = "uncheck";

                            inputParameters[7] = Constant.CHECK;
                            Object[] input = new Object[11];
                            input[0] = session.getProjectionId();
                            input[1] = startYear;
                            input[2] = endYear;
                            input[3] = startQuater;
                            input[4] = endQuater;
                            input[5] = value;
                            input[6] = "accGrowth";
                            input[8] = "massUpdate";
                            input[9] = session.getUserId();
                            input[10] = session.getSessionId();
                            salesLogic.massUpdateSalesProjection(input);

                            refreshTableData(getCheckedRecordsHierarchyNo());

                        } else if (Constant.PRODUCT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {

                            Double value = Double.valueOf(valueTxt.getValue().toString());

                            String levelValues = StringUtils.EMPTY;
                            String hierarchyNo = "WHERE";
                            for (SalesRowDto dto : customContainer.getItemIds()) {
                                if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                                    if (!levelValues.equals(StringUtils.EMPTY)) {
                                        levelValues = levelValues + ",";
                                    }
                                    if (!hierarchyNo.equals("WHERE")) {
                                        hierarchyNo = hierarchyNo + " OR ";
                                    }
                                    hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                                    levelValues = levelValues + "'" + dto.getLevelName() + "'";

                                }

                            }

                            Object[] inputParam = new Object[10];

                            inputParam[4] = session.getUserId();
                            inputParam[5] = session.getSessionId();
                            inputParam[8] = "getCheckedRecords";
                            List<Integer> proDetailsID = new ArrayList<Integer>();
                            proDetailsID = salesLogic.getCheckedRecords(inputParam);

                            if (proDetailsID.size() <= 1) {

                                AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for Update ");
                                return;
                            }
                            saveEditedRecs();
                            Object[] inputParameters = new Object[12];
                            inputParameters[0] = session.getProjectionId();
                            inputParameters[1] = levelValues;
                            inputParameters[2] = tableLogic.getViewType();
                            inputParameters[3] = hierarchyNo;
                            inputParameters[8] = "saveCheckRecords";
                            inputParameters[4] = session.getUserId();
                            inputParameters[5] = session.getSessionId();
                            inputParameters[6] = checkAll;
                            inputParameters[7] = "uncheck";

                            inputParameters[7] = Constant.CHECK;

                            Object[] input = new Object[11];
                            input[0] = session.getProjectionId();
                            input[1] = startYear;
                            input[2] = endYear;
                            input[3] = startQuater;
                            input[4] = endQuater;
                            input[5] = value;
                            input[6] = "proGrowth";
                            input[8] = "massUpdate";
                            input[9] = session.getUserId();
                            input[10] = session.getSessionId();
                            salesLogic.massUpdateSalesProjection(input);

                            refreshTableData(getCheckedRecordsHierarchyNo());

                        }

                    }
                } else {

                    String value = String.valueOf(valueDdlb.getValue());
                    if ((valueDdlb.getValue() == null || value.equals(Constant.NULL))) {

                        AbstractNotificationUtils.getErrorNotification("No Value Entered", "Please enter any value to update");
                        return;
                    }
                    String levelValues = StringUtils.EMPTY;
                    String hierarchyNo = "WHERE";

                    boolean tpSelected = false;
                    int i = 0;
                    int j = 0;
                    for (SalesRowDto dto : customContainer.getItemIds()) {
                        if ((Boolean) dto.getPropertyValue(Constant.CHECK) && ((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                            tpSelected = true;
                            ++i;
                            if (!levelValues.equals(StringUtils.EMPTY)) {
                                levelValues = levelValues + ",";
                            }
                            if (!hierarchyNo.equals("WHERE")) {
                                hierarchyNo = hierarchyNo + " OR ";
                            }
                            hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                            levelValues = levelValues + "'" + dto.getLevelName() + "'";

                        }
                    }

                    if (i > 0) {

                        saveEditedRecs();
                        Object[] inputParameters = new Object[12];
                        inputParameters[0] = session.getProjectionId();
                        inputParameters[1] = levelValues;
                        inputParameters[2] = tableLogic.getViewType();
                        inputParameters[3] = hierarchyNo;
                        inputParameters[8] = "saveCheckRecords";
                        inputParameters[4] = session.getUserId();
                        inputParameters[5] = session.getSessionId();
                        inputParameters[6] = checkAll;
                        inputParameters[7] = "uncheck";

                        inputParameters[7] = Constant.CHECK;

                        Object[] input = new Object[11];
                        input[0] = session.getProjectionId();
                        input[1] = levelValues;
                        input[2] = tableLogic.getViewType();
                        input[3] = hierarchyNo;
                        input[5] = value;
                        input[6] = Constant.GROUP;
                        input[8] = "saveGroup";
                        input[9] = session.getUserId();
                        input[10] = session.getSessionId();
                        salesLogic.massUpdateSalesProjection(input);
                        refreshTableData(getCheckedRecordsHierarchyNo());

                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a Trading Partner level in the hierarchy for Update ");
                        return;
                    }

                }
            } else {

                AbstractNotificationUtils.getErrorNotification("No Field Selected", "Please select any Field to update");
                return;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getCause());

        }

    }

    /**
     * Adjustment Logic
     *
     * @param event
     */
    @UiHandler("adjust")
    public void adjustmentLogic(Button.ClickEvent event) {
        String adjustmentValue = adjustment.getValue();
        adjustmentValue = adjustmentValue.replace(",", StringUtils.EMPTY);

        if (!adjustmentValue.matches("[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)")) {
            AbstractNotificationUtils.getErrorNotification("Field Error", "Only Numbers are allowed");
            adjustment.setValue(StringUtils.EMPTY);
            return;
        }

        if (allocMethodology.getValue() != null) {
            final Object salesSelections[] = new Object[10];

            final Object adjustSelections[] = new Object[13];
            final Object inputs[] = new Object[10];
            adjustSelections[0] = projectionId;
            adjustSelections[12] = tableLogic.isIsCustomHierarchy();
            adjustSelections[1] = String.valueOf(type.getValue());

            adjustSelections[2] = adjustmentValue;

            adjustSelections[3] = String.valueOf(basis.getValue());

            if (String.valueOf(variable.getValue()).equals(Constant.SALES_SMALL)) {
                adjustSelections[4] = Constant.STRING_ONE;
            } else {
                adjustSelections[4] = DASH;

            }

            adjustSelections[5] = String.valueOf(allocMethodology.getValue());

            adjustSelections[8] = "saveBfrAllocation";

            if (adjustment.getValue() != null && !StringUtils.isEmpty(adjustment.getValue())) {

                boolean check = false;
                for (Object key : checkBoxMap.keySet()) {

                    if (checkBoxMap.get(key)) {
                        check = true;
                        break;
                    }
                }

                String adjustPeriod = (String) adjustPeriods.getValue();
                String adjMethodology = (String) allocMethodology.getValue();
                if (adjustPeriod.equals(Constant.All)) {
                    if (adjMethodology.equals(Constant.HISPEROFBUSINESS)) {

                        inputs[0] = getSelectedHistoryPeriods(false);

                        if (String.valueOf(inputs[0]).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Historic Period");
                            return;
                        }

                        inputs[1] = getAllProjectionPeriods();

                    } else {

                        inputs[0] = getAllProjectionPeriods();

                        inputs[1] = getAllProjectionPeriods();

                    }

                } else {
                    if (adjMethodology.equals(Constant.HISPEROFBUSINESS)) {
                        inputs[0] = getSelectedHistoryPeriods(false);
                        if (String.valueOf(inputs[0]).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Historic Period");
                            return;
                        }
                        inputs[1] = getSelectedProjectionPeriods();
                        if (String.valueOf(inputs[1]).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Projection Period.");
                            return;
                        }

                    } else {
                        inputs[0] = getSelectedProjectionPeriods();
                        if (String.valueOf(inputs[0]).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select which periods need to be included in the adjustment.");
                            return;
                        }
                        inputs[1] = getSelectedProjectionPeriods();
                    }
                }

                inputs[2] = session.getProjectionId();
                inputs[3] = session.getUserId();
                inputs[4] = session.getSessionId();
                final SalesProjectionLogic salesLogic = new SalesProjectionLogic();

                String levelValues = StringUtils.EMPTY;
                String hierarchyNo = "WHERE";
                for (SalesRowDto dto : customContainer.getItemIds()) {
                    if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                        if (!levelValues.equals(StringUtils.EMPTY)) {
                            levelValues = levelValues + ",";
                        }
                        if (!hierarchyNo.equals("WHERE")) {
                            hierarchyNo = hierarchyNo + " OR ";
                        }
                        hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                        levelValues = levelValues + "'" + dto.getLevelName() + "'";

                    }

                }
                Object[] inputParam = new Object[10];

                inputParam[4] = session.getUserId();
                inputParam[5] = session.getSessionId();
                inputParam[8] = "getCheckedRecords";
                List<Integer> proDetailsID = new ArrayList<Integer>();
                proDetailsID = salesLogic.getCheckedRecords(inputParam);

                if (proDetailsID.size() <= 1) {

                    AbstractNotificationUtils.getErrorNotification("No level selected", "Please select a level this adjustment applies to");
                    return;
                }
                final Object[] inputParameters = new Object[10];
                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = levelValues;
                inputParameters[2] = tableLogic.getViewType();
                inputParameters[3] = hierarchyNo;
                inputParameters[8] = "saveCheckRecords";
                inputParameters[4] = session.getUserId();
                inputParameters[5] = session.getSessionId();
                inputParameters[6] = checkAll;
                inputParameters[7] = Constant.CHECK;

                adjustSelections[6] = levelValues;

                adjustSelections[7] = hierarchyNo;

                adjustSelections[9] = tableLogic.getViewType();

                adjustSelections[10] = session.getUserId();

                adjustSelections[11] = session.getSessionId();

                String confirmMessage = "Confirm Incremental adjustment";
                String messageBody = StringUtils.EMPTY;
                String adjustType = StringUtils.EMPTY;
                String adjustValue = " ";
                if (String.valueOf(basis.getValue()).equals(Constant.LabelConstants.AMOUNT) && String.valueOf(variable.getValue()).equals(Constant.SALES_SMALL)) {

                    adjustValue = String.valueOf(adjustment.getValue()) + "$";
                } else if (String.valueOf(basis.getValue()).equals(Constant.LabelConstants.AMOUNT) && String.valueOf(variable.getValue()).equals(Constant.UNIT)) {

                    adjustValue = String.valueOf(adjustment.getValue());

                } else if (String.valueOf(basis.getValue()).equals(Constant.LabelConstants.PERCENTAGE)) {

                    adjustValue = String.valueOf(adjustment.getValue()) + Constant.PERCENT;

                }
                String salesorUnits = Constant.SALES_SMALL;

                adjustType = String.valueOf(type.getValue());

                if (String.valueOf(variable.getValue()).equals(Constant.SALES_SMALL)) {
                    salesorUnits = Constant.SALES_SMALL;
                } else {
                    salesorUnits = Constant.UNITS_SMALL;

                }
                confirmMessage = "Confirm  adjustment";

                messageBody = " You are about to adjust the " + salesorUnits + " in the projection with a new  " + adjustValue + " value. Are you sure you want to continue?";

                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                    }

                    @Override
                    public void yesMethod() {
                        saveEditedRecs();
                        inputParameters[7] = "uncheck";

                        inputParameters[7] = Constant.CHECK;

                        if (tableLogic.isIsCustomHierarchy()) {
                            customAdjustment(adjustSelections, inputs);
                        } else {
                            salesLogic.saveAdjustmentSelections(adjustSelections);
                            try {
                                salesLogic.callAdjustmentProcedure(inputs);
                            } catch (SystemException ex) {
                                java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        refreshTableData(getCheckedRecordsHierarchyNo());

                    }
                }.getOkCancelMessage(confirmMessage, messageBody);

            } else {

                AbstractNotificationUtils.getErrorNotification("No Adjustment Value", "Please enter an Adjustment Value");
            }

        } else {
            AbstractNotificationUtils.getErrorNotification("No Allocation Methodology selected", "Please select an Allocation Methodology");
        }
    }

    /**
     * Calculate Logic
     *
     * @param event
     */
    @UiHandler("calculate")
    public void calculateLogic(Button.ClickEvent event) {

        final String historyS = String.valueOf(historyDdlb.getValue());
        final String variableS = String.valueOf(variables.getValue());
        final String actualOrProjectionS = String.valueOf(actualsProjections.getValue());
        final String tempPeriodOrderS = String.valueOf(proPeriodOrd.getValue()).trim();

        if (actualOrProjectionS.equalsIgnoreCase(actualsOrProjections)
                && historyS.equalsIgnoreCase(history)
                && tempPeriodOrderS.equalsIgnoreCase(projectionPeriodorder)
                && variableS.equalsIgnoreCase(variablesSelection)) {

            if (methodology.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology.");
                return;
            }

            String levelValues = StringUtils.EMPTY;
            String hierarchyNo = "WHERE";
            for (SalesRowDto dto : customContainer.getItemIds()) {
                if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                    if (!levelValues.equals(StringUtils.EMPTY)) {
                        levelValues = levelValues + ",";
                    }
                    if (!hierarchyNo.equals("WHERE")) {
                        hierarchyNo = hierarchyNo + " OR ";
                    }
                    hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                    levelValues = levelValues + "'" + dto.getLevelName() + "'";

                }

            }
            SalesProjectionLogic salesLogic = new SalesProjectionLogic();
            Object[] inputParameters = new Object[10];
            inputParameters[0] = projectionId;

            inputParameters[1] = levelValues;
            inputParameters[2] = tableLogic.getViewType();
            inputParameters[3] = hierarchyNo;
            inputParameters[4] = session.getUserId();
            inputParameters[5] = session.getSessionId();
            inputParameters[8] = "saveCheckRecords";
            inputParameters[6] = checkAll;
            Object[] inputParam = new Object[10];

            inputParam[4] = session.getUserId();
            inputParam[5] = session.getSessionId();
            inputParam[8] = "getCheckedRecords";
            List<Integer> proDetailsID = new ArrayList<Integer>();
            proDetailsID = salesLogic.getCheckedRecords(inputParam);

            if (proDetailsID.size() <= 1) {

                AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for the methodology.  ");
                return;
            }

            Object selections[] = new Object[12];
            selections[0] = projectionId;
            selections[1] = methodology.getValue();

            selections[2] = getSelectedHistoryPeriods(true);
            if (getCalculationBased() == 0) {

                selections[3] = Constant.SALES;

            } else if (getCalculationBased() == 1) {

                selections[3] = Constant.UNITS;

            } else {

                AbstractNotificationUtils.getErrorNotification("Mandatory Error", "Please select either sales or units for all periods,\n cannot combine both sales and units");
                return;

            }

            selections[4] = tableLogic.getViewType();
            selections[5] = levelValues;
            selections[6] = hierarchyNo;
            selections[9] = session.getUserId();
            selections[10] = session.getSessionId();
            selections[8] = "saveBfrCalculation";
            selections[7] = tableLogic.isIsCustomHierarchy();

            if (String.valueOf(selections[2]).equals(StringUtils.EMPTY)) {
                AbstractNotificationUtils.getErrorNotification("No period selected", "Please select a Historic Period. ");
                return;
            }

            if (String.valueOf(methodology.getValue()).equals(Constant.SINGLE_PERIOD)) {
                if (String.valueOf(selections[2]).contains(",")) {

                    AbstractNotificationUtils.getErrorNotification("Single period ", "Please select a single period for calculation. ");
                    return;
                }
            }

            saveEditedRecs();

            inputParameters[7] = "uncheck";

            inputParameters[7] = Constant.CHECK;

            if (!Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) && !Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
                Collection<?> itemsList = resultsTable.getRightFreezeAsTable().getItemIds();
                List<SalesRowDto> salesDtoList = new ArrayList<SalesRowDto>();
                List<SalesRowDto> finalList = new ArrayList<SalesRowDto>();
                if (itemsList != null && !itemsList.isEmpty()) {
                    List<String> hiearchyNoList = new ArrayList<String>();
                    for (Object item : itemsList) {
                        SalesRowDto dto = (SalesRowDto) item;
                        salesDtoList.add(dto);
                        hiearchyNoList.add(dto.getHierarchyNo());
                    }
                    inputParameters = new Object[18];
                    SalesProjectionLogic logic = new SalesProjectionLogic();
                    for (SalesRowDto sDto : salesDtoList) {
                        if ((Boolean) sDto.getPropertyValue(Constant.CHECK)) {
                            inputParameters[0] = session.getProjectionId();
                            inputParameters[1] = sDto.getTreeLevelNo() - 1;
                            inputParameters[2] = tableLogic.getViewType();
                            inputParameters[3] = StringUtils.EMPTY + sDto.getHierarchyNo() + StringUtils.EMPTY;
                            inputParameters[8] = "saveBfrCalculation";
                            inputParameters[4] = session.getUserId();
                            inputParameters[5] = session.getSessionId();
                            inputParameters[6] = checkAll;
                            inputParameters[7] = tableLogic.isIsCustomHierarchy();
                            inputParameters[9] = tableLogic.getCustomId();
                            inputParameters[10] = sDto.getParentHierarchyType();
                            inputParameters[11] = sDto.getLastCustomerHierarchyno();
                            inputParameters[12] = sDto.getLastProductHierarchyno();
                            inputParameters[13] = sDto.getHierarchyType();
                            inputParameters[14] = selections[1];
                            inputParameters[15] = selections[2];
                            inputParameters[16] = selections[3];
                            salesLogic.saveCalculationSelections(inputParameters);
                        }
                    }
                }

            } else {
                salesLogic.saveCalculationSelections(selections);
            }

            try {
                salesLogic.callCalculationProcedure(selections);
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean reloadTable = false;
            if (String.valueOf(baseLineFilter.getValue()).equals(Constant.SHOW_ALL)) {
                loadBaseLine();
            } else {
                if (!String.valueOf(baseLineFilter.getValue()).equals(getSelectedHistoryPeriods(true))) {

                    loadBaseLine();
                    reloadTable = true;
                }
            }

            if (String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL)) {
                loadMethodology();
            } else {
                if (!String.valueOf(metohdologyFilter.getValue()).equals(String.valueOf(methodology.getValue()))) {
                    loadMethodology();
                    reloadTable = true;
                }
            }
            if (reloadTable) {
                reLoadTable();
            } else {
                refreshTableData(getCheckedRecordsHierarchyNo());
            }

            final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.show(Page.getCurrent());

        } else {
            AbstractNotificationUtils.getErrorNotification("Confirmation",
                    "A change in the Sales Projection Selection has been made.\n Please click Generate to proceed");

        }

    }

    /**
     * Generate Button Logic
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtnLogic(Button.ClickEvent event) {

        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();

            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

            final String[] col = tempVariables.split(",");

            String actualsOrProjecton = actualsProjections.getValue().toString();

            boolean sales = false;
            boolean units = false;
            boolean accGrowth = false;
            boolean proGrowth = false;

            for (String value : col) {
                value = value.trim();

                if (value.equals(Constant.SALES_SMALL)) {
                    sales = true;

                }
                if (value.equals(Constant.UNITS_SMALL)) {

                    units = true;

                }
                if (value.equals(Constant.PRODUCT_GROWTH)) {

                    accGrowth = true;

                }
                if (value.equals(Constant.ACCOUNT_GROWTH)) {

                    proGrowth = true;
                }

            }
            int pageNo = tableLogic.getItemsPerPage();
            if (actualsOrProjecton.equalsIgnoreCase(Constant.ACTUALS)) {

                if (!sales && !units) {

                    AbstractNotificationUtils.getErrorNotification("Improper Variable Selection", "Please select a Variable as Sales or Units");

                } else {

                    reLoadTable();
                    loadOnGenerate();

                }

            } else if (actualsOrProjecton.equalsIgnoreCase(Constant.PROJECTIONS) || actualsOrProjecton.equalsIgnoreCase(Constant.BOTH)) {

                if (!sales && !units && !accGrowth && !proGrowth) {

                    AbstractNotificationUtils.getErrorNotification("Improper Variable Selection", "Please select any one Variable");

                } else {
                    reLoadTable();
                    loadOnGenerate();

                }

            }
            tableLogic.setItemsPerPage(pageNo);
        } else {

            AbstractNotificationUtils.getErrorNotification("No Variable Selected", "Please select any one Variable");

        }
    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    /**
     * Mass update.
     *
     * @param event the event
     */
    @UiHandler("massUpdate")
    public void massUpdate(Property.ValueChangeEvent event) {
        LOGGER.info("massupdate click listener starts");
        if ((Constant.LabelConstants.DISABLE).equals(massUpdate.getValue())) {

            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);

        } else {
            fieldDdlb.setEnabled(true);
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);
        }
        LOGGER.info("massupdate click listener ends");
    }

    public void configureTable() {
        valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                valueChange = true;
            }
        };

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public String getSelectedProjectionPeriods() {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        String selectedPeriods = StringUtils.EMPTY;

        Date currentDate = session.getForecastDTO().getProjectionStartDate();

        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        year = year + 1900;
        int currentYQ = year + quator;
        for (Object key : checkBoxMap.keySet()) {

            String temp[] = ((String) key).split("-");
            int num = Integer.parseInt(temp[1]);
            int tempQuator = Integer.parseInt(((temp[0].replace('q', ' ')).trim()));
            int tempYQ = num + tempQuator;

            if (currentYQ <= tempYQ) {
                if (checkBoxMap.get(key)) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = value.replace('q', 'Q');
                    value = value.replace('-', ' ');
                    selectedPeriods = selectedPeriods + value;

                }
            }
        }
        return selectedPeriods;
    }

    public String getSelectedHistoryPeriods(boolean calculate) {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        String selectedPeriods = StringUtils.EMPTY;

        Date currentDate = new Date();

        if (calculate) {
            currentDate = session.getForecastDTO().getForecastStartDate();
        } else {
            if (session.getForecastDTO().getProjectionStartDate().before(session.getForecastDTO().getForecastStartDate())) {

                currentDate = session.getForecastDTO().getProjectionStartDate();

            } else {

                currentDate = session.getForecastDTO().getForecastStartDate();

            }
        }
        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        year = year + 1900;

        for (Object key : checkBoxMap.keySet()) {

            String temp[] = ((String) key).split("-");
            int num = Integer.parseInt(temp[1]);
            int tempQuator = Integer.parseInt(((temp[0].replace('q', ' ')).trim()));
            if (num < year) {

                if (checkBoxMap.get(key)) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                        selectedPeriods = selectedPeriods + ",";
                    }

                    String value = (String) key;
                    value = value.replace('q', 'Q');
                    value = value.replace('-', ' ');

                    selectedPeriods = selectedPeriods + value;

                }

            } else if (num <= year && tempQuator < quator) {

                if (checkBoxMap.get(key)) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                        selectedPeriods = selectedPeriods + ",";
                    }

                    String value = (String) key;
                    value = value.replace('q', 'Q');
                    value = value.replace('-', ' ');

                    selectedPeriods = selectedPeriods + value;

                }

            }

        }

        return selectedPeriods;
    }

    private String getAllHistoryPeriods(boolean calculate) {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = new Date();

        if (calculate) {
            currentDate = session.getForecastDTO().getForecastStartDate();
        } else {
            if (session.getForecastDTO().getProjectionStartDate().before(session.getForecastDTO().getForecastStartDate())) {

                currentDate = session.getForecastDTO().getProjectionStartDate();

            } else {

                currentDate = session.getForecastDTO().getForecastStartDate();

            }

        }

        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        year = year + 1900;

        for (Object key : checkBoxMap.keySet()) {

            String temp[] = ((String) key).split("-");
            int num = Integer.parseInt(temp[1]);
            int tempQuator = Integer.parseInt(((temp[0].replace('q', ' ')).trim()));
            if (num < year) {
                if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = value.replace('q', 'Q');
                value = value.replace('-', ' ');

                selectedPeriods = selectedPeriods + value;

            } else if (num <= year && tempQuator < quator) {

                if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = value.replace('q', 'Q');
                value = value.replace('-', ' ');

                selectedPeriods = selectedPeriods + value;

            }
        }

        return selectedPeriods;
    }

    public String getAllProjectionPeriods() {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = session.getForecastDTO().getProjectionStartDate();

        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        year = year + 1900;

        int currentYQ = Integer.parseInt(year + StringUtils.EMPTY + quator);

        for (Object key : rightHeader.getDoubleColumns()) {
            if (!String.valueOf(key).equals(Constant.GROUP)) {

                String temp[] = ((String) key).split("-");
                int num = Integer.parseInt(temp[1]);
                int tempQuator = Integer.parseInt(((temp[0].replace('q', ' ')).trim()));
                int tempYQ = Integer.parseInt(num + StringUtils.EMPTY + tempQuator);

                if (currentYQ <= tempYQ) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {

                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = value.replace('q', 'Q');
                    value = value.replace('-', ' ');

                    selectedPeriods = selectedPeriods + value;

                }

            }
        }
        return selectedPeriods;
    }

    public int getCalculationBased() {

        boolean tempSalesvalue = false;
        boolean tempUnitValue = false;

        for (Object key : radioMap.keySet()) {
            String value = radioMap.get(key);
            if (checkBoxMap.get(key)) {

                if (value.contains(Constant.SALES_SMALL)) {
                    tempSalesvalue = true;
                }

                if (value.contains(Constant.UNIT)) {
                    tempUnitValue = true;
                }
            }
        }

        if (tempSalesvalue && tempUnitValue) {
            return -1;

        } else if (tempSalesvalue && !tempUnitValue) {

            return 0;
        } else if (!tempSalesvalue && tempUnitValue) {

            return 1;
        }

        return 0;
    }

    public void reLoadTable() {
        editedValues.clear();
        incOrDecPer.clear();
        editedGroupValues.clear();
        rowCountMap.clear();
        editedItems.clear();
        customContainer.removeAllItems();
        tableLogic.clearAll();
        tableLogic.sinkItemPerPageWithPageLength(false);
        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        final String actualsOrProjections = String.valueOf(actualsProjections.getValue());
        final String projectionPeriodorder = String.valueOf(proPeriodOrd.getValue());

        Map<String, String> selection = new HashMap<String, String>();
        selection.put(Constant.FREQUENCY, QUARTERLY.getConstant());
        selection.put(Constant.HISTORY, history);
        selection.put("projectFrequency", String.valueOf(getProjections(session.getForecastDTO().getForecastStartDate(), session.getForecastDTO().getForecastEndDate())));
        selection.put(Constant.ORDER, projectionPeriodorder);
        selection.put("projection", actualsOrProjections);
        selection.put(Constant.SALES, "false");
        selection.put(Constant.UNITS, "false");
        selection.put(Constant.P_Growth, "false");
        selection.put(Constant.A_Growth, "false");
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();

            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

            final String[] col = tempVariables.split(",");

            for (String value : col) {
                value = value.trim();

                if (value.equals(Constant.SALES_SMALL)) {

                    selection.put(Constant.SALES, Constant.TRUE);

                }
                if (value.equals(Constant.UNITS_SMALL)) {

                    selection.put(Constant.UNITS, Constant.TRUE);

                }
                if (value.equals(Constant.PRODUCT_GROWTH)) {

                    selection.put(Constant.P_Growth, Constant.TRUE);

                }
                if (value.equals(Constant.ACCOUNT_GROWTH)) {

                    selection.put(Constant.A_Growth, Constant.TRUE);

                }
            }
        }
        if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
            currentHierarchy = CommonLogic.getCustomerHierarchy(projectionId, hierarchyLevelNo);

            tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));

        } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = false;
            hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
            currentHierarchy = CommonLogic.getProductHierarchy(projectionId, hierarchyLevelNo);
            tableLogic.setViewType(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            tableLogic.setLevelNo(Integer.parseInt(session.getProductLevelNumber()));
        } else if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
            isCustomHierarchy = true;
        }

        tableLogic.setCurrentHierarchy(currentHierarchy);
        tableLogic.setIsCustomHierarchy(isCustomHierarchy);

        SalesProjectionLogic logic = new SalesProjectionLogic();
        leftHeader = HeaderUtils.getSalesProjectionLeftTableColumns(selection);
        List headerList = HeaderUtils.getSalesProjectionRightTableColumns(selection, session);
        rightHeader = (CustomTableHeaderDTO) headerList.get(0);

        excelHeader = (CustomTableHeaderDTO) headerList.get(1);
        doubleProjectedColumns = (List<String>) headerList.get(2);
        doubleHeaderHistoryMap = (Map<Object, Object[]>) headerList.get(3);

        for (Object obj : rightHeader.getDoubleHistoryColumns()) {
            Object single[] = doubleHeaderHistoryMap.get(obj);

            for (Object ob : single) {
                rightTable.setColumnRadioButton(ob, (String) obj);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }

        List<Object> visibleColumn = rightHeader.getSingleColumns();
        List<String> columnHeader = rightHeader.getSingleHeaders();
        List<Object> doubleVisibleColumn = rightHeader.getDoubleColumns();
        List<String> doubleColumnHeader = rightHeader.getDoubleHeaders();
        List<Object> tripleVisibleColumn = rightHeader.getTripleColumns();
        List<String> tripleColumnHeader = rightHeader.getTripleHeader();

        if (proPeriodOrd.getValue().toString().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(visibleColumn);
            Collections.reverse(columnHeader);
            Collections.reverse(doubleVisibleColumn);
            Collections.reverse(doubleColumnHeader);
            Collections.reverse(tripleVisibleColumn);
            Collections.reverse(tripleColumnHeader);
        }
        tableLogic.setRightDto(rightHeader);

        customContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);

        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        tableLogic.setContainerDataSource(customContainer);

        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(false);

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));

        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        alignRight();
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, 145, TAB_SALES_PROJECTION.getConstant());
        if (customContainer.getItemIds().size() > 0) {

            salesSubmitFlag = true;
        }
        leftTable.setColumnCheckBox(Constant.CHECK, true, false);
        checkAll = false;
        Object[] inputParameters = new Object[10];
        inputParameters[0] = session.getProjectionId();
        inputParameters[1] = "empty-empty-empty";
        inputParameters[2] = tableLogic.getViewType();
        inputParameters[3] = StringUtils.EMPTY;
        inputParameters[8] = "saveCheckUncheckRecords";
        inputParameters[4] = session.getUserId();
        inputParameters[5] = session.getSessionId();
        inputParameters[6] = true;
        inputParameters[7] = "uncheck";
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        salesLogic.savecheckedRecords(inputParameters);

        List<Object> propertyIdList = Arrays.asList(resultsTable.getRightFreezeAsTable().getVisibleColumns());
        resultsTable.getRightFreezeAsTable().setRefresh(Boolean.FALSE);
        for (Object propertyId : propertyIdList) {
            formatData(propertyId);
        }
        leftTable.setColumnWidth(Constant.CHECK, 120);
        leftTable.setColumnWidth(Constant.LEVELNAME, 150);
        leftTable.setColumnWidth(Constant.GROUP, 120);

        resultsTable.getRightFreezeAsTable().setRefresh(Boolean.TRUE);
    }

    public void loadOnGenerate() {

        history = String.valueOf(historyDdlb.getValue());
        variablesSelection = String.valueOf(variables.getValue());
        actualsOrProjections = String.valueOf(actualsProjections.getValue());
        projectionPeriodorder = String.valueOf(proPeriodOrd.getValue()).trim();

        radioMap.clear();
        checkBoxMap.clear();
        tableLogic.setUserGroup(STRING_EMPTY);
        tableLogic.setBaseLine(STRING_EMPTY);
        tableLogic.setMethodology(STRING_EMPTY);
        initializeResultTable();
        SalesProjectionLogic logic = new SalesProjectionLogic();
        List objList = rightHeader.getDoubleColumns();
        for (Object obj : objList) {
            if (!String.valueOf(obj).contains(Constant.GROUP)) {
                rightTable.setDoubleHeaderColumnCheckBox(obj, true);
                checkBoxMap.put(obj, false);
            }
        }
        int i = 1;
        int j = 0;
        Object tempObj = null;
        int selectedvar = 0;
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();

            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

            final String[] col = tempVariables.split(",");
            selectedvar = col.length;
        }

        Date currentDate = session.getForecastDTO().getForecastStartDate();

        int quator = logic.getQuator(currentDate.getMonth() + 1);
        int year = currentDate.getYear();

        year = year + 1900;

        String checkString = quator + "-" + year;

        for (Object obj : rightHeader.getDoubleHistoryColumns()) {
            Object single[] = doubleHeaderHistoryMap.get(obj);
            for (Object ob : single) {
                rightTable.setColumnRadioButton(ob, (String) obj);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }

        List objList1 = rightHeader.getDoubleHeaders();
        i = 1;
        j = 0;

        if (customContainer.getItemIds().size() > 0) {

            salesSubmitFlag = true;
        }
        loadPeriods();
        loadLevelFilterValue();
        loadLevelFilter();
        loadUserGroup();
        loadMethodology();
        loadBaseLine();
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("view")
    public void viewChangeOption(Property.ValueChangeEvent event) {
        LOGGER.info("viewChangeOption ValueChangeEvent initiated ");
        if (restrictOnViewChange) {
            if (view.getValue() != null) {
                tableLogic.setLevelFilterValue(STRING_EMPTY);
                if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {

                    leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                    checkAll = false;
                    Object[] inputParameters = new Object[10];
                    inputParameters[0] = session.getProjectionId();
                    inputParameters[1] = "empty-empty-empty";
                    inputParameters[2] = tableLogic.getViewType();
                    inputParameters[3] = StringUtils.EMPTY;
                    inputParameters[8] = "saveCheckUncheckRecords";
                    inputParameters[4] = session.getUserId();
                    inputParameters[5] = session.getSessionId();
                    inputParameters[6] = true;
                    inputParameters[7] = "uncheck";
                    SalesProjectionLogic salesLogic = new SalesProjectionLogic();
                    salesLogic.savecheckedRecords(inputParameters);

                    valueDdlb.setVisible(false);
                    valueTxt.setVisible(true);

                    if (fieldDdlb.containsId(Constant.GROUPFCAPS)) {
                        fieldDdlb.removeItem(Constant.GROUPFCAPS);
                    }
                    group.setEnabled(true);

                    currentHierarchy.clear();
                    tableLogic.setLevelNo(1);
                    tableLogic.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
                    tableLogic.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
                    isCustomHierarchy = true;
                    tableLogic.clearAll();
                    loadCustomDDLB();
                    loadLevelFilter();
                    levelFilter.setEnabled(false);
                    if (viewDdlb.getValue() != null) {

                        currentHierarchy = CommonLogic.getCustomTree(customId);
                    }

                    tableLogic.setCustomId(String.valueOf(customId));
                    tableLogic.setLevelName(STRING_EMPTY);
                    saveEditedRecs();
                    tableLogic.setCurrentHierarchy(currentHierarchy);
                    tableLogic.setIsCustomHierarchy(true);

                    reLoadTable();
                    viewDdlb.setEnabled(true);

                } else if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    customIdToSelect = customId;
                    leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                    checkAll = false;
                    tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    group.setEnabled(true);
                    Object[] inputParameters = new Object[10];
                    inputParameters[0] = session.getProjectionId();
                    inputParameters[1] = "empty-empty-empty";
                    inputParameters[2] = tableLogic.getViewType();
                    inputParameters[3] = StringUtils.EMPTY;
                    inputParameters[8] = "saveCheckUncheckRecords";
                    inputParameters[4] = session.getUserId();
                    inputParameters[5] = session.getSessionId();
                    inputParameters[6] = true;
                    inputParameters[7] = "uncheck";
                    SalesProjectionLogic salesLogic = new SalesProjectionLogic();
                    salesLogic.savecheckedRecords(inputParameters);
                    if (!fieldDdlb.containsId(Constant.GROUPFCAPS)) {
                        fieldDdlb.addItem(Constant.GROUPFCAPS);
                    }
                    if ((Constant.GROUPFCAPS).equals(fieldDdlb.getValue())) {
                        valueDdlb.setVisible(true);
                        valueTxt.setVisible(false);
                    }
                    expand.setEnabled(true);
                    collapse.setEnabled(true);
                    level.setEnabled(true);
                    levelFilter.setEnabled(true);
                    isCustomHierarchy = false;
                    hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
                    currentHierarchy = CommonLogic.getCustomerHierarchy(projectionId, hierarchyLevelNo);
                    saveEditedRecs();

                    tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
                    tableLogic.setCurrentHierarchy(currentHierarchy);
                    tableLogic.setIsCustomHierarchy(false);
                    tableLogic.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
                    tableLogic.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
                    loadLevelFilterValue();
                    loadLevelFilter();
                    reLoadTable();
                    viewDdlb.setEnabled(false);
                    editBtn.setEnabled(false);
                    newBtn.setEnabled(false);
                } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
                    customIdToSelect = customId;
                    leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                    checkAll = false;

                    tableLogic.setViewType(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    group.setEnabled(false);
                    Object[] inputParameters = new Object[10];
                    inputParameters[0] = session.getProjectionId();
                    inputParameters[1] = "empty-empty-empty";
                    inputParameters[2] = tableLogic.getViewType();
                    inputParameters[3] = StringUtils.EMPTY;
                    inputParameters[8] = "saveCheckUncheckRecords";
                    inputParameters[4] = session.getUserId();
                    inputParameters[5] = session.getSessionId();
                    inputParameters[6] = true;
                    inputParameters[7] = "uncheck";
                    SalesProjectionLogic salesLogic = new SalesProjectionLogic();
                    salesLogic.savecheckedRecords(inputParameters);

                    valueDdlb.setVisible(false);
                    valueTxt.setVisible(true);

                    if (fieldDdlb.containsId(Constant.GROUPFCAPS)) {
                        fieldDdlb.removeItem(Constant.GROUPFCAPS);
                    }
                    expand.setEnabled(true);
                    collapse.setEnabled(true);
                    level.setEnabled(true);
                    levelFilter.setEnabled(true);
                    saveEditedRecs();
                    isCustomHierarchy = false;
                    hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
                    currentHierarchy = CommonLogic.getProductHierarchy(projectionId, hierarchyLevelNo);

                    tableLogic.setLevelNo(Integer.parseInt(session.getProductLevelNumber()));
                    tableLogic.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
                    tableLogic.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
                    tableLogic.setCurrentHierarchy(currentHierarchy);
                    tableLogic.setIsCustomHierarchy(false);
                    loadLevelFilterValue();
                    loadLevelFilter();
                    reLoadTable();
                    viewDdlb.setEnabled(false);
                    editBtn.setEnabled(false);
                    newBtn.setEnabled(false);
                }

            }
        }
        LOGGER.info("viewChangeOption ValueChangeEvent ends ");
    }

    public void loadCustomDDLB() {
        LOGGER.info("loadCustomDDLB initiated ");
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);

        customViewList = CommonLogic.getCustomViewList(projectionId);
        if (customViewList != null) {
            viewDdlb.removeAllItems();
            viewDdlb.addItem(Constant.SELECT_ONE);
            viewDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
            Object select = null;
            for (CustomViewMaster obj : customViewList) {
                int customSid = obj.getCustomViewMasterSid();
                Object itemId = customSid;
                if (customIdToSelect == customSid) {
                    select = itemId;
                }
                viewDdlb.addItem(itemId);
                viewDdlb.setItemCaption(itemId, obj.getViewName());
            }
            if (select != null) {
                level.setEnabled(true);
                viewDdlb.select(customIdToSelect);
            } else {
                level.setEnabled(false);
                viewDdlb.setValue(Constant.SELECT_ONE);
            }
        }
        LOGGER.info("loadCustomDDLB ends ");
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("viewDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        tableLogic.setCustomId(String.valueOf(customId));
        level.setEnabled(customId != 0);
        tableLogic.setLevelName(STRING_EMPTY);
        saveEditedRecs();
        tableLogic.setLevelNo(1);
        tableLogic.setCurrentHierarchy(currentHierarchy);
        tableLogic.setIsCustomHierarchy(true);
        tableLogic.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        tableLogic.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
        if (customId != 0) {
            loadLevelFilterValue();
            reLoadTable();
        } else {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    private void attachValueChangeListener(AbstractField component) {
        component.addValueChangeListener(valueChangeListener);

    }

    private void detachLisener(AbstractField abstractField) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) abstractField.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            abstractField.removeValueChangeListener(object);
        }
    }

    public void saveEditedRecs() {
        try {
            List<StringBuilder> queryList = new ArrayList<StringBuilder>();
            StringBuilder queryBuilder1 = null;
            SalesProjectionLogic logic = new SalesProjectionLogic();
            String editedHierNo = StringUtils.EMPTY;
            String tempEditedNos = "WHERE";
            if (editedValues != null) {

                for (String values : editedValues.keySet()) {
                    queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

                    String formatedValue = editedValues.get(values);
                    formatedValue = formatedValue.replace(",", StringUtils.EMPTY);
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();

                    BigDecimal bigEditedValue = new BigDecimal(formatedValue);
                    bigEditedValue.round(MathContext.DECIMAL128);
                    String tempValue[] = values.split("~");
                    String propertyId = tempValue[0];
                    String hierarchyNo = tempValue[1];

                    String keyarr[] = propertyId.split("-");
                    keyarr[0] = (keyarr[0]).replace('q', ' ');
                    String column = keyarr[2];
                    int year = Integer.parseInt(keyarr[1]);
                    int quator = Integer.parseInt(keyarr[0].trim());
                    Double rowcount = Double.parseDouble(String.valueOf(rowCountMap.get(hierarchyNo)));

                    SalesRowDto dto = editedItems.get(tempValue[1] + "~" + tempValue[2] + "~" + tempValue[3]);

                    BigDecimal finalvalue = new BigDecimal(0.0);
                    finalvalue.round(MathContext.UNLIMITED);
                    BigDecimal bigRowCount = new BigDecimal(rowcount);
                    bigRowCount.round(MathContext.DECIMAL128);
                    if (column.equals("AccountGrowth")) {
                        finalvalue = bigEditedValue;
                        queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET ACCOUNT_GROWTH='" + finalvalue + "' ");
                    } else if (column.equals("ProductGrowth")) {
                        finalvalue = bigEditedValue;
                        queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET PRODUCT_GROWTH='" + finalvalue + "' ");

                    } else if (column.equals("ProjectedSales")) {
                        if (incOrDecPer.containsKey(values)) {

                            queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET PROJECTION_SALES=PROJECTION_SALES+(PROJECTION_SALES*'" + incOrDecPer.get(values) + "')");
                        } else {
                            finalvalue = bigEditedValue.divide(bigRowCount, 22, RoundingMode.HALF_UP);
                            queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET PROJECTION_SALES='" + finalvalue + "'");
                        }

                    } else if (column.equals("ProjectedUnits")) {

                        if (incOrDecPer.containsKey(values)) {

                            queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET PROJECTION_UNITS=PROJECTION_UNITS+(PROJECTION_UNITS*'" + incOrDecPer.get(values) + "') ");
                        } else {
                            finalvalue = bigEditedValue.divide(bigRowCount, 22, RoundingMode.HALF_UP);
                            queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION SET PROJECTION_UNITS='" + finalvalue + "' ");
                        }

                    }

                    if (column.equals("ProjectedUnits") || column.equals("ProjectedSales")) {
                        if (!editedHierNo.equals(StringUtils.EMPTY)) {
                            editedHierNo = editedHierNo + ",";
                        }

                        if (!tempEditedNos.equals("WHERE")) {
                            tempEditedNos = tempEditedNos + " OR ";
                        }

                        tempEditedNos = tempEditedNos + " RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ";

                        editedHierNo = editedHierNo + "'" + hierarchyNo + "'";

                    }

                    if (!tableLogic.isIsCustomHierarchy()) {

                        queryBuilder1.append("  where PROJECTION_DETAILS_SID  ");

                        queryBuilder1.append("  in ( ");

                        queryBuilder1.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE  PROJECTION_MASTER_SID='" + (projectionId) + "' AND CCP_DETAILS_SID IN  ");

                        queryBuilder1.append("   (SELECT LCCP.CCP_DETAILS_SID from       ");
                        queryBuilder1.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       ");
                        queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       ");
                        queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       ");
                        queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      ");
                        queryBuilder1.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + (projectionId) + "'      ");

                        if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                            queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH1     ");

                        } else {

                            queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH1     ");

                        }
                        queryBuilder1.append("  ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID  ");
                        queryBuilder1.append("   JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       ");
                        queryBuilder1.append("   WHERE PM.PROJECTION_MASTER_SID='" + (projectionId) + "') CCPMAP,       ");
                        queryBuilder1.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      ");
                        queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       ");

                        if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                            queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH     ");

                        } else {

                            queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH     ");

                        }
                        queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       ");
                        queryBuilder1.append("   AND PCH.PROJECTION_MASTER_SID='" + (projectionId) + "'       ");
                        queryBuilder1.append("    WHERE RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ) HLD      ");
                        queryBuilder1.append("   WHERE CCPMAP.HIERARCHY_NO like concat(HLD.HIERARCHY_NO,'%') ) LCCP      ");
                        queryBuilder1.append("   WHERE LCCP.HIERARCHY_NO in       ");
                        queryBuilder1.append("   (SELECT RLD2.HIERARCHY_NO       ");
                        queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      ");
                        if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                            queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2     ");

                        } else {

                            queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH2     ");

                        }

                        queryBuilder1.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       ");
                        queryBuilder1.append("   AND PCH2.PROJECTION_MASTER_SID='" + (projectionId) + "'       ");
                        queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO = '" + (hierarchyNo) + "')       ");

                        queryBuilder1.append(" ) )");

                    } else {
                        int levelNo = dto.getTreeLevelNo() - 1;
                        int parentLevel = levelNo - 1;

                        String customerLevel = Constant.PERCENT;
                        String productLevel = Constant.PERCENT;
                        String custHier = Constant.PERCENT;
                        String prodHier = Constant.PERCENT;

                        if (dto.getParentHierarchyType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                            customerLevel = levelNo + StringUtils.EMPTY;
                        } else {
                            productLevel = levelNo + StringUtils.EMPTY;
                        }

                        if (!dto.getLastCustomerHierarchyno().equals(Constant.PERCENT)) {

                            custHier = dto.getLastCustomerHierarchyno() + Constant.PERCENT;
                        } else {

                            prodHier = dto.getLastProductHierarchyno() + Constant.PERCENT;
                        }

                        queryBuilder1.append(" where PROJECTION_DETAILS_SID  IN (  \n");

                        queryBuilder1.append("   SELECT distinct pd.PROJECTION_DETAILS_SID   \n");
                        queryBuilder1.append("   FROM   projection_details pd   \n");
                        queryBuilder1.append("   JOIN    (SELECT distinct HLD" + (dto.getParentHierarchyType()) + ".RELATIONSHIP_LEVEL_SID,HLD" + (dto.getParentHierarchyType()) + ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM     \n");
                        queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD   \n");
                        queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID   \n");
                        queryBuilder1.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + (projectionId) + "'   \n");
                        queryBuilder1.append("   ) CCPMAPC   \n");
                        queryBuilder1.append("   JOIN   \n");
                        queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD   \n");
                        queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID   \n");
                        queryBuilder1.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + (projectionId) + "'  \n");
                        queryBuilder1.append("   ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID   \n");
                        queryBuilder1.append("   JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD   \n");
                        queryBuilder1.append("   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + (customId) + "'  AND CVD.LEVEL_NO  like '" + (customerLevel) + "'    \n");
                        queryBuilder1.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID   \n");
                        queryBuilder1.append("   JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID   \n");
                        queryBuilder1.append("   JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + (projectionId) + "'  \n");
                        queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO like '" + (custHier) + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'   \n");
                        queryBuilder1.append("   JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD   \n");
                        queryBuilder1.append("   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON   \n");
                        queryBuilder1.append("   CVD.CUSTOM_VIEW_MASTER_SID='" + customId + "' AND CVD.LEVEL_NO like '" + (productLevel) + "'   \n");
                        queryBuilder1.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID   \n");
                        queryBuilder1.append("   JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID   \n");
                        queryBuilder1.append("   JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + (projectionId) + "'  \n");
                        queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO like  '" + (prodHier) + "' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'   \n");
                        queryBuilder1.append("   ) CCP   \n");
                        queryBuilder1.append("   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID   \n");
                        queryBuilder1.append("   JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas   \n");
                        queryBuilder1.append("   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID   \n");
                        queryBuilder1.append("   JOIN ST_NM_SALES_PROJECTION nm_sp   \n");
                        queryBuilder1.append("   ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID   \n");
                        queryBuilder1.append("   JOIN PERIOD p  ON p.period_sid = nm_sp.PERIOD_SID     WHERE  pd.PROJECTION_MASTER_SID =  '" + (projectionId) + "'   \n");
                        queryBuilder1.append("   AND nm_mas.USER_ID ='" + session.getUserId() + "' AND nm_mas.SESSION_ID = '" + session.getSessionId() + "'  AND nm_sp.USER_ID  = '" + session.getUserId() + "'  AND nm_sp.SESSION_ID = '" + session.getSessionId() + "'  and rld.HIERARCHY_NO='" + hierarchyNo + "'  \n");

                        queryBuilder1.append("  )  AND USER_ID = '" + session.getUserId() + "' AND SESSION_ID = '" + session.getSessionId() + "'  \n");

                    }
                    queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='" + (year) + "'  and QUARTER ='" + (quator) + "' ) AND USER_ID = '" + session.getUserId() + "'  AND SESSION_ID ='" + session.getSessionId() + "' ");
                    queryList.add(queryBuilder1);

                }

                Object input[] = new Object[10];
                input[8] = "saveSalesRec";
                input[0] = queryList;

                logic.saveSalesProjections(input);
                editedValues.clear();
                editedItems.clear();
                incOrDecPer.clear();
                queryList.clear();
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);
                queryList = new ArrayList<StringBuilder>();
                for (String key : editedGroupValues.keySet()) {
                    queryBuilder1.append("  UPDATE ST_NM_SALES_PROJECTION_MASTER SET USER_GROUP='" + editedGroupValues.get(key) + "' ");
                    queryBuilder1.append("  where PROJECTION_DETAILS_SID  ");
                    queryBuilder1.append("  in ( ");
                    queryBuilder1.append("  SELECT PROJECTION_DETAILS_SID FROM PROJECTION_DETAILS WHERE  PROJECTION_MASTER_SID='" + (projectionId) + "' AND CCP_DETAILS_SID IN  ");
                    queryBuilder1.append("   (SELECT LCCP.CCP_DETAILS_SID from       ");
                    queryBuilder1.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       ");
                    queryBuilder1.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       ");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       ");
                    queryBuilder1.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      ");
                    queryBuilder1.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + (projectionId) + "'      ");

                    if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH1     ");

                    } else {

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH1     ");

                    }
                    queryBuilder1.append("  ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID  ");
                    queryBuilder1.append("   JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       ");
                    queryBuilder1.append("   WHERE PM.PROJECTION_MASTER_SID='" + (projectionId) + "') CCPMAP,       ");
                    queryBuilder1.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      ");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       ");

                    if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH     ");

                    } else {

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH     ");

                    }
                    queryBuilder1.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       ");
                    queryBuilder1.append("   AND PCH.PROJECTION_MASTER_SID='" + (projectionId) + "'       ");
                    queryBuilder1.append("    WHERE RLD1.HIERARCHY_NO like '" + key + "' ) HLD      ");
                    queryBuilder1.append("   WHERE CCPMAP.HIERARCHY_NO like concat(HLD.HIERARCHY_NO,'%') ) LCCP      ");
                    queryBuilder1.append("   WHERE LCCP.HIERARCHY_NO in       ");
                    queryBuilder1.append("   (SELECT RLD2.HIERARCHY_NO       ");
                    queryBuilder1.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      ");
                    if (tableLogic.getViewType().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

                        queryBuilder1.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2     ");

                    } else {

                        queryBuilder1.append("    JOIN PROJECTION_PROD_HIERARCHY PCH2     ");

                    }

                    queryBuilder1.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       ");
                    queryBuilder1.append("   AND PCH2.PROJECTION_MASTER_SID='" + (projectionId) + "'       ");
                    queryBuilder1.append("   WHERE RLD2.HIERARCHY_NO = '" + (key) + "')       ");

                    queryBuilder1.append(" ) ) AND USER_ID = '" + session.getUserId() + "'  AND SESSION_ID ='" + session.getSessionId() + "' ");

                    queryList.add(queryBuilder1);

                }

                input = new Object[10];
                input[8] = "saveSalesRec";
                input[0] = queryList;

                logic.saveSalesProjections(input);
                editedGroupValues.clear();

            }

            String levelValues = StringUtils.EMPTY;
            String hierarchyNo = "WHERE";
            for (SalesRowDto dto : customContainer.getItemIds()) {
                if ((Boolean) dto.getPropertyValue(Constant.CHECK)) {
                    if (!levelValues.equals(StringUtils.EMPTY)) {
                        levelValues = levelValues + ",";
                    }
                    if (!hierarchyNo.equals("WHERE")) {
                        hierarchyNo = hierarchyNo + " OR ";
                    }
                    hierarchyNo = hierarchyNo + " RLD1.HIERARCHY_NO like '" + dto.getHierarchyNo() + "' ";
                    levelValues = levelValues + "'" + dto.getLevelName() + "'";
                }
            }

            SalesProjectionLogic salesLogic = new SalesProjectionLogic();

            Object[] inputParameters = new Object[10];
            inputParameters[0] = projectionId;

            inputParameters[1] = levelValues;
            inputParameters[2] = tableLogic.getViewType();
            inputParameters[3] = hierarchyNo;
            inputParameters[4] = session.getUserId();
            inputParameters[5] = session.getSessionId();
            inputParameters[8] = "saveCheckRecords";
            inputParameters[6] = checkAll;
            inputParameters[7] = "uncheck";
            if (!levelValues.equals(StringUtils.EMPTY)) {

                inputParameters[7] = Constant.CHECK;

            }

            if (!changedProperty.equals(StringUtils.EMPTY)) {

                if (changedProperty.contains(Constant.SALES_SMALL)) {
                    changedProperty = Constant.SALES_SMALL;
                } else {
                    changedProperty = Constant.UNITS_SMALL;
                }

                inputParameters[8] = "getCheckedRecords";
                List<Integer> proDetailsID = salesLogic.getCheckedRecords(inputParameters);

                Object[] inputParameters1 = new Object[10];
                inputParameters1[0] = session.getProjectionId();
                inputParameters1[1] = "empty-empty-empty";
                inputParameters1[2] = tableLogic.getViewType();
                inputParameters1[3] = StringUtils.EMPTY;
                inputParameters1[8] = "saveCheckUncheckRecords";
                inputParameters1[4] = session.getUserId();
                inputParameters1[5] = session.getSessionId();
                inputParameters1[6] = true;
                inputParameters1[7] = "uncheck";

                salesLogic.savecheckedRecords(inputParameters1);

                inputParameters = new Object[10];
                inputParameters[8] = "updateByHierarchyNo";
                inputParameters[0] = projectionId;
                inputParameters[1] = tableLogic.getViewType();;
                inputParameters[2] = editedHierNo;
                inputParameters[3] = session.getUserId();
                inputParameters[4] = session.getSessionId();
                inputParameters[5] = Constant.CHECK;
                inputParameters[6] = tempEditedNos;
                logic.updateByHierarchyNo(inputParameters);
                logic.callManualEntry(session, changedProperty);
                inputParameters[5] = "uncheck";
                logic.updateByHierarchyNo(inputParameters);
                String detailsId = StringUtils.EMPTY;
                for (int val : proDetailsID) {
                    if (!detailsId.equals(StringUtils.EMPTY)) {
                        detailsId = detailsId + ",";
                    }
                    detailsId = detailsId + "'" + val + "'";

                }
                if (!detailsId.equals(StringUtils.EMPTY)) {
                    inputParameters[8] = "updateByDetailsId";
                    inputParameters[0] = String.valueOf(projectionId);
                    inputParameters[1] = tableLogic.getViewType();
                    inputParameters[2] = detailsId;
                    inputParameters[3] = session.getUserId();
                    inputParameters[4] = session.getSessionId();
                    logic.updateByDetailsId(inputParameters);
                }
                changedProperty = StringUtils.EMPTY;
            }

        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    public void loadLevelFilter() {
        LOGGER.info("loadLevelFilter initiated ");
        levelFilter.setEnabled(true);
        levelFilter.removeAllItems();

        levelFilter.addItem(Constant.SELECT_ONE);
        levelFilter.setNullSelectionItemId(Constant.SELECT_ONE);

        if (tableLogic.isIsCustomHierarchy()) {
            levelFilter.setEnabled(false);
        } else {
            levelFilter.setEnabled(true);
            if (currentHierarchy != null) {
                for (Leveldto levelDto : currentHierarchy) {
                    if (levelDto.getTreeLevelNo() >= tableLogic.getLevelNo()) {
                        String levelFiterSid = levelDto.getTreeLevelNo() + StringUtils.EMPTY;
                        String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                        Object itemId = levelFiterSid;
                        levelFilter.addItem(itemId);
                        levelFilter.setItemCaption(itemId, caption);
                    }
                }
            }
        }

        LOGGER.info("loadLevelFilter ends ");
    }

    public void loadLevelFilterValue() {
        LOGGER.info("loadLevelValue initiated currentHierarchy.size()=" + currentHierarchy.size());

        level.removeAllItems();

        level.addItem(Constant.SELECT_ONE);
        level.setNullSelectionItemId(Constant.SELECT_ONE);
        if (currentHierarchy != null) {
            for (int i = 0; i < currentHierarchy.size() - 1; i++) {
                Leveldto levelDto = currentHierarchy.get(i);

                if (levelDto.getTreeLevelNo() >= tableLogic.getLevelNo()) {
                    String levelFiterSid = levelDto.getTreeLevelNo() + StringUtils.EMPTY;
                    String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                    Object itemId = levelFiterSid;
                    level.addItem(itemId);
                    level.setItemCaption(itemId, caption);
                }
            }
        }

        LOGGER.info("loadLevelValue ends ");
    }

    public int getTabNumber() {
        return Constant.ONE;

    }

    @UiHandler("excelExport")
    public void excelExportListener(Button.ClickEvent event) {
        try {

            excelExportLogic();
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mass update Value TextField value change listener
     *
     * @param event
     */
    @UiHandler("valueTxt")
    public void valueFieldChangeLogic(Property.ValueChangeEvent event) {
        if (!String.valueOf(event.getProperty().getValue()).matches("([0-9|\\.|])*")) {
            AbstractNotificationUtils.getErrorNotification("Field Error", "Only Numeric values are allowed");
            event.getProperty().setValue(StringUtils.EMPTY);
            return;
        }
    }

    public void saveSalesSelections() {
        LOGGER.info("saveSalesProjection method starts");
        Map map = new HashMap();

        String tempVariables = variables.getValue().toString();

        tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
        map.put(Constant.HISTORY, historyDdlb.getValue());
        map.put("variables", tempVariables);
        map.put("actualOrProj", actualsProjections.getValue().toString());
        map.put("periodOrder", proPeriodOrd.getValue().toString());

        if (contract.getValue() != null && !contract.getValue().equals(StringUtils.EMPTY)) {
            map.put("altContract", contract.getValue().toString());
        } else {
            map.put("altContract", StringUtils.EMPTY);
        }

        if (brand.getValue() != null && !brand.getValue().equals(StringUtils.EMPTY)) {
            map.put("altBrand", brand.getValue().toString());
        } else {
            map.put("altBrand", " ");
        }
        if (methodology.getValue() != null) {
            map.put(Constant.METHODOLOGY, methodology.getValue().toString());
        } else {
            map.put(Constant.METHODOLOGY, Constant.SELECT_ONE);
        }

        CommonLogic.saveProjectionSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION);

        LOGGER.info("saveSalesProjection method ends");

    }

    public void setProjectionSelection() {

        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(session.getProjectionId(), Constant.SALES_PROJECTION);
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.HISTORY);

            if (value != null) {
                historyDdlb.setValue(Integer.parseInt(value.toString().trim()));
            }

            value = map.get("periodOrder");

            if (value != null) {
                proPeriodOrd.setValue(value.toString());
            }
            value = map.get("actualOrProj");

            if (value != null) {
                actualsProjections.setValue(value.toString());
            }

            value = map.get("variables");

            if (value != null) {
                String value1 = (String) value;
                final String[] col = value1.split(",");

                for (String tempValue : col) {

                    tempValue = tempValue.trim();

                    if (tempValue.equals(Constant.SALES_SMALL)) {

                        variables.select(Constant.SALES_SMALL);
                        salesFlag = true;
                    }
                    if (tempValue.equals(Constant.UNITS_SMALL)) {

                        variables.select(Constant.UNITS_SMALL);
                        unitFlag = true;
                    }
                    if (tempValue.equals(Constant.PRODUCT_GROWTH)) {

                        variables.select(Constant.PRODUCT_GROWTH);
                        proGrwFlag = true;
                    }
                    if (tempValue.equals(Constant.ACCOUNT_GROWTH)) {

                        variables.select(Constant.ACCOUNT_GROWTH);
                        accGrwFlag = true;
                    }
                }
            }
            if (map.containsKey(Constant.METHODOLOGY)) {
                value = map.get(Constant.METHODOLOGY);
                if (!String.valueOf(value).equalsIgnoreCase(Constant.SELECT_ONE)) {
                    methodology.select(value);
                }
            }

            if (map.containsKey("altContract")) {
                value = map.get("altContract");
                contract.setValue(String.valueOf(value));
            }
            if (map.containsKey("altBrand")) {
                value = map.get("altBrand");
                brand.setValue(String.valueOf(value));
            }

        }

    }

    public boolean getSubmitFlag() {

        if (customContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void enableDisableFields() {

        if (Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            contract.setEnabled(false);
            brand.setEnabled(false);
            adjust.setEnabled(false);
            massUpdate.setEnabled(false);
            type.setEnabled(false);
            basis.setEnabled(false);
            variable.setEnabled(false);
            adjustPeriods.setEnabled(false);
            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            methodology.setEnabled(false);
            methodology.setEnabled(false);
            allocMethodology.setEnabled(false);
            calculate.setEnabled(false);;
            populate.setEnabled(false);
            adjust.setEnabled(false);
            adjustment.setStyleName(Constant.TXT_RIGHT_ALIGN);
            adjustment.setEnabled(false);
        }

    }

    /**
     * To check and Clear all check box
     *
     * @param checkClear
     */
    public void checkClearAll(boolean checkClear) {
        LOGGER.info("Inside checkClearAll");
        tableLogic.setRefresh(Boolean.FALSE);
        Object[] inputParameters = new Object[10];
        inputParameters[0] = session.getProjectionId();
        String groupFilterValue = String.valueOf(group.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
        String methodologyFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());
        String baseFilterValue = String.valueOf(metohdologyFilter.getValue()).equals(Constant.SHOW_ALL) ? STRING_EMPTY : String.valueOf(metohdologyFilter.getValue());

        inputParameters[1] = groupFilterValue + "-" + methodologyFilterValue + "-" + baseFilterValue;

        inputParameters[2] = tableLogic.getViewType();
        inputParameters[3] = StringUtils.EMPTY;
        inputParameters[8] = "saveCheckUncheckRecords";
        inputParameters[4] = session.getUserId();
        inputParameters[5] = session.getSessionId();
        inputParameters[6] = checkAll;
        if (checkClear) {
            inputParameters[7] = Constant.CHECK;
        } else {
            inputParameters[7] = "uncheck";
        }
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        salesLogic.savecheckedRecords(inputParameters);

        for (String hierarchyNo : tableLogic.getAllLevels()) {

            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;
                tempDto.setUncheckCount(checkClear ? 0 : Integer.valueOf(tempDto.getCcpCount()));
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("exiting checkClearAll");
        tableLogic.setRefresh(Boolean.TRUE);
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

    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        tableLayout.addComponent(tableLogic.createControls());
    }

    private void alignRight() {
        for (Object obj : rightHeader.getSingleColumns()) {
            rightTable.setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    /**
     * Excel export logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void excelExportLogic() throws SystemException, PortalException, Exception {
        excelTable = new ExtCustomTreeTable();
        tableLayout.addComponent(excelTable);
        excelTable.setRefresh(Boolean.FALSE);
        excelTable.setVisible(Boolean.FALSE);
        excelContainer.setColumnProperties(excelHeader.getProperties());
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
        excelTable.setColumnHeaders(Arrays.copyOf(excelHeader.getSingleHeaders().toArray(), excelHeader.getSingleHeaders().size(), String[].class));
        for (Object propertyId : excelTable.getContainerPropertyIds()) {
            if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                excelTable.setConverter(propertyId, salesFormat);
            } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
                excelTable.setConverter(propertyId, unitFormat);
            } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
                excelTable.setConverter(propertyId, percentFormat);
            }
        }

        generateButtonlogicForExcel();
        Map<String, String> formatter = new HashMap<String, String>();
        formatter.put("currencyNoDecimal", Constant.SALES_SMALL);
        formatter.put("unitNoDecimal", Constant.UNITS_SMALL);
        formatter.put("percentTwoDecimal", Constant.GROWTH);
        excelTable.setRefresh(Boolean.TRUE);
        ForecastUI.EXCEL_CLOSE=true;
        CustomExcelNM export = new CustomExcelNM(new ExtCustomTableHolder(excelTable), "Sales Projection", "Sales Projection", "Sales Projection.xls", false, formatter);
        export.export();
    }

    public void generateButtonlogicForExcel() throws SystemException, PortalException, Exception {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        excelContainer.removeAllItems();
        int levelNumber = 1;
        String parentName = StringUtils.EMPTY;
        String hierarchyIndicator = "C";
        int treeLevelNo = 1;
        List customDetailsList = new ArrayList();

        Object[] selection = new Object[24];
        selection[0] = StringUtils.EMPTY + tableLogic.getProjectionId() + StringUtils.EMPTY;
        selection[1] = tableLogic.getLevelNo();
        selection[2] = tableLogic.getLevelName();
        selection[3] = tableLogic.getRightDto();
        selection[4] = false;
        selection[5] = 0;
        selection[6] = 10000;
        selection[7] = false;
        selection[8] = "fetchSalesResult";
        selection[9] = tableLogic.getViewType();
        selection[11] = tableLogic.isIsCustomHierarchy();
        selection[13] = tableLogic.getUserId();
        selection[14] = tableLogic.getSessionId();
        selection[15] = tableLogic.getCustomId();
        selection[17] = tableLogic.getViewType();

        tableLogic.setLastCustomerHierarchyNo(Constant.PERCENT);
        tableLogic.setLastProductHierarchyNo(Constant.PERCENT);

        selection[19] = tableLogic.getLastCustomerHierarchyNo();
        selection[20] = tableLogic.getLastProductHierarchyNo();

        if (tableLogic.getLevelFilterValue().equals(STRING_EMPTY)) {
            int tempLevelNumber = tableLogic.getLevelNo();
            if (tableLogic.isIsCustomHierarchy()) {
                tempLevelNumber = 1;
            }

            Leveldto levelDto = CommonLogic.getNextLevel(tempLevelNumber, currentHierarchy);

            if (levelDto != null) {
                parentName = levelDto.getParentNode();
                hierarchyIndicator = levelDto.getHierarchyIndicator();
                levelNumber = levelDto.getLevelNo();
                if (tableLogic.isIsCustomHierarchy()) {
                    treeLevelNo = 1;
                    selection[2] = STRING_EMPTY;
                    selection[1] = 1;
                } else {
                    treeLevelNo = levelNumber;
                }
                selection[9] = hierarchyIndicator;
            }

        } else {

            selection[8] = "fetchByHierarchyNo";
            selection[12] = tableLogic.getLevelFilterValue();
            selection[13] = tableLogic.getUserId();
            selection[14] = tableLogic.getSessionId();
        }
        selection[16] = STRING_EMPTY;

        selection[18] = tableLogic.getUserGroup();
        selection[21] = tableLogic.getMethodology();
        selection[22] = tableLogic.getBaseLine();

        if (String.valueOf(selection[9]).equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                tableLogic.setLastCustomerHierarchyNo((String) selection[16]);
            }

        } else {
            if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                tableLogic.setLastProductHierarchyNo((String) selection[16]);
            }
        }
        customDetailsList.add(levelNumber);
        customDetailsList.add(parentName);
        customDetailsList.add(treeLevelNo);

        selection[10] = customDetailsList;

        List list = logic.generateSalesProjection(selection, this);

        loadDataToContainer(list, null, true);

    }

    public void loadDataToContainer(List<SalesRowDto> resultList, Object parentId, boolean isRecursive) {
        for (SalesRowDto dto : resultList) {
            excelContainer.addBean(dto);
            if (parentId != null) {
                excelContainer.setParent(dto, parentId);
            }
            if (dto.getLevelNo() != null && dto.getLevelNo() != 0) {
                excelContainer.setChildrenAllowed(dto, true);
                if (isRecursive) {
                    addLowerLevelsForExport(dto);
                }
            } else {
                excelContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(SalesRowDto dto) {
        try {

            List list = new ArrayList();
            if (!tableLogic.isRestrictLoad()) {
                try {
                    int levelNumber = 1;
                    String parentName = StringUtils.EMPTY;
                    String hierarchyIndicator = "C";
                    int treeLevelNo = 1;
                    List customDetailsList = new ArrayList();

                    Object[] selection = new Object[24];
                    selection[0] = StringUtils.EMPTY + tableLogic.getProjectionId() + StringUtils.EMPTY;
                    selection[1] = tableLogic.getLevelNo();
                    selection[2] = dto.getActualLevel();
                    selection[3] = tableLogic.getRightDto();
                    selection[4] = false;
                    selection[5] = 0;
                    selection[6] = 10000;
                    selection[7] = false;
                    selection[8] = "fetchSalesResult";
                    selection[9] = tableLogic.getViewType();
                    selection[11] = tableLogic.isIsCustomHierarchy();
                    selection[13] = tableLogic.getUserId();
                    selection[14] = tableLogic.getSessionId();
                    selection[15] = tableLogic.getCustomId();
                    selection[17] = tableLogic.getViewType();

                    SalesProjectionLogic logic = new SalesProjectionLogic();

                    if (tableLogic.getLevelFilterValue().equals(STRING_EMPTY)) {

                        selection[1] = dto.getLevelNo() + 1;
                        selection[2] = dto.getActualLevel();
                        selection[16] = dto.getHierarchyNo();
                        selection[19] = dto.getLastCustomerHierarchyno();
                        selection[20] = dto.getLastProductHierarchyno();
                        int tempLevelNumber = dto.getLevelNo() + 1;
                        if (tableLogic.isIsCustomHierarchy()) {
                            tempLevelNumber = dto.getTreeLevelNo();
                        }
                        Leveldto levelDto = CommonLogic.getNextLevel(tempLevelNumber, currentHierarchy);

                        if (levelDto != null) {
                            if (tableLogic.isIsCustomHierarchy()) {
                                parentName = levelDto.getParentNode();
                                hierarchyIndicator = levelDto.getHierarchyIndicator();
                                levelNumber = levelDto.getLevelNo();
                                treeLevelNo = dto.getTreeLevelNo();
                                selection[2] = STRING_EMPTY;
                                selection[1] = dto.getTreeLevelNo();
                            } else {
                                levelNumber = levelDto.getLevelNo();
                                parentName = dto.getLevelName();
                                treeLevelNo = levelNumber;
                                hierarchyIndicator = levelDto.getHierarchyIndicator();
                            }

                            selection[9] = hierarchyIndicator;
                        }
                        if (tableLogic.isIsCustomHierarchy()) {
                            selection[17] = dto.getParentHierarchyType();
                        }

                    } else {

                        selection[8] = "fetchByHierarchyNo";
                        selection[12] = tableLogic.getLevelFilterValue();
                        selection[13] = tableLogic.getUserId();
                        selection[14] = tableLogic.getSessionId();
                    }

                    selection[18] = tableLogic.getUserGroup();
                    selection[21] = tableLogic.getMethodology();
                    selection[22] = tableLogic.getBaseLine();

                    if (String.valueOf(selection[9]).equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                            tableLogic.setLastCustomerHierarchyNo((String) selection[16]);
                        }

                    } else {
                        if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                            tableLogic.setLastProductHierarchyNo((String) selection[16]);
                        }
                    }
                    customDetailsList.add(levelNumber);
                    customDetailsList.add(parentName);
                    customDetailsList.add(treeLevelNo);

                    selection[10] = customDetailsList;

                    list = logic.generateSalesProjection(selection, this);

                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<SalesRowDto> levelList = list;
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Expand button logic.
     *
     * @param event the event
     */
    @UiHandler("expand")
    public void expandButtonLogic(Button.ClickEvent event) {
        expandCollapseLevelOption(true, level.getValue());
    }

    /**
     * Collapse button logic.
     *
     * @param event the event
     */
    @UiHandler("collapse")
    public void collapseButtonLogic(Button.ClickEvent event) {
        expandCollapseLevelOption(false, level.getValue());
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        String selectedId = DASH;
        if (value != null) {
            if (!SELECT_ONE.equals(String.valueOf(value))) {
                selectedId = String.valueOf(value);
            }
        }
        int levelNo = Integer.valueOf(selectedId);
        if (levelNo > 0) {
            Object val = levelFilter.getValue();
            if (val != null) {
                if (!SELECT_ONE.equals(String.valueOf(val))) {
                    levelFilter.removeValueChangeListener(levelFilterChangeOption);
                    levelFilter.setValue(SELECT_ONE);
                    tableLogic.clearAll();
                    levelFilter.addValueChangeListener(levelFilterChangeOption);
                } else {
                    tableLogic.clearAllExceptCurrentPage();
                }
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
            tableLogic.setLevelFilterValue(STRING_EMPTY);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }
    }

    private int getLevelNo() {
        int levelNo = 0;
        if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
            levelNo = Integer.valueOf(session.getCustomerLevelNumber());
        } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
            levelNo = Integer.valueOf(session.getProductLevelNumber());
        }

        return levelNo;
    }

    private void formatData(Object propertyId) {

        if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
            resultsTable.getRightFreezeAsTable().setConverter(propertyId, salesFormat);
        } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
            resultsTable.getRightFreezeAsTable().setConverter(propertyId, unitFormat);
        } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
            resultsTable.getRightFreezeAsTable().setConverter(propertyId, percentFormat);
        }
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
            if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                isCustomHierarchy = false;
                hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
                currentHierarchy = CommonLogic.getCustomerHierarchy(projectionId, hierarchyLevelNo);
                tableLogic.setViewType(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                tableLogic.setLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
            } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
                isCustomHierarchy = false;
                hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
                currentHierarchy = CommonLogic.getProductHierarchy(projectionId, hierarchyLevelNo);
                tableLogic.setViewType(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                tableLogic.setLevelNo(Integer.parseInt(session.getProductLevelNumber()));
            } else if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
                isCustomHierarchy = true;
                tableLogic.setLevelNo(1);
            }

        }

        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {

        }
    }

    public void loadUserGroup() {

        groupBean.removeAllItems();
        massGroupBean.removeAllItems();
        List<String> groupList = new ArrayList<String>();
        SalesProjectionLogic logic = new SalesProjectionLogic();

        Object[] inputs = new Object[9];
        inputs[0] = session.getProjectionId();
        inputs[1] = session.getSessionId();
        inputs[2] = session.getUserId();
        inputs[3] = Constant.GROUP;

        inputs[8] = "loadGroupValues";
        groupList = logic.loadGroup(inputs);
        groupBean.addBean(Constant.SHOW_ALL);

        for (String str : groupList) {

            if (str != null && !str.equals(StringUtils.EMPTY)) {

                groupBean.addBean(str);
                massGroupBean.addBean(str);
            }
        }
        group.setContainerDataSource(groupBean);
        group.select(Constant.SHOW_ALL);

    }

    public void loadBaseLine() {

        baseLineBean.removeAllItems();
        List<String> groupList = new ArrayList<String>();
        SalesProjectionLogic logic = new SalesProjectionLogic();

        Object[] inputs = new Object[9];
        inputs[0] = session.getProjectionId();
        inputs[1] = session.getSessionId();
        inputs[2] = session.getUserId();
        inputs[3] = Constant.BASELINE;

        inputs[8] = "loadGroupValues";
        groupList = logic.loadGroup(inputs);
        baseLineBean.addBean(Constant.SHOW_ALL);
        for (String str : groupList) {

            if (str != null && !str.equals(StringUtils.EMPTY)) {

                baseLineBean.addBean(str);
            }
        }

        baseLineFilter.select(Constant.SHOW_ALL);

    }

    public void loadMethodology() {

        methdologyBean.removeAllItems();
        List<String> groupList = new ArrayList<String>();
        SalesProjectionLogic logic = new SalesProjectionLogic();

        Object[] inputs = new Object[9];
        inputs[0] = session.getProjectionId();
        inputs[1] = session.getSessionId();
        inputs[2] = session.getUserId();
        inputs[3] = Constant.METHODOLOGY;

        inputs[8] = "loadGroupValues";
        groupList = logic.loadGroup(inputs);
        methdologyBean.addBean(Constant.SHOW_ALL);

        for (String str : groupList) {

            if (str != null && !str.equals(StringUtils.EMPTY)) {

                methdologyBean.addBean(str);
            }
        }
        metohdologyFilter.select(Constant.SHOW_ALL);
    }

    public void callManualEntryProcedure() {
        saveEditedRecs();
        refreshTableData(getManualEntryRefreshHiearachyNo());
        loadUserGroup();
        editedValues.clear();
        editedItems.clear();
    }

    public static int getProjections(Date startDate, Date endDate) {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if (diffMonth % 3 == 0) {

            return diffMonth / 3;
        } else {

            return (diffMonth / 3) + 1;
        }

    }

    @UiHandler("fieldDdlb")
    public void fieldDdlb(Property.ValueChangeEvent event) {
        LOGGER.info("fieldDdlb value change listener starts");

        if (fieldDdlb.getValue() != null) {
            if (!(Constant.GROUPFCAPS).equals(fieldDdlb.getValue())) {
                valueDdlb.setVisible(false);
                valueTxt.setVisible(true);
                startPeriod.setVisible(true);
                endPeriod.setVisible(true);
                lblEnd.setVisible(true);
                lblStart.setVisible(true);
            } else {
                valueDdlb.setVisible(true);
                valueTxt.setVisible(false);
                startPeriod.setVisible(false);
                endPeriod.setVisible(false);
                lblEnd.setVisible(false);
                lblStart.setVisible(false);
            }
        }
        LOGGER.info("fieldDdlb value change listener ends");
    }

    public void customAdjustment(Object[] selections, Object[] inputs) {
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();

        Collection<?> itemsList = resultsTable.getRightFreezeAsTable().getItemIds();
        List<SalesRowDto> salesDtoList = new ArrayList<SalesRowDto>();
        List<SalesRowDto> finalList = new ArrayList<SalesRowDto>();
        if (itemsList != null && !itemsList.isEmpty()) {
            List<String> hiearchyNoList = new ArrayList<String>();
            for (Object item : itemsList) {
                SalesRowDto dto = (SalesRowDto) item;
                salesDtoList.add(dto);
                hiearchyNoList.add(dto.getHierarchyNo());
            }
            Object[] inputParameters = new Object[20];
            SalesProjectionLogic logic = new SalesProjectionLogic();
            for (SalesRowDto sDto : salesDtoList) {
                if ((Boolean) sDto.getPropertyValue(Constant.CHECK)) {
                    inputParameters[0] = session.getProjectionId();
                    inputParameters[1] = sDto.getTreeLevelNo() - 1;
                    inputParameters[2] = tableLogic.getViewType();
                    inputParameters[3] = StringUtils.EMPTY + sDto.getHierarchyNo() + StringUtils.EMPTY;
                    inputParameters[8] = "saveBfrAllocation";
                    inputParameters[4] = session.getUserId();
                    inputParameters[5] = session.getSessionId();
                    inputParameters[6] = checkAll;
                    inputParameters[7] = sDto.getHierarchyType();
                    inputParameters[9] = tableLogic.getCustomId();
                    inputParameters[10] = sDto.getParentHierarchyType();
                    inputParameters[11] = sDto.getLastCustomerHierarchyno();
                    inputParameters[12] = tableLogic.isIsCustomHierarchy();
                    inputParameters[13] = sDto.getLastProductHierarchyno();
                    inputParameters[14] = selections[1];
                    inputParameters[15] = selections[2];
                    inputParameters[16] = selections[3];
                    inputParameters[17] = selections[4];
                    inputParameters[18] = selections[5];
                    salesLogic.saveAdjustmentSelections(inputParameters);
                }
            }
        }

        try {
            salesLogic.callAdjustmentProcedure(inputs);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {

        SalesRowDto dto = (SalesRowDto) itemId;
        int newRecordsCount = updatedRecordsNo;
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else {
            if (newRecordsCount < dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        }

        List<String> hierarchyNos = tableLogic.getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {

            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;

                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }

                updateChecks(tempId);
            }
        }
    }

    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {

        List<String> childTableHierarchyNos = tableLogic.getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(Integer.valueOf(tempDto.getCcpCount()));
                }

                updateChecks(tempId);
            }
        }

    }

    private void updateChecks(Object tempId) {

        ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
        SalesRowDto tempDto = (SalesRowDto) tempId;

        if (tempDto.getUncheckCount() != 0) {
            tempDto.addBooleanProperties(Constant.CHECK, false);
            if (leftTable.containsId(tempId)) {

                leftTable.getContainerProperty(tempId, Constant.CHECK).setValue(false);
            }
        } else {
            tempDto.addBooleanProperties(Constant.CHECK, true);
            if (leftTable.containsId(tempId)) {

                leftTable.getContainerProperty(tempId, Constant.CHECK).setValue(true);
            }
        }

    }

    public Set<String> getCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<String>();
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                int uncheckCount = ((SalesRowDto) itemId).getUncheckCount();
                int ccpCount = Integer.valueOf(((SalesRowDto) itemId).getCcpCount());
                if (ccpCount != uncheckCount) {
                    finalHirarechyNo.add(tableTreeLevelNo);
                }
            }
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHiearachyNo());
        return finalHirarechyNo;
    }

    private Set<String> getManualEntryRefreshHiearachyNo() {
        Set<String> finalHirarechyNo = new HashSet<String>();
        for (String hirarechyNo : tableHirarechyNos) {

            if (hirarechyNo != null && !hirarechyNo.equalsIgnoreCase(Constant.NULL)) {
                finalHirarechyNo.addAll(tableLogic.getAllParentLevels(hirarechyNo));
                finalHirarechyNo.addAll(tableLogic.getAllChildLevels(hirarechyNo));
                finalHirarechyNo.add(hirarechyNo);
            }
        }

        tableHirarechyNos.clear();
        return finalHirarechyNo;
    }

    public void refreshTableData(Set<String> finalHirarechyNo) {

        tableLogic.forRefresh(finalHirarechyNo);
        if (!tableLogic.isIsCustomHierarchy()) {
            tableLogic.setBulkDataLoadAllowed(true);
        }
        tableLogic.setCurrentPage(tableLogic.getCurrentPage());

        if (!tableLogic.isIsCustomHierarchy()) {
            tableLogic.setBulkDataLoadAllowed(false);
        }

    }

    public SessionDTO getSalesSession() {
        return session;
    }

    /**
     * To update the check box
     *
     * @param tempId
     * @param isPresentInContainer
     */
    private void updateChecks(Object tempId, boolean isPresentInContainer) {

        SalesRowDto tempDto = (SalesRowDto) tempId;
        boolean checkValue = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties(Constant.CHECK, checkValue);
        if (isPresentInContainer) {
            tableLogic.getContainerDataSource().getContainerProperty(tempId, Constant.CHECK).setValue(checkValue);
        }
    }

    public int customDdlbOptionChange(ComboBox customDdlb, Button editBtn) {
        editBtn.setEnabled(false);
        level.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!Constant.NULL.equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.valueOf(value);
            editBtn.setEnabled(true);
            level.setEnabled(true);
            return selectedId;
        }
        return 0;
    }
    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (event.isChecked()) {
                checkAll = true;
                checkClearAll(true);
            } else {
                checkAll = false;
                checkClearAll(false);
            }
        }
    };

}
