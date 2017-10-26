package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipBuilder;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipBuilderPersistenceImpl
 * @see RelationshipBuilderUtil
 * @generated
 */
public interface RelationshipBuilderPersistence extends BasePersistence<RelationshipBuilder> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RelationshipBuilderUtil} to access the relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the relationship builder in the entity cache if it is enabled.
    *
    * @param relationshipBuilder the relationship builder
    */
    public void cacheResult(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder);

    /**
    * Caches the relationship builders in the entity cache if it is enabled.
    *
    * @param relationshipBuilders the relationship builders
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RelationshipBuilder> relationshipBuilders);

    /**
    * Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
    *
    * @param relationshipBuilderSid the primary key for the new relationship builder
    * @return the new relationship builder
    */
    public com.stpl.app.model.RelationshipBuilder create(
        int relationshipBuilderSid);

    /**
    * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder that was removed
    * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipBuilder remove(
        int relationshipBuilderSid)
        throws com.stpl.app.NoSuchRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RelationshipBuilder updateImpl(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the relationship builder with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipBuilderException} if it could not be found.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder
    * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipBuilder findByPrimaryKey(
        int relationshipBuilderSid)
        throws com.stpl.app.NoSuchRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipBuilder fetchByPrimaryKey(
        int relationshipBuilderSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the relationship builders.
    *
    * @return the relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipBuilder> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship builders
    * @param end the upper bound of the range of relationship builders (not inclusive)
    * @return the range of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipBuilder> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship builders
    * @param end the upper bound of the range of relationship builders (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipBuilder> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the relationship builders from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of relationship builders.
    *
    * @return the number of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
