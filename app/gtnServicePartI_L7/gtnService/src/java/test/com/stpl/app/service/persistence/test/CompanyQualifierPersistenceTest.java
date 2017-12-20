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
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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

import com.stpl.app.exception.NoSuchCompanyQualifierException;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyQualifierPersistence;
import com.stpl.app.service.persistence.CompanyQualifierUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CompanyQualifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyQualifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyQualifier> iterator = _companyQualifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyQualifier companyQualifier = _persistence.create(pk);

		Assert.assertNotNull(companyQualifier);

		Assert.assertEquals(companyQualifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		_persistence.remove(newCompanyQualifier);

		CompanyQualifier existingCompanyQualifier = _persistence.fetchByPrimaryKey(newCompanyQualifier.getPrimaryKey());

		Assert.assertNull(existingCompanyQualifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyQualifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyQualifier newCompanyQualifier = _persistence.create(pk);

		newCompanyQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCompanyQualifier.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyQualifier.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyQualifier.setSource(RandomTestUtil.randomString());

		newCompanyQualifier.setCompanyQualifierValue(RandomTestUtil.randomString());

		newCompanyQualifier.setBatchId(RandomTestUtil.randomString());

		newCompanyQualifier.setCompanyQualifierName(RandomTestUtil.randomString());

		newCompanyQualifier.setEffectiveDates(RandomTestUtil.randomString());

		newCompanyQualifier.setNotes(RandomTestUtil.randomString());

		newCompanyQualifier.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyQualifier.setInboundStatus(RandomTestUtil.randomString());

		newCompanyQualifier.setModifiedDate(RandomTestUtil.nextDate());

		_companyQualifiers.add(_persistence.update(newCompanyQualifier));

		CompanyQualifier existingCompanyQualifier = _persistence.findByPrimaryKey(newCompanyQualifier.getPrimaryKey());

		Assert.assertEquals(existingCompanyQualifier.getRecordLockStatus(),
			newCompanyQualifier.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyQualifier.getCreatedDate()),
			Time.getShortTimestamp(newCompanyQualifier.getCreatedDate()));
		Assert.assertEquals(existingCompanyQualifier.getCreatedBy(),
			newCompanyQualifier.getCreatedBy());
		Assert.assertEquals(existingCompanyQualifier.getSource(),
			newCompanyQualifier.getSource());
		Assert.assertEquals(existingCompanyQualifier.getCompanyQualifierValue(),
			newCompanyQualifier.getCompanyQualifierValue());
		Assert.assertEquals(existingCompanyQualifier.getBatchId(),
			newCompanyQualifier.getBatchId());
		Assert.assertEquals(existingCompanyQualifier.getCompanyQualifierSid(),
			newCompanyQualifier.getCompanyQualifierSid());
		Assert.assertEquals(existingCompanyQualifier.getCompanyQualifierName(),
			newCompanyQualifier.getCompanyQualifierName());
		Assert.assertEquals(existingCompanyQualifier.getEffectiveDates(),
			newCompanyQualifier.getEffectiveDates());
		Assert.assertEquals(existingCompanyQualifier.getNotes(),
			newCompanyQualifier.getNotes());
		Assert.assertEquals(existingCompanyQualifier.getModifiedBy(),
			newCompanyQualifier.getModifiedBy());
		Assert.assertEquals(existingCompanyQualifier.getInboundStatus(),
			newCompanyQualifier.getInboundStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyQualifier.getModifiedDate()),
			Time.getShortTimestamp(newCompanyQualifier.getModifiedDate()));
	}

	@Test
	public void testCountByCompanyCrtQualifierName() throws Exception {
		_persistence.countByCompanyCrtQualifierName(StringPool.BLANK);

		_persistence.countByCompanyCrtQualifierName(StringPool.NULL);

		_persistence.countByCompanyCrtQualifierName((String)null);
	}

	@Test
	public void testCountByCompanyCrtQualifierByName()
		throws Exception {
		_persistence.countByCompanyCrtQualifierByName(StringPool.BLANK);

		_persistence.countByCompanyCrtQualifierByName(StringPool.NULL);

		_persistence.countByCompanyCrtQualifierByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		CompanyQualifier existingCompanyQualifier = _persistence.findByPrimaryKey(newCompanyQualifier.getPrimaryKey());

		Assert.assertEquals(existingCompanyQualifier, newCompanyQualifier);
	}

	@Test(expected = NoSuchCompanyQualifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyQualifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_QUALIFIER",
			"recordLockStatus", true, "createdDate", true, "createdBy", true,
			"source", true, "companyQualifierValue", true, "batchId", true,
			"companyQualifierSid", true, "companyQualifierName", true,
			"effectiveDates", true, "notes", true, "modifiedBy", true,
			"inboundStatus", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		CompanyQualifier existingCompanyQualifier = _persistence.fetchByPrimaryKey(newCompanyQualifier.getPrimaryKey());

		Assert.assertEquals(existingCompanyQualifier, newCompanyQualifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyQualifier missingCompanyQualifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyQualifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyQualifier newCompanyQualifier1 = addCompanyQualifier();
		CompanyQualifier newCompanyQualifier2 = addCompanyQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyQualifier1.getPrimaryKey());
		primaryKeys.add(newCompanyQualifier2.getPrimaryKey());

		Map<Serializable, CompanyQualifier> companyQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyQualifiers.size());
		Assert.assertEquals(newCompanyQualifier1,
			companyQualifiers.get(newCompanyQualifier1.getPrimaryKey()));
		Assert.assertEquals(newCompanyQualifier2,
			companyQualifiers.get(newCompanyQualifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyQualifier> companyQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyQualifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyQualifier> companyQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyQualifiers.size());
		Assert.assertEquals(newCompanyQualifier,
			companyQualifiers.get(newCompanyQualifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyQualifier> companyQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyQualifier.getPrimaryKey());

		Map<Serializable, CompanyQualifier> companyQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyQualifiers.size());
		Assert.assertEquals(newCompanyQualifier,
			companyQualifiers.get(newCompanyQualifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyQualifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyQualifier>() {
				@Override
				public void performAction(CompanyQualifier companyQualifier) {
					Assert.assertNotNull(companyQualifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyQualifierSid",
				newCompanyQualifier.getCompanyQualifierSid()));

		List<CompanyQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyQualifier existingCompanyQualifier = result.get(0);

		Assert.assertEquals(existingCompanyQualifier, newCompanyQualifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyQualifierSid",
				RandomTestUtil.nextInt()));

		List<CompanyQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyQualifierSid"));

		Object newCompanyQualifierSid = newCompanyQualifier.getCompanyQualifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyQualifierSid",
				new Object[] { newCompanyQualifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyQualifierSid = result.get(0);

		Assert.assertEquals(existingCompanyQualifierSid, newCompanyQualifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyQualifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyQualifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CompanyQualifier newCompanyQualifier = addCompanyQualifier();

		_persistence.clearCache();

		CompanyQualifier existingCompanyQualifier = _persistence.findByPrimaryKey(newCompanyQualifier.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCompanyQualifier.getCompanyQualifierName(),
				ReflectionTestUtil.invoke(existingCompanyQualifier,
					"getOriginalCompanyQualifierName", new Class<?>[0])));
	}

	protected CompanyQualifier addCompanyQualifier() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyQualifier companyQualifier = _persistence.create(pk);

		companyQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		companyQualifier.setCreatedDate(RandomTestUtil.nextDate());

		companyQualifier.setCreatedBy(RandomTestUtil.nextInt());

		companyQualifier.setSource(RandomTestUtil.randomString());

		companyQualifier.setCompanyQualifierValue(RandomTestUtil.randomString());

		companyQualifier.setBatchId(RandomTestUtil.randomString());

		companyQualifier.setCompanyQualifierName(RandomTestUtil.randomString());

		companyQualifier.setEffectiveDates(RandomTestUtil.randomString());

		companyQualifier.setNotes(RandomTestUtil.randomString());

		companyQualifier.setModifiedBy(RandomTestUtil.nextInt());

		companyQualifier.setInboundStatus(RandomTestUtil.randomString());

		companyQualifier.setModifiedDate(RandomTestUtil.nextDate());

		_companyQualifiers.add(_persistence.update(companyQualifier));

		return companyQualifier;
	}

	private List<CompanyQualifier> _companyQualifiers = new ArrayList<CompanyQualifier>();
	private CompanyQualifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}