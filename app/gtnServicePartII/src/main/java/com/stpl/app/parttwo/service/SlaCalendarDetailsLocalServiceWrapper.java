package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SlaCalendarDetailsLocalService}.
 *
 * @author
 * @see SlaCalendarDetailsLocalService
 * @generated
 */
public class SlaCalendarDetailsLocalServiceWrapper
    implements SlaCalendarDetailsLocalService,
        ServiceWrapper<SlaCalendarDetailsLocalService> {
    private SlaCalendarDetailsLocalService _slaCalendarDetailsLocalService;

    public SlaCalendarDetailsLocalServiceWrapper(
        SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
        _slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
    }

    /**
    * Adds the sla calendar details to the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetails the sla calendar details
    * @return the sla calendar details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails addSlaCalendarDetails(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.addSlaCalendarDetails(slaCalendarDetails);
    }

    /**
    * Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
    *
    * @param slaCalendarDetailsSid the primary key for the new sla calendar details
    * @return the new sla calendar details
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails createSlaCalendarDetails(
        int slaCalendarDetailsSid) {
        return _slaCalendarDetailsLocalService.createSlaCalendarDetails(slaCalendarDetailsSid);
    }

    /**
    * Deletes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details that was removed
    * @throws PortalException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails deleteSlaCalendarDetails(
        int slaCalendarDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.deleteSlaCalendarDetails(slaCalendarDetailsSid);
    }

    /**
    * Deletes the sla calendar details from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetails the sla calendar details
    * @return the sla calendar details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails deleteSlaCalendarDetails(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.deleteSlaCalendarDetails(slaCalendarDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _slaCalendarDetailsLocalService.dynamicQuery();
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
        return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _slaCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _slaCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails fetchSlaCalendarDetails(
        int slaCalendarDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.fetchSlaCalendarDetails(slaCalendarDetailsSid);
    }

    /**
    * Returns the sla calendar details with the primary key.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details
    * @throws PortalException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails getSlaCalendarDetails(
        int slaCalendarDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.getSlaCalendarDetails(slaCalendarDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the sla calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar detailses
    * @param end the upper bound of the range of sla calendar detailses (not inclusive)
    * @return the range of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> getSlaCalendarDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.getSlaCalendarDetailses(start,
            end);
    }

    /**
    * Returns the number of sla calendar detailses.
    *
    * @return the number of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getSlaCalendarDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.getSlaCalendarDetailsesCount();
    }

    /**
    * Updates the sla calendar details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetails the sla calendar details
    * @return the sla calendar details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.SlaCalendarDetails updateSlaCalendarDetails(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _slaCalendarDetailsLocalService.updateSlaCalendarDetails(slaCalendarDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _slaCalendarDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _slaCalendarDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _slaCalendarDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public SlaCalendarDetailsLocalService getWrappedSlaCalendarDetailsLocalService() {
        return _slaCalendarDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedSlaCalendarDetailsLocalService(
        SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
        _slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
    }

    @Override
    public SlaCalendarDetailsLocalService getWrappedService() {
        return _slaCalendarDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
        _slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
    }
}
