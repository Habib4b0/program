package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NationalAssumptionsLocalService}.
 *
 * @author
 * @see NationalAssumptionsLocalService
 * @generated
 */
public class NationalAssumptionsLocalServiceWrapper
    implements NationalAssumptionsLocalService,
        ServiceWrapper<NationalAssumptionsLocalService> {
    private NationalAssumptionsLocalService _nationalAssumptionsLocalService;

    public NationalAssumptionsLocalServiceWrapper(
        NationalAssumptionsLocalService nationalAssumptionsLocalService) {
        _nationalAssumptionsLocalService = nationalAssumptionsLocalService;
    }

    /**
    * Adds the national assumptions to the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptions the national assumptions
    * @return the national assumptions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptions addNationalAssumptions(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.addNationalAssumptions(nationalAssumptions);
    }

    /**
    * Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
    *
    * @param nationalAssumptionsPK the primary key for the new national assumptions
    * @return the new national assumptions
    */
    @Override
    public com.stpl.app.model.NationalAssumptions createNationalAssumptions(
        com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK) {
        return _nationalAssumptionsLocalService.createNationalAssumptions(nationalAssumptionsPK);
    }

    /**
    * Deletes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions that was removed
    * @throws PortalException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptions deleteNationalAssumptions(
        com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.deleteNationalAssumptions(nationalAssumptionsPK);
    }

    /**
    * Deletes the national assumptions from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptions the national assumptions
    * @return the national assumptions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptions deleteNationalAssumptions(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.deleteNationalAssumptions(nationalAssumptions);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nationalAssumptionsLocalService.dynamicQuery();
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
        return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
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
        return _nationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NationalAssumptions fetchNationalAssumptions(
        com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.fetchNationalAssumptions(nationalAssumptionsPK);
    }

    /**
    * Returns the national assumptions with the primary key.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions
    * @throws PortalException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptions getNationalAssumptions(
        com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.getNationalAssumptions(nationalAssumptionsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptionses
    * @param end the upper bound of the range of national assumptionses (not inclusive)
    * @return the range of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NationalAssumptions> getNationalAssumptionses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.getNationalAssumptionses(start,
            end);
    }

    /**
    * Returns the number of national assumptionses.
    *
    * @return the number of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNationalAssumptionsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.getNationalAssumptionsesCount();
    }

    /**
    * Updates the national assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptions the national assumptions
    * @return the national assumptions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NationalAssumptions updateNationalAssumptions(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nationalAssumptionsLocalService.updateNationalAssumptions(nationalAssumptions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nationalAssumptionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nationalAssumptionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nationalAssumptionsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NationalAssumptionsLocalService getWrappedNationalAssumptionsLocalService() {
        return _nationalAssumptionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNationalAssumptionsLocalService(
        NationalAssumptionsLocalService nationalAssumptionsLocalService) {
        _nationalAssumptionsLocalService = nationalAssumptionsLocalService;
    }

    @Override
    public NationalAssumptionsLocalService getWrappedService() {
        return _nationalAssumptionsLocalService;
    }

    @Override
    public void setWrappedService(
        NationalAssumptionsLocalService nationalAssumptionsLocalService) {
        _nationalAssumptionsLocalService = nationalAssumptionsLocalService;
    }
}
