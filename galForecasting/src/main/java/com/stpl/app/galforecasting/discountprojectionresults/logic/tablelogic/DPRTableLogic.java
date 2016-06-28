package com.stpl.app.galforecasting.discountprojectionresults.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.MMDPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.NMDPRLogic;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class DPRTableLogic extends PageTreeTableLogic {

    private static final Logger LOGGER = Logger.getLogger(DPRTableLogic.class);
    private boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    private DPRLogic dprLogic = new DPRLogic();
    private MMDPRLogic mmdprLogic = new MMDPRLogic();
    boolean firstTotal = false;
    private NMDPRLogic nmDPRLogic = new NMDPRLogic();
    private String screenName = StringUtils.EMPTY;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("Load Data Start=" + start + " and offset" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<DiscountProjectionResultsDTO> list = new ArrayList<DiscountProjectionResultsDTO>();
        if (firstGenerated && offset > 0) {
            try {
                switch (screenName) {
                    case CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED:

                        if (Constant.MM.equalsIgnoreCase(projSelDTO.getMarketTypeValue())) {
                            list = mmdprLogic.getConfiguredMMDicountResults(getLastParent(), start, offset, projSelDTO);
                        } else {
                            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
                            projSelDTO.setNonFetchableIndex(indexList);
                            list = dprLogic.getConfiguredDPResults(getLastParent(), start, offset, projSelDTO);
                        }
                        break;
                    case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                        list = nmDPRLogic.getConfiguredProjectionResults(getLastParent(), start, offset, projSelDTO);
                        break;
                }

                int i = start;
                for (DiscountProjectionResultsDTO dto : list) {
                    map.put(i, dto);
                    i++;
                }
                projSelDTO.clearNonFetchableIndex();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (firstGenerated) {

                switch (screenName) {
                    case CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED:
                        if (Constant.MM.equalsIgnoreCase(projSelDTO.getMarketTypeValue())) {
                            count = mmdprLogic.getConfiguredMMDiscountResultsCount(getLastParent(), projSelDTO, true);
                        } else {
                            count = dprLogic.getConfiguredDPResultsCount(getLastParent(), projSelDTO, true, initialProjSelDTO);
                        }
                        break;
                    case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                        count = nmDPRLogic.getConfiguredProjectionResultsCount(getLastParent(), projSelDTO, true);
                        break;
                }

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Return Count " + count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountProjectionResultsDTO dto = (DiscountProjectionResultsDTO) object;
        ((CustomTreeContainer<DiscountProjectionResultsDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<DiscountProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<DiscountProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        if (Constant.MM.equalsIgnoreCase(projSelDTO.getMarketTypeValue())) {
        }
        Object custFilter = getColumnIdToFilterValue(Constant.GROUP);
        if (custFilter != null) {
            String[] custHeirarchyNo = custFilter.toString().split("~");
            if (custHeirarchyNo != null && !Constant.DASH.equals(custHeirarchyNo) && String.valueOf(custFilter).contains(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                projSelDTO.setGroup(custHeirarchyNo[1]);
                projSelDTO.setFilterCustomerDDLB(custHeirarchyNo[1]);
                projSelDTO.setSelectedCust(custHeirarchyNo[0]);
                if (Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    projSelDTO.setFilterLevelValue("Pivot");
                    projSelDTO.setFilterFlag(true);
                    projSelDTO.setDiscountLevel(StringUtils.EMPTY);
                } else {
                    projSelDTO.setFilterLevelValue(Constant.CUSTOMER_SMALL);
                    projSelDTO.setFilterFlag(true);
                }
                projSelDTO.setFilterDdlb(true);
            } else {
                projSelDTO.setFilterDdlb(false);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setFilterCustomerDDLB(StringUtils.EMPTY);
                projSelDTO.setSelectedCust(StringUtils.EMPTY);
                projSelDTO.setFilterLevelValue(StringUtils.EMPTY);
            }
            projSelDTO.setIsTotal(true);
            projSelDTO.setIsFilter(false);
            clearAll();
        }

    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, boolean isTotal, String screenName) {

        this.projSelDTO = projSelDTO;
        this.screenName = screenName;
        clearAll();
        dprLogic = new DPRLogic();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName)) {
            initialProjSelDTO.setCustomerLevelNo(projSelDTO.getCustomerLevelNo());
            initialProjSelDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            initialProjSelDTO.setProductLevelNo(projSelDTO.getProductLevelNo());
            firstTotal = isTotal;
        }
        firstGenerated = true;
        setCurrentPage(1);
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) throws SystemException, PortalException {
        switch (screenName) {
            case CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED:
                mandatedExpandData(parentId, treeLevel, expandLevelNo);
                break;
            case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                nmExpandData(parentId, treeLevel, expandLevelNo);
                break;
        }

    }

    private void mandatedExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        initialProjSelDTO.setCustomerLevelNo(projSelDTO.getCustomerLevelNo());
        initialProjSelDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        initialProjSelDTO.setProductLevelNo(projSelDTO.getProductLevelNo());
        projSelDTO.setIsProjectionTotal(true);
        int count = dprLogic.getConfiguredDPResultsCount(parentId, projSelDTO, true, initialProjSelDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        projSelDTO.setIsProjectionTotal(true);
        String productHierarchyNo = projSelDTO.getProductHierarchyNo();
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
        String hierarchyNo = projSelDTO.getHierarchyNo();
        int levelNo = projSelDTO.getTreeLevelNo();
        int customLevelNo=projSelDTO.getCustomLevelNo();
        if(projSelDTO.isCustomFlag()){
        levelNo=projSelDTO.getCustomLevelNo();
        }
       
        try {
            if (expandLevelNo >= levelNo) {
                List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), false, StringUtils.EMPTY);

                int size = levelList.size();
                int index = count - size + 1;

                for (int j = 0; j < size; j++) {
                    Leveldto levelDto = levelList.get(j);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    projSelDTO.setHierarchyNo(hierarchyNo);
                    projSelDTO.setLevelNo(levelDto.getLevelNo());
                    projSelDTO.setTreeLevelNo(levelDto.getTreeLevelNo());
                    projSelDTO.setCustomLevelNo(customLevelNo);
                    dto.setCustomLevelNo(customLevelNo);
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(levelDto.getRelationshipLevelValue());
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(dto.getHierarchyNo(), dto.getHierarchyIndicator()));
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    DiscountProjectionResultsDTO discountDTO = new DiscountProjectionResultsDTO();

                    if (projSelDTO.getPivotView().equalsIgnoreCase(Constant.PERIOD)) {
                        discountDTO = dprLogic.getChildNodeValues(dto, projSelDTO);
                    } else {

                        discountDTO = dprLogic.getPivotChildNodeValues(dto, projSelDTO);
                    }
                    addExpandedTreeList(customTreeLevel, discountDTO);
                    recursivelyLoadExpandData(discountDTO, customTreeLevel, expandLevelNo);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void nmExpandData(Object parentId, String treeLevel, int expandLevelNo) throws SystemException, PortalException {
        int count = nmDPRLogic.getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo = projSelDTO.getProductHierarchyNo();
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
        String hierarchyNo = projSelDTO.getHierarchyNo();
        int levelNo = projSelDTO.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
            if (projSelDTO.isGroupCount()) {
                String customTreeLevel = treeLevel + (count) + ".";
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
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
                List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    Leveldto levelDto = levelList.get(j);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
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
                    DiscountProjectionResultsDTO discountDTO = new DiscountProjectionResultsDTO();
                    if (projSelDTO.getPivotView().equals(Constant.PERIOD)) {
                        discountDTO = nmDPRLogic.getChildNodeValues(dto, projSelDTO, null);
                    } else {
                        discountDTO = nmDPRLogic.getPivotChildNodeValues(dto, projSelDTO);
                    }
                    addExpandedTreeList(customTreeLevel, discountDTO);
                    recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                }
            }
        }
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }
}
