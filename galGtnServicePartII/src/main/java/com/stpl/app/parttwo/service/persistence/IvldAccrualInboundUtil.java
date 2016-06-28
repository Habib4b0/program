package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldAccrualInbound;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld accrual inbound service. This utility wraps {@link IvldAccrualInboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAccrualInboundPersistence
 * @see IvldAccrualInboundPersistenceImpl
 * @generated
 */
public class IvldAccrualInboundUtil {
    private static IvldAccrualInboundPersistence _persistence;

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
    public static void clearCache(IvldAccrualInbound ivldAccrualInbound) {
        getPersistence().clearCache(ivldAccrualInbound);
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
    public static List<IvldAccrualInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldAccrualInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldAccrualInbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldAccrualInbound update(
        IvldAccrualInbound ivldAccrualInbound) throws SystemException {
        return getPersistence().update(ivldAccrualInbound);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldAccrualInbound update(
        IvldAccrualInbound ivldAccrualInbound, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldAccrualInbound, serviceContext);
    }

    /**
    * Caches the ivld accrual inbound in the entity cache if it is enabled.
    *
    * @param ivldAccrualInbound the ivld accrual inbound
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound) {
        getPersistence().cacheResult(ivldAccrualInbound);
    }

    /**
    * Caches the ivld accrual inbounds in the entity cache if it is enabled.
    *
    * @param ivldAccrualInbounds the ivld accrual inbounds
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> ivldAccrualInbounds) {
        getPersistence().cacheResult(ivldAccrualInbounds);
    }

    /**
    * Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
    *
    * @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
    * @return the new ivld accrual inbound
    */
    public static com.stpl.app.parttwo.model.IvldAccrualInbound create(
        int ivldAccrualInboundSid) {
        return getPersistence().create(ivldAccrualInboundSid);
    }

    /**
    * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldAccrualInbound remove(
        int ivldAccrualInboundSid)
        throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldAccrualInboundSid);
    }

    public static com.stpl.app.parttwo.model.IvldAccrualInbound updateImpl(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldAccrualInbound);
    }

    /**
    * Returns the ivld accrual inbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldAccrualInboundException} if it could not be found.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound
    * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldAccrualInbound findByPrimaryKey(
        int ivldAccrualInboundSid)
        throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldAccrualInboundSid);
    }

    /**
    * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldAccrualInbound fetchByPrimaryKey(
        int ivldAccrualInboundSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldAccrualInboundSid);
    }

    /**
    * Returns all the ivld accrual inbounds.
    *
    * @return the ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld accrual inbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld accrual inbounds
    * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
    * @return the range of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld accrual inbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld accrual inbounds
    * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld accrual inbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld accrual inbounds.
    *
    * @return the number of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldAccrualInboundPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldAccrualInboundPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldAccrualInboundPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldAccrualInboundUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldAccrualInboundPersistence persistence) {
    }
}
