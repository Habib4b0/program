/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchProjectionMasterException;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.impl.ProjectionMasterImpl;
import com.stpl.app.model.impl.ProjectionMasterModelImpl;
import com.stpl.app.service.persistence.ProjectionMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.ProjectionMasterUtil
 * @generated
 */
@ProviderType
public class ProjectionMasterPersistenceImpl extends BasePersistenceImpl<ProjectionMaster>
	implements ProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionMasterUtil} to access the projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			ProjectionMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			ProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionMasterPersistenceImpl() {
		setModelClass(ProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("productHierarchyLevel", "PRODUCT_HIERARCHY_LEVEL");
			dbColumnNames.put("saveFlag", "SAVE_FLAG");
			dbColumnNames.put("projectionName", "PROJECTION_NAME");
			dbColumnNames.put("toDate", "TO_DATE");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("forecastingType", "FORECASTING_TYPE");
			dbColumnNames.put("productHierVersionNo", "PRODUCT_HIER_VERSION_NO");
			dbColumnNames.put("customerHierVersionNo",
				"CUSTOMER_HIER_VERSION_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("customerHierarchyLevel",
				"CUSTOMER_HIERARCHY_LEVEL");
			dbColumnNames.put("fromDate", "FROM_DATE");
			dbColumnNames.put("productHierarchySid", "PRODUCT_HIERARCHY_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("customerHierarchySid", "CUSTOMER_HIERARCHY_SID");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("brandType", "BRAND_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("projectionDescription", "PROJECTION_DESCRIPTION");
			dbColumnNames.put("isApproved", "IS_APPROVED");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("customerHierarchyInnerLevel",
				"CUSTOMER_HIERARCHY_INNER_LEVEL");
			dbColumnNames.put("productHierarchyInnerLevel",
				"PRODUCT_HIERARCHY_INNER_LEVEL");
			dbColumnNames.put("custRelationshipBuilderSid",
				"CUST_RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("prodRelationshipBuilderSid",
				"PROD_RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("discountType", "DISCOUNT_TYPE");
			dbColumnNames.put("businessUnit", "BUSINESS_UNIT");
			dbColumnNames.put("deductionHierarchySid", "DEDUCTION_HIERARCHY_SID");
			dbColumnNames.put("dedRelationshipBuilderSid",
				"DED_RELATIONSHIP_BULDER_SID");
			dbColumnNames.put("projectionCustVersionNo",
				"PROJECTION_CUST_VERSION");
			dbColumnNames.put("projectionProdVersionNo",
				"PROJECTION_PROD_VERSION");
			dbColumnNames.put("forecastEligibleDate", "FORECAST_ELIGIBLE_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the projection master in the entity cache if it is enabled.
	 *
	 * @param projectionMaster the projection master
	 */
	@Override
	public void cacheResult(ProjectionMaster projectionMaster) {
		entityCache.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterImpl.class, projectionMaster.getPrimaryKey(),
			projectionMaster);

		projectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the projection masters in the entity cache if it is enabled.
	 *
	 * @param projectionMasters the projection masters
	 */
	@Override
	public void cacheResult(List<ProjectionMaster> projectionMasters) {
		for (ProjectionMaster projectionMaster : projectionMasters) {
			if (entityCache.getResult(
						ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionMasterImpl.class,
						projectionMaster.getPrimaryKey()) == null) {
				cacheResult(projectionMaster);
			}
			else {
				projectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionMaster projectionMaster) {
		entityCache.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterImpl.class, projectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectionMaster> projectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionMaster projectionMaster : projectionMasters) {
			entityCache.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionMasterImpl.class, projectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection master with the primary key. Does not add the projection master to the database.
	 *
	 * @param projectionMasterSid the primary key for the new projection master
	 * @return the new projection master
	 */
	@Override
	public ProjectionMaster create(int projectionMasterSid) {
		ProjectionMaster projectionMaster = new ProjectionMasterImpl();

		projectionMaster.setNew(true);
		projectionMaster.setPrimaryKey(projectionMasterSid);

		return projectionMaster;
	}

	/**
	 * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionMasterSid the primary key of the projection master
	 * @return the projection master that was removed
	 * @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster remove(int projectionMasterSid)
		throws NoSuchProjectionMasterException {
		return remove((Serializable)projectionMasterSid);
	}

	/**
	 * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection master
	 * @return the projection master that was removed
	 * @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster remove(Serializable primaryKey)
		throws NoSuchProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			ProjectionMaster projectionMaster = (ProjectionMaster)session.get(ProjectionMasterImpl.class,
					primaryKey);

			if (projectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionMaster);
		}
		catch (NoSuchProjectionMasterException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ProjectionMaster removeImpl(ProjectionMaster projectionMaster) {
		projectionMaster = toUnwrappedModel(projectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionMaster)) {
				projectionMaster = (ProjectionMaster)session.get(ProjectionMasterImpl.class,
						projectionMaster.getPrimaryKeyObj());
			}

			if (projectionMaster != null) {
				session.delete(projectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionMaster != null) {
			clearCache(projectionMaster);
		}

		return projectionMaster;
	}

	@Override
	public ProjectionMaster updateImpl(ProjectionMaster projectionMaster) {
		projectionMaster = toUnwrappedModel(projectionMaster);

		boolean isNew = projectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionMaster.isNew()) {
				session.save(projectionMaster);

				projectionMaster.setNew(false);
			}
			else {
				projectionMaster = (ProjectionMaster)session.merge(projectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionMasterImpl.class, projectionMaster.getPrimaryKey(),
			projectionMaster, false);

		projectionMaster.resetOriginalValues();

		return projectionMaster;
	}

	protected ProjectionMaster toUnwrappedModel(
		ProjectionMaster projectionMaster) {
		if (projectionMaster instanceof ProjectionMasterImpl) {
			return projectionMaster;
		}

		ProjectionMasterImpl projectionMasterImpl = new ProjectionMasterImpl();

		projectionMasterImpl.setNew(projectionMaster.isNew());
		projectionMasterImpl.setPrimaryKey(projectionMaster.getPrimaryKey());

		projectionMasterImpl.setProductHierarchyLevel(projectionMaster.getProductHierarchyLevel());
		projectionMasterImpl.setSaveFlag(projectionMaster.isSaveFlag());
		projectionMasterImpl.setProjectionName(projectionMaster.getProjectionName());
		projectionMasterImpl.setToDate(projectionMaster.getToDate());
		projectionMasterImpl.setProjectionMasterSid(projectionMaster.getProjectionMasterSid());
		projectionMasterImpl.setForecastingType(projectionMaster.getForecastingType());
		projectionMasterImpl.setProductHierVersionNo(projectionMaster.getProductHierVersionNo());
		projectionMasterImpl.setCustomerHierVersionNo(projectionMaster.getCustomerHierVersionNo());
		projectionMasterImpl.setModifiedDate(projectionMaster.getModifiedDate());
		projectionMasterImpl.setCustomerHierarchyLevel(projectionMaster.getCustomerHierarchyLevel());
		projectionMasterImpl.setFromDate(projectionMaster.getFromDate());
		projectionMasterImpl.setProductHierarchySid(projectionMaster.getProductHierarchySid());
		projectionMasterImpl.setCreatedDate(projectionMaster.getCreatedDate());
		projectionMasterImpl.setCreatedBy(projectionMaster.getCreatedBy());
		projectionMasterImpl.setCustomerHierarchySid(projectionMaster.getCustomerHierarchySid());
		projectionMasterImpl.setCompanyGroupSid(projectionMaster.getCompanyGroupSid());
		projectionMasterImpl.setBrandType(projectionMaster.isBrandType());
		projectionMasterImpl.setModifiedBy(projectionMaster.getModifiedBy());
		projectionMasterImpl.setProjectionDescription(projectionMaster.getProjectionDescription());
		projectionMasterImpl.setIsApproved(projectionMaster.getIsApproved());
		projectionMasterImpl.setItemGroupSid(projectionMaster.getItemGroupSid());
		projectionMasterImpl.setCompanyMasterSid(projectionMaster.getCompanyMasterSid());
		projectionMasterImpl.setCustomerHierarchyInnerLevel(projectionMaster.getCustomerHierarchyInnerLevel());
		projectionMasterImpl.setProductHierarchyInnerLevel(projectionMaster.getProductHierarchyInnerLevel());
		projectionMasterImpl.setCustRelationshipBuilderSid(projectionMaster.getCustRelationshipBuilderSid());
		projectionMasterImpl.setProdRelationshipBuilderSid(projectionMaster.getProdRelationshipBuilderSid());
		projectionMasterImpl.setDiscountType(projectionMaster.getDiscountType());
		projectionMasterImpl.setBusinessUnit(projectionMaster.getBusinessUnit());
		projectionMasterImpl.setDeductionHierarchySid(projectionMaster.getDeductionHierarchySid());
		projectionMasterImpl.setDedRelationshipBuilderSid(projectionMaster.getDedRelationshipBuilderSid());
		projectionMasterImpl.setProjectionCustVersionNo(projectionMaster.getProjectionCustVersionNo());
		projectionMasterImpl.setProjectionProdVersionNo(projectionMaster.getProjectionProdVersionNo());
		projectionMasterImpl.setForecastEligibleDate(projectionMaster.getForecastEligibleDate());

		return projectionMasterImpl;
	}

	/**
	 * Returns the projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection master
	 * @return the projection master
	 * @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionMasterException {
		ProjectionMaster projectionMaster = fetchByPrimaryKey(primaryKey);

		if (projectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionMaster;
	}

	/**
	 * Returns the projection master with the primary key or throws a {@link NoSuchProjectionMasterException} if it could not be found.
	 *
	 * @param projectionMasterSid the primary key of the projection master
	 * @return the projection master
	 * @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster findByPrimaryKey(int projectionMasterSid)
		throws NoSuchProjectionMasterException {
		return findByPrimaryKey((Serializable)projectionMasterSid);
	}

	/**
	 * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection master
	 * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionMaster projectionMaster = (ProjectionMaster)serializable;

		if (projectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				projectionMaster = (ProjectionMaster)session.get(ProjectionMasterImpl.class,
						primaryKey);

				if (projectionMaster != null) {
					cacheResult(projectionMaster);
				}
				else {
					entityCache.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionMaster;
	}

	/**
	 * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionMasterSid the primary key of the projection master
	 * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
	 */
	@Override
	public ProjectionMaster fetchByPrimaryKey(int projectionMasterSid) {
		return fetchByPrimaryKey((Serializable)projectionMasterSid);
	}

	@Override
	public Map<Serializable, ProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionMaster> map = new HashMap<Serializable, ProjectionMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionMaster projectionMaster = fetchByPrimaryKey(primaryKey);

			if (projectionMaster != null) {
				map.put(primaryKey, projectionMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONMASTER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ProjectionMaster projectionMaster : (List<ProjectionMaster>)q.list()) {
				map.put(projectionMaster.getPrimaryKeyObj(), projectionMaster);

				cacheResult(projectionMaster);

				uncachedPrimaryKeys.remove(projectionMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionMasterImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the projection masters.
	 *
	 * @return the projection masters
	 */
	@Override
	public List<ProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection masters
	 * @param end the upper bound of the range of projection masters (not inclusive)
	 * @return the range of projection masters
	 */
	@Override
	public List<ProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection masters
	 * @param end the upper bound of the range of projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection masters
	 */
	@Override
	public List<ProjectionMaster> findAll(int start, int end,
		OrderByComparator<ProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection masters
	 * @param end the upper bound of the range of projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection masters
	 */
	@Override
	public List<ProjectionMaster> findAll(int start, int end,
		OrderByComparator<ProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(ProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionMaster projectionMaster : findAll()) {
			remove(projectionMaster);
		}
	}

	/**
	 * Returns the number of projection masters.
	 *
	 * @return the number of projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONMASTER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONMASTER = "SELECT projectionMaster FROM ProjectionMaster projectionMaster";
	private static final String _SQL_SELECT_PROJECTIONMASTER_WHERE_PKS_IN = "SELECT projectionMaster FROM ProjectionMaster projectionMaster WHERE PROJECTION_MASTER_SID IN (";
	private static final String _SQL_COUNT_PROJECTIONMASTER = "SELECT COUNT(projectionMaster) FROM ProjectionMaster projectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"productHierarchyLevel", "saveFlag", "projectionName", "toDate",
				"projectionMasterSid", "forecastingType", "productHierVersionNo",
				"customerHierVersionNo", "modifiedDate",
				"customerHierarchyLevel", "fromDate", "productHierarchySid",
				"createdDate", "createdBy", "customerHierarchySid",
				"companyGroupSid", "brandType", "modifiedBy",
				"projectionDescription", "isApproved", "itemGroupSid",
				"companyMasterSid", "customerHierarchyInnerLevel",
				"productHierarchyInnerLevel", "custRelationshipBuilderSid",
				"prodRelationshipBuilderSid", "discountType", "businessUnit",
				"deductionHierarchySid", "dedRelationshipBuilderSid",
				"projectionCustVersionNo", "projectionProdVersionNo",
				"forecastEligibleDate"
			});
}