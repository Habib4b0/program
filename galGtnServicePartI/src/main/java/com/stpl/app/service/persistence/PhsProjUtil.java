package com.stpl.app.service.persistence;

import com.stpl.app.model.PhsProj;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the phs proj service. This utility wraps {@link PhsProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsProjPersistence
 * @see PhsProjPersistenceImpl
 * @generated
 */
public class PhsProjUtil {
    private static PhsProjPersistence _persistence;

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
    public static void clearCache(PhsProj phsProj) {
        getPersistence().clearCache(phsProj);
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
    public static List<PhsProj> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PhsProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PhsProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static PhsProj update(PhsProj phsProj) throws SystemException {
        return getPersistence().update(phsProj);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static PhsProj update(PhsProj phsProj, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(phsProj, serviceContext);
    }

    /**
    * Caches the phs proj in the entity cache if it is enabled.
    *
    * @param phsProj the phs proj
    */
    public static void cacheResult(com.stpl.app.model.PhsProj phsProj) {
        getPersistence().cacheResult(phsProj);
    }

    /**
    * Caches the phs projs in the entity cache if it is enabled.
    *
    * @param phsProjs the phs projs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.PhsProj> phsProjs) {
        getPersistence().cacheResult(phsProjs);
    }

    /**
    * Creates a new phs proj with the primary key. Does not add the phs proj to the database.
    *
    * @param phsProjPK the primary key for the new phs proj
    * @return the new phs proj
    */
    public static com.stpl.app.model.PhsProj create(PhsProjPK phsProjPK) {
        return getPersistence().create(phsProjPK);
    }

    /**
    * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj that was removed
    * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PhsProj remove(PhsProjPK phsProjPK)
        throws com.stpl.app.NoSuchPhsProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(phsProjPK);
    }

    public static com.stpl.app.model.PhsProj updateImpl(
        com.stpl.app.model.PhsProj phsProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(phsProj);
    }

    /**
    * Returns the phs proj with the primary key or throws a {@link com.stpl.app.NoSuchPhsProjException} if it could not be found.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj
    * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PhsProj findByPrimaryKey(
        PhsProjPK phsProjPK)
        throws com.stpl.app.NoSuchPhsProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(phsProjPK);
    }

    /**
    * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PhsProj fetchByPrimaryKey(
        PhsProjPK phsProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(phsProjPK);
    }

    /**
    * Returns all the phs projs.
    *
    * @return the phs projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PhsProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the phs projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs projs
    * @param end the upper bound of the range of phs projs (not inclusive)
    * @return the range of phs projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PhsProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the phs projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs projs
    * @param end the upper bound of the range of phs projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of phs projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PhsProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the phs projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of phs projs.
    *
    * @return the number of phs projs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PhsProjPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PhsProjPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PhsProjPersistence.class.getName());

            ReferenceRegistry.registerReference(PhsProjUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PhsProjPersistence persistence) {
    }
}
