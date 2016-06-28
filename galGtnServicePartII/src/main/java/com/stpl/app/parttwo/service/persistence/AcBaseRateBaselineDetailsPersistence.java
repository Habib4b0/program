package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ac base rate baseline details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetailsPersistenceImpl
 * @see AcBaseRateBaselineDetailsUtil
 * @generated
 */
public interface AcBaseRateBaselineDetailsPersistence extends BasePersistence<AcBaseRateBaselineDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AcBaseRateBaselineDetailsUtil} to access the ac base rate baseline details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ac base rate baseline details in the entity cache if it is enabled.
    *
    * @param acBaseRateBaselineDetails the ac base rate baseline details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AcBaseRateBaselineDetails acBaseRateBaselineDetails);

    /**
    * Caches the ac base rate baseline detailses in the entity cache if it is enabled.
    *
    * @param acBaseRateBaselineDetailses the ac base rate baseline detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> acBaseRateBaselineDetailses);

    /**
    * Creates a new ac base rate baseline details with the primary key. Does not add the ac base rate baseline details to the database.
    *
    * @param acBrMethodologyDetailsSid the primary key for the new ac base rate baseline details
    * @return the new ac base rate baseline details
    */
    public com.stpl.app.parttwo.model.AcBaseRateBaselineDetails create(
        int acBrMethodologyDetailsSid);

    /**
    * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBaseRateBaselineDetails remove(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AcBaseRateBaselineDetails updateImpl(
        com.stpl.app.parttwo.model.AcBaseRateBaselineDetails acBaseRateBaselineDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac base rate baseline details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException} if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details
    * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBaseRateBaselineDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBaseRateBaselineDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ac base rate baseline detailses.
    *
    * @return the ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ac base rate baseline detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac base rate baseline detailses
    * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
    * @return the range of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ac base rate baseline detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac base rate baseline detailses
    * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ac base rate baseline detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ac base rate baseline detailses.
    *
    * @return the number of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
