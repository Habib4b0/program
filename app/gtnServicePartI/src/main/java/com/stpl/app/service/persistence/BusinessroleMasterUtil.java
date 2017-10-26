package com.stpl.app.service.persistence;

import com.stpl.app.model.BusinessroleMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the businessrole master service. This utility wraps {@link BusinessroleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleMasterPersistence
 * @see BusinessroleMasterPersistenceImpl
 * @generated
 */
public class BusinessroleMasterUtil {
    private static BusinessroleMasterPersistence _persistence;

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
    public static void clearCache(BusinessroleMaster businessroleMaster) {
        getPersistence().clearCache(businessroleMaster);
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
    public static List<BusinessroleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<BusinessroleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<BusinessroleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static BusinessroleMaster update(
        BusinessroleMaster businessroleMaster) throws SystemException {
        return getPersistence().update(businessroleMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static BusinessroleMaster update(
        BusinessroleMaster businessroleMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(businessroleMaster, serviceContext);
    }

    /**
    * Returns all the businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBusinessroleName(businessroleName);
    }

    /**
    * Returns a range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessroleName(businessroleName, start, end);
    }

    /**
    * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessroleName(businessroleName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster findByBusinessroleName_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessroleName_First(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster fetchByBusinessroleName_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBusinessroleName_First(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster findByBusinessroleName_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessroleName_Last(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster fetchByBusinessroleName_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBusinessroleName_Last(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleMasterSid the primary key of the current businessrole master
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
        int businessroleMasterSid, java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessroleName_PrevAndNext(businessroleMasterSid,
            businessroleName, orderByComparator);
    }

    /**
    * Removes all the businessrole masters where businessroleName = &#63; from the database.
    *
    * @param businessroleName the businessrole name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBusinessroleName(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByBusinessroleName(businessroleName);
    }

    /**
    * Returns the number of businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the number of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByBusinessroleName(java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByBusinessroleName(businessroleName);
    }

    /**
    * Returns all the businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBusinessRoleUnique(businessroleName);
    }

    /**
    * Returns a range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessRoleUnique(businessroleName, start, end);
    }

    /**
    * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessRoleUnique(businessroleName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster findByBusinessRoleUnique_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessRoleUnique_First(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster fetchByBusinessRoleUnique_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBusinessRoleUnique_First(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster findByBusinessRoleUnique_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessRoleUnique_Last(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster fetchByBusinessRoleUnique_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBusinessRoleUnique_Last(businessroleName,
            orderByComparator);
    }

    /**
    * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleMasterSid the primary key of the current businessrole master
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
        int businessroleMasterSid, java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBusinessRoleUnique_PrevAndNext(businessroleMasterSid,
            businessroleName, orderByComparator);
    }

    /**
    * Removes all the businessrole masters where businessroleName = &#63; from the database.
    *
    * @param businessroleName the businessrole name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBusinessRoleUnique(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByBusinessRoleUnique(businessroleName);
    }

    /**
    * Returns the number of businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the number of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByBusinessRoleUnique(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByBusinessRoleUnique(businessroleName);
    }

    /**
    * Caches the businessrole master in the entity cache if it is enabled.
    *
    * @param businessroleMaster the businessrole master
    */
    public static void cacheResult(
        com.stpl.app.model.BusinessroleMaster businessroleMaster) {
        getPersistence().cacheResult(businessroleMaster);
    }

    /**
    * Caches the businessrole masters in the entity cache if it is enabled.
    *
    * @param businessroleMasters the businessrole masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.BusinessroleMaster> businessroleMasters) {
        getPersistence().cacheResult(businessroleMasters);
    }

    /**
    * Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
    *
    * @param businessroleMasterSid the primary key for the new businessrole master
    * @return the new businessrole master
    */
    public static com.stpl.app.model.BusinessroleMaster create(
        int businessroleMasterSid) {
        return getPersistence().create(businessroleMasterSid);
    }

    /**
    * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master that was removed
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster remove(
        int businessroleMasterSid)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(businessroleMasterSid);
    }

    public static com.stpl.app.model.BusinessroleMaster updateImpl(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(businessroleMaster);
    }

    /**
    * Returns the businessrole master with the primary key or throws a {@link com.stpl.app.NoSuchBusinessroleMasterException} if it could not be found.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster findByPrimaryKey(
        int businessroleMasterSid)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(businessroleMasterSid);
    }

    /**
    * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleMaster fetchByPrimaryKey(
        int businessroleMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(businessroleMasterSid);
    }

    /**
    * Returns all the businessrole masters.
    *
    * @return the businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the businessrole masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the businessrole masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the businessrole masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of businessrole masters.
    *
    * @return the number of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static BusinessroleMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (BusinessroleMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    BusinessroleMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(BusinessroleMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(BusinessroleMasterPersistence persistence) {
    }
}
