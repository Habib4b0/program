package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyGroupDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupDetailsPersistenceImpl
 * @see CompanyGroupDetailsUtil
 * @generated
 */
public interface CompanyGroupDetailsPersistence extends BasePersistence<CompanyGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyGroupDetailsUtil} to access the company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the company group details in the entity cache if it is enabled.
    *
    * @param companyGroupDetails the company group details
    */
    public void cacheResult(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails);

    /**
    * Caches the company group detailses in the entity cache if it is enabled.
    *
    * @param companyGroupDetailses the company group detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CompanyGroupDetails> companyGroupDetailses);

    /**
    * Creates a new company group details with the primary key. Does not add the company group details to the database.
    *
    * @param companyGroupDetailsSid the primary key for the new company group details
    * @return the new company group details
    */
    public com.stpl.app.model.CompanyGroupDetails create(
        int companyGroupDetailsSid);

    /**
    * Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupDetailsSid the primary key of the company group details
    * @return the company group details that was removed
    * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroupDetails remove(
        int companyGroupDetailsSid)
        throws com.stpl.app.NoSuchCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CompanyGroupDetails updateImpl(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company group details with the primary key or throws a {@link com.stpl.app.NoSuchCompanyGroupDetailsException} if it could not be found.
    *
    * @param companyGroupDetailsSid the primary key of the company group details
    * @return the company group details
    * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroupDetails findByPrimaryKey(
        int companyGroupDetailsSid)
        throws com.stpl.app.NoSuchCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyGroupDetailsSid the primary key of the company group details
    * @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroupDetails fetchByPrimaryKey(
        int companyGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company group detailses.
    *
    * @return the company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company group detailses
    * @param end the upper bound of the range of company group detailses (not inclusive)
    * @return the range of company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company group detailses
    * @param end the upper bound of the range of company group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company group detailses.
    *
    * @return the number of company group detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
