package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for CompanyTradeClass. This utility wraps
 * {@link com.stpl.app.service.impl.CompanyTradeClassLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see CompanyTradeClassLocalService
 * @see com.stpl.app.service.base.CompanyTradeClassLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.CompanyTradeClassLocalServiceImpl
 * @generated
 */
public class CompanyTradeClassLocalServiceUtil {
    private static CompanyTradeClassLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.CompanyTradeClassLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the company trade class to the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClass the company trade class
    * @return the company trade class that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass addCompanyTradeClass(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addCompanyTradeClass(companyTradeClass);
    }

    /**
    * Creates a new company trade class with the primary key. Does not add the company trade class to the database.
    *
    * @param companyTradeClassSid the primary key for the new company trade class
    * @return the new company trade class
    */
    public static com.stpl.app.model.CompanyTradeClass createCompanyTradeClass(
        int companyTradeClassSid) {
        return getService().createCompanyTradeClass(companyTradeClassSid);
    }

    /**
    * Deletes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class that was removed
    * @throws PortalException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass deleteCompanyTradeClass(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteCompanyTradeClass(companyTradeClassSid);
    }

    /**
    * Deletes the company trade class from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClass the company trade class
    * @return the company trade class that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass deleteCompanyTradeClass(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteCompanyTradeClass(companyTradeClass);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.CompanyTradeClass fetchCompanyTradeClass(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchCompanyTradeClass(companyTradeClassSid);
    }

    /**
    * Returns the company trade class with the primary key.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class
    * @throws PortalException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass getCompanyTradeClass(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyTradeClass(companyTradeClassSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company trade classes
    * @param end the upper bound of the range of company trade classes (not inclusive)
    * @return the range of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyTradeClass> getCompanyTradeClasses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyTradeClasses(start, end);
    }

    /**
    * Returns the number of company trade classes.
    *
    * @return the number of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static int getCompanyTradeClassesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getCompanyTradeClassesCount();
    }

    /**
    * Updates the company trade class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClass the company trade class
    * @return the company trade class that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyTradeClass updateCompanyTradeClass(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateCompanyTradeClass(companyTradeClass);
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

    public static java.util.List getTradeClassDetails(
        java.lang.String companySystemId, java.lang.String tradeClassSysId) {
        return getService()
                   .getTradeClassDetails(companySystemId, tradeClassSysId);
    }

    public static java.util.List getCompanyparentDetails(
        java.lang.String companySystemId, java.lang.String parentSysId) {
        return getService().getCompanyparentDetails(companySystemId, parentSysId);
    }

    public static void clearService() {
        _service = null;
    }

    public static CompanyTradeClassLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CompanyTradeClassLocalService.class.getName());

            if (invokableLocalService instanceof CompanyTradeClassLocalService) {
                _service = (CompanyTradeClassLocalService) invokableLocalService;
            } else {
                _service = new CompanyTradeClassLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(CompanyTradeClassLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(CompanyTradeClassLocalService service) {
    }
}
