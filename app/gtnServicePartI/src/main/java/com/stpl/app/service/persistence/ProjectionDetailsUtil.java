package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the projection details service. This utility wraps {@link ProjectionDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionDetailsPersistence
 * @see ProjectionDetailsPersistenceImpl
 * @generated
 */
public class ProjectionDetailsUtil {
    private static ProjectionDetailsPersistence _persistence;

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
    public static void clearCache(ProjectionDetails projectionDetails) {
        getPersistence().clearCache(projectionDetails);
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
    public static List<ProjectionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProjectionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProjectionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ProjectionDetails update(ProjectionDetails projectionDetails)
        throws SystemException {
        return getPersistence().update(projectionDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ProjectionDetails update(
        ProjectionDetails projectionDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(projectionDetails, serviceContext);
    }

    /**
    * Caches the projection details in the entity cache if it is enabled.
    *
    * @param projectionDetails the projection details
    */
    public static void cacheResult(
        com.stpl.app.model.ProjectionDetails projectionDetails) {
        getPersistence().cacheResult(projectionDetails);
    }

    /**
    * Caches the projection detailses in the entity cache if it is enabled.
    *
    * @param projectionDetailses the projection detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionDetails> projectionDetailses) {
        getPersistence().cacheResult(projectionDetailses);
    }

    /**
    * Creates a new projection details with the primary key. Does not add the projection details to the database.
    *
    * @param projectionDetailsSid the primary key for the new projection details
    * @return the new projection details
    */
    public static com.stpl.app.model.ProjectionDetails create(
        int projectionDetailsSid) {
        return getPersistence().create(projectionDetailsSid);
    }

    /**
    * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details that was removed
    * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionDetails remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchProjectionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionDetailsSid);
    }

    public static com.stpl.app.model.ProjectionDetails updateImpl(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(projectionDetails);
    }

    /**
    * Returns the projection details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionDetailsException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details
    * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionDetails findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchProjectionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionDetails fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns all the projection detailses.
    *
    * @return the projection detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the projection detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection detailses
    * @param end the upper bound of the range of projection detailses (not inclusive)
    * @return the range of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the projection detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection detailses
    * @param end the upper bound of the range of projection detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the projection detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of projection detailses.
    *
    * @return the number of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProjectionDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProjectionDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ProjectionDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProjectionDetailsPersistence persistence) {
    }
}
