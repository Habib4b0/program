package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IfpModelLocalService}.
 *
 * @author
 * @see IfpModelLocalService
 * @generated
 */
public class IfpModelLocalServiceWrapper implements IfpModelLocalService,
    ServiceWrapper<IfpModelLocalService> {
    private IfpModelLocalService _ifpModelLocalService;

    public IfpModelLocalServiceWrapper(
        IfpModelLocalService ifpModelLocalService) {
        _ifpModelLocalService = ifpModelLocalService;
    }

    /**
    * Adds the ifp model to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpModel the ifp model
    * @return the ifp model that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpModel addIfpModel(
        com.stpl.app.model.IfpModel ifpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.addIfpModel(ifpModel);
    }

    /**
    * Creates a new ifp model with the primary key. Does not add the ifp model to the database.
    *
    * @param ifpModelSid the primary key for the new ifp model
    * @return the new ifp model
    */
    @Override
    public com.stpl.app.model.IfpModel createIfpModel(int ifpModelSid) {
        return _ifpModelLocalService.createIfpModel(ifpModelSid);
    }

    /**
    * Deletes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpModelSid the primary key of the ifp model
    * @return the ifp model that was removed
    * @throws PortalException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpModel deleteIfpModel(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.deleteIfpModel(ifpModelSid);
    }

    /**
    * Deletes the ifp model from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpModel the ifp model
    * @return the ifp model that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpModel deleteIfpModel(
        com.stpl.app.model.IfpModel ifpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.deleteIfpModel(ifpModel);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ifpModelLocalService.dynamicQuery();
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
        return _ifpModelLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpModelLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpModelLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _ifpModelLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ifpModelLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.IfpModel fetchIfpModel(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.fetchIfpModel(ifpModelSid);
    }

    /**
    * Returns the ifp model with the primary key.
    *
    * @param ifpModelSid the primary key of the ifp model
    * @return the ifp model
    * @throws PortalException if a ifp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpModel getIfpModel(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.getIfpModel(ifpModelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp models
    * @param end the upper bound of the range of ifp models (not inclusive)
    * @return the range of ifp models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IfpModel> getIfpModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.getIfpModels(start, end);
    }

    /**
    * Returns the number of ifp models.
    *
    * @return the number of ifp models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIfpModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.getIfpModelsCount();
    }

    /**
    * Updates the ifp model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpModel the ifp model
    * @return the ifp model that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpModel updateIfpModel(
        com.stpl.app.model.IfpModel ifpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpModelLocalService.updateIfpModel(ifpModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ifpModelLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ifpModelLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ifpModelLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IfpModelLocalService getWrappedIfpModelLocalService() {
        return _ifpModelLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIfpModelLocalService(
        IfpModelLocalService ifpModelLocalService) {
        _ifpModelLocalService = ifpModelLocalService;
    }

    @Override
    public IfpModelLocalService getWrappedService() {
        return _ifpModelLocalService;
    }

    @Override
    public void setWrappedService(IfpModelLocalService ifpModelLocalService) {
        _ifpModelLocalService = ifpModelLocalService;
    }
}
