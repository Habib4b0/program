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
import com.stpl.app.gtnforecasting.salesprojection.tree.node.SalesProjectionNodeCustom;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
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
        List<Object[]> customViewList = getAvailableHierarchiesCustom(projSelDTO);
        SalesBaseNode customApex = generateCustomTree(customViewList);
        sortTree(customApex.getAllChildHierarchies(), projSelDTO);
        setCurrentApex(customApex);
    }

    private void buildForCP(ProjectionSelectionDTO projSelDTO) {
        List<Object[]> customViewList = getAvailableHierarchiesCP(projSelDTO);
        SalesBaseNode customApex = null;
        if (projSelDTO.isLevelFilter()) {
            customApex = generateCPForLevelFilter(UiUtils.parseStringToInteger(projSelDTO.getLevelFilterValue()), customViewList);
        } else {
            customApex = generateCPTree(customViewList);
        }
    	sortTree(customApex.getAllChildHierarchies(), projSelDTO);
        setCurrentApex(customApex);
    }

    private List<Object[]> getAvailableHierarchiesCP(ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getSessionDTO().getHierarchyLevelDetails().isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            CommonLogic cmLogic = new CommonLogic();
            String sql = cmLogic.insertAvailableHierarchyNoForExpand(projSelDTO);
            if (projSelDTO.isLevelFilter()) {
            sql += SQlUtil.getQuery("custom-view-sales-condition-query");
            sql = sql.replace("@RELJOIN", " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO = A.HIERARCHY_NO ");
            }else{
             sql += " Select  distinct HIERARCHY_NO from #SELECTED_HIERARCHY_NO order by HIERARCHY_NO";
            }
            return (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames()));
        }
    }

    private List<Object[]> getAvailableHierarchiesCustom(ProjectionSelectionDTO projSelDTO) {
        String query = SQlUtil.getQuery("custom-relationship-hierarchy");
        query = query.replace("[?CUST_RELATIONSHIP_BUILDER_SID]", projSelDTO.getSessionDTO().getCustRelationshipBuilderSid());
        query = query.replace("[?PROD_RELATIONSHIP_BUILDER_SID]", projSelDTO.getSessionDTO().getProdRelationshipBuilderSid());
        query = query.replace("[?Custom_View_Master_SID]", String.valueOf(projSelDTO.getCustomId()));
        query = query.replace("[?UserGroup]","");
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            query = query.replace("[?FILTERCCP]"," AND PPA.FILTER_CCP=1");
        }else{
             query = query.replace("[?FILTERCCP]",StringUtils.EMPTY);
        }
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));

    }

    private SalesBaseNode generateCustomTree(List<Object[]> hierarchyList) {
        SalesProjectionNodeCustom apexNode = new SalesProjectionNodeCustom("");
        apexNode.setApex(true);
        HashMap buildMap = new HashMap<>(hierarchyList.size());
        for (Object[] object : hierarchyList) {
            String hiearachy = (String.valueOf(object[0])).trim();
            SalesProjectionNodeCustom salesNode = new SalesProjectionNodeCustom(hiearachy);
            String parentHierarchy = (String.valueOf(object[4])).trim();
            if (parentHierarchy.equals("null")) {
                apexNode.addChild(salesNode);
                salesNode.setHierarchyIndicator(String.valueOf(object[3]));
                salesNode.setLevel(Integer.parseInt(String.valueOf(object[2])));
                buildMap.put(hiearachy, salesNode);
            } else {
                SalesProjectionNodeCustom parent = (SalesProjectionNodeCustom) buildMap.get(parentHierarchy);
                salesNode.addParentNode(parent);
                parent.addChild(salesNode);
                salesNode.setHierarchyIndicator(String .valueOf(object[3]));
                salesNode.setLevel(Integer.parseInt(String.valueOf(object[2])));
                buildMap.put(parentHierarchy+"~ "+hiearachy, salesNode);
            }
        }
        return apexNode;
    }

    private void setCurrentApex(SalesBaseNode apex) {
        this.apex = apex;
    }

    private SalesBaseNode generateCPTree(List<Object[]> availableHierarachies) {
        SalesProjectionNodeCP apex = new SalesProjectionNodeCP("");
        HashMap dataMap = new HashMap<>(availableHierarachies.size());

        if (!availableHierarachies.isEmpty()) {
            int startLevel = StringUtils.countMatches(String.valueOf(availableHierarachies.get(0)), ".");
            apex.setApex(true);
            List<String> child = new ArrayList<>();
            int currentLevel = startLevel;
            SalesProjectionNodeCP parent = apex;
            for (Object availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                String hierarchyNo = hierarchy.contains(",") ? hierarchy.split(",")[0] : hierarchy;
                int level = StringUtils.countMatches(hierarchyNo.trim(), ".");
                if (level == currentLevel) {
                    child.add(hierarchy);
                } else {
                    if (currentLevel == startLevel) {
                        parent = addChildrenToParent(apex, child, currentLevel, dataMap);
                    } else {
                        parent = addChildrenToParent(parent, child, currentLevel, dataMap);
                    }
                    if (level < currentLevel) {
                        parent = (SalesProjectionNodeCP) getParent(hierarchy, dataMap, currentLevel, startLevel, apex);
                    }
                    child = new ArrayList<>();
                    child.add(hierarchy);
                    currentLevel = level;
                }
            }
            addChildrenToParent(parent, child, currentLevel, dataMap);
        }
        return apex;
    }

	private void sortTree(List<TreeNode> treeNodeList, final ProjectionSelectionDTO session) {
		if (treeNodeList != null) {
			Collections.sort(treeNodeList, new Comparator<TreeNode>() {

                                @Override
				public int compare(TreeNode o1, TreeNode o2) {
                    String hierarchyNo = o1.getHierachyNo().contains(",") ? o1.getHierachyNo().split(",")[0] : o1.getHierachyNo();
                    String hierarchyNo1 = o2.getHierachyNo().contains(",") ? o2.getHierachyNo().split(",")[0] : o2.getHierachyNo();
					return String
                            .valueOf(session.getSessionDTO().getHierarchyLevelDetails().get(hierarchyNo.trim()).get(0))
							.compareToIgnoreCase((String) session.getSessionDTO().getHierarchyLevelDetails()
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
    private SalesBaseNode generateCPForLevelFilter(int levelFiltered, List<Object[]> availableHierarachies) {
        SalesProjectionNodeCP apex = new SalesProjectionNodeCP("");
        if (!availableHierarachies.isEmpty()) {
            apex.setApex(true);
            List<String> child = new ArrayList<>();
            for (Object availableHierarachy : availableHierarachies) {
                String hierarchy = String.valueOf(availableHierarachy);
                String hierarchyNo = hierarchy.contains(",") ? hierarchy.split(",")[0] : hierarchy;
                int level = StringUtils.countMatches(hierarchyNo.trim(), ".");
                if (levelFiltered == level) {
                    child.add(hierarchy);
                }
            }
            addChildrenToParent(apex, child, levelFiltered, new HashMap());
        }
        return apex;
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