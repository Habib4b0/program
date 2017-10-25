package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyParentDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyParentDetailsPersistenceImpl
 * @see VwCompanyParentDetailsUtil
 * @generated
 */
public interface VwCompanyParentDetailsPersistence extends BasePersistence<VwCompanyParentDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwCompanyParentDetailsUtil} to access the vw company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw company parent details in the entity cache if it is enabled.
    *
    * @param vwCompanyParentDetails the vw company parent details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails);

    /**
    * Caches the vw company parent detailses in the entity cache if it is enabled.
    *
    * @param vwCompanyParentDetailses the vw company parent detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> vwCompanyParentDetailses);

    /**
    * Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
    *
    * @param companyParentDetailsSid the primary key for the new vw company parent details
    * @return the new vw company parent details
    */
    public com.stpl.app.parttwo.model.VwCompanyParentDetails create(
        int companyParentDetailsSid);

    /**
    * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyParentDetails remove(
        int companyParentDetailsSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwCompanyParentDetails updateImpl(
        com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company parent details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException} if it could not be found.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyParentDetails findByPrimaryKey(
        int companyParentDetailsSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyParentDetailsSid the primary key of the vw company parent details
    * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyParentDetails fetchByPrimaryKey(
        int companyParentDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw company parent detailses.
    *
    * @return the vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw company parent detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company parent detailses
    * @param end the upper bound of the range of vw company parent detailses (not inclusive)
    * @return the range of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw company parent detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company parent detailses
    * @param end the upper bound of the range of vw company parent detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw company parent detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw company parent detailses.
    *
    * @return the number of vw company parent detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
