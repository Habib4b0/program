package com.stpl.app.service.persistence;

import com.stpl.app.model.ModuleSubmoduleMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the module submodule master service. This utility wraps {@link ModuleSubmoduleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMasterPersistence
 * @see ModuleSubmoduleMasterPersistenceImpl
 * @generated
 */
public class ModuleSubmoduleMasterUtil {
    private static ModuleSubmoduleMasterPersistence _persistence;

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
    public static void clearCache(ModuleSubmoduleMaster moduleSubmoduleMaster) {
        getPersistence().clearCache(moduleSubmoduleMaster);
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
    public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModuleSubmoduleMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ModuleSubmoduleMaster update(
        ModuleSubmoduleMaster moduleSubmoduleMaster) throws SystemException {
        return getPersistence().update(moduleSubmoduleMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ModuleSubmoduleMaster update(
        ModuleSubmoduleMaster moduleSubmoduleMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(moduleSubmoduleMaster, serviceContext);
    }

    /**
    * Returns all the module submodule masters where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @return the matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findByModuleName(
        java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByModuleName(moduleName);
    }

    /**
    * Returns a range of all the module submodule masters where moduleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param moduleName the module name
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @return the range of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findByModuleName(
        java.lang.String moduleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByModuleName(moduleName, start, end);
    }

    /**
    * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param moduleName the module name
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findByModuleName(
        java.lang.String moduleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModuleName(moduleName, start, end, orderByComparator);
    }

    /**
    * Returns the first module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster findByModuleName_First(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModuleName_First(moduleName, orderByComparator);
    }

    /**
    * Returns the first module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster fetchByModuleName_First(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModuleName_First(moduleName, orderByComparator);
    }

    /**
    * Returns the last module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster findByModuleName_Last(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModuleName_Last(moduleName, orderByComparator);
    }

    /**
    * Returns the last module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster fetchByModuleName_Last(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModuleName_Last(moduleName, orderByComparator);
    }

    /**
    * Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleSubmoduleSid the primary key of the current module submodule master
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster[] findByModuleName_PrevAndNext(
        int moduleSubmoduleSid, java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModuleName_PrevAndNext(moduleSubmoduleSid,
            moduleName, orderByComparator);
    }

    /**
    * Removes all the module submodule masters where moduleName = &#63; from the database.
    *
    * @param moduleName the module name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModuleName(java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByModuleName(moduleName);
    }

    /**
    * Returns the number of module submodule masters where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @return the number of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByModuleName(java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByModuleName(moduleName);
    }

    /**
    * Returns all the module submodule masters where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @return the matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findBySubmoduleName(
        java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySubmoduleName(moduleName);
    }

    /**
    * Returns a range of all the module submodule masters where moduleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param moduleName the module name
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @return the range of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findBySubmoduleName(
        java.lang.String moduleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySubmoduleName(moduleName, start, end);
    }

    /**
    * Returns an ordered range of all the module submodule masters where moduleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param moduleName the module name
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findBySubmoduleName(
        java.lang.String moduleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubmoduleName(moduleName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster findBySubmoduleName_First(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubmoduleName_First(moduleName, orderByComparator);
    }

    /**
    * Returns the first module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster fetchBySubmoduleName_First(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySubmoduleName_First(moduleName, orderByComparator);
    }

    /**
    * Returns the last module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster findBySubmoduleName_Last(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubmoduleName_Last(moduleName, orderByComparator);
    }

    /**
    * Returns the last module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching module submodule master, or <code>null</code> if a matching module submodule master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster fetchBySubmoduleName_Last(
        java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySubmoduleName_Last(moduleName, orderByComparator);
    }

    /**
    * Returns the module submodule masters before and after the current module submodule master in the ordered set where moduleName = &#63;.
    *
    * @param moduleSubmoduleSid the primary key of the current module submodule master
    * @param moduleName the module name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster[] findBySubmoduleName_PrevAndNext(
        int moduleSubmoduleSid, java.lang.String moduleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubmoduleName_PrevAndNext(moduleSubmoduleSid,
            moduleName, orderByComparator);
    }

    /**
    * Removes all the module submodule masters where moduleName = &#63; from the database.
    *
    * @param moduleName the module name
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySubmoduleName(java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBySubmoduleName(moduleName);
    }

    /**
    * Returns the number of module submodule masters where moduleName = &#63;.
    *
    * @param moduleName the module name
    * @return the number of matching module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static int countBySubmoduleName(java.lang.String moduleName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBySubmoduleName(moduleName);
    }

    /**
    * Caches the module submodule master in the entity cache if it is enabled.
    *
    * @param moduleSubmoduleMaster the module submodule master
    */
    public static void cacheResult(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster) {
        getPersistence().cacheResult(moduleSubmoduleMaster);
    }

    /**
    * Caches the module submodule masters in the entity cache if it is enabled.
    *
    * @param moduleSubmoduleMasters the module submodule masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> moduleSubmoduleMasters) {
        getPersistence().cacheResult(moduleSubmoduleMasters);
    }

    /**
    * Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
    *
    * @param moduleSubmoduleSid the primary key for the new module submodule master
    * @return the new module submodule master
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster create(
        int moduleSubmoduleSid) {
        return getPersistence().create(moduleSubmoduleSid);
    }

    /**
    * Removes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param moduleSubmoduleSid the primary key of the module submodule master
    * @return the module submodule master that was removed
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster remove(
        int moduleSubmoduleSid)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(moduleSubmoduleSid);
    }

    public static com.stpl.app.model.ModuleSubmoduleMaster updateImpl(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(moduleSubmoduleMaster);
    }

    /**
    * Returns the module submodule master with the primary key or throws a {@link com.stpl.app.NoSuchModuleSubmoduleMasterException} if it could not be found.
    *
    * @param moduleSubmoduleSid the primary key of the module submodule master
    * @return the module submodule master
    * @throws com.stpl.app.NoSuchModuleSubmoduleMasterException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster findByPrimaryKey(
        int moduleSubmoduleSid)
        throws com.stpl.app.NoSuchModuleSubmoduleMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(moduleSubmoduleSid);
    }

    /**
    * Returns the module submodule master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param moduleSubmoduleSid the primary key of the module submodule master
    * @return the module submodule master, or <code>null</code> if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleSubmoduleMaster fetchByPrimaryKey(
        int moduleSubmoduleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(moduleSubmoduleSid);
    }

    /**
    * Returns all the module submodule masters.
    *
    * @return the module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the module submodule masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @return the range of module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the module submodule masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the module submodule masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of module submodule masters.
    *
    * @return the number of module submodule masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModuleSubmoduleMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModuleSubmoduleMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ModuleSubmoduleMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ModuleSubmoduleMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ModuleSubmoduleMasterPersistence persistence) {
    }
}
