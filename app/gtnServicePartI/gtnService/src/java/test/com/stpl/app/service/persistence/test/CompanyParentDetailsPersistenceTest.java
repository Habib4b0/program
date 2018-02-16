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

import com.stpl.app.exception.NoSuchCompanyParentDetailsException;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyParentDetailsPersistence;
import com.stpl.app.service.persistence.CompanyParentDetailsUtil;

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
public class CompanyParentDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyParentDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyParentDetails> iterator = _companyParentDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyParentDetails companyParentDetails = _persistence.create(pk);

		Assert.assertNotNull(companyParentDetails);

		Assert.assertEquals(companyParentDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		_persistence.remove(newCompanyParentDetails);

		CompanyParentDetails existingCompanyParentDetails = _persistence.fetchByPrimaryKey(newCompanyParentDetails.getPrimaryKey());

		Assert.assertNull(existingCompanyParentDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyParentDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyParentDetails newCompanyParentDetails = _persistence.create(pk);

		newCompanyParentDetails.setLastUpdatedDate(RandomTestUtil.nextDate());

		newCompanyParentDetails.setParentEndDate(RandomTestUtil.nextDate());

		newCompanyParentDetails.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyParentDetails.setParentCompanyMasterSid(RandomTestUtil.nextInt());

		newCompanyParentDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCompanyParentDetails.setPriorParentStartDate(RandomTestUtil.nextDate());

		newCompanyParentDetails.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyParentDetails.setSource(RandomTestUtil.randomString());

		newCompanyParentDetails.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyParentDetails.setPriorParentCmpyMasterSid(RandomTestUtil.randomString());

		newCompanyParentDetails.setBatchId(RandomTestUtil.randomString());

		newCompanyParentDetails.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyParentDetails.setInboundStatus(RandomTestUtil.randomString());

		newCompanyParentDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newCompanyParentDetails.setParentStartDate(RandomTestUtil.nextDate());

		_companyParentDetailses.add(_persistence.update(newCompanyParentDetails));

		CompanyParentDetails existingCompanyParentDetails = _persistence.findByPrimaryKey(newCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getLastUpdatedDate()),
			Time.getShortTimestamp(newCompanyParentDetails.getLastUpdatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getParentEndDate()),
			Time.getShortTimestamp(newCompanyParentDetails.getParentEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getModifiedDate()),
			Time.getShortTimestamp(newCompanyParentDetails.getModifiedDate()));
		Assert.assertEquals(existingCompanyParentDetails.getParentCompanyMasterSid(),
			newCompanyParentDetails.getParentCompanyMasterSid());
		Assert.assertEquals(existingCompanyParentDetails.getRecordLockStatus(),
			newCompanyParentDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getPriorParentStartDate()),
			Time.getShortTimestamp(
				newCompanyParentDetails.getPriorParentStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getCreatedDate()),
			Time.getShortTimestamp(newCompanyParentDetails.getCreatedDate()));
		Assert.assertEquals(existingCompanyParentDetails.getSource(),
			newCompanyParentDetails.getSource());
		Assert.assertEquals(existingCompanyParentDetails.getCreatedBy(),
			newCompanyParentDetails.getCreatedBy());
		Assert.assertEquals(existingCompanyParentDetails.getCompanyParentDetailsSid(),
			newCompanyParentDetails.getCompanyParentDetailsSid());
		Assert.assertEquals(existingCompanyParentDetails.getPriorParentCmpyMasterSid(),
			newCompanyParentDetails.getPriorParentCmpyMasterSid());
		Assert.assertEquals(existingCompanyParentDetails.getBatchId(),
			newCompanyParentDetails.getBatchId());
		Assert.assertEquals(existingCompanyParentDetails.getModifiedBy(),
			newCompanyParentDetails.getModifiedBy());
		Assert.assertEquals(existingCompanyParentDetails.getInboundStatus(),
			newCompanyParentDetails.getInboundStatus());
		Assert.assertEquals(existingCompanyParentDetails.getCompanyMasterSid(),
			newCompanyParentDetails.getCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyParentDetails.getParentStartDate()),
			Time.getShortTimestamp(newCompanyParentDetails.getParentStartDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		CompanyParentDetails existingCompanyParentDetails = _persistence.findByPrimaryKey(newCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(existingCompanyParentDetails,
			newCompanyParentDetails);
	}

	@Test(expected = NoSuchCompanyParentDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyParentDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_PARENT_DETAILS",
			"lastUpdatedDate", true, "parentEndDate", true, "modifiedDate",
			true, "parentCompanyMasterSid", true, "recordLockStatus", true,
			"priorParentStartDate", true, "createdDate", true, "source", true,
			"createdBy", true, "companyParentDetailsSid", true,
			"priorParentCmpyMasterSid", true, "batchId", true, "modifiedBy",
			true, "inboundStatus", true, "companyMasterSid", true,
			"parentStartDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		CompanyParentDetails existingCompanyParentDetails = _persistence.fetchByPrimaryKey(newCompanyParentDetails.getPrimaryKey());

		Assert.assertEquals(existingCompanyParentDetails,
			newCompanyParentDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyParentDetails missingCompanyParentDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyParentDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyParentDetails newCompanyParentDetails1 = addCompanyParentDetails();
		CompanyParentDetails newCompanyParentDetails2 = addCompanyParentDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyParentDetails1.getPrimaryKey());
		primaryKeys.add(newCompanyParentDetails2.getPrimaryKey());

		Map<Serializable, CompanyParentDetails> companyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyParentDetailses.size());
		Assert.assertEquals(newCompanyParentDetails1,
			companyParentDetailses.get(newCompanyParentDetails1.getPrimaryKey()));
		Assert.assertEquals(newCompanyParentDetails2,
			companyParentDetailses.get(newCompanyParentDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyParentDetails> companyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyParentDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyParentDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyParentDetails> companyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyParentDetailses.size());
		Assert.assertEquals(newCompanyParentDetails,
			companyParentDetailses.get(newCompanyParentDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyParentDetails> companyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyParentDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyParentDetails.getPrimaryKey());

		Map<Serializable, CompanyParentDetails> companyParentDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyParentDetailses.size());
		Assert.assertEquals(newCompanyParentDetails,
			companyParentDetailses.get(newCompanyParentDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyParentDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyParentDetails>() {
				@Override
				public void performAction(
					CompanyParentDetails companyParentDetails) {
					Assert.assertNotNull(companyParentDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyParentDetailsSid",
				newCompanyParentDetails.getCompanyParentDetailsSid()));

		List<CompanyParentDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyParentDetails existingCompanyParentDetails = result.get(0);

		Assert.assertEquals(existingCompanyParentDetails,
			newCompanyParentDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyParentDetailsSid",
				RandomTestUtil.nextInt()));

		List<CompanyParentDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyParentDetails newCompanyParentDetails = addCompanyParentDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyParentDetailsSid"));

		Object newCompanyParentDetailsSid = newCompanyParentDetails.getCompanyParentDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyParentDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyParentDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyParentDetails addCompanyParentDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyParentDetails companyParentDetails = _persistence.create(pk);

		companyParentDetails.setLastUpdatedDate(RandomTestUtil.nextDate());

		companyParentDetails.setParentEndDate(RandomTestUtil.nextDate());

		companyParentDetails.setModifiedDate(RandomTestUtil.nextDate());

		companyParentDetails.setParentCompanyMasterSid(RandomTestUtil.nextInt());

		companyParentDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		companyParentDetails.setPriorParentStartDate(RandomTestUtil.nextDate());

		companyParentDetails.setCreatedDate(RandomTestUtil.nextDate());

		companyParentDetails.setSource(RandomTestUtil.randomString());

		companyParentDetails.setCreatedBy(RandomTestUtil.nextInt());

		companyParentDetails.setPriorParentCmpyMasterSid(RandomTestUtil.randomString());

		companyParentDetails.setBatchId(RandomTestUtil.randomString());

		companyParentDetails.setModifiedBy(RandomTestUtil.nextInt());

		companyParentDetails.setInboundStatus(RandomTestUtil.randomString());

		companyParentDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		companyParentDetails.setParentStartDate(RandomTestUtil.nextDate());

		_companyParentDetailses.add(_persistence.update(companyParentDetails));

		return companyParentDetails;
	}

	private List<CompanyParentDetails> _companyParentDetailses = new ArrayList<CompanyParentDetails>();
	private CompanyParentDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}