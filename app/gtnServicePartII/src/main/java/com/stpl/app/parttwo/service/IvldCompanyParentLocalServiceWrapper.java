package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCompanyParentLocalService}.
 *
 * @author
 * @see IvldCompanyParentLocalService
 * @generated
 */
public class IvldCompanyParentLocalServiceWrapper
    implements IvldCompanyParentLocalService,
        ServiceWrapper<IvldCompanyParentLocalService> {
    private IvldCompanyParentLocalService _ivldCompanyParentLocalService;

    public IvldCompanyParentLocalServiceWrapper(
        IvldCompanyParentLocalService ivldCompanyParentLocalService) {
        _ivldCompanyParentLocalService = ivldCompanyParentLocalService;
    }

    /**
    * Adds the ivld company parent to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParent the ivld company parent
    * @return the ivld company parent that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent addIvldCompanyParent(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.addIvldCompanyParent(ivldCompanyParent);
    }

    /**
    * Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
    *
    * @param ivldCompanyParentSid the primary key for the new ivld company parent
    * @return the new ivld company parent
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent createIvldCompanyParent(
        int ivldCompanyParentSid) {
        return _ivldCompanyParentLocalService.createIvldCompanyParent(ivldCompanyParentSid);
    }

    /**
    * Deletes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent that was removed
    * @throws PortalException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent deleteIvldCompanyParent(
        int ivldCompanyParentSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.deleteIvldCompanyParent(ivldCompanyParentSid);
    }

    /**
    * Deletes the ivld company parent from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParent the ivld company parent
    * @return the ivld company parent that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent deleteIvldCompanyParent(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.deleteIvldCompanyParent(ivldCompanyParent);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCompanyParentLocalService.dynamicQuery();
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
        return _ivldCompanyParentLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyParentLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyParentLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldCompanyParentLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCompanyParentLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent fetchIvldCompanyParent(
        int ivldCompanyParentSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.fetchIvldCompanyParent(ivldCompanyParentSid);
    }

    /**
    * Returns the ivld company parent with the primary key.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent
    * @throws PortalException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent getIvldCompanyParent(
        int ivldCompanyParentSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.getIvldCompanyParent(ivldCompanyParentSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld company parents.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company parents
    * @param end the upper bound of the range of ivld company parents (not inclusive)
    * @return the range of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> getIvldCompanyParents(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.getIvldCompanyParents(start, end);
    }

    /**
    * Returns the number of ivld company parents.
    *
    * @return the number of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCompanyParentsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.getIvldCompanyParentsCount();
    }

    /**
    * Updates the ivld company parent in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParent the ivld company parent
    * @return the ivld company parent that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyParent updateIvldCompanyParent(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyParentLocalService.updateIvldCompanyParent(ivldCompanyParent);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCompanyParentLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCompanyParentLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCompanyParentLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCompanyParentLocalService getWrappedIvldCompanyParentLocalService() {
        return _ivldCompanyParentLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCompanyParentLocalService(
        IvldCompanyParentLocalService ivldCompanyParentLocalService) {
        _ivldCompanyParentLocalService = ivldCompanyParentLocalService;
    }

    @Override
    public IvldCompanyParentLocalService getWrappedService() {
        return _ivldCompanyParentLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCompanyParentLocalService ivldCompanyParentLocalService) {
        _ivldCompanyParentLocalService = ivldCompanyParentLocalService;
    }
}
