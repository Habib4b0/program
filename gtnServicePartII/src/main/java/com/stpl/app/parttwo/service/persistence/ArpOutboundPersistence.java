package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.ArpOutbound;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ArpOutboundPersistenceImpl
 * @see ArpOutboundUtil
 * @generated
 */
public interface ArpOutboundPersistence extends BasePersistence<ArpOutbound> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ArpOutboundUtil} to access the arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the arp outbound in the entity cache if it is enabled.
    *
    * @param arpOutbound the arp outbound
    */
    public void cacheResult(com.stpl.app.parttwo.model.ArpOutbound arpOutbound);

    /**
    * Caches the arp outbounds in the entity cache if it is enabled.
    *
    * @param arpOutbounds the arp outbounds
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.ArpOutbound> arpOutbounds);

    /**
    * Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
    *
    * @param arpOutboundPK the primary key for the new arp outbound
    * @return the new arp outbound
    */
    public com.stpl.app.parttwo.model.ArpOutbound create(
        ArpOutboundPK arpOutboundPK);

    /**
    * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound that was removed
    * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.ArpOutbound remove(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.ArpOutbound updateImpl(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the arp outbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchArpOutboundException} if it could not be found.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound
    * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.ArpOutbound findByPrimaryKey(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.ArpOutbound fetchByPrimaryKey(
        ArpOutboundPK arpOutboundPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the arp outbounds.
    *
    * @return the arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.parttwo.model.ArpOutbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the arp outbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of arp outbounds.
    *
    * @return the number of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
