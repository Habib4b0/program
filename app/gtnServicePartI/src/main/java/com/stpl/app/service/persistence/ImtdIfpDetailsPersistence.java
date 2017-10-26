package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdIfpDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdIfpDetailsPersistenceImpl
 * @see ImtdIfpDetailsUtil
 * @generated
 */
public interface ImtdIfpDetailsPersistence extends BasePersistence<ImtdIfpDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdIfpDetailsUtil} to access the imtd ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @return the matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId, java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findByTempIfpSearch(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdIfpDetails findByTempIfpSearch_First(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdIfpDetails fetchByTempIfpSearch_First(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdIfpDetails findByTempIfpSearch_Last(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdIfpDetails fetchByTempIfpSearch_Last(
        int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdIfpDetails[] findByTempIfpSearch_PrevAndNext(
        int imtdIfpDetailsSid, int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63; from the database.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @throws SystemException if a system exception occurred
    */
    public void removeByTempIfpSearch(int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreateddate the imtd createddate
    * @return the number of matching imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByTempIfpSearch(int usersSid, java.lang.String sessionId,
        java.util.Date imtdCreateddate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the imtd ifp details in the entity cache if it is enabled.
    *
    * @param imtdIfpDetails the imtd ifp details
    */
    public void cacheResult(com.stpl.app.model.ImtdIfpDetails imtdIfpDetails);

    /**
    * Caches the imtd ifp detailses in the entity cache if it is enabled.
    *
    * @param imtdIfpDetailses the imtd ifp detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdIfpDetails> imtdIfpDetailses);

    /**
    * Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
    *
    * @param imtdIfpDetailsSid the primary key for the new imtd ifp details
    * @return the new imtd ifp details
    */
    public com.stpl.app.model.ImtdIfpDetails create(int imtdIfpDetailsSid);

    /**
    * Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details that was removed
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdIfpDetails remove(int imtdIfpDetailsSid)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdIfpDetails updateImpl(
        com.stpl.app.model.ImtdIfpDetails imtdIfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd ifp details with the primary key or throws a {@link com.stpl.app.NoSuchImtdIfpDetailsException} if it could not be found.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details
    * @throws com.stpl.app.NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdIfpDetails findByPrimaryKey(
        int imtdIfpDetailsSid)
        throws com.stpl.app.NoSuchImtdIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdIfpDetailsSid the primary key of the imtd ifp details
    * @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdIfpDetails fetchByPrimaryKey(
        int imtdIfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd ifp detailses.
    *
    * @return the imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdIfpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd ifp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd ifp detailses.
    *
    * @return the number of imtd ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
