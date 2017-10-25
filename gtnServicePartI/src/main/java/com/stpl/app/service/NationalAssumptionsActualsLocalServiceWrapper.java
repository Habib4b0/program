package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NationalAssumptionsActualsLocalService}.
 *
 * @author
 * @see NationalAssumptionsActualsLocalService
 * @generated
 */
public class NationalAssumptionsActualsLocalServiceWrapper
    implements NationalAssumptionsActualsLocalService,
        ServiceWrapper<NationalAssumptionsActualsLocalService> {
    private NationalAssumptionsActualsLocalService _nationalAssumptionsActualsLocalService;

    public NationalAssumptionsActualsLocalServiceWrapper(
        NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
        _nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
    }

    /**
    * Adds the national assumptions actuals to the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActuals the national assumptions actuals
    * @return the national assumptions actuals that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals addNationalAssumptionsActuals(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.addNationalAssumptionsActuals(nationalAssumptionsActuals);
    }

    /**
    * Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
    *
    * @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
    * @return the new national assumptions actuals
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals createNationalAssumptionsActuals(
        com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
        return _nationalAssumptionsActualsLocalService.createNationalAssumptionsActuals(nationalAssumptionsActualsPK);
    }

    /**
    * Deletes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals that was removed
    * @throws PortalException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
        com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.deleteNationalAssumptionsActuals(nationalAssumptionsActualsPK);
    }

    /**
    * Deletes the national assumptions actuals from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActuals the national assumptions actuals
    * @return the national assumptions actuals that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.deleteNationalAssumptionsActuals(nationalAssumptionsActuals);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nationalAssumptionsActualsLocalService.dynamicQuery();
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
        return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery,
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
        return _nationalAssumptionsActualsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nationalAssumptionsActualsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NationalAssumptionsActuals fetchNationalAssumptionsActuals(
        com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.fetchNationalAssumptionsActuals(nationalAssumptionsActualsPK);
    }

    /**
    * Returns the national assumptions actuals with the primary key.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals
    * @throws PortalException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals getNationalAssumptionsActuals(
        com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActuals(nationalAssumptionsActualsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the national assumptions actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions actualses
    * @param end the upper bound of the range of national assumptions actualses (not inclusive)
    * @return the range of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NationalAssumptionsActuals> getNationalAssumptionsActualses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActualses(start,
            end);
    }

    /**
    * Returns the number of national assumptions actualses.
    *
    * @return the number of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNationalAssumptionsActualsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActualsesCount();
    }

    /**
    * Updates the national assumptions actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActuals the national assumptions actuals
    * @return the national assumptions actuals that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptionsActuals updateNationalAssumptionsActuals(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsActualsLocalService.updateNationalAssumptionsActuals(nationalAssumptionsActuals);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nationalAssumptionsActualsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nationalAssumptionsActualsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nationalAssumptionsActualsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NationalAssumptionsActualsLocalService getWrappedNationalAssumptionsActualsLocalService() {
        return _nationalAssumptionsActualsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNationalAssumptionsActualsLocalService(
        NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
        _nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
    }

    @Override
    public NationalAssumptionsActualsLocalService getWrappedService() {
        return _nationalAssumptionsActualsLocalService;
    }

    @Override
    public void setWrappedService(
        NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
        _nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
    }
}
