package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldAccrualInbound;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld accrual inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAccrualInboundPersistenceImpl
 * @see IvldAccrualInboundUtil
 * @generated
 */
public interface IvldAccrualInboundPersistence extends BasePersistence<IvldAccrualInbound> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldAccrualInboundUtil} to access the ivld accrual inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld accrual inbound in the entity cache if it is enabled.
    *
    * @param ivldAccrualInbound the ivld accrual inbound
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound);

    /**
    * Caches the ivld accrual inbounds in the entity cache if it is enabled.
    *
    * @param ivldAccrualInbounds the ivld accrual inbounds
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> ivldAccrualInbounds);

    /**
    * Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
    *
    * @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
    * @return the new ivld accrual inbound
    */
    public com.stpl.app.parttwo.model.IvldAccrualInbound create(
        int ivldAccrualInboundSid);

    /**
    * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldAccrualInbound remove(
        int ivldAccrualInboundSid)
        throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldAccrualInbound updateImpl(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld accrual inbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldAccrualInboundException} if it could not be found.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound
    * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldAccrualInbound findByPrimaryKey(
        int ivldAccrualInboundSid)
        throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldAccrualInbound fetchByPrimaryKey(
        int ivldAccrualInboundSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld accrual inbounds.
    *
    * @return the ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld accrual inbounds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld accrual inbounds.
    *
    * @return the number of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
