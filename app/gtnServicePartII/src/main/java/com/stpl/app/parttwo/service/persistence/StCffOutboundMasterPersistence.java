package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StCffOutboundMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st cff outbound master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StCffOutboundMasterPersistenceImpl
 * @see StCffOutboundMasterUtil
 * @generated
 */
public interface StCffOutboundMasterPersistence extends BasePersistence<StCffOutboundMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StCffOutboundMasterUtil} to access the st cff outbound master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st cff outbound master in the entity cache if it is enabled.
    *
    * @param stCffOutboundMaster the st cff outbound master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster);

    /**
    * Caches the st cff outbound masters in the entity cache if it is enabled.
    *
    * @param stCffOutboundMasters the st cff outbound masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> stCffOutboundMasters);

    /**
    * Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
    *
    * @param stCffOutboundMasterPK the primary key for the new st cff outbound master
    * @return the new st cff outbound master
    */
    public com.stpl.app.parttwo.model.StCffOutboundMaster create(
        StCffOutboundMasterPK stCffOutboundMasterPK);

    /**
    * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master that was removed
    * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StCffOutboundMaster remove(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.StCffOutboundMaster updateImpl(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st cff outbound master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStCffOutboundMasterException} if it could not be found.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master
    * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StCffOutboundMaster findByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stCffOutboundMasterPK the primary key of the st cff outbound master
    * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StCffOutboundMaster fetchByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st cff outbound masters.
    *
    * @return the st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st cff outbound masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st cff outbound masters
    * @param end the upper bound of the range of st cff outbound masters (not inclusive)
    * @return the range of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st cff outbound masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st cff outbound masters
    * @param end the upper bound of the range of st cff outbound masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st cff outbound masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st cff outbound masters.
    *
    * @return the number of st cff outbound masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
