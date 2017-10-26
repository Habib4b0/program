package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdCfpDetailsLocalService}.
 *
 * @author
 * @see ImtdCfpDetailsLocalService
 * @generated
 */
public class ImtdCfpDetailsLocalServiceWrapper
    implements ImtdCfpDetailsLocalService,
        ServiceWrapper<ImtdCfpDetailsLocalService> {
    private ImtdCfpDetailsLocalService _imtdCfpDetailsLocalService;

    public ImtdCfpDetailsLocalServiceWrapper(
        ImtdCfpDetailsLocalService imtdCfpDetailsLocalService) {
        _imtdCfpDetailsLocalService = imtdCfpDetailsLocalService;
    }

    /**
    * Adds the imtd cfp details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails addImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.addImtdCfpDetails(imtdCfpDetails);
    }

    /**
    * Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
    *
    * @param imtdCfpDetailsSid the primary key for the new imtd cfp details
    * @return the new imtd cfp details
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails createImtdCfpDetails(
        int imtdCfpDetailsSid) {
        return _imtdCfpDetailsLocalService.createImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Deletes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details that was removed
    * @throws PortalException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.deleteImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Deletes the imtd cfp details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.deleteImtdCfpDetails(imtdCfpDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdCfpDetailsLocalService.dynamicQuery();
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
        return _imtdCfpDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdCfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdCfpDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _imtdCfpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdCfpDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdCfpDetails fetchImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.fetchImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Returns the imtd cfp details with the primary key.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details
    * @throws PortalException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails getImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.getImtdCfpDetails(imtdCfpDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @return the range of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdCfpDetails> getImtdCfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.getImtdCfpDetailses(start, end);
    }

    /**
    * Returns the number of imtd cfp detailses.
    *
    * @return the number of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdCfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.getImtdCfpDetailsesCount();
    }

    /**
    * Updates the imtd cfp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdCfpDetails updateImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdCfpDetailsLocalService.updateImtdCfpDetails(imtdCfpDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdCfpDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdCfpDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdCfpDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.deleteAll(userId, sessionId,
            dateCreated, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateOperation(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateForPopulate(userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    @Override
    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateForPopulateAll(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.util.List getCompanyLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.String column,
        java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return _imtdCfpDetailsLocalService.getCompanyLazyList(start, offset,
            companyIdList, operation, searchValue, column, orderBy, filterMap);
    }

    @Override
    public java.util.List getSelectedCompanies(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap,
        java.lang.String operation) {
        return _imtdCfpDetailsLocalService.getSelectedCompanies(start, offset,
            userId, sessionId, columnName, orderBy, filterMap, operation);
    }

    @Override
    public java.lang.Object updateToCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateToCFPDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.lang.Object insertTempCfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.insertTempCfpDetailsInADD(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object insertTempCfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.insertTempCfpDetailsInEdit(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object validateTempCFPDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.validateTempCFPDeatils(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return _imtdCfpDetailsLocalService.getTempCFPLazyList(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4, filterMap);
    }

    @Override
    public java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateAll(userId, sessionId,
            dateCreated, operation, tempCfpSystemId, deleteYesterdayRecordFlag,
            future3, future4);
    }

    @Override
    public java.lang.Object updateCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return _imtdCfpDetailsLocalService.updateCFPDetails(ifpMasterSystemId,
            userId, sessionId, createdDate, operation, future1, future2,
            future3, future4);
    }

    @Override
    public java.util.List getTempCfpDetails(java.lang.String userId,
        java.lang.String sessionId, int start, int offset,
        java.lang.String column, java.lang.String orederBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return _imtdCfpDetailsLocalService.getTempCfpDetails(userId, sessionId,
            start, offset, column, orederBy, filterMap);
    }

    @Override
    public java.lang.Boolean loadTempCompanydetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdCfpDetailsLocalService.loadTempCompanydetails(input, future);
    }

    @Override
    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdCfpDetailsLocalService.massPopulate(input, future);
    }

    @Override
    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdCfpDetailsLocalService.massPopulateAll(input, future);
    }

    @Override
    public java.lang.Boolean saveCompany(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdCfpDetailsLocalService.saveCompany(input, future);
    }

    @Override
    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        return _imtdCfpDetailsLocalService.deleteAll(input, future);
    }

    @Override
    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        return _imtdCfpDetailsLocalService.updateAll(input, future);
    }

    @Override
    public java.lang.Object getOverlapedCompanies(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdCfpDetailsLocalService.getOverlapedCompanies(input, future);
    }

    @Override
    public java.util.List getSelectedCompanies(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean flag,
        java.lang.Object future1,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount) {
        return _imtdCfpDetailsLocalService.getSelectedCompanies(userID,
            sessionID, start, offset, column, orderBy, flag, future1,
            filterMap, isCount);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdCfpDetailsLocalService getWrappedImtdCfpDetailsLocalService() {
        return _imtdCfpDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdCfpDetailsLocalService(
        ImtdCfpDetailsLocalService imtdCfpDetailsLocalService) {
        _imtdCfpDetailsLocalService = imtdCfpDetailsLocalService;
    }

    @Override
    public ImtdCfpDetailsLocalService getWrappedService() {
        return _imtdCfpDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdCfpDetailsLocalService imtdCfpDetailsLocalService) {
        _imtdCfpDetailsLocalService = imtdCfpDetailsLocalService;
    }
}
