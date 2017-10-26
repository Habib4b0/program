package com.stpl.app.service.persistence;

import com.stpl.app.model.CpiIndexMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cpi index master service. This utility wraps {@link CpiIndexMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CpiIndexMasterPersistence
 * @see CpiIndexMasterPersistenceImpl
 * @generated
 */
public class CpiIndexMasterUtil {
    private static CpiIndexMasterPersistence _persistence;

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
    public static void clearCache(CpiIndexMaster cpiIndexMaster) {
        getPersistence().clearCache(cpiIndexMaster);
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
    public static List<CpiIndexMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CpiIndexMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CpiIndexMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CpiIndexMaster update(CpiIndexMaster cpiIndexMaster)
        throws SystemException {
        return getPersistence().update(cpiIndexMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CpiIndexMaster update(CpiIndexMaster cpiIndexMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cpiIndexMaster, serviceContext);
    }

    /**
    * Returns all the cpi index masters where status = &#63;.
    *
    * @param status the status
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStatus(status);
    }

    /**
    * Returns a range of all the cpi index masters where status = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param status the status
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStatus(status, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where status = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param status the status
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStatus(status, start, end, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByStatus_First(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStatus_First(status, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByStatus_First(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByStatus_First(status, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByStatus_Last(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStatus_Last(status, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByStatus_Last(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByStatus_Last(status, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where status = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByStatus_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStatus_PrevAndNext(cpiIndexMasterSid, status,
            orderByComparator);
    }

    /**
    * Removes all the cpi index masters where status = &#63; from the database.
    *
    * @param status the status
    * @throws SystemException if a system exception occurred
    */
    public static void removeByStatus(java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByStatus(status);
    }

    /**
    * Returns the number of cpi index masters where status = &#63;.
    *
    * @param status the status
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByStatus(java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByStatus(status);
    }

    /**
    * Returns all the cpi index masters where indexId = &#63;.
    *
    * @param indexId the index ID
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexId(indexId);
    }

    /**
    * Returns a range of all the cpi index masters where indexId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexId(indexId, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where indexId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexId(indexId, start, end, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexId_First(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexId_First(indexId, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexId_First(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByIndexId_First(indexId, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexId_Last(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexId_Last(indexId, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexId_Last(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByIndexId_Last(indexId, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByIndexId_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexId_PrevAndNext(cpiIndexMasterSid, indexId,
            orderByComparator);
    }

    /**
    * Removes all the cpi index masters where indexId = &#63; from the database.
    *
    * @param indexId the index ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIndexId(java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByIndexId(indexId);
    }

    /**
    * Returns the number of cpi index masters where indexId = &#63;.
    *
    * @param indexId the index ID
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByIndexId(java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByIndexId(indexId);
    }

    /**
    * Returns all the cpi index masters where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexValue(indexValue);
    }

    /**
    * Returns a range of all the cpi index masters where indexValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexValue the index value
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexValue(indexValue, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where indexValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexValue the index value
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexValue(indexValue, start, end, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexValue_First(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexValue_First(indexValue, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexValue_First(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIndexValue_First(indexValue, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexValue_Last(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexValue_Last(indexValue, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexValue_Last(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIndexValue_Last(indexValue, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByIndexValue_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexValue_PrevAndNext(cpiIndexMasterSid, indexValue,
            orderByComparator);
    }

    /**
    * Removes all the cpi index masters where indexValue = &#63; from the database.
    *
    * @param indexValue the index value
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIndexValue(java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByIndexValue(indexValue);
    }

    /**
    * Returns the number of cpi index masters where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByIndexValue(java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByIndexValue(indexValue);
    }

    /**
    * Returns all the cpi index masters where indexType = &#63;.
    *
    * @param indexType the index type
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexType(indexType);
    }

    /**
    * Returns a range of all the cpi index masters where indexType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexType the index type
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByIndexType(indexType, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where indexType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexType the index type
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexType(indexType, start, end, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexType_First(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexType_First(indexType, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexType_First(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIndexType_First(indexType, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByIndexType_Last(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexType_Last(indexType, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByIndexType_Last(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByIndexType_Last(indexType, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexType = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByIndexType_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByIndexType_PrevAndNext(cpiIndexMasterSid, indexType,
            orderByComparator);
    }

    /**
    * Removes all the cpi index masters where indexType = &#63; from the database.
    *
    * @param indexType the index type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByIndexType(java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByIndexType(indexType);
    }

    /**
    * Returns the number of cpi index masters where indexType = &#63;.
    *
    * @param indexType the index type
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByIndexType(java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByIndexType(indexType);
    }

    /**
    * Returns all the cpi index masters where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEffectiveDate(effectiveDate);
    }

    /**
    * Returns a range of all the cpi index masters where effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEffectiveDate(effectiveDate, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEffectiveDate(effectiveDate, start, end,
            orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByEffectiveDate_First(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEffectiveDate_First(effectiveDate, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByEffectiveDate_First(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByEffectiveDate_First(effectiveDate, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByEffectiveDate_Last(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEffectiveDate_Last(effectiveDate, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByEffectiveDate_Last(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByEffectiveDate_Last(effectiveDate, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByEffectiveDate_PrevAndNext(
        int cpiIndexMasterSid, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEffectiveDate_PrevAndNext(cpiIndexMasterSid,
            effectiveDate, orderByComparator);
    }

    /**
    * Removes all the cpi index masters where effectiveDate = &#63; from the database.
    *
    * @param effectiveDate the effective date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByEffectiveDate(java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByEffectiveDate(effectiveDate);
    }

    /**
    * Returns the number of cpi index masters where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByEffectiveDate(java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByEffectiveDate(effectiveDate);
    }

    /**
    * Returns all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique(indexId, status, indexType,
            effectiveDate);
    }

    /**
    * Returns a range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique(indexId, status, indexType,
            effectiveDate, start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique(indexId, status, indexType,
            effectiveDate, start, end, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByCpiIndexUnique_First(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique_First(indexId, status, indexType,
            effectiveDate, orderByComparator);
    }

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByCpiIndexUnique_First(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCpiIndexUnique_First(indexId, status, indexType,
            effectiveDate, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByCpiIndexUnique_Last(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique_Last(indexId, status, indexType,
            effectiveDate, orderByComparator);
    }

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByCpiIndexUnique_Last(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCpiIndexUnique_Last(indexId, status, indexType,
            effectiveDate, orderByComparator);
    }

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster[] findByCpiIndexUnique_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCpiIndexUnique_PrevAndNext(cpiIndexMasterSid,
            indexId, status, indexType, effectiveDate, orderByComparator);
    }

    /**
    * Removes all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63; from the database.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCpiIndexUnique(java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByCpiIndexUnique(indexId, status, indexType, effectiveDate);
    }

    /**
    * Returns the number of cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCpiIndexUnique(java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCpiIndexUnique(indexId, status, indexType,
            effectiveDate);
    }

    /**
    * Caches the cpi index master in the entity cache if it is enabled.
    *
    * @param cpiIndexMaster the cpi index master
    */
    public static void cacheResult(
        com.stpl.app.model.CpiIndexMaster cpiIndexMaster) {
        getPersistence().cacheResult(cpiIndexMaster);
    }

    /**
    * Caches the cpi index masters in the entity cache if it is enabled.
    *
    * @param cpiIndexMasters the cpi index masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CpiIndexMaster> cpiIndexMasters) {
        getPersistence().cacheResult(cpiIndexMasters);
    }

    /**
    * Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
    *
    * @param cpiIndexMasterSid the primary key for the new cpi index master
    * @return the new cpi index master
    */
    public static com.stpl.app.model.CpiIndexMaster create(
        int cpiIndexMasterSid) {
        return getPersistence().create(cpiIndexMasterSid);
    }

    /**
    * Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master that was removed
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster remove(
        int cpiIndexMasterSid)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cpiIndexMasterSid);
    }

    public static com.stpl.app.model.CpiIndexMaster updateImpl(
        com.stpl.app.model.CpiIndexMaster cpiIndexMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cpiIndexMaster);
    }

    /**
    * Returns the cpi index master with the primary key or throws a {@link com.stpl.app.NoSuchCpiIndexMasterException} if it could not be found.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster findByPrimaryKey(
        int cpiIndexMasterSid)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cpiIndexMasterSid);
    }

    /**
    * Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CpiIndexMaster fetchByPrimaryKey(
        int cpiIndexMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cpiIndexMasterSid);
    }

    /**
    * Returns all the cpi index masters.
    *
    * @return the cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cpi index masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cpi index masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CpiIndexMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cpi index masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cpi index masters.
    *
    * @return the number of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CpiIndexMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CpiIndexMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CpiIndexMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(CpiIndexMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CpiIndexMasterPersistence persistence) {
    }
}
