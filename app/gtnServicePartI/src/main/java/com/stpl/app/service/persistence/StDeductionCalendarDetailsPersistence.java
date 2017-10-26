package com.stpl.app.service.persistence;

import com.stpl.app.model.StDeductionCalendarDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetailsPersistenceImpl
 * @see StDeductionCalendarDetailsUtil
 * @generated
 */
public interface StDeductionCalendarDetailsPersistence extends BasePersistence<StDeductionCalendarDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StDeductionCalendarDetailsUtil} to access the st deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st deduction calendar details in the entity cache if it is enabled.
    *
    * @param stDeductionCalendarDetails the st deduction calendar details
    */
    public void cacheResult(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails);

    /**
    * Caches the st deduction calendar detailses in the entity cache if it is enabled.
    *
    * @param stDeductionCalendarDetailses the st deduction calendar detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StDeductionCalendarDetails> stDeductionCalendarDetailses);

    /**
    * Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
    *
    * @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
    * @return the new st deduction calendar details
    */
    public com.stpl.app.model.StDeductionCalendarDetails create(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK);

    /**
    * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details that was removed
    * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StDeductionCalendarDetails remove(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchStDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StDeductionCalendarDetails updateImpl(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st deduction calendar details with the primary key or throws a {@link com.stpl.app.NoSuchStDeductionCalendarDetailsException} if it could not be found.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details
    * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StDeductionCalendarDetails findByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchStDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StDeductionCalendarDetails fetchByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st deduction calendar detailses.
    *
    * @return the st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st deduction calendar detailses
    * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
    * @return the range of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st deduction calendar detailses
    * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st deduction calendar detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st deduction calendar detailses.
    *
    * @return the number of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
