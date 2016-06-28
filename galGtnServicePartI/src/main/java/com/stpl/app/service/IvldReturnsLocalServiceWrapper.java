package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldReturnsLocalService}.
 *
 * @author
 * @see IvldReturnsLocalService
 * @generated
 */
public class IvldReturnsLocalServiceWrapper implements IvldReturnsLocalService,
    ServiceWrapper<IvldReturnsLocalService> {
    private IvldReturnsLocalService _ivldReturnsLocalService;

    public IvldReturnsLocalServiceWrapper(
        IvldReturnsLocalService ivldReturnsLocalService) {
        _ivldReturnsLocalService = ivldReturnsLocalService;
    }

    /**
    * Adds the ivld returns to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturns the ivld returns
    * @return the ivld returns that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldReturns addIvldReturns(
        com.stpl.app.model.IvldReturns ivldReturns)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.addIvldReturns(ivldReturns);
    }

    /**
    * Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
    *
    * @param ivldReturnsSid the primary key for the new ivld returns
    * @return the new ivld returns
    */
    @Override
    public com.stpl.app.model.IvldReturns createIvldReturns(int ivldReturnsSid) {
        return _ivldReturnsLocalService.createIvldReturns(ivldReturnsSid);
    }

    /**
    * Deletes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns that was removed
    * @throws PortalException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldReturns deleteIvldReturns(int ivldReturnsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.deleteIvldReturns(ivldReturnsSid);
    }

    /**
    * Deletes the ivld returns from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturns the ivld returns
    * @return the ivld returns that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldReturns deleteIvldReturns(
        com.stpl.app.model.IvldReturns ivldReturns)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.deleteIvldReturns(ivldReturns);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldReturnsLocalService.dynamicQuery();
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
        return _ivldReturnsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldReturnsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldReturnsLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _ivldReturnsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldReturnsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldReturns fetchIvldReturns(int ivldReturnsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.fetchIvldReturns(ivldReturnsSid);
    }

    /**
    * Returns the ivld returns with the primary key.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns
    * @throws PortalException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldReturns getIvldReturns(int ivldReturnsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.getIvldReturns(ivldReturnsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld returnses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld returnses
    * @param end the upper bound of the range of ivld returnses (not inclusive)
    * @return the range of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldReturns> getIvldReturnses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.getIvldReturnses(start, end);
    }

    /**
    * Returns the number of ivld returnses.
    *
    * @return the number of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldReturnsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.getIvldReturnsesCount();
    }

    /**
    * Updates the ivld returns in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldReturns the ivld returns
    * @return the ivld returns that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldReturns updateIvldReturns(
        com.stpl.app.model.IvldReturns ivldReturns)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldReturnsLocalService.updateIvldReturns(ivldReturns);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldReturnsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldReturnsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldReturnsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldReturnsLocalService getWrappedIvldReturnsLocalService() {
        return _ivldReturnsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldReturnsLocalService(
        IvldReturnsLocalService ivldReturnsLocalService) {
        _ivldReturnsLocalService = ivldReturnsLocalService;
    }

    @Override
    public IvldReturnsLocalService getWrappedService() {
        return _ivldReturnsLocalService;
    }

    @Override
    public void setWrappedService(
        IvldReturnsLocalService ivldReturnsLocalService) {
        _ivldReturnsLocalService = ivldReturnsLocalService;
    }
}
