package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocDetailsLocalService}.
 *
 * @author
 * @see DocDetailsLocalService
 * @generated
 */
public class DocDetailsLocalServiceWrapper implements DocDetailsLocalService,
    ServiceWrapper<DocDetailsLocalService> {
    private DocDetailsLocalService _docDetailsLocalService;

    public DocDetailsLocalServiceWrapper(
        DocDetailsLocalService docDetailsLocalService) {
        _docDetailsLocalService = docDetailsLocalService;
    }

    /**
    * Adds the doc details to the database. Also notifies the appropriate model listeners.
    *
    * @param docDetails the doc details
    * @return the doc details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DocDetails addDocDetails(
        com.stpl.app.model.DocDetails docDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.addDocDetails(docDetails);
    }

    /**
    * Creates a new doc details with the primary key. Does not add the doc details to the database.
    *
    * @param docDetailsId the primary key for the new doc details
    * @return the new doc details
    */
    @Override
    public com.stpl.app.model.DocDetails createDocDetails(int docDetailsId) {
        return _docDetailsLocalService.createDocDetails(docDetailsId);
    }

    /**
    * Deletes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details that was removed
    * @throws PortalException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DocDetails deleteDocDetails(int docDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.deleteDocDetails(docDetailsId);
    }

    /**
    * Deletes the doc details from the database. Also notifies the appropriate model listeners.
    *
    * @param docDetails the doc details
    * @return the doc details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DocDetails deleteDocDetails(
        com.stpl.app.model.DocDetails docDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.deleteDocDetails(docDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _docDetailsLocalService.dynamicQuery();
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
        return _docDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _docDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _docDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _docDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _docDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.DocDetails fetchDocDetails(int docDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.fetchDocDetails(docDetailsId);
    }

    /**
    * Returns the doc details with the primary key.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details
    * @throws PortalException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DocDetails getDocDetails(int docDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.getDocDetails(docDetailsId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of doc detailses
    * @param end the upper bound of the range of doc detailses (not inclusive)
    * @return the range of doc detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.DocDetails> getDocDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.getDocDetailses(start, end);
    }

    /**
    * Returns the number of doc detailses.
    *
    * @return the number of doc detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDocDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.getDocDetailsesCount();
    }

    /**
    * Updates the doc details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param docDetails the doc details
    * @return the doc details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DocDetails updateDocDetails(
        com.stpl.app.model.DocDetails docDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _docDetailsLocalService.updateDocDetails(docDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _docDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _docDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _docDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DocDetailsLocalService getWrappedDocDetailsLocalService() {
        return _docDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDocDetailsLocalService(
        DocDetailsLocalService docDetailsLocalService) {
        _docDetailsLocalService = docDetailsLocalService;
    }

    @Override
    public DocDetailsLocalService getWrappedService() {
        return _docDetailsLocalService;
    }

    @Override
    public void setWrappedService(DocDetailsLocalService docDetailsLocalService) {
        _docDetailsLocalService = docDetailsLocalService;
    }
}
