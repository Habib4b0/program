package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the projection cust details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustDetailsPersistenceImpl
 * @see ProjectionCustDetailsUtil
 * @generated
 */
public interface ProjectionCustDetailsPersistence extends BasePersistence<ProjectionCustDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionCustDetailsUtil} to access the projection cust details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the projection cust details in the entity cache if it is enabled.
    *
    * @param projectionCustDetails the projection cust details
    */
    public void cacheResult(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails);

    /**
    * Caches the projection cust detailses in the entity cache if it is enabled.
    *
    * @param projectionCustDetailses the projection cust detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionCustDetails> projectionCustDetailses);

    /**
    * Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
    *
    * @param customerDetailsId the primary key for the new projection cust details
    * @return the new projection cust details
    */
    public com.stpl.app.model.ProjectionCustDetails create(
        int customerDetailsId);

    /**
    * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details that was removed
    * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustDetails remove(
        int customerDetailsId)
        throws com.stpl.app.NoSuchProjectionCustDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ProjectionCustDetails updateImpl(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection cust details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionCustDetailsException} if it could not be found.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details
    * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustDetails findByPrimaryKey(
        int customerDetailsId)
        throws com.stpl.app.NoSuchProjectionCustDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustDetails fetchByPrimaryKey(
        int customerDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the projection cust detailses.
    *
    * @return the projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the projection cust detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust detailses
    * @param end the upper bound of the range of projection cust detailses (not inclusive)
    * @return the range of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the projection cust detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust detailses
    * @param end the upper bound of the range of projection cust detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionCustDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the projection cust detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection cust detailses.
    *
    * @return the number of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
