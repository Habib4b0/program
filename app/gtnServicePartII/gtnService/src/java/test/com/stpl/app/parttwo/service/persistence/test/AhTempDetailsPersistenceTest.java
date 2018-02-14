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

import com.stpl.app.parttwo.exception.NoSuchAhTempDetailsException;
import com.stpl.app.parttwo.model.AhTempDetails;
import com.stpl.app.parttwo.service.AhTempDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AhTempDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AhTempDetailsUtil;

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
public class AhTempDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AhTempDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AhTempDetails> iterator = _ahTempDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AhTempDetails ahTempDetails = _persistence.create(pk);

		Assert.assertNotNull(ahTempDetails);

		Assert.assertEquals(ahTempDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		_persistence.remove(newAhTempDetails);

		AhTempDetails existingAhTempDetails = _persistence.fetchByPrimaryKey(newAhTempDetails.getPrimaryKey());

		Assert.assertNull(existingAhTempDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAhTempDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AhTempDetails newAhTempDetails = _persistence.create(pk);

		newAhTempDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newAhTempDetails.setContractHolder(RandomTestUtil.randomString());

		newAhTempDetails.setUserId(RandomTestUtil.randomString());

		newAhTempDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newAhTempDetails.setBusinessUnitNo(RandomTestUtil.randomString());

		newAhTempDetails.setCompanyName(RandomTestUtil.randomString());

		newAhTempDetails.setItemId(RandomTestUtil.randomString());

		newAhTempDetails.setBrandName(RandomTestUtil.randomString());

		newAhTempDetails.setComponentName(RandomTestUtil.randomString());

		newAhTempDetails.setCreatedDate(RandomTestUtil.nextDate());

		newAhTempDetails.setCreatedBy(RandomTestUtil.randomString());

		newAhTempDetails.setScreenName(RandomTestUtil.randomString());

		newAhTempDetails.setBusinessUnitName(RandomTestUtil.randomString());

		newAhTempDetails.setCompanyNo(RandomTestUtil.randomString());

		newAhTempDetails.setItemIdentifierType(RandomTestUtil.randomString());

		newAhTempDetails.setComponentNo(RandomTestUtil.randomString());

		newAhTempDetails.setSessionId(RandomTestUtil.randomString());

		newAhTempDetails.setItemName(RandomTestUtil.randomString());

		newAhTempDetails.setItemIdentifier(RandomTestUtil.randomString());

		newAhTempDetails.setCompanySid(RandomTestUtil.nextInt());

		newAhTempDetails.setItemNo(RandomTestUtil.randomString());

		newAhTempDetails.setComponentType(RandomTestUtil.randomString());

		newAhTempDetails.setTheraputicClass(RandomTestUtil.randomString());

		_ahTempDetailses.add(_persistence.update(newAhTempDetails));

		AhTempDetails existingAhTempDetails = _persistence.findByPrimaryKey(newAhTempDetails.getPrimaryKey());

		Assert.assertEquals(existingAhTempDetails.getCheckRecord(),
			newAhTempDetails.getCheckRecord());
		Assert.assertEquals(existingAhTempDetails.getContractHolder(),
			newAhTempDetails.getContractHolder());
		Assert.assertEquals(existingAhTempDetails.getUserId(),
			newAhTempDetails.getUserId());
		Assert.assertEquals(existingAhTempDetails.getItemMasterSid(),
			newAhTempDetails.getItemMasterSid());
		Assert.assertEquals(existingAhTempDetails.getBusinessUnitNo(),
			newAhTempDetails.getBusinessUnitNo());
		Assert.assertEquals(existingAhTempDetails.getCompanyName(),
			newAhTempDetails.getCompanyName());
		Assert.assertEquals(existingAhTempDetails.getItemId(),
			newAhTempDetails.getItemId());
		Assert.assertEquals(existingAhTempDetails.getBrandName(),
			newAhTempDetails.getBrandName());
		Assert.assertEquals(existingAhTempDetails.getComponentName(),
			newAhTempDetails.getComponentName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAhTempDetails.getCreatedDate()),
			Time.getShortTimestamp(newAhTempDetails.getCreatedDate()));
		Assert.assertEquals(existingAhTempDetails.getCreatedBy(),
			newAhTempDetails.getCreatedBy());
		Assert.assertEquals(existingAhTempDetails.getScreenName(),
			newAhTempDetails.getScreenName());
		Assert.assertEquals(existingAhTempDetails.getBusinessUnitName(),
			newAhTempDetails.getBusinessUnitName());
		Assert.assertEquals(existingAhTempDetails.getCompanyNo(),
			newAhTempDetails.getCompanyNo());
		Assert.assertEquals(existingAhTempDetails.getItemIdentifierType(),
			newAhTempDetails.getItemIdentifierType());
		Assert.assertEquals(existingAhTempDetails.getComponentNo(),
			newAhTempDetails.getComponentNo());
		Assert.assertEquals(existingAhTempDetails.getSessionId(),
			newAhTempDetails.getSessionId());
		Assert.assertEquals(existingAhTempDetails.getItemName(),
			newAhTempDetails.getItemName());
		Assert.assertEquals(existingAhTempDetails.getItemIdentifier(),
			newAhTempDetails.getItemIdentifier());
		Assert.assertEquals(existingAhTempDetails.getCompanySid(),
			newAhTempDetails.getCompanySid());
		Assert.assertEquals(existingAhTempDetails.getItemNo(),
			newAhTempDetails.getItemNo());
		Assert.assertEquals(existingAhTempDetails.getComponentType(),
			newAhTempDetails.getComponentType());
		Assert.assertEquals(existingAhTempDetails.getTheraputicClass(),
			newAhTempDetails.getTheraputicClass());
		Assert.assertEquals(existingAhTempDetails.getComponentMasterSid(),
			newAhTempDetails.getComponentMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		AhTempDetails existingAhTempDetails = _persistence.findByPrimaryKey(newAhTempDetails.getPrimaryKey());

		Assert.assertEquals(existingAhTempDetails, newAhTempDetails);
	}

	@Test(expected = NoSuchAhTempDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AhTempDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AH_TEMP_DETAILS",
			"checkRecord", true, "contractHolder", true, "userId", true,
			"itemMasterSid", true, "businessUnitNo", true, "companyName", true,
			"itemId", true, "brandName", true, "componentName", true,
			"createdDate", true, "createdBy", true, "screenName", true,
			"businessUnitName", true, "companyNo", true, "itemIdentifierType",
			true, "componentNo", true, "sessionId", true, "itemName", true,
			"itemIdentifier", true, "companySid", true, "itemNo", true,
			"componentType", true, "theraputicClass", true,
			"componentMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		AhTempDetails existingAhTempDetails = _persistence.fetchByPrimaryKey(newAhTempDetails.getPrimaryKey());

		Assert.assertEquals(existingAhTempDetails, newAhTempDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AhTempDetails missingAhTempDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAhTempDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AhTempDetails newAhTempDetails1 = addAhTempDetails();
		AhTempDetails newAhTempDetails2 = addAhTempDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAhTempDetails1.getPrimaryKey());
		primaryKeys.add(newAhTempDetails2.getPrimaryKey());

		Map<Serializable, AhTempDetails> ahTempDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ahTempDetailses.size());
		Assert.assertEquals(newAhTempDetails1,
			ahTempDetailses.get(newAhTempDetails1.getPrimaryKey()));
		Assert.assertEquals(newAhTempDetails2,
			ahTempDetailses.get(newAhTempDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AhTempDetails> ahTempDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ahTempDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAhTempDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AhTempDetails> ahTempDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ahTempDetailses.size());
		Assert.assertEquals(newAhTempDetails,
			ahTempDetailses.get(newAhTempDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AhTempDetails> ahTempDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ahTempDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAhTempDetails.getPrimaryKey());

		Map<Serializable, AhTempDetails> ahTempDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ahTempDetailses.size());
		Assert.assertEquals(newAhTempDetails,
			ahTempDetailses.get(newAhTempDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AhTempDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AhTempDetails>() {
				@Override
				public void performAction(AhTempDetails ahTempDetails) {
					Assert.assertNotNull(ahTempDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AhTempDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("componentMasterSid",
				newAhTempDetails.getComponentMasterSid()));

		List<AhTempDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AhTempDetails existingAhTempDetails = result.get(0);

		Assert.assertEquals(existingAhTempDetails, newAhTempDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AhTempDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("componentMasterSid",
				RandomTestUtil.nextInt()));

		List<AhTempDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AhTempDetails newAhTempDetails = addAhTempDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AhTempDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"componentMasterSid"));

		Object newComponentMasterSid = newAhTempDetails.getComponentMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("componentMasterSid",
				new Object[] { newComponentMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingComponentMasterSid = result.get(0);

		Assert.assertEquals(existingComponentMasterSid, newComponentMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AhTempDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"componentMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("componentMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AhTempDetails addAhTempDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AhTempDetails ahTempDetails = _persistence.create(pk);

		ahTempDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		ahTempDetails.setContractHolder(RandomTestUtil.randomString());

		ahTempDetails.setUserId(RandomTestUtil.randomString());

		ahTempDetails.setItemMasterSid(RandomTestUtil.nextInt());

		ahTempDetails.setBusinessUnitNo(RandomTestUtil.randomString());

		ahTempDetails.setCompanyName(RandomTestUtil.randomString());

		ahTempDetails.setItemId(RandomTestUtil.randomString());

		ahTempDetails.setBrandName(RandomTestUtil.randomString());

		ahTempDetails.setComponentName(RandomTestUtil.randomString());

		ahTempDetails.setCreatedDate(RandomTestUtil.nextDate());

		ahTempDetails.setCreatedBy(RandomTestUtil.randomString());

		ahTempDetails.setScreenName(RandomTestUtil.randomString());

		ahTempDetails.setBusinessUnitName(RandomTestUtil.randomString());

		ahTempDetails.setCompanyNo(RandomTestUtil.randomString());

		ahTempDetails.setItemIdentifierType(RandomTestUtil.randomString());

		ahTempDetails.setComponentNo(RandomTestUtil.randomString());

		ahTempDetails.setSessionId(RandomTestUtil.randomString());

		ahTempDetails.setItemName(RandomTestUtil.randomString());

		ahTempDetails.setItemIdentifier(RandomTestUtil.randomString());

		ahTempDetails.setCompanySid(RandomTestUtil.nextInt());

		ahTempDetails.setItemNo(RandomTestUtil.randomString());

		ahTempDetails.setComponentType(RandomTestUtil.randomString());

		ahTempDetails.setTheraputicClass(RandomTestUtil.randomString());

		_ahTempDetailses.add(_persistence.update(ahTempDetails));

		return ahTempDetails;
	}

	private List<AhTempDetails> _ahTempDetailses = new ArrayList<AhTempDetails>();
	private AhTempDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}