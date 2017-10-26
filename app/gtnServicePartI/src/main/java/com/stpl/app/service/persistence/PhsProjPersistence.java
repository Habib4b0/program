package com.stpl.app.service.persistence;

import com.stpl.app.model.PhsProj;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the phs proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsProjPersistenceImpl
 * @see PhsProjUtil
 * @generated
 */
public interface PhsProjPersistence extends BasePersistence<PhsProj> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PhsProjUtil} to access the phs proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the phs proj in the entity cache if it is enabled.
    *
    * @param phsProj the phs proj
    */
    public void cacheResult(com.stpl.app.model.PhsProj phsProj);

    /**
    * Caches the phs projs in the entity cache if it is enabled.
    *
    * @param phsProjs the phs projs
    */
    public void cacheResult(java.util.List<com.stpl.app.model.PhsProj> phsProjs);

    /**
    * Creates a new phs proj with the primary key. Does not add the phs proj to the database.
    *
    * @param phsProjPK the primary key for the new phs proj
    * @return the new phs proj
    */
    public com.stpl.app.model.PhsProj create(PhsProjPK phsProjPK);

    /**
    * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj that was removed
    * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsProj remove(PhsProjPK phsProjPK)
        throws com.stpl.app.NoSuchPhsProjException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PhsProj updateImpl(
        com.stpl.app.model.PhsProj phsProj)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the phs proj with the primary key or throws a {@link com.stpl.app.NoSuchPhsProjException} if it could not be found.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj
    * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsProj findByPrimaryKey(PhsProjPK phsProjPK)
        throws com.stpl.app.NoSuchPhsProjException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsProj fetchByPrimaryKey(PhsProjPK phsProjPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the phs projs.
    *
    * @return the phs projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the phs projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs projs
    * @param end the upper bound of the range of phs projs (not inclusive)
    * @return the range of phs projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsProj> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the phs projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs projs
    * @param end the upper bound of the range of phs projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of phs projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsProj> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the phs projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of phs projs.
    *
    * @return the number of phs projs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
