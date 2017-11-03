package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHelperTableException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.impl.HelperTableImpl;
import com.stpl.app.model.impl.HelperTableModelImpl;
import com.stpl.app.service.persistence.HelperTablePersistence;

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
 * The persistence implementation for the helper table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HelperTablePersistence
 * @see HelperTableUtil
 * @generated
 */
public class HelperTablePersistenceImpl extends BasePersistenceImpl<HelperTable>
    implements HelperTablePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HelperTableUtil} to access the helper table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HelperTableImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HELPERTABLEDETAILS =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByHelperTableDetails",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByHelperTableDetails",
            new String[] { String.class.getName() },
            HelperTableModelImpl.LISTNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByHelperTableDetails", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1 = "helperTable.listName IS NULL";
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2 = "helperTable.listName = ?";
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3 = "(helperTable.listName IS NULL OR helperTable.listName = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_ENTITY,
            "fetchByHelperTableDetailsByHelperTableSid",
            new String[] { Integer.class.getName() },
            HelperTableModelImpl.HELPERTABLESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByHelperTableDetailsByHelperTableSid",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2 =
        "helperTable.helperTableSid = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByHelperTableDetailsByDesc",
            new String[] { String.class.getName() },
            HelperTableModelImpl.DESCRIPTION_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByHelperTableDetailsByDesc",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1 =
        "helperTable.description IS NULL";
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2 =
        "helperTable.description = ?";
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3 =
        "(helperTable.description IS NULL OR helperTable.description = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByHelperTableDetailsByCode",
            new String[] { Integer.class.getName() },
            HelperTableModelImpl.HELPERTABLESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE =
        new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByHelperTableDetailsByCode",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2 =
        "helperTable.helperTableSid = ?";
    private static final String _SQL_SELECT_HELPERTABLE = "SELECT helperTable FROM HelperTable helperTable";
    private static final String _SQL_SELECT_HELPERTABLE_WHERE = "SELECT helperTable FROM HelperTable helperTable WHERE ";
    private static final String _SQL_COUNT_HELPERTABLE = "SELECT COUNT(helperTable) FROM HelperTable helperTable";
    private static final String _SQL_COUNT_HELPERTABLE_WHERE = "SELECT COUNT(helperTable) FROM HelperTable helperTable WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "helperTable.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HelperTable exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HelperTable exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HelperTablePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "description", "refCount",
                "listName", "helperTableSid", "modifiedBy", "modifiedDate",
                "aliasName"
            });
    private static HelperTable _nullHelperTable = new HelperTableImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HelperTable> toCacheModel() {
                return _nullHelperTableCacheModel;
            }
        };

    private static CacheModel<HelperTable> _nullHelperTableCacheModel = new CacheModel<HelperTable>() {
            @Override
            public HelperTable toEntityModel() {
                return _nullHelperTable;
            }
        };

    public HelperTablePersistenceImpl() {
        setModelClass(HelperTable.class);
    }

    /**
     * Returns all the helper tables where listName = &#63;.
     *
     * @param listName the list name
     * @return the matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findByHelperTableDetails(String listName)
        throws SystemException {
        return findByHelperTableDetails(listName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the helper tables where listName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param listName the list name
     * @param start the lower bound of the range of helper tables
     * @param end the upper bound of the range of helper tables (not inclusive)
     * @return the range of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findByHelperTableDetails(String listName,
        int start, int end) throws SystemException {
        return findByHelperTableDetails(listName, start, end, null);
    }

    /**
     * Returns an ordered range of all the helper tables where listName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param listName the list name
     * @param start the lower bound of the range of helper tables
     * @param end the upper bound of the range of helper tables (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findByHelperTableDetails(String listName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS;
            finderArgs = new Object[] { listName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HELPERTABLEDETAILS;
            finderArgs = new Object[] { listName, start, end, orderByComparator };
        }

        List<HelperTable> list = (List<HelperTable>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (HelperTable helperTable : list) {
                if (!Validator.equals(listName, helperTable.getListName())) {
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

            query.append(_SQL_SELECT_HELPERTABLE_WHERE);

            boolean bindListName = false;

            if (listName == null) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
            } else if (listName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
            } else {
                bindListName = true;

                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(HelperTableModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindListName) {
                    qPos.add(listName);
                }

                if (!pagination) {
                    list = (List<HelperTable>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HelperTable>(list);
                } else {
                    list = (List<HelperTable>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first helper table in the ordered set where listName = &#63;.
     *
     * @param listName the list name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByHelperTableDetails_First(String listName,
        OrderByComparator orderByComparator)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByHelperTableDetails_First(listName,
                orderByComparator);

        if (helperTable != null) {
            return helperTable;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("listName=");
        msg.append(listName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchHelperTableException(msg.toString());
    }

    /**
     * Returns the first helper table in the ordered set where listName = &#63;.
     *
     * @param listName the list name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetails_First(String listName,
        OrderByComparator orderByComparator) throws SystemException {
        List<HelperTable> list = findByHelperTableDetails(listName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last helper table in the ordered set where listName = &#63;.
     *
     * @param listName the list name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByHelperTableDetails_Last(String listName,
        OrderByComparator orderByComparator)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByHelperTableDetails_Last(listName,
                orderByComparator);

        if (helperTable != null) {
            return helperTable;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("listName=");
        msg.append(listName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchHelperTableException(msg.toString());
    }

    /**
     * Returns the last helper table in the ordered set where listName = &#63;.
     *
     * @param listName the list name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetails_Last(String listName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByHelperTableDetails(listName);

        if (count == 0) {
            return null;
        }

        List<HelperTable> list = findByHelperTableDetails(listName, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the helper tables before and after the current helper table in the ordered set where listName = &#63;.
     *
     * @param helperTableSid the primary key of the current helper table
     * @param listName the list name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable[] findByHelperTableDetails_PrevAndNext(
        int helperTableSid, String listName, OrderByComparator orderByComparator)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = findByPrimaryKey(helperTableSid);

        Session session = null;

        try {
            session = openSession();

            HelperTable[] array = new HelperTableImpl[3];

            array[0] = getByHelperTableDetails_PrevAndNext(session,
                    helperTable, listName, orderByComparator, true);

            array[1] = helperTable;

            array[2] = getByHelperTableDetails_PrevAndNext(session,
                    helperTable, listName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected HelperTable getByHelperTableDetails_PrevAndNext(Session session,
        HelperTable helperTable, String listName,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_HELPERTABLE_WHERE);

        boolean bindListName = false;

        if (listName == null) {
            query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
        } else if (listName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
        } else {
            bindListName = true;

            query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
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
            query.append(HelperTableModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindListName) {
            qPos.add(listName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(helperTable);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<HelperTable> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the helper tables where listName = &#63; from the database.
     *
     * @param listName the list name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByHelperTableDetails(String listName)
        throws SystemException {
        for (HelperTable helperTable : findByHelperTableDetails(listName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(helperTable);
        }
    }

    /**
     * Returns the number of helper tables where listName = &#63;.
     *
     * @param listName the list name
     * @return the number of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByHelperTableDetails(String listName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS;

        Object[] finderArgs = new Object[] { listName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_HELPERTABLE_WHERE);

            boolean bindListName = false;

            if (listName == null) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
            } else if (listName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
            } else {
                bindListName = true;

                query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindListName) {
                    qPos.add(listName);
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
     * Returns the helper table where helperTableSid = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
     *
     * @param helperTableSid the helper table sid
     * @return the matching helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByHelperTableDetailsByHelperTableSid(
        int helperTableSid) throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByHelperTableDetailsByHelperTableSid(helperTableSid);

        if (helperTable == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("helperTableSid=");
            msg.append(helperTableSid);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchHelperTableException(msg.toString());
        }

        return helperTable;
    }

    /**
     * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param helperTableSid the helper table sid
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByHelperTableSid(
        int helperTableSid) throws SystemException {
        return fetchByHelperTableDetailsByHelperTableSid(helperTableSid, true);
    }

    /**
     * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param helperTableSid the helper table sid
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByHelperTableSid(
        int helperTableSid, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { helperTableSid };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                    finderArgs, this);
        }

        if (result instanceof HelperTable) {
            HelperTable helperTable = (HelperTable) result;

            if ((helperTableSid != helperTable.getHelperTableSid())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_HELPERTABLE_WHERE);

            query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(helperTableSid);

                List<HelperTable> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "HelperTablePersistenceImpl.fetchByHelperTableDetailsByHelperTableSid(int, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    HelperTable helperTable = list.get(0);

                    result = helperTable;

                    cacheResult(helperTable);

                    if ((helperTable.getHelperTableSid() != helperTableSid)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                            finderArgs, helperTable);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (HelperTable) result;
        }
    }

    /**
     * Removes the helper table where helperTableSid = &#63; from the database.
     *
     * @param helperTableSid the helper table sid
     * @return the helper table that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable removeByHelperTableDetailsByHelperTableSid(
        int helperTableSid) throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = findByHelperTableDetailsByHelperTableSid(helperTableSid);

        return remove(helperTable);
    }

    /**
     * Returns the number of helper tables where helperTableSid = &#63;.
     *
     * @param helperTableSid the helper table sid
     * @return the number of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByHelperTableDetailsByHelperTableSid(int helperTableSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID;

        Object[] finderArgs = new Object[] { helperTableSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_HELPERTABLE_WHERE);

            query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(helperTableSid);

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
     * Returns the helper table where description = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
     *
     * @param description the description
     * @return the matching helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByHelperTableDetailsByDesc(String description)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByHelperTableDetailsByDesc(description);

        if (helperTable == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("description=");
            msg.append(description);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchHelperTableException(msg.toString());
        }

        return helperTable;
    }

    /**
     * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param description the description
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByDesc(String description)
        throws SystemException {
        return fetchByHelperTableDetailsByDesc(description, true);
    }

    /**
     * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param description the description
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByDesc(String description,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { description };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                    finderArgs, this);
        }

        if (result instanceof HelperTable) {
            HelperTable helperTable = (HelperTable) result;

            if (!Validator.equals(description, helperTable.getDescription())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_HELPERTABLE_WHERE);

            boolean bindDescription = false;

            if (description == null) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1);
            } else if (description.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3);
            } else {
                bindDescription = true;

                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDescription) {
                    qPos.add(description);
                }

                List<HelperTable> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "HelperTablePersistenceImpl.fetchByHelperTableDetailsByDesc(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    HelperTable helperTable = list.get(0);

                    result = helperTable;

                    cacheResult(helperTable);

                    if ((helperTable.getDescription() == null) ||
                            !helperTable.getDescription().equals(description)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                            finderArgs, helperTable);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (HelperTable) result;
        }
    }

    /**
     * Removes the helper table where description = &#63; from the database.
     *
     * @param description the description
     * @return the helper table that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable removeByHelperTableDetailsByDesc(String description)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = findByHelperTableDetailsByDesc(description);

        return remove(helperTable);
    }

    /**
     * Returns the number of helper tables where description = &#63;.
     *
     * @param description the description
     * @return the number of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByHelperTableDetailsByDesc(String description)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC;

        Object[] finderArgs = new Object[] { description };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_HELPERTABLE_WHERE);

            boolean bindDescription = false;

            if (description == null) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1);
            } else if (description.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3);
            } else {
                bindDescription = true;

                query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDescription) {
                    qPos.add(description);
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
     * Returns the helper table where helperTableSid = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
     *
     * @param helperTableSid the helper table sid
     * @return the matching helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByHelperTableDetailsByCode(int helperTableSid)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByHelperTableDetailsByCode(helperTableSid);

        if (helperTable == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("helperTableSid=");
            msg.append(helperTableSid);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchHelperTableException(msg.toString());
        }

        return helperTable;
    }

    /**
     * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param helperTableSid the helper table sid
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid)
        throws SystemException {
        return fetchByHelperTableDetailsByCode(helperTableSid, true);
    }

    /**
     * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param helperTableSid the helper table sid
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { helperTableSid };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                    finderArgs, this);
        }

        if (result instanceof HelperTable) {
            HelperTable helperTable = (HelperTable) result;

            if ((helperTableSid != helperTable.getHelperTableSid())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_HELPERTABLE_WHERE);

            query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(helperTableSid);

                List<HelperTable> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "HelperTablePersistenceImpl.fetchByHelperTableDetailsByCode(int, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    HelperTable helperTable = list.get(0);

                    result = helperTable;

                    cacheResult(helperTable);

                    if ((helperTable.getHelperTableSid() != helperTableSid)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                            finderArgs, helperTable);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (HelperTable) result;
        }
    }

    /**
     * Removes the helper table where helperTableSid = &#63; from the database.
     *
     * @param helperTableSid the helper table sid
     * @return the helper table that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable removeByHelperTableDetailsByCode(int helperTableSid)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = findByHelperTableDetailsByCode(helperTableSid);

        return remove(helperTable);
    }

    /**
     * Returns the number of helper tables where helperTableSid = &#63;.
     *
     * @param helperTableSid the helper table sid
     * @return the number of matching helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByHelperTableDetailsByCode(int helperTableSid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE;

        Object[] finderArgs = new Object[] { helperTableSid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_HELPERTABLE_WHERE);

            query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(helperTableSid);

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
     * Caches the helper table in the entity cache if it is enabled.
     *
     * @param helperTable the helper table
     */
    @Override
    public void cacheResult(HelperTable helperTable) {
        EntityCacheUtil.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableImpl.class, helperTable.getPrimaryKey(), helperTable);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
            new Object[] { helperTable.getHelperTableSid() }, helperTable);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
            new Object[] { helperTable.getDescription() }, helperTable);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
            new Object[] { helperTable.getHelperTableSid() }, helperTable);

        helperTable.resetOriginalValues();
    }

    /**
     * Caches the helper tables in the entity cache if it is enabled.
     *
     * @param helperTables the helper tables
     */
    @Override
    public void cacheResult(List<HelperTable> helperTables) {
        for (HelperTable helperTable : helperTables) {
            if (EntityCacheUtil.getResult(
                        HelperTableModelImpl.ENTITY_CACHE_ENABLED,
                        HelperTableImpl.class, helperTable.getPrimaryKey()) == null) {
                cacheResult(helperTable);
            } else {
                helperTable.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all helper tables.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HelperTableImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HelperTableImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the helper table.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HelperTable helperTable) {
        EntityCacheUtil.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableImpl.class, helperTable.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(helperTable);
    }

    @Override
    public void clearCache(List<HelperTable> helperTables) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HelperTable helperTable : helperTables) {
            EntityCacheUtil.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
                HelperTableImpl.class, helperTable.getPrimaryKey());

            clearUniqueFindersCache(helperTable);
        }
    }

    protected void cacheUniqueFindersCache(HelperTable helperTable) {
        if (helperTable.isNew()) {
            Object[] args = new Object[] { helperTable.getHelperTableSid() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                args, helperTable);

            args = new Object[] { helperTable.getDescription() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                args, helperTable);

            args = new Object[] { helperTable.getHelperTableSid() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                args, helperTable);
        } else {
            HelperTableModelImpl helperTableModelImpl = (HelperTableModelImpl) helperTable;

            if ((helperTableModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { helperTable.getHelperTableSid() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                    args, helperTable);
            }

            if ((helperTableModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { helperTable.getDescription() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                    args, helperTable);
            }

            if ((helperTableModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { helperTable.getHelperTableSid() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                    args, helperTable);
            }
        }
    }

    protected void clearUniqueFindersCache(HelperTable helperTable) {
        HelperTableModelImpl helperTableModelImpl = (HelperTableModelImpl) helperTable;

        Object[] args = new Object[] { helperTable.getHelperTableSid() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
            args);

        if ((helperTableModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID.getColumnBitmask()) != 0) {
            args = new Object[] { helperTableModelImpl.getOriginalHelperTableSid() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
                args);
        }

        args = new Object[] { helperTable.getDescription() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
            args);

        if ((helperTableModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC.getColumnBitmask()) != 0) {
            args = new Object[] { helperTableModelImpl.getOriginalDescription() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
                args);
        }

        args = new Object[] { helperTable.getHelperTableSid() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
            args);

        if ((helperTableModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE.getColumnBitmask()) != 0) {
            args = new Object[] { helperTableModelImpl.getOriginalHelperTableSid() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
                args);
        }
    }

    /**
     * Creates a new helper table with the primary key. Does not add the helper table to the database.
     *
     * @param helperTableSid the primary key for the new helper table
     * @return the new helper table
     */
    @Override
    public HelperTable create(int helperTableSid) {
        HelperTable helperTable = new HelperTableImpl();

        helperTable.setNew(true);
        helperTable.setPrimaryKey(helperTableSid);

        return helperTable;
    }

    /**
     * Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param helperTableSid the primary key of the helper table
     * @return the helper table that was removed
     * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable remove(int helperTableSid)
        throws NoSuchHelperTableException, SystemException {
        return remove((Serializable) helperTableSid);
    }

    /**
     * Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the helper table
     * @return the helper table that was removed
     * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable remove(Serializable primaryKey)
        throws NoSuchHelperTableException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HelperTable helperTable = (HelperTable) session.get(HelperTableImpl.class,
                    primaryKey);

            if (helperTable == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHelperTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(helperTable);
        } catch (NoSuchHelperTableException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HelperTable removeImpl(HelperTable helperTable)
        throws SystemException {
        helperTable = toUnwrappedModel(helperTable);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(helperTable)) {
                helperTable = (HelperTable) session.get(HelperTableImpl.class,
                        helperTable.getPrimaryKeyObj());
            }

            if (helperTable != null) {
                session.delete(helperTable);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (helperTable != null) {
            clearCache(helperTable);
        }

        return helperTable;
    }

    @Override
    public HelperTable updateImpl(com.stpl.app.model.HelperTable helperTable)
        throws SystemException {
        helperTable = toUnwrappedModel(helperTable);

        boolean isNew = helperTable.isNew();

        HelperTableModelImpl helperTableModelImpl = (HelperTableModelImpl) helperTable;

        Session session = null;

        try {
            session = openSession();

            if (helperTable.isNew()) {
                session.save(helperTable);

                helperTable.setNew(false);
            } else {
                session.merge(helperTable);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !HelperTableModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((helperTableModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        helperTableModelImpl.getOriginalListName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS,
                    args);

                args = new Object[] { helperTableModelImpl.getListName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS,
                    args);
            }
        }

        EntityCacheUtil.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
            HelperTableImpl.class, helperTable.getPrimaryKey(), helperTable);

        clearUniqueFindersCache(helperTable);
        cacheUniqueFindersCache(helperTable);

        return helperTable;
    }

    protected HelperTable toUnwrappedModel(HelperTable helperTable) {
        if (helperTable instanceof HelperTableImpl) {
            return helperTable;
        }

        HelperTableImpl helperTableImpl = new HelperTableImpl();

        helperTableImpl.setNew(helperTable.isNew());
        helperTableImpl.setPrimaryKey(helperTable.getPrimaryKey());

        helperTableImpl.setCreatedDate(helperTable.getCreatedDate());
        helperTableImpl.setCreatedBy(helperTable.getCreatedBy());
        helperTableImpl.setDescription(helperTable.getDescription());
        helperTableImpl.setRefCount(helperTable.getRefCount());
        helperTableImpl.setListName(helperTable.getListName());
        helperTableImpl.setHelperTableSid(helperTable.getHelperTableSid());
        helperTableImpl.setModifiedBy(helperTable.getModifiedBy());
        helperTableImpl.setModifiedDate(helperTable.getModifiedDate());
        helperTableImpl.setAliasName(helperTable.getAliasName());

        return helperTableImpl;
    }

    /**
     * Returns the helper table with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the helper table
     * @return the helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHelperTableException, SystemException {
        HelperTable helperTable = fetchByPrimaryKey(primaryKey);

        if (helperTable == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHelperTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return helperTable;
    }

    /**
     * Returns the helper table with the primary key or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
     *
     * @param helperTableSid the primary key of the helper table
     * @return the helper table
     * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable findByPrimaryKey(int helperTableSid)
        throws NoSuchHelperTableException, SystemException {
        return findByPrimaryKey((Serializable) helperTableSid);
    }

    /**
     * Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the helper table
     * @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HelperTable helperTable = (HelperTable) EntityCacheUtil.getResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
                HelperTableImpl.class, primaryKey);

        if (helperTable == _nullHelperTable) {
            return null;
        }

        if (helperTable == null) {
            Session session = null;

            try {
                session = openSession();

                helperTable = (HelperTable) session.get(HelperTableImpl.class,
                        primaryKey);

                if (helperTable != null) {
                    cacheResult(helperTable);
                } else {
                    EntityCacheUtil.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
                        HelperTableImpl.class, primaryKey, _nullHelperTable);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
                    HelperTableImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return helperTable;
    }

    /**
     * Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param helperTableSid the primary key of the helper table
     * @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HelperTable fetchByPrimaryKey(int helperTableSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) helperTableSid);
    }

    /**
     * Returns all the helper tables.
     *
     * @return the helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the helper tables.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of helper tables
     * @param end the upper bound of the range of helper tables (not inclusive)
     * @return the range of helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the helper tables.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of helper tables
     * @param end the upper bound of the range of helper tables (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of helper tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HelperTable> findAll(int start, int end,
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

        List<HelperTable> list = (List<HelperTable>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HELPERTABLE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HELPERTABLE;

                if (pagination) {
                    sql = sql.concat(HelperTableModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HelperTable>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HelperTable>(list);
                } else {
                    list = (List<HelperTable>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the helper tables from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HelperTable helperTable : findAll()) {
            remove(helperTable);
        }
    }

    /**
     * Returns the number of helper tables.
     *
     * @return the number of helper tables
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

                Query q = session.createQuery(_SQL_COUNT_HELPERTABLE);

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
     * Initializes the helper table persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HelperTable")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HelperTable>> listenersList = new ArrayList<ModelListener<HelperTable>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HelperTable>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HelperTableImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
