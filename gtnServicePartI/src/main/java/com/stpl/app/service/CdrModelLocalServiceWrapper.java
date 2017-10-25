package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CdrModelLocalService}.
 *
 * @author
 * @see CdrModelLocalService
 * @generated
 */
public class CdrModelLocalServiceWrapper implements CdrModelLocalService,
    ServiceWrapper<CdrModelLocalService> {
    private CdrModelLocalService _cdrModelLocalService;

    public CdrModelLocalServiceWrapper(
        CdrModelLocalService cdrModelLocalService) {
        _cdrModelLocalService = cdrModelLocalService;
    }

    /**
    * Adds the cdr model to the database. Also notifies the appropriate model listeners.
    *
    * @param cdrModel the cdr model
    * @return the cdr model that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrModel addCdrModel(
        com.stpl.app.model.CdrModel cdrModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.addCdrModel(cdrModel);
    }

    /**
    * Creates a new cdr model with the primary key. Does not add the cdr model to the database.
    *
    * @param cdrModelSid the primary key for the new cdr model
    * @return the new cdr model
    */
    @Override
    public com.stpl.app.model.CdrModel createCdrModel(int cdrModelSid) {
        return _cdrModelLocalService.createCdrModel(cdrModelSid);
    }

    /**
    * Deletes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrModelSid the primary key of the cdr model
    * @return the cdr model that was removed
    * @throws PortalException if a cdr model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrModel deleteCdrModel(int cdrModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.deleteCdrModel(cdrModelSid);
    }

    /**
    * Deletes the cdr model from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrModel the cdr model
    * @return the cdr model that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrModel deleteCdrModel(
        com.stpl.app.model.CdrModel cdrModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.deleteCdrModel(cdrModel);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cdrModelLocalService.dynamicQuery();
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
        return _cdrModelLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cdrModelLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cdrModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _cdrModelLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cdrModelLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.CdrModel fetchCdrModel(int cdrModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.fetchCdrModel(cdrModelSid);
    }

    /**
    * Returns the cdr model with the primary key.
    *
    * @param cdrModelSid the primary key of the cdr model
    * @return the cdr model
    * @throws PortalException if a cdr model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrModel getCdrModel(int cdrModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.getCdrModel(cdrModelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cdr models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr models
    * @param end the upper bound of the range of cdr models (not inclusive)
    * @return the range of cdr models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CdrModel> getCdrModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.getCdrModels(start, end);
    }

    /**
    * Returns the number of cdr models.
    *
    * @return the number of cdr models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCdrModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.getCdrModelsCount();
    }

    /**
    * Updates the cdr model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cdrModel the cdr model
    * @return the cdr model that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CdrModel updateCdrModel(
        com.stpl.app.model.CdrModel cdrModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cdrModelLocalService.updateCdrModel(cdrModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cdrModelLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cdrModelLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cdrModelLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CdrModelLocalService getWrappedCdrModelLocalService() {
        return _cdrModelLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCdrModelLocalService(
        CdrModelLocalService cdrModelLocalService) {
        _cdrModelLocalService = cdrModelLocalService;
    }

    @Override
    public CdrModelLocalService getWrappedService() {
        return _cdrModelLocalService;
    }

    @Override
    public void setWrappedService(CdrModelLocalService cdrModelLocalService) {
        _cdrModelLocalService = cdrModelLocalService;
    }
}
