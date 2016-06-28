package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ContractMaster. This utility wraps
 * {@link com.stpl.app.service.impl.ContractMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ContractMasterLocalService
 * @see com.stpl.app.service.base.ContractMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ContractMasterLocalServiceImpl
 * @generated
 */
public class ContractMasterLocalServiceUtil {
    private static ContractMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ContractMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contract master to the database. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster addContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addContractMaster(contractMaster);
    }

    /**
    * Creates a new contract master with the primary key. Does not add the contract master to the database.
    *
    * @param contractMasterSid the primary key for the new contract master
    * @return the new contract master
    */
    public static com.stpl.app.model.ContractMaster createContractMaster(
        int contractMasterSid) {
        return getService().createContractMaster(contractMasterSid);
    }

    /**
    * Deletes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master that was removed
    * @throws PortalException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster deleteContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteContractMaster(contractMasterSid);
    }

    /**
    * Deletes the contract master from the database. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster deleteContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteContractMaster(contractMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ContractMaster fetchContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchContractMaster(contractMasterSid);
    }

    /**
    * Returns the contract master with the primary key.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master
    * @throws PortalException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster getContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getContractMaster(contractMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contract masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract masters
    * @param end the upper bound of the range of contract masters (not inclusive)
    * @return the range of contract masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ContractMaster> getContractMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getContractMasters(start, end);
    }

    /**
    * Returns the number of contract masters.
    *
    * @return the number of contract masters
    * @throws SystemException if a system exception occurred
    */
    public static int getContractMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getContractMastersCount();
    }

    /**
    * Updates the contract master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ContractMaster updateContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateContractMaster(contractMaster);
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

    public static java.util.List getContractPriceInfo(int contractSystemId,
        int cfpId, int ifpId, int psId) {
        return getService()
                   .getContractPriceInfo(contractSystemId, cfpId, ifpId, psId);
    }

    public static java.util.List getTradingPartnerList(
        java.lang.String companyId, java.lang.String companyNo,
        java.lang.String companyName, int companyStatus, int companyType,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orederBy) {
        return getService()
                   .getTradingPartnerList(companyId, companyNo, companyName,
            companyStatus, companyType, filterMap, start, offset, column,
            orederBy);
    }

    public static java.util.List getContractList(java.lang.String contractId,
        java.lang.String contractNo, java.lang.String contractName,
        int contractStatus, int contractType, java.lang.String tradeClass,
        int tradingPartner,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        java.lang.String orderBy, java.lang.String column, int start,
        int offset, boolean isCount) {
        return getService()
                   .getContractList(contractId, contractNo, contractName,
            contractStatus, contractType, tradeClass, tradingPartner,
            filterMap, orderBy, column, start, offset, isCount);
    }

    public static java.util.List fetchFieldsForSecurity(
        java.lang.String moduleName, java.lang.String tabName,
        java.lang.Object obj1, java.lang.Object obj2, java.lang.Object obj3) {
        return getService()
                   .fetchFieldsForSecurity(moduleName, tabName, obj1, obj2, obj3);
    }

    public static java.util.List searchContractsForPromoteTp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().searchContractsForPromoteTp(parameters);
    }

    public static java.lang.Object executeSelectQueries(
        java.lang.String[] queries) {
        return getService().executeSelectQueries(queries);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContractMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContractMasterLocalService.class.getName());

            if (invokableLocalService instanceof ContractMasterLocalService) {
                _service = (ContractMasterLocalService) invokableLocalService;
            } else {
                _service = new ContractMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ContractMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ContractMasterLocalService service) {
    }
}
