/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ARMUtils.levelVariablesVarables;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Karthikeyan.Subraman
 * @param <T>
 */
public abstract class AbstractSummaryLogic<T extends AdjustmentDTO> extends AbstractBPLogic<T> {

    public abstract Boolean generateButtonCheck(SelectionDTO selection);

    protected List<String> getColumns(List<String[]> varibales) {
        List<String> column = new ArrayList<>();
        for (int i = 0; i < varibales.size(); i++) {
            String[] object = varibales.get(i);
            column.add(object[0]);
        }
        return column;
    }

    public Date getGlImpactDate(final SelectionDTO selectionDto) throws ParseException {
        Date today = null;
        List input = new ArrayList();
        input.add(selectionDto.getProjectionMasterSid());
        List<Object> list = QueryUtils.getItemData(input, "glImpactdate", null);
        if (list != null && !list.isEmpty()) {
            today = (Date) list.get(0);
        }
        return today;

    }

    @Override
    public int getCount(Criteria criteria) {
        Object[] returnObj = generateInputs(criteria.getParent(), criteria.getSelectionDto());
        List<Object> inputs = (List<Object>) returnObj[0];
        return getSummaryCount(inputs, criteria);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        Object[] returnObj = generateInputs(criteria.getParent(), criteria.getSelectionDto());
        List<Object> inputs = (List<Object>) returnObj[0];
        TreeMap<String, Integer> masterSids = (TreeMap<String, Integer>) returnObj[1];
        inputs.add(criteria.getStart() + 1);
        inputs.add(criteria.getStart() + criteria.getOffset());
        return getSummaryData(inputs, criteria, (TreeMap<String, Integer>) masterSids.clone());
    }

    protected abstract DataResult<T> getSummaryData(List<Object> inputs, Criteria criteria, TreeMap<String, Integer> masterIds);

    protected abstract int getSummaryCount(List<Object> inputs, Criteria criteria);

    public boolean saveGLImpact(Date glImpactDate, int configLevelId, int projectionId) {
        List input = new ArrayList();
        input.add(new Timestamp(glImpactDate.getTime()));
        input.add(configLevelId);
        input.add(projectionId);
        QueryUtils.itemUpdate(input, "saveImpactdate");
        return true;
    }

    public boolean updateOverride(List input) {
        try {

            QueryUtils.itemUpdate(input, getUpdateOverrideQueryName());

        } catch (Exception e) {
            LOGGER.error("Error in updateOverride"+e);
            return false;
        }
        return true;
    }

    public boolean updateOverrideColumn(SessionDTO sessionDTO) {
        try {
            QueryUtils.itemUpdate(getQueryTableinput(sessionDTO), "update_Override");

        } catch (Exception e) {
            LOGGER.error("Error in updateOverrideColumn"+e);
            return false;
        }
        return true;
    }

    public boolean updateTempOverrideColumn(SessionDTO sessionDTO) {
        try {
            QueryUtils.itemUpdate(getQueryTableinput(sessionDTO), "update_TempOverride");

        } catch (Exception e) {
            LOGGER.error("Error in updateTempOverrideColumn"+e);
            return false;
        }
        return true;
    }

    public void updatecalculateOverride(SessionDTO sessionDTO, String screenName) {
        try {
            if (ARMConstants.getPipelineAccrual().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_pipelineaccrual_overridecolumn");
            } else if (ARMConstants.getDemandAccrual().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_demand_overridecolumn");
            } else if (ARMConstants.getPipelineInventoryTrueUp().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_pipelineinventory_overridecolumn");
            } else if (ARMConstants.getDemandPaymentsRecon().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_demandpaymentrecon_overridecolumn");
            } else if (ARMConstants.getDemandReforecastTrueUp().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_demandaccrualreforcast_overridecolumn");
            } else if (ARMConstants.getTransaction6().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_transaction6_overridecolumn");
            } else if (ARMConstants.getTransaction7().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_transaction7_overridecolumn");
            } else if (ARMConstants.getTransaction8().equals(screenName)) {
                QueryUtils.itemUpdate(getQueryTableinputparameter(sessionDTO), "update_transaction8_overridecolumn");
            }
        } catch (Exception e) {
            LOGGER.error("Error in updatecalculateOverride"+e);
        }
    }

    protected abstract String getUpdateOverrideQueryName();

    protected abstract List getQueryTableinput(SessionDTO sessionDTO);

    public abstract List getQueryTableinputparameter(SessionDTO sessionDTO);

    public abstract List getTableInput(SessionDTO sessionDTO);

    public String getView(String deduction, String viewType) {
        Map<String, String> viewMap = ARMUtils.getViewName();
        String selectedView = viewMap.get(viewType);
        boolean isContractView = false;
        if (viewType.equals(ARMConstants.getDeductionCustomerContract())) {
            isContractView = Boolean.TRUE;
        }
        if (isContractView && deduction.equals(ARMConstants.getDeduction())) {
            selectedView = "Deduction";
        }
        return selectedView;
    }

    public Map<Integer, String> getLevelFilterValueData(String selectedLevel, int projectionId, int deductionType, String deductionValues) {
        LOGGER.debug("getLevelFilterValueData " + selectedLevel + " - " + projectionId);
        LOGGER.debug("deductionType - " + deductionType + " deductionValues - " + deductionValues);
        String queryName = StringUtils.EMPTY;
        String deductionTypeDescription = HelperListUtil.getInstance().getDescriptionByID(deductionType);
        if (selectedLevel.equals(levelVariablesVarables.DEDUCTION.toString())) {
            if (deductionTypeDescription.equalsIgnoreCase(ARMConstants.getDeduction())) {
                queryName = "levelFilterDdlbLoad-Deduction";
            } else {
                queryName = "levelFilterDdlbLoad-OtherDeduction";
            }
        } else if (selectedLevel.equals(levelVariablesVarables.CUSTOMER.toString())) {
            queryName = "levelFilterDdlbLoad-Customer";
        } else if (selectedLevel.equals(levelVariablesVarables.CONTRACT.toString())) {
            queryName = "levelFilterDdlbLoad-Contract";
        } else if (selectedLevel.equals(levelVariablesVarables.BRAND.toString())) {
            queryName = "levelFilterDdlbLoad-Brand";
        } else if (selectedLevel.equals(levelVariablesVarables.ITEM.toString())) {
            queryName = "levelFilterDdlbLoad-Product";
        }

        Map<Integer, String> levelFilterValueMap = new HashMap<>();
        if (queryName.isEmpty()) {
            LOGGER.debug("Query not available for " + selectedLevel);
            return levelFilterValueMap;
        }

        String query = SQlUtil.getQuery(queryName);
        query = query.replace("[@PROJECTION_ID]", String.valueOf(projectionId));
        LOGGER.debug(" deductionTypeDescription " + deductionTypeDescription);
        if (deductionTypeDescription.equalsIgnoreCase(ARMConstants.getDeduction())) {
            query = query + "and RC.RS_ID in (" + deductionValues + ")";
        } else {
            query = query + SQlUtil.getQuery("levelFilterDdlbLoad-DeductionJoin");
            query = query.replace("[@DEDUCTION_ID]", String.valueOf(deductionType));
            query = query.replace("[@DEDUCTION_VARIABLE_VALUES]", deductionValues);
        }
        List resultlist = QueryUtils.executeSelect(query);

        for (int i = 0; i < resultlist.size(); i++) {
            Object[] obj = (Object[]) resultlist.get(i);
            int j = 0;
            if (obj[0] instanceof Integer) {
                j = (Integer) obj[0];
            }
            levelFilterValueMap.put(j, String.valueOf(obj[1]));
        }
        return levelFilterValueMap;
    }

    public int getPeriodSid(String period) {
        String query = SQlUtil.getQuery("getPeriodSidValue");
        query = query.replace("$PERIOD", period);
        List list = QueryUtils.executeSelect(query);
        return list == null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
    }

    protected abstract Object[] generateInputs(Object parent, SelectionDTO selectionDto);
}
