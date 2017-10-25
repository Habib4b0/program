package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the sla calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarMasterPersistenceImpl
 * @see SlaCalendarMasterUtil
 * @generated
 */
public interface SlaCalendarMasterPersistence extends BasePersistence<SlaCalendarMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SlaCalendarMasterUtil} to access the sla calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the sla calendar master in the entity cache if it is enabled.
    *
    * @param slaCalendarMaster the sla calendar master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster);

    /**
    * Caches the sla calendar masters in the entity cache if it is enabled.
    *
    * @param slaCalendarMasters the sla calendar masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> slaCalendarMasters);

    /**
    * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
    *
    * @param slaCalendarMasterSid the primary key for the new sla calendar master
    * @return the new sla calendar master
    */
    public com.stpl.app.parttwo.model.SlaCalendarMaster create(
        int slaCalendarMasterSid);

    /**
    * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master that was removed
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarMaster remove(
        int slaCalendarMasterSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.SlaCalendarMaster updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sla calendar master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarMasterException} if it could not be found.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarMaster findByPrimaryKey(
        int slaCalendarMasterSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarMaster fetchByPrimaryKey(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sla calendar masters.
    *
    * @return the sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @return the range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sla calendar masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sla calendar masters.
    *
    * @return the number of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
