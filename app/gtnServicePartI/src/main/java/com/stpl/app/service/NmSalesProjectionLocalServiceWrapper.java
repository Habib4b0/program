package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmSalesProjectionLocalService}.
 *
 * @author
 * @see NmSalesProjectionLocalService
 * @generated
 */
public class NmSalesProjectionLocalServiceWrapper
    implements NmSalesProjectionLocalService,
        ServiceWrapper<NmSalesProjectionLocalService> {
    private NmSalesProjectionLocalService _nmSalesProjectionLocalService;

    public NmSalesProjectionLocalServiceWrapper(
        NmSalesProjectionLocalService nmSalesProjectionLocalService) {
        _nmSalesProjectionLocalService = nmSalesProjectionLocalService;
    }

    /**
    * Adds the nm sales projection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjection addNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.addNmSalesProjection(nmSalesProjection);
    }

    /**
    * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
    *
    * @param nmSalesProjectionPK the primary key for the new nm sales projection
    * @return the new nm sales projection
    */
    @Override
    public com.stpl.app.model.NmSalesProjection createNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK) {
        return _nmSalesProjectionLocalService.createNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Deletes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection that was removed
    * @throws PortalException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.deleteNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Deletes the nm sales projection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.deleteNmSalesProjection(nmSalesProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmSalesProjectionLocalService.dynamicQuery();
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
        return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _nmSalesProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmSalesProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmSalesProjection fetchNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.fetchNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Returns the nm sales projection with the primary key.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection
    * @throws PortalException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjection getNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.getNmSalesProjection(nmSalesProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @return the range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmSalesProjection> getNmSalesProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.getNmSalesProjections(start, end);
    }

    /**
    * Returns the number of nm sales projections.
    *
    * @return the number of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmSalesProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.getNmSalesProjectionsCount();
    }

    /**
    * Updates the nm sales projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjection updateNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionLocalService.updateNmSalesProjection(nmSalesProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmSalesProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmSalesProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmSalesProjectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List getSalesResult(java.lang.Object[] inputs) {
        return _nmSalesProjectionLocalService.getSalesResult(inputs);
    }

    @Override
    public java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName) {
        return _nmSalesProjectionLocalService.getAssumptionResult(input,
            queryName);
    }

    @Override
    public java.util.List getSalesProjectionResults(java.lang.Object[] inputs) {
        return _nmSalesProjectionLocalService.getSalesProjectionResults(inputs);
    }

    @Override
    public java.util.List getSalesProjectionResultLevels(
        java.lang.Object[] inputs) {
        return _nmSalesProjectionLocalService.getSalesProjectionResultLevels(inputs);
    }

    @Override
    public java.util.List getVarianceSales(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.lang.String year, int levelNo, java.lang.String sales) {
        return _nmSalesProjectionLocalService.getVarianceSales(projectionId,
            frequency, startAndEndPeriods, actualsOrProjections, parentName,
            year, levelNo, sales);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmSalesProjectionLocalService getWrappedNmSalesProjectionLocalService() {
        return _nmSalesProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmSalesProjectionLocalService(
        NmSalesProjectionLocalService nmSalesProjectionLocalService) {
        _nmSalesProjectionLocalService = nmSalesProjectionLocalService;
    }

    @Override
    public NmSalesProjectionLocalService getWrappedService() {
        return _nmSalesProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        NmSalesProjectionLocalService nmSalesProjectionLocalService) {
        _nmSalesProjectionLocalService = nmSalesProjectionLocalService;
    }
}
