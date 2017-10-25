package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SlaCalendarMasterLocalService}.
 *
 * @author
 * @see SlaCalendarMasterLocalService
 * @generated
 */
public class SlaCalendarMasterLocalServiceWrapper
    implements SlaCalendarMasterLocalService,
        ServiceWrapper<SlaCalendarMasterLocalService> {
    private SlaCalendarMasterLocalService _slaCalendarMasterLocalService;

    public SlaCalendarMasterLocalServiceWrapper(
        SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
        _slaCalendarMasterLocalService = slaCalendarMasterLocalService;
    }

    /**
    * Adds the sla calendar master to the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster addSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.addSlaCalendarMaster(slaCalendarMaster);
    }

    /**
    * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
    *
    * @param slaCalendarMasterSid the primary key for the new sla calendar master
    * @return the new sla calendar master
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster createSlaCalendarMaster(
        int slaCalendarMasterSid) {
        return _slaCalendarMasterLocalService.createSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Deletes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master that was removed
    * @throws PortalException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.deleteSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Deletes the sla calendar master from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.deleteSlaCalendarMaster(slaCalendarMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _slaCalendarMasterLocalService.dynamicQuery();
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
        return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _slaCalendarMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _slaCalendarMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster fetchSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.fetchSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Returns the sla calendar master with the primary key.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master
    * @throws PortalException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster getSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.getSlaCalendarMaster(slaCalendarMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @return the range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> getSlaCalendarMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.getSlaCalendarMasters(start, end);
    }

    /**
    * Returns the number of sla calendar masters.
    *
    * @return the number of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getSlaCalendarMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.getSlaCalendarMastersCount();
    }

    /**
    * Updates the sla calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarMaster updateSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarMasterLocalService.updateSlaCalendarMaster(slaCalendarMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _slaCalendarMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _slaCalendarMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _slaCalendarMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public SlaCalendarMasterLocalService getWrappedSlaCalendarMasterLocalService() {
        return _slaCalendarMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedSlaCalendarMasterLocalService(
        SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
        _slaCalendarMasterLocalService = slaCalendarMasterLocalService;
    }

    @Override
    public SlaCalendarMasterLocalService getWrappedService() {
        return _slaCalendarMasterLocalService;
    }

    @Override
    public void setWrappedService(
        SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
        _slaCalendarMasterLocalService = slaCalendarMasterLocalService;
    }
}
