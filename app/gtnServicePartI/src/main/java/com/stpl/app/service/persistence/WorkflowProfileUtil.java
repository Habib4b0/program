package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProfile;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the workflow profile service. This utility wraps {@link WorkflowProfilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProfilePersistence
 * @see WorkflowProfilePersistenceImpl
 * @generated
 */
public class WorkflowProfileUtil {
    private static WorkflowProfilePersistence _persistence;

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
    public static void clearCache(WorkflowProfile workflowProfile) {
        getPersistence().clearCache(workflowProfile);
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
    public static List<WorkflowProfile> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<WorkflowProfile> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<WorkflowProfile> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static WorkflowProfile update(WorkflowProfile workflowProfile)
        throws SystemException {
        return getPersistence().update(workflowProfile);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static WorkflowProfile update(WorkflowProfile workflowProfile,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(workflowProfile, serviceContext);
    }

    /**
    * Caches the workflow profile in the entity cache if it is enabled.
    *
    * @param workflowProfile the workflow profile
    */
    public static void cacheResult(
        com.stpl.app.model.WorkflowProfile workflowProfile) {
        getPersistence().cacheResult(workflowProfile);
    }

    /**
    * Caches the workflow profiles in the entity cache if it is enabled.
    *
    * @param workflowProfiles the workflow profiles
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowProfile> workflowProfiles) {
        getPersistence().cacheResult(workflowProfiles);
    }

    /**
    * Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
    *
    * @param processSid the primary key for the new workflow profile
    * @return the new workflow profile
    */
    public static com.stpl.app.model.WorkflowProfile create(int processSid) {
        return getPersistence().create(processSid);
    }

    /**
    * Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile that was removed
    * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProfile remove(int processSid)
        throws com.stpl.app.NoSuchWorkflowProfileException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(processSid);
    }

    public static com.stpl.app.model.WorkflowProfile updateImpl(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(workflowProfile);
    }

    /**
    * Returns the workflow profile with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowProfileException} if it could not be found.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile
    * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProfile findByPrimaryKey(
        int processSid)
        throws com.stpl.app.NoSuchWorkflowProfileException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(processSid);
    }

    /**
    * Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowProfile fetchByPrimaryKey(
        int processSid) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(processSid);
    }

    /**
    * Returns all the workflow profiles.
    *
    * @return the workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProfile> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the workflow profiles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow profiles
    * @param end the upper bound of the range of workflow profiles (not inclusive)
    * @return the range of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProfile> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the workflow profiles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow profiles
    * @param end the upper bound of the range of workflow profiles (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowProfile> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the workflow profiles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of workflow profiles.
    *
    * @return the number of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static WorkflowProfilePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (WorkflowProfilePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    WorkflowProfilePersistence.class.getName());

            ReferenceRegistry.registerReference(WorkflowProfileUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(WorkflowProfilePersistence persistence) {
    }
}
