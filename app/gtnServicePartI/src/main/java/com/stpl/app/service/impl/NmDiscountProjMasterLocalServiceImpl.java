package com.stpl.app.service.impl;

import com.stpl.app.service.base.NmDiscountProjMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.NmDiscountProjMasterFinderUtil;
import java.util.List;

/**
 * The implementation of the nm discount proj master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.NmDiscountProjMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.NmDiscountProjMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil
 */
public class NmDiscountProjMasterLocalServiceImpl
    extends NmDiscountProjMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil} to access the nm discount proj master local service.
     */
     public  java.util.List getDiscountProjection(int projectionId,
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
        java.lang.String relationshipBuilderSid, boolean isAltHistory,java.lang.String action) {
        return NmDiscountProjMasterFinderUtil.getDiscountProjection(projectionId, userId, sessionId,
            frequency, startAndEndPeriods, hierarchyNo, isProgram,
            discountList, year, historyNumber, levelNo, hierarchyIndicator,
            userGroup, startIndex, endIndex, isCount, isCustom,
            customViewDetails, isRefresh, refreshHierarchyNumbers,
            relationshipBuilderSid, isAltHistory,action);
    }
     
    
    public java.util.List getDiscountPrograms(int projectionId, java.lang.String userId, java.lang.String sessionId,
        java.lang.String programType, boolean viewFlag) {
        return NmDiscountProjMasterFinderUtil.getDiscountPrograms(projectionId, userId, sessionId, programType, viewFlag);
    }
    
    public void checkClearAll(int projectionId, java.lang.String userId, java.lang.String sessionId, java.lang.String userGroup, boolean checkValue, boolean isProgram, java.util.List<java.lang.String> discountNamesList) {
        NmDiscountProjMasterFinderUtil.checkClearAll(projectionId, userId, sessionId, userGroup, checkValue, isProgram, discountNamesList);
    }
    
    public int updateCheckRecord(int projectionId, java.lang.String userId, java.lang.String sessionId, boolean checkValue, java.lang.String hierarchyNo, java.lang.String userGroup,
            java.lang.String hierarchyIndicator, boolean isCustomView,  java.util.List<java.lang.String> customViewDetails, java.lang.String relationshipBuilderSid, 
            boolean isProgram, java.util.List<java.lang.String> discountNamesList) {
        return NmDiscountProjMasterFinderUtil.updateCheckRecord(projectionId, userId, sessionId, checkValue, hierarchyNo, userGroup, hierarchyIndicator, isCustomView, 
                customViewDetails, relationshipBuilderSid, isProgram, discountNamesList);
    }

    public void massUpdate(int projectionId, java.lang.String userId, java.lang.String sessionId, java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String selectedField, java.lang.String fieldValue, java.util.List<java.lang.String> checkedDiscounts, boolean isProgram) {
        NmDiscountProjMasterFinderUtil.massUpdate(projectionId, userId, sessionId, frequency, startAndEndPeriods,
            selectedField, fieldValue, checkedDiscounts, isProgram);
    }
     public boolean updateInputsForAdjustment(
        int projectionId, java.lang.String userId, java.lang.String sessionId,  
        java.lang.String frequency, java.lang.String levelType, 
        java.lang.String adjustmentType, java.lang.String adjustmentBasis,
        java.lang.String adjustmentValue, java.lang.String allocationMethodology,
        java.util.Map<String, java.util.Map<String, java.util.List<String>>> selectedPeriodsMap) {
        return NmDiscountProjMasterFinderUtil
                   .updateInputsForAdjustment(projectionId, userId,  sessionId, frequency, levelType, adjustmentType,
            adjustmentBasis, adjustmentValue, allocationMethodology, selectedPeriodsMap);
    }
     
    public boolean saveDiscountProjectionListView(int projectionId,
        java.lang.String userId, java.lang.String sessionId, java.lang.String frequency, int period,
        int year, java.lang.String hierarchy, int levelNo,
        java.lang.String hierarchyNo, java.lang.String discountName,
        java.lang.String fieldValue, boolean isProgram, boolean isCustomHierarchy, java.util.List<java.lang.String> customViewDetails, java.lang.String relationshipBuilderSid) {
        return NmDiscountProjMasterFinderUtil
                   .saveDiscountProjectionListView(projectionId, userId,
            sessionId, frequency, period, year, hierarchy, levelNo,
            hierarchyNo, discountName, fieldValue, isProgram, isCustomHierarchy,  customViewDetails, relationshipBuilderSid);
    }
    
//    public java.util.List getLevelvalues(int projectionId, java.lang.String hierarchyindicator, int startLevelNo, int endLevelNo, int customId, java.lang.String relationshipBuilderSid, boolean isCustomHierarchy, boolean isLevelFilter) {
//        return NmDiscountProjMasterFinderUtil.getLevelvalues(projectionId, hierarchyindicator, startLevelNo, endLevelNo, customId, relationshipBuilderSid, isCustomHierarchy, isLevelFilter);
//    }
    
    public java.util.List<java.lang.String> getGroupValues (int projectionId, java.lang.String userId, java.lang.String sessionId, java.lang.String masterTableName, java.util.List<java.lang.String> discountList, java.lang.String relationshipBuilderSid){
        return NmDiscountProjMasterFinderUtil.getGroupValues(projectionId, userId, sessionId, masterTableName, discountList, relationshipBuilderSid);
    }
    
    public int getIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo){
         return NmDiscountProjMasterFinderUtil.getIndex(projectionId, hierarchy, hierarchyNo, selectedHiearchyNo);
    }
    
    public boolean saveGroupValues (int projectionId, java.lang.String userId, java.lang.String sessionId, java.lang.String hierarchyNo, java.lang.String groupValue, boolean isProgram, 
            boolean isCustom, java.util.List<java.lang.String> customViewDetails , java.util.List<java.lang.String> discountList, java.lang.String relationshipBuilderSid){
        return NmDiscountProjMasterFinderUtil.saveGroupValues(projectionId, userId, sessionId, hierarchyNo, groupValue, isProgram, isCustom, customViewDetails, discountList, relationshipBuilderSid);
    }
        
   public  java.util.List getDiscountProjectionResults(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, boolean viewFlag) {
        return NmDiscountProjMasterFinderUtil
                   .getDiscountProjectionResults(discountprojectionId,
            frequency, history, actualsOrProjections, view, order,
            startAndEndPeriods, userId, sessionId, viewFlag);
    }

   public  java.util.List getChildDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String projection,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String frequency, int userId, int sessionId) {
        return NmDiscountProjMasterFinderUtil
                   .getChildDiscount(discountprojectionId, projection,
            startAndEndPeriods, frequency, userId, sessionId);
    }
    public java.util.List getVarianceDiscount(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int levelNo, java.lang.String sales){
        return  NmDiscountProjMasterFinderUtil.getVarianceDiscount(projectionId, frequency,
            startAndEndPeriods, actualsOrProjections, parentName, discountList,
            year, levelNo, sales);
    }
    public java.util.List getDiscountNo(int projectionId,
        java.util.List<java.lang.String> priceGroupType) {
        return NmDiscountProjMasterFinderUtil.getDiscountNo(projectionId, priceGroupType);
    }
    
    public  java.util.List getTotalDiscountNumber(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId,List<Object> view) {
        return NmDiscountProjMasterFinderUtil
                   .getTotalDiscountNumber(projectionDetailsId, frequency,
            actualsOrProjections, startAndEndPeriods, userId, sessionId, view);
    }

  public  java.util.List getSubDiscount(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return NmDiscountProjMasterFinderUtil
                   .getSubDiscount(projectionDetailsId, frequency,
            actualsOrProjections, startAndEndPeriods, userId, sessionId);
    }

    public  java.util.List getTotalDiscountCount(int projectionMasterId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return NmDiscountProjMasterFinderUtil
                   .getTotalDiscountCount(projectionMasterId, frequency,
            actualsOrProjections, startAndEndPeriods, userId, sessionId);
    }

   public  java.util.List getAllPesriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return NmDiscountProjMasterFinderUtil
                   .getAllPesriodDiscount(discountprojectionId, frequency,
            history, actualsOrProjections, view, order, startAndEndPeriods,
            userId, sessionId);
    }
    
    public int getCheckedRecordCount(int projectionId, java.lang.String userId, java.lang.String sessionId, 
            boolean isProgram, java.util.List<java.lang.String> discountList) {
        return NmDiscountProjMasterFinderUtil
                   .getCheckedRecordCount(projectionId, userId, sessionId, isProgram, discountList);
    }
    
     

    public  java.util.List getCCPDetailsID(int ProjectionMasterSid,
        java.lang.String hierarchyNo, java.lang.String levelNo) {
        return NmDiscountProjMasterFinderUtil
                   .getCCPDetailsID(ProjectionMasterSid, hierarchyNo, levelNo);
    }

    public  java.util.List getCCPDetailsIDForProductHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String levelNo) {
        return NmDiscountProjMasterFinderUtil
                   .getCCPDetailsIDForProductHierarchy(ProjectionMasterSid,
            hierarchyNo, levelNo);
    }
    
    
    public java.util.List getCCPDetailsIDForCustomHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String customViewId) {
        return NmDiscountProjMasterFinderUtil
                   .getCCPDetailsIDForCustomHierarchy(ProjectionMasterSid,
            hierarchyNo, customViewId);
    }
}
