package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldGlBalance;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld gl balance service. This utility wraps {@link IvldGlBalancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlBalancePersistence
 * @see IvldGlBalancePersistenceImpl
 * @generated
 */
public class IvldGlBalanceUtil {
    private static IvldGlBalancePersistence _persistence;

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
    public static void clearCache(IvldGlBalance ivldGlBalance) {
        getPersistence().clearCache(ivldGlBalance);
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
    public static List<IvldGlBalance> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldGlBalance> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldGlBalance> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldGlBalance update(IvldGlBalance ivldGlBalance)
        throws SystemException {
        return getPersistence().update(ivldGlBalance);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldGlBalance update(IvldGlBalance ivldGlBalance,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldGlBalance, serviceContext);
    }

    /**
    * Caches the ivld gl balance in the entity cache if it is enabled.
    *
    * @param ivldGlBalance the ivld gl balance
    */
    public static void cacheResult(
        com.stpl.app.model.IvldGlBalance ivldGlBalance) {
        getPersistence().cacheResult(ivldGlBalance);
    }

    /**
    * Caches the ivld gl balances in the entity cache if it is enabled.
    *
    * @param ivldGlBalances the ivld gl balances
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldGlBalance> ivldGlBalances) {
        getPersistence().cacheResult(ivldGlBalances);
    }

    /**
    * Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
    *
    * @param ivldGlBalanceSid the primary key for the new ivld gl balance
    * @return the new ivld gl balance
    */
    public static com.stpl.app.model.IvldGlBalance create(int ivldGlBalanceSid) {
        return getPersistence().create(ivldGlBalanceSid);
    }

    /**
    * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance that was removed
    * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlBalance remove(int ivldGlBalanceSid)
        throws com.stpl.app.NoSuchIvldGlBalanceException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldGlBalanceSid);
    }

    public static com.stpl.app.model.IvldGlBalance updateImpl(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldGlBalance);
    }

    /**
    * Returns the ivld gl balance with the primary key or throws a {@link com.stpl.app.NoSuchIvldGlBalanceException} if it could not be found.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance
    * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlBalance findByPrimaryKey(
        int ivldGlBalanceSid)
        throws com.stpl.app.NoSuchIvldGlBalanceException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldGlBalanceSid);
    }

    /**
    * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldGlBalance fetchByPrimaryKey(
        int ivldGlBalanceSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldGlBalanceSid);
    }

    /**
    * Returns all the ivld gl balances.
    *
    * @return the ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlBalance> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld gl balances.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl balances
    * @param end the upper bound of the range of ivld gl balances (not inclusive)
    * @return the range of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlBalance> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld gl balances.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl balances
    * @param end the upper bound of the range of ivld gl balances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldGlBalance> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld gl balances from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld gl balances.
    *
    * @return the number of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldGlBalancePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldGlBalancePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldGlBalancePersistence.class.getName());

            ReferenceRegistry.registerReference(IvldGlBalanceUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldGlBalancePersistence persistence) {
    }
}
