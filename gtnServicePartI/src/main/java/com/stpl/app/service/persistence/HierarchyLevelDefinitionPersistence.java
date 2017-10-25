package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelDefinition;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hierarchy level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinitionPersistenceImpl
 * @see HierarchyLevelDefinitionUtil
 * @generated
 */
public interface HierarchyLevelDefinitionPersistence extends BasePersistence<HierarchyLevelDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HierarchyLevelDefinitionUtil} to access the hierarchy level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hierarchy level definition in the entity cache if it is enabled.
    *
    * @param hierarchyLevelDefinition the hierarchy level definition
    */
    public void cacheResult(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition);

    /**
    * Caches the hierarchy level definitions in the entity cache if it is enabled.
    *
    * @param hierarchyLevelDefinitions the hierarchy level definitions
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyLevelDefinition> hierarchyLevelDefinitions);

    /**
    * Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
    *
    * @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
    * @return the new hierarchy level definition
    */
    public com.stpl.app.model.HierarchyLevelDefinition create(
        int hierarchyLevelDefinitionSid);

    /**
    * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition that was removed
    * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelDefinition remove(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HierarchyLevelDefinition updateImpl(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy level definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelDefinitionException} if it could not be found.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition
    * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelDefinition findByPrimaryKey(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelDefinition fetchByPrimaryKey(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hierarchy level definitions.
    *
    * @return the hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hierarchy level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level definitions
    * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
    * @return the range of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hierarchy level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level definitions
    * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hierarchy level definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hierarchy level definitions.
    *
    * @return the number of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
