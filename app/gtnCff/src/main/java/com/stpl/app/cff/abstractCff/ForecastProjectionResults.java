/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dto.CustomTreeBuild;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.ResultsTableLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CalculatePeriods.CALCULATE;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.*;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.YEAR;
import static com.stpl.app.cff.util.Constants.FrequencyConstants.YEARS;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERIOD;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

public abstract class ForecastProjectionResults extends CustomComponent {

    public static final Logger LOGGER = LoggerFactory.getLogger(ForecastProjectionResults.class);

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

    protected ExtTreeContainer<ProjectionResultsDTO> resultBeanContainer, excelResultBean = new ExtTreeContainer<>(ProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
    protected ResultsTableLogic tableLogic = new ResultsTableLogic();
    protected FreezePagedTreeTable periodTableId = new FreezePagedTreeTable(tableLogic);
    protected boolean generated, firstGenerated = false;
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private final Resource graphImage = new ThemeResource("../../icons/chart.png");
    protected HorizontalLayout controlLayout;
    protected ProjectionSelectionDTO projectionSelectionDTO = new ProjectionSelectionDTO();
    protected SessionDTO sessionDTO;
    protected String screenName;
    protected int projectionId = 0, customId = 0, customIdToSelect = 0;
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    final private BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
    protected CustomTableHeaderDTO leftHeader, rightHeader, fullHeader = new CustomTableHeaderDTO();
    protected List<CffCustomViewMaster> customViewList = new ArrayList<>();
    protected final float maxSplitPosition = 1000, minSplitPosition = NumericConstants.TWO_HUNDRED, splitPosition = 300;
    protected ExtCustomTreeTable exceltable;
    private final int tradingPartnerNo = 0;

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
    public ForecastProjectionResults(final SessionDTO sessionDTO, final String screenName) {
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        projectionSelectionDTO.setSessionDTO(sessionDTO);
        projectionSelectionDTO.setScreenName(screenName);
        projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
        projectionId = sessionDTO.getProjectionId();
        init();
        groupDdlbLabel.setVisible(false);
        groupDdlb.setVisible(false);
        configureProjectionDTO();
        

    }

    /**
     * Init method that reads the UI components from xml and configures the
     * Projection Results Screen.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/cff/tabs/ResultsTab.xml"), this));
        configureFields();
    }

    private void configureFields()  {
        frequencyDdlb.addItem(SELECT_ONE.getConstant());
        frequencyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequencyDdlb.addItem(ConstantsUtil.ANNUALLY);
        frequencyDdlb.addItem(ConstantsUtil.SEMI_ANNUALLY);
        frequencyDdlb.addItem(ConstantsUtil.QUARTERLY);
        frequencyDdlb.addItem(ConstantsUtil.MONTHLY);
        frequencyDdlb.setValue(ConstantsUtil.QUARTERLY);

        historyDdlb.setImmediate(true);

        salesOrUnitsOpg.addStyleName(ConstantsUtils.HORIZONTAL);
        salesOrUnitsOpg.addStyleName(Constants.OPTION_GROUP_WIDTH);
        salesOrUnitsOpg.addItem(SALES.getConstant());
        salesOrUnitsOpg.addItem(UNITS.getConstant());
        salesOrUnitsOpg.addItem(BOTH.getConstant());
        salesOrUnitsOpg.setValue(SALES.getConstant());
        salesOrUnitsOpg.setImmediate(true);

        periodOrderOpg.setImmediate(true);
        periodOrderOpg.addStyleName(ConstantsUtils.HORIZONTAL);
        periodOrderOpg.addStyleName(Constants.OPTION_GROUP_WIDTH);
        periodOrderOpg.addItem(ASCENDING.toString());
        periodOrderOpg.addItem(DESCENDING.toString());
        periodOrderOpg.setValue(ASCENDING.toString());
        actualOrProjectionsOpg.setImmediate(true);
        actualOrProjectionsOpg.addStyleName(ConstantsUtils.HORIZONTAL);
        actualOrProjectionsOpg.addStyleName(Constants.OPTION_GROUP_WIDTH);
        actualOrProjectionsOpg.addItem(Constants.LabelConstants.ACTUALS.toString());
        actualOrProjectionsOpg.addItem(Constants.LabelConstants.PROJECTIONS.toString());
        actualOrProjectionsOpg.addItem(Constants.LabelConstants.BOTH.toString());
        actualOrProjectionsOpg.setValue(Constants.LabelConstants.ACTUALS.toString());
        pivotViewOpg.setImmediate(true);
        pivotViewOpg.addStyleName(ConstantsUtils.HORIZONTAL);
        pivotViewOpg.addStyleName(Constants.OPTION_GROUP_WIDTH);
        pivotViewOpg.addItem(Constants.PERIOD);
        pivotViewOpg.addItem(Constants.VARIABLE_LABEL);
        pivotViewOpg.setValue(Constants.PERIOD);
        viewOpg.addStyleName("forecast-tab");
        viewOpg.addStyleName(ConstantsUtils.HORIZONTAL);
        viewOpg.addItem(Constants.CommonConstantsForChannels.CUSTOMER.toString());
        viewOpg.addItem(Constants.PRODUCT_LABEL);
        viewOpg.addItem(Constants.LabelConstants.CUSTOM.toString());
        viewOpg.setValue(Constants.CommonConstantsForChannels.CUSTOMER.toString());

        levelDdlb.addItem(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setValue(SELECT_ONE.getConstant());
        levelDdlb.addStyleName(Constants.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilterDdlb.addStyleName(Constants.POPUPCONTENTCOMBOSIZE);
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
        projectionSelectionDTO.setCustomerLevelNo(StringUtils.isBlank(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber()) || Constants.NULL.equals(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber())
                ? 1 : Integer.parseInt(projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionSelectionDTO.setProductLevelNo(StringUtils.isBlank(projectionSelectionDTO.getSessionDTO().getProductLevelNumber()) || Constants.NULL.equals(projectionSelectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.parseInt(projectionSelectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionSelectionDTO.setProjectionId(projectionSelectionDTO.getSessionDTO().getProjectionId());
        projectionSelectionDTO.setUserId(Integer.parseInt(projectionSelectionDTO.getSessionDTO().getUserId()));
        projectionSelectionDTO.setSessionId(Integer.parseInt(projectionSelectionDTO.getSessionDTO().getSessionId()));
        projectionSelectionDTO.setFrequency(String.valueOf(frequencyDdlb.getValue()));
        projectionSelectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
        projectionSelectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));
    }

    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event)  {
        generateButtonLogic();
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        resetButtonLogic();
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
                historyDdlb.setValue(StringConstantsUtil.FOUR_QUARTERS);
                salesOrUnitsOpg.setValue(SALES.getConstant());
                actualOrProjectionsOpg.setValue(Constants.LabelConstants.ACTUALS.getConstant());
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
                case ConstantsUtil.ANNUALLY:
                    historyBean.addAll(loadHistoryDdlb(ConstantsUtil.ANNUALLY, YEAR.getConstant()));
                    historyConstant = "1 Year";
                    break;
                case ConstantsUtil.SEMI_ANNUALLY:
                    historyBean.addAll(loadHistoryDdlb(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant()));
                    historyConstant = "2 Semi-Annual";
                    break;
                case ConstantsUtil.QUARTERLY:
                    historyBean.addAll(loadHistoryDdlb(QUARTERLY.getConstant(), QUARTERS.getConstant()));
                    historyConstant = StringConstantsUtil.FOUR_QUARTERS;
                    break;
                case ConstantsUtil.MONTHLY:
                    historyBean.addAll(loadHistoryDdlb(MONTHLY.getConstant(), MONTHS.getConstant()));
                    historyConstant = "12 Months";
                    break;
                default:
                    historyBean.addAll(loadHistoryDdlb(QUARTERLY.getConstant(), QUARTERS.getConstant()));
                    historyConstant = StringConstantsUtil.FOUR_QUARTERS;
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
                period = freq.replace("s", StringUtils.EMPTY);
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
        periodTableId.addStyleName("center-check");
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
        projectionSelectionDTO.getLevelDdlbMap().clear();
        if (projectionSelectionDTO.isIsCustomHierarchy()) {
           LOGGER.debug("Customer Hierarchy Selection");
            levelDdlb.setEnabled(customId != 0);
            levelFilterDdlb.setEnabled(false);
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if ("C".equals(projectionSelectionDTO.getHierarchyIndicator())) {
             LOGGER.debug("Customer C Hierarchy Selection");
            hierarchy = CommonLogic.getCustomerHierarchy(projectionId, projectionSelectionDTO.getCustomerLevelNo());
        } else if ("P".equals(projectionSelectionDTO.getHierarchyIndicator())) {
             LOGGER.debug("Customer P Hierarchy Selection");
            hierarchy = CommonLogic.getProductHierarchy(projectionId, projectionSelectionDTO.getProductLevelNo());
        }
        if (hierarchy != null) {
             LOGGER.debug("Hierarchy Not null");
             for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = "Level " + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                levelDdlb.addItem(itemId);
                levelDdlb.setItemCaption(itemId, caption);
                projectionSelectionDTO.getLevelDdlbMap().put(levelDto.getTreeLevelNo(), levelDto.getLevel());
                if (!projectionSelectionDTO.isIsCustomHierarchy()) {
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);
                }
            }
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
            if(sessionDTO.getProjectionId()!=0){
            customViewList = CommonLogic.getCustomViewList(sessionDTO.getProjectionId());
            }
            if (customViewList != null) {
                for (CffCustomViewMaster obj : customViewList) {
                    int customSid = obj.getCffCustomViewMasterSid();
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
            projectionSelectionDTO.setTpLevel(tpNo);
            sessionDTO.setCustomId(customId);
            CommonLogic.loadCustomHierarchyList(sessionDTO);
            callGenerateLogic();
        } else {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
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
                projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                projectionSelectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                loadCustomDDLB();
            } else {
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                customIdToSelect = customId;
                projectionSelectionDTO.setTpLevel(tradingPartnerNo);
                projectionSelectionDTO.setIsCustomHierarchy(false);
                if (CUSTOMER.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator("C");
                    projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                    projectionSelectionDTO.setRelationshipBuilderSid(projectionSelectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator("P");
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
        excelResultBean = new ExtTreeContainer<>(ProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
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
                LOGGER.debug("In Expand Button");
                expandCollapseLevelOption(true, levelDdlb.getValue());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
            LOGGER.error(ex.getMessage());
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
                        || (!resultBeanContainer.areChildrenAllowed(itemId) && !dto.getGroup().contains("Projection Total"))) {
                    chartiLst.add(dto);
                }

            }
        }
        if (projectionSelectionDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
            projectionSelectionDTO.setActualsOrProjections("Actuals and Projections");
        }
//        final PRChart chart = new PRChart(chartiLst, projectionSelectionDTO, fullHeader);
//        final NmSPRGraphWindow prGraphWindow = new NmSPRGraphWindow(chart.getChart(), TAB_PROJECTION_RESULTS.getConstant());
//        UI.getCurrent().addWindow(prGraphWindow);
//        prGraphWindow.focus();
        LOGGER.debug("graphExportLogic method ends");
    }

    /**
     * Expand / Collapse logic for all the forecast modules.
     *
     * @param isExpand
     * @param value
     */
    private void expandCollapseLevelOption(boolean isExpand, Object value) {

        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
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
            try {
                tableLogic.loadExpandData(levelNo);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ForecastProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveProjectionResultsSelection() throws PortalException, SystemException {
        LOGGER.debug("save Projection Results method starts");
        Map map = new HashMap();
        map.put("Frequency", frequencyDdlb.getValue().toString());
        map.put("History", historyDdlb.getValue().toString());
        map.put("Sales/Units", salesOrUnitsOpg.getValue().toString());
        map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
        map.put("Period Order", periodOrderOpg.getValue().toString());
        map.put("Pivot", pivotViewOpg.getValue().toString());
        CommonLogic.saveProjectionSelection(map, "Projection Results", sessionDTO.getProjectionId());
        LOGGER.debug("save Projection Results method ends");
    }

    public void loadOnEdit() throws PortalException, SystemException {
        Map<String, String> resultmap = CommonLogic.editProjectionResults("Projection Results", projectionSelectionDTO);
        if (resultmap != null && !resultmap.isEmpty()) {
            String value = resultmap.get("Frequency");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                frequencyDdlb.setValue(value);
            }
            value = resultmap.get("History");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                historyDdlb.setValue(value);
            }
            value = resultmap.get("Period Order");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                periodOrderOpg.setValue(value);
            }
            value = resultmap.get("Pivot");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                pivotViewOpg.setValue(value);
            }
            value = resultmap.get("Actuals/Projections");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                actualOrProjectionsOpg.setValue(value);
            }
            value = resultmap.get("Sales/Units");
            if (StringUtils.isNotBlank(value) && !Constants.NULL.equals(value)) {
                salesOrUnitsOpg.setValue(value);
            }
        }
    }

    protected abstract void excelExportLogic();

    protected abstract void levelFilterDdlbChangeOption(boolean excelExport);

    protected abstract void generateButtonLogic();

    protected abstract void generateLogic();

    protected abstract void loadExcelResultTable(int levelNo, String hierarchyNo);
}