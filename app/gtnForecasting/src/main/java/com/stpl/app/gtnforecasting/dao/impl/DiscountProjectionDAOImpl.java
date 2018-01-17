package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.DiscountProjectionDAO;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.service.finderImpl.NmDiscountImpl;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DiscountProjectionDAOImpl implements DiscountProjectionDAO {


    @Override
    public List<DiscountSelectionDTO> getDiscountProgramList(SessionDTO session, String programType) {
        return new NmDiscountImpl().getDiscountPrograms(session.getProjectionId(), session.getUserId(), session.getSessionId(), programType,"view".equals(session.getAction()));
    }

    @Override
    public List<DiscountProjectionDTO> getDiscountProjection(SessionDTO session, String frequency,
            List<Integer> startAndEndPeriods, String parentName, boolean isProgram, List<String> discountList, String year, int historyNumber,
            int levelNo, String hierarchyIndicator, String userGroup, int start, int offset, boolean isCount, boolean isCustom, List<String> customViewDetails, 
            boolean isRefresh, String refreshHierarchyNumbers, String relationshipBuilderSid,boolean isAltHistory) {
        return new NmDiscountImpl().getDiscountProjection(session.getProjectionId(), session.getUserId(), session.getSessionId(), frequency,
                startAndEndPeriods, parentName, isProgram, discountList, year, historyNumber, levelNo, hierarchyIndicator, userGroup,
                start, offset, isCount, isCustom, customViewDetails, isRefresh, refreshHierarchyNumbers, relationshipBuilderSid,isAltHistory,session.getAction());
    }

    @Override
    public void updateAdjustedDiscount(int projectionId) {
        // TODO Auto-generated method stub
    }

    @Override
    public int updateCheckRecord(SessionDTO session, boolean checkValue,String hierarchyNo, String userGroup, String hierarchyIndicator, 
            boolean isCustomView, List<String> customViewDetails, String relationshipBuilderSid, boolean isProgram, List<String> discountNamesList) {
        return new NmDiscountImpl().updateCheckRecord(session.getProjectionId(), session.getUserId(), session.getSessionId(), checkValue,
                hierarchyNo,  userGroup, hierarchyIndicator, isCustomView, customViewDetails, relationshipBuilderSid, isProgram, discountNamesList);
    }

    @Override
    public int getLevelIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo) {
        return new NmDiscountImpl().getIndex(projectionId, hierarchy, hierarchyNo, selectedHiearchyNo);
    }

    @Override
    public void massUpdate(SessionDTO session, String frequency, List<Integer> startAndEndPeriods, String selectedField, String fieldValue,
            List<String> selectedDiscounts, boolean isProgram) {
        new NmDiscountImpl().massUpdate(session.getProjectionId(), session.getUserId(), session.getSessionId(), frequency, startAndEndPeriods, selectedField, fieldValue,
                selectedDiscounts, isProgram);
    }

    @Override
    public boolean updateInputsForAdjustment(SessionDTO session, String frequency, String levelType, String adjustmentType, String adjustmentBasis,
            String adjustmentValue, String allocationMethodology, Map<String, Map<String, List<String>>> selectedPeriods) {

        return new NmDiscountImpl().updateInputsForAdjustment(session.getProjectionId(), session.getUserId(), session.getSessionId(), frequency, levelType, adjustmentType, adjustmentBasis,
                adjustmentValue, allocationMethodology, selectedPeriods);
    }

    @Override
    public boolean saveGroupValues(SessionDTO session, String hierarchyNo, String fieldValue, boolean isProgram, boolean isCustom, 
            List<String> customViewDetails , List<String> discountList, String relationshipBuilderSid) {
        return new NmDiscountImpl().saveGroupValues(session.getProjectionId(), session.getUserId(), session.getSessionId(), hierarchyNo, fieldValue, isProgram, isCustom, 
             customViewDetails ,  discountList,relationshipBuilderSid);
    }

    @Override
    public boolean saveDiscountProjectionListView(SessionDTO session, String frequency, int period, int year, String hierarchy,
            int levelNo, String hierarchyNo, String discountName, String fieldValue, boolean isProgram, boolean isCustomHierarchy, List<String> customViewDetails, String relationshipBuilderSid) {
        return new NmDiscountImpl().saveDiscountProjectionListView(session.getProjectionId(), session.getUserId(), session.getSessionId(), frequency, period, year, hierarchy,
                levelNo, hierarchyNo, discountName, fieldValue, isProgram, isCustomHierarchy, customViewDetails, relationshipBuilderSid);
    }

  
    @Override
    public int getCheckedRecordCount(int projectionId, String userId, String sessionId, boolean isProgram, List<String> discountList) {
        return new NmDiscountImpl().getCheckedRecordCount(projectionId, userId, sessionId, isProgram, discountList);
    }

    @Override
    public List<String> loadGroupValues(int projectionId, String userId, String sessionId, String masterTableName, List<String> discountList, String relationshipBuilderSid) {
        return new NmDiscountImpl().getGroupValues(projectionId, userId, sessionId, masterTableName, discountList, relationshipBuilderSid);
    }

    @Override
    public List getGlobalRebateDetails(int projectionId) {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkClear, boolean isProgram, List<String> discountList) {
        new NmDiscountImpl().checkClearAll(projectionId, userId, sessionId, userGroup, checkClear, isProgram, discountList);
    }

}
