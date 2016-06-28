package com.stpl.app.service.persistence;

import com.stpl.app.model.WfMailConfig;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the wf mail config service. This utility wraps {@link WfMailConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WfMailConfigPersistence
 * @see WfMailConfigPersistenceImpl
 * @generated
 */
public class WfMailConfigUtil {
    private static WfMailConfigPersistence _persistence;

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
    public static void clearCache(WfMailConfig wfMailConfig) {
        getPersistence().clearCache(wfMailConfig);
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
    public static List<WfMailConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<WfMailConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<WfMailConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static WfMailConfig update(WfMailConfig wfMailConfig)
        throws SystemException {
        return getPersistence().update(wfMailConfig);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static WfMailConfig update(WfMailConfig wfMailConfig,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(wfMailConfig, serviceContext);
    }

    /**
    * Caches the wf mail config in the entity cache if it is enabled.
    *
    * @param wfMailConfig the wf mail config
    */
    public static void cacheResult(com.stpl.app.model.WfMailConfig wfMailConfig) {
        getPersistence().cacheResult(wfMailConfig);
    }

    /**
    * Caches the wf mail configs in the entity cache if it is enabled.
    *
    * @param wfMailConfigs the wf mail configs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.WfMailConfig> wfMailConfigs) {
        getPersistence().cacheResult(wfMailConfigs);
    }

    /**
    * Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
    *
    * @param wfMailConfigSid the primary key for the new wf mail config
    * @return the new wf mail config
    */
    public static com.stpl.app.model.WfMailConfig create(int wfMailConfigSid) {
        return getPersistence().create(wfMailConfigSid);
    }

    /**
    * Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param wfMailConfigSid the primary key of the wf mail config
    * @return the wf mail config that was removed
    * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WfMailConfig remove(int wfMailConfigSid)
        throws com.stpl.app.NoSuchWfMailConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(wfMailConfigSid);
    }

    public static com.stpl.app.model.WfMailConfig updateImpl(
        com.stpl.app.model.WfMailConfig wfMailConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(wfMailConfig);
    }

    /**
    * Returns the wf mail config with the primary key or throws a {@link com.stpl.app.NoSuchWfMailConfigException} if it could not be found.
    *
    * @param wfMailConfigSid the primary key of the wf mail config
    * @return the wf mail config
    * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WfMailConfig findByPrimaryKey(
        int wfMailConfigSid)
        throws com.stpl.app.NoSuchWfMailConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(wfMailConfigSid);
    }

    /**
    * Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param wfMailConfigSid the primary key of the wf mail config
    * @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WfMailConfig fetchByPrimaryKey(
        int wfMailConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(wfMailConfigSid);
    }

    /**
    * Returns all the wf mail configs.
    *
    * @return the wf mail configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WfMailConfig> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the wf mail configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of wf mail configs
    * @param end the upper bound of the range of wf mail configs (not inclusive)
    * @return the range of wf mail configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WfMailConfig> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the wf mail configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of wf mail configs
    * @param end the upper bound of the range of wf mail configs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of wf mail configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WfMailConfig> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the wf mail configs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of wf mail configs.
    *
    * @return the number of wf mail configs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static WfMailConfigPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (WfMailConfigPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    WfMailConfigPersistence.class.getName());

            ReferenceRegistry.registerReference(WfMailConfigUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(WfMailConfigPersistence persistence) {
    }
}
