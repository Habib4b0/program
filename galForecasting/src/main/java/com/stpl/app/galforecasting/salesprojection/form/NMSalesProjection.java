/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.lookups.NMPmpyCalculator;
import com.stpl.app.galforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.galforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.galforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class NMSalesProjection extends ForecastSalesProjection {

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

    boolean generated = false;
    boolean firstGenerated = false;
    private static final Logger LOGGER = Logger.getLogger(NMSalesProjection.class);
    List<String> projectedPeriodList = new ArrayList();
    private SPRCommonLogic sprCommonLogic = new SPRCommonLogic();

    public NMSalesProjection(SessionDTO session, String screenName) throws Exception {
        super(session, screenName);
        init();
    }

    public void init() throws Exception {
        LOGGER.info("Inside NMSalesProjection Screen " + session.getUserId());
        projectionDTO.setSessionDTO(session);
        projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(session, projectionDTO));
        configureProjectionDTO();
        generateBtnLogic(null);
        configureGroupDDLB();
        super.configureGraph();
        securityForButton();
    }

    @Override
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

    @Override
    protected void excelExportLogic() {
        try {
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(Boolean.TRUE);
            if (excelTable.size() > 0) {
                ForecastUI.EXCEL_CLOSE=true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), "Sales Projection", "Sales Projection", "Sales Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void enableDisableFields() {
    }

    @Override
    protected void populateBtnLogic() {
        populateButtonLogic();
    }

    @Override
    protected void adjustmentLogic() {
    }

    @Override
    protected void calculateLogic() {
        try {
            calculateButtonLogic();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(NMSalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void massUpdateLogic() {
        if ((Constant.LabelConstants.DISABLE).equals(massUpdate.getValue())) {
            fieldDdlb.setValue(Constant.SELECT_ONE);
            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);

        } else {
            fieldDdlb.setEnabled(true);
            fieldDdlb.setValue(Constant.SELECT_ONE);
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);
        }
    }

    @Override
    protected void viewChangeOption() {
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        LOGGER.info(" customId  " + customId);
        LOGGER.info(" currentHierarchy " + currentHierarchy.size());
        projectionDTO.setCustomId(customId);
        generateLogic();
        if (viewDdlb.getValue() != null
                && !Constant.NULL.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !SELECT_ONE.getConstant().equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !DASH.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))) {
            editBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
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
            LOGGER.error(e);
        }
    }

    @Override
    protected void collapseButtonLogic() {
        mSalesProjectionTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        mSalesProjectionTableLogic.setRefresh(true);
    }

    @Override
    protected void fieldDdlbLogic() {
        if (Constant.ACCOUNT_GROWTH.equals(fieldDdlb.getValue()) || Constant.PRODUCT_GROWTH.equals(fieldDdlb.getValue()) || Constant.SELECT_ONE.equals(fieldDdlb.getValue()) || Constant.NULL.equals(String.valueOf(fieldDdlb.getValue()))) {
            startPeriod.setVisible(true);
            endPeriod.setVisible(true);
            lblStart.setVisible(true);
            lblEnd.setVisible(true);
            valueTxt.setVisible(true);
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setValue(Constant.SELECT_ONE);
            endPeriod.setValue(Constant.SELECT_ONE);
            valueTxt.setVisible(true);
            valueDdlb.setVisible(false);
            valueDdlb.select(Constant.SELECT_ONE);
        } else if (Constant.GROUPFCAPS.equals(fieldDdlb.getValue())) {
            valueDdlb.setVisible(true);
            valueTxt.setVisible(false);
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setVisible(false);
            endPeriod.setVisible(false);
            lblStart.setVisible(false);
            lblEnd.setVisible(false);
        }

    }

    @Override
    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY.getConstant())
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(4);
        }
        actualsProjections.select(Constant.ACTUALS);
        proPeriodOrd.select(Constant.ASCENDING);
        variables.removeAllItems();
        variables.addItem(Constant.SALES_SMALL);
        variables.addItem(Constant.UNITS_SMALL);
        variables.addItem(Constant.PRODUCT_GROWTH);
        variables.addItem(Constant.ACCOUNT_GROWTH);
        variables.select(Constant.SALES_SMALL);
    }

    @Override
    protected void pmpyLogic() {
        List<String> projectedPeriodList = new ArrayList();
        PmpyLogic pmpyLogic = new PmpyLogic();

        boolean tpSelected = false;
        boolean hasActuals = false;
        int i = 0;
        String levelValues = StringUtils.EMPTY;
        String hierarchyNo = StringUtils.EMPTY;

        for (SalesRowDto dto : customContainer.getItemIds()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && ((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                tpSelected = true;
                i++;
                hierarchyNo = dto.getHierarchyNo();

                levelValues = dto.getLevelName();

            }
            for (Object key : dto.getProperties().keySet()) {
                if ((Boolean) dto.getPropertyValue(Constant.CHECK) && ((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                    if (String.valueOf(key).contains("Actual")) {
                        String value = String.valueOf(dto.getProperties().get(key));
                        if (!value.equals("-") && !value.equals("0.00") && !value.equals("$0") && !value.equals(DASH) && !value.equals("0.000000")) {
                            hasActuals = true;
                        }

                    }
                }

            }

        }

        if (tpSelected && i == 1) {

            if (!hasActuals) {
                String historyPeriods = String.valueOf(historyDdlb.getValue());

                hierarchyNo = " WHERE RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ";
                Object[] inputParameters = new Object[10];
                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = hierarchyNo;
                List<Object> projectionDetailsIdForPMPY = pmpyLogic.getNmProjectionDetId(inputParameters);
                int projectionDetailsId = Integer.valueOf(projectionDetailsIdForPMPY.get(0).toString());
                List list = pmpyLogic.getTradingPartnerInfo(projectionDetailsId);

                String tradeName = String.valueOf(list.get(0) != null ? list.get(0) : " ");
                String tradeNo = String.valueOf(list.get(1) != null ? list.get(1) : " ");
                String contractHolder = String.valueOf(list.get(2) != null ? list.get(2) : " ");

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

    @Override
    protected void generateBtnLogic(Button.ClickEvent event) {
        try {
            if (checkSelection()) {
                LOGGER.info("generate button click listener starts ");
                generated = true;
                firstGenerated = true;
                tableLayout.removeAllComponents();
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
                super.initializeResultTable();
                configureResultTable();
                projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(projectionDTO.getSessionDTO(), projectionDTO));
                addResultTable();
                generateLogic();
                generated = false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("generate button click listener ends ");

    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        mSalesProjectionTableLogic.setPageLength(20);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);

        List<Object> visibleColumn = rightHeader.getSingleColumns();
        List<String> columnHeader = rightHeader.getSingleHeaders();
        List<Object> doubleVisibleColumn = rightHeader.getDoubleColumns();
        List<String> doubleColumnHeader = rightHeader.getDoubleHeaders();
        customContainer = new CustomTreeContainer<>(SalesRowDto.class);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());
        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setEditable(true);
        rightTable.setEditable(true);

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 135);
            } else if (String.valueOf(obj).contains(Constant.LEVELNAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 130);
            }
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
        loadPeriods();
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
        getHeaderList();
    }

    public void getHeaderList(){
    List<String> headerList=new ArrayList<>();
    for(Object header:rightHeader.getSingleColumns()){
    headerList.add(String.valueOf(header));
    }
    projectionDTO.setHeaderMapColumn(headerList);
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
        } else if ((Constant.CUSTOM).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        }
        loadLevelAndFilterValue();
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
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1).trim();
            final String[] col = tempVariables.split(",");
            List<String> colArray = new ArrayList<>();
            for (String string : col) {
                colArray.add(string.trim());
            }
            Collection<?> temp = variables.getItemIds();
            List<String> itemId = (List<String>) temp;
            List<String> sortedArray = new ArrayList<String>();
            for (String string : itemId) {
                if (colArray.contains(string)) {
                    sortedArray.add(string);
                }
            }
            projectionDTO.setVariableList(sortedArray);
        }

    }

    /**
     * Saves the Commercials Sales the Temp Table to the Main Table.
     *
     * @throws PortalException
     * @throws Exception
     */
    public void saveSalesProjection() throws PortalException, Exception {
        try {
            String userId = String.valueOf(projectionDTO.getUserId());
            String sessionId = String.valueOf(projectionDTO.getSessionId());
            salesLogic.saveNonMandatedSalesProjection(userId, sessionId);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureGroupDDLB() throws Exception {
        methodology.removeItem(Constant.PERCENTAGEOFBUSINESS);
        methodology.addItem(Constant.CUSTOMER_GTS);
        fieldDdlb.addItem(Constant.GROUPFCAPS);
        groupBean.removeAllItems();
        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
        groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
        massGroupBean.removeAllItems();
        massGroupBean.addBean(Constant.SELECT_ONE);
        massGroupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
        valueDdlb.setNewItemsAllowed(true);
        valueDdlb.setContainerDataSource(massGroupBean);
        valueDdlb.setNullSelectionAllowed(true);
        valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        valueDdlb.select(Constant.SELECT_ONE);
        valueDdlb.setTextInputAllowed(true);
    }

    public void securityForButton() {
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, GlobalConstants.getCommercialConstant() + "," + UISecurityUtil.SALES_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES)).isFunctionFlag())) {
                generate.setVisible(Boolean.FALSE);
                expand.setVisible(Boolean.FALSE);
                collapse.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);
                pmpy.setVisible(Boolean.FALSE);
            }
            if (!(functionPsHM.get(CommonUtils.CALCULATE) != null && ((AppPermission) functionPsHM.get(CommonUtils.CALCULATE)).isFunctionFlag())) {
                calculate.setVisible(Boolean.FALSE);
                populate.setVisible(Boolean.FALSE);
                adjust.setVisible(Boolean.FALSE);
            }

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveSPSave() {
        LOGGER.info("saveSPResults method starts");
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, nmFrequencyDdlb.getValue().toString());
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue().toString());
            map.put("Actuals/Projections", actualsProjections.getValue().toString());
            map.put(Constant.PERIOD_ORDER, proPeriodOrd.getValue().toString());
            map.put(Constant.VARIABLES, variables.getValue().toString());
            sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), "Sales Projection");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("saveSPResults method ends");
    }

}
