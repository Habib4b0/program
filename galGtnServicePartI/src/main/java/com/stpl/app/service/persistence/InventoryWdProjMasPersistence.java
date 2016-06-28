package com.stpl.app.service.persistence;

import com.stpl.app.model.InventoryWdProjMas;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdProjMasPersistenceImpl
 * @see InventoryWdProjMasUtil
 * @generated
 */
public interface InventoryWdProjMasPersistence extends BasePersistence<InventoryWdProjMas> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link InventoryWdProjMasUtil} to access the inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the inventory wd proj mas in the entity cache if it is enabled.
    *
    * @param inventoryWdProjMas the inventory wd proj mas
    */
    public void cacheResult(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas);

    /**
    * Caches the inventory wd proj mases in the entity cache if it is enabled.
    *
    * @param inventoryWdProjMases the inventory wd proj mases
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.InventoryWdProjMas> inventoryWdProjMases);

    /**
    * Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
    *
    * @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
    * @return the new inventory wd proj mas
    */
    public com.stpl.app.model.InventoryWdProjMas create(
        int inventoryWdProjMasSid);

    /**
    * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas that was removed
    * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.InventoryWdProjMas remove(
        int inventoryWdProjMasSid)
        throws com.stpl.app.NoSuchInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.InventoryWdProjMas updateImpl(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the inventory wd proj mas with the primary key or throws a {@link com.stpl.app.NoSuchInventoryWdProjMasException} if it could not be found.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas
    * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.InventoryWdProjMas findByPrimaryKey(
        int inventoryWdProjMasSid)
        throws com.stpl.app.NoSuchInventoryWdProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.InventoryWdProjMas fetchByPrimaryKey(
        int inventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the inventory wd proj mases.
    *
    * @return the inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd proj mases
    * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
    * @return the range of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd proj mases
    * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.InventoryWdProjMas> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the inventory wd proj mases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of inventory wd proj mases.
    *
    * @return the number of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
