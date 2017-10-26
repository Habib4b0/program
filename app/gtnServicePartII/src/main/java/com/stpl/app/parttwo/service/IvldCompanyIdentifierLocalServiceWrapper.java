package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCompanyIdentifierLocalService}.
 *
 * @author
 * @see IvldCompanyIdentifierLocalService
 * @generated
 */
public class IvldCompanyIdentifierLocalServiceWrapper
    implements IvldCompanyIdentifierLocalService,
        ServiceWrapper<IvldCompanyIdentifierLocalService> {
    private IvldCompanyIdentifierLocalService _ivldCompanyIdentifierLocalService;

    public IvldCompanyIdentifierLocalServiceWrapper(
        IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
        _ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
    }

    /**
    * Adds the ivld company identifier to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyIdentifier the ivld company identifier
    * @return the ivld company identifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier addIvldCompanyIdentifier(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.addIvldCompanyIdentifier(ivldCompanyIdentifier);
    }

    /**
    * Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
    *
    * @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
    * @return the new ivld company identifier
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier createIvldCompanyIdentifier(
        int ivldCompanyIdentifierSid) {
        return _ivldCompanyIdentifierLocalService.createIvldCompanyIdentifier(ivldCompanyIdentifierSid);
    }

    /**
    * Deletes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
    * @return the ivld company identifier that was removed
    * @throws PortalException if a ivld company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
        int ivldCompanyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.deleteIvldCompanyIdentifier(ivldCompanyIdentifierSid);
    }

    /**
    * Deletes the ivld company identifier from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyIdentifier the ivld company identifier
    * @return the ivld company identifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.deleteIvldCompanyIdentifier(ivldCompanyIdentifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCompanyIdentifierLocalService.dynamicQuery();
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
        return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier fetchIvldCompanyIdentifier(
        int ivldCompanyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.fetchIvldCompanyIdentifier(ivldCompanyIdentifierSid);
    }

    /**
    * Returns the ivld company identifier with the primary key.
    *
    * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
    * @return the ivld company identifier
    * @throws PortalException if a ivld company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier getIvldCompanyIdentifier(
        int ivldCompanyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifier(ivldCompanyIdentifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company identifiers
    * @param end the upper bound of the range of ivld company identifiers (not inclusive)
    * @return the range of ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> getIvldCompanyIdentifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifiers(start,
            end);
    }

    /**
    * Returns the number of ivld company identifiers.
    *
    * @return the number of ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCompanyIdentifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifiersCount();
    }

    /**
    * Updates the ivld company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyIdentifier the ivld company identifier
    * @return the ivld company identifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier updateIvldCompanyIdentifier(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyIdentifierLocalService.updateIvldCompanyIdentifier(ivldCompanyIdentifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCompanyIdentifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCompanyIdentifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCompanyIdentifierLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCompanyIdentifierLocalService getWrappedIvldCompanyIdentifierLocalService() {
        return _ivldCompanyIdentifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCompanyIdentifierLocalService(
        IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
        _ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
    }

    @Override
    public IvldCompanyIdentifierLocalService getWrappedService() {
        return _ivldCompanyIdentifierLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
        _ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
    }
}
