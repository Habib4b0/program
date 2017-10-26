package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemMasterPersistenceImpl
 * @see ItemMasterUtil
 * @generated
 */
public interface ItemMasterPersistence extends BasePersistence<ItemMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemMasterUtil} to access the item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where itemNo = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByItemNo_PrevAndNext(
        int itemMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where itemId = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByItemId_PrevAndNext(
        int itemMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where itemName = &#63;.
    *
    * @param itemName the item name
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemName(
        java.lang.String itemName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where itemName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemName the item name
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemName(
        java.lang.String itemName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where itemName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemName the item name
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemName(
        java.lang.String itemName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemName = &#63;.
    *
    * @param itemName the item name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemName_First(
        java.lang.String itemName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemName = &#63;.
    *
    * @param itemName the item name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemName_First(
        java.lang.String itemName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemName = &#63;.
    *
    * @param itemName the item name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemName_Last(
        java.lang.String itemName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemName = &#63;.
    *
    * @param itemName the item name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemName_Last(
        java.lang.String itemName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where itemName = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param itemName the item name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByItemName_PrevAndNext(
        int itemMasterSid, java.lang.String itemName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where itemName = &#63; from the database.
    *
    * @param itemName the item name
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemName(java.lang.String itemName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where itemName = &#63;.
    *
    * @param itemName the item name
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemName(java.lang.String itemName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where itemType = &#63;.
    *
    * @param itemType the item type
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemType(
        int itemType) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where itemType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemType the item type
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemType(
        int itemType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where itemType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemType the item type
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemType(
        int itemType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemType = &#63;.
    *
    * @param itemType the item type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemType_First(int itemType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemType = &#63;.
    *
    * @param itemType the item type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemType_First(int itemType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemType = &#63;.
    *
    * @param itemType the item type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemType_Last(int itemType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemType = &#63;.
    *
    * @param itemType the item type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemType_Last(int itemType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where itemType = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param itemType the item type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByItemType_PrevAndNext(
        int itemMasterSid, int itemType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where itemType = &#63; from the database.
    *
    * @param itemType the item type
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemType(int itemType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where itemType = &#63;.
    *
    * @param itemType the item type
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemType(int itemType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemStatus(
        int itemStatus) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where itemStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemStatus the item status
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemStatus(
        int itemStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where itemStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemStatus the item status
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByItemStatus(
        int itemStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemStatus_First(
        int itemStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemStatus_First(
        int itemStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByItemStatus_Last(int itemStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByItemStatus_Last(
        int itemStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where itemStatus = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param itemStatus the item status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByItemStatus_PrevAndNext(
        int itemMasterSid, int itemStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where itemStatus = &#63; from the database.
    *
    * @param itemStatus the item status
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemStatus(int itemStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where itemStatus = &#63;.
    *
    * @param itemStatus the item status
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemStatus(int itemStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByManufacturerNo(
        java.lang.String manufacturerId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where manufacturerId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param manufacturerId the manufacturer ID
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByManufacturerNo(
        java.lang.String manufacturerId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where manufacturerId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param manufacturerId the manufacturer ID
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByManufacturerNo(
        java.lang.String manufacturerId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByManufacturerNo_First(
        java.lang.String manufacturerId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByManufacturerNo_First(
        java.lang.String manufacturerId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByManufacturerNo_Last(
        java.lang.String manufacturerId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByManufacturerNo_Last(
        java.lang.String manufacturerId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where manufacturerId = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param manufacturerId the manufacturer ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByManufacturerNo_PrevAndNext(
        int itemMasterSid, java.lang.String manufacturerId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where manufacturerId = &#63; from the database.
    *
    * @param manufacturerId the manufacturer ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByManufacturerNo(java.lang.String manufacturerId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where manufacturerId = &#63;.
    *
    * @param manufacturerId the manufacturer ID
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByManufacturerNo(java.lang.String manufacturerId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where form = &#63;.
    *
    * @param form the form
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByForm(int form)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where form = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param form the form
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByForm(int form,
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where form = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param form the form
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByForm(int form,
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where form = &#63;.
    *
    * @param form the form
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByForm_First(int form,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where form = &#63;.
    *
    * @param form the form
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByForm_First(int form,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where form = &#63;.
    *
    * @param form the form
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByForm_Last(int form,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where form = &#63;.
    *
    * @param form the form
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByForm_Last(int form,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where form = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param form the form
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByForm_PrevAndNext(
        int itemMasterSid, int form,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where form = &#63; from the database.
    *
    * @param form the form
    * @throws SystemException if a system exception occurred
    */
    public void removeByForm(int form)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where form = &#63;.
    *
    * @param form the form
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByForm(int form)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where strength = &#63;.
    *
    * @param strength the strength
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByStrength(
        int strength) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where strength = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param strength the strength
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByStrength(
        int strength, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where strength = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param strength the strength
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByStrength(
        int strength, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where strength = &#63;.
    *
    * @param strength the strength
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByStrength_First(int strength,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where strength = &#63;.
    *
    * @param strength the strength
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByStrength_First(int strength,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where strength = &#63;.
    *
    * @param strength the strength
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByStrength_Last(int strength,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where strength = &#63;.
    *
    * @param strength the strength
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByStrength_Last(int strength,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where strength = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param strength the strength
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByStrength_PrevAndNext(
        int itemMasterSid, int strength,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where strength = &#63; from the database.
    *
    * @param strength the strength
    * @throws SystemException if a system exception occurred
    */
    public void removeByStrength(int strength)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where strength = &#63;.
    *
    * @param strength the strength
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByStrength(int strength)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @return the matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByPrimaryUom(
        int primaryUom) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters where primaryUom = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param primaryUom the primary uom
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByPrimaryUom(
        int primaryUom, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters where primaryUom = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param primaryUom the primary uom
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findByPrimaryUom(
        int primaryUom, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByPrimaryUom_First(
        int primaryUom,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item master in the ordered set where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByPrimaryUom_First(
        int primaryUom,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master
    * @throws com.stpl.app.NoSuchItemMasterException if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByPrimaryUom_Last(int primaryUom,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item master in the ordered set where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item master, or <code>null</code> if a matching item master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByPrimaryUom_Last(
        int primaryUom,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item masters before and after the current item master in the ordered set where primaryUom = &#63;.
    *
    * @param itemMasterSid the primary key of the current item master
    * @param primaryUom the primary uom
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster[] findByPrimaryUom_PrevAndNext(
        int itemMasterSid, int primaryUom,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters where primaryUom = &#63; from the database.
    *
    * @param primaryUom the primary uom
    * @throws SystemException if a system exception occurred
    */
    public void removeByPrimaryUom(int primaryUom)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters where primaryUom = &#63;.
    *
    * @param primaryUom the primary uom
    * @return the number of matching item masters
    * @throws SystemException if a system exception occurred
    */
    public int countByPrimaryUom(int primaryUom)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item master in the entity cache if it is enabled.
    *
    * @param itemMaster the item master
    */
    public void cacheResult(com.stpl.app.model.ItemMaster itemMaster);

    /**
    * Caches the item masters in the entity cache if it is enabled.
    *
    * @param itemMasters the item masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemMaster> itemMasters);

    /**
    * Creates a new item master with the primary key. Does not add the item master to the database.
    *
    * @param itemMasterSid the primary key for the new item master
    * @return the new item master
    */
    public com.stpl.app.model.ItemMaster create(int itemMasterSid);

    /**
    * Removes the item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the item master
    * @return the item master that was removed
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster remove(int itemMasterSid)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemMaster updateImpl(
        com.stpl.app.model.ItemMaster itemMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item master with the primary key or throws a {@link com.stpl.app.NoSuchItemMasterException} if it could not be found.
    *
    * @param itemMasterSid the primary key of the item master
    * @return the item master
    * @throws com.stpl.app.NoSuchItemMasterException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster findByPrimaryKey(int itemMasterSid)
        throws com.stpl.app.NoSuchItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemMasterSid the primary key of the item master
    * @return the item master, or <code>null</code> if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemMaster fetchByPrimaryKey(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item masters.
    *
    * @return the item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item masters.
    *
    * @return the number of item masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
