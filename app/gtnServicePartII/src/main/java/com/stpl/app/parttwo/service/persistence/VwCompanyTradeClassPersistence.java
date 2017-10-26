package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyTradeClass;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassPersistenceImpl
 * @see VwCompanyTradeClassUtil
 * @generated
 */
public interface VwCompanyTradeClassPersistence extends BasePersistence<VwCompanyTradeClass> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwCompanyTradeClassUtil} to access the vw company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw company trade class in the entity cache if it is enabled.
    *
    * @param vwCompanyTradeClass the vw company trade class
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass);

    /**
    * Caches the vw company trade classes in the entity cache if it is enabled.
    *
    * @param vwCompanyTradeClasses the vw company trade classes
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> vwCompanyTradeClasses);

    /**
    * Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
    *
    * @param companyTradeClassSid the primary key for the new vw company trade class
    * @return the new vw company trade class
    */
    public com.stpl.app.parttwo.model.VwCompanyTradeClass create(
        int companyTradeClassSid);

    /**
    * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyTradeClass remove(
        int companyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException} if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyTradeClass findByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyTradeClassSid the primary key of the vw company trade class
    * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyTradeClass fetchByPrimaryKey(
        int companyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw company trade classes.
    *
    * @return the vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company trade classes
    * @param end the upper bound of the range of vw company trade classes (not inclusive)
    * @return the range of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company trade classes
    * @param end the upper bound of the range of vw company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw company trade classes.
    *
    * @return the number of vw company trade classes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
