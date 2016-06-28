package com.stpl.app.service.persistence;

import com.stpl.app.model.ModuleProperties;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the module properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModulePropertiesPersistenceImpl
 * @see ModulePropertiesUtil
 * @generated
 */
public interface ModulePropertiesPersistence extends BasePersistence<ModuleProperties> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModulePropertiesUtil} to access the module properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the module properties in the entity cache if it is enabled.
    *
    * @param moduleProperties the module properties
    */
    public void cacheResult(
        com.stpl.app.model.ModuleProperties moduleProperties);

    /**
    * Caches the module propertieses in the entity cache if it is enabled.
    *
    * @param modulePropertieses the module propertieses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ModuleProperties> modulePropertieses);

    /**
    * Creates a new module properties with the primary key. Does not add the module properties to the database.
    *
    * @param modulePropertySid the primary key for the new module properties
    * @return the new module properties
    */
    public com.stpl.app.model.ModuleProperties create(int modulePropertySid);

    /**
    * Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modulePropertySid the primary key of the module properties
    * @return the module properties that was removed
    * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ModuleProperties remove(int modulePropertySid)
        throws com.stpl.app.NoSuchModulePropertiesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ModuleProperties updateImpl(
        com.stpl.app.model.ModuleProperties moduleProperties)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the module properties with the primary key or throws a {@link com.stpl.app.NoSuchModulePropertiesException} if it could not be found.
    *
    * @param modulePropertySid the primary key of the module properties
    * @return the module properties
    * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ModuleProperties findByPrimaryKey(
        int modulePropertySid)
        throws com.stpl.app.NoSuchModulePropertiesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modulePropertySid the primary key of the module properties
    * @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ModuleProperties fetchByPrimaryKey(
        int modulePropertySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the module propertieses.
    *
    * @return the module propertieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ModuleProperties> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the module propertieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module propertieses
    * @param end the upper bound of the range of module propertieses (not inclusive)
    * @return the range of module propertieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ModuleProperties> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the module propertieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module propertieses
    * @param end the upper bound of the range of module propertieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of module propertieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ModuleProperties> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the module propertieses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of module propertieses.
    *
    * @return the number of module propertieses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
