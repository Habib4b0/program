package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldGlCostCenter;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld gl cost center service. This utility wraps {@link IvldGlCostCenterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlCostCenterPersistence
 * @see IvldGlCostCenterPersistenceImpl
 * @generated
 */
public class IvldGlCostCenterUtil {
    private static IvldGlCostCenterPersistence _persistence;

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
    public static void clearCache(IvldGlCostCenter ivldGlCostCenter) {
        getPersistence().clearCache(ivldGlCostCenter);
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
    public static List<IvldGlCostCenter> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldGlCostCenter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldGlCostCenter> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldGlCostCenter update(IvldGlCostCenter ivldGlCostCenter)
        throws SystemException {
        return getPersistence().update(ivldGlCostCenter);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldGlCostCenter update(IvldGlCostCenter ivldGlCostCenter,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldGlCostCenter, serviceContext);
    }

    /**
    * Caches the ivld gl cost center in the entity cache if it is enabled.
    *
    * @param ivldGlCostCenter the ivld gl cost center
    */
    public static void cacheResult(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter) {
        getPersistence().cacheResult(ivldGlCostCenter);
    }

    /**
    * Caches the ivld gl cost centers in the entity cache if it is enabled.
    *
    * @param ivldGlCostCenters the ivld gl cost centers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldGlCostCenter> ivldGlCostCenters) {
        getPersistence().cacheResult(ivldGlCostCenters);
    }

    /**
    * Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
    *
    * @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
    * @return the new ivld gl cost center
    */
    public static com.stpl.app.model.IvldGlCostCenter create(
        int ivldGlCostCenterSid) {
        return getPersistence().create(ivldGlCostCenterSid);
    }

    /**
    * Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
    * @return the ivld gl cost center that was removed
    * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlCostCenter remove(
        int ivldGlCostCenterSid)
        throws com.stpl.app.NoSuchIvldGlCostCenterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldGlCostCenterSid);
    }

    public static com.stpl.app.model.IvldGlCostCenter updateImpl(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldGlCostCenter);
    }

    /**
    * Returns the ivld gl cost center with the primary key or throws a {@link com.stpl.app.NoSuchIvldGlCostCenterException} if it could not be found.
    *
    * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
    * @return the ivld gl cost center
    * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlCostCenter findByPrimaryKey(
        int ivldGlCostCenterSid)
        throws com.stpl.app.NoSuchIvldGlCostCenterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldGlCostCenterSid);
    }

    /**
    * Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
    * @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlCostCenter fetchByPrimaryKey(
        int ivldGlCostCenterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldGlCostCenterSid);
    }

    /**
    * Returns all the ivld gl cost centers.
    *
    * @return the ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlCostCenter> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld gl cost centers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl cost centers
    * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
    * @return the range of ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlCostCenter> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld gl cost centers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl cost centers
    * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlCostCenter> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld gl cost centers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld gl cost centers.
    *
    * @return the number of ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldGlCostCenterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldGlCostCenterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldGlCostCenterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldGlCostCenterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldGlCostCenterPersistence persistence) {
    }
}
