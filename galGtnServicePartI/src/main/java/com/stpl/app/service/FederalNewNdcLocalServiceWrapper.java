package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FederalNewNdcLocalService}.
 *
 * @author
 * @see FederalNewNdcLocalService
 * @generated
 */
public class FederalNewNdcLocalServiceWrapper
    implements FederalNewNdcLocalService,
        ServiceWrapper<FederalNewNdcLocalService> {
    private FederalNewNdcLocalService _federalNewNdcLocalService;

    public FederalNewNdcLocalServiceWrapper(
        FederalNewNdcLocalService federalNewNdcLocalService) {
        _federalNewNdcLocalService = federalNewNdcLocalService;
    }

    /**
    * Adds the federal new ndc to the database. Also notifies the appropriate model listeners.
    *
    * @param federalNewNdc the federal new ndc
    * @return the federal new ndc that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FederalNewNdc addFederalNewNdc(
        com.stpl.app.model.FederalNewNdc federalNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.addFederalNewNdc(federalNewNdc);
    }

    /**
    * Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
    *
    * @param itemMasterSid the primary key for the new federal new ndc
    * @return the new federal new ndc
    */
    @Override
    public com.stpl.app.model.FederalNewNdc createFederalNewNdc(
        int itemMasterSid) {
        return _federalNewNdcLocalService.createFederalNewNdc(itemMasterSid);
    }

    /**
    * Deletes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the federal new ndc
    * @return the federal new ndc that was removed
    * @throws PortalException if a federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FederalNewNdc deleteFederalNewNdc(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.deleteFederalNewNdc(itemMasterSid);
    }

    /**
    * Deletes the federal new ndc from the database. Also notifies the appropriate model listeners.
    *
    * @param federalNewNdc the federal new ndc
    * @return the federal new ndc that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FederalNewNdc deleteFederalNewNdc(
        com.stpl.app.model.FederalNewNdc federalNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.deleteFederalNewNdc(federalNewNdc);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _federalNewNdcLocalService.dynamicQuery();
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
        return _federalNewNdcLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _federalNewNdcLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _federalNewNdcLocalService.dynamicQuery(dynamicQuery, start,
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
        return _federalNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
        return _federalNewNdcLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.FederalNewNdc fetchFederalNewNdc(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.fetchFederalNewNdc(itemMasterSid);
    }

    /**
    * Returns the federal new ndc with the primary key.
    *
    * @param itemMasterSid the primary key of the federal new ndc
    * @return the federal new ndc
    * @throws PortalException if a federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FederalNewNdc getFederalNewNdc(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.getFederalNewNdc(itemMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the federal new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of federal new ndcs
    * @param end the upper bound of the range of federal new ndcs (not inclusive)
    * @return the range of federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.FederalNewNdc> getFederalNewNdcs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.getFederalNewNdcs(start, end);
    }

    /**
    * Returns the number of federal new ndcs.
    *
    * @return the number of federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getFederalNewNdcsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.getFederalNewNdcsCount();
    }

    /**
    * Updates the federal new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param federalNewNdc the federal new ndc
    * @return the federal new ndc that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FederalNewNdc updateFederalNewNdc(
        com.stpl.app.model.FederalNewNdc federalNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _federalNewNdcLocalService.updateFederalNewNdc(federalNewNdc);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _federalNewNdcLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _federalNewNdcLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _federalNewNdcLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FederalNewNdcLocalService getWrappedFederalNewNdcLocalService() {
        return _federalNewNdcLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFederalNewNdcLocalService(
        FederalNewNdcLocalService federalNewNdcLocalService) {
        _federalNewNdcLocalService = federalNewNdcLocalService;
    }

    @Override
    public FederalNewNdcLocalService getWrappedService() {
        return _federalNewNdcLocalService;
    }

    @Override
    public void setWrappedService(
        FederalNewNdcLocalService federalNewNdcLocalService) {
        _federalNewNdcLocalService = federalNewNdcLocalService;
    }
}
