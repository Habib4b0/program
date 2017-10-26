package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the projection master service. This utility wraps {@link ProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionMasterPersistence
 * @see ProjectionMasterPersistenceImpl
 * @generated
 */
public class ProjectionMasterUtil {
    private static ProjectionMasterPersistence _persistence;

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
    public static void clearCache(ProjectionMaster projectionMaster) {
        getPersistence().clearCache(projectionMaster);
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
    public static List<ProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProjectionMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ProjectionMaster update(ProjectionMaster projectionMaster)
        throws SystemException {
        return getPersistence().update(projectionMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ProjectionMaster update(ProjectionMaster projectionMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(projectionMaster, serviceContext);
    }

    /**
    * Caches the projection master in the entity cache if it is enabled.
    *
    * @param projectionMaster the projection master
    */
    public static void cacheResult(
        com.stpl.app.model.ProjectionMaster projectionMaster) {
        getPersistence().cacheResult(projectionMaster);
    }

    /**
    * Caches the projection masters in the entity cache if it is enabled.
    *
    * @param projectionMasters the projection masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionMaster> projectionMasters) {
        getPersistence().cacheResult(projectionMasters);
    }

    /**
    * Creates a new projection master with the primary key. Does not add the projection master to the database.
    *
    * @param projectionMasterSid the primary key for the new projection master
    * @return the new projection master
    */
    public static com.stpl.app.model.ProjectionMaster create(
        int projectionMasterSid) {
        return getPersistence().create(projectionMasterSid);
    }

    /**
    * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master that was removed
    * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster remove(
        int projectionMasterSid)
        throws com.stpl.app.NoSuchProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionMasterSid);
    }

    public static com.stpl.app.model.ProjectionMaster updateImpl(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(projectionMaster);
    }

    /**
    * Returns the projection master with the primary key or throws a {@link com.stpl.app.NoSuchProjectionMasterException} if it could not be found.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master
    * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster findByPrimaryKey(
        int projectionMasterSid)
        throws com.stpl.app.NoSuchProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionMasterSid);
    }

    /**
    * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster fetchByPrimaryKey(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionMasterSid);
    }

    /**
    * Returns all the projection masters.
    *
    * @return the projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @return the range of projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of projection masters.
    *
    * @return the number of projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProjectionMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProjectionMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ProjectionMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProjectionMasterPersistence persistence) {
    }
}
