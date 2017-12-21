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

import com.stpl.app.parttwo.exception.NoSuchIvldcompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldcompanyIdentifier;
import com.stpl.app.parttwo.service.IvldcompanyIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldcompanyIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.IvldcompanyIdentifierUtil;

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
public class IvldcompanyIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldcompanyIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldcompanyIdentifier> iterator = _ivldcompanyIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldcompanyIdentifier ivldcompanyIdentifier = _persistence.create(pk);

		Assert.assertNotNull(ivldcompanyIdentifier);

		Assert.assertEquals(ivldcompanyIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		_persistence.remove(newIvldcompanyIdentifier);

		IvldcompanyIdentifier existingIvldcompanyIdentifier = _persistence.fetchByPrimaryKey(newIvldcompanyIdentifier.getPrimaryKey());

		Assert.assertNull(existingIvldcompanyIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldcompanyIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldcompanyIdentifier newIvldcompanyIdentifier = _persistence.create(pk);

		newIvldcompanyIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCompanyId(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setEndDate(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newIvldcompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCompanyIdentifierIntfid(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setStartDate(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setSource(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newIvldcompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setErrorField(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setErrorCode(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldcompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldcompanyIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldcompanyIdentifiers.add(_persistence.update(
				newIvldcompanyIdentifier));

		IvldcompanyIdentifier existingIvldcompanyIdentifier = _persistence.findByPrimaryKey(newIvldcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldcompanyIdentifier.getReasonForFailure(),
			newIvldcompanyIdentifier.getReasonForFailure());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCompanyId(),
			newIvldcompanyIdentifier.getCompanyId());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCompanyName(),
			newIvldcompanyIdentifier.getCompanyName());
		Assert.assertEquals(existingIvldcompanyIdentifier.getEndDate(),
			newIvldcompanyIdentifier.getEndDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldcompanyIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newIvldcompanyIdentifier.getModifiedDate()));
		Assert.assertEquals(existingIvldcompanyIdentifier.getIdentifierStatus(),
			newIvldcompanyIdentifier.getIdentifierStatus());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCompanyIdentifier(),
			newIvldcompanyIdentifier.getCompanyIdentifier());
		Assert.assertEquals(existingIvldcompanyIdentifier.getEntityCode(),
			newIvldcompanyIdentifier.getEntityCode());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCompanyIdentifierIntfid(),
			newIvldcompanyIdentifier.getCompanyIdentifierIntfid());
		Assert.assertEquals(existingIvldcompanyIdentifier.getStartDate(),
			newIvldcompanyIdentifier.getStartDate());
		Assert.assertEquals(existingIvldcompanyIdentifier.getSource(),
			newIvldcompanyIdentifier.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldcompanyIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newIvldcompanyIdentifier.getCreatedDate()));
		Assert.assertEquals(existingIvldcompanyIdentifier.getCreatedBy(),
			newIvldcompanyIdentifier.getCreatedBy());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCompanyNo(),
			newIvldcompanyIdentifier.getCompanyNo());
		Assert.assertEquals(existingIvldcompanyIdentifier.getAddChgDelIndicator(),
			newIvldcompanyIdentifier.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldcompanyIdentifier.getBatchId(),
			newIvldcompanyIdentifier.getBatchId());
		Assert.assertEquals(existingIvldcompanyIdentifier.getErrorField(),
			newIvldcompanyIdentifier.getErrorField());
		Assert.assertEquals(existingIvldcompanyIdentifier.getErrorCode(),
			newIvldcompanyIdentifier.getErrorCode());
		Assert.assertEquals(existingIvldcompanyIdentifier.getIdentifierCodeQualifierName(),
			newIvldcompanyIdentifier.getIdentifierCodeQualifierName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldcompanyIdentifier.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldcompanyIdentifier.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldcompanyIdentifier.getModifiedBy(),
			newIvldcompanyIdentifier.getModifiedBy());
		Assert.assertEquals(existingIvldcompanyIdentifier.getIvldcompanyIdentifierSid(),
			newIvldcompanyIdentifier.getIvldcompanyIdentifierSid());
		Assert.assertEquals(existingIvldcompanyIdentifier.getReprocessedFlag(),
			newIvldcompanyIdentifier.getReprocessedFlag());
		Assert.assertEquals(existingIvldcompanyIdentifier.getIdentifierCodeQualifier(),
			newIvldcompanyIdentifier.getIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldcompanyIdentifier.getCheckRecord(),
			newIvldcompanyIdentifier.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		IvldcompanyIdentifier existingIvldcompanyIdentifier = _persistence.findByPrimaryKey(newIvldcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldcompanyIdentifier,
			newIvldcompanyIdentifier);
	}

	@Test(expected = NoSuchIvldcompanyIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldcompanyIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_COMPANY_IDENTIFIER",
			"reasonForFailure", true, "companyId", true, "companyName", true,
			"endDate", true, "modifiedDate", true, "identifierStatus", true,
			"companyIdentifier", true, "entityCode", true,
			"companyIdentifierIntfid", true, "startDate", true, "source", true,
			"createdDate", true, "createdBy", true, "companyNo", true,
			"addChgDelIndicator", true, "batchId", true, "errorField", true,
			"errorCode", true, "identifierCodeQualifierName", true,
			"intfInsertedDate", true, "modifiedBy", true,
			"ivldcompanyIdentifierSid", true, "reprocessedFlag", true,
			"identifierCodeQualifier", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		IvldcompanyIdentifier existingIvldcompanyIdentifier = _persistence.fetchByPrimaryKey(newIvldcompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldcompanyIdentifier,
			newIvldcompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldcompanyIdentifier missingIvldcompanyIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldcompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier1 = addIvldcompanyIdentifier();
		IvldcompanyIdentifier newIvldcompanyIdentifier2 = addIvldcompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldcompanyIdentifier1.getPrimaryKey());
		primaryKeys.add(newIvldcompanyIdentifier2.getPrimaryKey());

		Map<Serializable, IvldcompanyIdentifier> ivldcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldcompanyIdentifiers.size());
		Assert.assertEquals(newIvldcompanyIdentifier1,
			ivldcompanyIdentifiers.get(
				newIvldcompanyIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newIvldcompanyIdentifier2,
			ivldcompanyIdentifiers.get(
				newIvldcompanyIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldcompanyIdentifier> ivldcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldcompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldcompanyIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldcompanyIdentifier> ivldcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldcompanyIdentifiers.size());
		Assert.assertEquals(newIvldcompanyIdentifier,
			ivldcompanyIdentifiers.get(newIvldcompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldcompanyIdentifier> ivldcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldcompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldcompanyIdentifier.getPrimaryKey());

		Map<Serializable, IvldcompanyIdentifier> ivldcompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldcompanyIdentifiers.size());
		Assert.assertEquals(newIvldcompanyIdentifier,
			ivldcompanyIdentifiers.get(newIvldcompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldcompanyIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldcompanyIdentifier>() {
				@Override
				public void performAction(
					IvldcompanyIdentifier ivldcompanyIdentifier) {
					Assert.assertNotNull(ivldcompanyIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldcompanyIdentifierSid",
				newIvldcompanyIdentifier.getIvldcompanyIdentifierSid()));

		List<IvldcompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldcompanyIdentifier existingIvldcompanyIdentifier = result.get(0);

		Assert.assertEquals(existingIvldcompanyIdentifier,
			newIvldcompanyIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldcompanyIdentifierSid", RandomTestUtil.nextInt()));

		List<IvldcompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldcompanyIdentifier newIvldcompanyIdentifier = addIvldcompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldcompanyIdentifierSid"));

		Object newIvldcompanyIdentifierSid = newIvldcompanyIdentifier.getIvldcompanyIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldcompanyIdentifierSid",
				new Object[] { newIvldcompanyIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldcompanyIdentifierSid = result.get(0);

		Assert.assertEquals(existingIvldcompanyIdentifierSid,
			newIvldcompanyIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldcompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldcompanyIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldcompanyIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldcompanyIdentifier addIvldcompanyIdentifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldcompanyIdentifier ivldcompanyIdentifier = _persistence.create(pk);

		ivldcompanyIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCompanyId(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setEndDate(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		ivldcompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCompanyIdentifierIntfid(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setStartDate(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setSource(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		ivldcompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setErrorField(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setErrorCode(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldcompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldcompanyIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldcompanyIdentifiers.add(_persistence.update(ivldcompanyIdentifier));

		return ivldcompanyIdentifier;
	}

	private List<IvldcompanyIdentifier> _ivldcompanyIdentifiers = new ArrayList<IvldcompanyIdentifier>();
	private IvldcompanyIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}