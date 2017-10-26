package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyIdentifierException;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.impl.CompanyIdentifierImpl;
import com.stpl.app.model.impl.CompanyIdentifierModelImpl;
import com.stpl.app.service.persistence.CompanyIdentifierPersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.QueryPos;
import com.stpl.portal.kernel.dao.orm.QueryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.log.Log;
import com.stpl.portal.kernel.log.LogFactoryUtil;
import com.stpl.portal.kernel.util.CalendarUtil;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.InstanceFactory;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.PropsKeys;
import com.stpl.portal.kernel.util.PropsUtil;
import com.stpl.portal.kernel.util.SetUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.kernel.util.StringUtil;
import com.stpl.portal.kernel.util.UnmodifiableList;
import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyIdentifierPersistence
 * @see CompanyIdentifierUtil
 * @generated
 */
public class CompanyIdentifierPersistenceImpl extends BasePersistenceImpl<CompanyIdentifier>
    implements CompanyIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyIdentifierUtil} to access the company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER =
        new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyCrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER =
        new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyCrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName()
            },
            CompanyIdentifierModelImpl.COMPANYIDENTIFIERVALUE_COLUMN_BITMASK |
            CompanyIdentifierModelImpl.COMPANYQUALIFIERSID_COLUMN_BITMASK |
            CompanyIdentifierModelImpl.IDENTIFIERSTATUS_COLUMN_BITMASK |
            CompanyIdentifierModelImpl.STARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCompanyCrtIdentifier",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(), Date.class.getName()
            });
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_1 =
        "companyIdentifier.companyIdentifierValue IS NULL AND ";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_2 =
        "companyIdentifier.companyIdentifierValue = ? AND ";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_3 =
        "(companyIdentifier.companyIdentifierValue IS NULL OR companyIdentifier.companyIdentifierValue = '') AND ";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2 =
        "companyIdentifier.companyQualifierSid = ? AND ";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2 =
        "companyIdentifier.identifierStatus = ? AND ";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1 = "companyIdentifier.startDate IS NULL";
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2 = "companyIdentifier.startDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS =
        new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyCrtIdentifierDetails",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS =
        new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyCrtIdentifierDetails",
            new String[] { Integer.class.getName() },
            CompanyIdentifierModelImpl.COMPANYMASTERSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS =
        new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCompanyCrtIdentifierDetails",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2 =
        "companyIdentifier.companyMasterSid = ?";
    private static final String _SQL_SELECT_COMPANYIDENTIFIER = "SELECT companyIdentifier FROM CompanyIdentifier companyIdentifier";
    private static final String _SQL_SELECT_COMPANYIDENTIFIER_WHERE = "SELECT companyIdentifier FROM CompanyIdentifier companyIdentifier WHERE ";
    private static final String _SQL_COUNT_COMPANYIDENTIFIER = "SELECT COUNT(companyIdentifier) FROM CompanyIdentifier companyIdentifier";
    private static final String _SQL_COUNT_COMPANYIDENTIFIER_WHERE = "SELECT COUNT(companyIdentifier) FROM CompanyIdentifier companyIdentifier WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyIdentifier exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyIdentifier exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "endDate", "companyIdentifierSid", "modifiedDate",
                "identifierStatus", "entityCode", "recordLockStatus",
                "startDate", "createdDate", "source", "createdBy",
                "companyIdentifierValue", "batchId", "companyQualifierSid",
                "modifiedBy", "inboundStatus", "companyMasterSid"
            });
    private static CompanyIdentifier _nullCompanyIdentifier = new CompanyIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyIdentifier> toCacheModel() {
                return _nullCompanyIdentifierCacheModel;
            }
        };

    private static CacheModel<CompanyIdentifier> _nullCompanyIdentifierCacheModel =
        new CacheModel<CompanyIdentifier>() {
            @Override
            public CompanyIdentifier toEntityModel() {
                return _nullCompanyIdentifier;
            }
        };

    public CompanyIdentifierPersistenceImpl() {
        setModelClass(CompanyIdentifier.class);
    }

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
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifier(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate) throws SystemException {
        return findByCompanyCrtIdentifier(companyIdentifierValue,
            companyQualifierSid, identifierStatus, startDate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifier(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate, int start, int end)
        throws SystemException {
        return findByCompanyCrtIdentifier(companyIdentifierValue,
            companyQualifierSid, identifierStatus, startDate, start, end, null);
    }

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
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifier(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER;
            finderArgs = new Object[] {
                    companyIdentifierValue, companyQualifierSid,
                    identifierStatus, startDate
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER;
            finderArgs = new Object[] {
                    companyIdentifierValue, companyQualifierSid,
                    identifierStatus, startDate,
                    
                    start, end, orderByComparator
                };
        }

        List<CompanyIdentifier> list = (List<CompanyIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyIdentifier companyIdentifier : list) {
                if (!Validator.equals(companyIdentifierValue,
                            companyIdentifier.getCompanyIdentifierValue()) ||
                        (companyQualifierSid != companyIdentifier.getCompanyQualifierSid()) ||
                        (identifierStatus != companyIdentifier.getIdentifierStatus()) ||
                        !Validator.equals(startDate,
                            companyIdentifier.getStartDate())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(6 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(6);
            }

            query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

            boolean bindCompanyIdentifierValue = false;

            if (companyIdentifierValue == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_1);
            } else if (companyIdentifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_3);
            } else {
                bindCompanyIdentifierValue = true;

                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_2);
            }

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyIdentifierValue) {
                    qPos.add(companyIdentifierValue);
                }

                qPos.add(companyQualifierSid);

                qPos.add(identifierStatus);

                if (bindStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(startDate));
                }

                if (!pagination) {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyIdentifier>(list);
                } else {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

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
    @Override
    public CompanyIdentifier findByCompanyCrtIdentifier_First(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifier_First(companyIdentifierValue,
                companyQualifierSid, identifierStatus, startDate,
                orderByComparator);

        if (companyIdentifier != null) {
            return companyIdentifier;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyIdentifierValue=");
        msg.append(companyIdentifierValue);

        msg.append(", companyQualifierSid=");
        msg.append(companyQualifierSid);

        msg.append(", identifierStatus=");
        msg.append(identifierStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyIdentifierException(msg.toString());
    }

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
    @Override
    public CompanyIdentifier fetchByCompanyCrtIdentifier_First(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyIdentifier> list = findByCompanyCrtIdentifier(companyIdentifierValue,
                companyQualifierSid, identifierStatus, startDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CompanyIdentifier findByCompanyCrtIdentifier_Last(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifier_Last(companyIdentifierValue,
                companyQualifierSid, identifierStatus, startDate,
                orderByComparator);

        if (companyIdentifier != null) {
            return companyIdentifier;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyIdentifierValue=");
        msg.append(companyIdentifierValue);

        msg.append(", companyQualifierSid=");
        msg.append(companyQualifierSid);

        msg.append(", identifierStatus=");
        msg.append(identifierStatus);

        msg.append(", startDate=");
        msg.append(startDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyIdentifierException(msg.toString());
    }

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
    @Override
    public CompanyIdentifier fetchByCompanyCrtIdentifier_Last(
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyCrtIdentifier(companyIdentifierValue,
                companyQualifierSid, identifierStatus, startDate);

        if (count == 0) {
            return null;
        }

        List<CompanyIdentifier> list = findByCompanyCrtIdentifier(companyIdentifierValue,
                companyQualifierSid, identifierStatus, startDate, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CompanyIdentifier[] findByCompanyCrtIdentifier_PrevAndNext(
        int companyIdentifierSid, String companyIdentifierValue,
        int companyQualifierSid, int identifierStatus, Date startDate,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = findByPrimaryKey(companyIdentifierSid);

        Session session = null;

        try {
            session = openSession();

            CompanyIdentifier[] array = new CompanyIdentifierImpl[3];

            array[0] = getByCompanyCrtIdentifier_PrevAndNext(session,
                    companyIdentifier, companyIdentifierValue,
                    companyQualifierSid, identifierStatus, startDate,
                    orderByComparator, true);

            array[1] = companyIdentifier;

            array[2] = getByCompanyCrtIdentifier_PrevAndNext(session,
                    companyIdentifier, companyIdentifierValue,
                    companyQualifierSid, identifierStatus, startDate,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyIdentifier getByCompanyCrtIdentifier_PrevAndNext(
        Session session, CompanyIdentifier companyIdentifier,
        String companyIdentifierValue, int companyQualifierSid,
        int identifierStatus, Date startDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

        boolean bindCompanyIdentifierValue = false;

        if (companyIdentifierValue == null) {
            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_1);
        } else if (companyIdentifierValue.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_3);
        } else {
            bindCompanyIdentifierValue = true;

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_2);
        }

        query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

        query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

        boolean bindStartDate = false;

        if (startDate == null) {
            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
        } else {
            bindStartDate = true;

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyIdentifierValue) {
            qPos.add(companyIdentifierValue);
        }

        qPos.add(companyQualifierSid);

        qPos.add(identifierStatus);

        if (bindStartDate) {
            qPos.add(CalendarUtil.getTimestamp(startDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyIdentifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyIdentifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company identifiers where companyIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
     *
     * @param companyIdentifierValue the company identifier value
     * @param companyQualifierSid the company qualifier sid
     * @param identifierStatus the identifier status
     * @param startDate the start date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyCrtIdentifier(String companyIdentifierValue,
        int companyQualifierSid, int identifierStatus, Date startDate)
        throws SystemException {
        for (CompanyIdentifier companyIdentifier : findByCompanyCrtIdentifier(
                companyIdentifierValue, companyQualifierSid, identifierStatus,
                startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyIdentifier);
        }
    }

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
    @Override
    public int countByCompanyCrtIdentifier(String companyIdentifierValue,
        int companyQualifierSid, int identifierStatus, Date startDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER;

        Object[] finderArgs = new Object[] {
                companyIdentifierValue, companyQualifierSid, identifierStatus,
                startDate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_COMPANYIDENTIFIER_WHERE);

            boolean bindCompanyIdentifierValue = false;

            if (companyIdentifierValue == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_1);
            } else if (companyIdentifierValue.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_3);
            } else {
                bindCompanyIdentifierValue = true;

                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYIDENTIFIERVALUE_2);
            }

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

            boolean bindStartDate = false;

            if (startDate == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
            } else {
                bindStartDate = true;

                query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyIdentifierValue) {
                    qPos.add(companyIdentifierValue);
                }

                qPos.add(companyQualifierSid);

                qPos.add(identifierStatus);

                if (bindStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(startDate));
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the company identifiers where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @return the matching company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid) throws SystemException {
        return findByCompanyCrtIdentifierDetails(companyMasterSid,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid, int start, int end) throws SystemException {
        return findByCompanyCrtIdentifierDetails(companyMasterSid, start, end,
            null);
    }

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
    @Override
    public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS;
            finderArgs = new Object[] { companyMasterSid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS;
            finderArgs = new Object[] {
                    companyMasterSid,
                    
                    start, end, orderByComparator
                };
        }

        List<CompanyIdentifier> list = (List<CompanyIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyIdentifier companyIdentifier : list) {
                if ((companyMasterSid != companyIdentifier.getCompanyMasterSid())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyMasterSid);

                if (!pagination) {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyIdentifier>(list);
                } else {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company identifier
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier findByCompanyCrtIdentifierDetails_First(
        int companyMasterSid, OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifierDetails_First(companyMasterSid,
                orderByComparator);

        if (companyIdentifier != null) {
            return companyIdentifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyMasterSid=");
        msg.append(companyMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyIdentifierException(msg.toString());
    }

    /**
     * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_First(
        int companyMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        List<CompanyIdentifier> list = findByCompanyCrtIdentifierDetails(companyMasterSid,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company identifier
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a matching company identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier findByCompanyCrtIdentifierDetails_Last(
        int companyMasterSid, OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifierDetails_Last(companyMasterSid,
                orderByComparator);

        if (companyIdentifier != null) {
            return companyIdentifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyMasterSid=");
        msg.append(companyMasterSid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyIdentifierException(msg.toString());
    }

    /**
     * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_Last(
        int companyMasterSid, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByCompanyCrtIdentifierDetails(companyMasterSid);

        if (count == 0) {
            return null;
        }

        List<CompanyIdentifier> list = findByCompanyCrtIdentifierDetails(companyMasterSid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

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
    @Override
    public CompanyIdentifier[] findByCompanyCrtIdentifierDetails_PrevAndNext(
        int companyIdentifierSid, int companyMasterSid,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = findByPrimaryKey(companyIdentifierSid);

        Session session = null;

        try {
            session = openSession();

            CompanyIdentifier[] array = new CompanyIdentifierImpl[3];

            array[0] = getByCompanyCrtIdentifierDetails_PrevAndNext(session,
                    companyIdentifier, companyMasterSid, orderByComparator, true);

            array[1] = companyIdentifier;

            array[2] = getByCompanyCrtIdentifierDetails_PrevAndNext(session,
                    companyIdentifier, companyMasterSid, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyIdentifier getByCompanyCrtIdentifierDetails_PrevAndNext(
        Session session, CompanyIdentifier companyIdentifier,
        int companyMasterSid, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

        query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyMasterSid);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyIdentifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyIdentifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company identifiers where companyMasterSid = &#63; from the database.
     *
     * @param companyMasterSid the company master sid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyCrtIdentifierDetails(int companyMasterSid)
        throws SystemException {
        for (CompanyIdentifier companyIdentifier : findByCompanyCrtIdentifierDetails(
                companyMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyIdentifier);
        }
    }

    /**
     * Returns the number of company identifiers where companyMasterSid = &#63;.
     *
     * @param companyMasterSid the company master sid
     * @return the number of matching company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyCrtIdentifierDetails(int companyMasterSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS;

        Object[] finderArgs = new Object[] { companyMasterSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYIDENTIFIER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyMasterSid);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the company identifier in the entity cache if it is enabled.
     *
     * @param companyIdentifier the company identifier
     */
    @Override
    public void cacheResult(CompanyIdentifier companyIdentifier) {
        EntityCacheUtil.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey(),
            companyIdentifier);

        companyIdentifier.resetOriginalValues();
    }

    /**
     * Caches the company identifiers in the entity cache if it is enabled.
     *
     * @param companyIdentifiers the company identifiers
     */
    @Override
    public void cacheResult(List<CompanyIdentifier> companyIdentifiers) {
        for (CompanyIdentifier companyIdentifier : companyIdentifiers) {
            if (EntityCacheUtil.getResult(
                        CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyIdentifierImpl.class,
                        companyIdentifier.getPrimaryKey()) == null) {
                cacheResult(companyIdentifier);
            } else {
                companyIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyIdentifier companyIdentifier) {
        EntityCacheUtil.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyIdentifier> companyIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyIdentifier companyIdentifier : companyIdentifiers) {
            EntityCacheUtil.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new company identifier with the primary key. Does not add the company identifier to the database.
     *
     * @param companyIdentifierSid the primary key for the new company identifier
     * @return the new company identifier
     */
    @Override
    public CompanyIdentifier create(int companyIdentifierSid) {
        CompanyIdentifier companyIdentifier = new CompanyIdentifierImpl();

        companyIdentifier.setNew(true);
        companyIdentifier.setPrimaryKey(companyIdentifierSid);

        return companyIdentifier;
    }

    /**
     * Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyIdentifierSid the primary key of the company identifier
     * @return the company identifier that was removed
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier remove(int companyIdentifierSid)
        throws NoSuchCompanyIdentifierException, SystemException {
        return remove((Serializable) companyIdentifierSid);
    }

    /**
     * Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company identifier
     * @return the company identifier that was removed
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier remove(Serializable primaryKey)
        throws NoSuchCompanyIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyIdentifier companyIdentifier = (CompanyIdentifier) session.get(CompanyIdentifierImpl.class,
                    primaryKey);

            if (companyIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyIdentifier);
        } catch (NoSuchCompanyIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyIdentifier removeImpl(CompanyIdentifier companyIdentifier)
        throws SystemException {
        companyIdentifier = toUnwrappedModel(companyIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyIdentifier)) {
                companyIdentifier = (CompanyIdentifier) session.get(CompanyIdentifierImpl.class,
                        companyIdentifier.getPrimaryKeyObj());
            }

            if (companyIdentifier != null) {
                session.delete(companyIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyIdentifier != null) {
            clearCache(companyIdentifier);
        }

        return companyIdentifier;
    }

    @Override
    public CompanyIdentifier updateImpl(
        com.stpl.app.model.CompanyIdentifier companyIdentifier)
        throws SystemException {
        companyIdentifier = toUnwrappedModel(companyIdentifier);

        boolean isNew = companyIdentifier.isNew();

        CompanyIdentifierModelImpl companyIdentifierModelImpl = (CompanyIdentifierModelImpl) companyIdentifier;

        Session session = null;

        try {
            session = openSession();

            if (companyIdentifier.isNew()) {
                session.save(companyIdentifier);

                companyIdentifier.setNew(false);
            } else {
                session.merge(companyIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CompanyIdentifierModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((companyIdentifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyIdentifierModelImpl.getOriginalCompanyIdentifierValue(),
                        companyIdentifierModelImpl.getOriginalCompanyQualifierSid(),
                        companyIdentifierModelImpl.getOriginalIdentifierStatus(),
                        companyIdentifierModelImpl.getOriginalStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER,
                    args);

                args = new Object[] {
                        companyIdentifierModelImpl.getCompanyIdentifierValue(),
                        companyIdentifierModelImpl.getCompanyQualifierSid(),
                        companyIdentifierModelImpl.getIdentifierStatus(),
                        companyIdentifierModelImpl.getStartDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER,
                    args);
            }

            if ((companyIdentifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyIdentifierModelImpl.getOriginalCompanyMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS,
                    args);

                args = new Object[] {
                        companyIdentifierModelImpl.getCompanyMasterSid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey(),
            companyIdentifier);

        return companyIdentifier;
    }

    protected CompanyIdentifier toUnwrappedModel(
        CompanyIdentifier companyIdentifier) {
        if (companyIdentifier instanceof CompanyIdentifierImpl) {
            return companyIdentifier;
        }

        CompanyIdentifierImpl companyIdentifierImpl = new CompanyIdentifierImpl();

        companyIdentifierImpl.setNew(companyIdentifier.isNew());
        companyIdentifierImpl.setPrimaryKey(companyIdentifier.getPrimaryKey());

        companyIdentifierImpl.setEndDate(companyIdentifier.getEndDate());
        companyIdentifierImpl.setCompanyIdentifierSid(companyIdentifier.getCompanyIdentifierSid());
        companyIdentifierImpl.setModifiedDate(companyIdentifier.getModifiedDate());
        companyIdentifierImpl.setIdentifierStatus(companyIdentifier.getIdentifierStatus());
        companyIdentifierImpl.setEntityCode(companyIdentifier.getEntityCode());
        companyIdentifierImpl.setRecordLockStatus(companyIdentifier.isRecordLockStatus());
        companyIdentifierImpl.setStartDate(companyIdentifier.getStartDate());
        companyIdentifierImpl.setCreatedDate(companyIdentifier.getCreatedDate());
        companyIdentifierImpl.setSource(companyIdentifier.getSource());
        companyIdentifierImpl.setCreatedBy(companyIdentifier.getCreatedBy());
        companyIdentifierImpl.setCompanyIdentifierValue(companyIdentifier.getCompanyIdentifierValue());
        companyIdentifierImpl.setBatchId(companyIdentifier.getBatchId());
        companyIdentifierImpl.setCompanyQualifierSid(companyIdentifier.getCompanyQualifierSid());
        companyIdentifierImpl.setModifiedBy(companyIdentifier.getModifiedBy());
        companyIdentifierImpl.setInboundStatus(companyIdentifier.getInboundStatus());
        companyIdentifierImpl.setCompanyMasterSid(companyIdentifier.getCompanyMasterSid());

        return companyIdentifierImpl;
    }

    /**
     * Returns the company identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company identifier
     * @return the company identifier
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyIdentifierException, SystemException {
        CompanyIdentifier companyIdentifier = fetchByPrimaryKey(primaryKey);

        if (companyIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyIdentifier;
    }

    /**
     * Returns the company identifier with the primary key or throws a {@link com.stpl.app.NoSuchCompanyIdentifierException} if it could not be found.
     *
     * @param companyIdentifierSid the primary key of the company identifier
     * @return the company identifier
     * @throws com.stpl.app.NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier findByPrimaryKey(int companyIdentifierSid)
        throws NoSuchCompanyIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) companyIdentifierSid);
    }

    /**
     * Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company identifier
     * @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyIdentifier companyIdentifier = (CompanyIdentifier) EntityCacheUtil.getResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                CompanyIdentifierImpl.class, primaryKey);

        if (companyIdentifier == _nullCompanyIdentifier) {
            return null;
        }

        if (companyIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                companyIdentifier = (CompanyIdentifier) session.get(CompanyIdentifierImpl.class,
                        primaryKey);

                if (companyIdentifier != null) {
                    cacheResult(companyIdentifier);
                } else {
                    EntityCacheUtil.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyIdentifierImpl.class, primaryKey,
                        _nullCompanyIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyIdentifier;
    }

    /**
     * Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyIdentifierSid the primary key of the company identifier
     * @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyIdentifier fetchByPrimaryKey(int companyIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyIdentifierSid);
    }

    /**
     * Returns all the company identifiers.
     *
     * @return the company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<CompanyIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<CompanyIdentifier> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<CompanyIdentifier> list = (List<CompanyIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyIdentifier>(list);
                } else {
                    list = (List<CompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the company identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyIdentifier companyIdentifier : findAll()) {
            remove(companyIdentifier);
        }
    }

    /**
     * Returns the number of company identifiers.
     *
     * @return the number of company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_COMPANYIDENTIFIER);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the company identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyIdentifier>> listenersList = new ArrayList<ModelListener<CompanyIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
