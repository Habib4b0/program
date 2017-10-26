package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdIfpDetailsLocalService}.
 *
 * @author
 * @see ImtdIfpDetailsLocalService
 * @generated
 */
public class ImtdIfpDetailsLocalServiceWrapper
    implements ImtdIfpDetailsLocalService,
        ServiceWrapper<ImtdIfpDetailsLocalService> {
    private ImtdIfpDetailsLocalService _imtdIfpDetailsLocalService;

    public ImtdIfpDetailsLocalServiceWrapper(
        ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
        _imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
    }

    /**
    * Adds the imtd ifp details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetails the imtd ifp details
    * @return the imtd ifp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails addImtdIfpDetails(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.addImtdIfpDetails(imtdIfpDetails);
    }

    /**
    * Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
    *
    * @param imtdIfpDetailsSid the primary key for the new imtd ifp details
    * @return the new imtd ifp details
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails createImtdIfpDetails(
        int imtdIfpDetailsSid) {
        return _imtdIfpDetailsLocalService.createImtdIfpDetails(imtdIfpDetailsSid);
    }

    /**
    * Deletes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details that was removed
    * @throws PortalException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails deleteImtdIfpDetails(
        int imtdIfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.deleteImtdIfpDetails(imtdIfpDetailsSid);
    }

    /**
    * Deletes the imtd ifp details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetails the imtd ifp details
    * @return the imtd ifp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails deleteImtdIfpDetails(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.deleteImtdIfpDetails(imtdIfpDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdIfpDetailsLocalService.dynamicQuery();
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
        return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _imtdIfpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdIfpDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdIfpDetails fetchImtdIfpDetails(
        int imtdIfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.fetchImtdIfpDetails(imtdIfpDetailsSid);
    }

    /**
    * Returns the imtd ifp details with the primary key.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details
    * @throws PortalException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails getImtdIfpDetails(
        int imtdIfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.getImtdIfpDetails(imtdIfpDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ifp detailses
    * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
    * @return the range of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> getImtdIfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.getImtdIfpDetailses(start, end);
    }

    /**
    * Returns the number of imtd ifp detailses.
    *
    * @return the number of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdIfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.getImtdIfpDetailsesCount();
    }

    /**
    * Updates the imtd ifp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetails the imtd ifp details
    * @return the imtd ifp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdIfpDetails updateImtdIfpDetails(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdIfpDetailsLocalService.updateImtdIfpDetails(imtdIfpDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdIfpDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdIfpDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdIfpDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.deleteAll(userId, sessionId,
            dateCreated, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _imtdIfpDetailsLocalService.updateOperation(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4,
            parameters);
    }

    @Override
    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.updateForPopulate(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.updateForPopulateAll(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.util.List getItemLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.Object valueList,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _imtdIfpDetailsLocalService.getItemLazyList(start, offset,
            companyIdList, operation, searchValue, valueList, future1, future2,
            future3, future4, parameters);
    }

    @Override
    public java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.updateToIFPDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.lang.Object insertTempIfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.insertTempIfpDetailsInADD(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.insertTempIfpDetailsInEdit(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object validateTempIFPDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.validateTempIFPDeatils(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.getTempCFPLazyList(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object checkAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate, int value,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.checkAll(userId, sessionId,
            createdDate, value, populateField, populateValue, future3, future4);
    }

    @Override
    public java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempIfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.updateAll(userId, sessionId,
            dateCreated, operation, tempIfpSystemId, deleteYesterdayRecordFlag,
            future3, future4);
    }

    @Override
    public java.lang.Object updateIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdIfpDetailsLocalService.updateIFPDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.util.List getIFPSearchList(java.lang.String ifpId,
        java.lang.String ifpNo, java.lang.String ifpName, int ifpType,
        int ifpStatus, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean countFlag,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _imtdIfpDetailsLocalService.getIFPSearchList(ifpId, ifpNo,
            ifpName, ifpType, ifpStatus, itemId, itemNo, itemName, start,
            offset, column, orderBy, countFlag, parameters);
    }

    @Override
    public java.util.List fetchFieldsForSecurity(java.lang.String moduleName,
        java.lang.String tabName, java.lang.Object obj1, java.lang.Object obj2,
        java.lang.Object obj3) {
        return _imtdIfpDetailsLocalService.fetchFieldsForSecurity(moduleName,
            tabName, obj1, obj2, obj3);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdIfpDetailsLocalService getWrappedImtdIfpDetailsLocalService() {
        return _imtdIfpDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdIfpDetailsLocalService(
        ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
        _imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
    }

    @Override
    public ImtdIfpDetailsLocalService getWrappedService() {
        return _imtdIfpDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
        _imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
    }
}
