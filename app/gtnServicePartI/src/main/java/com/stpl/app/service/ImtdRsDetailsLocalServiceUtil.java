package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImtdRsDetails. This utility wraps
 * {@link com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ImtdRsDetailsLocalService
 * @see com.stpl.app.service.base.ImtdRsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl
 * @generated
 */
public class ImtdRsDetailsLocalServiceUtil {
    private static ImtdRsDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the imtd rs details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails addImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addImtdRsDetails(imtdRsDetails);
    }

    /**
    * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
    *
    * @param imtdRsDetailsSid the primary key for the new imtd rs details
    * @return the new imtd rs details
    */
    public static com.stpl.app.model.ImtdRsDetails createImtdRsDetails(
        int imtdRsDetailsSid) {
        return getService().createImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Deletes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details that was removed
    * @throws PortalException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Deletes the imtd rs details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdRsDetails(imtdRsDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ImtdRsDetails fetchImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchImtdRsDetails(imtdRsDetailsSid);
    }

    /**
    * Returns the imtd rs details with the primary key.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details
    * @throws PortalException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails getImtdRsDetails(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdRsDetails(imtdRsDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @return the range of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> getImtdRsDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdRsDetailses(start, end);
    }

    /**
    * Returns the number of imtd rs detailses.
    *
    * @return the number of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getImtdRsDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdRsDetailsesCount();
    }

    /**
    * Updates the imtd rs details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetails the imtd rs details
    * @return the imtd rs details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails updateImtdRsDetails(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateImtdRsDetails(imtdRsDetails);
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
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, populateField, populateValue, future3, future4);
    }

    public static java.lang.Object updateForPopulateAll(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateForPopulateAll(userId, sessionId, createdDate,
            operation, populateField, populateValue, future3, future4);
    }

    public static java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getService()
                   .getIfpLazyList(start, offset, ifpIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToRsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String flag,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateToRsDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, flag, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempRsDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String ifpSystemId,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempRsDetailsInADD(userId, sessionId, createdDate,
            ifpSystemId, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempRsDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String rsSystemId,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempRsDetailsInEdit(userId, sessionId, createdDate,
            rsSystemId, future1, future2, future3, future4);
    }

    public static java.util.List getItemLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getService()
                   .getItemLazyList(start, offset, companyIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateToIFPDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempIfpDetailsInADD(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempIfpDetailsInEdit(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempRSDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .validateTempRSDeatils(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempRSLazyList(java.lang.String rsId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .getTempRSLazyList(rsId, sessionId, createdDate, operation,
            future1, future2, future3, future4);
    }

    public static java.util.List loadIFPItems(java.lang.Integer start,
        java.lang.Integer end, java.lang.Object userID,
        java.lang.Object sessionID, java.lang.Object column,
        java.lang.Object orderBy, java.lang.Object value1,
        java.lang.Object value2,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .loadIFPItems(start, end, userID, sessionID, column,
            orderBy, value1, value2, parameters);
    }

    public static void mergeImtdRsDetailsFormula(int rsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId) {
        getService()
            .mergeImtdRsDetailsFormula(rsdSid, itemSid, userId, sessionId);
    }

    public static void deleteTempTableRecords(int rsSid, int rsdSid,
        java.lang.String userId, java.lang.String sessionId) {
        getService().deleteTempTableRecords(rsSid, rsdSid, userId, sessionId);
    }

    public static void insertFormulaToRsdFrImtd(int rsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getService()
            .insertFormulaToRsdFrImtd(rsdSid, userId, sessionId, createdDate);
    }

    public static void addAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getService()
            .addAllFormulaToRsdFrImtd(itemSid, userId, sessionId, createdDate);
    }

    public static void remaoveAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getService()
            .remaoveAllFormulaToRsdFrImtd(itemSid, userId, sessionId,
            createdDate);
    }

    public static void deleteRsdFr(int rsdSid) {
        getService().deleteRsdFr(rsdSid);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImtdRsDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImtdRsDetailsLocalService.class.getName());

            if (invokableLocalService instanceof ImtdRsDetailsLocalService) {
                _service = (ImtdRsDetailsLocalService) invokableLocalService;
            } else {
                _service = new ImtdRsDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImtdRsDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImtdRsDetailsLocalService service) {
    }
}
