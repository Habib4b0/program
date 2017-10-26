package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the company master service. This utility wraps {@link CompanyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyMasterPersistence
 * @see CompanyMasterPersistenceImpl
 * @generated
 */
public class CompanyMasterUtil {
    private static CompanyMasterPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache(com.stpl.portal.model.BaseModel)
     */
    public static void clearCache(CompanyMaster companyMaster) {
        getPersistence().clearCache(companyMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<CompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CompanyMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CompanyMaster update(CompanyMaster companyMaster)
        throws SystemException {
        return getPersistence().update(companyMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CompanyMaster update(CompanyMaster companyMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(companyMaster, serviceContext);
    }

    /**
    * Returns all the company masters where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyNo(companyNo);
    }

    /**
    * Returns a range of all the company masters where companyNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyNo the company no
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyNo(companyNo, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyNo the company no
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyNo(companyNo, start, end, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyNo_First(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyNo_First(companyNo, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyNo_First(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyNo_First(companyNo, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyNo_Last(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyNo_Last(companyNo, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyNo_Last(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyNo_Last(companyNo, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyNo_PrevAndNext(
        int companyMasterSid, java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyNo_PrevAndNext(companyMasterSid, companyNo,
            orderByComparator);
    }

    /**
    * Removes all the company masters where companyNo = &#63; from the database.
    *
    * @param companyNo the company no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyNo(java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyNo(companyNo);
    }

    /**
    * Returns the number of company masters where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyNo(java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyNo(companyNo);
    }

    /**
    * Returns all the company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

    /**
    * Returns a range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyId_PrevAndNext(
        int companyMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(companyMasterSid, companyId,
            orderByComparator);
    }

    /**
    * Removes all the company masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Returns the number of company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns all the company masters where companyName = &#63;.
    *
    * @param companyName the company name
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyName(companyName);
    }

    /**
    * Returns a range of all the company masters where companyName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyName the company name
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyName(companyName, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyName the company name
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyName(companyName, start, end, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyName_First(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyName_First(companyName, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyName_First(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyName_First(companyName, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyName_Last(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyName_Last(companyName, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyName_Last(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyName_Last(companyName, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyName_PrevAndNext(
        int companyMasterSid, java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyName_PrevAndNext(companyMasterSid,
            companyName, orderByComparator);
    }

    /**
    * Removes all the company masters where companyName = &#63; from the database.
    *
    * @param companyName the company name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyName(java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyName(companyName);
    }

    /**
    * Returns the number of company masters where companyName = &#63;.
    *
    * @param companyName the company name
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyName(java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyName(companyName);
    }

    /**
    * Returns all the company masters where companyType = &#63;.
    *
    * @param companyType the company type
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyType(companyType);
    }

    /**
    * Returns a range of all the company masters where companyType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyType the company type
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyType(companyType, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyType the company type
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyType(companyType, start, end, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyType_First(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyType_First(companyType, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyType_First(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyType_First(companyType, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyType_Last(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyType_Last(companyType, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyType_Last(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyType_Last(companyType, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyType_PrevAndNext(
        int companyMasterSid, int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyType_PrevAndNext(companyMasterSid,
            companyType, orderByComparator);
    }

    /**
    * Removes all the company masters where companyType = &#63; from the database.
    *
    * @param companyType the company type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyType(int companyType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyType(companyType);
    }

    /**
    * Returns the number of company masters where companyType = &#63;.
    *
    * @param companyType the company type
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyType(int companyType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyType(companyType);
    }

    /**
    * Returns all the company masters where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyStatus(companyStatus);
    }

    /**
    * Returns a range of all the company masters where companyStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyStatus the company status
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyStatus(companyStatus, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyStatus the company status
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyStatus(companyStatus, start, end,
            orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyStatus_First(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyStatus_First(companyStatus, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyStatus_First(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyStatus_First(companyStatus, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyStatus_Last(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyStatus_Last(companyStatus, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyStatus_Last(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyStatus_Last(companyStatus, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyStatus_PrevAndNext(
        int companyMasterSid, int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyStatus_PrevAndNext(companyMasterSid,
            companyStatus, orderByComparator);
    }

    /**
    * Removes all the company masters where companyStatus = &#63; from the database.
    *
    * @param companyStatus the company status
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyStatus(int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyStatus(companyStatus);
    }

    /**
    * Returns the number of company masters where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyStatus(int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyStatus(companyStatus);
    }

    /**
    * Returns all the company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyUnique(companyId);
    }

    /**
    * Returns a range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyUnique(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyUnique(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyUnique_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyUnique_First(companyId, orderByComparator);
    }

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyUnique_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyUnique_First(companyId, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByCompanyUnique_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyUnique_Last(companyId, orderByComparator);
    }

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByCompanyUnique_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyUnique_Last(companyId, orderByComparator);
    }

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster[] findByCompanyUnique_PrevAndNext(
        int companyMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyUnique_PrevAndNext(companyMasterSid,
            companyId, orderByComparator);
    }

    /**
    * Removes all the company masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyUnique(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyUnique(companyId);
    }

    /**
    * Returns the number of company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyUnique(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyUnique(companyId);
    }

    /**
    * Caches the company master in the entity cache if it is enabled.
    *
    * @param companyMaster the company master
    */
    public static void cacheResult(
        com.stpl.app.model.CompanyMaster companyMaster) {
        getPersistence().cacheResult(companyMaster);
    }

    /**
    * Caches the company masters in the entity cache if it is enabled.
    *
    * @param companyMasters the company masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CompanyMaster> companyMasters) {
        getPersistence().cacheResult(companyMasters);
    }

    /**
    * Creates a new company master with the primary key. Does not add the company master to the database.
    *
    * @param companyMasterSid the primary key for the new company master
    * @return the new company master
    */
    public static com.stpl.app.model.CompanyMaster create(int companyMasterSid) {
        return getPersistence().create(companyMasterSid);
    }

    /**
    * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master that was removed
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster remove(int companyMasterSid)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(companyMasterSid);
    }

    public static com.stpl.app.model.CompanyMaster updateImpl(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(companyMaster);
    }

    /**
    * Returns the company master with the primary key or throws a {@link com.stpl.app.NoSuchCompanyMasterException} if it could not be found.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster findByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(companyMasterSid);
    }

    /**
    * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master, or <code>null</code> if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CompanyMaster fetchByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(companyMasterSid);
    }

    /**
    * Returns all the company masters.
    *
    * @return the company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.stpl.app.model.CompanyMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CompanyMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the company masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of company masters.
    *
    * @return the number of company masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CompanyMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CompanyMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(CompanyMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CompanyMasterPersistence persistence) {
    }
}
