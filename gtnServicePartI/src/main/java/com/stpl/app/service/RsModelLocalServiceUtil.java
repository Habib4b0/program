package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for RsModel. This utility wraps
 * {@link com.stpl.app.service.impl.RsModelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RsModelLocalService
 * @see com.stpl.app.service.base.RsModelLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RsModelLocalServiceImpl
 * @generated
 */
public class RsModelLocalServiceUtil {
    private static RsModelLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RsModelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the rs model to the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel addRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addRsModel(rsModel);
    }

    /**
    * Creates a new rs model with the primary key. Does not add the rs model to the database.
    *
    * @param rsModelSid the primary key for the new rs model
    * @return the new rs model
    */
    public static com.stpl.app.model.RsModel createRsModel(int rsModelSid) {
        return getService().createRsModel(rsModelSid);
    }

    /**
    * Deletes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model that was removed
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel deleteRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteRsModel(rsModelSid);
    }

    /**
    * Deletes the rs model from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel deleteRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteRsModel(rsModel);
    }

    public static com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.stpl.app.model.RsModel fetchRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchRsModel(rsModelSid);
    }

    /**
    * Returns the rs model with the primary key.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model
    * @throws PortalException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel getRsModel(int rsModelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getRsModel(rsModelSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.stpl.app.model.RsModel> getRsModels(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getRsModels(start, end);
    }

    /**
    * Returns the number of rs models.
    *
    * @return the number of rs models
    * @throws SystemException if a system exception occurred
    */
    public static int getRsModelsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getRsModelsCount();
    }

    /**
    * Updates the rs model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rsModel the rs model
    * @return the rs model that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel updateRsModel(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateRsModel(rsModel);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List getItemDetailsOfIfp(java.lang.String ifpId) {
        return getService().getItemDetailsOfIfp(ifpId);
    }

    public static java.util.List getRebateScheduleDetails(
        int rebateScheduleSystemId, java.lang.Object future1,
        java.lang.Object future2) {
        return getService()
                   .getRebateScheduleDetails(rebateScheduleSystemId, future1,
            future2);
    }

    public static java.util.List getRebateScheduleDetailsUniqueCheck(
        java.lang.String rebateScheduleId, java.lang.String itemId,
        java.util.Date itemStartDate) {
        return getService()
                   .getRebateScheduleDetailsUniqueCheck(rebateScheduleId,
            itemId, itemStartDate);
    }

    public static java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
        int rebateScheduleSystemId, java.lang.String rebateScheduleId,
        java.lang.String itemId, java.util.Date itemStartDate) {
        return getService()
                   .getRebateScheduleDetailsUniqueCheckWithSysId(rebateScheduleSystemId,
            rebateScheduleId, itemId, itemStartDate);
    }

    public static java.util.List findRSModel(java.lang.String rsId,
        java.lang.String rsNo, java.lang.String rsName,
        java.lang.String rsStatus, java.lang.String rpType,
        java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, java.lang.String future,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .findRSModel(rsId, rsNo, rsName, rsStatus, rpType, itemId,
            itemNo, itemName, future, parameters);
    }

    public static java.util.List findIFP(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.String ifpType,
        java.lang.String ifpTypeString, java.lang.String ifpStartDate,
        java.lang.String ifpEndDate, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .findIFP(ifpNumber, ifpName, ifpType, ifpTypeString,
            ifpStartDate, ifpEndDate, itemNo, itemName, parameters);
    }

    public static java.util.List getParentRsList(java.lang.String rebateSchId,
        java.lang.String rebateSchNo, java.lang.String rebateSchName,
        java.lang.String rebateSchStatus, java.lang.String rebateSchType,
        java.lang.String rebateProgType, java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters,
        boolean isCount) {
        return getService()
                   .getParentRsList(rebateSchId, rebateSchNo, rebateSchName,
            rebateSchStatus, rebateSchType, rebateProgType, itemId, itemNo,
            itemName, start, offset, column, orderBy, parameters, isCount);
    }

    public static java.util.List getIfpList(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.Object ifpType,
        java.lang.String ifpStartDate, java.lang.String ifpEndDate,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .getIfpList(ifpNumber, ifpName, ifpType, ifpStartDate,
            ifpEndDate, itemNo, itemName, start, offset, column, orderBy,
            parameters);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getService().executeSelectQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getService().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.lang.String queryList, java.lang.Object obj1, java.lang.Object obj2) {
        return getService().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static void clearService() {
        _service = null;
    }

    public static RsModelLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    RsModelLocalService.class.getName());

            if (invokableLocalService instanceof RsModelLocalService) {
                _service = (RsModelLocalService) invokableLocalService;
            } else {
                _service = new RsModelLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(RsModelLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(RsModelLocalService service) {
    }
}
