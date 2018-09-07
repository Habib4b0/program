/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastDiscountProjectionResults;
import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.NMFilterGenerator;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.CommercialDPRLogic;
import static com.stpl.app.gtnforecasting.discountprojectionresults.logic.NMDPRLogic.executeSelectQuery;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionResultsLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.TAB_DISCOUNT_PROJECTION_RESULTS;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.gtnforecasting.utils.DPRChart;
import com.stpl.app.gtnforecasting.utils.NmSPRGraphWindow;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public class NMDiscountProjectionResults extends ForecastDiscountProjectionResults {

    private final SessionDTO sessionDto;
    
    

    private String screenName = StringUtils.EMPTY;

    private final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NMDiscountProjectionResults.class);

    private List<List<String>> discountlist = new ArrayList<>();
    private boolean generated = false;
    private boolean firstGenerated = false;
    private boolean flag = false;
    private final int tradingPartnerNo = 0;
    private final ForecastForm form;
    private boolean isTabVisible = true;
    private final Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {            
            levelFilterDdlbChangeOption(false);
        }
    };

    
    public NMDiscountProjectionResults(SessionDTO sessionDto, String screenName, ForecastForm form) {
        super(screenName, sessionDto);
        this.sessionDto = sessionDto;
        this.screenName = screenName;
        this.projectionDTO.setTabName(Constant.DISCOUNT_PROJECTION_RESULTS);
        this.projectionDTO.setScreenName(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
        this.form = form;
        if (flag) {
            configure();
        }
        flag = true;
    }

    @Override
    protected void loadFrequency() {
        LOGGER.debug("ProjectionResults ValueChangeEvent initiated ");
        CommonUtils.frequenceValueChange(frequencyDdlb.getValue(), historyDdlb, sessionDto);
        LOGGER.debug("ProjectionResults ValueChangeEvent ends ");
    }

    @Override
    protected void resetButtonLogic() {
         new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
        public void yesMethod() {
        frequencyDdlb.setValue(QUARTERLY);
        historyDdlb.setValue(NumericConstants.FOUR);
        actualOrProjectionsOpg.setValue(ACTUALS.getConstant());
        periodOrderOpg.setValue(ASCENDING.getConstant());
        pivotViewOpg.setValue(Constant.PERIOD);
        makeCheckedVariableMenuItem();
        }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");
    }

    @Override
    protected void generateButtonLogic() {
        List checkedValues = getCheckedValues();
        
        if (checkedValues.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No variables were selected", "Please select at least one variable and try again.");
            return;
        } else {
            generated = true;
            firstGenerated = true;
            if (loadProjectionSelection()) {
                if (discountlist != null && discountlist.size() > 1 && !discountlist.get(1).isEmpty()) {
                        projectionDTO.setIsGenerate(true);
                        tableVerticalLayout.removeAllComponents();
                        setTableLogic(new DPRTableLogic());
                        setResultsTable(new FreezePagedTreeTable(getTableLogic()));
                        initializeResultTable();
                        if (ACTUALS.getConstant().equals(projectionDTO.getActualsOrProjections())) {/* As per GAL-11186*/
                            projectionDTO.setActualsOrProjections(BOTH.getConstant());
                        }
                        configureResultTable();
                        addResultTable();
                        String groupFilter = String.valueOf(groupDdlb.getValue());
                        if (groupFilter.equals(Constant.NULL)) {
                            groupFilter = Constant.ALL_DISCOUNT_GROUP;
                        } 
                        projectionDTO.setGroupFilter(groupFilter);
                        loadTradingPartner();
                        generateLogic();
                        getTableLogic().setItemsPerPage(NumericConstants.TWENTY);
                        projectionDTO.setIsGenerate(false);
                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please select a frequency and/or history and try again.", ButtonId.OK);
            }

            generated = false;
        }
    }

    @Override
    protected void excelButtonLogic() {
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        exceltable.setRefresh(BooleanConstant.getTrueFlag());
        ForecastUI.setEXCEL_CLOSE(true);
        ExcelExport exp = null;
        int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
        if (Constant.PERIOD.equals(String.valueOf(pivotViewOpg.getValue())) && (QUARTERLY.equals(String.valueOf(frequencyDdlb.getValue())) || MONTHLY.equals(String.valueOf(frequencyDdlb.getValue())))) {
            for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
                exceltable.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                exceltable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                exceltable
                        .setDoubleHeaderVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.THREE)).toArray());
                Object[] doubleHeader = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FOUR)).toArray();
                exceltable
                        .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));

                exceltable.setDoubleHeaderMap((Map<Object, Object[]>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FIVE));
                String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                ForecastUI.setEXCEL_CLOSE(true);
                if (i == 0) {
                    exp = new ExcelExport(new ExtCustomTableHolder(exceltable), sheetName, Constant.DISCOUNT_PROJECTION_RESULTS, "Discount_Projection_Results.xls", false);
                } else {
                    excelNextTableHolder(exp, sheetName);
                }
                exportMultipleTabs(exp, i, exportAt);
            }
        } else {
             exp = new ExcelExport(new ExtCustomTableHolder(exceltable), Constant.DISCOUNT_PROJECTION_RESULTS, Constant.DISCOUNT_PROJECTION_RESULTS, "Discount_Projection_Results.xls", false);
            exp.export();
        }
    }

    public void excelNextTableHolder(ExcelExport exp, String sheetName) {
        if (exp != null) {
            exp.setNextTableHolder(new ExtCustomTableHolder(exceltable), sheetName);
        }
    }

    public void exportMultipleTabs(ExcelExport exp, int i, int exportAt) {
        if (exp != null) {
            boolean export = i == exportAt;
            exp.exportMultipleTabs(export);
        }
    }
 
    @Override
    protected void customDdlbChangeOptionLogic() {

        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, levelDdlb);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        levelDdlb.setEnabled(customId != 0);
        if (customId != 0) {
            sessionDto.setCustomId(customId);
            Utility.loadCustomHierarchyList(sessionDto);
        }
        projectionDTO.setTpLevel(tpNo);
        projectionDTO.setCustomId(customId);
        projectionDTO.setView(Constant.CUSTOM_LABEL);
        if (!generated && firstGenerated) {
            generateLogic();
        }
        levelFilterDdlb.setEnabled(false);
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void graphExportLogics() {
        LOGGER.debug("graphExportLogic method starts");
        List<DiscountProjectionResultsDTO> chartList = new ArrayList<>();
        for (DiscountProjectionResultsDTO dto : getResultBeanContainer().getBeans()) {
            chartList.add(dto);
        }
        if (projectionDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
            projectionDTO.setActualsOrProjections("Actuals and Projections");
        }
        final DPRChart chart = new DPRChart(chartList, projectionDTO, getFullHeader());
        final NmSPRGraphWindow prGraphWindow = new NmSPRGraphWindow(chart.getChart(), Constant.DISCOUNT_PROJECTION_RESULTS);
        UI.getCurrent().addWindow(prGraphWindow);
        prGraphWindow.focus();
        LOGGER.debug("graphExportLogic method ends");
    }

    @Override
    protected void expandButtonLogic() {
        expandCollapseLevelOption(true, levelDdlb.getValue());
    }

    @Override
    protected void collapseButtonLogic() {
        expandCollapseLevelOption(false, levelDdlb.getValue());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void configureFields() {
        CommonUtils.loadDDLBFromProperties(frequencyDdlb, Constant.FREQUENCY);
            variableMenu.focus();
            variableMenu.setScrollable(true);
            variableMenu.setPageLength(NumericConstants.FIVE);
            String[] variableValues = Constant.DPRVariables.names();
            customMenuItem = variableMenu.addItem("-Select Variables-", null);
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableValues.length];
            for (int i = 0; i < variableValues.length; i++) {
                customItem[i] = customMenuItem.addItem(variableValues[i].trim(), null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);

                customItem[i].setItemClickNotClosable(true);
                if (i == 0) {
                    customItem[i].setCheckAll(true);
                }
            }

        frequencyDdlb.setValue(QUARTERLY);
        frequencyDdlb.focus();
        historyDdlb.setValue(Constant.FOUR_QUARTERS);
        discountOpg.setImmediate(true);
        discountOpg.addStyleName(Constant.HORIZONTAL);
        discountOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        discountOpg.addItem(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
        discountOpg.addItem(Constant.SUPPLEMENTAL);
        discountOpg.addItem(Constant.BOTH);
        discountOpg.setValue(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
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
        pivotViewOpg.addItem(DISCOUNT.getConstant());
        pivotViewOpg.setValue(Constant.PERIOD);
        viewOpg.addStyleName("forecast-tab");
        viewOpg.addItem(Constant.CUSTOMER_SMALL);
        viewOpg.addItem(Constant.PRODUCT_LABEL);
        viewOpg.addItem(Constant.CUSTOM_LABEL);
        viewOpg.setValue(Constant.CUSTOMER_SMALL);
            makeCheckedVariableMenuItem();
            getCheckedValues();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelFilterDdlb.addItem(SELECT_ONE);
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE);
        levelFilterDdlb.setValue(SELECT_ONE);
        customDdlb.addItem(SELECT_ONE);
        customDdlb.setNullSelectionItemId(SELECT_ONE);
        customDdlb.setValue(SELECT_ONE);
        editBtn.setEnabled(false);
        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(sessionDto.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(sessionDto.getAction())) {
            fetchDiscountsFromSave();
        }
        try {
            loadGroupFilter();
        } catch (SystemException | PortalException ex) {
            LoggerFactory.getLogger(NMDiscountProjectionResults.class.getName()).error( StringUtils.EMPTY, ex);
        }
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
    }

    public void loadGroupFilter() throws PortalException {
        return;
    }

    /**
     * To get the discount Type
     *
     * @return
     */
    public String getDiscountType() {
        if (levelDdlb != null && levelDdlb.getValue() != null) {
            return String.valueOf(levelDdlb.getValue());
        }
        return null;
    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable() {
        getResultsTable().markAsDirty();
        getResultsTable().setSelectable(false);
        getResultsTable().setPageLength(NumericConstants.EIGHTEEN);
        getResultsTable().setSplitPosition(SPLIT_POSITION, Sizeable.Unit.PIXELS);
        getResultsTable().setMinSplitPosition(MIN_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        getResultsTable().setMaxSplitPosition(MAX_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        getResultsTable().addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequencyDdlb.getValue();
        boolean toFreq = false;
        int historyNum = 0;
            if ((freq != null) && (!SELECT_ONE.equals(String.valueOf(freq)))) {
                toFreq = true;
                projectionDTO.setFrequency(String.valueOf(freq));
            }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
            if ((hist != null) && (!SELECT_ONE.equals(hist.toString()))) {
                toHist = true;
                projectionDTO.setHistory(hist.toString());

                if (freq != null && freq.equals(QUARTERLY)) {
                    historyNum = Integer.parseInt(String.valueOf(hist).replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq != null && freq.equals(SEMI_ANNUALLY.getConstant())) {
                    historyNum = Integer.parseInt(String.valueOf(hist).replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());

                } else if (freq != null && freq.equals(MONTHLY)) {
                    historyNum = Integer.parseInt(String.valueOf(hist).replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());

                } else if (freq != null && freq.equals(ANNUALLY)) {
                    String histPeriod = String.valueOf(hist);
                    historyNum = Integer.parseInt(histPeriod.trim());
                }
            }
        if (toFreq && toHist) {
            toRet = true;
            NMDiscountProjection dp = form.getDiscountProjection();
            if (dp != null) {
                String discountType = form.getDiscountProjection().getDiscountType();
                if (form.getDiscountProjection().isDiscountGenerated() && discountType != null &&  sessionDto.isDiscountRSlistUpdated()) {
                      discountlist =  sessionDto.getDiscountRSlist();
                }
            }
            projectionDTO.setSessionDTO(sessionDto);
            setProjectionId(sessionDto.getProjectionId());
            projectionDTO.setForecastDTO(sessionDto.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), sessionDto));
            projectionDTO.setProjection(StringUtils.EMPTY + projectionDTO.getProjectionNum());
            projectionDTO.setUserId(Integer.parseInt(sessionDto.getUserId()));
            projectionDTO.setSessionId(Integer.parseInt(sessionDto.getSessionId()));
            projectionDTO.setCustRelationshipBuilderSid(sessionDto.getCustRelationshipBuilderSid());
            projectionDTO.setProdRelationshipBuilderSid(sessionDto.getProdRelationshipBuilderSid());
            projectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));

            projectionDTO.setProjectionId(sessionDto.getProjectionId());
            projectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
            projectionDTO.setPivotView(String.valueOf(pivotViewOpg.getValue()));
            tablePanel.setCaption(String.valueOf(pivotViewOpg.getValue()) + " Pivot View");
            projectionDTO.setForecastConfigPeriods(CommonUtils.prepareProjectionPeriodList(projectionDTO));
            projectionDTO.setCustomId(customId);
            projectionDTO.setView(String.valueOf(viewOpg.getValue()));
            projectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionDTO.setCustomerLevelNo(Integer.parseInt(sessionDto.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.parseInt(sessionDto.getProductLevelNumber()));
            List asd = getCheckedValues();
            projectionDTO.setdPVariablesList(asd);
            viewChange(false);
        }

        return toRet;
    }

    private void configureResultTable() {
            getTableLogic().setTreeNodeMultiClick(false);
            getTableLogic().setPageLength(NumericConstants.TWENTY);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            getTableLogic().getControlConfig().setPageLengthsAndCaptions(pagelength);
            getTableLogic().sinkItemPerPageWithPageLength(false);
            setFullHeader(new CustomTableHeaderDTO());
            setLeftHeader(HeaderUtils.getNMDiscountProjectionResultsLeftTableColumns(getFullHeader()));
            setRightHeader(HeaderUtils.getNMDiscountProjectionResultsRightTableColumns(projectionDTO, getFullHeader()));

            setResultBeanContainer(new ExtTreeContainer<>(DiscountProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP));
            getResultBeanContainer().setColumnProperties(getFullHeader().getProperties());
            getTableLogic().setContainerDataSource(getResultBeanContainer());
            final ExtFilterTreeTable leftTable = getResultsTable()
                    .getLeftFreezeAsTable();
            final ExtFilterTreeTable rightTable = getResultsTable()
                    .getRightFreezeAsTable();
            leftTable.setImmediate(true);
            rightTable.setImmediate(true);
            leftTable.setVisibleColumns(getLeftHeader().getSingleColumns().toArray());
            leftTable.setColumnHeaders(getLeftHeader().getSingleHeaders().toArray(new String[getLeftHeader().getSingleHeaders().size()]));
            rightTable.setVisibleColumns(getRightHeader().getSingleColumns().toArray());
            rightTable.setColumnHeaders(getRightHeader().getSingleHeaders().toArray(new String[getRightHeader().getSingleHeaders().size()]));
            getResultsTable().setHeight(Constant.SIX_FIFTY_PX);
            leftTable.setHeight(Constant.SIX_FIFTY_PX);
            rightTable.setHeight(Constant.SIX_FIFTY_PX);
            for (Object propertyId : rightTable.getVisibleColumns()) {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
            }
            rightTable
                    .setDoubleHeaderVisibleColumns(getRightHeader().getDoubleColumns().toArray());
            rightTable
                    .setDoubleHeaderColumnHeaders(getRightHeader().getDoubleHeaders().toArray(new String[getRightHeader().getDoubleHeaders().size()]));
            rightTable.setDoubleHeaderVisible(true);
            getResultsTable().setDoubleHeaderMap(getLeftHeader().getDoubleHeaderMaps(), getRightHeader().getDoubleHeaderMaps());
            UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_FOUR_FIVE, TAB_DISCOUNT_PROJECTION_RESULTS.getConstant());
            getResultsTable().getLeftFreezeAsTable().setFilterBarVisible(true);
            getResultsTable().getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
            projectionDTO.setProjectionId(getProjectionId());
            projectionDTO.setSessionId(sessionDto.getSessionId() != null && !StringUtils.EMPTY.equals(sessionDto.getSessionId()) ? Integer.parseInt(sessionDto.getSessionId()) : 0);
            getResultsTable().getLeftFreezeAsTable().setFilterGenerator(new NMFilterGenerator(sessionDto));
    }

    public void viewChange(boolean viewChange) {
        projectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        if (viewOpg.getValue() != null) {
            projectionDTO.setView(String.valueOf(viewOpg.getValue()));
            if (CUSTOM.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                projectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionDTO.setIsCustomHierarchy(true);
                groupDdlb.setValue(null);
                projectionDTO.setGroupFilter(Constant.ALL_DISCOUNT_GROUP);

                if (firstGenerated && !generated) {
                    getTableLogic().clearAll();
                    getTableLogic().getControlTable().getContainerDataSource().removeAllItems();
                }
                levelFilterDdlb.setEnabled(false);
                loadCustomDDLB();
                projectionDTO.setCustomId(customIdToSelect);
            } else {
                customIdToSelect = customId;
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                projectionDTO.setIsCustomHierarchy(false);
                projectionDTO.setTpLevel(tradingPartnerNo);
                if (CUSTOMER.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projectionDTO.setRelationshipBuilderSid(projectionDTO.getCustRelationshipBuilderSid());
                    groupDdlb.setEnabled(true);
                    if (viewChange && firstGenerated) {
                        generateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    projectionDTO.setRelationshipBuilderSid(projectionDTO.getProdRelationshipBuilderSid());
                    groupDdlb.setEnabled(false);
                    groupDdlb.setValue(null);
                    if (viewChange && firstGenerated) {
                        generateLogic();
                    }
                }
            }
        }
    }

    public void generateLogic() {
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        getTableLogic().clearAll();
        projectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    public void loadLevelAndFilterValue() {
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelDdlb.setId("levelDdlb");
        levelFilterDdlb.removeAllItems();
        levelFilterDdlb.addItem(SELECT_ONE);
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE);
        levelFilterDdlb.setValue(SELECT_ONE);
        if (projectionDTO.isIsCustomHierarchy()) {
            if (!sessionDto.getCustomHierarchyMap().isEmpty() && customId != 0) {
                Utility.loadLevelValueForResult(levelDdlb, null, null, sessionDto.getCustomHierarchyMap().get(customId), Constant.CUSTOM_LABEL);
            }
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilterDdlb, null, sessionDto.getCustomerHierarchyList(), viewOpg.getValue().toString());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(levelDdlb, levelFilterDdlb, null, sessionDto.getProductHierarchyList(), viewOpg.getValue().toString());
        }
        LOGGER.debug("loadLevelAndFilterValue ends ");
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        HorizontalLayout controlLayout;
        
        tableVerticalLayout.addComponent(getResultsTable());
        controlLayout = getTableLogic().createControls();
        getTableLogic().sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
        tableVerticalLayout.addComponent(exceltable);
        exceltable.setVisible(false);
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
            projectionDTO.setFilterLevelNo(levelNo);
            projectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setView(String.valueOf(viewOpg.getValue()));
            projectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
            getTableLogic().setProjectionResultsData(projectionDTO, screenName);
            getResultsTable().getLeftFreezeAsTable().setFilterBarVisible(true);
            getResultsTable().getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
            projectionDTO.setProjectionId(getProjectionId());
            projectionDTO.setSessionId(sessionDto.getSessionId() != null && !StringUtils.EMPTY.equals(sessionDto.getSessionId()) ? Integer.parseInt(sessionDto.getSessionId()) : 0);
            getResultsTable().getLeftFreezeAsTable().setFilterGenerator(new NMFilterGenerator(sessionDto));
        }

    public void levelFilterDdlbChangeOption(boolean excelExport) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilterDdlb.getValue());
        
        int levelNo = levelFilterDdlb.getValue() == null ? 0 : Integer.parseInt(levelHierarchy.get(0).toString());
        if (levelNo < 0) {
            levelNo = 0;
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));       
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }         
        if (excelExport) {
            loadExcelResultTable(levelNo, hierarchyNo);
        } else {
            getTableLogic().clearAll();
            loadResultTable(levelNo, hierarchyNo);
        }

    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable(int levelNo, String hierarchyNo) {
            boolean isPeriodView = projectionDTO.getPivotView().contains(PERIOD.getConstant());
            getExcelResultBeanContainer().removeAllItems();
            projectionDTO.setFilterLevelNo(levelNo);
            projectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            loadDataToContainer(hierarchyNo,isPeriodView);
    }
    
 public void loadDataToContainer(String hierarchyNo,boolean isPeriodView) {
         CommonLogic commonLogic = new CommonLogic();
         CommercialDPRLogic commercialDPRLogic = new CommercialDPRLogic();
            ExcelUtils excelUtils = new ExcelUtils();
            String levelNo = commonLogic.getFirstLevelNo(projectionDTO.getProjectionId(), projectionDTO.getHierarchyIndicator());
            List<String> hierarchy = commonLogic.getSelectedHierarchyForExcel(sessionDto, hierarchyNo.equals("0") ? "" : hierarchyNo, projectionDTO.getHierarchyIndicator(), Integer.parseInt(levelNo));
            List<DiscountProjectionResultsDTO> resultList = commercialDPRLogic.getProjectionTotal(projectionDTO, isPeriodView);
            List<DiscountProjectionResultsDTO> discountList = commercialDPRLogic.getAllRSDiscount(projectionDTO, isPeriodView);
            List mainList = commercialDPRLogic.configureLevelsForExcel(0, Integer.parseInt(hierarchy.get(0)), projectionDTO, isPeriodView, hierarchy.get(1));
            List discountListForContract = commercialDPRLogic.getDiscountForAllContractExcel(projectionDTO, isPeriodView, hierarchy.get(1));
         ExcelUtils.setStaticRowToExcelContainer(getExcelResultBeanContainer(), resultList);
         ExcelUtils.setStaticRowToExcelContainer(getExcelResultBeanContainer(), discountList);
         if (!projectionDTO.isIsCustomHierarchy() && isPeriodView) {
             excelUtils.setDataToExcelContainerForDPR(mainList, discountListForContract, projectionDTO, getExcelResultBeanContainer());

         } else if (!projectionDTO.isIsCustomHierarchy() && !isPeriodView) {
             excelUtils.setDataToExcelContainerForDPRDiscountView(mainList, discountListForContract, projectionDTO, getExcelResultBeanContainer());

         } else {
                excelUtils.setDataToExcelContainerForDPRCustom(mainList, discountListForContract, projectionDTO, getExcelResultBeanContainer(), isPeriodView);
         }
       
 }
 

    @Override
    public void loadCustomDDLB() {
        LOGGER.debug("discount projection results loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);
        Object select = null;
        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
           if(sessionDto.getCustomerViewList().isEmpty()){
            customViewList = CommonLogic.getCustomViewList(getProjectionId());
            sessionDto.setCustomerViewList(customViewList);
            }else{
                customViewList=sessionDto.getCustomerViewList();
            }
            if (customViewList != null) {

                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    if (customIdToSelect == customSid) {
                        select = itemId;
                        break;
                    }
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }

            }
            if (customIdToSelect == 0) {
                levelDdlb.setEnabled(false);
                customDdlb.setValue(SELECT_ONE);
                if (firstGenerated) {
                    generateLogic();
                }
            } else {
                levelDdlb.setEnabled(true);
                customDdlb.select(select);
            }
        }
        LOGGER.debug("discount projection results loadCustomDDLB ends ");
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            if (projectionDTO.isIsFilter()) {
                levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
                levelFilterDdlb.setValue(SELECT_ONE);
                projectionDTO.setIsFilter(false);
                getTableLogic().clearAll();
                levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
            } else {
                getTableLogic().clearAllExceptCurrentPage();
            }
            String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
            projectionDTO.setHierarchyIndicator(hierarchyIndicator);
            if (!isExpand) {
                levelNo--;
            }
            getTableLogic().loadExpandData(levelNo);
        }
    }

    private void loadTradingPartner()  {
        if (String.valueOf(viewOpg.getValue()).equals(Constant.PRODUCT_LABEL)) {
            projectionDTO.setTpLevel(0);
        } else if (sessionDto.getTradingPartner() == 0) {
            DiscountProjectionResultsLogic logic = new DiscountProjectionResultsLogic();
            String query = logic.getTradingPartnerLevel(projectionDTO.getProjectionId());
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                int tpLevel = Integer.parseInt(String.valueOf(list.get(0)));
                sessionDto.setTradingPartner(tpLevel);
                projectionDTO.setTpLevel(tpLevel);
            }
        } else {
            projectionDTO.setTpLevel(sessionDto.getTradingPartner());
        }

    }

    @SuppressWarnings("serial")
    private void configureExcelResultTable() {

        setExcelResultBeanContainer(new ExtTreeContainer<>(DiscountProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP));
        getExcelResultBeanContainer().setColumnProperties(getFullHeader().getProperties());
         exceltable.setRefresh(BooleanConstant.getFalseFlag());
        exceltable.setContainerDataSource(getExcelResultBeanContainer());
        exceltable.setVisibleColumns(getFullHeader().getSingleColumns().toArray());
        exceltable.setColumnHeaders(getFullHeader().getSingleHeaders().toArray(new String[getFullHeader().getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(getFullHeader().getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(getFullHeader().getDoubleHeaders().toArray(new String[getFullHeader().getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(getFullHeader().getDoubleHeaderMaps());
        exceltable.setVisible(false);
    }

    public int getTabNumber() {
        return Constant.FIVE;
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            // Refresh with new temp values
        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
            // Refresh after from period change in DS
        }
    }

    @UiHandler("viewOpg")
    public void viewChangeOption(Property.ValueChangeEvent event) {
        viewChange(true);
    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS);
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequencyDdlb.setValue(String.valueOf(value));
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.select(String.valueOf(value));
            }
            value = map.get(Constant.PERIOD_ORDER);
            if (value != null) {
                periodOrderOpg.setValue(String.valueOf(value));
            }
            value = map.get("Pivot");
            if (value != null) {
                pivotViewOpg.setValue(String.valueOf(value));
            }
            value = map.get("Actuals/Projections");
            if (value != null) {
                actualOrProjectionsOpg.setValue(String.valueOf(value));
            }
            value = map.get("View");
            if (value != null) {
                viewOpg.setValue(String.valueOf(value));
            }
        }
    }

    public void fetchDiscountsFromSave() {
        try {
            discountlist = new ArrayList();
            Map<Object, Object> map = CommonLogic.getNMProjectionSelection(sessionDto.getProjectionId(), TabNameUtil.DISCOUNT_PROJECTION);
            if (map != null && !map.isEmpty()) {
                String discountType = String.valueOf(map.get("Level"));                
                List<String> discountNames = new LinkedList<>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));                
                discountlist = CommonLogic.getDiscountNoList(discountNames, "Program".equals(discountType), sessionDto);
                sessionDto.setDiscountRSlist(discountlist);
                sessionDto.setDiscountRSlistUpdated(true);                
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("groupDdlb")
    public void groupDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.debug("groupDdlbChangeOption ValueChangeEvent initiated ");
        groupChange(true);
    }

    public void groupChange(boolean groupChange) {
        if (groupDdlb.getValue() != null && (projectionDTO.isIsCustomHierarchy() || !Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator()))) {
            projectionDTO.setGroupFilter(String.valueOf(groupDdlb.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (projectionDTO.isIsCustomHierarchy() || !Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator()))) {
            getTableLogic().groupChange();
        }
    }

    public void saveButtonLogic() {
        try {
            if (isIsTabVisible()) {
                Map map = new HashMap();
                map.put(Constant.FREQUENCY_SMALL, String.valueOf(frequencyDdlb.getValue()));
                map.put(Constant.PERIOD_ORDER, String.valueOf(periodOrderOpg.getValue()));
                map.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
                map.put("Actuals/Projections", String.valueOf(actualOrProjectionsOpg.getValue()));
                map.put("Pivot", String.valueOf(pivotViewOpg.getValue()));
                map.put("View", String.valueOf(viewOpg.getValue()));
                CommonLogic.saveProjectionSelection(map, sessionDto.getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void securityForButton() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), getCommercialConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION_RESULTS);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generateBtn.setVisible(BooleanConstant.getFalseFlag());
                expandBtn.setVisible(BooleanConstant.getFalseFlag());
                collapseBtn.setVisible(BooleanConstant.getFalseFlag());
                newBtn.setVisible(BooleanConstant.getFalseFlag());
                editBtn.setVisible(BooleanConstant.getFalseFlag());
            }
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(NMDiscountProjection.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    public final void configure() {
        if (flag) {
            configureFields();
            securityForButton();
            flag = false;
        }
    }

    public void makeCheckedVariableMenuItem() {

        if (customMenuItem != null) {
            for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                string.setChecked(true);
            }
        }
    }

    public void defaultFocus() {
        frequencyDdlb.focus();
}

    public boolean isIsTabVisible() {
        return isTabVisible;
    }

    public void setIsTabVisible(boolean isTabVisible) {
        this.isTabVisible = isTabVisible;
    }

}
