package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.ProjectionVarianceDAO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionvariance.dto.ProjectionVarianceDTO;
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
            List<Integer> comparisonProjections, String frequency, String level, String discountLevel,int levelNo,String sales, List<Integer> periods,ProjectionSelectionDTO projSelDTO) throws
            PortalException {
        return new ArrayList();
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
            throws PortalException {

         
        return Collections.emptyList();
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

}
