package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the workflow master service. This utility wraps {@link WorkflowMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowMasterPersistence
 * @see WorkflowMasterPersistenceImpl
 * @generated
 */
public class WorkflowMasterUtil {
    private static WorkflowMasterPersistence _persistence;

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
    public static void clearCache(WorkflowMaster workflowMaster) {
        getPersistence().clearCache(workflowMaster);
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
    public static List<WorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<WorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<WorkflowMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static WorkflowMaster update(WorkflowMaster workflowMaster)
        throws SystemException {
        return getPersistence().update(workflowMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static WorkflowMaster update(WorkflowMaster workflowMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(workflowMaster, serviceContext);
    }

    /**
    * Caches the workflow master in the entity cache if it is enabled.
    *
    * @param workflowMaster the workflow master
    */
    public static void cacheResult(
        com.stpl.app.model.WorkflowMaster workflowMaster) {
        getPersistence().cacheResult(workflowMaster);
    }

    /**
    * Caches the workflow masters in the entity cache if it is enabled.
    *
    * @param workflowMasters the workflow masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowMaster> workflowMasters) {
        getPersistence().cacheResult(workflowMasters);
    }

    /**
    * Creates a new workflow master with the primary key. Does not add the workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new workflow master
    * @return the new workflow master
    */
    public static com.stpl.app.model.WorkflowMaster create(
        int workflowMasterSid) {
        return getPersistence().create(workflowMasterSid);
    }

    /**
    * Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master that was removed
    * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster remove(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(workflowMasterSid);
    }

    public static com.stpl.app.model.WorkflowMaster updateImpl(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(workflowMaster);
    }

    /**
    * Returns the workflow master with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowMasterException} if it could not be found.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master
    * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster findByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(workflowMasterSid);
    }

    /**
    * Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster fetchByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(workflowMasterSid);
    }

    /**
    * Returns all the workflow masters.
    *
    * @return the workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow masters
    * @param end the upper bound of the range of workflow masters (not inclusive)
    * @return the range of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow masters
    * @param end the upper bound of the range of workflow masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the workflow masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of workflow masters.
    *
    * @return the number of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static WorkflowMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (WorkflowMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    WorkflowMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(WorkflowMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(WorkflowMasterPersistence persistence) {
    }
}
