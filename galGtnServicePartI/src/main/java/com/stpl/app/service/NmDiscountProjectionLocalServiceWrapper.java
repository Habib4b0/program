package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmDiscountProjectionLocalService}.
 *
 * @author
 * @see NmDiscountProjectionLocalService
 * @generated
 */
public class NmDiscountProjectionLocalServiceWrapper
    implements NmDiscountProjectionLocalService,
        ServiceWrapper<NmDiscountProjectionLocalService> {
    private NmDiscountProjectionLocalService _nmDiscountProjectionLocalService;

    public NmDiscountProjectionLocalServiceWrapper(
        NmDiscountProjectionLocalService nmDiscountProjectionLocalService) {
        _nmDiscountProjectionLocalService = nmDiscountProjectionLocalService;
    }

    /**
    * Adds the nm discount projection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjection the nm discount projection
    * @return the nm discount projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection addNmDiscountProjection(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.addNmDiscountProjection(nmDiscountProjection);
    }

    /**
    * Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
    *
    * @param nmDiscountProjectionPK the primary key for the new nm discount projection
    * @return the new nm discount projection
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection createNmDiscountProjection(
        com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK) {
        return _nmDiscountProjectionLocalService.createNmDiscountProjection(nmDiscountProjectionPK);
    }

    /**
    * Deletes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjectionPK the primary key of the nm discount projection
    * @return the nm discount projection that was removed
    * @throws PortalException if a nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection deleteNmDiscountProjection(
        com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.deleteNmDiscountProjection(nmDiscountProjectionPK);
    }

    /**
    * Deletes the nm discount projection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjection the nm discount projection
    * @return the nm discount projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection deleteNmDiscountProjection(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.deleteNmDiscountProjection(nmDiscountProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmDiscountProjectionLocalService.dynamicQuery();
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
        return _nmDiscountProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
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
        return _nmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmDiscountProjection fetchNmDiscountProjection(
        com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.fetchNmDiscountProjection(nmDiscountProjectionPK);
    }

    /**
    * Returns the nm discount projection with the primary key.
    *
    * @param nmDiscountProjectionPK the primary key of the nm discount projection
    * @return the nm discount projection
    * @throws PortalException if a nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection getNmDiscountProjection(
        com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.getNmDiscountProjection(nmDiscountProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm discount projections
    * @param end the upper bound of the range of nm discount projections (not inclusive)
    * @return the range of nm discount projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmDiscountProjection> getNmDiscountProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.getNmDiscountProjections(start,
            end);
    }

    /**
    * Returns the number of nm discount projections.
    *
    * @return the number of nm discount projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmDiscountProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.getNmDiscountProjectionsCount();
    }

    /**
    * Updates the nm discount projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjection the nm discount projection
    * @return the nm discount projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmDiscountProjection updateNmDiscountProjection(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmDiscountProjectionLocalService.updateNmDiscountProjection(nmDiscountProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmDiscountProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmDiscountProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmDiscountProjectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmDiscountProjectionLocalService getWrappedNmDiscountProjectionLocalService() {
        return _nmDiscountProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmDiscountProjectionLocalService(
        NmDiscountProjectionLocalService nmDiscountProjectionLocalService) {
        _nmDiscountProjectionLocalService = nmDiscountProjectionLocalService;
    }

    @Override
    public NmDiscountProjectionLocalService getWrappedService() {
        return _nmDiscountProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        NmDiscountProjectionLocalService nmDiscountProjectionLocalService) {
        _nmDiscountProjectionLocalService = nmDiscountProjectionLocalService;
    }
}
