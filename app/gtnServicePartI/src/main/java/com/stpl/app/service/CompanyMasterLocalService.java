package com.stpl.app.service;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.transaction.Isolation;
import com.stpl.portal.kernel.transaction.Propagation;
import com.stpl.portal.kernel.transaction.Transactional;
import com.stpl.portal.service.BaseLocalService;
import com.stpl.portal.service.InvokableLocalService;
import com.stpl.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for CompanyMaster. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see CompanyMasterLocalServiceUtil
 * @see com.stpl.app.service.base.CompanyMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.CompanyMasterLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface CompanyMasterLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyMasterLocalServiceUtil} to access the company master local service. Add custom service methods to {@link com.stpl.app.service.impl.CompanyMasterLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the company master to the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was added
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster addCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Creates a new company master with the primary key. Does not add the company master to the database.
    *
    * @param companyMasterSid the primary key for the new company master
    * @return the new company master
    */
    public com.stpl.app.model.CompanyMaster createCompanyMaster(
        int companyMasterSid);

    /**
    * Deletes the company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master that was removed
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Deletes the company master from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.CompanyMaster fetchCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company master with the primary key.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.CompanyMaster getCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.stpl.app.model.CompanyMaster> getCompanyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters.
    *
    * @return the number of company masters
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getCompanyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Updates the company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster updateCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public java.util.List findCompanyMaster(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCompanyTradeClass(int companySystemId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCompanyTradeClassUniqueCheck(
        java.lang.String tradeClass, java.util.Date tradeStartDate);

    public java.util.List deleteCompanyTradeClassForUpdate(int companySystemId);

    public java.util.List findCompanyMasterV1(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String companyCategory, java.lang.String companyGroup,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder, java.lang.Object index,
        java.lang.Object next,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCustomerSearchDetails(
        java.lang.String customerNo, java.lang.String tradeClass,
        java.lang.String customerStatus, java.lang.String state,
        java.lang.String customerName, java.lang.String customerType,
        java.lang.String city, java.lang.String zipCode);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getPriorParentNo(java.lang.String priorSystemId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getAvailableSearchResults(
        java.lang.String searchCriteria);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List searchTPCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getColumnNames(java.lang.String tablename);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getCompanyTypeCount(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List searchCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List executeQuery(java.lang.String query);

    public int executeUpdateQuery(java.lang.String query);
}
