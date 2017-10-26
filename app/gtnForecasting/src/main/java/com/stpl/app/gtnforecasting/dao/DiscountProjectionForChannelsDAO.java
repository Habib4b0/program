/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vigneshkanna
 */
public interface DiscountProjectionForChannelsDAO {
//    /**
//     * Get the Discount Projection
//     *
//     * @param session
//     * @param startAndEndPeriods
//     * @param frequency
//     * @param year
//     * @param actualsOrProjections
//     * @param isProgram
//     * @param discountList
//     * @param historyNumber
//     * @param levelNo
//     * @param isCount
//     * @param userGroup
//     * @param hierarchyIndicator
//     * @param start
//     * @param isCustom
//     * @param offset
//     * @param customViewDetails
//     * @param isRefresh
//     * @param refreshHierarchyNumbers
//     * @return
//     */

    List<DiscountProjectionDTO> getDiscountProjection(SessionDTO session, String parentName,
            String year, int historyNumber, int levelNo, String hierarchyIndicator,
            int start, int offset, boolean isCount, boolean isCustom, List<String> customViewDetails, boolean isRefresh, String refreshHierarchyNumbers, ProjectionSelectionDTO projectionSelection);
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
     * TO save Group values
     *
     * @param session
     * @param frequency
     * @param year
     * @param period
     * @param hierarchy
     * @param levelNo
     * @param hierarchyNo
     * @param discountName
     * @param fieldValue
     * @param isProgram
     * @param isCustomHierarchy
     * @param customViewDetails
     * @return
     */
    boolean saveDiscountProjectionListView(SessionDTO session, String frequency, int period, int year, String hierarchy,
            int levelNo, String hierarchyNo, String discountName, String fieldValue, boolean isCustomHierarchy, List<String> customViewDetails, String selectedField);

    /**
     * To update input before calling adjustment procedure.
     *
     * @param session
     * @param frequency
     * @param levelType
     * @param adjustmentType
     * @param adjustmentBasis
     * @param adjustmentValue
     * @param allocationMethodology
     * @param selectedPeriods
     * @return
     */
    boolean updateInputsForAdjustment(SessionDTO session, String frequency, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, List<String> baselinePeriods, List<String> selectedPeriods);

    /**
     * To load the level filter values
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @param startLevelNo
     * @param endLevelNo
     * @param customId
     * @param isCustomHierarchy
     * @param isLevelFilter
     * @return
     */
    List loadLevels(int projectionId, String hierarchyIndicator, int startLevelNo, int endLevelNo, int customId, boolean isCustomHierarchy, boolean isLevelFilter);

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
