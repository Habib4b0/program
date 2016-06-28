package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipLevelDefinition;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the relationship level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinitionPersistenceImpl
 * @see RelationshipLevelDefinitionUtil
 * @generated
 */
public interface RelationshipLevelDefinitionPersistence extends BasePersistence<RelationshipLevelDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RelationshipLevelDefinitionUtil} to access the relationship level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the relationship level definition in the entity cache if it is enabled.
    *
    * @param relationshipLevelDefinition the relationship level definition
    */
    public void cacheResult(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition);

    /**
    * Caches the relationship level definitions in the entity cache if it is enabled.
    *
    * @param relationshipLevelDefinitions the relationship level definitions
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RelationshipLevelDefinition> relationshipLevelDefinitions);

    /**
    * Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
    *
    * @param relationshipLevelSid the primary key for the new relationship level definition
    * @return the new relationship level definition
    */
    public com.stpl.app.model.RelationshipLevelDefinition create(
        int relationshipLevelSid);

    /**
    * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition that was removed
    * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipLevelDefinition remove(
        int relationshipLevelSid)
        throws com.stpl.app.NoSuchRelationshipLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RelationshipLevelDefinition updateImpl(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the relationship level definition with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipLevelDefinitionException} if it could not be found.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition
    * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipLevelDefinition findByPrimaryKey(
        int relationshipLevelSid)
        throws com.stpl.app.NoSuchRelationshipLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RelationshipLevelDefinition fetchByPrimaryKey(
        int relationshipLevelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the relationship level definitions.
    *
    * @return the relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the relationship level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship level definitions
    * @param end the upper bound of the range of relationship level definitions (not inclusive)
    * @return the range of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the relationship level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship level definitions
    * @param end the upper bound of the range of relationship level definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the relationship level definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of relationship level definitions.
    *
    * @return the number of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
