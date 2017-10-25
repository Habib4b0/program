package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchAverageShelfLifeMasterException;
import com.stpl.app.model.AverageShelfLifeMaster;
import com.stpl.app.model.impl.AverageShelfLifeMasterImpl;
import com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl;
import com.stpl.app.service.persistence.AverageShelfLifeMasterPersistence;

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
 * The persistence implementation for the average shelf life master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AverageShelfLifeMasterPersistence
 * @see AverageShelfLifeMasterUtil
 * @generated
 */
public class AverageShelfLifeMasterPersistenceImpl extends BasePersistenceImpl<AverageShelfLifeMaster>
    implements AverageShelfLifeMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AverageShelfLifeMasterUtil} to access the average shelf life master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AverageShelfLifeMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIDTYPE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemIdType",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIDTYPE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemIdType",
            new String[] { String.class.getName() },
            AverageShelfLifeMasterModelImpl.ITEMIDTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIDTYPE = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemIdType",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_1 = "averageShelfLifeMaster.itemIdType IS NULL";
    private static final String _FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_2 = "averageShelfLifeMaster.itemIdType = ?";
    private static final String _FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_3 = "(averageShelfLifeMaster.itemIdType IS NULL OR averageShelfLifeMaster.itemIdType = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
            new String[] { String.class.getName() },
            AverageShelfLifeMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "averageShelfLifeMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "averageShelfLifeMaster.itemId = ?";
    private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(averageShelfLifeMaster.itemId IS NULL OR averageShelfLifeMaster.itemId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AVGSHELFLIFE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAvgShelfLife",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVGSHELFLIFE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAvgShelfLife",
            new String[] { String.class.getName() },
            AverageShelfLifeMasterModelImpl.AVGSHELFLIFE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_AVGSHELFLIFE = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAvgShelfLife",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_1 = "averageShelfLifeMaster.avgShelfLife IS NULL";
    private static final String _FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_2 = "averageShelfLifeMaster.avgShelfLife = ?";
    private static final String _FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_3 = "(averageShelfLifeMaster.avgShelfLife IS NULL OR averageShelfLifeMaster.avgShelfLife = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAverageShelfLifeUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE =
        new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByAverageShelfLifeUnique",
            new String[] { String.class.getName(), String.class.getName() },
            AverageShelfLifeMasterModelImpl.ITEMIDTYPE_COLUMN_BITMASK |
            AverageShelfLifeMasterModelImpl.ITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_AVERAGESHELFLIFEUNIQUE = new FinderPath(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByAverageShelfLifeUnique",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_1 =
        "averageShelfLifeMaster.itemIdType IS NULL AND ";
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_2 =
        "averageShelfLifeMaster.itemIdType = ? AND ";
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_3 =
        "(averageShelfLifeMaster.itemIdType IS NULL OR averageShelfLifeMaster.itemIdType = '') AND ";
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_1 = "averageShelfLifeMaster.itemId IS NULL";
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_2 = "averageShelfLifeMaster.itemId = ?";
    private static final String _FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_3 = "(averageShelfLifeMaster.itemId IS NULL OR averageShelfLifeMaster.itemId = '')";
    private static final String _SQL_SELECT_AVERAGESHELFLIFEMASTER = "SELECT averageShelfLifeMaster FROM AverageShelfLifeMaster averageShelfLifeMaster";
    private static final String _SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE = "SELECT averageShelfLifeMaster FROM AverageShelfLifeMaster averageShelfLifeMaster WHERE ";
    private static final String _SQL_COUNT_AVERAGESHELFLIFEMASTER = "SELECT COUNT(averageShelfLifeMaster) FROM AverageShelfLifeMaster averageShelfLifeMaster";
    private static final String _SQL_COUNT_AVERAGESHELFLIFEMASTER_WHERE = "SELECT COUNT(averageShelfLifeMaster) FROM AverageShelfLifeMaster averageShelfLifeMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "averageShelfLifeMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AverageShelfLifeMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AverageShelfLifeMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AverageShelfLifeMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "averageShelfLifeMasterSid", "recordLockStatus",
                "itemIdType", "modifiedBy", "createdDate", "source", "itemId",
                "avgShelfLife", "batchId", "modifiedDate", "inboundStatus"
            });
    private static AverageShelfLifeMaster _nullAverageShelfLifeMaster = new AverageShelfLifeMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AverageShelfLifeMaster> toCacheModel() {
                return _nullAverageShelfLifeMasterCacheModel;
            }
        };

    private static CacheModel<AverageShelfLifeMaster> _nullAverageShelfLifeMasterCacheModel =
        new CacheModel<AverageShelfLifeMaster>() {
            @Override
            public AverageShelfLifeMaster toEntityModel() {
                return _nullAverageShelfLifeMaster;
            }
        };

    public AverageShelfLifeMasterPersistenceImpl() {
        setModelClass(AverageShelfLifeMaster.class);
    }

    /**
     * Returns all the average shelf life masters where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @return the matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemIdType(String itemIdType)
        throws SystemException {
        return findByItemIdType(itemIdType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the average shelf life masters where itemIdType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdType the item ID type
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @return the range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemIdType(String itemIdType,
        int start, int end) throws SystemException {
        return findByItemIdType(itemIdType, start, end, null);
    }

    /**
     * Returns an ordered range of all the average shelf life masters where itemIdType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdType the item ID type
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemIdType(String itemIdType,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIDTYPE;
            finderArgs = new Object[] { itemIdType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIDTYPE;
            finderArgs = new Object[] { itemIdType, start, end, orderByComparator };
        }

        List<AverageShelfLifeMaster> list = (List<AverageShelfLifeMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (AverageShelfLifeMaster averageShelfLifeMaster : list) {
                if (!Validator.equals(itemIdType,
                            averageShelfLifeMaster.getItemIdType())) {
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

            query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindItemIdType = false;

            if (itemIdType == null) {
                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_1);
            } else if (itemIdType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_3);
            } else {
                bindItemIdType = true;

                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdType) {
                    qPos.add(itemIdType);
                }

                if (!pagination) {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AverageShelfLifeMaster>(list);
                } else {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
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
     * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByItemIdType_First(String itemIdType,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByItemIdType_First(itemIdType,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdType=");
        msg.append(itemIdType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByItemIdType_First(String itemIdType,
        OrderByComparator orderByComparator) throws SystemException {
        List<AverageShelfLifeMaster> list = findByItemIdType(itemIdType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByItemIdType_Last(String itemIdType,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByItemIdType_Last(itemIdType,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdType=");
        msg.append(itemIdType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByItemIdType_Last(String itemIdType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemIdType(itemIdType);

        if (count == 0) {
            return null;
        }

        List<AverageShelfLifeMaster> list = findByItemIdType(itemIdType,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63;.
     *
     * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
     * @param itemIdType the item ID type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster[] findByItemIdType_PrevAndNext(
        int averageShelfLifeMasterSid, String itemIdType,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = findByPrimaryKey(averageShelfLifeMasterSid);

        Session session = null;

        try {
            session = openSession();

            AverageShelfLifeMaster[] array = new AverageShelfLifeMasterImpl[3];

            array[0] = getByItemIdType_PrevAndNext(session,
                    averageShelfLifeMaster, itemIdType, orderByComparator, true);

            array[1] = averageShelfLifeMaster;

            array[2] = getByItemIdType_PrevAndNext(session,
                    averageShelfLifeMaster, itemIdType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected AverageShelfLifeMaster getByItemIdType_PrevAndNext(
        Session session, AverageShelfLifeMaster averageShelfLifeMaster,
        String itemIdType, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

        boolean bindItemIdType = false;

        if (itemIdType == null) {
            query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_1);
        } else if (itemIdType.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_3);
        } else {
            bindItemIdType = true;

            query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_2);
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
            query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemIdType) {
            qPos.add(itemIdType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(averageShelfLifeMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<AverageShelfLifeMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the average shelf life masters where itemIdType = &#63; from the database.
     *
     * @param itemIdType the item ID type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemIdType(String itemIdType) throws SystemException {
        for (AverageShelfLifeMaster averageShelfLifeMaster : findByItemIdType(
                itemIdType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(averageShelfLifeMaster);
        }
    }

    /**
     * Returns the number of average shelf life masters where itemIdType = &#63;.
     *
     * @param itemIdType the item ID type
     * @return the number of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemIdType(String itemIdType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIDTYPE;

        Object[] finderArgs = new Object[] { itemIdType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindItemIdType = false;

            if (itemIdType == null) {
                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_1);
            } else if (itemIdType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_3);
            } else {
                bindItemIdType = true;

                query.append(_FINDER_COLUMN_ITEMIDTYPE_ITEMIDTYPE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdType) {
                    qPos.add(itemIdType);
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
     * Returns all the average shelf life masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemId(String itemId)
        throws SystemException {
        return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the average shelf life masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @return the range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemId(String itemId, int start,
        int end) throws SystemException {
        return findByItemId(itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the average shelf life masters where itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemId the item ID
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByItemId(String itemId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<AverageShelfLifeMaster> list = (List<AverageShelfLifeMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (AverageShelfLifeMaster averageShelfLifeMaster : list) {
                if (!Validator.equals(itemId, averageShelfLifeMaster.getItemId())) {
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

            query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

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
                query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
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
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AverageShelfLifeMaster>(list);
                } else {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
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
     * Returns the first average shelf life master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByItemId_First(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByItemId_First(itemId,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the first average shelf life master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByItemId_First(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        List<AverageShelfLifeMaster> list = findByItemId(itemId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByItemId_Last(String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByItemId_Last(itemId,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemId = &#63;.
     *
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByItemId_Last(String itemId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemId(itemId);

        if (count == 0) {
            return null;
        }

        List<AverageShelfLifeMaster> list = findByItemId(itemId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemId = &#63;.
     *
     * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster[] findByItemId_PrevAndNext(
        int averageShelfLifeMasterSid, String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = findByPrimaryKey(averageShelfLifeMasterSid);

        Session session = null;

        try {
            session = openSession();

            AverageShelfLifeMaster[] array = new AverageShelfLifeMasterImpl[3];

            array[0] = getByItemId_PrevAndNext(session, averageShelfLifeMaster,
                    itemId, orderByComparator, true);

            array[1] = averageShelfLifeMaster;

            array[2] = getByItemId_PrevAndNext(session, averageShelfLifeMaster,
                    itemId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected AverageShelfLifeMaster getByItemId_PrevAndNext(Session session,
        AverageShelfLifeMaster averageShelfLifeMaster, String itemId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

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
            query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
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
            Object[] values = orderByComparator.getOrderByConditionValues(averageShelfLifeMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<AverageShelfLifeMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the average shelf life masters where itemId = &#63; from the database.
     *
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemId(String itemId) throws SystemException {
        for (AverageShelfLifeMaster averageShelfLifeMaster : findByItemId(
                itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(averageShelfLifeMaster);
        }
    }

    /**
     * Returns the number of average shelf life masters where itemId = &#63;.
     *
     * @param itemId the item ID
     * @return the number of matching average shelf life masters
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

            query.append(_SQL_COUNT_AVERAGESHELFLIFEMASTER_WHERE);

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
     * Returns all the average shelf life masters where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @return the matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAvgShelfLife(String avgShelfLife)
        throws SystemException {
        return findByAvgShelfLife(avgShelfLife, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the average shelf life masters where avgShelfLife = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param avgShelfLife the avg shelf life
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @return the range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAvgShelfLife(
        String avgShelfLife, int start, int end) throws SystemException {
        return findByAvgShelfLife(avgShelfLife, start, end, null);
    }

    /**
     * Returns an ordered range of all the average shelf life masters where avgShelfLife = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param avgShelfLife the avg shelf life
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAvgShelfLife(
        String avgShelfLife, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVGSHELFLIFE;
            finderArgs = new Object[] { avgShelfLife };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AVGSHELFLIFE;
            finderArgs = new Object[] {
                    avgShelfLife,
                    
                    start, end, orderByComparator
                };
        }

        List<AverageShelfLifeMaster> list = (List<AverageShelfLifeMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (AverageShelfLifeMaster averageShelfLifeMaster : list) {
                if (!Validator.equals(avgShelfLife,
                            averageShelfLifeMaster.getAvgShelfLife())) {
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

            query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindAvgShelfLife = false;

            if (avgShelfLife == null) {
                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_1);
            } else if (avgShelfLife.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_3);
            } else {
                bindAvgShelfLife = true;

                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAvgShelfLife) {
                    qPos.add(avgShelfLife);
                }

                if (!pagination) {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AverageShelfLifeMaster>(list);
                } else {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
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
     * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByAvgShelfLife_First(
        String avgShelfLife, OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByAvgShelfLife_First(avgShelfLife,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("avgShelfLife=");
        msg.append(avgShelfLife);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByAvgShelfLife_First(
        String avgShelfLife, OrderByComparator orderByComparator)
        throws SystemException {
        List<AverageShelfLifeMaster> list = findByAvgShelfLife(avgShelfLife, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByAvgShelfLife_Last(String avgShelfLife,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByAvgShelfLife_Last(avgShelfLife,
                orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("avgShelfLife=");
        msg.append(avgShelfLife);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByAvgShelfLife_Last(
        String avgShelfLife, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByAvgShelfLife(avgShelfLife);

        if (count == 0) {
            return null;
        }

        List<AverageShelfLifeMaster> list = findByAvgShelfLife(avgShelfLife,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where avgShelfLife = &#63;.
     *
     * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
     * @param avgShelfLife the avg shelf life
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster[] findByAvgShelfLife_PrevAndNext(
        int averageShelfLifeMasterSid, String avgShelfLife,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = findByPrimaryKey(averageShelfLifeMasterSid);

        Session session = null;

        try {
            session = openSession();

            AverageShelfLifeMaster[] array = new AverageShelfLifeMasterImpl[3];

            array[0] = getByAvgShelfLife_PrevAndNext(session,
                    averageShelfLifeMaster, avgShelfLife, orderByComparator,
                    true);

            array[1] = averageShelfLifeMaster;

            array[2] = getByAvgShelfLife_PrevAndNext(session,
                    averageShelfLifeMaster, avgShelfLife, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected AverageShelfLifeMaster getByAvgShelfLife_PrevAndNext(
        Session session, AverageShelfLifeMaster averageShelfLifeMaster,
        String avgShelfLife, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

        boolean bindAvgShelfLife = false;

        if (avgShelfLife == null) {
            query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_1);
        } else if (avgShelfLife.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_3);
        } else {
            bindAvgShelfLife = true;

            query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_2);
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
            query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindAvgShelfLife) {
            qPos.add(avgShelfLife);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(averageShelfLifeMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<AverageShelfLifeMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the average shelf life masters where avgShelfLife = &#63; from the database.
     *
     * @param avgShelfLife the avg shelf life
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAvgShelfLife(String avgShelfLife)
        throws SystemException {
        for (AverageShelfLifeMaster averageShelfLifeMaster : findByAvgShelfLife(
                avgShelfLife, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(averageShelfLifeMaster);
        }
    }

    /**
     * Returns the number of average shelf life masters where avgShelfLife = &#63;.
     *
     * @param avgShelfLife the avg shelf life
     * @return the number of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAvgShelfLife(String avgShelfLife)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_AVGSHELFLIFE;

        Object[] finderArgs = new Object[] { avgShelfLife };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindAvgShelfLife = false;

            if (avgShelfLife == null) {
                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_1);
            } else if (avgShelfLife.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_3);
            } else {
                bindAvgShelfLife = true;

                query.append(_FINDER_COLUMN_AVGSHELFLIFE_AVGSHELFLIFE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindAvgShelfLife) {
                    qPos.add(avgShelfLife);
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
     * Returns all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @return the matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        String itemIdType, String itemId) throws SystemException {
        return findByAverageShelfLifeUnique(itemIdType, itemId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @return the range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        String itemIdType, String itemId, int start, int end)
        throws SystemException {
        return findByAverageShelfLifeUnique(itemIdType, itemId, start, end, null);
    }

    /**
     * Returns an ordered range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        String itemIdType, String itemId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE;
            finderArgs = new Object[] { itemIdType, itemId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE;
            finderArgs = new Object[] {
                    itemIdType, itemId,
                    
                    start, end, orderByComparator
                };
        }

        List<AverageShelfLifeMaster> list = (List<AverageShelfLifeMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (AverageShelfLifeMaster averageShelfLifeMaster : list) {
                if (!Validator.equals(itemIdType,
                            averageShelfLifeMaster.getItemIdType()) ||
                        !Validator.equals(itemId,
                            averageShelfLifeMaster.getItemId())) {
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

            query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindItemIdType = false;

            if (itemIdType == null) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_1);
            } else if (itemIdType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_3);
            } else {
                bindItemIdType = true;

                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_2);
            }

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdType) {
                    qPos.add(itemIdType);
                }

                if (bindItemId) {
                    qPos.add(itemId);
                }

                if (!pagination) {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AverageShelfLifeMaster>(list);
                } else {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
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
     * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByAverageShelfLifeUnique_First(
        String itemIdType, String itemId, OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByAverageShelfLifeUnique_First(itemIdType,
                itemId, orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdType=");
        msg.append(itemIdType);

        msg.append(", itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByAverageShelfLifeUnique_First(
        String itemIdType, String itemId, OrderByComparator orderByComparator)
        throws SystemException {
        List<AverageShelfLifeMaster> list = findByAverageShelfLifeUnique(itemIdType,
                itemId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByAverageShelfLifeUnique_Last(
        String itemIdType, String itemId, OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByAverageShelfLifeUnique_Last(itemIdType,
                itemId, orderByComparator);

        if (averageShelfLifeMaster != null) {
            return averageShelfLifeMaster;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("itemIdType=");
        msg.append(itemIdType);

        msg.append(", itemId=");
        msg.append(itemId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAverageShelfLifeMasterException(msg.toString());
    }

    /**
     * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByAverageShelfLifeUnique_Last(
        String itemIdType, String itemId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByAverageShelfLifeUnique(itemIdType, itemId);

        if (count == 0) {
            return null;
        }

        List<AverageShelfLifeMaster> list = findByAverageShelfLifeUnique(itemIdType,
                itemId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
     *
     * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster[] findByAverageShelfLifeUnique_PrevAndNext(
        int averageShelfLifeMasterSid, String itemIdType, String itemId,
        OrderByComparator orderByComparator)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = findByPrimaryKey(averageShelfLifeMasterSid);

        Session session = null;

        try {
            session = openSession();

            AverageShelfLifeMaster[] array = new AverageShelfLifeMasterImpl[3];

            array[0] = getByAverageShelfLifeUnique_PrevAndNext(session,
                    averageShelfLifeMaster, itemIdType, itemId,
                    orderByComparator, true);

            array[1] = averageShelfLifeMaster;

            array[2] = getByAverageShelfLifeUnique_PrevAndNext(session,
                    averageShelfLifeMaster, itemIdType, itemId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected AverageShelfLifeMaster getByAverageShelfLifeUnique_PrevAndNext(
        Session session, AverageShelfLifeMaster averageShelfLifeMaster,
        String itemIdType, String itemId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER_WHERE);

        boolean bindItemIdType = false;

        if (itemIdType == null) {
            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_1);
        } else if (itemIdType.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_3);
        } else {
            bindItemIdType = true;

            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_2);
        }

        boolean bindItemId = false;

        if (itemId == null) {
            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_1);
        } else if (itemId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_3);
        } else {
            bindItemId = true;

            query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_2);
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
            query.append(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindItemIdType) {
            qPos.add(itemIdType);
        }

        if (bindItemId) {
            qPos.add(itemId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(averageShelfLifeMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<AverageShelfLifeMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the average shelf life masters where itemIdType = &#63; and itemId = &#63; from the database.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAverageShelfLifeUnique(String itemIdType, String itemId)
        throws SystemException {
        for (AverageShelfLifeMaster averageShelfLifeMaster : findByAverageShelfLifeUnique(
                itemIdType, itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(averageShelfLifeMaster);
        }
    }

    /**
     * Returns the number of average shelf life masters where itemIdType = &#63; and itemId = &#63;.
     *
     * @param itemIdType the item ID type
     * @param itemId the item ID
     * @return the number of matching average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAverageShelfLifeUnique(String itemIdType, String itemId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_AVERAGESHELFLIFEUNIQUE;

        Object[] finderArgs = new Object[] { itemIdType, itemId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_AVERAGESHELFLIFEMASTER_WHERE);

            boolean bindItemIdType = false;

            if (itemIdType == null) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_1);
            } else if (itemIdType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_3);
            } else {
                bindItemIdType = true;

                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMIDTYPE_2);
            }

            boolean bindItemId = false;

            if (itemId == null) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_1);
            } else if (itemId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_3);
            } else {
                bindItemId = true;

                query.append(_FINDER_COLUMN_AVERAGESHELFLIFEUNIQUE_ITEMID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindItemIdType) {
                    qPos.add(itemIdType);
                }

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
     * Caches the average shelf life master in the entity cache if it is enabled.
     *
     * @param averageShelfLifeMaster the average shelf life master
     */
    @Override
    public void cacheResult(AverageShelfLifeMaster averageShelfLifeMaster) {
        EntityCacheUtil.putResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            averageShelfLifeMaster.getPrimaryKey(), averageShelfLifeMaster);

        averageShelfLifeMaster.resetOriginalValues();
    }

    /**
     * Caches the average shelf life masters in the entity cache if it is enabled.
     *
     * @param averageShelfLifeMasters the average shelf life masters
     */
    @Override
    public void cacheResult(
        List<AverageShelfLifeMaster> averageShelfLifeMasters) {
        for (AverageShelfLifeMaster averageShelfLifeMaster : averageShelfLifeMasters) {
            if (EntityCacheUtil.getResult(
                        AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AverageShelfLifeMasterImpl.class,
                        averageShelfLifeMaster.getPrimaryKey()) == null) {
                cacheResult(averageShelfLifeMaster);
            } else {
                averageShelfLifeMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all average shelf life masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AverageShelfLifeMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AverageShelfLifeMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the average shelf life master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AverageShelfLifeMaster averageShelfLifeMaster) {
        EntityCacheUtil.removeResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            averageShelfLifeMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AverageShelfLifeMaster> averageShelfLifeMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AverageShelfLifeMaster averageShelfLifeMaster : averageShelfLifeMasters) {
            EntityCacheUtil.removeResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
                AverageShelfLifeMasterImpl.class,
                averageShelfLifeMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new average shelf life master with the primary key. Does not add the average shelf life master to the database.
     *
     * @param averageShelfLifeMasterSid the primary key for the new average shelf life master
     * @return the new average shelf life master
     */
    @Override
    public AverageShelfLifeMaster create(int averageShelfLifeMasterSid) {
        AverageShelfLifeMaster averageShelfLifeMaster = new AverageShelfLifeMasterImpl();

        averageShelfLifeMaster.setNew(true);
        averageShelfLifeMaster.setPrimaryKey(averageShelfLifeMasterSid);

        return averageShelfLifeMaster;
    }

    /**
     * Removes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param averageShelfLifeMasterSid the primary key of the average shelf life master
     * @return the average shelf life master that was removed
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster remove(int averageShelfLifeMasterSid)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        return remove((Serializable) averageShelfLifeMasterSid);
    }

    /**
     * Removes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the average shelf life master
     * @return the average shelf life master that was removed
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster remove(Serializable primaryKey)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AverageShelfLifeMaster averageShelfLifeMaster = (AverageShelfLifeMaster) session.get(AverageShelfLifeMasterImpl.class,
                    primaryKey);

            if (averageShelfLifeMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAverageShelfLifeMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(averageShelfLifeMaster);
        } catch (NoSuchAverageShelfLifeMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AverageShelfLifeMaster removeImpl(
        AverageShelfLifeMaster averageShelfLifeMaster)
        throws SystemException {
        averageShelfLifeMaster = toUnwrappedModel(averageShelfLifeMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(averageShelfLifeMaster)) {
                averageShelfLifeMaster = (AverageShelfLifeMaster) session.get(AverageShelfLifeMasterImpl.class,
                        averageShelfLifeMaster.getPrimaryKeyObj());
            }

            if (averageShelfLifeMaster != null) {
                session.delete(averageShelfLifeMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (averageShelfLifeMaster != null) {
            clearCache(averageShelfLifeMaster);
        }

        return averageShelfLifeMaster;
    }

    @Override
    public AverageShelfLifeMaster updateImpl(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws SystemException {
        averageShelfLifeMaster = toUnwrappedModel(averageShelfLifeMaster);

        boolean isNew = averageShelfLifeMaster.isNew();

        AverageShelfLifeMasterModelImpl averageShelfLifeMasterModelImpl = (AverageShelfLifeMasterModelImpl) averageShelfLifeMaster;

        Session session = null;

        try {
            session = openSession();

            if (averageShelfLifeMaster.isNew()) {
                session.save(averageShelfLifeMaster);

                averageShelfLifeMaster.setNew(false);
            } else {
                session.merge(averageShelfLifeMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !AverageShelfLifeMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((averageShelfLifeMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIDTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        averageShelfLifeMasterModelImpl.getOriginalItemIdType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIDTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIDTYPE,
                    args);

                args = new Object[] {
                        averageShelfLifeMasterModelImpl.getItemIdType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMIDTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIDTYPE,
                    args);
            }

            if ((averageShelfLifeMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        averageShelfLifeMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);

                args = new Object[] { averageShelfLifeMasterModelImpl.getItemId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
                    args);
            }

            if ((averageShelfLifeMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVGSHELFLIFE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        averageShelfLifeMasterModelImpl.getOriginalAvgShelfLife()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVGSHELFLIFE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVGSHELFLIFE,
                    args);

                args = new Object[] {
                        averageShelfLifeMasterModelImpl.getAvgShelfLife()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVGSHELFLIFE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVGSHELFLIFE,
                    args);
            }

            if ((averageShelfLifeMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        averageShelfLifeMasterModelImpl.getOriginalItemIdType(),
                        averageShelfLifeMasterModelImpl.getOriginalItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVERAGESHELFLIFEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE,
                    args);

                args = new Object[] {
                        averageShelfLifeMasterModelImpl.getItemIdType(),
                        averageShelfLifeMasterModelImpl.getItemId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVERAGESHELFLIFEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVERAGESHELFLIFEUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
            AverageShelfLifeMasterImpl.class,
            averageShelfLifeMaster.getPrimaryKey(), averageShelfLifeMaster);

        return averageShelfLifeMaster;
    }

    protected AverageShelfLifeMaster toUnwrappedModel(
        AverageShelfLifeMaster averageShelfLifeMaster) {
        if (averageShelfLifeMaster instanceof AverageShelfLifeMasterImpl) {
            return averageShelfLifeMaster;
        }

        AverageShelfLifeMasterImpl averageShelfLifeMasterImpl = new AverageShelfLifeMasterImpl();

        averageShelfLifeMasterImpl.setNew(averageShelfLifeMaster.isNew());
        averageShelfLifeMasterImpl.setPrimaryKey(averageShelfLifeMaster.getPrimaryKey());

        averageShelfLifeMasterImpl.setCreatedBy(averageShelfLifeMaster.getCreatedBy());
        averageShelfLifeMasterImpl.setAverageShelfLifeMasterSid(averageShelfLifeMaster.getAverageShelfLifeMasterSid());
        averageShelfLifeMasterImpl.setRecordLockStatus(averageShelfLifeMaster.isRecordLockStatus());
        averageShelfLifeMasterImpl.setItemIdType(averageShelfLifeMaster.getItemIdType());
        averageShelfLifeMasterImpl.setModifiedBy(averageShelfLifeMaster.getModifiedBy());
        averageShelfLifeMasterImpl.setCreatedDate(averageShelfLifeMaster.getCreatedDate());
        averageShelfLifeMasterImpl.setSource(averageShelfLifeMaster.getSource());
        averageShelfLifeMasterImpl.setItemId(averageShelfLifeMaster.getItemId());
        averageShelfLifeMasterImpl.setAvgShelfLife(averageShelfLifeMaster.getAvgShelfLife());
        averageShelfLifeMasterImpl.setBatchId(averageShelfLifeMaster.getBatchId());
        averageShelfLifeMasterImpl.setModifiedDate(averageShelfLifeMaster.getModifiedDate());
        averageShelfLifeMasterImpl.setInboundStatus(averageShelfLifeMaster.getInboundStatus());

        return averageShelfLifeMasterImpl;
    }

    /**
     * Returns the average shelf life master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the average shelf life master
     * @return the average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = fetchByPrimaryKey(primaryKey);

        if (averageShelfLifeMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAverageShelfLifeMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return averageShelfLifeMaster;
    }

    /**
     * Returns the average shelf life master with the primary key or throws a {@link com.stpl.app.NoSuchAverageShelfLifeMasterException} if it could not be found.
     *
     * @param averageShelfLifeMasterSid the primary key of the average shelf life master
     * @return the average shelf life master
     * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster findByPrimaryKey(
        int averageShelfLifeMasterSid)
        throws NoSuchAverageShelfLifeMasterException, SystemException {
        return findByPrimaryKey((Serializable) averageShelfLifeMasterSid);
    }

    /**
     * Returns the average shelf life master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the average shelf life master
     * @return the average shelf life master, or <code>null</code> if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AverageShelfLifeMaster averageShelfLifeMaster = (AverageShelfLifeMaster) EntityCacheUtil.getResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
                AverageShelfLifeMasterImpl.class, primaryKey);

        if (averageShelfLifeMaster == _nullAverageShelfLifeMaster) {
            return null;
        }

        if (averageShelfLifeMaster == null) {
            Session session = null;

            try {
                session = openSession();

                averageShelfLifeMaster = (AverageShelfLifeMaster) session.get(AverageShelfLifeMasterImpl.class,
                        primaryKey);

                if (averageShelfLifeMaster != null) {
                    cacheResult(averageShelfLifeMaster);
                } else {
                    EntityCacheUtil.putResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AverageShelfLifeMasterImpl.class, primaryKey,
                        _nullAverageShelfLifeMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AverageShelfLifeMasterModelImpl.ENTITY_CACHE_ENABLED,
                    AverageShelfLifeMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return averageShelfLifeMaster;
    }

    /**
     * Returns the average shelf life master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param averageShelfLifeMasterSid the primary key of the average shelf life master
     * @return the average shelf life master, or <code>null</code> if a average shelf life master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AverageShelfLifeMaster fetchByPrimaryKey(
        int averageShelfLifeMasterSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) averageShelfLifeMasterSid);
    }

    /**
     * Returns all the average shelf life masters.
     *
     * @return the average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the average shelf life masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @return the range of average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the average shelf life masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of average shelf life masters
     * @param end the upper bound of the range of average shelf life masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of average shelf life masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AverageShelfLifeMaster> findAll(int start, int end,
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

        List<AverageShelfLifeMaster> list = (List<AverageShelfLifeMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_AVERAGESHELFLIFEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_AVERAGESHELFLIFEMASTER;

                if (pagination) {
                    sql = sql.concat(AverageShelfLifeMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AverageShelfLifeMaster>(list);
                } else {
                    list = (List<AverageShelfLifeMaster>) QueryUtil.list(q,
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
     * Removes all the average shelf life masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AverageShelfLifeMaster averageShelfLifeMaster : findAll()) {
            remove(averageShelfLifeMaster);
        }
    }

    /**
     * Returns the number of average shelf life masters.
     *
     * @return the number of average shelf life masters
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

                Query q = session.createQuery(_SQL_COUNT_AVERAGESHELFLIFEMASTER);

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
     * Initializes the average shelf life master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.AverageShelfLifeMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AverageShelfLifeMaster>> listenersList = new ArrayList<ModelListener<AverageShelfLifeMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AverageShelfLifeMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AverageShelfLifeMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
