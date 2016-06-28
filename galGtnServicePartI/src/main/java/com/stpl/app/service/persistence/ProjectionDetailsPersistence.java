package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the projection details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionDetailsPersistenceImpl
 * @see ProjectionDetailsUtil
 * @generated
 */
public interface ProjectionDetailsPersistence extends BasePersistence<ProjectionDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionDetailsUtil} to access the projection details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the projection details in the entity cache if it is enabled.
    *
    * @param projectionDetails the projection details
    */
    public void cacheResult(
        com.stpl.app.model.ProjectionDetails projectionDetails);

    /**
    * Caches the projection detailses in the entity cache if it is enabled.
    *
    * @param projectionDetailses the projection detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionDetails> projectionDetailses);

    /**
    * Creates a new projection details with the primary key. Does not add the projection details to the database.
    *
    * @param projectionDetailsSid the primary key for the new projection details
    * @return the new projection details
    */
    public com.stpl.app.model.ProjectionDetails create(int projectionDetailsSid);

    /**
    * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details that was removed
    * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionDetails remove(int projectionDetailsSid)
        throws com.stpl.app.NoSuchProjectionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ProjectionDetails updateImpl(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionDetailsException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details
    * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionDetails findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchProjectionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionDetails fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the projection detailses.
    *
    * @return the projection detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the projection detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection detailses
    * @param end the upper bound of the range of projection detailses (not inclusive)
    * @return the range of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the projection detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection detailses
    * @param end the upper bound of the range of projection detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the projection detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection detailses.
    *
    * @return the number of projection detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
