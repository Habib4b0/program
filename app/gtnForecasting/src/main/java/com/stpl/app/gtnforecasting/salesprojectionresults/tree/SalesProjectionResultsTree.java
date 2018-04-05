/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.tree;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRBaseNode;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRCP;
import com.stpl.app.gtnforecasting.salesprojectionresults.tree.node.SalesPRCustom;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.LabelConstants.VARIABLE;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public class SalesProjectionResultsTree {

    private SalesPRBaseNode apex;
    protected SalesProjectionResultsTreeBuildCreteria creteria;
    private SPRStaticData[] staticdata;

    public SalesProjectionResultsTree() {
        super();
    }

    public SalesPRBaseNode getApex() {
        return apex;
    }

    public SalesProjectionResultsDTO getStaticData(int position) {
        return staticdata[position].getStaticData();
    }

    public void setCurrentApex(SalesPRBaseNode generatedApex) {
        this.apex = generatedApex;
    }

    public void buildTree(ProjectionSelectionDTO projSelDTO) {
        if (creteria == null) {
            creteria = new SalesProjectionResultsTreeBuildCreteria(projSelDTO);
        }
        if (projSelDTO.getTreeLevelNo() != 0 && creteria.isNeedtoReBuildTree(projSelDTO)) {
            if (projSelDTO.isIsCustomHierarchy()) {
                buildCustomTree(projSelDTO);
            } else {
                buildForCP(projSelDTO);
            }
        }
    }

    public void buildCustomTree(ProjectionSelectionDTO projSelDTO) {
        List<Object[]> customViewList = getAvailableHierarchiesCustom(projSelDTO);
        SalesPRBaseNode customApex = generateCustomTree(customViewList);
        setCurrentApex(customApex);
    }

    private List<Object[]> getAvailableHierarchiesCustom(ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getCustomId() == 0) {
            return Collections.EMPTY_LIST;
        }
        String query = SQlUtil.getQuery("custom-relationship");
        query = query.replace("[?CUST_RELATIONSHIP_BUILDER_SID]", projSelDTO.getSessionDTO().getCustRelationshipBuilderSid());
        query = query.replace("[?PROD_RELATIONSHIP_BUILDER_SID]", projSelDTO.getSessionDTO().getProdRelationshipBuilderSid());
        query = query.replace("[?Custom_View_Master_SID]", String.valueOf(projSelDTO.getCustomId()));
        if (!String.valueOf(projSelDTO.getGroupFilter()).isEmpty()) {
            query = query.replace("[?UserGroup]", "and PPA.USER_GROUP like " + String.valueOf(projSelDTO.getGroupFilter()));
        } else {
            query = query.replace("[?UserGroup]", "and PPA.USER_GROUP like '%'");
        }
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));

    }

    private SalesPRBaseNode generateCustomTree(List<Object[]> hierarchyList) {
        SalesPRCustom apexNode = new SalesPRCustom("");
        apexNode.setApex(true);
        addStaticLinesToTreeCustom(apexNode);
        HashMap buildMap = new HashMap<>(hierarchyList.size());
        for (Object[] object : hierarchyList) {
            String hiearachy = String.valueOf(object[0]);
            SalesPRCustom salesNode = new SalesPRCustom(hiearachy);
            String parentHierarchy = String.valueOf(object[4]);
            if (parentHierarchy.equals("null")) {
                salesNode.setHierarchyIndicator(String.valueOf(object[3]));
                salesNode.setLevel(Integer.parseInt(String.valueOf(object[2])));
                addToChildCustom(apexNode, salesNode, buildMap);
                buildMap.put(hiearachy, salesNode);
            } else {
                String[] parentHierarrchy = parentHierarchy.split("~");
                SalesPRCustom parent;
                String secondParent = "";
                if (parentHierarrchy.length > 1) {
                    secondParent = parentHierarrchy[parentHierarrchy.length - 2];
                }
                parent = (SalesPRCustom) buildMap.get(parentHierarrchy[parentHierarrchy.length - 1] + secondParent);
                salesNode.addParentNode(parent);
                salesNode.setHierarchyIndicator(String.valueOf(object[3]));
                salesNode.setLevel(Integer.parseInt(String.valueOf(object[2])));
                addToChildCustom(parent, salesNode, buildMap);
                buildMap.put(hiearachy + parent.getHierachyNo(), salesNode);
            }
        }
        return apexNode;
    }

    private void addToChildCustom(SalesPRCustom parent, SalesPRCustom child, HashMap dataMap) {
        addSalesGroupCustom(parent, child, dataMap);
        addStaticRow(child);
    }

    private void addSalesGroupCustom(SalesPRCustom parent, SalesPRCustom child, HashMap dataMap) {
        if (child.getLevel() == creteria.tpLevel && creteria.tpLevel != 0) {
            SalesPRBaseNode tpParent = (SalesPRBaseNode) dataMap.get(parent.getHierachyNo() + "~All Sales Groups");
            if (tpParent == null) {
                tpParent = new SalesPRCustom(Constant.ALL_SALES_GROUP);
                tpParent.setStatic(true);
                dataMap.put(parent.getHierachyNo() + "~All Sales Groups", tpParent);
                parent.addChild(tpParent);
            }
            tpParent.addChild(child);
        } else {
            parent.addChild(child);
        }
        dataMap.put(child.getHierachyNo() + parent.getHierachyNo(), child);

    }

    protected void addStaticRow(SalesPRCustom child) {
        if (isSalesNeed()) {
            SalesPRCustom contractSales = new SalesPRCustom(SPRStaticData.SALES.getLabel());
            contractSales.setStatic(true);
            child.addChild(contractSales);
        }

        if (isUnitNeeded()) {
            SalesPRCustom unitVolume = new SalesPRCustom(SPRStaticData.UNIT.getLabel());
            unitVolume.setStatic(true);
            child.addChild(unitVolume);
        }
        SalesPRCustom contractSalesasExfact = new SalesPRCustom(SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT.getLabel());
        contractSalesasExfact.setStatic(true);
        child.addChild(contractSalesasExfact);
    }

    public void buildForCP(ProjectionSelectionDTO projSelDTO) {
        List<Object[]> customViewList = getAvailableHierarchiesCP(projSelDTO);
        Map<String, String> salesGroupRelation = getSalesGoupCombination(projSelDTO);
        SalesPRBaseNode customApex = null;
        if (projSelDTO.isIsFilter()) {
            customApex = generateCPForLevelFilter(projSelDTO.getFilterLevelNo(), customViewList);
        } else {
            customApex = generateCPTree(customViewList, salesGroupRelation);
        }

        setCurrentApex(customApex);
    }

    private SalesPRBaseNode generateCPForLevelFilter(int levelFiltered, List<Object[]> availableHierarachies) {
        SalesPRCP apex = new SalesPRCP("");
        if (!availableHierarachies.isEmpty()) {
            apex.setApex(true);
            List<String> child = new ArrayList<>();
            for (Object availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                int level = StringUtils.countMatches(hierarchy, ".");
                if (levelFiltered == level) {
                    child.add(hierarchy);
                }
            }
            addChildrenToParent(apex, child, levelFiltered, new HashMap(), new HashMap<String, String>());
        }
        return apex;
    }

    private List<Object[]> getAvailableHierarchiesCP(ProjectionSelectionDTO projSelDTO) {
        CommonLogic cmLogic = new CommonLogic();
        String sql = cmLogic.insertAvailableHierarchyNoForExpand(projSelDTO)
                + " Select  distinct HIERARCHY_NO from #SELECTED_HIERARCHY_NO order by HIERARCHY_NO";
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames()));
    }

    private SalesPRBaseNode generateCPTree(List<Object[]> availableHierarachies, Map<String, String> salesGroupRelation) {
        SalesPRCP apex = new SalesPRCP("");
        HashMap dataMap = new HashMap<>(availableHierarachies.size());
        apex.setApex(true);
        addStaticLinesToTree(apex);
        if (!availableHierarachies.isEmpty()) {
            int startLevel = StringUtils.countMatches(String.valueOf(availableHierarachies.get(0)), ".");
            List<String> child = new ArrayList<>();
            int currentLevel = startLevel;
            SalesPRCP parent = apex;
            for (Object availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                int level = StringUtils.countMatches(hierarchy, ".");
                if (level == currentLevel) {
                    child.add(hierarchy);
                } else {
                    if (currentLevel == startLevel) {
                        parent = addChildrenToParent(apex, child, currentLevel, dataMap, salesGroupRelation);
                    } else {
                        parent = addChildrenToParent(parent, child, currentLevel, dataMap, salesGroupRelation);
                    }
                    if (level < currentLevel) {
                        parent = (SalesPRCP) getParent(hierarchy, dataMap, currentLevel, startLevel, apex);
                    }
                    child = new ArrayList<>();
                    child.add(hierarchy);
                    currentLevel = level;
                }
            }
            addChildrenToParent(parent, child, currentLevel, dataMap, salesGroupRelation);
        }
        return apex;
    }

    private SalesPRCP addChildrenToParent(SalesPRCP parent, List<String> child, int currentLevel, HashMap dataMap, Map<String, String> salesGroupRelation) {
        if (!child.isEmpty()) {
            for (int i = 0; i < child.size() - 1; i++) {
                SalesPRCP node = new SalesPRCP(child.get(i));
                node.setLevel(currentLevel);
                addChildCP(parent, node, dataMap, salesGroupRelation);
            }
            SalesPRCP lastChild = new SalesPRCP(child.get(child.size() - 1));
            lastChild.setLevel(currentLevel);
            addChildCP(parent, lastChild, dataMap, salesGroupRelation);
            return lastChild;
        }
        return parent;
    }

    protected void addChildCP(SalesPRCP parent, SalesPRCP child, HashMap dataMap, Map<String, String> salesGroupRelation) {
        addSalesGroup(parent, child, dataMap, salesGroupRelation);
        addStaticDataRow(child);
    }

    private void addSalesGroup(SalesPRCP parent, SalesPRCP child, HashMap dataMap, Map<String, String> salesGroupRelation) {
        if (!creteria.cpIndicator.equals("P") && child.getLevel() == creteria.tpLevel && !creteria.isFilter) {
            String userGroup = salesGroupRelation.get(child.getHierachyNo());
            userGroup = userGroup == null ? Constant.ALL_SALES_GROUP : userGroup.equals("0") ? Constant.ALL_SALES_GROUP : "Sales Group -" + userGroup;
            SalesPRBaseNode tpParent = (SalesPRBaseNode) dataMap.get(parent.getHierachyNo() + "~" + userGroup);
            if (tpParent == null) {
                tpParent = new SalesPRCP(userGroup);
                tpParent.setStatic(true);
                dataMap.put(parent.getHierachyNo() + "~" + userGroup, tpParent);
                parent.addChild(tpParent);
            }
            tpParent.addChild(child);
        } else {
            parent.addChild(child);
        }
        dataMap.put(child.getHierachyNo(), child);
    }

    protected void addStaticDataRow(SalesPRCP child) {
        if (isSalesNeed()) {
            SalesPRCP contractSales = new SalesPRCP(SPRStaticData.SALES.getLabel());
            contractSales.setStatic(true);
            child.addChild(contractSales);
        }

        if (isUnitNeeded()) {
            SalesPRCP unitVolume = new SalesPRCP(SPRStaticData.UNIT.getLabel());
            unitVolume.setStatic(true);
            child.addChild(unitVolume);
        }
        SalesPRCP contractSalesasExfact = new SalesPRCP(SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT.getLabel());
        contractSalesasExfact.setStatic(true);
        child.addChild(contractSalesasExfact);
    }

    private TreeNode getParent(String hierarchy, HashMap dataMap, int currentLevel, int startLevel, TreeNode apex) {
        TreeNode parent = (TreeNode) dataMap.get(hierarchy);
        if (parent == null) {
            if (startLevel == currentLevel) {
                parent = apex;
                return parent;
            }
            String[] istParent = hierarchy.split("\\.");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < istParent.length - 1; i++) {
                builder.append(istParent[i]).append('.');
            }
            parent = getParent(builder.toString(), dataMap, currentLevel - 1, startLevel, apex);
        }
        return parent;
    }

    protected void addStaticLinesToTree(SalesPRCP apex) {
        Enum[] basicStatic = {SPRStaticData.PROJECTION_TOTAL, SPRStaticData.EXFACTORY_SALES, SPRStaticData.DEMAND_SALES, SPRStaticData.INVENTORY_WITHDRAW};
        List<SPRStaticData> staticList = new ArrayList(Arrays.asList(basicStatic));

        if (isSalesNeed()) {
            staticList.add(SPRStaticData.SALES);
        }
        if (isUnitNeeded()) {
            staticList.add(SPRStaticData.UNIT);
        }
        staticList.add(SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT);
        for (SPRStaticData staticData : staticList) {
            SalesPRCP staticLine = new SalesPRCP(staticData.getLabel());
            staticLine.setStatic(true);
            apex.addChild(staticLine);
        }
        staticdata = staticList.toArray(new SPRStaticData[staticList.size()]);
    }

    protected void addStaticLinesToTreeCustom(SalesPRCustom apex) {
        Enum[] basicStatic = {SPRStaticData.PROJECTION_TOTAL, SPRStaticData.EXFACTORY_SALES, SPRStaticData.DEMAND_SALES, SPRStaticData.INVENTORY_WITHDRAW};
        List<SPRStaticData> staticList = new ArrayList(Arrays.asList(basicStatic));

        if (isSalesNeed()) {
            staticList.add(SPRStaticData.SALES);
        }
        if (isUnitNeeded()) {
            staticList.add(SPRStaticData.UNIT);
        }
        staticList.add(SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT);

        for (SPRStaticData staticData : staticList) {
            SalesPRCustom staticLine = new SalesPRCustom(staticData.getLabel());
            staticLine.setStatic(true);
            apex.addChild(staticLine);
        }
        staticdata = staticList.toArray(new SPRStaticData[staticList.size()]);
    }

    public boolean isSalesNeed() {
        return creteria.getSalesUnitVariable().equals(Constant.SALES_SMALL) || creteria.getSalesUnitVariable().equals(Constant.BOTH);
    }

    public boolean isUnitNeeded() {
        return creteria.getSalesUnitVariable().equals(Constant.UNITS_SMALL) || creteria.getSalesUnitVariable().equals(Constant.BOTH);

    }

    public TreeNode getHierarchy(TreeNode apex, String hierarchy) {
        if (!hierarchy.isEmpty()) {
            String[] istParent = hierarchy.split("\\.");
            for (String tParent : istParent) {
                apex = apex.getNthChild(Integer.parseInt(tParent) - 1);
            }
        }
        return apex;
    }

    public void loadStaticLevelRow(List<SalesProjectionResultsDTO[]> dto, Set<TreeNode> dbLoadedSalesPRNode, Map<String, SalesProjectionResultsDTO> loadDataMap) {
        int i = 0;
        for (TreeNode treeNode : dbLoadedSalesPRNode) {
            int k = 1;
            SalesProjectionResultsDTO[] cuurentData = dto.get(i++);
            if (isSalesNeed()) {
                loadDataMap.put(treeNode.getHierarchyForTable() + k + ".", cuurentData[0]);
                k++;
            }
            if (isUnitNeeded()) {
                loadDataMap.put(treeNode.getHierarchyForTable() + k + ".", cuurentData[1]);
                k++;
            }

            loadDataMap.put(treeNode.getHierarchyForTable() + k + ".", cuurentData[2]);

        }
    }

    private Map<String, String> getSalesGoupCombination(ProjectionSelectionDTO projSelDTO) {
        String sql = SQlUtil.getQuery("salesgroup-relation-query");;
        List<Object[]> dbData = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames()));
        Map<String, String> salesMap = new HashMap<>();
        for (Object[] objects : dbData) {
            String hierarchyNo = String.valueOf(objects[0]);
            String salesGroup = String.valueOf(objects[1]);
            salesMap.put(hierarchyNo, salesGroup);
        }
        return salesMap;
    }

    public enum SPRStaticData {

        SALES("Contract Sales @ WAC"),
        UNIT("Unit Volume"),
        PROJECTION_TOTAL("Projection Total"),
        EXFACTORY_SALES("Ex-Factory Sales"),
        DEMAND_SALES("Demand Sales"),
        INVENTORY_WITHDRAW("Inventory Withdraw"),
        CONTRACT_SALES_PERCENT_EXFACT("Contract Sales as % of Ex-Factory Sales");

        private SalesProjectionResultsDTO staticData;
        private final String staticDataLabel;

        private SPRStaticData(String staticData) {
            this.staticDataLabel = staticData;
        }

        public String getLabel() {
            return staticDataLabel;
        }

        protected void setStaticData(SalesProjectionResultsDTO staticData) {
            this.staticData = staticData;
        }

        public SalesProjectionResultsDTO getStaticData() {
            return staticData;
        }
    }

    class SalesProjectionResultsTreeBuildCreteria {

        private String salesUnitVariable;
        private String frequency;
        private String history;
        private String actualProjection;
        private String cpIndicator;
        private boolean custom;
        private int tpLevel;
        private int customId;
        private boolean isFirst = true;
        private final String pivotView;
        private int levelNo;
        private boolean isFilter;

        public String getSalesUnitVariable() {
            return salesUnitVariable;
        }

        public SalesProjectionResultsTreeBuildCreteria(ProjectionSelectionDTO projdto) {
            this.salesUnitVariable = projdto.getSalesOrUnit();
            custom = projdto.isIsCustomHierarchy();
            if (projdto.getCustomId() != 0) {
                cpIndicator = projdto.getHierarchyIndicator();
                customId = projdto.getCustomId();
            }
            tpLevel = projdto.getTpLevel();
            pivotView = projdto.getPivotView();
            levelNo = projdto.getFilterLevelNo();
             isFilter = projdto.isIsFilter();
        }

        public boolean isNeedtoReBuildTree(ProjectionSelectionDTO projdto) {
            checkStaticBuildCreteria(projdto);
            boolean isNeeded = !((projdto.isIsCustomHierarchy() == custom) && (projdto.getCustomId() == customId) && (projdto.getHierarchyIndicator().equals(cpIndicator)) && (projdto.getSalesOrUnit().equals(salesUnitVariable))) || isFirst;
            if (!(projdto.getGroupFilter().isEmpty() || projdto.getGroupFilter().equalsIgnoreCase(Constant.ALL_SALES_GROUP))) {
                isNeeded = true;
            }
            if (!projdto.getPivotView().equals(pivotView)) {
                isFirst = true;
            }
            if (projdto.getPivotView().contains(VARIABLE.getConstant())) {
                isNeeded = isNeeded || !(projdto.getFrequency().equals(getFrequency())
                        && projdto.getHistory().equals(history)) || isFirst;
            }
            isNeeded = isNeeded || !(projdto.isIsFilter() == isFilter);
            if (projdto.isIsFilter()) {
                isNeeded = isNeeded || !(projdto.getFilterLevelNo() == levelNo)
                        || isFirst;
            }
            isFirst = false;
            this.salesUnitVariable = projdto.getSalesOrUnit();
            cpIndicator = projdto.getHierarchyIndicator();
            custom = projdto.isIsCustomHierarchy();
            cpIndicator = projdto.getHierarchyIndicator();
            customId = projdto.getCustomId();
            setConfigData(projdto);
            tpLevel = projdto.getTpLevel();
            levelNo = projdto.getFilterLevelNo();
            isFilter = projdto.isIsFilter();
            return (isNeeded);
        }

        private void checkStaticBuildCreteria(ProjectionSelectionDTO projdto) {
            if (!(projdto.getFrequency().equals(getFrequency())
                    && projdto.getHistory().equals(history)
                    && projdto.getActualsOrProjections().equals(actualProjection)) || projdto.getSessionDTO().isIsSPCalculationDoneAgain()) {
                loadStaticData(projdto);
                projdto.getSessionDTO().setIsSPCalculationDoneAgain(false);
            }
        }

        private void setConfigData(ProjectionSelectionDTO projdto) {
            setFrequency(projdto.getFrequency());
            history = projdto.getHistory();
            actualProjection = projdto.getActualsOrProjections();
        }

        private void loadStaticData(ProjectionSelectionDTO projSelDTO) {
            String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
            String freq = StringUtils.EMPTY;
            if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                freq = "ANNUAL";
            } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                freq = Constant.SEMIANNUAL_CAPS;
            } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                freq = "QUARTERLY";
            } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                freq = "MONTHLY";
            }
            Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId()};
            List<Object[]> gtsList = CommonLogic.callProcedure(SalesUtils.PRC_PROJECTION_RESULTS, orderedArgs);
            NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
            List<SalesProjectionResultsDTO> projectionTotalList = logic.getCustomizedProjectionTotal(gtsList, projSelDTO);
            SPRStaticData.EXFACTORY_SALES.setStaticData(projectionTotalList.get(0));
            SPRStaticData.DEMAND_SALES.setStaticData(projectionTotalList.get(1));
            SPRStaticData.INVENTORY_WITHDRAW.setStaticData(projectionTotalList.get(2));
            SPRStaticData.SALES.setStaticData(projectionTotalList.get(3));
            SPRStaticData.UNIT.setStaticData(projectionTotalList.get(4));
            SPRStaticData.CONTRACT_SALES_PERCENT_EXFACT.setStaticData(projectionTotalList.get(5));
            SalesProjectionResultsDTO projectionTotal = new SalesProjectionResultsDTO();
            projectionTotal.setParent(0);
            projectionTotal.setLevelValue(SPRStaticData.PROJECTION_TOTAL.getLabel());
            SPRStaticData.PROJECTION_TOTAL.setStaticData(projectionTotal);
        }

        public String getFrequency() {
                return frequency;
        }

        public void setFrequency(String frequency) {
                this.frequency = frequency;
        }
    }
}