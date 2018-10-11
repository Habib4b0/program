package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.DiscountProjectionDAO;
import com.stpl.app.gtnforecasting.dto.DiscountSelectionDTO;
import com.stpl.app.gtnforecasting.service.finderimpl.NmDiscountImpl;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.Collections;
import java.util.List;

public class DiscountProjectionDAOImpl implements DiscountProjectionDAO {


    @Override
    public List<DiscountSelectionDTO> getDiscountProgramList(SessionDTO session, String programType) {
        return new NmDiscountImpl().getDiscountPrograms(session.getProjectionId(), session.getUserId(), session.getSessionId(), programType,"view".equals(session.getAction()));
    }

    @Override
    public void updateAdjustedDiscount(int projectionId) {
        //updateAdjustedDiscount Method
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
    public int getCheckedRecordCount(int projectionId, String userId, String sessionId, boolean isProgram, List<String> discountList) {
        return new NmDiscountImpl().getCheckedRecordCount(projectionId, userId, sessionId, isProgram, discountList);
    }

    @Override
    public List<String> loadGroupValues(int projectionId, String userId, String sessionId, String masterTableName, List<String> discountList, String relationshipBuilderSid) {
        return new NmDiscountImpl().getGroupValues(projectionId, userId, sessionId, masterTableName, discountList, relationshipBuilderSid);
    }

    @Override
    public List getGlobalRebateDetails(int projectionId) {
        return Collections.emptyList();
    }

    @Override
    public void checkClearAll(int projectionId, String userId, String sessionId, String userGroup, boolean checkClear) {
        new NmDiscountImpl().checkClearAll(projectionId, userId, sessionId, userGroup, checkClear);
    }

}
