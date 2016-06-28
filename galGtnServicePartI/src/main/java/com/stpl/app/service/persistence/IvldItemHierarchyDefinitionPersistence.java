package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchyDefinition;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld item hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionPersistenceImpl
 * @see IvldItemHierarchyDefinitionUtil
 * @generated
 */
public interface IvldItemHierarchyDefinitionPersistence extends BasePersistence<IvldItemHierarchyDefinition> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldItemHierarchyDefinitionUtil} to access the ivld item hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld item hierarchy definition in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchyDefinition the ivld item hierarchy definition
    */
    public void cacheResult(
        com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition);

    /**
    * Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions);

    /**
    * Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
    * @return the new ivld item hierarchy definition
    */
    public com.stpl.app.model.IvldItemHierarchyDefinition create(
        int ivldItemHierarchyDefinitionSid);

    /**
    * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchyDefinition remove(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldItemHierarchyDefinition updateImpl(
        com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition
    * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchyDefinition findByPrimaryKey(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchyDefinition fetchByPrimaryKey(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld item hierarchy definitions.
    *
    * @return the ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld item hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchy definitions
    * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
    * @return the range of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld item hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchy definitions
    * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld item hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld item hierarchy definitions.
    *
    * @return the number of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
