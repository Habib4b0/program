package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the projection cust details service. This utility wraps {@link ProjectionCustDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustDetailsPersistence
 * @see ProjectionCustDetailsPersistenceImpl
 * @generated
 */
public class ProjectionCustDetailsUtil {
    private static ProjectionCustDetailsPersistence _persistence;

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
    public static void clearCache(ProjectionCustDetails projectionCustDetails) {
        getPersistence().clearCache(projectionCustDetails);
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
    public static List<ProjectionCustDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProjectionCustDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProjectionCustDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ProjectionCustDetails update(
        ProjectionCustDetails projectionCustDetails) throws SystemException {
        return getPersistence().update(projectionCustDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ProjectionCustDetails update(
        ProjectionCustDetails projectionCustDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(projectionCustDetails, serviceContext);
    }

    /**
    * Caches the projection cust details in the entity cache if it is enabled.
    *
    * @param projectionCustDetails the projection cust details
    */
    public static void cacheResult(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails) {
        getPersistence().cacheResult(projectionCustDetails);
    }

    /**
    * Caches the projection cust detailses in the entity cache if it is enabled.
    *
    * @param projectionCustDetailses the projection cust detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionCustDetails> projectionCustDetailses) {
        getPersistence().cacheResult(projectionCustDetailses);
    }

    /**
    * Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
    *
    * @param customerDetailsId the primary key for the new projection cust details
    * @return the new projection cust details
    */
    public static com.stpl.app.model.ProjectionCustDetails create(
        int customerDetailsId) {
        return getPersistence().create(customerDetailsId);
    }

    /**
    * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details that was removed
    * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionCustDetails remove(
        int customerDetailsId)
        throws com.stpl.app.NoSuchProjectionCustDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(customerDetailsId);
    }

    public static com.stpl.app.model.ProjectionCustDetails updateImpl(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(projectionCustDetails);
    }

    /**
    * Returns the projection cust details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionCustDetailsException} if it could not be found.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details
    * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionCustDetails findByPrimaryKey(
        int customerDetailsId)
        throws com.stpl.app.NoSuchProjectionCustDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(customerDetailsId);
    }

    /**
    * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionCustDetails fetchByPrimaryKey(
        int customerDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(customerDetailsId);
    }

    /**
    * Returns all the projection cust detailses.
    *
    * @return the projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the projection cust detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust detailses
    * @param end the upper bound of the range of projection cust detailses (not inclusive)
    * @return the range of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the projection cust detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust detailses
    * @param end the upper bound of the range of projection cust detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the projection cust detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of projection cust detailses.
    *
    * @return the number of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProjectionCustDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProjectionCustDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionCustDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ProjectionCustDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProjectionCustDetailsPersistence persistence) {
    }
}
