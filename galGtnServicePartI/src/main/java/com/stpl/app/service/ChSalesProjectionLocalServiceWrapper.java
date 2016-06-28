package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChSalesProjectionLocalService}.
 *
 * @author
 * @see ChSalesProjectionLocalService
 * @generated
 */
public class ChSalesProjectionLocalServiceWrapper
    implements ChSalesProjectionLocalService,
        ServiceWrapper<ChSalesProjectionLocalService> {
    private ChSalesProjectionLocalService _chSalesProjectionLocalService;

    public ChSalesProjectionLocalServiceWrapper(
        ChSalesProjectionLocalService chSalesProjectionLocalService) {
        _chSalesProjectionLocalService = chSalesProjectionLocalService;
    }

    /**
    * Adds the ch sales projection to the database. Also notifies the appropriate model listeners.
    *
    * @param chSalesProjection the ch sales projection
    * @return the ch sales projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChSalesProjection addChSalesProjection(
        com.stpl.app.model.ChSalesProjection chSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.addChSalesProjection(chSalesProjection);
    }

    /**
    * Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
    *
    * @param chSalesProjectionPK the primary key for the new ch sales projection
    * @return the new ch sales projection
    */
    @Override
    public com.stpl.app.model.ChSalesProjection createChSalesProjection(
        com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK) {
        return _chSalesProjectionLocalService.createChSalesProjection(chSalesProjectionPK);
    }

    /**
    * Deletes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chSalesProjectionPK the primary key of the ch sales projection
    * @return the ch sales projection that was removed
    * @throws PortalException if a ch sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChSalesProjection deleteChSalesProjection(
        com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.deleteChSalesProjection(chSalesProjectionPK);
    }

    /**
    * Deletes the ch sales projection from the database. Also notifies the appropriate model listeners.
    *
    * @param chSalesProjection the ch sales projection
    * @return the ch sales projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChSalesProjection deleteChSalesProjection(
        com.stpl.app.model.ChSalesProjection chSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.deleteChSalesProjection(chSalesProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chSalesProjectionLocalService.dynamicQuery();
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
        return _chSalesProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _chSalesProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chSalesProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChSalesProjection fetchChSalesProjection(
        com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.fetchChSalesProjection(chSalesProjectionPK);
    }

    /**
    * Returns the ch sales projection with the primary key.
    *
    * @param chSalesProjectionPK the primary key of the ch sales projection
    * @return the ch sales projection
    * @throws PortalException if a ch sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChSalesProjection getChSalesProjection(
        com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.getChSalesProjection(chSalesProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch sales projections
    * @param end the upper bound of the range of ch sales projections (not inclusive)
    * @return the range of ch sales projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChSalesProjection> getChSalesProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.getChSalesProjections(start, end);
    }

    /**
    * Returns the number of ch sales projections.
    *
    * @return the number of ch sales projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChSalesProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.getChSalesProjectionsCount();
    }

    /**
    * Updates the ch sales projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chSalesProjection the ch sales projection
    * @return the ch sales projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChSalesProjection updateChSalesProjection(
        com.stpl.app.model.ChSalesProjection chSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chSalesProjectionLocalService.updateChSalesProjection(chSalesProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chSalesProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chSalesProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chSalesProjectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChSalesProjectionLocalService getWrappedChSalesProjectionLocalService() {
        return _chSalesProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChSalesProjectionLocalService(
        ChSalesProjectionLocalService chSalesProjectionLocalService) {
        _chSalesProjectionLocalService = chSalesProjectionLocalService;
    }

    @Override
    public ChSalesProjectionLocalService getWrappedService() {
        return _chSalesProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        ChSalesProjectionLocalService chSalesProjectionLocalService) {
        _chSalesProjectionLocalService = chSalesProjectionLocalService;
    }
}
