package com.stpl.app.cff.dao.impl;

import com.stpl.app.cff.dao.ProjectionVarianceDAO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;

public class ProjectionVarianceDAOImpl implements ProjectionVarianceDAO {

    /**
     * Gets the discount contract holder summary.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the comparison projections
     * @param frequency the frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the contract holder summary
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List getContractHolderSummary(int projectionId, String indicator, List<Integer> comparisonProjections, String frequency,
            String level, String discountLevel) throws SystemException, PortalException {


        return null;
    }

    /**
     * Generate projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the comparison projections
     * @param frequency the frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List generateProjectionVariance(int projectionId, String indicator,
            List<Integer> comparisonProjections, String frequency, String level, String discountLevel,int levelNo,String sales, List<Integer> periods,ProjectionSelectionDTO projSelDTO) throws SystemException,
            PortalException {
        List resuList = new ArrayList();
        // TODO Auto-generated method stub
        return resuList;
    }

    /**
     * Generate pivot projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the comparison projections
     * @param frequency the frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List<ProjectionVarianceDTO> generatePivotProjectionVariance(int projectionId, String indicator,
            List<Integer> comparisonProjections, String frequency, String level, String discountLevel)
            throws SystemException, PortalException {

        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Search for projections in comparison lookup.
     *
     * @param workflowStatus the workflow status
     * @param marketType the market type
     * @param brand the brand
     * @param projName the proj name
     * @param contHldr the cont hldr
     * @param ndcNo the ndc no
     * @param ndcName the ndc name
     * @param desc the desc
     * @param contract the contract
     * @param from the from
     * @param to the to
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List searchForProjections(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to) throws SystemException, PortalException {

        List list = ProjectionCustHierarchyLocalServiceUtil.getComparisonSearch(workflowStatus, marketType, brand, projName, contHldr, ndcNo, ndcName, desc, contract, from, to);
        return list;
    }

    /**
     * Save or updates projection variance custom view hierarchies.
     *
     * @param indicator to indicate whether action is SAVE/UPDATE
     * @param projectionId the projection id
     */
    @Override
    public void saveProjectionVarianceView(String indicator, int projectionId) {
    }
    @Override
    public List getPVResultsSales(int projectionId, String frequency,List<Integer> periods, String actualsOrProjections,String parentName, String year, int levelNo, String sales) {
        return NmSalesProjectionLocalServiceUtil.getVarianceSales(projectionId, frequency,periods, actualsOrProjections,parentName, year, levelNo, sales);
    }
     @Override
    public List getPVResultsDiscount(int projectionId, String frequency,List<Integer> periods, String discountTotal,String parentName, List<String> discountList, String year, int levelNo, String sales) {
        return NmDiscountProjMasterLocalServiceUtil.getVarianceDiscount(projectionId, frequency,periods, discountTotal,parentName, discountList, year, levelNo, sales);
    }
}
