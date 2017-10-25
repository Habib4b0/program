package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmDiscountProjMasterLocalService}.
 *
 * @author
 * @see NmDiscountProjMasterLocalService
 * @generated
 */
public class NmDiscountProjMasterLocalServiceWrapper
    implements NmDiscountProjMasterLocalService,
        ServiceWrapper<NmDiscountProjMasterLocalService> {
    private NmDiscountProjMasterLocalService _nmDiscountProjMasterLocalService;

    public NmDiscountProjMasterLocalServiceWrapper(
        NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
        _nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
    }

    /**
    * Adds the nm discount proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster addNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.addNmDiscountProjMaster(nmDiscountProjMaster);
    }

    /**
    * Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm discount proj master
    * @return the new nm discount proj master
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster createNmDiscountProjMaster(
        int projectionDetailsSid) {
        return _nmDiscountProjMasterLocalService.createNmDiscountProjMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm discount proj master
    * @return the nm discount proj master that was removed
    * @throws PortalException if a nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.deleteNmDiscountProjMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm discount proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.deleteNmDiscountProjMaster(nmDiscountProjMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmDiscountProjMasterLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmDiscountProjMaster fetchNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.fetchNmDiscountProjMaster(projectionDetailsSid);
    }

    /**
    * Returns the nm discount proj master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the nm discount proj master
    * @return the nm discount proj master
    * @throws PortalException if a nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster getNmDiscountProjMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.getNmDiscountProjMaster(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm discount proj masters
    * @param end the upper bound of the range of nm discount proj masters (not inclusive)
    * @return the range of nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmDiscountProjMaster> getNmDiscountProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.getNmDiscountProjMasters(start,
            end);
    }

    /**
    * Returns the number of nm discount proj masters.
    *
    * @return the number of nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmDiscountProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.getNmDiscountProjMastersCount();
    }

    /**
    * Updates the nm discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjMaster the nm discount proj master
    * @return the nm discount proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjMaster updateNmDiscountProjMaster(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjMasterLocalService.updateNmDiscountProjMaster(nmDiscountProjMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmDiscountProjMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmDiscountProjMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmDiscountProjMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
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
        java.lang.String relationshipBuilderSid, boolean isAltHistory,
        java.lang.String action) {
        return _nmDiscountProjMasterLocalService.getDiscountProjection(projectionId,
            userId, sessionId, frequency, startAndEndPeriods, hierarchyNo,
            isProgram, discountList, year, historyNumber, levelNo,
            hierarchyIndicator, userGroup, startIndex, endIndex, isCount,
            isCustom, customViewDetails, isRefresh, refreshHierarchyNumbers,
            relationshipBuilderSid, isAltHistory, action);
    }

    @Override
    public java.util.List getDiscountPrograms(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String programType, boolean viewFlag) {
        return _nmDiscountProjMasterLocalService.getDiscountPrograms(projectionId,
            userId, sessionId, programType, viewFlag);
    }

    @Override
    public void checkClearAll(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String userGroup,
        boolean checkValue, boolean isProgram,
        java.util.List<java.lang.String> discountNamesList) {
        _nmDiscountProjMasterLocalService.checkClearAll(projectionId, userId,
            sessionId, userGroup, checkValue, isProgram, discountNamesList);
    }

    @Override
    public int updateCheckRecord(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean checkValue,
        java.lang.String hierarchyNo, java.lang.String userGroup,
        java.lang.String hierarchyIndicator, boolean isCustomView,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid, boolean isProgram,
        java.util.List<java.lang.String> discountNamesList) {
        return _nmDiscountProjMasterLocalService.updateCheckRecord(projectionId,
            userId, sessionId, checkValue, hierarchyNo, userGroup,
            hierarchyIndicator, isCustomView, customViewDetails,
            relationshipBuilderSid, isProgram, discountNamesList);
    }

    @Override
    public void massUpdate(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String selectedField, java.lang.String fieldValue,
        java.util.List<java.lang.String> checkedDiscounts, boolean isProgram) {
        _nmDiscountProjMasterLocalService.massUpdate(projectionId, userId,
            sessionId, frequency, startAndEndPeriods, selectedField,
            fieldValue, checkedDiscounts, isProgram);
    }

    @Override
    public boolean updateInputsForAdjustment(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, java.lang.String levelType,
        java.lang.String adjustmentType, java.lang.String adjustmentBasis,
        java.lang.String adjustmentValue,
        java.lang.String allocationMethodology,
        java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.List<java.lang.String>>> selectedPeriodsMap) {
        return _nmDiscountProjMasterLocalService.updateInputsForAdjustment(projectionId,
            userId, sessionId, frequency, levelType, adjustmentType,
            adjustmentBasis, adjustmentValue, allocationMethodology,
            selectedPeriodsMap);
    }

    @Override
    public boolean saveDiscountProjectionListView(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String frequency, int period, int year,
        java.lang.String hierarchy, int levelNo, java.lang.String hierarchyNo,
        java.lang.String discountName, java.lang.String fieldValue,
        boolean isProgram, boolean isCustomHierarchy,
        java.util.List<java.lang.String> customViewDetails,
        java.lang.String relationshipBuilderSid) {
        return _nmDiscountProjMasterLocalService.saveDiscountProjectionListView(projectionId,
            userId, sessionId, frequency, period, year, hierarchy, levelNo,
            hierarchyNo, discountName, fieldValue, isProgram,
            isCustomHierarchy, customViewDetails, relationshipBuilderSid);
    }

    @Override
    public java.util.List<java.lang.String> getGroupValues(int projectionId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String masterTableName,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid) {
        return _nmDiscountProjMasterLocalService.getGroupValues(projectionId,
            userId, sessionId, masterTableName, discountList,
            relationshipBuilderSid);
    }

    @Override
    public int getIndex(int projectionId, java.lang.String hierarchy,
        java.lang.String hierarchyNo, java.lang.String selectedHiearchyNo) {
        return _nmDiscountProjMasterLocalService.getIndex(projectionId,
            hierarchy, hierarchyNo, selectedHiearchyNo);
    }

    @Override
    public boolean saveGroupValues(int projectionId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String hierarchyNo,
        java.lang.String groupValue, boolean isProgram, boolean isCustom,
        java.util.List<java.lang.String> customViewDetails,
        java.util.List<java.lang.String> discountList,
        java.lang.String relationshipBuilderSid) {
        return _nmDiscountProjMasterLocalService.saveGroupValues(projectionId,
            userId, sessionId, hierarchyNo, groupValue, isProgram, isCustom,
            customViewDetails, discountList, relationshipBuilderSid);
    }

    @Override
    public java.util.List getDiscountProjectionResults(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, boolean viewFlag) {
        return _nmDiscountProjMasterLocalService.getDiscountProjectionResults(discountprojectionId,
            frequency, history, actualsOrProjections, view, order,
            startAndEndPeriods, userId, sessionId, viewFlag);
    }

    @Override
    public java.util.List getChildDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String projection,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String frequency, int userId, int sessionId) {
        return _nmDiscountProjMasterLocalService.getChildDiscount(discountprojectionId,
            projection, startAndEndPeriods, frequency, userId, sessionId);
    }

    @Override
    public java.util.List getVarianceDiscount(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.util.List<java.lang.String> discountList, java.lang.String year,
        int levelNo, java.lang.String sales) {
        return _nmDiscountProjMasterLocalService.getVarianceDiscount(projectionId,
            frequency, startAndEndPeriods, actualsOrProjections, parentName,
            discountList, year, levelNo, sales);
    }

    @Override
    public java.util.List getDiscountNo(int projectionId,
        java.util.List<java.lang.String> priceGroupType) {
        return _nmDiscountProjMasterLocalService.getDiscountNo(projectionId,
            priceGroupType);
    }

    @Override
    public java.util.List getTotalDiscountNumber(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId, java.util.List<java.lang.Object> view) {
        return _nmDiscountProjMasterLocalService.getTotalDiscountNumber(projectionDetailsId,
            frequency, actualsOrProjections, startAndEndPeriods, userId,
            sessionId, view);
    }

    @Override
    public java.util.List getSubDiscount(
        java.util.List<java.lang.Integer> projectionDetailsId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return _nmDiscountProjMasterLocalService.getSubDiscount(projectionDetailsId,
            frequency, actualsOrProjections, startAndEndPeriods, userId,
            sessionId);
    }

    @Override
    public java.util.List getTotalDiscountCount(int projectionMasterId,
        java.lang.String frequency, java.lang.String actualsOrProjections,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return _nmDiscountProjMasterLocalService.getTotalDiscountCount(projectionMasterId,
            frequency, actualsOrProjections, startAndEndPeriods, userId,
            sessionId);
    }

    @Override
    public java.util.List getAllPesriodDiscount(
        java.util.List<java.lang.Integer> discountprojectionId,
        java.lang.String frequency, java.lang.String history,
        java.lang.String actualsOrProjections, java.lang.String view,
        java.lang.String order,
        java.util.List<java.lang.Integer> startAndEndPeriods, int userId,
        int sessionId) {
        return _nmDiscountProjMasterLocalService.getAllPesriodDiscount(discountprojectionId,
            frequency, history, actualsOrProjections, view, order,
            startAndEndPeriods, userId, sessionId);
    }

    @Override
    public int getCheckedRecordCount(int projectionId, java.lang.String userId,
        java.lang.String sessionId, boolean isProgram,
        java.util.List<java.lang.String> discountList) {
        return _nmDiscountProjMasterLocalService.getCheckedRecordCount(projectionId,
            userId, sessionId, isProgram, discountList);
    }

    @Override
    public java.util.List getCCPDetailsID(int ProjectionMasterSid,
        java.lang.String hierarchyNo, java.lang.String levelNo) {
        return _nmDiscountProjMasterLocalService.getCCPDetailsID(ProjectionMasterSid,
            hierarchyNo, levelNo);
    }

    @Override
    public java.util.List getCCPDetailsIDForProductHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String levelNo) {
        return _nmDiscountProjMasterLocalService.getCCPDetailsIDForProductHierarchy(ProjectionMasterSid,
            hierarchyNo, levelNo);
    }

    @Override
    public java.util.List getCCPDetailsIDForCustomHierarchy(
        int ProjectionMasterSid, java.lang.String hierarchyNo,
        java.lang.String customViewId) {
        return _nmDiscountProjMasterLocalService.getCCPDetailsIDForCustomHierarchy(ProjectionMasterSid,
            hierarchyNo, customViewId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmDiscountProjMasterLocalService getWrappedNmDiscountProjMasterLocalService() {
        return _nmDiscountProjMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmDiscountProjMasterLocalService(
        NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
        _nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
    }

    @Override
    public NmDiscountProjMasterLocalService getWrappedService() {
        return _nmDiscountProjMasterLocalService;
    }

    @Override
    public void setWrappedService(
        NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
        _nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
    }
}
