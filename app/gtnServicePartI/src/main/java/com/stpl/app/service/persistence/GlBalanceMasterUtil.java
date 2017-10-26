package com.stpl.app.service.persistence;

import com.stpl.app.model.GlBalanceMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the gl balance master service. This utility wraps {@link GlBalanceMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlBalanceMasterPersistence
 * @see GlBalanceMasterPersistenceImpl
 * @generated
 */
public class GlBalanceMasterUtil {
    private static GlBalanceMasterPersistence _persistence;

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
    public static void clearCache(GlBalanceMaster glBalanceMaster) {
        getPersistence().clearCache(glBalanceMaster);
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
    public static List<GlBalanceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<GlBalanceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<GlBalanceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static GlBalanceMaster update(GlBalanceMaster glBalanceMaster)
        throws SystemException {
        return getPersistence().update(glBalanceMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static GlBalanceMaster update(GlBalanceMaster glBalanceMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(glBalanceMaster, serviceContext);
    }

    /**
    * Returns all the gl balance masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountNo(accountNo);
    }

    /**
    * Returns a range of all the gl balance masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountNo(accountNo, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo(accountNo, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_First(accountNo, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountNo_First(accountNo, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_Last(accountNo, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountNo_Last(accountNo, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByAccountNo_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_PrevAndNext(glBalanceMasterSid, accountNo,
            orderByComparator);
    }

    /**
    * Removes all the gl balance masters where accountNo = &#63; from the database.
    *
    * @param accountNo the account no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAccountNo(accountNo);
    }

    /**
    * Returns the number of gl balance masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAccountNo(accountNo);
    }

    /**
    * Returns all the gl balance masters where isActive = &#63;.
    *
    * @param isActive the is active
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIsActive(isActive);
    }

    /**
    * Returns a range of all the gl balance masters where isActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param isActive the is active
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIsActive(isActive, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where isActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param isActive the is active
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIsActive(isActive, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByIsActive_First(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIsActive_First(isActive, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByIsActive_First(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIsActive_First(isActive, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByIsActive_Last(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIsActive_Last(isActive, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByIsActive_Last(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByIsActive_Last(isActive, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where isActive = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByIsActive_PrevAndNext(
        int glBalanceMasterSid, java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIsActive_PrevAndNext(glBalanceMasterSid, isActive,
            orderByComparator);
    }

    /**
    * Removes all the gl balance masters where isActive = &#63; from the database.
    *
    * @param isActive the is active
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIsActive(java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByIsActive(isActive);
    }

    /**
    * Returns the number of gl balance masters where isActive = &#63;.
    *
    * @param isActive the is active
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByIsActive(java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByIsActive(isActive);
    }

    /**
    * Returns all the gl balance masters where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByUploadDate(uploadDate);
    }

    /**
    * Returns a range of all the gl balance masters where uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByUploadDate(uploadDate, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUploadDate(uploadDate, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByUploadDate_First(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUploadDate_First(uploadDate, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByUploadDate_First(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUploadDate_First(uploadDate, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByUploadDate_Last(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUploadDate_Last(uploadDate, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByUploadDate_Last(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUploadDate_Last(uploadDate, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByUploadDate_PrevAndNext(
        int glBalanceMasterSid, java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUploadDate_PrevAndNext(glBalanceMasterSid,
            uploadDate, orderByComparator);
    }

    /**
    * Removes all the gl balance masters where uploadDate = &#63; from the database.
    *
    * @param uploadDate the upload date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUploadDate(java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByUploadDate(uploadDate);
    }

    /**
    * Returns the number of gl balance masters where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByUploadDate(java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByUploadDate(uploadDate);
    }

    /**
    * Returns all the gl balance masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId);
    }

    /**
    * Returns a range of all the gl balance masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId(accountId, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountId = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByAccountId_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_PrevAndNext(glBalanceMasterSid, accountId,
            orderByComparator);
    }

    /**
    * Removes all the gl balance masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAccountId(accountId);
    }

    /**
    * Returns the number of gl balance masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAccountId(accountId);
    }

    /**
    * Returns all the gl balance masters where year = &#63;.
    *
    * @param year the year
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByYear(year);
    }

    /**
    * Returns a range of all the gl balance masters where year = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param year the year
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByYear(year, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where year = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param year the year
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByYear(year, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByYear_First(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByYear_First(year, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByYear_First(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByYear_First(year, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByYear_Last(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByYear_Last(year, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByYear_Last(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByYear_Last(year, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where year = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByYear_PrevAndNext(
        int glBalanceMasterSid, java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByYear_PrevAndNext(glBalanceMasterSid, year,
            orderByComparator);
    }

    /**
    * Removes all the gl balance masters where year = &#63; from the database.
    *
    * @param year the year
    * @throws SystemException if a system exception occurred
    */
    public static void removeByYear(java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByYear(year);
    }

    /**
    * Returns the number of gl balance masters where year = &#63;.
    *
    * @param year the year
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByYear(java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByYear(year);
    }

    /**
    * Returns all the gl balance masters where period = &#63;.
    *
    * @param period the period
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod(period);
    }

    /**
    * Returns a range of all the gl balance masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod(period, start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPeriod(period, start, end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod_First(period, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPeriod_First(period, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod_Last(period, orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPeriod_Last(period, orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where period = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByPeriod_PrevAndNext(
        int glBalanceMasterSid, java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPeriod_PrevAndNext(glBalanceMasterSid, period,
            orderByComparator);
    }

    /**
    * Removes all the gl balance masters where period = &#63; from the database.
    *
    * @param period the period
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByPeriod(period);
    }

    /**
    * Returns the number of gl balance masters where period = &#63;.
    *
    * @param period the period
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByPeriod(period);
    }

    /**
    * Returns all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique(accountNo, period, uploadDate);
    }

    /**
    * Returns a range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique(accountNo, period, uploadDate, start,
            end);
    }

    /**
    * Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique(accountNo, period, uploadDate, start,
            end, orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByGlBalanceUnique_First(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique_First(accountNo, period, uploadDate,
            orderByComparator);
    }

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByGlBalanceUnique_First(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByGlBalanceUnique_First(accountNo, period, uploadDate,
            orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByGlBalanceUnique_Last(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique_Last(accountNo, period, uploadDate,
            orderByComparator);
    }

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByGlBalanceUnique_Last(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByGlBalanceUnique_Last(accountNo, period, uploadDate,
            orderByComparator);
    }

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster[] findByGlBalanceUnique_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGlBalanceUnique_PrevAndNext(glBalanceMasterSid,
            accountNo, period, uploadDate, orderByComparator);
    }

    /**
    * Removes all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63; from the database.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByGlBalanceUnique(java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByGlBalanceUnique(accountNo, period, uploadDate);
    }

    /**
    * Returns the number of gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByGlBalanceUnique(java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByGlBalanceUnique(accountNo, period, uploadDate);
    }

    /**
    * Caches the gl balance master in the entity cache if it is enabled.
    *
    * @param glBalanceMaster the gl balance master
    */
    public static void cacheResult(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster) {
        getPersistence().cacheResult(glBalanceMaster);
    }

    /**
    * Caches the gl balance masters in the entity cache if it is enabled.
    *
    * @param glBalanceMasters the gl balance masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.GlBalanceMaster> glBalanceMasters) {
        getPersistence().cacheResult(glBalanceMasters);
    }

    /**
    * Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
    *
    * @param glBalanceMasterSid the primary key for the new gl balance master
    * @return the new gl balance master
    */
    public static com.stpl.app.model.GlBalanceMaster create(
        int glBalanceMasterSid) {
        return getPersistence().create(glBalanceMasterSid);
    }

    /**
    * Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master that was removed
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster remove(
        int glBalanceMasterSid)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(glBalanceMasterSid);
    }

    public static com.stpl.app.model.GlBalanceMaster updateImpl(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(glBalanceMaster);
    }

    /**
    * Returns the gl balance master with the primary key or throws a {@link com.stpl.app.NoSuchGlBalanceMasterException} if it could not be found.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster findByPrimaryKey(
        int glBalanceMasterSid)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(glBalanceMasterSid);
    }

    /**
    * Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GlBalanceMaster fetchByPrimaryKey(
        int glBalanceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(glBalanceMasterSid);
    }

    /**
    * Returns all the gl balance masters.
    *
    * @return the gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the gl balance masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the gl balance masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GlBalanceMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the gl balance masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of gl balance masters.
    *
    * @return the number of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static GlBalanceMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (GlBalanceMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    GlBalanceMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(GlBalanceMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(GlBalanceMasterPersistence persistence) {
    }
}
