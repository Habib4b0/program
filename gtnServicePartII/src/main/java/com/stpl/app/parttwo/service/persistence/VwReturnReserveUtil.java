package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwReturnReserve;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw return reserve service. This utility wraps {@link VwReturnReservePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwReturnReservePersistence
 * @see VwReturnReservePersistenceImpl
 * @generated
 */
public class VwReturnReserveUtil {
    private static VwReturnReservePersistence _persistence;

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
    public static void clearCache(VwReturnReserve vwReturnReserve) {
        getPersistence().clearCache(vwReturnReserve);
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
    public static List<VwReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwReturnReserve> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwReturnReserve update(VwReturnReserve vwReturnReserve)
        throws SystemException {
        return getPersistence().update(vwReturnReserve);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwReturnReserve update(VwReturnReserve vwReturnReserve,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwReturnReserve, serviceContext);
    }

    /**
    * Caches the vw return reserve in the entity cache if it is enabled.
    *
    * @param vwReturnReserve the vw return reserve
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwReturnReserve vwReturnReserve) {
        getPersistence().cacheResult(vwReturnReserve);
    }

    /**
    * Caches the vw return reserves in the entity cache if it is enabled.
    *
    * @param vwReturnReserves the vw return reserves
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwReturnReserve> vwReturnReserves) {
        getPersistence().cacheResult(vwReturnReserves);
    }

    /**
    * Creates a new vw return reserve with the primary key. Does not add the vw return reserve to the database.
    *
    * @param returnReserveSid the primary key for the new vw return reserve
    * @return the new vw return reserve
    */
    public static com.stpl.app.parttwo.model.VwReturnReserve create(
        int returnReserveSid) {
        return getPersistence().create(returnReserveSid);
    }

    /**
    * Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param returnReserveSid the primary key of the vw return reserve
    * @return the vw return reserve that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwReturnReserve remove(
        int returnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(returnReserveSid);
    }

    public static com.stpl.app.parttwo.model.VwReturnReserve updateImpl(
        com.stpl.app.parttwo.model.VwReturnReserve vwReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwReturnReserve);
    }

    /**
    * Returns the vw return reserve with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwReturnReserveException} if it could not be found.
    *
    * @param returnReserveSid the primary key of the vw return reserve
    * @return the vw return reserve
    * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwReturnReserve findByPrimaryKey(
        int returnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(returnReserveSid);
    }

    /**
    * Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param returnReserveSid the primary key of the vw return reserve
    * @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwReturnReserve fetchByPrimaryKey(
        int returnReserveSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(returnReserveSid);
    }

    /**
    * Returns all the vw return reserves.
    *
    * @return the vw return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwReturnReserve> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw return reserves
    * @param end the upper bound of the range of vw return reserves (not inclusive)
    * @return the range of vw return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwReturnReserve> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw return reserves
    * @param end the upper bound of the range of vw return reserves (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw return reserves
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwReturnReserve> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw return reserves from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw return reserves.
    *
    * @return the number of vw return reserves
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwReturnReservePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwReturnReservePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwReturnReservePersistence.class.getName());

            ReferenceRegistry.registerReference(VwReturnReserveUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwReturnReservePersistence persistence) {
    }
}
