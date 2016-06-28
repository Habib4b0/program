package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdIfpDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd ifp details service. This utility wraps {@link ImtdIfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdIfpDetailsPersistence
 * @see ImtdIfpDetailsPersistenceImpl
 * @generated
 */
public class ImtdIfpDetailsUtil {
    private static ImtdIfpDetailsPersistence _persistence;

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
    public static void clearCache(ImtdIfpDetails imtdIfpDetails) {
        getPersistence().clearCache(imtdIfpDetails);
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
    public static List<ImtdIfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdIfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdIfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdIfpDetails update(ImtdIfpDetails imtdIfpDetails)
        throws SystemException {
        return getPersistence().update(imtdIfpDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdIfpDetails update(ImtdIfpDetails imtdIfpDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdIfpDetails, serviceContext);
    }

    /**
    * Returns all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @return the matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId, java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
    }

    /**
    * Returns a range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param start the lower bound of the range of imtd ifp detailses
    * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
    * @return the range of matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
            start, end);
    }

    /**
    * Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param start the lower bound of the range of imtd ifp detailses
    * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
            start, end, orderByComparator);
    }

    /**
    * Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd ifp details
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails findByTempIfpSearch_First(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch_First(usersSid, sessionId,
            imtdCreateddate, orderByComparator);
    }

    /**
    * Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails fetchByTempIfpSearch_First(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTempIfpSearch_First(usersSid, sessionId,
            imtdCreateddate, orderByComparator);
    }

    /**
    * Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd ifp details
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails findByTempIfpSearch_Last(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch_Last(usersSid, sessionId,
            imtdCreateddate, orderByComparator);
    }

    /**
    * Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails fetchByTempIfpSearch_Last(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTempIfpSearch_Last(usersSid, sessionId,
            imtdCreateddate, orderByComparator);
    }

    /**
    * Returns the imtd ifp detailses before and after the current imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param imtdIfpDetailsSid the primary key of the current imtd ifp details
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next imtd ifp details
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails[] findByTempIfpSearch_PrevAndNext(
        int imtdIfpDetailsSid, int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempIfpSearch_PrevAndNext(imtdIfpDetailsSid,
            usersSid, sessionId, imtdCreateddate, orderByComparator);
    }

    /**
    * Removes all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63; from the database.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTempIfpSearch(int usersSid,
        java.lang.String sessionId, java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
    }

    /**
    * Returns the number of imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @return the number of matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByTempIfpSearch(int usersSid,
        java.lang.String sessionId, java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
    }

    /**
    * Caches the imtd ifp details in the entity cache if it is enabled.
    *
    * @param imtdIfpDetails the imtd ifp details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails) {
        getPersistence().cacheResult(imtdIfpDetails);
    }

    /**
    * Caches the imtd ifp detailses in the entity cache if it is enabled.
    *
    * @param imtdIfpDetailses the imtd ifp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdIfpDetails> imtdIfpDetailses) {
        getPersistence().cacheResult(imtdIfpDetailses);
    }

    /**
    * Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
    *
    * @param imtdIfpDetailsSid the primary key for the new imtd ifp details
    * @return the new imtd ifp details
    */
    public static com.stpl.app.model.ImtdIfpDetails create(
        int imtdIfpDetailsSid) {
        return getPersistence().create(imtdIfpDetailsSid);
    }

    /**
    * Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details that was removed
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails remove(
        int imtdIfpDetailsSid)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdIfpDetailsSid);
    }

    public static com.stpl.app.model.ImtdIfpDetails updateImpl(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdIfpDetails);
    }

    /**
    * Returns the imtd ifp details with the primary key or throws a {@link com.stpl.app.NoSuchImtdIfpDetailsException} if it could not be found.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails findByPrimaryKey(
        int imtdIfpDetailsSid)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdIfpDetailsSid);
    }

    /**
    * Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdIfpDetails fetchByPrimaryKey(
        int imtdIfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdIfpDetailsSid);
    }

    /**
    * Returns all the imtd ifp detailses.
    *
    * @return the imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ifp detailses
    * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
    * @return the range of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ifp detailses
    * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd ifp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd ifp detailses.
    *
    * @return the number of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdIfpDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdIfpDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdIfpDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdIfpDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdIfpDetailsPersistence persistence) {
    }
}
