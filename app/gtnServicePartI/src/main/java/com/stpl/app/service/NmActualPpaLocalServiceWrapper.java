package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmActualPpaLocalService}.
 *
 * @author
 * @see NmActualPpaLocalService
 * @generated
 */
public class NmActualPpaLocalServiceWrapper implements NmActualPpaLocalService,
    ServiceWrapper<NmActualPpaLocalService> {
    private NmActualPpaLocalService _nmActualPpaLocalService;

    public NmActualPpaLocalServiceWrapper(
        NmActualPpaLocalService nmActualPpaLocalService) {
        _nmActualPpaLocalService = nmActualPpaLocalService;
    }

    /**
    * Adds the nm actual ppa to the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpa the nm actual ppa
    * @return the nm actual ppa that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualPpa addNmActualPpa(
        com.stpl.app.model.NmActualPpa nmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.addNmActualPpa(nmActualPpa);
    }

    /**
    * Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
    *
    * @param nmActualPpaPK the primary key for the new nm actual ppa
    * @return the new nm actual ppa
    */
    @Override
    public com.stpl.app.model.NmActualPpa createNmActualPpa(
        com.stpl.app.service.persistence.NmActualPpaPK nmActualPpaPK) {
        return _nmActualPpaLocalService.createNmActualPpa(nmActualPpaPK);
    }

    /**
    * Deletes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa that was removed
    * @throws PortalException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualPpa deleteNmActualPpa(
        com.stpl.app.service.persistence.NmActualPpaPK nmActualPpaPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.deleteNmActualPpa(nmActualPpaPK);
    }

    /**
    * Deletes the nm actual ppa from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpa the nm actual ppa
    * @return the nm actual ppa that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualPpa deleteNmActualPpa(
        com.stpl.app.model.NmActualPpa nmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.deleteNmActualPpa(nmActualPpa);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmActualPpaLocalService.dynamicQuery();
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
        return _nmActualPpaLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmActualPpaLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmActualPpaLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _nmActualPpaLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmActualPpaLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmActualPpa fetchNmActualPpa(
        com.stpl.app.service.persistence.NmActualPpaPK nmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.fetchNmActualPpa(nmActualPpaPK);
    }

    /**
    * Returns the nm actual ppa with the primary key.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa
    * @throws PortalException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualPpa getNmActualPpa(
        com.stpl.app.service.persistence.NmActualPpaPK nmActualPpaPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.getNmActualPpa(nmActualPpaPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual ppas
    * @param end the upper bound of the range of nm actual ppas (not inclusive)
    * @return the range of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmActualPpa> getNmActualPpas(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.getNmActualPpas(start, end);
    }

    /**
    * Returns the number of nm actual ppas.
    *
    * @return the number of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmActualPpasCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.getNmActualPpasCount();
    }

    /**
    * Updates the nm actual ppa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpa the nm actual ppa
    * @return the nm actual ppa that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualPpa updateNmActualPpa(
        com.stpl.app.model.NmActualPpa nmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualPpaLocalService.updateNmActualPpa(nmActualPpa);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmActualPpaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmActualPpaLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmActualPpaLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmActualPpaLocalService getWrappedNmActualPpaLocalService() {
        return _nmActualPpaLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmActualPpaLocalService(
        NmActualPpaLocalService nmActualPpaLocalService) {
        _nmActualPpaLocalService = nmActualPpaLocalService;
    }

    @Override
    public NmActualPpaLocalService getWrappedService() {
        return _nmActualPpaLocalService;
    }

    @Override
    public void setWrappedService(
        NmActualPpaLocalService nmActualPpaLocalService) {
        _nmActualPpaLocalService = nmActualPpaLocalService;
    }
}
