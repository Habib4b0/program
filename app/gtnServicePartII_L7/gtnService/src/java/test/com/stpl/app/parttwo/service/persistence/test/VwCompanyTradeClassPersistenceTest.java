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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyTradeClassException;
import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.service.VwCompanyTradeClassLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassUtil;

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
public class VwCompanyTradeClassPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwCompanyTradeClassUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwCompanyTradeClass> iterator = _vwCompanyTradeClasses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyTradeClass vwCompanyTradeClass = _persistence.create(pk);

		Assert.assertNotNull(vwCompanyTradeClass);

		Assert.assertEquals(vwCompanyTradeClass.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		_persistence.remove(newVwCompanyTradeClass);

		VwCompanyTradeClass existingVwCompanyTradeClass = _persistence.fetchByPrimaryKey(newVwCompanyTradeClass.getPrimaryKey());

		Assert.assertNull(existingVwCompanyTradeClass);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwCompanyTradeClass();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyTradeClass newVwCompanyTradeClass = _persistence.create(pk);

		newVwCompanyTradeClass.setPriorTradeClass(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setCompanyIdString(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setLastUpdatedDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setTradeClassEndDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setTradeClassStartDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setSource(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setCreatedBy(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		newVwCompanyTradeClass.setCompanyTradeClass(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setBatchId(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwCompanyTradeClass.setModifiedBy(RandomTestUtil.randomString());

		_vwCompanyTradeClasses.add(_persistence.update(newVwCompanyTradeClass));

		VwCompanyTradeClass existingVwCompanyTradeClass = _persistence.findByPrimaryKey(newVwCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyTradeClass.getPriorTradeClass(),
			newVwCompanyTradeClass.getPriorTradeClass());
		Assert.assertEquals(existingVwCompanyTradeClass.getCompanyTradeClassSid(),
			newVwCompanyTradeClass.getCompanyTradeClassSid());
		Assert.assertEquals(existingVwCompanyTradeClass.getCompanyIdString(),
			newVwCompanyTradeClass.getCompanyIdString());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getLastUpdatedDate()),
			Time.getShortTimestamp(newVwCompanyTradeClass.getLastUpdatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getPriorTradeClassStartDate()),
			Time.getShortTimestamp(
				newVwCompanyTradeClass.getPriorTradeClassStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getModifiedDate()),
			Time.getShortTimestamp(newVwCompanyTradeClass.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getTradeClassEndDate()),
			Time.getShortTimestamp(
				newVwCompanyTradeClass.getTradeClassEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getTradeClassStartDate()),
			Time.getShortTimestamp(
				newVwCompanyTradeClass.getTradeClassStartDate()));
		Assert.assertEquals(existingVwCompanyTradeClass.getSource(),
			newVwCompanyTradeClass.getSource());
		Assert.assertEquals(existingVwCompanyTradeClass.getCreatedBy(),
			newVwCompanyTradeClass.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyTradeClass.getCreatedDate()),
			Time.getShortTimestamp(newVwCompanyTradeClass.getCreatedDate()));
		Assert.assertEquals(existingVwCompanyTradeClass.getCompanyTradeClass(),
			newVwCompanyTradeClass.getCompanyTradeClass());
		Assert.assertEquals(existingVwCompanyTradeClass.getBatchId(),
			newVwCompanyTradeClass.getBatchId());
		Assert.assertEquals(existingVwCompanyTradeClass.getAddChgDelIndicator(),
			newVwCompanyTradeClass.getAddChgDelIndicator());
		Assert.assertEquals(existingVwCompanyTradeClass.getModifiedBy(),
			newVwCompanyTradeClass.getModifiedBy());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		VwCompanyTradeClass existingVwCompanyTradeClass = _persistence.findByPrimaryKey(newVwCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyTradeClass, newVwCompanyTradeClass);
	}

	@Test(expected = NoSuchVwCompanyTradeClassException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwCompanyTradeClass> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_COMPANY_TRADE_CLASS",
			"priorTradeClass", true, "companyTradeClassSid", true,
			"companyIdString", true, "lastUpdatedDate", true,
			"priorTradeClassStartDate", true, "modifiedDate", true,
			"tradeClassEndDate", true, "tradeClassStartDate", true, "source",
			true, "createdBy", true, "createdDate", true, "companyTradeClass",
			true, "batchId", true, "addChgDelIndicator", true, "modifiedBy",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		VwCompanyTradeClass existingVwCompanyTradeClass = _persistence.fetchByPrimaryKey(newVwCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyTradeClass, newVwCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyTradeClass missingVwCompanyTradeClass = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass1 = addVwCompanyTradeClass();
		VwCompanyTradeClass newVwCompanyTradeClass2 = addVwCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyTradeClass1.getPrimaryKey());
		primaryKeys.add(newVwCompanyTradeClass2.getPrimaryKey());

		Map<Serializable, VwCompanyTradeClass> vwCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwCompanyTradeClasses.size());
		Assert.assertEquals(newVwCompanyTradeClass1,
			vwCompanyTradeClasses.get(newVwCompanyTradeClass1.getPrimaryKey()));
		Assert.assertEquals(newVwCompanyTradeClass2,
			vwCompanyTradeClasses.get(newVwCompanyTradeClass2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwCompanyTradeClass> vwCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyTradeClass.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwCompanyTradeClass> vwCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyTradeClasses.size());
		Assert.assertEquals(newVwCompanyTradeClass,
			vwCompanyTradeClasses.get(newVwCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwCompanyTradeClass> vwCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyTradeClass.getPrimaryKey());

		Map<Serializable, VwCompanyTradeClass> vwCompanyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyTradeClasses.size());
		Assert.assertEquals(newVwCompanyTradeClass,
			vwCompanyTradeClasses.get(newVwCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwCompanyTradeClassLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwCompanyTradeClass>() {
				@Override
				public void performAction(
					VwCompanyTradeClass vwCompanyTradeClass) {
					Assert.assertNotNull(vwCompanyTradeClass);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClassSid",
				newVwCompanyTradeClass.getCompanyTradeClassSid()));

		List<VwCompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwCompanyTradeClass existingVwCompanyTradeClass = result.get(0);

		Assert.assertEquals(existingVwCompanyTradeClass, newVwCompanyTradeClass);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClassSid",
				RandomTestUtil.nextInt()));

		List<VwCompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwCompanyTradeClass newVwCompanyTradeClass = addVwCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyTradeClassSid"));

		Object newCompanyTradeClassSid = newVwCompanyTradeClass.getCompanyTradeClassSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyTradeClassSid",
				new Object[] { newCompanyTradeClassSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyTradeClassSid = result.get(0);

		Assert.assertEquals(existingCompanyTradeClassSid,
			newCompanyTradeClassSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyTradeClassSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyTradeClassSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwCompanyTradeClass addVwCompanyTradeClass()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyTradeClass vwCompanyTradeClass = _persistence.create(pk);

		vwCompanyTradeClass.setPriorTradeClass(RandomTestUtil.randomString());

		vwCompanyTradeClass.setCompanyIdString(RandomTestUtil.randomString());

		vwCompanyTradeClass.setLastUpdatedDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setTradeClassEndDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setTradeClassStartDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setSource(RandomTestUtil.randomString());

		vwCompanyTradeClass.setCreatedBy(RandomTestUtil.randomString());

		vwCompanyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		vwCompanyTradeClass.setCompanyTradeClass(RandomTestUtil.randomString());

		vwCompanyTradeClass.setBatchId(RandomTestUtil.randomString());

		vwCompanyTradeClass.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwCompanyTradeClass.setModifiedBy(RandomTestUtil.randomString());

		_vwCompanyTradeClasses.add(_persistence.update(vwCompanyTradeClass));

		return vwCompanyTradeClass;
	}

	private List<VwCompanyTradeClass> _vwCompanyTradeClasses = new ArrayList<VwCompanyTradeClass>();
	private VwCompanyTradeClassPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}