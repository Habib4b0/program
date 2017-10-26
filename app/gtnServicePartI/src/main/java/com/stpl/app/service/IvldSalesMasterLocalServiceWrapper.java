package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldSalesMasterLocalService}.
 *
 * @author
 * @see IvldSalesMasterLocalService
 * @generated
 */
public class IvldSalesMasterLocalServiceWrapper
    implements IvldSalesMasterLocalService,
        ServiceWrapper<IvldSalesMasterLocalService> {
    private IvldSalesMasterLocalService _ivldSalesMasterLocalService;

    public IvldSalesMasterLocalServiceWrapper(
        IvldSalesMasterLocalService ivldSalesMasterLocalService) {
        _ivldSalesMasterLocalService = ivldSalesMasterLocalService;
    }

    /**
    * Adds the ivld sales master to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldSalesMaster the ivld sales master
    * @return the ivld sales master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster addIvldSalesMaster(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.addIvldSalesMaster(ivldSalesMaster);
    }

    /**
    * Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
    *
    * @param ivldSalesMasterSid the primary key for the new ivld sales master
    * @return the new ivld sales master
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster createIvldSalesMaster(
        int ivldSalesMasterSid) {
        return _ivldSalesMasterLocalService.createIvldSalesMaster(ivldSalesMasterSid);
    }

    /**
    * Deletes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldSalesMasterSid the primary key of the ivld sales master
    * @return the ivld sales master that was removed
    * @throws PortalException if a ivld sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster deleteIvldSalesMaster(
        int ivldSalesMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.deleteIvldSalesMaster(ivldSalesMasterSid);
    }

    /**
    * Deletes the ivld sales master from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldSalesMaster the ivld sales master
    * @return the ivld sales master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster deleteIvldSalesMaster(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.deleteIvldSalesMaster(ivldSalesMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldSalesMasterLocalService.dynamicQuery();
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
        return _ivldSalesMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldSalesMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldSalesMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldSalesMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldSalesMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldSalesMaster fetchIvldSalesMaster(
        int ivldSalesMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.fetchIvldSalesMaster(ivldSalesMasterSid);
    }

    /**
    * Returns the ivld sales master with the primary key.
    *
    * @param ivldSalesMasterSid the primary key of the ivld sales master
    * @return the ivld sales master
    * @throws PortalException if a ivld sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster getIvldSalesMaster(
        int ivldSalesMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.getIvldSalesMaster(ivldSalesMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld sales masters
    * @param end the upper bound of the range of ivld sales masters (not inclusive)
    * @return the range of ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldSalesMaster> getIvldSalesMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.getIvldSalesMasters(start, end);
    }

    /**
    * Returns the number of ivld sales masters.
    *
    * @return the number of ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldSalesMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.getIvldSalesMastersCount();
    }

    /**
    * Updates the ivld sales master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldSalesMaster the ivld sales master
    * @return the ivld sales master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldSalesMaster updateIvldSalesMaster(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldSalesMasterLocalService.updateIvldSalesMaster(ivldSalesMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldSalesMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldSalesMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldSalesMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldSalesMasterLocalService getWrappedIvldSalesMasterLocalService() {
        return _ivldSalesMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldSalesMasterLocalService(
        IvldSalesMasterLocalService ivldSalesMasterLocalService) {
        _ivldSalesMasterLocalService = ivldSalesMasterLocalService;
    }

    @Override
    public IvldSalesMasterLocalService getWrappedService() {
        return _ivldSalesMasterLocalService;
    }

    @Override
    public void setWrappedService(
        IvldSalesMasterLocalService ivldSalesMasterLocalService) {
        _ivldSalesMasterLocalService = ivldSalesMasterLocalService;
    }
}
