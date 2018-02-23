/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.liferay.portal.kernel.exception.PortalException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.AHSummeryTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUAL;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.model.CustomViewMaster;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author JM
 */
public class AlternateSummery extends CustomComponent {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
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
    private final float splitPosition = 300;
    private ExtTreeContainer<SalesRowDto> customContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
    private ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    private CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    
    @UiField("historyDdlb")
    private ComboBox historyDdlb;

    /**
     * The pro period ord.
     */
    @UiField("proPeriodOrd")
    private OptionGroup proPeriodOrd;
    /**
     * The actuals projections.
     */
    @UiField("ActualsProjections")
    private OptionGroup actualsProjections;

    /**
     * The view.
     */
    @UiField("view")
    private OptionGroup view;
    /**
     * The newnew btn.
     */
    @UiField("newBtn")
    private Button newBtn;
    /**
     * The edit btn.
     */
    @UiField("editBtn")
    private Button editBtn;

    /**
     * The excel export.
     */
    @UiField("excelExport")
    private Button excelExport;

    /**
     * The level
     */
    @UiField("levelDdlb")
    private ComboBox level;
    /**
     * The level filter ddlb
     */
    @UiField("levelFilterDdlb")
    private ComboBox levelFilter;
    /**
     * The view ddlb
     */
    @UiField("viewDdlb")
    private ComboBox viewDdlb;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    
    @UiField("pivotViewVar")
    private OptionGroup pivotViewVar;

    @UiField("spAdjustment")
    private Panel spAdjustment;
    
    @UiField("nmFrequencyDdlb")
    private ComboBox nmFrequencyDdlb;
    private int customId = 0;
    
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    private CustomTableHeaderDTO leftHeader = null;
    private CustomTableHeaderDTO rightHeader = null;
    private SessionDTO session;
    private FreezePagedTreeTable resultsTable;
    private AHSummeryTableLogic summeryTableLogic;
    private ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    private ExtTreeContainer<SalesRowDto> excelContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
    
    private Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                projectionDTO.setGroup(StringUtils.EMPTY);
            }
            levelFilterDdlbChangeOption();
        }
    };
    private ComboBox metohdologyFilter = new ComboBox();
    private ComboBox baseLineFilter = new ComboBox();
    private final BeanItemContainer<String> methdologyBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> baseLineBean = new BeanItemContainer<>(String.class);
    private String screenName;

    private int customIdToSelect = 0;
    private List<CustomViewMaster> customViewList = new ArrayList<>();
    private final SalesLogic salesLogic = new SalesLogic();
    private List<Leveldto> currentHierarchy = new ArrayList<>();
    private Map<Object, Boolean> checkBoxMap = new HashMap<>();
    private Map<Object, String> radioMap = new HashMap<>();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AlternateSummery.class);


    public AlternateSummery(SessionDTO session, String screenName) {
        try {
            this.session = session;
            this.screenName = screenName;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/AltenateSummeryTab.xml"), this));
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                projectionDTO.setScreenName(screenName);
                summeryTableLogic = new AHSummeryTableLogic();
                resultsTable = new FreezePagedTreeTable(summeryTableLogic);
            }
            addComponent();
            configurefields();
            init();
        } catch (PortalException ex) {

            LOGGER.error(ex.getMessage());
        }

    }

    /**
     *
     * Configurefields.
     */
    private void configurefields() {
        level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        level.setImmediate(true);
        levelFilter.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilter.setImmediate(true);
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);

        historyDdlb.focus();
        historyDdlb.addItem(Constant.SELECT_ONE);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        tableLayout.addComponent(resultsTable);
        tableLayout.addComponent(excelTable);

        actualsProjections.addItem(Constant.ACTUALS);
        actualsProjections.addItem(Constant.PROJECTIONS);
        actualsProjections.addItem(Constant.BOTH);
        actualsProjections.select(Constant.BOTH);
        actualsProjections.setStyleName(Constant.HORIZONTAL);

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT_LABEL);
        view.addItem(Constant.CUSTOM_LABEL);
        view.select(Constant.CUSTOMER_SMALL);
        view.setStyleName(Constant.HORIZONTAL);

        pivotViewVar.addItem(Constant.PERIOD);
        pivotViewVar.addItem("Variable");
        pivotViewVar.select(Constant.PERIOD);
        pivotViewVar.setStyleName(Constant.HORIZONTAL);

        excelExport.setIcon(excelExportImage);

        excelTable.setVisible(false);

        configureFrequency(nmFrequencyDdlb, historyDdlb);
        loadFrequency(nmFrequencyDdlb, historyDdlb);
        level.addItem(Constant.SELECT_ONE);
        level.setNullSelectionItemId(Constant.SELECT_ONE);

        levelFilter.addItem(Constant.SELECT_ONE);
        levelFilter.setNullSelectionItemId(Constant.SELECT_ONE);

        viewDdlb.addItem(Constant.SELECT_ONE);
        viewDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        metohdologyFilter.setContainerDataSource(methdologyBean);
        metohdologyFilter.setNullSelectionAllowed(false);
        baseLineFilter.setNullSelectionAllowed(false);
        baseLineFilter.setContainerDataSource(baseLineBean);

        loadLevelFilterValue(String.valueOf(view.getValue()));
        view.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadOnViewChange();
            }
        });

        if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            setProjectionSelection();
        }
    }

    /**
     * Contract brand lookup.s
     *
     * @param event the event
     */
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

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
                LOGGER.debug("Inside Overriden method: do nothing");
            }

            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            @Override
            public void yesMethod() {
                LOGGER.debug("Entering resetBtn method");
                resetBtnLogic();
                LOGGER.debug("End of resetBtn method");
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");

    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    /**
     * Generate Button Logic
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        checkBoxMap.clear();
        radioMap.clear();
        generateBtnLogic();
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("viewDdlb")
    public void viewDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        customDdlbChangeOption();
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public int getTabNumber() {
        return Constant.ONE;
    }

    @UiHandler("excelExport")
    public void excelExportListener(Button.ClickEvent event) {
        excelExportLogic();
    }

    /**
     * Mass update Value TextField value change listener
     *
     * @param event
     */
    /**
     * Expand button logic.
     *
     * @param event the event
     */
    @UiHandler("expand")
    public void expand(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        expandButtonLogic();
    }

    /**
     * Collapse button logic.
     *
     * @param event the event
     */
    @UiHandler("collapse")
    public void collapse(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        collapseButtonLogic();
    }

    private void addComponent() {

        spAdjustment.setCaption(Constant.SALES_PROJECTION);
        proPeriodOrd.addItem(Constant.ASCENDING);
        proPeriodOrd.addItem(Constant.DESCENDING);
        proPeriodOrd.select(Constant.ASCENDING);
        proPeriodOrd.setStyleName(Constant.HORIZONTAL);
        CommonUtils.frequenceValueChange(QUARTERLY.getConstant(), historyDdlb, session);
        historyDdlb.setValue(1);

    }

    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName(Constant.CENTER_CHECK);
    }

    protected void initializeDualTable() {
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        rightTable.setEditable(true);
        leftTable.setEditable(true);
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);

    }

    protected void loadLevelFilterValue(final String view) {
        if (view.equalsIgnoreCase(PRODUCT_HIERARCHY.getConstant())) {
            int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.valueOf(session.getProductLevelNumber()) : 0;
            currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getProdRelationshipBuilderSid());
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
            CommonUtils.loadLevelDdlb(levelFilter, false, currentHierarchy);

        } else if (view.equalsIgnoreCase(CUSTOMER_HIERARCHY.getConstant())) {
            int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
            currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getCustRelationshipBuilderSid());
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
            CommonUtils.loadLevelDdlb(levelFilter, false, currentHierarchy);
        } else if (view.equalsIgnoreCase(CUSTOM_HIERARCHY.getConstant())) {
            currentHierarchy = CommonLogic.getCustomTree(customId);
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        }
    }

    /**
     * Configures the Sales Projection Selection for Mandated and Non-Mandated
     * and returns the map containing Sales Projection Selection.
     *
     * @return - Selection Map
     */
    protected Map configureSalesProjectionSelection() {

        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        final String actualsOrProjections = String.valueOf(actualsProjections.getValue());
        final String projectionPeriodorder = String.valueOf(proPeriodOrd.getValue());
        Map<String, String> selection = new HashMap<>();
        selection.put(Constant.FREQUENCY, QUARTERLY.getConstant());
        selection.put(Constant.HISTORY, history);
        if (session.getForecastDTO().getForecastEndDate().after(session.getForecastDTO().getProjectionEndDate())) {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getForecastEndDate(), QUARTERLY.getConstant())));
        } else {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getProjectionEndDate(), QUARTERLY.getConstant())));
        }
        selection.put(Constant.ORDER, projectionPeriodorder);
        selection.put("actualsorprojections", actualsOrProjections);
        selection.put(Constant.SALES, Constant.FALSE);
        selection.put(Constant.UNITS, Constant.FALSE);
        selection.put(Constant.P_GROWTH, Constant.FALSE);
        selection.put(Constant.A_GROWTH, Constant.FALSE);

        return selection;
    }

    /**
     * Returns the SalesRowDto based on the given Item Id.
     *
     * @param obj
     * @return
     */
    public SalesRowDto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesRowDto) {
            targetItem = new BeanItem<>(
                    (SalesRowDto) obj);
        }
        return (SalesRowDto) targetItem.getBean();
    }

    /**
     * Method holds the logic for loading the sales projection grid on view
     * change.
     */
    protected void loadOnViewChange() {
        LOGGER.debug("viewOptionGroup value change listener starts");
        salesLogic.resetChkbox(projectionDTO, false);
        summeryTableLogic.setRefresh(false);
        leftTable.setColumnCheckBox(Constant.CHECK, true, false);
        leftTable.setColumnCheckBoxDisable(Constant.CHECK, Constant.VIEW.equals(session.getAction()));
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        if ((PRODUCT.getConstant()).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, true);
        } else if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        }
        if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            newBtn.setEnabled(!Constant.VIEW.equals(session.getAction()));
            viewDdlb.setEnabled(!Constant.VIEW.equals(session.getAction()));
            projectionDTO.setIsCustomHierarchy(true);
            projectionDTO.setHierarchyIndicator(Constant.CUSTOM_LABEL);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(false);
            loadCustomDDLB();
            viewDdlb.setValue(SELECT_ONE);
            summeryTableLogic.clearAll();
        } else if ((PRODUCT.getConstant()).equals(view.getValue())) {
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            summeryTableLogic.setProjectionResultsData(projectionDTO);
        } else {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            summeryTableLogic.setProjectionResultsData(projectionDTO);
        }
        summeryTableLogic.setRefresh(true);
        LOGGER.debug("viewOptionGroup value change listener ends");
    }

    /**
     * Loads the previously created Custom Hierarchy for a particular projection
     * in the Custom DDLB.
     */
    protected void loadCustomDDLB() {
        LOGGER.debug("loadCustomDDLB initiated " + customIdToSelect);
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        editBtn.setEnabled(false);
        customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
        if (customViewList != null) {
            viewDdlb.removeAllItems();
            viewDdlb.addItem(SELECT_ONE);
            viewDdlb.setNullSelectionItemId(SELECT_ONE);
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
                viewDdlb.select(customIdToSelect);
            } else {
                viewDdlb.setValue(SELECT_ONE);
            }
        }
        customId = customIdToSelect;
        LOGGER.debug("loadCustomDDLB ends ");
    }

    /**
     * Opens the CustomTreePopup to create a new Custom Hierarchy
     */
    protected void customTreeViewLogic() {

        LOGGER.debug("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(session);
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
        LOGGER.debug("newCustomHierarchhy clickEvent method ends");
    }

    /**
     * Opens the CustomTreePopup to edit the existing custom hierarchy.
     */
    public void editHierarchyLogic() {
        LOGGER.debug("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(viewDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(session, customId);
            customTree.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.debug(" Ending editHierarchyBtn ");
    }

    /**
     * Formats the given value based on the passed decimal format.
     *
     * @param FORMAT
     * @param value
     * @param appendChar
     * @return
     */
    public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (Constant.CURRENCY.equals(appendChar)) {
            value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
        } else {
            value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
        }
        return value;
    }

    /**
     * Configures the frequency DDLB in the Sales Projection Selection.
     *
     * @param frequency
     * @param history
     * @param flag
     */
    private void configureFrequency(final ComboBox frequency, final ComboBox history) {
        frequency.addItem(MONTHLY);
        frequency.addItem(QUARTERLY.getConstant());

        frequency.addItem(SEMI_ANNUAL.getConstant());

        frequency.addItem(ANNUAL);
        frequency.select(QUARTERLY.getConstant());
        frequency.setNullSelectionAllowed(false);
        frequency.setImmediate(true);
        frequency.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadFrequency(frequency, history);
            }
        });
    }

    /**
     * Loads the history dropdown based on the frequency.
     *
     * @param frequency
     * @param history
     */
    public void loadFrequency(final ComboBox frequency, final ComboBox history) {
        LOGGER.debug("loadFrequency for " + String.valueOf(frequency.getValue()));
        CommonUtils.frequenceValueChange(String.valueOf(frequency.getValue()), history, session);
        LOGGER.debug("loadFrequency ends ");
    }

    /**
     * Loads the Level DDLB for the Expand / Collapse based on the Hierarchy.
     */
    public void loadLevelAndFilterValue() {
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        level.removeAllItems();
        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionDTO.isIsCustomHierarchy()) {
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), projectionDTO.getCustomerLevelNo(), projectionDTO.getCustRelationshipBuilderSid());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), projectionDTO.getProductLevelNo(), projectionDTO.getProdRelationshipBuilderSid());
        }
        
        if (hierarchy != null) {
            int maxLevel = hierarchy.size() - 1;
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                if (levelDto.getCount() <= maxLevel) {
                    level.addItem(itemId);
                    level.setItemCaption(itemId, caption);
                }
            }
        }
        LOGGER.debug("loadLevelAndFilterValue ends ");
    }

    /**
     * Configures the table for the excel export.
     */
    protected void configureExcelResultTable() {
        excelContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
        List<String> columnHeader = new ArrayList<>();
        List<Object> visibleColumns = new ArrayList<>();
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

        visibleColumns.add(Constant.LEVEL_NAME);
        columnHeader.add("Level");
        visibleColumns.add(Constant.BASELINE);
        columnHeader.add("Base Line");
        visibleColumns.add(Constant.METHODOLOGY);
        columnHeader.add("Methodology");

        for (Object obj : excelHeader.getSingleColumns()) {
            visibleColumns.add(obj);
        }
        for (String header : excelHeader.getSingleHeaders()) {
            columnHeader.add(StringUtils.EMPTY + header);
        }
        excelTable.setRefresh(false);
        excelTable.setVisible(Boolean.FALSE);
        excelContainer.setColumnProperties(excelHeader.getProperties());
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(visibleColumns.toArray());
        excelTable.setColumnHeaders(Arrays.copyOf(columnHeader.toArray(), columnHeader.size(), String[].class));
        tableLayout.addComponent(excelTable);

    }

    protected void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.debug("excelExport"+excelExport);
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        loadExcelResultTable(levelNo, hierarchyNo);
    }

    /**
     * Initial setup of the excel container based on the selection.
     *
     * @param levelNo
     * @param hierarchyNo
     */
    protected void loadExcelResultTable(int levelNo, String hierarchyNo) {
        try {
            excelContainer.removeAllItems();
            projectionDTO.setFilterLevelNo(levelNo);
            projectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.clearNonFetchableIndex();
            int count;

            if ("Returns".equalsIgnoreCase(screenName)) {
                count = salesLogic.getConfiguredSalesProjectionCount(new Object(), projectionDTO, true, initialProjSelDTO);
            } else {
                count = salesLogic.getConfiguredSalesProjectionCount(new Object(), projectionDTO, true, projectionDTO);
            }
            List<SalesRowDto> resultList = salesLogic.getConfiguredSalesProjection(new Object(), 0, count, projectionDTO);
            loadDataToContainer(resultList, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Recursive method to load the inner levels of each level in the Sales
     * Projection grid for the excel Container.
     *
     * @param id
     */
    protected void addLowerLevelsForExport(Object id) {
        try {
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            int count = salesLogic.getConfiguredSalesProjectionCount(id, projectionDTO, true, projectionDTO);
            List<SalesRowDto> resultList = salesLogic.getConfiguredSalesProjection(id, 0, count, projectionDTO);
            loadDataToContainer(resultList, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Initial Method Call to load the excel container.
     *
     * @param resultList
     * @param parentId
     */
    protected void loadDataToContainer(List<SalesRowDto> resultList, Object parentId) {
        try {
            if (!resultList.isEmpty()) {
                for (SalesRowDto dto : resultList) {
                    excelContainer.addBean(dto);
                    if (parentId != null) {
                        excelContainer.setParent(dto, parentId);
                    }
                    if (dto.getParent() == 1) {
                        excelContainer.setChildrenAllowed(dto, true);
                        addLowerLevelsForExport(dto);
                    } else {
                        excelContainer.setChildrenAllowed(dto, false);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method does the initial setup for the Expand/Collapse.
     *
     * @param isExpand
     * @param value
     */
    protected void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));

        if (levelNo > 0) {
            Object val = levelFilter.getValue();
            if (val != null) {
                if (!SELECT_ONE.getConstant().equals(String.valueOf(val))) {
                    levelFilter.setValue(SELECT_ONE);
                    summeryTableLogic.clearAll();
                    projectionDTO.setLevelFilter(false);
                    projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
                } else {
                    summeryTableLogic.clearAllExceptCurrentPage();
                }
            } else {
                summeryTableLogic.clearAllExceptCurrentPage();
            }
            if (!isExpand) {
                levelNo--;
            }
            summeryTableLogic.loadExpandData(levelNo);
        }
    }

    private void setProjectionSelection() {
        Map<Object, Object> map = null;
        if ("Returns".equalsIgnoreCase(screenName)) {
            map = CommonLogic.getReturnsProjectionSelection(session.getProjectionId());
        } else {
            map = CommonLogic.getNMProjectionSelection(session.getProjectionId(), Constant.SALES_PROJECTION);
        }

        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                nmFrequencyDdlb.setValue(map.get(Constant.FREQUENCY_SMALL));
                nmFrequencyDdlb.setImmediate(true);
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                int i = Integer.parseInt(String.valueOf(value));
                historyDdlb.setValue(i);
                historyDdlb.setImmediate(true);
            }
            value = map.get(Constant.PERIOD_ORDER);
            if (value != null) {
                proPeriodOrd.setValue(String.valueOf(value));
            }
            value = map.get("Actuals/Projections");
            if (value != null) {
                actualsProjections.setValue(String.valueOf(value));
            }

        }
    }

    public boolean getValidation(String selectedPeriods) {
        boolean flag = true;
        if (!ANNUAL.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            String[] historySelected = selectedPeriods.split(",");
            for (int i = 0; i < historySelected.length; i++) {
                if (!historySelected[0].split(" ")[1].equalsIgnoreCase(historySelected[i].split(" ")[1])) {
                    flag = false;
                    return flag;
                }
            }
        } else {
            if (selectedPeriods.length() > NumericConstants.FOUR) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    public void init() throws PortalException {
        LOGGER.debug("Inside NMSalesProjection Screen " + session.getUserId());
        projectionDTO.setSessionDTO(session);
        projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(session, projectionDTO));
        configureProjectionDTO();
        generateBtnLogic();

    }

    protected void levelFilterDdlbChangeOption() {
        if (levelFilter.getValue() != null) {
            projectionDTO.setIsFilter(true);
            projectionDTO.setLevelFilter(true);
            projectionDTO.setLevelFilterValue(String.valueOf(UiUtils.parseStringToInteger(String.valueOf(levelFilter.getValue()).split("-")[0].trim())));
            projectionDTO.setFilterLevelNo(Integer.valueOf(projectionDTO.getLevelFilterValue()));
            summeryTableLogic.setProjectionResultsData(projectionDTO);
            projectionDTO.setLevelFilter(false);
        } else {
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelFilter(false);
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
            summeryTableLogic.setProjectionResultsData(projectionDTO);
        }
    }

    protected void excelExportLogic() {
        try {
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(Boolean.TRUE);
            if (excelTable.size() > 0) {
                ForecastUI.setEXCEL_CLOSE(true);
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), Constant.SALES_PROJECTION, Constant.SALES_PROJECTION, "Sales_Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    protected void customDdlbChangeOption() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        LOGGER.debug(" customId  " + customId);
        LOGGER.debug(" currentHierarchy " + currentHierarchy.size());
        projectionDTO.setCustomId(customId);
        generateLogic();
        if (viewDdlb.getValue() != null
                && !Constant.NULL.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !SELECT_ONE.getConstant().equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !Constant.DASH.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))) {
            editBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    protected void expandButtonLogic() {
        try {
            if (StringUtils.isNotBlank(String.valueOf(level.getValue())) || !Constant.NULL.equals(String.valueOf(level.getValue()))) {
                projectionDTO.setExpandCollapseFlag(Boolean.TRUE);
                expandCollapseLevelOption(true, level.getValue());
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
            } else {
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
                AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
    }

    protected void collapseButtonLogic() {
        summeryTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        summeryTableLogic.setRefresh(true);
    }

    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY)
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(NumericConstants.FOUR);
        }
        actualsProjections.select(Constant.BOTH);
        proPeriodOrd.select(Constant.ASCENDING);

    }

    protected void generateBtnLogic() {
        try {
            LOGGER.debug("generate button click listener starts ");
            tableLayout.removeAllComponents();
            summeryTableLogic = new AHSummeryTableLogic();
            resultsTable = new FreezePagedTreeTable(summeryTableLogic);
            initializeResultTable();
            configureResultTable();
            projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(projectionDTO.getSessionDTO(), projectionDTO));
            addResultTable();
            generateLogic();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("generate button click listener ends ");

    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        summeryTableLogic.setTreeNodeMultiClick(false);
        summeryTableLogic.setPageLength(NumericConstants.TWENTY);
        summeryTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        summeryTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getAHSummeryTabLeftTableColumns(fullHeader);
        session.setForecastName(Constant.SALES_PROJECTION);
        projectionDTO.setIsSumarry(true);
        rightHeader = HeaderUtils.getAlternateHistoryRightTableColumns(projectionDTO, session, fullHeader);

        customContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        summeryTableLogic.setContainerDataSource(customContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.THREE_HUNDRED);
            } else if (String.valueOf(obj).contains(Constant.LEVEL_NAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.THREE_HUNDRED);
            }
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());

        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);

        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    /**
     * Adds the layout and pagination control to the Layout.
     */
    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        HorizontalLayout controls = summeryTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(controlLayout);
    }

    /**
     * Contains the generate logic.
     */
    private void generateLogic() {
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(view.getValue()) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        if ((PRODUCT.getConstant()).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, true);
        } else if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        }
        loadLevelAndFilterValue();
        summeryTableLogic.setProjectionResultsData(projectionDTO);
    }

    /**
     * Configures the Projection Selection DTO on entering Screen. Sets the
     * values in the Session DTO to the ProjectionSelectionDTO.
     */
    private void configureProjectionDTO() {
        boolean toHist = false;
        int historyNum = 0;
        projectionDTO.setScreenName(screenName);
        projectionDTO.setCustRelationshipBuilderSid(projectionDTO.getSessionDTO().getCustRelationshipBuilderSid());
        projectionDTO.setProdRelationshipBuilderSid(projectionDTO.getSessionDTO().getProdRelationshipBuilderSid());
        projectionDTO.setCustomerLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getCustomerLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getCustomerLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionDTO.setProductLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionDTO.setProjectionId(projectionDTO.getSessionDTO().getProjectionId());
        projectionDTO.setUserId(Integer.valueOf(projectionDTO.getSessionDTO().getUserId()));
        projectionDTO.setSessionId(Integer.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        projectionDTO.setProjectionOrder(String.valueOf(proPeriodOrd.getValue()));
        projectionDTO.setActualsOrProjections(String.valueOf(actualsProjections.getValue()));
        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        if (history != null && !StringUtils.isBlank(history) && !NULL.equals(history) && !SELECT_ONE.getConstant().equals(history)) {
            toHist = true;
            projectionDTO.setHistory(history);
            historyNum = Integer.valueOf(projectionDTO.getHistory());
        }

        if (toHist) {
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), session));
        }

    }

}