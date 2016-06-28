package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionNameConfig;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the projection name config service. This utility wraps {@link ProjectionNameConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionNameConfigPersistence
 * @see ProjectionNameConfigPersistenceImpl
 * @generated
 */
public class ProjectionNameConfigUtil {
    private static ProjectionNameConfigPersistence _persistence;

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
    public static void clearCache(ProjectionNameConfig projectionNameConfig) {
        getPersistence().clearCache(projectionNameConfig);
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
    public static List<ProjectionNameConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProjectionNameConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProjectionNameConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ProjectionNameConfig update(
        ProjectionNameConfig projectionNameConfig) throws SystemException {
        return getPersistence().update(projectionNameConfig);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ProjectionNameConfig update(
        ProjectionNameConfig projectionNameConfig, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(projectionNameConfig, serviceContext);
    }

    /**
    * Caches the projection name config in the entity cache if it is enabled.
    *
    * @param projectionNameConfig the projection name config
    */
    public static void cacheResult(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig) {
        getPersistence().cacheResult(projectionNameConfig);
    }

    /**
    * Caches the projection name configs in the entity cache if it is enabled.
    *
    * @param projectionNameConfigs the projection name configs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionNameConfig> projectionNameConfigs) {
        getPersistence().cacheResult(projectionNameConfigs);
    }

    /**
    * Creates a new projection name config with the primary key. Does not add the projection name config to the database.
    *
    * @param projectionNameConfigSid the primary key for the new projection name config
    * @return the new projection name config
    */
    public static com.stpl.app.model.ProjectionNameConfig create(
        int projectionNameConfigSid) {
        return getPersistence().create(projectionNameConfigSid);
    }

    /**
    * Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionNameConfigSid the primary key of the projection name config
    * @return the projection name config that was removed
    * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionNameConfig remove(
        int projectionNameConfigSid)
        throws com.stpl.app.NoSuchProjectionNameConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionNameConfigSid);
    }

    public static com.stpl.app.model.ProjectionNameConfig updateImpl(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(projectionNameConfig);
    }

    /**
    * Returns the projection name config with the primary key or throws a {@link com.stpl.app.NoSuchProjectionNameConfigException} if it could not be found.
    *
    * @param projectionNameConfigSid the primary key of the projection name config
    * @return the projection name config
    * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionNameConfig findByPrimaryKey(
        int projectionNameConfigSid)
        throws com.stpl.app.NoSuchProjectionNameConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionNameConfigSid);
    }

    /**
    * Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionNameConfigSid the primary key of the projection name config
    * @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionNameConfig fetchByPrimaryKey(
        int projectionNameConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionNameConfigSid);
    }

    /**
    * Returns all the projection name configs.
    *
    * @return the projection name configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionNameConfig> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the projection name configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection name configs
    * @param end the upper bound of the range of projection name configs (not inclusive)
    * @return the range of projection name configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionNameConfig> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the projection name configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection name configs
    * @param end the upper bound of the range of projection name configs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection name configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionNameConfig> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the projection name configs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of projection name configs.
    *
    * @return the number of projection name configs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProjectionNameConfigPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProjectionNameConfigPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionNameConfigPersistence.class.getName());

            ReferenceRegistry.registerReference(ProjectionNameConfigUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProjectionNameConfigPersistence persistence) {
    }
}
