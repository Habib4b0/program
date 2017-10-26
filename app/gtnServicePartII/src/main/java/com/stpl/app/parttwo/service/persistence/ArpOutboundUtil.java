package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.ArpOutbound;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the arp outbound service. This utility wraps {@link ArpOutboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ArpOutboundPersistence
 * @see ArpOutboundPersistenceImpl
 * @generated
 */
public class ArpOutboundUtil {
    private static ArpOutboundPersistence _persistence;

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
    public static void clearCache(ArpOutbound arpOutbound) {
        getPersistence().clearCache(arpOutbound);
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
    public static List<ArpOutbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ArpOutbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ArpOutbound> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ArpOutbound update(ArpOutbound arpOutbound)
        throws SystemException {
        return getPersistence().update(arpOutbound);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ArpOutbound update(ArpOutbound arpOutbound,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(arpOutbound, serviceContext);
    }

    /**
    * Caches the arp outbound in the entity cache if it is enabled.
    *
    * @param arpOutbound the arp outbound
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound) {
        getPersistence().cacheResult(arpOutbound);
    }

    /**
    * Caches the arp outbounds in the entity cache if it is enabled.
    *
    * @param arpOutbounds the arp outbounds
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.ArpOutbound> arpOutbounds) {
        getPersistence().cacheResult(arpOutbounds);
    }

    /**
    * Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
    *
    * @param arpOutboundPK the primary key for the new arp outbound
    * @return the new arp outbound
    */
    public static com.stpl.app.parttwo.model.ArpOutbound create(
        ArpOutboundPK arpOutboundPK) {
        return getPersistence().create(arpOutboundPK);
    }

    /**
    * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound that was removed
    * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.ArpOutbound remove(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(arpOutboundPK);
    }

    public static com.stpl.app.parttwo.model.ArpOutbound updateImpl(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(arpOutbound);
    }

    /**
    * Returns the arp outbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchArpOutboundException} if it could not be found.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound
    * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.ArpOutbound findByPrimaryKey(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(arpOutboundPK);
    }

    /**
    * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.ArpOutbound fetchByPrimaryKey(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(arpOutboundPK);
    }

    /**
    * Returns all the arp outbounds.
    *
    * @return the arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of arp outbounds
    * @param end the upper bound of the range of arp outbounds (not inclusive)
    * @return the range of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of arp outbounds
    * @param end the upper bound of the range of arp outbounds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the arp outbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of arp outbounds.
    *
    * @return the number of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ArpOutboundPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ArpOutboundPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    ArpOutboundPersistence.class.getName());

            ReferenceRegistry.registerReference(ArpOutboundUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ArpOutboundPersistence persistence) {
    }
}
