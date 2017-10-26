package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UdcsLocalService}.
 *
 * @author
 * @see UdcsLocalService
 * @generated
 */
public class UdcsLocalServiceWrapper implements UdcsLocalService,
    ServiceWrapper<UdcsLocalService> {
    private UdcsLocalService _udcsLocalService;

    public UdcsLocalServiceWrapper(UdcsLocalService udcsLocalService) {
        _udcsLocalService = udcsLocalService;
    }

    /**
    * Adds the udcs to the database. Also notifies the appropriate model listeners.
    *
    * @param udcs the udcs
    * @return the udcs that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.Udcs addUdcs(com.stpl.app.model.Udcs udcs)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.addUdcs(udcs);
    }

    /**
    * Creates a new udcs with the primary key. Does not add the udcs to the database.
    *
    * @param udcsSid the primary key for the new udcs
    * @return the new udcs
    */
    @Override
    public com.stpl.app.model.Udcs createUdcs(int udcsSid) {
        return _udcsLocalService.createUdcs(udcsSid);
    }

    /**
    * Deletes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs that was removed
    * @throws PortalException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.Udcs deleteUdcs(int udcsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.deleteUdcs(udcsSid);
    }

    /**
    * Deletes the udcs from the database. Also notifies the appropriate model listeners.
    *
    * @param udcs the udcs
    * @return the udcs that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.Udcs deleteUdcs(com.stpl.app.model.Udcs udcs)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.deleteUdcs(udcs);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _udcsLocalService.dynamicQuery();
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
        return _udcsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _udcsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _udcsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _udcsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _udcsLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.Udcs fetchUdcs(int udcsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.fetchUdcs(udcsSid);
    }

    /**
    * Returns the udcs with the primary key.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs
    * @throws PortalException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.Udcs getUdcs(int udcsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.getUdcs(udcsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the udcses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of udcses
    * @param end the upper bound of the range of udcses (not inclusive)
    * @return the range of udcses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.Udcs> getUdcses(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.getUdcses(start, end);
    }

    /**
    * Returns the number of udcses.
    *
    * @return the number of udcses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getUdcsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.getUdcsesCount();
    }

    /**
    * Updates the udcs in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param udcs the udcs
    * @return the udcs that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.Udcs updateUdcs(com.stpl.app.model.Udcs udcs)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _udcsLocalService.updateUdcs(udcs);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _udcsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _udcsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _udcsLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public UdcsLocalService getWrappedUdcsLocalService() {
        return _udcsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUdcsLocalService(UdcsLocalService udcsLocalService) {
        _udcsLocalService = udcsLocalService;
    }

    @Override
    public UdcsLocalService getWrappedService() {
        return _udcsLocalService;
    }

    @Override
    public void setWrappedService(UdcsLocalService udcsLocalService) {
        _udcsLocalService = udcsLocalService;
    }
}
