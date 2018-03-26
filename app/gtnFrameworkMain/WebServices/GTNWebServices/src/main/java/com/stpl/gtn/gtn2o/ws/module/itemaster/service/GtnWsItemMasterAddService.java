package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Udcs;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemPricing;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.ItemPricingQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GntWsItemPricingBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.itemaster.constants.GtnWsTableConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.itemmaster.GtnWsItemMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

@Service()
@Scope(value = "singleton")
public class GtnWsItemMasterAddService {
	private static final String ITEM_PRICING_TEMP_TABLE_REGEX = "\\bITEM_PRICING_TEMP_TABLE\\b";

	private static final String ITEM_PRICING_TABLE_NAME = "ITEM_PRICING";

	public GtnWsItemMasterAddService() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsItemMasterAddService.class);

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public void setSysSessionFactory(org.hibernate.SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;
	}

	public void fetchBrandMasterInfo(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsBrandMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsBrandMasterBean();
			BrandMaster brandMaster = session.get(BrandMaster.class, bean.getBrandMasterSid());
			if (brandMaster != null) {
				bean.setBrandId(brandMaster.getBrandId());
				bean.setBrandName(brandMaster.getBrandName());
				bean.setBrandDesc(brandMaster.getBrandDesc());
				bean.setDisplayBrand(brandMaster.getDisplayBrand());
			}
			GtnWsItemMasterResponse reponse = new GtnWsItemMasterResponse();
			reponse.setGtnWsBrandMasterBean(bean);
			gtnResponse.setGtnWsItemMasterResponse(reponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in fetchBrandMasterInfo ", e);
		} finally {
			session.close();
		}

	}

	public void fetchCompanyInfo(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Integer companyMasterSid = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
					.getGtnWsItemMasterInfoBean().getCompanyMasterSid();
			CompanyMaster brandMaster = session.get(CompanyMaster.class, companyMasterSid);
			GtnWsItemMasterResponse reponse = new GtnWsItemMasterResponse();
			if (brandMaster != null) {
				reponse.setCompanyName(brandMaster.getCompanyName());
			}
			gtnResponse.setGtnWsItemMasterResponse(reponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in fetchCompanyInfo ", e);
		} finally {
			session.close();
		}

	}

	public void fetchItemMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsItemMasterBean bean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean();

			GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
			ItemMaster itemMaster = session.get(ItemMaster.class, bean.getGtnWsItemMasterInfoBean().getItemMasterSid());
			if (itemMaster != null) {
				infoBean.setItemMasterSid(itemMaster.getItemMasterSid());
				infoBean.setItemId(itemMaster.getItemId());
				infoBean.setItemNo(itemMaster.getItemNo());
				infoBean.setItemName(itemMaster.getItemName());
				infoBean.setItemDesc(itemMaster.getItemDesc());
				infoBean.setItemStatus(getHelpervalue(itemMaster.getHelperTableByItemStatus()));
				infoBean.setItemStartDate(itemMaster.getItemStartDate());
				infoBean.setItemEndDate(itemMaster.getItemEndDate());
				infoBean.setItemType(getHelpervalue(itemMaster.getHelperTableByItemType()));
				infoBean.setTherapeuticClass(getHelpervalue(itemMaster.getHelperTableByTherapeuticClass()));
				infoBean.setBrandMasterSid(itemMaster.getBrandMaster().getBrandMasterSid());
				infoBean.setItemClass(getHelpervalue(itemMaster.getHelperTableByItemClass()));
				infoBean.setForm(getHelpervalue(itemMaster.getHelperTableByForm()));
				infoBean.setStrength(getHelpervalue(itemMaster.getHelperTableByStrength()));
				infoBean.setFirstSaleDate(itemMaster.getFirstSaleDate());
				infoBean.setNdc9(itemMaster.getNdc9());
				infoBean.setNdc8(itemMaster.getNdc8());
				infoBean.setPrimaryUom(getHelpervalue(itemMaster.getHelperTableByPrimaryUom()));
				infoBean.setSecondaryUom(getHelpervalue(itemMaster.getHelperTableBySecondaryUom()));
				infoBean.setLabelerCode(itemMaster.getLabelerCode());
				infoBean.setItemCode(itemMaster.getItemCode());
				infoBean.setPackageSize(getHelpervalue(itemMaster.getHelperTableByPackageSize()));
				infoBean.setPackageSizeCode(itemMaster.getPackageSizeCode());
				infoBean.setItemTypeIndication(getHelpervalue(itemMaster.getHelperTableByItemTypeIndication()));
				infoBean.setPackageSizeIntroDate(itemMaster.getPackageSizeIntroDate());
				infoBean.setManufacturerId(
						(itemMaster.getManufacturerId() == null || itemMaster.getManufacturerId().isEmpty()
								|| GtnFrameworkCommonStringConstants.STRING_NULL.equals(itemMaster.getManufacturerId()))
										? 0 : Integer.valueOf(itemMaster.getManufacturerId()));
				Udcs udcs = getUdcs(infoBean, session);
				if (udcs != null) {
					infoBean.setUdc1(getHelpervalue(udcs.getHelperTableByUdc1()));
					infoBean.setUdc2(getHelpervalue(udcs.getHelperTableByUdc2()));
					infoBean.setUdc3(getHelpervalue(udcs.getHelperTableByUdc3()));
					infoBean.setUdc4(getHelpervalue(udcs.getHelperTableByUdc4()));
					infoBean.setUdc5(getHelpervalue(udcs.getHelperTableByUdc5()));
					infoBean.setUdc6(getHelpervalue(udcs.getHelperTableByUdc6()));
				}
				infoBean.setCompanyMasterSid(itemMaster.getCompanyMaster().getCompanyMasterSid());
				infoBean.setDosesPerUnit(itemMaster.getDosesPerUnit());
				infoBean.setShelfLife(itemMaster.getShelfLife());
				infoBean.setShelfLifeType(getHelpervalue(itemMaster.getHelperTableByShelfLifeType()));
				infoBean.setLastLotExpirationDate(itemMaster.getLastLotExpirationDate());
				infoBean.setAuthorizedGenericStartDate(itemMaster.getAuthorizedGenericStartDate());
				infoBean.setPediatricExclusiveStartDate(itemMaster.getPediatricExclusiveStartDate());
				infoBean.setClottingFactorStartDate(itemMaster.getClottingFactorStartDate());
				infoBean.setDiscontinuationDate(itemMaster.getDiscontinuationDate());
				infoBean.setAuthorizedGenericEndDate(itemMaster.getAuthorizedGenericEndDate());
				infoBean.setPediatricExclusiveEndDate(itemMaster.getPediatricExclusiveEndDate());
				infoBean.setClottingFactorEndDate(itemMaster.getClottingFactorEndDate());
				infoBean.setDivestitureDate(itemMaster.getDivestitureDate());
				infoBean.setAcquisitionDate(itemMaster.getAcquisitionDate());
				infoBean.setNonFederalExpirationDate(itemMaster.getNonFederalExpirationDate());
				infoBean.setMarketTerminationDate(itemMaster.getMarketTerminationDate());
				infoBean.setNewFormulationStartDate(itemMaster.getNewFormulationStartDate());
				infoBean.setBaseCpiPeriod(itemMaster.getBaseCpiPeriod());
				infoBean.setNewFormulationEndDate(itemMaster.getNewFormulationEndDate());
				infoBean.setAuthorizedGeneric(itemMaster.getAuthorizedGeneric());
				infoBean.setPediatricExclusiveIndicator(itemMaster.getPediatricExclusiveIndicator());
				infoBean.setClottingFactorIndicator(itemMaster.getClottingFactorIndicator());
				infoBean.setDualPricingIndicator(itemMaster.getDualPricingIndicator());
				infoBean.setNewFormulationIndicator(itemMaster.getNewFormulationIndicator());
				infoBean.setUpps(getDoublevalue(itemMaster.getUpps()));
				infoBean.setBaselineAmp(itemMaster.getBaselineAmp());
				
				infoBean.setCreatedBy(itemMaster.getCreatedBy());
				infoBean.setCreatedByUserName(
						gtnWebServiceAllListConfig.getUserIdNameMap().get(itemMaster.getCreatedBy()));
				infoBean.setCreatedDate(itemMaster.getCreatedDate());
				infoBean.setModifiedBy(itemMaster.getModifiedBy());
				infoBean.setModifiedUserName(
						gtnWebServiceAllListConfig.getUserIdNameMap().get(itemMaster.getModifiedBy()));
				infoBean.setModifiedDate(itemMaster.getModifiedDate());
				infoBean.setSource(itemMaster.getSource());
				infoBean.setRecordLockStatus(itemMaster.isRecordLockStatus());
				infoBean.setBatchId(itemMaster.getBatchId());
				infoBean.setInternalNotes(itemMaster.getInternalNotes());
				infoBean.setNewFormulation(itemMaster.getNewFormulation());
				infoBean.setItemCategory(getHelpervalue(itemMaster.getHelperTableByItemCategory()));
                                setValuesInInfoBean(infoBean, itemMaster);

			}
			bean.setGtnWsItemMasterInfoBean(infoBean);
			bean.setGtnWsItemIdentifierBeanList(
					getItemIdentifier(session, bean.getGtnWsItemMasterInfoBean().getItemMasterSid()));
			createTembTable(gtnWsRequest);
			bean.setNoteBeanList(getNotesTabDetails(bean.getGtnWsItemMasterInfoBean().getItemMasterSid()));
			bean.setNoteBeanList(getCfpNotesTabAttachDetails(bean.getGtnWsItemMasterInfoBean().getItemMasterSid()));
			
			GtnWsItemMasterResponse reponse = new GtnWsItemMasterResponse();
			reponse.setGtnWsItemMasterBean(bean);
			gtnResponse.setGtnWsItemMasterResponse(reponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in fetchItemMaster ", e);
		} finally {
			session.close();
		}

	}

	Integer getHelpervalue(Object value) {
		return value == null ? 0 : ((HelperTable) value).getHelperTableSid();
	}

	Double getDoublevalue(BigDecimal value) {
		return value == null ? null : value.doubleValue();
	}

	@SuppressWarnings("unchecked")
	private Udcs getUdcs(GtnWsItemMasterInfoBean infoBean, Session session) throws GtnFrameworkGeneralException {
		Criterion itemMasterSIDCriterion = Restrictions.eq("masterSid", infoBean.getItemMasterSid());

		List<Udcs> results = (List<Udcs>) gtnSqlQueryEngine.executeSelectQuery(Udcs.class,
				Arrays.asList(new Criterion[] { itemMasterSIDCriterion }), session);

		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<GtnWsItemIdentifierBean> getItemIdentifier(Session session, int itemSystemId)
			throws GtnFrameworkGeneralException {
		List<GtnWsItemIdentifierBean> identifierBeanList = new ArrayList<>();

		Criterion itemMasterSIDCriterion = Restrictions.eq(GtnWsTableConstants.ITEM_MASTER_NAME,
				session.load(ItemMaster.class, itemSystemId));
		Criterion identifierCriterion = Restrictions.ne("inboundStatus", 'D');
		List<ItemIdentifier> results = (List<ItemIdentifier>) gtnSqlQueryEngine.executeSelectQuery(ItemIdentifier.class,
				Arrays.asList(new Criterion[] { itemMasterSIDCriterion, identifierCriterion }), session);

		if (results != null && !results.isEmpty()) {
			GtnWsItemIdentifierBean idenBean;
			for (ItemIdentifier object : results) {
				idenBean = new GtnWsItemIdentifierBean();
				idenBean.setItemIdentifierSid(object.getItemIdentifierSid());
				idenBean.setItemIdentifierValue(object.getItemIdentifierValue());
				idenBean.setItemIdentifierStatus(object.getHelperTable().getHelperTableSid());
				idenBean.setItemQualifierMasterSid(object.getItemQualifier().getItemQualifierSid());
				idenBean.setItemQualifierName(object.getItemQualifier().getItemQualifierName());
				idenBean.setStartDate(object.getStartDate());
				idenBean.setEndDate(object.getEndDate());
				idenBean.setEntityCode(object.getEntityCode() == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
						: object.getEntityCode());
				idenBean.setModifiedBy(object.getModifiedBy());
				idenBean.setModifiedByAsName(gtnWebServiceAllListConfig.getUserIdNameMap().get(object.getModifiedBy()));
				idenBean.setModifiedDate(object.getModifiedDate());
				idenBean.setCreatedBy(object.getCreatedBy());
				idenBean.setCreatedByAsName(gtnWebServiceAllListConfig.getUserIdNameMap().get(object.getCreatedBy()));
				idenBean.setCreatedDate(object.getCreatedDate());
				idenBean.setItemIdentifierStatusDes(object.getHelperTable().getDescription());
				identifierBeanList.add(idenBean);
			}
		}

		return identifierBeanList;

	}

	@SuppressWarnings("unchecked")
	public void deleteItemMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		int itemSystemId = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
				.getGtnWsItemMasterInfoBean().getItemMasterSid();

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		char inboundStatus = 'D';
		boolean itemPresentInContract = false;
		boolean itemPresentInIfp = false;
		try {

			ItemMaster itemMaster = session.get(ItemMaster.class, itemSystemId);
			itemMaster.setInboundStatus(inboundStatus);
			session.saveOrUpdate(itemMaster);

			Criterion itemMasterSIDCriterion = Restrictions.eq("itemMaster",
					session.load(ItemMaster.class, itemSystemId));

			List<ItemIdentifier> identifierResults = (List<ItemIdentifier>) gtnSqlQueryEngine.executeSelectQuery(
					ItemIdentifier.class, Arrays.asList(new Criterion[] { itemMasterSIDCriterion }));

			if (identifierResults != null && !identifierResults.isEmpty()) {
				for (ItemIdentifier itemIdentifier : identifierResults) {
					LOGGER.info("Deleting item identifier " + itemIdentifier.getItemIdentifierSid());
					itemIdentifier.setInboundStatus(inboundStatus);
					session.saveOrUpdate(itemIdentifier);
				}

			}

			List<ItemPricing> pricingResults = (List<ItemPricing>) gtnSqlQueryEngine
					.executeSelectQuery(ItemPricing.class, Arrays.asList(new Criterion[] { itemMasterSIDCriterion }));
			if (pricingResults != null && !pricingResults.isEmpty()) {
				for (ItemPricing itemPricing : pricingResults) {
					LOGGER.info("Deleting item pricing " + itemPricing.getItemPricingSid());
					itemPricing.setInboundStatus(inboundStatus);
					session.saveOrUpdate(itemPricing);
				}

			}

			GtnWsItemMasterResponse reponse = new GtnWsItemMasterResponse();
			reponse.setItemPresentInContract(itemPresentInContract);
			reponse.setItemPresentInIfp(itemPresentInIfp);
			gtnResponse.setGtnWsItemMasterResponse(reponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in deleteItemMaster ", e);
		} finally {
			session.close();
		}

	}

	boolean checkIfItemExist(List<Long> results) {
		return results != null && !results.isEmpty() && results.get(0) > 0;
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		LOGGER.info("Enter getNotesTabDetails");
		String cmNotesTabDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_SELECT + systemId
				+ " AND MASTER_TABLE_NAME='ITEM_MASTER'";
		List<Object[]> cmNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		return GtnCommonUtil.getNotesTabBean(cmNotesDetailsResultList, gtnWebServiceAllListConfig);
	}
	

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getCfpNotesTabAttachDetails(int systemId) throws GtnFrameworkGeneralException {
		LOGGER.info("Enter getitemMasterNotesTabAttachDetails");
		String cmNotesTabDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_ATTACHMENT_SELECT + +systemId
				+ " AND MASTER_TABLE_NAME='ITEM_MASTER'";
		List<Object[]> cmNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		return GtnCommonUtil.getNotesTabBean(cmNotesDetailsResultList, gtnWebServiceAllListConfig);
	}

	public void deleteItemQualifier(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		int itemQualifierSid = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemQualifierBean()
				.getItemQualifierSid();

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.delete(session.load(ItemQualifier.class, itemQualifierSid));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in deleteItemQualifier ", e);
		} finally {
			session.close();
		}

	}

	public void deletePricingQualifier(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		int pricingQualifierSid = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemPricingQualifierBean()
				.getItemPricingQualifierSid();

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.delete(session.load(ItemPricingQualifier.class, pricingQualifierSid));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in deleteItemQualifier ", e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void loadPricingTempData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException, ParseException, SQLException {
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "itemPricingCount" : "itemPricingResult";
		List<Object> inputlist = new ArrayList<>();
		String sysCatalog;
		try (Connection connection = getSysSessionFactory().getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			sysCatalog = connection.getCatalog();
		}
		inputlist.add(sysCatalog);
		inputlist.add(sysCatalog);
		inputlist.add(getWherecOnditions(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()));
		inputlist.add(sortedInput("ITEM_PRICING_QUALIFIER_NAME", "ASC",
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList(), getPricingColumnMap()));
		inputlist.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
		inputlist.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		List<Object[]> result = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
				gtnWsSqlService.getQuery(inputlist, queryName).replaceAll(ITEM_PRICING_TEMP_TABLE_REGEX,
						generateTempTableName(ITEM_PRICING_TABLE_NAME,
								gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
								gtnWsRequest.getGtnWsGeneralRequest().getSessionId())));

		if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
		} else {
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(result);
			gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		}
		gtnResponse.setGtnSerachResponse(gtnSerachResponse);

	}

	private String getWherecOnditions(List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList)
			throws ParseException {
		StringBuilder conditionBuilder = new StringBuilder();
		Map<String, String> columnIdMapping = getPricingColumnMap();
		for (Iterator<GtnWebServiceSearchCriteria> iterator = gtnWebServiceSearchCriteriaList.iterator(); iterator
				.hasNext();) {
			conditionBuilder.append(" AND  ");
			GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = iterator.next();
			new GtnWsSearchQueryGenerationLogic().generateSqlBasedOnExpression(gtnWebServiceSearchCriteria,
					conditionBuilder, columnIdMapping.get(gtnWebServiceSearchCriteria.getFieldId()));

		}
		return conditionBuilder.toString();

	}

	public void createTembTable(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		int sid = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
				.getItemMasterSid();
		if (sid != 0) {
			String query = getQueryAndReplaceWithTempTable(gtnWsRequest,
					Arrays.asList(" ", " where ITEM_MASTER_SID = ? "));
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, new Object[] { sid },
					new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		} else {
			String query = getQueryAndReplaceWithTempTable(gtnWsRequest, Arrays.asList("TOP 0", " "));
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		}

	}

	@SuppressWarnings("rawtypes")
	private String getQueryAndReplaceWithTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest, List queryParams) {
		String query = gtnWsSqlService.getQuery(queryParams, "createTembTable");
		query = query.replaceAll(ITEM_PRICING_TEMP_TABLE_REGEX,
				generateTempTableName(ITEM_PRICING_TABLE_NAME, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));
		return query;
	}

	private Map<String, String> getPricingColumnMap() {
		Map<String, String> pricingColumnMaping = new HashMap<>();
		pricingColumnMaping.put(GtnFrameworkCommonConstants.PRICING_QUALIFIER_NAME, "ITEM_PRICING_QUALIFIER_NAME");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.ITEM_PRICE, "ITEM_PRICE");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.PRICING_STATUS_ID, "PRICING_CODE_STATUS");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.ITEM_UOM_PARAM, "ITEM_UOM");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.START_DATE, "START_DATE");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.END_DATE, "END_DATE");
		pricingColumnMaping.put(GtnFrameworkCommonConstants.ENTITY_CODE, "ENTITY_CODE");
		pricingColumnMaping.put("source", "\"SOURCE\"");
		pricingColumnMaping.put("modifiedBy", "MODIFIED_BY");
		pricingColumnMaping.put("modifiedDate", "MODIFIED_DATE");
		pricingColumnMaping.put("createdBy", "CREATED_BY");
		pricingColumnMaping.put("createdDate", "CREATED_DATE");
		pricingColumnMaping.put("pricingStatusDes", "prcingstatusdesc");
		pricingColumnMaping.put("itemUOMDes", "itemUomdesc");
		return pricingColumnMaping;
	}

	private String sortedInput(String defaultOrderColumn, String defalutOrder,
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList,
			Map<String, String> columnPropertyMap) {
		StringBuilder orderByBuilder = new StringBuilder();
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			for (Iterator<GtnWebServiceOrderByCriteria> iterator = gtnWebServiceOrderByCriteriaList.iterator(); iterator
					.hasNext();) {
				GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria = iterator.next();
				orderByBuilder.append(" ").append(columnPropertyMap.get(gtnWebServiceOrderByCriteria.getPropertyId()))
						.append(" ").append(gtnWebServiceOrderByCriteria.getOrderByCriteria());
			}
		} else {
			orderByBuilder.append(" ").append(defaultOrderColumn).append(" ").append(defalutOrder);
		}
		return orderByBuilder.toString();
	}

	private String generateTempTableName(String tableName, String userId, String sessionId) {
		return tableName + "_" + userId + "_" + sessionId;
	}

	public void updateData(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException, ParseException {
		String query = gtnWsSqlService.getQuery("updateinPricingTempTable");
		query = query.replace("columnName", getPricingColumnMap().get(gtnWsRequest.getGtnWsItemMasterRequest()
				.getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().getPopulateField()));
		query = replaceTempTableName(gtnWsRequest, query);
		GtnFrameworkDataType[] dataType;
		Object[] params;

		if (gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
				.getPopulateField().contains("Date")) {
			DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
			Date updateDate = null;
			if (gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
					.getPopulateValue() != null
					&& !"null".equalsIgnoreCase(gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
							.getGtnWsItemMasterInfoBean().getPopulateValue())) {
				updateDate = format.parse(gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
						.getGtnWsItemMasterInfoBean().getPopulateValue());
			} else {
				return;
			}
			dataType = new GtnFrameworkDataType[] { GtnFrameworkDataType.DATE, GtnFrameworkDataType.INTEGER };
			params = new Object[] { updateDate, Integer.parseInt(gtnWsRequest.getGtnWsItemMasterRequest()
					.getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().getPopulateIdentityId()) };
		} else if (gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean()
				.getPopulateField().equalsIgnoreCase("itemPrice")) {
			dataType = new GtnFrameworkDataType[] { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.INTEGER };
			Double itemPrice = 0D;
			String itemPriceValue = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
					.getGtnWsItemMasterInfoBean().getPopulateValue();
			if (itemPriceValue != null && !itemPriceValue.isEmpty()) {
				itemPrice = Double.parseDouble(itemPriceValue);
			}
			params = new Object[] { itemPrice, Integer.parseInt(gtnWsRequest.getGtnWsItemMasterRequest()
					.getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().getPopulateIdentityId()) };
		} else {
			String helperIdString = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
					.getGtnWsItemMasterInfoBean().getPopulateValue();
			int helperTableId = 0;
			if (!"null".equalsIgnoreCase(helperIdString)) {
				helperTableId = Integer.parseInt(helperIdString);
			}

			dataType = new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			params = new Object[] { helperTableId, Integer.parseInt(gtnWsRequest.getGtnWsItemMasterRequest()
					.getGtnWsItemMasterBean().getGtnWsItemMasterInfoBean().getPopulateIdentityId()) };

		}

		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, dataType);

	}

	private String replaceTempTableName(GtnUIFrameworkWebserviceRequest gtnWsRequest, String query) {
		return query.replaceAll(ITEM_PRICING_TEMP_TABLE_REGEX,
				generateTempTableName(ITEM_PRICING_TABLE_NAME, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));

	}

	public void insertInPricingTembTable(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		if (validateUniqueCombination(gtnWsRequest)) {

			String query = gtnWsSqlService.getQuery("insertInPricingTempTable");
			query = replaceTempTableName(gtnWsRequest, query);
			GntWsItemPricingBean pricingBean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
					.getGntWsItemPricingBean();

			Date today = new Date();
			Object[] params = new Object[] { pricingBean.getItemPricingQualifierSid(), pricingBean.getItemUom(),
					pricingBean.getItemPrice().toPlainString(), pricingBean.getPricingCodeStatus(),
					pricingBean.getEntityCode(), pricingBean.getStartDate(), pricingBean.getEndDate(),
					gtnWsRequest.getGtnWsGeneralRequest().getUserId(), today,
					gtnWsRequest.getGtnWsGeneralRequest().getUserId(), today };
			GtnFrameworkDataType[] typeParams = new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.STRING, GtnFrameworkDataType.DATE, GtnFrameworkDataType.DATE,
					GtnFrameworkDataType.STRING, GtnFrameworkDataType.DATE, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.DATE };
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, typeParams);
		} else {
			generateResponseWithValidationBean(gtnResponse);
			gtnResponse.getGtnWsItemMasterResponse().getGtnWsValidationBean()
					.setValidationMsg(GtnWsItemMasterContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_010);
		}
	}

	public void deletePricingData(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		String query = gtnWsSqlService.getQuery("deleteInPricingTempTable");
		query = replaceTempTableName(gtnWsRequest, query);
		GntWsItemPricingBean pricingBean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
				.getGntWsItemPricingBean();
		Object[] params = new Object[] { pricingBean.getItemPricingSid() };
		GtnFrameworkDataType[] typeParams = new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, typeParams);

	}

	private boolean validateUniqueCombination(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		String query = gtnWsSqlService.getQuery("pricingUniqueValidation");
		query = replaceTempTableName(gtnWsRequest, query);
		GntWsItemPricingBean pricingBean = gtnWsRequest.getGtnWsItemMasterRequest().getGtnWsItemMasterBean()
				.getGntWsItemPricingBean();
		Object[] params = new Object[] { pricingBean.getItemPricingQualifierSid(), pricingBean.getItemUom(),
				pricingBean.getPricingCodeStatus(), pricingBean.getStartDate() };
		GtnFrameworkDataType[] typeParams = new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.DATE };
		int count = Integer
				.parseInt(String.valueOf(gtnSqlQueryEngine.executeSelectQuery(query, params, typeParams).get(0)));
		return count == 0;
	}

	public void dropPricingTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		String sql = "DROP TABLE ##ITEM_PRICING_TEMP_TABLE";
		sql = replaceTempTableName(gtnWsRequest, sql);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(sql);
	}

	public void validatePricingData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {

		String sql = gtnWsSqlService.getQuery("pricingvalidation");
		sql = replaceTempTableName(gtnWsRequest, sql);
		String columnTaginSql = "COLUMNNAME";
		generateResponseWithValidationBean(gtnResponse);
		String itemPriceValidationQuery = sql.replaceAll("\\b" + columnTaginSql + "\\b", "ITEM_PRICE");
		int totalCount = getCountBasedOnCountQuery(itemPriceValidationQuery);
		if (totalCount > 0) {
			gtnResponse.getGtnWsItemMasterResponse().getGtnWsValidationBean()
					.setValidationMsg(GtnWsItemMasterContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_005);
			return;
		}

		String statusValidationQuery = sql.replaceAll("\\b" + columnTaginSql + "\\b", "PRICING_CODE_STATUS");
		totalCount = getCountBasedOnCountQuery(statusValidationQuery);
		if (totalCount > 0) {
			gtnResponse.getGtnWsItemMasterResponse().getGtnWsValidationBean()
					.setValidationMsg(GtnWsItemMasterContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_006);
			return;
		}

		String uomValidationQuery = sql.replaceAll("\\b" + columnTaginSql + "\\b", "ITEM_UOM");
		totalCount = getCountBasedOnCountQuery(uomValidationQuery);
		if (totalCount > 0) {
			gtnResponse.getGtnWsItemMasterResponse().getGtnWsValidationBean()
					.setValidationMsg(GtnWsItemMasterContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_004);
			return;
		}

		String startDateValidationQuery = sql.replaceAll("\\b" + columnTaginSql + "\\b", "START_DATE");
		totalCount = getCountBasedOnCountQuery(startDateValidationQuery);
		if (totalCount > 0) {
			gtnResponse.getGtnWsItemMasterResponse().getGtnWsValidationBean()
					.setValidationMsg(GtnWsItemMasterContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_003);
			return;
		}

	}

	private void generateResponseWithValidationBean(GtnUIFrameworkWebserviceResponse gtnResponse) {
		gtnResponse.setGtnWsItemMasterResponse(new GtnWsItemMasterResponse());
		gtnResponse.getGtnWsItemMasterResponse().setGtnWsValidationBean(new GtnWsValidationBean());
	}

	private int getCountBasedOnCountQuery(String statusValidationQuery) throws GtnFrameworkGeneralException {
		return Integer.parseInt(String.valueOf(gtnSqlQueryEngine.executeSelectQuery(statusValidationQuery).get(0)));

	}
        
        private void setValuesInInfoBean(GtnWsItemMasterInfoBean infoBean, ItemMaster itemMaster) {
            if (itemMaster.getBaseCpi() != null) {
                infoBean.setBaseCpi(
                        new BigDecimal(itemMaster.getBaseCpi().toString()).setScale(3, BigDecimal.ROUND_DOWN));
            }
            if (itemMaster.getAcquiredAmp() != null) {
                infoBean.setAcquiredAmp(itemMaster.getAcquiredAmp().intValue());
            }
            if (itemMaster.getAcquiredBamp() != null) {
                infoBean.setAcquiredBamp(itemMaster.getAcquiredBamp().intValue());
            }
            if (itemMaster.getDra() != null) {
                infoBean.setDra(itemMaster.getDra().intValue());
            }
            if (itemMaster.getObraBamp() != null) {
                infoBean.setObraBamp(itemMaster.getObraBamp().intValue());
            }
        }

}
