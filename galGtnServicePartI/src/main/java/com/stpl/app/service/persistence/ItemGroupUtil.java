package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroup;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the item group service. This utility wraps {@link ItemGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemGroupPersistence
 * @see ItemGroupPersistenceImpl
 * @generated
 */
public class ItemGroupUtil {
    private static ItemGroupPersistence _persistence;

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
    public static void clearCache(ItemGroup itemGroup) {
        getPersistence().clearCache(itemGroup);
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
    public static List<ItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ItemGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ItemGroup update(ItemGroup itemGroup)
        throws SystemException {
        return getPersistence().update(itemGroup);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ItemGroup update(ItemGroup itemGroup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(itemGroup, serviceContext);
    }

    /**
    * Caches the item group in the entity cache if it is enabled.
    *
    * @param itemGroup the item group
    */
    public static void cacheResult(com.stpl.app.model.ItemGroup itemGroup) {
        getPersistence().cacheResult(itemGroup);
    }

    /**
    * Caches the item groups in the entity cache if it is enabled.
    *
    * @param itemGroups the item groups
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ItemGroup> itemGroups) {
        getPersistence().cacheResult(itemGroups);
    }

    /**
    * Creates a new item group with the primary key. Does not add the item group to the database.
    *
    * @param itemGroupSid the primary key for the new item group
    * @return the new item group
    */
    public static com.stpl.app.model.ItemGroup create(int itemGroupSid) {
        return getPersistence().create(itemGroupSid);
    }

    /**
    * Removes the item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group that was removed
    * @throws com.stpl.app.NoSuchItemGroupException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup remove(int itemGroupSid)
        throws com.stpl.app.NoSuchItemGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemGroupSid);
    }

    public static com.stpl.app.model.ItemGroup updateImpl(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(itemGroup);
    }

    /**
    * Returns the item group with the primary key or throws a {@link com.stpl.app.NoSuchItemGroupException} if it could not be found.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group
    * @throws com.stpl.app.NoSuchItemGroupException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup findByPrimaryKey(
        int itemGroupSid)
        throws com.stpl.app.NoSuchItemGroupException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemGroupSid);
    }

    /**
    * Returns the item group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group, or <code>null</code> if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup fetchByPrimaryKey(
        int itemGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemGroupSid);
    }

    /**
    * Returns all the item groups.
    *
    * @return the item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item groups
    * @param end the upper bound of the range of item groups (not inclusive)
    * @return the range of item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemGroup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item groups
    * @param end the upper bound of the range of item groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemGroup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the item groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of item groups.
    *
    * @return the number of item groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ItemGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ItemGroupPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(ItemGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ItemGroupPersistence persistence) {
    }
}
