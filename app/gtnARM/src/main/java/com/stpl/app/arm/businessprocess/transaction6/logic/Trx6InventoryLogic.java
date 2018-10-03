/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 * @param <T>
 * @param <E>
 */
public class Trx6InventoryLogic<T extends AdjustmentDTO, E extends Trx6SelectionDTO> extends AbstractPipelineLogic<T, E> {

    public static final String ARM_INFLATION_INVENTORY = "ARM_INFLATION_INVENTORY";

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

    @Override
    public List<Object> getMonthYear() {
        String sqlQuery = SQlUtil.getQuery("getMonthYear");
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        List<Object> defaultValues = new ArrayList<>();
        if (!resultList.isEmpty()) {
            Object[] value = (Object[]) resultList.get(0);
            for (Object value1 : value) {
                defaultValues.add(String.valueOf(value1));
            }
        }
        return defaultValues;
    }

    /**
     * Get month name from month number
     *
     * @param inflationMonthNo
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int inflationMonthNo) {
        String inventoryMonthName = StringUtils.EMPTY;
        try {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            inventoryMonthName = months[inflationMonthNo - 1];
        } catch (Exception e) {
            LOGGER.error("Error in getMonthName :", e);
        }
        return inventoryMonthName;
    }

    public int getSalesCount(Object inflationParentId, E inflationSelection) {

        List input = new ArrayList();
        int levelNo;
        TreeMap<String, Integer> inflationMasterSids;
        input.add(inflationSelection.getDataSelectionDTO().getProjectionId());
        if (inflationParentId instanceof AdjustmentDTO) {
            AdjustmentDTO dto = (AdjustmentDTO) inflationParentId;
            levelNo = dto.getLevelNo();

            inflationMasterSids = new TreeMap<>(dto.getMasterIds());
            inflationMasterSids.put(ARMUtils.getInstance().getLevelAndLevelFilterMultiPeriod(CommonConstant.TRX6_INVENTORY).get(levelNo), Integer.valueOf(dto.getBranditemmasterSid()));
            levelNo++;
        } else {
            inflationMasterSids = (TreeMap<String, Integer>) ARMUtils.getMasterIdsMapForTrx6();
            levelNo = 1;
            if ("Brand".equals(inflationSelection.getSaleslevelFilterValue())) {
                levelNo = NumericConstants.TWO;
            } else if ("Product".equals(inflationSelection.getSaleslevelFilterValue())) {
                levelNo = NumericConstants.THREE;
            }
        }
        input.add(ARMUtils.getInstance().getLevelAndLevelFilterMultiPeriod(CommonConstant.TRX6_INVENTORY).get(levelNo));
        if (inflationSelection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            input.add(ARM_INFLATION_INVENTORY);
        } else {
            input.add(inflationSelection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
        }
        for (Map.Entry<String, Integer> entry : inflationMasterSids.entrySet()) {
            input.add(entry.getValue() == null ? "%" : entry.getValue());
        }
        List result = QueryUtils.getItemData(input, "getTrx6_Inventory_Count", null);
        return CommonLogic.getCount(result);
    }

    public List<AdjustmentDTO> getSalesData(Object inflationParentId, E inflationSelection, int start, int offset) {

        List input = new ArrayList();
        int levelNo = 0;
        TreeMap<String, Integer> masterSids;
        input.add(inflationSelection.getDataSelectionDTO().getProjectionId());
        input.add(inflationSelection.getSaleslevelFilterValue());
        if (inflationParentId instanceof AdjustmentDTO) {
            AdjustmentDTO dto = (AdjustmentDTO) inflationParentId;
            levelNo = dto.getLevelNo();
            masterSids = new TreeMap<>(dto.getMasterIds());
            masterSids.put(ARMUtils.getInstance().getLevelAndLevelFilterMultiPeriod(CommonConstant.TRX6_INVENTORY).get(levelNo), Integer.valueOf(dto.getBranditemmasterSid()));
            levelNo++;
        } else {
            masterSids = (TreeMap<String, Integer>) ARMUtils.getMasterIdsMapForTrx6();
            levelNo = 1;
            if ("Brand".equals(inflationSelection.getSaleslevelFilterValue())) {
                levelNo = NumericConstants.TWO;
            } else if ("Product".equals(inflationSelection.getSaleslevelFilterValue())) {
                levelNo = NumericConstants.THREE;
            }
        }
        inflationSelection.setLevelNo(levelNo);
        inflationSelection.setMasterSids(masterSids);
        input.add(ARMUtils.getInstance().getLevelAndLevelFilterMultiPeriod(CommonConstant.TRX6_INVENTORY).get(levelNo));
        if (inflationSelection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            input.add(ARM_INFLATION_INVENTORY);
        } else {
            input.add(inflationSelection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
        }

        for (Map.Entry<String, Integer> entry : masterSids.entrySet()) {
            input.add(entry.getValue() == null ? "%" : entry.getValue());
        }
        input.add(start);
        input.add(offset);
        List result = QueryUtils.getItemData(input, "getTrx6_SgetSalesBrandInventory", null);
        return cutomize(result, inflationSelection);
    }

    private List<AdjustmentDTO> cutomize(List<Object[]> data, E selection) {
        List<AdjustmentDTO> resultList = new ArrayList<>();
        List<String> variables = selection.getInventoryHeaderList();
        int index = 0;
        String lastItem = "";
        AdjustmentDTO dto = null;
        Map<String, String> fixedColumnList = selection.getInventoryfixedColumnList();

        for (int i = 0; i < data.size(); i++) {
            Object[] obj = data.get(i);
            String item = obj[0] == null ? StringUtils.EMPTY : obj[0].toString();
            if (!item.equals(lastItem)) {
                dto = new AdjustmentDTO();
                dto.setMasterIds(new TreeMap<>(selection.getMasterSids()));
                dto.setLevelNo(selection.getLevelNo());
                dto.setBranditemno(obj[1].toString());
                dto.setBranditemname(obj[1].toString());
                dto.setBranditemmasterSid(item);
                dto.setChildrenAllowed(!String.valueOf(obj[NumericConstants.FOURTEEN]).equals(0));
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
            LOGGER.error("Error in updatePriceOverride :", e);
            return false;
        }
        return true;

    }

    public void getInventoryResults(Object[] orderedArgs) {
        QueryUtil.callProcedure("PRC_ARM_INFLATION_ADJUSTMENT", orderedArgs);
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO inflationSelection) {
        String query;
        boolean isView = inflationSelection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getInventoryExcelQuery_For_TRx6_View");
        } else {
            query = SQlUtil.getQuery("getInventoryExcelQuery_For_TRx6");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(inflationSelection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(inflationSelection.getSessionDTO().getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(inflationSelection.getSessionDTO().getSessionId()));
        query = query.replace("@ARM_INVENTORY_TABLE_VIEW", isView ? ARM_INFLATION_INVENTORY : "CONCAT( 'ST_ARM_INFLATION_INVENTORY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE( CONVERT( VARCHAR( 50 ), GETDATE(), 2 ), '.', '' ))");
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, inflationSelection.getSessionDTO().getCurrentTableNames()));
    }

    @Override
    public List<Object> generateInventoryHeader(E selection) {
        List<Object> finalList = new ArrayList<>();
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> defaultVisibleColumn = new ArrayList<>();
        List<String> singleVisibleHeader = new ArrayList<>();
        int index = 0;
        int defaultindex = 0;
        List<String> columnList = getColumns(selection.getSalesVariables());
        String[] variableVisibleColumn = selection.getVariableVisibleColumns();
        HashMap<String, String> map = new HashMap<>();
        for (String inflationColumn : variableVisibleColumn) {

            if (columnList.contains(inflationColumn)) {
                int listIndex = columnList.indexOf(inflationColumn);
                String visibleColumn = selection.getSalesVariables().get(listIndex)[0] + ARMUtils.DOT + index;
                String header = selection.getSalesVariables().get(listIndex)[1];
                singleVisibleColumn.add(visibleColumn);
                singleVisibleHeader.add(header);
            }

            if (inflationColumn.contains("~") && columnList.contains(inflationColumn)) {
                int listIndex = columnList.indexOf(inflationColumn);
                String visibleColumn = selection.getSalesVariables().get(listIndex)[0] + ARMUtils.DOT + index;
                defaultVisibleColumn.add(visibleColumn);
            } else if (!inflationColumn.contains("~")) {
                String column1 = ARMUtils.getVariableInventoryVisibleColumn()[defaultindex++];
                String visibleColumn = column1 + ARMUtils.DOT + index;
                defaultVisibleColumn.add(visibleColumn);
                map.put(column1, visibleColumn);
            }
            index++;
        }
        finalList.add(singleVisibleColumn);
        finalList.add(singleVisibleHeader);
        selection.setInventoryHeaderList(defaultVisibleColumn);
        selection.setInventoryfixedColumnList(map);
        return finalList;
    }

    public boolean updateForCalculte(List input, String tempTableName) {
        try {
            QueryUtils.itemUpdate(input, "Inventory_calculte");

            input.add(0, tempTableName);
            input.add(1, input.get(NumericConstants.TWO));

            QueryUtils.itemUpdate(input, "Inventory_calculte1");
        } catch (Exception e) {
            LOGGER.error("Error in updateForCalculte :", e);
            return false;
        }
        return true;
    }

    public List getInflationFactor(List input) {
        return QueryUtils.getItemData(input, "getInflationFactor", null);
    }

}
