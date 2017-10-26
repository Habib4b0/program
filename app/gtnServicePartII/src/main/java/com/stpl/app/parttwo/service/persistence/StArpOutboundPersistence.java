package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StArpOutbound;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StArpOutboundPersistenceImpl
 * @see StArpOutboundUtil
 * @generated
 */
public interface StArpOutboundPersistence extends BasePersistence<StArpOutbound> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StArpOutboundUtil} to access the st arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st arp outbound in the entity cache if it is enabled.
    *
    * @param stArpOutbound the st arp outbound
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound);

    /**
    * Caches the st arp outbounds in the entity cache if it is enabled.
    *
    * @param stArpOutbounds the st arp outbounds
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StArpOutbound> stArpOutbounds);

    /**
    * Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
    *
    * @param stArpOutboundPK the primary key for the new st arp outbound
    * @return the new st arp outbound
    */
    public com.stpl.app.parttwo.model.StArpOutbound create(
        StArpOutboundPK stArpOutboundPK);

    /**
    * Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stArpOutboundPK the primary key of the st arp outbound
    * @return the st arp outbound that was removed
    * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StArpOutbound remove(
        StArpOutboundPK stArpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchStArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.StArpOutbound updateImpl(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st arp outbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStArpOutboundException} if it could not be found.
    *
    * @param stArpOutboundPK the primary key of the st arp outbound
    * @return the st arp outbound
    * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StArpOutbound findByPrimaryKey(
        StArpOutboundPK stArpOutboundPK)
        throws com.stpl.app.parttwo.NoSuchStArpOutboundException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stArpOutboundPK the primary key of the st arp outbound
    * @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StArpOutbound fetchByPrimaryKey(
        StArpOutboundPK stArpOutboundPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st arp outbounds.
    *
    * @return the st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StArpOutbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st arp outbounds
    * @param end the upper bound of the range of st arp outbounds (not inclusive)
    * @return the range of st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StArpOutbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st arp outbounds
    * @param end the upper bound of the range of st arp outbounds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StArpOutbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st arp outbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st arp outbounds.
    *
    * @return the number of st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
