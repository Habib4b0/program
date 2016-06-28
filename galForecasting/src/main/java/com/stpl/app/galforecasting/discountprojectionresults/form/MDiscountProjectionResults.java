/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastDiscountProjectionResults;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.galforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.galforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.galforecasting.utils.Constant.SPACE;
import static com.stpl.app.galforecasting.utils.Constant.YEAR;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.model.CustomViewMaster;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PIVOT_VIEW;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author pvinoth
 */
public class MDiscountProjectionResults extends ForecastDiscountProjectionResults {

    private static final Logger LOGGER = Logger
            .getLogger(MDiscountProjectionResults.class);
    CommonUtils commonUtils = new CommonUtils();
    SessionDTO session;
    String screenName = StringUtils.EMPTY;
    final private BeanItemContainer<String> historyBean = new BeanItemContainer<String>(String.class);
    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    boolean firstGenerated = false;
    boolean generated = false;
//    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    public HorizontalLayout controlLayout;
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    private ExtCustomTreeTable exceltable;
    private CustomTreeContainer<DiscountProjectionResultsDTO> excelResultBean = new CustomTreeContainer<DiscountProjectionResultsDTO>(
            DiscountProjectionResultsDTO.class);
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };

    public MDiscountProjectionResults(SessionDTO session, String screenName) throws Exception {
        super(screenName, session);
        this.session = session;
        this.screenName = screenName;
        projectionDTO.setSessionDTO( session);
        configureFields();
        setButtonSecurity();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void loadFrequency() {
        LOGGER.info("DPR ValueChangeEvent initiated with frequency -->"
                + frequencyDdlb.getValue());
        if (frequencyDdlb.getValue() == null) {
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE);
            historyDdlb.setNullSelectionItemId(SELECT_ONE);
        } else {
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE);
            historyDdlb.setNullSelectionItemId(SELECT_ONE);
            String historyConstant = StringUtils.EMPTY;
            if (ANNUALLY.equals(String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(ANNUALLY, YEAR));
                if (historyBean.size() >= 1) {
                    historyConstant = "1 Year";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (SEMI_ANNUALLY.getConstant().equals(String.valueOf(frequencyDdlb
                    .getValue()))) {
                historyBean.addAll(loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant()));
                if (historyBean.size() >= 2) {
                    historyConstant = "2 Semi-Annual Periods";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (QUARTERLY.equals(
                    String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(QUARTERLY, QUARTERS.getConstant()));
                if (historyBean.size() >= 4) {
                    historyConstant = "4 Quarters";
                } else {
                    historyConstant = SELECT_ONE;
                }

            } else if (MONTHLY.equals(
                    String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(MONTHLY, MONTHS.getConstant()));
                if (historyBean.size() >= 12) {
                    historyConstant = "12 Months";
                } else {
                    historyConstant = SELECT_ONE;
                }
            }

            historyDdlb.setContainerDataSource(historyBean);
            historyDdlb.setValue(historyConstant);
        }
        LOGGER.info("DPR ValueChangeEvent ends ");
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period) {
        LOGGER.info("Entering loadHistory method");
        List<String> history = new ArrayList<String>();
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = commonUtils.getHistoryEndDetails(session, frequency);
            endValue = commonUtils.getProjections(session.getForecastDTO().getHistoryStartDate(), commonUtils.getDate(historyEndDetails.get(HISTORY_END_MONTH), historyEndDetails.get(HISTORY_END_YEAR)), frequency);
            history = CommonUtils.getHistoryDdlbList(endValue, frequency, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.info("End of loadHistory method");
        return history;
    }

    @Override
    protected void resetButtonLogic() {
        new AbstractNotificationUtils() {

            @Override
            public void noMethod() {

            }

            @Override
            public void yesMethod() {
                frequencyDdlb.setValue(QUARTERLY);
                historyDdlb.setValue("4 Quarters");
                discountOpg.setValue(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
                actualOrProjectionsOpg.setValue(Constant.ACTUALS);
                periodOrderOpg.setValue(ASCENDING.getConstant());
                pivotViewOpg.setValue(Constant.PERIOD);
                viewOpg.setValue(Constant.CUSTOMER_SMALL);
                levelDdlb.setValue(SELECT_ONE);
                levelFilterDdlb.setValue(SELECT_ONE);
                customDdlb.setValue(SELECT_ONE);
                tableLogic.clearAll();
                tableLogic.getControlTable().getContainerDataSource().removeAllItems();
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");
    }

    @Override
    protected void generateButtonLogic() {
        LOGGER.info("generate button click listener starts ");
        generated = true;
        firstGenerated = true;
        int pageNo = tableLogic.getItemsPerPage();
        projectionDTO.setCustomFlag(false);
        if (loadProjectionSelection()) {
            try {
                tableVerticalLayout.removeAllComponents();
                if (Constant.PERIOD.equalsIgnoreCase(pivotViewOpg.getValue().toString())) {
                    tablePanel.setCaption("Period Pivot View");
                } else {
                    tablePanel.setCaption("Discount Pivot View");
                }
                tableLogic = new DPRTableLogic();
                resultsTable = new FreezePagedTreeTable(tableLogic);
                initializeResultTable();
                configureResultTable();
                addResultTable();
                generateLogic();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            MessageBox.showPlain(Icon.ERROR, "Generate Error", "You must select a Frequency and History from the drop down list boxes.", ButtonId.OK);
        }
        generated = false;
        LOGGER.info("generate button click listener ends ");
    }

    @Override
    protected void excelButtonLogic() {
        configureExcelResultTable();
        initialProjSelDTO.setCustomerLevelNo(projectionDTO.getCustomerLevelNo());
        initialProjSelDTO.setHierarchyIndicator(projectionDTO.getHierarchyIndicator());
        initialProjSelDTO.setProductLevelNo(projectionDTO.getProductLevelNo());
        levelFilterDdlbChangeOption(true);
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), Constant.DISCOUNT_PROJECTION_RESULTS, Constant.DISCOUNT_PROJECTION_RESULTS, "Discount Projection Results.xls", false);
        exp.export();
        layout.removeComponent(exceltable);
    }

    @Override
    protected void customDdlbChangeOptionLogic() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, levelDdlb);
        projectionDTO.setCustomId(customId);
        if (String.valueOf(customDdlb.getValue()) != null || !SELECT_ONE.equalsIgnoreCase(String.valueOf(customDdlb.getValue()))) {
            editBtn.setEnabled(true);
            if (!generated && firstGenerated) {
                projectionDTO.setCustomFlag(true);
                generateLogic();
            }
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void graphExportLogics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void expandButtonLogic() {
        tableLogic.setRefresh(false);
        expandCollapseLevelOption(true, levelDdlb.getValue());
        tableLogic.setRefresh(true);
    }

    @Override
    protected void collapseButtonLogic() {
        tableLogic.setRefresh(false);
        expandCollapseLevelOption(false, levelDdlb.getValue());
        tableLogic.setRefresh(true);
    }

    private void configureFields() {
        CommonUtils.loadDDLBFromProperties(frequencyDdlb, Constant.FREQUENCY);
        frequencyDdlb.setValue(QUARTERLY);
        frequencyDdlb.focus();
        historyDdlb.addItem("4 Quarters");
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
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
            setProjectionSelection();
        }
        addResultTable();
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName("valo-theme-extfiltertable");
    }

    public boolean loadProjectionSelection() {
        boolean flag = false;
        Object freq = frequencyDdlb.getValue();
        Object hist = historyDdlb.getValue();
        boolean freqFlag = false;
        int historyNum = 0;
        if (freq != null) {
            if (!SELECT_ONE.equals(freq.toString())) {
                freqFlag = true;
                projectionDTO.setFrequency(freq.toString());
            }
        }
        boolean histFlag = false;
        if (hist != null) {
            if (!SELECT_ONE.equals(hist.toString())) {
                histFlag = true;
                projectionDTO.setHistory(hist.toString());
                if (freq.equals(QUARTERLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                    historyNum = String.valueOf(hist).endsWith("Periods") ? Integer.valueOf(String.valueOf(hist).replace("Semi-Annual Periods", StringUtils.EMPTY).trim())
                            : Integer.valueOf(String.valueOf(hist).replace("Semi-Annual Period", StringUtils.EMPTY).trim());

                } else if (freq.equals(MONTHLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());

                } else if (freq.equals(ANNUALLY)) {
                    String histPeriod = String.valueOf(hist);
                    if (histPeriod.endsWith(Constant.YEAR) || histPeriod.endsWith("Years")) {
                        historyNum = histPeriod.endsWith(Constant.YEAR) ? Integer.valueOf(String.valueOf(hist).replace(Constant.YEAR, StringUtils.EMPTY).trim()) : Integer.valueOf(String.valueOf(hist).replace("Years", StringUtils.EMPTY).trim());
                    }
                }
            }
        }
        if (freqFlag && histFlag) {
            flag = true;
            projectionId = session.getProjectionId();
            projectionDTO.setUserId(Integer.valueOf(session.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setActualsOrProjections(actualOrProjectionsOpg.getValue().toString());
            projectionDTO.setProjectionOrder(periodOrderOpg.getValue().toString());
            projectionDTO.setPivotView(pivotViewOpg.getValue().toString());
            projectionDTO.setView(viewOpg.getValue().toString());
            projectionDTO.setProjectionId(projectionId);
            projectionDTO.setMandatedOrSupp(discountOpg.getValue().toString());
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.setCustomId(customId);
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setCustHierarchySID(session.getCustomerHierarchyId());
            projectionDTO.setProdHierarchySID(session.getProductHierarchyId());
            tablePanel.setCaption(pivotViewOpg.getValue().toString() + SPACE + PIVOT_VIEW.getConstant());
            viewChange(false);
        }
        return flag;
    }

    @UiHandler("viewOpg")
    public void viewChangeOption(Property.ValueChangeEvent event) {
        viewChange(true);
    }

    public void viewChange(boolean viewChange) {
        projectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        if (viewOpg.getValue() != null) {
            if (Constant.CUSTOM.equals(String.valueOf(viewOpg.getValue()))) {
                projectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionDTO.setIsCustomHierarchy(true);
                expandBtn.setEnabled(Boolean.TRUE);
                collapseBtn.setEnabled(Boolean.TRUE);
                levelDdlb.setValue(SELECT_ONE);
                levelDdlb.setEnabled(false);
                levelFilterDdlb.setValue(SELECT_ONE);
                levelFilterDdlb.setEnabled(false);
                projectionDTO.setCustomFlag(true);
                projectionDTO.setView(String.valueOf(viewOpg.getValue()));
                if (firstGenerated && !generated) {
                    configureResultTable();
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                customIdToSelect = 0;
                loadCustomDDLB();
                levelDdlb.setEnabled(false);
                levelFilterDdlb.setEnabled(false);
            } else {
                customIdToSelect = customId;
                customDdlb.setValue(SELECT_ONE);
                expandBtn.setEnabled(true);
                collapseBtn.setEnabled(true);
                projectionDTO.setIsCustomHierarchy(false);
                levelFilterDdlb.setEnabled(true);
                projectionDTO.setCustomFlag(false);
                projectionDTO.setView(String.valueOf(viewOpg.getValue()));
                levelDdlb.setEnabled(true);
                if (Constant.CUSTOMER_SMALL.equals(String.valueOf(viewOpg.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    if (viewChange && firstGenerated) {
                        tableLogic.clearAll();
                        generateLogic();
                    }
                } else if (Constant.PRODUCT.equals(String.valueOf(viewOpg.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    if (viewChange && firstGenerated) {
                        tableLogic.clearAll();
                        generateLogic();
                    }
                }
            }
        }
    }

    public void loadCustomDDLB() {
        LOGGER.info("loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
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
            }
            if (customIdToSelect == 0) {
                customDdlb.setValue(SELECT_ONE);
            } else {
                customDdlb.select(customIdToSelect);
                projectionDTO.setCustomId(customIdToSelect);
            }
        }
        LOGGER.info("loadCustomDDLB ends ");
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        tableLogic.setPageLength(20);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        fullHeader = new CustomTableHeaderDTO();
        String viewString = String.valueOf(viewOpg.getValue());
        projectionDTO.setMarketTypeValue(session.getMarketTypeValue());
        leftHeader = HeaderUtils.getDiscountProjectionResultsLeftTableColumns(fullHeader, viewString);
        rightHeader = HeaderUtils.getDiscountProjectionResultsRightTableColumn(projectionDTO, fullHeader);
        if (projectionDTO.getProjectionOrder().equalsIgnoreCase("Descending")) {
            Collections.reverse(rightHeader.getDoubleColumns());
            Collections.reverse(rightHeader.getDoubleHeaders());

        }
        resultBeanContainer = new CustomTreeContainer<DiscountProjectionResultsDTO>(DiscountProjectionResultsDTO.class);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        final ExtFilterTreeTable leftTable = resultsTable
                .getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = resultsTable
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        leftTable.setColumnWidth(Constant.GROUP, 300);
        leftTable.setDoubleHeaderColumnWidth(Constant.GROUP, 300);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        resultsTable.setDoubleHeaderVisible(true);
        leftTable
                .setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable
                .setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        resultsTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
        projectionDTO.setRightHeaderDoubleColumns(rightHeader.getDoubleColumns());

        if (Constant.DISCOUNT_SMALL.equalsIgnoreCase(projectionDTO.getPivotView())) {
            int columnIndex = 2;
            List<Object> pcNameList = dprLogic.getProgramCodeName(projectionDTO);
            rightTable.setColumnCollapsingAllowed(true);
            rightTable.setImmediate(true);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(0), false);
            rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(0), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(1), false);
            rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(1), true);
            if (pcNameList != null && !pcNameList.isEmpty()) {
                for (int i = 0; i < pcNameList.size(); i++) {
                    rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(columnIndex++), true);
                }
            }
            int suppIndex = columnIndex;
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(suppIndex), false);
            rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(suppIndex++), true);
            if (pcNameList != null && !pcNameList.isEmpty()) {
                for (int i = 0; i < pcNameList.size(); i++) {
                    rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(suppIndex++), true);
                }
            }

            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                private static final long serialVersionUID = -4215343675341144627L;

                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    LOGGER.info("SupplementalDiscountProjection addColumnExpandIconListener initiated ");
                    int columnIndex = 2;
                    List<Object> pcNameList = dprLogic.getProgramCodeName(projectionDTO);
                    if (pcNameList != null && !pcNameList.isEmpty()) {
                        for (int i = 0; i < pcNameList.size(); i++) {
                            columnIndex++;
                        }
                    }
                    int suppIndex = columnIndex;
                    if (event.getPropertyId().equals(Constant.TOTALDISCOUNT)) {
                        LOGGER.info(event.getPropertyId().toString() + "=====" + !event.isExpanded());
                        rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(1), !event.isExpanded());
                        rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(suppIndex), !event.isExpanded());
                        if (!event.isExpanded()) {
                            for (int i = 1; i < rightHeader.getDoubleColumns().size(); i++) {
                                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i), !event.isExpanded());
                            }
                            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(1), false);
                            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(suppIndex), false);
                            rightTable.markAsDirty();
                        }

                    } else if (event.getPropertyId().equals("MandatedDiscount") || event.getPropertyId().equals("SupplementalDiscount")) {

                        if (pcNameList != null && !pcNameList.isEmpty()) {
                            int j = event.getPropertyId().equals("MandatedDiscount") ? 2 : suppIndex + 1;
                            for (int i = 0; i < pcNameList.size(); i++) {
                                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(j++), !event.isExpanded());
                            }
                        }

                    }
                    LOGGER.info("SupplementalDiscountProjection addColumnExpandIconListener ends ");
                }
            });
        } else {
            if (!ANNUALLY.equalsIgnoreCase(projectionDTO.getFrequency())) {
                rightTable.setColumnCollapsingAllowed(true);
                rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(0), false);
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(0), false);
                for (int i = 0; i < rightHeader.getDoubleColumns().size(); i++) {
                    if (!String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("j") && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("f")
                            && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("m") && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("a")
                            && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith(Constant.S_SMALL) && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("o")
                            && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("n") && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith("d")
                            && !String.valueOf(rightHeader.getDoubleColumns().get(i)).startsWith(Constant.Q_SMALL)) {
                        rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(i), false);
                        rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i), false);
                    } else {
                        rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i), true);
                    }
                }

                rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {

                    @Override
                    public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                        for (int i = 0; i < rightHeader.getDoubleColumns().size(); i++) {
                            if (String.valueOf(rightHeader.getDoubleColumns().get(i)).trim().contains(String.valueOf(event.getPropertyId()))
                                    && !String.valueOf(rightHeader.getDoubleColumns().get(i)).trim().equals(String.valueOf(event.getPropertyId()))) {
                                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i), !event.isExpanded());
                            }
                        }
                    }
                });
                rightTable.setImmediate(true);
            } else {
                rightTable.setColumnCollapsingAllowed(false);
            }
        }

        CommonUtils.setExtFilterTreeTableColumnWidth(rightTable, 145);
    }

    private void setProjectionSelection() {
        Map<Object, Object> map = DPRLogic.getMProjectionSelection(projectionId, "Discount Projection Results");
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequencyDdlb.setValue(String.valueOf(value));
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.setValue(String.valueOf(value));
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
            value = map.get("Mandated/Supplemental");
            if (value != null) {
                discountOpg.setValue(String.valueOf(value));
            }
        }
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(resultsTable);
        controlLayout = tableLogic.createControls();
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
    }

    public void generateLogic() {
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        loadAllLevelValue();
        projectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY, true);
        tableLogic.setRefresh(true);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    public void loadAllLevelValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
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
        if (projectionDTO.isIsCustomHierarchy()) {
            levelDdlb.setEnabled(customId != 0);
            levelFilterDdlb.setEnabled(false);
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchy(projectionId, projectionDTO.getCustomerLevelNo());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchy(projectionId, projectionDTO.getProductLevelNo());
        }        
        if (hierarchy != null) {
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                levelDdlb.addItem(itemId);
                levelDdlb.setItemCaption(itemId, caption);
                if (!projectionDTO.isIsCustomHierarchy()) {
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);
                }
            }
        }
        LOGGER.info("loadLevelAndFilterValue ends ");
    }

    public void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.info("levelFilterDdlbChangeOption inititated");
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilterDdlb.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        if (excelExport) {
            loadExcelResultTable(levelNo, hierarchyNo);
        } else {
            projectionDTO.setCustomFlag(false);
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            loadResultTable(levelNo, hierarchyNo, false);
            tableLogic.setRefresh(true);
        }
        LOGGER.info("levelFilterDdlbChangeOption ends");
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
            int count = dprLogic.getConfiguredDPResultsCount(new Object(), projectionDTO, true, initialProjSelDTO);
            projectionDTO.setExcel(true);
            List<DiscountProjectionResultsDTO> resultList = dprLogic.getConfiguredDPResults(new Object(), 0, count, projectionDTO);
            projectionDTO.setExcel(false);
            loadDataToContainer(resultList, null);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void loadDataToContainer(List<DiscountProjectionResultsDTO> resultList, Object parentId) {
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

    public void addLowerLevelsForExport(Object id) {
        try {
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            int count = dprLogic.getConfiguredDPResultsCount(id, projectionDTO, true, initialProjSelDTO);
            List<DiscountProjectionResultsDTO> resultList = dprLogic.getConfiguredDPResults(id, 0, count, projectionDTO);
            loadDataToContainer(resultList, id);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo, boolean freezeFlag) {
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        tableLogic.setProjectionResultsData(projectionDTO, freezeFlag, screenName);
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));

        if (levelNo != 0) {
            if (projectionDTO.isIsFilter()) {
                levelDdlb.removeValueChangeListener(levelFilterChangeOption);
                levelDdlb.setValue(SELECT_ONE);
                projectionDTO.setIsFilter(false);
                tableLogic.clearAll();
                levelDdlb.addValueChangeListener(levelFilterChangeOption);
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
            String hierarchyNo = String.valueOf(levelHierarchy.get(1));
            projectionDTO.setHierarchyNo(hierarchyNo);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }
    }

    private void configureExcelResultTable() {
        excelResultBeanContainer = new CustomTreeContainer<DiscountProjectionResultsDTO>(DiscountProjectionResultsDTO.class);
        excelResultBeanContainer.setColumnProperties(fullHeader.getProperties());
        exceltable = new ExtCustomTreeTable();
        layout.addComponent(exceltable);
        exceltable.setRefresh(false);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelResultBeanContainer);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
    }

    public void saveDPR() {
        try {
            LOGGER.info("saveSPResults method starts");
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, frequencyDdlb.getValue() != null ? frequencyDdlb.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue() != null ? historyDdlb.getValue().toString() : StringUtils.EMPTY);
            map.put("Mandated/Supplemental", discountOpg.getValue().toString());
            map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
            map.put(Constant.PERIOD_ORDER, periodOrderOpg.getValue().toString());
            map.put("Pivot", pivotViewOpg.getValue().toString());
            CommonLogic.saveProjectionSelection(map, session.getProjectionId(), "Discount Projection Results");
            dprLogic.saveDiscountProjection(session);
            LOGGER.info("saveSPResults method ends");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Button Level Security
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION_RESULTS);
        if (functionPsHM.get(FunctionNameUtil.GENERATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag()) {
            generateBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
        }
    }
}
