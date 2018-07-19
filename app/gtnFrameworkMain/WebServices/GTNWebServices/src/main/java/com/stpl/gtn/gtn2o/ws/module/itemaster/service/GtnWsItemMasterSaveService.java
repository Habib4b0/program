/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Attachment;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Udcs;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.ItemPricingQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.itemmaster.GtnWsItemMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

/**
 *
 * @author Karthikeyan.Subraman
 */
@Service()
@Scope(value = "singleton")
public class GtnWsItemMasterSaveService {

	public GtnWsItemMasterSaveService() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsItemMasterSaveService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkAutomaticService automaticRelationService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public int saveItemMasterIndetifierQualifier(GtnUIFrameworkWebserviceRequest generalWSRequest)
			throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsItemQualifierBean itemQualifierBean = generalWSRequest.getGtnWsItemMasterRequest()
					.getGtnWsItemQualifierBean();
			ItemQualifier masterData;
			if (itemQualifierBean.getItemQualifierSid() == 0) {
				masterData = new ItemQualifier();
				masterData.setItemQualifierName(itemQualifierBean.getItemQualifierName());
				masterData.setItemQualifierValue(itemQualifierBean.getItemQualifierValue());
				masterData.setEffectiveDates(itemQualifierBean.getEffectiveDates());
				masterData.setSpecificEntityCode(itemQualifierBean.getSpecificEntityCode());
				masterData.setNotes(itemQualifierBean.getNotes());
				masterData.setInboundStatus('A');
				masterData.setRecordLockStatus(false);
				masterData.setSource("GTN");
				masterData.setCreatedBy(Integer.parseInt(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
				masterData.setCreatedDate(new Date());
				masterData.setModifiedBy(Integer.valueOf(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
				masterData.setModifiedDate(new Date());
				return (Integer) session.save(masterData);
			} else {
				masterData = session.load(ItemQualifier.class, itemQualifierBean.getItemQualifierSid());
				buildItemQualifierBeanMaster(masterData, itemQualifierBean, generalWSRequest);
				session.update(masterData);
				return masterData.getItemQualifierSid();
			}
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveItemMasterIndetifierQualifier ", e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	private void buildItemQualifierBeanMaster(ItemQualifier masterDataEdit, GtnWsItemQualifierBean itemQualifierBean,
			GtnUIFrameworkWebserviceRequest generalWSRequest) {

		masterDataEdit.setItemQualifierName(itemQualifierBean.getItemQualifierName());
		masterDataEdit.setItemQualifierValue(itemQualifierBean.getItemQualifierValue());
		masterDataEdit.setEffectiveDates(itemQualifierBean.getEffectiveDates());
		masterDataEdit.setSpecificEntityCode(itemQualifierBean.getSpecificEntityCode());
		masterDataEdit.setNotes(itemQualifierBean.getNotes());
		masterDataEdit.setInboundStatus('A');
		masterDataEdit.setRecordLockStatus(false);
		masterDataEdit.setSource("GTN");
		masterDataEdit.setCreatedBy(Integer.parseInt(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
		masterDataEdit.setCreatedDate(new Date());
		masterDataEdit.setModifiedBy(Integer.valueOf(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
		masterDataEdit.setModifiedDate(new Date());
	}

	public int saveItemMasterPricingQualifier(GtnUIFrameworkWebserviceRequest generalWSRequest)
			throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsItemPricingQualifierBean pricingQualifierBean = generalWSRequest.getGtnWsItemMasterRequest()
					.getGtnWsItemPricingQualifierBean();
			ItemPricingQualifier ipemPricingQualifier;

			if (pricingQualifierBean.getItemPricingQualifierSid() == 0) {
				ipemPricingQualifier = new ItemPricingQualifier();
				ipemPricingQualifier.setItemPricingQualifierName(pricingQualifierBean.getItemPricingQualifierName());
				ipemPricingQualifier.setPricingQualifier(pricingQualifierBean.getPricingQualifier());
				ipemPricingQualifier.setEffectiveDates(pricingQualifierBean.getEffectiveDates());
				ipemPricingQualifier.setSpecificEntityCode(pricingQualifierBean.getSpecificEntityCode());
				ipemPricingQualifier.setNotes(pricingQualifierBean.getNotes());
				ipemPricingQualifier.setInboundStatus('A');
				ipemPricingQualifier.setRecordLockStatus(false);
				ipemPricingQualifier.setSource("GTN");
				ipemPricingQualifier
						.setCreatedBy(Integer.parseInt(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
				ipemPricingQualifier.setCreatedDate(new Date());
				ipemPricingQualifier
						.setModifiedBy(Integer.valueOf(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
				ipemPricingQualifier.setModifiedDate(new Date());
				return (Integer) session.save(ipemPricingQualifier);
			} else {
				ipemPricingQualifier = session.load(ItemPricingQualifier.class,
						pricingQualifierBean.getItemPricingQualifierSid());
				buildPriceQualifierBeanMaster(ipemPricingQualifier, pricingQualifierBean, generalWSRequest);
				session.update(ipemPricingQualifier);
				return ipemPricingQualifier.getItemPricingQualifierSid();
			}

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveItemMasterPricingQualifier ", e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	private void buildPriceQualifierBeanMaster(ItemPricingQualifier ipemPricingQualifierEdit,
			GtnWsItemPricingQualifierBean pricingQualifierBean, GtnUIFrameworkWebserviceRequest generalWSRequest) {

		ipemPricingQualifierEdit.setItemPricingQualifierName(pricingQualifierBean.getItemPricingQualifierName());
		ipemPricingQualifierEdit.setPricingQualifier(pricingQualifierBean.getPricingQualifier());
		ipemPricingQualifierEdit.setEffectiveDates(pricingQualifierBean.getEffectiveDates());
		ipemPricingQualifierEdit.setSpecificEntityCode(pricingQualifierBean.getSpecificEntityCode());
		ipemPricingQualifierEdit.setNotes(pricingQualifierBean.getNotes());
		ipemPricingQualifierEdit.setInboundStatus('A');
		ipemPricingQualifierEdit.setRecordLockStatus(false);
		ipemPricingQualifierEdit.setSource("GTN");
		ipemPricingQualifierEdit
				.setCreatedBy(Integer.parseInt(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
		ipemPricingQualifierEdit.setCreatedDate(new Date());
		ipemPricingQualifierEdit
				.setModifiedBy(Integer.valueOf(generalWSRequest.getGtnWsItemMasterRequest().getUserId()));
		ipemPricingQualifierEdit.setModifiedDate(new Date());
	}

	public void saveNotesTabDetails(GtnWsItemMasterBean ruleInfoBean) throws GtnFrameworkGeneralException {
		deleteNotesTab(ruleInfoBean.getGtnWsItemMasterInfoBean().getItemMasterSid());
		deleteNotesTabAttachment(ruleInfoBean.getGtnWsItemMasterInfoBean().getItemMasterSid());
		notesTabInsert(ruleInfoBean);
		notesTabAttachment(ruleInfoBean);
	}

	private int deleteNotesTab(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='ITEM_MASTER'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}

	private int notesTabInsert(GtnWsItemMasterBean ifpBean) throws GtnFrameworkGeneralException {
		LOGGER.info("Enter Item Master notesTabInsert");
		List<NotesTabBean> notesTabRequestList = ifpBean.getNoteBeanList();
		if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
			StringBuilder cmNotesTabQuery = new StringBuilder(
					GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_INSERT + "VALUES ");
			int i = 0;
			for (NotesTabBean notesTabRequest : notesTabRequestList) {
				if (i == 0) {
					cmNotesTabQuery.append(" (").append(ifpBean.getGtnWsItemMasterInfoBean().getItemMasterSid())
							.append(",'").append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				} else {
					cmNotesTabQuery.append(",(").append(ifpBean.getGtnWsItemMasterInfoBean().getItemMasterSid())
							.append(",'").append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				}
				i++;
			}
			LOGGER.info("Exit Item Master   notesTabInsert");
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(cmNotesTabQuery.toString());
		}
		return 0;
	}

	public Integer saveItemMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsItemMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();
			GtnWsItemMasterInfoBean infoBean = bean.getGtnWsItemMasterInfoBean();
			ItemMaster masterData = new ItemMaster();
			getItemMaster(masterData, bean, session, gtnWsRequest.getGtnWsItemMasterRequest().getUserId());
			Integer itemSystemId = (Integer) session.save(masterData);

			infoBean.setItemMasterSid(itemSystemId);

			saveOrUpdateUdcs(infoBean, session);
			saveOrUpdateItemIdentifier(gtnWsRequest.getGtnWsItemMasterRequest(), session, itemSystemId);

			bean.setGtnWsItemMasterInfoBean(infoBean);
			GtnWsItemMasterResponse imReponse = new GtnWsItemMasterResponse();
			imReponse.setGtnWsItemMasterBean(bean);
			response.setGtnWsItemMasterResponse(imReponse);
			tx.commit();
			saveOrUpdateItemPricing(gtnWsRequest, itemSystemId);
			return itemSystemId;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveItemMaster ", e);
		} finally {
			session.close();
		}
	}

	private void getItemMaster(ItemMaster itemMasterData, GtnWsItemMasterBean bean, Session session, String userId) {

		GtnWsItemMasterInfoBean infoBean = bean.getGtnWsItemMasterInfoBean();
		itemMasterData.setItemId(infoBean.getItemId());
		itemMasterData.setItemNo(infoBean.getItemNo());
		itemMasterData.setItemName(infoBean.getItemName());
		itemMasterData.setItemDesc(infoBean.getItemDesc());
		itemMasterData.setHelperTableByItemStatus(getHelperTable(infoBean.getItemStatus(), session));
		itemMasterData.setItemStartDate(infoBean.getItemStartDate());
		itemMasterData.setItemEndDate(infoBean.getItemEndDate());
		itemMasterData.setHelperTableByItemType(getHelperTable(infoBean.getItemType(), session));
		itemMasterData.setHelperTableByTherapeuticClass(getHelperTable(infoBean.getTherapeuticClass(), session));
		itemMasterData.setBrandMaster(session.load(BrandMaster.class, infoBean.getBrandMasterSid()));
		itemMasterData.setHelperTableByItemClass(getHelperTable(infoBean.getItemClass(), session));
		itemMasterData.setHelperTableByForm(getHelperTable(infoBean.getForm(), session));
		itemMasterData.setHelperTableByStrength(getHelperTable(infoBean.getStrength(), session));
		itemMasterData.setFirstSaleDate(infoBean.getFirstSaleDate());
		itemMasterData.setNdc9(infoBean.getNdc9());
		itemMasterData.setNdc8(infoBean.getNdc8());
		itemMasterData.setHelperTableByPrimaryUom(getHelperTable(infoBean.getPrimaryUom(), session));
		itemMasterData.setHelperTableBySecondaryUom(getHelperTable(infoBean.getSecondaryUom(), session));
		itemMasterData.setLabelerCode(infoBean.getLabelerCode());
		itemMasterData.setItemCode(infoBean.getItemCode());
		itemMasterData.setHelperTableByPackageSize(getHelperTable(infoBean.getPackageSize(), session));
		itemMasterData.setPackageSizeCode(infoBean.getPackageSizeCode());
		itemMasterData.setHelperTableByItemTypeIndication(getHelperTable(infoBean.getItemTypeIndication(), session));
		itemMasterData.setHelperTableByItemCategory(getHelperTable(infoBean.getItemCategory(), session));
		itemMasterData.setUpps(infoBean.getUpps() == null ? null : new BigDecimal(infoBean.getUpps()));
		itemMasterData.setManufacturerId(
				infoBean.getManufacturerId() == null ? null : String.valueOf(infoBean.getManufacturerId()));

		itemMasterData.setCompanyMaster(session.load(CompanyMaster.class, infoBean.getCompanyMasterSid()));

		// Tab 2
		itemMasterData.setDosesPerUnit(infoBean.getDosesPerUnit());
		itemMasterData.setShelfLife(infoBean.getShelfLife());
		itemMasterData.setHelperTableByShelfLifeType(getHelperTable(infoBean.getShelfLifeType(), session));
		itemMasterData.setLastLotExpirationDate(infoBean.getLastLotExpirationDate());
		itemMasterData.setAuthorizedGeneric(infoBean.getAuthorizedGeneric());
		itemMasterData.setPediatricExclusiveIndicator(infoBean.getPediatricExclusiveIndicator());
		itemMasterData.setClottingFactorIndicator(infoBean.getClottingFactorIndicator());
		itemMasterData.setDualPricingIndicator(infoBean.getDualPricingIndicator());
		itemMasterData.setAuthorizedGenericStartDate(infoBean.getAuthorizedGenericStartDate());
		itemMasterData.setPediatricExclusiveStartDate(infoBean.getPediatricExclusiveStartDate());
		itemMasterData.setClottingFactorStartDate(infoBean.getClottingFactorStartDate());
		itemMasterData.setDiscontinuationDate(infoBean.getDiscontinuationDate());
		itemMasterData.setAuthorizedGenericEndDate(infoBean.getAuthorizedGenericEndDate());
		itemMasterData.setPediatricExclusiveEndDate(infoBean.getPediatricExclusiveEndDate());
		itemMasterData.setClottingFactorEndDate(infoBean.getClottingFactorEndDate());
		itemMasterData.setDivestitureDate(infoBean.getDivestitureDate());
		itemMasterData.setNewFormulationIndicator(infoBean.getNewFormulationIndicator());
		itemMasterData.setBaselineAmp(infoBean.getBaselineAmp() == null ? null : infoBean.getBaselineAmp());
		itemMasterData.setAcquisitionDate(infoBean.getAcquisitionDate());
		itemMasterData.setNonFederalExpirationDate(infoBean.getNonFederalExpirationDate());
		itemMasterData.setBaseCpi(infoBean.getBaseCpi() == null ? null : infoBean.getBaseCpi());
		itemMasterData
				.setAcquiredAmp(infoBean.getAcquiredAmp() == null ? null : new BigDecimal(infoBean.getAcquiredAmp()));
		itemMasterData.setMarketTerminationDate(infoBean.getMarketTerminationDate());
		itemMasterData.setNewFormulationStartDate(infoBean.getNewFormulationStartDate());
		itemMasterData.setBaseCpiPeriod(infoBean.getBaseCpiPeriod());
		itemMasterData.setAcquiredBamp(
				infoBean.getAcquiredBamp() == null ? null : new BigDecimal(infoBean.getAcquiredBamp()));
		itemMasterData.setNewFormulationEndDate(infoBean.getNewFormulationEndDate());
		itemMasterData.setDra(infoBean.getDra() == null ? null : new BigDecimal(infoBean.getDra()));
		itemMasterData.setObraBamp(infoBean.getObraBamp() == null ? null : new BigDecimal(infoBean.getObraBamp()));
		itemMasterData.setNewFormulation(infoBean.getNewFormulation());
		itemMasterData.setInternalNotes(infoBean.getInternalNotes());
		itemMasterData.setInboundStatus('A');
		itemMasterData.setRecordLockStatus(false);
		itemMasterData.setSource("GTN");
		itemMasterData.setCreatedBy(Integer.parseInt(userId));
		itemMasterData.setCreatedDate(new Date());
		itemMasterData.setModifiedBy(Integer.parseInt(userId));
		itemMasterData.setModifiedDate(new Date());

	}

	@SuppressWarnings("unchecked")
	private List<Integer> getUdcsList(GtnWsItemMasterInfoBean infoBean) throws GtnFrameworkGeneralException {
		Criterion itemMasterSIDCriterion = Restrictions.eq("masterSid", infoBean.getItemMasterSid());
		Criterion type = Restrictions.eq("masterType", "ITEM_MASTER");
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.distinct(Projections.property("udcsSid")));
		return (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(Udcs.class,
				Arrays.asList(new Criterion[] { itemMasterSIDCriterion, type }), proj);

	}

	private void saveOrUpdateUdcs(GtnWsItemMasterInfoBean infoBean, Session session)
			throws GtnFrameworkGeneralException {
		// Save UDCS
		if ((infoBean.getUdc1() != null) || (infoBean.getUdc2() != null) || (infoBean.getUdc3() != null)
				|| (infoBean.getUdc4() != null) || (infoBean.getUdc5() != null) || (infoBean.getUdc6() != null)) {

			List<Integer> results = getUdcsList(infoBean);

			Udcs udcs = new Udcs();
			if (results != null && !results.isEmpty() && results.get(0) > 0) {
				udcs = session.get(Udcs.class, results.get(0));
			}
			udcs.setMasterSid(infoBean.getItemMasterSid());
			udcs.setMasterType("ITEM_MASTER");
			udcs.setHelperTableByUdc1(getHelperTable(infoBean.getUdc1(), session));
			udcs.setHelperTableByUdc2(getHelperTable(infoBean.getUdc2(), session));
			udcs.setHelperTableByUdc3(getHelperTable(infoBean.getUdc3(), session));
			udcs.setHelperTableByUdc4(getHelperTable(infoBean.getUdc4(), session));
			udcs.setHelperTableByUdc5(getHelperTable(infoBean.getUdc5(), session));
			udcs.setHelperTableByUdc6(getHelperTable(infoBean.getUdc6(), session));
			session.saveOrUpdate(udcs);
		}
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? Integer.valueOf(0) : systemId);
	}

	public void updateItemMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsItemMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();
			GtnWsItemMasterInfoBean infoBean = bean.getGtnWsItemMasterInfoBean();
			ItemMaster itemMasterEditData = session.get(ItemMaster.class, infoBean.getItemMasterSid());
			itemMasterEditData.setItemId(infoBean.getItemId());
			itemMasterEditData.setItemNo(infoBean.getItemNo());
			itemMasterEditData.setItemName(infoBean.getItemName());
			itemMasterEditData.setItemDesc(infoBean.getItemDesc());
			itemMasterEditData.setHelperTableByItemStatus(getHelperTable(infoBean.getItemStatus(), session));
			itemMasterEditData.setItemStartDate(infoBean.getItemStartDate());
			itemMasterEditData.setItemEndDate(infoBean.getItemEndDate());
			itemMasterEditData.setHelperTableByItemType(getHelperTable(infoBean.getItemType(), session));
			itemMasterEditData
					.setHelperTableByTherapeuticClass(getHelperTable(infoBean.getTherapeuticClass(), session));
			itemMasterEditData.setBrandMaster(session.load(BrandMaster.class, infoBean.getBrandMasterSid()));
			itemMasterEditData.setHelperTableByItemClass(getHelperTable(infoBean.getItemClass(), session));
			itemMasterEditData.setHelperTableByForm(getHelperTable(infoBean.getForm(), session));
			itemMasterEditData.setHelperTableByStrength(getHelperTable(infoBean.getStrength(), session));
			itemMasterEditData.setFirstSaleDate(infoBean.getFirstSaleDate());
			itemMasterEditData.setNdc9(infoBean.getNdc9());
			itemMasterEditData.setNdc8(infoBean.getNdc8());
			itemMasterEditData.setHelperTableByPrimaryUom(getHelperTable(infoBean.getPrimaryUom(), session));
			itemMasterEditData.setHelperTableBySecondaryUom(getHelperTable(infoBean.getSecondaryUom(), session));
			itemMasterEditData.setLabelerCode(infoBean.getLabelerCode());
			itemMasterEditData.setItemCode(infoBean.getItemCode());
			itemMasterEditData.setHelperTableByPackageSize(getHelperTable(infoBean.getPackageSize(), session));
			itemMasterEditData.setPackageSizeCode(infoBean.getPackageSizeCode());
			itemMasterEditData
					.setHelperTableByItemTypeIndication(getHelperTable(infoBean.getItemTypeIndication(), session));
			itemMasterEditData.setHelperTableByItemCategory(getHelperTable(infoBean.getItemCategory(), session));
			itemMasterEditData.setUpps(getBigDecimalValue(infoBean.getUpps()));
			itemMasterEditData.setPackageSizeIntroDate(infoBean.getPackageSizeIntroDate());
			itemMasterEditData.setManufacturerId(
					infoBean.getManufacturerId() == null ? null : String.valueOf(infoBean.getManufacturerId()));

			itemMasterEditData.setCompanyMaster(session.load(CompanyMaster.class, infoBean.getCompanyMasterSid()));

			// Tab 2
			itemMasterEditData.setDosesPerUnit(infoBean.getDosesPerUnit());
			itemMasterEditData.setShelfLife(infoBean.getShelfLife());
			itemMasterEditData.setHelperTableByShelfLifeType(getHelperTable(infoBean.getShelfLifeType(), session));
			itemMasterEditData.setLastLotExpirationDate(infoBean.getLastLotExpirationDate());
			itemMasterEditData.setAuthorizedGeneric(infoBean.getAuthorizedGeneric());
			itemMasterEditData.setPediatricExclusiveIndicator(infoBean.getPediatricExclusiveIndicator());
			itemMasterEditData.setClottingFactorIndicator(infoBean.getClottingFactorIndicator());
			itemMasterEditData.setDualPricingIndicator(infoBean.getDualPricingIndicator());
			itemMasterEditData.setAuthorizedGenericStartDate(infoBean.getAuthorizedGenericStartDate());
			itemMasterEditData.setPediatricExclusiveStartDate(infoBean.getPediatricExclusiveStartDate());
			itemMasterEditData.setClottingFactorStartDate(infoBean.getClottingFactorStartDate());
			itemMasterEditData.setDiscontinuationDate(infoBean.getDiscontinuationDate());
			itemMasterEditData.setAuthorizedGenericEndDate(infoBean.getAuthorizedGenericEndDate());
			itemMasterEditData.setPediatricExclusiveEndDate(infoBean.getPediatricExclusiveEndDate());
			itemMasterEditData.setClottingFactorEndDate(infoBean.getClottingFactorEndDate());
			itemMasterEditData.setDivestitureDate(infoBean.getDivestitureDate());
			itemMasterEditData.setNewFormulationIndicator(infoBean.getNewFormulationIndicator());
			itemMasterEditData.setBaselineAmp(infoBean.getBaselineAmp());
			itemMasterEditData.setAcquisitionDate(infoBean.getAcquisitionDate());
			itemMasterEditData.setNonFederalExpirationDate(infoBean.getNonFederalExpirationDate());
			itemMasterEditData.setBaseCpi(infoBean.getBaseCpi());
			itemMasterEditData.setAcquiredAmp(getBigDecimalValue(infoBean.getAcquiredAmp()));
			itemMasterEditData.setMarketTerminationDate(infoBean.getMarketTerminationDate());
			itemMasterEditData.setNewFormulationStartDate(infoBean.getNewFormulationStartDate());
			itemMasterEditData.setBaseCpiPeriod(infoBean.getBaseCpiPeriod());
			itemMasterEditData.setAcquiredBamp(getBigDecimalValue(infoBean.getAcquiredBamp()));
			itemMasterEditData.setDra(getBigDecimalValue(infoBean.getDra()));
			itemMasterEditData.setObraBamp(getBigDecimalValue(infoBean.getObraBamp()));
			itemMasterEditData.setNewFormulationEndDate(infoBean.getNewFormulationEndDate());
			itemMasterEditData.setNewFormulation(infoBean.getNewFormulation());
			itemMasterEditData.setInternalNotes(infoBean.getInternalNotes());
			itemMasterEditData.setModifiedDate(new Date());
			session.saveOrUpdate(itemMasterEditData);
			saveOrUpdateUdcs(infoBean, session);
			saveOrUpdateItemIdentifier(gtnWsRequest.getGtnWsItemMasterRequest(), session, infoBean.getItemMasterSid());
			saveOrUpdateItemPricing(gtnWsRequest, infoBean.getItemMasterSid());

			bean.setGtnWsItemMasterInfoBean(infoBean);
			GtnWsItemMasterResponse imReponse = new GtnWsItemMasterResponse();
			imReponse.setGtnWsItemMasterBean(bean);
			response.setGtnWsItemMasterResponse(imReponse);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateItemMaster ", e);
		} finally {
			session.close();
		}
	}

	BigDecimal getBigDecimalValue(Double value) {
		return value == null ? null : new BigDecimal(value);
	}

	BigDecimal getBigDecimalValue(Integer value) {
		return value == null ? new BigDecimal(0) : new BigDecimal(value);
	}

	private void saveOrUpdateItemIdentifier(GtnWsItemMasterRequest imRquest, Session session, int itemSystemId)
			throws GtnFrameworkGeneralException {
		List<GtnWsItemIdentifierBean> identifierBeanList = imRquest.getGtnWsItemMasterBean()
				.getGtnWsItemIdentifierBeanList();

		LOGGER.info("identifierBeanList---save zise-----" + identifierBeanList.size());
		for (GtnWsItemIdentifierBean idenBean : identifierBeanList) {
			ItemIdentifier identifier = null;
			LOGGER.info("Identifier SId:" + idenBean.getItemIdentifierSid());
			if (idenBean.getItemIdentifierSid() == 0) {
				identifier = new ItemIdentifier();
				identifier.setCreatedBy(Integer.parseInt(imRquest.getUserId()));
				identifier.setCreatedDate(new Date());
				identifier.setInboundStatus('A');
				identifier.setSource("GTN");
				setIdentifierValues(imRquest, identifier, idenBean, session, itemSystemId);
				Integer itemIdentifierSid = (Integer) session.save(identifier);
				idenBean.setItemIdentifierSid(itemIdentifierSid);
				idenBean.setItemQualifierName(identifier.getItemQualifier().getItemQualifierName());
			} else {
				updateInboundStatus(identifierBeanList,
						imRquest.getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().getItemMasterSid());
				identifier = session.get(ItemIdentifier.class, idenBean.getItemIdentifierSid());
				identifier.setItemIdentifierSid(idenBean.getItemIdentifierSid());
				setIdentifierValues(imRquest, identifier, idenBean, session, itemSystemId);
				session.saveOrUpdate(identifier);
				idenBean.setItemQualifierName(identifier.getItemQualifier().getItemQualifierName());
			}

		}
	}

	private void updateInboundStatus(List<GtnWsItemIdentifierBean> identifierBeanList, int itemMasterSID)
			throws GtnFrameworkGeneralException {
		List<Object> identifierCriteriaIdList = new ArrayList<>();
		for (GtnWsItemIdentifierBean idenBean : identifierBeanList) {
			identifierCriteriaIdList.add(idenBean.getItemIdentifierSid());
		}
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery("changeStatusOfItemIdentiferValue"),
				new Object[] { itemMasterSID, identifierCriteriaIdList },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.IN_LIST });
	}

	private void setIdentifierValues(GtnWsItemMasterRequest imRquest, ItemIdentifier identifier,
			GtnWsItemIdentifierBean idenBean, Session session, int itemSystemId) {
		identifier.setItemMaster(session.load(ItemMaster.class, itemSystemId));
		ItemQualifier iq = session.load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());
		identifier.setItemQualifier(iq);
		identifier.setItemIdentifierValue(idenBean.getItemIdentifierValue());
		identifier.setHelperTable(getHelperTable(idenBean.getItemIdentifierStatus(), session));
		identifier.setStartDate(idenBean.getStartDate());
		identifier.setEndDate(idenBean.getEndDate());
		identifier.setEntityCode(idenBean.getEntityCode());
		identifier.setModifiedBy(Integer.parseInt(imRquest.getUserId()));
		identifier.setModifiedDate(new Date());
	}

	private String replaceTempTableName(GtnUIFrameworkWebserviceRequest gtnWsRequest, String query) {
		return query.replace("ITEM_PRICING_TEMP_TABLE",
				generateTempTableName("ITEM_PRICING", gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));

	}

	private String generateTempTableName(String tableName, String userId, String sessionId) {
		return tableName + "_" + userId + "_" + sessionId;
	}

	private void saveOrUpdateItemPricing(GtnUIFrameworkWebserviceRequest gtnWsRequest, int itemSystemId)
			throws GtnFrameworkGeneralException {
		if (itemSystemId > 0) {
			deleteOldPricingData(itemSystemId);
			String sqlQuery = gtnWsSqlService.getQuery("insertIntoMainTable");
			sqlQuery = replaceTempTableName(gtnWsRequest, sqlQuery);
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery, new Object[] { itemSystemId },
					new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		}

	}

	private void deleteOldPricingData(int itemSystemId) throws GtnFrameworkGeneralException {
		String sqlQuery = gtnWsSqlService.getQuery("deleteOldPricingData");
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery, new Object[] { itemSystemId },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });

	}

	public void insertInCPDetailsForItems(int itemMasterSid) throws GtnFrameworkGeneralException {
		Object[] saveData = { itemMasterSid };
		GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER };
		String insertQuery = gtnWsSqlService.getQuery("item-CPDetailsInsert");
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(insertQuery, saveData, saveDataHeader);
	}

	public void checkAndUpdateAllrelationShip() {
		try {
			automaticRelationService.checkAndUpdateAllRelationShip("");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	private void notesTabAttachment(GtnWsItemMasterBean itemMasterBean) throws GtnFrameworkGeneralException {
		LOGGER.info("Enter Cfp notesTabInsert");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Attachment attach = new Attachment();
			List<NotesTabBean> notesTabRequestList = itemMasterBean.getNoteBeanList();

			if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {

				for (NotesTabBean notesTabRequest : notesTabRequestList) {
					byte[] fileBytes = readBytesFromFile(notesTabRequest.getFilePath());
					attach.setAttachmentTableSid(itemMasterBean.getGtnWsItemMasterInfoBean().getItemMasterSid());
					attach.setFileName(notesTabRequest.getFileName());
					attach.setFileData(fileBytes);
					attach.setMasterTableName(notesTabRequest.getMasterTableName());
					attach.setCreatedBy(notesTabRequest.getCreatedBy());
					attach.setCreatedDate(new Date());
					session.saveOrUpdate(attach);
					tx.commit();
				}
			}
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in save attachQuery ", e);
		} finally {
			session.close();
		}
	}

	private static byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = GtnFileNameUtils.getFile(filePath);
		FileInputStream inputStreamIm = GtnFileNameUtils.getFileInputStreamFile(inputFile);
		byte[] fileBytes = new byte[(int) inputFile.length()];
		int i = inputStreamIm.read(fileBytes);
		if (i > 0)
			return fileBytes;
		return new byte[0];
	}

	private int deleteNotesTabAttachment(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_ATTACHMENT_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='ITEM_MASTER'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}

}
