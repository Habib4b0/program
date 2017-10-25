/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import static com.stpl.app.serviceUtils.ConstantUtil.SELECT_ONE;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Shrihariharan
 */
public abstract class ForecastProjectionVariance extends CustomComponent implements View {

    /**
     * The comparison.
     */
    @UiField("comparison")
    protected CustomTextField comparison;
    /**
     * The level.
     */
    @UiField("level")
    protected OptionGroup level;
    /**
     * The projection period order.
     */
    @UiField("projectionPeriodOrder")
    protected OptionGroup projectionPeriodOrder;
    /**
     * The pivot view.
     */
    @UiField("pivotView")
    protected OptionGroup pivotView;
    
    public OptionGroup variableCategory;
    /**
     * The variable category.
     */
    @UiField("variableCategoryCustomMenuBar")
    public CustomMenuBar variableCategoryCustomMenuBar;
    
    protected CustomMenuBar.CustomMenuItem variableCategoryCustomMenuItem;
    /**
     * The variables.
     */
    public OptionGroup variables = new OptionGroup();

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    protected CustomMenuBar.CustomMenuItem customMenuItem;
    /**
     * The view.
     */
    @UiField("view")
    protected OptionGroup view;
    /**
     * The from date.
     */
    @UiField("fromDate")
    protected ComboBox fromDate;
    /**
     * The to date.
     */
    @UiField("toDate")
    protected ComboBox toDate;
    
    @UiField("comparisonBasis")
    protected ComboBox comparisonBasis;
    /**
     * The level ddlb.
     */
    @UiField("levelDdlb")
    protected ComboBox levelDdlb;
    /**
     * The level filter.
     */
    @UiField("levelFilter")
    protected ComboBox levelFilter;
    /**
     * The custom ddlb.
     */
    @UiField("customDdlb")
    protected ComboBox customDdlb;
    /**
     * The frequency.
     */
    @UiField("frequency")
    protected ComboBox frequency;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    protected Button excelBtn;
    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    protected Button graphBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * The expand lvl btn.
     */
    @UiField("expandBtn")
    protected Button expandLvlBtn;

    @UiField("generateBtn")
    protected Button generateBtn;
    /**
     * The collapse lvl btn.
     */
    @UiField("collapseBtn")
    protected Button collapseLvlBtn;
    /**
     * The add view btn.
     */
    @UiField("newBtn")
    protected Button addViewBtn;
    /**
     * The edit view btn.
     */
    @UiField("editBtn")
    protected Button editViewBtn;
    /**
     * The discount level.
     */
    @UiField("discountLevel")
    protected OptionGroup discountLevel;
    /**
     * The TableVerticalLayout.
     */
    @UiField("tableVerticalLayout")
    protected VerticalLayout tableVerticalLayout;
    /**
     * The VerticalLayout.
     */
    @UiField("verticalLayout")
    protected VerticalLayout verticalLayout;
    /**
     * The excel btn.
     */
    @UiField("group")
    protected ComboBox group;

    @UiField("pivotPanel")
    protected Panel pivotPanel;

    @UiField("groupLabel")
    protected Label groupLabel;

    @UiField("discountLevelLabel")
    protected Label discountLevelLabel;

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    /**
     * Session DTO
     */
    protected SessionDTO sessionDTO;
    /**
     * Screen Name
     */
    protected String screenName;
    protected PVSelectionDTO pvSelectionDTO = new PVSelectionDTO();
    protected int customIdToSelect = 0;
    protected boolean generated = false;
    protected List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    protected int customId = 0;
    /**
     * Logger for ForecastProjectionVariance
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastProjectionVariance.class);
    /**
     * Table logic class instance
     */
    protected ProjectionVarianceTableLogic tableLogic = new ProjectionVarianceTableLogic();
    /**
     * Table
     */
    protected FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    /**
     * Result container
     */
    protected ExtTreeContainer<ProjectionVarianceDTO> resultBeanContainer = new ExtTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * Excel export result container
     */
    protected ExtTreeContainer<ProjectionVarianceDTO> resultExcelContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;
    /**
     * Excel table
     */
    protected ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /**
     * Left Header
     */
    protected CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    /**
     * Right Header
     */
    protected CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    /**
     * Full header
     */
    protected CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    /**
     * Property file for alert message
     */
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    /**
     * Level filter value change listener
     */
    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };
    protected String variablesValue = StringUtils.EMPTY;
    protected String variableCategoryValue = StringUtils.EMPTY;
    protected ForecastProjectionVariance(SessionDTO session, String screenName) {
        this.sessionDTO = session;
        this.screenName = screenName;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/ProjectionVariance.xml"), this));
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            pvSelectionDTO.setScreenName(screenName);
        }
        groupLabel.setVisible(false);
        group.setVisible(false);
        Panel panel = new Panel();
        panel.setContent(layout);
        setCompositionRoot(panel);
        configureFilds();

    }

    public void configureFilds() {
        comparison.addStyleName(Constant.SEARCH_ICON_STYLE);
        comparison.setReadOnly(false);
        comparison.setValue(SELECT_ONE);
        customMenuBar.setScrollable(true);
        customMenuBar.setPageLength(NumericConstants.TEN);
        level.addItem(Constant.TOTAL);
        level.addItem(Constant.DETAIL);
        level.select(Constant.TOTAL);
        level.addStyleName(Constant.HORIZONTAL);
        level.addStyleName(Constant.OPTION_GROUP_WIDTH);

        projectionPeriodOrder.addItem(ASCENDING.getConstant());
        projectionPeriodOrder.addItem(Constant.DESCENDING);
        projectionPeriodOrder.select(ASCENDING.getConstant());
        projectionPeriodOrder.addStyleName(Constant.HORIZONTAL);
        projectionPeriodOrder.addStyleName(Constant.OPTION_GROUP_WIDTH);

        fromDate.addItem(SELECT_ONE);
        fromDate.setNullSelectionItemId(SELECT_ONE);
        fromDate.select(SELECT_ONE);

        toDate.addItem(SELECT_ONE);
        toDate.setNullSelectionItemId(SELECT_ONE);
        toDate.select(SELECT_ONE);
        
        comparisonBasis.addItem("Actuals");
        comparisonBasis.addItem("Current Projection");
        comparisonBasis.select("Current Projection");

        view.addStyleName(Constant.HORIZONTAL);
        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT);
        view.addItem(Constant.CUSTOM);
        view.setValue(Constant.CUSTOMER_SMALL);

        pivotView.addItem(Constant.PERIOD);
        pivotView.addItem("Variable");
        pivotView.select(Constant.PERIOD);
        pivotView.addStyleName(Constant.HORIZONTAL);
        pivotView.addStyleName(Constant.OPTION_GROUP_WIDTH);

        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.select(SELECT_ONE);
        levelDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilter.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilter.setImmediate(true);
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.select(SELECT_ONE);

        customDdlb.addItem(SELECT_ONE);
        customDdlb.setNullSelectionItemId(SELECT_ONE);
        customDdlb.select(SELECT_ONE);

//        variableCategory.addStyleName(Constant.HORIZONTAL);
//        variableCategory.addItem(CommonUtils.COL_VALUE);
//        variableCategory.addItem(CommonUtils.COL_VARIANCE);
//        variableCategory.addItem(CommonUtils.COL_PERCENTAGE);

        comparison.setReadOnly(true);
        comparison.focus();

        view.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                viewChange(true);
            }
        });

        initializeResultTable();
    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public void configureExcelTable() {
        resultExcelContainer = new ExtTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
        resultExcelContainer.setColumnProperties(leftHeader.getProperties());
        resultExcelContainer.setColumnProperties(rightHeader.getProperties());
        excelTable.setContainerDataSource(resultExcelContainer);
        excelTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        excelTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        excelTable.setDoubleHeaderVisible(true);
        excelTable.setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        excelTable.setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));
        excelTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    protected void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            controlLayout = tableLogic.createControls();
        } else {
            HorizontalLayout controls = tableLogic.createControls();
            controlLayout = CommonLogic.getResponsiveControls(controls);
        }
        tableVerticalLayout.addComponent(controlLayout);
        tableVerticalLayout.addComponent(excelTable);
    }

    public void levelFilterDdlbChangeOption(final boolean excelFlag) {
        LOGGER.debug("levelFilterDdlbChangeOption ValueChangeEvent initiated ");

        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        pvSelectionDTO.setIslevelFiler(true);
        if (levelNo == 0) {
            pvSelectionDTO.setIslevelFiler(false);
        }
        callTableLogic(levelNo, hierarchyNo, excelFlag);
        LOGGER.debug("levelFilterDdlbChangeOption ValueChangeEvent ends ");
    }

    @UiHandler("comparison")
    public void comparisonLookupListener(CustomTextField.ClickEvent event) {
        LOGGER.debug("comparisonLookupListener ValueChangeEvent initiated ");
        comparisonLookupLogic();
        LOGGER.debug("comparisonLookupListener ValueChangeEvent initiated ");
    }

    @UiHandler("expandBtn")
    public void expandBtnLogic(Button.ClickEvent event) {
        try {
            expandCollapseLevelOption(true, levelDdlb.getValue());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("collapseBtn")
    public void collapseBtnLogic(Button.ClickEvent event) {
      
        try {
            expandCollapseLevelOption(false, levelDdlb.getValue());
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }

    /**
     * new the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        customTreeViewLogic();
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        editHierarchyLogic();

    }

    @UiHandler("customDdlb")
    public void customDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customDdlbChangeOption();
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @UiHandler("frequency")
    public void loadFrequencyDdlb(Property.ValueChangeEvent event) {
        loadFrequency();
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        resetBtnLogic();
    }

    @UiHandler("generateBtn")
    public void generate(Button.ClickEvent event) throws SystemException, PortalException {
        try {

            LOGGER.debug("------ Inside generate security Projection Variance Tab and generate Button");

            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                Utility.getTradingPartnerLevelNo(sessionDTO.getProjectionId(),sessionDTO);
                PPAProjectionLogic.waitForPPAProcedure();
            }

            if (frequency.getValue() != null && !Constant.NULL.equals(String.valueOf(frequency.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(frequency.getValue()))) {
                String selectedCustomID = String.valueOf(customDdlb.getValue());
                customIdToSelect=!Constant.NULL.equals(selectedCustomID) && !SELECT_ONE.equals(selectedCustomID)
                        ? Integer.valueOf(selectedCustomID) : 0;
                getGenerateCall(false);
                setDisableFields();
                
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", alertMsg.getString("PV_MSG_ID_01"), ButtonId.OK);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Excel export button click method.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("excelBtn")
    public void excelBtn(Button.ClickEvent event) {
        excelBtnLogic();
    }

    /**
     * Graph button click listener.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("graphBtn")
    public void graphBtn(Button.ClickEvent event) {
        graphBtnLogic();
    }

    protected void loadCustomDDLB() {
        LOGGER.debug("projection variance loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        editViewBtn.setEnabled(false);
        addViewBtn.setEnabled(true);
        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            if(!sessionDTO.getCustomerViewList().isEmpty()){
              customViewList= sessionDTO.getCustomerViewList();
            }
            else{
            customViewList = CommonLogic.getCustomViewList(sessionDTO.getProjectionId());
           sessionDTO.setCustomerViewList(customViewList);
            }
            if (customViewList != null) {

                Object select = null;
                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    if (customIdToSelect == customSid) {
                        select = itemId;
                    }
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }
                if (select == null) {
                    levelDdlb.setEnabled(false);
                    customDdlb.setValue(SELECT_ONE);
                } else {
                    levelDdlb.setEnabled(true);
                    customDdlb.select(customIdToSelect);
                }
            }

        }
        LOGGER.debug("projection variance  loadCustomDDLB ends ");
    }

    protected void customTreeViewLogic() {
        LOGGER.debug("projection variance customTreeViewLogic initiated ");
        final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(Constant.ADD_FULL_SMALL, sessionDTO);
        customerTreeLookup.addCloseListener(new Window.CloseListener() {

            public void windowClose(Window.CloseEvent e) {
                if (customerTreeLookup.isIsSelect()) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                }
                sessionDTO.setCustomerViewList(CommonLogic.getCustomViewList(sessionDTO.getProjectionId()));
                loadCustomDDLB();

            }
        });
        getUI().addWindow(customerTreeLookup);
        LOGGER.debug("projection variance customTreeViewLogic ends ");
    }

    public void editHierarchyLogic() {
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(Constant.EDIT, sessionDTO, customId);
            customerTreeLookup.addCloseListener(new Window.CloseListener() {

                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                   sessionDTO.setCustomerViewList(CommonLogic.getCustomViewList(sessionDTO.getProjectionId()));
                    loadCustomDDLB();

                }
            });
            getUI().addWindow(customerTreeLookup);
        }
    }

    protected void loadLevelAndFilterValue() {
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelFilter.removeAllItems();
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);
        if (pvSelectionDTO.isIsCustomHierarchy()) {
            Utility.loadLevelValueForResult(levelDdlb, null, null, sessionDTO.getCustomHierarchyMap().get(customId), Constant.CUSTOM);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilter, null, sessionDTO.getCustomerHierarchyList(), view.getValue().toString());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilter, null, sessionDTO.getProductHierarchyList(), view.getValue().toString());
        }

        LOGGER.debug("loadLevelAndFilterValue ends ");
    }
    
    
    public void loadLevelFilter(){
        LOGGER.debug("loadLevelFilter initiated ");
        level.setEnabled(true);
        List<Leveldto> hierarchy = null;
       String view = StringUtils.EMPTY;
        if (pvSelectionDTO.isIsCustomHierarchy()) {
            view=Constant.CUSTOM;
           if(sessionDTO.getCustomHierarchyMap().containsKey(customId)){
            hierarchy = sessionDTO.getCustomHierarchyMap().get(customId);
            }
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = sessionDTO.getCustomerHierarchyList();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = sessionDTO.getProductHierarchyList();
        }
         Utility.loadLevelDDlbValue(levelDdlb, levelFilter, null, hierarchy, view);

        LOGGER.debug("loadLevelFilter ends ");
        }
    
    /**
     * Method to get Values
     *
     * @return String List
     */
    protected String getCheckedValues() {
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<String> result = new ArrayList<String>();
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    if (customMenuItem1.getMenuItem().getCaption().equals(Constant.PVVariables.CHECK_ALL.toString())) {
                        result.addAll(Arrays.asList(Constant.PVVariables.getCheckAllVariables()));
                        variablesValue = Arrays.toString(result.toArray());
                        return Arrays.toString(result.toArray());
                    }
                    result.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
            variablesValue = Arrays.toString(result.toArray());
            return Arrays.toString(result.toArray());
        }
        return StringUtils.EMPTY;
    }

    /**
     * Method to get checked Variable Category Values
     *
     * @return String List
     */
    protected String getCheckedVariableCategoryValues() {
        if (variableCategoryCustomMenuItem != null && variableCategoryCustomMenuItem.getSize() > 0) {
            List<String> results = new ArrayList<String>();
            List<CustomMenuBar.CustomMenuItem> items = variableCategoryCustomMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    results.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
            variableCategoryValue = Arrays.toString(results.toArray());
            return Arrays.toString(results.toArray());
        }
        return StringUtils.EMPTY;
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @UiHandler("fromDate")
    public void fromDateDdlbChange(Property.ValueChangeEvent event) {
        if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !"".equals(String.valueOf(fromDate.getValue()))
                && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            loadToPeriod(fromDate.getValue().toString());
        } else {
            fromDate.setValue(Constant.SELECT_ONE);
            loadToPeriod(Constant.SELECT_ONE);
        }
    }
    
    /**
     * load To DATE DDLB period
     *
     * @param fromDateVal
     * @param toDateVal
     */
    public void loadToPeriod(String fromDateVal) {
        Object toDateVal = toDate.getValue();
        toDate.removeAllItems();
        toDate.addItem(Constant.SELECT_ONE);
        int start = 0;
        Map<String, String> listMap = new HashMap<String, String>();
        List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
        if (String.valueOf(pvSelectionDTO.getProjectionPeriodOrder()).equals("Descending") && CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)) {
            Collections.reverse(periodList);
        }
        for (int i = 0; i < periodList.size(); i++) {
            periodList.set(i, String.valueOf(periodList.get(i)).toLowerCase());
        }
        for (Map.Entry<String, String> entry : pvSelectionDTO.getPeriodListMap().entrySet()) {
            listMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !"".equals(String.valueOf(fromDate.getValue()))
                && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue())) && !fromDateVal.equals(Constant.SELECT_ONE)) {
            String fromVal = fromDateVal.replace(" ", "");
            fromVal = fromVal.toLowerCase();
            start = periodList.indexOf(fromVal);
        }
        int end = periodList.size() - 1;
        for (int i = start; i <= end; i++) {
            toDate.addItem(listMap.get(periodList.get(i)));
        }
        toDate.setValue(toDateVal);
    }
    
     public void loadFromPeriod(String fromDateVal) {
         fromDate.removeAllItems();
         fromDate.addItem(Constant.SELECT_ONE);
         Map<String, String> listMap = new HashMap<String, String>();
         List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
         if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")&& CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)) {
             Collections.reverse(periodList);
         }
         for (int i = 0; i < periodList.size(); i++) {
             periodList.set(i, String.valueOf(periodList.get(i)).toLowerCase());
         }
         for (Map.Entry<String, String> entry : pvSelectionDTO.getPeriodListMap().entrySet()) {
             listMap.put(entry.getKey().toLowerCase(), entry.getValue());
         }
         if (!periodList.isEmpty()) {
             for (int i = 0; i < periodList.size(); i++) {
                 String header = listMap.get(periodList.get(i));
                 fromDate.addItem(header);
             }
         }
         fromDate.setValue(fromDateVal);
    }
    
    protected abstract void expandCollapseLevelOption(boolean isExpand, Object value);

    protected abstract void callTableLogic(int levelNo, String hierarchyNo, final boolean excelFlag);

    protected abstract void comparisonLookupLogic();

    protected abstract void getGenerateCall(boolean flag);

    protected abstract void setDisableFields();

    protected abstract void viewChange(boolean flag);

    protected abstract void customDdlbChangeOption();

    protected abstract void loadFrequency();

    protected abstract void resetBtnLogic();

    protected abstract void excelBtnLogic();

    protected abstract void graphBtnLogic();
}
