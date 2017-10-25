/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class InventoryLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

    private static final List<String> inventoryVariablesList = new ArrayList<>();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    @Override
    public int getCount(Criteria criteria) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getSalesCount(criteria.getParent(), criteria.getSelectionDto());

    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    List<AdjustmentDTO> list = 
        List list = getSalesData(criteria.getParent(), criteria.getSelectionDto(), criteria.getStart(), criteria.getOffset());
        OriginalDataResult<T> dataResult = new OriginalDataResult<T>();
        dataResult.setDataResults(list);
        return dataResult;
    }

    public List<Object> getMonthYear() {
        String sql = SQlUtil.getQuery("getMonthYear");
        List result = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        List<Object> defaultValues = new ArrayList<>();
        if (!result.isEmpty()) {
            Object[] value = (Object[]) result.get(0);
            for (Object value1 : value) {
                defaultValues.add(String.valueOf(value1));
            }
        }
        return defaultValues;
    }

    /**
     * Get month name from month number
     *
     * @param monthName
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int monthNo) {
        String monthName = StringUtils.EMPTY;
        try {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            monthName = months[monthNo - 1];
        } catch (Exception e) {
            LOGGER.error(e);

        }
        return monthName;
    }

    public int getSalesCount(Object parentId, SelectionDTO selection) {
        String sql = "";
        String level = "BRAND";
        String brandMasterSid = "0";
        if (parentId instanceof AdjustmentDTO) {
            level = "ITEM";
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
        }

        if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSales_levelFilterValue()))) {
            level = "ITEM";
        }
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("getInventoryCount");
        } else {
            sql = SQlUtil.getQuery("getInventoryCountEdit");
            sql = sql.replace("[TABLE]", selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY"));
        }
        sql = sql.replace("[$VIEW]", level);
//        String.valueOf(selection.getProjectionMasterSid())
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        List<String[]> custList = selection.getSales_variables();
        String ids = getSelectedCustomerOrCustomerGrp(custList);
        if (ids.isEmpty()) {
            sql = sql.replace("@COMP_CUST_MASTER_SID", "");
            sql = sql.replace("@AI.COMP_CUST_MASTER_SID", "");
             sql = sql.replace("@AI.REF_CUST_MASTER_SID", ",''");
            
        } else {
            sql = sql.replace("@COMP_CUST_MASTER_SID", " AND AI.COMP_CUST_MASTER_SID in (" + ids + ")");
             sql = sql.replace("@AI.COMP_CUST_MASTER_SID", ",AI.COMP_CUST_MASTER_SID");
              sql = sql.replace("@AI.REF_CUST_MASTER_SID", ",AI.COMP_CUST_MASTER_SID");
        }
        if ("ITEM".equals(level)) {

            if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSales_levelFilterValue()))) {
                sql = sql.replace("[BRAND_MASTER_SID]", "");
            } else {
                sql = sql.replace("[BRAND_MASTER_SID]", "AND IM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } else {
            sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
        List<Integer> result = QueryUtils.executeSelect(sql);
        return result.get(0);
    }

    private String getSelectedCustomerOrCustomerGrp(List<String[]> custList) {
        StringBuilder sb = new StringBuilder();
        for (String[] strings : custList) {
            if (strings[0].contains("~")) {
                sb.append(strings[0].split("~")[1]).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.replace(sb.length() - 1, sb.length(), StringUtils.EMPTY);
        }
        return sb.toString();
    }

    public List<AdjustmentDTO> getSalesData(Object parentId, SelectionDTO selection, int start, int offset) {
        String sql = "";
        String level = "BRAND";
        String brandMasterSid = "0";
        if (parentId instanceof AdjustmentDTO) {
            level = "ITEM";
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
        }
        if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSales_levelFilterValue()))) {
            level = "ITEM";
        }
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("getSalesBrandInventory");
        } else {
            sql = SQlUtil.getQuery("getSalesBrandInventoryEdit");
            sql = sql.replace("[TABLE]", selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY"));
        }
        sql = sql.replace("[$VIEW]", level);
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        sql = sql.replace("[LEVEL_FILTER]", String.valueOf(selection.getSales_levelFilterValue()));
        if ("ITEM".equals(level)) {
            if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSales_levelFilterValue()))) {
                sql = sql.replace("[BRAND_MASTER_SID]", "");
            } else {
                sql = sql.replace("[BRAND_MASTER_SID]", "AND IM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } else {
            sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
        List<String[]> custList = selection.getSales_variables();

        String ids = getSelectedCustomerOrCustomerGrp(custList);
       if (ids.isEmpty()) {
            sql = sql.replace("@COMP_CUST_MASTER_SID", "");
             sql = sql.replace("@AI.COMP_CUST_MASTER_SID", "");
              sql = sql.replace("@AI.REF_CUST_MASTER_SID", "''");
            
        } else {
            sql = sql.replace("@COMP_CUST_MASTER_SID", " AND AI.COMP_CUST_MASTER_SID in (" + ids + ")");
             sql = sql.replace("@AI.COMP_CUST_MASTER_SID", ",AI.COMP_CUST_MASTER_SID");
               sql = sql.replace("@AI.REF_CUST_MASTER_SID", "AI.COMP_CUST_MASTER_SID");
        }

        sql = sql.replace("[START]", "" + start);
        sql = sql.replace("[OFFSET]", "" + offset);
        List<Object[]> result = QueryUtils.executeSelect(sql);
        List<AdjustmentDTO> resultList = cutomize(result, selection);

        return resultList;
    }


    public static void callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);

        String procedureToCall = "{call " + procedureName;
        int noOfArgs = orderedArgs.length;
        for (int i = 0; i < noOfArgs; i++) {
            if (i == 0) {
                procedureToCall += "(";
            }
            procedureToCall += "?,";
            if (i == noOfArgs - 1) {
                procedureToCall += ")";
            }
        }
        procedureToCall = procedureToCall.replace(",)", ")");
        procedureToCall += "}";
        try (Connection connection = ((DataSource) new InitialContext().lookup(DATASOURCE_CONTEXT)).getConnection();
                CallableStatement statement = connection.prepareCall(procedureToCall)) {
            for (int i = 0; i < noOfArgs; i++) {
                LOGGER.debug(i + " -- " + orderedArgs[i]);
                statement.setObject(i + 1, orderedArgs[i]);
            }
            statement.executeUpdate();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static List<String> getInventoryVariables(SelectionDTO selection) {

        if (inventoryVariablesList.isEmpty()) {
            List<String> variables = selection.getSummary_columnList();
            inventoryVariablesList.addAll(variables);
        }

        return inventoryVariablesList;
    }

    private List<AdjustmentDTO> cutomize(List<Object[]> data, SelectionDTO selection) {
        List<AdjustmentDTO> resultList = new ArrayList<>();
        List<String> variables = selection.getInventory_columnList();
        int index = 0;
        String lastItem = "";
        AdjustmentDTO dto = null;
        DecimalFormat decimalformat = new DecimalFormat("#,##,##0");
        DecimalFormat decimalformatfor = new DecimalFormat("#,##,##0.00");
        DecimalFormat decimalformatforzero = new DecimalFormat("$#,##,##0");
        DecimalFormat twodecimal = new DecimalFormat("$#,##,##0.00"); 
        Map<String, String> fixedColumnList = ((PipelineInventorySelectionDTO) selection).getInventory_fixed_ColumnList();

        for (int i = 0; i < data.size(); i++) {
            Object[] obj = data.get(i);
            String item = obj[NumericConstants.TEN] == null ? StringUtils.EMPTY : obj[NumericConstants.TEN].toString();
            if (!item.equals(lastItem)) {
                dto = new AdjustmentDTO();
                dto.setBrand_item_no(obj[NumericConstants.EIGHT].toString());
                dto.setBrand_item_name(obj[NumericConstants.NINE].toString());
                dto.setBrand_item_masterSid(obj[NumericConstants.TEN].toString());
                dto.setChildrenAllowed(obj[NumericConstants.ELEVEN] == null ? false : ((Integer) obj[NumericConstants.ELEVEN] != 0));
                dto.setCustomer_masterSid(obj[NumericConstants.TWELVE].toString());
                dto.addStringProperties(fixedColumnList.get("totalInventory"), obj[1] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(obj[1].toString())));
                dto.addStringProperties(fixedColumnList.get("weeksOnHand"), obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : decimalformatfor.format(Double.valueOf(obj[NumericConstants.TWO].toString())));
                dto.addStringProperties(fixedColumnList.get("unitsPerRetail"), obj[NumericConstants.THREE] == null ? StringUtils.EMPTY : decimalformatfor.format(Double.valueOf(obj[NumericConstants.THREE].toString())));
                dto.addStringProperties(fixedColumnList.get("price"), obj[NumericConstants.FOUR] == null ? StringUtils.EMPTY : twodecimal.format(Double.valueOf(obj[NumericConstants.FOUR].toString())));
                dto.addStringProperties(fixedColumnList.get("priceOverride"), obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : twodecimal.format(Double.valueOf(obj[NumericConstants.FIVE].toString())));
                dto.addStringProperties(fixedColumnList.get("returnReserve"), obj[NumericConstants.SIX] == null ? StringUtils.EMPTY : decimalformatforzero.format(Double.valueOf(obj[NumericConstants.SIX].toString())));
                dto.addStringProperties(fixedColumnList.get("netPipelineValue"), obj[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : decimalformatforzero.format(Double.valueOf(obj[NumericConstants.SEVEN].toString())));
                resultList.add(dto);
                index = 0;
            }
            if (dto != null) {
                int dynamicIndex = index++;
                if (dynamicIndex <= variables.size() && !variables.isEmpty()) {
                    String column = variables.get(dynamicIndex);
                    if (column.contains("~")) {
                        dto.addStringProperties(column, obj[0] == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(obj[0].toString())));
                    }
                }
            }
            lastItem = item;
        }

        return resultList;
    }

    public boolean updatePriceOverride(List input) {
        try {
            QueryUtils.itemUpdate(input, "PI_Inventory_updatePriceOverride");
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;

    }

    public void getInventoryResults(Object[] orderedArgs) {
        callProcedure("PRC_ARM_INVENTORY_SALES", orderedArgs);

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query = StringUtils.EMPTY;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getPIInventoryExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPIInventoryExcelQuery");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    
    
    public List<String> getCustomerGroupView(String viewSid, List<CustomerGroupDTO> custGroupList) {
        List<String> customerGroupList = new ArrayList<>();
        try {
            List<Object> listValue;

            String cgQuery = SQlUtil.getQuery("loadViewCustomerGroupDetails");
            cgQuery = cgQuery.replace("@ARM_VIEW_MASTER_SID", viewSid);
            listValue = HelperTableLocalServiceUtil.executeSelectQuery(cgQuery);

            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dto = new CustomerGroupDTO();
                dto.setCustomerGroupSid(String.valueOf(obj[0]));
                dto.setCustomerGroupName(String.valueOf(obj[1]));
                dto.setIndicator(obj[NumericConstants.TWO] == null ? null : (Boolean) obj[NumericConstants.TWO]);
                dto.setInclude(obj[NumericConstants.THREE] == null ? false : (boolean) obj[NumericConstants.THREE]);
                dto.setSelectedFlag(true);
                customerGroupList.add(dto.getCustomerGroupName() + "~" + dto.getCustomerGroupSid());
                custGroupList.add(dto);

            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return customerGroupList;
    }
       
     public List<String> getCustomerView(String viewMasterSid,List<CustomerGroupDTO> custGroupList) {
         List<String> customerList = new ArrayList<>();
        try {
            String cgQuery = SQlUtil.getQuery("loadCustomerDetailsQuery");
            cgQuery = cgQuery.replace("@ARM_VIEW_MASTER_SID", viewMasterSid);
            List<Object> listValue = HelperTableLocalServiceUtil.executeSelectQuery(cgQuery);
            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dto = new CustomerGroupDTO();
                dto.setCompanyMasterSid(String.valueOf(obj[0]));
                dto.setCustomerName(String.valueOf(obj[1]));
                dto.setIndicator(obj[NumericConstants.TWO] == null ? null : (Boolean) obj[NumericConstants.TWO]);
                dto.setInclude((boolean) obj[NumericConstants.THREE]);
                customerList.add(dto.getCustomerName() + "~" + dto.getCompanyMasterSid());
                custGroupList.add(dto);
            }
            return customerList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return customerList;
        }
    }
     
    public void resetCustomerGroupValue(int projectionId, AbstractSelectionDTO selectionDto) {
        try {
            StringBuilder resetQuery = new StringBuilder(StringUtils.EMPTY);
            resetQuery.append(SQlUtil.getQuery("resetCustomerGroupInventDetails").replace("@PROJECTION_MASTER_SID", "" + projectionId)
                    .replace("@USER_ID", "" + selectionDto.getSessionDTO().getUserId()).replace("@SESSION_ID", "" + selectionDto.getSessionDTO().getSessionId()));
           
            HelperTableLocalServiceUtil.executeUpdateQuery(resetQuery.toString());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

}