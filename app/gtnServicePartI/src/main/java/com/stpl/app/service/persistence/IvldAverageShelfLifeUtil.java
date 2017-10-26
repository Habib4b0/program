package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldAverageShelfLife;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld average shelf life service. This utility wraps {@link IvldAverageShelfLifePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAverageShelfLifePersistence
 * @see IvldAverageShelfLifePersistenceImpl
 * @generated
 */
public class IvldAverageShelfLifeUtil {
    private static IvldAverageShelfLifePersistence _persistence;

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
    public static void clearCache(IvldAverageShelfLife ivldAverageShelfLife) {
        getPersistence().clearCache(ivldAverageShelfLife);
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
    public static List<IvldAverageShelfLife> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldAverageShelfLife> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldAverageShelfLife> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldAverageShelfLife update(
        IvldAverageShelfLife ivldAverageShelfLife) throws SystemException {
        return getPersistence().update(ivldAverageShelfLife);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldAverageShelfLife update(
        IvldAverageShelfLife ivldAverageShelfLife, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldAverageShelfLife, serviceContext);
    }

    /**
    * Caches the ivld average shelf life in the entity cache if it is enabled.
    *
    * @param ivldAverageShelfLife the ivld average shelf life
    */
    public static void cacheResult(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife) {
        getPersistence().cacheResult(ivldAverageShelfLife);
    }

    /**
    * Caches the ivld average shelf lifes in the entity cache if it is enabled.
    *
    * @param ivldAverageShelfLifes the ivld average shelf lifes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldAverageShelfLife> ivldAverageShelfLifes) {
        getPersistence().cacheResult(ivldAverageShelfLifes);
    }

    /**
    * Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
    *
    * @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
    * @return the new ivld average shelf life
    */
    public static com.stpl.app.model.IvldAverageShelfLife create(
        int ivldAverageShelfLifeSid) {
        return getPersistence().create(ivldAverageShelfLifeSid);
    }

    /**
    * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life that was removed
    * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife remove(
        int ivldAverageShelfLifeSid)
        throws com.stpl.app.NoSuchIvldAverageShelfLifeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldAverageShelfLifeSid);
    }

    public static com.stpl.app.model.IvldAverageShelfLife updateImpl(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldAverageShelfLife);
    }

    /**
    * Returns the ivld average shelf life with the primary key or throws a {@link com.stpl.app.NoSuchIvldAverageShelfLifeException} if it could not be found.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life
    * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife findByPrimaryKey(
        int ivldAverageShelfLifeSid)
        throws com.stpl.app.NoSuchIvldAverageShelfLifeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldAverageShelfLifeSid);
    }

    /**
    * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife fetchByPrimaryKey(
        int ivldAverageShelfLifeSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldAverageShelfLifeSid);
    }

    /**
    * Returns all the ivld average shelf lifes.
    *
    * @return the ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld average shelf lifes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld average shelf lifes
    * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
    * @return the range of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld average shelf lifes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld average shelf lifes
    * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld average shelf lifes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld average shelf lifes.
    *
    * @return the number of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldAverageShelfLifePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldAverageShelfLifePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldAverageShelfLifePersistence.class.getName());

            ReferenceRegistry.registerReference(IvldAverageShelfLifeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldAverageShelfLifePersistence persistence) {
    }
}
