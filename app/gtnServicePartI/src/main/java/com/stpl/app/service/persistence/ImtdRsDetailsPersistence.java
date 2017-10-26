package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsPersistenceImpl
 * @see ImtdRsDetailsUtil
 * @generated
 */
public interface ImtdRsDetailsPersistence extends BasePersistence<ImtdRsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdRsDetailsUtil} to access the imtd rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findByTempRsSearch(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdRsDetails findByTempRsSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdRsDetails fetchByTempRsSearch_First(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdRsDetails findByTempRsSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdRsDetails fetchByTempRsSearch_Last(
        java.lang.String usersSid, java.lang.String sessionId,
        java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public com.stpl.app.model.ImtdRsDetails[] findByTempRsSearch_PrevAndNext(
        int imtdRsDetailsSid, java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @throws SystemException if a system exception occurred
    */
    public void removeByTempRsSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
    *
    * @param usersSid the users sid
    * @param sessionId the session ID
    * @param imtdCreatedDate the imtd created date
    * @return the number of matching imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByTempRsSearch(java.lang.String usersSid,
        java.lang.String sessionId, java.util.Date imtdCreatedDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the imtd rs details in the entity cache if it is enabled.
    *
    * @param imtdRsDetails the imtd rs details
    */
    public void cacheResult(com.stpl.app.model.ImtdRsDetails imtdRsDetails);

    /**
    * Caches the imtd rs detailses in the entity cache if it is enabled.
    *
    * @param imtdRsDetailses the imtd rs detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsDetails> imtdRsDetailses);

    /**
    * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
    *
    * @param imtdRsDetailsSid the primary key for the new imtd rs details
    * @return the new imtd rs details
    */
    public com.stpl.app.model.ImtdRsDetails create(int imtdRsDetailsSid);

    /**
    * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details that was removed
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetails remove(int imtdRsDetailsSid)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdRsDetails updateImpl(
        com.stpl.app.model.ImtdRsDetails imtdRsDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs details with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsException} if it could not be found.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details
    * @throws com.stpl.app.NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetails findByPrimaryKey(
        int imtdRsDetailsSid)
        throws com.stpl.app.NoSuchImtdRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsDetailsSid the primary key of the imtd rs details
    * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetails fetchByPrimaryKey(
        int imtdRsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd rs detailses.
    *
    * @return the imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ImtdRsDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd rs detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd rs detailses.
    *
    * @return the number of imtd rs detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
