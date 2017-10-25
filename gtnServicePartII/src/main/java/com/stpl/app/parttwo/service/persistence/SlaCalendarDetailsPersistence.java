package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the sla calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsPersistenceImpl
 * @see SlaCalendarDetailsUtil
 * @generated
 */
public interface SlaCalendarDetailsPersistence extends BasePersistence<SlaCalendarDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SlaCalendarDetailsUtil} to access the sla calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the sla calendar details in the entity cache if it is enabled.
    *
    * @param slaCalendarDetails the sla calendar details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails);

    /**
    * Caches the sla calendar detailses in the entity cache if it is enabled.
    *
    * @param slaCalendarDetailses the sla calendar detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> slaCalendarDetailses);

    /**
    * Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
    *
    * @param slaCalendarDetailsSid the primary key for the new sla calendar details
    * @return the new sla calendar details
    */
    public com.stpl.app.parttwo.model.SlaCalendarDetails create(
        int slaCalendarDetailsSid);

    /**
    * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details that was removed
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarDetails remove(
        int slaCalendarDetailsSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.SlaCalendarDetails updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sla calendar details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException} if it could not be found.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarDetails findByPrimaryKey(
        int slaCalendarDetailsSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.SlaCalendarDetails fetchByPrimaryKey(
        int slaCalendarDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sla calendar detailses.
    *
    * @return the sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sla calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar detailses
    * @param end the upper bound of the range of sla calendar detailses (not inclusive)
    * @return the range of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sla calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar detailses
    * @param end the upper bound of the range of sla calendar detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sla calendar detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sla calendar detailses.
    *
    * @return the number of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
