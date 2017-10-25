package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProcessInfo;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the workflow process info service. This utility wraps {@link WorkflowProcessInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProcessInfoPersistence
 * @see WorkflowProcessInfoPersistenceImpl
 * @generated
 */
public class WorkflowProcessInfoUtil {
    private static WorkflowProcessInfoPersistence _persistence;

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
    public static void clearCache(WorkflowProcessInfo workflowProcessInfo) {
        getPersistence().clearCache(workflowProcessInfo);
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
    public static List<WorkflowProcessInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<WorkflowProcessInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<WorkflowProcessInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static WorkflowProcessInfo update(
        WorkflowProcessInfo workflowProcessInfo) throws SystemException {
        return getPersistence().update(workflowProcessInfo);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static WorkflowProcessInfo update(
        WorkflowProcessInfo workflowProcessInfo, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(workflowProcessInfo, serviceContext);
    }

    /**
    * Caches the workflow process info in the entity cache if it is enabled.
    *
    * @param workflowProcessInfo the workflow process info
    */
    public static void cacheResult(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo) {
        getPersistence().cacheResult(workflowProcessInfo);
    }

    /**
    * Caches the workflow process infos in the entity cache if it is enabled.
    *
    * @param workflowProcessInfos the workflow process infos
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowProcessInfo> workflowProcessInfos) {
        getPersistence().cacheResult(workflowProcessInfos);
    }

    /**
    * Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
    *
    * @param workflowProcessInfoSid the primary key for the new workflow process info
    * @return the new workflow process info
    */
    public static com.stpl.app.model.WorkflowProcessInfo create(
        int workflowProcessInfoSid) {
        return getPersistence().create(workflowProcessInfoSid);
    }

    /**
    * Removes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info that was removed
    * @throws com.stpl.app.NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProcessInfo remove(
        int workflowProcessInfoSid)
        throws com.stpl.app.NoSuchWorkflowProcessInfoException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(workflowProcessInfoSid);
    }

    public static com.stpl.app.model.WorkflowProcessInfo updateImpl(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(workflowProcessInfo);
    }

    /**
    * Returns the workflow process info with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowProcessInfoException} if it could not be found.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info
    * @throws com.stpl.app.NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProcessInfo findByPrimaryKey(
        int workflowProcessInfoSid)
        throws com.stpl.app.NoSuchWorkflowProcessInfoException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(workflowProcessInfoSid);
    }

    /**
    * Returns the workflow process info with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info, or <code>null</code> if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProcessInfo fetchByPrimaryKey(
        int workflowProcessInfoSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(workflowProcessInfoSid);
    }

    /**
    * Returns all the workflow process infos.
    *
    * @return the workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the workflow process infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow process infos
    * @param end the upper bound of the range of workflow process infos (not inclusive)
    * @return the range of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the workflow process infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow process infos
    * @param end the upper bound of the range of workflow process infos (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the workflow process infos from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of workflow process infos.
    *
    * @return the number of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static WorkflowProcessInfoPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (WorkflowProcessInfoPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    WorkflowProcessInfoPersistence.class.getName());

            ReferenceRegistry.registerReference(WorkflowProcessInfoUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(WorkflowProcessInfoPersistence persistence) {
    }
}
