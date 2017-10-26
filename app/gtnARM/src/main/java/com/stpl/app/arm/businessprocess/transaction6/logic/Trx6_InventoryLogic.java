/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6_SelectionDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 * @param <T>
 * @param <E>
 */
public class Trx6_InventoryLogic<T extends AdjustmentDTO, E extends Trx6_SelectionDTO> extends AbstractPipelineLogic<T, E> {

    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    @Override
    public int getCount(Criteria criteria) {
        return getSalesCount(criteria.getParent(), (E) criteria.getSelectionDto());

    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        List list = getSalesData(criteria.getParent(), (E) criteria.getSelectionDto(), criteria.getStart(), criteria.getOffset());
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
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
     * @param monthNo
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

    public int getSalesCount(Object parentId, E selection) {

        List input = new ArrayList();
        int levelNo;
        TreeMap<String, Integer> masterSids;
        input.add(selection.getDataSelectionDTO().getProjectionId());
        if (parentId instanceof AdjustmentDTO) {
            AdjustmentDTO dto = (AdjustmentDTO) parentId;
            levelNo = dto.getLevelNo();

            masterSids = (TreeMap<String, Integer>) dto.getMasterIds().clone();
            masterSids.put(ARMUtils.getLevelAndLevelFilterMultiPeriod("Trx6_Inventory").get(levelNo), Integer.valueOf(dto.getBrand_item_masterSid()));
            levelNo++;
        } else {
            masterSids = ARMUtils.getMasterIdsMapForTrx6();
            levelNo = 1;
            if(selection.getSales_levelFilterValue().equals("Brand")){
                levelNo = NumericConstants.TWO;
            } else if(selection.getSales_levelFilterValue().equals("Product")){
                levelNo = NumericConstants.THREE;
            }
        }
        input.add(ARMUtils.getLevelAndLevelFilterMultiPeriod("Trx6_Inventory").get(levelNo));
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            input.add("ARM_INFLATION_INVENTORY");
        } else {
            input.add(selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
        }
        for (String input1 : masterSids.keySet()) {
            input.add(masterSids.get(input1) == null ? "%" : masterSids.get(input1));
        }
        List result = QueryUtils.getItemData(input, "getTrx6_Inventory_Count", null);
        return CommonLogic.getCount(result);
    }

    public List<AdjustmentDTO> getSalesData(Object parentId, E selection, int start, int offset) {

        List input = new ArrayList();
        int levelNo = 0;
        TreeMap<String, Integer> masterSids = new TreeMap<>();
        input.add(selection.getDataSelectionDTO().getProjectionId());
        input.add(selection.getSales_levelFilterValue());
        if (parentId instanceof AdjustmentDTO) {
            AdjustmentDTO dto = (AdjustmentDTO) parentId;
            levelNo = dto.getLevelNo();
            masterSids = (TreeMap<String, Integer>) dto.getMasterIds().clone();
            masterSids.put(ARMUtils.getLevelAndLevelFilterMultiPeriod("Trx6_Inventory").get(levelNo), Integer.valueOf(dto.getBrand_item_masterSid()));
            levelNo++;
        } else {
            masterSids = ARMUtils.getMasterIdsMapForTrx6();
            levelNo = 1;
            if(selection.getSales_levelFilterValue().equals("Brand")){
                levelNo = NumericConstants.TWO;
            } else if(selection.getSales_levelFilterValue().equals("Product")){
                levelNo = NumericConstants.THREE;
            }            
        }
        selection.setLevelNo(levelNo);
        selection.setMasterSids(masterSids);
        input.add(ARMUtils.getLevelAndLevelFilterMultiPeriod("Trx6_Inventory").get(levelNo));
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            input.add("ARM_INFLATION_INVENTORY");
        } else {
             input.add(selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
        }
        for (String input1 : masterSids.keySet()) {
            input.add(masterSids.get(input1) == null ? "%" : masterSids.get(input1));
        }
        input.add(start);
        input.add(offset);
        List result = QueryUtils.getItemData(input, "getTrx6_SgetSalesBrandInventory", null);
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

    private List<AdjustmentDTO> cutomize(List<Object[]> data, E selection) {
        List<AdjustmentDTO> resultList = new ArrayList<>();
        List<String> variables = selection.getInventoryHeaderList();
        int index = 0;
        String lastItem = "";
        AdjustmentDTO dto = null;
        Map<String, String> fixedColumnList = selection.getInventory_fixed_ColumnList();

        for (int i = 0; i < data.size(); i++) {
            Object[] obj = data.get(i);
            String item = obj[0] == null ? StringUtils.EMPTY : obj[0].toString();
            if (!item.equals(lastItem)) {
                dto = new AdjustmentDTO();
                dto.setMasterIds((TreeMap<String, Integer>) selection.getMasterSids().clone());
                dto.setLevelNo(selection.getLevelNo());
                dto.setBrand_item_no(obj[1].toString());
                dto.setBrand_item_name(obj[1].toString());
                dto.setBrand_item_masterSid(item);
                dto.setChildrenAllowed(obj[NumericConstants.FOURTEEN] == null ? false : ((Integer) obj[NumericConstants.FOURTEEN] != 0));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.TOTAL_INVENTORY.getColumn()), obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.TWO].toString(), ARMUtils.ZERO_DECIMAL));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.BASELINE_PRICE.getColumn()), obj[NumericConstants.THREE] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.THREE].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn()), obj[NumericConstants.FOUR] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.FOUR].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.ADJUSTED_PRICE.getColumn()), obj[NumericConstants.SIX] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.SIX].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn()), obj[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.SEVEN].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.PRICE_CHANGE.getColumn()), obj[NumericConstants.NINE] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.NINE].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.PRICE_CHANGE_PERCENT.getColumn()), obj[NumericConstants.TEN] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.TEN].toString(), ARMUtils.TWO_DECIMAL_PERCENT) + "%");
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.BASELINE_CALCULATED_AMOUNT.getColumn()), obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.FIVE].toString(), ARMUtils.ZERO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.ADJUSTED_CALCULATED_AMOUNT.getColumn()), obj[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.EIGHT].toString(), ARMUtils.ZERO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.NET_CALCULATED_AMOUNT.getColumn()), obj[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.ELEVEN].toString(), ARMUtils.ZERO_DECIMAL_CURRENCY));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.INFLATION_FACTOR.getColumn()), obj[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.TWELVE].toString(), ARMUtils.TWO_DECIMAL_PERCENT));
                dto.addStringProperties(fixedColumnList.get(ARMUtils.Trx6_Variables.INFLATION_ADJUSTMENT.getColumn()), obj[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : ARMUtils.getFormattedValue(obj[NumericConstants.THIRTEEN].toString(), ARMUtils.TWO_DECIMAL_CURRENCY));
                resultList.add(dto);
                index = 0; 
            }
            if (dto != null) {
                int dynamicIndex = index++;
                if (variables.get(dynamicIndex).contains("~")) {
                    dto.addStringProperties(variables.get(dynamicIndex), obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                }

            }
            lastItem = item;
        }

        return resultList;
    }

    public boolean updatePriceOverride(List input) {
        try {
            QueryUtils.itemUpdate(input, "Inventory_updates");
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;

    }

    public void getInventoryResults(Object[] orderedArgs) {
        callProcedure("PRC_ARM_INFLATION_ADJUSTMENT", orderedArgs);

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getInventoryExcelQuery_For_TRx6_View");
        } else {
            query = SQlUtil.getQuery("getInventoryExcelQuery_For_TRx6");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionDTO().getSessionId()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }

    @Override
    public List<Object> generateInventoryHeader(E selection) {
        List<Object> finalList = new ArrayList<>();
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> defaultVisibleColumn = new ArrayList<>();
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> singleColumn = new ArrayList<>();
        int index = 0;
        int defaultindex = 0;
        List<String> columnList = getColumns(selection.getSales_variables());
        String[] variableVisibleColumn = selection.getVariableVisibleColumns();
        HashMap<String, String> map = new HashMap<>();
        for (String column : variableVisibleColumn) {
            singleColumn.add(column + "." + index++);

            if (columnList.contains(column)) {
                int listIndex = columnList.indexOf(column);
                String visibleColumn = selection.getSales_variables().get(listIndex)[0] + "." + index;
                String header = selection.getSales_variables().get(listIndex)[1];
                singleVisibleColumn.add(visibleColumn);
                singleVisibleHeader.add(header);
            }

            if (column.contains("~") && columnList.contains(column)) {
                int listIndex = columnList.indexOf(column);
                String visibleColumn = selection.getSales_variables().get(listIndex)[0] + "." + index;
                defaultVisibleColumn.add(visibleColumn);
            } else if (!column.contains("~")) {
                String column1 = ARMUtils.VARIABLE_INVENTORY_VISIBLE_COLUMN[defaultindex++];
                String visibleColumn = column1 + "." + index;
                defaultVisibleColumn.add(visibleColumn);
                map.put(column1, visibleColumn);
            }
        }
        finalList.add(singleVisibleColumn);
        finalList.add(singleVisibleHeader);
        selection.setInventoryHeaderList(defaultVisibleColumn);
        selection.setInventory_fixed_ColumnList(map);
        return finalList;
    }

    public boolean updateForCalculte(List input,String tempTableName) {
        try {
            QueryUtils.itemUpdate(input, "Inventory_calculte");

            input.add(0, tempTableName);
            input.add(1, input.get(NumericConstants.TWO));

             
            QueryUtils.itemUpdate(input, "Inventory_calculte1");
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
    
     public List getInflationFactor(List input){
        List result = QueryUtils.getItemData(input, "getInflationFactor", null);        
        return result;
    } 

}
