package com.stpl.app.service.persistence;

import com.stpl.app.model.UsergroupBusinessrole;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the usergroup businessrole service. This utility wraps {@link UsergroupBusinessrolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupBusinessrolePersistence
 * @see UsergroupBusinessrolePersistenceImpl
 * @generated
 */
public class UsergroupBusinessroleUtil {
    private static UsergroupBusinessrolePersistence _persistence;

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
    public static void clearCache(UsergroupBusinessrole usergroupBusinessrole) {
        getPersistence().clearCache(usergroupBusinessrole);
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
    public static List<UsergroupBusinessrole> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<UsergroupBusinessrole> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<UsergroupBusinessrole> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static UsergroupBusinessrole update(
        UsergroupBusinessrole usergroupBusinessrole) throws SystemException {
        return getPersistence().update(usergroupBusinessrole);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static UsergroupBusinessrole update(
        UsergroupBusinessrole usergroupBusinessrole,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(usergroupBusinessrole, serviceContext);
    }

    /**
    * Caches the usergroup businessrole in the entity cache if it is enabled.
    *
    * @param usergroupBusinessrole the usergroup businessrole
    */
    public static void cacheResult(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole) {
        getPersistence().cacheResult(usergroupBusinessrole);
    }

    /**
    * Caches the usergroup businessroles in the entity cache if it is enabled.
    *
    * @param usergroupBusinessroles the usergroup businessroles
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.UsergroupBusinessrole> usergroupBusinessroles) {
        getPersistence().cacheResult(usergroupBusinessroles);
    }

    /**
    * Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
    *
    * @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
    * @return the new usergroup businessrole
    */
    public static com.stpl.app.model.UsergroupBusinessrole create(
        int usergroupBusinessroleSid) {
        return getPersistence().create(usergroupBusinessroleSid);
    }

    /**
    * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole that was removed
    * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.UsergroupBusinessrole remove(
        int usergroupBusinessroleSid)
        throws com.stpl.app.NoSuchUsergroupBusinessroleException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(usergroupBusinessroleSid);
    }

    public static com.stpl.app.model.UsergroupBusinessrole updateImpl(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(usergroupBusinessrole);
    }

    /**
    * Returns the usergroup businessrole with the primary key or throws a {@link com.stpl.app.NoSuchUsergroupBusinessroleException} if it could not be found.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole
    * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.UsergroupBusinessrole findByPrimaryKey(
        int usergroupBusinessroleSid)
        throws com.stpl.app.NoSuchUsergroupBusinessroleException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(usergroupBusinessroleSid);
    }

    /**
    * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.UsergroupBusinessrole fetchByPrimaryKey(
        int usergroupBusinessroleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(usergroupBusinessroleSid);
    }

    /**
    * Returns all the usergroup businessroles.
    *
    * @return the usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the usergroup businessroles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup businessroles
    * @param end the upper bound of the range of usergroup businessroles (not inclusive)
    * @return the range of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the usergroup businessroles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup businessroles
    * @param end the upper bound of the range of usergroup businessroles (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the usergroup businessroles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of usergroup businessroles.
    *
    * @return the number of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static UsergroupBusinessrolePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (UsergroupBusinessrolePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    UsergroupBusinessrolePersistence.class.getName());

            ReferenceRegistry.registerReference(UsergroupBusinessroleUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(UsergroupBusinessrolePersistence persistence) {
    }
}
