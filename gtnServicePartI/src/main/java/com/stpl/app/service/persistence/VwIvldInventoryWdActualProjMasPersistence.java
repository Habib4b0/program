package com.stpl.app.service.persistence;

import com.stpl.app.model.VwIvldInventoryWdActualProjMas;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw ivld inventory wd actual proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldInventoryWdActualProjMasPersistenceImpl
 * @see VwIvldInventoryWdActualProjMasUtil
 * @generated
 */
public interface VwIvldInventoryWdActualProjMasPersistence
    extends BasePersistence<VwIvldInventoryWdActualProjMas> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwIvldInventoryWdActualProjMasUtil} to access the vw ivld inventory wd actual proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw ivld inventory wd actual proj mas in the entity cache if it is enabled.
    *
    * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
    */
    public void cacheResult(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas);

    /**
    * Caches the vw ivld inventory wd actual proj mases in the entity cache if it is enabled.
    *
    * @param vwIvldInventoryWdActualProjMases the vw ivld inventory wd actual proj mases
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases);

    /**
    * Creates a new vw ivld inventory wd actual proj mas with the primary key. Does not add the vw ivld inventory wd actual proj mas to the database.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key for the new vw ivld inventory wd actual proj mas
    * @return the new vw ivld inventory wd actual proj mas
    */
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas create(
        int ivldInventoryWdActualProjMasSid);

    /**
    * Removes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas that was removed
    * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas remove(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.VwIvldInventoryWdActualProjMas updateImpl(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld inventory wd actual proj mas with the primary key or throws a {@link com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException} if it could not be found.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas
    * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas findByPrimaryKey(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas, or <code>null</code> if a vw ivld inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas fetchByPrimaryKey(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw ivld inventory wd actual proj mases.
    *
    * @return the vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw ivld inventory wd actual proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
    * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
    * @return the range of vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw ivld inventory wd actual proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
    * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw ivld inventory wd actual proj mases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw ivld inventory wd actual proj mases.
    *
    * @return the number of vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
