/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastDiscountProjectionResults;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.discountprojectionresults.dto.NMFilterGenerator;
import static com.stpl.app.galforecasting.discountprojectionresults.logic.NMDPRLogic.executeSelectQuery;
import com.stpl.app.galforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.galforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.DiscountProjectionResultsLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.ForecastForm;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.TAB_DISCOUNT_PROJECTION_RESULTS;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.galforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.galforecasting.utils.DPRChart;
import com.stpl.app.galforecasting.utils.NmSPRGraphWindow;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
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
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_AMT;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_RATE;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.REBATE_PER_UNIT;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.UI;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public class NMDiscountProjectionResults extends ForecastDiscountProjectionResults {

    private static final Logger LOGGER = Logger
            .getLogger(NMDiscountProjectionResults.class);

    SessionDTO session;

    String screenName = StringUtils.EMPTY;

    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();

    List<List<String>> discountlist = new ArrayList<List<String>>();
    boolean generated = false;
    boolean firstGenerated = false;
    boolean flag = false;
    int tradingPartnerNo = 0;
    ForecastForm form;
    @UiField("variables")
    protected OptionGroup variables;
    public HorizontalLayout controlLayout;
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };

    public NMDiscountProjectionResults(SessionDTO session, String screenName, ForecastForm form) {
        super(screenName, session);
        this.session = session;
        this.screenName = screenName;
        this.form = form;
        if (flag) {
            configure();
        }
        flag = true;
    }

    @Override
    protected void loadFrequency() {
        LOGGER.info("ProjectionResults ValueChangeEvent initiated ");
        CommonUtils.frequenceValueChange(frequencyDdlb.getValue(), historyDdlb, session);
        LOGGER.info("ProjectionResults ValueChangeEvent ends ");
    }

    @Override
    protected void resetButtonLogic() {
        frequencyDdlb.setValue(QUARTERLY);
        historyDdlb.setValue("4 Quarters");
        actualOrProjectionsOpg.setValue(ACTUALS.getConstant());
        periodOrderOpg.setValue(ASCENDING.getConstant());
        pivotViewOpg.setValue(Constant.PERIOD);
    }

    @Override
    protected void generateButtonLogic() {
        if (((Collection) variables.getValue()).isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No variables were selected", "Please select at least one variable and try again.");
            return;
        } else {
            generated = true;
            firstGenerated = true;
            int pageNo = tableLogic.getItemsPerPage();
            if (loadProjectionSelection()) {
                if (discountlist != null && discountlist.size() > 1 && discountlist.get(1).size() > 0) {
                    try {
                        tableVerticalLayout.removeAllComponents();
                        tableLogic = new DPRTableLogic();
                        resultsTable = new FreezePagedTreeTable(tableLogic);
                        initializeResultTable();
                        configureResultTable();
                        addResultTable();
                        String groupFilter = String.valueOf(groupDdlb.getValue());
                        if (groupFilter.equals(Constant.NULL)) {
                            groupFilter = "All Discount Groups";
                        } else {

                        }
                        projectionDTO.setGroupFilter(groupFilter);
                        loadTradingPartner();
                        generateLogic();
                        tableLogic.setItemsPerPage(pageNo);
                    } catch (SystemException ex) {
                        java.util.logging.Logger.getLogger(NMDiscountProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (PortalException ex) {
                        java.util.logging.Logger.getLogger(NMDiscountProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        exceltable.setRefresh(Boolean.TRUE);
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Discount Projection Results", "Discount Projection Results", "Discount Projection Results.xls", false);
        exp.export();
    }

    @Override
    protected void customDdlbChangeOptionLogic() {

        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, levelDdlb);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        levelDdlb.setEnabled(customId != 0);
        projectionDTO.setTpLevel(tpNo);
        projectionDTO.setCustomId(customId);
        projectionDTO.setView(Constant.CUSTOM);
        if (!generated && firstGenerated) {
            generateLogic();
        }
        levelFilterDdlb.setEnabled(false);
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void graphExportLogics() {
        LOGGER.info("graphExportLogic method starts");
        List<DiscountProjectionResultsDTO> chartList = new ArrayList<DiscountProjectionResultsDTO>();
        for (DiscountProjectionResultsDTO dto : resultBeanContainer.getItemIds()) {
            chartList.add(dto);
        }
        if (projectionDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
            projectionDTO.setActualsOrProjections("Actuals and Projections");
        }
        final DPRChart chart = new DPRChart(chartList, projectionDTO, fullHeader);
        final NmSPRGraphWindow prGraphWindow = new NmSPRGraphWindow(chart.getChart(), "Discount Projection Results");
        UI.getCurrent().addWindow(prGraphWindow);
        prGraphWindow.focus();
        LOGGER.info("graphExportLogic method ends");
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
        variables.addItem(DISCOUNT_RATE.getConstant());
        variables.addItem(REBATE_PER_UNIT.getConstant());
        variables.addItem(DISCOUNT_AMT.getConstant());
        variables.focus();
        variables.setImmediate(true);
        variables.select(DISCOUNT_RATE.getConstant());
        variables.select(REBATE_PER_UNIT.getConstant());
        variables.select(DISCOUNT_AMT.getConstant());
        variables.addStyleName(Constant.HORIZONTAL);
        variables.addStyleName("optiongroupextrawidth");
        frequencyDdlb.setValue(QUARTERLY);
        historyDdlb.setValue("4 Quarters");
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
        viewOpg.addItem(Constant.PRODUCT);
        viewOpg.addItem(Constant.CUSTOM);
        viewOpg.setValue(Constant.CUSTOMER_SMALL);

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
        try {
            loadGroupFilter();
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
    }

    public void loadGroupFilter() throws SystemException, PortalException {

        DiscountProjectionResultsLogic logic = new DiscountProjectionResultsLogic();
        groupDdlb.addItem("All Discount Groups");
        groupDdlb.setNullSelectionItemId("All Discount Groups");
        IndexedContainer con = new IndexedContainer();
        con.removeAllItems();
        int projectionId = session.getProjectionId();
        int sessionId = Integer.valueOf(session.getSessionId());
        String query = logic.getGroup(projectionId, sessionId);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                con.addItem(Constant.DISCOUNT + list.get(i));
            }
        }
        groupDdlb.setContainerDataSource(con);
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
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setPageLength(18);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequencyDdlb.getValue();
        boolean toFreq = false;
        int historyNum = 0;
        if (freq != null) {
            if (!SELECT_ONE.equals(String.valueOf(freq))) {
                toFreq = true;
                projectionDTO.setFrequency(String.valueOf(freq));
            }
        }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
        if (hist != null) {
            if (!SELECT_ONE.equals(hist.toString())) {
                toHist = true;
                projectionDTO.setHistory(hist.toString());

                if (freq.equals(QUARTERLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Semi-Annual", StringUtils.EMPTY).trim());

                } else if (freq.equals(MONTHLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());

                } else if (freq.equals(ANNUALLY)) {
                    String histPeriod = String.valueOf(hist);
                    historyNum = Integer.valueOf(histPeriod.trim());
                }
            }
        }
        if (toFreq && toHist) {
            toRet = true;
            discountlist = new ArrayList<List<String>>();
            NMDiscountProjection dp = form.getDiscountProjection();
            if (dp != null) {
                String discountType = form.getDiscountProjection().getDiscountType();
                if (form.getDiscountProjection().isDiscountGenerated() && discountType != null) {
                    List<String> discountNames = form.getDiscountProjection().getDiscountNamesList();
                    discountlist = CommonLogic.getDiscountNoList(projectionId, discountNames, "Program".equals(discountType),session.getUserId(),session.getSessionId());
                } else if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                    fetchDiscountsFromSave();
                }
            }
            projectionDTO.setSessionDTO(session);
            projectionId = session.getProjectionId();
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), session));
            projectionDTO.setProjection(StringUtils.EMPTY + projectionDTO.getProjectionNum());
            projectionDTO.setUserId(Integer.valueOf(session.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
            projectionDTO.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
            projectionDTO.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
            projectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));

            projectionDTO.setProjectionId(session.getProjectionId());
            projectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
            projectionDTO.setPivotView(String.valueOf(pivotViewOpg.getValue()));
            tablePanel.setCaption(String.valueOf(pivotViewOpg.getValue()) + " Pivot View");

            projectionDTO.setCustomId(customId);
            projectionDTO.setView(String.valueOf(viewOpg.getValue()));
            projectionDTO.setDiscountList(new ArrayList<List<String>>(discountlist));
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            Collection c = (Collection) variables.getValue();
            List<String> list = new ArrayList();
            for (Object s : c) {
                list.add(String.valueOf(s));
            }
            projectionDTO.setdPVariablesList(list);
            viewChange(false);
        }

        return toRet;
    }

    private void configureResultTable() {
        try {
            tableLogic.setTreeNodeMultiClick(false);
            tableLogic.setPageLength(20);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
            tableLogic.sinkItemPerPageWithPageLength(false);
            fullHeader = new CustomTableHeaderDTO();
            leftHeader = HeaderUtils.getNMDiscountProjectionResultsLeftTableColumns(projectionDTO, fullHeader);
            rightHeader = HeaderUtils.getNMDiscountProjectionResultsRightTableColumns(projectionDTO, fullHeader);

            resultBeanContainer = new CustomTreeContainer<>(DiscountProjectionResultsDTO.class);
            resultBeanContainer.setColumnProperties(fullHeader.getProperties());
            tableLogic.setContainerDataSource(resultBeanContainer);
            final ExtFilterTreeTable leftTable = resultsTable
                    .getLeftFreezeAsTable();
            final ExtFilterTreeTable rightTable = resultsTable
                    .getRightFreezeAsTable();
            leftTable.setImmediate(true);
            rightTable.setImmediate(true);
            leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
            leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
            rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
            resultsTable.setHeight("650px");
            leftTable.setHeight("650px");
            rightTable.setHeight("650px");
            for (Object propertyId : rightTable.getVisibleColumns()) {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
            }
            rightTable
                    .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
            rightTable
                    .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
            rightTable.setDoubleHeaderVisible(true);
            resultsTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
            UiUtils.setExtFilterTreeTableColumnWidth(rightTable, 145, TAB_DISCOUNT_PROJECTION_RESULTS.getConstant());
            resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
            resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
            projectionDTO.setProjectionId(projectionId);
            projectionDTO.setSessionId(session.getSessionId() != null && !StringUtils.EMPTY.equals(session.getSessionId()) ? Integer.valueOf(session.getSessionId()) : 0);
            resultsTable.getLeftFreezeAsTable().setFilterGenerator(new NMFilterGenerator(projectionDTO));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
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
                projectionDTO.setGroupFilter("All Discount Groups");

                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                levelFilterDdlb.setEnabled(false);
                loadCustomDDLB();
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
        tableLogic.clearAll();
        projectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    public void loadLevelAndFilterValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelFilterDdlb.removeAllItems();
        levelFilterDdlb.addItem(SELECT_ONE);
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE);
        levelFilterDdlb.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionDTO.isIsCustomHierarchy()) {
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchy(projectionId, projectionDTO.getCustomerLevelNo());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchy(projectionId, projectionDTO.getProductLevelNo());
        }
        if (hierarchy != null) {
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                levelDdlb.addItem(itemId);
                levelDdlb.setItemCaption(itemId, caption);
                levelFilterDdlb.addItem(itemId);
                levelFilterDdlb.setItemCaption(itemId, caption);
            }
        }
        LOGGER.info("loadLevelAndFilterValue ends ");
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        controlLayout = tableLogic.createControls();
        tableLogic.sinkItemPerPageWithPageLength(false);
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
        tableLogic.setProjectionResultsData(projectionDTO, false, screenName);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        projectionDTO.setProjectionId(projectionId);
        projectionDTO.setSessionId(session.getSessionId() != null && !StringUtils.EMPTY.equals(session.getSessionId()) ? Integer.valueOf(session.getSessionId()) : 0);
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new NMFilterGenerator(projectionDTO));
    }

    public void levelFilterDdlbChangeOption(boolean excelExport) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilterDdlb.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
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
            tableLogic.clearAll();
            loadResultTable(levelNo, hierarchyNo);
        }

    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable(int levelNo, String hierarchyNo) {
        try {
            excelResultBeanContainer.removeAllItems();
            projectionDTO.setFilterLevelNo(levelNo);
            projectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            int resultsCount = nmDPRLogic.getConfiguredProjectionResultsCount(new Object(), projectionDTO, true);
            List<DiscountProjectionResultsDTO> resultList1 = nmDPRLogic.getConfiguredProjectionResults(new Object(), 0, resultsCount, projectionDTO);
            loadDataToContainer(resultList1, null);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
    }

    public void loadDataToContainer(List<DiscountProjectionResultsDTO> resultList, Object parentId) throws SystemException, PortalException {
        for (DiscountProjectionResultsDTO dto : resultList) {
            excelResultBeanContainer.addBean(dto);
            if (parentId != null) {
                excelResultBeanContainer.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBeanContainer.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBeanContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) throws SystemException, PortalException {
        projectionDTO.setFilterLevelNo(0);
        projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = nmDPRLogic.getConfiguredProjectionResultsCount(id, projectionDTO, true);
        List<DiscountProjectionResultsDTO> resultList = nmDPRLogic.getConfiguredProjectionResults(id, 0, count, projectionDTO);
        loadDataToContainer(resultList, id);
    }

    public void loadCustomDDLB() {
        LOGGER.info("discount projection results loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);
        Object select = null;
        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            customViewList = CommonLogic.getCustomViewList(projectionId);
            if (customViewList != null) {

                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    if (customIdToSelect == customSid) {
                        select = itemId;
                    }
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }

            }
            if (customIdToSelect == 0) {
                levelDdlb.setEnabled(false);
                customDdlb.setValue(SELECT_ONE);
                if (firstGenerated && !generated) {
                    generateLogic();
                }
            } else {
                levelDdlb.setEnabled(true);
                customDdlb.select(select);
            }
        }
        LOGGER.info("discount projection results loadCustomDDLB ends ");
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            if (projectionDTO.isIsFilter()) {
                levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
                levelFilterDdlb.setValue(SELECT_ONE);
                projectionDTO.setIsFilter(false);
                tableLogic.clearAll();
                levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
            String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
            projectionDTO.setHierarchyIndicator(hierarchyIndicator);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }
    }

    private void loadTradingPartner() throws SystemException, PortalException {
        if (String.valueOf(viewOpg.getValue()).equals(Constant.PRODUCT)) {
            projectionDTO.setTpLevel(0);
        } else {
            DiscountProjectionResultsLogic logic = new DiscountProjectionResultsLogic();
            String query = logic.getTradingPartnerLevel(projectionDTO.getProjectionId());
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                int tpLevel = Integer.valueOf(String.valueOf(list.get(0)));
                projectionDTO.setTpLevel(tpLevel);
            }
        }
    }

    @SuppressWarnings("serial")
    private void configureExcelResultTable() {

        excelResultBeanContainer = new CustomTreeContainer<DiscountProjectionResultsDTO>(DiscountProjectionResultsDTO.class);
        excelResultBeanContainer.setColumnProperties(fullHeader.getProperties());
        exceltable.setRefresh(Boolean.FALSE);
        exceltable.setContainerDataSource(excelResultBeanContainer);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setVisible(false);
    }

    public int getTabNumber() {
        return Constant.FOUR;
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
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, "Discount Projection Results");
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
            Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TabNameUtil.DISCOUNT_PROJECTION);
            if (map != null && !map.isEmpty()) {
                String discountType = String.valueOf(map.get("Level"));
                List<String> discountNames = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                discountlist = CommonLogic.getDiscountNoList(projectionId, discountNames, "Program".equals(discountType),session.getUserId(),session.getSessionId());
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("groupDdlb")
    public void groupDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.info("groupDdlbChangeOption ValueChangeEvent initiated ");
        groupChange(true);
    }

    public void groupChange(boolean groupChange) {
        if (groupDdlb.getValue() != null && (projectionDTO.isIsCustomHierarchy() || !Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator()))) {
            projectionDTO.setGroupFilter(String.valueOf(groupDdlb.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (projectionDTO.isIsCustomHierarchy() || !Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator()))) {
            tableLogic.groupChange();
        }
    }

    public void saveButtonLogic() {
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, String.valueOf(frequencyDdlb.getValue()));
            map.put(Constant.PERIOD_ORDER, String.valueOf(periodOrderOpg.getValue()));
            map.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
            map.put("Actuals/Projections", String.valueOf(actualOrProjectionsOpg.getValue()));
            map.put("Pivot", String.valueOf(pivotViewOpg.getValue()));
            map.put("View", String.valueOf(viewOpg.getValue()));
            CommonLogic.saveProjectionSelection(map, session.getProjectionId(), "Discount Projection Results");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public void securityForButton() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION_RESULTS);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES)).isFunctionFlag())) {
                generateBtn.setVisible(Boolean.FALSE);
                expandBtn.setVisible(Boolean.FALSE);
                collapseBtn.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);
            }
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configure() {
        if (flag) {
            configureFields();
            securityForButton();
            flag = false;
        }
    }

}
