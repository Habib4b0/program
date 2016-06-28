package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionMasterPersistenceImpl
 * @see ProjectionMasterUtil
 * @generated
 */
public interface ProjectionMasterPersistence extends BasePersistence<ProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionMasterUtil} to access the projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the projection master in the entity cache if it is enabled.
    *
    * @param projectionMaster the projection master
    */
    public void cacheResult(
        com.stpl.app.model.ProjectionMaster projectionMaster);

    /**
    * Caches the projection masters in the entity cache if it is enabled.
    *
    * @param projectionMasters the projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionMaster> projectionMasters);

    /**
    * Creates a new projection master with the primary key. Does not add the projection master to the database.
    *
    * @param projectionMasterSid the primary key for the new projection master
    * @return the new projection master
    */
    public com.stpl.app.model.ProjectionMaster create(int projectionMasterSid);

    /**
    * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master that was removed
    * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionMaster remove(int projectionMasterSid)
        throws com.stpl.app.NoSuchProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ProjectionMaster updateImpl(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection master with the primary key or throws a {@link com.stpl.app.NoSuchProjectionMasterException} if it could not be found.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master
    * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionMaster findByPrimaryKey(
        int projectionMasterSid)
        throws com.stpl.app.NoSuchProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionMaster fetchByPrimaryKey(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the projection masters.
    *
    * @return the projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @return the range of projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection masters.
    *
    * @return the number of projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
