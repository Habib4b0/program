package com.stpl.app.gtnforecasting.dao.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.ProjectionVarianceDAO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.service.finderImpl.NmDiscountImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.NmSalesProjectionImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.ProjectionCustHierarchyImpl;
import java.util.ArrayList;
import java.util.Collections;
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
            String level, String discountLevel) throws PortalException {

        return Collections.emptyList();
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
            List<Integer> comparisonProjections, String frequency, String level, String discountLevel, int levelNo, String sales, List<Integer> periods, ProjectionSelectionDTO projSelDTO) throws PortalException{
        List resuList = new ArrayList();
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
            throws PortalException{

        return Collections.emptyList();
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
            String from, String to) throws PortalException{

        List list = new ProjectionCustHierarchyImpl().getComparisonSearch(workflowStatus, marketType, brand, projName, contHldr, ndcNo, ndcName, desc, contract, from, to);
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
        return;
    }

    @Override
    public List getPVResultsSales(int projectionId, String frequency, List<Integer> periods, String actualsOrProjections, String parentName, String year, int levelNo, String sales) {
        return new NmSalesProjectionImpl().getVarianceSales(projectionId, frequency, periods, parentName, year, levelNo, sales);
    }

    @Override
    public List getPVResultsDiscount(int projectionId, String frequency, List<Integer> periods, String discountTotal, String parentName, List<String> discountList, String year, int levelNo, String sales) {
        return new NmDiscountImpl().getVarianceDiscount(projectionId, frequency, periods, discountTotal, parentName, discountList, year, levelNo, sales);
    }
}
