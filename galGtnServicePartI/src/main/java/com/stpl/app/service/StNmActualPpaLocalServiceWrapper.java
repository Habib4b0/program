package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNmActualPpaLocalService}.
 *
 * @author
 * @see StNmActualPpaLocalService
 * @generated
 */
public class StNmActualPpaLocalServiceWrapper
    implements StNmActualPpaLocalService,
        ServiceWrapper<StNmActualPpaLocalService> {
    private StNmActualPpaLocalService _stNmActualPpaLocalService;

    public StNmActualPpaLocalServiceWrapper(
        StNmActualPpaLocalService stNmActualPpaLocalService) {
        _stNmActualPpaLocalService = stNmActualPpaLocalService;
    }

    /**
    * Adds the st nm actual ppa to the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpa the st nm actual ppa
    * @return the st nm actual ppa that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmActualPpa addStNmActualPpa(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.addStNmActualPpa(stNmActualPpa);
    }

    /**
    * Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
    *
    * @param stNmActualPpaPK the primary key for the new st nm actual ppa
    * @return the new st nm actual ppa
    */
    @Override
    public com.stpl.app.model.StNmActualPpa createStNmActualPpa(
        com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK) {
        return _stNmActualPpaLocalService.createStNmActualPpa(stNmActualPpaPK);
    }

    /**
    * Deletes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa that was removed
    * @throws PortalException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmActualPpa deleteStNmActualPpa(
        com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.deleteStNmActualPpa(stNmActualPpaPK);
    }

    /**
    * Deletes the st nm actual ppa from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpa the st nm actual ppa
    * @return the st nm actual ppa that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmActualPpa deleteStNmActualPpa(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.deleteStNmActualPpa(stNmActualPpa);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNmActualPpaLocalService.dynamicQuery();
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
        return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stNmActualPpaLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNmActualPpaLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNmActualPpa fetchStNmActualPpa(
        com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.fetchStNmActualPpa(stNmActualPpaPK);
    }

    /**
    * Returns the st nm actual ppa with the primary key.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa
    * @throws PortalException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmActualPpa getStNmActualPpa(
        com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.getStNmActualPpa(stNmActualPpaPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual ppas
    * @param end the upper bound of the range of st nm actual ppas (not inclusive)
    * @return the range of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNmActualPpa> getStNmActualPpas(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.getStNmActualPpas(start, end);
    }

    /**
    * Returns the number of st nm actual ppas.
    *
    * @return the number of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNmActualPpasCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.getStNmActualPpasCount();
    }

    /**
    * Updates the st nm actual ppa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpa the st nm actual ppa
    * @return the st nm actual ppa that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmActualPpa updateStNmActualPpa(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmActualPpaLocalService.updateStNmActualPpa(stNmActualPpa);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNmActualPpaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNmActualPpaLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNmActualPpaLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNmActualPpaLocalService getWrappedStNmActualPpaLocalService() {
        return _stNmActualPpaLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNmActualPpaLocalService(
        StNmActualPpaLocalService stNmActualPpaLocalService) {
        _stNmActualPpaLocalService = stNmActualPpaLocalService;
    }

    @Override
    public StNmActualPpaLocalService getWrappedService() {
        return _stNmActualPpaLocalService;
    }

    @Override
    public void setWrappedService(
        StNmActualPpaLocalService stNmActualPpaLocalService) {
        _stNmActualPpaLocalService = stNmActualPpaLocalService;
    }
}
