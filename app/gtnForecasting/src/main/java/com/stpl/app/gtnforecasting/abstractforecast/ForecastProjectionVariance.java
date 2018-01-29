/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.OR_DOLLAR;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.ConstantUtil.SELECT_ONE;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
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
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    protected OptionGroup variableCategory;
    /**
     * The variable category.
     */
    @UiField("variableCategoryCustomMenuBar")
    protected CustomMenuBar variableCategoryCustomMenuBar;
    
    protected CustomMenuBar.CustomMenuItem variableCategoryCustomMenuItem;
    /**
     * The variables.
     */
    protected OptionGroup variables = new OptionGroup();

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
    
    @UiField("UomLb")
    protected Label uomLb;
    
    @UiField("displaySelectionLayout")
    protected VerticalLayout displaySelectionLayout;
    
    @UiField("filterOptionLayout")
    protected VerticalLayout filterOptionLayout;
    
    @UiField("UomDdlb")
    protected ComboBox uomDdlb;
    
    @UiField("tabsheetVariance")
    protected TabSheet tabsheetVariance;
    
    @UiField("panelVariance")
    protected Panel panelVariance;
    
    @UiField("customerlevelDdlb")
    protected ComboBox customerlevelDdlb;
    
    @UiField("productlevelDdlb")
    protected ComboBox productlevelDdlb;
    
    @UiField("deductionlevelDdlb")
    protected ComboBox deductionlevelDdlb;
    
    @UiField("deductionInclusionDdlb")
    protected CustomMenuBar deductionInclusionDdlb;
    
    @UiField("salesInclusionDdlb")
    protected CustomMenuBar salesInclusionDdlb;
    
    @UiField("customerFilterDdlb")
    protected CustomMenuBar customerFilterDdlb;
    
    @UiField("productFilterDdlb")
    protected CustomMenuBar productFilterDdlb;
    
    @UiField("deductionFilterDdlb")
    protected CustomMenuBar deductionFilterDdlb;
    
     @UiField("conversionFactorDdlb")
    protected ComboBox conversionFactorDdlb;
    
    protected CustomMenuBar.CustomMenuItem deductionInclusionValues;
    protected CustomMenuBar.CustomMenuItem salesInclusionValues;
    protected CustomMenuBar.CustomMenuItem customerFilterValues;
    protected CustomMenuBar.CustomMenuItem productFilterValues;
    protected CustomMenuBar.CustomMenuItem deductionFilterValues;
    protected static final String VARIANCE = "Variance";

    protected final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    /**
     * Display Format Custom menu bar
     */
    @UiField("displayFormatDdlb")
    protected CustomMenuBar displayFormatDdlb;
    
    protected CustomMenuBar.CustomMenuItem displayFormatValues;
    /**
     * Session DTO
     */
    private SessionDTO sessionDTO;
    /**
     * Screen Name
     */
    protected String screenName;
    protected PVSelectionDTO pvSelectionDTO = new PVSelectionDTO();
    protected int customIdToSelect = 0;
    protected boolean generated = false;
    protected List<CustomViewMaster> customViewList = new ArrayList<>();
    protected int customId = 0;
    /**
     * Logger for ForecastProjectionVariance
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastProjectionVariance.class);
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
    protected ExtTreeContainer<ProjectionVarianceDTO> resultBeanContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
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
    protected HorizontalLayout controlLayout;
    /**
     * Property file for alert message
     */
    protected static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    
    protected List<Object> generateDiscountToBeLoaded = new ArrayList<>();
    protected List<Object> generateDiscountNamesToBeLoaded = new ArrayList<>();
    protected List<Object> generateProductToBeLoaded = new ArrayList<>();
    protected List<Object> generateCustomerToBeLoaded = new ArrayList<>();
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
        this.setSessionDTO(session);
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
 
        if (CommonUtil.isValueEligibleForLoading()) {
            panelVariance.setVisible(false);
            tabsheetVariance.addTab(displaySelectionLayout, "Display Selection");
            tabsheetVariance.addTab(filterOptionLayout, "Filter Options");
            tabsheetVariance.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabsheetVariance.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        } else {
            filterOptionLayout.setVisible(false);
            uomLb.setVisible(false);
            uomDdlb.setVisible(false);
        }

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
        
        comparisonBasis.addItem(Constant.ACTUALS);
        comparisonBasis.addItem("Current Projection");
        if(CommonUtil.isValueEligibleForLoading()){
            comparisonBasis.addItem(Constant.ACCRUALS);
        }
        comparisonBasis.select("Current Projection");

        view.addStyleName(Constant.HORIZONTAL);
        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT_LABEL);
        view.addItem(Constant.CUSTOM_LABEL);
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
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public void configureExcelTable() {
        resultExcelContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
        resultExcelContainer.setColumnProperties(leftHeader.getProperties());
        resultExcelContainer.setColumnProperties(rightHeader.getProperties());
        excelTable.setContainerDataSource(resultExcelContainer);
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
            LOGGER.error(ex.getMessage());
        }
    }

    @UiHandler("collapseBtn")
    public void collapseBtnLogic(Button.ClickEvent event) {
      
        try {
            expandCollapseLevelOption(false, levelDdlb.getValue());
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

    /**
     * new the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        getSessionDTO().setTabName(VARIANCE);
        customTreeViewLogic();
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        getSessionDTO().setTabName(VARIANCE);
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
    public void generate(Button.ClickEvent event)  {
        try {

            LOGGER.debug("------ Inside generate security Projection Variance Tab and generate Button");
       
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                Utility.getTradingPartnerLevelNo(getSessionDTO().getProjectionId(),getSessionDTO());
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
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
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
            if(!getSessionDTO().getCustomerViewList().isEmpty()){
              customViewList= getSessionDTO().getCustomerViewList();
            }
            else{
            customViewList = CommonLogic.getCustomViewList(getSessionDTO().getProjectionId());
           getSessionDTO().setCustomerViewList(customViewList);
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
        final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(getSessionDTO());
        customerTreeLookup.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customerTreeLookup.isIsSelect()) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                }
                getSessionDTO().setCustomerViewList(CommonLogic.getCustomViewList(getSessionDTO().getProjectionId()));
                loadCustomDDLB();

            }
        });
        getUI().addWindow(customerTreeLookup);
        LOGGER.debug("projection variance customTreeViewLogic ends ");
    }

    public void editHierarchyLogic() {
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(getSessionDTO(), customId);
            customerTreeLookup.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                   getSessionDTO().setCustomerViewList(CommonLogic.getCustomViewList(getSessionDTO().getProjectionId()));
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
            Utility.loadLevelValueForResult(levelDdlb, null, null, getSessionDTO().getCustomHierarchyMap().get(customId), Constant.CUSTOM_LABEL);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilter, null, getSessionDTO().getCustomerHierarchyList(), view.getValue().toString());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilter, null, getSessionDTO().getProductHierarchyList(), view.getValue().toString());
        }

        LOGGER.debug("loadLevelAndFilterValue ends ");
    }
    
    
    public void loadLevelFilter(){
        LOGGER.debug("loadLevelFilter initiated ");
        level.setEnabled(true);
        List<Leveldto> hierarchy = null;
               if (pvSelectionDTO.isIsCustomHierarchy()) {
                  if(getSessionDTO().getCustomHierarchyMap().containsKey(customId)){
            hierarchy = getSessionDTO().getCustomHierarchyMap().get(customId);
            }
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = getSessionDTO().getCustomerHierarchyList();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = getSessionDTO().getProductHierarchyList();
        }
         Utility.loadLevelDDlbValue(levelDdlb, levelFilter, hierarchy);

        LOGGER.debug("loadLevelFilter ends ");
        }
    
    /**
     * Method to get Values
     *
     * @return String List
     */
    protected String getCheckedValues() {
        List<String> returnList = loadVariablesDdlb();
        String[] variableValues = returnList.toArray(new String[0]);
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<String> result = new ArrayList<>();
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    if (customMenuItem1.getMenuItem().getCaption().equals(Constant.PVVariables.CHECK_ALL.toString())) {
                        result.addAll(Arrays.asList(Arrays.toString(ArrayUtils.removeElement(variableValues, Constant.PVVariables.CHECK_ALL.toString())).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",")));
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
            List<String> results = new ArrayList<>();
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
        Map<String, String> listMap = new HashMap<>();
        List<String> periodList = new ArrayList<>(pvSelectionDTO.getPeriodHeaderList());
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
         Map<String, String> listMap = new HashMap<>();
         List<String> periodList = new ArrayList<>(pvSelectionDTO.getPeriodHeaderList());
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

    public List<String> loadVariablesDdlb() {
        Set<String> outputSet = new TreeSet();
        List<String> variablesList = new ArrayList<>();
            String query;
            List<String> returnList = new ArrayList<>();
            Map<String, List<String>> input = new HashMap<>();
            List<String> defaultNames = Arrays.asList("01.Check All", "08.Contract Sales @ WAC", "09.Contract Units", "10.Discount $", "11.Discount %", "12.RPU", "14.Net Sales", "18.COGS", "19.Net Profit");
            List<String> exfactNames = Arrays.asList("02.Ex-Factory Sales", "04.Contract Sales % Of Ex-Factory", "13.Discount % of Ex-Factory", "15.Net Sales % of Ex-Factory", "16.Net Ex-Factory Sales", "17.Net Ex-Factory Sales as % of Ex-Factory Sales");
            List<String> demandNames = Arrays.asList("03.Demand Sales", "05.% Of Demand");
            List<String> inventoryNames = Arrays.asList("06.Inventory Withdrawal Sales", "07.% Of Inventory Withdrawal Sales");
            input.put("Ex-Factory Sales", exfactNames);
            input.put("Demand", demandNames);
            input.put("Inventory Withdrawal - Forecast Detail", inventoryNames);
            input.put("Inventory Withdrawal - Forecast Summary", inventoryNames);
            query = SQlUtil.getQuery("get-file-type-query");
            returnList = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
            outputSet.addAll(defaultNames);
            for (String string : returnList) {
                if (!"Customer Sales".equals(string) && !"Adjusted Demand".equals(string)) {
                    outputSet.addAll(input.get(string));
                }
            }
            for (String str : outputSet) {
                variablesList.add(str.split("\\.")[1]);
            }
        return variablesList;
    }

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}
}
