package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class NmDiscountProjMasterFinderUtil {
    private static NmDiscountProjMasterFinder _finder;

    public static java.util.List getDiscountProjection(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String hierarchyNo, boolean isProgram,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int historyNumber, int levelNo, java.lang.String hierarchyIndicator,
        java.lang.String userGroup, int startIndex, int endIndex,
        boolean isCount, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails, boolean isRefresh,
        java.lang.String refreshHierarchyNumbers,
        java.lang.String relationshipBuilderSid, boolean isAltHistory) {
        return getFinder()
                   .getDiscountProjection(projectionId, userId, sessionId,
            frequency, startAndEndPeriods, hierarchyNo, isProgram,
            discountList, year, historyNumber, levelNo, hierarchyIndicator,
            userGroup, startIndex, endIndex, isCount, isCustom,
            customViewDetails, isRefresh, refreshHierarchyNumbers,
            relationshipBuilderSid, isAltHistory);
    }

    public static int getIndex(int projectionId,
        java.lang.String hierarchyIndicator, java.lang.String hierarchyNo,
        java.lang.String selectedHiearchyNo) {
        return getFinder()
                   .getIndex(projectionId, hierarchyIndicator, hierarchyNo,
            selectedHiearchyNo);
    }

    public static int getCheckedRecordCount(int projectionId,
        java.lang.String userId, java.lang.String sessionId, boolean isProgram,
        java.util.List<java.lang.String> discountList) {
        return getFinder()
                   .getCheckedRecordCount(projectionId, userId, sessionId,
            isProgram, discountList);
    }

    public static java.util.List getDiscountPrograms(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String programType) {
        return getFinder()
                   .getDiscountPrograms(projectionId, userId, sessionId,
            programType);
    }

    public static void checkClearAll(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String userGroup,
        boolean checkValue, boolean isProgram,
        java.util.List<java.lang.String> discountList) {
        getFinder()
            .checkClearAll(projectionId, userId, sessionId, userGroup,
            checkValue, isProgram, discountList);
    }

    public static int updateCheckRecord(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        boolean checkValue, java.lang.String hierarchyNo,
        java.lang.String userGroup, java.lang.String hierarchyIndicator,
        boolean isCustomView,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid, boolean isProgram,
        java.util.List<java.lang.String> discountList) {
        return getFinder()
                   .updateCheckRecord(projectionId, userId, sessionId,
            checkValue, hierarchyNo, userGroup, hierarchyIndicator,
            isCustomView, customViewDetails, relationshipBuilderSid, isProgram,
            discountList);
    }

    public static void massUpdate(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String selectedField, java.lang.String fieldValue,
        java.util.List<java.lang.String> checkedDiscounts, boolean isProgram) {
        getFinder()
            .massUpdate(projectionId, userId, sessionId, frequency,
            startAndEndPeriods, selectedField, fieldValue, checkedDiscounts,
            isProgram);
    }

    public static boolean updateInputsForAdjustment(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, java.lang.String levelType,
        java.lang.String adjustmentType, java.lang.String adjustmentBasis,
        java.lang.String adjustmentValue,
        java.lang.String allocationMethodology,
        java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.List<java.lang.String>>> periodsMap) {
        return getFinder()
                   .updateInputsForAdjustment(projectionId, userId, sessionId,
            frequency, levelType, adjustmentType, adjustmentBasis,
            adjustmentValue, allocationMethodology, periodsMap);
    }

    public static java.util.List<java.lang.String> getGroupValues(
        int projectionId, java.lang.String userId, java.lang.String sessionId,
        java.lang.String masterTable,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid) {
        return getFinder()
                   .getGroupValues(projectionId, userId, sessionId,
            masterTable, discountList, relationshipBuilderSid);
    }

    public static boolean saveGroupValues(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String hierarchyNo, java.lang.String groupValue,
        boolean isProgram, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid) {
        return getFinder()
                   .saveGroupValues(projectionId, userId, sessionId,
            hierarchyNo, groupValue, isProgram, isCustom, customViewDetails,
            discountList, relationshipBuilderSid);
    }

    public static boolean saveDiscountProjectionListView(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, int period, int year,
        java.lang.String hierarchyIndicator, int levelNo,
        java.lang.String hierarchyNo, java.lang.String discountName,
        java.lang.String fieldValue, boolean isProgram,
        boolean isCustomHierarchy,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid) {
        return getFinder()
                   .saveDiscountProjectionListView(projectionId, userId,
            sessionId, frequency, period, year, hierarchyIndicator, levelNo,
            hierarchyNo, discountName, fieldValue, isProgram,
            isCustomHierarchy, customViewDetails, relationshipBuilderSid);
    }

    public static java.util.List getDiscountProjectionResults(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String discountString,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return getFinder()
                   .getDiscountProjectionResults(discountprojectionId,
            frequency, discountString, actualsOrProjections, view, order,
            startAndEndPeriods, userId, sessionId);
    }

    public static java.util.List getChildDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String projection,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String frequency, int userId, int sessionId) {
        return getFinder()
                   .getChildDiscount(discountprojectionId, projection,
            startAndEndPeriods, frequency, userId, sessionId);
    }

    public static java.util.List getVarianceDiscount(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String discountTotal, java.lang.String parentName,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int levelNo, java.lang.String sales) {
        return getFinder()
                   .getVarianceDiscount(projectionId, frequency,
            startAndEndPeriods, discountTotal, parentName, discountList, year,
            levelNo, sales);
    }

    public static java.util.List getDiscountNo(int projectionId,
        java.util.List<java.lang.String> priceGroupType) {
        return getFinder().getDiscountNo(projectionId, priceGroupType);
    }

    public static java.util.List getTotalDiscountNumber(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String discountName,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, java.util.List<java.lang.Object> view) {
        return getFinder()
                   .getTotalDiscountNumber(projectionDetailsId, frequency,
            discountName, startAndEndPeriods, userId, sessionId, view);
    }

    public static java.util.List getSubDiscount(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String discountList,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return getFinder()
                   .getSubDiscount(projectionDetailsId, frequency,
            discountList, startAndEndPeriods, userId, sessionId);
    }

    public static java.util.List getTotalDiscountCount(int projectionMasterId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return getFinder()
                   .getTotalDiscountCount(projectionMasterId, frequency,
            actualsOrProjections, startAndEndPeriods, userId, sessionId);
    }

    public static java.util.List getCCPDetailsID(int ProjectionMasterSid,
        java.lang.String hierarchyNo, java.lang.String levelNo) {
        return getFinder()
                   .getCCPDetailsID(ProjectionMasterSid, hierarchyNo, levelNo);
    }

    public static java.util.List getCCPDetailsIDForProductHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String levelNo) {
        return getFinder()
                   .getCCPDetailsIDForProductHierarchy(ProjectionMasterSid,
            hierarchyNo, levelNo);
    }

    public static java.util.List getCCPDetailsIDForCustomHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String customViewId) {
        return getFinder()
                   .getCCPDetailsIDForCustomHierarchy(ProjectionMasterSid,
            hierarchyNo, customViewId);
    }

    public static java.util.List getAllPeriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods) {
        return getFinder()
                   .getAllPeriodDiscount(discountprojectionId, frequency,
            history, actualsOrProjections, view, order, startAndEndPeriods);
    }

    public static java.util.List getAllPesriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String discountName,
        java.lang.String hist, java.lang.String view, java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return getFinder()
                   .getAllPesriodDiscount(discountprojectionId, frequency,
            discountName, hist, view, order, startAndEndPeriods, userId,
            sessionId);
    }

    public static NmDiscountProjMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (NmDiscountProjMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmDiscountProjMasterFinder.class.getName());

            ReferenceRegistry.registerReference(NmDiscountProjMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(NmDiscountProjMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(NmDiscountProjMasterFinderUtil.class,
            "_finder");
    }
}
