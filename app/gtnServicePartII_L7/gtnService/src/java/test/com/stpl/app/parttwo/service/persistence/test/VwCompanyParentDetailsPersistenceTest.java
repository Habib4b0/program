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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyParentDetailsException;
import com.stpl.app.parttwo.model.VwCompanyParentDetails;
import com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsUtil;

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
public class VwCompanyParentDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwCompanyParentDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwCompanyParentDetails> iterator = _vwCompanyParentDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyParentDetails vwCompanyParentDetails = _persistence.create(pk);

		Assert.assertNotNull(vwCompanyParentDetails);

		Assert.assertEquals(vwCompanyParentDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		_persistence.remove(newVwCompanyParentDetails);

		VwCompanyParentDetails existingVwCompanyParentDetails = _persistence.fetchByPrimaryKey(newVwCompanyParentDetails.getPrimaryKey());

		Assert.assertNull(existingVwCompanyParentDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwCompanyParentDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyParentDetails newVwCompanyParentDetails = _persistence.create(pk);

		newVwCompanyParentDetails.setParentcompanyId(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setPriorParentcompanyId(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setCompanyIdString(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setLastUpdatedDate(RandomTestUtil.nextDate());

		newVwCompanyParentDetails.setParentEndDate(RandomTestUtil.nextDate());

		newVwCompanyParentDetails.setModifiedDate(RandomTestUtil.nextDate());

		newVwCompanyParentDetails.setPriorParentStartDate(RandomTestUtil.nextDate());

		newVwCompanyParentDetails.setSource(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setCreatedBy(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setCreatedDate(RandomTestUtil.nextDate());

		newVwCompanyParentDetails.setBatchId(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setModifiedBy(RandomTestUtil.randomString());

		newVwCompanyParentDetails.setParentStartDate(RandomTestUtil.nextDate());

		_vwCompanyParentDetailses.add(_persistence.update(
				newVwCompanyParentDetails));

		VwCompanyParentDetails existingVwCompanyParentDetails = _persistence.findByPrimaryKey(newVwCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyParentDetails.getParentcompanyId(),
			newVwCompanyParentDetails.getParentcompanyId());
		Assert.assertEquals(existingVwCompanyParentDetails.getPriorParentcompanyId(),
			newVwCompanyParentDetails.getPriorParentcompanyId());
		Assert.assertEquals(existingVwCompanyParentDetails.getCompanyIdString(),
			newVwCompanyParentDetails.getCompanyIdString());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getLastUpdatedDate()),
			Time.getShortTimestamp(
				newVwCompanyParentDetails.getLastUpdatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getParentEndDate()),
			Time.getShortTimestamp(newVwCompanyParentDetails.getParentEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getModifiedDate()),
			Time.getShortTimestamp(newVwCompanyParentDetails.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getPriorParentStartDate()),
			Time.getShortTimestamp(
				newVwCompanyParentDetails.getPriorParentStartDate()));
		Assert.assertEquals(existingVwCompanyParentDetails.getSource(),
			newVwCompanyParentDetails.getSource());
		Assert.assertEquals(existingVwCompanyParentDetails.getCreatedBy(),
			newVwCompanyParentDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getCreatedDate()),
			Time.getShortTimestamp(newVwCompanyParentDetails.getCreatedDate()));
		Assert.assertEquals(existingVwCompanyParentDetails.getCompanyParentDetailsSid(),
			newVwCompanyParentDetails.getCompanyParentDetailsSid());
		Assert.assertEquals(existingVwCompanyParentDetails.getBatchId(),
			newVwCompanyParentDetails.getBatchId());
		Assert.assertEquals(existingVwCompanyParentDetails.getAddChgDelIndicator(),
			newVwCompanyParentDetails.getAddChgDelIndicator());
		Assert.assertEquals(existingVwCompanyParentDetails.getModifiedBy(),
			newVwCompanyParentDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyParentDetails.getParentStartDate()),
			Time.getShortTimestamp(
				newVwCompanyParentDetails.getParentStartDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		VwCompanyParentDetails existingVwCompanyParentDetails = _persistence.findByPrimaryKey(newVwCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyParentDetails,
			newVwCompanyParentDetails);
	}

	@Test(expected = NoSuchVwCompanyParentDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwCompanyParentDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_COMPANY_PARENT_DETAILS",
			"parentcompanyId", true, "priorParentcompanyId", true,
			"companyIdString", true, "lastUpdatedDate", true, "parentEndDate",
			true, "modifiedDate", true, "priorParentStartDate", true, "source",
			true, "createdBy", true, "createdDate", true,
			"companyParentDetailsSid", true, "batchId", true,
			"addChgDelIndicator", true, "modifiedBy", true, "parentStartDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		VwCompanyParentDetails existingVwCompanyParentDetails = _persistence.fetchByPrimaryKey(newVwCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyParentDetails,
			newVwCompanyParentDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyParentDetails missingVwCompanyParentDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwCompanyParentDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails1 = addVwCompanyParentDetails();
		VwCompanyParentDetails newVwCompanyParentDetails2 = addVwCompanyParentDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyParentDetails1.getPrimaryKey());
		primaryKeys.add(newVwCompanyParentDetails2.getPrimaryKey());

		Map<Serializable, VwCompanyParentDetails> vwCompanyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwCompanyParentDetailses.size());
		Assert.assertEquals(newVwCompanyParentDetails1,
			vwCompanyParentDetailses.get(
				newVwCompanyParentDetails1.getPrimaryKey()));
		Assert.assertEquals(newVwCompanyParentDetails2,
			vwCompanyParentDetailses.get(
				newVwCompanyParentDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwCompanyParentDetails> vwCompanyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyParentDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyParentDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwCompanyParentDetails> vwCompanyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyParentDetailses.size());
		Assert.assertEquals(newVwCompanyParentDetails,
			vwCompanyParentDetailses.get(
				newVwCompanyParentDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwCompanyParentDetails> vwCompanyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyParentDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyParentDetails.getPrimaryKey());

		Map<Serializable, VwCompanyParentDetails> vwCompanyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyParentDetailses.size());
		Assert.assertEquals(newVwCompanyParentDetails,
			vwCompanyParentDetailses.get(
				newVwCompanyParentDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwCompanyParentDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwCompanyParentDetails>() {
				@Override
				public void performAction(
					VwCompanyParentDetails vwCompanyParentDetails) {
					Assert.assertNotNull(vwCompanyParentDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyParentDetailsSid",
				newVwCompanyParentDetails.getCompanyParentDetailsSid()));

		List<VwCompanyParentDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwCompanyParentDetails existingVwCompanyParentDetails = result.get(0);

		Assert.assertEquals(existingVwCompanyParentDetails,
			newVwCompanyParentDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyParentDetailsSid",
				RandomTestUtil.nextInt()));

		List<VwCompanyParentDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwCompanyParentDetails newVwCompanyParentDetails = addVwCompanyParentDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyParentDetailsSid"));

		Object newCompanyParentDetailsSid = newVwCompanyParentDetails.getCompanyParentDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyParentDetailsSid",
				new Object[] { newCompanyParentDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyParentDetailsSid = result.get(0);

		Assert.assertEquals(existingCompanyParentDetailsSid,
			newCompanyParentDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyParentDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyParentDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwCompanyParentDetails addVwCompanyParentDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyParentDetails vwCompanyParentDetails = _persistence.create(pk);

		vwCompanyParentDetails.setParentcompanyId(RandomTestUtil.randomString());

		vwCompanyParentDetails.setPriorParentcompanyId(RandomTestUtil.randomString());

		vwCompanyParentDetails.setCompanyIdString(RandomTestUtil.randomString());

		vwCompanyParentDetails.setLastUpdatedDate(RandomTestUtil.nextDate());

		vwCompanyParentDetails.setParentEndDate(RandomTestUtil.nextDate());

		vwCompanyParentDetails.setModifiedDate(RandomTestUtil.nextDate());

		vwCompanyParentDetails.setPriorParentStartDate(RandomTestUtil.nextDate());

		vwCompanyParentDetails.setSource(RandomTestUtil.randomString());

		vwCompanyParentDetails.setCreatedBy(RandomTestUtil.randomString());

		vwCompanyParentDetails.setCreatedDate(RandomTestUtil.nextDate());

		vwCompanyParentDetails.setBatchId(RandomTestUtil.randomString());

		vwCompanyParentDetails.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwCompanyParentDetails.setModifiedBy(RandomTestUtil.randomString());

		vwCompanyParentDetails.setParentStartDate(RandomTestUtil.nextDate());

		_vwCompanyParentDetailses.add(_persistence.update(
				vwCompanyParentDetails));

		return vwCompanyParentDetails;
	}

	private List<VwCompanyParentDetails> _vwCompanyParentDetailses = new ArrayList<VwCompanyParentDetails>();
	private VwCompanyParentDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}