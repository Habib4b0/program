package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionProdDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the projection prod details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdDetailsPersistenceImpl
 * @see ProjectionProdDetailsUtil
 * @generated
 */
public interface ProjectionProdDetailsPersistence extends BasePersistence<ProjectionProdDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionProdDetailsUtil} to access the projection prod details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the projection prod details in the entity cache if it is enabled.
    *
    * @param projectionProdDetails the projection prod details
    */
    public void cacheResult(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails);

    /**
    * Caches the projection prod detailses in the entity cache if it is enabled.
    *
    * @param projectionProdDetailses the projection prod detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionProdDetails> projectionProdDetailses);

    /**
    * Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
    *
    * @param productDetailsId the primary key for the new projection prod details
    * @return the new projection prod details
    */
    public com.stpl.app.model.ProjectionProdDetails create(int productDetailsId);

    /**
    * Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param productDetailsId the primary key of the projection prod details
    * @return the projection prod details that was removed
    * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionProdDetails remove(int productDetailsId)
        throws com.stpl.app.NoSuchProjectionProdDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ProjectionProdDetails updateImpl(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection prod details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionProdDetailsException} if it could not be found.
    *
    * @param productDetailsId the primary key of the projection prod details
    * @return the projection prod details
    * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionProdDetails findByPrimaryKey(
        int productDetailsId)
        throws com.stpl.app.NoSuchProjectionProdDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param productDetailsId the primary key of the projection prod details
    * @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionProdDetails fetchByPrimaryKey(
        int productDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the projection prod detailses.
    *
    * @return the projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionProdDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the projection prod detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection prod detailses
    * @param end the upper bound of the range of projection prod detailses (not inclusive)
    * @return the range of projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionProdDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the projection prod detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection prod detailses
    * @param end the upper bound of the range of projection prod detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionProdDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the projection prod detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection prod detailses.
    *
    * @return the number of projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
