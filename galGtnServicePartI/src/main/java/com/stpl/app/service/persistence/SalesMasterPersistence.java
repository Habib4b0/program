package com.stpl.app.service.persistence;

import com.stpl.app.model.SalesMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesMasterPersistenceImpl
 * @see SalesMasterUtil
 * @generated
 */
public interface SalesMasterPersistence extends BasePersistence<SalesMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SalesMasterUtil} to access the sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the sales masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where accountId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByAccountId_PrevAndNext(
        int salesMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where itemNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByItemNo_PrevAndNext(
        int salesMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where itemId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByItemId_PrevAndNext(
        int salesMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findBySalesId_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchBySalesId_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findBySalesId_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchBySalesId_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findBySalesId_PrevAndNext(
        int salesMasterSid, java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where salesId = &#63; from the database.
    *
    * @param salesId the sales ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySalesId(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countBySalesId(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where accountNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByAccountNo_PrevAndNext(
        int salesMasterSid, java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where accountNo = &#63; from the database.
    *
    * @param accountNo the account no
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where contractId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByContractId_PrevAndNext(
        int salesMasterSid, java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where contractId = &#63; from the database.
    *
    * @param contractId the contract ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where companyId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByCompanyId_PrevAndNext(
        int salesMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where contractNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findByContractNo_PrevAndNext(
        int salesMasterSid, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where contractNo = &#63; from the database.
    *
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findBySalesUnique_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchBySalesUnique_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findBySalesUnique_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchBySalesUnique_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster[] findBySalesUnique_PrevAndNext(
        int salesMasterSid, java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters where salesId = &#63; from the database.
    *
    * @param salesId the sales ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySalesUnique(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countBySalesUnique(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the sales master in the entity cache if it is enabled.
    *
    * @param salesMaster the sales master
    */
    public void cacheResult(com.stpl.app.model.SalesMaster salesMaster);

    /**
    * Caches the sales masters in the entity cache if it is enabled.
    *
    * @param salesMasters the sales masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.SalesMaster> salesMasters);

    /**
    * Creates a new sales master with the primary key. Does not add the sales master to the database.
    *
    * @param salesMasterSid the primary key for the new sales master
    * @return the new sales master
    */
    public com.stpl.app.model.SalesMaster create(int salesMasterSid);

    /**
    * Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master that was removed
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster remove(int salesMasterSid)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.SalesMaster updateImpl(
        com.stpl.app.model.SalesMaster salesMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales master with the primary key or throws a {@link com.stpl.app.NoSuchSalesMasterException} if it could not be found.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster findByPrimaryKey(int salesMasterSid)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesMaster fetchByPrimaryKey(int salesMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales masters.
    *
    * @return the sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sales masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales masters.
    *
    * @return the number of sales masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
