package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyTradeClass;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyTradeClassPersistenceImpl
 * @see CompanyTradeClassUtil
 * @generated
 */
public interface CompanyTradeClassPersistence extends BasePersistence<CompanyTradeClass> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyTradeClassUtil} to access the company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the company trade class in the entity cache if it is enabled.
    *
    * @param companyTradeClass the company trade class
    */
    public void cacheResult(
        com.stpl.app.model.CompanyTradeClass companyTradeClass);

    /**
    * Caches the company trade classes in the entity cache if it is enabled.
    *
    * @param companyTradeClasses the company trade classes
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CompanyTradeClass> companyTradeClasses);

    /**
    * Creates a new company trade class with the primary key. Does not add the company trade class to the database.
    *
    * @param companyTradeClassSid the primary key for the new company trade class
    * @return the new company trade class
    */
    public com.stpl.app.model.CompanyTradeClass create(int companyTradeClassSid);

    /**
    * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class that was removed
    * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyTradeClass remove(int companyTradeClassSid)
        throws com.stpl.app.NoSuchCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CompanyTradeClass updateImpl(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company trade class with the primary key or throws a {@link com.stpl.app.NoSuchCompanyTradeClassException} if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class
    * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyTradeClass findByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.app.NoSuchCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the company trade class
    * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyTradeClass fetchByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company trade classes.
    *
    * @return the company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company trade classes
    * @param end the upper bound of the range of company trade classes (not inclusive)
    * @return the range of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company trade classes
    * @param end the upper bound of the range of company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company trade classes.
    *
    * @return the number of company trade classes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
