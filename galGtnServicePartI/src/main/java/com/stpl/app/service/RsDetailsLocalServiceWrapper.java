package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RsDetailsLocalService}.
 *
 * @author
 * @see RsDetailsLocalService
 * @generated
 */
public class RsDetailsLocalServiceWrapper implements RsDetailsLocalService,
    ServiceWrapper<RsDetailsLocalService> {
    private RsDetailsLocalService _rsDetailsLocalService;

    public RsDetailsLocalServiceWrapper(
        RsDetailsLocalService rsDetailsLocalService) {
        _rsDetailsLocalService = rsDetailsLocalService;
    }

    /**
    * Adds the rs details to the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetails the rs details
    * @return the rs details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsDetails addRsDetails(
        com.stpl.app.model.RsDetails rsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.addRsDetails(rsDetails);
    }

    /**
    * Creates a new rs details with the primary key. Does not add the rs details to the database.
    *
    * @param rsDetailsSid the primary key for the new rs details
    * @return the new rs details
    */
    @Override
    public com.stpl.app.model.RsDetails createRsDetails(int rsDetailsSid) {
        return _rsDetailsLocalService.createRsDetails(rsDetailsSid);
    }

    /**
    * Deletes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details that was removed
    * @throws PortalException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsDetails deleteRsDetails(int rsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.deleteRsDetails(rsDetailsSid);
    }

    /**
    * Deletes the rs details from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetails the rs details
    * @return the rs details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsDetails deleteRsDetails(
        com.stpl.app.model.RsDetails rsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.deleteRsDetails(rsDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _rsDetailsLocalService.dynamicQuery();
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
        return _rsDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _rsDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _rsDetailsLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.RsDetails fetchRsDetails(int rsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.fetchRsDetails(rsDetailsSid);
    }

    /**
    * Returns the rs details with the primary key.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details
    * @throws PortalException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsDetails getRsDetails(int rsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.getRsDetails(rsDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs detailses
    * @param end the upper bound of the range of rs detailses (not inclusive)
    * @return the range of rs detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RsDetails> getRsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.getRsDetailses(start, end);
    }

    /**
    * Returns the number of rs detailses.
    *
    * @return the number of rs detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.getRsDetailsesCount();
    }

    /**
    * Updates the rs details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rsDetails the rs details
    * @return the rs details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsDetails updateRsDetails(
        com.stpl.app.model.RsDetails rsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsDetailsLocalService.updateRsDetails(rsDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rsDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rsDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rsDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RsDetailsLocalService getWrappedRsDetailsLocalService() {
        return _rsDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRsDetailsLocalService(
        RsDetailsLocalService rsDetailsLocalService) {
        _rsDetailsLocalService = rsDetailsLocalService;
    }

    @Override
    public RsDetailsLocalService getWrappedService() {
        return _rsDetailsLocalService;
    }

    @Override
    public void setWrappedService(RsDetailsLocalService rsDetailsLocalService) {
        _rsDetailsLocalService = rsDetailsLocalService;
    }
}
