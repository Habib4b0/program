package com.stpl.app.service.persistence;

import com.stpl.app.model.NmActualPpa;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the nm actual ppa service. This utility wraps {@link NmActualPpaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualPpaPersistence
 * @see NmActualPpaPersistenceImpl
 * @generated
 */
public class NmActualPpaUtil {
    private static NmActualPpaPersistence _persistence;

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
    public static void clearCache(NmActualPpa nmActualPpa) {
        getPersistence().clearCache(nmActualPpa);
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
    public static List<NmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NmActualPpa> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NmActualPpa update(NmActualPpa nmActualPpa)
        throws SystemException {
        return getPersistence().update(nmActualPpa);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NmActualPpa update(NmActualPpa nmActualPpa,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(nmActualPpa, serviceContext);
    }

    /**
    * Caches the nm actual ppa in the entity cache if it is enabled.
    *
    * @param nmActualPpa the nm actual ppa
    */
    public static void cacheResult(com.stpl.app.model.NmActualPpa nmActualPpa) {
        getPersistence().cacheResult(nmActualPpa);
    }

    /**
    * Caches the nm actual ppas in the entity cache if it is enabled.
    *
    * @param nmActualPpas the nm actual ppas
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NmActualPpa> nmActualPpas) {
        getPersistence().cacheResult(nmActualPpas);
    }

    /**
    * Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
    *
    * @param nmActualPpaPK the primary key for the new nm actual ppa
    * @return the new nm actual ppa
    */
    public static com.stpl.app.model.NmActualPpa create(
        NmActualPpaPK nmActualPpaPK) {
        return getPersistence().create(nmActualPpaPK);
    }

    /**
    * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa that was removed
    * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmActualPpa remove(
        NmActualPpaPK nmActualPpaPK)
        throws com.stpl.app.NoSuchNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nmActualPpaPK);
    }

    public static com.stpl.app.model.NmActualPpa updateImpl(
        com.stpl.app.model.NmActualPpa nmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nmActualPpa);
    }

    /**
    * Returns the nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchNmActualPpaException} if it could not be found.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa
    * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmActualPpa findByPrimaryKey(
        NmActualPpaPK nmActualPpaPK)
        throws com.stpl.app.NoSuchNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nmActualPpaPK);
    }

    /**
    * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmActualPpa fetchByPrimaryKey(
        NmActualPpaPK nmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nmActualPpaPK);
    }

    /**
    * Returns all the nm actual ppas.
    *
    * @return the nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmActualPpa> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual ppas
    * @param end the upper bound of the range of nm actual ppas (not inclusive)
    * @return the range of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmActualPpa> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual ppas
    * @param end the upper bound of the range of nm actual ppas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmActualPpa> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the nm actual ppas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of nm actual ppas.
    *
    * @return the number of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NmActualPpaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NmActualPpaPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmActualPpaPersistence.class.getName());

            ReferenceRegistry.registerReference(NmActualPpaUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NmActualPpaPersistence persistence) {
    }
}
