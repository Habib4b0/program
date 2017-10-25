package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StMedicaidNewNdcLocalService}.
 *
 * @author
 * @see StMedicaidNewNdcLocalService
 * @generated
 */
public class StMedicaidNewNdcLocalServiceWrapper
    implements StMedicaidNewNdcLocalService,
        ServiceWrapper<StMedicaidNewNdcLocalService> {
    private StMedicaidNewNdcLocalService _stMedicaidNewNdcLocalService;

    public StMedicaidNewNdcLocalServiceWrapper(
        StMedicaidNewNdcLocalService stMedicaidNewNdcLocalService) {
        _stMedicaidNewNdcLocalService = stMedicaidNewNdcLocalService;
    }

    /**
    * Adds the st medicaid new ndc to the database. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdc the st medicaid new ndc
    * @return the st medicaid new ndc that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc addStMedicaidNewNdc(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.addStMedicaidNewNdc(stMedicaidNewNdc);
    }

    /**
    * Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
    *
    * @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
    * @return the new st medicaid new ndc
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc createStMedicaidNewNdc(
        com.stpl.app.service.persistence.StMedicaidNewNdcPK stMedicaidNewNdcPK) {
        return _stMedicaidNewNdcLocalService.createStMedicaidNewNdc(stMedicaidNewNdcPK);
    }

    /**
    * Deletes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc that was removed
    * @throws PortalException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc deleteStMedicaidNewNdc(
        com.stpl.app.service.persistence.StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.deleteStMedicaidNewNdc(stMedicaidNewNdcPK);
    }

    /**
    * Deletes the st medicaid new ndc from the database. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdc the st medicaid new ndc
    * @return the st medicaid new ndc that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc deleteStMedicaidNewNdc(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.deleteStMedicaidNewNdc(stMedicaidNewNdc);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stMedicaidNewNdcLocalService.dynamicQuery();
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
        return _stMedicaidNewNdcLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stMedicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stMedicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stMedicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stMedicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StMedicaidNewNdc fetchStMedicaidNewNdc(
        com.stpl.app.service.persistence.StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.fetchStMedicaidNewNdc(stMedicaidNewNdcPK);
    }

    /**
    * Returns the st medicaid new ndc with the primary key.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc
    * @throws PortalException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc getStMedicaidNewNdc(
        com.stpl.app.service.persistence.StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.getStMedicaidNewNdc(stMedicaidNewNdcPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st medicaid new ndcs
    * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
    * @return the range of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StMedicaidNewNdc> getStMedicaidNewNdcs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.getStMedicaidNewNdcs(start, end);
    }

    /**
    * Returns the number of st medicaid new ndcs.
    *
    * @return the number of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStMedicaidNewNdcsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.getStMedicaidNewNdcsCount();
    }

    /**
    * Updates the st medicaid new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdc the st medicaid new ndc
    * @return the st medicaid new ndc that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMedicaidNewNdc updateStMedicaidNewNdc(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMedicaidNewNdcLocalService.updateStMedicaidNewNdc(stMedicaidNewNdc);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stMedicaidNewNdcLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stMedicaidNewNdcLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stMedicaidNewNdcLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StMedicaidNewNdcLocalService getWrappedStMedicaidNewNdcLocalService() {
        return _stMedicaidNewNdcLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStMedicaidNewNdcLocalService(
        StMedicaidNewNdcLocalService stMedicaidNewNdcLocalService) {
        _stMedicaidNewNdcLocalService = stMedicaidNewNdcLocalService;
    }

    @Override
    public StMedicaidNewNdcLocalService getWrappedService() {
        return _stMedicaidNewNdcLocalService;
    }

    @Override
    public void setWrappedService(
        StMedicaidNewNdcLocalService stMedicaidNewNdcLocalService) {
        _stMedicaidNewNdcLocalService = stMedicaidNewNdcLocalService;
    }
}
