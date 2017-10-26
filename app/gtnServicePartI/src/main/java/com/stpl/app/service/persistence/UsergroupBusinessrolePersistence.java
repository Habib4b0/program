package com.stpl.app.service.persistence;

import com.stpl.app.model.UsergroupBusinessrole;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the usergroup businessrole service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupBusinessrolePersistenceImpl
 * @see UsergroupBusinessroleUtil
 * @generated
 */
public interface UsergroupBusinessrolePersistence extends BasePersistence<UsergroupBusinessrole> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link UsergroupBusinessroleUtil} to access the usergroup businessrole persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the usergroup businessrole in the entity cache if it is enabled.
    *
    * @param usergroupBusinessrole the usergroup businessrole
    */
    public void cacheResult(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole);

    /**
    * Caches the usergroup businessroles in the entity cache if it is enabled.
    *
    * @param usergroupBusinessroles the usergroup businessroles
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.UsergroupBusinessrole> usergroupBusinessroles);

    /**
    * Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
    *
    * @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
    * @return the new usergroup businessrole
    */
    public com.stpl.app.model.UsergroupBusinessrole create(
        int usergroupBusinessroleSid);

    /**
    * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole that was removed
    * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupBusinessrole remove(
        int usergroupBusinessroleSid)
        throws com.stpl.app.NoSuchUsergroupBusinessroleException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.UsergroupBusinessrole updateImpl(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the usergroup businessrole with the primary key or throws a {@link com.stpl.app.NoSuchUsergroupBusinessroleException} if it could not be found.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole
    * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupBusinessrole findByPrimaryKey(
        int usergroupBusinessroleSid)
        throws com.stpl.app.NoSuchUsergroupBusinessroleException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupBusinessrole fetchByPrimaryKey(
        int usergroupBusinessroleSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the usergroup businessroles.
    *
    * @return the usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the usergroup businessroles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup businessroles
    * @param end the upper bound of the range of usergroup businessroles (not inclusive)
    * @return the range of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the usergroup businessroles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup businessroles
    * @param end the upper bound of the range of usergroup businessroles (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupBusinessrole> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the usergroup businessroles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of usergroup businessroles.
    *
    * @return the number of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
