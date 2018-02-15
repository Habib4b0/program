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

import com.stpl.app.exception.NoSuchAverageShelfLifeMasterException;
import com.stpl.app.model.AverageShelfLifeMaster;
import com.stpl.app.service.AverageShelfLifeMasterLocalServiceUtil;
import com.stpl.app.service.persistence.AverageShelfLifeMasterPersistence;
import com.stpl.app.service.persistence.AverageShelfLifeMasterUtil;

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
public class AverageShelfLifeMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = AverageShelfLifeMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AverageShelfLifeMaster> iterator = _averageShelfLifeMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AverageShelfLifeMaster averageShelfLifeMaster = _persistence.create(pk);

		Assert.assertNotNull(averageShelfLifeMaster);

		Assert.assertEquals(averageShelfLifeMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		_persistence.remove(newAverageShelfLifeMaster);

		AverageShelfLifeMaster existingAverageShelfLifeMaster = _persistence.fetchByPrimaryKey(newAverageShelfLifeMaster.getPrimaryKey());

		Assert.assertNull(existingAverageShelfLifeMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAverageShelfLifeMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AverageShelfLifeMaster newAverageShelfLifeMaster = _persistence.create(pk);

		newAverageShelfLifeMaster.setCreatedBy(RandomTestUtil.nextInt());

		newAverageShelfLifeMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newAverageShelfLifeMaster.setItemIdType(RandomTestUtil.randomString());

		newAverageShelfLifeMaster.setModifiedBy(RandomTestUtil.nextInt());

		newAverageShelfLifeMaster.setCreatedDate(RandomTestUtil.nextDate());

		newAverageShelfLifeMaster.setSource(RandomTestUtil.randomString());

		newAverageShelfLifeMaster.setItemId(RandomTestUtil.randomString());

		newAverageShelfLifeMaster.setAvgShelfLife(RandomTestUtil.randomString());

		newAverageShelfLifeMaster.setBatchId(RandomTestUtil.randomString());

		newAverageShelfLifeMaster.setModifiedDate(RandomTestUtil.nextDate());

		newAverageShelfLifeMaster.setInboundStatus(RandomTestUtil.randomString());

		_averageShelfLifeMasters.add(_persistence.update(
				newAverageShelfLifeMaster));

		AverageShelfLifeMaster existingAverageShelfLifeMaster = _persistence.findByPrimaryKey(newAverageShelfLifeMaster.getPrimaryKey());

		Assert.assertEquals(existingAverageShelfLifeMaster.getCreatedBy(),
			newAverageShelfLifeMaster.getCreatedBy());
		Assert.assertEquals(existingAverageShelfLifeMaster.getAverageShelfLifeMasterSid(),
			newAverageShelfLifeMaster.getAverageShelfLifeMasterSid());
		Assert.assertEquals(existingAverageShelfLifeMaster.getRecordLockStatus(),
			newAverageShelfLifeMaster.getRecordLockStatus());
		Assert.assertEquals(existingAverageShelfLifeMaster.getItemIdType(),
			newAverageShelfLifeMaster.getItemIdType());
		Assert.assertEquals(existingAverageShelfLifeMaster.getModifiedBy(),
			newAverageShelfLifeMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAverageShelfLifeMaster.getCreatedDate()),
			Time.getShortTimestamp(newAverageShelfLifeMaster.getCreatedDate()));
		Assert.assertEquals(existingAverageShelfLifeMaster.getSource(),
			newAverageShelfLifeMaster.getSource());
		Assert.assertEquals(existingAverageShelfLifeMaster.getItemId(),
			newAverageShelfLifeMaster.getItemId());
		Assert.assertEquals(existingAverageShelfLifeMaster.getAvgShelfLife(),
			newAverageShelfLifeMaster.getAvgShelfLife());
		Assert.assertEquals(existingAverageShelfLifeMaster.getBatchId(),
			newAverageShelfLifeMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAverageShelfLifeMaster.getModifiedDate()),
			Time.getShortTimestamp(newAverageShelfLifeMaster.getModifiedDate()));
		Assert.assertEquals(existingAverageShelfLifeMaster.getInboundStatus(),
			newAverageShelfLifeMaster.getInboundStatus());
	}

	@Test
	public void testCountByItemIdType() throws Exception {
		_persistence.countByItemIdType(StringPool.BLANK);

		_persistence.countByItemIdType(StringPool.NULL);

		_persistence.countByItemIdType((String)null);
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByAvgShelfLife() throws Exception {
		_persistence.countByAvgShelfLife(StringPool.BLANK);

		_persistence.countByAvgShelfLife(StringPool.NULL);

		_persistence.countByAvgShelfLife((String)null);
	}

	@Test
	public void testCountByAverageShelfLifeUnique() throws Exception {
		_persistence.countByAverageShelfLifeUnique(StringPool.BLANK,
			StringPool.BLANK);

		_persistence.countByAverageShelfLifeUnique(StringPool.NULL,
			StringPool.NULL);

		_persistence.countByAverageShelfLifeUnique((String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		AverageShelfLifeMaster existingAverageShelfLifeMaster = _persistence.findByPrimaryKey(newAverageShelfLifeMaster.getPrimaryKey());

		Assert.assertEquals(existingAverageShelfLifeMaster,
			newAverageShelfLifeMaster);
	}

	@Test(expected = NoSuchAverageShelfLifeMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AverageShelfLifeMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AVERAGE_SHELF_LIFE_MASTER",
			"createdBy", true, "averageShelfLifeMasterSid", true,
			"recordLockStatus", true, "itemIdType", true, "modifiedBy", true,
			"createdDate", true, "source", true, "itemId", true,
			"avgShelfLife", true, "batchId", true, "modifiedDate", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		AverageShelfLifeMaster existingAverageShelfLifeMaster = _persistence.fetchByPrimaryKey(newAverageShelfLifeMaster.getPrimaryKey());

		Assert.assertEquals(existingAverageShelfLifeMaster,
			newAverageShelfLifeMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AverageShelfLifeMaster missingAverageShelfLifeMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAverageShelfLifeMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster1 = addAverageShelfLifeMaster();
		AverageShelfLifeMaster newAverageShelfLifeMaster2 = addAverageShelfLifeMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAverageShelfLifeMaster1.getPrimaryKey());
		primaryKeys.add(newAverageShelfLifeMaster2.getPrimaryKey());

		Map<Serializable, AverageShelfLifeMaster> averageShelfLifeMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, averageShelfLifeMasters.size());
		Assert.assertEquals(newAverageShelfLifeMaster1,
			averageShelfLifeMasters.get(
				newAverageShelfLifeMaster1.getPrimaryKey()));
		Assert.assertEquals(newAverageShelfLifeMaster2,
			averageShelfLifeMasters.get(
				newAverageShelfLifeMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AverageShelfLifeMaster> averageShelfLifeMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(averageShelfLifeMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAverageShelfLifeMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AverageShelfLifeMaster> averageShelfLifeMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, averageShelfLifeMasters.size());
		Assert.assertEquals(newAverageShelfLifeMaster,
			averageShelfLifeMasters.get(
				newAverageShelfLifeMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AverageShelfLifeMaster> averageShelfLifeMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(averageShelfLifeMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAverageShelfLifeMaster.getPrimaryKey());

		Map<Serializable, AverageShelfLifeMaster> averageShelfLifeMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, averageShelfLifeMasters.size());
		Assert.assertEquals(newAverageShelfLifeMaster,
			averageShelfLifeMasters.get(
				newAverageShelfLifeMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AverageShelfLifeMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AverageShelfLifeMaster>() {
				@Override
				public void performAction(
					AverageShelfLifeMaster averageShelfLifeMaster) {
					Assert.assertNotNull(averageShelfLifeMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AverageShelfLifeMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"averageShelfLifeMasterSid",
				newAverageShelfLifeMaster.getAverageShelfLifeMasterSid()));

		List<AverageShelfLifeMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AverageShelfLifeMaster existingAverageShelfLifeMaster = result.get(0);

		Assert.assertEquals(existingAverageShelfLifeMaster,
			newAverageShelfLifeMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AverageShelfLifeMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"averageShelfLifeMasterSid", RandomTestUtil.nextInt()));

		List<AverageShelfLifeMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AverageShelfLifeMaster newAverageShelfLifeMaster = addAverageShelfLifeMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AverageShelfLifeMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"averageShelfLifeMasterSid"));

		Object newAverageShelfLifeMasterSid = newAverageShelfLifeMaster.getAverageShelfLifeMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"averageShelfLifeMasterSid",
				new Object[] { newAverageShelfLifeMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAverageShelfLifeMasterSid = result.get(0);

		Assert.assertEquals(existingAverageShelfLifeMasterSid,
			newAverageShelfLifeMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AverageShelfLifeMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"averageShelfLifeMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"averageShelfLifeMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AverageShelfLifeMaster addAverageShelfLifeMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AverageShelfLifeMaster averageShelfLifeMaster = _persistence.create(pk);

		averageShelfLifeMaster.setCreatedBy(RandomTestUtil.nextInt());

		averageShelfLifeMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		averageShelfLifeMaster.setItemIdType(RandomTestUtil.randomString());

		averageShelfLifeMaster.setModifiedBy(RandomTestUtil.nextInt());

		averageShelfLifeMaster.setCreatedDate(RandomTestUtil.nextDate());

		averageShelfLifeMaster.setSource(RandomTestUtil.randomString());

		averageShelfLifeMaster.setItemId(RandomTestUtil.randomString());

		averageShelfLifeMaster.setAvgShelfLife(RandomTestUtil.randomString());

		averageShelfLifeMaster.setBatchId(RandomTestUtil.randomString());

		averageShelfLifeMaster.setModifiedDate(RandomTestUtil.nextDate());

		averageShelfLifeMaster.setInboundStatus(RandomTestUtil.randomString());

		_averageShelfLifeMasters.add(_persistence.update(averageShelfLifeMaster));

		return averageShelfLifeMaster;
	}

	private List<AverageShelfLifeMaster> _averageShelfLifeMasters = new ArrayList<AverageShelfLifeMaster>();
	private AverageShelfLifeMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}