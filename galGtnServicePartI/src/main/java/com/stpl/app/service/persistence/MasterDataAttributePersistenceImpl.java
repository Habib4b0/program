package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMasterDataAttributeException;
import com.stpl.app.model.MasterDataAttribute;
import com.stpl.app.model.impl.MasterDataAttributeImpl;
import com.stpl.app.model.impl.MasterDataAttributeModelImpl;
import com.stpl.app.service.persistence.MasterDataAttributePersistence;

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
 * The persistence implementation for the master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataAttributePersistence
 * @see MasterDataAttributeUtil
 * @generated
 */
public class MasterDataAttributePersistenceImpl extends BasePersistenceImpl<MasterDataAttribute>
    implements MasterDataAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MasterDataAttributeUtil} to access the master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MasterDataAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERTYPE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMasterType",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERTYPE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMasterType",
            new String[] { String.class.getName() },
            MasterDataAttributeModelImpl.MASTERTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MASTERTYPE = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMasterType",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MASTERTYPE_MASTERTYPE_1 = "masterDataAttribute.masterType IS NULL";
    private static final String _FINDER_COLUMN_MASTERTYPE_MASTERTYPE_2 = "masterDataAttribute.masterType = ?";
    private static final String _FINDER_COLUMN_MASTERTYPE_MASTERTYPE_3 = "(masterDataAttribute.masterType IS NULL OR masterDataAttribute.masterType = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERATTRIBUTE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMasterAttribute",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERATTRIBUTE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMasterAttribute",
            new String[] { String.class.getName() },
            MasterDataAttributeModelImpl.MASTERATTRIBUTE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MASTERATTRIBUTE = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByMasterAttribute", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_1 =
        "masterDataAttribute.masterAttribute IS NULL";
    private static final String _FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_2 =
        "masterDataAttribute.masterAttribute = ?";
    private static final String _FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_3 =
        "(masterDataAttribute.masterAttribute IS NULL OR masterDataAttribute.masterAttribute = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERID = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMasterId",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERID =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMasterId",
            new String[] { String.class.getName() },
            MasterDataAttributeModelImpl.MASTERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MASTERID = new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMasterId",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_MASTERID_MASTERID_1 = "masterDataAttribute.masterId IS NULL";
    private static final String _FINDER_COLUMN_MASTERID_MASTERID_2 = "masterDataAttribute.masterId = ?";
    private static final String _FINDER_COLUMN_MASTERID_MASTERID_3 = "(masterDataAttribute.masterId IS NULL OR masterDataAttribute.masterId = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByMasterDataAttributeUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
            MasterDataAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByMasterDataAttributeUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName()
            },
            MasterDataAttributeModelImpl.MASTERATTRIBUTE_COLUMN_BITMASK |
            MasterDataAttributeModelImpl.MASTERID_COLUMN_BITMASK |
            MasterDataAttributeModelImpl.MASTERTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MASTERDATAATTRIBUTEUNIQUE =
        new FinderPath(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByMasterDataAttributeUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName()
            });
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_1 =
        "masterDataAttribute.masterAttribute IS NULL AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_2 =
        "masterDataAttribute.masterAttribute = ? AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_3 =
        "(masterDataAttribute.masterAttribute IS NULL OR masterDataAttribute.masterAttribute = '') AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_1 =
        "masterDataAttribute.masterId IS NULL AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_2 =
        "masterDataAttribute.masterId = ? AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_3 =
        "(masterDataAttribute.masterId IS NULL OR masterDataAttribute.masterId = '') AND ";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_1 =
        "masterDataAttribute.masterType IS NULL";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_2 =
        "masterDataAttribute.masterType = ?";
    private static final String _FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_3 =
        "(masterDataAttribute.masterType IS NULL OR masterDataAttribute.masterType = '')";
    private static final String _SQL_SELECT_MASTERDATAATTRIBUTE = "SELECT masterDataAttribute FROM MasterDataAttribute masterDataAttribute";
    private static final String _SQL_SELECT_MASTERDATAATTRIBUTE_WHERE = "SELECT masterDataAttribute FROM MasterDataAttribute masterDataAttribute WHERE ";
    private static final String _SQL_COUNT_MASTERDATAATTRIBUTE = "SELECT COUNT(masterDataAttribute) FROM MasterDataAttribute masterDataAttribute";
    private static final String _SQL_COUNT_MASTERDATAATTRIBUTE_WHERE = "SELECT COUNT(masterDataAttribute) FROM MasterDataAttribute masterDataAttribute WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "masterDataAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MasterDataAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MasterDataAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MasterDataAttributePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "column15", "column14", "column17", "column16", "column11",
                "modifiedBy", "column10", "createdDate", "column13", "column12",
                "batchId", "column19", "column18", "masterAttribute",
                "createdBy", "masterType", "masterId", "column20", "column9",
                "modifiedDate", "column6", "masterDataAttributeSid", "column5",
                "column8", "column7", "recordLockStatus", "column2", "column1",
                "column4", "column3", "source", "inboundStatus"
            });
    private static MasterDataAttribute _nullMasterDataAttribute = new MasterDataAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MasterDataAttribute> toCacheModel() {
                return _nullMasterDataAttributeCacheModel;
            }
        };

    private static CacheModel<MasterDataAttribute> _nullMasterDataAttributeCacheModel =
        new CacheModel<MasterDataAttribute>() {
            @Override
            public MasterDataAttribute toEntityModel() {
                return _nullMasterDataAttribute;
            }
        };

    public MasterDataAttributePersistenceImpl() {
        setModelClass(MasterDataAttribute.class);
    }

    /**
     * Returns all the master data attributes where masterType = &#63;.
     *
     * @param masterType the master type
     * @return the matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterType(String masterType)
        throws SystemException {
        return findByMasterType(masterType, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the master data attributes where masterType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterType the master type
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @return the range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterType(String masterType,
        int start, int end) throws SystemException {
        return findByMasterType(masterType, start, end, null);
    }

    /**
     * Returns an ordered range of all the master data attributes where masterType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterType the master type
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterType(String masterType,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERTYPE;
            finderArgs = new Object[] { masterType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERTYPE;
            finderArgs = new Object[] { masterType, start, end, orderByComparator };
        }

        List<MasterDataAttribute> list = (List<MasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MasterDataAttribute masterDataAttribute : list) {
                if (!Validator.equals(masterType,
                            masterDataAttribute.getMasterType())) {
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

            query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterType = false;

            if (masterType == null) {
                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_1);
            } else if (masterType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_3);
            } else {
                bindMasterType = true;

                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterType) {
                    qPos.add(masterType);
                }

                if (!pagination) {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataAttribute>(list);
                } else {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
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
     * Returns the first master data attribute in the ordered set where masterType = &#63;.
     *
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterType_First(String masterType,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterType_First(masterType,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterType=");
        msg.append(masterType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the first master data attribute in the ordered set where masterType = &#63;.
     *
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterType_First(String masterType,
        OrderByComparator orderByComparator) throws SystemException {
        List<MasterDataAttribute> list = findByMasterType(masterType, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last master data attribute in the ordered set where masterType = &#63;.
     *
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterType_Last(String masterType,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterType_Last(masterType,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterType=");
        msg.append(masterType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the last master data attribute in the ordered set where masterType = &#63;.
     *
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterType_Last(String masterType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMasterType(masterType);

        if (count == 0) {
            return null;
        }

        List<MasterDataAttribute> list = findByMasterType(masterType,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the master data attributes before and after the current master data attribute in the ordered set where masterType = &#63;.
     *
     * @param masterDataAttributeSid the primary key of the current master data attribute
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute[] findByMasterType_PrevAndNext(
        int masterDataAttributeSid, String masterType,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = findByPrimaryKey(masterDataAttributeSid);

        Session session = null;

        try {
            session = openSession();

            MasterDataAttribute[] array = new MasterDataAttributeImpl[3];

            array[0] = getByMasterType_PrevAndNext(session,
                    masterDataAttribute, masterType, orderByComparator, true);

            array[1] = masterDataAttribute;

            array[2] = getByMasterType_PrevAndNext(session,
                    masterDataAttribute, masterType, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MasterDataAttribute getByMasterType_PrevAndNext(Session session,
        MasterDataAttribute masterDataAttribute, String masterType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

        boolean bindMasterType = false;

        if (masterType == null) {
            query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_1);
        } else if (masterType.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_3);
        } else {
            bindMasterType = true;

            query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_2);
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
            query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMasterType) {
            qPos.add(masterType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(masterDataAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MasterDataAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the master data attributes where masterType = &#63; from the database.
     *
     * @param masterType the master type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMasterType(String masterType) throws SystemException {
        for (MasterDataAttribute masterDataAttribute : findByMasterType(
                masterType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(masterDataAttribute);
        }
    }

    /**
     * Returns the number of master data attributes where masterType = &#63;.
     *
     * @param masterType the master type
     * @return the number of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMasterType(String masterType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MASTERTYPE;

        Object[] finderArgs = new Object[] { masterType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterType = false;

            if (masterType == null) {
                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_1);
            } else if (masterType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_3);
            } else {
                bindMasterType = true;

                query.append(_FINDER_COLUMN_MASTERTYPE_MASTERTYPE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterType) {
                    qPos.add(masterType);
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
     * Returns all the master data attributes where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @return the matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterAttribute(
        String masterAttribute) throws SystemException {
        return findByMasterAttribute(masterAttribute, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the master data attributes where masterAttribute = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterAttribute the master attribute
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @return the range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterAttribute(
        String masterAttribute, int start, int end) throws SystemException {
        return findByMasterAttribute(masterAttribute, start, end, null);
    }

    /**
     * Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterAttribute the master attribute
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterAttribute(
        String masterAttribute, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERATTRIBUTE;
            finderArgs = new Object[] { masterAttribute };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERATTRIBUTE;
            finderArgs = new Object[] {
                    masterAttribute,
                    
                    start, end, orderByComparator
                };
        }

        List<MasterDataAttribute> list = (List<MasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MasterDataAttribute masterDataAttribute : list) {
                if (!Validator.equals(masterAttribute,
                            masterDataAttribute.getMasterAttribute())) {
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

            query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterAttribute = false;

            if (masterAttribute == null) {
                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_1);
            } else if (masterAttribute.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_3);
            } else {
                bindMasterAttribute = true;

                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterAttribute) {
                    qPos.add(masterAttribute);
                }

                if (!pagination) {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataAttribute>(list);
                } else {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
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
     * Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterAttribute_First(
        String masterAttribute, OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterAttribute_First(masterAttribute,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterAttribute=");
        msg.append(masterAttribute);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterAttribute_First(
        String masterAttribute, OrderByComparator orderByComparator)
        throws SystemException {
        List<MasterDataAttribute> list = findByMasterAttribute(masterAttribute,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterAttribute_Last(
        String masterAttribute, OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterAttribute_Last(masterAttribute,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterAttribute=");
        msg.append(masterAttribute);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterAttribute_Last(
        String masterAttribute, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByMasterAttribute(masterAttribute);

        if (count == 0) {
            return null;
        }

        List<MasterDataAttribute> list = findByMasterAttribute(masterAttribute,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63;.
     *
     * @param masterDataAttributeSid the primary key of the current master data attribute
     * @param masterAttribute the master attribute
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute[] findByMasterAttribute_PrevAndNext(
        int masterDataAttributeSid, String masterAttribute,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = findByPrimaryKey(masterDataAttributeSid);

        Session session = null;

        try {
            session = openSession();

            MasterDataAttribute[] array = new MasterDataAttributeImpl[3];

            array[0] = getByMasterAttribute_PrevAndNext(session,
                    masterDataAttribute, masterAttribute, orderByComparator,
                    true);

            array[1] = masterDataAttribute;

            array[2] = getByMasterAttribute_PrevAndNext(session,
                    masterDataAttribute, masterAttribute, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MasterDataAttribute getByMasterAttribute_PrevAndNext(
        Session session, MasterDataAttribute masterDataAttribute,
        String masterAttribute, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

        boolean bindMasterAttribute = false;

        if (masterAttribute == null) {
            query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_1);
        } else if (masterAttribute.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_3);
        } else {
            bindMasterAttribute = true;

            query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_2);
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
            query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMasterAttribute) {
            qPos.add(masterAttribute);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(masterDataAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MasterDataAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the master data attributes where masterAttribute = &#63; from the database.
     *
     * @param masterAttribute the master attribute
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMasterAttribute(String masterAttribute)
        throws SystemException {
        for (MasterDataAttribute masterDataAttribute : findByMasterAttribute(
                masterAttribute, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(masterDataAttribute);
        }
    }

    /**
     * Returns the number of master data attributes where masterAttribute = &#63;.
     *
     * @param masterAttribute the master attribute
     * @return the number of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMasterAttribute(String masterAttribute)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MASTERATTRIBUTE;

        Object[] finderArgs = new Object[] { masterAttribute };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterAttribute = false;

            if (masterAttribute == null) {
                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_1);
            } else if (masterAttribute.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_3);
            } else {
                bindMasterAttribute = true;

                query.append(_FINDER_COLUMN_MASTERATTRIBUTE_MASTERATTRIBUTE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterAttribute) {
                    qPos.add(masterAttribute);
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
     * Returns all the master data attributes where masterId = &#63;.
     *
     * @param masterId the master ID
     * @return the matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterId(String masterId)
        throws SystemException {
        return findByMasterId(masterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the master data attributes where masterId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterId the master ID
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @return the range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterId(String masterId, int start,
        int end) throws SystemException {
        return findByMasterId(masterId, start, end, null);
    }

    /**
     * Returns an ordered range of all the master data attributes where masterId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterId the master ID
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterId(String masterId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERID;
            finderArgs = new Object[] { masterId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERID;
            finderArgs = new Object[] { masterId, start, end, orderByComparator };
        }

        List<MasterDataAttribute> list = (List<MasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MasterDataAttribute masterDataAttribute : list) {
                if (!Validator.equals(masterId,
                            masterDataAttribute.getMasterId())) {
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

            query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterId = false;

            if (masterId == null) {
                query.append(_FINDER_COLUMN_MASTERID_MASTERID_1);
            } else if (masterId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERID_MASTERID_3);
            } else {
                bindMasterId = true;

                query.append(_FINDER_COLUMN_MASTERID_MASTERID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterId) {
                    qPos.add(masterId);
                }

                if (!pagination) {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataAttribute>(list);
                } else {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
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
     * Returns the first master data attribute in the ordered set where masterId = &#63;.
     *
     * @param masterId the master ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterId_First(String masterId,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterId_First(masterId,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterId=");
        msg.append(masterId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the first master data attribute in the ordered set where masterId = &#63;.
     *
     * @param masterId the master ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterId_First(String masterId,
        OrderByComparator orderByComparator) throws SystemException {
        List<MasterDataAttribute> list = findByMasterId(masterId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last master data attribute in the ordered set where masterId = &#63;.
     *
     * @param masterId the master ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterId_Last(String masterId,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterId_Last(masterId,
                orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterId=");
        msg.append(masterId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the last master data attribute in the ordered set where masterId = &#63;.
     *
     * @param masterId the master ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterId_Last(String masterId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMasterId(masterId);

        if (count == 0) {
            return null;
        }

        List<MasterDataAttribute> list = findByMasterId(masterId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the master data attributes before and after the current master data attribute in the ordered set where masterId = &#63;.
     *
     * @param masterDataAttributeSid the primary key of the current master data attribute
     * @param masterId the master ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute[] findByMasterId_PrevAndNext(
        int masterDataAttributeSid, String masterId,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = findByPrimaryKey(masterDataAttributeSid);

        Session session = null;

        try {
            session = openSession();

            MasterDataAttribute[] array = new MasterDataAttributeImpl[3];

            array[0] = getByMasterId_PrevAndNext(session, masterDataAttribute,
                    masterId, orderByComparator, true);

            array[1] = masterDataAttribute;

            array[2] = getByMasterId_PrevAndNext(session, masterDataAttribute,
                    masterId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MasterDataAttribute getByMasterId_PrevAndNext(Session session,
        MasterDataAttribute masterDataAttribute, String masterId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

        boolean bindMasterId = false;

        if (masterId == null) {
            query.append(_FINDER_COLUMN_MASTERID_MASTERID_1);
        } else if (masterId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERID_MASTERID_3);
        } else {
            bindMasterId = true;

            query.append(_FINDER_COLUMN_MASTERID_MASTERID_2);
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
            query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMasterId) {
            qPos.add(masterId);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(masterDataAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MasterDataAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the master data attributes where masterId = &#63; from the database.
     *
     * @param masterId the master ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMasterId(String masterId) throws SystemException {
        for (MasterDataAttribute masterDataAttribute : findByMasterId(
                masterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(masterDataAttribute);
        }
    }

    /**
     * Returns the number of master data attributes where masterId = &#63;.
     *
     * @param masterId the master ID
     * @return the number of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMasterId(String masterId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MASTERID;

        Object[] finderArgs = new Object[] { masterId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterId = false;

            if (masterId == null) {
                query.append(_FINDER_COLUMN_MASTERID_MASTERID_1);
            } else if (masterId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERID_MASTERID_3);
            } else {
                bindMasterId = true;

                query.append(_FINDER_COLUMN_MASTERID_MASTERID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterId) {
                    qPos.add(masterId);
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
     * Returns all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @return the matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterDataAttributeUnique(
        String masterAttribute, String masterId, String masterType)
        throws SystemException {
        return findByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @return the range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterDataAttributeUnique(
        String masterAttribute, String masterId, String masterType, int start,
        int end) throws SystemException {
        return findByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType, start, end, null);
    }

    /**
     * Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findByMasterDataAttributeUnique(
        String masterAttribute, String masterId, String masterType, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE;
            finderArgs = new Object[] { masterAttribute, masterId, masterType };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE;
            finderArgs = new Object[] {
                    masterAttribute, masterId, masterType,
                    
                    start, end, orderByComparator
                };
        }

        List<MasterDataAttribute> list = (List<MasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MasterDataAttribute masterDataAttribute : list) {
                if (!Validator.equals(masterAttribute,
                            masterDataAttribute.getMasterAttribute()) ||
                        !Validator.equals(masterId,
                            masterDataAttribute.getMasterId()) ||
                        !Validator.equals(masterType,
                            masterDataAttribute.getMasterType())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(5);
            }

            query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterAttribute = false;

            if (masterAttribute == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_1);
            } else if (masterAttribute.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_3);
            } else {
                bindMasterAttribute = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_2);
            }

            boolean bindMasterId = false;

            if (masterId == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_1);
            } else if (masterId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_3);
            } else {
                bindMasterId = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_2);
            }

            boolean bindMasterType = false;

            if (masterType == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_1);
            } else if (masterType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_3);
            } else {
                bindMasterType = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterAttribute) {
                    qPos.add(masterAttribute);
                }

                if (bindMasterId) {
                    qPos.add(masterId);
                }

                if (bindMasterType) {
                    qPos.add(masterType);
                }

                if (!pagination) {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataAttribute>(list);
                } else {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
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
     * Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterDataAttributeUnique_First(
        String masterAttribute, String masterId, String masterType,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterDataAttributeUnique_First(masterAttribute,
                masterId, masterType, orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterAttribute=");
        msg.append(masterAttribute);

        msg.append(", masterId=");
        msg.append(masterId);

        msg.append(", masterType=");
        msg.append(masterType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterDataAttributeUnique_First(
        String masterAttribute, String masterId, String masterType,
        OrderByComparator orderByComparator) throws SystemException {
        List<MasterDataAttribute> list = findByMasterDataAttributeUnique(masterAttribute,
                masterId, masterType, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByMasterDataAttributeUnique_Last(
        String masterAttribute, String masterId, String masterType,
        OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByMasterDataAttributeUnique_Last(masterAttribute,
                masterId, masterType, orderByComparator);

        if (masterDataAttribute != null) {
            return masterDataAttribute;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("masterAttribute=");
        msg.append(masterAttribute);

        msg.append(", masterId=");
        msg.append(masterId);

        msg.append(", masterType=");
        msg.append(masterType);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMasterDataAttributeException(msg.toString());
    }

    /**
     * Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByMasterDataAttributeUnique_Last(
        String masterAttribute, String masterId, String masterType,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMasterDataAttributeUnique(masterAttribute, masterId,
                masterType);

        if (count == 0) {
            return null;
        }

        List<MasterDataAttribute> list = findByMasterDataAttributeUnique(masterAttribute,
                masterId, masterType, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterDataAttributeSid the primary key of the current master data attribute
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute[] findByMasterDataAttributeUnique_PrevAndNext(
        int masterDataAttributeSid, String masterAttribute, String masterId,
        String masterType, OrderByComparator orderByComparator)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = findByPrimaryKey(masterDataAttributeSid);

        Session session = null;

        try {
            session = openSession();

            MasterDataAttribute[] array = new MasterDataAttributeImpl[3];

            array[0] = getByMasterDataAttributeUnique_PrevAndNext(session,
                    masterDataAttribute, masterAttribute, masterId, masterType,
                    orderByComparator, true);

            array[1] = masterDataAttribute;

            array[2] = getByMasterDataAttributeUnique_PrevAndNext(session,
                    masterDataAttribute, masterAttribute, masterId, masterType,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MasterDataAttribute getByMasterDataAttributeUnique_PrevAndNext(
        Session session, MasterDataAttribute masterDataAttribute,
        String masterAttribute, String masterId, String masterType,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MASTERDATAATTRIBUTE_WHERE);

        boolean bindMasterAttribute = false;

        if (masterAttribute == null) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_1);
        } else if (masterAttribute.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_3);
        } else {
            bindMasterAttribute = true;

            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_2);
        }

        boolean bindMasterId = false;

        if (masterId == null) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_1);
        } else if (masterId.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_3);
        } else {
            bindMasterId = true;

            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_2);
        }

        boolean bindMasterType = false;

        if (masterType == null) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_1);
        } else if (masterType.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_3);
        } else {
            bindMasterType = true;

            query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_2);
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
            query.append(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindMasterAttribute) {
            qPos.add(masterAttribute);
        }

        if (bindMasterId) {
            qPos.add(masterId);
        }

        if (bindMasterType) {
            qPos.add(masterType);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(masterDataAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MasterDataAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63; from the database.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMasterDataAttributeUnique(String masterAttribute,
        String masterId, String masterType) throws SystemException {
        for (MasterDataAttribute masterDataAttribute : findByMasterDataAttributeUnique(
                masterAttribute, masterId, masterType, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(masterDataAttribute);
        }
    }

    /**
     * Returns the number of master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
     *
     * @param masterAttribute the master attribute
     * @param masterId the master ID
     * @param masterType the master type
     * @return the number of matching master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMasterDataAttributeUnique(String masterAttribute,
        String masterId, String masterType) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MASTERDATAATTRIBUTEUNIQUE;

        Object[] finderArgs = new Object[] { masterAttribute, masterId, masterType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_MASTERDATAATTRIBUTE_WHERE);

            boolean bindMasterAttribute = false;

            if (masterAttribute == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_1);
            } else if (masterAttribute.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_3);
            } else {
                bindMasterAttribute = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERATTRIBUTE_2);
            }

            boolean bindMasterId = false;

            if (masterId == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_1);
            } else if (masterId.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_3);
            } else {
                bindMasterId = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERID_2);
            }

            boolean bindMasterType = false;

            if (masterType == null) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_1);
            } else if (masterType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_3);
            } else {
                bindMasterType = true;

                query.append(_FINDER_COLUMN_MASTERDATAATTRIBUTEUNIQUE_MASTERTYPE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindMasterAttribute) {
                    qPos.add(masterAttribute);
                }

                if (bindMasterId) {
                    qPos.add(masterId);
                }

                if (bindMasterType) {
                    qPos.add(masterType);
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
     * Caches the master data attribute in the entity cache if it is enabled.
     *
     * @param masterDataAttribute the master data attribute
     */
    @Override
    public void cacheResult(MasterDataAttribute masterDataAttribute) {
        EntityCacheUtil.putResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeImpl.class, masterDataAttribute.getPrimaryKey(),
            masterDataAttribute);

        masterDataAttribute.resetOriginalValues();
    }

    /**
     * Caches the master data attributes in the entity cache if it is enabled.
     *
     * @param masterDataAttributes the master data attributes
     */
    @Override
    public void cacheResult(List<MasterDataAttribute> masterDataAttributes) {
        for (MasterDataAttribute masterDataAttribute : masterDataAttributes) {
            if (EntityCacheUtil.getResult(
                        MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        MasterDataAttributeImpl.class,
                        masterDataAttribute.getPrimaryKey()) == null) {
                cacheResult(masterDataAttribute);
            } else {
                masterDataAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all master data attributes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MasterDataAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MasterDataAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the master data attribute.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MasterDataAttribute masterDataAttribute) {
        EntityCacheUtil.removeResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeImpl.class, masterDataAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MasterDataAttribute> masterDataAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MasterDataAttribute masterDataAttribute : masterDataAttributes) {
            EntityCacheUtil.removeResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                MasterDataAttributeImpl.class,
                masterDataAttribute.getPrimaryKey());
        }
    }

    /**
     * Creates a new master data attribute with the primary key. Does not add the master data attribute to the database.
     *
     * @param masterDataAttributeSid the primary key for the new master data attribute
     * @return the new master data attribute
     */
    @Override
    public MasterDataAttribute create(int masterDataAttributeSid) {
        MasterDataAttribute masterDataAttribute = new MasterDataAttributeImpl();

        masterDataAttribute.setNew(true);
        masterDataAttribute.setPrimaryKey(masterDataAttributeSid);

        return masterDataAttribute;
    }

    /**
     * Removes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param masterDataAttributeSid the primary key of the master data attribute
     * @return the master data attribute that was removed
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute remove(int masterDataAttributeSid)
        throws NoSuchMasterDataAttributeException, SystemException {
        return remove((Serializable) masterDataAttributeSid);
    }

    /**
     * Removes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the master data attribute
     * @return the master data attribute that was removed
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute remove(Serializable primaryKey)
        throws NoSuchMasterDataAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MasterDataAttribute masterDataAttribute = (MasterDataAttribute) session.get(MasterDataAttributeImpl.class,
                    primaryKey);

            if (masterDataAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(masterDataAttribute);
        } catch (NoSuchMasterDataAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MasterDataAttribute removeImpl(
        MasterDataAttribute masterDataAttribute) throws SystemException {
        masterDataAttribute = toUnwrappedModel(masterDataAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(masterDataAttribute)) {
                masterDataAttribute = (MasterDataAttribute) session.get(MasterDataAttributeImpl.class,
                        masterDataAttribute.getPrimaryKeyObj());
            }

            if (masterDataAttribute != null) {
                session.delete(masterDataAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (masterDataAttribute != null) {
            clearCache(masterDataAttribute);
        }

        return masterDataAttribute;
    }

    @Override
    public MasterDataAttribute updateImpl(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute)
        throws SystemException {
        masterDataAttribute = toUnwrappedModel(masterDataAttribute);

        boolean isNew = masterDataAttribute.isNew();

        MasterDataAttributeModelImpl masterDataAttributeModelImpl = (MasterDataAttributeModelImpl) masterDataAttribute;

        Session session = null;

        try {
            session = openSession();

            if (masterDataAttribute.isNew()) {
                session.save(masterDataAttribute);

                masterDataAttribute.setNew(false);
            } else {
                session.merge(masterDataAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !MasterDataAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((masterDataAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        masterDataAttributeModelImpl.getOriginalMasterType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERTYPE,
                    args);

                args = new Object[] { masterDataAttributeModelImpl.getMasterType() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERTYPE,
                    args);
            }

            if ((masterDataAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERATTRIBUTE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        masterDataAttributeModelImpl.getOriginalMasterAttribute()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERATTRIBUTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERATTRIBUTE,
                    args);

                args = new Object[] {
                        masterDataAttributeModelImpl.getMasterAttribute()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERATTRIBUTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERATTRIBUTE,
                    args);
            }

            if ((masterDataAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        masterDataAttributeModelImpl.getOriginalMasterId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERID,
                    args);

                args = new Object[] { masterDataAttributeModelImpl.getMasterId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERID,
                    args);
            }

            if ((masterDataAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        masterDataAttributeModelImpl.getOriginalMasterAttribute(),
                        masterDataAttributeModelImpl.getOriginalMasterId(),
                        masterDataAttributeModelImpl.getOriginalMasterType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERDATAATTRIBUTEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE,
                    args);

                args = new Object[] {
                        masterDataAttributeModelImpl.getMasterAttribute(),
                        masterDataAttributeModelImpl.getMasterId(),
                        masterDataAttributeModelImpl.getMasterType()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MASTERDATAATTRIBUTEUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MASTERDATAATTRIBUTEUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataAttributeImpl.class, masterDataAttribute.getPrimaryKey(),
            masterDataAttribute);

        return masterDataAttribute;
    }

    protected MasterDataAttribute toUnwrappedModel(
        MasterDataAttribute masterDataAttribute) {
        if (masterDataAttribute instanceof MasterDataAttributeImpl) {
            return masterDataAttribute;
        }

        MasterDataAttributeImpl masterDataAttributeImpl = new MasterDataAttributeImpl();

        masterDataAttributeImpl.setNew(masterDataAttribute.isNew());
        masterDataAttributeImpl.setPrimaryKey(masterDataAttribute.getPrimaryKey());

        masterDataAttributeImpl.setColumn15(masterDataAttribute.getColumn15());
        masterDataAttributeImpl.setColumn14(masterDataAttribute.getColumn14());
        masterDataAttributeImpl.setColumn17(masterDataAttribute.getColumn17());
        masterDataAttributeImpl.setColumn16(masterDataAttribute.getColumn16());
        masterDataAttributeImpl.setColumn11(masterDataAttribute.getColumn11());
        masterDataAttributeImpl.setModifiedBy(masterDataAttribute.getModifiedBy());
        masterDataAttributeImpl.setColumn10(masterDataAttribute.getColumn10());
        masterDataAttributeImpl.setCreatedDate(masterDataAttribute.getCreatedDate());
        masterDataAttributeImpl.setColumn13(masterDataAttribute.getColumn13());
        masterDataAttributeImpl.setColumn12(masterDataAttribute.getColumn12());
        masterDataAttributeImpl.setBatchId(masterDataAttribute.getBatchId());
        masterDataAttributeImpl.setColumn19(masterDataAttribute.getColumn19());
        masterDataAttributeImpl.setColumn18(masterDataAttribute.getColumn18());
        masterDataAttributeImpl.setMasterAttribute(masterDataAttribute.getMasterAttribute());
        masterDataAttributeImpl.setCreatedBy(masterDataAttribute.getCreatedBy());
        masterDataAttributeImpl.setMasterType(masterDataAttribute.getMasterType());
        masterDataAttributeImpl.setMasterId(masterDataAttribute.getMasterId());
        masterDataAttributeImpl.setColumn20(masterDataAttribute.getColumn20());
        masterDataAttributeImpl.setColumn9(masterDataAttribute.getColumn9());
        masterDataAttributeImpl.setModifiedDate(masterDataAttribute.getModifiedDate());
        masterDataAttributeImpl.setColumn6(masterDataAttribute.getColumn6());
        masterDataAttributeImpl.setMasterDataAttributeSid(masterDataAttribute.getMasterDataAttributeSid());
        masterDataAttributeImpl.setColumn5(masterDataAttribute.getColumn5());
        masterDataAttributeImpl.setColumn8(masterDataAttribute.getColumn8());
        masterDataAttributeImpl.setColumn7(masterDataAttribute.getColumn7());
        masterDataAttributeImpl.setRecordLockStatus(masterDataAttribute.isRecordLockStatus());
        masterDataAttributeImpl.setColumn2(masterDataAttribute.getColumn2());
        masterDataAttributeImpl.setColumn1(masterDataAttribute.getColumn1());
        masterDataAttributeImpl.setColumn4(masterDataAttribute.getColumn4());
        masterDataAttributeImpl.setColumn3(masterDataAttribute.getColumn3());
        masterDataAttributeImpl.setSource(masterDataAttribute.getSource());
        masterDataAttributeImpl.setInboundStatus(masterDataAttribute.getInboundStatus());

        return masterDataAttributeImpl;
    }

    /**
     * Returns the master data attribute with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the master data attribute
     * @return the master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMasterDataAttributeException, SystemException {
        MasterDataAttribute masterDataAttribute = fetchByPrimaryKey(primaryKey);

        if (masterDataAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return masterDataAttribute;
    }

    /**
     * Returns the master data attribute with the primary key or throws a {@link com.stpl.app.NoSuchMasterDataAttributeException} if it could not be found.
     *
     * @param masterDataAttributeSid the primary key of the master data attribute
     * @return the master data attribute
     * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute findByPrimaryKey(int masterDataAttributeSid)
        throws NoSuchMasterDataAttributeException, SystemException {
        return findByPrimaryKey((Serializable) masterDataAttributeSid);
    }

    /**
     * Returns the master data attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the master data attribute
     * @return the master data attribute, or <code>null</code> if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MasterDataAttribute masterDataAttribute = (MasterDataAttribute) EntityCacheUtil.getResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                MasterDataAttributeImpl.class, primaryKey);

        if (masterDataAttribute == _nullMasterDataAttribute) {
            return null;
        }

        if (masterDataAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                masterDataAttribute = (MasterDataAttribute) session.get(MasterDataAttributeImpl.class,
                        primaryKey);

                if (masterDataAttribute != null) {
                    cacheResult(masterDataAttribute);
                } else {
                    EntityCacheUtil.putResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        MasterDataAttributeImpl.class, primaryKey,
                        _nullMasterDataAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    MasterDataAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return masterDataAttribute;
    }

    /**
     * Returns the master data attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param masterDataAttributeSid the primary key of the master data attribute
     * @return the master data attribute, or <code>null</code> if a master data attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataAttribute fetchByPrimaryKey(int masterDataAttributeSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) masterDataAttributeSid);
    }

    /**
     * Returns all the master data attributes.
     *
     * @return the master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the master data attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @return the range of master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the master data attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of master data attributes
     * @param end the upper bound of the range of master data attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of master data attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataAttribute> findAll(int start, int end,
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

        List<MasterDataAttribute> list = (List<MasterDataAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MASTERDATAATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MASTERDATAATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(MasterDataAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataAttribute>(list);
                } else {
                    list = (List<MasterDataAttribute>) QueryUtil.list(q,
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
     * Removes all the master data attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MasterDataAttribute masterDataAttribute : findAll()) {
            remove(masterDataAttribute);
        }
    }

    /**
     * Returns the number of master data attributes.
     *
     * @return the number of master data attributes
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

                Query q = session.createQuery(_SQL_COUNT_MASTERDATAATTRIBUTE);

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
     * Initializes the master data attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MasterDataAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MasterDataAttribute>> listenersList = new ArrayList<ModelListener<MasterDataAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MasterDataAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MasterDataAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
