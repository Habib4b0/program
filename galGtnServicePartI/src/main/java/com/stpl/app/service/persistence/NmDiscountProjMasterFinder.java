package com.stpl.app.service.persistence;

public interface NmDiscountProjMasterFinder {
    public java.util.List getDiscountProjection(int projectionId,
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
        java.lang.String relationshipBuilderSid, boolean isAltHistory);

    public int getIndex(int projectionId, java.lang.String hierarchyIndicator,
        java.lang.String hierarchyNo, java.lang.String selectedHiearchyNo);

    public int getCheckedRecordCount(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean isProgram,
        java.util.List<java.lang.String> discountList);

    public java.util.List getDiscountPrograms(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String programType);

    public void checkClearAll(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String userGroup,
        boolean checkValue, boolean isProgram,
        java.util.List<java.lang.String> discountList);

    public int updateCheckRecord(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean checkValue,
        java.lang.String hierarchyNo, java.lang.String userGroup,
        java.lang.String hierarchyIndicator, boolean isCustomView,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid, boolean isProgram,
        java.util.List<java.lang.String> discountList);

    public void massUpdate(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String selectedField, java.lang.String fieldValue,
        java.util.List<java.lang.String> checkedDiscounts, boolean isProgram);

    public boolean updateInputsForAdjustment(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, java.lang.String levelType,
        java.lang.String adjustmentType, java.lang.String adjustmentBasis,
        java.lang.String adjustmentValue,
        java.lang.String allocationMethodology,
        java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.List<java.lang.String>>> periodsMap);

    public java.util.List<java.lang.String> getGroupValues(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String masterTable,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid);

    public boolean saveGroupValues(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String hierarchyNo,
        java.lang.String groupValue, boolean isProgram, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid);

    public boolean saveDiscountProjectionListView(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, int period, int year,
        java.lang.String hierarchyIndicator, int levelNo,
        java.lang.String hierarchyNo, java.lang.String discountName,
        java.lang.String fieldValue, boolean isProgram,
        boolean isCustomHierarchy,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid);

    public java.util.List getDiscountProjectionResults(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String discountString,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    public java.util.List getChildDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String projection,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String frequency, int userId, int sessionId);

    public java.util.List getVarianceDiscount(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String discountTotal, java.lang.String parentName,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int levelNo, java.lang.String sales);

    public java.util.List getDiscountNo(int projectionId,
        java.util.List<java.lang.String> priceGroupType);

    public java.util.List getTotalDiscountNumber(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String discountName,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, java.util.List<java.lang.Object> view);

    public java.util.List getSubDiscount(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String discountList,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    public java.util.List getTotalDiscountCount(int projectionMasterId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);

    public java.util.List getCCPDetailsID(int ProjectionMasterSid,
        java.lang.String hierarchyNo, java.lang.String levelNo);

    public java.util.List getCCPDetailsIDForProductHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String levelNo);

    public java.util.List getCCPDetailsIDForCustomHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String customViewId);

    public java.util.List getAllPeriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods);

    public java.util.List getAllPesriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String discountName,
        java.lang.String hist, java.lang.String view, java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId);
}
