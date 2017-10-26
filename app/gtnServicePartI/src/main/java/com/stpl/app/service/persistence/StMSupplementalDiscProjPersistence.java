package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscProj;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProjPersistenceImpl
 * @see StMSupplementalDiscProjUtil
 * @generated
 */
public interface StMSupplementalDiscProjPersistence extends BasePersistence<StMSupplementalDiscProj> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StMSupplementalDiscProjUtil} to access the st m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st m supplemental disc proj in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscProj the st m supplemental disc proj
    */
    public void cacheResult(
        com.stpl.app.model.StMSupplementalDiscProj stMSupplementalDiscProj);

    /**
    * Caches the st m supplemental disc projs in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscProjs the st m supplemental disc projs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StMSupplementalDiscProj> stMSupplementalDiscProjs);

    /**
    * Creates a new st m supplemental disc proj with the primary key. Does not add the st m supplemental disc proj to the database.
    *
    * @param stMSupplementalDiscProjPK the primary key for the new st m supplemental disc proj
    * @return the new st m supplemental disc proj
    */
    public com.stpl.app.model.StMSupplementalDiscProj create(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK);

    /**
    * Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
    * @return the st m supplemental disc proj that was removed
    * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscProj remove(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StMSupplementalDiscProj updateImpl(
        com.stpl.app.model.StMSupplementalDiscProj stMSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m supplemental disc proj with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscProjException} if it could not be found.
    *
    * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
    * @return the st m supplemental disc proj
    * @throws com.stpl.app.NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscProj findByPrimaryKey(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
    * @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscProj fetchByPrimaryKey(
        StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st m supplemental disc projs.
    *
    * @return the st m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc projs
    * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
    * @return the range of st m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc projs
    * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st m supplemental disc projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st m supplemental disc projs.
    *
    * @return the number of st m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
