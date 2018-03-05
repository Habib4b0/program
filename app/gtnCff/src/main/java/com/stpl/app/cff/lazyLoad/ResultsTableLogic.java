/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.ui.projectionresults.logic.ProjectionResultsLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeLogicBase;  
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author porchelvi.g
 */
public class ResultsTableLogic extends PageTreeTableLogic {

    private static final BooleanConstant CONSTANT = new BooleanConstant();
    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final ProjectionResultsLogic projectionResultsLogic = new ProjectionResultsLogic();
    private boolean firstGenerated = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultsTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {} and start= {} and offset= {}", firstGenerated, start, offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            try {
                List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
                projSelDTO.setNonFetchableIndex(indexList);
                List<ProjectionResultsDTO> list = loadDataByForecastName(getLastParent(), start, offset);
                int i = start;
                for (ProjectionResultsDTO dto : list) {                    
                    while (projSelDTO.hasNonFetchableIndex("" + i)) {
                        i++;
                    }
                    map.put(i, dto);
                    i++;
                }
                projSelDTO.clearNonFetchableIndex();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        LOGGER.debug("loadData ended= {} ", map.size());
        return map;
    }

    @Override
    public int getCount() {
        LOGGER.debug("getCount initiated with firstGenerated= {}", firstGenerated);
        int count = 0;
        if (firstGenerated) {
            try {
                count = getCountByForecastName(getLastParent());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        LOGGER.debug("getCount ended with count= {}", count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        ProjectionResultsDTO dto = (ProjectionResultsDTO) object;
        ((ExtTreeContainer<ProjectionResultsDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<ProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<ProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue("group");
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
                projSelDTO.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo)  {
        int count = getCountByForecastName(parentId);
        CommonLogic commonLogic = new CommonLogic();
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo = projSelDTO.getProductHierarchyNo();
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
        String hierarchyNo = projSelDTO.getHierarchyNo();
        int levelNo = projSelDTO.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
            if (projSelDTO.isGroupCount()) {
                String customTreeLevel = treeLevel + (count) + ".";
                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                dto.setLevelNo(projSelDTO.getLevelNo());
                dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                dto.setParentNode(projSelDTO.getParentNode());
                dto.setGroup(projSelDTO.getGroupFilter());
                dto.setLevelValue(projSelDTO.getLevelValue());
                dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                dto.setHierarchyNo(hierarchyNo);
                if (dto.getHierarchyIndicator().equals("C")) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals("P")) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            } else {
                List<String> detailsList;
                  List<String> hierarchyNoList = Collections.EMPTY_LIST;
                 String hierarchy;
                String hierarchyIndicator=StringUtils.EMPTY;
                 Map<String, List> relationshipLevelDetailsMap =Collections.EMPTY_MAP ;
                if(projSelDTO.getLevelCount()!=0){
                    if (projSelDTO.isIsCustomHierarchy()) {
    
                 hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
              relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO,  0, projSelDTO.getLevelCount());     
    
            } else {
               relationshipLevelDetailsMap =  projSelDTO.getSessionDTO().getHierarchyLevelDetails();
               hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO,  0, projSelDTO.getLevelCount());
    
                    hierarchyIndicator=projSelDTO.getHierarchyIndicator();
            }
                }
                int size = hierarchyNoList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    hierarchy=hierarchyNoList.get(j);
                    detailsList= relationshipLevelDetailsMap.get(hierarchy);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
                     dto.setTreeLevelNo(levelNo);
                    dto.setGroup(detailsList.get(0).toString());
                    dto.setLevelValue(detailsList.get(NumericConstants.THREE).toString());
                    dto.setHierarchyNo(hierarchy);
                    dto.setHierarchyIndicator(hierarchyIndicator);
                    if (dto.getHierarchyIndicator().equals("C")) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(productHierarchyNo);
                    } else if (dto.getHierarchyIndicator().equals("P")) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(customerHierarchyNo);
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                }
            }
        }
    }
    
    public void loadExpandData(int levelNo)  {
        recursivelyLoadExpandData(new Object(), "", levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(CONSTANT.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(CONSTANT.getTrueFlag());
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

    /**
     *
     * @param parentId
     * @return
     */
    private int getCountByForecastName(final Object parentId) { 
        int count;
        count = projectionResultsLogic.getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
        LOGGER.debug("===count==========================>>>>> {}", count);
        return count;
    }

    private List<ProjectionResultsDTO> loadDataByForecastName(final Object parentId, final int start, final int offset)  {
        List<ProjectionResultsDTO> list;
        String screenName = StringUtils.isBlank(projSelDTO.getScreenName()) ? StringUtils.EMPTY : projSelDTO.getScreenName();
        LOGGER.debug("Screen Name= {}", screenName);
        LOGGER.debug("Projection results load data method with start = {} and offset = {}", start, offset);
        list = projectionResultsLogic.getConfiguredProjectionResults(parentId, start, offset, projSelDTO);
        return list;
    }
    
}
