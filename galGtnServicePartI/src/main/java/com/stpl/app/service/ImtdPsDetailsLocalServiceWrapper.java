package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdPsDetailsLocalService}.
 *
 * @author
 * @see ImtdPsDetailsLocalService
 * @generated
 */
public class ImtdPsDetailsLocalServiceWrapper
    implements ImtdPsDetailsLocalService,
        ServiceWrapper<ImtdPsDetailsLocalService> {
    private ImtdPsDetailsLocalService _imtdPsDetailsLocalService;

    public ImtdPsDetailsLocalServiceWrapper(
        ImtdPsDetailsLocalService imtdPsDetailsLocalService) {
        _imtdPsDetailsLocalService = imtdPsDetailsLocalService;
    }

    /**
    * Adds the imtd ps details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails addImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.addImtdPsDetails(imtdPsDetails);
    }

    /**
    * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
    *
    * @param imtdPsDetailsSid the primary key for the new imtd ps details
    * @return the new imtd ps details
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails createImtdPsDetails(
        int imtdPsDetailsSid) {
        return _imtdPsDetailsLocalService.createImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Deletes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details that was removed
    * @throws PortalException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails deleteImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.deleteImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Deletes the imtd ps details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails deleteImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.deleteImtdPsDetails(imtdPsDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdPsDetailsLocalService.dynamicQuery();
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
        return _imtdPsDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdPsDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdPsDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _imtdPsDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdPsDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdPsDetails fetchImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.fetchImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Returns the imtd ps details with the primary key.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details
    * @throws PortalException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails getImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.getImtdPsDetails(imtdPsDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @return the range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdPsDetails> getImtdPsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.getImtdPsDetailses(start, end);
    }

    /**
    * Returns the number of imtd ps detailses.
    *
    * @return the number of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdPsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.getImtdPsDetailsesCount();
    }

    /**
    * Updates the imtd ps details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdPsDetails updateImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdPsDetailsLocalService.updateImtdPsDetails(imtdPsDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdPsDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdPsDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdPsDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.deleteAll(userId, sessionId,
            dateCreated, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.updateOperation(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.updateForPopulate(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.updateForPopulateAll(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.util.List getIFPLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return _imtdPsDetailsLocalService.getIFPLazyList(start, offset,
            companyIdList, operation, searchValue);
    }

    @Override
    public java.lang.Object updateToPsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdPsDetailsLocalService.updateToPsDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.lang.Object insertTempPsDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.insertTempPsDetailsInADD(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempPsDetailsInEdit(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.insertTempPsDetailsInEdit(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object validateTempPSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.validateTempPSDeatils(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdPsDetailsLocalService.getTempCFPLazyList(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.util.List getItemPriceDetails(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _imtdPsDetailsLocalService.getItemPriceDetails(start, offset,
            userId, sessionId, columnName, orderBy, parameters);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.lang.String queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _imtdPsDetailsLocalService.executeUpdateQuery(queryList, obj1,
            obj2);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdPsDetailsLocalService getWrappedImtdPsDetailsLocalService() {
        return _imtdPsDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdPsDetailsLocalService(
        ImtdPsDetailsLocalService imtdPsDetailsLocalService) {
        _imtdPsDetailsLocalService = imtdPsDetailsLocalService;
    }

    @Override
    public ImtdPsDetailsLocalService getWrappedService() {
        return _imtdPsDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdPsDetailsLocalService imtdPsDetailsLocalService) {
        _imtdPsDetailsLocalService = imtdPsDetailsLocalService;
    }
}
