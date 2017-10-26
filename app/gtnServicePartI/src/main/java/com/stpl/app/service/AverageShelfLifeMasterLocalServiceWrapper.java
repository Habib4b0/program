package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AverageShelfLifeMasterLocalService}.
 *
 * @author
 * @see AverageShelfLifeMasterLocalService
 * @generated
 */
public class AverageShelfLifeMasterLocalServiceWrapper
    implements AverageShelfLifeMasterLocalService,
        ServiceWrapper<AverageShelfLifeMasterLocalService> {
    private AverageShelfLifeMasterLocalService _averageShelfLifeMasterLocalService;

    public AverageShelfLifeMasterLocalServiceWrapper(
        AverageShelfLifeMasterLocalService averageShelfLifeMasterLocalService) {
        _averageShelfLifeMasterLocalService = averageShelfLifeMasterLocalService;
    }

    /**
    * Adds the average shelf life master to the database. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMaster the average shelf life master
    * @return the average shelf life master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster addAverageShelfLifeMaster(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.addAverageShelfLifeMaster(averageShelfLifeMaster);
    }

    /**
    * Creates a new average shelf life master with the primary key. Does not add the average shelf life master to the database.
    *
    * @param averageShelfLifeMasterSid the primary key for the new average shelf life master
    * @return the new average shelf life master
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster createAverageShelfLifeMaster(
        int averageShelfLifeMasterSid) {
        return _averageShelfLifeMasterLocalService.createAverageShelfLifeMaster(averageShelfLifeMasterSid);
    }

    /**
    * Deletes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master that was removed
    * @throws PortalException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster deleteAverageShelfLifeMaster(
        int averageShelfLifeMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.deleteAverageShelfLifeMaster(averageShelfLifeMasterSid);
    }

    /**
    * Deletes the average shelf life master from the database. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMaster the average shelf life master
    * @return the average shelf life master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster deleteAverageShelfLifeMaster(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.deleteAverageShelfLifeMaster(averageShelfLifeMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _averageShelfLifeMasterLocalService.dynamicQuery();
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
        return _averageShelfLifeMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _averageShelfLifeMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _averageShelfLifeMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _averageShelfLifeMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _averageShelfLifeMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.AverageShelfLifeMaster fetchAverageShelfLifeMaster(
        int averageShelfLifeMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.fetchAverageShelfLifeMaster(averageShelfLifeMasterSid);
    }

    /**
    * Returns the average shelf life master with the primary key.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master
    * @throws PortalException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster getAverageShelfLifeMaster(
        int averageShelfLifeMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.getAverageShelfLifeMaster(averageShelfLifeMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the average shelf life masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> getAverageShelfLifeMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.getAverageShelfLifeMasters(start,
            end);
    }

    /**
    * Returns the number of average shelf life masters.
    *
    * @return the number of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAverageShelfLifeMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.getAverageShelfLifeMastersCount();
    }

    /**
    * Updates the average shelf life master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMaster the average shelf life master
    * @return the average shelf life master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AverageShelfLifeMaster updateAverageShelfLifeMaster(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _averageShelfLifeMasterLocalService.updateAverageShelfLifeMaster(averageShelfLifeMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _averageShelfLifeMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _averageShelfLifeMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _averageShelfLifeMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AverageShelfLifeMasterLocalService getWrappedAverageShelfLifeMasterLocalService() {
        return _averageShelfLifeMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAverageShelfLifeMasterLocalService(
        AverageShelfLifeMasterLocalService averageShelfLifeMasterLocalService) {
        _averageShelfLifeMasterLocalService = averageShelfLifeMasterLocalService;
    }

    @Override
    public AverageShelfLifeMasterLocalService getWrappedService() {
        return _averageShelfLifeMasterLocalService;
    }

    @Override
    public void setWrappedService(
        AverageShelfLifeMasterLocalService averageShelfLifeMasterLocalService) {
        _averageShelfLifeMasterLocalService = averageShelfLifeMasterLocalService;
    }
}
