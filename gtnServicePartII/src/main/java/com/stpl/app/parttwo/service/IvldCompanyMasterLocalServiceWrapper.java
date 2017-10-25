package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCompanyMasterLocalService}.
 *
 * @author
 * @see IvldCompanyMasterLocalService
 * @generated
 */
public class IvldCompanyMasterLocalServiceWrapper
    implements IvldCompanyMasterLocalService,
        ServiceWrapper<IvldCompanyMasterLocalService> {
    private IvldCompanyMasterLocalService _ivldCompanyMasterLocalService;

    public IvldCompanyMasterLocalServiceWrapper(
        IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
        _ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
    }

    /**
    * Adds the ivld company master to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMaster the ivld company master
    * @return the ivld company master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster addIvldCompanyMaster(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.addIvldCompanyMaster(ivldCompanyMaster);
    }

    /**
    * Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
    *
    * @param ivldCompanyMasterSid the primary key for the new ivld company master
    * @return the new ivld company master
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster createIvldCompanyMaster(
        int ivldCompanyMasterSid) {
        return _ivldCompanyMasterLocalService.createIvldCompanyMaster(ivldCompanyMasterSid);
    }

    /**
    * Deletes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master that was removed
    * @throws PortalException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster deleteIvldCompanyMaster(
        int ivldCompanyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.deleteIvldCompanyMaster(ivldCompanyMasterSid);
    }

    /**
    * Deletes the ivld company master from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMaster the ivld company master
    * @return the ivld company master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster deleteIvldCompanyMaster(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.deleteIvldCompanyMaster(ivldCompanyMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCompanyMasterLocalService.dynamicQuery();
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
        return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldCompanyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCompanyMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster fetchIvldCompanyMaster(
        int ivldCompanyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.fetchIvldCompanyMaster(ivldCompanyMasterSid);
    }

    /**
    * Returns the ivld company master with the primary key.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master
    * @throws PortalException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster getIvldCompanyMaster(
        int ivldCompanyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.getIvldCompanyMaster(ivldCompanyMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company masters
    * @param end the upper bound of the range of ivld company masters (not inclusive)
    * @return the range of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> getIvldCompanyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.getIvldCompanyMasters(start, end);
    }

    /**
    * Returns the number of ivld company masters.
    *
    * @return the number of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCompanyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.getIvldCompanyMastersCount();
    }

    /**
    * Updates the ivld company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMaster the ivld company master
    * @return the ivld company master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyMaster updateIvldCompanyMaster(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyMasterLocalService.updateIvldCompanyMaster(ivldCompanyMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCompanyMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCompanyMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCompanyMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCompanyMasterLocalService getWrappedIvldCompanyMasterLocalService() {
        return _ivldCompanyMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCompanyMasterLocalService(
        IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
        _ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
    }

    @Override
    public IvldCompanyMasterLocalService getWrappedService() {
        return _ivldCompanyMasterLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
        _ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
    }
}
