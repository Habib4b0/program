/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.lookups.NMPmpyCalculator;
import com.stpl.app.gtnforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUAL;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
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
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */

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
    private ExtTreeContainer<SalesRowDto> customContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
    protected ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    protected ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    protected CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    protected CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    /**
     * The history ddlb.
     */
    @UiField("historyDdlb")
    protected ComboBox historyDdlb;

    /**
     * The pro period ord.
     */
    @UiField("proPeriodOrd")
    protected OptionGroup proPeriodOrd;
    /**
     * The actuals projections.
     */
    @UiField("ActualsProjections")
    protected OptionGroup actualsProjections;

    /**
     * The view.
     */
    @UiField("view")
    protected OptionGroup view;
    /**
     * The newnew btn.
     */
    @UiField("newBtn")
    protected Button newBtn;
    /**
     * The edit btn.
     */
    @UiField("editBtn")
    protected Button editBtn;

    /**
     * The reset.
     */
    @UiField("resetBtn")
    protected Button resetBtn;

    /**
     * The excel export.
     */
    @UiField("excelExport")
    protected Button excelExport;

    /**
     * The level
     */
    @UiField("levelDdlb")
    protected ComboBox level;
    /**
     * The level filter ddlb
     */
    @UiField("levelFilterDdlb")
    protected ComboBox levelFilter;
    /**
     * The view ddlb
     */
    @UiField("viewDdlb")
    protected ComboBox viewDdlb;
    /**
     *
     * /**
     * The generate
     */
    @UiField("generateBtn")
    protected Button generate;

    @UiField("tableLayout")
    protected VerticalLayout tableLayout;
    @UiField("expand")
    protected Button expand;
    @UiField("collapse")
    protected Button collapse;

    @UiField("viewLabel")
    private Label viewLabel;

    @UiField("buttonLayout")
    protected HorizontalLayout buttonLayout;
    /*
        RESET button
     */
    private int customId = 0;

    protected boolean checkAll = false;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    protected ExtFilterTreeTable leftTable;
    protected ExtFilterTreeTable rightTable;
    protected CustomTableHeaderDTO leftHeader = null;
    protected CustomTableHeaderDTO rightHeader = null;
    private final BeanItemContainer<String> groupBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> massGroupBean = new BeanItemContainer<>(String.class);
    protected SessionDTO session;
    protected FreezePagedTreeTable resultsTable;

    protected MSalesProjectionTableLogic mSalesProjectionTableLogic;
    protected ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    protected ExtTreeContainer<SalesRowDto> excelContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
    /**
     * Level Filter Listener
     */
    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                projectionDTO.setGroup(StringUtils.EMPTY);
            }
            levelFilterDdlbChangeOption();
        }
    };
    private Property.ValueChangeListener groupFilterValueChangeListener = null;
    private Property.ValueChangeListener baseLineFilterValueChangeListener = null;
    private Property.ValueChangeListener methodologyFilterValueChangeListener = null;

    protected ComboBox metohdologyFilter = new ComboBox();
    protected ComboBox baseLineFilter = new ComboBox();
    private final BeanItemContainer<String> methdologyBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> baseLineBean = new BeanItemContainer<>(String.class);
    protected String screenName;
    @UiField("mainPanel")
    private Panel mainPanel;
    @UiField("salesProjectionSelection")
    private Panel salesProjectionSelection;
    @UiField("viewLayout")
    private GridLayout viewLayout;
    @UiField("spAdjustment")
    private Panel spAdjustment;
    @UiField("nmFrequencyDdlb")
    protected ComboBox nmFrequencyDdlb;
    protected int customIdToSelect = 0;
    protected List<CustomViewMaster> customViewList = new ArrayList<>();
    protected final SalesLogic salesLogic = new SalesLogic();
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    private DataFormatConverter unitFormat = new DataFormatConverter("#,##0.0");
    private DataFormatConverter salesFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
    private DataFormatConverter growthFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    private String oldValue = StringUtils.EMPTY;
    private String oldGroupValue = StringUtils.EMPTY;
    private final Set<String> tableHirarechyNos = new HashSet<>();
    private Map<Object, Boolean> checkBoxMap = new HashMap<>();
    private Map<Object, String> radioMap = new HashMap<>();
    protected boolean isSalesCalculated;
    private List<String> checkedList;
    private Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<>();

    @UiField("GridLayoutProjection")
    protected GridLayout GridLayoutProjection;

    @UiField("projPeriodOrdr")
    protected Label projPeriodOrdr;
    @UiField("pivotViewVar")
    protected OptionGroup pivotViewVar;

    private final StplSecurity stplSecurity = new StplSecurity();
    private final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

    private static final Logger LOGGER = LoggerFactory.getLogger(AlternateSummery.class);
    private SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    private List<String> variableList;

    public AlternateSummery(SessionDTO session, String screenName, List<String> variableList) {
        try {
            this.session = session;
            this.screenName = screenName;
            this.variableList = variableList;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/AltenateSummeryTab.xml"), this));

            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                projectionDTO.setScreenName(screenName);
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
            }
            addComponent();
            configurefields();
            init();
        } catch (PortalException | SystemException ex) {
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

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT_LABEL);
        view.addItem(Constant.CUSTOM_LABEL);
        view.select(Constant.CUSTOMER_SMALL);
        view.setStyleName(Constant.HORIZONTAL);
        pivotViewVar.addItem(Constant.PERIOD);
        pivotViewVar.addItem("Variable");
        pivotViewVar.select(Constant.PERIOD);
        pivotViewVar.setStyleName(Constant.HORIZONTAL);
        pivotViewVar.setImmediate(true);
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

        groupFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.debug("groupDdlbChangeOption ValueChangeEvent initiated ");
                String groupValue = String.valueOf(event.getProperty().getValue());
                groupValue = Constant.SHOW_ALL_GROUPS.equals(groupValue) ? Constant.PERCENT : groupValue;
                projectionDTO.setGroup(groupValue);
                mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
                LOGGER.debug("groupDdlbChangeOption ValueChangeEvent ends ");
            }
        };

        baseLineFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                return;
            }
        };

        methodologyFilterValueChangeListener = new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                return;
            }
        };

        baseLineFilter.addValueChangeListener(baseLineFilterValueChangeListener);
        metohdologyFilter.addValueChangeListener(methodologyFilterValueChangeListener);

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
        actualsProjections.select(Constant.BOTH);
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
                return;
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
        generateBtnLogic(event);
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
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            variableList.clear();
            variableList.add(Constant.UNITS_SMALL);
            projectionDTO.setVariableList(variableList);
        }
        spAdjustment.setCaption(Constant.SALES_PROJECTION);
        proPeriodOrd.addItem(Constant.ASCENDING);
        proPeriodOrd.addItem(Constant.DESCENDING);
        proPeriodOrd.select(Constant.ASCENDING);
        proPeriodOrd.setStyleName(Constant.HORIZONTAL);
        actualsProjections.addItem(Constant.ACTUALS);
        actualsProjections.addItem(Constant.PROJECTIONS);
        actualsProjections.addItem(Constant.BOTH);
        actualsProjections.setStyleName(Constant.HORIZONTAL);
        actualsProjections.select(Constant.BOTH);

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
            loadLevelDdlb(level, true, currentHierarchy);
            loadLevelDdlb(levelFilter, false, currentHierarchy);
        } else if (view.equalsIgnoreCase(CUSTOMER_HIERARCHY.getConstant())) {
            int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
            currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getCustRelationshipBuilderSid());
            loadLevelDdlb(level, true, currentHierarchy);
            loadLevelDdlb(levelFilter, false, currentHierarchy);
        } else if (view.equalsIgnoreCase(CUSTOM_HIERARCHY.getConstant())) {
            currentHierarchy = CommonLogic.getCustomTree(customId);
            loadLevelDdlb(level, true, currentHierarchy);
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
        mSalesProjectionTableLogic.setRefresh(false);
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
            mSalesProjectionTableLogic.clearAll();
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
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
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
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }
        mSalesProjectionTableLogic.setRefresh(true);
        LOGGER.debug("viewOptionGroup value change listener ends");
    }

    /**
     * Loads the previously created Custom Hierarchy for a particular projection
     * in the Custom DDLB.
     */
    protected void loadCustomDDLB() {
        LOGGER.debug("loadCustomDDLB initiated= {} " , customIdToSelect);
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
     * Configures the field factory for the tables used Mandated and
     * Non-Mandated.
     */
    protected void configureTableFieldFactory() {

        final ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                checkAll = event.isChecked();
                checkClearAll(event.isChecked());
            }
        };

        leftTable = resultsTable.getLeftFreezeAsTable();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            NonMandatedFilter();
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionDTO.getScreenName())) {
            MandatedFilter();
        }
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_FOUR_FIVE, TAB_SALES_PROJECTION.getConstant());
        leftTable.addColumnCheckListener(checkListener);
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (String.valueOf(propertyId).equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setValue(false);
                    check.setEnabled(!Constant.VIEW.equals(session.getAction()));
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            try {
                                SalesRowDto checkDTO = (SalesRowDto) itemId;
                                Boolean checkValue = check.getValue();
                                mSalesProjectionTableLogic.setRefresh(false);
                                final String tableHierarchyNo = mSalesProjectionTableLogic.getTreeLevelonCurrentPage(itemId);
                                checkDTO.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = salesLogic.saveCheckedRecords(projectionDTO, getBeanFromId(itemId), checkValue, false);
                                updateCheckForParentLevels(itemId, updatedRecordsNo, checkValue);
                                updateCheckForChildLevels(tableHierarchyNo, itemId, checkValue);

                                if (!checkValue) {
                                    ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                                    leftTable.removeColumnCheckListener(checkListener);
                                    leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                                    leftTable.addColumnCheckListener(checkListener);
                                    checkAll = false;
                                }
                                resultsTable.getLeftFreezeAsTable().setRefresh(true);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                    return check;
                } else if (!Constant.VIEW.equals(session.getAction()) && Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    SalesRowDto salesRowDto = (SalesRowDto) itemId;
                    if (Constant.GROUP.equals(propertyId) && (Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel()))) {
                        final TextField textField = new TextField();
                        textField.setData(getBeanFromId(itemId).getHierarchyNo());
                        textField.setImmediate(true);
                        textField.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                        textField.addFocusListener(new FocusListener() {
                            
                            @Override
                            public void focus(FocusEvent event) {
                                oldGroupValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            }
                        });
                        textField.addBlurListener(new BlurListener() {
                            
                            @Override
                            public void blur(BlurEvent event) {
                                String newValue = ((TextField) event.getComponent()).getValue();
                                if (!oldGroupValue.equals(newValue)) {
                                    try {
                                        SalesRowDto dto = (SalesRowDto) itemId;
                                        mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.GROUP).setValue(String.valueOf(((TextField) event.getComponent()).getValue()));
                                        dto.addStringProperties(Constant.GROUP, String.valueOf(((TextField) event.getComponent()).getValue()));
                                        salesLogic.saveSalesGroup(projectionDTO, dto.getHierarchyNo(), newValue);
                                        LOGGER.debug("-----------In Blur Listerner--------------");
                                        groupBean.removeAllItems();
                                        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
                                        groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
                                    } catch (PortalException | SystemException | Property.ReadOnlyException ex) {
                                        LOGGER.error(ex.getMessage());
                                    }
                                }
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
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (!Constant.VIEW.equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.HISTORY_CAPS) && !String.valueOf(propertyId).contains(Constant.ACTUALSALES) && !String.valueOf(propertyId).contains(Constant.ACTUALUNITS)
                        && !String.valueOf(propertyId).contains(Constant.METHODOLOGY) && !String.valueOf(propertyId).contains(Constant.BASELINE) && !String.valueOf(propertyId).contains(Constant.GROUP) && !String.valueOf(propertyId).contains("Dis")
                        && !String.valueOf(propertyId).contains("ActualReturned") && !String.valueOf(propertyId).contains(Constant.ACTUALRPU)) {

                    final TextField textField = new TextField();
                    final SalesRowDto salesRowDto = getBeanFromId(itemId);
                    textField.setData(propertyId + "~" + salesRowDto.getHierarchyNo());
                    textField.setImmediate(true);
                    textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                    textField.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                    textField.setEnabled(true);
                    if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                        textField.setConverter(salesFormat);
                    } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
                        textField.setConverter(unitFormat);
                    } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
                        textField.setConverter(growthFormat);
                    }
                    

                    textField.addFocusListener(new FocusListener() {
                        @Override
                        public void focus(FocusEvent event) {
                            oldValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            oldValue = oldValue.replace("$", StringUtils.EMPTY);
                            oldValue = oldValue.replace(",", StringUtils.EMPTY);
                            oldValue = oldValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                        }
                    });
                    textField.addBlurListener(new BlurListener() {
                           @Override
                        public void blur(BlurEvent event) {
                            String newValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            newValue = newValue.replace("$", StringUtils.EMPTY);
                            newValue = newValue.replace(",", StringUtils.EMPTY);
                            newValue = newValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                            if (!oldValue.equals(newValue)) {
                                try {
                                    Double newNumber, oldNumber;
                                    newNumber = StringUtils.EMPTY.equals(newValue) || Constant.NULL.equals(newValue) ? 0.0 : Double.valueOf(newValue);
                                    oldNumber = StringUtils.EMPTY.equals(oldValue) || Constant.NULL.equals(oldValue) ? 0.0 : Double.valueOf(oldValue);
                                     Double incOrDec;
                                    if (oldNumber == 0.0) {
                                        incOrDec = Double.POSITIVE_INFINITY;
                                    } else {
                                        incOrDec = ((newNumber - oldNumber) / oldNumber) * NumericConstants.HUNDRED;
                                    }
                                    String tempValue = String.valueOf(((TextField) event.getComponent()).getData());
                                    String tempArray[] = tempValue.split("-");
                                    tempValue = tempArray[NumericConstants.TWO];
                                    String tempArray1[] = tempValue.split("~");
                                    String changedProperty = tempArray1[0];

                                    String changedValue = ((TextField) event.getComponent()).getValue();
                                    changedValue = StringUtils.isBlank(changedValue) || Constant.NULL.equals(changedValue) ? "0.0" : changedValue;
                                    if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equalsIgnoreCase(screenName)) {
                                        salesLogic.saveEditedRecsReturns(propertyId.toString(), changedValue, incOrDec, salesRowDto, projectionDTO);
                                    } else {
                                        salesLogic.saveEditedRecs(propertyId.toString(), changedValue, incOrDec, changedProperty, salesRowDto, projectionDTO, checkAll, !tempArray1[0].contains(Constant.GROWTH));
                                    }
                                    salesRowDto.addStringProperties(propertyId, newValue);
                                    tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(itemId));
                                } catch (Exception ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                            }
                        }
                    });
                    return textField;
                }
                return null;
            }
        });

        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {

            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {

                checkBoxMap.put(event.getPropertyId(), event.isChecked());

            }
        });

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.CHECK)) {
                leftTable.setColumnCheckBox(obj, projectionDTO.getFrequencyDivision() == NumericConstants.FOUR);
                leftTable.setColumnCheckBoxDisable(obj, Constant.VIEW.equalsIgnoreCase(session.getAction()));
                checkAll = false;
            }
        }
        resultsTable.addStyleName("stableheader");

    }

    /**
     * Method Checks/Unchecks the records in grid on click in the check box in
     * the table header.
     *
     * @param checkClear
     */
    public void checkClearAll(boolean checkClear) {
        try {
            mSalesProjectionTableLogic.setRefresh(false);
            salesLogic.resetChkbox(projectionDTO, checkAll);
            for (Object itemID : leftTable.getItemIds()) {
                SalesRowDto dto = (SalesRowDto) itemID;
                dto.addBooleanProperties(Constant.CHECK, checkClear);
                leftTable.getContainerProperty(itemID, Constant.CHECK).setValue(checkClear);
            }

            for (String hierarchyNo : mSalesProjectionTableLogic.getAllLevels()) {
                boolean isPresentInContainer = true;
                Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
                if (tempId == null) {
                    isPresentInContainer = false;
                    tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
                }
                if (tempId != null) {
                    SalesRowDto tempDto = (SalesRowDto) tempId;
                    tempDto.setUncheckCount(checkClear ? 0 : Integer.valueOf(tempDto.getCcpCount()));
                    updateChecks(tempId, isPresentInContainer);
                }
            }
            mSalesProjectionTableLogic.setRefresh(true);

        } catch (Property.ReadOnlyException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
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
            mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(tempId, Constant.CHECK).setValue(checkValue);
        }
    }

    /**
     * Updates the check value of a parent record, when child records are
     * checked.
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {

        SalesRowDto dto = (SalesRowDto) itemId;
        int newRecordsCount = updatedRecordsNo;
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else if (newRecordsCount < dto.getUncheckCount()) {
            newRecordsCount = dto.getUncheckCount();
        }

        List<String> hierarchyNos = mSalesProjectionTableLogic.getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {
            Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;

                //To update records count for parent
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }

                updateChecks(tempId);
            }
        }
    }

    /**
     * updates the check value of a record.
     *
     * @param tempId
     */
    private void updateChecks(Object tempId) {

        leftTable = resultsTable.getLeftFreezeAsTable();
        // To update the check
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

    /**
     * Updates the checked status of the child record, when a parent record is
     * checked.
     *
     * @param tableHierarchyNo
     * @param itemId
     * @param checkValue
     */
    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {

        List<String> childTableHierarchyNos = mSalesProjectionTableLogic.getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
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

    /**
     * Gets the hierarchy no. of the checked records.
     *
     * @return
     */
    public Set<String> getCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : mSalesProjectionTableLogic.getAllLevels()) {
            Object itemId = mSalesProjectionTableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = mSalesProjectionTableLogic.getExpandedTreeValues(tableTreeLevelNo);
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

    public Set<String> getReturnsCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : mSalesProjectionTableLogic.getAllLevels()) {
            finalHirarechyNo.add(tableTreeLevelNo);
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHiearachyNo());
        return finalHirarechyNo;
    }

    /**
     * Gets the hierarchy No. of the records that are changed to manipulate that
     * during table refresh.
     *
     * @return
     */
    private Set<String> getManualEntryRefreshHiearachyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String hirarechyNo : tableHirarechyNos) {
            if (hirarechyNo != null && !hirarechyNo.equalsIgnoreCase(Constant.NULL)) {
                finalHirarechyNo.addAll(mSalesProjectionTableLogic.getAllParentLevels(hirarechyNo));
                finalHirarechyNo.addAll(mSalesProjectionTableLogic.getAllChildLevels(hirarechyNo));
                finalHirarechyNo.add(hirarechyNo);
            }
        }
        tableHirarechyNos.clear();
        return finalHirarechyNo;
    }

    /**
     * Method contains the logic for the Table Refresh.
     *
     * @param finalHirarechyNo
     */
    public void refreshTableData(Set<String> finalHirarechyNo) {
        mSalesProjectionTableLogic.forRefresh(finalHirarechyNo);
        mSalesProjectionTableLogic.setCurrentPage(mSalesProjectionTableLogic.getCurrentPage());
    }

    /**
     * Gets the selected History periods that can be used for the adjustment and
     * calculation.
     *
     * @return
     */
    protected String getSelectedHistoryPeriods() {

        String selectedPeriods = StringUtils.EMPTY;
        List list = CommonUtils.getCurrent();
        int currentMonth = (Integer) list.get(0);
        int currentQuarter = (Integer) list.get(1);
        int currentYear = (Integer) list.get(NumericConstants.TWO);
        int currentSemi = (Integer) list.get(NumericConstants.THREE);
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempMonth < currentMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempQuarter < currentQuarter);
                    break;
                case Constant.SEMI_ANNUALY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempSemi < currentSemi);
                    break;
                case ANNUAL:
                    condition = tempYear < currentYear;
                    break;
            }
            if ((condition) && (checkBoxMap.get(key))) {
                if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                selectedPeriods = selectedPeriods + value;
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Gets all Projection Periods that can be used for the adjustment.
     *
     * @return
     */
    protected String getAllProjectionPeriods() {

        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = projectionDTO.getSessionDTO().getForecastDTO().getProjectionStartDate();
        int projStartMonth = currentDate.getMonth() + 1;
        int projStartQuarter = CommonUtils.getQuarter(projStartMonth);
        int projStartSemi = CommonUtils.getSemiAnnual(projStartMonth);
        int projStartYear = currentDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : rightHeader.getDoubleColumns()) {
            if (!String.valueOf(key).equals(Constant.GROUP)) {
                String temp[] = ((String) key).split("-");
                int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
                int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
                int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
                int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
                boolean condition = false;
                switch (selectedFreq) {
                    case MONTHLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                        break;
                    case Constant.QUARTERLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                        break;
                    case Constant.SEMI_ANNUALY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                        break;
                    case ANNUAL:
                        condition = tempYear >= projStartYear;
                        break;
                }
                if (condition) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                    selectedPeriods = selectedPeriods + value;
                }
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Gets the Projection Periods that can be used for the adjustment.
     *
     * @return
     */
    protected String getSelectedProjectionPeriods() {
        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = projectionDTO.getSessionDTO().getForecastDTO().getProjectionStartDate();
        int projStartMonth = currentDate.getMonth() + 1;
        int projStartQuarter = CommonUtils.getQuarter(projStartMonth);
        int projStartSemi = CommonUtils.getSemiAnnual(projStartMonth);
        int projStartYear = currentDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        String selectedFreq = projectionDTO.getFrequency();

        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.REGEX_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                    break;
                case Constant.SEMI_ANNUALY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                    break;
                case ANNUAL:
                    condition = tempYear >= projStartYear;
                    break;
            }

            if ((condition) && (checkBoxMap.get(key))) {
                if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                selectedPeriods = selectedPeriods + value;
            }
        }
        return selectedPeriods.toUpperCase();
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
     * Method checks whether at least one record is checked in the table.
     *
     * @return
     */
    protected boolean checkForCheckedRecord() {
        boolean isChecked = false;
        Collection<?> salesList = resultsTable.getLeftFreezeAsTable().getItemIds();
        if (!salesList.isEmpty()) {
            for (Object item : salesList) {
                if ((Boolean) mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(item, Constant.CHECK).getValue()) {
                    isChecked = true;
                    break;
                }
            }
        }
        return isChecked;
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
        LOGGER.debug("loadFrequency for= {} " , String.valueOf(frequency.getValue()));
        CommonUtils.frequenceValueChange(String.valueOf(frequency.getValue()), history, session);
        LOGGER.debug("loadFrequency ends ");
    }

    /**
     * Loads the Level DDLB for the Expand / Collapse based on the Hierarchy.
     */
    public void loadLevelAndFilterValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
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
                String caption = Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                if (levelDto.getCount() <= maxLevel) {
                    level.addItem(itemId);
                    level.setItemCaption(itemId, caption);
                }
            }
        }
        LOGGER.info("loadLevelAndFilterValue ends ");
    }

    /**
     * Configures the table for the excel export.
     */
    protected void configureExcelResultTable() {
        excelContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
        List<String> columnHeader = new ArrayList<>();
        List<Object> visibleColumns = new ArrayList<>();
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        visibleColumns.add(Constant.LEVEL_NAME);
        columnHeader.add("Level Name");

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
        LOGGER.debug("Excel= {}" , excelExport);
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
            int count = 0;

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
                    mSalesProjectionTableLogic.clearAll();
                    projectionDTO.setLevelFilter(false);
                    projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
                } else {
                    mSalesProjectionTableLogic.clearAllExceptCurrentPage();
                }
            } else {
                mSalesProjectionTableLogic.clearAllExceptCurrentPage();
            }
            if (!isExpand) {
                levelNo--;
            }
            mSalesProjectionTableLogic.loadExpandData(levelNo);
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
                historyDdlb.setValue(NumericConstants.FOUR);
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

    /**
     * Refreshes the group ddlb after populate button.
     */
    public void refreshGroupDdlb() {
        try {
            massGroupBean.removeAllItems();
            massGroupBean.addBean(Constant.SELECT_ONE);
            massGroupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
            groupBean.removeAllItems();
            groupBean.addBean(Constant.SHOW_ALL_GROUPS);
            groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
        } else if (selectedPeriods.length() > NumericConstants.FOUR) {
            flag = false;
            return flag;
        }
        return flag;
    }

    public boolean isSalesCalculated() {
        return isSalesCalculated;
    }

    public boolean ismultipleDiscount() {
        boolean isOne = true;
        boolean ismultipleDiscount = false;
        tripleHeaderForCheckedDoubleHeader.keySet().iterator();
        checkedList = new ArrayList<>();
        for (String d : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(d);
            for (String e : checkedDoubleHeaders.keySet()) {
                List a = checkedDoubleHeaders.get(e);
                if (!checkedList.isEmpty() && !a.isEmpty() && !isOne) {
                    ismultipleDiscount = true;
                    break;
                } else {
                    checkedList.addAll(a);
                }
            }
            if (!checkedList.isEmpty()) {
                isOne = false;
            }
        }
        return !ismultipleDiscount;
    }

    /**
     * Method returns a integer value which has the Year followed by the
     * frequency value which used to compare periods.
     *
     * @param str
     * @param isTableColumn
     * @return
     */
    public void NonMandatedFilter() {
        leftTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constant.GROUP.equals(propertyId)) {
                    ComboBox group = new ComboBox();
                    group.addValueChangeListener(groupFilterValueChangeListener);
                    group.setContainerDataSource(groupBean);
                    group.setNullSelectionAllowed(true);
                    group.setNullSelectionItemId(Constant.SHOW_ALL_GROUPS);
                    group.select(Constant.SHOW_ALL_GROUPS);
                    group.setWidth("100%");
                    return group;
                } else if (Constant.METHODOLOGY.equals(propertyId)) {
                    TextField metohdologyFilter = new TextField();
                    metohdologyFilter.setReadOnly(true);
                    metohdologyFilter.setWidth("100%");
                    return metohdologyFilter;
                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField baseLineFilter = new TextField();
                    baseLineFilter.setReadOnly(true);
                    baseLineFilter.setWidth("100%");
                    return baseLineFilter;
                } else if (Constant.LEVEL_NAME.equals(propertyId)) {
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
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
    }

    public void MandatedFilter() {
        leftTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constant.GROUP.equals(propertyId)) {
                } else if (Constant.METHODOLOGY.equals(propertyId)) {
                    TextField metohdologyFilter = new TextField();
                    metohdologyFilter.setReadOnly(true);
                    metohdologyFilter.setWidth("100%");
                    return metohdologyFilter;

                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField baseLineFilter = new TextField();
                    baseLineFilter.setReadOnly(true);
                    baseLineFilter.setWidth("100%");
                    return baseLineFilter;

                } else if (Constant.LEVEL_NAME.equals(propertyId)) {
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
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
    }

    public void init() throws PortalException, SystemException {
        LOGGER.debug("Inside NMSalesProjection Screen= {} " , session.getUserId());
        projectionDTO.setSessionDTO(session);
        projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(session, projectionDTO));
        configureProjectionDTO();
        generateBtnLogic(null);
        configureGroupDDLB();

        securityForButton();
    }

    protected void levelFilterDdlbChangeOption() {
        if (levelFilter.getValue() != null) {
            projectionDTO.setIsFilter(true);
            projectionDTO.setLevelFilter(true);
            projectionDTO.setLevelFilterValue(String.valueOf(UiUtils.parseStringToInteger(String.valueOf(levelFilter.getValue()).split("-")[0].trim())));
            projectionDTO.setFilterLevelNo(Integer.valueOf(projectionDTO.getLevelFilterValue()));
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            projectionDTO.setLevelFilter(false);
        } else {
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelFilter(false);
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
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
        loadLevelDdlb(level, true, currentHierarchy);
        LOGGER.debug(" customId= {} " , customId);
        LOGGER.debug(" currentHierarchy= {} " , currentHierarchy.size());
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
        mSalesProjectionTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        mSalesProjectionTableLogic.setRefresh(true);
    }

    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY)
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(NumericConstants.FOUR);
        }
        actualsProjections.setValue(Constant.BOTH);
        proPeriodOrd.select(Constant.ASCENDING);

    }

    protected void pmpyLogic() {
        PmpyLogic pmpyLogic = new PmpyLogic();

        boolean tpSelected = false;
        boolean hasActuals = false;
        int i = 0;
        String hierarchyNo = StringUtils.EMPTY;

        for (SalesRowDto dto : customContainer.getBeans()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel()))) {
                tpSelected = true;
                i++;
                hierarchyNo = dto.getHierarchyNo();

            }
            for (Object key : dto.getProperties().keySet()) {
                if (((Boolean) dto.getPropertyValue(Constant.CHECK) && (Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel()))) && (String.valueOf(key).contains("Actual"))) {
                    String value = String.valueOf(dto.getProperties().get(key));
                    if (!value.equals("-") && !value.equals("0.00") && !value.equals("$0") && !value.equals(DASH) && !value.equals("0.000000")) {
                        hasActuals = true;
                    }

                }

            }

        }

        if (tpSelected && i == 1) {

            if (!hasActuals) {
                String historyPeriods = String.valueOf(historyDdlb.getValue());

                hierarchyNo = " WHERE RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ";
                Object[] inputParameters = new Object[NumericConstants.TEN];
                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = hierarchyNo;
                List<Object> projectionDetailsIdForPMPY = pmpyLogic.getNmProjectionDetId(inputParameters);
                int projectionDetailsId = Integer.valueOf(projectionDetailsIdForPMPY.get(0).toString());
                List list = pmpyLogic.getTradingPartnerInfo(projectionDetailsId);

                String tradeName = String.valueOf(list.get(0) != null ? list.get(0) : " ");
                String tradeNo = String.valueOf(list.get(1) != null ? list.get(1) : " ");
                String contractHolder = String.valueOf(list.get(NumericConstants.TWO) != null ? list.get(NumericConstants.TWO) : " ");

                final NMPmpyCalculator pmpyCalc = new NMPmpyCalculator(historyPeriods, projectionDetailsIdForPMPY, rightHeader, tradeName, tradeNo, contractHolder, session, projectionDTO);
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
        } else if (tpSelected && i > 1) {
            AbstractNotificationUtils.getErrorNotification("More than one Trading Partner Selected",
                    "There are More than one trading partners selected.\n Please select only one trading partner and try again");

        } else {

            AbstractNotificationUtils.getErrorNotification("No Trading Partner Selected.", "Please select a Trading Partner. ");

        }

    }

    protected void channelsViewChange() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void generateBtnLogic(Button.ClickEvent event) {
        try {

            LOGGER.debug("generate button click listener starts ");

            tableLayout.removeAllComponents();
            mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
            resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
            initializeResultTable();
            configureResultTable();
            projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(projectionDTO.getSessionDTO(), projectionDTO));
            addResultTable();
            if (event != null) {
                generateLogic();
            }

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
        fullHeader = new CustomTableHeaderDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        mSalesProjectionTableLogic.setPageLength(NumericConstants.TWENTY);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);

        customContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
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
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_FIVE);
            } else if (String.valueOf(obj).contains(Constant.LEVEL_NAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.TWO_EIGHT_ZERO);
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
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    /**
     * Adds the layout and pagination control to the Layout.
     */
    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        HorizontalLayout controls = mSalesProjectionTableLogic.createControls();
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
        projectionDTO.setFunctionality("Alternate_History");
        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
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
        projectionDTO.setPivotView(String.valueOf(pivotViewVar.getValue()));
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
        projectionDTO.setFunctionality("Alternate_History");

    }

    /**
     * Saves the Commercials Sales the Temp Table to the Main Table.
     *
     * @throws PortalException
     * @throws Exception
     */
    public void saveSalesProjection() throws PortalException {
        try {
            salesLogic.saveNonMandatedSalesProjection(session);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void configureGroupDDLB() throws PortalException, SystemException {
        actualsProjections.select(Constant.BOTH);
        groupBean.removeAllItems();
        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
        groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
        massGroupBean.removeAllItems();
        massGroupBean.addBean(Constant.SELECT_ONE);
        massGroupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
    }

    public void securityForButton() {
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.SALES_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generate.setVisible(Boolean.FALSE);
                expand.setVisible(Boolean.FALSE);
                collapse.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);

            }

        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(NMDiscountProjection.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    public void saveSPSave() {
        LOGGER.debug("saveSPResults method starts");
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, nmFrequencyDdlb.getValue().toString());
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue().toString());
            map.put("Actuals/Projections", actualsProjections.getValue().toString());
            map.put(Constant.PERIOD_ORDER, proPeriodOrd.getValue().toString());
            sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("saveSPResults method ends");
    }

    public void loadLevelDdlb(ComboBox ddlb, final boolean isExpCol, List<Leveldto> currentHierarchy) {
        resetDdlb(ddlb);
        if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
        	Collections.sort(currentHierarchy,new Comparator<Leveldto>(){
            	@Override
    			public int compare(Leveldto o1, Leveldto o2) {
    				return o2.getTreeLevelNo()-o1.getTreeLevelNo();
            	}
            });
            Collections.reverse(currentHierarchy);
            int maxLevel = "Variable".equalsIgnoreCase(String.valueOf(pivotViewVar.getValue())) ? currentHierarchy.size() : currentHierarchy.size() - 1;
            for (int i = 0; i < currentHierarchy.size(); i++) {
                Leveldto levelDto = (Leveldto) currentHierarchy.get(i);
                if (!isExpCol || levelDto.getCount() <= maxLevel) {
                    ddlb.addItem(levelDto.getTreeLevelNo());
                    ddlb.setItemCaption(levelDto.getTreeLevelNo(), Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel());
                }
            }
        }
    }

    public void resetDdlb(ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(SELECT_ONE.getConstant());
        ddlb.setNullSelectionAllowed(true);
        ddlb.setImmediate(true);
        ddlb.setNullSelectionItemId(SELECT_ONE.getConstant());
    }

}
