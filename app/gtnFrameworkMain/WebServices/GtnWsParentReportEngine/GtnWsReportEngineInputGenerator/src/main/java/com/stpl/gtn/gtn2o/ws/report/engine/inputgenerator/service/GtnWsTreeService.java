package com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service;

import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNode;
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

    public GtnWsTreeNode buildTree(List<Object[]> resultList, final Map<String, Object[]> hierarchyNames) {

        GtnWsTreeNode rootNode = new GtnWsTreeNode();
        rootNode.setLevelNumber(0);
        rootNode.setHierarchyNo(StringUtils.EMPTY);
        int initialLevelNo = 0;
        int index = 0;
        for (Object[] resultValue : resultList) {
            GtnWsTreeNode treeNode = new GtnWsTreeNode();
//            treeNode.setHierarchyNo(resultValue[2].toString());
//            treeNode.setLevelValue(hierarchyNames.get(treeNode.getHierarchyNo()));
//            treeNode.setLevelName(resultValue[1].toString());
//            treeNode.setLevelNumber(Integer.valueOf(resultValue[0].toString()));
//            treeNode.setNodeIndex(index++);
//            initialLevelNo = initialLevelNo == 0 ? treeNode.getLevelNumber() : initialLevelNo;
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

    private void addChildrenRecursively(GtnWsTreeNode parentNode, GtnWsTreeNode treeNode) {
        if (treeNode.getLevelNumber() == parentNode.getLevelNumber() + 1) {
            if (treeNode.getHierarchyNo().startsWith(parentNode.getHierarchyNo())) {
                parentNode.addChildren(treeNode);
            }
        } else {
            for (GtnWsTreeNode childNode : parentNode.getChildren()) {
                addChildrenRecursively(childNode, treeNode);
            }
        }
    }

    public List<List<Object>> loadDataFromMasterFile(List<List<Object>> masterSource, List<List<Object>> resultset,
            GtnWsTreeNode rootNode) {
        loadMasterData(null, rootNode, masterSource, resultset);
        return resultset;
    }

    private void loadMasterData(Object object, GtnWsTreeNode rootNode, List<List<Object>> masterSource,
            List<List<Object>> resultSet) {

        for (int i = 0; i < masterSource.size(); i++) {
            for (GtnWsTreeNode children : rootNode.getChildren()) {
                addMasterData(masterSource.get(i), resultSet, children);
            }
        }
    }

    private void addMasterData(List<Object> masterSourceRow, List<List<Object>> resultSet, GtnWsTreeNode node) {

        try {

            if (node.getNodeIndex() == resultSet.size()) {
                resultSet.add(new ArrayList<>());
            }
            if (masterSourceRow.get(0).toString().startsWith(node.getHierarchyNo())) {
                resultSet.set(node.getNodeIndex(), new ArrayList<>(masterSourceRow));
            }

            if (node.getChildren() != null) {
                for (GtnWsTreeNode children : node.getChildren()) {
                    addMasterData(masterSourceRow, resultSet, children);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
    public GtnWsTreeNode buildCCPTree(GtnWsTreeNode customer, GtnWsTreeNode product, List<Object[]> ccpResult, List<Object[]> customViewInput, List<Object[]> deductionHierarchy) {
        GtnWsTreeNode rootNode = new GtnWsTreeNode();
        rootNode.setLevelNumber(0);
        rootNode.setHierarchyNo(StringUtils.EMPTY);
        int initialLevelNo = 0;
        int index = 0;
        for (Object[] objects : customViewInput) {
            List<GtnWsTreeNode> nextCCPChildNode = getNextCCPChildNode(rootNode, Integer.parseInt(objects[3].toString()));
            if (nextCCPChildNode != null) {
                for (GtnWsTreeNode gtnWsTreeNode : nextCCPChildNode) {
                    switch (objects[1].toString()) {
                        case "C":
                            addCCPJoinNode(customer, gtnWsTreeNode, objects, index, initialLevelNo, "C", ccpResult);
                            break;
                        case "P":
                            addCCPJoinNode(product, gtnWsTreeNode, objects, index, initialLevelNo, "P", ccpResult);
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

    private List<GtnWsTreeNode> getNextCCPChildNode(GtnWsTreeNode rootNode, int nextLevelNo) {
        List<GtnWsTreeNode> resultNode = new ArrayList<>();
        if (rootNode.getLevelNumber() == 0 && nextLevelNo == 1) {
            resultNode.add(rootNode);
        } else if (nextLevelNo == rootNode.getLevelNumber() + 1) {
            resultNode.add(rootNode);
        } else if (rootNode.getChildren() != null) {
            for (GtnWsTreeNode gtnWsTreeNode : rootNode.getChildren()) {
                resultNode.addAll(getNextCCPChildNode(gtnWsTreeNode, nextLevelNo));
            }

        }
        return resultNode;
    }

    private void addCCPJoinNode(GtnWsTreeNode treeNode, GtnWsTreeNode rootNode, Object[] objects, int index, int initialLevelNo, String indicator, List<Object[]> ccpResult) {
        int currentLevelNo = (int) objects[0];
        int customeViewLevelNo = (int) objects[3];
        if (treeNode.getLevelNumber() != 0 && currentLevelNo == treeNode.getLevelNumber()) {
//            addCCPChildrenRecursively(ccpResult, indicator, rootNode, index, initialLevelNo, customeViewLevelNo, treeNode, saveCcpId);
            addCCPChildrenRecc(ccpResult, indicator, rootNode, index, initialLevelNo, customeViewLevelNo, treeNode);
        } else {
            for (GtnWsTreeNode childNode : treeNode.getChildren()) {
                addCCPJoinNode(childNode, rootNode, objects, index, initialLevelNo, indicator, ccpResult);
            }
        }
    }

    private void addCCPDiscountJoinNode(GtnWsTreeNode rootNode, Object[] objects, int index, int initialLevelNo, List<Object[]> ccpResult, List<Object[]> deductionHierarchy) {
        int currentLevelNo = (int) objects[0];
        int customeViewLevelNo = (int) objects[3];
        Set<Integer> ccpId = rootNode.getCcpIds();
        int oldLevel = 0;
        int newLevel;
        GtnWsTreeNode deductionNode = null;
        List<GtnWsTreeNode> deductionTree = new ArrayList<>();
        for (Object[] ccpResults : ccpResult) {
            if ((ccpId == null || ccpId.contains(Integer.valueOf(ccpResults[0].toString()))) && ccpResults[3] != null) {
                int rsId = Integer.valueOf(ccpResults[3].toString());
                for (Object[] deductionHie : deductionHierarchy) {
                    int dedHierarchyId = Integer.parseInt(deductionHie[0].toString());
                    if (rsId == dedHierarchyId) {
                        newLevel = Integer.parseInt(deductionHie[currentLevelNo].toString());
                        if (oldLevel != newLevel) {
                            deductionNode = new GtnWsTreeNode();
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

        for (GtnWsTreeNode gtnWsTreeNode : deductionTree) {
            addCCPChildrenRecc(ccpResult, "D", rootNode, index, initialLevelNo, customeViewLevelNo, gtnWsTreeNode);
        }

    }

//    private void addCCPChildrenRecursively(List<Object[]> ccpResult, String indicator, GtnWsTreeNode rootNode,
//            int index, int initialLevelNo, int customeViewLevelNo, GtnWsTreeNode treeNode, boolean saveCcpId) {
//        String parentHierarchyNo = rootNode.getHierarchyNo();
//        String superParentHierarchyNo = rootNode.getParent() == null
//                ? StringUtils.EMPTY : rootNode.getParent().getHierarchyNo();
//        String superParentIndicator = rootNode.getParent() == null ? StringUtils.EMPTY : rootNode.getParent().getIndicator();
//        String parentIndicator = rootNode.getIndicator();
//        int ccpIndex = "C".equals(indicator) ? 1 : 2;
//        int parentCcpIndex = "C".equals(indicator) ? "C".equals(parentIndicator) ? 1 : 2 : "P".equals(parentIndicator) ? 2 : 1;
//        int superParentCcpIndex = "C".equals(superParentIndicator) ? 1 : 2;
//        for (Object[] objects : ccpResult) {
//            if ((StringUtils.EMPTY.equals(parentHierarchyNo) || objects[parentCcpIndex].toString().startsWith(parentHierarchyNo))
//                    && objects[ccpIndex].toString().startsWith(treeNode.getHierarchyNo())
//                    && (StringUtils.EMPTY.equals(superParentHierarchyNo)
//                    || (indicator.equals(superParentIndicator) && treeNode.getHierarchyNo().startsWith(superParentHierarchyNo))
//                    || (!indicator.equals(superParentIndicator) && objects[superParentCcpIndex].toString().startsWith(superParentHierarchyNo)))) {
//                GtnWsTreeNode newNode = new GtnWsTreeNode();
//                newNode.setHierarchyNo(treeNode.getHierarchyNo());
//                newNode.setLevelValue(treeNode.getLevelValue());
//                newNode.setLevelName(treeNode.getLevelName());
//                newNode.setNodeIndex(index++);
//                newNode.setIndicator(indicator);
//                initialLevelNo = initialLevelNo == 0 ? customeViewLevelNo : initialLevelNo;
//                newNode.setLevelNumber(initialLevelNo);
//                if (saveCcpId) {
//                    newNode.addCcpIds(Integer.parseInt(objects[0].toString()));
//                }
//                rootNode.addChildren(newNode);
//                rootNode.addCcpIds(Integer.parseInt(objects[0].toString()));
//            }
//        }
//    }
    private void addCCPChildrenRecc(List<Object[]> ccpResult, String indicator, GtnWsTreeNode rootNode,
            int index, int initialLevelNo, int customeViewLevelNo, GtnWsTreeNode treeNode) {
        int ccpIndex = "D".equals(indicator) ? 3 : "C".equals(indicator) ? 1 : 2;
        for (Object[] objects : ccpResult) {
            if (objects[ccpIndex] != null && (objects[ccpIndex].toString().startsWith(treeNode.getHierarchyNo())
                    || ("D".equals(indicator) && objects[ccpIndex] != null
                    && treeNode.getRsIds() != null && treeNode.getRsIds().contains(Integer.parseInt(objects[ccpIndex].toString())))) && parentCheck(rootNode, objects)) {
                GtnWsTreeNode newNode = new GtnWsTreeNode();
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

    private boolean parentCheck(GtnWsTreeNode rootNode, Object[] objects) {
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
                returnFlag = parentCheck(rootNode.getParent(), objects);
            }
        } else if (rootNode.getParent() == null && objects[ccpIndex].toString().startsWith(rootNode.getHierarchyNo())) {
            returnFlag = true;
        } else if (objects[ccpIndex].toString().startsWith(rootNode.getHierarchyNo())) {
            returnFlag = parentCheck(rootNode.getParent(), objects);
        }

        return returnFlag;
    }

}
