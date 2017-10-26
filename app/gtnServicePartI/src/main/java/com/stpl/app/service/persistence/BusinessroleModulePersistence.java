package com.stpl.app.service.persistence;

import com.stpl.app.model.BusinessroleModule;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the businessrole module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleModulePersistenceImpl
 * @see BusinessroleModuleUtil
 * @generated
 */
public interface BusinessroleModulePersistence extends BasePersistence<BusinessroleModule> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BusinessroleModuleUtil} to access the businessrole module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the businessrole module in the entity cache if it is enabled.
    *
    * @param businessroleModule the businessrole module
    */
    public void cacheResult(
        com.stpl.app.model.BusinessroleModule businessroleModule);

    /**
    * Caches the businessrole modules in the entity cache if it is enabled.
    *
    * @param businessroleModules the businessrole modules
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.BusinessroleModule> businessroleModules);

    /**
    * Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
    *
    * @param businessroleModuleSid the primary key for the new businessrole module
    * @return the new businessrole module
    */
    public com.stpl.app.model.BusinessroleModule create(
        int businessroleModuleSid);

    /**
    * Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module that was removed
    * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleModule remove(
        int businessroleModuleSid)
        throws com.stpl.app.NoSuchBusinessroleModuleException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.BusinessroleModule updateImpl(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole module with the primary key or throws a {@link com.stpl.app.NoSuchBusinessroleModuleException} if it could not be found.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module
    * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleModule findByPrimaryKey(
        int businessroleModuleSid)
        throws com.stpl.app.NoSuchBusinessroleModuleException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleModule fetchByPrimaryKey(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the businessrole modules.
    *
    * @return the businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleModule> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the businessrole modules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole modules
    * @param end the upper bound of the range of businessrole modules (not inclusive)
    * @return the range of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleModule> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the businessrole modules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole modules
    * @param end the upper bound of the range of businessrole modules (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleModule> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the businessrole modules from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of businessrole modules.
    *
    * @return the number of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
