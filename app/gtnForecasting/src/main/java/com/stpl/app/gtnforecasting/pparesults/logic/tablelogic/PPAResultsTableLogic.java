/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.gtnforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.extfilteringtable.PageTreeLogicBase;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author srithar
 */
public class PPAResultsTableLogic extends PageTreeTableLogic {

    protected ProjectionSelectionDTO selection = new ProjectionSelectionDTO();
    protected PPAProjectionResultsLogic logic = new PPAProjectionResultsLogic();
    protected SessionDTO session;
    protected boolean firstGenerated = false;
    protected int totalCount = 0;
    protected List totalList = new ArrayList();

    public PPAProjectionResultsLogic getLogic() {
        return logic;
    }

    public void setLogic(PPAProjectionResultsLogic logic) {
        this.logic = logic;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public ProjectionSelectionDTO getSelection() {
        return selection;
    }

    public void setSelection(ProjectionSelectionDTO selection) {
        this.selection = selection;
    }
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PPAResultsTableLogic.class);

    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug(start + "Inside load data" + offset);
        GtnSmallHashMap finalMap = new GtnSmallHashMap();
        List<PPAProjectionResultsDTO> list = new ArrayList<>();
        if (firstGenerated && offset > 0) {
            int maybeadded = 0;
            if (!(getLastParent() instanceof PPAProjectionResultsDTO) && !selection.isIsFilter()) {
                selection.setIsProjectionTotal(Boolean.TRUE);
                list = logic.getPPAProjectionResultsTotal(start, offset, selection, session);

            }
            maybeadded = list.size();
            if (offset > maybeadded) {
                List<String> indexList = new ArrayList<>();
                for (int i = 0; i < getNonFetchableData().size(); i++) {
                    indexList.add(getNonFetchableData().getIndex(i).getKey().toString());
                }
                selection.setNonFetchableIndex(indexList);
                list.addAll(logic.getConfiguredPPAProjectionResults1(getLastParent(), start, offset - maybeadded, selection, session));
            }
        }
        int i = start;
        for (PPAProjectionResultsDTO dto : list) {
            finalMap.put(i, dto);
            i++;
        }
        selection.clearNonFetchableIndex();
        return finalMap;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = logic.getConfiguredPPAProjectionResultsCount(getLastParent(), selection, Boolean.TRUE);
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        ((ExtTreeContainer<PPAProjectionResultsDTO>) datasource).addBean((PPAProjectionResultsDTO) object);

        PPAProjectionResultsDTO dto = (PPAProjectionResultsDTO) object;
        if (dto.getLevelNo() == 0) {
            ((ExtTreeContainer<PPAProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }

        return object;
    }

    
    public void setProjectionResultsData(ProjectionSelectionDTO selection) {

        this.selection = selection;
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
        session.setIsPPAUpdated(true);
    }

    public boolean isFirstGenerated() {
        return firstGenerated;
    }

    public void setFirstGenerated(boolean firstGenerated) {
        this.firstGenerated = firstGenerated;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(Boolean.TRUE);
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

    protected void recursivelyLoadExpandData(ProjectionSelectionDTO selection,Object parentId, String treeLevel, int expandLevelNo) {
        try {

        int count = logic.getConfiguredPPAProjectionResultsCount(parentId, selection, true);
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo = selection.getProductHierarchyNo();
        String customerHierarchyNo = selection.getCustomerHierarchyNo();
        String hierarchyNo = selection.getHierarchyNo();
        int levelNo = selection.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
            if (selection.isGroupCount()) {
                String customTreeLevel = treeLevel + (count) + ".";
                PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
                dto.setLevelNo(selection.getLevelNo());
                dto.setTreeLevelNo(selection.getTreeLevelNo());
                dto.setGroup(selection.getGroupFilter());
                dto.setHirarechyIndicater(selection.getHierarchyIndicator());
                dto.setHirarechyNo(hierarchyNo);
                if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHirarechyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHirarechyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }

                addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(selection,dto, customTreeLevel, expandLevelNo);
            } else {
                    CommonLogic commonLogic = new CommonLogic();
                    Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
                    if (selection.isIsCustomHierarchy()) {                        
                        String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(selection);                        
                        List<String> levelList = commonLogic.getHiearchyNoForCustomView(selection, 0, selection.getLevelCount());                        
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = treeLevel + (index + j) + ".";
                            PPAProjectionResultsDTO dto = logic.configureDetailsInDTO(selection, levelList.get(j), hierarchyIndicator, levelNo, relationshipLevelDetailsMap.get(levelList.get(j)));
                            addExpandedTreeList(customTreeLevel, dto);
                            recursivelyLoadExpandData(selection, dto, customTreeLevel, expandLevelNo);
                    }

                    } else {                        
                        List<String> levelList = commonLogic.getHiearchyNoAsList(selection, 0, selection.getLevelCount());
                        int size = levelList.size();
                        int index = count - size + 1;
                        for (int j = 0; j < size; j++) {
                            String customTreeLevel = treeLevel + (index + j) + ".";
                            PPAProjectionResultsDTO dto = logic.configureDetailsInDTO(selection, levelList.get(j), selection.getHierarchyIndicator(), Integer.valueOf(relationshipLevelDetailsMap.get(levelList.get(j)).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(levelList.get(j)));
                    addExpandedTreeList(customTreeLevel, dto);
                            recursivelyLoadExpandData(selection,dto, customTreeLevel, expandLevelNo);                            
                }
            }
        }
    }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void loadExpandData(int levelNo) {
        recursivelyLoadExpandData(selection,new Object(), StringUtils.EMPTY, levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        if (groupDdlb != null) {
            if (selection.isIsCustomHierarchy() || !selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                selection.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }
}
