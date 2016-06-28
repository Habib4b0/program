package com.stpl.app.service.persistence;

import com.stpl.app.model.HistRelationshipBuilder;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipBuilderPersistenceImpl
 * @see HistRelationshipBuilderUtil
 * @generated
 */
public interface HistRelationshipBuilderPersistence extends BasePersistence<HistRelationshipBuilder> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistRelationshipBuilderUtil} to access the hist relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist relationship builder in the entity cache if it is enabled.
    *
    * @param histRelationshipBuilder the hist relationship builder
    */
    public void cacheResult(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder);

    /**
    * Caches the hist relationship builders in the entity cache if it is enabled.
    *
    * @param histRelationshipBuilders the hist relationship builders
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistRelationshipBuilder> histRelationshipBuilders);

    /**
    * Creates a new hist relationship builder with the primary key. Does not add the hist relationship builder to the database.
    *
    * @param histRelationshipBuilderPK the primary key for the new hist relationship builder
    * @return the new hist relationship builder
    */
    public com.stpl.app.model.HistRelationshipBuilder create(
        HistRelationshipBuilderPK histRelationshipBuilderPK);

    /**
    * Removes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipBuilderPK the primary key of the hist relationship builder
    * @return the hist relationship builder that was removed
    * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipBuilder remove(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.app.NoSuchHistRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistRelationshipBuilder updateImpl(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist relationship builder with the primary key or throws a {@link com.stpl.app.NoSuchHistRelationshipBuilderException} if it could not be found.
    *
    * @param histRelationshipBuilderPK the primary key of the hist relationship builder
    * @return the hist relationship builder
    * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipBuilder findByPrimaryKey(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.app.NoSuchHistRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist relationship builder with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histRelationshipBuilderPK the primary key of the hist relationship builder
    * @return the hist relationship builder, or <code>null</code> if a hist relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipBuilder fetchByPrimaryKey(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist relationship builders.
    *
    * @return the hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipBuilder> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship builders
    * @param end the upper bound of the range of hist relationship builders (not inclusive)
    * @return the range of hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipBuilder> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship builders
    * @param end the upper bound of the range of hist relationship builders (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipBuilder> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist relationship builders from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist relationship builders.
    *
    * @return the number of hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
