package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistCompanyGroupDetailsLocalService}.
 *
 * @author
 * @see HistCompanyGroupDetailsLocalService
 * @generated
 */
public class HistCompanyGroupDetailsLocalServiceWrapper
    implements HistCompanyGroupDetailsLocalService,
        ServiceWrapper<HistCompanyGroupDetailsLocalService> {
    private HistCompanyGroupDetailsLocalService _histCompanyGroupDetailsLocalService;

    public HistCompanyGroupDetailsLocalServiceWrapper(
        HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
        _histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
    }

    /**
    * Adds the hist company group details to the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetails the hist company group details
    * @return the hist company group details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails addHistCompanyGroupDetails(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.addHistCompanyGroupDetails(histCompanyGroupDetails);
    }

    /**
    * Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
    *
    * @param histCompanyGroupDetailsPK the primary key for the new hist company group details
    * @return the new hist company group details
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails createHistCompanyGroupDetails(
        com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
        return _histCompanyGroupDetailsLocalService.createHistCompanyGroupDetails(histCompanyGroupDetailsPK);
    }

    /**
    * Deletes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details that was removed
    * @throws PortalException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails deleteHistCompanyGroupDetails(
        com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.deleteHistCompanyGroupDetails(histCompanyGroupDetailsPK);
    }

    /**
    * Deletes the hist company group details from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetails the hist company group details
    * @return the hist company group details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails deleteHistCompanyGroupDetails(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.deleteHistCompanyGroupDetails(histCompanyGroupDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histCompanyGroupDetailsLocalService.dynamicQuery();
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
        return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _histCompanyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histCompanyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistCompanyGroupDetails fetchHistCompanyGroupDetails(
        com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.fetchHistCompanyGroupDetails(histCompanyGroupDetailsPK);
    }

    /**
    * Returns the hist company group details with the primary key.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details
    * @throws PortalException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails getHistCompanyGroupDetails(
        com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetails(histCompanyGroupDetailsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company group detailses
    * @param end the upper bound of the range of hist company group detailses (not inclusive)
    * @return the range of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistCompanyGroupDetails> getHistCompanyGroupDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetailses(start,
            end);
    }

    /**
    * Returns the number of hist company group detailses.
    *
    * @return the number of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistCompanyGroupDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetailsesCount();
    }

    /**
    * Updates the hist company group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetails the hist company group details
    * @return the hist company group details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroupDetails updateHistCompanyGroupDetails(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupDetailsLocalService.updateHistCompanyGroupDetails(histCompanyGroupDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histCompanyGroupDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histCompanyGroupDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histCompanyGroupDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistCompanyGroupDetailsLocalService getWrappedHistCompanyGroupDetailsLocalService() {
        return _histCompanyGroupDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistCompanyGroupDetailsLocalService(
        HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
        _histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
    }

    @Override
    public HistCompanyGroupDetailsLocalService getWrappedService() {
        return _histCompanyGroupDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
        _histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
    }
}
