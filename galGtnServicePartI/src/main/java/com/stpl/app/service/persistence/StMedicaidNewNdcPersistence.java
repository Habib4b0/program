package com.stpl.app.service.persistence;

import com.stpl.app.model.StMedicaidNewNdc;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMedicaidNewNdcPersistenceImpl
 * @see StMedicaidNewNdcUtil
 * @generated
 */
public interface StMedicaidNewNdcPersistence extends BasePersistence<StMedicaidNewNdc> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StMedicaidNewNdcUtil} to access the st medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st medicaid new ndc in the entity cache if it is enabled.
    *
    * @param stMedicaidNewNdc the st medicaid new ndc
    */
    public void cacheResult(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc);

    /**
    * Caches the st medicaid new ndcs in the entity cache if it is enabled.
    *
    * @param stMedicaidNewNdcs the st medicaid new ndcs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StMedicaidNewNdc> stMedicaidNewNdcs);

    /**
    * Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
    *
    * @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
    * @return the new st medicaid new ndc
    */
    public com.stpl.app.model.StMedicaidNewNdc create(
        StMedicaidNewNdcPK stMedicaidNewNdcPK);

    /**
    * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc that was removed
    * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMedicaidNewNdc remove(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.app.NoSuchStMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StMedicaidNewNdc updateImpl(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st medicaid new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStMedicaidNewNdcException} if it could not be found.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc
    * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMedicaidNewNdc findByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.app.NoSuchStMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMedicaidNewNdc fetchByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st medicaid new ndcs.
    *
    * @return the st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st medicaid new ndcs
    * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
    * @return the range of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st medicaid new ndcs
    * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st medicaid new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st medicaid new ndcs.
    *
    * @return the number of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
