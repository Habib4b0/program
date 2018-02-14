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

import com.stpl.app.exception.NoSuchCompanyTradeClassException;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyTradeClassPersistence;
import com.stpl.app.service.persistence.CompanyTradeClassUtil;

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
public class CompanyTradeClassPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyTradeClassUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyTradeClass> iterator = _companyTradeClasses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyTradeClass companyTradeClass = _persistence.create(pk);

		Assert.assertNotNull(companyTradeClass);

		Assert.assertEquals(companyTradeClass.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		_persistence.remove(newCompanyTradeClass);

		CompanyTradeClass existingCompanyTradeClass = _persistence.fetchByPrimaryKey(newCompanyTradeClass.getPrimaryKey());

		Assert.assertNull(existingCompanyTradeClass);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyTradeClass();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyTradeClass newCompanyTradeClass = _persistence.create(pk);

		newCompanyTradeClass.setPriorTradeClass(RandomTestUtil.nextInt());

		newCompanyTradeClass.setLastUpdatedDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setTradeClassEndDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setTradeClassStartDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCompanyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyTradeClass.setSource(RandomTestUtil.randomString());

		newCompanyTradeClass.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyTradeClass.setBatchId(RandomTestUtil.randomString());

		newCompanyTradeClass.setCompanyTradeClass(RandomTestUtil.nextInt());

		newCompanyTradeClass.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyTradeClass.setInboundStatus(RandomTestUtil.randomString());

		newCompanyTradeClass.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyTradeClasses.add(_persistence.update(newCompanyTradeClass));

		CompanyTradeClass existingCompanyTradeClass = _persistence.findByPrimaryKey(newCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingCompanyTradeClass.getPriorTradeClass(),
			newCompanyTradeClass.getPriorTradeClass());
		Assert.assertEquals(existingCompanyTradeClass.getCompanyTradeClassSid(),
			newCompanyTradeClass.getCompanyTradeClassSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getLastUpdatedDate()),
			Time.getShortTimestamp(newCompanyTradeClass.getLastUpdatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getPriorTradeClassStartDate()),
			Time.getShortTimestamp(
				newCompanyTradeClass.getPriorTradeClassStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getModifiedDate()),
			Time.getShortTimestamp(newCompanyTradeClass.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getTradeClassEndDate()),
			Time.getShortTimestamp(newCompanyTradeClass.getTradeClassEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getTradeClassStartDate()),
			Time.getShortTimestamp(
				newCompanyTradeClass.getTradeClassStartDate()));
		Assert.assertEquals(existingCompanyTradeClass.getRecordLockStatus(),
			newCompanyTradeClass.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyTradeClass.getCreatedDate()),
			Time.getShortTimestamp(newCompanyTradeClass.getCreatedDate()));
		Assert.assertEquals(existingCompanyTradeClass.getSource(),
			newCompanyTradeClass.getSource());
		Assert.assertEquals(existingCompanyTradeClass.getCreatedBy(),
			newCompanyTradeClass.getCreatedBy());
		Assert.assertEquals(existingCompanyTradeClass.getBatchId(),
			newCompanyTradeClass.getBatchId());
		Assert.assertEquals(existingCompanyTradeClass.getCompanyTradeClass(),
			newCompanyTradeClass.getCompanyTradeClass());
		Assert.assertEquals(existingCompanyTradeClass.getModifiedBy(),
			newCompanyTradeClass.getModifiedBy());
		Assert.assertEquals(existingCompanyTradeClass.getInboundStatus(),
			newCompanyTradeClass.getInboundStatus());
		Assert.assertEquals(existingCompanyTradeClass.getCompanyMasterSid(),
			newCompanyTradeClass.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		CompanyTradeClass existingCompanyTradeClass = _persistence.findByPrimaryKey(newCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingCompanyTradeClass, newCompanyTradeClass);
	}

	@Test(expected = NoSuchCompanyTradeClassException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyTradeClass> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_TRADE_CLASS",
			"priorTradeClass", true, "companyTradeClassSid", true,
			"lastUpdatedDate", true, "priorTradeClassStartDate", true,
			"modifiedDate", true, "tradeClassEndDate", true,
			"tradeClassStartDate", true, "recordLockStatus", true,
			"createdDate", true, "source", true, "createdBy", true, "batchId",
			true, "companyTradeClass", true, "modifiedBy", true,
			"inboundStatus", true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		CompanyTradeClass existingCompanyTradeClass = _persistence.fetchByPrimaryKey(newCompanyTradeClass.getPrimaryKey());

		Assert.assertEquals(existingCompanyTradeClass, newCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyTradeClass missingCompanyTradeClass = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyTradeClass);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyTradeClass newCompanyTradeClass1 = addCompanyTradeClass();
		CompanyTradeClass newCompanyTradeClass2 = addCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyTradeClass1.getPrimaryKey());
		primaryKeys.add(newCompanyTradeClass2.getPrimaryKey());

		Map<Serializable, CompanyTradeClass> companyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyTradeClasses.size());
		Assert.assertEquals(newCompanyTradeClass1,
			companyTradeClasses.get(newCompanyTradeClass1.getPrimaryKey()));
		Assert.assertEquals(newCompanyTradeClass2,
			companyTradeClasses.get(newCompanyTradeClass2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyTradeClass> companyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyTradeClass.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyTradeClass> companyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyTradeClasses.size());
		Assert.assertEquals(newCompanyTradeClass,
			companyTradeClasses.get(newCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyTradeClass> companyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyTradeClasses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyTradeClass.getPrimaryKey());

		Map<Serializable, CompanyTradeClass> companyTradeClasses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyTradeClasses.size());
		Assert.assertEquals(newCompanyTradeClass,
			companyTradeClasses.get(newCompanyTradeClass.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyTradeClassLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyTradeClass>() {
				@Override
				public void performAction(CompanyTradeClass companyTradeClass) {
					Assert.assertNotNull(companyTradeClass);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClassSid",
				newCompanyTradeClass.getCompanyTradeClassSid()));

		List<CompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyTradeClass existingCompanyTradeClass = result.get(0);

		Assert.assertEquals(existingCompanyTradeClass, newCompanyTradeClass);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClassSid",
				RandomTestUtil.nextInt()));

		List<CompanyTradeClass> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyTradeClass newCompanyTradeClass = addCompanyTradeClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyTradeClassSid"));

		Object newCompanyTradeClassSid = newCompanyTradeClass.getCompanyTradeClassSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyTradeClassSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyTradeClassSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyTradeClass addCompanyTradeClass()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyTradeClass companyTradeClass = _persistence.create(pk);

		companyTradeClass.setPriorTradeClass(RandomTestUtil.nextInt());

		companyTradeClass.setLastUpdatedDate(RandomTestUtil.nextDate());

		companyTradeClass.setPriorTradeClassStartDate(RandomTestUtil.nextDate());

		companyTradeClass.setModifiedDate(RandomTestUtil.nextDate());

		companyTradeClass.setTradeClassEndDate(RandomTestUtil.nextDate());

		companyTradeClass.setTradeClassStartDate(RandomTestUtil.nextDate());

		companyTradeClass.setRecordLockStatus(RandomTestUtil.randomBoolean());

		companyTradeClass.setCreatedDate(RandomTestUtil.nextDate());

		companyTradeClass.setSource(RandomTestUtil.randomString());

		companyTradeClass.setCreatedBy(RandomTestUtil.nextInt());

		companyTradeClass.setBatchId(RandomTestUtil.randomString());

		companyTradeClass.setCompanyTradeClass(RandomTestUtil.nextInt());

		companyTradeClass.setModifiedBy(RandomTestUtil.nextInt());

		companyTradeClass.setInboundStatus(RandomTestUtil.randomString());

		companyTradeClass.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyTradeClasses.add(_persistence.update(companyTradeClass));

		return companyTradeClass;
	}

	private List<CompanyTradeClass> _companyTradeClasses = new ArrayList<CompanyTradeClass>();
	private CompanyTradeClassPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}