package com.stpl.app.service.persistence;

import com.stpl.app.model.PsDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsDetailsPersistenceImpl
 * @see PsDetailsUtil
 * @generated
 */
public interface PsDetailsPersistence extends BasePersistence<PsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PsDetailsUtil} to access the ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ps detailses where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @return the matching ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findByPriceScheduleMasterDetails(
        int psModelSid) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps detailses where psModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psModelSid the ps model sid
    * @param start the lower bound of the range of ps detailses
    * @param end the upper bound of the range of ps detailses (not inclusive)
    * @return the range of matching ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findByPriceScheduleMasterDetails(
        int psModelSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps detailses where psModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psModelSid the ps model sid
    * @param start the lower bound of the range of ps detailses
    * @param end the upper bound of the range of ps detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findByPriceScheduleMasterDetails(
        int psModelSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps details in the ordered set where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps details
    * @throws com.stpl.app.NoSuchPsDetailsException if a matching ps details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails findByPriceScheduleMasterDetails_First(
        int psModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ps details in the ordered set where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps details, or <code>null</code> if a matching ps details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails fetchByPriceScheduleMasterDetails_First(
        int psModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps details in the ordered set where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps details
    * @throws com.stpl.app.NoSuchPsDetailsException if a matching ps details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails findByPriceScheduleMasterDetails_Last(
        int psModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ps details in the ordered set where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps details, or <code>null</code> if a matching ps details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails fetchByPriceScheduleMasterDetails_Last(
        int psModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps detailses before and after the current ps details in the ordered set where psModelSid = &#63;.
    *
    * @param psDetailsSid the primary key of the current ps details
    * @param psModelSid the ps model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps details
    * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
        int psDetailsSid, int psModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps detailses where psModelSid = &#63; from the database.
    *
    * @param psModelSid the ps model sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByPriceScheduleMasterDetails(int psModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps detailses where psModelSid = &#63;.
    *
    * @param psModelSid the ps model sid
    * @return the number of matching ps detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByPriceScheduleMasterDetails(int psModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the ps details in the entity cache if it is enabled.
    *
    * @param psDetails the ps details
    */
    public void cacheResult(com.stpl.app.model.PsDetails psDetails);

    /**
    * Caches the ps detailses in the entity cache if it is enabled.
    *
    * @param psDetailses the ps detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.PsDetails> psDetailses);

    /**
    * Creates a new ps details with the primary key. Does not add the ps details to the database.
    *
    * @param psDetailsSid the primary key for the new ps details
    * @return the new ps details
    */
    public com.stpl.app.model.PsDetails create(int psDetailsSid);

    /**
    * Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psDetailsSid the primary key of the ps details
    * @return the ps details that was removed
    * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails remove(int psDetailsSid)
        throws com.stpl.app.NoSuchPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PsDetails updateImpl(
        com.stpl.app.model.PsDetails psDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps details with the primary key or throws a {@link com.stpl.app.NoSuchPsDetailsException} if it could not be found.
    *
    * @param psDetailsSid the primary key of the ps details
    * @return the ps details
    * @throws com.stpl.app.NoSuchPsDetailsException if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails findByPrimaryKey(int psDetailsSid)
        throws com.stpl.app.NoSuchPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psDetailsSid the primary key of the ps details
    * @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsDetails fetchByPrimaryKey(int psDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps detailses.
    *
    * @return the ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps detailses
    * @param end the upper bound of the range of ps detailses (not inclusive)
    * @return the range of ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps detailses
    * @param end the upper bound of the range of ps detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps detailses.
    *
    * @return the number of ps detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
