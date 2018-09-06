/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.logic;

import com.stpl.app.arm.abstractforms.AbstractFilter;
import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.server.VaadinSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigLogic {

    protected static final AdjustmentConfigLogic logic = new AdjustmentConfigLogic();
    protected static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentConfigLogic.class);

    private AdjustmentConfigLogic() {

    }

    public static AdjustmentConfigLogic getInstance() {
        return logic;
    }

    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = new QueryUtils().fetchFieldsForSecurity(moduleName, tabName);
        } catch (Exception ex) {
            LOGGER.error("Error in getFieldsForSecurity :", ex);
        }
        return resultList;
    }

    public int getAdjustmentConfigCount(Set<Container.Filter> filters) throws SQLException {
        List input = new ArrayList<>();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
                sql = sql.replace(0, NumericConstants.THREE, "WHERE");
                sql = new StringBuilder(sql.toString().replace("Ht.DESCRIPTION", "DESCRIPTION"));
                sql = new StringBuilder(sql.toString().replace("AC.CREATED_DATE", "CREATED_DATE"));
                sql = new StringBuilder(sql.toString().replace("AC.MODIFIED_DATE", "MODIFIED_DATE"));
                sql = new StringBuilder(sql.toString().replace("(crt.lastName+' '+crt.firstName)", "created"));
                sql = new StringBuilder(sql.toString().replace("(mod.lastName+' '+mod.firstName)", "Modified"));
            }
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(sql);
            LOGGER.debug("Inside getAdjustmentConfigCount method --{}", sql);
            return CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigCount", null));
        }
    }

    public List getAdjustmentConfigData(int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws SQLException {
        List input = new ArrayList<>();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            sql.append(getOrderByQuery(sortByColumns));
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(sql);
            input.add(start);
            input.add(offset);
            LOGGER.debug("Inside getAdjustmentConfigData method --{}", sql);
            return getCustomizedAdjustmentConfig(QueryUtils.getItemData(input, "AdjustmentConfigLoadData", null));
        }
    }

    private List getCustomizedAdjustmentConfig(List<Object[]> itemData) {
        LOGGER.debug("Inside getCustomizedAdjustmentConfig method --");
        List finalList = new ArrayList<>();
        for (Object[] itemData1 : itemData) {
            AdjustmentConfigDTO dto = new AdjustmentConfigDTO();
            dto.setAdjustmentConfigSid(getIntegerValue(0, itemData1));
            dto.setTransactionName(getStringValue(1, itemData1));
            dto.setTransactionDesc(getStringValue(NumericConstants.TWO, itemData1));
            dto.setMethodologyDDLB(getIntegerValue(NumericConstants.THREE, itemData1));
            dto.setMethodology(getStringValue(NumericConstants.FOUR, itemData1));
            dto.setRedemptionPeriod(String.valueOf(itemData1[NumericConstants.FIVE]));
            dto.setCreatedDate(getDateValue(NumericConstants.SIX, itemData1));
            dto.setCreatedBy(getStringValue(NumericConstants.SEVEN, itemData1));
            dto.setModifiedDate(getDateValue(NumericConstants.SIX, itemData1));
            dto.setModifiedBy(getStringValue(NumericConstants.NINE, itemData1));
            finalList.add(dto);
        }
        LOGGER.debug("Exit getCustomizedAdjustmentConfig method --{}", finalList.size());
        return finalList;
    }

    public void saveDataforAddMode(AdjustmentConfigDTO binderDto) {
        List input = new ArrayList();
        LOGGER.debug("Inside saveDataforAddMode method --");
        input.add(binderDto.getTransactionName());
        input.add(binderDto.getTransactionDesc());
        input.add(binderDto.getMethodologyDDLB());
        int rep = binderDto.getRedemptionPeriod().equals(ARMConstants.getYes()) ? 1 : 0;
        input.add(rep);
        input.add(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        QueryUtils.itemUpdate(input, "Add-Mode Save");
        LOGGER.debug("Exit saveDataforAddMode method --");

    }

    public void saveDataforEditMode(AdjustmentConfigDTO binderDto) {
        List input = new ArrayList();
        LOGGER.debug("Inside saveDataforEditMode method --");
        input.add(binderDto.getTransactionDesc());
        input.add(binderDto.getMethodologyDDLB());
        int rep = binderDto.getRedemptionPeriod().equals(ARMConstants.getYes()) ? 1 : 0;
        input.add(rep);
        input.add(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        input.add(binderDto.getAdjustmentConfigSid());
        QueryUtils.itemUpdate(input, "Edit-Mode Save");
        LOGGER.debug("Inside saveDataforEditMode method --");
    }

    public int checkAdjustmentTypeExitorNot(AdjustmentConfigDTO binderDto) {
        List input = new ArrayList<>();
        LOGGER.debug("Inside checkAdjustmentTypeExitorNot method --");
        input.add(binderDto.getAdjustmentConfigSid());
        int count = (int) QueryUtils.getItemData(input, "Check Adjusment Type", null).get(0);
        LOGGER.debug("Exit checkAdjustmentTypeExitorNot method --");
        return count;
    }

    public void deleteAdjustmentConfig(int adjustmentConfigSid) {
        List input = new ArrayList();
        LOGGER.debug("Inside deleteAdjustmentConfig method --");
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        QueryUtils.itemUpdate(input, "delte adjustment Config");
        LOGGER.debug("Inside deleteAdjustmentConfig method --");
    }

    public int getAdjustmentConfigCountForHistory(Set<Container.Filter> filters, AdjustmentConfigDTO binder) throws SQLException {
        List input = new ArrayList<>();
        LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            if (sql != null && !sql.toString().equals(StringUtils.EMPTY)) {
                sql = sql.replace(0, NumericConstants.THREE, "WHERE");
                sql = new StringBuilder(sql.toString().replace("Ht.DESCRIPTION", "DESCRIPTION"));
                sql = new StringBuilder(sql.toString().replace("AC.CREATED_DATE", "CREATED_DATE"));
                sql = new StringBuilder(sql.toString().replace("AC.MODIFIED_DATE", "MODIFIED_DATE"));
                sql = new StringBuilder(sql.toString().replace("(crt.lastName+' '+crt.firstName)", "created"));
                sql = new StringBuilder(sql.toString().replace("(mod.lastName+' '+mod.firstName)", "Modified"));
            }
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(binder.getAdjustmentConfigSid());
            input.add(sql);
            int count = CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigCountForHistory", null));
            LOGGER.debug("Exit getAdjustmentConfigCountForHistory method --");
            return count;
        }
    }

    public List getAdjustmentConfigDataForHistory(int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, AdjustmentConfigDTO binder) throws SQLException {
        List input = new ArrayList<>();
        LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            sql.append(getOrderByQuery(sortByColumns));
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(binder.getAdjustmentConfigSid());
            input.add(sql);
            input.add(start);
            input.add(offset);
            LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
            return getCustomizedAdjustmentConfig(QueryUtils.getItemData(input, "AdjustmentConfigLoadDataForHistory", null));
        }
    }

    public boolean isProjectionIsCreated(int masterSid) {
        List input = new ArrayList();
        LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
        input.add(masterSid);
        int count = CommonLogic.getCount(QueryUtils.getItemData(input, "Projection created Check", null));
        LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
        return count == 0;
    }

    /**
     * This method is used to get whether the adjustment type has been used in
     * workflow
     *
     * @param adjustmentConfigSid
     * @return
     */
    public boolean deleteVerfication(String adjustmentConfigSid) {
        LOGGER.debug(CommonConstant.INSIDE_GET_ADJUSTMENT_CONFIG_COUNT_FOR_HISTORY);
        if (adjustmentConfigSid != null && StringUtils.isNotBlank(adjustmentConfigSid)) {
            List input = new ArrayList();
            input.add(adjustmentConfigSid);
            List countList = QueryUtils.getItemData(input, "usedCount", null);
            return Integer.parseInt(String.valueOf(countList.get(0))) > 0 ? false : true;
        } else {
            return true;
        }
    }

    /**
     * Checks for the given transaction name while saving in add mode.
     *
     * @param transactionName
     * @return true - If transaction name already available. false - If
     * transaction name is not available.
     */
    public boolean checkDuplicateTransactionName(final String transactionName) {
        LOGGER.debug("Inside checkDuplicateTransactionName method --");
        List input = new ArrayList();
        input.add(transactionName);
        List list = QueryUtils.getItemData(input, "duplicate-transaction-name-check", null);
        return (Integer) list.get(0) == 1;

    }

    String getOrderByQuery(List<SortByColumn> sortByColumns) {
        String orderBy = " ORDER by ac.TRANSACTION_NAME ";
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            Map<String, String> queryMap = new HashMap<>();
            queryMap.putAll(ARMUtils.getVisibleToDBColumnMapForConfigForSort());
            orderBy = String.valueOf(AbstractFilter.getInstance().orderByQueryGenerator(sortByColumns, queryMap));
        }
        LOGGER.debug("Inside getOrderByQuery method --{}", orderBy);
        return orderBy;

    }

    public int adjustmentConfigDelCount(String adjustmentConfigSid) {
        LOGGER.debug("Inside adjustmentConfigDelCount method --");
        List input = new ArrayList();
        input.add(adjustmentConfigSid);
        return CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigDelCount", null));
    }

    private int getIntegerValue(int index, Object[] itemData) {
        return itemData[index] == null ? 0 : (Integer) (itemData[index]);
    }

    private String getStringValue(int index, Object[] itemData) {
        return itemData[index] == null ? StringUtils.EMPTY : String.valueOf(itemData[index]);
    }

    private String getDateValue(int index, Object[] itemData) {
        return itemData[index] == null ? null : CommonUtils.convertStringToDate(String.valueOf(itemData[index]));
    }

}
