package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImtdPsDetails. This utility wraps
 * {@link com.stpl.app.service.impl.ImtdPsDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ImtdPsDetailsLocalService
 * @see com.stpl.app.service.base.ImtdPsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ImtdPsDetailsLocalServiceImpl
 * @generated
 */
public class ImtdPsDetailsLocalServiceUtil {
    private static ImtdPsDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ImtdPsDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the imtd ps details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails addImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addImtdPsDetails(imtdPsDetails);
    }

    /**
    * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
    *
    * @param imtdPsDetailsSid the primary key for the new imtd ps details
    * @return the new imtd ps details
    */
    public static com.stpl.app.model.ImtdPsDetails createImtdPsDetails(
        int imtdPsDetailsSid) {
        return getService().createImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Deletes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details that was removed
    * @throws PortalException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails deleteImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Deletes the imtd ps details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails deleteImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdPsDetails(imtdPsDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ImtdPsDetails fetchImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchImtdPsDetails(imtdPsDetailsSid);
    }

    /**
    * Returns the imtd ps details with the primary key.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details
    * @throws PortalException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails getImtdPsDetails(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdPsDetails(imtdPsDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @return the range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdPsDetails> getImtdPsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdPsDetailses(start, end);
    }

    /**
    * Returns the number of imtd ps detailses.
    *
    * @return the number of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getImtdPsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdPsDetailsesCount();
    }

    /**
    * Updates the imtd ps details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetails the imtd ps details
    * @return the imtd ps details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails updateImtdPsDetails(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateImtdPsDetails(imtdPsDetails);
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

    public static java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .deleteAll(userId, sessionId, dateCreated, operation,
            future1, future2, future3, future4);
    }

    public static java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .updateOperation(userId, sessionId, createdDate, operation,
            future1, future2, future3, future4);
    }

    public static java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object updateForPopulateAll(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateForPopulateAll(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.util.List getIFPLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getService()
                   .getIFPLazyList(start, offset, companyIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToPsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateToPsDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempPsDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempPsDetailsInADD(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempPsDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempPsDetailsInEdit(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempPSDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .validateTempPSDeatils(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .getTempCFPLazyList(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.util.List getItemPriceDetails(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .getItemPriceDetails(start, offset, userId, sessionId,
            columnName, orderBy, parameters);
    }

    public static java.lang.Object executeUpdateQuery(
        java.lang.String queryList, java.lang.Object obj1, java.lang.Object obj2) {
        return getService().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImtdPsDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImtdPsDetailsLocalService.class.getName());

            if (invokableLocalService instanceof ImtdPsDetailsLocalService) {
                _service = (ImtdPsDetailsLocalService) invokableLocalService;
            } else {
                _service = new ImtdPsDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImtdPsDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImtdPsDetailsLocalService service) {
    }
}
