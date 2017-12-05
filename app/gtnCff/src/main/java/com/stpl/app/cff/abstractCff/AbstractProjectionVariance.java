
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import com.stpl.app.cff.dto.CustomTreeBuild;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.lazyLoad.VarianceTableLogic;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.projectionVariance.logic.ProjectionVarianceLogic;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.Constants.LabelConstants;
import static com.stpl.app.cff.util.Constants.LabelConstants.CUSTOM;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.ConstantUtil.SELECT_ONE;
import com.stpl.ifs.util.CustomTableHeaderDTO;
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
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Shrihariharan
 */
public abstract class AbstractProjectionVariance extends CustomComponent implements View {

     /**
     * varianceSelection VerticalLayout.
     */
    @UiField("varianceSelection")
    protected VerticalLayout varianceSelection;
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
     * Display Format Custom menu bar
     */
    @UiField("displayFormatDdlb")
    protected CustomMenuBar displayFormatDdlb;
    
    protected CustomMenuBar.CustomMenuItem displayFormatValues;
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
    
    /**
     * The customerlevelDdlb Button.
     */
    @UiField("customerlevelDdlb")
    protected ComboBox customerlevelDdlb;
    /**
     * The customerlevelDdlb Button.
     */
    @UiField("productlevelDdlb")
    protected ComboBox productlevelDdlb;
    /**
     * The deductionlevelDdlb Button.
     */
    @UiField("deductionlevelDdlb")
    protected ComboBox deductionlevelDdlb;
    /**
     * The deductionInclusionDdlb Button.
     */
    @UiField("deductionInclusionDdlb")
    protected CustomMenuBar deductionInclusionDdlb;
    
        /**
     * The salesInclusionDdlb Button.
     */
    @UiField("salesInclusionDdlb")
    protected CustomMenuBar salesInclusionDdlb;
    /**
     * The customerFilterDdlb Button.
     */
    @UiField("customerFilterDdlb")
    protected CustomMenuBar customerFilterDdlb;
    /**
     * The productFilterDdlb Button.
     */
    @UiField("productFilterDdlb")
    protected CustomMenuBar productFilterDdlb;
    /**
     * The deductionFilterDdlb Button.
     */
    @UiField("deductionFilterDdlb")
    protected CustomMenuBar deductionFilterDdlb;
  /**
     * The TabSheet.
     */
    @UiField("tabsheetVariance")
    protected TabSheet tabsheetVariance;
    
 
    
    @UiField("projectionVariancefilterLayout")
    protected HorizontalLayout projectionVariancefilterLayout;
    
    @UiField("conversionFactorDdlb")
    protected ComboBox conversionFactorDdlb;
    
    /**
     * The UOMDdlb Button.
     */
    @UiField("uomDdlb")
    protected ComboBox uomDdlb;
    private final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    /**
     * Screen Name
     */
    protected String screenName;
    protected int customIdToSelect = 0;
    protected boolean generated = false;
    protected int customId = 0;
    /**
     * Logger for ForecastProjectionVariance
     */
    public static final Logger LOGGER = Logger.getLogger(AbstractProjectionVariance.class);
    /**
     * Table logic class instance
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
    protected ExtTreeContainer<ProjectionVarianceDTO> resultExcelContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * Table logic class instance
     */
    protected VarianceTableLogic tableLogic = new VarianceTableLogic();
    /**
     * Table
     */
    protected FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
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
    protected ExtTreeContainer<ProjectionVarianceDTO> resultBeanContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    protected List<CffCustomViewMaster> customViewList = new ArrayList<>();
    /**
     * Property file for alert message
     */
    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };
    protected String variablesValue = StringUtils.EMPTY;
    protected String variableCategoryValue = StringUtils.EMPTY;
    protected PVSelectionDTO pvSelectionDTO = new PVSelectionDTO();
    protected SessionDTO sessionDTO;
    protected ProjectionVarianceLogic logic = new ProjectionVarianceLogic();
    protected CustomMenuBar.CustomMenuItem deductionInclusionValues;
    protected CustomMenuBar.CustomMenuItem salesInclusionValues;
    protected CustomMenuBar.CustomMenuItem customerFilterValues;
    protected CustomMenuBar.CustomMenuItem productFilterValues;
    protected CustomMenuBar.CustomMenuItem deductionFilterValues;

    public AbstractProjectionVariance(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/cff/tabs/ProjectionVarianceTab.xml"), this));
        groupLabel.setVisible(false);
        group.setVisible(false);
        Panel panel = new Panel();
        panel.setContent(layout);
        setCompositionRoot(panel);
        configureFilds();

    }

    public void configureFilds() {
        tabsheetVariance.addTab(varianceSelection, "Display Selection");
        tabsheetVariance.addTab(projectionVariancefilterLayout, "Filter Option");
        tabsheetVariance.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheetVariance.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        
        comparison.addStyleName(ConstantsUtils.SEARCH_ICON);
        comparison.setReadOnly(false);
        comparison.setValue(SELECT_ONE);

        level.addItem(LabelConstants.TOTAL.toString());
        level.addItem(LabelConstants.DETAIL.toString());
        level.select(LabelConstants.TOTAL.toString());
        level.addStyleName(ConstantsUtils.HORIZONTAL);
        level.addStyleName(Constants.OPTION_GROUP_WIDTH);


        discountLevel.addItem("Total Discount");
        discountLevel.addItem("Program Category");
        discountLevel.addItem("Program");
        discountLevel.select("Total Discount");
        discountLevel.addStyleName(ConstantsUtils.HORIZONTAL);
        projectionPeriodOrder.addItem(Constants.CommonConstantsForChannels.ASCENDING.toString());
        projectionPeriodOrder.addItem(Constants.CommonConstantsForChannels.DESCENDING.toString());
        projectionPeriodOrder.select(Constants.CommonConstantsForChannels.ASCENDING.toString());
        projectionPeriodOrder.addStyleName(ConstantsUtils.HORIZONTAL);
        projectionPeriodOrder.addStyleName(Constants.OPTION_GROUP_WIDTH);

        fromDate.addItem(SELECT_ONE);
        fromDate.setNullSelectionItemId(SELECT_ONE);
        fromDate.select(SELECT_ONE);

        toDate.addItem(SELECT_ONE);
        toDate.setNullSelectionItemId(SELECT_ONE);
        toDate.select(SELECT_ONE);
        
        
        comparisonBasis.addItem("Actuals");
        comparisonBasis.addItem("Current Projection");
        if (CommonUtils.isValueEligibleForLoading()) {
            comparisonBasis.addItem("Accruals");
        }
        comparisonBasis.select("Current Projection");

        view.addStyleName(ConstantsUtils.HORIZONTAL);
        view.addItem(Constants.CommonConstantsForChannels.CUSTOMER.toString());
        view.addItem(Constants.PRODUCT_LABEL);
        view.addItem(Constants.LabelConstants.CUSTOM.toString());
        view.setValue(Constants.CommonConstantsForChannels.CUSTOMER.toString());

        pivotView.addItem(Constants.PERIOD);
        pivotView.addItem(Constants.VARIABLE_LABEL);
        pivotView.select(Constants.PERIOD);
        pivotView.addStyleName(ConstantsUtils.HORIZONTAL);
        pivotView.addStyleName(Constants.OPTION_GROUP_WIDTH);

        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.select(SELECT_ONE);
        levelDdlb.addStyleName(Constants.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilter.addStyleName(Constants.POPUPCONTENTCOMBOSIZE);
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
        
        levelDdlb.setEnabled(false);
        expandLvlBtn.setEnabled(false);
        collapseLvlBtn.setEnabled(false);
        levelFilter.setEnabled(false);
        view.setEnabled(false);
        
        level.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                setDisable();
            }
        });

        initializeResultTable();
        addResultTable();
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
        tableVerticalLayout.addComponent(excelTable);
        resultExcelContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
        resultExcelContainer.setColumnProperties(fullHeader.getProperties());
        excelTable.setContainerDataSource(resultExcelContainer);

        excelTable.setVisible(false);
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    protected void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        controlLayout = CommonLogic.getResponsiveControls(controls);
        tableVerticalLayout.addComponent(controlLayout);
    }

    @UiHandler("comparison")
    public void comparisonLookupListener(CustomTextField.ClickEvent event) {
        LOGGER.debug("comparisonLookupListener ValueChangeEvent initiated ");
        comparisonLookupLogic();
        LOGGER.debug("comparisonLookupListener ValueChangeEvent initiated ");
    }

    @UiHandler("expandBtn")
    public void expandBtnLogic(Button.ClickEvent event) {
        // Implement
        try {
            expandCollapseLevelOption(true, levelDdlb.getValue());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("collapseBtn")
    public void collapseBtnLogic(Button.ClickEvent event) {
        // Implement
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

    /**
     * Excel export button click method.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelBtn(Button.ClickEvent event) {
        excelBtnLogic();
    }

    /**
     * Graph button click listener.
     *
     * @param event the event
     */
    @UiHandler("graphBtn")
    public void graphBtn(Button.ClickEvent event) {
        graphBtnLogic();
    }

    @UiHandler("generateBtn")
    public void generate(Button.ClickEvent event) {
        try {

            LOGGER.debug("------ Inside generate security Projection Variance Tab and generate Button");

            ProjectionVarianceLogic.waitForPPAProcedure();

            if (frequency.getValue() != null && !"null".equals(String.valueOf(frequency.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(frequency.getValue()))) {
                getGenerateCall(false);
                setDisableFields();
            } else {
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e);
        }
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

    protected String getCheckedValues() {
        List<String> returnList = loadVariablesDdlb();
        String[] variableValues = returnList.toArray(new String[0]);
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<String> result = new ArrayList<>();
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    if (customMenuItem1.getMenuItem().getCaption().equals(Constants.PVVariables.CHECK_ALL.toString())) {
                        result.addAll(Arrays.asList(Arrays.toString(ArrayUtils.removeElement(variableValues, Constants.PVVariables.CHECK_ALL.toString())).replaceAll("^.|.$", "").split(",")));
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

    

    protected void customTreeViewLogic() {
        LOGGER.debug("projection variance customTreeViewLogic initiated ");
        final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(sessionDTO);
        customerTreeLookup.addCloseListener(new Window.CloseListener() {
            public void windowClose(Window.CloseEvent e) {
                if (customerTreeLookup.isIsSelect()) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                }
                loadCustomDDLB();

            }
        });
        getUI().addWindow(customerTreeLookup);
        LOGGER.debug("projection variance customTreeViewLogic ends ");
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
            customViewList = CommonLogic.getCustomViewList(sessionDTO.getProjectionId());
            if (customViewList != null) {

                Object select = null;
                for (CffCustomViewMaster obj : customViewList) {
                    int customSid = obj.getCffCustomViewMasterSid();
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

    public void editHierarchyLogic() {
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customerTreeLookup = new CustomTreeBuild(sessionDTO, customId);
            customerTreeLookup.addCloseListener(new Window.CloseListener() {
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customerTreeLookup.getCustomId();
                    loadCustomDDLB();

                }
            });
            getUI().addWindow(customerTreeLookup);
        }
    }
    
    protected void setDisable() {
        if ("Total".equalsIgnoreCase(String.valueOf(level.getValue()))) {
            levelDdlb.setEnabled(false);
            expandLvlBtn.setEnabled(false);
            collapseLvlBtn.setEnabled(false);
            levelFilter.setEnabled(false);
            view.setEnabled(false);
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                customDdlb.setEnabled(false);
                editViewBtn.setEnabled(false);
                addViewBtn.setEnabled(false);
            }
        } else {
            levelDdlb.setEnabled(true);
            expandLvlBtn.setEnabled(true);
            collapseLvlBtn.setEnabled(true);
            levelFilter.setEnabled(true);
            view.setEnabled(true);
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                customDdlb.setEnabled(true);
                addViewBtn.setEnabled(true);
            }
        }
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
        List<String> outputList = new ArrayList<>();
        List<String> variablesList = new ArrayList<>();
            String query;
            List<String> returnList;
            Map<String, List<String>> input = new HashMap<>();
            List<String> defaultNames = Arrays.asList("01.Check All", "14.Contract Sales @ WAC", "15.Contract Units", "16.Discount $", "17.Discount %", "18.RPU", "20.Net Sales", "24.COGS", "25.Net Profit");
            List<String> ExfactNames = Arrays.asList("02.Ex-Factory Product", "03.Ex-Factory Customer", "08.% Of Ex-Factory Product", "09.% Of Ex-Factory Customer", "21.Net Sales % of Ex-Factory", "22.Net Ex-Factory Sales", "23.Net Ex-Factory Sales as % of Ex-Factory Sales", "19.Discount % of Ex-Factory");
            List<String> DemandNames = Arrays.asList("04.Demand", "10.% Of Demand");
            List<String> InventoryNames = Arrays.asList("12.Inventory Withdrawal Summary", "13.Inventory Withdrawal Details", "09.% Of Inventory Withdrawal Summary", "10.% Of Inventory Withdrawal Details");
            List<String> AdjDemandNames = Arrays.asList("05.Adjusted Demand", "11.% Of Adjusted Demand");
            input.put("Ex-Factory Sales", ExfactNames);
            input.put("Demand", DemandNames);
            input.put("Inventory Withdrawal - Forecast Detail", InventoryNames);
            input.put("Inventory Withdrawal - Forecast Summary", InventoryNames);
            input.put("Adjusted Demand", AdjDemandNames);
            query = SQlUtil.getQuery("get-file-type-query");
            returnList = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
            outputList.addAll(defaultNames);
            for (String string : returnList) {
                if (!"Customer Sales".equals(string)) {
                    outputList.addAll(input.get(string));
                }
            }
            Collections.sort(outputList);
            for (String str : outputList) {
                variablesList.add(str.split("\\.")[1]);
            }
        return variablesList;
    }
}
