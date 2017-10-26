package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyMasterException;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.impl.CompanyMasterImpl;
import com.stpl.app.model.impl.CompanyMasterModelImpl;
import com.stpl.app.service.persistence.CompanyMasterPersistence;

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
 * The persistence implementation for the company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyMasterPersistence
 * @see CompanyMasterUtil
 * @generated
 */
public class CompanyMasterPersistenceImpl extends BasePersistenceImpl<CompanyMaster>
    implements CompanyMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyMasterUtil} to access the company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNO =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyNo", new String[] { String.class.getName() },
            CompanyMasterModelImpl.COMPANYNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYNO = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_1 = "companyMaster.companyNo IS NULL";
    private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_2 = "companyMaster.companyNo = ?";
    private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_3 = "(companyMaster.companyNo IS NULL OR companyMaster.companyNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyId", new String[] { String.class.getName() },
            CompanyMasterModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_1 = "companyMaster.companyId IS NULL";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "companyMaster.companyId = ?";
    private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_3 = "(companyMaster.companyId IS NULL OR companyMaster.companyId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNAME =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyName", new String[] { String.class.getName() },
            CompanyMasterModelImpl.COMPANYNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYNAME = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1 = "companyMaster.companyName IS NULL";
    private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2 = "companyMaster.companyName = ?";
    private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3 = "(companyMaster.companyName IS NULL OR companyMaster.companyName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYTYPE =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyType",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyType", new String[] { Integer.class.getName() },
            CompanyMasterModelImpl.COMPANYTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYTYPE = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyType",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2 = "companyMaster.companyType = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSTATUS =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyStatus",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyStatus", new String[] { Integer.class.getName() },
            CompanyMasterModelImpl.COMPANYSTATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSTATUS = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyStatus",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2 = "companyMaster.companyStatus = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYUNIQUE =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByCompanyUnique",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE =
        new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyUnique", new String[] { String.class.getName() },
            CompanyMasterModelImpl.COMPANYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYUNIQUE = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyUnique",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_1 = "companyMaster.companyId IS NULL";
    private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_2 = "companyMaster.companyId = ?";
    private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_3 = "(companyMaster.companyId IS NULL OR companyMaster.companyId = '')";
    private static final String _SQL_SELECT_COMPANYMASTER = "SELECT companyMaster FROM CompanyMaster companyMaster";
    private static final String _SQL_SELECT_COMPANYMASTER_WHERE = "SELECT companyMaster FROM CompanyMaster companyMaster WHERE ";
    private static final String _SQL_COUNT_COMPANYMASTER = "SELECT COUNT(companyMaster) FROM CompanyMaster companyMaster";
    private static final String _SQL_COUNT_COMPANYMASTER_WHERE = "SELECT COUNT(companyMaster) FROM CompanyMaster companyMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "state", "financialSystem", "companyGroup", "companyName",
                "companyCategory", "lastUpdatedDate", "modifiedDate", "lives",
                "address2", "createdDate", "createdBy", "source", "address1",
                "modifiedBy", "inboundStatus", "companyMasterSid", "zipCode",
                "companyId", "country", "internalNotes", "orgKey", "companyType",
                "recordLockStatus", "companyStartDate", "companyNo", "batchId",
                "companyStatus", "companyEndDate", "city", "regionCode"
            });
    private static CompanyMaster _nullCompanyMaster = new CompanyMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyMaster> toCacheModel() {
                return _nullCompanyMasterCacheModel;
            }
        };

    private static CacheModel<CompanyMaster> _nullCompanyMasterCacheModel = new CacheModel<CompanyMaster>() {
            @Override
            public CompanyMaster toEntityModel() {
                return _nullCompanyMaster;
            }
        };

    public CompanyMasterPersistenceImpl() {
        setModelClass(CompanyMaster.class);
    }

    /**
     * Returns all the company masters where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyNo(String companyNo)
        throws SystemException {
        return findByCompanyNo(companyNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the company masters where companyNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyNo the company no
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyNo(String companyNo, int start,
        int end) throws SystemException {
        return findByCompanyNo(companyNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyNo the company no
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyNo(String companyNo, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO;
            finderArgs = new Object[] { companyNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNO;
            finderArgs = new Object[] { companyNo, start, end, orderByComparator };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if (!Validator.equals(companyNo, companyMaster.getCompanyNo())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            boolean bindCompanyNo = false;

            if (companyNo == null) {
                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
            } else if (companyNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
            } else {
                bindCompanyNo = true;

                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyNo) {
                    qPos.add(companyNo);
                }

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyNo_First(String companyNo,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyNo_First(companyNo,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyNo=");
        msg.append(companyNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyNo_First(String companyNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyNo(companyNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyNo_Last(String companyNo,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyNo_Last(companyNo,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyNo=");
        msg.append(companyNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyNo_Last(String companyNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyNo(companyNo);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyNo(companyNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyNo the company no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyNo_PrevAndNext(int companyMasterSid,
        String companyNo, OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyNo_PrevAndNext(session, companyMaster,
                    companyNo, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyNo_PrevAndNext(session, companyMaster,
                    companyNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyNo_PrevAndNext(Session session,
        CompanyMaster companyMaster, String companyNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        boolean bindCompanyNo = false;

        if (companyNo == null) {
            query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
        } else if (companyNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
        } else {
            bindCompanyNo = true;

            query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyNo) {
            qPos.add(companyNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyNo = &#63; from the database.
     *
     * @param companyNo the company no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyNo(String companyNo) throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyNo(companyNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyNo = &#63;.
     *
     * @param companyNo the company no
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyNo(String companyNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYNO;

        Object[] finderArgs = new Object[] { companyNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            boolean bindCompanyNo = false;

            if (companyNo == null) {
                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
            } else if (companyNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
            } else {
                bindCompanyNo = true;

                query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyNo) {
                    qPos.add(companyNo);
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
     * Returns all the company masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyId(String companyId)
        throws SystemException {
        return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the company masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyId(String companyId, int start,
        int end) throws SystemException {
        return findByCompanyId(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyId(String companyId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
            finderArgs = new Object[] { companyId, start, end, orderByComparator };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if (!Validator.equals(companyId, companyMaster.getCompanyId())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
                }

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyId_First(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyId_First(companyId,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyId_First(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyId(companyId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyId_Last(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyId_Last(companyId,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyId_Last(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyId(companyId);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyId(companyId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyId_PrevAndNext(int companyMasterSid,
        String companyId, OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyId_PrevAndNext(session, companyMaster,
                    companyId, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyId_PrevAndNext(session, companyMaster,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyId_PrevAndNext(Session session,
        CompanyMaster companyMaster, String companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        boolean bindCompanyId = false;

        if (companyId == null) {
            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
        } else if (companyId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
        } else {
            bindCompanyId = true;

            query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyId) {
            qPos.add(companyId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyId(String companyId) throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyId(companyId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyId(String companyId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
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
     * Returns all the company masters where companyName = &#63;.
     *
     * @param companyName the company name
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyName(String companyName)
        throws SystemException {
        return findByCompanyName(companyName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company masters where companyName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyName the company name
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyName(String companyName, int start,
        int end) throws SystemException {
        return findByCompanyName(companyName, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyName = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyName the company name
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyName(String companyName, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME;
            finderArgs = new Object[] { companyName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNAME;
            finderArgs = new Object[] { companyName, start, end, orderByComparator };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if (!Validator.equals(companyName,
                            companyMaster.getCompanyName())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            boolean bindCompanyName = false;

            if (companyName == null) {
                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
            } else if (companyName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
            } else {
                bindCompanyName = true;

                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyName) {
                    qPos.add(companyName);
                }

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyName = &#63;.
     *
     * @param companyName the company name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyName_First(String companyName,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyName_First(companyName,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyName=");
        msg.append(companyName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyName = &#63;.
     *
     * @param companyName the company name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyName_First(String companyName,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyName(companyName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyName = &#63;.
     *
     * @param companyName the company name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyName_Last(String companyName,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyName_Last(companyName,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyName=");
        msg.append(companyName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyName = &#63;.
     *
     * @param companyName the company name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyName_Last(String companyName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyName(companyName);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyName(companyName, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyName the company name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyName_PrevAndNext(int companyMasterSid,
        String companyName, OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyName_PrevAndNext(session, companyMaster,
                    companyName, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyName_PrevAndNext(session, companyMaster,
                    companyName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyName_PrevAndNext(Session session,
        CompanyMaster companyMaster, String companyName,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        boolean bindCompanyName = false;

        if (companyName == null) {
            query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
        } else if (companyName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
        } else {
            bindCompanyName = true;

            query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyName) {
            qPos.add(companyName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyName = &#63; from the database.
     *
     * @param companyName the company name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyName(String companyName)
        throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyName(companyName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyName = &#63;.
     *
     * @param companyName the company name
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyName(String companyName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYNAME;

        Object[] finderArgs = new Object[] { companyName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            boolean bindCompanyName = false;

            if (companyName == null) {
                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
            } else if (companyName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
            } else {
                bindCompanyName = true;

                query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyName) {
                    qPos.add(companyName);
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
     * Returns all the company masters where companyType = &#63;.
     *
     * @param companyType the company type
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyType(int companyType)
        throws SystemException {
        return findByCompanyType(companyType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company masters where companyType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyType the company type
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyType(int companyType, int start,
        int end) throws SystemException {
        return findByCompanyType(companyType, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyType the company type
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyType(int companyType, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE;
            finderArgs = new Object[] { companyType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYTYPE;
            finderArgs = new Object[] { companyType, start, end, orderByComparator };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if ((companyType != companyMaster.getCompanyType())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyType);

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyType = &#63;.
     *
     * @param companyType the company type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyType_First(int companyType,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyType_First(companyType,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyType=");
        msg.append(companyType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyType = &#63;.
     *
     * @param companyType the company type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyType_First(int companyType,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyType(companyType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyType = &#63;.
     *
     * @param companyType the company type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyType_Last(int companyType,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyType_Last(companyType,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyType=");
        msg.append(companyType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyType = &#63;.
     *
     * @param companyType the company type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyType_Last(int companyType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyType(companyType);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyType(companyType, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyType the company type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyType_PrevAndNext(int companyMasterSid,
        int companyType, OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyType_PrevAndNext(session, companyMaster,
                    companyType, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyType_PrevAndNext(session, companyMaster,
                    companyType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyType_PrevAndNext(Session session,
        CompanyMaster companyMaster, int companyType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyType);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyType = &#63; from the database.
     *
     * @param companyType the company type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyType(int companyType) throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyType(companyType,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyType = &#63;.
     *
     * @param companyType the company type
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyType(int companyType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYTYPE;

        Object[] finderArgs = new Object[] { companyType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyType);

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
     * Returns all the company masters where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyStatus(int companyStatus)
        throws SystemException {
        return findByCompanyStatus(companyStatus, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company masters where companyStatus = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyStatus the company status
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyStatus(int companyStatus,
        int start, int end) throws SystemException {
        return findByCompanyStatus(companyStatus, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyStatus = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyStatus the company status
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyStatus(int companyStatus,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS;
            finderArgs = new Object[] { companyStatus };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSTATUS;
            finderArgs = new Object[] {
                    companyStatus,
                    
                    start, end, orderByComparator
                };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if ((companyStatus != companyMaster.getCompanyStatus())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyStatus);

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyStatus_First(int companyStatus,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyStatus_First(companyStatus,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyStatus=");
        msg.append(companyStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyStatus_First(int companyStatus,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyStatus(companyStatus, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyStatus_Last(int companyStatus,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyStatus_Last(companyStatus,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyStatus=");
        msg.append(companyStatus);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyStatus_Last(int companyStatus,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyStatus(companyStatus);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyStatus(companyStatus,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyStatus the company status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyStatus_PrevAndNext(
        int companyMasterSid, int companyStatus,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyStatus_PrevAndNext(session, companyMaster,
                    companyStatus, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyStatus_PrevAndNext(session, companyMaster,
                    companyStatus, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyStatus_PrevAndNext(Session session,
        CompanyMaster companyMaster, int companyStatus,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyStatus);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyStatus = &#63; from the database.
     *
     * @param companyStatus the company status
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyStatus(int companyStatus)
        throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyStatus(companyStatus,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyStatus = &#63;.
     *
     * @param companyStatus the company status
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyStatus(int companyStatus)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSTATUS;

        Object[] finderArgs = new Object[] { companyStatus };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(companyStatus);

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
     * Returns all the company masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyUnique(String companyId)
        throws SystemException {
        return findByCompanyUnique(companyId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyUnique(String companyId, int start,
        int end) throws SystemException {
        return findByCompanyUnique(companyId, start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters where companyId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyId the company ID
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findByCompanyUnique(String companyId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE;
            finderArgs = new Object[] { companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYUNIQUE;
            finderArgs = new Object[] { companyId, start, end, orderByComparator };
        }

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (CompanyMaster companyMaster : list) {
                if (!Validator.equals(companyId, companyMaster.getCompanyId())) {
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

            query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
                }

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Returns the first company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyUnique_First(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyUnique_First(companyId,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the first company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyUnique_First(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        List<CompanyMaster> list = findByCompanyUnique(companyId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByCompanyUnique_Last(String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByCompanyUnique_Last(companyId,
                orderByComparator);

        if (companyMaster != null) {
            return companyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchCompanyMasterException(msg.toString());
    }

    /**
     * Returns the last company master in the ordered set where companyId = &#63;.
     *
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching company master, or <code>null</code> if a matching company master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByCompanyUnique_Last(String companyId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyUnique(companyId);

        if (count == 0) {
            return null;
        }

        List<CompanyMaster> list = findByCompanyUnique(companyId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
     *
     * @param companyMasterSid the primary key of the current company master
     * @param companyId the company ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster[] findByCompanyUnique_PrevAndNext(
        int companyMasterSid, String companyId,
        OrderByComparator orderByComparator)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

        Session session = null;

        try {
            session = openSession();

            CompanyMaster[] array = new CompanyMasterImpl[3];

            array[0] = getByCompanyUnique_PrevAndNext(session, companyMaster,
                    companyId, orderByComparator, true);

            array[1] = companyMaster;

            array[2] = getByCompanyUnique_PrevAndNext(session, companyMaster,
                    companyId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected CompanyMaster getByCompanyUnique_PrevAndNext(Session session,
        CompanyMaster companyMaster, String companyId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

        boolean bindCompanyId = false;

        if (companyId == null) {
            query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_1);
        } else if (companyId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_3);
        } else {
            bindCompanyId = true;

            query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_2);
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
            query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyId) {
            qPos.add(companyId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<CompanyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the company masters where companyId = &#63; from the database.
     *
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyUnique(String companyId)
        throws SystemException {
        for (CompanyMaster companyMaster : findByCompanyUnique(companyId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters where companyId = &#63;.
     *
     * @param companyId the company ID
     * @return the number of matching company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyUnique(String companyId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYUNIQUE;

        Object[] finderArgs = new Object[] { companyId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

            boolean bindCompanyId = false;

            if (companyId == null) {
                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_1);
            } else if (companyId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_3);
            } else {
                bindCompanyId = true;

                query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyId) {
                    qPos.add(companyId);
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
     * Caches the company master in the entity cache if it is enabled.
     *
     * @param companyMaster the company master
     */
    @Override
    public void cacheResult(CompanyMaster companyMaster) {
        EntityCacheUtil.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterImpl.class, companyMaster.getPrimaryKey(),
            companyMaster);

        companyMaster.resetOriginalValues();
    }

    /**
     * Caches the company masters in the entity cache if it is enabled.
     *
     * @param companyMasters the company masters
     */
    @Override
    public void cacheResult(List<CompanyMaster> companyMasters) {
        for (CompanyMaster companyMaster : companyMasters) {
            if (EntityCacheUtil.getResult(
                        CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyMasterImpl.class, companyMaster.getPrimaryKey()) == null) {
                cacheResult(companyMaster);
            } else {
                companyMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyMaster companyMaster) {
        EntityCacheUtil.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterImpl.class, companyMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyMaster> companyMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyMaster companyMaster : companyMasters) {
            EntityCacheUtil.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                CompanyMasterImpl.class, companyMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new company master with the primary key. Does not add the company master to the database.
     *
     * @param companyMasterSid the primary key for the new company master
     * @return the new company master
     */
    @Override
    public CompanyMaster create(int companyMasterSid) {
        CompanyMaster companyMaster = new CompanyMasterImpl();

        companyMaster.setNew(true);
        companyMaster.setPrimaryKey(companyMasterSid);

        return companyMaster;
    }

    /**
     * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyMasterSid the primary key of the company master
     * @return the company master that was removed
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster remove(int companyMasterSid)
        throws NoSuchCompanyMasterException, SystemException {
        return remove((Serializable) companyMasterSid);
    }

    /**
     * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company master
     * @return the company master that was removed
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster remove(Serializable primaryKey)
        throws NoSuchCompanyMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyMaster companyMaster = (CompanyMaster) session.get(CompanyMasterImpl.class,
                    primaryKey);

            if (companyMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyMaster);
        } catch (NoSuchCompanyMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyMaster removeImpl(CompanyMaster companyMaster)
        throws SystemException {
        companyMaster = toUnwrappedModel(companyMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyMaster)) {
                companyMaster = (CompanyMaster) session.get(CompanyMasterImpl.class,
                        companyMaster.getPrimaryKeyObj());
            }

            if (companyMaster != null) {
                session.delete(companyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyMaster != null) {
            clearCache(companyMaster);
        }

        return companyMaster;
    }

    @Override
    public CompanyMaster updateImpl(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws SystemException {
        companyMaster = toUnwrappedModel(companyMaster);

        boolean isNew = companyMaster.isNew();

        CompanyMasterModelImpl companyMasterModelImpl = (CompanyMasterModelImpl) companyMaster;

        Session session = null;

        try {
            session = openSession();

            if (companyMaster.isNew()) {
                session.save(companyMaster);

                companyMaster.setNew(false);
            } else {
                session.merge(companyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !CompanyMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYNO,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO,
                    args);
            }

            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
                    args);
            }

            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME,
                    args);
            }

            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE,
                    args);
            }

            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyStatus()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyStatus() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS,
                    args);
            }

            if ((companyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        companyMasterModelImpl.getOriginalCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE,
                    args);

                args = new Object[] { companyMasterModelImpl.getCompanyId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            CompanyMasterImpl.class, companyMaster.getPrimaryKey(),
            companyMaster);

        return companyMaster;
    }

    protected CompanyMaster toUnwrappedModel(CompanyMaster companyMaster) {
        if (companyMaster instanceof CompanyMasterImpl) {
            return companyMaster;
        }

        CompanyMasterImpl companyMasterImpl = new CompanyMasterImpl();

        companyMasterImpl.setNew(companyMaster.isNew());
        companyMasterImpl.setPrimaryKey(companyMaster.getPrimaryKey());

        companyMasterImpl.setState(companyMaster.getState());
        companyMasterImpl.setFinancialSystem(companyMaster.getFinancialSystem());
        companyMasterImpl.setCompanyGroup(companyMaster.getCompanyGroup());
        companyMasterImpl.setCompanyName(companyMaster.getCompanyName());
        companyMasterImpl.setCompanyCategory(companyMaster.getCompanyCategory());
        companyMasterImpl.setLastUpdatedDate(companyMaster.getLastUpdatedDate());
        companyMasterImpl.setModifiedDate(companyMaster.getModifiedDate());
        companyMasterImpl.setLives(companyMaster.getLives());
        companyMasterImpl.setAddress2(companyMaster.getAddress2());
        companyMasterImpl.setCreatedDate(companyMaster.getCreatedDate());
        companyMasterImpl.setCreatedBy(companyMaster.getCreatedBy());
        companyMasterImpl.setSource(companyMaster.getSource());
        companyMasterImpl.setAddress1(companyMaster.getAddress1());
        companyMasterImpl.setModifiedBy(companyMaster.getModifiedBy());
        companyMasterImpl.setInboundStatus(companyMaster.getInboundStatus());
        companyMasterImpl.setCompanyMasterSid(companyMaster.getCompanyMasterSid());
        companyMasterImpl.setZipCode(companyMaster.getZipCode());
        companyMasterImpl.setCompanyId(companyMaster.getCompanyId());
        companyMasterImpl.setCountry(companyMaster.getCountry());
        companyMasterImpl.setInternalNotes(companyMaster.getInternalNotes());
        companyMasterImpl.setOrgKey(companyMaster.getOrgKey());
        companyMasterImpl.setCompanyType(companyMaster.getCompanyType());
        companyMasterImpl.setRecordLockStatus(companyMaster.isRecordLockStatus());
        companyMasterImpl.setCompanyStartDate(companyMaster.getCompanyStartDate());
        companyMasterImpl.setCompanyNo(companyMaster.getCompanyNo());
        companyMasterImpl.setBatchId(companyMaster.getBatchId());
        companyMasterImpl.setCompanyStatus(companyMaster.getCompanyStatus());
        companyMasterImpl.setCompanyEndDate(companyMaster.getCompanyEndDate());
        companyMasterImpl.setCity(companyMaster.getCity());
        companyMasterImpl.setRegionCode(companyMaster.getRegionCode());

        return companyMasterImpl;
    }

    /**
     * Returns the company master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company master
     * @return the company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyMasterException, SystemException {
        CompanyMaster companyMaster = fetchByPrimaryKey(primaryKey);

        if (companyMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyMaster;
    }

    /**
     * Returns the company master with the primary key or throws a {@link com.stpl.app.NoSuchCompanyMasterException} if it could not be found.
     *
     * @param companyMasterSid the primary key of the company master
     * @return the company master
     * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster findByPrimaryKey(int companyMasterSid)
        throws NoSuchCompanyMasterException, SystemException {
        return findByPrimaryKey((Serializable) companyMasterSid);
    }

    /**
     * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company master
     * @return the company master, or <code>null</code> if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyMaster companyMaster = (CompanyMaster) EntityCacheUtil.getResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                CompanyMasterImpl.class, primaryKey);

        if (companyMaster == _nullCompanyMaster) {
            return null;
        }

        if (companyMaster == null) {
            Session session = null;

            try {
                session = openSession();

                companyMaster = (CompanyMaster) session.get(CompanyMasterImpl.class,
                        primaryKey);

                if (companyMaster != null) {
                    cacheResult(companyMaster);
                } else {
                    EntityCacheUtil.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyMasterImpl.class, primaryKey, _nullCompanyMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyMaster;
    }

    /**
     * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyMasterSid the primary key of the company master
     * @return the company master, or <code>null</code> if a company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyMaster fetchByPrimaryKey(int companyMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyMasterSid);
    }

    /**
     * Returns all the company masters.
     *
     * @return the company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @return the range of company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company masters
     * @param end the upper bound of the range of company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyMaster> findAll(int start, int end,
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

        List<CompanyMaster> list = (List<CompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYMASTER;

                if (pagination) {
                    sql = sql.concat(CompanyMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyMaster>(list);
                } else {
                    list = (List<CompanyMaster>) QueryUtil.list(q,
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
     * Removes all the company masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyMaster companyMaster : findAll()) {
            remove(companyMaster);
        }
    }

    /**
     * Returns the number of company masters.
     *
     * @return the number of company masters
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYMASTER);

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
     * Initializes the company master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyMaster>> listenersList = new ArrayList<ModelListener<CompanyMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
