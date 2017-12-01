/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionresults.logic.tablelogic.ProjectionResultsTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CalculatePeriods.CALCULATE;
import static com.stpl.app.gtnforecasting.utils.Constant.CommonConstants.*;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.*;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.*;
import static com.stpl.app.gtnforecasting.utils.Constant.ResourceConstants.*;
import static com.stpl.app.gtnforecasting.utils.Constant.SessionConstants.*;
import com.stpl.app.gtnforecasting.utils.NmSPRGraphWindow;
import com.stpl.app.gtnforecasting.utils.PRChart;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.ExtCustomTreeTable;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public abstract class ForecastProjectionResults extends CustomComponent {

    private final static Logger LOGGER = Logger.getLogger(ForecastProjectionResults.class);

    @UiField("tablePanel")
    protected Panel tablePanel;

    @UiField("groupDdlbLabel")
    protected Label groupDdlbLabel;

    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;

    @UiField("historyDdlb")
    protected ComboBox historyDdlb;

    @UiField("levelDdlb")
    protected ComboBox levelDdlb;

    @UiField("groupDdlb")
    protected ComboBox groupDdlb;

    @UiField("customDdlb")
    protected ComboBox customDdlb;

    @UiField("levelFilterDdlb")
    protected ComboBox levelFilterDdlb;

    @UiField("periodOrderOpg")
    protected OptionGroup periodOrderOpg;

    @UiField("salesOrUnitsOpg")
    protected OptionGroup salesOrUnitsOpg;

    @UiField("actualOrProjectionsOpg")
    protected OptionGroup actualOrProjectionsOpg;

    @UiField("pivotViewOpg")
    protected OptionGroup pivotViewOpg;

    @UiField("viewOpg")
    protected OptionGroup viewOpg;

    @UiField("generateBtn")
    protected Button generateBtn;

    @UiField("resetBtn")
    protected Button resetBtn;

    @UiField("expandBtn")
    protected Button expandBtn;

    @UiField("collapseBtn")
    protected Button collapseBtn;

    @UiField("newBtn")
    protected Button newBtn;

    @UiField("editBtn")
    protected Button editBtn;

    @UiField("excelBtn")
    protected Button excelBtn;

    @UiField("graphBtn")
    protected Button graphBtn;

    @UiField("tableVerticalLayout")
    protected VerticalLayout tableVerticalLayout;

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

    protected ExtTreeContainer<ProjectionResultsDTO> resultBeanContainer, excelResultBean = new ExtTreeContainer<>(ProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
    protected ProjectionResultsTableLogic tableLogic = new ProjectionResultsTableLogic();
    protected FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    protected boolean generated, firstGenerated = false;
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    protected HorizontalLayout controlLayout;
    protected ProjectionSelectionDTO projectionSelectionDTO = new ProjectionSelectionDTO();
    protected SessionDTO sessionDTO;
    protected String screenName;
    protected int projectionId = 0, customId = 0, customIdToSelect = 0;
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    final private BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
    protected CustomTableHeaderDTO leftHeader, rightHeader, fullHeader = new CustomTableHeaderDTO();
    protected List<CustomViewMaster> customViewList = new ArrayList<>();
    protected final float maxSplitPosition = 1000, minSplitPosition = 200, splitPosition = 300;
    protected ExtCustomTreeTable exceltable;
    int tradingPartnerNo = 0;
    boolean isTabVisible = true;

    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };

    /**
     * Abstract Class constructor for Projection Results.
     *
     * @param sessionDTO
     * @param screenName
     */
    public ForecastProjectionResults(final SessionDTO sessionDTO, final String screenName) throws SystemException, PortalException {
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        projectionSelectionDTO.setSessionDTO(sessionDTO);
        projectionSelectionDTO.setScreenName(screenName);
        projectionSelectionDTO.setTabName(Constant.PROJECTION_RESULTS_LABEL);
        projectionId = sessionDTO.getProjectionId();
        init();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            groupDdlbLabel.setVisible(false);
            groupDdlb.setVisible(false);
        }
        configureProjectionDTO();
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(sessionDTO.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(sessionDTO.getAction())) {
            loadOnEdit();
        }

    }

    /**
     * Init method that reads the UI components from xml and configures the
     * Projection Results Screen.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractforecast/forecast-projection-results.xml"), this));
        configureFields();
    }

    private void configureFields() {
        frequencyDdlb.addItem(SELECT_ONE.getConstant());
        frequencyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequencyDdlb.addItem(ANNUALLY.getConstant());
        frequencyDdlb.addItem(SEMI_ANNUALLY.getConstant());
        frequencyDdlb.addItem(QUARTERLY.getConstant());
        frequencyDdlb.addItem(MONTHLY.getConstant());
        frequencyDdlb.setValue(QUARTERLY.getConstant());

        historyDdlb.setImmediate(true);

        salesOrUnitsOpg.addStyleName(Constant.HORIZONTAL);
        salesOrUnitsOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        salesOrUnitsOpg.addItem(SALES.getConstant());
        salesOrUnitsOpg.addItem(UNITS.getConstant());
        salesOrUnitsOpg.addItem(BOTH.getConstant());
        salesOrUnitsOpg.setValue(SALES.getConstant());
        salesOrUnitsOpg.setImmediate(true);

        periodOrderOpg.setImmediate(true);
        periodOrderOpg.addStyleName(Constant.HORIZONTAL);
        periodOrderOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        periodOrderOpg.addItem(ASCENDING.getConstant());
        periodOrderOpg.addItem(Constant.DESCENDING);
        periodOrderOpg.setValue(ASCENDING.getConstant());
        actualOrProjectionsOpg.setImmediate(true);
        actualOrProjectionsOpg.addStyleName(Constant.HORIZONTAL);
        actualOrProjectionsOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        actualOrProjectionsOpg.addItem(Constant.ACTUALS);
        actualOrProjectionsOpg.addItem(Constant.PROJECTIONS);
        actualOrProjectionsOpg.addItem(Constant.BOTH);
        actualOrProjectionsOpg.setValue(Constant.ACTUALS);
        pivotViewOpg.setImmediate(true);
        pivotViewOpg.addStyleName(Constant.HORIZONTAL);
        pivotViewOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        pivotViewOpg.addItem(Constant.PERIOD);
        pivotViewOpg.addItem(Constant.VARIABLE);
        pivotViewOpg.setValue(Constant.PERIOD);
        viewOpg.addStyleName("forecast-tab");
        viewOpg.addStyleName(Constant.HORIZONTAL);
        viewOpg.addItem(Constant.CUSTOMER_SMALL);
        viewOpg.addItem(Constant.PRODUCT_LABEL);
        viewOpg.addItem(Constant.CUSTOM_LABEL);
        viewOpg.setValue(Constant.CUSTOMER_SMALL);

        levelDdlb.addItem(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setValue(SELECT_ONE.getConstant());
        levelDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilterDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilterDdlb.setImmediate(true);
        levelFilterDdlb.addItem(SELECT_ONE.getConstant());
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelFilterDdlb.setValue(SELECT_ONE.getConstant());
        customDdlb.addItem(SELECT_ONE.getConstant());
        customDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        customDdlb.setValue(SELECT_ONE.getConstant());
        editBtn.setEnabled(false);
        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        viewOpg.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                viewChange(true);
            }
        });

    }

    @UiHandler("frequencyDdlb")
    public void loadFrequency(Property.ValueChangeEvent event) {
        LOGGER.debug("Loading Frequency Combo Box");
        loadFrequencyDdlb();
        LOGGER.debug("Frequency Combo Box Loaded");
    }

    private void configureProjectionDTO() {
        projectionSelectionDTO.setScreenName(screenName);
        projectionSelectionDTO.setCustRelationshipBuilderSid(projectionSelectionDTO.getSessionDTO().getCustRelationshipBuilderSid());
        projectionSelectionDTO.setProdRelationshipBuilderSid(projectionSelectionDTO.getSessionDTO().getProdRelationshipBuilderSid());
        projectionSelectionDTO.setCustomerLevelNo(StringUtils.isBlank(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber()) || Constant.NULL.equals(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber())
                ? 1 : Integer.valueOf(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionSelectionDTO.setProductLevelNo(StringUtils.isBlank(projectionSelectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionSelectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.valueOf(projectionSelectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionSelectionDTO.setProjectionId(projectionSelectionDTO.getSessionDTO().getProjectionId());
        projectionSelectionDTO.setUserId(Integer.valueOf(projectionSelectionDTO.getSessionDTO().getUserId()));
        projectionSelectionDTO.setSessionId(Integer.valueOf(projectionSelectionDTO.getSessionDTO().getSessionId()));
        projectionSelectionDTO.setFrequency(String.valueOf(frequencyDdlb.getValue()));
        projectionSelectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
        projectionSelectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));
    }

    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) {
        tradingPartnerNo = Utility.getTradingPartnerLevelNo(projectionId, sessionDTO);
        generateButtonLogic();
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        resetButtonLogic();
    }

    public int getTabNumber() {
        return Constant.SEVEN;
    }

    protected void resetButtonLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {
                frequencyDdlb.setValue(QUARTERLY.getConstant());
                historyDdlb.setValue(Constant.FOUR_QUARTERS);
                salesOrUnitsOpg.setValue(SALES.getConstant());
                actualOrProjectionsOpg.setValue(ACTUALS.getConstant());
                periodOrderOpg.setValue(ASCENDING.getConstant());
                pivotViewOpg.setValue(PERIOD.getConstant());
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");
    }

    protected void loadFrequencyDdlb() {
        LOGGER.debug("Loading Frequency Combo Box Initiated");
        if (frequencyDdlb.getValue() == null) {
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE.getConstant());
            historyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        } else {
            String historyConstant;
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE.getConstant());
            historyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
            String frequency = String.valueOf(frequencyDdlb.getValue());
            switch (frequency) {
                case Constant.ANNUALLY:
                    historyBean.addAll(loadHistoryDdlb(Constant.ANNUALLY, YEAR.getConstant()));
                    historyConstant = "1 Year";
                    break;
                case Constant.SEMIANNUALLY:
                    historyBean.addAll(loadHistoryDdlb(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant()));
                    historyConstant = "2 Semi-Annual";
                    break;
                case Constant.QUARTERLY:
                    historyBean.addAll(loadHistoryDdlb(QUARTERLY.getConstant(), QUARTERS.getConstant()));
                    historyConstant = Constant.FOUR_QUARTERS;
                    break;
                case Constant.MONTHLY:
                    historyBean.addAll(loadHistoryDdlb(MONTHLY.getConstant(), MONTHS.getConstant()));
                    historyConstant = "12 Months";
                    break;
                default:
                    historyBean.addAll(loadHistoryDdlb(QUARTERLY.getConstant(), QUARTERS.getConstant()));
                    historyConstant = Constant.FOUR_QUARTERS;
                    break;
            }
            historyDdlb.setContainerDataSource(historyBean);
            historyDdlb.setValue(historyConstant);
        }
        LOGGER.debug("Loading Frequency Combo Box Ended");
    }

    protected final List<String> loadHistoryDdlb(String frequency, String period) {
        LOGGER.debug("Loading Frequency Combo Box Initiated");
        List<String> history = new ArrayList<>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            endValue = CALCULATE.getAnnualCount();
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getSemiAnnualCount();
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getQuarterCount();
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getMonthCount();
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(freq) || MONTHS
                    .getConstant().equals(freq) || YEARS.getConstant().equals(freq))) {
                period = freq.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + period);
            } else {
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            }
        }
        LOGGER.debug("Loading History Combo Box Ended");
        return history;

    }

    /**
     * Initializes the Table,Sets the split positions for the split panel and
     * also adds the required styles to the table.
     */
    protected void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        periodTableId.addStyleName(Constant.CENTER_CHECK);
    }

    /**
     * Configures the pagination layout for the result table.
     */
    protected void addResultTable() {
        tableVerticalLayout.addComponent(periodTableId);
        HorizontalLayout controls = tableLogic.createControls();
        controlLayout = CommonLogic.getResponsiveControls(controls);
        tableVerticalLayout.addComponent(controlLayout);
    }

    /**
     * Loads the level drop down in Commercial, Government and Other Forecast
     * based on the view
     */
    protected void loadLevelAndFilterValue() {
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        levelDdlb.setEnabled(true);
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelFilterDdlb.setEnabled(true);
        levelFilterDdlb.removeAllItems();
        levelFilterDdlb.addItem(SELECT_ONE);
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE);
        levelFilterDdlb.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionSelectionDTO.isIsCustomHierarchy()) {
            levelDdlb.setEnabled(customId != 0);
            levelFilterDdlb.setEnabled(false);
            hierarchy = sessionDTO.getCustomHierarchyMap().get(customId);
            Utility.loadLevelValueForResult(levelDdlb, null, null, hierarchy, Constant.CUSTOM_LABEL);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilterDdlb, null, sessionDTO.getCustomerHierarchyList(), String.valueOf(viewOpg.getValue()));
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionSelectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilterDdlb, null, sessionDTO.getProductHierarchyList(), String.valueOf(viewOpg.getValue()));
        }
        LOGGER.debug("loadLevelAndFilterValue ends ");
    }

    /**
     * Loads the customDDLB on view change.
     */
    protected void loadCustomDDLB() {
        LOGGER.debug("projection results loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);
        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            if (sessionDTO.getCustomerViewList().isEmpty()) {
                customViewList = CommonLogic.getCustomViewList(projectionId);
                sessionDTO.setCustomerViewList(customViewList);
            } else {
                customViewList = sessionDTO.getCustomerViewList();
            }
            if (customViewList != null) {
                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }

            }
            if (customIdToSelect == 0) {
                levelDdlb.setEnabled(false);
                customDdlb.setValue(SELECT_ONE);

            } else {
                levelDdlb.setEnabled(true);
                customDdlb.select(customIdToSelect);
            }
        }
        LOGGER.debug("projection results loadCustomDDLB ends ");
    }

    /**
     * Loads the grid to selecting the custom hierarchy.
     *
     * @param event the event
     */
    @UiHandler("customDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, levelDdlb);
        if (customId != 0) {
            projectionSelectionDTO.setCustomId(customId);
            int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
            sessionDTO.setCustomId(customId);
            Utility.loadCustomHierarchyList(sessionDTO);
            projectionSelectionDTO.setTpLevel(tpNo);
            callGenerateLogic();
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    /**
     * Generate Logic that will be called during view change and custom view
     * selection.
     */
    protected void callGenerateLogic() {
        if (firstGenerated && !generated) {
            generateLogic();
        }
    }

    /**
     * Loads the level, level filter ddlb and projection results grid on view
     * change.
     *
     * @param viewChange
     */
    protected void viewChange(boolean viewChange) {
        projectionSelectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        if (viewOpg.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                levelDdlb.setEnabled(customId != 0);
                levelFilterDdlb.setEnabled(false);
                projectionSelectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionSelectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                loadCustomDDLB();
                tableLogic.nmProjectionResultsLogic.clearProjectionTotalList();
            } else {
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                customIdToSelect = customId;
                projectionSelectionDTO.setTpLevel(tradingPartnerNo);
                projectionSelectionDTO.setIsCustomHierarchy(false);
                tableLogic.nmProjectionResultsLogic.clearProjectionTotalList();
                if (CUSTOMER.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                    projectionSelectionDTO.setRelationshipBuilderSid(projectionSelectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                    projectionSelectionDTO.setRelationshipBuilderSid(projectionSelectionDTO.getProdRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                }
            }
        }
    }

    /**
     * New Button Listener. Opens the Custom view pop up to create a new custom
     * view hierarchy.
     *
     * @param event
     */
    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        final CustomTreeBuild customTree = new CustomTreeBuild(sessionDTO);
        customTree.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                sessionDTO.setCustomerViewList(CommonLogic.getCustomViewList(projectionId));
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
    }

    /**
     * Edit Button listener. Opens the Custom view pop up to edit the existing
     * custom view hierarchy.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(sessionDTO, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    sessionDTO.setCustomerViewList(CommonLogic.getCustomViewList(projectionId));
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelButtonLogic(Button.ClickEvent event) {
        excelExportLogic();
    }

    /**
     * Configures the excel result table.
     */
    protected void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(ProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP
        );
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exceltable = new ExtCustomTreeTable();
        tableVerticalLayout.addComponent(exceltable);
        exceltable.setRefresh(false);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelResultBean);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable.setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable.setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));
        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
    }

    /**
     * Button Click Listener for Graph Export.
     *
     * @param event
     */
    @UiHandler("graphBtn")
    public void graphExport(Button.ClickEvent event) {
        LOGGER.debug("graphExport clickEvent method starts");
        graphExportLogic();
        LOGGER.debug("graphExport clickEvent method ends");
    }

    /**
     * Expand button logic.
     *
     * @param event the event
     */
    @UiHandler("expandBtn")
    public void expandButtonLogic(Button.ClickEvent event) {
        try {
            if (levelDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(levelDdlb.getValue())) {
                expandCollapseLevelOption(true, levelDdlb.getValue());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Collapse button logic.
     *
     * @param event the event
     */
    @UiHandler("collapseBtn")
    public void collapseButtonLogic(Button.ClickEvent event) {
        try {
            if (levelDdlb.getValue() != null && !SELECT_ONE.getConstant().equals(levelDdlb.getValue())) {
                expandCollapseLevelOption(false, levelDdlb.getValue());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Graph Export logic for all forecast modules.
     */
    public void graphExportLogic() {
        LOGGER.debug("graphExportLogic method starts");

        List<ProjectionResultsDTO> chartiLst = new ArrayList<>();
        if (resultBeanContainer.size() > 0) {
            for (Object itemId : resultBeanContainer.getItemIds(0, resultBeanContainer.size())) {
                ProjectionResultsDTO dto = (ProjectionResultsDTO) itemId;
                if ((resultBeanContainer.areChildrenAllowed(itemId) && dto.getGroup().contains("Total Discount"))
                        || (!resultBeanContainer.areChildrenAllowed(itemId) && !dto.getGroup().contains(Constant.PROJECTION_TOTAL))) {
                    chartiLst.add(dto);
                }

            }
        }
        if (projectionSelectionDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
            projectionSelectionDTO.setActualsOrProjections("Actuals and Projections");
        }
        final PRChart chart = new PRChart(chartiLst, projectionSelectionDTO, fullHeader);
        final NmSPRGraphWindow prGraphWindow = new NmSPRGraphWindow(chart.getChart(), TAB_PROJECTION_RESULTS.getConstant());
        UI.getCurrent().addWindow(prGraphWindow);
        prGraphWindow.focus();
        LOGGER.debug("graphExportLogic method ends");
    }

    /**
     * Expand / Collapse logic for all the forecast modules.
     *
     * @param isExpand
     * @param value
     */
    private void expandCollapseLevelOption(boolean isExpand, Object value) throws PortalException, SystemException {

        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            if (projectionSelectionDTO.isIsFilter()) {
                levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
                levelFilterDdlb.setValue(SELECT_ONE);
                projectionSelectionDTO.setIsFilter(false);
                tableLogic.clearAll();
                levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
            String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
            projectionSelectionDTO.setHierarchyIndicator(hierarchyIndicator);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }
    }

    public void saveProjectionResultsSelection() throws PortalException, SystemException {
        LOGGER.debug("save Projection Results method starts");
        Map map = new HashMap();
        map.put(Constant.FREQUENCY_SMALL, frequencyDdlb.getValue().toString());
        map.put(Constant.HISTORY_CAPS, historyDdlb.getValue().toString());
        map.put("Sales/Units", salesOrUnitsOpg.getValue().toString());
        map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
        map.put(Constant.PERIOD_ORDER, periodOrderOpg.getValue().toString());
        map.put("Pivot", pivotViewOpg.getValue().toString());
        CommonLogic.saveProjectionSelection(map, Constant.PROJECTION_RESULTS_LABEL, projectionSelectionDTO);
        LOGGER.debug("save Projection Results method ends");
    }

    private void loadOnEdit() throws SystemException, PortalException {

        Map<String, String> resultmap = CommonLogic.editProjectionResults(Constant.PROJECTION_RESULTS_LABEL, projectionSelectionDTO);
        if (resultmap != null && !resultmap.isEmpty()) {
            String value = resultmap.get(Constant.FREQUENCY_SMALL);
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                frequencyDdlb.setValue(value);
            }
            value = resultmap.get(Constant.HISTORY_CAPS);
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                historyDdlb.setValue(value);
            }
            value = resultmap.get(Constant.PERIOD_ORDER);
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                periodOrderOpg.setValue(value);
            }
            value = resultmap.get("Pivot");
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                pivotViewOpg.setValue(value);
            }
            value = resultmap.get("Actuals/Projections");
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                actualOrProjectionsOpg.setValue(value);
            }
            value = resultmap.get("Sales/Units");
            if (StringUtils.isNotBlank(value) && !Constant.NULL.equals(value)) {
                salesOrUnitsOpg.setValue(value);
            }
        }
    }

    public boolean isIsTabVisible() {
        return isTabVisible;
    }

    public void setIsTabVisible(boolean isTabVisible) {
        this.isTabVisible = isTabVisible;
    }

    protected abstract void excelExportLogic();

    protected abstract void levelFilterDdlbChangeOption(boolean excelExport);

    protected abstract void generateButtonLogic();

    protected abstract void generateLogic();

    protected abstract void loadExcelResultTable(int levelNo, String hierarchyNo);

}
