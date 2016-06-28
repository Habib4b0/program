package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistItemGroupDetailsLocalService}.
 *
 * @author
 * @see HistItemGroupDetailsLocalService
 * @generated
 */
public class HistItemGroupDetailsLocalServiceWrapper
    implements HistItemGroupDetailsLocalService,
        ServiceWrapper<HistItemGroupDetailsLocalService> {
    private HistItemGroupDetailsLocalService _histItemGroupDetailsLocalService;

    public HistItemGroupDetailsLocalServiceWrapper(
        HistItemGroupDetailsLocalService histItemGroupDetailsLocalService) {
        _histItemGroupDetailsLocalService = histItemGroupDetailsLocalService;
    }

    /**
    * Adds the hist item group details to the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetails the hist item group details
    * @return the hist item group details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails addHistItemGroupDetails(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.addHistItemGroupDetails(histItemGroupDetails);
    }

    /**
    * Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
    *
    * @param histItemGroupDetailsPK the primary key for the new hist item group details
    * @return the new hist item group details
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails createHistItemGroupDetails(
        com.stpl.app.service.persistence.HistItemGroupDetailsPK histItemGroupDetailsPK) {
        return _histItemGroupDetailsLocalService.createHistItemGroupDetails(histItemGroupDetailsPK);
    }

    /**
    * Deletes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details that was removed
    * @throws PortalException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails deleteHistItemGroupDetails(
        com.stpl.app.service.persistence.HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.deleteHistItemGroupDetails(histItemGroupDetailsPK);
    }

    /**
    * Deletes the hist item group details from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetails the hist item group details
    * @return the hist item group details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails deleteHistItemGroupDetails(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.deleteHistItemGroupDetails(histItemGroupDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histItemGroupDetailsLocalService.dynamicQuery();
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
        return _histItemGroupDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histItemGroupDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histItemGroupDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _histItemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histItemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistItemGroupDetails fetchHistItemGroupDetails(
        com.stpl.app.service.persistence.HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.fetchHistItemGroupDetails(histItemGroupDetailsPK);
    }

    /**
    * Returns the hist item group details with the primary key.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details
    * @throws PortalException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails getHistItemGroupDetails(
        com.stpl.app.service.persistence.HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.getHistItemGroupDetails(histItemGroupDetailsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item group detailses
    * @param end the upper bound of the range of hist item group detailses (not inclusive)
    * @return the range of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistItemGroupDetails> getHistItemGroupDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.getHistItemGroupDetailses(start,
            end);
    }

    /**
    * Returns the number of hist item group detailses.
    *
    * @return the number of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistItemGroupDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.getHistItemGroupDetailsesCount();
    }

    /**
    * Updates the hist item group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetails the hist item group details
    * @return the hist item group details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroupDetails updateHistItemGroupDetails(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupDetailsLocalService.updateHistItemGroupDetails(histItemGroupDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histItemGroupDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histItemGroupDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histItemGroupDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistItemGroupDetailsLocalService getWrappedHistItemGroupDetailsLocalService() {
        return _histItemGroupDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistItemGroupDetailsLocalService(
        HistItemGroupDetailsLocalService histItemGroupDetailsLocalService) {
        _histItemGroupDetailsLocalService = histItemGroupDetailsLocalService;
    }

    @Override
    public HistItemGroupDetailsLocalService getWrappedService() {
        return _histItemGroupDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        HistItemGroupDetailsLocalService histItemGroupDetailsLocalService) {
        _histItemGroupDetailsLocalService = histItemGroupDetailsLocalService;
    }
}
