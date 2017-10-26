package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemHierarchyMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item hierarchy master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyMasterPersistenceImpl
 * @see ItemHierarchyMasterUtil
 * @generated
 */
public interface ItemHierarchyMasterPersistence extends BasePersistence<ItemHierarchyMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemHierarchyMasterUtil} to access the item hierarchy master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item hierarchy masters where level0 = &#63;.
    *
    * @param level0 the level0
    * @return the matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0(
        java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy masters where level0 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0 the level0
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0(
        java.lang.String level0, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0 the level0
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0(
        java.lang.String level0, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0_First(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0_First(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0_Last(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0_Last(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster[] findByLevel0_PrevAndNext(
        int itemHierarchyMasterSid, java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy masters where level0 = &#63; from the database.
    *
    * @param level0 the level0
    * @throws SystemException if a system exception occurred
    */
    public void removeByLevel0(java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy masters where level0 = &#63;.
    *
    * @param level0 the level0
    * @return the number of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLevel0(java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy masters where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @return the matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Alias(
        java.lang.String level0Alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy masters where level0Alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0Alias the level0 alias
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Alias(
        java.lang.String level0Alias, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0Alias the level0 alias
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Alias(
        java.lang.String level0Alias, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0Alias_First(
        java.lang.String level0Alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0Alias_First(
        java.lang.String level0Alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0Alias_Last(
        java.lang.String level0Alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0Alias_Last(
        java.lang.String level0Alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Alias = &#63;.
    *
    * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
    * @param level0Alias the level0 alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster[] findByLevel0Alias_PrevAndNext(
        int itemHierarchyMasterSid, java.lang.String level0Alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy masters where level0Alias = &#63; from the database.
    *
    * @param level0Alias the level0 alias
    * @throws SystemException if a system exception occurred
    */
    public void removeByLevel0Alias(java.lang.String level0Alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy masters where level0Alias = &#63;.
    *
    * @param level0Alias the level0 alias
    * @return the number of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLevel0Alias(java.lang.String level0Alias)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy masters where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @return the matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Tag(
        java.lang.String level0Tag)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy masters where level0Tag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0Tag the level0 tag
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Tag(
        java.lang.String level0Tag, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0Tag the level0 tag
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByLevel0Tag(
        java.lang.String level0Tag, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0Tag_First(
        java.lang.String level0Tag,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0Tag_First(
        java.lang.String level0Tag,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByLevel0Tag_Last(
        java.lang.String level0Tag,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByLevel0Tag_Last(
        java.lang.String level0Tag,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Tag = &#63;.
    *
    * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
    * @param level0Tag the level0 tag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster[] findByLevel0Tag_PrevAndNext(
        int itemHierarchyMasterSid, java.lang.String level0Tag,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy masters where level0Tag = &#63; from the database.
    *
    * @param level0Tag the level0 tag
    * @throws SystemException if a system exception occurred
    */
    public void removeByLevel0Tag(java.lang.String level0Tag)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy masters where level0Tag = &#63;.
    *
    * @param level0Tag the level0 tag
    * @return the number of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public int countByLevel0Tag(java.lang.String level0Tag)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy masters where level0 = &#63;.
    *
    * @param level0 the level0
    * @return the matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByItemHierarchyUnique(
        java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy masters where level0 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0 the level0
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByItemHierarchyUnique(
        java.lang.String level0, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param level0 the level0
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findByItemHierarchyUnique(
        java.lang.String level0, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByItemHierarchyUnique_First(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByItemHierarchyUnique_First(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByItemHierarchyUnique_Last(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByItemHierarchyUnique_Last(
        java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
    *
    * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
    * @param level0 the level0
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster[] findByItemHierarchyUnique_PrevAndNext(
        int itemHierarchyMasterSid, java.lang.String level0,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy masters where level0 = &#63; from the database.
    *
    * @param level0 the level0
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemHierarchyUnique(java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy masters where level0 = &#63;.
    *
    * @param level0 the level0
    * @return the number of matching item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemHierarchyUnique(java.lang.String level0)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item hierarchy master in the entity cache if it is enabled.
    *
    * @param itemHierarchyMaster the item hierarchy master
    */
    public void cacheResult(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster);

    /**
    * Caches the item hierarchy masters in the entity cache if it is enabled.
    *
    * @param itemHierarchyMasters the item hierarchy masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemHierarchyMaster> itemHierarchyMasters);

    /**
    * Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
    *
    * @param itemHierarchyMasterSid the primary key for the new item hierarchy master
    * @return the new item hierarchy master
    */
    public com.stpl.app.model.ItemHierarchyMaster create(
        int itemHierarchyMasterSid);

    /**
    * Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyMasterSid the primary key of the item hierarchy master
    * @return the item hierarchy master that was removed
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster remove(
        int itemHierarchyMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemHierarchyMaster updateImpl(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy master with the primary key or throws a {@link com.stpl.app.NoSuchItemHierarchyMasterException} if it could not be found.
    *
    * @param itemHierarchyMasterSid the primary key of the item hierarchy master
    * @return the item hierarchy master
    * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster findByPrimaryKey(
        int itemHierarchyMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemHierarchyMasterSid the primary key of the item hierarchy master
    * @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemHierarchyMaster fetchByPrimaryKey(
        int itemHierarchyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item hierarchy masters.
    *
    * @return the item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item hierarchy masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item hierarchy masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item hierarchy masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item hierarchy masters.
    *
    * @return the number of item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
