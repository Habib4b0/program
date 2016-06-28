package com.stpl.app.service.persistence;

import com.stpl.app.model.FcpProj;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the fcp proj service. This utility wraps {@link FcpProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpProjPersistence
 * @see FcpProjPersistenceImpl
 * @generated
 */
public class FcpProjUtil {
    private static FcpProjPersistence _persistence;

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
    public static void clearCache(FcpProj fcpProj) {
        getPersistence().clearCache(fcpProj);
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
    public static List<FcpProj> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FcpProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FcpProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static FcpProj update(FcpProj fcpProj) throws SystemException {
        return getPersistence().update(fcpProj);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static FcpProj update(FcpProj fcpProj, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(fcpProj, serviceContext);
    }

    /**
    * Caches the fcp proj in the entity cache if it is enabled.
    *
    * @param fcpProj the fcp proj
    */
    public static void cacheResult(com.stpl.app.model.FcpProj fcpProj) {
        getPersistence().cacheResult(fcpProj);
    }

    /**
    * Caches the fcp projs in the entity cache if it is enabled.
    *
    * @param fcpProjs the fcp projs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.FcpProj> fcpProjs) {
        getPersistence().cacheResult(fcpProjs);
    }

    /**
    * Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
    *
    * @param fcpProjPK the primary key for the new fcp proj
    * @return the new fcp proj
    */
    public static com.stpl.app.model.FcpProj create(FcpProjPK fcpProjPK) {
        return getPersistence().create(fcpProjPK);
    }

    /**
    * Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpProjPK the primary key of the fcp proj
    * @return the fcp proj that was removed
    * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpProj remove(FcpProjPK fcpProjPK)
        throws com.stpl.app.NoSuchFcpProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(fcpProjPK);
    }

    public static com.stpl.app.model.FcpProj updateImpl(
        com.stpl.app.model.FcpProj fcpProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(fcpProj);
    }

    /**
    * Returns the fcp proj with the primary key or throws a {@link com.stpl.app.NoSuchFcpProjException} if it could not be found.
    *
    * @param fcpProjPK the primary key of the fcp proj
    * @return the fcp proj
    * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpProj findByPrimaryKey(
        FcpProjPK fcpProjPK)
        throws com.stpl.app.NoSuchFcpProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(fcpProjPK);
    }

    /**
    * Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param fcpProjPK the primary key of the fcp proj
    * @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpProj fetchByPrimaryKey(
        FcpProjPK fcpProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(fcpProjPK);
    }

    /**
    * Returns all the fcp projs.
    *
    * @return the fcp projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the fcp projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp projs
    * @param end the upper bound of the range of fcp projs (not inclusive)
    * @return the range of fcp projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the fcp projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp projs
    * @param end the upper bound of the range of fcp projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of fcp projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the fcp projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of fcp projs.
    *
    * @return the number of fcp projs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FcpProjPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FcpProjPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FcpProjPersistence.class.getName());

            ReferenceRegistry.registerReference(FcpProjUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FcpProjPersistence persistence) {
    }
}
