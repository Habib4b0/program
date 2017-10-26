package com.stpl.app.service.persistence;

import com.stpl.app.model.AverageShelfLifeMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the average shelf life master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AverageShelfLifeMasterPersistenceImpl
 * @see AverageShelfLifeMasterUtil
 * @generated
 */
public interface AverageShelfLifeMasterPersistence extends BasePersistence<AverageShelfLifeMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AverageShelfLifeMasterUtil} to access the average shelf life master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the average shelf life masters where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the average shelf life masters where itemIdType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the average shelf life masters where itemIdType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByItemIdType_First(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByItemIdType_First(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByItemIdType_Last(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByItemIdType_Last(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster[] findByItemIdType_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the average shelf life masters where itemIdType = &#63; from the database.
    *
    * @param itemIdType the item ID type
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemIdType(java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of average shelf life masters where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemIdType(java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the average shelf life masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the average shelf life masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the average shelf life masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster[] findByItemId_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the average shelf life masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of average shelf life masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the average shelf life masters where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the average shelf life masters where avgShelfLife = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param avgShelfLife the avg shelf life
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the average shelf life masters where avgShelfLife = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param avgShelfLife the avg shelf life
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByAvgShelfLife_First(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByAvgShelfLife_First(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByAvgShelfLife_Last(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByAvgShelfLife_Last(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster[] findByAvgShelfLife_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the average shelf life masters where avgShelfLife = &#63; from the database.
    *
    * @param avgShelfLife the avg shelf life
    * @throws SystemException if a system exception occurred
    */
    public void removeByAvgShelfLife(java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of average shelf life masters where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAvgShelfLife(java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByAverageShelfLifeUnique_First(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByAverageShelfLifeUnique_First(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByAverageShelfLifeUnique_Last(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByAverageShelfLifeUnique_Last(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster[] findByAverageShelfLifeUnique_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemIdType,
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the average shelf life masters where itemIdType = &#63; and itemId = &#63; from the database.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAverageShelfLifeUnique(java.lang.String itemIdType,
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAverageShelfLifeUnique(java.lang.String itemIdType,
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the average shelf life master in the entity cache if it is enabled.
    *
    * @param averageShelfLifeMaster the average shelf life master
    */
    public void cacheResult(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster);

    /**
    * Caches the average shelf life masters in the entity cache if it is enabled.
    *
    * @param averageShelfLifeMasters the average shelf life masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.AverageShelfLifeMaster> averageShelfLifeMasters);

    /**
    * Creates a new average shelf life master with the primary key. Does not add the average shelf life master to the database.
    *
    * @param averageShelfLifeMasterSid the primary key for the new average shelf life master
    * @return the new average shelf life master
    */
    public com.stpl.app.model.AverageShelfLifeMaster create(
        int averageShelfLifeMasterSid);

    /**
    * Removes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master that was removed
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster remove(
        int averageShelfLifeMasterSid)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.AverageShelfLifeMaster updateImpl(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life master with the primary key or throws a {@link com.stpl.app.NoSuchAverageShelfLifeMasterException} if it could not be found.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster findByPrimaryKey(
        int averageShelfLifeMasterSid)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the average shelf life master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master, or <code>null</code> if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AverageShelfLifeMaster fetchByPrimaryKey(
        int averageShelfLifeMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the average shelf life masters.
    *
    * @return the average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the average shelf life masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the average shelf life masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the average shelf life masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of average shelf life masters.
    *
    * @return the number of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
