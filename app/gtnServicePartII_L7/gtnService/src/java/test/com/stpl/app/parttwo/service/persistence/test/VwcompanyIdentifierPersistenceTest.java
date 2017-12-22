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

import com.stpl.app.parttwo.exception.NoSuchVwcompanyIdentifierException;
import com.stpl.app.parttwo.model.VwcompanyIdentifier;
import com.stpl.app.parttwo.service.VwcompanyIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwcompanyIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.VwcompanyIdentifierUtil;

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
public class VwcompanyIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwcompanyIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwcompanyIdentifier> iterator = _vwcompanyIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwcompanyIdentifier vwcompanyIdentifier = _persistence.create(pk);

		Assert.assertNotNull(vwcompanyIdentifier);

		Assert.assertEquals(vwcompanyIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		_persistence.remove(newVwcompanyIdentifier);

		VwcompanyIdentifier existingVwcompanyIdentifier = _persistence.fetchByPrimaryKey(newVwcompanyIdentifier.getPrimaryKey());

		Assert.assertNull(existingVwcompanyIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwcompanyIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwcompanyIdentifier newVwcompanyIdentifier = _persistence.create(pk);

		newVwcompanyIdentifier.setCompanyIdString(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setEndDate(RandomTestUtil.nextDate());

		newVwcompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newVwcompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setStartDate(RandomTestUtil.nextDate());

		newVwcompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newVwcompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setSource(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		newVwcompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		_vwcompanyIdentifiers.add(_persistence.update(newVwcompanyIdentifier));

		VwcompanyIdentifier existingVwcompanyIdentifier = _persistence.findByPrimaryKey(newVwcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwcompanyIdentifier.getCompanyIdString(),
			newVwcompanyIdentifier.getCompanyIdString());
		Assert.assertEquals(existingVwcompanyIdentifier.getCompanyName(),
			newVwcompanyIdentifier.getCompanyName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwcompanyIdentifier.getEndDate()),
			Time.getShortTimestamp(newVwcompanyIdentifier.getEndDate()));
		Assert.assertEquals(existingVwcompanyIdentifier.getCompanyIdentifierSid(),
			newVwcompanyIdentifier.getCompanyIdentifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwcompanyIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newVwcompanyIdentifier.getModifiedDate()));
		Assert.assertEquals(existingVwcompanyIdentifier.getIdentifierStatus(),
			newVwcompanyIdentifier.getIdentifierStatus());
		Assert.assertEquals(existingVwcompanyIdentifier.getCompanyIdentifier(),
			newVwcompanyIdentifier.getCompanyIdentifier());
		Assert.assertEquals(existingVwcompanyIdentifier.getEntityCode(),
			newVwcompanyIdentifier.getEntityCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwcompanyIdentifier.getStartDate()),
			Time.getShortTimestamp(newVwcompanyIdentifier.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwcompanyIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newVwcompanyIdentifier.getCreatedDate()));
		Assert.assertEquals(existingVwcompanyIdentifier.getCreatedBy(),
			newVwcompanyIdentifier.getCreatedBy());
		Assert.assertEquals(existingVwcompanyIdentifier.getSource(),
			newVwcompanyIdentifier.getSource());
		Assert.assertEquals(existingVwcompanyIdentifier.getCompanyNo(),
			newVwcompanyIdentifier.getCompanyNo());
		Assert.assertEquals(existingVwcompanyIdentifier.getBatchId(),
			newVwcompanyIdentifier.getBatchId());
		Assert.assertEquals(existingVwcompanyIdentifier.getAddChgDelIndicator(),
			newVwcompanyIdentifier.getAddChgDelIndicator());
		Assert.assertEquals(existingVwcompanyIdentifier.getIdentifierCodeQualifierName(),
			newVwcompanyIdentifier.getIdentifierCodeQualifierName());
		Assert.assertEquals(existingVwcompanyIdentifier.getModifiedBy(),
			newVwcompanyIdentifier.getModifiedBy());
		Assert.assertEquals(existingVwcompanyIdentifier.getIdentifierCodeQualifier(),
			newVwcompanyIdentifier.getIdentifierCodeQualifier());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		VwcompanyIdentifier existingVwcompanyIdentifier = _persistence.findByPrimaryKey(newVwcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwcompanyIdentifier, newVwcompanyIdentifier);
	}

	@Test(expected = NoSuchVwcompanyIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwcompanyIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_COMPANY_IDENTIFIER",
			"companyIdString", true, "companyName", true, "endDate", true,
			"companyIdentifierSid", true, "modifiedDate", true,
			"identifierStatus", true, "companyIdentifier", true, "entityCode",
			true, "startDate", true, "createdDate", true, "createdBy", true,
			"source", true, "companyNo", true, "batchId", true,
			"addChgDelIndicator", true, "identifierCodeQualifierName", true,
			"modifiedBy", true, "identifierCodeQualifier", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		VwcompanyIdentifier existingVwcompanyIdentifier = _persistence.fetchByPrimaryKey(newVwcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwcompanyIdentifier, newVwcompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwcompanyIdentifier missingVwcompanyIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwcompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier1 = addVwcompanyIdentifier();
		VwcompanyIdentifier newVwcompanyIdentifier2 = addVwcompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwcompanyIdentifier1.getPrimaryKey());
		primaryKeys.add(newVwcompanyIdentifier2.getPrimaryKey());

		Map<Serializable, VwcompanyIdentifier> vwcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwcompanyIdentifiers.size());
		Assert.assertEquals(newVwcompanyIdentifier1,
			vwcompanyIdentifiers.get(newVwcompanyIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newVwcompanyIdentifier2,
			vwcompanyIdentifiers.get(newVwcompanyIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwcompanyIdentifier> vwcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwcompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwcompanyIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwcompanyIdentifier> vwcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwcompanyIdentifiers.size());
		Assert.assertEquals(newVwcompanyIdentifier,
			vwcompanyIdentifiers.get(newVwcompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwcompanyIdentifier> vwcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwcompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwcompanyIdentifier.getPrimaryKey());

		Map<Serializable, VwcompanyIdentifier> vwcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwcompanyIdentifiers.size());
		Assert.assertEquals(newVwcompanyIdentifier,
			vwcompanyIdentifiers.get(newVwcompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwcompanyIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwcompanyIdentifier>() {
				@Override
				public void performAction(
					VwcompanyIdentifier vwcompanyIdentifier) {
					Assert.assertNotNull(vwcompanyIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyIdentifierSid",
				newVwcompanyIdentifier.getCompanyIdentifierSid()));

		List<VwcompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwcompanyIdentifier existingVwcompanyIdentifier = result.get(0);

		Assert.assertEquals(existingVwcompanyIdentifier, newVwcompanyIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyIdentifierSid",
				RandomTestUtil.nextInt()));

		List<VwcompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwcompanyIdentifier newVwcompanyIdentifier = addVwcompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyIdentifierSid"));

		Object newCompanyIdentifierSid = newVwcompanyIdentifier.getCompanyIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyIdentifierSid",
				new Object[] { newCompanyIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyIdentifierSid = result.get(0);

		Assert.assertEquals(existingCompanyIdentifierSid,
			newCompanyIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwcompanyIdentifier addVwcompanyIdentifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwcompanyIdentifier vwcompanyIdentifier = _persistence.create(pk);

		vwcompanyIdentifier.setCompanyIdString(RandomTestUtil.randomString());

		vwcompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		vwcompanyIdentifier.setEndDate(RandomTestUtil.nextDate());

		vwcompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		vwcompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		vwcompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		vwcompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		vwcompanyIdentifier.setStartDate(RandomTestUtil.nextDate());

		vwcompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		vwcompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		vwcompanyIdentifier.setSource(RandomTestUtil.randomString());

		vwcompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		vwcompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		vwcompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwcompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		vwcompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		vwcompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		_vwcompanyIdentifiers.add(_persistence.update(vwcompanyIdentifier));

		return vwcompanyIdentifier;
	}

	private List<VwcompanyIdentifier> _vwcompanyIdentifiers = new ArrayList<VwcompanyIdentifier>();
	private VwcompanyIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}