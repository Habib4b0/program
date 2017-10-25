package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdRsDetailsLocalService}.
 *
 * @author
 * @see ImtdRsDetailsLocalService
 * @generated
 */
public class ImtdRsDetailsLocalServiceWrapper
    implements ImtdRsDetailsLocalService,
        ServiceWrapper<ImtdRsDetailsLocalService> {
    private ImtdRsDetailsLocalService _imtdRsDetailsLocalService;

    public ImtdRsDetailsLocalServiceWrapper(
        ImtdRsDetailsLocalService imtdRsDetailsLocalService) {
        _imtdRsDetailsLocalService = imtdRsDetailsLocalService;
    }

    /**
    * Adds the imtd rs details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails addImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.addImtdRsDetails(imtdRsDetails);
    }

    /**
    * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
    *
    * @param imtdRsDetailsSid the primary key for the new imtd rs details
    * @return the new imtd rs details
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails createImtdRsDetails(
        int imtdRsDetailsSid) {
        return _imtdRsDetailsLocalService.createImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Deletes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details that was removed
    * @throws PortalException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.deleteImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Deletes the imtd rs details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.deleteImtdRsDetails(imtdRsDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdRsDetailsLocalService.dynamicQuery();
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
        return _imtdRsDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdRsDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdRsDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _imtdRsDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdRsDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdRsDetails fetchImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.fetchImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Returns the imtd rs details with the primary key.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details
    * @throws PortalException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails getImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.getImtdRsDetails(imtdRsDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @return the range of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdRsDetails> getImtdRsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.getImtdRsDetailses(start, end);
    }

    /**
    * Returns the number of imtd rs detailses.
    *
    * @return the number of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdRsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.getImtdRsDetailsesCount();
    }

    /**
    * Updates the imtd rs details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsDetails updateImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsDetailsLocalService.updateImtdRsDetails(imtdRsDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdRsDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdRsDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdRsDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.deleteAll(userId, sessionId,
            dateCreated, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.updateOperation(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.updateForPopulate(userId, sessionId,
            createdDate, operation, populateField, populateValue, future3,
            future4);
    }

    @Override
    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.updateForPopulateAll(userId,
            sessionId, createdDate, operation, populateField, populateValue,
            future3, future4);
    }

    @Override
    public java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return _imtdRsDetailsLocalService.getIfpLazyList(start, offset,
            ifpIdList, operation, searchValue);
    }

    @Override
    public java.lang.Object updateToRsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String flag,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdRsDetailsLocalService.updateToRsDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, flag, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempRsDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String ifpSystemId, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.insertTempRsDetailsInADD(userId,
            sessionId, createdDate, ifpSystemId, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempRsDetailsInEdit(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String rsSystemId, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.insertTempRsDetailsInEdit(userId,
            sessionId, createdDate, rsSystemId, future1, future2, future3,
            future4);
    }

    @Override
    public java.util.List getItemLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return _imtdRsDetailsLocalService.getItemLazyList(start, offset,
            companyIdList, operation, searchValue);
    }

    @Override
    public java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdRsDetailsLocalService.updateToIFPDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.lang.Object insertTempIfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.insertTempIfpDetailsInADD(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdRsDetailsLocalService.insertTempIfpDetailsInEdit(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object validateTempRSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.validateTempRSDeatils(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object getTempRSLazyList(java.lang.String rsId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdRsDetailsLocalService.getTempRSLazyList(rsId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.util.List loadIFPItems(java.lang.Integer start,
        java.lang.Integer end, java.lang.Object userID,
        java.lang.Object sessionID, java.lang.Object column,
        java.lang.Object orderBy, java.lang.Object value1,
        java.lang.Object value2,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _imtdRsDetailsLocalService.loadIFPItems(start, end, userID,
            sessionID, column, orderBy, value1, value2, parameters);
    }

    @Override
    public void mergeImtdRsDetailsFormula(int rsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId) {
        _imtdRsDetailsLocalService.mergeImtdRsDetailsFormula(rsdSid, itemSid,
            userId, sessionId);
    }

    @Override
    public void deleteTempTableRecords(int rsSid, int rsdSid,
        java.lang.String userId, java.lang.String sessionId) {
        _imtdRsDetailsLocalService.deleteTempTableRecords(rsSid, rsdSid,
            userId, sessionId);
    }

    @Override
    public void insertFormulaToRsdFrImtd(int rsdSid, java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate) {
        _imtdRsDetailsLocalService.insertFormulaToRsdFrImtd(rsdSid, userId,
            sessionId, createdDate);
    }

    @Override
    public void addAllFormulaToRsdFrImtd(int itemSid, java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate) {
        _imtdRsDetailsLocalService.addAllFormulaToRsdFrImtd(itemSid, userId,
            sessionId, createdDate);
    }

    @Override
    public void remaoveAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        _imtdRsDetailsLocalService.remaoveAllFormulaToRsdFrImtd(itemSid,
            userId, sessionId, createdDate);
    }

    @Override
    public void deleteRsdFr(int rsdSid) {
        _imtdRsDetailsLocalService.deleteRsdFr(rsdSid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdRsDetailsLocalService getWrappedImtdRsDetailsLocalService() {
        return _imtdRsDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdRsDetailsLocalService(
        ImtdRsDetailsLocalService imtdRsDetailsLocalService) {
        _imtdRsDetailsLocalService = imtdRsDetailsLocalService;
    }

    @Override
    public ImtdRsDetailsLocalService getWrappedService() {
        return _imtdRsDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdRsDetailsLocalService imtdRsDetailsLocalService) {
        _imtdRsDetailsLocalService = imtdRsDetailsLocalService;
    }
}
