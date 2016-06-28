package com.stpl.app.service.persistence;

import com.stpl.app.model.MasterDataFiles;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the master data files service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataFilesPersistenceImpl
 * @see MasterDataFilesUtil
 * @generated
 */
public interface MasterDataFilesPersistence extends BasePersistence<MasterDataFiles> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MasterDataFilesUtil} to access the master data files persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the master data files in the entity cache if it is enabled.
    *
    * @param masterDataFiles the master data files
    */
    public void cacheResult(com.stpl.app.model.MasterDataFiles masterDataFiles);

    /**
    * Caches the master data fileses in the entity cache if it is enabled.
    *
    * @param masterDataFileses the master data fileses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MasterDataFiles> masterDataFileses);

    /**
    * Creates a new master data files with the primary key. Does not add the master data files to the database.
    *
    * @param masterDataFilesSid the primary key for the new master data files
    * @return the new master data files
    */
    public com.stpl.app.model.MasterDataFiles create(int masterDataFilesSid);

    /**
    * Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataFilesSid the primary key of the master data files
    * @return the master data files that was removed
    * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MasterDataFiles remove(int masterDataFilesSid)
        throws com.stpl.app.NoSuchMasterDataFilesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MasterDataFiles updateImpl(
        com.stpl.app.model.MasterDataFiles masterDataFiles)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the master data files with the primary key or throws a {@link com.stpl.app.NoSuchMasterDataFilesException} if it could not be found.
    *
    * @param masterDataFilesSid the primary key of the master data files
    * @return the master data files
    * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MasterDataFiles findByPrimaryKey(
        int masterDataFilesSid)
        throws com.stpl.app.NoSuchMasterDataFilesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param masterDataFilesSid the primary key of the master data files
    * @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MasterDataFiles fetchByPrimaryKey(
        int masterDataFilesSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the master data fileses.
    *
    * @return the master data fileses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MasterDataFiles> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the master data fileses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data fileses
    * @param end the upper bound of the range of master data fileses (not inclusive)
    * @return the range of master data fileses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MasterDataFiles> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the master data fileses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data fileses
    * @param end the upper bound of the range of master data fileses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of master data fileses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MasterDataFiles> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the master data fileses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of master data fileses.
    *
    * @return the number of master data fileses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
