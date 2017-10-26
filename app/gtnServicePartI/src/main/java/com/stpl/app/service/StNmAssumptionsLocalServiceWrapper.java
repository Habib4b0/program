package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNmAssumptionsLocalService}.
 *
 * @author
 * @see StNmAssumptionsLocalService
 * @generated
 */
public class StNmAssumptionsLocalServiceWrapper
    implements StNmAssumptionsLocalService,
        ServiceWrapper<StNmAssumptionsLocalService> {
    private StNmAssumptionsLocalService _stNmAssumptionsLocalService;

    public StNmAssumptionsLocalServiceWrapper(
        StNmAssumptionsLocalService stNmAssumptionsLocalService) {
        _stNmAssumptionsLocalService = stNmAssumptionsLocalService;
    }

    /**
    * Adds the st nm assumptions to the database. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptions the st nm assumptions
    * @return the st nm assumptions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmAssumptions addStNmAssumptions(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.addStNmAssumptions(stNmAssumptions);
    }

    /**
    * Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
    *
    * @param stNmAssumptionsPK the primary key for the new st nm assumptions
    * @return the new st nm assumptions
    */
    @Override
    public com.stpl.app.model.StNmAssumptions createStNmAssumptions(
        com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK) {
        return _stNmAssumptionsLocalService.createStNmAssumptions(stNmAssumptionsPK);
    }

    /**
    * Deletes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions that was removed
    * @throws PortalException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmAssumptions deleteStNmAssumptions(
        com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.deleteStNmAssumptions(stNmAssumptionsPK);
    }

    /**
    * Deletes the st nm assumptions from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptions the st nm assumptions
    * @return the st nm assumptions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmAssumptions deleteStNmAssumptions(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.deleteStNmAssumptions(stNmAssumptions);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNmAssumptionsLocalService.dynamicQuery();
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
        return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stNmAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNmAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNmAssumptions fetchStNmAssumptions(
        com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.fetchStNmAssumptions(stNmAssumptionsPK);
    }

    /**
    * Returns the st nm assumptions with the primary key.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions
    * @throws PortalException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmAssumptions getStNmAssumptions(
        com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.getStNmAssumptions(stNmAssumptionsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st nm assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm assumptionses
    * @param end the upper bound of the range of st nm assumptionses (not inclusive)
    * @return the range of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNmAssumptions> getStNmAssumptionses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.getStNmAssumptionses(start, end);
    }

    /**
    * Returns the number of st nm assumptionses.
    *
    * @return the number of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNmAssumptionsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.getStNmAssumptionsesCount();
    }

    /**
    * Updates the st nm assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptions the st nm assumptions
    * @return the st nm assumptions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmAssumptions updateStNmAssumptions(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmAssumptionsLocalService.updateStNmAssumptions(stNmAssumptions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNmAssumptionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNmAssumptionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNmAssumptionsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNmAssumptionsLocalService getWrappedStNmAssumptionsLocalService() {
        return _stNmAssumptionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNmAssumptionsLocalService(
        StNmAssumptionsLocalService stNmAssumptionsLocalService) {
        _stNmAssumptionsLocalService = stNmAssumptionsLocalService;
    }

    @Override
    public StNmAssumptionsLocalService getWrappedService() {
        return _stNmAssumptionsLocalService;
    }

    @Override
    public void setWrappedService(
        StNmAssumptionsLocalService stNmAssumptionsLocalService) {
        _stNmAssumptionsLocalService = stNmAssumptionsLocalService;
    }
}
