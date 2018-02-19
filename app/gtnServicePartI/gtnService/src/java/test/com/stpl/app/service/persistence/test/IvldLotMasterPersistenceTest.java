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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchIvldLotMasterException;
import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.service.IvldLotMasterLocalServiceUtil;
import com.stpl.app.service.persistence.IvldLotMasterPersistence;
import com.stpl.app.service.persistence.IvldLotMasterUtil;

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
public class IvldLotMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldLotMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldLotMaster> iterator = _ivldLotMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldLotMaster ivldLotMaster = _persistence.create(pk);

		Assert.assertNotNull(ivldLotMaster);

		Assert.assertEquals(ivldLotMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		_persistence.remove(newIvldLotMaster);

		IvldLotMaster existingIvldLotMaster = _persistence.fetchByPrimaryKey(newIvldLotMaster.getPrimaryKey());

		Assert.assertNull(existingIvldLotMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldLotMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldLotMaster newIvldLotMaster = _persistence.create(pk);

		newIvldLotMaster.setReasonForFailure(RandomTestUtil.randomString());

		newIvldLotMaster.setItemId(RandomTestUtil.randomString());

		newIvldLotMaster.setModifiedDate(RandomTestUtil.nextDate());

		newIvldLotMaster.setCreatedBy(RandomTestUtil.randomString());

		newIvldLotMaster.setCreatedDate(RandomTestUtil.nextDate());

		newIvldLotMaster.setSource(RandomTestUtil.randomString());

		newIvldLotMaster.setLastLotSoldDate(RandomTestUtil.randomString());

		newIvldLotMaster.setLotExpirationDate(RandomTestUtil.randomString());

		newIvldLotMaster.setBatchId(RandomTestUtil.randomString());

		newIvldLotMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldLotMaster.setErrorField(RandomTestUtil.randomString());

		newIvldLotMaster.setErrorCode(RandomTestUtil.randomString());

		newIvldLotMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldLotMaster.setModifiedBy(RandomTestUtil.randomString());

		newIvldLotMaster.setLotNo(RandomTestUtil.randomString());

		newIvldLotMaster.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldLotMaster.setLotMasterIntfid(RandomTestUtil.randomString());

		newIvldLotMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldLotMasters.add(_persistence.update(newIvldLotMaster));

		IvldLotMaster existingIvldLotMaster = _persistence.findByPrimaryKey(newIvldLotMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldLotMaster.getReasonForFailure(),
			newIvldLotMaster.getReasonForFailure());
		Assert.assertEquals(existingIvldLotMaster.getItemId(),
			newIvldLotMaster.getItemId());
		Assert.assertEquals(existingIvldLotMaster.getIvldLotMasterSid(),
			newIvldLotMaster.getIvldLotMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldLotMaster.getModifiedDate()),
			Time.getShortTimestamp(newIvldLotMaster.getModifiedDate()));
		Assert.assertEquals(existingIvldLotMaster.getCreatedBy(),
			newIvldLotMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldLotMaster.getCreatedDate()),
			Time.getShortTimestamp(newIvldLotMaster.getCreatedDate()));
		Assert.assertEquals(existingIvldLotMaster.getSource(),
			newIvldLotMaster.getSource());
		Assert.assertEquals(existingIvldLotMaster.getLastLotSoldDate(),
			newIvldLotMaster.getLastLotSoldDate());
		Assert.assertEquals(existingIvldLotMaster.getLotExpirationDate(),
			newIvldLotMaster.getLotExpirationDate());
		Assert.assertEquals(existingIvldLotMaster.getBatchId(),
			newIvldLotMaster.getBatchId());
		Assert.assertEquals(existingIvldLotMaster.getAddChgDelIndicator(),
			newIvldLotMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldLotMaster.getErrorField(),
			newIvldLotMaster.getErrorField());
		Assert.assertEquals(existingIvldLotMaster.getErrorCode(),
			newIvldLotMaster.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldLotMaster.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldLotMaster.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldLotMaster.getModifiedBy(),
			newIvldLotMaster.getModifiedBy());
		Assert.assertEquals(existingIvldLotMaster.getLotNo(),
			newIvldLotMaster.getLotNo());
		Assert.assertEquals(existingIvldLotMaster.getReprocessedFlag(),
			newIvldLotMaster.getReprocessedFlag());
		Assert.assertEquals(existingIvldLotMaster.getLotMasterIntfid(),
			newIvldLotMaster.getLotMasterIntfid());
		Assert.assertEquals(existingIvldLotMaster.getCheckRecord(),
			newIvldLotMaster.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		IvldLotMaster existingIvldLotMaster = _persistence.findByPrimaryKey(newIvldLotMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldLotMaster, newIvldLotMaster);
	}

	@Test(expected = NoSuchIvldLotMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldLotMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_LOT_MASTER",
			"reasonForFailure", true, "itemId", true, "ivldLotMasterSid", true,
			"modifiedDate", true, "createdBy", true, "createdDate", true,
			"source", true, "lastLotSoldDate", true, "lotExpirationDate", true,
			"batchId", true, "addChgDelIndicator", true, "errorField", true,
			"errorCode", true, "intfInsertedDate", true, "modifiedBy", true,
			"lotNo", true, "reprocessedFlag", true, "lotMasterIntfid", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		IvldLotMaster existingIvldLotMaster = _persistence.fetchByPrimaryKey(newIvldLotMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldLotMaster, newIvldLotMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldLotMaster missingIvldLotMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldLotMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldLotMaster newIvldLotMaster1 = addIvldLotMaster();
		IvldLotMaster newIvldLotMaster2 = addIvldLotMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldLotMaster1.getPrimaryKey());
		primaryKeys.add(newIvldLotMaster2.getPrimaryKey());

		Map<Serializable, IvldLotMaster> ivldLotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldLotMasters.size());
		Assert.assertEquals(newIvldLotMaster1,
			ivldLotMasters.get(newIvldLotMaster1.getPrimaryKey()));
		Assert.assertEquals(newIvldLotMaster2,
			ivldLotMasters.get(newIvldLotMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldLotMaster> ivldLotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldLotMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldLotMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldLotMaster> ivldLotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldLotMasters.size());
		Assert.assertEquals(newIvldLotMaster,
			ivldLotMasters.get(newIvldLotMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldLotMaster> ivldLotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldLotMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldLotMaster.getPrimaryKey());

		Map<Serializable, IvldLotMaster> ivldLotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldLotMasters.size());
		Assert.assertEquals(newIvldLotMaster,
			ivldLotMasters.get(newIvldLotMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldLotMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldLotMaster>() {
				@Override
				public void performAction(IvldLotMaster ivldLotMaster) {
					Assert.assertNotNull(ivldLotMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldLotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldLotMasterSid",
				newIvldLotMaster.getIvldLotMasterSid()));

		List<IvldLotMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldLotMaster existingIvldLotMaster = result.get(0);

		Assert.assertEquals(existingIvldLotMaster, newIvldLotMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldLotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldLotMasterSid",
				RandomTestUtil.nextInt()));

		List<IvldLotMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldLotMaster newIvldLotMaster = addIvldLotMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldLotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldLotMasterSid"));

		Object newIvldLotMasterSid = newIvldLotMaster.getIvldLotMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldLotMasterSid",
				new Object[] { newIvldLotMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldLotMasterSid = result.get(0);

		Assert.assertEquals(existingIvldLotMasterSid, newIvldLotMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldLotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldLotMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldLotMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldLotMaster addIvldLotMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldLotMaster ivldLotMaster = _persistence.create(pk);

		ivldLotMaster.setReasonForFailure(RandomTestUtil.randomString());

		ivldLotMaster.setItemId(RandomTestUtil.randomString());

		ivldLotMaster.setModifiedDate(RandomTestUtil.nextDate());

		ivldLotMaster.setCreatedBy(RandomTestUtil.randomString());

		ivldLotMaster.setCreatedDate(RandomTestUtil.nextDate());

		ivldLotMaster.setSource(RandomTestUtil.randomString());

		ivldLotMaster.setLastLotSoldDate(RandomTestUtil.randomString());

		ivldLotMaster.setLotExpirationDate(RandomTestUtil.randomString());

		ivldLotMaster.setBatchId(RandomTestUtil.randomString());

		ivldLotMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldLotMaster.setErrorField(RandomTestUtil.randomString());

		ivldLotMaster.setErrorCode(RandomTestUtil.randomString());

		ivldLotMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldLotMaster.setModifiedBy(RandomTestUtil.randomString());

		ivldLotMaster.setLotNo(RandomTestUtil.randomString());

		ivldLotMaster.setReprocessedFlag(RandomTestUtil.randomString());

		ivldLotMaster.setLotMasterIntfid(RandomTestUtil.randomString());

		ivldLotMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldLotMasters.add(_persistence.update(ivldLotMaster));

		return ivldLotMaster;
	}

	private List<IvldLotMaster> _ivldLotMasters = new ArrayList<IvldLotMaster>();
	private IvldLotMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}