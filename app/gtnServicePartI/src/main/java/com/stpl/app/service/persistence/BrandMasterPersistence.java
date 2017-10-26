package com.stpl.app.service.persistence;

import com.stpl.app.model.BrandMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the brand master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BrandMasterPersistenceImpl
 * @see BrandMasterUtil
 * @generated
 */
public interface BrandMasterPersistence extends BasePersistence<BrandMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BrandMasterUtil} to access the brand master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the brand master in the entity cache if it is enabled.
    *
    * @param brandMaster the brand master
    */
    public void cacheResult(com.stpl.app.model.BrandMaster brandMaster);

    /**
    * Caches the brand masters in the entity cache if it is enabled.
    *
    * @param brandMasters the brand masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.BrandMaster> brandMasters);

    /**
    * Creates a new brand master with the primary key. Does not add the brand master to the database.
    *
    * @param brandMasterSid the primary key for the new brand master
    * @return the new brand master
    */
    public com.stpl.app.model.BrandMaster create(int brandMasterSid);

    /**
    * Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master that was removed
    * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BrandMaster remove(int brandMasterSid)
        throws com.stpl.app.NoSuchBrandMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.BrandMaster updateImpl(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the brand master with the primary key or throws a {@link com.stpl.app.NoSuchBrandMasterException} if it could not be found.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master
    * @throws com.stpl.app.NoSuchBrandMasterException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BrandMaster findByPrimaryKey(int brandMasterSid)
        throws com.stpl.app.NoSuchBrandMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BrandMaster fetchByPrimaryKey(int brandMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the brand masters.
    *
    * @return the brand masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BrandMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the brand masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of brand masters
    * @param end the upper bound of the range of brand masters (not inclusive)
    * @return the range of brand masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BrandMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the brand masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of brand masters
    * @param end the upper bound of the range of brand masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of brand masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BrandMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the brand masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of brand masters.
    *
    * @return the number of brand masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
