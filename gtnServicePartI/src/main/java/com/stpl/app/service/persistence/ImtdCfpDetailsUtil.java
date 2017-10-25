package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdCfpDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd cfp details service. This utility wraps {@link ImtdCfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdCfpDetailsPersistence
 * @see ImtdCfpDetailsPersistenceImpl
 * @generated
 */
public class ImtdCfpDetailsUtil {
    private static ImtdCfpDetailsPersistence _persistence;

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
    public static void clearCache(ImtdCfpDetails imtdCfpDetails) {
        getPersistence().clearCache(imtdCfpDetails);
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
    public static List<ImtdCfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdCfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdCfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdCfpDetails update(ImtdCfpDetails imtdCfpDetails)
        throws SystemException {
        return getPersistence().update(imtdCfpDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdCfpDetails update(ImtdCfpDetails imtdCfpDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdCfpDetails, serviceContext);
    }

    /**
    * Returns all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the matching imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findByImtdCfpSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Returns a range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @return the range of matching imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findByImtdCfpSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
            start, end);
    }

    /**
    * Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findByImtdCfpSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
            start, end, orderByComparator);
    }

    /**
    * Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd cfp details
    * @throws com.stpl.app.NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails findByImtdCfpSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch_First(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails fetchByImtdCfpSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByImtdCfpSearch_First(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd cfp details
    * @throws com.stpl.app.NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails findByImtdCfpSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch_Last(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails fetchByImtdCfpSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByImtdCfpSearch_Last(usersSid, sessionId,
            imtdCreatedDate, orderByComparator);
    }

    /**
    * Returns the imtd cfp detailses before and after the current imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param imtdCfpDetailsSid the primary key of the current imtd cfp details
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next imtd cfp details
    * @throws com.stpl.app.NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails[] findByImtdCfpSearch_PrevAndNext(
        int imtdCfpDetailsSid, java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByImtdCfpSearch_PrevAndNext(imtdCfpDetailsSid,
            usersSid, sessionId, imtdCreatedDate, orderByComparator);
    }

    /**
    * Removes all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByImtdCfpSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Returns the number of imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the number of matching imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByImtdCfpSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
    }

    /**
    * Caches the imtd cfp details in the entity cache if it is enabled.
    *
    * @param imtdCfpDetails the imtd cfp details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails) {
        getPersistence().cacheResult(imtdCfpDetails);
    }

    /**
    * Caches the imtd cfp detailses in the entity cache if it is enabled.
    *
    * @param imtdCfpDetailses the imtd cfp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdCfpDetails> imtdCfpDetailses) {
        getPersistence().cacheResult(imtdCfpDetailses);
    }

    /**
    * Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
    *
    * @param imtdCfpDetailsSid the primary key for the new imtd cfp details
    * @return the new imtd cfp details
    */
    public static com.stpl.app.model.ImtdCfpDetails create(
        int imtdCfpDetailsSid) {
        return getPersistence().create(imtdCfpDetailsSid);
    }

    /**
    * Removes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details that was removed
    * @throws com.stpl.app.NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails remove(
        int imtdCfpDetailsSid)
        throws com.stpl.app.NoSuchImtdCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdCfpDetailsSid);
    }

    public static com.stpl.app.model.ImtdCfpDetails updateImpl(
        com.stpl.app.model.ImtdCfpDetails imtdCfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdCfpDetails);
    }

    /**
    * Returns the imtd cfp details with the primary key or throws a {@link com.stpl.app.NoSuchImtdCfpDetailsException} if it could not be found.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details
    * @throws com.stpl.app.NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails findByPrimaryKey(
        int imtdCfpDetailsSid)
        throws com.stpl.app.NoSuchImtdCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdCfpDetailsSid);
    }

    /**
    * Returns the imtd cfp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdCfpDetailsSid the primary key of the imtd cfp details
    * @return the imtd cfp details, or <code>null</code> if a imtd cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdCfpDetails fetchByPrimaryKey(
        int imtdCfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdCfpDetailsSid);
    }

    /**
    * Returns all the imtd cfp detailses.
    *
    * @return the imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @return the range of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd cfp detailses
    * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdCfpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd cfp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd cfp detailses.
    *
    * @return the number of imtd cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdCfpDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdCfpDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdCfpDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdCfpDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdCfpDetailsPersistence persistence) {
    }
}
