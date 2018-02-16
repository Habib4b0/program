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

import com.stpl.app.exception.NoSuchCompanyGroupDetailsException;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyGroupDetailsPersistence;
import com.stpl.app.service.persistence.CompanyGroupDetailsUtil;

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
public class CompanyGroupDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyGroupDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyGroupDetails> iterator = _companyGroupDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroupDetails companyGroupDetails = _persistence.create(pk);

		Assert.assertNotNull(companyGroupDetails);

		Assert.assertEquals(companyGroupDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		_persistence.remove(newCompanyGroupDetails);

		CompanyGroupDetails existingCompanyGroupDetails = _persistence.fetchByPrimaryKey(newCompanyGroupDetails.getPrimaryKey());

		Assert.assertNull(existingCompanyGroupDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyGroupDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroupDetails newCompanyGroupDetails = _persistence.create(pk);

		newCompanyGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyGroupDetails.setCompanyParentDetailsSid(RandomTestUtil.randomString());

		newCompanyGroupDetails.setCompanyTradeclassSid(RandomTestUtil.nextInt());

		newCompanyGroupDetails.setCompanyGroupSid(RandomTestUtil.nextInt());

		newCompanyGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		newCompanyGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyGroupDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyGroupDetailses.add(_persistence.update(newCompanyGroupDetails));

		CompanyGroupDetails existingCompanyGroupDetails = _persistence.findByPrimaryKey(newCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyGroupDetails.getCreatedDate()),
			Time.getShortTimestamp(newCompanyGroupDetails.getCreatedDate()));
		Assert.assertEquals(existingCompanyGroupDetails.getCreatedBy(),
			newCompanyGroupDetails.getCreatedBy());
		Assert.assertEquals(existingCompanyGroupDetails.getCompanyParentDetailsSid(),
			newCompanyGroupDetails.getCompanyParentDetailsSid());
		Assert.assertEquals(existingCompanyGroupDetails.getCompanyTradeclassSid(),
			newCompanyGroupDetails.getCompanyTradeclassSid());
		Assert.assertEquals(existingCompanyGroupDetails.getCompanyGroupSid(),
			newCompanyGroupDetails.getCompanyGroupSid());
		Assert.assertEquals(existingCompanyGroupDetails.getVersionNo(),
			newCompanyGroupDetails.getVersionNo());
		Assert.assertEquals(existingCompanyGroupDetails.getCompanyGroupDetailsSid(),
			newCompanyGroupDetails.getCompanyGroupDetailsSid());
		Assert.assertEquals(existingCompanyGroupDetails.getModifiedBy(),
			newCompanyGroupDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyGroupDetails.getModifiedDate()),
			Time.getShortTimestamp(newCompanyGroupDetails.getModifiedDate()));
		Assert.assertEquals(existingCompanyGroupDetails.getCompanyMasterSid(),
			newCompanyGroupDetails.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		CompanyGroupDetails existingCompanyGroupDetails = _persistence.findByPrimaryKey(newCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingCompanyGroupDetails, newCompanyGroupDetails);
	}

	@Test(expected = NoSuchCompanyGroupDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyGroupDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_GROUP_DETAILS",
			"createdDate", true, "createdBy", true, "companyParentDetailsSid",
			true, "companyTradeclassSid", true, "companyGroupSid", true,
			"versionNo", true, "companyGroupDetailsSid", true, "modifiedBy",
			true, "modifiedDate", true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		CompanyGroupDetails existingCompanyGroupDetails = _persistence.fetchByPrimaryKey(newCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingCompanyGroupDetails, newCompanyGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroupDetails missingCompanyGroupDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyGroupDetails newCompanyGroupDetails1 = addCompanyGroupDetails();
		CompanyGroupDetails newCompanyGroupDetails2 = addCompanyGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroupDetails1.getPrimaryKey());
		primaryKeys.add(newCompanyGroupDetails2.getPrimaryKey());

		Map<Serializable, CompanyGroupDetails> companyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyGroupDetailses.size());
		Assert.assertEquals(newCompanyGroupDetails1,
			companyGroupDetailses.get(newCompanyGroupDetails1.getPrimaryKey()));
		Assert.assertEquals(newCompanyGroupDetails2,
			companyGroupDetailses.get(newCompanyGroupDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyGroupDetails> companyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroupDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyGroupDetails> companyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyGroupDetailses.size());
		Assert.assertEquals(newCompanyGroupDetails,
			companyGroupDetailses.get(newCompanyGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyGroupDetails> companyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroupDetails.getPrimaryKey());

		Map<Serializable, CompanyGroupDetails> companyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyGroupDetailses.size());
		Assert.assertEquals(newCompanyGroupDetails,
			companyGroupDetailses.get(newCompanyGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyGroupDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyGroupDetails>() {
				@Override
				public void performAction(
					CompanyGroupDetails companyGroupDetails) {
					Assert.assertNotNull(companyGroupDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupDetailsSid",
				newCompanyGroupDetails.getCompanyGroupDetailsSid()));

		List<CompanyGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyGroupDetails existingCompanyGroupDetails = result.get(0);

		Assert.assertEquals(existingCompanyGroupDetails, newCompanyGroupDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupDetailsSid",
				RandomTestUtil.nextInt()));

		List<CompanyGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyGroupDetails newCompanyGroupDetails = addCompanyGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyGroupDetailsSid"));

		Object newCompanyGroupDetailsSid = newCompanyGroupDetails.getCompanyGroupDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyGroupDetailsSid",
				new Object[] { newCompanyGroupDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyGroupDetailsSid = result.get(0);

		Assert.assertEquals(existingCompanyGroupDetailsSid,
			newCompanyGroupDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyGroupDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyGroupDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyGroupDetails addCompanyGroupDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroupDetails companyGroupDetails = _persistence.create(pk);

		companyGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		companyGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		companyGroupDetails.setCompanyParentDetailsSid(RandomTestUtil.randomString());

		companyGroupDetails.setCompanyTradeclassSid(RandomTestUtil.nextInt());

		companyGroupDetails.setCompanyGroupSid(RandomTestUtil.nextInt());

		companyGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		companyGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		companyGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		companyGroupDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_companyGroupDetailses.add(_persistence.update(companyGroupDetails));

		return companyGroupDetails;
	}

	private List<CompanyGroupDetails> _companyGroupDetailses = new ArrayList<CompanyGroupDetails>();
	private CompanyGroupDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}