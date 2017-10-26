package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCompanyIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyIdentifierPersistenceImpl
 * @see VwCompanyIdentifierUtil
 * @generated
 */
public interface VwCompanyIdentifierPersistence extends BasePersistence<VwCompanyIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwCompanyIdentifierUtil} to access the vw company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw company identifier in the entity cache if it is enabled.
    *
    * @param vwCompanyIdentifier the vw company identifier
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier);

    /**
    * Caches the vw company identifiers in the entity cache if it is enabled.
    *
    * @param vwCompanyIdentifiers the vw company identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> vwCompanyIdentifiers);

    /**
    * Creates a new vw company identifier with the primary key. Does not add the vw company identifier to the database.
    *
    * @param companyIdentifierSid the primary key for the new vw company identifier
    * @return the new vw company identifier
    */
    public com.stpl.app.parttwo.model.VwCompanyIdentifier create(
        int companyIdentifierSid);

    /**
    * Removes the vw company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyIdentifier remove(
        int companyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwCompanyIdentifier updateImpl(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException} if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier
    * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyIdentifier findByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw company identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier, or <code>null</code> if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwCompanyIdentifier fetchByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw company identifiers.
    *
    * @return the vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company identifiers
    * @param end the upper bound of the range of vw company identifiers (not inclusive)
    * @return the range of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company identifiers
    * @param end the upper bound of the range of vw company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw company identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw company identifiers.
    *
    * @return the number of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
