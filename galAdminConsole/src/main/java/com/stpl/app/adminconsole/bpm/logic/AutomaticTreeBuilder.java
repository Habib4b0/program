package com.stpl.app.adminconsole.bpm.logic;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Tree;
import org.jboss.logging.Logger;

public class AutomaticTreeBuilder {

    private static final Logger LOGGER = Logger.getLogger(AutomaticTreeBuilder.class);
    final private static RelationBuilderLogic logic = new RelationBuilderLogic();

    public static void build(List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList, Tree hierarchyTree, SessionDTO sessionDTO) {
        final int selectedHierarchyId = sessionDTO.getSelectedHierarchySessionId();
        final int hierarchyVersion = sessionDTO.getHierarchyVersion();
        try {
            final List<HistHierarchyLevelDefn> listOfLevels = logic.getHistHierarchyLevels(selectedHierarchyId, sessionDTO);
            if (hierarchyTree.rootItemIds().size() > 0) {
                detachTree(availableLevelsBeanList, hierarchyTree, listOfLevels);
            }
            hierarchyTree.removeAllItems();
            List<HierarchyLevelsDTO> parents = new ArrayList<HierarchyLevelsDTO>();
            if (listOfLevels != null && !listOfLevels.isEmpty()) {
                for (int i = 0; i < listOfLevels.size(); i++) {
                    HistHierarchyLevelDefn hierarchyLevelDef = listOfLevels.get(i);
                    if (!hierarchyLevelDef.getLevelValueReference().equals("User Defined")) {
                        availableLevelsBeanList.get(i).removeAllItems();
                    }
                    if (i == 0) {
                        List<HierarchyLevelsDTO> levelValues = availableLevelsBeanList.get(i).getItemIds();
                        for (int j = 0; j < levelValues.size(); j++) {
                            if (j == 0) {
                                addItem(hierarchyTree, levelValues.get(j), null);
                                parents.add(levelValues.get(j));
                            }
                        }
                        for (HierarchyLevelsDTO temp : parents) {
                            availableLevelsBeanList.get(i).removeItem(temp);
                        }
                    } else {
                        List<HierarchyLevelsDTO> tempParents = new ArrayList<HierarchyLevelsDTO>();
                        for (int j = 0; j < parents.size(); j++) {
                            HierarchyLevelsDTO parentDTO = parents.get(j);
                            int levelNo = Integer.valueOf(parentDTO.getLevelNo());
                            if (levelNo < availableLevelsBeanList.size()) {
                                List<String> levelValues = new ArrayList<String>();
                                String levelValue = parentDTO.getLevelValue();
                                levelValues.add("");
                                levelValues.add(levelValue);
                                Object childItemId = parentDTO;
                                for (int k = 1; k < levelNo; k++) {
                                    Object parentItemId = hierarchyTree.getParent(childItemId);
                                    HierarchyLevelsDTO parent;
                                    BeanItem<HierarchyLevelsDTO> tempItem = null;
                                    if (parentItemId instanceof HierarchyLevelsDTO) {
                                        tempItem = new BeanItem<HierarchyLevelsDTO>((HierarchyLevelsDTO) parentItemId);
                                    }
                                    parent = (HierarchyLevelsDTO) tempItem.getBean();
                                    levelValues.add(parent.getLevelValue());
                                    childItemId = parentItemId;
                                }
                              
                                List<HierarchyLevelsDTO> filteredValues = BpmLogic.getFilteredValues(selectedHierarchyId, hierarchyVersion, levelNo, levelValues);
                                if (filteredValues != null) {
                                    for (int k = 0; k < filteredValues.size(); k++) {
                                        addItem(hierarchyTree, filteredValues.get(k), parentDTO);
                                        if (i + 1 == listOfLevels.size()) {
                                            hierarchyTree.setChildrenAllowed(filteredValues.get(k), false);
                                        }
                                        tempParents.add(filteredValues.get(k));
                                    }
                                } else if (hierarchyLevelDef.getLevelValueReference().equals("User Defined")) {
                                    LOGGER.info("Current Index: " + i + " with type: " + hierarchyLevelDef.getLevelValueReference());
                                    List<HierarchyLevelsDTO> oldValues = availableLevelsBeanList.get(i).getItemIds();
                                    if (oldValues != null) {
                                        for (int k = 0; k < oldValues.size(); k++) {
                                            if (k == 0) {
                                                addItem(hierarchyTree, oldValues.get(k), parentDTO);
                                                if (i + 1 == listOfLevels.size()) {
                                                    hierarchyTree.setChildrenAllowed(oldValues.get(k), false);
                                                }
                                                tempParents.add(oldValues.get(k));
                                            }
                                        }
                                        for (HierarchyLevelsDTO temp : tempParents) {
                                            availableLevelsBeanList.get(i).removeItem(temp);
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        parents = tempParents;
                    }
                }
            }
           
            for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                hierarchyTree.expandItemsRecursively(it.next());
            }
        } catch (Exception e) {
            LOGGER.error(e);

        }

    }

    private static void detachTree(List<BeanItemContainer<HierarchyLevelsDTO>> availableLevelsBeanList, Tree hierarchyTree, List<HistHierarchyLevelDefn> listOfLevels) {
        List<HierarchyLevelsDTO> parents = new ArrayList<HierarchyLevelsDTO>();
        for (int i = 0; i < listOfLevels.size(); i++) {
            if (i == 0) {
                for (final Iterator<?> it = hierarchyTree.rootItemIds().iterator(); it.hasNext();) {
                    HierarchyLevelsDTO parent = (HierarchyLevelsDTO) it.next();
                    availableLevelsBeanList.get(0).addItem(parent);
                    parents.add(parent);
                }
            } else {
                List<HierarchyLevelsDTO> tempParents = new ArrayList<HierarchyLevelsDTO>();
                for (HierarchyLevelsDTO parent : parents) {
                    Collection<?> childs = hierarchyTree.getChildren(parent);
                    if (childs != null && !childs.isEmpty()) {
                        for (final Iterator<?> childIt = childs.iterator(); childIt.hasNext();) {
                            HierarchyLevelsDTO child = (HierarchyLevelsDTO) childIt.next();
                            if (listOfLevels.get(i).getLevelValueReference().equals("User Defined")) {
                                availableLevelsBeanList.get(i).addItem(child);
                            }
                            tempParents.add(child);
                        }
                    }
                }
                parents = tempParents;
            }
        }
    }

    private static void addItem(Tree hierarchyTree, HierarchyLevelsDTO currentDTO, HierarchyLevelsDTO parentDTO) {
        if (parentDTO == null) {
            currentDTO.setParentNode("0");
            currentDTO.setHierarchyNo(String.valueOf(hierarchyTree.rootItemIds().size() + 1));
        } else {
            currentDTO.setParentNode(parentDTO.getLevelNo() + "~" + parentDTO.getHiddenId());
            int size = hierarchyTree.getChildren(parentDTO) != null ? hierarchyTree.getChildren(parentDTO).size() : 0;
            size = size + 1;
            String hierarchy1 = parentDTO.getHierarchyNo();
            String hierrachyNo = hierarchy1 + "." + size;
            currentDTO.setHierarchyNo(hierrachyNo);
        }
        hierarchyTree.addItem(currentDTO);
        if (parentDTO != null) {
            hierarchyTree.setParent(currentDTO, parentDTO);
        }
        hierarchyTree.setItemCaption(currentDTO, currentDTO.getLevelValue());

    }

}
