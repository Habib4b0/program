package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemHierarchyDefMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item hierarchy def master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMasterPersistenceImpl
 * @see ItemHierarchyDefMasterUtil
 * @generated
 */
public interface ItemHierarchyDefMasterPersistence extends BasePersistence<ItemHierarchyDefMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemHierarchyDefMasterUtil} to access the item hierarchy def master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item hierarchy def masters where member = &#63;.
    *
    * @param member the member
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy def masters where member = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param member the member
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy def masters where member = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param member the member
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByMember_First(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByMember_First(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByMember_Last(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByMember_Last(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster[] findByMember_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy def masters where member = &#63; from the database.
    *
    * @param member the member
    * @throws SystemException if a system exception occurred
    */
    public void removeByMember(java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy def masters where member = &#63;.
    *
    * @param member the member
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public int countByMember(java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy def masters where alias = &#63;.
    *
    * @param alias the alias
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy def masters where alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param alias the alias
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param alias the alias
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByAlias_First(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByAlias_First(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByAlias_Last(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByAlias_Last(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy def masters where alias = &#63; from the database.
    *
    * @param alias the alias
    * @throws SystemException if a system exception occurred
    */
    public void removeByAlias(java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy def masters where alias = &#63;.
    *
    * @param alias the alias
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAlias(java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy def masters where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy def masters where bpiLvl = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param bpiLvl the bpi lvl
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param bpiLvl the bpi lvl
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByBpiLvl_First(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByBpiLvl_First(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByBpiLvl_Last(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByBpiLvl_Last(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
    *
    * @param bpiLvl the bpi lvl
    * @throws SystemException if a system exception occurred
    */
    public void removeByBpiLvl(java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy def masters where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public int countByBpiLvl(java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param member the member
    * @param alias the alias
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param member the member
    * @param alias the alias
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String member,
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
    *
    * @param member the member
    * @param alias the alias
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemHierarchyDefUnique(java.lang.String member,
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemHierarchyDefUnique(java.lang.String member,
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item hierarchy def master in the entity cache if it is enabled.
    *
    * @param itemHierarchyDefMaster the item hierarchy def master
    */
    public void cacheResult(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster);

    /**
    * Caches the item hierarchy def masters in the entity cache if it is enabled.
    *
    * @param itemHierarchyDefMasters the item hierarchy def masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> itemHierarchyDefMasters);

    /**
    * Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
    *
    * @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
    * @return the new item hierarchy def master
    */
    public com.stpl.app.model.ItemHierarchyDefMaster create(
        int itemHierarchyDefMasterSid);

    /**
    * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master that was removed
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster remove(
        int itemHierarchyDefMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemHierarchyDefMaster updateImpl(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def master with the primary key or throws a {@link com.stpl.app.NoSuchItemHierarchyDefMasterException} if it could not be found.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster findByPrimaryKey(
        int itemHierarchyDefMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyDefMaster fetchByPrimaryKey(
        int itemHierarchyDefMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy def masters.
    *
    * @return the item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy def masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy def masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy def masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy def masters.
    *
    * @return the number of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
