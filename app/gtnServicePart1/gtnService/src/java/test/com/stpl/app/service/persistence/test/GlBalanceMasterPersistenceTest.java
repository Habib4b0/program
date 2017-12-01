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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchGlBalanceMasterException;
import com.stpl.app.model.GlBalanceMaster;
import com.stpl.app.service.GlBalanceMasterLocalServiceUtil;
import com.stpl.app.service.persistence.GlBalanceMasterPersistence;
import com.stpl.app.service.persistence.GlBalanceMasterUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class GlBalanceMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GlBalanceMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GlBalanceMaster> iterator = _glBalanceMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlBalanceMaster glBalanceMaster = _persistence.create(pk);

		Assert.assertNotNull(glBalanceMaster);

		Assert.assertEquals(glBalanceMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		_persistence.remove(newGlBalanceMaster);

		GlBalanceMaster existingGlBalanceMaster = _persistence.fetchByPrimaryKey(newGlBalanceMaster.getPrimaryKey());

		Assert.assertNull(existingGlBalanceMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGlBalanceMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlBalanceMaster newGlBalanceMaster = _persistence.create(pk);

		newGlBalanceMaster.setCreatedBy(RandomTestUtil.nextInt());

		newGlBalanceMaster.setModifiedBy(RandomTestUtil.nextInt());

		newGlBalanceMaster.setAccountId(RandomTestUtil.randomString());

		newGlBalanceMaster.setUploadDate(RandomTestUtil.nextDate());

		newGlBalanceMaster.setCreatedDate(RandomTestUtil.nextDate());

		newGlBalanceMaster.setIsActive(RandomTestUtil.randomString());

		newGlBalanceMaster.setBatchId(RandomTestUtil.randomString());

		newGlBalanceMaster.setModifiedDate(RandomTestUtil.nextDate());

		newGlBalanceMaster.setBalance(RandomTestUtil.randomString());

		newGlBalanceMaster.setCloseIndicator(RandomTestUtil.randomString());

		newGlBalanceMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newGlBalanceMaster.setYear(RandomTestUtil.randomString());

		newGlBalanceMaster.setPeriod(RandomTestUtil.randomString());

		newGlBalanceMaster.setSource(RandomTestUtil.randomString());

		newGlBalanceMaster.setInsertedDate(RandomTestUtil.nextDate());

		newGlBalanceMaster.setAccountNo(RandomTestUtil.randomString());

		newGlBalanceMaster.setInboundStatus(RandomTestUtil.randomString());

		_glBalanceMasters.add(_persistence.update(newGlBalanceMaster));

		GlBalanceMaster existingGlBalanceMaster = _persistence.findByPrimaryKey(newGlBalanceMaster.getPrimaryKey());

		Assert.assertEquals(existingGlBalanceMaster.getCreatedBy(),
			newGlBalanceMaster.getCreatedBy());
		Assert.assertEquals(existingGlBalanceMaster.getModifiedBy(),
			newGlBalanceMaster.getModifiedBy());
		Assert.assertEquals(existingGlBalanceMaster.getAccountId(),
			newGlBalanceMaster.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlBalanceMaster.getUploadDate()),
			Time.getShortTimestamp(newGlBalanceMaster.getUploadDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlBalanceMaster.getCreatedDate()),
			Time.getShortTimestamp(newGlBalanceMaster.getCreatedDate()));
		Assert.assertEquals(existingGlBalanceMaster.getGlBalanceMasterSid(),
			newGlBalanceMaster.getGlBalanceMasterSid());
		Assert.assertEquals(existingGlBalanceMaster.getIsActive(),
			newGlBalanceMaster.getIsActive());
		Assert.assertEquals(existingGlBalanceMaster.getBatchId(),
			newGlBalanceMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlBalanceMaster.getModifiedDate()),
			Time.getShortTimestamp(newGlBalanceMaster.getModifiedDate()));
		Assert.assertEquals(existingGlBalanceMaster.getBalance(),
			newGlBalanceMaster.getBalance());
		Assert.assertEquals(existingGlBalanceMaster.getCloseIndicator(),
			newGlBalanceMaster.getCloseIndicator());
		Assert.assertEquals(existingGlBalanceMaster.getRecordLockStatus(),
			newGlBalanceMaster.getRecordLockStatus());
		Assert.assertEquals(existingGlBalanceMaster.getYear(),
			newGlBalanceMaster.getYear());
		Assert.assertEquals(existingGlBalanceMaster.getPeriod(),
			newGlBalanceMaster.getPeriod());
		Assert.assertEquals(existingGlBalanceMaster.getSource(),
			newGlBalanceMaster.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlBalanceMaster.getInsertedDate()),
			Time.getShortTimestamp(newGlBalanceMaster.getInsertedDate()));
		Assert.assertEquals(existingGlBalanceMaster.getAccountNo(),
			newGlBalanceMaster.getAccountNo());
		Assert.assertEquals(existingGlBalanceMaster.getInboundStatus(),
			newGlBalanceMaster.getInboundStatus());
	}

	@Test
	public void testCountByAccountNo() throws Exception {
		_persistence.countByAccountNo(StringPool.BLANK);

		_persistence.countByAccountNo(StringPool.NULL);

		_persistence.countByAccountNo((String)null);
	}

	@Test
	public void testCountByIsActive() throws Exception {
		_persistence.countByIsActive(StringPool.BLANK);

		_persistence.countByIsActive(StringPool.NULL);

		_persistence.countByIsActive((String)null);
	}

	@Test
	public void testCountByUploadDate() throws Exception {
		_persistence.countByUploadDate(RandomTestUtil.nextDate());

		_persistence.countByUploadDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByAccountId() throws Exception {
		_persistence.countByAccountId(StringPool.BLANK);

		_persistence.countByAccountId(StringPool.NULL);

		_persistence.countByAccountId((String)null);
	}

	@Test
	public void testCountByYear() throws Exception {
		_persistence.countByYear(StringPool.BLANK);

		_persistence.countByYear(StringPool.NULL);

		_persistence.countByYear((String)null);
	}

	@Test
	public void testCountByPeriod() throws Exception {
		_persistence.countByPeriod(StringPool.BLANK);

		_persistence.countByPeriod(StringPool.NULL);

		_persistence.countByPeriod((String)null);
	}

	@Test
	public void testCountByGlBalanceUnique() throws Exception {
		_persistence.countByGlBalanceUnique(StringPool.BLANK, StringPool.BLANK,
			RandomTestUtil.nextDate());

		_persistence.countByGlBalanceUnique(StringPool.NULL, StringPool.NULL,
			RandomTestUtil.nextDate());

		_persistence.countByGlBalanceUnique((String)null, (String)null,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		GlBalanceMaster existingGlBalanceMaster = _persistence.findByPrimaryKey(newGlBalanceMaster.getPrimaryKey());

		Assert.assertEquals(existingGlBalanceMaster, newGlBalanceMaster);
	}

	@Test(expected = NoSuchGlBalanceMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GlBalanceMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GL_BALANCE_MASTER",
			"createdBy", true, "modifiedBy", true, "accountId", true,
			"uploadDate", true, "createdDate", true, "glBalanceMasterSid",
			true, "isActive", true, "batchId", true, "modifiedDate", true,
			"balance", true, "closeIndicator", true, "recordLockStatus", true,
			"year", true, "period", true, "source", true, "insertedDate", true,
			"accountNo", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		GlBalanceMaster existingGlBalanceMaster = _persistence.fetchByPrimaryKey(newGlBalanceMaster.getPrimaryKey());

		Assert.assertEquals(existingGlBalanceMaster, newGlBalanceMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlBalanceMaster missingGlBalanceMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGlBalanceMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GlBalanceMaster newGlBalanceMaster1 = addGlBalanceMaster();
		GlBalanceMaster newGlBalanceMaster2 = addGlBalanceMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlBalanceMaster1.getPrimaryKey());
		primaryKeys.add(newGlBalanceMaster2.getPrimaryKey());

		Map<Serializable, GlBalanceMaster> glBalanceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, glBalanceMasters.size());
		Assert.assertEquals(newGlBalanceMaster1,
			glBalanceMasters.get(newGlBalanceMaster1.getPrimaryKey()));
		Assert.assertEquals(newGlBalanceMaster2,
			glBalanceMasters.get(newGlBalanceMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GlBalanceMaster> glBalanceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(glBalanceMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlBalanceMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GlBalanceMaster> glBalanceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, glBalanceMasters.size());
		Assert.assertEquals(newGlBalanceMaster,
			glBalanceMasters.get(newGlBalanceMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GlBalanceMaster> glBalanceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(glBalanceMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlBalanceMaster.getPrimaryKey());

		Map<Serializable, GlBalanceMaster> glBalanceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, glBalanceMasters.size());
		Assert.assertEquals(newGlBalanceMaster,
			glBalanceMasters.get(newGlBalanceMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GlBalanceMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GlBalanceMaster>() {
				@Override
				public void performAction(GlBalanceMaster glBalanceMaster) {
					Assert.assertNotNull(glBalanceMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlBalanceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("glBalanceMasterSid",
				newGlBalanceMaster.getGlBalanceMasterSid()));

		List<GlBalanceMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GlBalanceMaster existingGlBalanceMaster = result.get(0);

		Assert.assertEquals(existingGlBalanceMaster, newGlBalanceMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlBalanceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("glBalanceMasterSid",
				RandomTestUtil.nextInt()));

		List<GlBalanceMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GlBalanceMaster newGlBalanceMaster = addGlBalanceMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlBalanceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"glBalanceMasterSid"));

		Object newGlBalanceMasterSid = newGlBalanceMaster.getGlBalanceMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("glBalanceMasterSid",
				new Object[] { newGlBalanceMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGlBalanceMasterSid = result.get(0);

		Assert.assertEquals(existingGlBalanceMasterSid, newGlBalanceMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlBalanceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"glBalanceMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("glBalanceMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GlBalanceMaster addGlBalanceMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlBalanceMaster glBalanceMaster = _persistence.create(pk);

		glBalanceMaster.setCreatedBy(RandomTestUtil.nextInt());

		glBalanceMaster.setModifiedBy(RandomTestUtil.nextInt());

		glBalanceMaster.setAccountId(RandomTestUtil.randomString());

		glBalanceMaster.setUploadDate(RandomTestUtil.nextDate());

		glBalanceMaster.setCreatedDate(RandomTestUtil.nextDate());

		glBalanceMaster.setIsActive(RandomTestUtil.randomString());

		glBalanceMaster.setBatchId(RandomTestUtil.randomString());

		glBalanceMaster.setModifiedDate(RandomTestUtil.nextDate());

		glBalanceMaster.setBalance(RandomTestUtil.randomString());

		glBalanceMaster.setCloseIndicator(RandomTestUtil.randomString());

		glBalanceMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		glBalanceMaster.setYear(RandomTestUtil.randomString());

		glBalanceMaster.setPeriod(RandomTestUtil.randomString());

		glBalanceMaster.setSource(RandomTestUtil.randomString());

		glBalanceMaster.setInsertedDate(RandomTestUtil.nextDate());

		glBalanceMaster.setAccountNo(RandomTestUtil.randomString());

		glBalanceMaster.setInboundStatus(RandomTestUtil.randomString());

		_glBalanceMasters.add(_persistence.update(glBalanceMaster));

		return glBalanceMaster;
	}

	private List<GlBalanceMaster> _glBalanceMasters = new ArrayList<GlBalanceMaster>();
	private GlBalanceMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}