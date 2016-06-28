package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistItemGroupLocalService}.
 *
 * @author
 * @see HistItemGroupLocalService
 * @generated
 */
public class HistItemGroupLocalServiceWrapper
    implements HistItemGroupLocalService,
        ServiceWrapper<HistItemGroupLocalService> {
    private HistItemGroupLocalService _histItemGroupLocalService;

    public HistItemGroupLocalServiceWrapper(
        HistItemGroupLocalService histItemGroupLocalService) {
        _histItemGroupLocalService = histItemGroupLocalService;
    }

    /**
    * Adds the hist item group to the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroup the hist item group
    * @return the hist item group that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroup addHistItemGroup(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.addHistItemGroup(histItemGroup);
    }

    /**
    * Creates a new hist item group with the primary key. Does not add the hist item group to the database.
    *
    * @param histItemGroupPK the primary key for the new hist item group
    * @return the new hist item group
    */
    @Override
    public com.stpl.app.model.HistItemGroup createHistItemGroup(
        com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK) {
        return _histItemGroupLocalService.createHistItemGroup(histItemGroupPK);
    }

    /**
    * Deletes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group that was removed
    * @throws PortalException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroup deleteHistItemGroup(
        com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.deleteHistItemGroup(histItemGroupPK);
    }

    /**
    * Deletes the hist item group from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroup the hist item group
    * @return the hist item group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroup deleteHistItemGroup(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.deleteHistItemGroup(histItemGroup);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histItemGroupLocalService.dynamicQuery();
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
        return _histItemGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histItemGroupLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histItemGroupLocalService.dynamicQuery(dynamicQuery, start,
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
        return _histItemGroupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histItemGroupLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistItemGroup fetchHistItemGroup(
        com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.fetchHistItemGroup(histItemGroupPK);
    }

    /**
    * Returns the hist item group with the primary key.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group
    * @throws PortalException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroup getHistItemGroup(
        com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.getHistItemGroup(histItemGroupPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item groups
    * @param end the upper bound of the range of hist item groups (not inclusive)
    * @return the range of hist item groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistItemGroup> getHistItemGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.getHistItemGroups(start, end);
    }

    /**
    * Returns the number of hist item groups.
    *
    * @return the number of hist item groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistItemGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.getHistItemGroupsCount();
    }

    /**
    * Updates the hist item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histItemGroup the hist item group
    * @return the hist item group that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistItemGroup updateHistItemGroup(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histItemGroupLocalService.updateHistItemGroup(histItemGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histItemGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histItemGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histItemGroupLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistItemGroupLocalService getWrappedHistItemGroupLocalService() {
        return _histItemGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistItemGroupLocalService(
        HistItemGroupLocalService histItemGroupLocalService) {
        _histItemGroupLocalService = histItemGroupLocalService;
    }

    @Override
    public HistItemGroupLocalService getWrappedService() {
        return _histItemGroupLocalService;
    }

    @Override
    public void setWrappedService(
        HistItemGroupLocalService histItemGroupLocalService) {
        _histItemGroupLocalService = histItemGroupLocalService;
    }
}
