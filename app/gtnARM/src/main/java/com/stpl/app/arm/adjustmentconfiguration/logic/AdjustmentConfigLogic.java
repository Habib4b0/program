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
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigLogic {

    static final AdjustmentConfigLogic logic = new AdjustmentConfigLogic();
    protected static final Logger LOGGER = Logger.getLogger(AdjustmentConfigLogic.class);

    private AdjustmentConfigLogic() {

    }

    public static AdjustmentConfigLogic getInstance() {
        return logic;
    }

    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error("Error in getFieldsForSecurity :"+ex);
        }
        return resultList;
    }

    public int getAdjustmentConfigCount(Set<Container.Filter> filters) throws SQLException {
        List input = new ArrayList<>();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(sql);
            LOGGER.debug("Inside getAdjustmentConfigCount method --" + sql);
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
            LOGGER.debug("Inside getAdjustmentConfigData method --" + sql);
            return getCustomizedAdjustmentConfig(QueryUtils.getItemData(input, "AdjustmentConfigLoadData", null));
        }
    }

    private List getCustomizedAdjustmentConfig(List<Object[]> itemData) {
        LOGGER.debug("Inside getCustomizedAdjustmentConfig method --");
        List finalList = new ArrayList<>();
        for (Object[] itemData1 : itemData) {
            AdjustmentConfigDTO dto = new AdjustmentConfigDTO();
            dto.setAdjustmentConfigSid(itemData1[0] == null ? 0 : Integer.valueOf(itemData1[0].toString()));
            dto.setTransactionName(itemData1[1] == null ? StringUtils.EMPTY : itemData1[1].toString());
            dto.setTransactionDesc(itemData1[NumericConstants.TWO] == null ? StringUtils.EMPTY : itemData1[NumericConstants.TWO].toString());
            dto.setMethodologyDDLB(itemData1[NumericConstants.THREE] == null ? 0 : Integer.valueOf(itemData1[NumericConstants.THREE].toString()));
            dto.setMethodology(itemData1[NumericConstants.FOUR] == null ? StringUtils.EMPTY : itemData1[NumericConstants.FOUR].toString());
            String value = ((boolean) itemData1[NumericConstants.FIVE] ? ARMConstants.getYes() : ARMConstants.getNo());
            String rep = itemData1[NumericConstants.FIVE] == null ? GlobalConstants.getSelectOne() : value;
            dto.setRedemptionPeriod(rep);
            dto.setCreatedDate(itemData1[NumericConstants.SIX] == null ? null : (Date) itemData1[NumericConstants.SIX]);
            dto.setCreatedBy(itemData1[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : itemData1[NumericConstants.SEVEN].toString());
            dto.setModifiedDate(itemData1[NumericConstants.EIGHT] == null ? null : (Date) itemData1[NumericConstants.EIGHT]);
            dto.setModifiedBy(itemData1[NumericConstants.NINE] == null ? StringUtils.EMPTY : itemData1[NumericConstants.NINE].toString());
            finalList.add(dto);
        }
        LOGGER.debug("Exit getCustomizedAdjustmentConfig method --" + finalList.size());
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
            queryMap.putAll(ARMUtils.getVisibleToDBColumnMapForConfig());
            orderBy = String.valueOf(AbstractFilter.getInstance().orderByQueryGenerator(sortByColumns, queryMap));
        }
        LOGGER.debug("Inside getOrderByQuery method --" + orderBy);
        return orderBy;

    }

    public int adjustmentConfigDelCount(String adjustmentConfigSid) {
        LOGGER.debug("Inside adjustmentConfigDelCount method --");
        List input = new ArrayList();
        input.add(adjustmentConfigSid);
        return CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigDelCount", null));
    }

}
