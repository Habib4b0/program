package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ac br methodology details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsPersistenceImpl
 * @see AcBrMethodologyDetailsUtil
 * @generated
 */
public interface AcBrMethodologyDetailsPersistence extends BasePersistence<AcBrMethodologyDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AcBrMethodologyDetailsUtil} to access the ac br methodology details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ac br methodology details in the entity cache if it is enabled.
    *
    * @param acBrMethodologyDetails the ac br methodology details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails);

    /**
    * Caches the ac br methodology detailses in the entity cache if it is enabled.
    *
    * @param acBrMethodologyDetailses the ac br methodology detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> acBrMethodologyDetailses);

    /**
    * Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
    *
    * @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
    * @return the new ac br methodology details
    */
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails create(
        int acBrMethodologyDetailsSid);

    /**
    * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails remove(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AcBrMethodologyDetails updateImpl(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac br methodology details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException} if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details
    * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ac br methodology detailses.
    *
    * @return the ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ac br methodology detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac br methodology detailses
    * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
    * @return the range of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ac br methodology detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac br methodology detailses
    * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ac br methodology detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ac br methodology detailses.
    *
    * @return the number of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
