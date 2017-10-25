package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmPpaProjectionLocalService}.
 *
 * @author
 * @see NmPpaProjectionLocalService
 * @generated
 */
public class NmPpaProjectionLocalServiceWrapper
    implements NmPpaProjectionLocalService,
        ServiceWrapper<NmPpaProjectionLocalService> {
    private NmPpaProjectionLocalService _nmPpaProjectionLocalService;

    public NmPpaProjectionLocalServiceWrapper(
        NmPpaProjectionLocalService nmPpaProjectionLocalService) {
        _nmPpaProjectionLocalService = nmPpaProjectionLocalService;
    }

    /**
    * Adds the nm ppa projection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjection the nm ppa projection
    * @return the nm ppa projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjection addNmPpaProjection(
        com.stpl.app.model.NmPpaProjection nmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.addNmPpaProjection(nmPpaProjection);
    }

    /**
    * Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
    *
    * @param nmPpaProjectionPK the primary key for the new nm ppa projection
    * @return the new nm ppa projection
    */
    @Override
    public com.stpl.app.model.NmPpaProjection createNmPpaProjection(
        com.stpl.app.service.persistence.NmPpaProjectionPK nmPpaProjectionPK) {
        return _nmPpaProjectionLocalService.createNmPpaProjection(nmPpaProjectionPK);
    }

    /**
    * Deletes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionPK the primary key of the nm ppa projection
    * @return the nm ppa projection that was removed
    * @throws PortalException if a nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjection deleteNmPpaProjection(
        com.stpl.app.service.persistence.NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.deleteNmPpaProjection(nmPpaProjectionPK);
    }

    /**
    * Deletes the nm ppa projection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjection the nm ppa projection
    * @return the nm ppa projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjection deleteNmPpaProjection(
        com.stpl.app.model.NmPpaProjection nmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.deleteNmPpaProjection(nmPpaProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmPpaProjectionLocalService.dynamicQuery();
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
        return _nmPpaProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _nmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmPpaProjection fetchNmPpaProjection(
        com.stpl.app.service.persistence.NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.fetchNmPpaProjection(nmPpaProjectionPK);
    }

    /**
    * Returns the nm ppa projection with the primary key.
    *
    * @param nmPpaProjectionPK the primary key of the nm ppa projection
    * @return the nm ppa projection
    * @throws PortalException if a nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjection getNmPpaProjection(
        com.stpl.app.service.persistence.NmPpaProjectionPK nmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.getNmPpaProjection(nmPpaProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projections
    * @param end the upper bound of the range of nm ppa projections (not inclusive)
    * @return the range of nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmPpaProjection> getNmPpaProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.getNmPpaProjections(start, end);
    }

    /**
    * Returns the number of nm ppa projections.
    *
    * @return the number of nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmPpaProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.getNmPpaProjectionsCount();
    }

    /**
    * Updates the nm ppa projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjection the nm ppa projection
    * @return the nm ppa projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjection updateNmPpaProjection(
        com.stpl.app.model.NmPpaProjection nmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionLocalService.updateNmPpaProjection(nmPpaProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmPpaProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmPpaProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmPpaProjectionLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmPpaProjectionLocalService getWrappedNmPpaProjectionLocalService() {
        return _nmPpaProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmPpaProjectionLocalService(
        NmPpaProjectionLocalService nmPpaProjectionLocalService) {
        _nmPpaProjectionLocalService = nmPpaProjectionLocalService;
    }

    @Override
    public NmPpaProjectionLocalService getWrappedService() {
        return _nmPpaProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        NmPpaProjectionLocalService nmPpaProjectionLocalService) {
        _nmPpaProjectionLocalService = nmPpaProjectionLocalService;
    }
}
