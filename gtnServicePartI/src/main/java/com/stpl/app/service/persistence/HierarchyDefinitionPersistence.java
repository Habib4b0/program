package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyDefinition;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyDefinitionPersistenceImpl
 * @see HierarchyDefinitionUtil
 * @generated
 */
public interface HierarchyDefinitionPersistence extends BasePersistence<HierarchyDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HierarchyDefinitionUtil} to access the hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hierarchy definition in the entity cache if it is enabled.
    *
    * @param hierarchyDefinition the hierarchy definition
    */
    public void cacheResult(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition);

    /**
    * Caches the hierarchy definitions in the entity cache if it is enabled.
    *
    * @param hierarchyDefinitions the hierarchy definitions
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyDefinition> hierarchyDefinitions);

    /**
    * Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
    *
    * @param hierarchyDefinitionSid the primary key for the new hierarchy definition
    * @return the new hierarchy definition
    */
    public com.stpl.app.model.HierarchyDefinition create(
        int hierarchyDefinitionSid);

    /**
    * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyDefinition remove(
        int hierarchyDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HierarchyDefinition updateImpl(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyDefinitionException} if it could not be found.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition
    * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyDefinition findByPrimaryKey(
        int hierarchyDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyDefinition fetchByPrimaryKey(
        int hierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hierarchy definitions.
    *
    * @return the hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy definitions
    * @param end the upper bound of the range of hierarchy definitions (not inclusive)
    * @return the range of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy definitions
    * @param end the upper bound of the range of hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hierarchy definitions.
    *
    * @return the number of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
