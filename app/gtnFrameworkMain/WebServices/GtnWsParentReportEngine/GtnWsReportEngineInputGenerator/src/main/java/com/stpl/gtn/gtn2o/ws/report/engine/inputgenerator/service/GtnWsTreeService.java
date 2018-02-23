package com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service;

import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class GtnWsTreeService {

    private static GtnWsTreeService TREE_INSTANCE = null;

    private GtnWsTreeService() {
        super();
    }

    public static GtnWsTreeService getInstance() {
        if (TREE_INSTANCE == null) {
            TREE_INSTANCE = new GtnWsTreeService();
        }
        return TREE_INSTANCE;
    }

    public GtnWsReportEngineTreeNode buildTree(List<Object[]> resultList, final Map<String, Object[]> hierarchyNames) {
        GtnWsReportEngineTreeNode rootNode = new GtnWsReportEngineTreeNode();
        rootNode.setLevelNumber(0);
        rootNode.setHierarchyNo(StringUtils.EMPTY);
        int initialLevelNo = 0;
        int index = 0;
        for (Object[] resultValue : resultList) {
            GtnWsReportEngineTreeNode treeNode = new GtnWsReportEngineTreeNode();
            treeNode.setHierarchyNo(resultValue[2].toString());
            Object[] hierarchyDetails = hierarchyNames.get(treeNode.getHierarchyNo());
            if (hierarchyDetails != null) {
                treeNode.setHierarchyNo(hierarchyDetails[0].toString());
                treeNode.setLevelValue(hierarchyDetails[1].toString());
                treeNode.setLevelName(hierarchyDetails[2].toString());
                treeNode.setLevelNumber(Integer.valueOf(hierarchyDetails[3].toString()));
                treeNode.setNodeIndex(index++);
                initialLevelNo = initialLevelNo == 0 ? treeNode.getLevelNumber() : initialLevelNo;

                if (treeNode.getLevelNumber() == initialLevelNo) {
                    rootNode.addChildren(treeNode);
                } else {
                    addChildrenRecursively(rootNode, treeNode);
                }
            }
        }
        return rootNode;
    }

    private void addChildrenRecursively(GtnWsReportEngineTreeNode parentNode, GtnWsReportEngineTreeNode treeNode) {
        if (treeNode.getLevelNumber() == parentNode.getLevelNumber() + 1) {
            if (treeNode.getHierarchyNo().startsWith(parentNode.getHierarchyNo())) {
                parentNode.addChildren(treeNode);
            }
        } else {
            for (GtnWsReportEngineTreeNode childNode : parentNode.getChildren()) {
                addChildrenRecursively(childNode, treeNode);
            }
        }
    }

    /**
     *
     * customViewInput 0 - Level No 1 - Indicator 2 - Level Name 3 - Custom view
     * Level NO
     *
     */
    /**
     *
     * customViewInput 0 - Level No 1 - Indicator 2 - Level Name 3 - Custom view
     * Level NO
     *
     */
    public GtnWsReportEngineTreeNode formingTreeBasedOnInputs(GtnWsReportEngineTreeNode customer, GtnWsReportEngineTreeNode product, List<Object[]> ccpResult,
            List<Object[]> customViewInput, List<Object[]> deductionHierarchy) {
        GtnWsReportEngineTreeNode rootNode = new GtnWsReportEngineTreeNode();
        rootNode.setLevelNumber(0);
        rootNode.setHierarchyNo(StringUtils.EMPTY);
        int initialLevelNo = 0;
        int index = 0;
        for (Object[] objects : customViewInput) {
            List<GtnWsReportEngineTreeNode> nextCCPChildNode = getNextCCPChildNode(rootNode, Integer.parseInt(objects[3].toString()));
            if (nextCCPChildNode != null) {
                for (GtnWsReportEngineTreeNode gtnWsTreeNode : nextCCPChildNode) {
                    switch (objects[1].toString()) {
                        case "C":
                            addingInputNodeToParentNodeRecursive(customer, gtnWsTreeNode, objects, index, initialLevelNo, "C", ccpResult);
                            break;
                        case "P":
                            addingInputNodeToParentNodeRecursive(product, gtnWsTreeNode, objects, index, initialLevelNo, "P", ccpResult);
                            break;
                        case "D":
                            addCCPDiscountJoinNode(gtnWsTreeNode, objects, index, initialLevelNo, ccpResult, deductionHierarchy);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return rootNode;
    }

    private List<GtnWsReportEngineTreeNode> getNextCCPChildNode(GtnWsReportEngineTreeNode rootNode, int nextLevelNo) {
        List<GtnWsReportEngineTreeNode> resultNode = new ArrayList<>();
        if (rootNode.getLevelNumber() == 0 && nextLevelNo == 1) {
            resultNode.add(rootNode);
        } else if (nextLevelNo == rootNode.getLevelNumber() + 1) {
            resultNode.add(rootNode);
        } else if (rootNode.getChildren() != null) {
            for (GtnWsReportEngineTreeNode gtnWsTreeNode : rootNode.getChildren()) {
                resultNode.addAll(getNextCCPChildNode(gtnWsTreeNode, nextLevelNo));
            }
        }
        return resultNode;
    }

    private void addingInputNodeToParentNodeRecursive(GtnWsReportEngineTreeNode treeNode, GtnWsReportEngineTreeNode rootNode, Object[] objects, int index, int initialLevelNo, String indicator, List<Object[]> ccpResult) {
        int currentLevelNo = (int) objects[0];
        int customeViewLevelNo = (int) objects[3];
        if (treeNode.getLevelNumber() != 0 && currentLevelNo == treeNode.getLevelNumber()) {
            addingChildNodeToParentNodeRecursive(ccpResult, indicator, rootNode, index, initialLevelNo, customeViewLevelNo, treeNode);
        } else {
            for (GtnWsReportEngineTreeNode childNode : treeNode.getChildren()) {
                addingInputNodeToParentNodeRecursive(childNode, rootNode, objects, index, initialLevelNo, indicator, ccpResult);
            }
        }
    }

    private void addCCPDiscountJoinNode(GtnWsReportEngineTreeNode rootNode, Object[] objects, int index, int initialLevelNo, List<Object[]> ccpResult, List<Object[]> deductionHierarchy) {
        int currentLevelNo = (int) objects[0];
        int customeViewLevelNo = (int) objects[3];
        Set<Integer> ccpId = rootNode.getCcpIds();
        int oldLevel = 0;
        int newLevel;
        GtnWsReportEngineTreeNode deductionNode = null;
        List<GtnWsReportEngineTreeNode> deductionTree = new ArrayList<>();
        for (Object[] ccpResults : ccpResult) {
            if ((ccpId == null || ccpId.contains(Integer.valueOf(ccpResults[0].toString()))) && ccpResults[3] != null) {
                int rsId = Integer.valueOf(ccpResults[3].toString());
                for (Object[] deductionHie : deductionHierarchy) {
                    int dedHierarchyId = Integer.parseInt(deductionHie[0].toString());
                    if (rsId == dedHierarchyId) {
                        newLevel = Integer.parseInt(deductionHie[currentLevelNo].toString());
                        if (oldLevel != newLevel) {
                            deductionNode = new GtnWsReportEngineTreeNode();
                            deductionNode.setHierarchyNo(newLevel + "");
                            deductionNode.setLevelValue("");
                            deductionNode.setLevelName("");
                            deductionNode.setNodeIndex(index++);
                            deductionNode.setIndicator("D");
                            initialLevelNo = initialLevelNo == 0 ? customeViewLevelNo : initialLevelNo;
                            deductionNode.setLevelNumber(initialLevelNo);
                            deductionNode.addRsIds(rsId);
                            deductionTree.add(deductionNode);
                        } else {
                            deductionNode.addRsIds(rsId);
                        }
                        oldLevel = newLevel;
                    }
                }
            }
        }

        for (GtnWsReportEngineTreeNode gtnWsTreeNode : deductionTree) {
            addingChildNodeToParentNodeRecursive(ccpResult, "D", rootNode, index, initialLevelNo, customeViewLevelNo, gtnWsTreeNode);
        }

    }

    private void addingChildNodeToParentNodeRecursive(List<Object[]> ccpResult, String indicator, GtnWsReportEngineTreeNode rootNode,
            int index, int initialLevelNo, int customeViewLevelNo, GtnWsReportEngineTreeNode treeNode) {
        int ccpIndex = "D".equals(indicator) ? 3 : "C".equals(indicator) ? 1 : 2;
        for (Object[] objects : ccpResult) {
            if (objects[ccpIndex] != null && (objects[ccpIndex].toString().startsWith(treeNode.getHierarchyNo())
                    || ("D".equals(indicator) && objects[ccpIndex] != null
                    && treeNode.getRsIds() != null && treeNode.getRsIds().contains(Integer.parseInt(objects[ccpIndex].toString())))) && checkingParentNodeRecursively(rootNode, objects)) {
                GtnWsReportEngineTreeNode newNode = new GtnWsReportEngineTreeNode();
                newNode.setHierarchyNo(treeNode.getHierarchyNo());
                newNode.setLevelValue(treeNode.getLevelValue());
                newNode.setLevelName(treeNode.getLevelName());
                newNode.setNodeIndex(index++);
                newNode.setIndicator(indicator);
                initialLevelNo = initialLevelNo == 0 ? customeViewLevelNo : initialLevelNo;
                newNode.setLevelNumber(initialLevelNo);
                newNode.addCcpIds(Integer.parseInt(objects[0].toString()));
                if (objects[3] != null) {
                    newNode.addRsIds(Integer.parseInt(objects[3].toString()));
                }
                rootNode.addChildren(newNode);
                if (objects[3] != null) {
                    rootNode.addRsIds(Integer.parseInt(objects[3].toString()));
                }
                rootNode.addCcpIds(Integer.parseInt(objects[0].toString()));
            }

        }
    }

    private boolean checkingParentNodeRecursively(GtnWsReportEngineTreeNode rootNode, Object[] objects) {
        boolean returnFlag = false;
        int ccpIndex = "D".equals(rootNode.getIndicator()) ? 3 : "C".equals(rootNode.getIndicator()) ? 1 : 2;
        if ("D".equals(rootNode.getIndicator())) {
            if (rootNode.getParent() == null && objects[ccpIndex] != null
                    && rootNode.getRsIds() != null && rootNode.getRsIds().contains(Integer.parseInt(objects[ccpIndex].toString()))
                    && rootNode.getCcpIds() != null && rootNode.getCcpIds().contains(Integer.parseInt(objects[0].toString()))) {
                returnFlag = true;
            } else if (objects[ccpIndex] != null
                    && rootNode.getRsIds() != null && rootNode.getRsIds().contains(Integer.parseInt(objects[ccpIndex].toString()))
                    && rootNode.getCcpIds() != null && rootNode.getCcpIds().contains(Integer.parseInt(objects[0].toString()))) {
                returnFlag = checkingParentNodeRecursively(rootNode.getParent(), objects);
            }
        } else if (rootNode.getParent() == null && objects[ccpIndex].toString().startsWith(rootNode.getHierarchyNo())) {
            returnFlag = true;
        } else if (objects[ccpIndex].toString().startsWith(rootNode.getHierarchyNo())) {
            returnFlag = checkingParentNodeRecursively(rootNode.getParent(), objects);
        }

        return returnFlag;
    }

}
