package com.stpl.app.gtnforecasting.discountprojectionresults.logic.tablelogic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.CommercialDPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.MMDPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.NMDPRLogic;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pvinoth
 */
public class DPRTableLogic extends PageTreeTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DPRTableLogic.class);
    private boolean firstGenerated = false;
    protected ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    protected ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    private DPRLogic dprLogic = new DPRLogic();
    private MMDPRLogic mmdprLogic = new MMDPRLogic();    
    private NMDPRLogic nmDPRLogic = new NMDPRLogic();
    private String screenName = StringUtils.EMPTY;
    
    private final CommercialDPRLogic commercialDPRLogic = new CommercialDPRLogic();

    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug("Load Data Start= {},  and offset= {} " , start, offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        List<DiscountProjectionResultsDTO> list = new ArrayList<>();
        if (firstGenerated && offset > 0) {
            try {
                switch (screenName) {
                    case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                        list = commercialDPRLogic.getConfiguredProjectionResults(getLastParent(), start, offset, projSelDTO);
                        break;
                    default:
                        LOGGER.warn("screenName is not valid= {} " , screenName);
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
        LOGGER.debug("Load Data end with record= {}" , map.size());
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (firstGenerated) {

                switch (screenName) {
                    case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                        count = commercialDPRLogic.getConfiguredProjectionResultsCount(getLastParent(), projSelDTO, true);
                        break;
                    default:
                        LOGGER.warn("screenName is not valid= {} " , screenName);
                        break;
                }
               
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Return Count= {} " , count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountProjectionResultsDTO dto = (DiscountProjectionResultsDTO) object;
        ((ExtTreeContainer<DiscountProjectionResultsDTO>) datasource).addItem(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<DiscountProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<DiscountProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object custFilter = getColumnIdToFilterValue(Constant.GROUP);
        if (custFilter != null) {
            String[] custHeirarchyNo = custFilter.toString().split("~");
            if (custHeirarchyNo != null && !Constant.DASH.equals(custHeirarchyNo[0])) {
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
                String[] groupValue = custFilter.toString().split("-");
              if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                projSelDTO.setGroupFilter(String.valueOf(groupValue[1]));
            }
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

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, String screenName) {

        this.projSelDTO = projSelDTO;
        this.screenName = screenName;
        clearAll();
         dprLogic.setProjectionTotalList(new ArrayList<DiscountProjectionResultsDTO>());
        firstGenerated = true;
        setCurrentPage(1);
    }

     public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());

        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) throws PortalException {
        switch (screenName) {
            case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                nmExpandData(parentId, treeLevel, expandLevelNo);
                break;
            default:
                LOGGER.warn("screenName is not valid in default= {} " , screenName);
                break;
        }

    }

    private void nmExpandData(Object parentId, String treeLevel, int expandLevelNo) throws PortalException {
        int count = commercialDPRLogic.getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
         CommonLogic commonLogic = new CommonLogic();     
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
                List<String> hierarchyNoList = Collections.emptyList();
                if (projSelDTO.getLevelCount() != 0) {
                    if (projSelDTO.isIsCustomHierarchy()) {
                        hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, 0, projSelDTO.getLevelCount());
                    } else {
                        hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, 0, projSelDTO.getLevelCount());
                    }
                }
                int size = hierarchyNoList.size();
                int index = count - size + 1;
                List<DiscountProjectionResultsDTO> data =commercialDPRLogic.getConfiguredProjectionResults(parentId, index-1, size, projSelDTO);
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    addExpandedTreeList(customTreeLevel, data.get(j));
                    recursivelyLoadExpandData(data.get(j), customTreeLevel, expandLevelNo);
                    }
                    }
                }
            }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public MMDPRLogic getMmdprLogic() {
            return mmdprLogic;
    }

    public void setMmdprLogic(MMDPRLogic mmdprLogic) {
            this.mmdprLogic = mmdprLogic;
    }

    public DPRLogic getDprLogic() {
            return dprLogic;
    }

    public void setDprLogic(DPRLogic dprLogic) {
            this.dprLogic = dprLogic;
    }

    public NMDPRLogic getNmDPRLogic() {
            return nmDPRLogic;
    }

    public void setNmDPRLogic(NMDPRLogic nmDPRLogic) {
            this.nmDPRLogic = nmDPRLogic;
    }
}
