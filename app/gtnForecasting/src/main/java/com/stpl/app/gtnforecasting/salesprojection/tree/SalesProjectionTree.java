/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.tree;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.tree.node.SalesBaseNode;
import com.stpl.app.gtnforecasting.salesprojection.tree.node.SalesProjectionNodeCP;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class SalesProjectionTree {

    /**
     * The Constant LOGGER.
     */
    public static final Logger logger = LoggerFactory.getLogger(SalesProjectionTree.class);

    private SalesBaseNode apex;

    public SalesBaseNode getApex() {
        return this.apex;
    }

    public SalesProjectionTree() {
        logger.debug("Inside Constructor");
    }

    public void buildtree(ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getTreeLevelNo() != 0) {
            if (projSelDTO.isIsCustomHierarchy()) {
                buildCustomTree(projSelDTO);
            } else {
                buildForCP(projSelDTO);
            }
        }
    }

    private void buildCustomTree(ProjectionSelectionDTO projSelDTO) {
        List<String> customViewList = getAvailableHierarchiesCustom(projSelDTO);
        SalesBaseNode customApex = generateCPTree(customViewList);
        sortTree(customApex.getAllChildHierarchies(), projSelDTO);
        setCurrentApex(customApex);
    }

    private void buildForCP(ProjectionSelectionDTO projSelDTO) {
        List<String> customViewList = getAvailableHierarchiesCP(projSelDTO);
        SalesBaseNode customApex = null;
        if (projSelDTO.isLevelFilter()) {
            customApex = generateCPForLevelFilter(UiUtils.parseStringToInteger(projSelDTO.getLevelFilterValue()), customViewList);
        } else {
            customApex = generateCPTree(customViewList);
        }
        sortTree(customApex.getAllChildHierarchies(), projSelDTO);
        setCurrentApex(customApex);
    }

    private List<String> getAvailableHierarchiesCP(ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getSessionDTO().getHierarchyLevelDetails().isEmpty()) {
            return Collections.emptyList();
        } else {
            CommonLogic cmLogic = new CommonLogic();
            String sql = cmLogic.insertAvailableHierarchyNoForExpand(projSelDTO);
            if (projSelDTO.isLevelFilter()) {
            sql += SQlUtil.getQuery("custom-view-sales-condition-query");
            }else{
             sql += " Select  distinct HIERARCHY_NO from #SELECTED_HIERARCHY_NO order by HIERARCHY_NO";
            }
            sql = sql.replace(Constant.RELJOIN, CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO()));
            return (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames()));
        }
    }

    private List<String> getAvailableHierarchiesCustom(ProjectionSelectionDTO projSelDTO) {
        String query = SQlUtil.getQuery("custom-relationship-hierarchy");
        query = query.replace("@CUSTMASTERSID",String.valueOf(projSelDTO.getSessionDTO().getCustomRelationShipSid()));
       
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            query = query.replace("[?FILTERCCP]"," AND SPM.FILTER_CCP=1");
        }else{
             query = query.replace("[?FILTERCCP]",StringUtils.EMPTY);
        }
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));

    }


    private void setCurrentApex(SalesBaseNode apex) {
        this.apex = apex;
    }

    private SalesBaseNode generateCPTree(List<String> availableHierarachies) {
        SalesProjectionNodeCP salesProjNodeCp = new SalesProjectionNodeCP("");
        HashMap dataMap = new HashMap<>(availableHierarachies.size());

        if (!availableHierarachies.isEmpty()) {
            int startLevel = StringUtils.countMatches(String.valueOf(availableHierarachies.get(0)), ".");
            salesProjNodeCp.setApex(true);
            List<String> child = new ArrayList<>();
            int currentLevel = startLevel;
            SalesProjectionNodeCP parent = salesProjNodeCp;
            for (Object availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                String hierarchyNo = hierarchy.contains(",") ? hierarchy.split(",")[0] : hierarchy;
                int level = StringUtils.countMatches(hierarchyNo.trim(), ".");
                if (level == currentLevel) {
                    child.add(hierarchy);
                } else {
                    if (currentLevel == startLevel) {
                        parent = addChildrenToParent(salesProjNodeCp, child, currentLevel, dataMap);
                    } else {
                        parent = addChildrenToParent(parent, child, currentLevel, dataMap);
                    }
                    if (level < currentLevel) {
                        parent = (SalesProjectionNodeCP) getParent(hierarchy, dataMap, currentLevel, startLevel, salesProjNodeCp);
                    }
                    child = new ArrayList<>();
                    child.add(hierarchy);
                    currentLevel = level;
                }
            }
            addChildrenToParent(parent, child, currentLevel, dataMap);
        }
        return salesProjNodeCp;
    }

    private void sortTree(List<TreeNode> treeNodeList, final ProjectionSelectionDTO session) {
        Map<String, List> hierarchyDetailsMap = session.isIsCustomHierarchy() ? session.getSessionDTO().getSalesHierarchyLevelDetails() : session.getSessionDTO().getHierarchyLevelDetails();
        if (treeNodeList != null) {
            Collections.sort(treeNodeList, new Comparator<TreeNode>() {

                @Override
                public int compare(TreeNode o1, TreeNode o2) {
                    String hierarchyNo = o1.getHierachyNo().contains(",") ? o1.getHierachyNo().split(",")[0] : o1.getHierachyNo();
                    String hierarchyNo1 = o2.getHierachyNo().contains(",") ? o2.getHierachyNo().split(",")[0] : o2.getHierachyNo();
                    return String
                            .valueOf(hierarchyDetailsMap.get(hierarchyNo.trim()).get(0))
                            .compareToIgnoreCase((String) hierarchyDetailsMap
                                    .get(hierarchyNo1.trim()).get(0));
                }
            });
            for (TreeNode treeNode : treeNodeList) {
                treeNode.generateHierarchy();
                treeNode.getHierachyNo();
                sortTree(treeNode.getAllChildHierarchies(), session);
            }
        }
    }
    private SalesBaseNode generateCPForLevelFilter(int levelFiltered, List<String> availableHierarachies) {
        SalesProjectionNodeCP salesCpForLevelFilter = new SalesProjectionNodeCP("");
        if (!availableHierarachies.isEmpty()) {
            salesCpForLevelFilter.setApex(true);
            Map<String, String> childMap = new HashMap<>();
            for (String availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                String hierarchyNo = hierarchy.contains(",") ? hierarchy.split(",")[0] : hierarchy;
                String lastIndexRemoved = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                String masterId = lastIndexRemoved.lastIndexOf('.') == -1 ? StringUtils.EMPTY : lastIndexRemoved.substring(lastIndexRemoved.lastIndexOf('.'), lastIndexRemoved.length());
                int level = StringUtils.countMatches(hierarchyNo.trim(), ".");
                if (levelFiltered == level && !childMap.containsKey(masterId)) {
                    childMap.put(masterId, hierarchy);
                }
            }
            addChildrenToParent(salesCpForLevelFilter, new ArrayList<>(childMap.values()), levelFiltered, new HashMap());
        }
        return salesCpForLevelFilter;
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

    private SalesProjectionNodeCP addChildrenToParent(SalesProjectionNodeCP parent, List<String> child, int level, Map dataMap) {
        if (!child.isEmpty()) {
            for (int i = 0; i < child.size() - 1; i++) {
                SalesProjectionNodeCP node = new SalesProjectionNodeCP(child.get(i));
                node.setLevel(level);
                parent.addChild(node);
                dataMap.put(child.get(i), node);
            }
            SalesProjectionNodeCP lastChild = new SalesProjectionNodeCP(child.get(child.size() - 1));
            lastChild.setLevel(level);
            parent.addChild(lastChild);
            dataMap.put(child.get(child.size() - 1), lastChild);
            return lastChild;
        }
        return parent;
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
}
