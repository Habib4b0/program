/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.projectionresults.logic.tablelogic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionresults.logic.MProjectionResultsLogic;
import com.stpl.app.gtnforecasting.projectionresults.logic.NMProjectionResultsLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.extfilteringtable.PageTreeLogicBase;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sibi
 */
public class ProjectionResultsTableLogic extends PageTreeTableLogic {

    protected ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private  NMProjectionResultsLogic nmProjectionResultsLogic = new NMProjectionResultsLogic();
    private  MProjectionResultsLogic mProjectionResultsLogic= new MProjectionResultsLogic();
    protected boolean firstGenerated = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectionResultsTableLogic.class);  
    

    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start= {}, and offset= {}" , firstGenerated, start, offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        if (firstGenerated && offset > 0) {
            try {
                List<String> indexList = new ArrayList<>();
                for (int i = 0; i < getNonFetchableData().size(); i++) {
                    indexList.add(getNonFetchableData().getIndex(i).getKey().toString());
                }
                projSelDTO.setNonFetchableIndex(indexList);
                List<ProjectionResultsDTO> list = loadDataByForecastName(getLastParent(), start, offset);
                int i = start;
                if(!list.isEmpty()){
                for (ProjectionResultsDTO dto : list) {
                    while (projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + i)) {
                        i++;
                    }
                    map.put(i, dto);
                    i++;
                }
                }
                projSelDTO.clearNonFetchableIndex();
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        LOGGER.debug("loadData ended= {} ",map.size());
        return map;
    }

    @Override
    public int getCount() {
        LOGGER.debug("getCount initiated with firstGenerated= {}" , firstGenerated);
        int count = 0;
        if (firstGenerated) {
            try {            
                count = getCountByForecastName(getLastParent());
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        LOGGER.debug("getCount ended with count= {}" , count);
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
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) throws PortalException {
        int count = getCountByForecastName(parentId);               
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
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            } else {
                 List<Leveldto> levelList =Collections.emptyList();
                if(projSelDTO.getLevelCount()!=0){
                levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), StringUtils.EMPTY, 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(),projSelDTO.getCustRelationshipBuilderSid(),projSelDTO.getProdRelationshipBuilderSid(), false, true , projSelDTO.getDiscountNoList(),projSelDTO);
                }
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    Leveldto levelDto = levelList.get(j);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(productHierarchyNo);
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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
    protected void recursivelyLoadExpandDataForNM(Object parentId, String treeLevel, int expandLevelNo) throws PortalException  {
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
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandDataForNM(dto, customTreeLevel, expandLevelNo);
            } else {
                 List<String> detailsList;
                  List<String> hierarchyNoList = Collections.emptyList();
                 String hierarchy;
                String hierarchyIndicator=StringUtils.EMPTY;
                 Map<String, List> relationshipLevelDetailsMap = Collections.emptyMap() ;
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
                    dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO)));
                     dto.setTreeLevelNo(levelNo);
                    dto.setGroup(detailsList.get(0));
                    dto.setLevelValue(detailsList.get(NumericConstants.THREE));
                    dto.setHierarchyNo(hierarchy);
                    dto.setHierarchyIndicator(hierarchyIndicator);
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(productHierarchyNo);
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(customerHierarchyNo);
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandDataForNM(dto, customTreeLevel, expandLevelNo);
                }
            }
        }
    }

    public void loadExpandData(int levelNo) throws PortalException {
          recursivelyLoadExpandDataForNM(new Object(), StringUtils.EMPTY, levelNo);   
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
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(BooleanConstant.getTrueFlag());
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
    private int getCountByForecastName(final Object parentId) throws PortalException {
        int count;
        String screenName = StringUtils.isBlank(projSelDTO.getScreenName()) ? StringUtils.EMPTY : projSelDTO.getScreenName();
       
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            count = getNmProjectionResultsLogic().getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
        } else {
            LOGGER.warn("BUSINESS_PROCESS_TYPE is Empty.Commercial is loaded by default.");
            count = getNmProjectionResultsLogic().getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
        }
        return count;
    }
    
    private List<ProjectionResultsDTO> loadDataByForecastName(final Object parentId, final int start, final int offset) throws PortalException {
        List<ProjectionResultsDTO> list;
        String screenName = StringUtils.isBlank(projSelDTO.getScreenName()) ? StringUtils.EMPTY : projSelDTO.getScreenName();
        LOGGER.debug("Screen Name is= {} ",screenName);
        
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            LOGGER.debug("Projection results load data method with start = {}, and offset= {}",start,offset);
            list = getNmProjectionResultsLogic().getConfiguredProjectionResults(parentId, start, offset, projSelDTO);
        } else {
             LOGGER.warn("BUSINESS_PROCESS_TYPE is Empty.Commercial is loaded by default.");
                list = getNmProjectionResultsLogic().getConfiguredProjectionResults(parentId, start, offset, projSelDTO);
        }
        return list;
    }

	public NMProjectionResultsLogic getNmProjectionResultsLogic() {
		return nmProjectionResultsLogic;
	}

	public void setNmProjectionResultsLogic(NMProjectionResultsLogic nmProjectionResultsLogic) {
		this.nmProjectionResultsLogic = nmProjectionResultsLogic;
	}

	public MProjectionResultsLogic getmProjectionResultsLogic() {
		return mProjectionResultsLogic;
	}

	public void setmProjectionResultsLogic(MProjectionResultsLogic mProjectionResultsLogic) {
		this.mProjectionResultsLogic = mProjectionResultsLogic;
	}
    
}
