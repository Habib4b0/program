package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNewNdcLocalService}.
 *
 * @author
 * @see StNewNdcLocalService
 * @generated
 */
public class StNewNdcLocalServiceWrapper implements StNewNdcLocalService,
    ServiceWrapper<StNewNdcLocalService> {
    private StNewNdcLocalService _stNewNdcLocalService;

    public StNewNdcLocalServiceWrapper(
        StNewNdcLocalService stNewNdcLocalService) {
        _stNewNdcLocalService = stNewNdcLocalService;
    }

    /**
    * Adds the st new ndc to the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNewNdc addStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.addStNewNdc(stNewNdc);
    }

    /**
    * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
    *
    * @param stNewNdcPK the primary key for the new st new ndc
    * @return the new st new ndc
    */
    @Override
    public com.stpl.app.model.StNewNdc createStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK) {
        return _stNewNdcLocalService.createStNewNdc(stNewNdcPK);
    }

    /**
    * Deletes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc that was removed
    * @throws PortalException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNewNdc deleteStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.deleteStNewNdc(stNewNdcPK);
    }

    /**
    * Deletes the st new ndc from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNewNdc deleteStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.deleteStNewNdc(stNewNdc);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNewNdcLocalService.dynamicQuery();
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
        return _stNewNdcLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNewNdcLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNewNdcLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _stNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNewNdcLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.StNewNdc fetchStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.fetchStNewNdc(stNewNdcPK);
    }

    /**
    * Returns the st new ndc with the primary key.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc
    * @throws PortalException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNewNdc getStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.getStNewNdc(stNewNdcPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @return the range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNewNdc> getStNewNdcs(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.getStNewNdcs(start, end);
    }

    /**
    * Returns the number of st new ndcs.
    *
    * @return the number of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNewNdcsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.getStNewNdcsCount();
    }

    /**
    * Updates the st new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNewNdc updateStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNewNdcLocalService.updateStNewNdc(stNewNdc);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNewNdcLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNewNdcLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNewNdcLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNewNdcLocalService getWrappedStNewNdcLocalService() {
        return _stNewNdcLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNewNdcLocalService(
        StNewNdcLocalService stNewNdcLocalService) {
        _stNewNdcLocalService = stNewNdcLocalService;
    }

    @Override
    public StNewNdcLocalService getWrappedService() {
        return _stNewNdcLocalService;
    }

    @Override
    public void setWrappedService(StNewNdcLocalService stNewNdcLocalService) {
        _stNewNdcLocalService = stNewNdcLocalService;
    }
}
