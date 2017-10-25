package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd rs details service. This utility wraps {@link ImtdRsDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsPersistence
 * @see ImtdRsDetailsPersistenceImpl
 * @generated
 */
public class ImtdRsDetailsUtil {
    private static ImtdRsDetailsPersistence _persistence;

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
    public static void clearCache(ImtdRsDetails imtdRsDetails) {
        getPersistence().clearCache(imtdRsDetails);
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
    public static List<ImtdRsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdRsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdRsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdRsDetails update(ImtdRsDetails imtdRsDetails)
        throws SystemException {
        return getPersistence().update(imtdRsDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdRsDetails update(ImtdRsDetails imtdRsDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdRsDetails, serviceContext);
    }

    /**
    * Returns all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Returns a range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @return the range of matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch(usersSid, sessionId, imtdCreatedDate,
            start, end);
    }

    /**
    * Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch(usersSid, sessionId, imtdCreatedDate,
            start, end, orderByComparator);
    }

    /**
    * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd rs details
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails findByTempRsSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch_First(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails fetchByTempRsSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTempRsSearch_First(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd rs details
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails findByTempRsSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch_Last(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails fetchByTempRsSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTempRsSearch_Last(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the imtd rs detailses before and after the current imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param imtdRsDetailsSid the primary key of the current imtd rs details
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next imtd rs details
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails[] findByTempRsSearch_PrevAndNext(
        int imtdRsDetailsSid, java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTempRsSearch_PrevAndNext(imtdRsDetailsSid, usersSid,
            sessionId, imtdCreatedDate, orderByComparator);
    }

    /**
    * Removes all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTempRsSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByTempRsSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Returns the number of imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the number of matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByTempRsSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByTempRsSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Caches the imtd rs details in the entity cache if it is enabled.
    *
    * @param imtdRsDetails the imtd rs details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails) {
        getPersistence().cacheResult(imtdRsDetails);
    }

    /**
    * Caches the imtd rs detailses in the entity cache if it is enabled.
    *
    * @param imtdRsDetailses the imtd rs detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsDetails> imtdRsDetailses) {
        getPersistence().cacheResult(imtdRsDetailses);
    }

    /**
    * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
    *
    * @param imtdRsDetailsSid the primary key for the new imtd rs details
    * @return the new imtd rs details
    */
    public static com.stpl.app.model.ImtdRsDetails create(int imtdRsDetailsSid) {
        return getPersistence().create(imtdRsDetailsSid);
    }

    /**
    * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details that was removed
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails remove(int imtdRsDetailsSid)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdRsDetailsSid);
    }

    public static com.stpl.app.model.ImtdRsDetails updateImpl(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdRsDetails);
    }

    /**
    * Returns the imtd rs details with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsException} if it could not be found.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails findByPrimaryKey(
        int imtdRsDetailsSid)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdRsDetailsSid);
    }

    /**
    * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetails fetchByPrimaryKey(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdRsDetailsSid);
    }

    /**
    * Returns all the imtd rs detailses.
    *
    * @return the imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @return the range of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs detailses
    * @param end the upper bound of the range of imtd rs detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd rs detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd rs detailses.
    *
    * @return the number of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdRsDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdRsDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdRsDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdRsDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdRsDetailsPersistence persistence) {
    }
}
