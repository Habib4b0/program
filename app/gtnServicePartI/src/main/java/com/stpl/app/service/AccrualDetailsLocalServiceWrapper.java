package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccrualDetailsLocalService}.
 *
 * @author
 * @see AccrualDetailsLocalService
 * @generated
 */
public class AccrualDetailsLocalServiceWrapper
    implements AccrualDetailsLocalService,
        ServiceWrapper<AccrualDetailsLocalService> {
    private AccrualDetailsLocalService _accrualDetailsLocalService;

    public AccrualDetailsLocalServiceWrapper(
        AccrualDetailsLocalService accrualDetailsLocalService) {
        _accrualDetailsLocalService = accrualDetailsLocalService;
    }

    /**
    * Adds the accrual details to the database. Also notifies the appropriate model listeners.
    *
    * @param accrualDetails the accrual details
    * @return the accrual details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualDetails addAccrualDetails(
        com.stpl.app.model.AccrualDetails accrualDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.addAccrualDetails(accrualDetails);
    }

    /**
    * Creates a new accrual details with the primary key. Does not add the accrual details to the database.
    *
    * @param accrualDetailsSid the primary key for the new accrual details
    * @return the new accrual details
    */
    @Override
    public com.stpl.app.model.AccrualDetails createAccrualDetails(
        int accrualDetailsSid) {
        return _accrualDetailsLocalService.createAccrualDetails(accrualDetailsSid);
    }

    /**
    * Deletes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualDetailsSid the primary key of the accrual details
    * @return the accrual details that was removed
    * @throws PortalException if a accrual details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualDetails deleteAccrualDetails(
        int accrualDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.deleteAccrualDetails(accrualDetailsSid);
    }

    /**
    * Deletes the accrual details from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualDetails the accrual details
    * @return the accrual details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualDetails deleteAccrualDetails(
        com.stpl.app.model.AccrualDetails accrualDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.deleteAccrualDetails(accrualDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _accrualDetailsLocalService.dynamicQuery();
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
        return _accrualDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accrualDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accrualDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _accrualDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _accrualDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.AccrualDetails fetchAccrualDetails(
        int accrualDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.fetchAccrualDetails(accrualDetailsSid);
    }

    /**
    * Returns the accrual details with the primary key.
    *
    * @param accrualDetailsSid the primary key of the accrual details
    * @return the accrual details
    * @throws PortalException if a accrual details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualDetails getAccrualDetails(
        int accrualDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.getAccrualDetails(accrualDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the accrual detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual detailses
    * @param end the upper bound of the range of accrual detailses (not inclusive)
    * @return the range of accrual detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.AccrualDetails> getAccrualDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.getAccrualDetailses(start, end);
    }

    /**
    * Returns the number of accrual detailses.
    *
    * @return the number of accrual detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAccrualDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.getAccrualDetailsesCount();
    }

    /**
    * Updates the accrual details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accrualDetails the accrual details
    * @return the accrual details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualDetails updateAccrualDetails(
        com.stpl.app.model.AccrualDetails accrualDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualDetailsLocalService.updateAccrualDetails(accrualDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _accrualDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _accrualDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _accrualDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AccrualDetailsLocalService getWrappedAccrualDetailsLocalService() {
        return _accrualDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAccrualDetailsLocalService(
        AccrualDetailsLocalService accrualDetailsLocalService) {
        _accrualDetailsLocalService = accrualDetailsLocalService;
    }

    @Override
    public AccrualDetailsLocalService getWrappedService() {
        return _accrualDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        AccrualDetailsLocalService accrualDetailsLocalService) {
        _accrualDetailsLocalService = accrualDetailsLocalService;
    }
}
