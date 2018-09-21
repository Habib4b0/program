package com.stpl.app.gtnforecasting.dao;

import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.List;


/**
 * The Interface DiscountProjectionDAO.
 */
public interface DiscountProjectionDAO {

    /**
     * Get the Discount Program list
     *
     * @param session
     * @param programType
     * @return
     */
    List<DiscountSelectionDTO> getDiscountProgramList(SessionDTO session, String programType);

    /**
     * To update the adjusted discount rates
     *
     * @param projectionId
     */
    void updateAdjustedDiscount(int projectionId);

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
            List<String> selectedDiscounts, boolean isProgram);

    /**
     * To load level Filter
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @param masterTableName
     * @param discountList
     * @param relationshipBuilderSid
     * @return
     */
    List loadGroupValues(int projectionId, String userId, String sessionId, String masterTableName, List<String> discountList, String relationshipBuilderSid);

    /**
     * To get Global Rebate Details
     *
     * @param projectionId
     * @return
     */
    public List getGlobalRebateDetails(int projectionId);

    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkClear);

    public int getCheckedRecordCount(int projectionId, String userId, String sessionId, boolean isProgram, List<String> discountList) ;

}
