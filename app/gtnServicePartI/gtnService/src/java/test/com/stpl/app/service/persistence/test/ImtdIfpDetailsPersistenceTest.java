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

import com.stpl.app.exception.NoSuchImtdIfpDetailsException;
import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdIfpDetailsPersistence;
import com.stpl.app.service.persistence.ImtdIfpDetailsUtil;

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
public class ImtdIfpDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdIfpDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdIfpDetails> iterator = _imtdIfpDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdIfpDetails imtdIfpDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdIfpDetails);

		Assert.assertEquals(imtdIfpDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		_persistence.remove(newImtdIfpDetails);

		ImtdIfpDetails existingImtdIfpDetails = _persistence.fetchByPrimaryKey(newImtdIfpDetails.getPrimaryKey());

		Assert.assertNull(existingImtdIfpDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdIfpDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdIfpDetails newImtdIfpDetails = _persistence.create(pk);

		newImtdIfpDetails.setItemStatus(RandomTestUtil.nextInt());

		newImtdIfpDetails.setIfpDetailsEndDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdIfpDetails.setImtdCreateddate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setItemPackageSize(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsCreatedDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setTotalDollarCommitment(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsCreatedBy(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemId(RandomTestUtil.randomString());

		newImtdIfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setIfpDetailsModifiedBy(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsModifiedDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdIfpDetails.setUsersSid(RandomTestUtil.nextInt());

		newImtdIfpDetails.setItemDesc(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemStartDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setItemStrength(RandomTestUtil.randomString());

		newImtdIfpDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdIfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdIfpDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemNo(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsSid(RandomTestUtil.nextInt());

		newImtdIfpDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newImtdIfpDetails.setItemTherapeuticClass(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsStartDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setItemForm(RandomTestUtil.randomString());

		newImtdIfpDetails.setTotalVolumeCommitment(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemEndDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setCheckBox(RandomTestUtil.randomBoolean());

		newImtdIfpDetails.setIfpDetailsAttachedStatus(RandomTestUtil.nextInt());

		newImtdIfpDetails.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		newImtdIfpDetails.setIfpDetailsAttachedDate(RandomTestUtil.nextDate());

		newImtdIfpDetails.setSessionId(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemName(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemPrimaryUom(RandomTestUtil.randomString());

		newImtdIfpDetails.setOperation(RandomTestUtil.randomString());

		newImtdIfpDetails.setItemBrand(RandomTestUtil.randomString());

		newImtdIfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		_imtdIfpDetailses.add(_persistence.update(newImtdIfpDetails));

		ImtdIfpDetails existingImtdIfpDetails = _persistence.findByPrimaryKey(newImtdIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdIfpDetails.getItemStatus(),
			newImtdIfpDetails.getItemStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getIfpDetailsEndDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getIfpDetailsEndDate()));
		Assert.assertEquals(existingImtdIfpDetails.getItemMasterSid(),
			newImtdIfpDetails.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getImtdCreateddate()),
			Time.getShortTimestamp(newImtdIfpDetails.getImtdCreateddate()));
		Assert.assertEquals(existingImtdIfpDetails.getItemPackageSize(),
			newImtdIfpDetails.getItemPackageSize());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getIfpDetailsCreatedDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getIfpDetailsCreatedDate()));
		Assert.assertEquals(existingImtdIfpDetails.getTotalDollarCommitment(),
			newImtdIfpDetails.getTotalDollarCommitment());
		Assert.assertEquals(existingImtdIfpDetails.getIfpDetailsCreatedBy(),
			newImtdIfpDetails.getIfpDetailsCreatedBy());
		Assert.assertEquals(existingImtdIfpDetails.getItemId(),
			newImtdIfpDetails.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdIfpDetails.getIfpDetailsModifiedBy(),
			newImtdIfpDetails.getIfpDetailsModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getIfpDetailsModifiedDate()),
			Time.getShortTimestamp(
				newImtdIfpDetails.getIfpDetailsModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdIfpDetails.getCreatedBy(),
			newImtdIfpDetails.getCreatedBy());
		Assert.assertEquals(existingImtdIfpDetails.getUsersSid(),
			newImtdIfpDetails.getUsersSid());
		Assert.assertEquals(existingImtdIfpDetails.getItemDesc(),
			newImtdIfpDetails.getItemDesc());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getItemStartDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getItemStartDate()));
		Assert.assertEquals(existingImtdIfpDetails.getItemStrength(),
			newImtdIfpDetails.getItemStrength());
		Assert.assertEquals(existingImtdIfpDetails.getContractMasterSid(),
			newImtdIfpDetails.getContractMasterSid());
		Assert.assertEquals(existingImtdIfpDetails.getModifiedBy(),
			newImtdIfpDetails.getModifiedBy());
		Assert.assertEquals(existingImtdIfpDetails.getCommitmentPeriod(),
			newImtdIfpDetails.getCommitmentPeriod());
		Assert.assertEquals(existingImtdIfpDetails.getItemNo(),
			newImtdIfpDetails.getItemNo());
		Assert.assertEquals(existingImtdIfpDetails.getIfpDetailsSid(),
			newImtdIfpDetails.getIfpDetailsSid());
		Assert.assertEquals(existingImtdIfpDetails.getIfpModelSid(),
			newImtdIfpDetails.getIfpModelSid());
		Assert.assertEquals(existingImtdIfpDetails.getItemTherapeuticClass(),
			newImtdIfpDetails.getItemTherapeuticClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getIfpDetailsStartDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getIfpDetailsStartDate()));
		Assert.assertEquals(existingImtdIfpDetails.getItemForm(),
			newImtdIfpDetails.getItemForm());
		Assert.assertEquals(existingImtdIfpDetails.getTotalVolumeCommitment(),
			newImtdIfpDetails.getTotalVolumeCommitment());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getItemEndDate()),
			Time.getShortTimestamp(newImtdIfpDetails.getItemEndDate()));
		Assert.assertEquals(existingImtdIfpDetails.getCheckBox(),
			newImtdIfpDetails.getCheckBox());
		Assert.assertEquals(existingImtdIfpDetails.getIfpDetailsAttachedStatus(),
			newImtdIfpDetails.getIfpDetailsAttachedStatus());
		Assert.assertEquals(existingImtdIfpDetails.getTotalMarketshareCommitment(),
			newImtdIfpDetails.getTotalMarketshareCommitment());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdIfpDetails.getIfpDetailsAttachedDate()),
			Time.getShortTimestamp(
				newImtdIfpDetails.getIfpDetailsAttachedDate()));
		Assert.assertEquals(existingImtdIfpDetails.getImtdIfpDetailsSid(),
			newImtdIfpDetails.getImtdIfpDetailsSid());
		Assert.assertEquals(existingImtdIfpDetails.getSessionId(),
			newImtdIfpDetails.getSessionId());
		Assert.assertEquals(existingImtdIfpDetails.getItemName(),
			newImtdIfpDetails.getItemName());
		Assert.assertEquals(existingImtdIfpDetails.getItemPrimaryUom(),
			newImtdIfpDetails.getItemPrimaryUom());
		Assert.assertEquals(existingImtdIfpDetails.getOperation(),
			newImtdIfpDetails.getOperation());
		Assert.assertEquals(existingImtdIfpDetails.getItemBrand(),
			newImtdIfpDetails.getItemBrand());
		Assert.assertEquals(existingImtdIfpDetails.getCfpModelSid(),
			newImtdIfpDetails.getCfpModelSid());
	}

	@Test
	public void testCountByTempIfpSearch() throws Exception {
		_persistence.countByTempIfpSearch(RandomTestUtil.nextInt(),
			StringPool.BLANK, RandomTestUtil.nextDate());

		_persistence.countByTempIfpSearch(0, StringPool.NULL,
			RandomTestUtil.nextDate());

		_persistence.countByTempIfpSearch(0, (String)null,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		ImtdIfpDetails existingImtdIfpDetails = _persistence.findByPrimaryKey(newImtdIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdIfpDetails, newImtdIfpDetails);
	}

	@Test(expected = NoSuchImtdIfpDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdIfpDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_IFP_DETAILS",
			"itemStatus", true, "ifpDetailsEndDate", true, "itemMasterSid",
			true, "imtdCreateddate", true, "itemPackageSize", true,
			"ifpDetailsCreatedDate", true, "totalDollarCommitment", true,
			"ifpDetailsCreatedBy", true, "itemId", true, "modifiedDate", true,
			"ifpDetailsModifiedBy", true, "ifpDetailsModifiedDate", true,
			"createdDate", true, "createdBy", true, "usersSid", true,
			"itemDesc", true, "itemStartDate", true, "itemStrength", true,
			"contractMasterSid", true, "modifiedBy", true, "commitmentPeriod",
			true, "itemNo", true, "ifpDetailsSid", true, "ifpModelSid", true,
			"itemTherapeuticClass", true, "ifpDetailsStartDate", true,
			"itemForm", true, "totalVolumeCommitment", true, "itemEndDate",
			true, "checkBox", true, "ifpDetailsAttachedStatus", true,
			"totalMarketshareCommitment", true, "ifpDetailsAttachedDate", true,
			"imtdIfpDetailsSid", true, "sessionId", true, "itemName", true,
			"itemPrimaryUom", true, "operation", true, "itemBrand", true,
			"cfpModelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		ImtdIfpDetails existingImtdIfpDetails = _persistence.fetchByPrimaryKey(newImtdIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdIfpDetails, newImtdIfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdIfpDetails missingImtdIfpDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdIfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdIfpDetails newImtdIfpDetails1 = addImtdIfpDetails();
		ImtdIfpDetails newImtdIfpDetails2 = addImtdIfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdIfpDetails1.getPrimaryKey());
		primaryKeys.add(newImtdIfpDetails2.getPrimaryKey());

		Map<Serializable, ImtdIfpDetails> imtdIfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdIfpDetailses.size());
		Assert.assertEquals(newImtdIfpDetails1,
			imtdIfpDetailses.get(newImtdIfpDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdIfpDetails2,
			imtdIfpDetailses.get(newImtdIfpDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdIfpDetails> imtdIfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdIfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdIfpDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdIfpDetails> imtdIfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdIfpDetailses.size());
		Assert.assertEquals(newImtdIfpDetails,
			imtdIfpDetailses.get(newImtdIfpDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdIfpDetails> imtdIfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdIfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdIfpDetails.getPrimaryKey());

		Map<Serializable, ImtdIfpDetails> imtdIfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdIfpDetailses.size());
		Assert.assertEquals(newImtdIfpDetails,
			imtdIfpDetailses.get(newImtdIfpDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdIfpDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdIfpDetails>() {
				@Override
				public void performAction(ImtdIfpDetails imtdIfpDetails) {
					Assert.assertNotNull(imtdIfpDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdIfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdIfpDetailsSid",
				newImtdIfpDetails.getImtdIfpDetailsSid()));

		List<ImtdIfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdIfpDetails existingImtdIfpDetails = result.get(0);

		Assert.assertEquals(existingImtdIfpDetails, newImtdIfpDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdIfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdIfpDetailsSid",
				RandomTestUtil.nextInt()));

		List<ImtdIfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdIfpDetails newImtdIfpDetails = addImtdIfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdIfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdIfpDetailsSid"));

		Object newImtdIfpDetailsSid = newImtdIfpDetails.getImtdIfpDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdIfpDetailsSid",
				new Object[] { newImtdIfpDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdIfpDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdIfpDetailsSid, newImtdIfpDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdIfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdIfpDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdIfpDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdIfpDetails addImtdIfpDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdIfpDetails imtdIfpDetails = _persistence.create(pk);

		imtdIfpDetails.setItemStatus(RandomTestUtil.nextInt());

		imtdIfpDetails.setIfpDetailsEndDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		imtdIfpDetails.setImtdCreateddate(RandomTestUtil.nextDate());

		imtdIfpDetails.setItemPackageSize(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsCreatedDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setTotalDollarCommitment(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsCreatedBy(RandomTestUtil.randomString());

		imtdIfpDetails.setItemId(RandomTestUtil.randomString());

		imtdIfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setIfpDetailsModifiedBy(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsModifiedDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdIfpDetails.setUsersSid(RandomTestUtil.nextInt());

		imtdIfpDetails.setItemDesc(RandomTestUtil.randomString());

		imtdIfpDetails.setItemStartDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setItemStrength(RandomTestUtil.randomString());

		imtdIfpDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdIfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdIfpDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		imtdIfpDetails.setItemNo(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsSid(RandomTestUtil.nextInt());

		imtdIfpDetails.setIfpModelSid(RandomTestUtil.nextInt());

		imtdIfpDetails.setItemTherapeuticClass(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsStartDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setItemForm(RandomTestUtil.randomString());

		imtdIfpDetails.setTotalVolumeCommitment(RandomTestUtil.randomString());

		imtdIfpDetails.setItemEndDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setCheckBox(RandomTestUtil.randomBoolean());

		imtdIfpDetails.setIfpDetailsAttachedStatus(RandomTestUtil.nextInt());

		imtdIfpDetails.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		imtdIfpDetails.setIfpDetailsAttachedDate(RandomTestUtil.nextDate());

		imtdIfpDetails.setSessionId(RandomTestUtil.randomString());

		imtdIfpDetails.setItemName(RandomTestUtil.randomString());

		imtdIfpDetails.setItemPrimaryUom(RandomTestUtil.randomString());

		imtdIfpDetails.setOperation(RandomTestUtil.randomString());

		imtdIfpDetails.setItemBrand(RandomTestUtil.randomString());

		imtdIfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		_imtdIfpDetailses.add(_persistence.update(imtdIfpDetails));

		return imtdIfpDetails;
	}

	private List<ImtdIfpDetails> _imtdIfpDetailses = new ArrayList<ImtdIfpDetails>();
	private ImtdIfpDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}