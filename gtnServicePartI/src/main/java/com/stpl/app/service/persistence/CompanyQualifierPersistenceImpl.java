package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyQualifierException;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.impl.CompanyQualifierImpl;
import com.stpl.app.model.impl.CompanyQualifierModelImpl;
import com.stpl.app.service.persistence.CompanyQualifierPersistence;

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
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the company qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyQualifierPersistence
 * @see CompanyQualifierUtil
 * @generated
 */
public class CompanyQualifierPersistenceImpl extends BasePersistenceImpl<CompanyQualifier>
    implements CompanyQualifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyQualifierUtil} to access the company qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyQualifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyQualifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME =
        new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyCrtQualifierName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME =
        new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyQualifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyCrtQualifierName",
            new String[] { String.class.getName() },
            CompanyQualifierModelImpl.COMPANYQUALIFIERNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCompanyCrtQualifierName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1 =
        "companyQualifier.companyQualifierName IS NULL";
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2 =
        "companyQualifier.companyQualifierName = ?";
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3 =
        "(companyQualifier.companyQualifierName IS NULL OR companyQualifier.companyQualifierName = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME =
        new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
            CompanyQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByCompanyCrtQualifierByName",
            new String[] { String.class.getName() },
            CompanyQualifierModelImpl.COMPANYQUALIFIERNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME =
        new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCompanyCrtQualifierByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1 =
        "companyQualifier.companyQualifierName IS NULL";
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2 =
        "companyQualifier.companyQualifierName = ?";
    private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3 =
        "(companyQualifier.companyQualifierName IS NULL OR companyQualifier.companyQualifierName = '')";
    private static final String _SQL_SELECT_COMPANYQUALIFIER = "SELECT companyQualifier FROM CompanyQualifier companyQualifier";
    private static final String _SQL_SELECT_COMPANYQUALIFIER_WHERE = "SELECT companyQualifier FROM CompanyQualifier companyQualifier WHERE ";
    private static final String _SQL_COUNT_COMPANYQUALIFIER = "SELECT COUNT(companyQualifier) FROM CompanyQualifier companyQualifier";
    private static final String _SQL_COUNT_COMPANYQUALIFIER_WHERE = "SELECT COUNT(companyQualifier) FROM CompanyQualifier companyQualifier WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyQualifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyQualifier exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyQualifier exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyQualifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "recordLockStatus", "createdDate", "createdBy", "source",
                "companyQualifierValue", "batchId", "companyQualifierSid",
                "companyQualifierName", "effectiveDates", "notes", "modifiedBy",
                "inboundStatus", "modifiedDate"
            });
    private static CompanyQualifier _nullCompanyQualifier = new CompanyQualifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyQualifier> toCacheModel() {
                return _nullCompanyQualifierCacheModel;
            }
        };

    private static CacheModel<CompanyQualifier> _nullCompanyQualifierCacheModel = new CacheModel<CompanyQualifier>() {
            @Override
            public CompanyQualifier toEntityModel() {
                return _nullCompanyQualifier;
            }
        };

    public CompanyQualifierPersistenceImpl() {
        setModelClass(CompanyQualifier.class);
    }

    /**
     * Returns all the company qualifiers where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @return the matching company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findByCompanyCrtQualifierName(
        String companyQualifierName) throws SystemException {
        return findByCompanyCrtQualifierName(companyQualifierName,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company qualifiers where companyQualifierName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyQualifierName the company qualifier name
     * @param start the lower bound of the range of company qualifiers
     * @param end the upper bound of the range of company qualifiers (not inclusive)
     * @return the range of matching company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findByCompanyCrtQualifierName(
        String companyQualifierName, int start, int end)
        throws SystemException {
        return findByCompanyCrtQualifierName(companyQualifierName, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyQualifierName the company qualifier name
     * @param start the lower bound of the range of company qualifiers
     * @param end the upper bound of the range of company qualifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findByCompanyCrtQualifierName(
        String companyQualifierName, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME;
            finderArgs = new Object[] { companyQualifierName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME;
            finderArgs = new Object[] {
                    companyQualifierName,
                    
                    start, end, orderByComparator
                };
        }

        List<CompanyQualifier> list = (List<CompanyQualifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyQualifier companyQualifier : list) {
                if (!Validator.equals(companyQualifierName,
                            companyQualifier.getCompanyQualifierName())) {
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

            query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

            boolean bindCompanyQualifierName = false;

            if (companyQualifierName == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
            } else if (companyQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
            } else {
                bindCompanyQualifierName = true;

                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyQualifierModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyQualifierName) {
                    qPos.add(companyQualifierName);
                }

                if (!pagination) {
                    list = (List<CompanyQualifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyQualifier>(list);
                } else {
                    list = (List<CompanyQualifier>) QueryUtil.list(q,
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
     * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier findByCompanyCrtQualifierName_First(
        String companyQualifierName, OrderByComparator orderByComparator)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierName_First(companyQualifierName,
                orderByComparator);

        if (companyQualifier != null) {
            return companyQualifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyQualifierName=");
        msg.append(companyQualifierName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyQualifierException(msg.toString());
    }

    /**
     * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByCompanyCrtQualifierName_First(
        String companyQualifierName, OrderByComparator orderByComparator)
        throws SystemException {
        List<CompanyQualifier> list = findByCompanyCrtQualifierName(companyQualifierName,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier findByCompanyCrtQualifierName_Last(
        String companyQualifierName, OrderByComparator orderByComparator)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierName_Last(companyQualifierName,
                orderByComparator);

        if (companyQualifier != null) {
            return companyQualifier;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyQualifierName=");
        msg.append(companyQualifierName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyQualifierException(msg.toString());
    }

    /**
     * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByCompanyCrtQualifierName_Last(
        String companyQualifierName, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByCompanyCrtQualifierName(companyQualifierName);

        if (count == 0) {
            return null;
        }

        List<CompanyQualifier> list = findByCompanyCrtQualifierName(companyQualifierName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company qualifiers before and after the current company qualifier in the ordered set where companyQualifierName = &#63;.
     *
     * @param companyQualifierSid the primary key of the current company qualifier
     * @param companyQualifierName the company qualifier name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier[] findByCompanyCrtQualifierName_PrevAndNext(
        int companyQualifierSid, String companyQualifierName,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = findByPrimaryKey(companyQualifierSid);

        Session session = null;

        try {
            session = openSession();

            CompanyQualifier[] array = new CompanyQualifierImpl[3];

            array[0] = getByCompanyCrtQualifierName_PrevAndNext(session,
                    companyQualifier, companyQualifierName, orderByComparator,
                    true);

            array[1] = companyQualifier;

            array[2] = getByCompanyCrtQualifierName_PrevAndNext(session,
                    companyQualifier, companyQualifierName, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyQualifier getByCompanyCrtQualifierName_PrevAndNext(
        Session session, CompanyQualifier companyQualifier,
        String companyQualifierName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

        boolean bindCompanyQualifierName = false;

        if (companyQualifierName == null) {
            query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
        } else if (companyQualifierName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
        } else {
            bindCompanyQualifierName = true;

            query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
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
            query.append(CompanyQualifierModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyQualifierName) {
            qPos.add(companyQualifierName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyQualifier);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyQualifier> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company qualifiers where companyQualifierName = &#63; from the database.
     *
     * @param companyQualifierName the company qualifier name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyCrtQualifierName(String companyQualifierName)
        throws SystemException {
        for (CompanyQualifier companyQualifier : findByCompanyCrtQualifierName(
                companyQualifierName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyQualifier);
        }
    }

    /**
     * Returns the number of company qualifiers where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @return the number of matching company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyCrtQualifierName(String companyQualifierName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME;

        Object[] finderArgs = new Object[] { companyQualifierName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYQUALIFIER_WHERE);

            boolean bindCompanyQualifierName = false;

            if (companyQualifierName == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
            } else if (companyQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
            } else {
                bindCompanyQualifierName = true;

                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyQualifierName) {
                    qPos.add(companyQualifierName);
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
     * Returns the company qualifier where companyQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchCompanyQualifierException} if it could not be found.
     *
     * @param companyQualifierName the company qualifier name
     * @return the matching company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier findByCompanyCrtQualifierByName(
        String companyQualifierName)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierByName(companyQualifierName);

        if (companyQualifier == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("companyQualifierName=");
            msg.append(companyQualifierName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchCompanyQualifierException(msg.toString());
        }

        return companyQualifier;
    }

    /**
     * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param companyQualifierName the company qualifier name
     * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByCompanyCrtQualifierByName(
        String companyQualifierName) throws SystemException {
        return fetchByCompanyCrtQualifierByName(companyQualifierName, true);
    }

    /**
     * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param companyQualifierName the company qualifier name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByCompanyCrtQualifierByName(
        String companyQualifierName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { companyQualifierName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                    finderArgs, this);
        }

        if (result instanceof CompanyQualifier) {
            CompanyQualifier companyQualifier = (CompanyQualifier) result;

            if (!Validator.equals(companyQualifierName,
                        companyQualifier.getCompanyQualifierName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

            boolean bindCompanyQualifierName = false;

            if (companyQualifierName == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1);
            } else if (companyQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3);
            } else {
                bindCompanyQualifierName = true;

                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyQualifierName) {
                    qPos.add(companyQualifierName);
                }

                List<CompanyQualifier> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "CompanyQualifierPersistenceImpl.fetchByCompanyCrtQualifierByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    CompanyQualifier companyQualifier = list.get(0);

                    result = companyQualifier;

                    cacheResult(companyQualifier);

                    if ((companyQualifier.getCompanyQualifierName() == null) ||
                            !companyQualifier.getCompanyQualifierName()
                                                 .equals(companyQualifierName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                            finderArgs, companyQualifier);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (CompanyQualifier) result;
        }
    }

    /**
     * Removes the company qualifier where companyQualifierName = &#63; from the database.
     *
     * @param companyQualifierName the company qualifier name
     * @return the company qualifier that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier removeByCompanyCrtQualifierByName(
        String companyQualifierName)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = findByCompanyCrtQualifierByName(companyQualifierName);

        return remove(companyQualifier);
    }

    /**
     * Returns the number of company qualifiers where companyQualifierName = &#63;.
     *
     * @param companyQualifierName the company qualifier name
     * @return the number of matching company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyCrtQualifierByName(String companyQualifierName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME;

        Object[] finderArgs = new Object[] { companyQualifierName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYQUALIFIER_WHERE);

            boolean bindCompanyQualifierName = false;

            if (companyQualifierName == null) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1);
            } else if (companyQualifierName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3);
            } else {
                bindCompanyQualifierName = true;

                query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyQualifierName) {
                    qPos.add(companyQualifierName);
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
     * Caches the company qualifier in the entity cache if it is enabled.
     *
     * @param companyQualifier the company qualifier
     */
    @Override
    public void cacheResult(CompanyQualifier companyQualifier) {
        EntityCacheUtil.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierImpl.class, companyQualifier.getPrimaryKey(),
            companyQualifier);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
            new Object[] { companyQualifier.getCompanyQualifierName() },
            companyQualifier);

        companyQualifier.resetOriginalValues();
    }

    /**
     * Caches the company qualifiers in the entity cache if it is enabled.
     *
     * @param companyQualifiers the company qualifiers
     */
    @Override
    public void cacheResult(List<CompanyQualifier> companyQualifiers) {
        for (CompanyQualifier companyQualifier : companyQualifiers) {
            if (EntityCacheUtil.getResult(
                        CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyQualifierImpl.class,
                        companyQualifier.getPrimaryKey()) == null) {
                cacheResult(companyQualifier);
            } else {
                companyQualifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company qualifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyQualifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyQualifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company qualifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyQualifier companyQualifier) {
        EntityCacheUtil.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierImpl.class, companyQualifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(companyQualifier);
    }

    @Override
    public void clearCache(List<CompanyQualifier> companyQualifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyQualifier companyQualifier : companyQualifiers) {
            EntityCacheUtil.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
                CompanyQualifierImpl.class, companyQualifier.getPrimaryKey());

            clearUniqueFindersCache(companyQualifier);
        }
    }

    protected void cacheUniqueFindersCache(CompanyQualifier companyQualifier) {
        if (companyQualifier.isNew()) {
            Object[] args = new Object[] {
                    companyQualifier.getCompanyQualifierName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                args, companyQualifier);
        } else {
            CompanyQualifierModelImpl companyQualifierModelImpl = (CompanyQualifierModelImpl) companyQualifier;

            if ((companyQualifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyQualifier.getCompanyQualifierName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                    args, companyQualifier);
            }
        }
    }

    protected void clearUniqueFindersCache(CompanyQualifier companyQualifier) {
        CompanyQualifierModelImpl companyQualifierModelImpl = (CompanyQualifierModelImpl) companyQualifier;

        Object[] args = new Object[] { companyQualifier.getCompanyQualifierName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
            args);

        if ((companyQualifierModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    companyQualifierModelImpl.getOriginalCompanyQualifierName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
                args);
        }
    }

    /**
     * Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
     *
     * @param companyQualifierSid the primary key for the new company qualifier
     * @return the new company qualifier
     */
    @Override
    public CompanyQualifier create(int companyQualifierSid) {
        CompanyQualifier companyQualifier = new CompanyQualifierImpl();

        companyQualifier.setNew(true);
        companyQualifier.setPrimaryKey(companyQualifierSid);

        return companyQualifier;
    }

    /**
     * Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyQualifierSid the primary key of the company qualifier
     * @return the company qualifier that was removed
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier remove(int companyQualifierSid)
        throws NoSuchCompanyQualifierException, SystemException {
        return remove((Serializable) companyQualifierSid);
    }

    /**
     * Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company qualifier
     * @return the company qualifier that was removed
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier remove(Serializable primaryKey)
        throws NoSuchCompanyQualifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyQualifier companyQualifier = (CompanyQualifier) session.get(CompanyQualifierImpl.class,
                    primaryKey);

            if (companyQualifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyQualifier);
        } catch (NoSuchCompanyQualifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyQualifier removeImpl(CompanyQualifier companyQualifier)
        throws SystemException {
        companyQualifier = toUnwrappedModel(companyQualifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyQualifier)) {
                companyQualifier = (CompanyQualifier) session.get(CompanyQualifierImpl.class,
                        companyQualifier.getPrimaryKeyObj());
            }

            if (companyQualifier != null) {
                session.delete(companyQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyQualifier != null) {
            clearCache(companyQualifier);
        }

        return companyQualifier;
    }

    @Override
    public CompanyQualifier updateImpl(
        com.stpl.app.model.CompanyQualifier companyQualifier)
        throws SystemException {
        companyQualifier = toUnwrappedModel(companyQualifier);

        boolean isNew = companyQualifier.isNew();

        CompanyQualifierModelImpl companyQualifierModelImpl = (CompanyQualifierModelImpl) companyQualifier;

        Session session = null;

        try {
            session = openSession();

            if (companyQualifier.isNew()) {
                session.save(companyQualifier);

                companyQualifier.setNew(false);
            } else {
                session.merge(companyQualifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CompanyQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((companyQualifierModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyQualifierModelImpl.getOriginalCompanyQualifierName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME,
                    args);

                args = new Object[] {
                        companyQualifierModelImpl.getCompanyQualifierName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
            CompanyQualifierImpl.class, companyQualifier.getPrimaryKey(),
            companyQualifier);

        clearUniqueFindersCache(companyQualifier);
        cacheUniqueFindersCache(companyQualifier);

        return companyQualifier;
    }

    protected CompanyQualifier toUnwrappedModel(
        CompanyQualifier companyQualifier) {
        if (companyQualifier instanceof CompanyQualifierImpl) {
            return companyQualifier;
        }

        CompanyQualifierImpl companyQualifierImpl = new CompanyQualifierImpl();

        companyQualifierImpl.setNew(companyQualifier.isNew());
        companyQualifierImpl.setPrimaryKey(companyQualifier.getPrimaryKey());

        companyQualifierImpl.setRecordLockStatus(companyQualifier.isRecordLockStatus());
        companyQualifierImpl.setCreatedDate(companyQualifier.getCreatedDate());
        companyQualifierImpl.setCreatedBy(companyQualifier.getCreatedBy());
        companyQualifierImpl.setSource(companyQualifier.getSource());
        companyQualifierImpl.setCompanyQualifierValue(companyQualifier.getCompanyQualifierValue());
        companyQualifierImpl.setBatchId(companyQualifier.getBatchId());
        companyQualifierImpl.setCompanyQualifierSid(companyQualifier.getCompanyQualifierSid());
        companyQualifierImpl.setCompanyQualifierName(companyQualifier.getCompanyQualifierName());
        companyQualifierImpl.setEffectiveDates(companyQualifier.getEffectiveDates());
        companyQualifierImpl.setNotes(companyQualifier.getNotes());
        companyQualifierImpl.setModifiedBy(companyQualifier.getModifiedBy());
        companyQualifierImpl.setInboundStatus(companyQualifier.getInboundStatus());
        companyQualifierImpl.setModifiedDate(companyQualifier.getModifiedDate());

        return companyQualifierImpl;
    }

    /**
     * Returns the company qualifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company qualifier
     * @return the company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyQualifierException, SystemException {
        CompanyQualifier companyQualifier = fetchByPrimaryKey(primaryKey);

        if (companyQualifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyQualifier;
    }

    /**
     * Returns the company qualifier with the primary key or throws a {@link com.stpl.app.NoSuchCompanyQualifierException} if it could not be found.
     *
     * @param companyQualifierSid the primary key of the company qualifier
     * @return the company qualifier
     * @throws com.stpl.app.NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier findByPrimaryKey(int companyQualifierSid)
        throws NoSuchCompanyQualifierException, SystemException {
        return findByPrimaryKey((Serializable) companyQualifierSid);
    }

    /**
     * Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company qualifier
     * @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyQualifier companyQualifier = (CompanyQualifier) EntityCacheUtil.getResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
                CompanyQualifierImpl.class, primaryKey);

        if (companyQualifier == _nullCompanyQualifier) {
            return null;
        }

        if (companyQualifier == null) {
            Session session = null;

            try {
                session = openSession();

                companyQualifier = (CompanyQualifier) session.get(CompanyQualifierImpl.class,
                        primaryKey);

                if (companyQualifier != null) {
                    cacheResult(companyQualifier);
                } else {
                    EntityCacheUtil.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyQualifierImpl.class, primaryKey,
                        _nullCompanyQualifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyQualifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyQualifier;
    }

    /**
     * Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyQualifierSid the primary key of the company qualifier
     * @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyQualifier fetchByPrimaryKey(int companyQualifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyQualifierSid);
    }

    /**
     * Returns all the company qualifiers.
     *
     * @return the company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company qualifiers
     * @param end the upper bound of the range of company qualifiers (not inclusive)
     * @return the range of company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company qualifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company qualifiers
     * @param end the upper bound of the range of company qualifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company qualifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyQualifier> findAll(int start, int end,
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

        List<CompanyQualifier> list = (List<CompanyQualifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYQUALIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYQUALIFIER;

                if (pagination) {
                    sql = sql.concat(CompanyQualifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyQualifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyQualifier>(list);
                } else {
                    list = (List<CompanyQualifier>) QueryUtil.list(q,
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
     * Removes all the company qualifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyQualifier companyQualifier : findAll()) {
            remove(companyQualifier);
        }
    }

    /**
     * Returns the number of company qualifiers.
     *
     * @return the number of company qualifiers
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYQUALIFIER);

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
     * Initializes the company qualifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyQualifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyQualifier>> listenersList = new ArrayList<ModelListener<CompanyQualifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyQualifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyQualifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
