package com.stpl.gtn.gtn2o.ws.module.companygroups.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpDataBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.entity.companygroup.CompanyGroup;
import com.stpl.gtn.gtn2o.ws.entity.companygroup.CompanyGroupDetails;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.companygroups.config.GtnWebServiceCompanyGroupConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.companygroupreponse.GtnWsCompanyGroupResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsCompanyGrpService {
    public GtnWsCompanyGrpService(){
        /**
         * empty constructor
         */
    }

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @param gtnWsRequest
	 * @return
	 * @throws GtnFrameworkGeneralException
	 * 
	 */
	public int addCompanyQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			String sql = gtnWsSqlService.getQuery("getCompanyGrpAddDeclareQuery");

			List<GtnFrameworkDataType> dataTypeTemp = new ArrayList<>();
			dataTypeTemp.add(GtnFrameworkDataType.STRING);
			dataTypeTemp.add(GtnFrameworkDataType.STRING);

			List<Object> paramListTemp = new ArrayList<>();
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(sql);
			List<GtnCompanyGrpDataBean> beanList = gtnWsRequest.getGtnCompanyGroupRequest()
					.getGtnCompanyGrpDataBeanList();
			for (GtnCompanyGrpDataBean gtnCompanyGrpDataBean : beanList) {
				String subSql = gtnWsSqlService.getQuery("getCompanyGrpAddQuery");

				dataTypeTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnCompanyGrpDataBean.getCompanyGroupSid());

				dataTypeTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnCompanyGrpDataBean.getCompanyMasterSid());

				dataTypeTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(gtnCompanyGrpDataBean.getCompanyTradeClassSid());

				dataTypeTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(gtnCompanyGrpDataBean.getCompanyParentDetailsSid());

				dataTypeTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnCompanyGrpDataBean.getVersionNo());

				sqlQuery.append(subSql);
			}
			if (sqlQuery.length() > 0) {
				return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery.toString(), paramListTemp.toArray(),
						dataTypeTemp.toArray(new GtnFrameworkDataType[dataTypeTemp.size()]));
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in addCompanyQuery ", e);
		}
		return 0;
	}

	public int getAddAllQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String query = gtnWsSqlService.getQuery("getCompanyGrpAddAllQuery");
			GtnWsSearchQueryConfig mainClauseConfig = new GtnWebServiceCompanyGroupConfig().getSearchQueryConfigMap()
					.get("cGrpAddTabSearchQuery");
			GtnWsSearchQueryGenerationLogic gtnWsSearchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			query = query.replace("[$MAIN_QUERY_CLAUSE]", mainClauseConfig.getSearchQuery());
			query = query.replace("[$WHERE_CLAUSE]", gtnWsSearchQueryGenerationLogic.generateSearchQueryWhereClause());
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getCompanyGroupImtdCount ", e);
		}
	}

	public int getRemoveQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			StringBuilder sql = new StringBuilder();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

			List<GtnCompanyGrpDataBean> beanList = gtnWsRequest.getGtnCompanyGroupRequest()
					.getGtnCompanyGrpDataBeanList();
			List<GtnFrameworkDataType> dataTypeTemp = new ArrayList<>();
			List<Object> paramListTemp = new ArrayList<>();
			for (GtnCompanyGrpDataBean gtnCompanyGrpDataBean : beanList) {
				String query = gtnWsSqlService.getQuery("getCompanyGrpRemoveQuery");

				paramListTemp.add(gtnCompanyGrpDataBean.getCompanyMasterSid());
				dataTypeTemp.add(GtnFrameworkDataType.INTEGER);

				paramListTemp.add(generalWSRequest.getUserId());
				dataTypeTemp.add(GtnFrameworkDataType.STRING);

				paramListTemp.add(generalWSRequest.getSessionId());
				dataTypeTemp.add(GtnFrameworkDataType.STRING);

				sql.append(query);
			}
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sql.toString(), paramListTemp.toArray(),
					dataTypeTemp.toArray(new GtnFrameworkDataType[dataTypeTemp.size()]));
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error while executing Remove query ", e);
		}
	}

	public int getRemoveAllQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsSearchQueryConfig mainClauseConfig = new GtnWebServiceCompanyGroupConfig().getSearchQueryConfigMap()
					.get("cGrpAddTabSelectedSearchQuery");
			GtnWsSearchQueryGenerationLogic gtnWsSearchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			String query = "DELETE IMTD " + mainClauseConfig.getSearchQuery()
					+ gtnWsSearchQueryGenerationLogic.generateSearchQueryWhereClause();
			if (generalWSRequest.getUserId() != null) {
				query += " AND IMTD.USERS_ID = " + generalWSRequest.getUserId();
			}
			if (generalWSRequest.getSessionId() != null) {
				query += " AND IMTD.SESSION_ID = " + generalWSRequest.getSessionId();
			}
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error while getRemoveAllQuery query ", e);
		}
	}

	public void getCompanyGrpFetchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			int systemId = gtnWsRequest.getGtnCompanyGroupRequest().getGtnCompanyGroupBean()
					.getGtnCompanyGrpInformationBean().getCompanyGrpSid();
			CompanyGroup masterData = session.get(CompanyGroup.class, systemId);
			GtnCompanyGrpInformationBean cGrpInfo = new GtnCompanyGrpInformationBean();
			cGrpInfo.setCompanyGrpSid(masterData.getCompanyGroupSid());
			cGrpInfo.setCompanyGrpName(masterData.getCompanyGroupName());
			cGrpInfo.setCompanyGrpNo(masterData.getCompanyGroupNo());
			cGrpInfo.setCompanyGrpDesc(masterData.getCompanyGroupDescription());
			cGrpInfo.setVersionNo(masterData.getVersionNo());
			GtnWsCompanyGroupResponse cGrpResponse = new GtnWsCompanyGroupResponse();
			cGrpResponse.setGtnCompanyGrpInformationBean(cGrpInfo);
			response.setGtnWsCompanyGroupResponse(cGrpResponse);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCompanyGrpFetchQuery ", e);
		} finally {
			session.close();
		}

	}

	public int updateCompanyGrpDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCompanyGrpDetailsFetchQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId(), gtnWsRequest.getGtnCompanyGroupRequest()
							.getGtnCompanyGroupBean().getGtnCompanyGrpInformationBean().getCompanyGrpSid(), gtnWsRequest.getGtnCompanyGroupRequest()
							.getGtnCompanyGroupBean().getGtnCompanyGrpInformationBean().getVersionNo() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	public void getCompanyGrpDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			int systemId = gtnWsRequest.getGtnCompanyGroupRequest().getGtnCompanyGroupBean()
					.getGtnCompanyGrpInformationBean().getCompanyGrpSid();
			if (systemId != 0) {
				CompanyGroup masterData = null;
				masterData = session.load(CompanyGroup.class, systemId);
				@SuppressWarnings("unchecked")
				Set<CompanyGroupDetails> cmpyDetailsSet = masterData.getCompanyGroupDetailses();
				for (CompanyGroupDetails cmpDet : cmpyDetailsSet) {
					session.delete(cmpDet);
				}
				session.delete(masterData);
				tx.commit();
			}
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCompanyGrpDeleteQuery ", e);
		} finally {
			session.close();
		}

	}

	public int saveCompanyGrpQuery(GtnCompanyGroupBean bean) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CompanyGroup masterData = generateCompanyGroup(bean);
			Integer companyGroupSid = (Integer) session.save(masterData);
			tx.commit();
			return companyGroupSid;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveCompanyGrpQuery ", e);
		} finally {
			session.close();
		}
	}

	private CompanyGroup generateCompanyGroup(GtnCompanyGroupBean masterbean) {
		GtnCompanyGrpInformationBean companyGrpInfoBean = masterbean.getGtnCompanyGrpInformationBean();
		int userId = companyGrpInfoBean.getModifiedBy();
		return new CompanyGroup(companyGrpInfoBean.getCompanyGrpNo(), companyGrpInfoBean.getCompanyGrpName(),
				companyGrpInfoBean.getCompanyGrpDesc(), null, userId, new Date(), userId, new Date(), 1, null);
	}

	public void updateCompanyGrpQuery(GtnCompanyGroupBean bean) throws GtnFrameworkGeneralException {

		GtnCompanyGrpInformationBean infoBean = bean.getGtnCompanyGrpInformationBean();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CompanyGroup masterData = session.get(CompanyGroup.class, infoBean.getCompanyGrpSid());
			masterData.setModifiedBy(infoBean.getModifiedBy());
			masterData.setModifiedDate(new Date());
			masterData.setVersionNo(infoBean.getVersionNo());
			session.saveOrUpdate(masterData);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateCompanyGrpQuery ", e);
		} finally {
			session.flush();
			session.close();
		}

	}

	public int updateCompanyGrpDetailsTable(GtnUIFrameworkWebserviceRequest gtnWsRequest, int companyGrpSid, GtnCompanyGroupBean companyGrpBean)
			throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCompanyGrpDetailsUpdateQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId(), String.valueOf(companyGrpSid), companyGrpBean.getGtnCompanyGrpInformationBean().getVersionNo() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public void getCompanyGroupImtdCount(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsCompanyGroupResponse cGrpResponse = new GtnWsCompanyGroupResponse();
		GtnCompanyGrpValidationBean validationBean = gtnWsRequest.getGtnCompanyGroupRequest()
				.getGtnCompanyGrpValidationBean();
		try {
			String query = gtnWsSqlService.getQuery("getCompanyGrpValidationQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			int count = (int) ((List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, type)).get(0);
			validationBean.setImtdCount(count);
			cGrpResponse.setGtnCompanyGrpValidationBean(validationBean);
			gtnResponse.setGtnWsCompanyGroupResponse(cGrpResponse);

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			boolean isCompanyGrpName = false;
			boolean isCompanyGrpNo = false;

			int systemId = validationBean.getCompanyGrpMasterSid();
			Criteria cr = session.createCriteria(CompanyGroup.class)
					.add(Restrictions.eq("companyGroupName", validationBean.getCompanyGrpName()));
			if (systemId > 0) {
				cr.add(Restrictions.ne("companyGroupSid", systemId));
			}
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.countDistinct("companyGroupName"));
			cr.setProjection(proj);
			List<Long> results = cr.list();
			if (results != null && !results.isEmpty()) {
				isCompanyGrpName = (long) results.get(0) > 0;
			}
			Criteria cr2 = session.createCriteria(CompanyGroup.class)
					.add(Restrictions.eq("companyGroupNo", validationBean.getCompanyGrpNo()));
			if (systemId > 0) {
				cr2.add(Restrictions.ne("companyGroupSid", systemId));
			}
			ProjectionList proj2 = Projections.projectionList();
			proj2.add(Projections.countDistinct("companyGroupNo"));
			cr2.setProjection(proj2);

			List<Long> results2 = cr2.list();
			if (results2 != null && !results2.isEmpty()) {
				isCompanyGrpNo = (long) results2.get(0) > 0;
			}

			tx.commit();
			validationBean.setCompanyGrpNameExist(isCompanyGrpName);
			validationBean.setCompanyGrpNoExist(isCompanyGrpNo);
			cGrpResponse.setGtnCompanyGrpValidationBean(validationBean);
			gtnResponse.setGtnWsCompanyGroupResponse(cGrpResponse);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCompanyGroupImtdCount ", e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void getCompanyGroupNameValidation(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCustomerGrpNameDuplicateValidationQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING };
			Object[] params = { gtnWsRequest.getGtnCompanyGroupRequest().getGtnCompanyGroupBean()
					.getGtnCompanyGrpInformationBean().getCompanyGrpName() };
			List<Object> resultList = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, type);
			boolean customerGroupName = false;
			if (resultList != null && !resultList.isEmpty()) {
				String returnValue = resultList.get(0).toString().trim();
				customerGroupName = !returnValue.isEmpty() && returnValue.contains("CUSTOMER_GROUP_NAME");
			}
			GtnCompanyGrpValidationBean validationBean = gtnResponse.getGtnWsCompanyGroupResponse()
					.getGtnCompanyGrpValidationBean();
			validationBean.setCompanyGrpNameExist(customerGroupName);
			gtnResponse.getGtnWsCompanyGroupResponse().setGtnCompanyGrpValidationBean(validationBean);

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String query;
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				query = gtnWsSqlService.getQuery("getCompanyGrpSelectedTableCountQuery");
			} else {
				query = gtnWsSqlService.getQuery("getCompanyGrpSelectedTableQuery");
			}
			GtnWsSearchQueryConfig mainClauseConfig = new GtnWebServiceCompanyGroupConfig().getSearchQueryConfigMap()
					.get("cGrpAddTabSelectedSearchQuery");

			GtnWsSearchQueryGenerationLogic gtnWsSearchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			query += gtnWsSearchQueryGenerationLogic.generateSearchQueryWhereClause();

			if (generalWSRequest.getUserId() != null) {
				query += " AND IMTD.USERS_ID = " + generalWSRequest.getUserId();
			}
			if (generalWSRequest.getSessionId() != null) {
				query += " AND IMTD.SESSION_ID = " + generalWSRequest.getSessionId();
			}
			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				query += gtnWsSearchQueryGenerationLogic.generateSearchQueryOrderByAndOffset();
			}
			return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	public void clearCustomerGroupTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {

			String query = gtnWsSqlService.getQuery("customerGroupTempTableClear");

			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

}
