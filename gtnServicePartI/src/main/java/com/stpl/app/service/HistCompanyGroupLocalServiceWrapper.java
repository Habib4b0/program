package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistCompanyGroupLocalService}.
 *
 * @author
 * @see HistCompanyGroupLocalService
 * @generated
 */
public class HistCompanyGroupLocalServiceWrapper
    implements HistCompanyGroupLocalService,
        ServiceWrapper<HistCompanyGroupLocalService> {
    private HistCompanyGroupLocalService _histCompanyGroupLocalService;

    public HistCompanyGroupLocalServiceWrapper(
        HistCompanyGroupLocalService histCompanyGroupLocalService) {
        _histCompanyGroupLocalService = histCompanyGroupLocalService;
    }

    /**
    * Adds the hist company group to the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup addHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.addHistCompanyGroup(histCompanyGroup);
    }

    /**
    * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
    *
    * @param histCompanyGroupPK the primary key for the new hist company group
    * @return the new hist company group
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup createHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK) {
        return _histCompanyGroupLocalService.createHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Deletes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group that was removed
    * @throws PortalException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup deleteHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.deleteHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Deletes the hist company group from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup deleteHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.deleteHistCompanyGroup(histCompanyGroup);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histCompanyGroupLocalService.dynamicQuery();
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
        return _histCompanyGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histCompanyGroupLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histCompanyGroupLocalService.dynamicQuery(dynamicQuery, start,
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
        return _histCompanyGroupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histCompanyGroupLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistCompanyGroup fetchHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.fetchHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Returns the hist company group with the primary key.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group
    * @throws PortalException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup getHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.getHistCompanyGroup(histCompanyGroupPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @return the range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistCompanyGroup> getHistCompanyGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.getHistCompanyGroups(start, end);
    }

    /**
    * Returns the number of hist company groups.
    *
    * @return the number of hist company groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistCompanyGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.getHistCompanyGroupsCount();
    }

    /**
    * Updates the hist company group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistCompanyGroup updateHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histCompanyGroupLocalService.updateHistCompanyGroup(histCompanyGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histCompanyGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histCompanyGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histCompanyGroupLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistCompanyGroupLocalService getWrappedHistCompanyGroupLocalService() {
        return _histCompanyGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistCompanyGroupLocalService(
        HistCompanyGroupLocalService histCompanyGroupLocalService) {
        _histCompanyGroupLocalService = histCompanyGroupLocalService;
    }

    @Override
    public HistCompanyGroupLocalService getWrappedService() {
        return _histCompanyGroupLocalService;
    }

    @Override
    public void setWrappedService(
        HistCompanyGroupLocalService histCompanyGroupLocalService) {
        _histCompanyGroupLocalService = histCompanyGroupLocalService;
    }
}
