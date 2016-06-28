package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AhTempDetailsLocalService}.
 *
 * @author
 * @see AhTempDetailsLocalService
 * @generated
 */
public class AhTempDetailsLocalServiceWrapper
    implements AhTempDetailsLocalService,
        ServiceWrapper<AhTempDetailsLocalService> {
    private AhTempDetailsLocalService _ahTempDetailsLocalService;

    public AhTempDetailsLocalServiceWrapper(
        AhTempDetailsLocalService ahTempDetailsLocalService) {
        _ahTempDetailsLocalService = ahTempDetailsLocalService;
    }

    /**
    * Adds the ah temp details to the database. Also notifies the appropriate model listeners.
    *
    * @param ahTempDetails the ah temp details
    * @return the ah temp details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails addAhTempDetails(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.addAhTempDetails(ahTempDetails);
    }

    /**
    * Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
    *
    * @param componentMasterSid the primary key for the new ah temp details
    * @return the new ah temp details
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails createAhTempDetails(
        int componentMasterSid) {
        return _ahTempDetailsLocalService.createAhTempDetails(componentMasterSid);
    }

    /**
    * Deletes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details that was removed
    * @throws PortalException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails deleteAhTempDetails(
        int componentMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.deleteAhTempDetails(componentMasterSid);
    }

    /**
    * Deletes the ah temp details from the database. Also notifies the appropriate model listeners.
    *
    * @param ahTempDetails the ah temp details
    * @return the ah temp details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails deleteAhTempDetails(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.deleteAhTempDetails(ahTempDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ahTempDetailsLocalService.dynamicQuery();
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
        return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ahTempDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ahTempDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AhTempDetails fetchAhTempDetails(
        int componentMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.fetchAhTempDetails(componentMasterSid);
    }

    /**
    * Returns the ah temp details with the primary key.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details
    * @throws PortalException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails getAhTempDetails(
        int componentMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.getAhTempDetails(componentMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ah temp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ah temp detailses
    * @param end the upper bound of the range of ah temp detailses (not inclusive)
    * @return the range of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AhTempDetails> getAhTempDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.getAhTempDetailses(start, end);
    }

    /**
    * Returns the number of ah temp detailses.
    *
    * @return the number of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAhTempDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.getAhTempDetailsesCount();
    }

    /**
    * Updates the ah temp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ahTempDetails the ah temp details
    * @return the ah temp details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AhTempDetails updateAhTempDetails(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ahTempDetailsLocalService.updateAhTempDetails(ahTempDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ahTempDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ahTempDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ahTempDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AhTempDetailsLocalService getWrappedAhTempDetailsLocalService() {
        return _ahTempDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAhTempDetailsLocalService(
        AhTempDetailsLocalService ahTempDetailsLocalService) {
        _ahTempDetailsLocalService = ahTempDetailsLocalService;
    }

    @Override
    public AhTempDetailsLocalService getWrappedService() {
        return _ahTempDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        AhTempDetailsLocalService ahTempDetailsLocalService) {
        _ahTempDetailsLocalService = ahTempDetailsLocalService;
    }
}
