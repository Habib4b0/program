package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyGroup;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupPersistenceImpl
 * @see CompanyGroupUtil
 * @generated
 */
public interface CompanyGroupPersistence extends BasePersistence<CompanyGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyGroupUtil} to access the company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the company group in the entity cache if it is enabled.
    *
    * @param companyGroup the company group
    */
    public void cacheResult(com.stpl.app.model.CompanyGroup companyGroup);

    /**
    * Caches the company groups in the entity cache if it is enabled.
    *
    * @param companyGroups the company groups
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CompanyGroup> companyGroups);

    /**
    * Creates a new company group with the primary key. Does not add the company group to the database.
    *
    * @param companyGroupSid the primary key for the new company group
    * @return the new company group
    */
    public com.stpl.app.model.CompanyGroup create(int companyGroupSid);

    /**
    * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group that was removed
    * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroup remove(int companyGroupSid)
        throws com.stpl.app.NoSuchCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CompanyGroup updateImpl(
        com.stpl.app.model.CompanyGroup companyGroup)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company group with the primary key or throws a {@link com.stpl.app.NoSuchCompanyGroupException} if it could not be found.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group
    * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroup findByPrimaryKey(int companyGroupSid)
        throws com.stpl.app.NoSuchCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group, or <code>null</code> if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyGroup fetchByPrimaryKey(
        int companyGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company groups.
    *
    * @return the company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company groups
    * @param end the upper bound of the range of company groups (not inclusive)
    * @return the range of company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroup> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company groups
    * @param end the upper bound of the range of company groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyGroup> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company groups.
    *
    * @return the number of company groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
