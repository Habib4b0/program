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

import com.stpl.app.exception.NoSuchMailNotificationMasterException;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.model.impl.MailNotificationMasterImpl;
import com.stpl.app.model.impl.MailNotificationMasterModelImpl;
import com.stpl.app.service.persistence.MailNotificationMasterPersistence;

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
 * The persistence implementation for the mail notification master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MailNotificationMasterPersistence
 * @see com.stpl.app.service.persistence.MailNotificationMasterUtil
 * @generated
 */
@ProviderType
public class MailNotificationMasterPersistenceImpl extends BasePersistenceImpl<MailNotificationMaster>
	implements MailNotificationMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MailNotificationMasterUtil} to access the mail notification master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MailNotificationMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED,
			MailNotificationMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED,
			MailNotificationMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MailNotificationMasterPersistenceImpl() {
		setModelClass(MailNotificationMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("subject", "SUBJECT");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("toMailIds", "TO_MAIL_IDS");
			dbColumnNames.put("notificationCategoryId",
				"NOTIFICATION_CATEGORY_ID");
			dbColumnNames.put("notificationModule", "NOTIFICATION_MODULE");
			dbColumnNames.put("body", "BODY");
			dbColumnNames.put("fromMailId", "FROM_MAIL_ID");
			dbColumnNames.put("ccMailIds", "CC_MAIL_IDS");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("mailNotificationSid", "MAIL_NOTIFICATION_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the mail notification master in the entity cache if it is enabled.
	 *
	 * @param mailNotificationMaster the mail notification master
	 */
	@Override
	public void cacheResult(MailNotificationMaster mailNotificationMaster) {
		entityCache.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterImpl.class,
			mailNotificationMaster.getPrimaryKey(), mailNotificationMaster);

		mailNotificationMaster.resetOriginalValues();
	}

	/**
	 * Caches the mail notification masters in the entity cache if it is enabled.
	 *
	 * @param mailNotificationMasters the mail notification masters
	 */
	@Override
	public void cacheResult(
		List<MailNotificationMaster> mailNotificationMasters) {
		for (MailNotificationMaster mailNotificationMaster : mailNotificationMasters) {
			if (entityCache.getResult(
						MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
						MailNotificationMasterImpl.class,
						mailNotificationMaster.getPrimaryKey()) == null) {
				cacheResult(mailNotificationMaster);
			}
			else {
				mailNotificationMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mail notification masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MailNotificationMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mail notification master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MailNotificationMaster mailNotificationMaster) {
		entityCache.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterImpl.class,
			mailNotificationMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MailNotificationMaster> mailNotificationMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MailNotificationMaster mailNotificationMaster : mailNotificationMasters) {
			entityCache.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
				MailNotificationMasterImpl.class,
				mailNotificationMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
	 *
	 * @param mailNotificationSid the primary key for the new mail notification master
	 * @return the new mail notification master
	 */
	@Override
	public MailNotificationMaster create(int mailNotificationSid) {
		MailNotificationMaster mailNotificationMaster = new MailNotificationMasterImpl();

		mailNotificationMaster.setNew(true);
		mailNotificationMaster.setPrimaryKey(mailNotificationSid);

		return mailNotificationMaster;
	}

	/**
	 * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mailNotificationSid the primary key of the mail notification master
	 * @return the mail notification master that was removed
	 * @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster remove(int mailNotificationSid)
		throws NoSuchMailNotificationMasterException {
		return remove((Serializable)mailNotificationSid);
	}

	/**
	 * Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mail notification master
	 * @return the mail notification master that was removed
	 * @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster remove(Serializable primaryKey)
		throws NoSuchMailNotificationMasterException {
		Session session = null;

		try {
			session = openSession();

			MailNotificationMaster mailNotificationMaster = (MailNotificationMaster)session.get(MailNotificationMasterImpl.class,
					primaryKey);

			if (mailNotificationMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMailNotificationMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mailNotificationMaster);
		}
		catch (NoSuchMailNotificationMasterException nsee) {
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
	protected MailNotificationMaster removeImpl(
		MailNotificationMaster mailNotificationMaster) {
		mailNotificationMaster = toUnwrappedModel(mailNotificationMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mailNotificationMaster)) {
				mailNotificationMaster = (MailNotificationMaster)session.get(MailNotificationMasterImpl.class,
						mailNotificationMaster.getPrimaryKeyObj());
			}

			if (mailNotificationMaster != null) {
				session.delete(mailNotificationMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mailNotificationMaster != null) {
			clearCache(mailNotificationMaster);
		}

		return mailNotificationMaster;
	}

	@Override
	public MailNotificationMaster updateImpl(
		MailNotificationMaster mailNotificationMaster) {
		mailNotificationMaster = toUnwrappedModel(mailNotificationMaster);

		boolean isNew = mailNotificationMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mailNotificationMaster.isNew()) {
				session.save(mailNotificationMaster);

				mailNotificationMaster.setNew(false);
			}
			else {
				mailNotificationMaster = (MailNotificationMaster)session.merge(mailNotificationMaster);
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

		entityCache.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
			MailNotificationMasterImpl.class,
			mailNotificationMaster.getPrimaryKey(), mailNotificationMaster,
			false);

		mailNotificationMaster.resetOriginalValues();

		return mailNotificationMaster;
	}

	protected MailNotificationMaster toUnwrappedModel(
		MailNotificationMaster mailNotificationMaster) {
		if (mailNotificationMaster instanceof MailNotificationMasterImpl) {
			return mailNotificationMaster;
		}

		MailNotificationMasterImpl mailNotificationMasterImpl = new MailNotificationMasterImpl();

		mailNotificationMasterImpl.setNew(mailNotificationMaster.isNew());
		mailNotificationMasterImpl.setPrimaryKey(mailNotificationMaster.getPrimaryKey());

		mailNotificationMasterImpl.setSubject(mailNotificationMaster.getSubject());
		mailNotificationMasterImpl.setCreatedDate(mailNotificationMaster.getCreatedDate());
		mailNotificationMasterImpl.setCreatedBy(mailNotificationMaster.getCreatedBy());
		mailNotificationMasterImpl.setToMailIds(mailNotificationMaster.getToMailIds());
		mailNotificationMasterImpl.setNotificationCategoryId(mailNotificationMaster.getNotificationCategoryId());
		mailNotificationMasterImpl.setNotificationModule(mailNotificationMaster.getNotificationModule());
		mailNotificationMasterImpl.setBody(mailNotificationMaster.getBody());
		mailNotificationMasterImpl.setFromMailId(mailNotificationMaster.getFromMailId());
		mailNotificationMasterImpl.setCcMailIds(mailNotificationMaster.getCcMailIds());
		mailNotificationMasterImpl.setVersionNo(mailNotificationMaster.getVersionNo());
		mailNotificationMasterImpl.setModifiedBy(mailNotificationMaster.getModifiedBy());
		mailNotificationMasterImpl.setModifiedDate(mailNotificationMaster.getModifiedDate());
		mailNotificationMasterImpl.setMailNotificationSid(mailNotificationMaster.getMailNotificationSid());

		return mailNotificationMasterImpl;
	}

	/**
	 * Returns the mail notification master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail notification master
	 * @return the mail notification master
	 * @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMailNotificationMasterException {
		MailNotificationMaster mailNotificationMaster = fetchByPrimaryKey(primaryKey);

		if (mailNotificationMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMailNotificationMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mailNotificationMaster;
	}

	/**
	 * Returns the mail notification master with the primary key or throws a {@link NoSuchMailNotificationMasterException} if it could not be found.
	 *
	 * @param mailNotificationSid the primary key of the mail notification master
	 * @return the mail notification master
	 * @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster findByPrimaryKey(int mailNotificationSid)
		throws NoSuchMailNotificationMasterException {
		return findByPrimaryKey((Serializable)mailNotificationSid);
	}

	/**
	 * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mail notification master
	 * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
				MailNotificationMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MailNotificationMaster mailNotificationMaster = (MailNotificationMaster)serializable;

		if (mailNotificationMaster == null) {
			Session session = null;

			try {
				session = openSession();

				mailNotificationMaster = (MailNotificationMaster)session.get(MailNotificationMasterImpl.class,
						primaryKey);

				if (mailNotificationMaster != null) {
					cacheResult(mailNotificationMaster);
				}
				else {
					entityCache.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
						MailNotificationMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
					MailNotificationMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mailNotificationMaster;
	}

	/**
	 * Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mailNotificationSid the primary key of the mail notification master
	 * @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
	 */
	@Override
	public MailNotificationMaster fetchByPrimaryKey(int mailNotificationSid) {
		return fetchByPrimaryKey((Serializable)mailNotificationSid);
	}

	@Override
	public Map<Serializable, MailNotificationMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MailNotificationMaster> map = new HashMap<Serializable, MailNotificationMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MailNotificationMaster mailNotificationMaster = fetchByPrimaryKey(primaryKey);

			if (mailNotificationMaster != null) {
				map.put(primaryKey, mailNotificationMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
					MailNotificationMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MailNotificationMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MAILNOTIFICATIONMASTER_WHERE_PKS_IN);

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

			for (MailNotificationMaster mailNotificationMaster : (List<MailNotificationMaster>)q.list()) {
				map.put(mailNotificationMaster.getPrimaryKeyObj(),
					mailNotificationMaster);

				cacheResult(mailNotificationMaster);

				uncachedPrimaryKeys.remove(mailNotificationMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MailNotificationMasterModelImpl.ENTITY_CACHE_ENABLED,
					MailNotificationMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the mail notification masters.
	 *
	 * @return the mail notification masters
	 */
	@Override
	public List<MailNotificationMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mail notification masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mail notification masters
	 * @param end the upper bound of the range of mail notification masters (not inclusive)
	 * @return the range of mail notification masters
	 */
	@Override
	public List<MailNotificationMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the mail notification masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mail notification masters
	 * @param end the upper bound of the range of mail notification masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mail notification masters
	 */
	@Override
	public List<MailNotificationMaster> findAll(int start, int end,
		OrderByComparator<MailNotificationMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mail notification masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of mail notification masters
	 * @param end the upper bound of the range of mail notification masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of mail notification masters
	 */
	@Override
	public List<MailNotificationMaster> findAll(int start, int end,
		OrderByComparator<MailNotificationMaster> orderByComparator,
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

		List<MailNotificationMaster> list = null;

		if (retrieveFromCache) {
			list = (List<MailNotificationMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MAILNOTIFICATIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MAILNOTIFICATIONMASTER;

				if (pagination) {
					sql = sql.concat(MailNotificationMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MailNotificationMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MailNotificationMaster>)QueryUtil.list(q,
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
	 * Removes all the mail notification masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MailNotificationMaster mailNotificationMaster : findAll()) {
			remove(mailNotificationMaster);
		}
	}

	/**
	 * Returns the number of mail notification masters.
	 *
	 * @return the number of mail notification masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MAILNOTIFICATIONMASTER);

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
		return MailNotificationMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mail notification master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MailNotificationMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MAILNOTIFICATIONMASTER = "SELECT mailNotificationMaster FROM MailNotificationMaster mailNotificationMaster";
	private static final String _SQL_SELECT_MAILNOTIFICATIONMASTER_WHERE_PKS_IN = "SELECT mailNotificationMaster FROM MailNotificationMaster mailNotificationMaster WHERE MAIL_NOTIFICATION_SID IN (";
	private static final String _SQL_COUNT_MAILNOTIFICATIONMASTER = "SELECT COUNT(mailNotificationMaster) FROM MailNotificationMaster mailNotificationMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mailNotificationMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MailNotificationMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MailNotificationMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"subject", "createdDate", "createdBy", "toMailIds",
				"notificationCategoryId", "notificationModule", "body",
				"fromMailId", "ccMailIds", "versionNo", "modifiedBy",
				"modifiedDate", "mailNotificationSid"
			});
}