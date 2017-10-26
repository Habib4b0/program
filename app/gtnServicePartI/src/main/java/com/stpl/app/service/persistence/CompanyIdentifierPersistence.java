package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyIdentifierPersistenceImpl
 * @see CompanyIdentifierUtil
 * @generated
 */
public interface CompanyIdentifierPersistence extends BasePersistence<CompanyIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyIdentifierUtil} to access the company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @return the matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifier(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @return the range of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifier(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifier(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company identifier in the ordered set where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier findByCompanyCrtIdentifier_First(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company identifier in the ordered set where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier fetchByCompanyCrtIdentifier_First(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company identifier in the ordered set where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier findByCompanyCrtIdentifier_Last(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company identifier in the ordered set where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier fetchByCompanyCrtIdentifier_Last(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company identifiers before and after the current company identifier in the ordered set where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierSid the primary key of the current company identifier
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier[] findByCompanyCrtIdentifier_PrevAndNext(
        int companyIdentifierSid, java.lang.String companyIdentifierValue,
        int companyQualifierSid, int identifierStatus,
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyCrtIdentifier(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param companyIdentifierValue the company identifier value
    * @param companyQualifierSid the company qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @return the number of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyCrtIdentifier(
        java.lang.String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company identifiers where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @return the matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company identifiers where companyMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @return the range of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company identifiers where companyMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier findByCompanyCrtIdentifierDetails_First(
        int companyMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier fetchByCompanyCrtIdentifierDetails_First(
        int companyMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier findByCompanyCrtIdentifierDetails_Last(
        int companyMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier fetchByCompanyCrtIdentifierDetails_Last(
        int companyMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company identifiers before and after the current company identifier in the ordered set where companyMasterSid = &#63;.
    *
    * @param companyIdentifierSid the primary key of the current company identifier
    * @param companyMasterSid the company master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier[] findByCompanyCrtIdentifierDetails_PrevAndNext(
        int companyIdentifierSid, int companyMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company identifiers where companyMasterSid = &#63; from the database.
    *
    * @param companyMasterSid the company master sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyCrtIdentifierDetails(int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company identifiers where companyMasterSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @return the number of matching company identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyCrtIdentifierDetails(int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the company identifier in the entity cache if it is enabled.
    *
    * @param companyIdentifier the company identifier
    */
    public void cacheResult(
        com.stpl.app.model.CompanyIdentifier companyIdentifier);

    /**
    * Caches the company identifiers in the entity cache if it is enabled.
    *
    * @param companyIdentifiers the company identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CompanyIdentifier> companyIdentifiers);

    /**
    * Creates a new company identifier with the primary key. Does not add the company identifier to the database.
    *
    * @param companyIdentifierSid the primary key for the new company identifier
    * @return the new company identifier
    */
    public com.stpl.app.model.CompanyIdentifier create(int companyIdentifierSid);

    /**
    * Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifierSid the primary key of the company identifier
    * @return the company identifier that was removed
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier remove(int companyIdentifierSid)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CompanyIdentifier updateImpl(
        com.stpl.app.model.CompanyIdentifier companyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company identifier with the primary key or throws a {@link com.stpl.app.NoSuchCompanyIdentifierException} if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the company identifier
    * @return the company identifier
    * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier findByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.app.NoSuchCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyIdentifierSid the primary key of the company identifier
    * @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyIdentifier fetchByPrimaryKey(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company identifiers.
    *
    * @return the company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @return the range of company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company identifiers.
    *
    * @return the number of company identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
