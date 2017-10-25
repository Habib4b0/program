package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldReturnReserve;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw ivld return reserve service. This utility wraps {@link VwIvldReturnReservePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldReturnReservePersistence
 * @see VwIvldReturnReservePersistenceImpl
 * @generated
 */
public class VwIvldReturnReserveUtil {
    private static VwIvldReturnReservePersistence _persistence;

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
    public static void clearCache(VwIvldReturnReserve vwIvldReturnReserve) {
        getPersistence().clearCache(vwIvldReturnReserve);
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
    public static List<VwIvldReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwIvldReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwIvldReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwIvldReturnReserve update(
        VwIvldReturnReserve vwIvldReturnReserve) throws SystemException {
        return getPersistence().update(vwIvldReturnReserve);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwIvldReturnReserve update(
        VwIvldReturnReserve vwIvldReturnReserve, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(vwIvldReturnReserve, serviceContext);
    }

    /**
    * Caches the vw ivld return reserve in the entity cache if it is enabled.
    *
    * @param vwIvldReturnReserve the vw ivld return reserve
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve) {
        getPersistence().cacheResult(vwIvldReturnReserve);
    }

    /**
    * Caches the vw ivld return reserves in the entity cache if it is enabled.
    *
    * @param vwIvldReturnReserves the vw ivld return reserves
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> vwIvldReturnReserves) {
        getPersistence().cacheResult(vwIvldReturnReserves);
    }

    /**
    * Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
    *
    * @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
    * @return the new vw ivld return reserve
    */
    public static com.stpl.app.parttwo.model.VwIvldReturnReserve create(
        int ivldReturnReserveSid) {
        return getPersistence().create(ivldReturnReserveSid);
    }

    /**
    * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldReturnReserve remove(
        int ivldReturnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldReturnReserveSid);
    }

    public static com.stpl.app.parttwo.model.VwIvldReturnReserve updateImpl(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwIvldReturnReserve);
    }

    /**
    * Returns the vw ivld return reserve with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException} if it could not be found.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve
    * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldReturnReserve findByPrimaryKey(
        int ivldReturnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldReturnReserveSid);
    }

    /**
    * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldReturnReserve fetchByPrimaryKey(
        int ivldReturnReserveSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldReturnReserveSid);
    }

    /**
    * Returns all the vw ivld return reserves.
    *
    * @return the vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw ivld return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld return reserves
    * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
    * @return the range of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw ivld return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld return reserves
    * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw ivld return reserves from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw ivld return reserves.
    *
    * @return the number of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwIvldReturnReservePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwIvldReturnReservePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwIvldReturnReservePersistence.class.getName());

            ReferenceRegistry.registerReference(VwIvldReturnReserveUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwIvldReturnReservePersistence persistence) {
    }
}
