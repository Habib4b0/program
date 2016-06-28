package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldLotMasterLocalService}.
 *
 * @author
 * @see IvldLotMasterLocalService
 * @generated
 */
public class IvldLotMasterLocalServiceWrapper
    implements IvldLotMasterLocalService,
        ServiceWrapper<IvldLotMasterLocalService> {
    private IvldLotMasterLocalService _ivldLotMasterLocalService;

    public IvldLotMasterLocalServiceWrapper(
        IvldLotMasterLocalService ivldLotMasterLocalService) {
        _ivldLotMasterLocalService = ivldLotMasterLocalService;
    }

    /**
    * Adds the ivld lot master to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMaster the ivld lot master
    * @return the ivld lot master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldLotMaster addIvldLotMaster(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.addIvldLotMaster(ivldLotMaster);
    }

    /**
    * Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
    *
    * @param ivldLotMasterSid the primary key for the new ivld lot master
    * @return the new ivld lot master
    */
    @Override
    public com.stpl.app.model.IvldLotMaster createIvldLotMaster(
        int ivldLotMasterSid) {
        return _ivldLotMasterLocalService.createIvldLotMaster(ivldLotMasterSid);
    }

    /**
    * Deletes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master that was removed
    * @throws PortalException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldLotMaster deleteIvldLotMaster(
        int ivldLotMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.deleteIvldLotMaster(ivldLotMasterSid);
    }

    /**
    * Deletes the ivld lot master from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMaster the ivld lot master
    * @return the ivld lot master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldLotMaster deleteIvldLotMaster(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.deleteIvldLotMaster(ivldLotMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldLotMasterLocalService.dynamicQuery();
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
        return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldLotMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldLotMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldLotMaster fetchIvldLotMaster(
        int ivldLotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.fetchIvldLotMaster(ivldLotMasterSid);
    }

    /**
    * Returns the ivld lot master with the primary key.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master
    * @throws PortalException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldLotMaster getIvldLotMaster(
        int ivldLotMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.getIvldLotMaster(ivldLotMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld lot masters
    * @param end the upper bound of the range of ivld lot masters (not inclusive)
    * @return the range of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldLotMaster> getIvldLotMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.getIvldLotMasters(start, end);
    }

    /**
    * Returns the number of ivld lot masters.
    *
    * @return the number of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldLotMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.getIvldLotMastersCount();
    }

    /**
    * Updates the ivld lot master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMaster the ivld lot master
    * @return the ivld lot master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldLotMaster updateIvldLotMaster(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldLotMasterLocalService.updateIvldLotMaster(ivldLotMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldLotMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldLotMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldLotMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldLotMasterLocalService getWrappedIvldLotMasterLocalService() {
        return _ivldLotMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldLotMasterLocalService(
        IvldLotMasterLocalService ivldLotMasterLocalService) {
        _ivldLotMasterLocalService = ivldLotMasterLocalService;
    }

    @Override
    public IvldLotMasterLocalService getWrappedService() {
        return _ivldLotMasterLocalService;
    }

    @Override
    public void setWrappedService(
        IvldLotMasterLocalService ivldLotMasterLocalService) {
        _ivldLotMasterLocalService = ivldLotMasterLocalService;
    }
}
