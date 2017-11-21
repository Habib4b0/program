
package com.stpl.app.gtnforecasting.salesprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.lookups.MPmpyCalculator;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.v7.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.jboss.logging.Logger;

/**
 * Mandated Sales Projection.
 *
 * @author sibi
 */
public class MSalesProjection extends ForecastSalesProjection {

    String screenName;
    private static final Logger LOGGER = Logger.getLogger(MSalesProjection.class);
    public static Map<String, Integer> rowCountMap = new HashMap<>();
    boolean generated = false;
    boolean firstGenerated = false;
    List<String> projectedPeriodList = new ArrayList();
    SalesRowDto salesPMPYDTO = new SalesRowDto();
    Set<String> tableHirarechyNos = new HashSet<>();

    public MSalesProjection(SessionDTO session, String screenName) throws PortalException, SystemException  {
        super(session, screenName);
        this.screenName = screenName;
        init();
    }

    public void init() throws PortalException, SystemException  {
        projectionDTO.setSessionDTO(session);
        configureProjectionDTO();
        Utility.loadHierarchyList(session);
        generateBtnLogic(null);
        super.configureGraph();
        setButtonSecurity();
    }

    @Override
    protected void levelFilterDdlbChangeOption() {
        projectionDTO.setHierarchyNo(StringUtils.EMPTY);
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
                ForecastUI.EXCEL_CLOSE = true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), Constant.SALES_PROJECTION, Constant.SALES_PROJECTION, "Sales_Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void enableDisableFields() {
        return;
    }

    @Override
    protected void populateBtnLogic() {
        populateButtonLogic();
    }

    @Override
    protected void adjustmentLogic() {
        return;
    }

    @Override
    protected void calculateLogic() {
        try {
            calculateButtonLogic();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    protected void generateBtnLogic(Button.ClickEvent event) {
        try {
            if (checkSelection()) {
                LOGGER.debug("generate button click listener starts ");
                generated = true;
                firstGenerated = true;
                tableLayout.removeAllComponents();
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
                super.initializeResultTable();
                configureResultTable();
                addResultTable();
                generateLogic();
                generated = false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("generate button click listener ends ");

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
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);
    }
    }
    
    @Override
    protected void variableChangeLogic() {
        return;

    }

    @Override
    protected void viewChangeOption() {
        return;
    }

    @Override
    protected void expandButtonLogic() {
        try {
            if (!Constant.NULL.equals(String.valueOf(level.getValue()))) {
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
        if (Constant.ACCOUNT_GROWTH.equals(fieldDdlb.getValue()) || Constant.PRODUCT_GROWTH.equals(fieldDdlb.getValue())) {
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setValue(Constant.SELECT_ONE);
            endPeriod.setValue(Constant.SELECT_ONE);
            valueTxt.setVisible(true);
        }
    }

    @Override
    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY.getConstant())
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(NumericConstants.FOUR);
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

        boolean contractSelected = false;
        boolean hasActuals = false;
        final String hierarchyNo;
        int tempContractLevel = 0;
        int tempCustomerLevel = 0;
        int i = 0;
        for (Object obj : rightTable.getItemIds()) {
            SalesRowDto dto = getBeanFromId(obj);
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("CONTRACT".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.CONTRACT.equalsIgnoreCase(dto.getHierarchyLevel()))
                    || ("CUSTOMER".equalsIgnoreCase(dto.getHierarchyLevel()) || "customer".equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;
                if ("CONTRACT".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.CONTRACT.equalsIgnoreCase(dto.getHierarchyLevel())) {
                    tempContractLevel++;
                }
                if ("CUSTOMER".equalsIgnoreCase(dto.getHierarchyLevel()) || "customer".equalsIgnoreCase(dto.getHierarchyLevel())) {
                    tempCustomerLevel++;
                }
                for (Object key : dto.getProperties().keySet()) {
                    if (String.valueOf(key).contains("Actual")) {
                        String value = String.valueOf(dto.getProperties().get(key));
                        if (!value.equalsIgnoreCase("-") && !value.equalsIgnoreCase("0.00") && !value.equalsIgnoreCase("$0") && !value.equalsIgnoreCase(DASH)) {
                            hasActuals = true;
                        }
                    }
                }
                salesPMPYDTO = dto;
            }

        }
        if (contractSelected && (tempContractLevel != 0 || tempCustomerLevel != 0) && (tempCustomerLevel <= 1 || tempCustomerLevel <= 1)) {
            if (!hasActuals) {
                hierarchyNo = salesPMPYDTO.getHierarchyNo();
                tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(salesPMPYDTO));
                final MPmpyCalculator pmpyCalc = new MPmpyCalculator(session, projectedPeriodList);
                getUI().addWindow(pmpyCalc);
                pmpyCalc.addCloseListener(new Window.CloseListener() {
                    public void windowClose(Window.CloseEvent e) {
                        if (pmpyCalc.isImport) {
                            updateOnPMPYImport(hierarchyNo, pmpyCalc.getUpdateValue(), pmpyCalc.getUpdatePeriod(), pmpyCalc.isSalesOrUnits, salesPMPYDTO);
                        }
                    }
                });
            } else {
                AbstractNotificationUtils.getErrorNotification("Contract LookUp", "Selected contract should not have  History");
            }
        } else if (contractSelected && i > 1) {
            AbstractNotificationUtils.getErrorNotification("More than one Contract Selected",
                    "There are More than one Contract selected.\n Please select only one Contract and try again");
        } else {
            AbstractNotificationUtils.getErrorNotification("No Contract Selected.", "Please select a Contract. ");
        }
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
     *
     */
    private void generateLogic() {
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(view.getValue()) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        loadLevelAndFilterValue();
        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        mSalesProjectionTableLogic.setPageLength(NumericConstants.TWENTY);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);
        customContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setEditable(true);
        rightTable.setEditable(true);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());

        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.LEVELNAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_FIVE);
            } else if (String.valueOf(obj).contains(Constant.BASELINE)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj,  NumericConstants.ONE_THREE_FIVE);
            } else if (String.valueOf(obj).contains(Constant.METHODOLOGY)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj,  NumericConstants.ONE_THREE_FIVE);
            }
        }

        loadPeriods();
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
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
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
            final String[] col = tempVariables.split(",");
            projectionDTO.setVariableList(Arrays.asList(col));
        }

    }

    protected void channelsViewChange() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        LOGGER.debug(" customId  " + customId);
        LOGGER.debug(" currentHierarchy " + currentHierarchy.size());
        projectionDTO.setCustomId(customId);
        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
        }
        generateLogic();
        if (viewDdlb.getValue() != null
                && !Constant.NULL.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !SELECT_ONE.getConstant().equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !DASH.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))) {
            editBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public void updateOnPMPYImport(String hierarchyNo, String updateValue, String updatePeriod, boolean salesOrUnits, SalesRowDto salesRow) {

        try {
            String salesOrUnitsProperty;

            if (StringUtils.isNotBlank(hierarchyNo) && StringUtils.isNotEmpty(hierarchyNo)) {
                updatePeriod = updatePeriod.replace(Constant.Q, Constant.Q_SMALL);
                updatePeriod = updatePeriod.replace(" ", "-");
                if (salesOrUnits) {
                    updatePeriod = updatePeriod + "-ProjectedSales";
                    salesOrUnitsProperty = Constant.SALES_SMALL;
                } else {
                    updatePeriod = updatePeriod + "-ProjectedUnits";
                    salesOrUnitsProperty = Constant.UNITS_SMALL;
                }

                salesLogic.saveEditedRecs(updatePeriod, updateValue, Double.NaN, salesOrUnitsProperty, salesRow, projectionDTO, false, true);
                refreshTableData(getCheckedRecordsHierarchyNo());

            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void saveSalesProjection() throws PortalException, SystemException {
        String userId = String.valueOf(projectionDTO.getUserId());
        String sessionId = String.valueOf(projectionDTO.getSessionId());
        salesLogic.saveMandatedSalesProjection(userId, sessionId);
    }


    public boolean getSubmitFlag() {
        if (customContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.SALES_PROJECTION);
        if (functionPsHM.get(FunctionNameUtil.GENERATEBTN) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATEBTN)).isFunctionFlag()) {
            generate.setVisible(Boolean.FALSE);
            expand.setVisible(Boolean.FALSE);
            collapse.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(CommonUtils.PMPY) != null && ((AppPermission) functionPsHM.get(CommonUtils.PMPY)).isFunctionFlag()) {
            pmpy.setVisible(true);
        } else {
            pmpy.setVisible(false);
        }
        if (functionPsHM.get(CommonUtils.ALT_HISTORY_BTN) != null && ((AppPermission) functionPsHM.get(CommonUtils.ALT_HISTORY_BTN)).isFunctionFlag()) {
            altHistoryBtn.setVisible(true);
        } else {
            altHistoryBtn.setVisible(false);
        }
        if (functionPsHM.get(FunctionNameUtil.CALCULATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.CALCULATE)).isFunctionFlag()) {
            calculate.setVisible(Boolean.FALSE);
            populate.setVisible(Boolean.FALSE);
            adjust.setVisible(Boolean.FALSE);
        }
    }
}

