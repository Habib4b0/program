package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNationalAssumptionsLocalService}.
 *
 * @author
 * @see StNationalAssumptionsLocalService
 * @generated
 */
public class StNationalAssumptionsLocalServiceWrapper
    implements StNationalAssumptionsLocalService,
        ServiceWrapper<StNationalAssumptionsLocalService> {
    private StNationalAssumptionsLocalService _stNationalAssumptionsLocalService;

    public StNationalAssumptionsLocalServiceWrapper(
        StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
        _stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
    }

    /**
    * Adds the st national assumptions to the database. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptions the st national assumptions
    * @return the st national assumptions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions addStNationalAssumptions(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.addStNationalAssumptions(stNationalAssumptions);
    }

    /**
    * Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
    *
    * @param stNationalAssumptionsPK the primary key for the new st national assumptions
    * @return the new st national assumptions
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions createStNationalAssumptions(
        com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK) {
        return _stNationalAssumptionsLocalService.createStNationalAssumptions(stNationalAssumptionsPK);
    }

    /**
    * Deletes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions that was removed
    * @throws PortalException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions deleteStNationalAssumptions(
        com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.deleteStNationalAssumptions(stNationalAssumptionsPK);
    }

    /**
    * Deletes the st national assumptions from the database. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptions the st national assumptions
    * @return the st national assumptions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions deleteStNationalAssumptions(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.deleteStNationalAssumptions(stNationalAssumptions);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNationalAssumptionsLocalService.dynamicQuery();
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
        return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
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
        return _stNationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNationalAssumptions fetchStNationalAssumptions(
        com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.fetchStNationalAssumptions(stNationalAssumptionsPK);
    }

    /**
    * Returns the st national assumptions with the primary key.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions
    * @throws PortalException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions getStNationalAssumptions(
        com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.getStNationalAssumptions(stNationalAssumptionsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st national assumptionses
    * @param end the upper bound of the range of st national assumptionses (not inclusive)
    * @return the range of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNationalAssumptions> getStNationalAssumptionses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.getStNationalAssumptionses(start,
            end);
    }

    /**
    * Returns the number of st national assumptionses.
    *
    * @return the number of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNationalAssumptionsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.getStNationalAssumptionsesCount();
    }

    /**
    * Updates the st national assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptions the st national assumptions
    * @return the st national assumptions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNationalAssumptions updateStNationalAssumptions(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNationalAssumptionsLocalService.updateStNationalAssumptions(stNationalAssumptions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNationalAssumptionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNationalAssumptionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNationalAssumptionsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNationalAssumptionsLocalService getWrappedStNationalAssumptionsLocalService() {
        return _stNationalAssumptionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNationalAssumptionsLocalService(
        StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
        _stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
    }

    @Override
    public StNationalAssumptionsLocalService getWrappedService() {
        return _stNationalAssumptionsLocalService;
    }

    @Override
    public void setWrappedService(
        StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
        _stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
    }
}
