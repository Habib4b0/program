/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.logic;

import com.stpl.app.arm.AbstractForms.AbstractFilter;
import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
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

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigLogic {

    static final AdjustmentConfigLogic logic = new AdjustmentConfigLogic();

    private AdjustmentConfigLogic() {

    }

    public static AdjustmentConfigLogic getInstance() {
        return logic;
    }
    public List<Object> getFieldsForSecurity(String moduleName, String tabName){
        List<Object> resultList = new ArrayList<Object>();
        try {            
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName,tabName,null,null,null);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
            int count = CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigCount", null));
            return count;
        }
    }

    public List getAdjustmentConfigData(int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws SQLException  {
        List input = new ArrayList<>();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            sql.append(getOrderByQuery(sortByColumns)) ;
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(sql);
            input.add(start);
            input.add(offset);
            return getCustomizedAdjustmentConfig(QueryUtils.getItemData(input, "AdjustmentConfigLoadData", null));
        }
    }

    private List getCustomizedAdjustmentConfig(List<Object[]> itemData) {
        List finalList = new ArrayList<>();
        for (Object[] itemData1 : itemData) {
            AdjustmentConfigDTO dto = new AdjustmentConfigDTO();
            dto.setAdjustmentConfigSid(itemData1[0] == null ? 0 : Integer.valueOf(itemData1[0].toString()));
            dto.setTransactionName(itemData1[1] == null ? StringUtils.EMPTY : itemData1[1].toString());
            dto.setTransactionDesc(itemData1[NumericConstants.TWO] == null ? StringUtils.EMPTY : itemData1[NumericConstants.TWO].toString());
            dto.setMethodologyDDLB(itemData1[NumericConstants.THREE] == null ? 0 : Integer.valueOf(itemData1[NumericConstants.THREE].toString()));
            dto.setMethodology(itemData1[NumericConstants.FOUR] == null ? StringUtils.EMPTY : itemData1[NumericConstants.FOUR].toString());
            String rep = itemData1[NumericConstants.FIVE] == null ? GlobalConstants.getSelectOne() : ((boolean) itemData1[NumericConstants.FIVE] ? ARMConstants.getYes() : ARMConstants.getNo());
            dto.setRedemptionPeriod(rep);
            dto.setCreatedDate(itemData1[NumericConstants.SIX] == null ? null : (Date) itemData1[NumericConstants.SIX]);
            dto.setCreatedBy(itemData1[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : itemData1[NumericConstants.SEVEN].toString());
            dto.setModifiedDate(itemData1[NumericConstants.EIGHT] == null ? null : (Date) itemData1[NumericConstants.EIGHT]);
            dto.setModifiedBy(itemData1[NumericConstants.NINE] == null ? StringUtils.EMPTY : itemData1[NumericConstants.NINE].toString());
            finalList.add(dto);
        }
        return finalList;
    }

    public void saveDataforAddMode(AdjustmentConfigDTO binderDto) {
        List input = new ArrayList();
        input.add(binderDto.getTransactionName());
        input.add(binderDto.getTransactionDesc());
        input.add(binderDto.getMethodologyDDLB());
        int rep = binderDto.getRedemptionPeriod().equals(ARMConstants.getYes()) ? 1 : 0;
        input.add(rep);
        input.add(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        QueryUtils.itemUpdate(input, "Add-Mode Save");

    }

    public void saveDataforEditMode(AdjustmentConfigDTO binderDto) {
        List input = new ArrayList();
        input.add(binderDto.getTransactionDesc());
        input.add(binderDto.getMethodologyDDLB());
        int rep = binderDto.getRedemptionPeriod().equals(ARMConstants.getYes()) ? 1 : 0;
        input.add(rep);
        input.add(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        input.add(binderDto.getAdjustmentConfigSid());
        QueryUtils.itemUpdate(input, "Edit-Mode Save");
    }

    public void deleteAdjustmentConfig(int adjustmentConfigSid) {
        List input = new ArrayList();
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        input.add(adjustmentConfigSid);
        QueryUtils.itemUpdate(input, "delte adjustment Config");
    }

    public int getAdjustmentConfigCountForHistory(Set<Container.Filter> filters, AdjustmentConfigDTO binder) throws SQLException {
        List input = new ArrayList<>();
        try(Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(binder.getAdjustmentConfigSid());
            input.add(sql);
            int count = CommonLogic.getCount(QueryUtils.getItemData(input, "AdjustmentConfigCountForHistory", null));
            return count;
        }
    }

    public List getAdjustmentConfigDataForHistory(int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, AdjustmentConfigDTO binder) throws SQLException  {
        List input = new ArrayList<>();
        try(Connection con = SysDataSourceConnection.getConnection()) {
            StringBuilder sql = AbstractFilter.getInstance().getFilterForAdjustMentConfig(filters);
            sql.append(getOrderByQuery(sortByColumns));
            input.add(con.getCatalog());
            input.add(con.getCatalog());
            input.add(binder.getAdjustmentConfigSid());
            input.add(sql);
            input.add(start);
            input.add(offset);
            return getCustomizedAdjustmentConfig(QueryUtils.getItemData(input, "AdjustmentConfigLoadDataForHistory", null));
        }
    }

    public boolean isProjectionIsCreated(int masterSid) {
        List input = new ArrayList();
        input.add(masterSid);
        int count = CommonLogic.getCount(QueryUtils.getItemData(input, "Projection created Check", null));
        return count == 0;
    }
    /**
     * This method is used to get whether the adjustment type has been used in workflow 
     * @param adjustmentConfigSid
     * @return 
     */
    public boolean deleteVerfication(String adjustmentConfigSid) {       
        if (adjustmentConfigSid != null && StringUtils.isNotBlank(adjustmentConfigSid)) {
            List input = new ArrayList();
            input.add(adjustmentConfigSid);
            List countList = QueryUtils.getItemData(input, "usedCount", null);
            return Integer.parseInt(String.valueOf(countList.get(0))) > 0 ? false : true;
        } else {
            return Boolean.TRUE;
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

        List input = new ArrayList();
        input.add(transactionName);
        List list = QueryUtils.getItemData(input, "duplicate-transaction-name-check", null);
        return (Integer) list.get(0) == 1;

    }
    String getOrderByQuery(List<SortByColumn> sortByColumns){
        String orderBy=" ORDER by ac.TRANSACTION_NAME ";
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            Map<String, String> queryMap = new HashMap<>();
            queryMap.putAll(ARMUtils.getVisibleToDBColumnMapForConfig());
           orderBy=String.valueOf( AbstractFilter.getInstance().orderByQueryGenerator(sortByColumns, queryMap));
        }
        return orderBy;
    
    }
    
}
