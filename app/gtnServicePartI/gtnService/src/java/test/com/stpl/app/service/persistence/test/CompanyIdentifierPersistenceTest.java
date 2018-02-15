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

import com.stpl.app.exception.NoSuchCompanyIdentifierException;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyIdentifierPersistence;
import com.stpl.app.service.persistence.CompanyIdentifierUtil;

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
public class CompanyIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyIdentifier> iterator = _companyIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyIdentifier companyIdentifier = _persistence.create(pk);

		Assert.assertNotNull(companyIdentifier);

		Assert.assertEquals(companyIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		_persistence.remove(newCompanyIdentifier);

		CompanyIdentifier existingCompanyIdentifier = _persistence.fetchByPrimaryKey(newCompanyIdentifier.getPrimaryKey());

		Assert.assertNull(existingCompanyIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyIdentifier newCompanyIdentifier = _persistence.create(pk);

		newCompanyIdentifier.setEndDate(RandomTestUtil.nextDate());

		newCompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyIdentifier.setIdentifierStatus(RandomTestUtil.nextInt());

		newCompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		newCompanyIdentifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCompanyIdentifier.setStartDate(RandomTestUtil.nextDate());

		newCompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyIdentifier.setSource(RandomTestUtil.randomString());

		newCompanyIdentifier.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyIdentifier.setCompanyStringIdentifierValue(RandomTestUtil.randomString());

		newCompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		newCompanyIdentifier.setCompanyQualifierSid(RandomTestUtil.nextInt());

		newCompanyIdentifier.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyIdentifier.setInboundStatus(RandomTestUtil.randomString());

		newCompanyIdentifier.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyIdentifiers.add(_persistence.update(newCompanyIdentifier));

		CompanyIdentifier existingCompanyIdentifier = _persistence.findByPrimaryKey(newCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyIdentifier.getEndDate()),
			Time.getShortTimestamp(newCompanyIdentifier.getEndDate()));
		Assert.assertEquals(existingCompanyIdentifier.getCompanyStringIdentifierSid(),
			newCompanyIdentifier.getCompanyStringIdentifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newCompanyIdentifier.getModifiedDate()));
		Assert.assertEquals(existingCompanyIdentifier.getIdentifierStatus(),
			newCompanyIdentifier.getIdentifierStatus());
		Assert.assertEquals(existingCompanyIdentifier.getEntityCode(),
			newCompanyIdentifier.getEntityCode());
		Assert.assertEquals(existingCompanyIdentifier.getRecordLockStatus(),
			newCompanyIdentifier.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyIdentifier.getStartDate()),
			Time.getShortTimestamp(newCompanyIdentifier.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newCompanyIdentifier.getCreatedDate()));
		Assert.assertEquals(existingCompanyIdentifier.getSource(),
			newCompanyIdentifier.getSource());
		Assert.assertEquals(existingCompanyIdentifier.getCreatedBy(),
			newCompanyIdentifier.getCreatedBy());
		Assert.assertEquals(existingCompanyIdentifier.getCompanyStringIdentifierValue(),
			newCompanyIdentifier.getCompanyStringIdentifierValue());
		Assert.assertEquals(existingCompanyIdentifier.getBatchId(),
			newCompanyIdentifier.getBatchId());
		Assert.assertEquals(existingCompanyIdentifier.getCompanyQualifierSid(),
			newCompanyIdentifier.getCompanyQualifierSid());
		Assert.assertEquals(existingCompanyIdentifier.getModifiedBy(),
			newCompanyIdentifier.getModifiedBy());
		Assert.assertEquals(existingCompanyIdentifier.getInboundStatus(),
			newCompanyIdentifier.getInboundStatus());
		Assert.assertEquals(existingCompanyIdentifier.getCompanyMasterSid(),
			newCompanyIdentifier.getCompanyMasterSid());
	}

	@Test
	public void testCountByCompanyCrtIdentifier() throws Exception {
		_persistence.countByCompanyCrtIdentifier(StringPool.BLANK,
			RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
			RandomTestUtil.nextDate());

		_persistence.countByCompanyCrtIdentifier(StringPool.NULL, 0, 0,
			RandomTestUtil.nextDate());

		_persistence.countByCompanyCrtIdentifier((String)null, 0, 0,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByCompanyCrtIdentifierDetails()
		throws Exception {
		_persistence.countByCompanyCrtIdentifierDetails(RandomTestUtil.nextInt());

		_persistence.countByCompanyCrtIdentifierDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		CompanyIdentifier existingCompanyIdentifier = _persistence.findByPrimaryKey(newCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingCompanyIdentifier, newCompanyIdentifier);
	}

	@Test(expected = NoSuchCompanyIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_IDENTIFIER",
			"endDate", true, "companyStringIdentifierSid", true,
			"modifiedDate", true, "identifierStatus", true, "entityCode", true,
			"recordLockStatus", true, "startDate", true, "createdDate", true,
			"source", true, "createdBy", true, "companyStringIdentifierValue",
			true, "batchId", true, "companyQualifierSid", true, "modifiedBy",
			true, "inboundStatus", true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		CompanyIdentifier existingCompanyIdentifier = _persistence.fetchByPrimaryKey(newCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingCompanyIdentifier, newCompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyIdentifier missingCompanyIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyIdentifier newCompanyIdentifier1 = addCompanyIdentifier();
		CompanyIdentifier newCompanyIdentifier2 = addCompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyIdentifier1.getPrimaryKey());
		primaryKeys.add(newCompanyIdentifier2.getPrimaryKey());

		Map<Serializable, CompanyIdentifier> companyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyIdentifiers.size());
		Assert.assertEquals(newCompanyIdentifier1,
			companyIdentifiers.get(newCompanyIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newCompanyIdentifier2,
			companyIdentifiers.get(newCompanyIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyIdentifier> companyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyIdentifier> companyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyIdentifiers.size());
		Assert.assertEquals(newCompanyIdentifier,
			companyIdentifiers.get(newCompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyIdentifier> companyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyIdentifier.getPrimaryKey());

		Map<Serializable, CompanyIdentifier> companyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyIdentifiers.size());
		Assert.assertEquals(newCompanyIdentifier,
			companyIdentifiers.get(newCompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyIdentifier>() {
				@Override
				public void performAction(CompanyIdentifier companyIdentifier) {
					Assert.assertNotNull(companyIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"companyStringIdentifierSid",
				newCompanyIdentifier.getCompanyStringIdentifierSid()));

		List<CompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyIdentifier existingCompanyIdentifier = result.get(0);

		Assert.assertEquals(existingCompanyIdentifier, newCompanyIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"companyStringIdentifierSid", RandomTestUtil.nextInt()));

		List<CompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyIdentifier newCompanyIdentifier = addCompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyStringIdentifierSid"));

		Object newCompanyStringIdentifierSid = newCompanyIdentifier.getCompanyStringIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"companyStringIdentifierSid",
				new Object[] { newCompanyStringIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyStringIdentifierSid = result.get(0);

		Assert.assertEquals(existingCompanyStringIdentifierSid,
			newCompanyStringIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyStringIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"companyStringIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyIdentifier addCompanyIdentifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyIdentifier companyIdentifier = _persistence.create(pk);

		companyIdentifier.setEndDate(RandomTestUtil.nextDate());

		companyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		companyIdentifier.setIdentifierStatus(RandomTestUtil.nextInt());

		companyIdentifier.setEntityCode(RandomTestUtil.randomString());

		companyIdentifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		companyIdentifier.setStartDate(RandomTestUtil.nextDate());

		companyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		companyIdentifier.setSource(RandomTestUtil.randomString());

		companyIdentifier.setCreatedBy(RandomTestUtil.nextInt());

		companyIdentifier.setCompanyStringIdentifierValue(RandomTestUtil.randomString());

		companyIdentifier.setBatchId(RandomTestUtil.randomString());

		companyIdentifier.setCompanyQualifierSid(RandomTestUtil.nextInt());

		companyIdentifier.setModifiedBy(RandomTestUtil.nextInt());

		companyIdentifier.setInboundStatus(RandomTestUtil.randomString());

		companyIdentifier.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyIdentifiers.add(_persistence.update(companyIdentifier));

		return companyIdentifier;
	}

	private List<CompanyIdentifier> _companyIdentifiers = new ArrayList<CompanyIdentifier>();
	private CompanyIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}