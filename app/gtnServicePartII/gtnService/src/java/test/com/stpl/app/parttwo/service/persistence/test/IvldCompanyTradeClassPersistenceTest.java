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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyTradeClassException;
import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassUtil;

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
public class IvldCompanyTradeClassPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCompanyTradeClassUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCompanyTradeClass> iterator = _ivldCompanyTradeClasses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyTradeClass ivldCompanyTradeClass = _persistence.create(pk);

		Assert.assertNotNull(ivldCompanyTradeClass);

		Assert.assertEquals(ivldCompanyTradeClass.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		_persistence.remove(newIvldCompanyTradeClass);

		IvldCompanyTradeClass existingIvldCompanyTradeClass = _persistence.fetchByPrimaryKey(newIvldCompanyTradeClass.getPrimaryKey());

		Assert.assertNull(existingIvldCompanyTradeClass);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCompanyTradeClass();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyTradeClass newIvldCompanyTradeClass = _persistence.create(pk);

		newIvldCompanyTradeClass.setPriorTradeClass(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setCompanyIdString(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setLastUpdatedDate(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCompanyTradeClass.setTradeClassEndDate(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setTradeClassIntfid(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setTradeClassStartDate(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setSource(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setCreatedBy(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCompanyTradeClass.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setBatchId(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setErrorField(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setErrorCode(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setTradeClass(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCompanyTradeClass.setModifiedBy(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCompanyTradeClass.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyTradeClasses.add(_persistence.update(
				newIvldCompanyTradeClass));

		IvldCompanyTradeClass existingIvldCompanyTradeClass = _persistence.findByPrimaryKey(newIvldCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyTradeClass.getIvldCompanyTradeClassSid(),
			newIvldCompanyTradeClass.getIvldCompanyTradeClassSid());
		Assert.assertEquals(existingIvldCompanyTradeClass.getPriorTradeClass(),
			newIvldCompanyTradeClass.getPriorTradeClass());
		Assert.assertEquals(existingIvldCompanyTradeClass.getReasonForFailure(),
			newIvldCompanyTradeClass.getReasonForFailure());
		Assert.assertEquals(existingIvldCompanyTradeClass.getCompanyIdString(),
			newIvldCompanyTradeClass.getCompanyIdString());
		Assert.assertEquals(existingIvldCompanyTradeClass.getLastUpdatedDate(),
			newIvldCompanyTradeClass.getLastUpdatedDate());
		Assert.assertEquals(existingIvldCompanyTradeClass.getPriorTradeClassStartDate(),
			newIvldCompanyTradeClass.getPriorTradeClassStartDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyTradeClass.getModifiedDate()),
			Time.getShortTimestamp(newIvldCompanyTradeClass.getModifiedDate()));
		Assert.assertEquals(existingIvldCompanyTradeClass.getTradeClassEndDate(),
			newIvldCompanyTradeClass.getTradeClassEndDate());
		Assert.assertEquals(existingIvldCompanyTradeClass.getTradeClassIntfid(),
			newIvldCompanyTradeClass.getTradeClassIntfid());
		Assert.assertEquals(existingIvldCompanyTradeClass.getTradeClassStartDate(),
			newIvldCompanyTradeClass.getTradeClassStartDate());
		Assert.assertEquals(existingIvldCompanyTradeClass.getSource(),
			newIvldCompanyTradeClass.getSource());
		Assert.assertEquals(existingIvldCompanyTradeClass.getCreatedBy(),
			newIvldCompanyTradeClass.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyTradeClass.getCreatedDate()),
			Time.getShortTimestamp(newIvldCompanyTradeClass.getCreatedDate()));
		Assert.assertEquals(existingIvldCompanyTradeClass.getAddChgDelIndicator(),
			newIvldCompanyTradeClass.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCompanyTradeClass.getBatchId(),
			newIvldCompanyTradeClass.getBatchId());
		Assert.assertEquals(existingIvldCompanyTradeClass.getErrorField(),
			newIvldCompanyTradeClass.getErrorField());
		Assert.assertEquals(existingIvldCompanyTradeClass.getErrorCode(),
			newIvldCompanyTradeClass.getErrorCode());
		Assert.assertEquals(existingIvldCompanyTradeClass.getTradeClass(),
			newIvldCompanyTradeClass.getTradeClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyTradeClass.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldCompanyTradeClass.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCompanyTradeClass.getModifiedBy(),
			newIvldCompanyTradeClass.getModifiedBy());
		Assert.assertEquals(existingIvldCompanyTradeClass.getReprocessedFlag(),
			newIvldCompanyTradeClass.getReprocessedFlag());
		Assert.assertEquals(existingIvldCompanyTradeClass.getCheckRecord(),
			newIvldCompanyTradeClass.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		IvldCompanyTradeClass existingIvldCompanyTradeClass = _persistence.findByPrimaryKey(newIvldCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyTradeClass,
			newIvldCompanyTradeClass);
	}

	@Test(expected = NoSuchIvldCompanyTradeClassException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCompanyTradeClass> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_COMPANY_TRADE_CLASS",
			"ivldCompanyTradeClassSid", true, "priorTradeClass", true,
			"reasonForFailure", true, "companyIdString", true,
			"lastUpdatedDate", true, "priorTradeClassStartDate", true,
			"modifiedDate", true, "tradeClassEndDate", true,
			"tradeClassIntfid", true, "tradeClassStartDate", true, "source",
			true, "createdBy", true, "createdDate", true, "addChgDelIndicator",
			true, "batchId", true, "errorField", true, "errorCode", true,
			"tradeClass", true, "intfInsertedDate", true, "modifiedBy", true,
			"reprocessedFlag", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		IvldCompanyTradeClass existingIvldCompanyTradeClass = _persistence.fetchByPrimaryKey(newIvldCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyTradeClass,
			newIvldCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyTradeClass missingIvldCompanyTradeClass = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass1 = addIvldCompanyTradeClass();
		IvldCompanyTradeClass newIvldCompanyTradeClass2 = addIvldCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyTradeClass1.getPrimaryKey());
		primaryKeys.add(newIvldCompanyTradeClass2.getPrimaryKey());

		Map<Serializable, IvldCompanyTradeClass> ivldCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCompanyTradeClasses.size());
		Assert.assertEquals(newIvldCompanyTradeClass1,
			ivldCompanyTradeClasses.get(
				newIvldCompanyTradeClass1.getPrimaryKey()));
		Assert.assertEquals(newIvldCompanyTradeClass2,
			ivldCompanyTradeClasses.get(
				newIvldCompanyTradeClass2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCompanyTradeClass> ivldCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyTradeClass.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCompanyTradeClass> ivldCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyTradeClasses.size());
		Assert.assertEquals(newIvldCompanyTradeClass,
			ivldCompanyTradeClasses.get(
				newIvldCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCompanyTradeClass> ivldCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyTradeClass.getPrimaryKey());

		Map<Serializable, IvldCompanyTradeClass> ivldCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyTradeClasses.size());
		Assert.assertEquals(newIvldCompanyTradeClass,
			ivldCompanyTradeClasses.get(
				newIvldCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCompanyTradeClassLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCompanyTradeClass>() {
				@Override
				public void performAction(
					IvldCompanyTradeClass ivldCompanyTradeClass) {
					Assert.assertNotNull(ivldCompanyTradeClass);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCompanyTradeClassSid",
				newIvldCompanyTradeClass.getIvldCompanyTradeClassSid()));

		List<IvldCompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCompanyTradeClass existingIvldCompanyTradeClass = result.get(0);

		Assert.assertEquals(existingIvldCompanyTradeClass,
			newIvldCompanyTradeClass);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCompanyTradeClassSid", RandomTestUtil.nextInt()));

		List<IvldCompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCompanyTradeClass newIvldCompanyTradeClass = addIvldCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyTradeClassSid"));

		Object newIvldCompanyTradeClassSid = newIvldCompanyTradeClass.getIvldCompanyTradeClassSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCompanyTradeClassSid",
				new Object[] { newIvldCompanyTradeClassSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCompanyTradeClassSid = result.get(0);

		Assert.assertEquals(existingIvldCompanyTradeClassSid,
			newIvldCompanyTradeClassSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyTradeClassSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCompanyTradeClassSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCompanyTradeClass addIvldCompanyTradeClass()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyTradeClass ivldCompanyTradeClass = _persistence.create(pk);

		ivldCompanyTradeClass.setPriorTradeClass(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setReasonForFailure(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setCompanyIdString(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setLastUpdatedDate(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		ivldCompanyTradeClass.setTradeClassEndDate(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setTradeClassIntfid(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setTradeClassStartDate(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setSource(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setCreatedBy(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		ivldCompanyTradeClass.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setBatchId(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setErrorField(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setErrorCode(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setTradeClass(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCompanyTradeClass.setModifiedBy(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCompanyTradeClass.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyTradeClasses.add(_persistence.update(ivldCompanyTradeClass));

		return ivldCompanyTradeClass;
	}

	private List<IvldCompanyTradeClass> _ivldCompanyTradeClasses = new ArrayList<IvldCompanyTradeClass>();
	private IvldCompanyTradeClassPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}