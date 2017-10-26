package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchLotMasterException;
import com.stpl.app.model.LotMaster;
import com.stpl.app.model.impl.LotMasterImpl;
import com.stpl.app.model.impl.LotMasterModelImpl;
import com.stpl.app.service.persistence.LotMasterPersistence;

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
 * The persistence implementation for the lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see LotMasterPersistence
 * @see LotMasterUtil
 * @generated
 */
public class LotMasterPersistenceImpl extends BasePersistenceImpl<LotMaster>
    implements LotMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link LotMasterUtil} to access the lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = LotMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
            new String[] { String.class.getName() },
            LotMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "lotMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "lotMaster.itemId = ?";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(lotMaster.itemId IS NULL OR lotMaster.itemId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTNO = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLotNo",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTNO = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLotNo",
            new String[] { String.class.getName() },
            LotMasterModelImpl.LOTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LOTNO = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLotNo",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_LOTNO_LOTNO_1 = "lotMaster.lotNo IS NULL";
    private static final String _FINDER_COLUMN_LOTNO_LOTNO_2 = "lotMaster.lotNo = ?";
    private static final String _FINDER_COLUMN_LOTNO_LOTNO_3 = "(lotMaster.lotNo IS NULL OR lotMaster.lotNo = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTEXPIRATIONDATE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLotExpirationDate",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTEXPIRATIONDATE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByLotExpirationDate", new String[] { Date.class.getName() },
            LotMasterModelImpl.LOTEXPIRATIONDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LOTEXPIRATIONDATE = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByLotExpirationDate", new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_1 =
        "lotMaster.lotExpirationDate IS NULL";
    private static final String _FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_2 =
        "lotMaster.lotExpirationDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTLOTSOLDDATE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLastLotSoldDate",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTLOTSOLDDATE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLastLotSoldDate",
            new String[] { Date.class.getName() },
            LotMasterModelImpl.LASTLOTSOLDDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LASTLOTSOLDDATE = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByLastLotSoldDate", new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_1 =
        "lotMaster.lastLotSoldDate IS NULL";
    private static final String _FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_2 =
        "lotMaster.lastLotSoldDate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTUNIQUE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLotUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTUNIQUE =
        new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, LotMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLotUnique",
            new String[] { String.class.getName(), String.class.getName() },
            LotMasterModelImpl.ITEMID_COLUMN_BITMASK |
            LotMasterModelImpl.LOTNO_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LOTUNIQUE = new FinderPath(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLotUnique",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_LOTUNIQUE_ITEMID_1 = "lotMaster.itemId IS NULL AND ";
    private static final String _FINDER_COLUMN_LOTUNIQUE_ITEMID_2 = "lotMaster.itemId = ? AND ";
    private static final String _FINDER_COLUMN_LOTUNIQUE_ITEMID_3 = "(lotMaster.itemId IS NULL OR lotMaster.itemId = '') AND ";
    private static final String _FINDER_COLUMN_LOTUNIQUE_LOTNO_1 = "lotMaster.lotNo IS NULL";
    private static final String _FINDER_COLUMN_LOTUNIQUE_LOTNO_2 = "lotMaster.lotNo = ?";
    private static final String _FINDER_COLUMN_LOTUNIQUE_LOTNO_3 = "(lotMaster.lotNo IS NULL OR lotMaster.lotNo = '')";
    private static final String _SQL_SELECT_LOTMASTER = "SELECT lotMaster FROM LotMaster lotMaster";
    private static final String _SQL_SELECT_LOTMASTER_WHERE = "SELECT lotMaster FROM LotMaster lotMaster WHERE ";
    private static final String _SQL_COUNT_LOTMASTER = "SELECT COUNT(lotMaster) FROM LotMaster lotMaster";
    private static final String _SQL_COUNT_LOTMASTER_WHERE = "SELECT COUNT(lotMaster) FROM LotMaster lotMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "lotMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LotMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LotMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(LotMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "createdDate", "itemId", "batchId",
                "modifiedDate", "recordLockStatus", "lastLotSoldDate",
                "lotExpirationDate", "source", "lotMasterSid", "lotNo",
                "inboundStatus"
            });
    private static LotMaster _nullLotMaster = new LotMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<LotMaster> toCacheModel() {
                return _nullLotMasterCacheModel;
            }
        };

    private static CacheModel<LotMaster> _nullLotMasterCacheModel = new CacheModel<LotMaster>() {
            @Override
            public LotMaster toEntityModel() {
                return _nullLotMaster;
            }
        };

    public LotMasterPersistenceImpl() {
        setModelClass(LotMaster.class);
    }

    /**
     * Returns all the lot masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByItemId(String itemId)
        throws SystemException {
        return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByItemId(String itemId, int start, int end)
        throws SystemException {
        return findByItemId(itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByItemId(String itemId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID;
            finderArgs = new Object[] { itemId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID;
            finderArgs = new Object[] { itemId, start, end, orderByComparator };
        }

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LotMaster lotMaster : list) {
                if (!Validator.equals(itemId, lotMaster.getItemId())) {
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

            query.append(_SQL_SELECT_LOTMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LotMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first lot master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByItemId_First(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByItemId_First(itemId, orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the first lot master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByItemId_First(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        List<LotMaster> list = findByItemId(itemId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last lot master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByItemId_Last(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByItemId_Last(itemId, orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the last lot master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByItemId_Last(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemId(itemId);

        if (count == 0) {
            return null;
        }

        List<LotMaster> list = findByItemId(itemId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63;.
     *
     * @param lotMasterSid the primary key of the current lot master
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster[] findByItemId_PrevAndNext(int lotMasterSid,
        String itemId, OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = findByPrimaryKey(lotMasterSid);

        Session session = null;

        try {
            session = openSession();

            LotMaster[] array = new LotMasterImpl[3];

            array[0] = getByItemId_PrevAndNext(session, lotMaster, itemId,
                    orderByComparator, true);

            array[1] = lotMaster;

            array[2] = getByItemId_PrevAndNext(session, lotMaster, itemId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LotMaster getByItemId_PrevAndNext(Session session,
        LotMaster lotMaster, String itemId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LOTMASTER_WHERE);

        boolean bindItemId = false;

        if (itemId == null) {
            query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
        } else if (itemId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
        } else {
            bindItemId = true;

            query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
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
            query.append(LotMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemId) {
            qPos.add(itemId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lotMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LotMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the lot masters where itemId = &#63; from the database.
     *
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemId(String itemId) throws SystemException {
        for (LotMaster lotMaster : findByItemId(itemId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the number of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemId(String itemId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMID;

        Object[] finderArgs = new Object[] { itemId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LOTMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
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
     * Returns all the lot masters where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @return the matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotNo(String lotNo) throws SystemException {
        return findByLotNo(lotNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters where lotNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lotNo the lot no
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotNo(String lotNo, int start, int end)
        throws SystemException {
        return findByLotNo(lotNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters where lotNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lotNo the lot no
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotNo(String lotNo, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTNO;
            finderArgs = new Object[] { lotNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTNO;
            finderArgs = new Object[] { lotNo, start, end, orderByComparator };
        }

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LotMaster lotMaster : list) {
                if (!Validator.equals(lotNo, lotMaster.getLotNo())) {
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

            query.append(_SQL_SELECT_LOTMASTER_WHERE);

            boolean bindLotNo = false;

            if (lotNo == null) {
                query.append(_FINDER_COLUMN_LOTNO_LOTNO_1);
            } else if (lotNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTNO_LOTNO_3);
            } else {
                bindLotNo = true;

                query.append(_FINDER_COLUMN_LOTNO_LOTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LotMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLotNo) {
                    qPos.add(lotNo);
                }

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first lot master in the ordered set where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotNo_First(String lotNo,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotNo_First(lotNo, orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lotNo=");
        msg.append(lotNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the first lot master in the ordered set where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotNo_First(String lotNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<LotMaster> list = findByLotNo(lotNo, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last lot master in the ordered set where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotNo_Last(String lotNo,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotNo_Last(lotNo, orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lotNo=");
        msg.append(lotNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the last lot master in the ordered set where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotNo_Last(String lotNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLotNo(lotNo);

        if (count == 0) {
            return null;
        }

        List<LotMaster> list = findByLotNo(lotNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the lot masters before and after the current lot master in the ordered set where lotNo = &#63;.
     *
     * @param lotMasterSid the primary key of the current lot master
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster[] findByLotNo_PrevAndNext(int lotMasterSid, String lotNo,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = findByPrimaryKey(lotMasterSid);

        Session session = null;

        try {
            session = openSession();

            LotMaster[] array = new LotMasterImpl[3];

            array[0] = getByLotNo_PrevAndNext(session, lotMaster, lotNo,
                    orderByComparator, true);

            array[1] = lotMaster;

            array[2] = getByLotNo_PrevAndNext(session, lotMaster, lotNo,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LotMaster getByLotNo_PrevAndNext(Session session,
        LotMaster lotMaster, String lotNo, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LOTMASTER_WHERE);

        boolean bindLotNo = false;

        if (lotNo == null) {
            query.append(_FINDER_COLUMN_LOTNO_LOTNO_1);
        } else if (lotNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LOTNO_LOTNO_3);
        } else {
            bindLotNo = true;

            query.append(_FINDER_COLUMN_LOTNO_LOTNO_2);
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
            query.append(LotMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLotNo) {
            qPos.add(lotNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lotMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LotMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the lot masters where lotNo = &#63; from the database.
     *
     * @param lotNo the lot no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLotNo(String lotNo) throws SystemException {
        for (LotMaster lotMaster : findByLotNo(lotNo, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters where lotNo = &#63;.
     *
     * @param lotNo the lot no
     * @return the number of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLotNo(String lotNo) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LOTNO;

        Object[] finderArgs = new Object[] { lotNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LOTMASTER_WHERE);

            boolean bindLotNo = false;

            if (lotNo == null) {
                query.append(_FINDER_COLUMN_LOTNO_LOTNO_1);
            } else if (lotNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTNO_LOTNO_3);
            } else {
                bindLotNo = true;

                query.append(_FINDER_COLUMN_LOTNO_LOTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLotNo) {
                    qPos.add(lotNo);
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
     * Returns all the lot masters where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @return the matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotExpirationDate(Date lotExpirationDate)
        throws SystemException {
        return findByLotExpirationDate(lotExpirationDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters where lotExpirationDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lotExpirationDate the lot expiration date
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotExpirationDate(Date lotExpirationDate,
        int start, int end) throws SystemException {
        return findByLotExpirationDate(lotExpirationDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lotExpirationDate the lot expiration date
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotExpirationDate(Date lotExpirationDate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTEXPIRATIONDATE;
            finderArgs = new Object[] { lotExpirationDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTEXPIRATIONDATE;
            finderArgs = new Object[] {
                    lotExpirationDate,
                    
                    start, end, orderByComparator
                };
        }

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LotMaster lotMaster : list) {
                if (!Validator.equals(lotExpirationDate,
                            lotMaster.getLotExpirationDate())) {
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

            query.append(_SQL_SELECT_LOTMASTER_WHERE);

            boolean bindLotExpirationDate = false;

            if (lotExpirationDate == null) {
                query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_1);
            } else {
                bindLotExpirationDate = true;

                query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LotMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLotExpirationDate) {
                    qPos.add(CalendarUtil.getTimestamp(lotExpirationDate));
                }

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotExpirationDate_First(Date lotExpirationDate,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotExpirationDate_First(lotExpirationDate,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lotExpirationDate=");
        msg.append(lotExpirationDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotExpirationDate_First(Date lotExpirationDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<LotMaster> list = findByLotExpirationDate(lotExpirationDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotExpirationDate_Last(Date lotExpirationDate,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotExpirationDate_Last(lotExpirationDate,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lotExpirationDate=");
        msg.append(lotExpirationDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotExpirationDate_Last(Date lotExpirationDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLotExpirationDate(lotExpirationDate);

        if (count == 0) {
            return null;
        }

        List<LotMaster> list = findByLotExpirationDate(lotExpirationDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the lot masters before and after the current lot master in the ordered set where lotExpirationDate = &#63;.
     *
     * @param lotMasterSid the primary key of the current lot master
     * @param lotExpirationDate the lot expiration date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster[] findByLotExpirationDate_PrevAndNext(int lotMasterSid,
        Date lotExpirationDate, OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = findByPrimaryKey(lotMasterSid);

        Session session = null;

        try {
            session = openSession();

            LotMaster[] array = new LotMasterImpl[3];

            array[0] = getByLotExpirationDate_PrevAndNext(session, lotMaster,
                    lotExpirationDate, orderByComparator, true);

            array[1] = lotMaster;

            array[2] = getByLotExpirationDate_PrevAndNext(session, lotMaster,
                    lotExpirationDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LotMaster getByLotExpirationDate_PrevAndNext(Session session,
        LotMaster lotMaster, Date lotExpirationDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LOTMASTER_WHERE);

        boolean bindLotExpirationDate = false;

        if (lotExpirationDate == null) {
            query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_1);
        } else {
            bindLotExpirationDate = true;

            query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_2);
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
            query.append(LotMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLotExpirationDate) {
            qPos.add(CalendarUtil.getTimestamp(lotExpirationDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lotMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LotMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the lot masters where lotExpirationDate = &#63; from the database.
     *
     * @param lotExpirationDate the lot expiration date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLotExpirationDate(Date lotExpirationDate)
        throws SystemException {
        for (LotMaster lotMaster : findByLotExpirationDate(lotExpirationDate,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters where lotExpirationDate = &#63;.
     *
     * @param lotExpirationDate the lot expiration date
     * @return the number of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLotExpirationDate(Date lotExpirationDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LOTEXPIRATIONDATE;

        Object[] finderArgs = new Object[] { lotExpirationDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LOTMASTER_WHERE);

            boolean bindLotExpirationDate = false;

            if (lotExpirationDate == null) {
                query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_1);
            } else {
                bindLotExpirationDate = true;

                query.append(_FINDER_COLUMN_LOTEXPIRATIONDATE_LOTEXPIRATIONDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLotExpirationDate) {
                    qPos.add(CalendarUtil.getTimestamp(lotExpirationDate));
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
     * Returns all the lot masters where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @return the matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate)
        throws SystemException {
        return findByLastLotSoldDate(lastLotSoldDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters where lastLotSoldDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lastLotSoldDate the last lot sold date
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate,
        int start, int end) throws SystemException {
        return findByLastLotSoldDate(lastLotSoldDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param lastLotSoldDate the last lot sold date
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTLOTSOLDDATE;
            finderArgs = new Object[] { lastLotSoldDate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTLOTSOLDDATE;
            finderArgs = new Object[] {
                    lastLotSoldDate,
                    
                    start, end, orderByComparator
                };
        }

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LotMaster lotMaster : list) {
                if (!Validator.equals(lastLotSoldDate,
                            lotMaster.getLastLotSoldDate())) {
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

            query.append(_SQL_SELECT_LOTMASTER_WHERE);

            boolean bindLastLotSoldDate = false;

            if (lastLotSoldDate == null) {
                query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_1);
            } else {
                bindLastLotSoldDate = true;

                query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LotMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLastLotSoldDate) {
                    qPos.add(CalendarUtil.getTimestamp(lastLotSoldDate));
                }

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLastLotSoldDate_First(Date lastLotSoldDate,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLastLotSoldDate_First(lastLotSoldDate,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lastLotSoldDate=");
        msg.append(lastLotSoldDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLastLotSoldDate_First(Date lastLotSoldDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<LotMaster> list = findByLastLotSoldDate(lastLotSoldDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLastLotSoldDate_Last(Date lastLotSoldDate,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLastLotSoldDate_Last(lastLotSoldDate,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("lastLotSoldDate=");
        msg.append(lastLotSoldDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLastLotSoldDate_Last(Date lastLotSoldDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLastLotSoldDate(lastLotSoldDate);

        if (count == 0) {
            return null;
        }

        List<LotMaster> list = findByLastLotSoldDate(lastLotSoldDate,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the lot masters before and after the current lot master in the ordered set where lastLotSoldDate = &#63;.
     *
     * @param lotMasterSid the primary key of the current lot master
     * @param lastLotSoldDate the last lot sold date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster[] findByLastLotSoldDate_PrevAndNext(int lotMasterSid,
        Date lastLotSoldDate, OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = findByPrimaryKey(lotMasterSid);

        Session session = null;

        try {
            session = openSession();

            LotMaster[] array = new LotMasterImpl[3];

            array[0] = getByLastLotSoldDate_PrevAndNext(session, lotMaster,
                    lastLotSoldDate, orderByComparator, true);

            array[1] = lotMaster;

            array[2] = getByLastLotSoldDate_PrevAndNext(session, lotMaster,
                    lastLotSoldDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LotMaster getByLastLotSoldDate_PrevAndNext(Session session,
        LotMaster lotMaster, Date lastLotSoldDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LOTMASTER_WHERE);

        boolean bindLastLotSoldDate = false;

        if (lastLotSoldDate == null) {
            query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_1);
        } else {
            bindLastLotSoldDate = true;

            query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_2);
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
            query.append(LotMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLastLotSoldDate) {
            qPos.add(CalendarUtil.getTimestamp(lastLotSoldDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lotMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LotMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the lot masters where lastLotSoldDate = &#63; from the database.
     *
     * @param lastLotSoldDate the last lot sold date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLastLotSoldDate(Date lastLotSoldDate)
        throws SystemException {
        for (LotMaster lotMaster : findByLastLotSoldDate(lastLotSoldDate,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters where lastLotSoldDate = &#63;.
     *
     * @param lastLotSoldDate the last lot sold date
     * @return the number of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLastLotSoldDate(Date lastLotSoldDate)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LASTLOTSOLDDATE;

        Object[] finderArgs = new Object[] { lastLotSoldDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LOTMASTER_WHERE);

            boolean bindLastLotSoldDate = false;

            if (lastLotSoldDate == null) {
                query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_1);
            } else {
                bindLastLotSoldDate = true;

                query.append(_FINDER_COLUMN_LASTLOTSOLDDATE_LASTLOTSOLDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLastLotSoldDate) {
                    qPos.add(CalendarUtil.getTimestamp(lastLotSoldDate));
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
     * Returns all the lot masters where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @return the matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotUnique(String itemId, String lotNo)
        throws SystemException {
        return findByLotUnique(itemId, lotNo, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters where itemId = &#63; and lotNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotUnique(String itemId, String lotNo,
        int start, int end) throws SystemException {
        return findByLotUnique(itemId, lotNo, start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findByLotUnique(String itemId, String lotNo,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTUNIQUE;
            finderArgs = new Object[] { itemId, lotNo };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LOTUNIQUE;
            finderArgs = new Object[] {
                    itemId, lotNo,
                    
                    start, end, orderByComparator
                };
        }

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LotMaster lotMaster : list) {
                if (!Validator.equals(itemId, lotMaster.getItemId()) ||
                        !Validator.equals(lotNo, lotMaster.getLotNo())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_LOTMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_2);
            }

            boolean bindLotNo = false;

            if (lotNo == null) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_1);
            } else if (lotNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_3);
            } else {
                bindLotNo = true;

                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LotMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (bindLotNo) {
                    qPos.add(lotNo);
                }

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotUnique_First(String itemId, String lotNo,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotUnique_First(itemId, lotNo,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(", lotNo=");
        msg.append(lotNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotUnique_First(String itemId, String lotNo,
        OrderByComparator orderByComparator) throws SystemException {
        List<LotMaster> list = findByLotUnique(itemId, lotNo, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByLotUnique_Last(String itemId, String lotNo,
        OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByLotUnique_Last(itemId, lotNo,
                orderByComparator);

        if (lotMaster != null) {
            return lotMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(", lotNo=");
        msg.append(lotNo);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLotMasterException(msg.toString());
    }

    /**
     * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByLotUnique_Last(String itemId, String lotNo,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLotUnique(itemId, lotNo);

        if (count == 0) {
            return null;
        }

        List<LotMaster> list = findByLotUnique(itemId, lotNo, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
     *
     * @param lotMasterSid the primary key of the current lot master
     * @param itemId the item ID
     * @param lotNo the lot no
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster[] findByLotUnique_PrevAndNext(int lotMasterSid,
        String itemId, String lotNo, OrderByComparator orderByComparator)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = findByPrimaryKey(lotMasterSid);

        Session session = null;

        try {
            session = openSession();

            LotMaster[] array = new LotMasterImpl[3];

            array[0] = getByLotUnique_PrevAndNext(session, lotMaster, itemId,
                    lotNo, orderByComparator, true);

            array[1] = lotMaster;

            array[2] = getByLotUnique_PrevAndNext(session, lotMaster, itemId,
                    lotNo, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LotMaster getByLotUnique_PrevAndNext(Session session,
        LotMaster lotMaster, String itemId, String lotNo,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LOTMASTER_WHERE);

        boolean bindItemId = false;

        if (itemId == null) {
            query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_1);
        } else if (itemId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_3);
        } else {
            bindItemId = true;

            query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_2);
        }

        boolean bindLotNo = false;

        if (lotNo == null) {
            query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_1);
        } else if (lotNo.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_3);
        } else {
            bindLotNo = true;

            query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_2);
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
            query.append(LotMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemId) {
            qPos.add(itemId);
        }

        if (bindLotNo) {
            qPos.add(lotNo);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lotMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LotMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the lot masters where itemId = &#63; and lotNo = &#63; from the database.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLotUnique(String itemId, String lotNo)
        throws SystemException {
        for (LotMaster lotMaster : findByLotUnique(itemId, lotNo,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters where itemId = &#63; and lotNo = &#63;.
     *
     * @param itemId the item ID
     * @param lotNo the lot no
     * @return the number of matching lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLotUnique(String itemId, String lotNo)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LOTUNIQUE;

        Object[] finderArgs = new Object[] { itemId, lotNo };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_LOTMASTER_WHERE);

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_LOTUNIQUE_ITEMID_2);
            }

            boolean bindLotNo = false;

            if (lotNo == null) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_1);
            } else if (lotNo.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_3);
            } else {
                bindLotNo = true;

                query.append(_FINDER_COLUMN_LOTUNIQUE_LOTNO_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (bindLotNo) {
                    qPos.add(lotNo);
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
     * Caches the lot master in the entity cache if it is enabled.
     *
     * @param lotMaster the lot master
     */
    @Override
    public void cacheResult(LotMaster lotMaster) {
        EntityCacheUtil.putResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterImpl.class, lotMaster.getPrimaryKey(), lotMaster);

        lotMaster.resetOriginalValues();
    }

    /**
     * Caches the lot masters in the entity cache if it is enabled.
     *
     * @param lotMasters the lot masters
     */
    @Override
    public void cacheResult(List<LotMaster> lotMasters) {
        for (LotMaster lotMaster : lotMasters) {
            if (EntityCacheUtil.getResult(
                        LotMasterModelImpl.ENTITY_CACHE_ENABLED,
                        LotMasterImpl.class, lotMaster.getPrimaryKey()) == null) {
                cacheResult(lotMaster);
            } else {
                lotMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all lot masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(LotMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(LotMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the lot master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(LotMaster lotMaster) {
        EntityCacheUtil.removeResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterImpl.class, lotMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<LotMaster> lotMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (LotMaster lotMaster : lotMasters) {
            EntityCacheUtil.removeResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
                LotMasterImpl.class, lotMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new lot master with the primary key. Does not add the lot master to the database.
     *
     * @param lotMasterSid the primary key for the new lot master
     * @return the new lot master
     */
    @Override
    public LotMaster create(int lotMasterSid) {
        LotMaster lotMaster = new LotMasterImpl();

        lotMaster.setNew(true);
        lotMaster.setPrimaryKey(lotMasterSid);

        return lotMaster;
    }

    /**
     * Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param lotMasterSid the primary key of the lot master
     * @return the lot master that was removed
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster remove(int lotMasterSid)
        throws NoSuchLotMasterException, SystemException {
        return remove((Serializable) lotMasterSid);
    }

    /**
     * Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the lot master
     * @return the lot master that was removed
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster remove(Serializable primaryKey)
        throws NoSuchLotMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LotMaster lotMaster = (LotMaster) session.get(LotMasterImpl.class,
                    primaryKey);

            if (lotMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(lotMaster);
        } catch (NoSuchLotMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected LotMaster removeImpl(LotMaster lotMaster)
        throws SystemException {
        lotMaster = toUnwrappedModel(lotMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(lotMaster)) {
                lotMaster = (LotMaster) session.get(LotMasterImpl.class,
                        lotMaster.getPrimaryKeyObj());
            }

            if (lotMaster != null) {
                session.delete(lotMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (lotMaster != null) {
            clearCache(lotMaster);
        }

        return lotMaster;
    }

    @Override
    public LotMaster updateImpl(com.stpl.app.model.LotMaster lotMaster)
        throws SystemException {
        lotMaster = toUnwrappedModel(lotMaster);

        boolean isNew = lotMaster.isNew();

        LotMasterModelImpl lotMasterModelImpl = (LotMasterModelImpl) lotMaster;

        Session session = null;

        try {
            session = openSession();

            if (lotMaster.isNew()) {
                session.save(lotMaster);

                lotMaster.setNew(false);
            } else {
                session.merge(lotMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !LotMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((lotMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lotMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);

                args = new Object[] { lotMasterModelImpl.getItemId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);
            }

            if ((lotMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTNO.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lotMasterModelImpl.getOriginalLotNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTNO,
                    args);

                args = new Object[] { lotMasterModelImpl.getLotNo() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTNO, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTNO,
                    args);
            }

            if ((lotMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTEXPIRATIONDATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lotMasterModelImpl.getOriginalLotExpirationDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTEXPIRATIONDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTEXPIRATIONDATE,
                    args);

                args = new Object[] { lotMasterModelImpl.getLotExpirationDate() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTEXPIRATIONDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTEXPIRATIONDATE,
                    args);
            }

            if ((lotMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTLOTSOLDDATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lotMasterModelImpl.getOriginalLastLotSoldDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LASTLOTSOLDDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTLOTSOLDDATE,
                    args);

                args = new Object[] { lotMasterModelImpl.getLastLotSoldDate() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LASTLOTSOLDDATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTLOTSOLDDATE,
                    args);
            }

            if ((lotMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lotMasterModelImpl.getOriginalItemId(),
                        lotMasterModelImpl.getOriginalLotNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTUNIQUE,
                    args);

                args = new Object[] {
                        lotMasterModelImpl.getItemId(),
                        lotMasterModelImpl.getLotNo()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LOTUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LOTUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
            LotMasterImpl.class, lotMaster.getPrimaryKey(), lotMaster);

        return lotMaster;
    }

    protected LotMaster toUnwrappedModel(LotMaster lotMaster) {
        if (lotMaster instanceof LotMasterImpl) {
            return lotMaster;
        }

        LotMasterImpl lotMasterImpl = new LotMasterImpl();

        lotMasterImpl.setNew(lotMaster.isNew());
        lotMasterImpl.setPrimaryKey(lotMaster.getPrimaryKey());

        lotMasterImpl.setCreatedBy(lotMaster.getCreatedBy());
        lotMasterImpl.setModifiedBy(lotMaster.getModifiedBy());
        lotMasterImpl.setCreatedDate(lotMaster.getCreatedDate());
        lotMasterImpl.setItemId(lotMaster.getItemId());
        lotMasterImpl.setBatchId(lotMaster.getBatchId());
        lotMasterImpl.setModifiedDate(lotMaster.getModifiedDate());
        lotMasterImpl.setRecordLockStatus(lotMaster.isRecordLockStatus());
        lotMasterImpl.setLastLotSoldDate(lotMaster.getLastLotSoldDate());
        lotMasterImpl.setLotExpirationDate(lotMaster.getLotExpirationDate());
        lotMasterImpl.setSource(lotMaster.getSource());
        lotMasterImpl.setLotMasterSid(lotMaster.getLotMasterSid());
        lotMasterImpl.setLotNo(lotMaster.getLotNo());
        lotMasterImpl.setInboundStatus(lotMaster.getInboundStatus());

        return lotMasterImpl;
    }

    /**
     * Returns the lot master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the lot master
     * @return the lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchLotMasterException, SystemException {
        LotMaster lotMaster = fetchByPrimaryKey(primaryKey);

        if (lotMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return lotMaster;
    }

    /**
     * Returns the lot master with the primary key or throws a {@link com.stpl.app.NoSuchLotMasterException} if it could not be found.
     *
     * @param lotMasterSid the primary key of the lot master
     * @return the lot master
     * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster findByPrimaryKey(int lotMasterSid)
        throws NoSuchLotMasterException, SystemException {
        return findByPrimaryKey((Serializable) lotMasterSid);
    }

    /**
     * Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the lot master
     * @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        LotMaster lotMaster = (LotMaster) EntityCacheUtil.getResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
                LotMasterImpl.class, primaryKey);

        if (lotMaster == _nullLotMaster) {
            return null;
        }

        if (lotMaster == null) {
            Session session = null;

            try {
                session = openSession();

                lotMaster = (LotMaster) session.get(LotMasterImpl.class,
                        primaryKey);

                if (lotMaster != null) {
                    cacheResult(lotMaster);
                } else {
                    EntityCacheUtil.putResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
                        LotMasterImpl.class, primaryKey, _nullLotMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(LotMasterModelImpl.ENTITY_CACHE_ENABLED,
                    LotMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return lotMaster;
    }

    /**
     * Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param lotMasterSid the primary key of the lot master
     * @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LotMaster fetchByPrimaryKey(int lotMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) lotMasterSid);
    }

    /**
     * Returns all the lot masters.
     *
     * @return the lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the lot masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @return the range of lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the lot masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of lot masters
     * @param end the upper bound of the range of lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LotMaster> findAll(int start, int end,
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

        List<LotMaster> list = (List<LotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_LOTMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_LOTMASTER;

                if (pagination) {
                    sql = sql.concat(LotMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LotMaster>(list);
                } else {
                    list = (List<LotMaster>) QueryUtil.list(q, getDialect(),
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
     * Removes all the lot masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (LotMaster lotMaster : findAll()) {
            remove(lotMaster);
        }
    }

    /**
     * Returns the number of lot masters.
     *
     * @return the number of lot masters
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

                Query q = session.createQuery(_SQL_COUNT_LOTMASTER);

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
     * Initializes the lot master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.LotMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LotMaster>> listenersList = new ArrayList<ModelListener<LotMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LotMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(LotMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
