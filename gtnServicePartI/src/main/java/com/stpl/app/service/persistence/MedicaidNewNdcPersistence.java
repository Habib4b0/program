package com.stpl.app.service.persistence;

import com.stpl.app.model.MedicaidNewNdc;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidNewNdcPersistenceImpl
 * @see MedicaidNewNdcUtil
 * @generated
 */
public interface MedicaidNewNdcPersistence extends BasePersistence<MedicaidNewNdc> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MedicaidNewNdcUtil} to access the medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the medicaid new ndc in the entity cache if it is enabled.
    *
    * @param medicaidNewNdc the medicaid new ndc
    */
    public void cacheResult(com.stpl.app.model.MedicaidNewNdc medicaidNewNdc);

    /**
    * Caches the medicaid new ndcs in the entity cache if it is enabled.
    *
    * @param medicaidNewNdcs the medicaid new ndcs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MedicaidNewNdc> medicaidNewNdcs);

    /**
    * Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
    *
    * @param ndc9 the primary key for the new medicaid new ndc
    * @return the new medicaid new ndc
    */
    public com.stpl.app.model.MedicaidNewNdc create(java.lang.String ndc9);

    /**
    * Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ndc9 the primary key of the medicaid new ndc
    * @return the medicaid new ndc that was removed
    * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidNewNdc remove(java.lang.String ndc9)
        throws com.stpl.app.NoSuchMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MedicaidNewNdc updateImpl(
        com.stpl.app.model.MedicaidNewNdc medicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the medicaid new ndc with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidNewNdcException} if it could not be found.
    *
    * @param ndc9 the primary key of the medicaid new ndc
    * @return the medicaid new ndc
    * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidNewNdc findByPrimaryKey(
        java.lang.String ndc9)
        throws com.stpl.app.NoSuchMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ndc9 the primary key of the medicaid new ndc
    * @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidNewNdc fetchByPrimaryKey(
        java.lang.String ndc9)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the medicaid new ndcs.
    *
    * @return the medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MedicaidNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid new ndcs
    * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
    * @return the range of medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MedicaidNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid new ndcs
    * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MedicaidNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the medicaid new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of medicaid new ndcs.
    *
    * @return the number of medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
