package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscProj;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m supplemental disc proj service. This utility wraps {@link MSupplementalDiscProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscProjPersistence
 * @see MSupplementalDiscProjPersistenceImpl
 * @generated
 */
public class MSupplementalDiscProjUtil {
    private static MSupplementalDiscProjPersistence _persistence;

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
    public static void clearCache(MSupplementalDiscProj mSupplementalDiscProj) {
        getPersistence().clearCache(mSupplementalDiscProj);
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
    public static List<MSupplementalDiscProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MSupplementalDiscProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MSupplementalDiscProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MSupplementalDiscProj update(
        MSupplementalDiscProj mSupplementalDiscProj) throws SystemException {
        return getPersistence().update(mSupplementalDiscProj);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MSupplementalDiscProj update(
        MSupplementalDiscProj mSupplementalDiscProj,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mSupplementalDiscProj, serviceContext);
    }

    /**
    * Caches the m supplemental disc proj in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscProj the m supplemental disc proj
    */
    public static void cacheResult(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj) {
        getPersistence().cacheResult(mSupplementalDiscProj);
    }

    /**
    * Caches the m supplemental disc projs in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscProjs the m supplemental disc projs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MSupplementalDiscProj> mSupplementalDiscProjs) {
        getPersistence().cacheResult(mSupplementalDiscProjs);
    }

    /**
    * Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
    *
    * @param projectionDetailsSid the primary key for the new m supplemental disc proj
    * @return the new m supplemental disc proj
    */
    public static com.stpl.app.model.MSupplementalDiscProj create(
        int projectionDetailsSid) {
        return getPersistence().create(projectionDetailsSid);
    }

    /**
    * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj that was removed
    * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscProj remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(projectionDetailsSid);
    }

    public static com.stpl.app.model.MSupplementalDiscProj updateImpl(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mSupplementalDiscProj);
    }

    /**
    * Returns the m supplemental disc proj with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscProjException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj
    * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscProj findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MSupplementalDiscProj fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
    }

    /**
    * Returns all the m supplemental disc projs.
    *
    * @return the m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc projs
    * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
    * @return the range of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc projs
    * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m supplemental disc projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m supplemental disc projs.
    *
    * @return the number of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MSupplementalDiscProjPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MSupplementalDiscProjPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MSupplementalDiscProjPersistence.class.getName());

            ReferenceRegistry.registerReference(MSupplementalDiscProjUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MSupplementalDiscProjPersistence persistence) {
    }
}
