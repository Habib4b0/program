package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RsModelLocalService}.
 *
 * @author
 * @see RsModelLocalService
 * @generated
 */
public class RsModelLocalServiceWrapper implements RsModelLocalService,
    ServiceWrapper<RsModelLocalService> {
    private RsModelLocalService _rsModelLocalService;

    public RsModelLocalServiceWrapper(RsModelLocalService rsModelLocalService) {
        _rsModelLocalService = rsModelLocalService;
    }

    /**
    * Adds the rs model to the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsModel addRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.addRsModel(rsModel);
    }

    /**
    * Creates a new rs model with the primary key. Does not add the rs model to the database.
    *
    * @param rsModelSid the primary key for the new rs model
    * @return the new rs model
    */
    @Override
    public com.stpl.app.model.RsModel createRsModel(int rsModelSid) {
        return _rsModelLocalService.createRsModel(rsModelSid);
    }

    /**
    * Deletes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model that was removed
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsModel deleteRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.deleteRsModel(rsModelSid);
    }

    /**
    * Deletes the rs model from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsModel deleteRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.deleteRsModel(rsModel);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _rsModelLocalService.dynamicQuery();
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
        return _rsModelLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsModelLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _rsModelLocalService.dynamicQueryCount(dynamicQuery);
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
        return _rsModelLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.RsModel fetchRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.fetchRsModel(rsModelSid);
    }

    /**
    * Returns the rs model with the primary key.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsModel getRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.getRsModel(rsModelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the rs models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of rs models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RsModel> getRsModels(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.getRsModels(start, end);
    }

    /**
    * Returns the number of rs models.
    *
    * @return the number of rs models
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRsModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.getRsModelsCount();
    }

    /**
    * Updates the rs model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsModel updateRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsModelLocalService.updateRsModel(rsModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rsModelLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rsModelLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rsModelLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public java.util.List getItemDetailsOfIfp(java.lang.String ifpId) {
        return _rsModelLocalService.getItemDetailsOfIfp(ifpId);
    }

    @Override
    public java.util.List getRebateScheduleDetails(int rebateScheduleSystemId,
        java.lang.Object future1, java.lang.Object future2) {
        return _rsModelLocalService.getRebateScheduleDetails(rebateScheduleSystemId,
            future1, future2);
    }

    @Override
    public java.util.List getRebateScheduleDetailsUniqueCheck(
        java.lang.String rebateScheduleId, java.lang.String itemId,
        java.util.Date itemStartDate) {
        return _rsModelLocalService.getRebateScheduleDetailsUniqueCheck(rebateScheduleId,
            itemId, itemStartDate);
    }

    @Override
    public java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
        int rebateScheduleSystemId, java.lang.String rebateScheduleId,
        java.lang.String itemId, java.util.Date itemStartDate) {
        return _rsModelLocalService.getRebateScheduleDetailsUniqueCheckWithSysId(rebateScheduleSystemId,
            rebateScheduleId, itemId, itemStartDate);
    }

    @Override
    public java.util.List findRSModel(java.lang.String rsId,
        java.lang.String rsNo, java.lang.String rsName,
        java.lang.String rsStatus, java.lang.String rpType,
        java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, java.lang.String future,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _rsModelLocalService.findRSModel(rsId, rsNo, rsName, rsStatus,
            rpType, itemId, itemNo, itemName, future, parameters);
    }

    @Override
    public java.util.List findIFP(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.String ifpType,
        java.lang.String ifpTypeString, java.lang.String ifpStartDate,
        java.lang.String ifpEndDate, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _rsModelLocalService.findIFP(ifpNumber, ifpName, ifpType,
            ifpTypeString, ifpStartDate, ifpEndDate, itemNo, itemName,
            parameters);
    }

    @Override
    public java.util.List getParentRsList(java.lang.String rebateSchId,
        java.lang.String rebateSchNo, java.lang.String rebateSchName,
        java.lang.String rebateSchStatus, java.lang.String rebateSchType,
        java.lang.String rebateProgType, java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters,
        boolean isCount) {
        return _rsModelLocalService.getParentRsList(rebateSchId, rebateSchNo,
            rebateSchName, rebateSchStatus, rebateSchType, rebateProgType,
            itemId, itemNo, itemName, start, offset, column, orderBy,
            parameters, isCount);
    }

    @Override
    public java.util.List getIfpList(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.Object ifpType,
        java.lang.String ifpStartDate, java.lang.String ifpEndDate,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _rsModelLocalService.getIfpList(ifpNumber, ifpName, ifpType,
            ifpStartDate, ifpEndDate, itemNo, itemName, start, offset, column,
            orderBy, parameters);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _rsModelLocalService.executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _rsModelLocalService.executeUpdateQuery(queryList, obj1, obj2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.lang.String queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _rsModelLocalService.executeUpdateQuery(queryList, obj1, obj2);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RsModelLocalService getWrappedRsModelLocalService() {
        return _rsModelLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRsModelLocalService(
        RsModelLocalService rsModelLocalService) {
        _rsModelLocalService = rsModelLocalService;
    }

    @Override
    public RsModelLocalService getWrappedService() {
        return _rsModelLocalService;
    }

    @Override
    public void setWrappedService(RsModelLocalService rsModelLocalService) {
        _rsModelLocalService = rsModelLocalService;
    }
}
