package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for CompanyMaster. This utility wraps
 * {@link com.stpl.app.service.impl.CompanyMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see CompanyMasterLocalService
 * @see com.stpl.app.service.base.CompanyMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.CompanyMasterLocalServiceImpl
 * @generated
 */
public class CompanyMasterLocalServiceUtil {
    private static CompanyMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.CompanyMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the company master to the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster addCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addCompanyMaster(companyMaster);
    }

    /**
    * Creates a new company master with the primary key. Does not add the company master to the database.
    *
    * @param companyMasterSid the primary key for the new company master
    * @return the new company master
    */
    public static com.stpl.app.model.CompanyMaster createCompanyMaster(
        int companyMasterSid) {
        return getService().createCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master that was removed
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the company master from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteCompanyMaster(companyMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.CompanyMaster fetchCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchCompanyMaster(companyMasterSid);
    }

    /**
    * Returns the company master with the primary key.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster getCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyMaster(companyMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> getCompanyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyMasters(start, end);
    }

    /**
    * Returns the number of company masters.
    *
    * @return the number of company masters
    * @throws SystemException if a system exception occurred
    */
    public static int getCompanyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyMastersCount();
    }

    /**
    * Updates the company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster updateCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateCompanyMaster(companyMaster);
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

    public static java.util.List findCompanyMaster(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder) {
        return getService()
                   .findCompanyMaster(companyId, companyNo, companyName,
            companyStatus, companyType, tradeClass, identifierType, identifier,
            orderByColumn, sortOrder);
    }

    public static java.util.List getCompanyTradeClass(int companySystemId) {
        return getService().getCompanyTradeClass(companySystemId);
    }

    public static java.util.List getCompanyTradeClassUniqueCheck(
        java.lang.String tradeClass, java.util.Date tradeStartDate) {
        return getService()
                   .getCompanyTradeClassUniqueCheck(tradeClass, tradeStartDate);
    }

    public static java.util.List deleteCompanyTradeClassForUpdate(
        int companySystemId) {
        return getService().deleteCompanyTradeClassForUpdate(companySystemId);
    }

    public static java.util.List findCompanyMasterV1(
        java.lang.String companyId, java.lang.String companyNo,
        java.lang.String companyName, java.lang.String companyStatus,
        java.lang.String companyType, java.lang.String companyCategory,
        java.lang.String companyGroup, java.lang.String tradeClass,
        int identifierType, java.lang.String identifier,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.lang.Object index, java.lang.Object next,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .findCompanyMasterV1(companyId, companyNo, companyName,
            companyStatus, companyType, companyCategory, companyGroup,
            tradeClass, identifierType, identifier, orderByColumn, sortOrder,
            index, next, parameters);
    }

    public static java.util.List getCustomerSearchDetails(
        java.lang.String customerNo, java.lang.String tradeClass,
        java.lang.String customerStatus, java.lang.String state,
        java.lang.String customerName, java.lang.String customerType,
        java.lang.String city, java.lang.String zipCode) {
        return getService()
                   .getCustomerSearchDetails(customerNo, tradeClass,
            customerStatus, state, customerName, customerType, city, zipCode);
    }

    public static java.util.List getPriorParentNo(
        java.lang.String priorSystemId) {
        return getService().getPriorParentNo(priorSystemId);
    }

    public static java.util.List getAvailableSearchResults(
        java.lang.String searchCriteria) {
        return getService().getAvailableSearchResults(searchCriteria);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getService().executeSelectQuery(query, udc1, udc2);
    }

    public static java.util.List searchTPCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().searchTPCompanies(parameters);
    }

    public static java.util.List getColumnNames(java.lang.String tablename) {
        return getService().getColumnNames(tablename);
    }

    public static java.util.List getCompanyTypeCount(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCompanyTypeCount(parameters);
    }

    public static java.util.List searchCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().searchCompanies(parameters);
    }

    public static java.util.List executeQuery(java.lang.String query) {
        return getService().executeQuery(query);
    }

    public static int executeUpdateQuery(java.lang.String query) {
        return getService().executeUpdateQuery(query);
    }

    public static void clearService() {
        _service = null;
    }

    public static CompanyMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CompanyMasterLocalService.class.getName());

            if (invokableLocalService instanceof CompanyMasterLocalService) {
                _service = (CompanyMasterLocalService) invokableLocalService;
            } else {
                _service = new CompanyMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(CompanyMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(CompanyMasterLocalService service) {
    }
}
