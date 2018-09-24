/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vigneshkanna
 */
public interface DiscountProjectionForChannelsDAO {
    /**
     * To update the records which are all checked
     *
     * @param session
     * @param checkValue
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param isCustomView
     * @param customViewDetails
     * @param relationshipBuilderSid
     * @return 
     */
    int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String hierarchyIndicator, 
            boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid);

    /**
     *
     * To update the check records for every operation
     *
     * @param projectionId
     * @param hierarchy
     * @param selectedHiearchyNo
     * @param hierarchyNo
     * @return
     */

    int getLevelIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo);

    /**
     * To get the level Index for expand collapse functionality
     *
     * @param session
     * @param frequency
     * @param startAndEndPeriods
     * @param selectedField
     * @param fieldValue
     * @param selectedDiscounts
     * @param isProgram
     */
    void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts);

    /**
     * Query for the alternate contract history
     *
     * @param contractquery
     * @return
     */
    List getAlternateContract(String contractquery);
    /**
     * Query for the retrieving company in alternate contract history
     *
     * @param query
     * @return
     */
    List<String> getCompanyForAlternate(String query);

    /**
     * To get Global Rebate Details
     *
     * @param projectionId
     * @return
     */
    public void getCalculation(ProjectionSelectionDTO projectionSelectionDTO);

    public void discountPopulate(final Map<String, Object> input, final String queryName) throws SystemException;

    public void checkClearAll(int projectionId, String userId, String sessionId, boolean checkClear);
}
