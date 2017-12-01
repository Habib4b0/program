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
import com.liferay.portal.kernel.test.AssertUtils;
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

import com.stpl.app.exception.NoSuchCdrDetailsException;
import com.stpl.app.model.CdrDetails;
import com.stpl.app.service.CdrDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CdrDetailsPersistence;
import com.stpl.app.service.persistence.CdrDetailsUtil;

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
public class CdrDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CdrDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CdrDetails> iterator = _cdrDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrDetails cdrDetails = _persistence.create(pk);

		Assert.assertNotNull(cdrDetails);

		Assert.assertEquals(cdrDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		_persistence.remove(newCdrDetails);

		CdrDetails existingCdrDetails = _persistence.fetchByPrimaryKey(newCdrDetails.getPrimaryKey());

		Assert.assertNull(existingCdrDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCdrDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrDetails newCdrDetails = _persistence.create(pk);

		newCdrDetails.setCreatedBy(RandomTestUtil.nextInt());

		newCdrDetails.setModifiedBy(RandomTestUtil.nextInt());

		newCdrDetails.setCreatedDate(RandomTestUtil.nextDate());

		newCdrDetails.setLineType(RandomTestUtil.randomString());

		newCdrDetails.setKeyword(RandomTestUtil.randomString());

		newCdrDetails.setItemGroupMsAssociation(RandomTestUtil.randomString());

		newCdrDetails.setValue(RandomTestUtil.nextDouble());

		newCdrDetails.setModifiedDate(RandomTestUtil.nextDate());

		newCdrDetails.setLogicalOperator(RandomTestUtil.randomString());

		newCdrDetails.setOperator(RandomTestUtil.randomString());

		newCdrDetails.setCdrModelSid(RandomTestUtil.nextInt());

		newCdrDetails.setComparison(RandomTestUtil.randomString());

		_cdrDetailses.add(_persistence.update(newCdrDetails));

		CdrDetails existingCdrDetails = _persistence.findByPrimaryKey(newCdrDetails.getPrimaryKey());

		Assert.assertEquals(existingCdrDetails.getCreatedBy(),
			newCdrDetails.getCreatedBy());
		Assert.assertEquals(existingCdrDetails.getModifiedBy(),
			newCdrDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCdrDetails.getCreatedDate()),
			Time.getShortTimestamp(newCdrDetails.getCreatedDate()));
		Assert.assertEquals(existingCdrDetails.getLineType(),
			newCdrDetails.getLineType());
		Assert.assertEquals(existingCdrDetails.getKeyword(),
			newCdrDetails.getKeyword());
		Assert.assertEquals(existingCdrDetails.getItemGroupMsAssociation(),
			newCdrDetails.getItemGroupMsAssociation());
		AssertUtils.assertEquals(existingCdrDetails.getValue(),
			newCdrDetails.getValue());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCdrDetails.getModifiedDate()),
			Time.getShortTimestamp(newCdrDetails.getModifiedDate()));
		Assert.assertEquals(existingCdrDetails.getLogicalOperator(),
			newCdrDetails.getLogicalOperator());
		Assert.assertEquals(existingCdrDetails.getOperator(),
			newCdrDetails.getOperator());
		Assert.assertEquals(existingCdrDetails.getCdrDetailsSid(),
			newCdrDetails.getCdrDetailsSid());
		Assert.assertEquals(existingCdrDetails.getCdrModelSid(),
			newCdrDetails.getCdrModelSid());
		Assert.assertEquals(existingCdrDetails.getComparison(),
			newCdrDetails.getComparison());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		CdrDetails existingCdrDetails = _persistence.findByPrimaryKey(newCdrDetails.getPrimaryKey());

		Assert.assertEquals(existingCdrDetails, newCdrDetails);
	}

	@Test(expected = NoSuchCdrDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CdrDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CDR_DETAILS", "createdBy",
			true, "modifiedBy", true, "createdDate", true, "lineType", true,
			"keyword", true, "itemGroupMsAssociation", true, "value", true,
			"modifiedDate", true, "logicalOperator", true, "operator", true,
			"cdrDetailsSid", true, "cdrModelSid", true, "comparison", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		CdrDetails existingCdrDetails = _persistence.fetchByPrimaryKey(newCdrDetails.getPrimaryKey());

		Assert.assertEquals(existingCdrDetails, newCdrDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrDetails missingCdrDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCdrDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CdrDetails newCdrDetails1 = addCdrDetails();
		CdrDetails newCdrDetails2 = addCdrDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrDetails1.getPrimaryKey());
		primaryKeys.add(newCdrDetails2.getPrimaryKey());

		Map<Serializable, CdrDetails> cdrDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cdrDetailses.size());
		Assert.assertEquals(newCdrDetails1,
			cdrDetailses.get(newCdrDetails1.getPrimaryKey()));
		Assert.assertEquals(newCdrDetails2,
			cdrDetailses.get(newCdrDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CdrDetails> cdrDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cdrDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CdrDetails> cdrDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cdrDetailses.size());
		Assert.assertEquals(newCdrDetails,
			cdrDetailses.get(newCdrDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CdrDetails> cdrDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cdrDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCdrDetails.getPrimaryKey());

		Map<Serializable, CdrDetails> cdrDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cdrDetailses.size());
		Assert.assertEquals(newCdrDetails,
			cdrDetailses.get(newCdrDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CdrDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CdrDetails>() {
				@Override
				public void performAction(CdrDetails cdrDetails) {
					Assert.assertNotNull(cdrDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cdrDetailsSid",
				newCdrDetails.getCdrDetailsSid()));

		List<CdrDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CdrDetails existingCdrDetails = result.get(0);

		Assert.assertEquals(existingCdrDetails, newCdrDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cdrDetailsSid",
				RandomTestUtil.nextInt()));

		List<CdrDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CdrDetails newCdrDetails = addCdrDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cdrDetailsSid"));

		Object newCdrDetailsSid = newCdrDetails.getCdrDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cdrDetailsSid",
				new Object[] { newCdrDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCdrDetailsSid = result.get(0);

		Assert.assertEquals(existingCdrDetailsSid, newCdrDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CdrDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cdrDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cdrDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CdrDetails addCdrDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CdrDetails cdrDetails = _persistence.create(pk);

		cdrDetails.setCreatedBy(RandomTestUtil.nextInt());

		cdrDetails.setModifiedBy(RandomTestUtil.nextInt());

		cdrDetails.setCreatedDate(RandomTestUtil.nextDate());

		cdrDetails.setLineType(RandomTestUtil.randomString());

		cdrDetails.setKeyword(RandomTestUtil.randomString());

		cdrDetails.setItemGroupMsAssociation(RandomTestUtil.randomString());

		cdrDetails.setValue(RandomTestUtil.nextDouble());

		cdrDetails.setModifiedDate(RandomTestUtil.nextDate());

		cdrDetails.setLogicalOperator(RandomTestUtil.randomString());

		cdrDetails.setOperator(RandomTestUtil.randomString());

		cdrDetails.setCdrModelSid(RandomTestUtil.nextInt());

		cdrDetails.setComparison(RandomTestUtil.randomString());

		_cdrDetailses.add(_persistence.update(cdrDetails));

		return cdrDetails;
	}

	private List<CdrDetails> _cdrDetailses = new ArrayList<CdrDetails>();
	private CdrDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}