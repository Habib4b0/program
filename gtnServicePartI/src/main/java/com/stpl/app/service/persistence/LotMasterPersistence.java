package com.stpl.app.service.persistence;

import com.stpl.app.model.LotMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see LotMasterPersistenceImpl
 * @see LotMasterUtil
 * @generated
 */
public interface LotMasterPersistence extends BasePersistence<LotMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link LotMasterUtil} to access the lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the lot masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster[] findByItemId_PrevAndNext(
        int lotMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the lot masters where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters where lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters where lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotNo_First(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotNo_First(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotNo_Last(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotNo_Last(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster[] findByLotNo_PrevAndNext(
        int lotMasterSid, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters where lotNo = &#63; from the database.
    *
    * @param lotNo the lot no
    * @throws SystemException if a system exception occurred
    */
    public void removeByLotNo(java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLotNo(java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the lot masters where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters where lotExpirationDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotExpirationDate the lot expiration date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotExpirationDate the lot expiration date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotExpirationDate_First(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotExpirationDate_First(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotExpirationDate_Last(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotExpirationDate_Last(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster[] findByLotExpirationDate_PrevAndNext(
        int lotMasterSid, java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters where lotExpirationDate = &#63; from the database.
    *
    * @param lotExpirationDate the lot expiration date
    * @throws SystemException if a system exception occurred
    */
    public void removeByLotExpirationDate(java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLotExpirationDate(java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the lot masters where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters where lastLotSoldDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lastLotSoldDate the last lot sold date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lastLotSoldDate the last lot sold date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLastLotSoldDate_First(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLastLotSoldDate_First(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLastLotSoldDate_Last(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLastLotSoldDate_Last(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster[] findByLastLotSoldDate_PrevAndNext(
        int lotMasterSid, java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters where lastLotSoldDate = &#63; from the database.
    *
    * @param lastLotSoldDate the last lot sold date
    * @throws SystemException if a system exception occurred
    */
    public void removeByLastLotSoldDate(java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLastLotSoldDate(java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotUnique_First(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotUnique_First(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByLotUnique_Last(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByLotUnique_Last(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster[] findByLotUnique_PrevAndNext(
        int lotMasterSid, java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters where itemId = &#63; and lotNo = &#63; from the database.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @throws SystemException if a system exception occurred
    */
    public void removeByLotUnique(java.lang.String itemId,
        java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLotUnique(java.lang.String itemId, java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the lot master in the entity cache if it is enabled.
    *
    * @param lotMaster the lot master
    */
    public void cacheResult(com.stpl.app.model.LotMaster lotMaster);

    /**
    * Caches the lot masters in the entity cache if it is enabled.
    *
    * @param lotMasters the lot masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.LotMaster> lotMasters);

    /**
    * Creates a new lot master with the primary key. Does not add the lot master to the database.
    *
    * @param lotMasterSid the primary key for the new lot master
    * @return the new lot master
    */
    public com.stpl.app.model.LotMaster create(int lotMasterSid);

    /**
    * Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master that was removed
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster remove(int lotMasterSid)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.LotMaster updateImpl(
        com.stpl.app.model.LotMaster lotMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot master with the primary key or throws a {@link com.stpl.app.NoSuchLotMasterException} if it could not be found.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster findByPrimaryKey(int lotMasterSid)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.LotMaster fetchByPrimaryKey(int lotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the lot masters.
    *
    * @return the lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.LotMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the lot masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of lot masters.
    *
    * @return the number of lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
