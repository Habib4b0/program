package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldInventoryWdProjMas;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMasPersistenceImpl
 * @see IvldInventoryWdProjMasUtil
 * @generated
 */
public interface IvldInventoryWdProjMasPersistence extends BasePersistence<IvldInventoryWdProjMas> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldInventoryWdProjMasUtil} to access the ivld inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld inventory wd proj mas in the entity cache if it is enabled.
    *
    * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
    */
    public void cacheResult(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas);

    /**
    * Caches the ivld inventory wd proj mases in the entity cache if it is enabled.
    *
    * @param ivldInventoryWdProjMases the ivld inventory wd proj mases
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> ivldInventoryWdProjMases);

    /**
    * Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
    *
    * @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
    * @return the new ivld inventory wd proj mas
    */
    public com.stpl.app.model.IvldInventoryWdProjMas create(
        int ivldInventoryWdProjMasSid);

    /**
    * Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas that was removed
    * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldInventoryWdProjMas remove(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.app.NoSuchIvldInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldInventoryWdProjMas updateImpl(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld inventory wd proj mas with the primary key or throws a {@link com.stpl.app.NoSuchIvldInventoryWdProjMasException} if it could not be found.
    *
    * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas
    * @throws com.stpl.app.NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldInventoryWdProjMas findByPrimaryKey(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.app.NoSuchIvldInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldInventoryWdProjMas fetchByPrimaryKey(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld inventory wd proj mases.
    *
    * @return the ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld inventory wd proj mases
    * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
    * @return the range of ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld inventory wd proj mases
    * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld inventory wd proj mases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld inventory wd proj mases.
    *
    * @return the number of ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
