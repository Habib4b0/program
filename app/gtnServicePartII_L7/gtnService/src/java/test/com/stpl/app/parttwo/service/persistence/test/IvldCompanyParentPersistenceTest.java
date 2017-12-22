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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyParentException;
import com.stpl.app.parttwo.model.IvldCompanyParent;
import com.stpl.app.parttwo.service.IvldCompanyParentLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCompanyParentPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyParentUtil;

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
public class IvldCompanyParentPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCompanyParentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCompanyParent> iterator = _ivldCompanyParents.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyParent ivldCompanyParent = _persistence.create(pk);

		Assert.assertNotNull(ivldCompanyParent);

		Assert.assertEquals(ivldCompanyParent.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		_persistence.remove(newIvldCompanyParent);

		IvldCompanyParent existingIvldCompanyParent = _persistence.fetchByPrimaryKey(newIvldCompanyParent.getPrimaryKey());

		Assert.assertNull(existingIvldCompanyParent);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCompanyParent();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyParent newIvldCompanyParent = _persistence.create(pk);

		newIvldCompanyParent.setParentcompanyId(RandomTestUtil.randomString());

		newIvldCompanyParent.setPriorParentcompanyId(RandomTestUtil.randomString());

		newIvldCompanyParent.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCompanyParent.setCompanyId(RandomTestUtil.randomString());

		newIvldCompanyParent.setLastUpdatedDate(RandomTestUtil.randomString());

		newIvldCompanyParent.setParentEndDate(RandomTestUtil.randomString());

		newIvldCompanyParent.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCompanyParent.setParentDetailsIntfid(RandomTestUtil.randomString());

		newIvldCompanyParent.setPriorParentStartDate(RandomTestUtil.randomString());

		newIvldCompanyParent.setSource(RandomTestUtil.randomString());

		newIvldCompanyParent.setCreatedBy(RandomTestUtil.randomString());

		newIvldCompanyParent.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCompanyParent.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCompanyParent.setBatchId(RandomTestUtil.randomString());

		newIvldCompanyParent.setErrorField(RandomTestUtil.randomString());

		newIvldCompanyParent.setErrorCode(RandomTestUtil.randomString());

		newIvldCompanyParent.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCompanyParent.setModifiedBy(RandomTestUtil.randomString());

		newIvldCompanyParent.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCompanyParent.setParentStartDate(RandomTestUtil.randomString());

		newIvldCompanyParent.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyParents.add(_persistence.update(newIvldCompanyParent));

		IvldCompanyParent existingIvldCompanyParent = _persistence.findByPrimaryKey(newIvldCompanyParent.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyParent.getParentcompanyId(),
			newIvldCompanyParent.getParentcompanyId());
		Assert.assertEquals(existingIvldCompanyParent.getPriorParentcompanyId(),
			newIvldCompanyParent.getPriorParentcompanyId());
		Assert.assertEquals(existingIvldCompanyParent.getReasonForFailure(),
			newIvldCompanyParent.getReasonForFailure());
		Assert.assertEquals(existingIvldCompanyParent.getCompanyId(),
			newIvldCompanyParent.getCompanyId());
		Assert.assertEquals(existingIvldCompanyParent.getLastUpdatedDate(),
			newIvldCompanyParent.getLastUpdatedDate());
		Assert.assertEquals(existingIvldCompanyParent.getParentEndDate(),
			newIvldCompanyParent.getParentEndDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyParent.getModifiedDate()),
			Time.getShortTimestamp(newIvldCompanyParent.getModifiedDate()));
		Assert.assertEquals(existingIvldCompanyParent.getParentDetailsIntfid(),
			newIvldCompanyParent.getParentDetailsIntfid());
		Assert.assertEquals(existingIvldCompanyParent.getPriorParentStartDate(),
			newIvldCompanyParent.getPriorParentStartDate());
		Assert.assertEquals(existingIvldCompanyParent.getSource(),
			newIvldCompanyParent.getSource());
		Assert.assertEquals(existingIvldCompanyParent.getCreatedBy(),
			newIvldCompanyParent.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyParent.getCreatedDate()),
			Time.getShortTimestamp(newIvldCompanyParent.getCreatedDate()));
		Assert.assertEquals(existingIvldCompanyParent.getAddChgDelIndicator(),
			newIvldCompanyParent.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCompanyParent.getBatchId(),
			newIvldCompanyParent.getBatchId());
		Assert.assertEquals(existingIvldCompanyParent.getIvldCompanyParentSid(),
			newIvldCompanyParent.getIvldCompanyParentSid());
		Assert.assertEquals(existingIvldCompanyParent.getErrorField(),
			newIvldCompanyParent.getErrorField());
		Assert.assertEquals(existingIvldCompanyParent.getErrorCode(),
			newIvldCompanyParent.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyParent.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldCompanyParent.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCompanyParent.getModifiedBy(),
			newIvldCompanyParent.getModifiedBy());
		Assert.assertEquals(existingIvldCompanyParent.getReprocessedFlag(),
			newIvldCompanyParent.getReprocessedFlag());
		Assert.assertEquals(existingIvldCompanyParent.getParentStartDate(),
			newIvldCompanyParent.getParentStartDate());
		Assert.assertEquals(existingIvldCompanyParent.getCheckRecord(),
			newIvldCompanyParent.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		IvldCompanyParent existingIvldCompanyParent = _persistence.findByPrimaryKey(newIvldCompanyParent.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyParent, newIvldCompanyParent);
	}

	@Test(expected = NoSuchIvldCompanyParentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCompanyParent> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_COMPANY_PARENT",
			"parentcompanyId", true, "priorParentcompanyId", true,
			"reasonForFailure", true, "companyId", true, "lastUpdatedDate",
			true, "parentEndDate", true, "modifiedDate", true,
			"parentDetailsIntfid", true, "priorParentStartDate", true,
			"source", true, "createdBy", true, "createdDate", true,
			"addChgDelIndicator", true, "batchId", true,
			"ivldCompanyParentSid", true, "errorField", true, "errorCode",
			true, "intfInsertedDate", true, "modifiedBy", true,
			"reprocessedFlag", true, "parentStartDate", true, "checkRecord",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		IvldCompanyParent existingIvldCompanyParent = _persistence.fetchByPrimaryKey(newIvldCompanyParent.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyParent, newIvldCompanyParent);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyParent missingIvldCompanyParent = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCompanyParent);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCompanyParent newIvldCompanyParent1 = addIvldCompanyParent();
		IvldCompanyParent newIvldCompanyParent2 = addIvldCompanyParent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyParent1.getPrimaryKey());
		primaryKeys.add(newIvldCompanyParent2.getPrimaryKey());

		Map<Serializable, IvldCompanyParent> ivldCompanyParents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCompanyParents.size());
		Assert.assertEquals(newIvldCompanyParent1,
			ivldCompanyParents.get(newIvldCompanyParent1.getPrimaryKey()));
		Assert.assertEquals(newIvldCompanyParent2,
			ivldCompanyParents.get(newIvldCompanyParent2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCompanyParent> ivldCompanyParents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyParents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyParent.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCompanyParent> ivldCompanyParents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyParents.size());
		Assert.assertEquals(newIvldCompanyParent,
			ivldCompanyParents.get(newIvldCompanyParent.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCompanyParent> ivldCompanyParents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyParents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyParent.getPrimaryKey());

		Map<Serializable, IvldCompanyParent> ivldCompanyParents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyParents.size());
		Assert.assertEquals(newIvldCompanyParent,
			ivldCompanyParents.get(newIvldCompanyParent.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCompanyParentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCompanyParent>() {
				@Override
				public void performAction(IvldCompanyParent ivldCompanyParent) {
					Assert.assertNotNull(ivldCompanyParent);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyParent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCompanyParentSid",
				newIvldCompanyParent.getIvldCompanyParentSid()));

		List<IvldCompanyParent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCompanyParent existingIvldCompanyParent = result.get(0);

		Assert.assertEquals(existingIvldCompanyParent, newIvldCompanyParent);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyParent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldCompanyParentSid",
				RandomTestUtil.nextInt()));

		List<IvldCompanyParent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCompanyParent newIvldCompanyParent = addIvldCompanyParent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyParent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyParentSid"));

		Object newIvldCompanyParentSid = newIvldCompanyParent.getIvldCompanyParentSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCompanyParentSid",
				new Object[] { newIvldCompanyParentSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCompanyParentSid = result.get(0);

		Assert.assertEquals(existingIvldCompanyParentSid,
			newIvldCompanyParentSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyParent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyParentSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldCompanyParentSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCompanyParent addIvldCompanyParent()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyParent ivldCompanyParent = _persistence.create(pk);

		ivldCompanyParent.setParentcompanyId(RandomTestUtil.randomString());

		ivldCompanyParent.setPriorParentcompanyId(RandomTestUtil.randomString());

		ivldCompanyParent.setReasonForFailure(RandomTestUtil.randomString());

		ivldCompanyParent.setCompanyId(RandomTestUtil.randomString());

		ivldCompanyParent.setLastUpdatedDate(RandomTestUtil.randomString());

		ivldCompanyParent.setParentEndDate(RandomTestUtil.randomString());

		ivldCompanyParent.setModifiedDate(RandomTestUtil.nextDate());

		ivldCompanyParent.setParentDetailsIntfid(RandomTestUtil.randomString());

		ivldCompanyParent.setPriorParentStartDate(RandomTestUtil.randomString());

		ivldCompanyParent.setSource(RandomTestUtil.randomString());

		ivldCompanyParent.setCreatedBy(RandomTestUtil.randomString());

		ivldCompanyParent.setCreatedDate(RandomTestUtil.nextDate());

		ivldCompanyParent.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCompanyParent.setBatchId(RandomTestUtil.randomString());

		ivldCompanyParent.setErrorField(RandomTestUtil.randomString());

		ivldCompanyParent.setErrorCode(RandomTestUtil.randomString());

		ivldCompanyParent.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCompanyParent.setModifiedBy(RandomTestUtil.randomString());

		ivldCompanyParent.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCompanyParent.setParentStartDate(RandomTestUtil.randomString());

		ivldCompanyParent.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyParents.add(_persistence.update(ivldCompanyParent));

		return ivldCompanyParent;
	}

	private List<IvldCompanyParent> _ivldCompanyParents = new ArrayList<IvldCompanyParent>();
	private IvldCompanyParentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}