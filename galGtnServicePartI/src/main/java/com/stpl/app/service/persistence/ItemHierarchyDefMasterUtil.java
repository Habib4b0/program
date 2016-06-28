package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemHierarchyDefMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the item hierarchy def master service. This utility wraps {@link ItemHierarchyDefMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMasterPersistence
 * @see ItemHierarchyDefMasterPersistenceImpl
 * @generated
 */
public class ItemHierarchyDefMasterUtil {
    private static ItemHierarchyDefMasterPersistence _persistence;

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
    public static void clearCache(ItemHierarchyDefMaster itemHierarchyDefMaster) {
        getPersistence().clearCache(itemHierarchyDefMaster);
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
    public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ItemHierarchyDefMaster update(
        ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws SystemException {
        return getPersistence().update(itemHierarchyDefMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ItemHierarchyDefMaster update(
        ItemHierarchyDefMaster itemHierarchyDefMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(itemHierarchyDefMaster, serviceContext);
    }

    /**
    * Returns all the item hierarchy def masters where member = &#63;.
    *
    * @param member the member
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMember(member);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMember(member, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByMember(
        java.lang.String member, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMember(member, start, end, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByMember_First(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMember_First(member, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByMember_First(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMember_First(member, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByMember_Last(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMember_Last(member, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63;.
    *
    * @param member the member
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByMember_Last(
        java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMember_Last(member, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster[] findByMember_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String member,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMember_PrevAndNext(itemHierarchyDefMasterSid, member,
            orderByComparator);
    }

    /**
    * Removes all the item hierarchy def masters where member = &#63; from the database.
    *
    * @param member the member
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMember(java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByMember(member);
    }

    /**
    * Returns the number of item hierarchy def masters where member = &#63;.
    *
    * @param member the member
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByMember(java.lang.String member)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByMember(member);
    }

    /**
    * Returns all the item hierarchy def masters where alias = &#63;.
    *
    * @param alias the alias
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAlias(alias);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAlias(alias, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByAlias(
        java.lang.String alias, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAlias(alias, start, end, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByAlias_First(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAlias_First(alias, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByAlias_First(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByAlias_First(alias, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByAlias_Last(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAlias_Last(alias, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
    *
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByAlias_Last(
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByAlias_Last(alias, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAlias_PrevAndNext(itemHierarchyDefMasterSid, alias,
            orderByComparator);
    }

    /**
    * Removes all the item hierarchy def masters where alias = &#63; from the database.
    *
    * @param alias the alias
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAlias(java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAlias(alias);
    }

    /**
    * Returns the number of item hierarchy def masters where alias = &#63;.
    *
    * @param alias the alias
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAlias(java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAlias(alias);
    }

    /**
    * Returns all the item hierarchy def masters where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBpiLvl(bpiLvl);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBpiLvl(bpiLvl, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByBpiLvl(
        java.lang.String bpiLvl, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBpiLvl(bpiLvl, start, end, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByBpiLvl_First(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBpiLvl_First(bpiLvl, orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByBpiLvl_First(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByBpiLvl_First(bpiLvl, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByBpiLvl_Last(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByBpiLvl_Last(bpiLvl, orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByBpiLvl_Last(
        java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByBpiLvl_Last(bpiLvl, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String bpiLvl,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBpiLvl_PrevAndNext(itemHierarchyDefMasterSid, bpiLvl,
            orderByComparator);
    }

    /**
    * Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
    *
    * @param bpiLvl the bpi lvl
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBpiLvl(java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByBpiLvl(bpiLvl);
    }

    /**
    * Returns the number of item hierarchy def masters where bpiLvl = &#63;.
    *
    * @param bpiLvl the bpi lvl
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByBpiLvl(java.lang.String bpiLvl)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByBpiLvl(bpiLvl);
    }

    /**
    * Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @return the matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemHierarchyDefUnique(member, alias);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemHierarchyDefUnique(member, alias, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
        java.lang.String member, java.lang.String alias, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemHierarchyDefUnique(member, alias, start, end,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemHierarchyDefUnique_First(member, alias,
            orderByComparator);
    }

    /**
    * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemHierarchyDefUnique_First(member, alias,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemHierarchyDefUnique_Last(member, alias,
            orderByComparator);
    }

    /**
    * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
        java.lang.String member, java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemHierarchyDefUnique_Last(member, alias,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
        int itemHierarchyDefMasterSid, java.lang.String member,
        java.lang.String alias,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemHierarchyDefUnique_PrevAndNext(itemHierarchyDefMasterSid,
            member, alias, orderByComparator);
    }

    /**
    * Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
    *
    * @param member the member
    * @param alias the alias
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemHierarchyDefUnique(java.lang.String member,
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemHierarchyDefUnique(member, alias);
    }

    /**
    * Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
    *
    * @param member the member
    * @param alias the alias
    * @return the number of matching item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemHierarchyDefUnique(java.lang.String member,
        java.lang.String alias)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemHierarchyDefUnique(member, alias);
    }

    /**
    * Caches the item hierarchy def master in the entity cache if it is enabled.
    *
    * @param itemHierarchyDefMaster the item hierarchy def master
    */
    public static void cacheResult(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster) {
        getPersistence().cacheResult(itemHierarchyDefMaster);
    }

    /**
    * Caches the item hierarchy def masters in the entity cache if it is enabled.
    *
    * @param itemHierarchyDefMasters the item hierarchy def masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> itemHierarchyDefMasters) {
        getPersistence().cacheResult(itemHierarchyDefMasters);
    }

    /**
    * Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
    *
    * @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
    * @return the new item hierarchy def master
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster create(
        int itemHierarchyDefMasterSid) {
        return getPersistence().create(itemHierarchyDefMasterSid);
    }

    /**
    * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master that was removed
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster remove(
        int itemHierarchyDefMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemHierarchyDefMasterSid);
    }

    public static com.stpl.app.model.ItemHierarchyDefMaster updateImpl(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(itemHierarchyDefMaster);
    }

    /**
    * Returns the item hierarchy def master with the primary key or throws a {@link com.stpl.app.NoSuchItemHierarchyDefMasterException} if it could not be found.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master
    * @throws com.stpl.app.NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster findByPrimaryKey(
        int itemHierarchyDefMasterSid)
        throws com.stpl.app.NoSuchItemHierarchyDefMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemHierarchyDefMasterSid);
    }

    /**
    * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemHierarchyDefMaster fetchByPrimaryKey(
        int itemHierarchyDefMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemHierarchyDefMasterSid);
    }

    /**
    * Returns all the item hierarchy def masters.
    *
    * @return the item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the item hierarchy def masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of item hierarchy def masters.
    *
    * @return the number of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ItemHierarchyDefMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ItemHierarchyDefMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemHierarchyDefMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ItemHierarchyDefMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ItemHierarchyDefMasterPersistence persistence) {
    }
}
