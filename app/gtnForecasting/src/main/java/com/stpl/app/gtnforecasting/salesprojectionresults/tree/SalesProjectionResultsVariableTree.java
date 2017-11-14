/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.tree;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRCP;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRCustom;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.LabelConstants.SPRDASH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Abishek.Ram
 */
public class SalesProjectionResultsVariableTree extends SalesProjectionResultsTree {

    Object[] dataLevel;
    Object[] showData;

    @Override
    public void buildTree(ProjectionSelectionDTO projSelDTO) {
        loadStaticRows(projSelDTO);
        super.buildTree(projSelDTO); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SalesProjectionResultsDTO getStaticData(int position) {
        if (position == 0) {
            return loadProjectioTotal();
        }
        SalesProjectionResultsDTO staticData = new SalesProjectionResultsDTO();
        staticData.setLevelValue(getSPRStaticLevelValue(dataLevel[position - 1]));
        loadVariableStaticData(staticData, position - 1);
        return staticData;

    }

    private String getSPRStaticLevelValue(Object value) {
        String stringValue = String.valueOf(value);
        char[] valueArray = stringValue.toCharArray();
       if (!Constants.MONTH.equals(creteria.frequency) && (valueArray[0] == 'Q' || valueArray[0] == 'S')) {
            char temp = valueArray[0];
            valueArray[0] = valueArray[1];
            valueArray[1] = temp;
            return String.valueOf(valueArray).replaceAll("\\s+", "");
        }
        return stringValue;
    }

    private void loadStaticRows(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setPivotView(Constant.PERIOD);
        CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
        projSelDTO.setProjTabName("SPR");
        CustomTableHeaderDTO table = HeaderUtils.getSalesProjectionResultsCalculatedColumns(new CustomTableHeaderDTO(), projSelDTO, fullHeader);
        dataLevel = table.getDoubleHeaders().toArray();
        showData = table.getDoubleColumns().toArray();
        projSelDTO.setPivotView(Constant.VARIABLE);
    }

    @Override
    protected void addStaticDataRow(SalesPRCP child) {
        for (Object dataLevel1 : dataLevel) {
            SalesPRCP salesprCP = new SalesPRCP(String.valueOf(dataLevel1));
            salesprCP.setStatic(true);
            child.addChild(salesprCP);
        }
    }

    @Override
    protected void addStaticLinesToTree(SalesPRCP apex) {
        SalesPRCP projectionTotal = new SalesPRCP(SPRStaticData.PROJECTION_TOTAL.getLabel());
        projectionTotal.setStatic(true);
        apex.addChild(projectionTotal);
        for (Object levelName : showData) {
            SalesPRCP statcLevel = new SalesPRCP(String.valueOf(levelName));
            apex.addChild(statcLevel);
            statcLevel.setStatic(true);
        }
    }

    @Override
    protected void addStaticLinesToTreeCustom(SalesPRCustom apex) {
        SalesPRCustom projectionTotal = new SalesPRCustom(SPRStaticData.PROJECTION_TOTAL.getLabel());
        projectionTotal.setStatic(true);
        apex.addChild(projectionTotal);
        for (Object levelName : showData) {
            SalesPRCustom statcLevel = new SalesPRCustom(String.valueOf(levelName));
            apex.addChild(statcLevel);
            statcLevel.setStatic(true);
        }
    }

    @Override
    protected void addStaticRow(SalesPRCustom child) {
        for (Object dataLevel1 : dataLevel) {
            SalesPRCustom salesprCustom = new SalesPRCustom(String.valueOf(dataLevel1));
            salesprCustom.setStatic(true);
            child.addChild(salesprCustom);
        }
    }

    private void loadVariableStaticData(SalesProjectionResultsDTO staticRow, int position) {
        SPRStaticData[] basicStatic = {SPRStaticData.EXFACTORY_SALES, SPRStaticData.DEMAND_SALES, SPRStaticData.INVENTORY_WITHDRAW};
        String[] commonColumn = new String[]{"efs", "dms", "iws"};
        for (int i = 0; i < basicStatic.length; i++) {
            loadActualData(basicStatic[i].getStaticData(), staticRow, position, commonColumn[i]);
            loadProjectionData(basicStatic[i].getStaticData(), staticRow, position, commonColumn[i]);
        }

        loadExfactoryStaticrow(staticRow, position); 
        
        if (isSalesNeed()) {
            loadSalesStaticrow(staticRow, position);
        }
        if (isUnitNeeded()) {
            loadUnitStaticRow(staticRow, position);
        }

        // 
    }

    private SalesProjectionResultsDTO loadProjectioTotal() {
        SalesProjectionResultsDTO projTotal = new SalesProjectionResultsDTO();
        projTotal.setLevelValue(SPRStaticData.PROJECTION_TOTAL.getLabel());
        return projTotal;
    }

    private void loadActualData(SalesProjectionResultsDTO staticDatas, SalesProjectionResultsDTO staticRow, int position, String coloumnName) {
        String value = String.valueOf(staticDatas.getProperties().get(showData[position] + Constant.ACTUALS));
        staticRow.addStringProperties(coloumnName + Constant.ACTUALS, getFormattedValue(value));
    }

    private void loadProjectionData(SalesProjectionResultsDTO staticDatas, SalesProjectionResultsDTO staticRow, int position, String coloumnName) {
        String value = String.valueOf(staticDatas.getProperties().get(showData[position] + Constant.PROJECTIONS));
        staticRow.addStringProperties(coloumnName + Constant.PROJECTIONS, getFormattedValue(value));
    }

    private void loadSalesStaticrow(SalesProjectionResultsDTO staticRow, int position) {
        loadsalesData(SPRStaticData.SALES.getStaticData(), staticRow, position);
    }
    
    private void loadExfactoryStaticrow(SalesProjectionResultsDTO staticRow, int position) {
        loadExfactoryData(SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT.getStaticData(), staticRow, position);
    }

    private void loadsalesData(SalesProjectionResultsDTO sales, SalesProjectionResultsDTO staticRow, int position) {
        loadActualData(sales, staticRow, position, "csw");
        loadProjectionData(sales, staticRow, position, "csw");
    }
    private void loadExfactoryData(SalesProjectionResultsDTO sales, SalesProjectionResultsDTO staticRow, int position) {
        loadActualData(sales, staticRow, position, "cexs");
        loadProjectionData(sales, staticRow, position, "cexs");
    }

    private void loadUnitStaticRow(SalesProjectionResultsDTO staticRow, int position) {
        loadUnitData(SPRStaticData.UNIT.getStaticData(), staticRow, position);
    }

    private void loadUnitData(SalesProjectionResultsDTO unit, SalesProjectionResultsDTO staticRow, int position) {
        loadActualData(unit, staticRow, position, "uv");
        loadProjectionData(unit, staticRow, position, "uv");
    }

    public String getFormattedValue(String value) {
        if (value.contains(Constant.NULL)) {
            value = SPRDASH.getConstant();
        }
        return value;
    }

    @Override
    public void loadStaticLevelRow(List<SalesProjectionResultsDTO[]> dto, Set<TreeNode> dbLoadedSalesPRNode, Map<String, SalesProjectionResultsDTO> loadDataMap) {
        int i = 0;
        for (TreeNode treeNode : dbLoadedSalesPRNode) {
            int k = 1;
            SalesProjectionResultsDTO[] cuurentData = dto.get(i++);
            for (int s = 0; s < showData.length; s++) {
                SalesProjectionResultsDTO staticLevelRow = new SalesProjectionResultsDTO();
                staticLevelRow.setLevelValue(String.valueOf(dataLevel[s]));
                if (isSalesNeed()) {
                    loadsalesData(cuurentData[0], staticLevelRow, s);
                }
                if (isUnitNeeded()) {
                    loadUnitData(cuurentData[1], staticLevelRow, s);
                }
                loadExfactoryData(cuurentData[2], staticLevelRow, s);
                loadDataMap.put(treeNode.getHierarchyForTable() + k + ".", staticLevelRow);
                k++;
            }

        }
    }
}
