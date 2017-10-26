package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdItemPriceRebateDetailsLocalService}.
 *
 * @author
 * @see ImtdItemPriceRebateDetailsLocalService
 * @generated
 */
public class ImtdItemPriceRebateDetailsLocalServiceWrapper
    implements ImtdItemPriceRebateDetailsLocalService,
        ServiceWrapper<ImtdItemPriceRebateDetailsLocalService> {
    private ImtdItemPriceRebateDetailsLocalService _imtdItemPriceRebateDetailsLocalService;

    public ImtdItemPriceRebateDetailsLocalServiceWrapper(
        ImtdItemPriceRebateDetailsLocalService imtdItemPriceRebateDetailsLocalService) {
        _imtdItemPriceRebateDetailsLocalService = imtdItemPriceRebateDetailsLocalService;
    }

    /**
    * Adds the imtd item price rebate details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails addImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.addImtdItemPriceRebateDetails(imtdItemPriceRebateDetails);
    }

    /**
    * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
    *
    * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
    * @return the new imtd item price rebate details
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails createImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid) {
        return _imtdItemPriceRebateDetailsLocalService.createImtdItemPriceRebateDetails(imtdItemPriceRebateSid);
    }

    /**
    * Deletes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws PortalException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails deleteImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.deleteImtdItemPriceRebateDetails(imtdItemPriceRebateSid);
    }

    /**
    * Deletes the imtd item price rebate details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails deleteImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.deleteImtdItemPriceRebateDetails(imtdItemPriceRebateDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdItemPriceRebateDetailsLocalService.dynamicQuery();
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
        return _imtdItemPriceRebateDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdItemPriceRebateDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdItemPriceRebateDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _imtdItemPriceRebateDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdItemPriceRebateDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails fetchImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.fetchImtdItemPriceRebateDetails(imtdItemPriceRebateSid);
    }

    /**
    * Returns the imtd item price rebate details with the primary key.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details
    * @throws PortalException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails getImtdItemPriceRebateDetails(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.getImtdItemPriceRebateDetails(imtdItemPriceRebateSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @return the range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> getImtdItemPriceRebateDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.getImtdItemPriceRebateDetailses(start,
            end);
    }

    /**
    * Returns the number of imtd item price rebate detailses.
    *
    * @return the number of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdItemPriceRebateDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.getImtdItemPriceRebateDetailsesCount();
    }

    /**
    * Updates the imtd item price rebate details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    * @return the imtd item price rebate details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdItemPriceRebateDetails updateImtdItemPriceRebateDetails(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdItemPriceRebateDetailsLocalService.updateImtdItemPriceRebateDetails(imtdItemPriceRebateDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdItemPriceRebateDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdItemPriceRebateDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdItemPriceRebateDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Boolean loadTempItemdetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.loadTempItemdetails(input,
            future);
    }

    @Override
    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.massPopulate(input,
            future);
    }

    @Override
    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.massPopulateAll(input,
            future);
    }

    @Override
    public java.lang.Boolean saveItem(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.saveItem(input, future);
    }

    @Override
    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.deleteAll(input, future);
    }

    @Override
    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future) {
        return _imtdItemPriceRebateDetailsLocalService.updateAll(input, future);
    }

    @Override
    public java.lang.Object validateTempRSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return _imtdItemPriceRebateDetailsLocalService.validateTempRSDeatils(userId,
            sessionId, createdDate, operation, future1, future2, future3,
            future4);
    }

    @Override
    public java.util.List getSelectedItemList(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.Boolean orderBy,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount, java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3) {
        return _imtdItemPriceRebateDetailsLocalService.getSelectedItemList(userID,
            sessionID, start, offset, column, orderBy, filterMap, isCount,
            future1, future2, future3);
    }

    @Override
    public void mergeImtdRsContractDetailsFormula(int contRsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId) {
        _imtdItemPriceRebateDetailsLocalService.mergeImtdRsContractDetailsFormula(contRsdSid,
            itemSid, userId, sessionId);
    }

    @Override
    public void deleteTempRsContractTableRecords(int contRsSid, int contRsdSid,
        java.lang.String userId, java.lang.String sessionId) {
        _imtdItemPriceRebateDetailsLocalService.deleteTempRsContractTableRecords(contRsSid,
            contRsdSid, userId, sessionId);
    }

    @Override
    public void insertFormulaToContractRsdFrImtd(int contRsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        _imtdItemPriceRebateDetailsLocalService.insertFormulaToContractRsdFrImtd(contRsdSid,
            userId, sessionId, createdDate);
    }

    @Override
    public void addAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        _imtdItemPriceRebateDetailsLocalService.addAllFormulaToContractRsdFrImtd(itemSid,
            userId, sessionId, createdDate);
    }

    @Override
    public void remaoveAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        _imtdItemPriceRebateDetailsLocalService.remaoveAllFormulaToContractRsdFrImtd(itemSid,
            userId, sessionId, createdDate);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdItemPriceRebateDetailsLocalService getWrappedImtdItemPriceRebateDetailsLocalService() {
        return _imtdItemPriceRebateDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdItemPriceRebateDetailsLocalService(
        ImtdItemPriceRebateDetailsLocalService imtdItemPriceRebateDetailsLocalService) {
        _imtdItemPriceRebateDetailsLocalService = imtdItemPriceRebateDetailsLocalService;
    }

    @Override
    public ImtdItemPriceRebateDetailsLocalService getWrappedService() {
        return _imtdItemPriceRebateDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdItemPriceRebateDetailsLocalService imtdItemPriceRebateDetailsLocalService) {
        _imtdItemPriceRebateDetailsLocalService = imtdItemPriceRebateDetailsLocalService;
    }
}
