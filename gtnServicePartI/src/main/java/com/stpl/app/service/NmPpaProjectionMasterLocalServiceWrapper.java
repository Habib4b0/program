package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmPpaProjectionMasterLocalService}.
 *
 * @author
 * @see NmPpaProjectionMasterLocalService
 * @generated
 */
public class NmPpaProjectionMasterLocalServiceWrapper
    implements NmPpaProjectionMasterLocalService,
        ServiceWrapper<NmPpaProjectionMasterLocalService> {
    private NmPpaProjectionMasterLocalService _nmPpaProjectionMasterLocalService;

    public NmPpaProjectionMasterLocalServiceWrapper(
        NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
        _nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
    }

    /**
    * Adds the nm ppa projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster addNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.addNmPpaProjectionMaster(nmPpaProjectionMaster);
    }

    /**
    * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm ppa projection master
    * @return the new nm ppa projection master
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster createNmPpaProjectionMaster(
        int projectionDetailsSid) {
        return _nmPpaProjectionMasterLocalService.createNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws PortalException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.deleteNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm ppa projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.deleteNmPpaProjectionMaster(nmPpaProjectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmPpaProjectionMasterLocalService.dynamicQuery();
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
        return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _nmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmPpaProjectionMaster fetchNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.fetchNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Returns the nm ppa projection master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master
    * @throws PortalException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster getNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMaster(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @return the range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmPpaProjectionMaster> getNmPpaProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMasters(start,
            end);
    }

    /**
    * Returns the number of nm ppa projection masters.
    *
    * @return the number of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmPpaProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMastersCount();
    }

    /**
    * Updates the nm ppa projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmPpaProjectionMaster updateNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmPpaProjectionMasterLocalService.updateNmPpaProjectionMaster(nmPpaProjectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmPpaProjectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmPpaProjectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmPpaProjectionMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List getPPAProjectionList(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.lang.String levelName) {
        return _nmPpaProjectionMasterLocalService.getPPAProjectionList(projectionId,
            levelNo, parent, last, startIndex, endIndex, isCount, levelName);
    }

    @Override
    public void setPPAProjectionMassUpdate(java.lang.Object priceCap,
        int startQuater, int endQuater, int startYear, int endYear,
        int projectionId, java.lang.String parent, java.lang.String levelValue) {
        _nmPpaProjectionMasterLocalService.setPPAProjectionMassUpdate(priceCap,
            startQuater, endQuater, startYear, endYear, projectionId, parent,
            levelValue);
    }

    @Override
    public java.util.List getPPAResults(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.util.List<java.lang.String> input,
        java.lang.String levelName) {
        return _nmPpaProjectionMasterLocalService.getPPAResults(projectionId,
            levelNo, parent, last, startIndex, endIndex, isCount, input,
            levelName);
    }

    @Override
    public java.util.List getLevelValues(int projectionId, int levelNo,
        java.lang.String parent) {
        return _nmPpaProjectionMasterLocalService.getLevelValues(projectionId,
            levelNo, parent);
    }

    @Override
    public java.util.List getProductHierarchyLevel(int projectionId,
        int levelNo, java.lang.String parent) {
        return _nmPpaProjectionMasterLocalService.getProductHierarchyLevel(projectionId,
            levelNo, parent);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmPpaProjectionMasterLocalService getWrappedNmPpaProjectionMasterLocalService() {
        return _nmPpaProjectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmPpaProjectionMasterLocalService(
        NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
        _nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
    }

    @Override
    public NmPpaProjectionMasterLocalService getWrappedService() {
        return _nmPpaProjectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
        _nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
    }
}
