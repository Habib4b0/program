package com.stpl.app.gtnforecasting.dao;

import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.List;

import java.util.Map;

// TODO: Auto-generated Javadoc
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
     * Get the Discount Projection
     *
     * @param session
     * @param startAndEndPeriods
     * @param frequency
     * @param year
     * @param actualsOrProjections
     * @param isProgram
     * @param discountList
     * @param historyNumber
     * @param levelNo
     * @param isCount
     * @param userGroup
     * @param hierarchyIndicator
     * @param start
     * @param isCustom
     * @param offset
     * @param customViewDetails
     * @param isRefresh
     * @param refreshHierarchyNumbers
     * @return
     */
    List<DiscountProjectionDTO> getDiscountProjection(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String actualsOrProjections,
            boolean isProgram, List<String> discountList, String year, int historyNumber, int levelNo, String hierarchyIndicator, String userGroup,
            int start, int offset, boolean isCount, boolean isCustom, List<String> customViewDetails, boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid,boolean isAltHistory);

    /**
     * To update the adjusted discount rates
     *
     * @param projectionId
     */
    void updateAdjustedDiscount(int projectionId);

    /**
     * To update the records which are all checked
     *
     * @param session
     * @param checkValue
     * @param hierarchyNo
     * @param uncheckedHierarchyNo
     * @param checkedHierarchyNo
     * @param hierarchyIndicator
     * @param isCustomView
     * @param customViewDetails
     * @param relationshipBuilderSid
     * @return 
     */
    int updateCheckRecord(SessionDTO session, boolean checkValue, String hierarchyNo, String userGroup, String hierarchyIndicator, 
            boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid, boolean isProgram, List<String> discountNamesList);

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
     * To mass update
     *
     * @param session
     * @param hierarchyNo
     * @param fieldValue
     * @return
     */
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue,  boolean isProgram, boolean isCustom, 
            List<String> customViewDetails , List<String> discountList, String relationshipBuilderSid);

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
            int levelNo, String hierarchyNo, String discountName, String fieldValue, boolean isProgram, boolean isCustomHierarchy, List<String> customViewDetails, String relationshipBuilderSid);

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
    boolean updateInputsForAdjustment(SessionDTO session, String frequency, String levelType, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, Map<String, Map<String, List<String>>> selectedPeriods);


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

    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkClear, boolean isProgram, List<String> discountList);

    public int getCheckedRecordCount(int projectionId, String userId, String sessionId, boolean isProgram, List<String> discountList) ;

}
