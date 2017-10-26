package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImtdCfpDetails. This utility wraps
 * {@link com.stpl.app.service.impl.ImtdCfpDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ImtdCfpDetailsLocalService
 * @see com.stpl.app.service.base.ImtdCfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ImtdCfpDetailsLocalServiceImpl
 * @generated
 */
public class ImtdCfpDetailsLocalServiceUtil {
    private static ImtdCfpDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ImtdCfpDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the imtd cfp details to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails addImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addImtdCfpDetails(imtdCfpDetails);
    }

    /**
    * Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
    *
    * @param imtdCfpDetailsSid the primary key for the new imtd cfp details
    * @return the new imtd cfp details
    */
    public static com.stpl.app.model.ImtdCfpDetails createImtdCfpDetails(
        int imtdCfpDetailsSid) {
        return getService().createImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Deletes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details that was removed
    * @throws PortalException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Deletes the imtd cfp details from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails deleteImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteImtdCfpDetails(imtdCfpDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ImtdCfpDetails fetchImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchImtdCfpDetails(imtdCfpDetailsSid);
    }

    /**
    * Returns the imtd cfp details with the primary key.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details
    * @throws PortalException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails getImtdCfpDetails(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdCfpDetails(imtdCfpDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @return the range of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> getImtdCfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdCfpDetailses(start, end);
    }

    /**
    * Returns the number of imtd cfp detailses.
    *
    * @return the number of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getImtdCfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getImtdCfpDetailsesCount();
    }

    /**
    * Updates the imtd cfp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetails the imtd cfp details
    * @return the imtd cfp details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails updateImtdCfpDetails(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateImtdCfpDetails(imtdCfpDetails);
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

    public static java.util.List getCompanyLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.String column,
        java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getService()
                   .getCompanyLazyList(start, offset, companyIdList, operation,
            searchValue, column, orderBy, filterMap);
    }

    public static java.util.List getSelectedCompanies(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap,
        java.lang.String operation) {
        return getService()
                   .getSelectedCompanies(start, offset, userId, sessionId,
            columnName, orderBy, filterMap, operation);
    }

    public static java.lang.Object updateToCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateToCFPDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempCfpDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempCfpDetailsInADD(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempCfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .insertTempCfpDetailsInEdit(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempCFPDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .validateTempCFPDeatils(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempCFPLazyList(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getService()
                   .getTempCFPLazyList(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4, filterMap);
    }

    public static java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return getService()
                   .updateAll(userId, sessionId, dateCreated, operation,
            tempCfpSystemId, deleteYesterdayRecordFlag, future3, future4);
    }

    public static java.lang.Object updateCFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getService()
                   .updateCFPDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.util.List getTempCfpDetails(java.lang.String userId,
        java.lang.String sessionId, int start, int offset,
        java.lang.String column, java.lang.String orederBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getService()
                   .getTempCfpDetails(userId, sessionId, start, offset, column,
            orederBy, filterMap);
    }

    public static java.lang.Boolean loadTempCompanydetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().loadTempCompanydetails(input, future);
    }

    public static java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().massPopulate(input, future);
    }

    public static java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().massPopulateAll(input, future);
    }

    public static java.lang.Boolean saveCompany(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().saveCompany(input, future);
    }

    public static java.lang.Boolean deleteAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().deleteAll(input, future);
    }

    public static java.lang.Boolean updateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().updateAll(input, future);
    }

    public static java.lang.Object getOverlapedCompanies(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().getOverlapedCompanies(input, future);
    }

    public static java.util.List getSelectedCompanies(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean flag,
        java.lang.Object future1,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount) {
        return getService()
                   .getSelectedCompanies(userID, sessionID, start, offset,
            column, orderBy, flag, future1, filterMap, isCount);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImtdCfpDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImtdCfpDetailsLocalService.class.getName());

            if (invokableLocalService instanceof ImtdCfpDetailsLocalService) {
                _service = (ImtdCfpDetailsLocalService) invokableLocalService;
            } else {
                _service = new ImtdCfpDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImtdCfpDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImtdCfpDetailsLocalService service) {
    }
}
