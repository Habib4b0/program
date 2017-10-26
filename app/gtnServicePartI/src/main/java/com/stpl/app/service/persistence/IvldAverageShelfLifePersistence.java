package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldAverageShelfLife;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld average shelf life service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAverageShelfLifePersistenceImpl
 * @see IvldAverageShelfLifeUtil
 * @generated
 */
public interface IvldAverageShelfLifePersistence extends BasePersistence<IvldAverageShelfLife> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldAverageShelfLifeUtil} to access the ivld average shelf life persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld average shelf life in the entity cache if it is enabled.
    *
    * @param ivldAverageShelfLife the ivld average shelf life
    */
    public void cacheResult(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife);

    /**
    * Caches the ivld average shelf lifes in the entity cache if it is enabled.
    *
    * @param ivldAverageShelfLifes the ivld average shelf lifes
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldAverageShelfLife> ivldAverageShelfLifes);

    /**
    * Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
    *
    * @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
    * @return the new ivld average shelf life
    */
    public com.stpl.app.model.IvldAverageShelfLife create(
        int ivldAverageShelfLifeSid);

    /**
    * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life that was removed
    * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldAverageShelfLife remove(
        int ivldAverageShelfLifeSid)
        throws com.stpl.app.NoSuchIvldAverageShelfLifeException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldAverageShelfLife updateImpl(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld average shelf life with the primary key or throws a {@link com.stpl.app.NoSuchIvldAverageShelfLifeException} if it could not be found.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life
    * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldAverageShelfLife findByPrimaryKey(
        int ivldAverageShelfLifeSid)
        throws com.stpl.app.NoSuchIvldAverageShelfLifeException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldAverageShelfLife fetchByPrimaryKey(
        int ivldAverageShelfLifeSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld average shelf lifes.
    *
    * @return the ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld average shelf lifes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld average shelf lifes
    * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
    * @return the range of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld average shelf lifes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld average shelf lifes
    * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldAverageShelfLife> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld average shelf lifes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld average shelf lifes.
    *
    * @return the number of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
