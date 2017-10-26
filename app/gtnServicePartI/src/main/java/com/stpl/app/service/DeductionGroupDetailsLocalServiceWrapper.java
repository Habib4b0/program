package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DeductionGroupDetailsLocalService}.
 *
 * @author
 * @see DeductionGroupDetailsLocalService
 * @generated
 */
public class DeductionGroupDetailsLocalServiceWrapper
    implements DeductionGroupDetailsLocalService,
        ServiceWrapper<DeductionGroupDetailsLocalService> {
    private DeductionGroupDetailsLocalService _deductionGroupDetailsLocalService;

    public DeductionGroupDetailsLocalServiceWrapper(
        DeductionGroupDetailsLocalService deductionGroupDetailsLocalService) {
        _deductionGroupDetailsLocalService = deductionGroupDetailsLocalService;
    }

    /**
    * Adds the deduction group details to the database. Also notifies the appropriate model listeners.
    *
    * @param deductionGroupDetails the deduction group details
    * @return the deduction group details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails addDeductionGroupDetails(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.addDeductionGroupDetails(deductionGroupDetails);
    }

    /**
    * Creates a new deduction group details with the primary key. Does not add the deduction group details to the database.
    *
    * @param deductionGroupDetailsSid the primary key for the new deduction group details
    * @return the new deduction group details
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails createDeductionGroupDetails(
        int deductionGroupDetailsSid) {
        return _deductionGroupDetailsLocalService.createDeductionGroupDetails(deductionGroupDetailsSid);
    }

    /**
    * Deletes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionGroupDetailsSid the primary key of the deduction group details
    * @return the deduction group details that was removed
    * @throws PortalException if a deduction group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails deleteDeductionGroupDetails(
        int deductionGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.deleteDeductionGroupDetails(deductionGroupDetailsSid);
    }

    /**
    * Deletes the deduction group details from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionGroupDetails the deduction group details
    * @return the deduction group details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails deleteDeductionGroupDetails(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.deleteDeductionGroupDetails(deductionGroupDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _deductionGroupDetailsLocalService.dynamicQuery();
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
        return _deductionGroupDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionGroupDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _deductionGroupDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _deductionGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _deductionGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.DeductionGroupDetails fetchDeductionGroupDetails(
        int deductionGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.fetchDeductionGroupDetails(deductionGroupDetailsSid);
    }

    /**
    * Returns the deduction group details with the primary key.
    *
    * @param deductionGroupDetailsSid the primary key of the deduction group details
    * @return the deduction group details
    * @throws PortalException if a deduction group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails getDeductionGroupDetails(
        int deductionGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.getDeductionGroupDetails(deductionGroupDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the deduction group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction group detailses
    * @param end the upper bound of the range of deduction group detailses (not inclusive)
    * @return the range of deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.DeductionGroupDetails> getDeductionGroupDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.getDeductionGroupDetailses(start,
            end);
    }

    /**
    * Returns the number of deduction group detailses.
    *
    * @return the number of deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDeductionGroupDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.getDeductionGroupDetailsesCount();
    }

    /**
    * Updates the deduction group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param deductionGroupDetails the deduction group details
    * @return the deduction group details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DeductionGroupDetails updateDeductionGroupDetails(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _deductionGroupDetailsLocalService.updateDeductionGroupDetails(deductionGroupDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _deductionGroupDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _deductionGroupDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _deductionGroupDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DeductionGroupDetailsLocalService getWrappedDeductionGroupDetailsLocalService() {
        return _deductionGroupDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDeductionGroupDetailsLocalService(
        DeductionGroupDetailsLocalService deductionGroupDetailsLocalService) {
        _deductionGroupDetailsLocalService = deductionGroupDetailsLocalService;
    }

    @Override
    public DeductionGroupDetailsLocalService getWrappedService() {
        return _deductionGroupDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        DeductionGroupDetailsLocalService deductionGroupDetailsLocalService) {
        _deductionGroupDetailsLocalService = deductionGroupDetailsLocalService;
    }
}
