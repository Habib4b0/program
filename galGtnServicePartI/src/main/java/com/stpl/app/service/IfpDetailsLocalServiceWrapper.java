package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IfpDetailsLocalService}.
 *
 * @author
 * @see IfpDetailsLocalService
 * @generated
 */
public class IfpDetailsLocalServiceWrapper implements IfpDetailsLocalService,
    ServiceWrapper<IfpDetailsLocalService> {
    private IfpDetailsLocalService _ifpDetailsLocalService;

    public IfpDetailsLocalServiceWrapper(
        IfpDetailsLocalService ifpDetailsLocalService) {
        _ifpDetailsLocalService = ifpDetailsLocalService;
    }

    /**
    * Adds the ifp details to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpDetails addIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.addIfpDetails(ifpDetails);
    }

    /**
    * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
    *
    * @param ifpDetailsSid the primary key for the new ifp details
    * @return the new ifp details
    */
    @Override
    public com.stpl.app.model.IfpDetails createIfpDetails(int ifpDetailsSid) {
        return _ifpDetailsLocalService.createIfpDetails(ifpDetailsSid);
    }

    /**
    * Deletes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details that was removed
    * @throws PortalException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpDetails deleteIfpDetails(int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.deleteIfpDetails(ifpDetailsSid);
    }

    /**
    * Deletes the ifp details from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpDetails deleteIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.deleteIfpDetails(ifpDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ifpDetailsLocalService.dynamicQuery();
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
        return _ifpDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ifpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ifpDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IfpDetails fetchIfpDetails(int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.fetchIfpDetails(ifpDetailsSid);
    }

    /**
    * Returns the ifp details with the primary key.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details
    * @throws PortalException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpDetails getIfpDetails(int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.getIfpDetails(ifpDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IfpDetails> getIfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.getIfpDetailses(start, end);
    }

    /**
    * Returns the number of ifp detailses.
    *
    * @return the number of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.getIfpDetailsesCount();
    }

    /**
    * Updates the ifp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpDetails updateIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.updateIfpDetails(ifpDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ifpDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ifpDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ifpDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpDetailsLocalService.findByItemFamilyPlanDetails(ifpModelSid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IfpDetailsLocalService getWrappedIfpDetailsLocalService() {
        return _ifpDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIfpDetailsLocalService(
        IfpDetailsLocalService ifpDetailsLocalService) {
        _ifpDetailsLocalService = ifpDetailsLocalService;
    }

    @Override
    public IfpDetailsLocalService getWrappedService() {
        return _ifpDetailsLocalService;
    }

    @Override
    public void setWrappedService(IfpDetailsLocalService ifpDetailsLocalService) {
        _ifpDetailsLocalService = ifpDetailsLocalService;
    }
}
