package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyDefinition;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyDefinitionPersistenceImpl
 * @see HistHierarchyDefinitionUtil
 * @generated
 */
public interface HistHierarchyDefinitionPersistence extends BasePersistence<HistHierarchyDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistHierarchyDefinitionUtil} to access the hist hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist hierarchy definition in the entity cache if it is enabled.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    */
    public void cacheResult(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition);

    /**
    * Caches the hist hierarchy definitions in the entity cache if it is enabled.
    *
    * @param histHierarchyDefinitions the hist hierarchy definitions
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistHierarchyDefinition> histHierarchyDefinitions);

    /**
    * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
    *
    * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
    * @return the new hist hierarchy definition
    */
    public com.stpl.app.model.HistHierarchyDefinition create(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK);

    /**
    * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyDefinition remove(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.app.NoSuchHistHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistHierarchyDefinition updateImpl(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyDefinitionException} if it could not be found.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition
    * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyDefinition findByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.app.NoSuchHistHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyDefinition fetchByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist hierarchy definitions.
    *
    * @return the hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @return the range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist hierarchy definitions.
    *
    * @return the number of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
