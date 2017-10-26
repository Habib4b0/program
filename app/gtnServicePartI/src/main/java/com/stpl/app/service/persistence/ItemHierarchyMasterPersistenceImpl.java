package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemHierarchyMasterException;
import com.stpl.app.model.ItemHierarchyMaster;
import com.stpl.app.model.impl.ItemHierarchyMasterImpl;
import com.stpl.app.model.impl.ItemHierarchyMasterModelImpl;
import com.stpl.app.service.persistence.ItemHierarchyMasterPersistence;

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
 * The persistence implementation for the item hierarchy master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyMasterPersistence
 * @see ItemHierarchyMasterUtil
 * @generated
 */
public class ItemHierarchyMasterPersistenceImpl extends BasePersistenceImpl<ItemHierarchyMaster>
    implements ItemHierarchyMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemHierarchyMasterUtil} to access the item hierarchy master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemHierarchyMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0 = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0 =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0",
            new String[] { String.class.getName() },
            ItemHierarchyMasterModelImpl.LEVEL0_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0 = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_1 = "itemHierarchyMaster.level0 IS NULL";
    private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_2 = "itemHierarchyMaster.level0 = ?";
    private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_3 = "(itemHierarchyMaster.level0 IS NULL OR itemHierarchyMaster.level0 = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0ALIAS =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0Alias",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0Alias",
            new String[] { String.class.getName() },
            ItemHierarchyMasterModelImpl.LEVEL0ALIAS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0ALIAS = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0Alias",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1 = "itemHierarchyMaster.level0Alias IS NULL";
    private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2 = "itemHierarchyMaster.level0Alias = ?";
    private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3 = "(itemHierarchyMaster.level0Alias IS NULL OR itemHierarchyMaster.level0Alias = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0TAG =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0Tag",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0Tag",
            new String[] { String.class.getName() },
            ItemHierarchyMasterModelImpl.LEVEL0TAG_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0TAG = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0Tag",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1 = "itemHierarchyMaster.level0Tag IS NULL";
    private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2 = "itemHierarchyMaster.level0Tag = ?";
    private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3 = "(itemHierarchyMaster.level0Tag IS NULL OR itemHierarchyMaster.level0Tag = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByItemHierarchyUnique",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE =
        new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByItemHierarchyUnique",
            new String[] { String.class.getName() },
            ItemHierarchyMasterModelImpl.LEVEL0_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByItemHierarchyUnique",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1 = "itemHierarchyMaster.level0 IS NULL";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2 = "itemHierarchyMaster.level0 = ?";
    private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3 = "(itemHierarchyMaster.level0 IS NULL OR itemHierarchyMaster.level0 = '')";
    private static final String _SQL_SELECT_ITEMHIERARCHYMASTER = "SELECT itemHierarchyMaster FROM ItemHierarchyMaster itemHierarchyMaster";
    private static final String _SQL_SELECT_ITEMHIERARCHYMASTER_WHERE = "SELECT itemHierarchyMaster FROM ItemHierarchyMaster itemHierarchyMaster WHERE ";
    private static final String _SQL_COUNT_ITEMHIERARCHYMASTER = "SELECT COUNT(itemHierarchyMaster) FROM ItemHierarchyMaster itemHierarchyMaster";
    private static final String _SQL_COUNT_ITEMHIERARCHYMASTER_WHERE = "SELECT COUNT(itemHierarchyMaster) FROM ItemHierarchyMaster itemHierarchyMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemHierarchyMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemHierarchyMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemHierarchyMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemHierarchyMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "level1Alias", "level11Alias", "createdDate", "level8Alias",
                "level14Alias", "level5Alias", "createdBy", "level10Alias",
                "itemHierarchyMasterSid", "level17Alias", "level9Alias",
                "level0Alias", "modifiedDate", "level13Alias", "source",
                "level6Alias", "gtnBrand", "modifiedBy", "level3Alias",
                "level16Alias", "batchId", "level19Alias", "level20",
                "level2Alias", "level20Alias", "gtnTherapeuticClass",
                "level7Alias", "level0", "level1", "level2", "level3",
                "level12Alias", "level8", "level11", "level4Alias", "level9",
                "level12", "level13", "level14", "recordLockStatus", "level0Tag",
                "level4", "level5", "level6", "level15Alias", "level7",
                "level10", "level19", "level15", "level16", "gtnCompany",
                "level17", "level18Alias", "level18", "inboundStatus"
            });
    private static ItemHierarchyMaster _nullItemHierarchyMaster = new ItemHierarchyMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemHierarchyMaster> toCacheModel() {
                return _nullItemHierarchyMasterCacheModel;
            }
        };

    private static CacheModel<ItemHierarchyMaster> _nullItemHierarchyMasterCacheModel =
        new CacheModel<ItemHierarchyMaster>() {
            @Override
            public ItemHierarchyMaster toEntityModel() {
                return _nullItemHierarchyMaster;
            }
        };

    public ItemHierarchyMasterPersistenceImpl() {
        setModelClass(ItemHierarchyMaster.class);
    }

    /**
     * Returns all the item hierarchy masters where level0 = &#63;.
     *
     * @param level0 the level0
     * @return the matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0(String level0)
        throws SystemException {
        return findByLevel0(level0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy masters where level0 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0 the level0
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @return the range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0(String level0, int start,
        int end) throws SystemException {
        return findByLevel0(level0, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0 the level0
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0(String level0, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0;
            finderArgs = new Object[] { level0 };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0;
            finderArgs = new Object[] { level0, start, end, orderByComparator };
        }

        List<ItemHierarchyMaster> list = (List<ItemHierarchyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyMaster itemHierarchyMaster : list) {
                if (!Validator.equals(level0, itemHierarchyMaster.getLevel0())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0 = false;

            if (level0 == null) {
                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
            } else if (level0.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
            } else {
                bindLevel0 = true;

                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0) {
                    qPos.add(level0);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyMaster>(list);
                } else {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0_First(String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0_First(level0,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0=");
        msg.append(level0);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0_First(String level0,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyMaster> list = findByLevel0(level0, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0_Last(String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0_Last(level0,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0=");
        msg.append(level0);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0_Last(String level0,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLevel0(level0);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyMaster> list = findByLevel0(level0, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster[] findByLevel0_PrevAndNext(
        int itemHierarchyMasterSid, String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

            array[0] = getByLevel0_PrevAndNext(session, itemHierarchyMaster,
                    level0, orderByComparator, true);

            array[1] = itemHierarchyMaster;

            array[2] = getByLevel0_PrevAndNext(session, itemHierarchyMaster,
                    level0, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyMaster getByLevel0_PrevAndNext(Session session,
        ItemHierarchyMaster itemHierarchyMaster, String level0,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

        boolean bindLevel0 = false;

        if (level0 == null) {
            query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
        } else if (level0.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
        } else {
            bindLevel0 = true;

            query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
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
            query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLevel0) {
            qPos.add(level0);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy masters where level0 = &#63; from the database.
     *
     * @param level0 the level0
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLevel0(String level0) throws SystemException {
        for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0(level0,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyMaster);
        }
    }

    /**
     * Returns the number of item hierarchy masters where level0 = &#63;.
     *
     * @param level0 the level0
     * @return the number of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLevel0(String level0) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0;

        Object[] finderArgs = new Object[] { level0 };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0 = false;

            if (level0 == null) {
                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
            } else if (level0.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
            } else {
                bindLevel0 = true;

                query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0) {
                    qPos.add(level0);
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
     * Returns all the item hierarchy masters where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @return the matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias)
        throws SystemException {
        return findByLevel0Alias(level0Alias, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy masters where level0Alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0Alias the level0 alias
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @return the range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias,
        int start, int end) throws SystemException {
        return findByLevel0Alias(level0Alias, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0Alias the level0 alias
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS;
            finderArgs = new Object[] { level0Alias };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0ALIAS;
            finderArgs = new Object[] { level0Alias, start, end, orderByComparator };
        }

        List<ItemHierarchyMaster> list = (List<ItemHierarchyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyMaster itemHierarchyMaster : list) {
                if (!Validator.equals(level0Alias,
                            itemHierarchyMaster.getLevel0Alias())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0Alias = false;

            if (level0Alias == null) {
                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
            } else if (level0Alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
            } else {
                bindLevel0Alias = true;

                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0Alias) {
                    qPos.add(level0Alias);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyMaster>(list);
                } else {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0Alias_First(String level0Alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Alias_First(level0Alias,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0Alias=");
        msg.append(level0Alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0Alias_First(String level0Alias,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyMaster> list = findByLevel0Alias(level0Alias, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0Alias_Last(String level0Alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Alias_Last(level0Alias,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0Alias=");
        msg.append(level0Alias);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0Alias_Last(String level0Alias,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLevel0Alias(level0Alias);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyMaster> list = findByLevel0Alias(level0Alias,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Alias = &#63;.
     *
     * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
     * @param level0Alias the level0 alias
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster[] findByLevel0Alias_PrevAndNext(
        int itemHierarchyMasterSid, String level0Alias,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

            array[0] = getByLevel0Alias_PrevAndNext(session,
                    itemHierarchyMaster, level0Alias, orderByComparator, true);

            array[1] = itemHierarchyMaster;

            array[2] = getByLevel0Alias_PrevAndNext(session,
                    itemHierarchyMaster, level0Alias, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyMaster getByLevel0Alias_PrevAndNext(
        Session session, ItemHierarchyMaster itemHierarchyMaster,
        String level0Alias, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

        boolean bindLevel0Alias = false;

        if (level0Alias == null) {
            query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
        } else if (level0Alias.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
        } else {
            bindLevel0Alias = true;

            query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
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
            query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLevel0Alias) {
            qPos.add(level0Alias);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy masters where level0Alias = &#63; from the database.
     *
     * @param level0Alias the level0 alias
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLevel0Alias(String level0Alias)
        throws SystemException {
        for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0Alias(
                level0Alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyMaster);
        }
    }

    /**
     * Returns the number of item hierarchy masters where level0Alias = &#63;.
     *
     * @param level0Alias the level0 alias
     * @return the number of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLevel0Alias(String level0Alias) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0ALIAS;

        Object[] finderArgs = new Object[] { level0Alias };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0Alias = false;

            if (level0Alias == null) {
                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
            } else if (level0Alias.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
            } else {
                bindLevel0Alias = true;

                query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0Alias) {
                    qPos.add(level0Alias);
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
     * Returns all the item hierarchy masters where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @return the matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag)
        throws SystemException {
        return findByLevel0Tag(level0Tag, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the item hierarchy masters where level0Tag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0Tag the level0 tag
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @return the range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag,
        int start, int end) throws SystemException {
        return findByLevel0Tag(level0Tag, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0Tag the level0 tag
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG;
            finderArgs = new Object[] { level0Tag };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0TAG;
            finderArgs = new Object[] { level0Tag, start, end, orderByComparator };
        }

        List<ItemHierarchyMaster> list = (List<ItemHierarchyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyMaster itemHierarchyMaster : list) {
                if (!Validator.equals(level0Tag,
                            itemHierarchyMaster.getLevel0Tag())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0Tag = false;

            if (level0Tag == null) {
                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
            } else if (level0Tag.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
            } else {
                bindLevel0Tag = true;

                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0Tag) {
                    qPos.add(level0Tag);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyMaster>(list);
                } else {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0Tag_First(String level0Tag,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Tag_First(level0Tag,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0Tag=");
        msg.append(level0Tag);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0Tag_First(String level0Tag,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyMaster> list = findByLevel0Tag(level0Tag, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByLevel0Tag_Last(String level0Tag,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Tag_Last(level0Tag,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0Tag=");
        msg.append(level0Tag);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByLevel0Tag_Last(String level0Tag,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByLevel0Tag(level0Tag);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyMaster> list = findByLevel0Tag(level0Tag, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Tag = &#63;.
     *
     * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
     * @param level0Tag the level0 tag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster[] findByLevel0Tag_PrevAndNext(
        int itemHierarchyMasterSid, String level0Tag,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

            array[0] = getByLevel0Tag_PrevAndNext(session, itemHierarchyMaster,
                    level0Tag, orderByComparator, true);

            array[1] = itemHierarchyMaster;

            array[2] = getByLevel0Tag_PrevAndNext(session, itemHierarchyMaster,
                    level0Tag, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyMaster getByLevel0Tag_PrevAndNext(Session session,
        ItemHierarchyMaster itemHierarchyMaster, String level0Tag,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

        boolean bindLevel0Tag = false;

        if (level0Tag == null) {
            query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
        } else if (level0Tag.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
        } else {
            bindLevel0Tag = true;

            query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
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
            query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLevel0Tag) {
            qPos.add(level0Tag);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy masters where level0Tag = &#63; from the database.
     *
     * @param level0Tag the level0 tag
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByLevel0Tag(String level0Tag) throws SystemException {
        for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0Tag(
                level0Tag, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyMaster);
        }
    }

    /**
     * Returns the number of item hierarchy masters where level0Tag = &#63;.
     *
     * @param level0Tag the level0 tag
     * @return the number of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByLevel0Tag(String level0Tag) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0TAG;

        Object[] finderArgs = new Object[] { level0Tag };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0Tag = false;

            if (level0Tag == null) {
                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
            } else if (level0Tag.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
            } else {
                bindLevel0Tag = true;

                query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0Tag) {
                    qPos.add(level0Tag);
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
     * Returns all the item hierarchy masters where level0 = &#63;.
     *
     * @param level0 the level0
     * @return the matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0)
        throws SystemException {
        return findByItemHierarchyUnique(level0, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy masters where level0 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0 the level0
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @return the range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0,
        int start, int end) throws SystemException {
        return findByItemHierarchyUnique(level0, start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param level0 the level0
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE;
            finderArgs = new Object[] { level0 };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE;
            finderArgs = new Object[] { level0, start, end, orderByComparator };
        }

        List<ItemHierarchyMaster> list = (List<ItemHierarchyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ItemHierarchyMaster itemHierarchyMaster : list) {
                if (!Validator.equals(level0, itemHierarchyMaster.getLevel0())) {
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

            query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0 = false;

            if (level0 == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
            } else if (level0.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
            } else {
                bindLevel0 = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0) {
                    qPos.add(level0);
                }

                if (!pagination) {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyMaster>(list);
                } else {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
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
     * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByItemHierarchyUnique_First(String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByItemHierarchyUnique_First(level0,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0=");
        msg.append(level0);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByItemHierarchyUnique_First(String level0,
        OrderByComparator orderByComparator) throws SystemException {
        List<ItemHierarchyMaster> list = findByItemHierarchyUnique(level0, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByItemHierarchyUnique_Last(String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByItemHierarchyUnique_Last(level0,
                orderByComparator);

        if (itemHierarchyMaster != null) {
            return itemHierarchyMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("level0=");
        msg.append(level0);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchItemHierarchyMasterException(msg.toString());
    }

    /**
     * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByItemHierarchyUnique_Last(String level0,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByItemHierarchyUnique(level0);

        if (count == 0) {
            return null;
        }

        List<ItemHierarchyMaster> list = findByItemHierarchyUnique(level0,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
     *
     * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
     * @param level0 the level0
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster[] findByItemHierarchyUnique_PrevAndNext(
        int itemHierarchyMasterSid, String level0,
        OrderByComparator orderByComparator)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

        Session session = null;

        try {
            session = openSession();

            ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

            array[0] = getByItemHierarchyUnique_PrevAndNext(session,
                    itemHierarchyMaster, level0, orderByComparator, true);

            array[1] = itemHierarchyMaster;

            array[2] = getByItemHierarchyUnique_PrevAndNext(session,
                    itemHierarchyMaster, level0, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ItemHierarchyMaster getByItemHierarchyUnique_PrevAndNext(
        Session session, ItemHierarchyMaster itemHierarchyMaster,
        String level0, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

        boolean bindLevel0 = false;

        if (level0 == null) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
        } else if (level0.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
        } else {
            bindLevel0 = true;

            query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
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
            query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLevel0) {
            qPos.add(level0);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ItemHierarchyMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the item hierarchy masters where level0 = &#63; from the database.
     *
     * @param level0 the level0
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByItemHierarchyUnique(String level0)
        throws SystemException {
        for (ItemHierarchyMaster itemHierarchyMaster : findByItemHierarchyUnique(
                level0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(itemHierarchyMaster);
        }
    }

    /**
     * Returns the number of item hierarchy masters where level0 = &#63;.
     *
     * @param level0 the level0
     * @return the number of matching item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByItemHierarchyUnique(String level0)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE;

        Object[] finderArgs = new Object[] { level0 };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

            boolean bindLevel0 = false;

            if (level0 == null) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
            } else if (level0.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
            } else {
                bindLevel0 = true;

                query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLevel0) {
                    qPos.add(level0);
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
     * Caches the item hierarchy master in the entity cache if it is enabled.
     *
     * @param itemHierarchyMaster the item hierarchy master
     */
    @Override
    public void cacheResult(ItemHierarchyMaster itemHierarchyMaster) {
        EntityCacheUtil.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey(),
            itemHierarchyMaster);

        itemHierarchyMaster.resetOriginalValues();
    }

    /**
     * Caches the item hierarchy masters in the entity cache if it is enabled.
     *
     * @param itemHierarchyMasters the item hierarchy masters
     */
    @Override
    public void cacheResult(List<ItemHierarchyMaster> itemHierarchyMasters) {
        for (ItemHierarchyMaster itemHierarchyMaster : itemHierarchyMasters) {
            if (EntityCacheUtil.getResult(
                        ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ItemHierarchyMasterImpl.class,
                        itemHierarchyMaster.getPrimaryKey()) == null) {
                cacheResult(itemHierarchyMaster);
            } else {
                itemHierarchyMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item hierarchy masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemHierarchyMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemHierarchyMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item hierarchy master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemHierarchyMaster itemHierarchyMaster) {
        EntityCacheUtil.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ItemHierarchyMaster> itemHierarchyMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemHierarchyMaster itemHierarchyMaster : itemHierarchyMasters) {
            EntityCacheUtil.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
                ItemHierarchyMasterImpl.class,
                itemHierarchyMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
     *
     * @param itemHierarchyMasterSid the primary key for the new item hierarchy master
     * @return the new item hierarchy master
     */
    @Override
    public ItemHierarchyMaster create(int itemHierarchyMasterSid) {
        ItemHierarchyMaster itemHierarchyMaster = new ItemHierarchyMasterImpl();

        itemHierarchyMaster.setNew(true);
        itemHierarchyMaster.setPrimaryKey(itemHierarchyMasterSid);

        return itemHierarchyMaster;
    }

    /**
     * Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemHierarchyMasterSid the primary key of the item hierarchy master
     * @return the item hierarchy master that was removed
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster remove(int itemHierarchyMasterSid)
        throws NoSuchItemHierarchyMasterException, SystemException {
        return remove((Serializable) itemHierarchyMasterSid);
    }

    /**
     * Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item hierarchy master
     * @return the item hierarchy master that was removed
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster remove(Serializable primaryKey)
        throws NoSuchItemHierarchyMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemHierarchyMaster itemHierarchyMaster = (ItemHierarchyMaster) session.get(ItemHierarchyMasterImpl.class,
                    primaryKey);

            if (itemHierarchyMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemHierarchyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemHierarchyMaster);
        } catch (NoSuchItemHierarchyMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemHierarchyMaster removeImpl(
        ItemHierarchyMaster itemHierarchyMaster) throws SystemException {
        itemHierarchyMaster = toUnwrappedModel(itemHierarchyMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemHierarchyMaster)) {
                itemHierarchyMaster = (ItemHierarchyMaster) session.get(ItemHierarchyMasterImpl.class,
                        itemHierarchyMaster.getPrimaryKeyObj());
            }

            if (itemHierarchyMaster != null) {
                session.delete(itemHierarchyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemHierarchyMaster != null) {
            clearCache(itemHierarchyMaster);
        }

        return itemHierarchyMaster;
    }

    @Override
    public ItemHierarchyMaster updateImpl(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster)
        throws SystemException {
        itemHierarchyMaster = toUnwrappedModel(itemHierarchyMaster);

        boolean isNew = itemHierarchyMaster.isNew();

        ItemHierarchyMasterModelImpl itemHierarchyMasterModelImpl = (ItemHierarchyMasterModelImpl) itemHierarchyMaster;

        Session session = null;

        try {
            session = openSession();

            if (itemHierarchyMaster.isNew()) {
                session.save(itemHierarchyMaster);

                itemHierarchyMaster.setNew(false);
            } else {
                session.merge(itemHierarchyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ItemHierarchyMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyMasterModelImpl.getOriginalLevel0()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0,
                    args);

                args = new Object[] { itemHierarchyMasterModelImpl.getLevel0() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0,
                    args);
            }

            if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyMasterModelImpl.getOriginalLevel0Alias()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0ALIAS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS,
                    args);

                args = new Object[] {
                        itemHierarchyMasterModelImpl.getLevel0Alias()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0ALIAS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS,
                    args);
            }

            if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyMasterModelImpl.getOriginalLevel0Tag()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0TAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG,
                    args);

                args = new Object[] { itemHierarchyMasterModelImpl.getLevel0Tag() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEVEL0TAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG,
                    args);
            }

            if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        itemHierarchyMasterModelImpl.getOriginalLevel0()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE,
                    args);

                args = new Object[] { itemHierarchyMasterModelImpl.getLevel0() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
            ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey(),
            itemHierarchyMaster);

        return itemHierarchyMaster;
    }

    protected ItemHierarchyMaster toUnwrappedModel(
        ItemHierarchyMaster itemHierarchyMaster) {
        if (itemHierarchyMaster instanceof ItemHierarchyMasterImpl) {
            return itemHierarchyMaster;
        }

        ItemHierarchyMasterImpl itemHierarchyMasterImpl = new ItemHierarchyMasterImpl();

        itemHierarchyMasterImpl.setNew(itemHierarchyMaster.isNew());
        itemHierarchyMasterImpl.setPrimaryKey(itemHierarchyMaster.getPrimaryKey());

        itemHierarchyMasterImpl.setLevel1Alias(itemHierarchyMaster.getLevel1Alias());
        itemHierarchyMasterImpl.setLevel11Alias(itemHierarchyMaster.getLevel11Alias());
        itemHierarchyMasterImpl.setCreatedDate(itemHierarchyMaster.getCreatedDate());
        itemHierarchyMasterImpl.setLevel8Alias(itemHierarchyMaster.getLevel8Alias());
        itemHierarchyMasterImpl.setLevel14Alias(itemHierarchyMaster.getLevel14Alias());
        itemHierarchyMasterImpl.setLevel5Alias(itemHierarchyMaster.getLevel5Alias());
        itemHierarchyMasterImpl.setCreatedBy(itemHierarchyMaster.getCreatedBy());
        itemHierarchyMasterImpl.setLevel10Alias(itemHierarchyMaster.getLevel10Alias());
        itemHierarchyMasterImpl.setItemHierarchyMasterSid(itemHierarchyMaster.getItemHierarchyMasterSid());
        itemHierarchyMasterImpl.setLevel17Alias(itemHierarchyMaster.getLevel17Alias());
        itemHierarchyMasterImpl.setLevel9Alias(itemHierarchyMaster.getLevel9Alias());
        itemHierarchyMasterImpl.setLevel0Alias(itemHierarchyMaster.getLevel0Alias());
        itemHierarchyMasterImpl.setModifiedDate(itemHierarchyMaster.getModifiedDate());
        itemHierarchyMasterImpl.setLevel13Alias(itemHierarchyMaster.getLevel13Alias());
        itemHierarchyMasterImpl.setSource(itemHierarchyMaster.getSource());
        itemHierarchyMasterImpl.setLevel6Alias(itemHierarchyMaster.getLevel6Alias());
        itemHierarchyMasterImpl.setGtnBrand(itemHierarchyMaster.getGtnBrand());
        itemHierarchyMasterImpl.setModifiedBy(itemHierarchyMaster.getModifiedBy());
        itemHierarchyMasterImpl.setLevel3Alias(itemHierarchyMaster.getLevel3Alias());
        itemHierarchyMasterImpl.setLevel16Alias(itemHierarchyMaster.getLevel16Alias());
        itemHierarchyMasterImpl.setBatchId(itemHierarchyMaster.getBatchId());
        itemHierarchyMasterImpl.setLevel19Alias(itemHierarchyMaster.getLevel19Alias());
        itemHierarchyMasterImpl.setLevel20(itemHierarchyMaster.getLevel20());
        itemHierarchyMasterImpl.setLevel2Alias(itemHierarchyMaster.getLevel2Alias());
        itemHierarchyMasterImpl.setLevel20Alias(itemHierarchyMaster.getLevel20Alias());
        itemHierarchyMasterImpl.setGtnTherapeuticClass(itemHierarchyMaster.getGtnTherapeuticClass());
        itemHierarchyMasterImpl.setLevel7Alias(itemHierarchyMaster.getLevel7Alias());
        itemHierarchyMasterImpl.setLevel0(itemHierarchyMaster.getLevel0());
        itemHierarchyMasterImpl.setLevel1(itemHierarchyMaster.getLevel1());
        itemHierarchyMasterImpl.setLevel2(itemHierarchyMaster.getLevel2());
        itemHierarchyMasterImpl.setLevel3(itemHierarchyMaster.getLevel3());
        itemHierarchyMasterImpl.setLevel12Alias(itemHierarchyMaster.getLevel12Alias());
        itemHierarchyMasterImpl.setLevel8(itemHierarchyMaster.getLevel8());
        itemHierarchyMasterImpl.setLevel11(itemHierarchyMaster.getLevel11());
        itemHierarchyMasterImpl.setLevel4Alias(itemHierarchyMaster.getLevel4Alias());
        itemHierarchyMasterImpl.setLevel9(itemHierarchyMaster.getLevel9());
        itemHierarchyMasterImpl.setLevel12(itemHierarchyMaster.getLevel12());
        itemHierarchyMasterImpl.setLevel13(itemHierarchyMaster.getLevel13());
        itemHierarchyMasterImpl.setLevel14(itemHierarchyMaster.getLevel14());
        itemHierarchyMasterImpl.setRecordLockStatus(itemHierarchyMaster.isRecordLockStatus());
        itemHierarchyMasterImpl.setLevel0Tag(itemHierarchyMaster.getLevel0Tag());
        itemHierarchyMasterImpl.setLevel4(itemHierarchyMaster.getLevel4());
        itemHierarchyMasterImpl.setLevel5(itemHierarchyMaster.getLevel5());
        itemHierarchyMasterImpl.setLevel6(itemHierarchyMaster.getLevel6());
        itemHierarchyMasterImpl.setLevel15Alias(itemHierarchyMaster.getLevel15Alias());
        itemHierarchyMasterImpl.setLevel7(itemHierarchyMaster.getLevel7());
        itemHierarchyMasterImpl.setLevel10(itemHierarchyMaster.getLevel10());
        itemHierarchyMasterImpl.setLevel19(itemHierarchyMaster.getLevel19());
        itemHierarchyMasterImpl.setLevel15(itemHierarchyMaster.getLevel15());
        itemHierarchyMasterImpl.setLevel16(itemHierarchyMaster.getLevel16());
        itemHierarchyMasterImpl.setGtnCompany(itemHierarchyMaster.getGtnCompany());
        itemHierarchyMasterImpl.setLevel17(itemHierarchyMaster.getLevel17());
        itemHierarchyMasterImpl.setLevel18Alias(itemHierarchyMaster.getLevel18Alias());
        itemHierarchyMasterImpl.setLevel18(itemHierarchyMaster.getLevel18());
        itemHierarchyMasterImpl.setInboundStatus(itemHierarchyMaster.getInboundStatus());

        return itemHierarchyMasterImpl;
    }

    /**
     * Returns the item hierarchy master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item hierarchy master
     * @return the item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemHierarchyMasterException, SystemException {
        ItemHierarchyMaster itemHierarchyMaster = fetchByPrimaryKey(primaryKey);

        if (itemHierarchyMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemHierarchyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemHierarchyMaster;
    }

    /**
     * Returns the item hierarchy master with the primary key or throws a {@link com.stpl.app.NoSuchItemHierarchyMasterException} if it could not be found.
     *
     * @param itemHierarchyMasterSid the primary key of the item hierarchy master
     * @return the item hierarchy master
     * @throws com.stpl.app.NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster findByPrimaryKey(int itemHierarchyMasterSid)
        throws NoSuchItemHierarchyMasterException, SystemException {
        return findByPrimaryKey((Serializable) itemHierarchyMasterSid);
    }

    /**
     * Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item hierarchy master
     * @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemHierarchyMaster itemHierarchyMaster = (ItemHierarchyMaster) EntityCacheUtil.getResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
                ItemHierarchyMasterImpl.class, primaryKey);

        if (itemHierarchyMaster == _nullItemHierarchyMaster) {
            return null;
        }

        if (itemHierarchyMaster == null) {
            Session session = null;

            try {
                session = openSession();

                itemHierarchyMaster = (ItemHierarchyMaster) session.get(ItemHierarchyMasterImpl.class,
                        primaryKey);

                if (itemHierarchyMaster != null) {
                    cacheResult(itemHierarchyMaster);
                } else {
                    EntityCacheUtil.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ItemHierarchyMasterImpl.class, primaryKey,
                        _nullItemHierarchyMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ItemHierarchyMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemHierarchyMaster;
    }

    /**
     * Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemHierarchyMasterSid the primary key of the item hierarchy master
     * @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemHierarchyMaster fetchByPrimaryKey(int itemHierarchyMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemHierarchyMasterSid);
    }

    /**
     * Returns all the item hierarchy masters.
     *
     * @return the item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item hierarchy masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @return the range of item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item hierarchy masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item hierarchy masters
     * @param end the upper bound of the range of item hierarchy masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item hierarchy masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemHierarchyMaster> findAll(int start, int end,
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

        List<ItemHierarchyMaster> list = (List<ItemHierarchyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMHIERARCHYMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMHIERARCHYMASTER;

                if (pagination) {
                    sql = sql.concat(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemHierarchyMaster>(list);
                } else {
                    list = (List<ItemHierarchyMaster>) QueryUtil.list(q,
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
     * Removes all the item hierarchy masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemHierarchyMaster itemHierarchyMaster : findAll()) {
            remove(itemHierarchyMaster);
        }
    }

    /**
     * Returns the number of item hierarchy masters.
     *
     * @return the number of item hierarchy masters
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

                Query q = session.createQuery(_SQL_COUNT_ITEMHIERARCHYMASTER);

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
     * Initializes the item hierarchy master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemHierarchyMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemHierarchyMaster>> listenersList = new ArrayList<ModelListener<ItemHierarchyMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemHierarchyMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemHierarchyMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
