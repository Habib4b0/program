package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyMasterPersistenceImpl
 * @see VwCompanyMasterUtil
 * @generated
 */
public interface VwCompanyMasterPersistence extends BasePersistence<VwCompanyMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwCompanyMasterUtil} to access the vw company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw company master in the entity cache if it is enabled.
    *
    * @param vwCompanyMaster the vw company master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster);

    /**
    * Caches the vw company masters in the entity cache if it is enabled.
    *
    * @param vwCompanyMasters the vw company masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> vwCompanyMasters);

    /**
    * Creates a new vw company master with the primary key. Does not add the vw company master to the database.
    *
    * @param companyMasterSid the primary key for the new vw company master
    * @return the new vw company master
    */
    public com.stpl.app.parttwo.model.VwCompanyMaster create(
        int companyMasterSid);

    /**
    * Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the vw company master
    * @return the vw company master that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyMaster remove(
        int companyMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwCompanyMaster updateImpl(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyMasterException} if it could not be found.
    *
    * @param companyMasterSid the primary key of the vw company master
    * @return the vw company master
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyMaster findByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyMasterSid the primary key of the vw company master
    * @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyMaster fetchByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw company masters.
    *
    * @return the vw company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company masters
    * @param end the upper bound of the range of vw company masters (not inclusive)
    * @return the range of vw company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company masters
    * @param end the upper bound of the range of vw company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw company masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw company masters.
    *
    * @return the number of vw company masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
