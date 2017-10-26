package com.stpl.app.service.persistence;

import com.stpl.app.model.HistWorkflowMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist workflow master service. This utility wraps {@link HistWorkflowMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistWorkflowMasterPersistence
 * @see HistWorkflowMasterPersistenceImpl
 * @generated
 */
public class HistWorkflowMasterUtil {
    private static HistWorkflowMasterPersistence _persistence;

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
    public static void clearCache(HistWorkflowMaster histWorkflowMaster) {
        getPersistence().clearCache(histWorkflowMaster);
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
    public static List<HistWorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistWorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistWorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistWorkflowMaster update(
        HistWorkflowMaster histWorkflowMaster) throws SystemException {
        return getPersistence().update(histWorkflowMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistWorkflowMaster update(
        HistWorkflowMaster histWorkflowMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(histWorkflowMaster, serviceContext);
    }

    /**
    * Caches the hist workflow master in the entity cache if it is enabled.
    *
    * @param histWorkflowMaster the hist workflow master
    */
    public static void cacheResult(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster) {
        getPersistence().cacheResult(histWorkflowMaster);
    }

    /**
    * Caches the hist workflow masters in the entity cache if it is enabled.
    *
    * @param histWorkflowMasters the hist workflow masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistWorkflowMaster> histWorkflowMasters) {
        getPersistence().cacheResult(histWorkflowMasters);
    }

    /**
    * Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new hist workflow master
    * @return the new hist workflow master
    */
    public static com.stpl.app.model.HistWorkflowMaster create(
        int workflowMasterSid) {
        return getPersistence().create(workflowMasterSid);
    }

    /**
    * Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master that was removed
    * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistWorkflowMaster remove(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchHistWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(workflowMasterSid);
    }

    public static com.stpl.app.model.HistWorkflowMaster updateImpl(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histWorkflowMaster);
    }

    /**
    * Returns the hist workflow master with the primary key or throws a {@link com.stpl.app.NoSuchHistWorkflowMasterException} if it could not be found.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master
    * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistWorkflowMaster findByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchHistWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(workflowMasterSid);
    }

    /**
    * Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistWorkflowMaster fetchByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(workflowMasterSid);
    }

    /**
    * Returns all the hist workflow masters.
    *
    * @return the hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist workflow masters
    * @param end the upper bound of the range of hist workflow masters (not inclusive)
    * @return the range of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist workflow masters
    * @param end the upper bound of the range of hist workflow masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist workflow masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist workflow masters.
    *
    * @return the number of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistWorkflowMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistWorkflowMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistWorkflowMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(HistWorkflowMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistWorkflowMasterPersistence persistence) {
    }
}
