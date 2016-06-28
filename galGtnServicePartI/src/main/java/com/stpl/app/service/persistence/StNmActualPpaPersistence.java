package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmActualPpa;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualPpaPersistenceImpl
 * @see StNmActualPpaUtil
 * @generated
 */
public interface StNmActualPpaPersistence extends BasePersistence<StNmActualPpa> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNmActualPpaUtil} to access the st nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st nm actual ppa in the entity cache if it is enabled.
    *
    * @param stNmActualPpa the st nm actual ppa
    */
    public void cacheResult(com.stpl.app.model.StNmActualPpa stNmActualPpa);

    /**
    * Caches the st nm actual ppas in the entity cache if it is enabled.
    *
    * @param stNmActualPpas the st nm actual ppas
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNmActualPpa> stNmActualPpas);

    /**
    * Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
    *
    * @param stNmActualPpaPK the primary key for the new st nm actual ppa
    * @return the new st nm actual ppa
    */
    public com.stpl.app.model.StNmActualPpa create(
        StNmActualPpaPK stNmActualPpaPK);

    /**
    * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa that was removed
    * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmActualPpa remove(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.app.NoSuchStNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNmActualPpa updateImpl(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchStNmActualPpaException} if it could not be found.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa
    * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmActualPpa findByPrimaryKey(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.app.NoSuchStNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmActualPpaPK the primary key of the st nm actual ppa
    * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmActualPpa fetchByPrimaryKey(
        StNmActualPpaPK stNmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st nm actual ppas.
    *
    * @return the st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmActualPpa> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual ppas
    * @param end the upper bound of the range of st nm actual ppas (not inclusive)
    * @return the range of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmActualPpa> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm actual ppas
    * @param end the upper bound of the range of st nm actual ppas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmActualPpa> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st nm actual ppas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st nm actual ppas.
    *
    * @return the number of st nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
