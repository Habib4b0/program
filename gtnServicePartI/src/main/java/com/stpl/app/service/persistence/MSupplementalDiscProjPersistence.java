package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscProj;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscProjPersistenceImpl
 * @see MSupplementalDiscProjUtil
 * @generated
 */
public interface MSupplementalDiscProjPersistence extends BasePersistence<MSupplementalDiscProj> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MSupplementalDiscProjUtil} to access the m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m supplemental disc proj in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscProj the m supplemental disc proj
    */
    public void cacheResult(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj);

    /**
    * Caches the m supplemental disc projs in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscProjs the m supplemental disc projs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MSupplementalDiscProj> mSupplementalDiscProjs);

    /**
    * Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
    *
    * @param projectionDetailsSid the primary key for the new m supplemental disc proj
    * @return the new m supplemental disc proj
    */
    public com.stpl.app.model.MSupplementalDiscProj create(
        int projectionDetailsSid);

    /**
    * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj that was removed
    * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscProj remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MSupplementalDiscProj updateImpl(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m supplemental disc proj with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscProjException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj
    * @throws com.stpl.app.NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscProj findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscProjException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscProj fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m supplemental disc projs.
    *
    * @return the m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc projs
    * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
    * @return the range of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc projs
    * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m supplemental disc projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m supplemental disc projs.
    *
    * @return the number of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
