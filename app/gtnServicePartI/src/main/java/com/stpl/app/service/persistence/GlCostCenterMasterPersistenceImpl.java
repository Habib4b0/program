package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGlCostCenterMasterException;
import com.stpl.app.model.GlCostCenterMaster;
import com.stpl.app.model.impl.GlCostCenterMasterImpl;
import com.stpl.app.model.impl.GlCostCenterMasterModelImpl;
import com.stpl.app.service.persistence.GlCostCenterMasterPersistence;

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
 * The persistence implementation for the gl cost center master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlCostCenterMasterPersistence
 * @see GlCostCenterMasterUtil
 * @generated
 */
public class GlCostCenterMasterPersistenceImpl extends BasePersistenceImpl<GlCostCenterMaster>
    implements GlCostCenterMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GlCostCenterMasterUtil} to access the gl cost center master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GlCostCenterMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCOSTCENTER =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyCostCenter",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCOSTCENTER =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCompanyCostCenter", new String[] { String.class.getName() },
            GlCostCenterMasterModelImpl.COMPANYCOSTCENTER_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCOSTCENTER = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCompanyCostCenter", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_1 =
        "glCostCenterMaster.companyCostCenter IS NULL";
    private static final String _FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_2 =
        "glCostCenterMaster.companyCostCenter = ?";
    private static final String _FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_3 =
        "(glCostCenterMaster.companyCostCenter IS NULL OR glCostCenterMaster.companyCostCenter = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NDC8 = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNdc8",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC8 = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNdc8",
            new String[] { String.class.getName() },
            GlCostCenterMasterModelImpl.NDC8_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NDC8 = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNdc8",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_NDC8_NDC8_1 = "glCostCenterMaster.ndc8 IS NULL";
    private static final String _FINDER_COLUMN_NDC8_NDC8_2 = "glCostCenterMaster.ndc8 = ?";
    private static final String _FINDER_COLUMN_NDC8_NDC8_3 = "(glCostCenterMaster.ndc8 IS NULL OR glCostCenterMaster.ndc8 = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCODE =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyCode",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCODE =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyCode",
            new String[] { String.class.getName() },
            GlCostCenterMasterModelImpl.COMPANYCODE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCODE = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyCode",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_COMPANYCODE_COMPANYCODE_1 = "glCostCenterMaster.companyCode IS NULL";
    private static final String _FINDER_COLUMN_COMPANYCODE_COMPANYCODE_2 = "glCostCenterMaster.companyCode = ?";
    private static final String _FINDER_COLUMN_COMPANYCODE_COMPANYCODE_3 = "(glCostCenterMaster.companyCode IS NULL OR glCostCenterMaster.companyCode = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGlCostCenterUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE =
        new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED,
            GlCostCenterMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByGlCostCenterUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName()
            },
            GlCostCenterMasterModelImpl.COMPANYCOSTCENTER_COLUMN_BITMASK |
            GlCostCenterMasterModelImpl.NDC8_COLUMN_BITMASK |
            GlCostCenterMasterModelImpl.COMPANYCODE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GLCOSTCENTERUNIQUE = new FinderPath(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByGlCostCenterUnique",
            new String[] {
                String.class.getName(), String.class.getName(),
                String.class.getName()
            });
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_1 =
        "glCostCenterMaster.companyCostCenter IS NULL AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_2 =
        "glCostCenterMaster.companyCostCenter = ? AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_3 =
        "(glCostCenterMaster.companyCostCenter IS NULL OR glCostCenterMaster.companyCostCenter = '') AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_1 = "glCostCenterMaster.ndc8 IS NULL AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_2 = "glCostCenterMaster.ndc8 = ? AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_3 = "(glCostCenterMaster.ndc8 IS NULL OR glCostCenterMaster.ndc8 = '') AND ";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_1 = "glCostCenterMaster.companyCode IS NULL";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_2 = "glCostCenterMaster.companyCode = ?";
    private static final String _FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_3 = "(glCostCenterMaster.companyCode IS NULL OR glCostCenterMaster.companyCode = '')";
    private static final String _SQL_SELECT_GLCOSTCENTERMASTER = "SELECT glCostCenterMaster FROM GlCostCenterMaster glCostCenterMaster";
    private static final String _SQL_SELECT_GLCOSTCENTERMASTER_WHERE = "SELECT glCostCenterMaster FROM GlCostCenterMaster glCostCenterMaster WHERE ";
    private static final String _SQL_COUNT_GLCOSTCENTERMASTER = "SELECT COUNT(glCostCenterMaster) FROM GlCostCenterMaster glCostCenterMaster";
    private static final String _SQL_COUNT_GLCOSTCENTERMASTER_WHERE = "SELECT COUNT(glCostCenterMaster) FROM GlCostCenterMaster glCostCenterMaster WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "glCostCenterMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GlCostCenterMaster exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GlCostCenterMaster exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GlCostCenterMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "uploadDate", "createdDate",
                "glCostCenterMasterSid", "batchId", "modifiedDate", "ndc8",
                "recordLockStatus", "companyCode", "source", "companyCostCenter",
                "inboundStatus"
            });
    private static GlCostCenterMaster _nullGlCostCenterMaster = new GlCostCenterMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GlCostCenterMaster> toCacheModel() {
                return _nullGlCostCenterMasterCacheModel;
            }
        };

    private static CacheModel<GlCostCenterMaster> _nullGlCostCenterMasterCacheModel =
        new CacheModel<GlCostCenterMaster>() {
            @Override
            public GlCostCenterMaster toEntityModel() {
                return _nullGlCostCenterMaster;
            }
        };

    public GlCostCenterMasterPersistenceImpl() {
        setModelClass(GlCostCenterMaster.class);
    }

    /**
     * Returns all the gl cost center masters where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @return the matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCostCenter(
        String companyCostCenter) throws SystemException {
        return findByCompanyCostCenter(companyCostCenter, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl cost center masters where companyCostCenter = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCostCenter the company cost center
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @return the range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCostCenter(
        String companyCostCenter, int start, int end) throws SystemException {
        return findByCompanyCostCenter(companyCostCenter, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCostCenter the company cost center
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCostCenter(
        String companyCostCenter, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCOSTCENTER;
            finderArgs = new Object[] { companyCostCenter };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCOSTCENTER;
            finderArgs = new Object[] {
                    companyCostCenter,
                    
                    start, end, orderByComparator
                };
        }

        List<GlCostCenterMaster> list = (List<GlCostCenterMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlCostCenterMaster glCostCenterMaster : list) {
                if (!Validator.equals(companyCostCenter,
                            glCostCenterMaster.getCompanyCostCenter())) {
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

            query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCostCenter = false;

            if (companyCostCenter == null) {
                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_1);
            } else if (companyCostCenter.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_3);
            } else {
                bindCompanyCostCenter = true;

                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCostCenter) {
                    qPos.add(companyCostCenter);
                }

                if (!pagination) {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlCostCenterMaster>(list);
                } else {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
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
     * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByCompanyCostCenter_First(
        String companyCostCenter, OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByCompanyCostCenter_First(companyCostCenter,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCostCenter=");
        msg.append(companyCostCenter);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByCompanyCostCenter_First(
        String companyCostCenter, OrderByComparator orderByComparator)
        throws SystemException {
        List<GlCostCenterMaster> list = findByCompanyCostCenter(companyCostCenter,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByCompanyCostCenter_Last(
        String companyCostCenter, OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByCompanyCostCenter_Last(companyCostCenter,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCostCenter=");
        msg.append(companyCostCenter);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByCompanyCostCenter_Last(
        String companyCostCenter, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByCompanyCostCenter(companyCostCenter);

        if (count == 0) {
            return null;
        }

        List<GlCostCenterMaster> list = findByCompanyCostCenter(companyCostCenter,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63;.
     *
     * @param glCostCenterMasterSid the primary key of the current gl cost center master
     * @param companyCostCenter the company cost center
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster[] findByCompanyCostCenter_PrevAndNext(
        int glCostCenterMasterSid, String companyCostCenter,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = findByPrimaryKey(glCostCenterMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlCostCenterMaster[] array = new GlCostCenterMasterImpl[3];

            array[0] = getByCompanyCostCenter_PrevAndNext(session,
                    glCostCenterMaster, companyCostCenter, orderByComparator,
                    true);

            array[1] = glCostCenterMaster;

            array[2] = getByCompanyCostCenter_PrevAndNext(session,
                    glCostCenterMaster, companyCostCenter, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlCostCenterMaster getByCompanyCostCenter_PrevAndNext(
        Session session, GlCostCenterMaster glCostCenterMaster,
        String companyCostCenter, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

        boolean bindCompanyCostCenter = false;

        if (companyCostCenter == null) {
            query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_1);
        } else if (companyCostCenter.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_3);
        } else {
            bindCompanyCostCenter = true;

            query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_2);
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
            query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyCostCenter) {
            qPos.add(companyCostCenter);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glCostCenterMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlCostCenterMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl cost center masters where companyCostCenter = &#63; from the database.
     *
     * @param companyCostCenter the company cost center
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyCostCenter(String companyCostCenter)
        throws SystemException {
        for (GlCostCenterMaster glCostCenterMaster : findByCompanyCostCenter(
                companyCostCenter, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glCostCenterMaster);
        }
    }

    /**
     * Returns the number of gl cost center masters where companyCostCenter = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @return the number of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyCostCenter(String companyCostCenter)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCOSTCENTER;

        Object[] finderArgs = new Object[] { companyCostCenter };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCostCenter = false;

            if (companyCostCenter == null) {
                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_1);
            } else if (companyCostCenter.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_3);
            } else {
                bindCompanyCostCenter = true;

                query.append(_FINDER_COLUMN_COMPANYCOSTCENTER_COMPANYCOSTCENTER_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCostCenter) {
                    qPos.add(companyCostCenter);
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
     * Returns all the gl cost center masters where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @return the matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByNdc8(String ndc8)
        throws SystemException {
        return findByNdc8(ndc8, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl cost center masters where ndc8 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ndc8 the ndc8
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @return the range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByNdc8(String ndc8, int start, int end)
        throws SystemException {
        return findByNdc8(ndc8, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ndc8 the ndc8
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByNdc8(String ndc8, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC8;
            finderArgs = new Object[] { ndc8 };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NDC8;
            finderArgs = new Object[] { ndc8, start, end, orderByComparator };
        }

        List<GlCostCenterMaster> list = (List<GlCostCenterMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlCostCenterMaster glCostCenterMaster : list) {
                if (!Validator.equals(ndc8, glCostCenterMaster.getNdc8())) {
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

            query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

            boolean bindNdc8 = false;

            if (ndc8 == null) {
                query.append(_FINDER_COLUMN_NDC8_NDC8_1);
            } else if (ndc8.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NDC8_NDC8_3);
            } else {
                bindNdc8 = true;

                query.append(_FINDER_COLUMN_NDC8_NDC8_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindNdc8) {
                    qPos.add(ndc8);
                }

                if (!pagination) {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlCostCenterMaster>(list);
                } else {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
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
     * Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByNdc8_First(String ndc8,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByNdc8_First(ndc8,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ndc8=");
        msg.append(ndc8);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByNdc8_First(String ndc8,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlCostCenterMaster> list = findByNdc8(ndc8, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByNdc8_Last(String ndc8,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByNdc8_Last(ndc8,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ndc8=");
        msg.append(ndc8);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByNdc8_Last(String ndc8,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByNdc8(ndc8);

        if (count == 0) {
            return null;
        }

        List<GlCostCenterMaster> list = findByNdc8(ndc8, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where ndc8 = &#63;.
     *
     * @param glCostCenterMasterSid the primary key of the current gl cost center master
     * @param ndc8 the ndc8
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster[] findByNdc8_PrevAndNext(
        int glCostCenterMasterSid, String ndc8,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = findByPrimaryKey(glCostCenterMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlCostCenterMaster[] array = new GlCostCenterMasterImpl[3];

            array[0] = getByNdc8_PrevAndNext(session, glCostCenterMaster, ndc8,
                    orderByComparator, true);

            array[1] = glCostCenterMaster;

            array[2] = getByNdc8_PrevAndNext(session, glCostCenterMaster, ndc8,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlCostCenterMaster getByNdc8_PrevAndNext(Session session,
        GlCostCenterMaster glCostCenterMaster, String ndc8,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

        boolean bindNdc8 = false;

        if (ndc8 == null) {
            query.append(_FINDER_COLUMN_NDC8_NDC8_1);
        } else if (ndc8.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_NDC8_NDC8_3);
        } else {
            bindNdc8 = true;

            query.append(_FINDER_COLUMN_NDC8_NDC8_2);
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
            query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindNdc8) {
            qPos.add(ndc8);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glCostCenterMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlCostCenterMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl cost center masters where ndc8 = &#63; from the database.
     *
     * @param ndc8 the ndc8
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByNdc8(String ndc8) throws SystemException {
        for (GlCostCenterMaster glCostCenterMaster : findByNdc8(ndc8,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glCostCenterMaster);
        }
    }

    /**
     * Returns the number of gl cost center masters where ndc8 = &#63;.
     *
     * @param ndc8 the ndc8
     * @return the number of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByNdc8(String ndc8) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_NDC8;

        Object[] finderArgs = new Object[] { ndc8 };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLCOSTCENTERMASTER_WHERE);

            boolean bindNdc8 = false;

            if (ndc8 == null) {
                query.append(_FINDER_COLUMN_NDC8_NDC8_1);
            } else if (ndc8.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NDC8_NDC8_3);
            } else {
                bindNdc8 = true;

                query.append(_FINDER_COLUMN_NDC8_NDC8_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindNdc8) {
                    qPos.add(ndc8);
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
     * Returns all the gl cost center masters where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @return the matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCode(String companyCode)
        throws SystemException {
        return findByCompanyCode(companyCode, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl cost center masters where companyCode = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCode the company code
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @return the range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCode(String companyCode,
        int start, int end) throws SystemException {
        return findByCompanyCode(companyCode, start, end, null);
    }

    /**
     * Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCode the company code
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByCompanyCode(String companyCode,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCODE;
            finderArgs = new Object[] { companyCode };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCODE;
            finderArgs = new Object[] { companyCode, start, end, orderByComparator };
        }

        List<GlCostCenterMaster> list = (List<GlCostCenterMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlCostCenterMaster glCostCenterMaster : list) {
                if (!Validator.equals(companyCode,
                            glCostCenterMaster.getCompanyCode())) {
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

            query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCode = false;

            if (companyCode == null) {
                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_1);
            } else if (companyCode.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_3);
            } else {
                bindCompanyCode = true;

                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCode) {
                    qPos.add(companyCode);
                }

                if (!pagination) {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlCostCenterMaster>(list);
                } else {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
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
     * Returns the first gl cost center master in the ordered set where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByCompanyCode_First(String companyCode,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByCompanyCode_First(companyCode,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCode=");
        msg.append(companyCode);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the first gl cost center master in the ordered set where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByCompanyCode_First(String companyCode,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlCostCenterMaster> list = findByCompanyCode(companyCode, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByCompanyCode_Last(String companyCode,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByCompanyCode_Last(companyCode,
                orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCode=");
        msg.append(companyCode);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByCompanyCode_Last(String companyCode,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCompanyCode(companyCode);

        if (count == 0) {
            return null;
        }

        List<GlCostCenterMaster> list = findByCompanyCode(companyCode,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCode = &#63;.
     *
     * @param glCostCenterMasterSid the primary key of the current gl cost center master
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster[] findByCompanyCode_PrevAndNext(
        int glCostCenterMasterSid, String companyCode,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = findByPrimaryKey(glCostCenterMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlCostCenterMaster[] array = new GlCostCenterMasterImpl[3];

            array[0] = getByCompanyCode_PrevAndNext(session,
                    glCostCenterMaster, companyCode, orderByComparator, true);

            array[1] = glCostCenterMaster;

            array[2] = getByCompanyCode_PrevAndNext(session,
                    glCostCenterMaster, companyCode, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlCostCenterMaster getByCompanyCode_PrevAndNext(Session session,
        GlCostCenterMaster glCostCenterMaster, String companyCode,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

        boolean bindCompanyCode = false;

        if (companyCode == null) {
            query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_1);
        } else if (companyCode.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_3);
        } else {
            bindCompanyCode = true;

            query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_2);
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
            query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyCode) {
            qPos.add(companyCode);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glCostCenterMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlCostCenterMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl cost center masters where companyCode = &#63; from the database.
     *
     * @param companyCode the company code
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCompanyCode(String companyCode)
        throws SystemException {
        for (GlCostCenterMaster glCostCenterMaster : findByCompanyCode(
                companyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(glCostCenterMaster);
        }
    }

    /**
     * Returns the number of gl cost center masters where companyCode = &#63;.
     *
     * @param companyCode the company code
     * @return the number of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCompanyCode(String companyCode) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCODE;

        Object[] finderArgs = new Object[] { companyCode };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCode = false;

            if (companyCode == null) {
                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_1);
            } else if (companyCode.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_3);
            } else {
                bindCompanyCode = true;

                query.append(_FINDER_COLUMN_COMPANYCODE_COMPANYCODE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCode) {
                    qPos.add(companyCode);
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
     * Returns all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @return the matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByGlCostCenterUnique(
        String companyCostCenter, String ndc8, String companyCode)
        throws SystemException {
        return findByGlCostCenterUnique(companyCostCenter, ndc8, companyCode,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @return the range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByGlCostCenterUnique(
        String companyCostCenter, String ndc8, String companyCode, int start,
        int end) throws SystemException {
        return findByGlCostCenterUnique(companyCostCenter, ndc8, companyCode,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findByGlCostCenterUnique(
        String companyCostCenter, String ndc8, String companyCode, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE;
            finderArgs = new Object[] { companyCostCenter, ndc8, companyCode };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE;
            finderArgs = new Object[] {
                    companyCostCenter, ndc8, companyCode,
                    
                    start, end, orderByComparator
                };
        }

        List<GlCostCenterMaster> list = (List<GlCostCenterMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (GlCostCenterMaster glCostCenterMaster : list) {
                if (!Validator.equals(companyCostCenter,
                            glCostCenterMaster.getCompanyCostCenter()) ||
                        !Validator.equals(ndc8, glCostCenterMaster.getNdc8()) ||
                        !Validator.equals(companyCode,
                            glCostCenterMaster.getCompanyCode())) {
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

            query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCostCenter = false;

            if (companyCostCenter == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_1);
            } else if (companyCostCenter.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_3);
            } else {
                bindCompanyCostCenter = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_2);
            }

            boolean bindNdc8 = false;

            if (ndc8 == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_1);
            } else if (ndc8.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_3);
            } else {
                bindNdc8 = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_2);
            }

            boolean bindCompanyCode = false;

            if (companyCode == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_1);
            } else if (companyCode.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_3);
            } else {
                bindCompanyCode = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCostCenter) {
                    qPos.add(companyCostCenter);
                }

                if (bindNdc8) {
                    qPos.add(ndc8);
                }

                if (bindCompanyCode) {
                    qPos.add(companyCode);
                }

                if (!pagination) {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlCostCenterMaster>(list);
                } else {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
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
     * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByGlCostCenterUnique_First(
        String companyCostCenter, String ndc8, String companyCode,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByGlCostCenterUnique_First(companyCostCenter,
                ndc8, companyCode, orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCostCenter=");
        msg.append(companyCostCenter);

        msg.append(", ndc8=");
        msg.append(ndc8);

        msg.append(", companyCode=");
        msg.append(companyCode);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByGlCostCenterUnique_First(
        String companyCostCenter, String ndc8, String companyCode,
        OrderByComparator orderByComparator) throws SystemException {
        List<GlCostCenterMaster> list = findByGlCostCenterUnique(companyCostCenter,
                ndc8, companyCode, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByGlCostCenterUnique_Last(
        String companyCostCenter, String ndc8, String companyCode,
        OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByGlCostCenterUnique_Last(companyCostCenter,
                ndc8, companyCode, orderByComparator);

        if (glCostCenterMaster != null) {
            return glCostCenterMaster;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("companyCostCenter=");
        msg.append(companyCostCenter);

        msg.append(", ndc8=");
        msg.append(ndc8);

        msg.append(", companyCode=");
        msg.append(companyCode);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchGlCostCenterMasterException(msg.toString());
    }

    /**
     * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByGlCostCenterUnique_Last(
        String companyCostCenter, String ndc8, String companyCode,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByGlCostCenterUnique(companyCostCenter, ndc8,
                companyCode);

        if (count == 0) {
            return null;
        }

        List<GlCostCenterMaster> list = findByGlCostCenterUnique(companyCostCenter,
                ndc8, companyCode, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param glCostCenterMasterSid the primary key of the current gl cost center master
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster[] findByGlCostCenterUnique_PrevAndNext(
        int glCostCenterMasterSid, String companyCostCenter, String ndc8,
        String companyCode, OrderByComparator orderByComparator)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = findByPrimaryKey(glCostCenterMasterSid);

        Session session = null;

        try {
            session = openSession();

            GlCostCenterMaster[] array = new GlCostCenterMasterImpl[3];

            array[0] = getByGlCostCenterUnique_PrevAndNext(session,
                    glCostCenterMaster, companyCostCenter, ndc8, companyCode,
                    orderByComparator, true);

            array[1] = glCostCenterMaster;

            array[2] = getByGlCostCenterUnique_PrevAndNext(session,
                    glCostCenterMaster, companyCostCenter, ndc8, companyCode,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected GlCostCenterMaster getByGlCostCenterUnique_PrevAndNext(
        Session session, GlCostCenterMaster glCostCenterMaster,
        String companyCostCenter, String ndc8, String companyCode,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_GLCOSTCENTERMASTER_WHERE);

        boolean bindCompanyCostCenter = false;

        if (companyCostCenter == null) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_1);
        } else if (companyCostCenter.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_3);
        } else {
            bindCompanyCostCenter = true;

            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_2);
        }

        boolean bindNdc8 = false;

        if (ndc8 == null) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_1);
        } else if (ndc8.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_3);
        } else {
            bindNdc8 = true;

            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_2);
        }

        boolean bindCompanyCode = false;

        if (companyCode == null) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_1);
        } else if (companyCode.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_3);
        } else {
            bindCompanyCode = true;

            query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_2);
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
            query.append(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindCompanyCostCenter) {
            qPos.add(companyCostCenter);
        }

        if (bindNdc8) {
            qPos.add(ndc8);
        }

        if (bindCompanyCode) {
            qPos.add(companyCode);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(glCostCenterMaster);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<GlCostCenterMaster> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63; from the database.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByGlCostCenterUnique(String companyCostCenter,
        String ndc8, String companyCode) throws SystemException {
        for (GlCostCenterMaster glCostCenterMaster : findByGlCostCenterUnique(
                companyCostCenter, ndc8, companyCode, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(glCostCenterMaster);
        }
    }

    /**
     * Returns the number of gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
     *
     * @param companyCostCenter the company cost center
     * @param ndc8 the ndc8
     * @param companyCode the company code
     * @return the number of matching gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByGlCostCenterUnique(String companyCostCenter, String ndc8,
        String companyCode) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GLCOSTCENTERUNIQUE;

        Object[] finderArgs = new Object[] { companyCostCenter, ndc8, companyCode };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_GLCOSTCENTERMASTER_WHERE);

            boolean bindCompanyCostCenter = false;

            if (companyCostCenter == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_1);
            } else if (companyCostCenter.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_3);
            } else {
                bindCompanyCostCenter = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCOSTCENTER_2);
            }

            boolean bindNdc8 = false;

            if (ndc8 == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_1);
            } else if (ndc8.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_3);
            } else {
                bindNdc8 = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_NDC8_2);
            }

            boolean bindCompanyCode = false;

            if (companyCode == null) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_1);
            } else if (companyCode.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_3);
            } else {
                bindCompanyCode = true;

                query.append(_FINDER_COLUMN_GLCOSTCENTERUNIQUE_COMPANYCODE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindCompanyCostCenter) {
                    qPos.add(companyCostCenter);
                }

                if (bindNdc8) {
                    qPos.add(ndc8);
                }

                if (bindCompanyCode) {
                    qPos.add(companyCode);
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
     * Caches the gl cost center master in the entity cache if it is enabled.
     *
     * @param glCostCenterMaster the gl cost center master
     */
    @Override
    public void cacheResult(GlCostCenterMaster glCostCenterMaster) {
        EntityCacheUtil.putResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterImpl.class, glCostCenterMaster.getPrimaryKey(),
            glCostCenterMaster);

        glCostCenterMaster.resetOriginalValues();
    }

    /**
     * Caches the gl cost center masters in the entity cache if it is enabled.
     *
     * @param glCostCenterMasters the gl cost center masters
     */
    @Override
    public void cacheResult(List<GlCostCenterMaster> glCostCenterMasters) {
        for (GlCostCenterMaster glCostCenterMaster : glCostCenterMasters) {
            if (EntityCacheUtil.getResult(
                        GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
                        GlCostCenterMasterImpl.class,
                        glCostCenterMaster.getPrimaryKey()) == null) {
                cacheResult(glCostCenterMaster);
            } else {
                glCostCenterMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gl cost center masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GlCostCenterMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GlCostCenterMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gl cost center master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GlCostCenterMaster glCostCenterMaster) {
        EntityCacheUtil.removeResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterImpl.class, glCostCenterMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GlCostCenterMaster> glCostCenterMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GlCostCenterMaster glCostCenterMaster : glCostCenterMasters) {
            EntityCacheUtil.removeResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
                GlCostCenterMasterImpl.class, glCostCenterMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new gl cost center master with the primary key. Does not add the gl cost center master to the database.
     *
     * @param glCostCenterMasterSid the primary key for the new gl cost center master
     * @return the new gl cost center master
     */
    @Override
    public GlCostCenterMaster create(int glCostCenterMasterSid) {
        GlCostCenterMaster glCostCenterMaster = new GlCostCenterMasterImpl();

        glCostCenterMaster.setNew(true);
        glCostCenterMaster.setPrimaryKey(glCostCenterMasterSid);

        return glCostCenterMaster;
    }

    /**
     * Removes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param glCostCenterMasterSid the primary key of the gl cost center master
     * @return the gl cost center master that was removed
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster remove(int glCostCenterMasterSid)
        throws NoSuchGlCostCenterMasterException, SystemException {
        return remove((Serializable) glCostCenterMasterSid);
    }

    /**
     * Removes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gl cost center master
     * @return the gl cost center master that was removed
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster remove(Serializable primaryKey)
        throws NoSuchGlCostCenterMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GlCostCenterMaster glCostCenterMaster = (GlCostCenterMaster) session.get(GlCostCenterMasterImpl.class,
                    primaryKey);

            if (glCostCenterMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGlCostCenterMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(glCostCenterMaster);
        } catch (NoSuchGlCostCenterMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GlCostCenterMaster removeImpl(
        GlCostCenterMaster glCostCenterMaster) throws SystemException {
        glCostCenterMaster = toUnwrappedModel(glCostCenterMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(glCostCenterMaster)) {
                glCostCenterMaster = (GlCostCenterMaster) session.get(GlCostCenterMasterImpl.class,
                        glCostCenterMaster.getPrimaryKeyObj());
            }

            if (glCostCenterMaster != null) {
                session.delete(glCostCenterMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (glCostCenterMaster != null) {
            clearCache(glCostCenterMaster);
        }

        return glCostCenterMaster;
    }

    @Override
    public GlCostCenterMaster updateImpl(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster)
        throws SystemException {
        glCostCenterMaster = toUnwrappedModel(glCostCenterMaster);

        boolean isNew = glCostCenterMaster.isNew();

        GlCostCenterMasterModelImpl glCostCenterMasterModelImpl = (GlCostCenterMasterModelImpl) glCostCenterMaster;

        Session session = null;

        try {
            session = openSession();

            if (glCostCenterMaster.isNew()) {
                session.save(glCostCenterMaster);

                glCostCenterMaster.setNew(false);
            } else {
                session.merge(glCostCenterMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !GlCostCenterMasterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((glCostCenterMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCOSTCENTER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glCostCenterMasterModelImpl.getOriginalCompanyCostCenter()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCOSTCENTER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCOSTCENTER,
                    args);

                args = new Object[] {
                        glCostCenterMasterModelImpl.getCompanyCostCenter()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCOSTCENTER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCOSTCENTER,
                    args);
            }

            if ((glCostCenterMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC8.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glCostCenterMasterModelImpl.getOriginalNdc8()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NDC8, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC8,
                    args);

                args = new Object[] { glCostCenterMasterModelImpl.getNdc8() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NDC8, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NDC8,
                    args);
            }

            if ((glCostCenterMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCODE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glCostCenterMasterModelImpl.getOriginalCompanyCode()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCODE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCODE,
                    args);

                args = new Object[] { glCostCenterMasterModelImpl.getCompanyCode() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYCODE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCODE,
                    args);
            }

            if ((glCostCenterMasterModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        glCostCenterMasterModelImpl.getOriginalCompanyCostCenter(),
                        glCostCenterMasterModelImpl.getOriginalNdc8(),
                        glCostCenterMasterModelImpl.getOriginalCompanyCode()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLCOSTCENTERUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE,
                    args);

                args = new Object[] {
                        glCostCenterMasterModelImpl.getCompanyCostCenter(),
                        glCostCenterMasterModelImpl.getNdc8(),
                        glCostCenterMasterModelImpl.getCompanyCode()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLCOSTCENTERUNIQUE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLCOSTCENTERUNIQUE,
                    args);
            }
        }

        EntityCacheUtil.putResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
            GlCostCenterMasterImpl.class, glCostCenterMaster.getPrimaryKey(),
            glCostCenterMaster);

        return glCostCenterMaster;
    }

    protected GlCostCenterMaster toUnwrappedModel(
        GlCostCenterMaster glCostCenterMaster) {
        if (glCostCenterMaster instanceof GlCostCenterMasterImpl) {
            return glCostCenterMaster;
        }

        GlCostCenterMasterImpl glCostCenterMasterImpl = new GlCostCenterMasterImpl();

        glCostCenterMasterImpl.setNew(glCostCenterMaster.isNew());
        glCostCenterMasterImpl.setPrimaryKey(glCostCenterMaster.getPrimaryKey());

        glCostCenterMasterImpl.setCreatedBy(glCostCenterMaster.getCreatedBy());
        glCostCenterMasterImpl.setModifiedBy(glCostCenterMaster.getModifiedBy());
        glCostCenterMasterImpl.setUploadDate(glCostCenterMaster.getUploadDate());
        glCostCenterMasterImpl.setCreatedDate(glCostCenterMaster.getCreatedDate());
        glCostCenterMasterImpl.setGlCostCenterMasterSid(glCostCenterMaster.getGlCostCenterMasterSid());
        glCostCenterMasterImpl.setBatchId(glCostCenterMaster.getBatchId());
        glCostCenterMasterImpl.setModifiedDate(glCostCenterMaster.getModifiedDate());
        glCostCenterMasterImpl.setNdc8(glCostCenterMaster.getNdc8());
        glCostCenterMasterImpl.setRecordLockStatus(glCostCenterMaster.isRecordLockStatus());
        glCostCenterMasterImpl.setCompanyCode(glCostCenterMaster.getCompanyCode());
        glCostCenterMasterImpl.setSource(glCostCenterMaster.getSource());
        glCostCenterMasterImpl.setCompanyCostCenter(glCostCenterMaster.getCompanyCostCenter());
        glCostCenterMasterImpl.setInboundStatus(glCostCenterMaster.getInboundStatus());

        return glCostCenterMasterImpl;
    }

    /**
     * Returns the gl cost center master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gl cost center master
     * @return the gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGlCostCenterMasterException, SystemException {
        GlCostCenterMaster glCostCenterMaster = fetchByPrimaryKey(primaryKey);

        if (glCostCenterMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGlCostCenterMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return glCostCenterMaster;
    }

    /**
     * Returns the gl cost center master with the primary key or throws a {@link com.stpl.app.NoSuchGlCostCenterMasterException} if it could not be found.
     *
     * @param glCostCenterMasterSid the primary key of the gl cost center master
     * @return the gl cost center master
     * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster findByPrimaryKey(int glCostCenterMasterSid)
        throws NoSuchGlCostCenterMasterException, SystemException {
        return findByPrimaryKey((Serializable) glCostCenterMasterSid);
    }

    /**
     * Returns the gl cost center master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gl cost center master
     * @return the gl cost center master, or <code>null</code> if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GlCostCenterMaster glCostCenterMaster = (GlCostCenterMaster) EntityCacheUtil.getResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
                GlCostCenterMasterImpl.class, primaryKey);

        if (glCostCenterMaster == _nullGlCostCenterMaster) {
            return null;
        }

        if (glCostCenterMaster == null) {
            Session session = null;

            try {
                session = openSession();

                glCostCenterMaster = (GlCostCenterMaster) session.get(GlCostCenterMasterImpl.class,
                        primaryKey);

                if (glCostCenterMaster != null) {
                    cacheResult(glCostCenterMaster);
                } else {
                    EntityCacheUtil.putResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
                        GlCostCenterMasterImpl.class, primaryKey,
                        _nullGlCostCenterMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GlCostCenterMasterModelImpl.ENTITY_CACHE_ENABLED,
                    GlCostCenterMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return glCostCenterMaster;
    }

    /**
     * Returns the gl cost center master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param glCostCenterMasterSid the primary key of the gl cost center master
     * @return the gl cost center master, or <code>null</code> if a gl cost center master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GlCostCenterMaster fetchByPrimaryKey(int glCostCenterMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) glCostCenterMasterSid);
    }

    /**
     * Returns all the gl cost center masters.
     *
     * @return the gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gl cost center masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @return the range of gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gl cost center masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gl cost center masters
     * @param end the upper bound of the range of gl cost center masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gl cost center masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GlCostCenterMaster> findAll(int start, int end,
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

        List<GlCostCenterMaster> list = (List<GlCostCenterMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GLCOSTCENTERMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GLCOSTCENTERMASTER;

                if (pagination) {
                    sql = sql.concat(GlCostCenterMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GlCostCenterMaster>(list);
                } else {
                    list = (List<GlCostCenterMaster>) QueryUtil.list(q,
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
     * Removes all the gl cost center masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GlCostCenterMaster glCostCenterMaster : findAll()) {
            remove(glCostCenterMaster);
        }
    }

    /**
     * Returns the number of gl cost center masters.
     *
     * @return the number of gl cost center masters
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

                Query q = session.createQuery(_SQL_COUNT_GLCOSTCENTERMASTER);

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
     * Initializes the gl cost center master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GlCostCenterMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GlCostCenterMaster>> listenersList = new ArrayList<ModelListener<GlCostCenterMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GlCostCenterMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GlCostCenterMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
