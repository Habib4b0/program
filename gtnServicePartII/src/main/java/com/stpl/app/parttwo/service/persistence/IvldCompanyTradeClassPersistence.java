package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassPersistenceImpl
 * @see IvldCompanyTradeClassUtil
 * @generated
 */
public interface IvldCompanyTradeClassPersistence extends BasePersistence<IvldCompanyTradeClass> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldCompanyTradeClassUtil} to access the ivld company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld company trade class in the entity cache if it is enabled.
    *
    * @param ivldCompanyTradeClass the ivld company trade class
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass);

    /**
    * Caches the ivld company trade classes in the entity cache if it is enabled.
    *
    * @param ivldCompanyTradeClasses the ivld company trade classes
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> ivldCompanyTradeClasses);

    /**
    * Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
    *
    * @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
    * @return the new ivld company trade class
    */
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass create(
        int ivldCompanyTradeClassSid);

    /**
    * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass remove(
        int ivldCompanyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException} if it could not be found.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass findByPrimaryKey(
        int ivldCompanyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass fetchByPrimaryKey(
        int ivldCompanyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld company trade classes.
    *
    * @return the ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company trade classes
    * @param end the upper bound of the range of ivld company trade classes (not inclusive)
    * @return the range of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company trade classes
    * @param end the upper bound of the range of ivld company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld company trade classes.
    *
    * @return the number of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
